package com.dobajar.myapplication.Card;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dobajar.myapplication.Adapter.CardAdapter;
import com.dobajar.myapplication.CheckOut.Checkout;
import com.dobajar.myapplication.CheckOut.DeliveryAddress;
import com.dobajar.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class Cart_activity extends AppCompatActivity implements CardAdapter.OnItemClickListener {

    TextView next, cardProductPrize, cardProductPrize2, cardProductQuanity, cardProductQuanity2;
    ImageButton cardProductIncrement, cardProductIncrement2, cardProductDecriment, cardProductDecriment2;
    int incrementCount=1, incrementCount2=1;

    private CardAdapter cardAdapter;
    private List<Integer> cardImage;
    private List<String> cardName;
    private List<String> cardPrize;
    private List<String> cardQuantity;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_activity);
        setTitle("Cart Item's");

        next= findViewById(R.id.goToCheckOut);
        recyclerView= findViewById(R.id.card_item_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        cardImage= new ArrayList<Integer>();
        cardName= new ArrayList<String>();
        cardPrize= new ArrayList<String>();
        cardQuantity= new ArrayList<String>();

        cardImage.add(R.drawable.pepsi);
        cardImage.add(R.drawable.cocacola);

        cardName.add("Pepsi");
        cardName.add("Coca Cola");

        cardPrize.add("35");
        cardPrize.add("30");

        cardQuantity.add("2");
        cardQuantity.add("3");

        /*cardAdapter= new CardAdapter(this, cardImage, cardQuantity, cardPrize, cardName);
        recyclerView.setAdapter(cardAdapter);*/

       /* cardProductPrize= findViewById(R.id.card_product_prize);
        cardProductPrize2= findViewById(R.id.card_product_prize2);
        cardProductQuanity= findViewById(R.id.card_product_quantity);
        cardProductQuanity2= findViewById(R.id.card_product_quantity2);
        cardProductIncrement= findViewById(R.id.card_product_increment);
        cardProductIncrement2= findViewById(R.id.card_product_increment2);
        cardProductDecriment= findViewById(R.id.card_product_decrement);
        cardProductDecriment2= findViewById(R.id.card_product_decrement2);

        cardProductIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementCount++;
                cardProductQuanity.setText(String.valueOf(incrementCount));
            }
        });

        cardProductIncrement2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementCount2++;
                cardProductQuanity2.setText(String.valueOf(incrementCount2));
            }
        });

        cardProductDecriment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementCount--;
                cardProductQuanity.setText(String.valueOf(incrementCount));
            }
        });

        cardProductDecriment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementCount2--;
                cardProductQuanity2.setText(String.valueOf(incrementCount2));
            }
        });*/

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Cart_activity.this, DeliveryAddress.class));
            }
        });

    }

    @Override
    public void OnItemClick(int position) {
        /*Intent intent= new Intent(this, CardProductDetails.class);
        startActivity(intent);*/
    }
}
