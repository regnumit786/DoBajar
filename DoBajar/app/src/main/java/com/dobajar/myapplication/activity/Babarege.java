package com.dobajar.myapplication.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dobajar.myapplication.Adapter.AllFragmentDataAdpr;
import com.dobajar.myapplication.Adapter.SubCategoryItem;
import com.dobajar.myapplication.Card.Cart_activity;
import com.dobajar.myapplication.Model.Retrofit.ApiClint;
import com.dobajar.myapplication.Model.Retrofit.RetrofitClint;
import com.dobajar.myapplication.Model.SubCategory.Subcategory;
import com.dobajar.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Babarege extends AppCompatActivity implements AllFragmentDataAdpr.OnItemClickListener {

    private final String TAG= this.getClass().getSimpleName();
    private TextView cartText;
    private ImageView cardImage;

    private ArrayList<String> itemTitle;
    private ArrayList<String> itemImage;
    private ArrayList<String> itemDescription;
    private ArrayList<String> itemPrize;
    private ArrayList<String> itemStock;
    private SubCategoryItem subCategoryItem;

    private ApiClint apiClint;
    private RecyclerView foodDataRecycler;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.babarege);

        progressDialog= new ProgressDialog(this);
        progressDialog.setTitle("Loading Data...");
        progressDialog.setCancelable(true);


        foodDataRecycler = findViewById(R.id.food_data_recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2)  ;
        foodDataRecycler.setLayoutManager(mLayoutManager);
        foodDataRecycler.setHasFixedSize(true);

        LoadSubCategoryData();
    }

    private void LoadSubCategoryData() {
        progressDialog.show();
        apiClint= RetrofitClint.getRetrifitClint().create(ApiClint.class);
        Call<Subcategory> call= apiClint.subCategoryList();

        call.enqueue(new Callback<Subcategory>() {
            @Override
            public void onResponse(Call<Subcategory> call, Response<Subcategory> response) {
                progressDialog.dismiss();
                if (!response.isSuccessful()){
                    Toast.makeText(Babarege.this, "response not found", Toast.LENGTH_LONG).show();
                    Log.i(TAG, response.message());
                }

                Subcategory subcategory= response.body();

                assert subcategory != null;
                for (int i = 0; i<subcategory.getData().size(); i++) {
                    itemTitle.add(subcategory.getData().get(i).getTitle());
                    itemImage.add(subcategory.getData().get(i).getImage());
                    itemDescription.add(subcategory.getData().get(i).getDescription());
                    itemPrize.add(subcategory.getData().get(i).getPrice());
                    itemStock.add(subcategory.getData().get(i).getStock());
                }
                subCategoryItem= new SubCategoryItem(Babarege.this, itemTitle, itemImage, itemDescription, itemPrize, itemStock);
                foodDataRecycler.setAdapter(subCategoryItem);
            }

            @Override
            public void onFailure(Call<Subcategory> call, Throwable t) {

            }
        });
    }

    @Override
    public void OnItemClick(int position) {
        Intent intent= new Intent(this, ProductDetails.class);
        startActivity(intent);
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
                Toast.makeText(Babarege.this, "Card item", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Babarege.this, Cart_activity.class));
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
