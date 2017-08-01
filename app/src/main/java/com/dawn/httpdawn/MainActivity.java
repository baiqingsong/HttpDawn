package com.dawn.httpdawn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void jumpRxJava(View view){
        startActivity(new Intent(this, RxJavaActivity.class));
    }
    public void jumpOkHttp(View view){
        startActivity(new Intent(this, OkHttpActivity.class));
    }
    public void jumpRetrofit(View view){
        startActivity(new Intent(this, RetrofitActivity.class));
    }
    public void jumpRetrofitRxJava(View view){
        startActivity(new Intent(this, RetrofitRxJavaActivity.class));
    }
}
