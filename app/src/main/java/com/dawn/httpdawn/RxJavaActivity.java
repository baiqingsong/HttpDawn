package com.dawn.httpdawn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 * Created by 90449 on 2017/7/31.
 */

public class RxJavaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
    }
    public void observable(View view){
        rxJava();
    }
    private void rxJava(){
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("step 01");
                subscriber.onNext("step 02");
                subscriber.onNext("step 03");
                subscriber.onCompleted();
            }
        });
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.i("dawn", "observer on completed");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("dawn", "observer on error");
            }

            @Override
            public void onNext(String s) {
                Log.i("dawn", "observer on next string " + s);
            }
        };
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.i("dawn", "subscriber on completed");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("dawn", "subscriber on error");
            }

            @Override
            public void onNext(String s) {
                Log.e("dawn", "subscriber on error");
            }
        };
        observable.subscribe(observer);
//        observable.subscribe(subscriber);
    }
}
