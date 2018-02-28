package com.lingyongdai.finance.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * Created by guoliang on 2018/2/26.
 */

public abstract class BaseFragment extends RxFragment {
    ViewDataBinding mViewDataBinding;
    LayoutInflater inflater;
    ViewGroup container;

    protected abstract int getLayoutId();

    /**
     * 初始化界面
     * @param savedInstanceState
     */
    protected abstract void initView(Bundle savedInstanceState);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater=inflater;
        this.container=container;
        mViewDataBinding=DataBindingUtil.inflate(inflater,getLayoutId(),container,false);
        initView(savedInstanceState);
        return mViewDataBinding.getRoot();
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
}
