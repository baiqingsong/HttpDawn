# 网络请求框架
## RxJava

* [RxJava引用](#rxjava引用)
* [RxJava使用](#rxjava使用)
    * [Observer创建](#observer创建)
    * [Observable创建](#observable创建)
    * [订阅](#订阅)
* [RxJava参考地址](#rxjava参考地址)
* [OkHttp引用](#okhttp引用)
* [OkHttp使用](#okhttp使用)
    * [GET请求](#get请求)
    * [POST请求](#post请求)
        * [json形式提交参数](#json形式提交参数)
        * [键值对形式提交参数](#键值对形式提交参数)


### RxJava引用
```
dependencies {
    compile 'io.reactivex:rxjava:1.0.14'
    compile 'io.reactivex:rxandroid:1.0.1'
}
```

### RxJava使用
链式结构，异步操作

#### Observer创建
异步请求后的操作
```
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
```

Subscriber 对 Observer 接口进行了一些扩展
```
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
```

**注：一般异步请求后的操作在onNext方法中进行**


#### Observable创建
可以调用create方法来创建Observable类。
```
Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
    @Override
    public void call(Subscriber<? super String> subscriber) {
        subscriber.onNext("step 01");
        subscriber.onNext("step 02");
        subscriber.onNext("step 03");
        subscriber.onCompleted();
    }
});
```

#### 订阅
想将observable和observer关联，需要调用订阅subscribe方法
```
observable.subscribe(observer);
```
Observable的订阅中参数也可以是onNext方法，onError方法，onCompleted方法
```
// 自动创建 Subscriber ，并使用 onNextAction 来定义 onNext()
observable.subscribe(onNextAction);
// 自动创建 Subscriber ，并使用 onNextAction 和 onErrorAction 来定义 onNext() 和 onError()
observable.subscribe(onNextAction, onErrorAction);
// 自动创建 Subscriber ，并使用 onNextAction、 onErrorAction 和 onCompletedAction 来定义 onNext()、 onError() 和 onCompleted()
observable.subscribe(onNextAction, onErrorAction, onCompletedAction);
```

### RxJava参考地址

[https://gank.io/post/560e15be2dca930e00da1083](https://gank.io/post/560e15be2dca930e00da1083 "RxJava参考地址")


### OkHttp引用
```
dependencies {
    compile 'com.squareup.okhttp3:okhttp:3.2.0'
}
```

### OkHttp使用
常用的请求有get请求和post请求

#### GET请求

#### POST请求
post请求的参数可以是键值对形式提交，也可以是json形式提交。

##### json形式提交参数

##### 键值对形式提交参数
