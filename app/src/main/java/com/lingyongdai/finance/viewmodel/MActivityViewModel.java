package com.lingyongdai.finance.viewmodel;

import android.app.Activity;

import com.lingyongdai.finance.api.HttpManager;
import com.lingyongdai.finance.base.BaseSubscriber;
import com.lingyongdai.finance.bean.PlatformData;
import com.lingyongdai.finance.databinding.ActivityMainBinding;
import com.lingyongdai.finance.dialog.MainZhuCeDialog;

import io.reactivex.disposables.Disposable;

/**
 * Created by guoliang on 2018/2/26.
 */

public class MActivityViewModel {


    public MActivityViewModel(final Activity activity, ActivityMainBinding mainBinding) {
        HttpManager.getInstance().getAllAmountMonth(new BaseSubscriber<PlatformData>() {

            @Override
            public void onSubscribe(Disposable d) {
                super.onSubscribe(d);
            }

            @Override
            public void onNext(PlatformData platformData) {
                super.onNext(platformData);
                MainZhuCeDialog mainZhuCeDialog=new MainZhuCeDialog(activity, platformData.getImgMain(), platformData.getImgVice(), new MainZhuCeDialog.MyDialogListener() {
                    @Override
                    public void quxiaoListener() {

                    }

                    @Override
                    public void sureListener() {

                    }
                });
                mainZhuCeDialog.show();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }

            @Override
            public void onComplete() {
                super.onComplete();
            }
        }, "AndroidAppKey", 0, "", "", "");
    }

}
