package com.dawn.httpdawn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 90449 on 2017/7/31.
 */

public class OkHttpActivity extends AppCompatActivity {
    String url = "https://routetest.189cube.com/syncJob/caroute/queryDeviceInfoList?";
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
    private void requestFormPost(){
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
                Log.i("dawn", "result : " + response.body().string());
            }else{
                Log.e("dawn", "response failure");
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("dawn", "response exception");
        }
    }
}
