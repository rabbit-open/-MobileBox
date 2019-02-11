package com.hualala.mobilebox.module.boot.view;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewCompat;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import com.google.zxing.WriterException;
import com.hualala.mobilebox.MBBusinessContractor;
import com.hualala.mobilebox.R;
import com.hualala.mobilebox.R2;
import com.hualala.mobilebox.base.ViewDelegate;
import com.hualala.mobilebox.module.UINavgation;
import com.hualala.mobilebox.module.boot.viewmodel.MainViewData;
import com.hualala.mobilebox.module.zxing.QRCodeUtils;
import com.hualala.mobilebox.widget.recyclelib.SupetRecyclerAdapter;
import com.hualala.mobilebox.widget.recyclelib.SupetRecyclerView;
import com.hualala.mobilebox.widget.recyclelib.SupetStaggeredGridLayoutManager;
import com.sample.commondialog.QRDialog;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainTabView extends ViewDelegate<View> implements IMainView,
        BottomNavigationView.OnNavigationItemSelectedListener {

    @Setter
    private IMainViewListener Listener;

    @BindView(R2.id.list)
    public SupetRecyclerView mListView;

    @BindView(R2.id.floatbtn)
    public FloatingActionButton floatingActionButton;
    @BindView(R2.id.tab)
    public TabLayout mTabLayout;

    private List<String> tabIndicators;

    private SupetRecyclerAdapter adapter;

    public MainTabView(View view) {
        super(view);
        ButterKnife.bind(this, view);

        initTab();
        mListView.setLayoutManager(new SupetStaggeredGridLayoutManager(2, SupetStaggeredGridLayoutManager.VERTICAL));
        adapter = new MainAdapter(getContext());
        mListView.setAdapter(adapter);
    }

    private void initTab() {

        tabIndicators = new ArrayList<>();
        tabIndicators.add("图片");
        tabIndicators.add("视频");
        tabIndicators.add("音乐");
        tabIndicators.add("设置");

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(null);//根据viewpager自动创建tab数量，此处为NULL.则mTabLayout.getTabAt(i)为null，需要创建


        if (tabIndicators.size() > 0) {

            for (int i = 0; i < tabIndicators.size(); i++) {
                TabLayout.Tab itemTab = mTabLayout.getTabAt(i);
                if (itemTab == null) {
                    itemTab = mTabLayout.newTab();
                    itemTab.setCustomView(R.layout.item_tab_item);
                    mTabLayout.addTab(itemTab);
                }

                TextView itemTv = Objects.requireNonNull(itemTab.getCustomView()).findViewById(R.id.tv_menu_item);
                itemTv.setText(tabIndicators.get(i));
            }

            mTabLayout.getTabAt(0).select();
        }
        //放在最后，防止第一次select执行
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                refresh();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @OnClick(R2.id.floatbtn)
    public void onFloatClick() {
        UINavgation.startScanCodeActivity(getContext());
    }

    @OnLongClick(R2.id.floatbtn)
    public boolean onFloatLongClick() {
        String url = MBBusinessContractor.getBusinessContractor().getGeneralConfig().getCloudServerInfo().getBaseApiUrl();
        try {
            QRDialog.newInstance()
                    .setTitle("本机服务器地址二维码")
                    .setImage(QRCodeUtils.CreateTwoDCode(url))
                    .setContent(url)
                    .setMargin(60)
                    .setOutCancel(true)
                    .show(getFragmentActivity().getSupportFragmentManager());
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void updateData(MainViewData originData) {
        adapter.addHomePage(originData.getVideoList());
    }

    private void showMusic() {
        Listener.getMediaAudio();
    }

    private void showVideo() {
        Listener.getMediaVideo();
    }

    private void showPicture() {
        Listener.getMediaImage();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                showPicture();
                return true;
            case R.id.navigation_dashboard:
                showVideo();
                return true;
            case R.id.navigation_notifications:
                showMusic();
                return true;
        }
        return true;
    }

    public void refresh() {
        switch (mTabLayout.getSelectedTabPosition()) {
            case 0:
                showPicture();
                break;
            case 1:
                showVideo();
                break;
            case 2:
                showMusic();
                break;
        }
    }

}
