package com.hualala.mobilebox.module.boot.view;

import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hualala.domain.model.MVideo;
import com.hualala.mobilebox.MBBusinessContractor;
import com.hualala.mobilebox.R;
import com.hualala.mobilebox.R2;
import com.hualala.mobilebox.base.ViewDelegate;
import com.hualala.mobilebox.module.boot.viewmodel.MainViewData;
import com.hualala.mobilebox.widget.recyclelib.SupetRecyclerAdapter;
import com.hualala.mobilebox.widget.recyclelib.SupetRecyclerView;
import com.hualala.mobilebox.widget.recyclelib.SupetRecyclerViewHolder;
import com.hualala.mobilebox.widget.recyclelib.SupetStaggeredGridLayoutManager;
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


    private SupetRecyclerAdapter adapter;

    public MainView(View view) {
        super(view);
        ButterKnife.bind(this, view);
        navigationView.setOnNavigationItemSelectedListener(this);
        mListView.setLayoutManager(new SupetStaggeredGridLayoutManager(2, SupetStaggeredGridLayoutManager.VERTICAL));
        adapter = new SupetRecyclerAdapter<MVideo>(getContext()) {
            @Override
            public int getItemViewType(int position) {
                return 0;
            }

            @Override
            public SupetRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType, SupetRecyclerAdapter adapter) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.card_base_layout, parent, false);
                return new SupetRecyclerViewHolder(itemView, adapter);
            }

            @Override
            public void onBindViewHolder(SupetRecyclerViewHolder holder, int position) {
                MVideo data = getData(position);
                SimpleDraweeView simpleDraweeView = holder.itemView.findViewById(R.id.main_pic);

                if (data.getHeight() > 0) {
                    simpleDraweeView.setAspectRatio(data.getWidth() * 1f / data.getHeight());
                } else {
                    simpleDraweeView.setAspectRatio(1f);
                }

                String path = MBBusinessContractor.getFileBaseUrl() + (data.getThumbPath() == null ? data.getPath() : data.getThumbPath());
                simpleDraweeView.setController(Fresco.newDraweeControllerBuilder()
                        .setUri(path)
                        .setRetainImageOnFailure(true)
                        .setOldController(simpleDraweeView.getController())
                        .setAutoPlayAnimations(true).build());
            }
        };
        mListView.setAdapter(adapter);
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
        return false;
    }
}
