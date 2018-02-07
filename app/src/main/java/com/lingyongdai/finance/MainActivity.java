package com.lingyongdai.finance;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.lingyongdai.finance.base.BaseActivity;
import com.lingyongdai.finance.bean.TabEntity;
import com.lingyongdai.finance.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    private String[] mTitles = {"首页", "收益", "服务", "我的"};
    private int[] mIconUnselectIds = {
            R.mipmap.main_licai_huise_icon, R.mipmap.main_shouyi_huise_icon, R.mipmap.main_fuwu_huise_icon, R.mipmap.main_myac_huise_icon};
    private int[] mIconSelectIds = {
            R.mipmap.main_licai_icon, R.mipmap.main_shouyi_icon, R.mipmap.main_fuwu_icon, R.mipmap.main_myac_icon};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ActivityMainBinding activityMainBinding;
    //判断上一个tab是否变大
    int startTabPotison = -1;

    @Override
    protected void initView() {
        activityMainBinding= (ActivityMainBinding) getDataBinding();
        initTab();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setEvent() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 初始化tab
     */
    private void initTab() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        activityMainBinding.tabLayout.setTabData(mTabEntities);
        activityMainBinding.tabLayout.bringToFront();
        ImageView iconView = activityMainBinding.tabLayout.getIconView(0);
        iconView.setScaleX(2f * iconView.getScaleX());
        iconView.setScaleY(2f * iconView.getScaleY());
        //点击监听
        activityMainBinding.tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (startTabPotison==-1&&position==0){
                    return;
                }else if (startTabPotison==-1){
                    ImageView iconView = activityMainBinding.tabLayout.getIconView(0);
                    iconView.setScaleX(iconView.getScaleX()/2f);
                    iconView.setScaleY(iconView.getScaleY()/2f);
                }
                ImageView iconView = activityMainBinding.tabLayout.getIconView(position);
                iconView.setScaleX(2f * iconView.getScaleX());
                iconView.setScaleY(2f  * iconView.getScaleY());
                if (startTabPotison != -1) {
                    ImageView startIconView = activityMainBinding.tabLayout.getIconView(startTabPotison);
                    startIconView.setScaleX(iconView.getScaleX()/2f);
                    startIconView.setScaleY(iconView.getScaleY()/2f);
                }
                startTabPotison = position;


            }

            @Override
            public void onTabReselect(int position) {
            }
        });
    }
}
