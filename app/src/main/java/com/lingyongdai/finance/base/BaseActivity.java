package com.lingyongdai.finance.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lingyongdai.finance.manager.ActivityStackManager;

import java.lang.ref.WeakReference;

/**
 * Created by guoliang on 2018/2/5.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private WeakReference<Activity> activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = new WeakReference<Activity>(this);
        ActivityStackManager.getInstance().addActivity(activity);
        initView();
    }
    /**
     * 初始化控件
     */
    protected abstract void initView();

    @Override
    protected void onDestroy() {
        ActivityStackManager.getInstance().removeActivity(activity);
        super.onDestroy();
    }
}
