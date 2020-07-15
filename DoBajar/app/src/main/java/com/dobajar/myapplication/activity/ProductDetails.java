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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dobajar.myapplication.Card.Cart_activity;
import com.dobajar.myapplication.R;

public class ProductDetails extends AppCompatActivity {
    private final String TAG= this.getClass().getSimpleName();

    TextView _productPrize, _quantityCount, _productDetailsName, _productWeight;
    ImageButton incrementQuantity, decrementQuantity, cardImage;
    int count=0, loadCardCount=0;
    TextView cartText;
    MenuItem menuItem;
    ImageView _productDetailsImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details_activity);

        InitialView();

        SharedPreferences receiveProductDetailsData = getSharedPreferences("STORE_PRODUCT_DETAILS_DATA", Context.MODE_PRIVATE);
        _productDetailsName.setText(receiveProductDetailsData.getString("productName","default"));
        Log.i("productDetailsName",_productDetailsName.getText().toString());
        _productWeight.setText(receiveProductDetailsData.getString("productWeight","default"));
        Log.i("productWeight",_productWeight.getText().toString());
        _productPrize.setText(receiveProductDetailsData.getString("productPrize","default"));
        Log.i("productPrize",_productPrize.getText().toString());
        _quantityCount.setText(receiveProductDetailsData.getString("productQuantity","default"));
        Log.i("quantityCount",_quantityCount.getText().toString());
        _productDetailsImage.setImageResource(receiveProductDetailsData.getInt("productImage",0));

        if (_quantityCount.getText().toString().equals("0")){
            _quantityCount.setVisibility(View.INVISIBLE);
            decrementQuantity.setVisibility(View.INVISIBLE);
        } else {
            _quantityCount.setVisibility(View.VISIBLE);
            decrementQuantity.setVisibility(View.VISIBLE);
            count= Integer.parseInt(_quantityCount.getText().toString());
        }

        incrementQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                _quantityCount.setVisibility(View.VISIBLE);
                _quantityCount.setText(String.valueOf(count));
                decrementQuantity.setVisibility(View.VISIBLE);

                SharedPreferences sharedPref = getSharedPreferences("UPLOAD_CARD_DATA", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("cardCount",count);
                Log.d(TAG, String.valueOf(count));
                editor.commit();

               LoadCardData();
            }
        });

        decrementQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadCardData();
                count--;
                if (count<=0){
                    decrementQuantity.setVisibility(View.INVISIBLE);
                    _quantityCount.setVisibility(View.INVISIBLE);
                }
                _quantityCount.setText(String.valueOf(count));

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        menuItem= menu.findItem(R.id.add_to_card_toolbar);
        View actionView= menuItem.getActionView();
        if (actionView!= null) {
            cartText= actionView.findViewById(R.id.toolbarText);
            cardImage= actionView.findViewById(R.id.toolberCard);
            cartText.setVisibility(View.GONE);
        }
        cardImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ProductDetails.this, "Card item", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ProductDetails.this, Cart_activity.class));
            }
        });
        return super.onCreateOptionsMenu(menu) ;
    }

    private void LoadCardData(){
        SharedPreferences sharedPref = getSharedPreferences("UPLOAD_CARD_DATA", Context.MODE_PRIVATE);
        loadCardCount= sharedPref.getInt("cardCount", 0);
        cartText.setVisibility(View.VISIBLE);
        cartText.setText(String.valueOf(loadCardCount));
    }

    private void InitialView() {
        _productPrize= findViewById(R.id._product_prize);
        _quantityCount= findViewById(R.id.quantity_count);
        _productDetailsName= findViewById(R.id.product_details_name);
        _productWeight= findViewById(R.id.product_weight);
        incrementQuantity= findViewById(R.id.increment_quantity);
        decrementQuantity= findViewById(R.id.decrement_quantity);
        _productDetailsImage= findViewById(R.id.product_details_image);
    }

    private void addShoppingCart(){
        cartText.setText(String.valueOf(count));
    }
}
