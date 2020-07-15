package com.dobajar.myapplication.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dobajar.myapplication.Adapter.SubCategoriesAdapter;
import com.dobajar.myapplication.Card.Cart_activity;
import com.dobajar.myapplication.Model.Products.AllProducts;
import com.dobajar.myapplication.Model.Products.Datum;
import com.dobajar.myapplication.Model.Retrofit.ApiClint;
import com.dobajar.myapplication.Model.Retrofit.RetrofitClint;
import com.dobajar.myapplication.Model.SubCategories;
import com.dobajar.myapplication.Model.SubCategoriesModel.SubCategorieModel;
import com.dobajar.myapplication.Model.SuperCategories;
import com.dobajar.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllCategories extends AppCompatActivity{
    private TextView cartText;
    private ImageButton cardImage;
    RecyclerView recyclerViewSubcategories;
    private ApiClint apiClint;

    private static ArrayList<String> ctName;
    private static ArrayList<String> ctImage;
    private static ArrayList<String> subCategoriesList;

    SubCategoriesAdapter subCategoriesAdapter;
    private String categoriesID;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_categories);

        progressDialog= new ProgressDialog(this);
        progressDialog.setTitle("Loading Data...");
        progressDialog.setCancelable(true);

        ctName = new ArrayList<>();
        ctImage = new ArrayList<>();
        subCategoriesList= new ArrayList<>();

        categoriesID= Objects.requireNonNull(getIntent().getExtras()).getString("superShop");
        assert categoriesID != null;
        Log.i("you_want_to_see",categoriesID);

        recyclerViewSubcategories = findViewById(R.id.recylerview_subcategories);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2)  ;
        recyclerViewSubcategories.setLayoutManager(mLayoutManager);
        recyclerViewSubcategories.setHasFixedSize(true);

        GetAllSubCategoriesApi();

    }

    private void GetAllSubCategoriesApi() {
        progressDialog.show();
        apiClint= RetrofitClint.getRetrifitClint().create(ApiClint.class);
        Call<SubCategorieModel> call= apiClint.subCategoriesGet();

        call.enqueue(new Callback<SubCategorieModel>() {
            @Override
            public void onResponse(Call<SubCategorieModel> call, Response<SubCategorieModel> response) {
                progressDialog.dismiss();
                if (!response.isSuccessful()) {
                    Toast.makeText(AllCategories.this, "Response not oky", Toast.LENGTH_SHORT).show();
                }

                SubCategorieModel subCategorieModel= response.body();

                assert subCategorieModel != null;
                for (int i = 0; i<subCategorieModel.getData().size(); i++)
                {
                    if (subCategorieModel.getData().get(i).getCategoryId().equals(categoriesID))
                    {
                        Log.i("status", "equeal: "+ i);
                        ctName.add(subCategorieModel.getData().get(i).getName());
                        ctImage.add(subCategorieModel.getData().get(i).getImage());
                    }
                }

                Log.i("nameArraySize",String.valueOf(ctName.size()));
                Log.i("imageArraySize",String.valueOf(ctImage.size()));

                subCategoriesAdapter = new SubCategoriesAdapter(AllCategories.this, ctName, ctImage);
                recyclerViewSubcategories.setAdapter(subCategoriesAdapter);
            }

            @Override
            public void onFailure(Call<SubCategorieModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(AllCategories.this, "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("onFailedMSG", Objects.requireNonNull(t.getMessage()));
            }
        });
    }

    private void getAllProductApi() {
        progressDialog.setTitle("Get Products Categories");
        progressDialog.show();
        apiClint = RetrofitClint.getRetrifitClint().create(ApiClint.class);
        Call<AllProducts> call = apiClint.productsGet();

        call.enqueue(new Callback<AllProducts>() {
            @Override
            public void onResponse(Call<AllProducts> call, Response<AllProducts> response) {
                progressDialog.dismiss();
                if (!response.isSuccessful()) {
                    Toast.makeText(AllCategories.this, "Response not oky", Toast.LENGTH_SHORT).show();
                }
                AllProducts allProducts = response.body();

                for (int i=0; i<allProducts.getData().size(); i++){

                    subCategoriesList.add(allProducts.getData().get(i).getSubCategory());

                    Log.i("allSubCategories", allProducts.getData().get(i).getSubCategory());
                    Log.i("ctName", allProducts.getData().get(i).getTitle());
                    Log.i("ctImage", allProducts.getData().get(i).getImage().get(0));
                    Log.i("ctPrize", allProducts.getData().get(i).getPrice());

                }
            }

            @Override
            public void onFailure(Call<AllProducts> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(AllCategories.this, "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("onFailedMSG", t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem menuItem = menu.findItem(R.id.add_to_card_toolbar);
        View actionView = menuItem.getActionView();
        if (actionView != null) {
            cartText = actionView.findViewById(R.id.toolbarText);
            cardImage = actionView.findViewById(R.id.toolberCard);
            cartText.setVisibility(View.GONE);
        }
        cardImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AllCategories.this, "Card item", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AllCategories.this, Cart_activity.class));
            }
        });
        LoadCardData();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void LoadCardData() {
        SharedPreferences sharedPref = getSharedPreferences("UPLOAD_CARD_DATA", Context.MODE_PRIVATE);
        int loadCardCount = sharedPref.getInt("cardCount", 0);
        cartText.setVisibility(View.VISIBLE);
        cartText.setText(String.valueOf(loadCardCount));
    }

}
