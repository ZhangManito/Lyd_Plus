package com.lingyongdai.finance.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import com.lingyongdai.finance.R;
import com.lingyongdai.finance.base.BaseActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        SharedPreferences setting = getSharedPreferences("SHARE_APP_TAG", 0);
        final Boolean user_first = setting.getBoolean("FirstOpen",true);
        Observable.timer(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (user_first){
                            startActivity(new Intent(WelcomeActivity.this, BootPageActivity.class));
                        }else {
                            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                        }
                        finish();
                    }
                });
    }

}
