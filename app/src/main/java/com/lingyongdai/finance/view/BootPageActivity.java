package com.lingyongdai.finance.view;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lingyongdai.finance.R;
import com.lingyongdai.finance.base.BaseActivity;
import com.lingyongdai.finance.databinding.ActivityBootPageBinding;

public class BootPageActivity extends BaseActivity {

    //引导图
    private TypedArray images;
    //记录上一次位置
    private int prevPosition;
    private ActivityBootPageBinding bootPageBinding;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_boot_page;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        bootPageBinding = getDataBinding();
        images=getResources().obtainTypedArray(R.array.splash_image);
        // 构建适配器对象  将ViewPager和适配器对象进行关联
        bootPageBinding.splashViewpager.setAdapter(new CarouselAdapter());
        initIndicator();
        bootPageBinding.splashViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // 上一个点
                View prevView = bootPageBinding.layoutIndicator.getChildAt(prevPosition);
                prevView.setEnabled(false);
                // 当前选中点
                View currView = bootPageBinding.layoutIndicator.getChildAt(position);
                currView.setEnabled(true);
                prevPosition = position;
                if(position==images.length()-1){
                    bootPageBinding.btnGo.setVisibility(View.VISIBLE);
                    bootPageBinding.layoutIndicator.setVisibility(View.GONE);
                }else {
                    bootPageBinding.btnGo.setVisibility(View.GONE);
                    bootPageBinding.layoutIndicator.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bootPageBinding.btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(BootPageActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * 初始化小圆点信息
     */
    private void initIndicator() {
        View view;
        for (int i = 0; i < images.length(); i++) {
            // 创建一个圆点对象
            view = new View(this);

            // 设置点的大小以及点之间的距离
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(25, 25);
            if (i != 0) {
                params.leftMargin = 20;
            }
            // 设置参数
            view.setLayoutParams(params);
            // 设置圆点的背景
            view.setBackgroundResource(R.drawable.bootpager_round_dot_select);
            // 将圆点加入到指示器容器
            bootPageBinding.layoutIndicator.addView(view);
            // 设置第一个默认启用
            view.setEnabled(i == 0);
        }
    }
    class CarouselAdapter extends PagerAdapter {

        /**
         * 返回页卡的数量
         *
         * @return
         */
        @Override
        public int getCount() {
            return images.length();
        }

        /**
         * 判断当前加载的业卡是否是一个view对象
         *
         * @param view
         * @param object
         * @return
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /**
         * 销毁一个item
         *
         * @param container
         * @param position
         * @param object
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        /**
         * 装载一个view
         *
         * @param container
         * @param position
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            // 创建用于展示图片的ImageView
            ImageView view = new ImageView(container.getContext());
            // 设置显示的背景
            view.setBackground(images.getDrawable(position));
            // 将view对象加入到 ViewPager 容器
            container.addView(view);
            // 返回显示的一项ImageView对象
            return view;
        }
    }
}
