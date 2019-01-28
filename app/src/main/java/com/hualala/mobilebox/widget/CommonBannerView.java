package com.hualala.mobilebox.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hualala.libcommonui.RatioFrameLayout;
import com.hualala.libcommonui.banner.MYBannerData;
import com.hualala.libcommonui.banner.MYSlideImageView;
import com.hualala.mobilebox.R;

import java.util.ArrayList;
import java.util.List;

//banner设置宽高比,按高或者宽匹配
public class CommonBannerView extends RatioFrameLayout implements View.OnClickListener {

    private int mWidth = 600;
    private int mHeight = 1024;

    private MYSlideImageView mSlideImageView;
    private OnBannerRefreshListener mListener;

    protected List<Object> mBanners;
    private MYBannerData.BannerType mBannerType;

    public CommonBannerView(Context context) {
        this(context, null);
    }

    public CommonBannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommonBannerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        mSlideImageView = new MYSlideImageView(context);
        mSlideImageView.setVisibility(View.GONE);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        addView(mSlideImageView, params);

        mSlideImageView.setAutoPlay(true);
        mSlideImageView.setStrategy(new MYSlideImageView.Strategy() {

            @Override
            public View instantiateItem(Context context, Object object, int position, ViewGroup container) {
                return initiateBannerView(context, object, container);
            }
        });

        setPadding(0, 1, 0, 0);//解决下拉
    }

    /**
     * 重写此方法可自定义布局
     *
     * @param context
     * @param object
     * @return
     */
    public View initiateBannerView(Context context, Object object, ViewGroup container) {
        MYBannerData banner = (MYBannerData) object;
        SimpleDraweeView imageView = new SimpleDraweeView(context);
        Drawable placeholder = getResources().getDrawable(R.drawable.place_holder);
        imageView.getHierarchy().setPlaceholderImage(placeholder, ScalingUtils.ScaleType.CENTER_CROP);
        imageView.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP);
        imageView.setTag(banner);
        imageView.setOnClickListener(this);
        imageView.setImageURI(Uri.parse(banner.image));
        return imageView;
    }

    @Override
    public void onClick(View v) {
        MYBannerData banner = (MYBannerData) v.getTag();

    }

    /**
     * 在onresume回调
     */
    @Deprecated
    private void onResume() {
        mSlideImageView.onResume();
    }

    /**
     * 在onpause回调
     */
    @Deprecated
    private void onPause() {
        mSlideImageView.onPause();
    }

    /**
     * 刷新数据
     */
    public void refresh() {
        refresh(null);
    }

    /**
     * 刷新数据，有数据回调
     *
     * @param listener
     */
    public void refresh(OnBannerRefreshListener listener) {
        mListener = listener;
        //这里获取本地缓存，根据banner类型
        if (mBanners != null && mBanners.size() > 0) {
            realRefreshBanner();
        }
        requestBannerData();
    }

    /**
     * 子类实现此方法，获取banner数据
     */
    public void requestBannerData() {

    }

    public void setBannerRatio(int width, int height) {
        this.mWidth = width;
        this.mHeight = height;
    }

    protected void realRefreshBanner() {
        boolean hasBanner = hasBanner();
        adjustImageViewHeight();
        mSlideImageView.setData(mBanners);
        mSlideImageView.setVisibility(hasBanner ? View.VISIBLE : View.GONE);
        if (mListener != null) {
            mListener.onRefresh(hasBanner);
        }
    }

    private void adjustImageViewHeight() {
        setRatio(mWidth * 1.0f / mHeight);
    }

    private boolean hasBanner() {
        return mBanners != null && !mBanners.isEmpty();
    }

    public interface OnBannerRefreshListener {
        void onRefresh(boolean hasBanner);
    }

    /**
     * 设置banner类型
     *
     * @param type
     */
    public void setBannerType(MYBannerData.BannerType type) {
        mBannerType = type;
    }

    public MYBannerData.BannerType getBannerType() {
        return mBannerType;
    }

    /**
     * 更新banner数据，默认第一个图的宽高
     *
     * @param data
     */
    public void resetBannerData(ArrayList<Object> data) {
        mBanners = data;
        realRefreshBanner();
    }

    /**
     * 更新banner数据，默认第一个图的宽高
     *
     * @param data
     */
    public void setBannerData(List<Object> data) {
        mBanners = data;
        refresh();
    }

}

