package com.dawn.httpdawn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.reflect.Modifier;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by 90449 on 2017/8/1.
 */

public class RetrofitActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
    }
    public void get(View view){
        try{
            requestGet();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void formPost(View view){
        try{
            requestFormPost();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void jsonPost(View view){
        try{
            requestJsonPost();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void jsonPost2(View view){
        try{
            requestJsonPost2();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void requestGet(){
        String url = "https://github.com/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .build();
        GetServer getServer = retrofit.create(GetServer.class);
        Call<ResponseBody> responseBodyCall = getServer.getData();
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.i("dawn", "request get result = " + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("dawn", "request get result exception");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("dawn", "request get call failure");
            }
        });
    }
    private interface GetServer{
        @GET("baiqingsong/HttpDawn")
        Call<ResponseBody> getData();
    }

    private void requestFormPost(){
        String url = "https://routetest.189cube.com/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .build();
        Call<ResponseBody> responseBodyCall = retrofit.create(FormPostServer.class).getData("", "");
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.i("dawn", "request form post result = " + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("dawn", "request form post result exception");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("dawn", "request form post call failure");
            }
        });
    }
    private interface FormPostServer{
        @FormUrlEncoded
        @POST("syncJob/caroute/queryDeviceInfoList")
        Call<ResponseBody> getData(@Field("token") String token, @Field("userAcct") String userAcct);
    }

    private void requestJsonPost(){
        String url = "http://fintstest.189cube.com/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(
                        GsonConverterFactory.create(new GsonBuilder()
                                .serializeNulls()
                                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                                .create()))
                .build();
        Login login = new Login("password", "13911111111", "111111", "");
        Call<ResponseBody> responseBodyCall = retrofit.create(JsonPostServer.class).getData(login);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.i("dawn", "request json post result = " + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("dawn", "request json post result exception");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("dawn", "request json post call failure");
            }
        });
    }
    private interface JsonPostServer{
        @POST("oauth2.0/authorize?response_type=esurfingpassword")
        Call<ResponseBody> getData(@Body Login login);
    }

    private void requestJsonPost2(){
        String url = "http://fintstest.189cube.com/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build();
        Login login = new Login("password", "13911111111", "111111", "");
        Call<LoginResult> responseBodyCall = retrofit.create(JsonPostServer2.class).getData(login);
        responseBodyCall.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                Log.i("dawn", "request json post 2 result = " + response.body().toString());
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Log.e("dawn", "request json post 2 call failure");
            }
        });
    }
    private interface JsonPostServer2{
        @POST("oauth2.0/authorize?response_type=esurfingpassword")
        Call<LoginResult> getData(@Body Login login);
    }

    private class Login{
        private String response_type;
        private String username;
        private String password;
        private String scope;

        public Login(String response_type, String username, String password, String scope) {
            this.response_type = response_type;
            this.username = username;
            this.password = password;
            this.scope = scope;
        }
    }
    private class LoginResult{
        private String error;
        private String error_description;

        public String getError() {
            return error;
        }

        public String getError_description() {
            return error_description;
        }

        @Override
        public String toString() {
            return "error = " + error + " error_description = " + error_description;
        }
    }

}
