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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.dobajar.myapplication.Adapter.AllFragmentDataAdpr;
import com.dobajar.myapplication.Adapter.SubCategoryItem;
import com.dobajar.myapplication.Card.Cart_activity;
import com.dobajar.myapplication.Model.Retrofit.ApiClint;
import com.dobajar.myapplication.Model.Retrofit.RetrofitClint;
import com.dobajar.myapplication.Model.SubCategory.DatumSubCategory;
import com.dobajar.myapplication.Model.SubCategory.Subcategory;
import com.dobajar.myapplication.R;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class Babarege extends AppCompatActivity implements AllFragmentDataAdpr.OnItemClickListener {
    private TextView cartText;
    private ImageView cardImage;
    private static final String JSON_URL = "http://dobajar.regnumit.com/api/Food/subcategorious-products";
    private SubCategoryItem subCategoryItem;
    private ArrayList<DatumSubCategory> subcategoriesList;
    private RecyclerView foodDataRecycler;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.babarege);

        subcategoriesList = new ArrayList<DatumSubCategory>();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading Data...");
        progressDialog.setCancelable(true);

        foodDataRecycler = findViewById(R.id.food_data_recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        foodDataRecycler.setLayoutManager(mLayoutManager);
        foodDataRecycler.setHasFixedSize(true);

        LoadSubCategoryData();
    }

    private void LoadSubCategoryData() {
        progressDialog.show();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest arrayRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressDialog.dismiss();
                Log.i("Jsonlength", String.valueOf(response.length()));

                for (int i = 0; i < response.length(); i++) {
                    Toast.makeText(Babarege.this, "oky successfully: " + i, Toast.LENGTH_SHORT).show();
                    try {
                        JSONArray jsonArray= response.getJSONArray("data");
                        JSONObject jsonObject= jsonArray.getJSONObject(i);
                        DatumSubCategory subcategory= new DatumSubCategory();

                        subcategory.setTitle(jsonObject.getString("title").toString());
                        subcategory.setImage(jsonObject.getString("image").toString());
                        subcategory.setDescription(jsonObject.getString("description").toString());
                        subcategory.setPrice(jsonObject.getString("price").toString());
                        subcategory.setStock(jsonObject.getString("stock").toString());

                        Log.i("title", subcategory.getTitle().toString());
                        Log.i("image", subcategory.getImage().toString());
                        Log.i("description", subcategory.getDescription().toString());
                        Log.i("price", subcategory.getPrice().toString());
                        Log.i("stock", subcategory.getStock().toString());

                        subcategoriesList.add(subcategory);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                subCategoryItem = new SubCategoryItem(Babarege.this, subcategoriesList);
                foodDataRecycler.setAdapter(subCategoryItem);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(Babarege.this, "Error is: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("Error_is: ", error.getMessage());
            }
        });

        requestQueue.add(arrayRequest);
    }

    @Override
    public void OnItemClick(int position) {
        Intent intent = new Intent(this, ProductDetails.class);
        startActivity(intent);
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
                Toast.makeText(Babarege.this, "Card item", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Babarege.this, Cart_activity.class));
            }
        });
        LoadCardData();
        return super.onCreateOptionsMenu(menu);
    }

    private void LoadCardData() {
        SharedPreferences sharedPref = getSharedPreferences("UPLOAD_CARD_DATA", Context.MODE_PRIVATE);
        int loadCardCount = sharedPref.getInt("cardCount", 0);
        cartText.setVisibility(View.VISIBLE);
        cartText.setText(String.valueOf(loadCardCount));
    }
}
