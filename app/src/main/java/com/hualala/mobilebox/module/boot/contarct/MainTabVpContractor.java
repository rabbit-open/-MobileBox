package com.hualala.mobilebox.module.boot.contarct;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.hualala.mobilebox.R;
import com.hualala.mobilebox.R2;
import com.hualala.mobilebox.base.BaseContractor;
import com.hualala.mobilebox.base.BaseFragment;
import com.hualala.mobilebox.module.boot.view.PictureListFragment;
import com.hualala.mobilebox.module.setting.SetListFragment;

import java.util.ArrayList;
import java.util.List;

public class MainTabVpContractor extends BaseContractor {

    @BindView(R2.id.tab)
    public TabLayout mTabLayout;
    @BindView(R2.id.viewpager)
    public ViewPager mViewPager;

    private List<String> tabIndicators;
    private List<BaseFragment> tabFragments;

    public MainTabVpContractor(FragmentActivity lifecycleOwner) {
        super(lifecycleOwner);

        View rootView = LayoutInflater.from(lifecycleOwner)
                .inflate(R.layout.activity_main_tabvp, null);
        lifecycleOwner.setContentView(rootView);

        ButterKnife.bind(this, rootView);

        tabIndicators = new ArrayList<>();
        tabIndicators.add("图片");
        tabIndicators.add("视频");
        tabIndicators.add("音乐");
        tabIndicators.add("设置");

        tabFragments = new ArrayList<>();
        tabFragments.add(PictureListFragment.newInstance(1));
        tabFragments.add(PictureListFragment.newInstance(2));
        tabFragments.add(PictureListFragment.newInstance(3));
        tabFragments.add(SetListFragment.newInstance());

        mViewPager.setAdapter(new ContentPagerAdapter(getLifecycleOwner().getSupportFragmentManager()));

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < tabIndicators.size(); i++) {
            TabLayout.Tab itemTab = mTabLayout.getTabAt(i);
            if (itemTab != null) {
                itemTab.setCustomView(R.layout.item_tab_item);
                TextView itemTv = itemTab.getCustomView().findViewById(R.id.tv_menu_item);
                itemTv.setText(tabIndicators.get(i));
            }
            mTabLayout.getTabAt(0).select();
        }

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                tabFragments.get(mViewPager.getCurrentItem()).manuRefresh();
            }
        });
    }

    class ContentPagerAdapter extends FragmentPagerAdapter {

        public ContentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return tabFragments.get(position);
        }

        @Override
        public int getCount() {
            return tabIndicators.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabIndicators.get(position);
        }
    }
}
