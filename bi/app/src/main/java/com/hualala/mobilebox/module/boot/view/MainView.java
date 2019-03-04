package com.hualala.mobilebox.module.boot.view;

import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.view.MenuItem;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import com.google.zxing.WriterException;
import com.hualala.bi.framework.application.MBBusinessContractor;
import com.hualala.mobilebox.R;
import com.hualala.mobilebox.R2;
import com.hualala.bi.framework.base.ViewDelegate;
import com.hualala.mobilebox.module.UINavgation;
import com.hualala.mobilebox.module.boot.viewmodel.MainViewData;
import com.hualala.mobilebox.module.zxing.QRCodeUtils;
import com.hualala.ui.widget.recyclelib.SupetRecyclerAdapter;
import com.hualala.ui.widget.recyclelib.SupetRecyclerView;
import com.hualala.ui.widget.recyclelib.SupetStaggeredGridLayoutManager;
import com.sample.commondialog.QRDialog;
import lombok.Setter;

@Keep
public class MainView extends ViewDelegate<View> implements IMainView,
        BottomNavigationView.OnNavigationItemSelectedListener {


    @Setter
    private IMainViewListener Listener;

    @BindView(R2.id.list)
    public SupetRecyclerView mListView;

    @BindView(R2.id.navigation)
    public BottomNavigationView navigationView;
    @BindView(R2.id.floatbtn)
    public FloatingActionButton floatingActionButton;

    private SupetRecyclerAdapter adapter;

    public MainView(View view) {
        super(view);
        ButterKnife.bind(this, view);

        navigationView.setOnNavigationItemSelectedListener(this);

        mListView.setLayoutManager(new SupetStaggeredGridLayoutManager(2, SupetStaggeredGridLayoutManager.VERTICAL));
        adapter = new MainAdapter(getContext());
        mListView.setAdapter(adapter);
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
        switch (navigationView.getSelectedItemId()) {
            case R.id.navigation_home:
                showPicture();
                break;
            case R.id.navigation_dashboard:
                showVideo();
                break;
            case R.id.navigation_notifications:
                showMusic();
                break;
        }
    }

}
