package com.lingyongdai.finance.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.lingyongdai.finance.R;
import com.lingyongdai.finance.base.BaseFragment;
import com.lingyongdai.finance.databinding.FragmentEarningsPageBinding;
import com.lingyongdai.finance.view.WelcomeActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class EarningsPageFragment extends BaseFragment {



    public EarningsPageFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_earnings_page;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        FragmentEarningsPageBinding pageBinding= getDataBinding();
        pageBinding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), WelcomeActivity.class));
            }
        });
    }

}
