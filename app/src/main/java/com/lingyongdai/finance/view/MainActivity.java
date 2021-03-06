package com.lingyongdai.finance.view;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.widget.ImageView;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.lingyongdai.finance.R;
import com.lingyongdai.finance.adapter.MyViewPaerAdapter;
import com.lingyongdai.finance.base.BaseActivity;
import com.lingyongdai.finance.bean.TabEntity;
import com.lingyongdai.finance.databinding.ActivityMainBinding;
import com.lingyongdai.finance.view.fragment.EarningsPageFragment;
import com.lingyongdai.finance.view.fragment.HomePageFragment;
import com.lingyongdai.finance.view.fragment.MyInfoPageFragment;
import com.lingyongdai.finance.view.fragment.ServicePageFragment;
import com.lingyongdai.finance.viewmodel.DaggerMActivityViewModel_MainComponent;
import com.lingyongdai.finance.viewmodel.MActivityViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {
    private String[] mTitles = {"首页", "收益", "服务", "我的"};
    private int[] mIconUnselectIds = {
            R.mipmap.main_licai_huise_icon, R.mipmap.main_shouyi_huise_icon, R.mipmap.main_fuwu_huise_icon, R.mipmap.main_myac_huise_icon};
    private int[] mIconSelectIds = {
            R.mipmap.main_licai_icon, R.mipmap.main_shouyi_icon, R.mipmap.main_fuwu_icon, R.mipmap.main_myac_icon};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ActivityMainBinding activityMainBinding;
    private List<Fragment> fragmentList = new ArrayList<>();
    //判断上一个tab是否变大
    int startTabPotison = -1;
    @Inject
    MActivityViewModel mActivityViewModel;

    @Override
    protected void initView(Bundle savedInstanceState) {
        //全面屏
        activityMainBinding = getDataBinding();
        fragmentList.add(new HomePageFragment());
        fragmentList.add(new EarningsPageFragment());
        fragmentList.add(new ServicePageFragment());
        fragmentList.add(new MyInfoPageFragment());
        //viewpager缓存页面+当前页面
        activityMainBinding.viewPager.setOffscreenPageLimit(3);
        activityMainBinding.viewPager.setAdapter(new MyViewPaerAdapter(getSupportFragmentManager(), Arrays.asList(mTitles), fragmentList));
        initTab();
//        MActivityViewModel mActivityViewModel=new MActivityViewModel(this,activityMainBinding);
        DaggerMActivityViewModel_MainComponent.builder()
                .mainModule(new MActivityViewModel.MainModule(this, activityMainBinding))
                .build()
                .inject(this);
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
                //捆绑viewpager显示
                activityMainBinding.viewPager.setCurrentItem(position);
                //tab变大变小效果
                if (startTabPotison == -1 && position == 0) {
                    return;
                } else if (startTabPotison == -1) {
                    ImageView iconView = activityMainBinding.tabLayout.getIconView(0);
                    iconView.setScaleX(iconView.getScaleX() / 2f);
                    iconView.setScaleY(iconView.getScaleY() / 2f);
                }
                ImageView iconView = activityMainBinding.tabLayout.getIconView(position);
                iconView.setScaleX(2f * iconView.getScaleX());
                iconView.setScaleY(2f * iconView.getScaleY());
                if (startTabPotison != -1) {
                    ImageView startIconView = activityMainBinding.tabLayout.getIconView(startTabPotison);
                    startIconView.setScaleX(iconView.getScaleX() / 2f);
                    startIconView.setScaleY(iconView.getScaleY() / 2f);
                }
                startTabPotison = position;


            }

            @Override
            public void onTabReselect(int position) {
            }
        });
    }
}
