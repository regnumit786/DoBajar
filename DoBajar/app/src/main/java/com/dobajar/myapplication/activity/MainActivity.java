package com.dobajar.myapplication.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.dobajar.myapplication.Card.Cart_activity;
import com.dobajar.myapplication.Loged.Login;
import com.dobajar.myapplication.Profile.ProfileActivity;
import com.dobajar.myapplication.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ActionBarDrawerToggle toggle;
    DrawerLayout drawer;
    private ImageView cardImage;
    private TextView cartText;
    MenuItem menuItem;

    ViewFlipper viewFlip;
    int[] image = {R.drawable.meenabazarslider, R.drawable.shwapnoslider, R.drawable.unimartslider, R.drawable.princebajarslider, R.drawable.dailyshopslider,
            R.drawable.agoraslider};

    private ImageView gotoSuperShop, gotoDobazarPackage, gotoHoleSell, gotoFamilyPack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (ConnectivityHelper.isConnectedToNetwork(this)) {
            //Show the connected screen
            Toast.makeText(this, "Loading Product", Toast.LENGTH_SHORT).show();
        } else {
            //Show disconnected screen
            Toast.makeText(this, "Not Connected!", Toast.LENGTH_SHORT).show();
        }

        drawer= findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        View header= navigationView.getHeaderView(0);

        ImageView imageViewheader= header.findViewById(R.id.profile_image);
        imageViewheader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });

        FindAllView();

        ImageSlider imageSlider= findViewById(R.id.sliderimage);
        List<SlideModel> slideModels= new ArrayList<>();
        slideModels.add(new SlideModel("http://dobajar.regnumit.com/backend/images/offer/offer-1.jpg",""));
        slideModels.add(new SlideModel("http://dobajar.regnumit.com/backend/images/offer/offer-8.jpg",""));
        slideModels.add(new SlideModel("http://dobajar.regnumit.com/backend/images/offer/offer-3.jpg",""));
        imageSlider.setImageList(slideModels, true);

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_meena_bazar);
        navigationView.getMenu().getItem(0).setActionView(R.layout.nav_right_icon);
        navigationView.setCheckedItem(R.id.nav_shwapno);
        navigationView.getMenu().getItem(1).setActionView(R.layout.nav_right_icon);
        navigationView.setCheckedItem(R.id.nav_prince_bazar);
        navigationView.getMenu().getItem(2).setActionView(R.layout.nav_right_icon);
        navigationView.setCheckedItem(R.id.nav_agora);
        navigationView.getMenu().getItem(3).setActionView(R.layout.nav_right_icon);
        navigationView.setCheckedItem(R.id.nav_unimart);
        navigationView.getMenu().getItem(4).setActionView(R.layout.nav_right_icon);
        navigationView.setCheckedItem(R.id.nav_daily_shop);
        navigationView.getMenu().getItem(5).setActionView(R.layout.nav_right_icon);

        gotoSuperShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SuperShop.class));
            }
        });

        gotoDobazarPackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DoBazarPackage.class));
            }
        });

        gotoHoleSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HoleSell.class));
            }
        });

        gotoFamilyPack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FamilyPackage.class));
            }
        });
    }

    public void FindAllView(){
        gotoSuperShop= findViewById(R.id.goto_supershop);
        gotoDobazarPackage= findViewById(R.id.goto_dobajarpackage);
        gotoHoleSell= findViewById(R.id.goto_holesell);
        gotoFamilyPack= findViewById(R.id.goto_family_pack);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("Exiting the DoBajar")
                    .setMessage("Are you sure?")
                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            dialog.dismiss();
                        }
                    }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).create().show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        menuItem= menu.findItem(R.id.add_to_card_toolbar);
        View actionView = menuItem.getActionView();
        if (actionView != null) {
            cartText= actionView.findViewById(R.id.toolbarText);
            cardImage= actionView.findViewById(R.id.toolberCard);
            cartText.setVisibility(View.GONE);
        }
        cardImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Card item", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, Cart_activity.class));
            }
        });
        LoadCardData();
        return super.onCreateOptionsMenu(menu) ;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.add_to_card_toolbar){
            Toast.makeText(this, "comming...", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_meena_bazar) {
            Intent intent= new Intent(MainActivity.this, AllCategories.class);
            intent.putExtra("superShop","Meena Bazar");
            startActivity(intent);
        } else if (id == R.id.nav_shwapno) {
            Intent intent= new Intent(MainActivity.this, AllCategories.class);
            intent.putExtra("superShop","Swapno");
            startActivity(intent);
        } else if (id == R.id.nav_prince_bazar) {
            Intent intent= new Intent(MainActivity.this, AllCategories.class);
            intent.putExtra("superShop","Unimart");
            startActivity(intent);
        } else if (id == R.id.nav_agora) {
            Intent intent= new Intent(MainActivity.this, AllCategories.class);
            intent.putExtra("superShop","AugoraShop");
            startActivity(intent);
        } else if (id == R.id.nav_unimart) {
            Intent intent= new Intent(MainActivity.this, AllCategories.class);
            intent.putExtra("superShop","PrinceShop");
            startActivity(intent);
        }else if (id == R.id.nav_daily_shop) {
            Intent intent= new Intent(MainActivity.this, AllCategories.class);
            intent.putExtra("superShop","DailyShop");
            startActivity(intent);
        } else if (id == R.id.nav_share){
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Share this");
            sendIntent.setType("text/plain");
            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
        } else if (id == R.id.nav_logout){
            SharedPreferences sharedPreferences= getSharedPreferences("STORE_PASSWORD_FOR_LOGIN",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor= sharedPreferences.edit();
            editor.clear();
            editor.apply();
            startActivity(new Intent(this, Login.class));
            finish();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void Flipper(int i){
        ImageView imageView= new ImageView(this);
        imageView.setBackgroundResource(i);
        viewFlip.addView(imageView);
        viewFlip.setFlipInterval(4000);
        viewFlip.setAutoStart(true);
        viewFlip.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlip.setOutAnimation(this, android.R.anim.slide_out_right);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onResume(){
        super.onResume();
        // put your code here...
    }

    private boolean isconnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();

    }

    public static class ConnectivityHelper {
        public static boolean isConnectedToNetwork(Context context) {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            boolean isConnected = false;
            if (connectivityManager != null) {
                NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
                isConnected = (activeNetwork != null) && (activeNetwork.isConnectedOrConnecting());
            }

            return isConnected;
        }
    }

    private void LoadCardData(){
        SharedPreferences sharedPref = getSharedPreferences("UPLOAD_CARD_DATA", Context.MODE_PRIVATE);
        int loadCardCount = sharedPref.getInt("cardCount", 0);
        cartText.setVisibility(View.VISIBLE);
        cartText.setText(String.valueOf(loadCardCount));
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPref = getSharedPreferences("STORE_LOCATION", Context.MODE_PRIVATE);
        String address= sharedPref.getString("your_home_address","default");
        Log.i("main_Address_is: ",address);
        if (address.isEmpty() || address.equals("default")){
            Intent intent= new Intent(this, SelectLocation.class);
            startActivity(intent);
            finish();
        }
    }
}
