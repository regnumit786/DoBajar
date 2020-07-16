package com.dobajar.myapplication.Loged;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.dobajar.myapplication.Model.LoginResponse;
import com.dobajar.myapplication.Model.Retrofit.ApiClint;
import com.dobajar.myapplication.Model.Retrofit.RetrofitClint;
import com.dobajar.myapplication.R;
import com.dobajar.myapplication.activity.MainActivity;
import com.dobajar.myapplication.activity.SelectLocation;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    private String TAG= getClass().getSimpleName();
    private EditText edLogPhone, edLogPassword;
    private Button mlogin;
    private TextView goToRegister;
    private ProgressDialog progressDialog;
    private SharedPreferences sharedPref;
    private int USER_STATE;
    private ApiClint apiClint;
    /*private String mobile;
    private String password;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edLogPhone= findViewById(R.id.edlogPhone);
        edLogPassword= findViewById(R.id.edlogPassword);
        mlogin= findViewById(R.id.btnLogin);
        goToRegister= findViewById(R.id.gotoregister);

        progressDialog= new ProgressDialog(this);

        goToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Register.class));
                finish();
            }
        });

        sharedPref= getSharedPreferences("STORE_PASSWORD_FOR_LOGIN", Context.MODE_PRIVATE);
        USER_STATE= sharedPref.getInt("USER_STATE",0);

        CheckConnection();
    }

    private void CheckConnection() {
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ConnectionInfo= cm.getActiveNetworkInfo();

        if (ConnectionInfo!= null){
            try {
                mlogin.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onClick(View v) {
                        String mobile= edLogPhone.getText().toString();
                        String password= edLogPassword.getText().toString();

                        if (TextUtils.isEmpty(mobile)){
                            edLogPhone.setError("Enter email");
                            edLogPhone.requestFocus();
                            return;
                        } else if(TextUtils.isEmpty(password)){
                            edLogPassword.setError("Enter password");
                            edLogPassword.requestFocus();
                            return;
                        } else {
                           try {

                               apiClint= RetrofitClint.getRetrifitClint().create(ApiClint.class);
                               Call<LoginResponse> call= apiClint.loginPost(mobile, password);
                               call.enqueue(new Callback<LoginResponse>() {
                                   @Override
                                   public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                                       LoginResponse loginResponse= response.body();
                                       String phone= loginResponse.getPhone_number();
                                       String pass= loginResponse.getPassword();

                                       if (edLogPassword.getText().toString().equals(pass)){
                                           Log.i("phone",phone);
                                           Log.i("password",pass);

                                           SharedPreferences sharedPreferences= getSharedPreferences("STORE_PASSWORD_FOR_LOGIN",Context.MODE_PRIVATE);
                                           SharedPreferences.Editor editor= sharedPreferences.edit();
                                           editor.putInt("USER_STATE",1);
                                           editor.commit();

                                           Toast.makeText(Login.this, "Log in successfully", Toast.LENGTH_SHORT).show();
                                           startActivity(new Intent(Login.this, SelectLocation.class));
                                           edLogPhone.setText("");
                                           edLogPassword.setText("");
                                           finish();
                                       } else {
                                           Toast.makeText(Login.this, "Username or Email incorrect", Toast.LENGTH_SHORT).show();
                                       }
                                   }

                                   @Override
                                   public void onFailure(Call<LoginResponse> call, Throwable t) {
                                       Toast.makeText(Login.this, "onFailure: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                                   }
                               });

                           } catch (Exception e){
                               e.printStackTrace();
                           }
                        }

                        try {
                            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(), 0);
                        } catch (Exception e) {
                            Log.d(TAG,"Keyboard Error: "+e.getMessage());
                        }
                    }
                });

            }catch (Exception e){
                Toast.makeText(this, "Log in Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (sharedPref.contains("USER_STATE")){
            if (USER_STATE==1){
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
        }
    }
}
