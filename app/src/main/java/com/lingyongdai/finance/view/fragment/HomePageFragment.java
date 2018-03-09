package com.lingyongdai.finance.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.lingyongdai.finance.R;
import com.lingyongdai.finance.base.BaseFragment;
import com.lingyongdai.finance.viewmodel.fragment.HomePageViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends BaseFragment {

    private static final String TAG = "HomePageFragment";

    public HomePageFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_page;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        HomePageViewModel homePageViewModel=new HomePageViewModel(getActivity());
    }

}
