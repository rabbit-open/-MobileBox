package com.hualala.mobilebox.module.boot.view;

import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.zxing.WriterException;
import com.hualala.domain.model.MVideo;
import com.hualala.mobilebox.MBBusinessContractor;
import com.hualala.mobilebox.R;
import com.hualala.mobilebox.R2;
import com.hualala.mobilebox.base.ViewDelegate;
import com.hualala.mobilebox.module.UINavgation;
import com.hualala.mobilebox.module.boot.viewmodel.MainViewData;
import com.hualala.mobilebox.module.zxing.QRCodeUtils;
import com.hualala.mobilebox.widget.recyclelib.SupetRecyclerAdapter;
import com.hualala.mobilebox.widget.recyclelib.SupetRecyclerView;
import com.hualala.mobilebox.widget.recyclelib.SupetRecyclerViewHolder;
import com.hualala.mobilebox.widget.recyclelib.SupetStaggeredGridLayoutManager;
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
        adapter = new SupetRecyclerAdapter<MVideo>(getContext()) {
            @Override
            public int getItemViewType(int position) {
                return getData(position).getType();
            }

            @Override
            public SupetRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType, SupetRecyclerAdapter adapter) {
                View itemView = null;
                if (viewType == MVideo.MVideoTypeVideo) {
                    itemView = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.card_video_layout, parent, false);
                } else if (viewType == MVideo.MVideoTypeAudio) {
                    itemView = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.card_audio_layout, parent, false);
                } else {
                    itemView = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.card_base_layout, parent, false);
                }
                return new SupetRecyclerViewHolder(itemView, adapter);
            }

            @Override
            public void onBindViewHolder(SupetRecyclerViewHolder holder, int position) {
                MVideo data = getData(position);
                int viewType = getItemViewType(position);
                if (viewType == MVideo.MVideoTypeVideo) {
                    SimpleDraweeView simpleDraweeView = holder.itemView.findViewById(R.id.main_pic);
                    simpleDraweeView.setAspectRatio(1.7f);

                    final String path = MBBusinessContractor.getFileBaseUrl() + data.getPath();
                    simpleDraweeView.setController(Fresco.newDraweeControllerBuilder()
                            .setUri(path)
                            .setRetainImageOnFailure(true)
                            .setOldController(simpleDraweeView.getController())
                            .setAutoPlayAnimations(true).build());

                    ImageView playerView = holder.itemView.findViewById(R.id.playbtn);
                    playerView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            UINavgation.startPlayerActivity(getContext(), path);
                        }
                    });

                } else if (viewType == MVideo.MVideoTypeAudio) {

                    TextView name = holder.itemView.findViewById(R.id.name);
                    name.setText(data.getName());

                    String path = MBBusinessContractor.getFileBaseUrl() + data.getPath();

                    ImageView playerView = holder.itemView.findViewById(R.id.playbtn);
                    playerView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            UINavgation.startPlayerActivity(getContext(), path);
                        }
                    });
                } else {

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

            }
        };
        mListView.setAdapter(adapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UINavgation.startScanCodeActivity(getContext());
            }
        });

        floatingActionButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

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
        });
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
