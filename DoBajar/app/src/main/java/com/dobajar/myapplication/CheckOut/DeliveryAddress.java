package com.dobajar.myapplication.CheckOut;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dobajar.myapplication.R;

public class DeliveryAddress extends AppCompatActivity {
    private Button addressConfirm;
    private EditText deliveryAddress, deliveryAddressName, deliveryAddressPhone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        addressConfirm= findViewById(R.id.address_confirm);
        deliveryAddress= findViewById(R.id.edit_delivery_address);
        deliveryAddressName= findViewById(R.id.edit_delivery_address_name);
        deliveryAddressPhone= findViewById(R.id.edit_delivery_address_phone);

        SharedPreferences receiveProductDetailsData = getSharedPreferences("STORE_LOCATION", Context.MODE_PRIVATE);
        deliveryAddress.setText(receiveProductDetailsData.getString("your_home_address","default"));


        addressConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPref = getSharedPreferences("STORE_DELIVERY_ADDRESS_DATA", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("_delivery_address",deliveryAddress.getText().toString());
                editor.putString("_delivery_address_name",deliveryAddressName.getText().toString());
                editor.putString("_delivery_address_phone",deliveryAddressPhone.getText().toString());
                editor.commit();
                startActivity(new Intent(DeliveryAddress.this, PaymentSelect.class));
            }
        });
    }
}
