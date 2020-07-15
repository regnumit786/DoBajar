package com.dobajar.myapplication.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.dobajar.myapplication.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class SelectLocation extends FragmentActivity implements OnMapReadyCallback {

    private final String TAG = this.getClass().getSimpleName();
    private double latitude;
    private double longitude;
    private TextView txtShowAddress;
    private String strAdd = "";
    private int countAddress=0;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_location);

        requestPermission();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);

        getLatitudeLongitude();

        txtShowAddress = findViewById(R.id.txtShowYourAddress);
        Button btnShowAddress = findViewById(R.id.btnShowAddress);

        btnShowAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (strAdd.isEmpty()){
                    Toast.makeText(SelectLocation.this, "Please check your location and restart", Toast.LENGTH_SHORT).show();
                } else {
                    txtShowAddress.setVisibility(View.VISIBLE);
                    txtShowAddress.setText(strAdd);
                    countAddress++;
                }
            }
        });

        ImageButton next = findViewById(R.id.goToNext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (countAddress<=0){
                    Toast.makeText(SelectLocation.this, "Please view your Address", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences sharedPref = getSharedPreferences("STORE_LOCATION", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("your_home_address", txtShowAddress.getText().toString());
                    editor.commit();
                    startActivity(new Intent(SelectLocation.this, MainActivity.class));
                    finish();
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getLatitudeLongitude() {
        Log.i("execute", "method lat lon");
        try {

            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    Activity#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                return;
            }
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            latitude = location.getLatitude();
            Log.i("latitude", String.valueOf(latitude));
            longitude = location.getLongitude();
            Log.i("longitude", String.valueOf(longitude));
            getCompleteAddressString(latitude, longitude);
        }
        catch (Exception e){
            Toast.makeText(this, "Enable your location.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @SuppressLint("LongLogTag")
    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                Log.i(TAG, strReturnedAddress.toString());
            } else {
                Log.i(TAG, "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w(TAG, "Canont get Address!");
        }
        Log.i("your_address: ",strAdd);
        //Toast.makeText(this, "your address is: "+strAdd, Toast.LENGTH_SHORT).show();
        return strAdd;
    }

    public void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(latitude, longitude);
        googleMap.setMinZoomPreference(20.0f);
        googleMap.setMaxZoomPreference(30.0f);
        googleMap.addMarker(new MarkerOptions().position(sydney).title("Your Place"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
