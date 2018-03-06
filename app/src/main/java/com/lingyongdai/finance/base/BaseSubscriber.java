package com.lingyongdai.finance.base;

import com.google.gson.Gson;
import com.lingyongdai.finance.bean.BaseEntity;
import com.lingyongdai.finance.utils.NetworkUtil;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by guoliang on 2018/3/3.
 */

public class BaseSubscriber<T> implements Observer<T> {

    private String stringjson;

    @Override
    public void onSubscribe(Disposable d) {
        if (!NetworkUtil.isNetworkConnected()){
            d.dispose();
        }

    }

    @Override
    public void onNext(T t) {
        stringjson = new Gson().toJson(t);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
