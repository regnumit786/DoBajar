package com.dobajar.myapplication.Loged;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dobajar.myapplication.Model.Post;
import com.dobajar.myapplication.Model.Retrofit.ApiClint;
import com.dobajar.myapplication.Model.Retrofit.RetrofitClint;
import com.dobajar.myapplication.Model.SignUpPostModel;
import com.dobajar.myapplication.R;
import com.dobajar.myapplication.activity.SelectLocation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    private EditText edRegName, edRegPhone, edRegPassword, edRegRePassword;
    private Button btRegister;
    private String name, mobile, password, repassword;
    private ProgressDialog dialog;
    private TextView alreadyAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FindAllView();
        SetTextString();

        final SharedPreferences sharedPreferences= getSharedPreferences("STORE_PASSWORD_FOR_LOGIN",Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor= sharedPreferences.edit();

        dialog= new ProgressDialog(this);
        dialog.setTitle("Sign up...");
        dialog.setCancelable(true);

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetTextString();

                if (TextUtils.isEmpty(name)){
                    edRegName.setError("Enter your Name !");
                    edRegName.requestFocus();
                } else if (TextUtils.isEmpty(mobile)){
                    edRegPhone.setError("Enter Phone Number !");
                    edRegPhone.requestFocus();
                } else if(password.length()< 6){
                    edRegPassword.setError("Password at least 6 char long");
                    edRegPassword.requestFocus();
                } else if(repassword.length()< 6){
                    edRegRePassword.setError("Password at least 6 char long");
                    edRegRePassword.requestFocus();
                } else if (!password.equals(repassword)){
                    edRegPassword.setError("Password not match");
                    edRegPassword.requestFocus();
                } else {
                    editor.putInt("USER_STATE",1);
                    editor.commit();

                    PostApi();

                    edRegName.setText("");
                    edRegPhone.setText("");
                    edRegPassword.setText("");
                    edRegRePassword.setText("");
                }
            }
        });

        alreadyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });
    }

    private void SetTextString(){
        name= edRegName.getText().toString().trim();
        mobile= edRegPhone.getText().toString().trim();
        password= edRegPassword.getText().toString().trim();
        repassword= edRegRePassword.getText().toString().trim();
    }

    private void PostApi() {
        dialog.show();
        ApiClint apiClint = RetrofitClint.getRetrifitClint().create(ApiClint.class);

        Call<SignUpPostModel> call= apiClint.signUpPost(edRegName.getText().toString(), edRegPhone.getText().toString(), edRegPassword.getText().toString());
        call.enqueue(new Callback<SignUpPostModel>() {
            @Override
            public void onResponse(Call<SignUpPostModel> call, Response<SignUpPostModel> response) {
                dialog.dismiss();
                if (response.isSuccessful()){
                    Toast.makeText(Register.this, "Successfully Register", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register.this, SelectLocation.class));
                    finish();
                } else {
                    Toast.makeText(Register.this, "Register Failed: "+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SignUpPostModel> call, Throwable t) {
                dialog.dismiss();
                Log.i("ServerOnFailed",t.getMessage());
            }
        });
    }

    /*

    private void fakePost(){
        final ProgressDialog dialog= new ProgressDialog(this);
        dialog.setTitle("Sign up...");
        dialog.setCancelable(true);
        dialog.show();

        ApiClint apiClint= RetrofitClint.getFakeRetrifitClint().create(ApiClint.class);

        Call<Post> call= apiClint.fakePost("504", "Akij", "CSE");
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                dialog.dismiss();
                if (!response.isSuccessful()){
                    Toast.makeText(Register.this, "Server failed", Toast.LENGTH_SHORT).show();
                    return;
                }
                Post post= response.body();
                Log.i("userId",String.valueOf(post.getUserId()));
                Log.i("title",post.getTitle());
                Log.i("body",post.getBody());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                dialog.dismiss();
                Log.e("Server_Error",t.getMessage());
            }
        });
    }

    */

    private void FindAllView() {
        edRegName= findViewById(R.id.edName);
        edRegPhone= findViewById(R.id.edPhone);
        edRegPassword= findViewById(R.id.edPassword);
        edRegRePassword= findViewById(R.id.edConfirmPassword);
        btRegister= findViewById(R.id.btnRegister);
        alreadyAccount= findViewById(R.id.i_have_already_account);
    }
}
