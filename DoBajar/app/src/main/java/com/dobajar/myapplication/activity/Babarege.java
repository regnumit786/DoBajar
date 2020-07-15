package com.dobajar.myapplication.activity;

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

import com.dobajar.myapplication.Adapter.AllFragmentDataAdpr;
import com.dobajar.myapplication.Card.Cart_activity;
import com.dobajar.myapplication.R;


public class Babarege extends AppCompatActivity implements AllFragmentDataAdpr.OnItemClickListener {

    private final String TAG= this.getClass().getSimpleName();
    private ImageButton addToBagInciment, addToBagDecriment, addToBagInciment2, addToBagDecriment2;
    private LinearLayout layout, layout2;
    private TextView addToBagText, addToBagText2, productItemCount, productItemCount2;
    int count=0;
    int count2=0;
    int loadCardCount=0, addToCartItem1=0, addToCartItem2=0, totalCartItem=0;
    private TextView cartText;
    private ImageView cardImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.babarege);

        FindAllView();

        if (count>0){
            addToBagText.setVisibility(View.GONE);
            productItemCount.setVisibility(View.VISIBLE);
            addToBagDecriment.setVisibility(View.VISIBLE);
        } else {
            addToBagText.setVisibility(View.VISIBLE);
            addToBagDecriment.setVisibility(View.GONE);
            productItemCount.setVisibility(View.GONE);
        }

        if (count2>0){
            addToBagText2.setVisibility(View.GONE);
            productItemCount2.setVisibility(View.VISIBLE);
            addToBagDecriment2.setVisibility(View.VISIBLE);
        } else {
            addToBagText2.setVisibility(View.VISIBLE);
            addToBagDecriment2.setVisibility(View.GONE);
            productItemCount2.setVisibility(View.GONE);
        }

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareStoreData1();
                startActivity(new Intent(Babarege.this, ProductDetails.class));
            }
        });

        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareStoreData2();
                startActivity(new Intent(Babarege.this, ProductDetails.class));
            }
        });

        addToBagInciment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCartItem1=1;
                count++;
                addToBagText.setVisibility(View.GONE);
                addToBagDecriment.setVisibility(View.VISIBLE);
                productItemCount.setVisibility(View.VISIBLE);
                productItemCount.setText(String.valueOf(count));
                ShareStoreData1();
            }
        });

        addToBagInciment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCartItem2=1;
                count2++;
                Log.e("print count 2", String.valueOf(count2));
                addToBagText2.setVisibility(View.GONE);
                addToBagDecriment2.setVisibility(View.VISIBLE);
                productItemCount2.setVisibility(View.VISIBLE);
                productItemCount2.setText(String.valueOf(count2));
                Log.e("print after show", productItemCount2.toString());
                ShareStoreData2();
            }
        });

        addToBagDecriment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count>0){
                    count--;
                    productItemCount.setText(String.valueOf(count));
                    if (count<=0){
                        addToCartItem1= addToCartItem1-1;
                        addToBagText.setVisibility(View.VISIBLE);
                        addToBagDecriment.setVisibility(View.GONE);
                        productItemCount.setVisibility(View.GONE);
                    }
                }
            }
        });

        addToBagDecriment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count2>0){
                    Log.e("print decrement", String.valueOf(count2));
                    count2--;
                    productItemCount2.setText(String.valueOf(count2));
                    if (count2<=0){
                        addToCartItem2= addToCartItem2-1;
                        addToBagText2.setVisibility(View.VISIBLE);
                        addToBagDecriment2.setVisibility(View.GONE);
                        productItemCount2.setVisibility(View.GONE);
                    }
                }
            }
        });

        totalCartItem= addToCartItem1+addToCartItem2;
    }

    private void FindAllView(){
        addToBagInciment= findViewById(R.id.add_to_bag_count_incre);
        addToBagInciment2= findViewById(R.id.add_to_bag_count_incre2);
        addToBagDecriment= findViewById(R.id.add_to_bag_count_dec);
        addToBagDecriment2= findViewById(R.id.add_to_bag_count_dec2);
        layout= findViewById(R.id.layout_one);
        layout2= findViewById(R.id.layout_two);
        addToBagText= findViewById(R.id.add_to_bag_count_text);
        addToBagText2= findViewById(R.id.add_to_bag_count_text2);
        productItemCount= findViewById(R.id.product_item_count);
        productItemCount2= findViewById(R.id.product_item_count2);
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

    private void ShareStoreData1(){
        SharedPreferences sharedPref2 = getSharedPreferences("STORE_PRODUCT_DETAILS_DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref2.edit();
        editor.putInt("productImage",R.drawable.cocacola);
        editor.putString("productName","Coca Cola");
        editor.putString("productPrize","৳ 30");
        editor.putString("productWeight","[ 500 ml ]");
        editor.putString("productQuantity", String.valueOf(count));
        editor.commit();
    }

    private void ShareStoreData2(){
        SharedPreferences sharedPref2 = getSharedPreferences("STORE_PRODUCT_DETAILS_DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref2.edit();
        editor.putInt("productImage",R.drawable.pepsi);
        editor.putString("productName","Pepsi");
        editor.putString("productPrize","৳ 35");
        editor.putString("productWeight","[ 500 ml ]");
        editor.putString("productQuantity", String.valueOf(count2));
        editor.commit();
    }

}
