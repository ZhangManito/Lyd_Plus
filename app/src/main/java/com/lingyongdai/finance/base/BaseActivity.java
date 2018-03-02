package com.lingyongdai.finance.base;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.gyf.barlibrary.ImmersionBar;
import com.lingyongdai.finance.R;
import com.lingyongdai.finance.manager.ActivityStackManager;
import com.lingyongdai.finance.utils.StatusBarUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.lang.ref.WeakReference;

/**
 * Created by guoliang on 2018/2/5.
 */

public abstract class BaseActivity extends RxAppCompatActivity {
    private static final String TAG = "BaseActivity";
    private ViewDataBinding mViewDataBinding;
    private WeakReference<Activity> activity;


    protected abstract int getLayoutId();


    /**
     * 初始化界面
     **/
    protected abstract void initView(Bundle savedInstanceState);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏

        activity = new WeakReference<Activity>(this);
        ActivityStackManager.getInstance().addActivity(activity);
        mViewDataBinding = DataBindingUtil.setContentView(this,getLayoutId());
//        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorAccent));
        StatusBarUtil.setTranslucent(this,50);
        initView(savedInstanceState);
    }

    /**
     * 获取ViewDataBinding
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T extends ViewDataBinding>T getDataBinding(){
        return (T)mViewDataBinding;
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

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
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
