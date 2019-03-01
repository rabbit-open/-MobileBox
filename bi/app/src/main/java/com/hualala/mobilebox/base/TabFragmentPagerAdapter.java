package com.hualala.mobilebox.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

public abstract class TabFragmentPagerAdapter extends FragmentPagerAdapter {

    private ViewPager mViewPager;
    private FragmentManager fragmentMgr;

    public TabFragmentPagerAdapter(ViewPager viewPager, FragmentManager fm) {
        super(fm);
        mViewPager = viewPager;
        fragmentMgr = fm;
    }

    @Override
    public final Fragment getItem(int position) {
        BaseFragment fragment = getItemFragment(position);
        if (fragment != null) {
            fragment.setLazyLoad(position != mViewPager.getCurrentItem());
        }
        return fragment;
    }

    public abstract BaseFragment getItemFragment(int position);

    public BaseFragment findFragment(int position) {
        String tag = "android:switcher:" + mViewPager.getId() + ":" + position;
        return (BaseFragment) fragmentMgr.findFragmentByTag(tag);
    }

    public void removeAllFragment(int count) {
        FragmentTransaction ft = fragmentMgr.beginTransaction();
        for (int index = 0; index < count; index++) {
            BaseFragment fragment = findFragment(index);
            if (fragment == null) {
                continue;
            }
            ft.remove(fragment);
        }
        ft.commit();
        fragmentMgr.executePendingTransactions();
    }

}
