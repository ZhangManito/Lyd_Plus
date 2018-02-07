package com.lingyongdai.finance.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.lingyongdai.finance.manager.ActivityStackManager;
import com.lingyongdai.finance.manager.ScreenManager;

import java.lang.ref.WeakReference;

/**
 * Created by guoliang on 2018/2/5.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";
    private ViewDataBinding mViewDataBinding;
    private WeakReference<Activity> activity;
    /**
     * 是否沉浸状态栏
     **/
    private boolean isStatusBar = true;
    /**
     * 是否允许全屏
     **/
    private boolean isFullScreen = true;

    /**
     * 是否禁止旋转屏幕
     **/
    private boolean isScreenRoate = false;
    /**
     * 是否输出日志信息
     **/
    private boolean isDebug;

    /**
     * 初始化界面
     **/
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 绑定事件
     */
    protected abstract void setEvent();

    protected abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "--->onCreate()");
        activity = new WeakReference<Activity>(this);
        ActivityStackManager.getInstance().addActivity(activity);
        mViewDataBinding = getDataBinding();
        initView();
        initData();
        setEvent();
        ScreenManager screenManager = ScreenManager.getInstance();
//        screenManager.setStatusBar(isStatusBar, this);
//        screenManager.setScreenRoate(isScreenRoate, this);
//        screenManager.setFullScreen(isFullScreen, this);
    }
    protected ViewDataBinding getDataBinding() {
        return DataBindingUtil.setContentView(this, getLayoutId());
    }
    /**
     * 跳转Activity
     * skip Another Activity
     *
     * @param activity
     * @param cls
     */
    public static void skipAnotherActivity(Activity activity,
                                           Class<? extends Activity> cls) {
        Intent intent = new Intent(activity, cls);
        activity.startActivity(intent);
        activity.finish();
    }

    /**
     * 退出应用
     * called while exit app.
     */
    public void exitLogic() {
        ActivityStackManager.getInstance().AppExit(this);//remove all activity.
    }

    /**
     * [是否设置沉浸状态栏]
     *
     * @param statusBar
     */
    public void setStatusBar(boolean statusBar) {
        isStatusBar = statusBar;
    }

    /**
     * [是否设置全屏]
     *
     * @param fullScreen
     */
    public void setFullScreen(boolean fullScreen) {
        isFullScreen = fullScreen;
    }

    /**
     * [是否设置旋转屏幕]
     *
     * @param screenRoate
     */
    public void setScreenRoate(boolean screenRoate) {
        isScreenRoate = screenRoate;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "--->onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "--->onResume()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "--->onRestart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "--->onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "--->onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityStackManager.getInstance().removeActivity(activity);
    }
}
