package com.dawn.httpdawn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;
import rx.Observer;
import rx.schedulers.Schedulers;

/**
 * Created by 90449 on 2017/8/1.
 */

public class RetrofitRxJavaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_rxjava);
    }
    public void formPost(View view){
        try{
            requestFormPost();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void requestFormPost(){
        String url = "https://routetest.189cube.com/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        retrofit.create(FormPostServer.class).getData("", "")
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultModel>() {
                    @Override
                    public void onCompleted() {
                        Log.i("dawn", "request form post completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.e("dawn", "request form post error " + e.getMessage());
                    }

                    @Override
                    public void onNext(ResultModel resultModel) {
                        Log.i("dawn", "request form post next " + resultModel.getResult());
                    }
                });
    }
    private interface FormPostServer{
        @FormUrlEncoded
        @POST("syncJob/caroute/queryDeviceInfoList")
        Observable<ResultModel> getData(@Field("token") String token, @Field("userAcct") String userAcct);
    }
    private class ResultModel{
        String Result;

        public String getResult() {
            return Result;
        }
    }
}
