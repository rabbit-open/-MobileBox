package com.hualala.mobilebox.module.boot.view;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.zxing.WriterException;
import com.hualala.domain.model.MVideo;
import com.hualala.mobilebox.MBBusinessContractor;
import com.hualala.mobilebox.R;
import com.hualala.mobilebox.module.UINavgation;
import com.hualala.mobilebox.module.zxing.QRCodeUtils;
import com.hualala.mobilebox.widget.recyclelib.SupetRecyclerAdapter;
import com.hualala.mobilebox.widget.recyclelib.SupetRecyclerViewHolder;
import com.sample.commondialog.QRDialog;

public class MainAdapter extends SupetRecyclerAdapter<MVideo> {

    public MainAdapter(Context mContext) {
        super(mContext);
    }

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

            final String thumbPath = MBBusinessContractor.getFileBaseUrl() + data.getThumbPath();
            Log.v("thumbPath", thumbPath);

            final String path = MBBusinessContractor.getFileBaseUrl() + data.getPath();

            simpleDraweeView.setController(Fresco.newDraweeControllerBuilder()
                    .setUri(thumbPath)
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

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    try {
                        String path = MBBusinessContractor.getShareFileBaseUrl() + data.getPath();
                        QRDialog.newInstance()
                                .setTitle("分享视频二维码")
                                .setImage(QRCodeUtils.CreateTwoDCode(path))
                                .setContent(path)
                                .setMargin(60)
                                .setOutCancel(true)
                                .show(((FragmentActivity) getContext()).getSupportFragmentManager());
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
            });

        } else if (viewType == MVideo.MVideoTypeAudio) {

            TextView name = holder.itemView.findViewById(R.id.name);
            name.setText(data.getName());

            String path = MBBusinessContractor.getFileBaseUrl() + data.getPath();
            Log.v("path", path);

            ImageView playerView = holder.itemView.findViewById(R.id.playbtn);
            playerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UINavgation.startPlayerActivity(getContext(), path);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    try {
                        String path = MBBusinessContractor.getShareFileBaseUrl() + data.getPath();
                        QRDialog.newInstance()
                                .setTitle("分享音乐二维码")
                                .setImage(QRCodeUtils.CreateTwoDCode(path))
                                .setContent(path)
                                .setMargin(60)
                                .setOutCancel(true)
                                .show(((FragmentActivity) getContext()).getSupportFragmentManager());
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                    return true;
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

            Log.v("path", path);

            simpleDraweeView.setController(Fresco.newDraweeControllerBuilder()
                    .setUri(path)
                    .setRetainImageOnFailure(true)
                    .setOldController(simpleDraweeView.getController())
                    .setAutoPlayAnimations(true).build());

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    try {
                        String path = MBBusinessContractor.getShareFileBaseUrl() + data.getPath();
                        QRDialog.newInstance()
                                .setTitle("分享图片二维码")
                                .setImage(QRCodeUtils.CreateTwoDCode(path))
                                .setContent(path)
                                .setMargin(60)
                                .setOutCancel(true)
                                .show(((FragmentActivity) getContext()).getSupportFragmentManager());
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
            });
        }

    }
}