package com.lingyongdai.finance.viewmodel;

import android.app.Activity;

import com.lingyongdai.finance.api.HttpManager;
import com.lingyongdai.finance.base.BaseSubscriber;
import com.lingyongdai.finance.bean.BaseEntity;
import com.lingyongdai.finance.bean.PlatformData;
import com.lingyongdai.finance.databinding.ActivityMainBinding;

import io.reactivex.disposables.Disposable;

/**
 * Created by guoliang on 2018/2/26.
 */

public class MActivityViewModel {
    public MActivityViewModel(Activity activity, ActivityMainBinding mainBinding) {
        HttpManager.getInstance().getAllAmountMonth(new BaseSubscriber<BaseEntity<PlatformData>>(){
            @Override
            public void onSubscribe(Disposable d) {
                super.onSubscribe(d);
            }

            @Override
            public void onNext(BaseEntity<BaseEntity<PlatformData>> baseEntityBaseEntity) {
                super.onNext(baseEntityBaseEntity);

            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }

            @Override
            public void onComplete() {
                super.onComplete();
            }
        },"AndroidAppKey",0,"","","");
    }
}
