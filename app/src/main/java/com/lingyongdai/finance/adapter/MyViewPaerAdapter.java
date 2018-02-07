package com.lingyongdai.finance.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by guoliang on 2018/2/5.
 */

public class MyViewPaerAdapter extends FragmentPagerAdapter{
    private List<String> titles;
    private List<Fragment> fragments;

    public MyViewPaerAdapter(FragmentManager fm, List<String> titles,List<Fragment> fragments) {
        super(fm);
        this.titles = titles;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 :fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
