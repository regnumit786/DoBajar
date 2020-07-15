package com.dobajar.myapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dobajar.myapplication.Adapter.SubCategoriesAdapter;
import com.dobajar.myapplication.Card.Cart_activity;
import com.dobajar.myapplication.Model.Retrofit.ApiClint;
import com.dobajar.myapplication.Model.Retrofit.RetrofitClint;
import com.dobajar.myapplication.Model.SubCategoriesModel.SubCategorieModel;
import com.dobajar.myapplication.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuperShop extends AppCompatActivity {

    private ImageView gotoMeenaShop, gotoShowpnoShop, gotoUnimartShop, gotoAugoraShop, gotoPrinceShop, gotoDailyShop;
    private TextView cartText;
    private ImageView cardImage;
    private ApiClint apiClint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_shop);

        FindAllView();

        gotoMeenaShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(SuperShop.this, AllCategories.class);
                intent.putExtra("superShop","Meena Bazar");
                startActivity(intent);
            }
        });

        gotoShowpnoShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(SuperShop.this, AllCategories.class);
                intent.putExtra("superShop","Swapno");
                startActivity(intent);
            }
        });

        gotoUnimartShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(SuperShop.this, AllCategories.class);
                intent.putExtra("superShop","Unimart");
                startActivity(intent);
            }
        });

        gotoAugoraShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(SuperShop.this, AllCategories.class);
                intent.putExtra("superShop","AugoraShop");
                startActivity(intent);
            }
        });

        gotoPrinceShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(SuperShop.this, AllCategories.class);
                intent.putExtra("superShop","PrinceShop");
                startActivity(intent);
            }
        });

        gotoDailyShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(SuperShop.this, AllCategories.class);
                intent.putExtra("superShop","DailyShop");
                startActivity(intent);
            }
        });

        GetAllSubCategoriesApi();
    }

    private void GetAllSubCategoriesApi() {
        apiClint= RetrofitClint.getRetrifitClint().create(ApiClint.class);
        Call<SubCategorieModel> call= apiClint.subCategoriesGet();
        call.enqueue(new Callback<SubCategorieModel>() {
            @Override
            public void onResponse(Call<SubCategorieModel> call, Response<SubCategorieModel> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(SuperShop.this, "Response not oky", Toast.LENGTH_SHORT).show();
                }

                SubCategorieModel subCategorieModel= response.body();

                for (int i=0; i<subCategorieModel.getData().size(); i++) {

                    Log.i("ctName", subCategorieModel.getData().get(i).getName());
                    Log.i("ctImage", subCategorieModel.getData().get(i).getImage());
                    Log.i("ctCategories", subCategorieModel.getData().get(i).getCategoryId());
                }
            }

            @Override
            public void onFailure(Call<SubCategorieModel> call, Throwable t) {
                Toast.makeText(SuperShop.this, "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("onFailedMSG", t.getMessage());
            }
        });
    }

    public void FindAllView(){
        gotoMeenaShop= findViewById(R.id.goto_meenabazar);
        gotoShowpnoShop= findViewById(R.id.goto_showpno);
        gotoUnimartShop= findViewById(R.id.goto_unimart);
        gotoAugoraShop= findViewById(R.id.goto_augora);
        gotoPrinceShop= findViewById(R.id.goto_princebajar);
        gotoDailyShop= findViewById(R.id.goto_dailyshop);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem menuItem = menu.findItem(R.id.add_to_card_toolbar);
        View actionView= menuItem.getActionView();
        if (actionView!= null) {
            cartText= actionView.findViewById(R.id.toolbarText);
            cardImage= actionView.findViewById(R.id.toolberCard);
            cartText.setVisibility(View.GONE);
        }
        cardImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SuperShop.this, "Card item", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SuperShop.this, Cart_activity.class));
            }
        });
        LoadCardData();
        return super.onCreateOptionsMenu(menu);
    }

    private void LoadCardData(){
        SharedPreferences sharedPref = getSharedPreferences("UPLOAD_CARD_DATA", Context.MODE_PRIVATE);
        int loadCardCount = sharedPref.getInt("cardCount", 0);
        cartText.setVisibility(View.VISIBLE);
        cartText.setText(String.valueOf(loadCardCount));
    }
}
