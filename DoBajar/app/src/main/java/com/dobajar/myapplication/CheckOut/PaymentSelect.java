package com.dobajar.myapplication.CheckOut;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dobajar.myapplication.Paypal.Payment;
import com.dobajar.myapplication.R;

public class PaymentSelect extends AppCompatActivity implements View.OnClickListener {
    private Button btnPaymentConfirm;
    CardView cashOn, masterCard, visaCard, paypalCard, duchBangla, bkash;
    private String paymentString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymen);

        btnPaymentConfirm = findViewById(R.id.payment_confirm);

        cashOn = findViewById(R.id.cardCashon);
        masterCard = findViewById(R.id.card_master_card);
        visaCard = findViewById(R.id.card_visa_card);
        paypalCard = findViewById(R.id.card_paypal_card);
        duchBangla = findViewById(R.id.card_duch_bangla);
        bkash = findViewById(R.id.card_bkash);

        cashOn.setOnClickListener(this);
        masterCard.setOnClickListener(this);
        visaCard.setOnClickListener(this);
        paypalCard.setOnClickListener(this);
        duchBangla.setOnClickListener(this);
        bkash.setOnClickListener(this);

        btnPaymentConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaymentSelect.this, Review.class);
                intent.putExtra("colorString", paymentString);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        int id= view.getId();
        if (id==R.id.cardCashon){
            paymentString="Cash On Delivery";
            Toast.makeText(this, paymentString, Toast.LENGTH_SHORT).show();
            SharedPreferences sharedPref = getSharedPreferences("STORE_PAYMENT_DATA", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("payment_type",paymentString);
            editor.putInt("payment_image", R.drawable.cashon);
            editor.commit();
            cashOn.setBackgroundColor(getResources().getColor(R.color.low_black));

            masterCard.setBackgroundColor(getResources().getColor(R.color.white));
            visaCard.setBackgroundColor(getResources().getColor(R.color.white));
            paypalCard.setBackgroundColor(getResources().getColor(R.color.white));
            duchBangla.setBackgroundColor(getResources().getColor(R.color.white));
            bkash.setBackgroundColor(getResources().getColor(R.color.white));
        } else if (id==R.id.card_master_card){
            paymentString="Master Card";
            Toast.makeText(this, paymentString, Toast.LENGTH_SHORT).show();
            SharedPreferences sharedPref = getSharedPreferences("STORE_PAYMENT_DATA", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("payment_type",paymentString);
            editor.putInt("payment_image", R.drawable.mastercard);
            editor.commit();
            masterCard.setBackgroundColor(getResources().getColor(R.color.low_black));

            cashOn.setBackgroundColor(getResources().getColor(R.color.white));
            visaCard.setBackgroundColor(getResources().getColor(R.color.white));
            paypalCard.setBackgroundColor(getResources().getColor(R.color.white));
            duchBangla.setBackgroundColor(getResources().getColor(R.color.white));
            bkash.setBackgroundColor(getResources().getColor(R.color.white));
        } else if (id==R.id.card_visa_card){
            paymentString="Visa Card";
            Toast.makeText(this, paymentString, Toast.LENGTH_SHORT).show();
            SharedPreferences sharedPref = getSharedPreferences("STORE_PAYMENT_DATA", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("payment_type",paymentString);
            editor.putInt("payment_image", R.drawable.visacard);
            editor.commit();
            visaCard.setBackgroundColor(getResources().getColor(R.color.low_black));

            cashOn.setBackgroundColor(getResources().getColor(R.color.white));
            masterCard.setBackgroundColor(getResources().getColor(R.color.white));
            paypalCard.setBackgroundColor(getResources().getColor(R.color.white));
            duchBangla.setBackgroundColor(getResources().getColor(R.color.white));
            bkash.setBackgroundColor(getResources().getColor(R.color.white));
        } else if (id==R.id.card_paypal_card){
            paymentString="PayPal Card";
            Toast.makeText(this, paymentString, Toast.LENGTH_SHORT).show();
            SharedPreferences sharedPref = getSharedPreferences("STORE_PAYMENT_DATA", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("payment_type",paymentString);
            editor.putInt("payment_image", R.drawable.paypal);
            editor.commit();
            paypalCard.setBackgroundColor(getResources().getColor(R.color.low_black));

            cashOn.setBackgroundColor(getResources().getColor(R.color.white));
            masterCard.setBackgroundColor(getResources().getColor(R.color.white));
            visaCard.setBackgroundColor(getResources().getColor(R.color.white));
            duchBangla.setBackgroundColor(getResources().getColor(R.color.white));
            bkash.setBackgroundColor(getResources().getColor(R.color.white));
        } else if (id==R.id.card_duch_bangla){
            paymentString="DuchBangla";
            Toast.makeText(this, paymentString, Toast.LENGTH_SHORT).show();
            SharedPreferences sharedPref = getSharedPreferences("STORE_PAYMENT_DATA", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("payment_type",paymentString);
            editor.putInt("payment_image", R.drawable.duchbangla);
            editor.commit();
            duchBangla.setBackgroundColor(getResources().getColor(R.color.low_black));

            cashOn.setBackgroundColor(getResources().getColor(R.color.white));
            masterCard.setBackgroundColor(getResources().getColor(R.color.white));
            visaCard.setBackgroundColor(getResources().getColor(R.color.white));
            paypalCard.setBackgroundColor(getResources().getColor(R.color.white));
            bkash.setBackgroundColor(getResources().getColor(R.color.white));
        } else if (id==R.id.card_bkash){
            paymentString="BKash";
            Toast.makeText(this, paymentString, Toast.LENGTH_SHORT).show();
            SharedPreferences sharedPref = getSharedPreferences("STORE_PAYMENT_DATA", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("payment_type",paymentString);
            editor.putInt("payment_image", R.drawable.bkash);
            editor.commit();
            bkash.setBackgroundColor(getResources().getColor(R.color.low_black));

            cashOn.setBackgroundColor(getResources().getColor(R.color.white));
            masterCard.setBackgroundColor(getResources().getColor(R.color.white));
            visaCard.setBackgroundColor(getResources().getColor(R.color.white));
            paypalCard.setBackgroundColor(getResources().getColor(R.color.white));
            duchBangla.setBackgroundColor(getResources().getColor(R.color.white));
        }
    }
}
