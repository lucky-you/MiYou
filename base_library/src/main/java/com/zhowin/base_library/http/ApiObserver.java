package com.zhowin.base_library.http;


import com.google.gson.JsonParseException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.adapter.rxjava2.HttpException;


public abstract class ApiObserver<T> implements Observer<ApiResponse<T>> {

    public static final int NET_ERROR = -1;
    public static final int JSON_ERROR = -2;
    public static final int DATA_ERROR = -3;
    public static final int TOKEN_ERROR = -4;
    public static final int PERMISSION_ERROR = -5;

    @Override
    public void onSubscribe(Disposable d) {
        subscribe(d);
    }

    @Override
    public void onNext(ApiResponse<T> response) {
        //在这边对 基础数据 进行统一处理  初步解析：
        if (response.getCode() == 200) {
            onSuccess(response.getData());
        } else {
            onFail(response.getCode(), response.getMsg());
        }
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable throwable) {
        String error;
        if (throwable instanceof SocketTimeoutException  //网络超时,网络连接异常
                || throwable instanceof ConnectException   //均视为网络异常
                || throwable instanceof UnknownHostException) {
            onFail(NET_ERROR, "网络错误");
        } else if (throwable instanceof JsonParseException//均视为解析错误
                || throwable instanceof java.text.ParseException) {
            error = throwable.getMessage();
            onFail(JSON_ERROR, "数据解析异常:" + error);
        } else if (throwable instanceof HttpException) {
//            Log.e("xy", "错误: " + throwable.getMessage());
            onFail(JSON_ERROR, "Http错误: " + throwable.getMessage());
        }

    }

    public abstract void onSuccess(T demo);

    public void subscribe(Disposable d) {

    }

    public abstract void onFail(int errorCode, String errorMsg);
}
