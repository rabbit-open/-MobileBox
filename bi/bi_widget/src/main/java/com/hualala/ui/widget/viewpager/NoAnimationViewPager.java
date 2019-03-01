package com.hualala.ui.widget.viewpager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

public class NoAnimationViewPager extends ViewPager {


    public NoAnimationViewPager(@NonNull Context context) {
        super(context);
    }

    public NoAnimationViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    /**
     * 去除页面切换时的滑动翻页效果
     *
     * @param item
     */
    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item, false);
    }

}