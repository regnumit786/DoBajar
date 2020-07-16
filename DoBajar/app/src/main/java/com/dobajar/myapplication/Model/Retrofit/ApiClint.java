package com.dobajar.myapplication.Model.Retrofit;

import com.dobajar.myapplication.Model.LoginResponse;
import com.dobajar.myapplication.Model.Post;
import com.dobajar.myapplication.Model.Products.AllProducts;
import com.dobajar.myapplication.Model.Products.Datum;
import com.dobajar.myapplication.Model.SignUpPostModel;
import com.dobajar.myapplication.Model.SubCategories;
import com.dobajar.myapplication.Model.SubCategoriesModel.SubCategorieModel;
import com.dobajar.myapplication.Model.SuperCategories;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiClint {
    @FormUrlEncoded
    @POST("api/reg")
    Call<SignUpPostModel> signUpPost (
            @Field("name") String name,
            @Field("phone_number") String mobile,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("posts")
    Call<Post> fakePost (
            @Field("userId") String userId,
            @Field("title") String title,
            @Field("body") String body
    );

    @FormUrlEncoded
    @POST("api/login")
    Call<LoginResponse> loginPost (
            @Field("phone_number") String userId,
            @Field("password") String title
    );

    @GET("api/products")
    Call<AllProducts> productsGet();

    @GET("api/sub-categories")
    Call<SubCategorieModel> subCategoriesGet();

}
