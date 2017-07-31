package com.dawn.httpdawn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 90449 on 2017/7/31.
 */

public class OkHttpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
    }
    public void formPost(View view){
        new Thread(){
            @Override
            public void run() {
                super.run();
                requestFormPost();
            }
        }.start();
    }
    public void jsonPost(View view){
        new Thread(){
            @Override
            public void run() {
                super.run();
                requestJsonPost();
            }
        }.start();
    }
    public void get(View view){
        new Thread(){
            @Override
            public void run() {
                super.run();
                requestGet();
            }
        }.start();
    }
    private void requestGet(){
        String url = "https://www.baidu.com";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if(response.isSuccessful()){
                Log.i("dawn", "request get result : " + response.body().string());
            }else{
                Log.e("dawn", "response failure");
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("dawn", "response exception");
        }
    }
    private void requestFormPost(){
        String url = "https://routetest.189cube.com/syncJob/caroute/queryDeviceInfoList?";
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody formBody = new FormBody.Builder()
                .add("token", "")
                .add("userAcct", "")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if(response.isSuccessful()){
                Log.i("dawn", "request form post result : " + response.body().string());
            }else{
                Log.e("dawn", "response failure");
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("dawn", "response exception");
        }
    }
    private void requestJsonPost(){
        String url = "http://fintstest.189cube.com/oauth2.0/authorize?response_type=esurfingpassword";
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        String bodyJsonStr = getBodyJsonStr();
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(mediaType, bodyJsonStr);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if(response.isSuccessful()){
                Log.i("dawn", "request json post result : " + response.body().string());
            }else{
                Log.e("dawn", "response failure");
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("dawn", "response exception");
        }
    }
    private String getBodyJsonStr(){
        Login loginStr = new Login("password", "13911111111", "111111", "");
        return new GsonBuilder().create().toJson(loginStr);
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
}
