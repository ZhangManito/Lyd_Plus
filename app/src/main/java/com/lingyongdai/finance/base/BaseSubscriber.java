package com.lingyongdai.finance.base;

import com.lingyongdai.finance.bean.BaseEntity;
import com.lingyongdai.finance.utils.NetworkUtil;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by guoliang on 2018/3/3.
 */

public class BaseSubscriber<T> implements Observer<BaseEntity<T>> {
    @Override
    public void onSubscribe(Disposable d) {
        if (!NetworkUtil.isNetworkConnected()){
            d.dispose();
        }

    }

    @Override
    public void onNext(BaseEntity<T> tBaseEntity) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
