package com.hualala.libutils.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.lang.ref.WeakReference;

@SuppressLint("ClickableViewAccessibility")
public class ViewTapCompat implements View.OnTouchListener, View.OnClickListener {

    private OnViewTapListener mOnViewTapListener;
    private GestureDetectorCompat impl;
    private WeakReference<View> mAttartView;

    public ViewTapCompat(View view) {
        mAttartView = new WeakReference<>(view);
        view.setClickable(true);
        view.setLongClickable(true);
        view.setOnTouchListener(this);
        view.setOnClickListener(this);
    }

    public ViewTapCompat(View view, OnViewTapListener onViewTapListener) {
        mAttartView = new WeakReference<>(view);
        view.setClickable(true);
        view.setLongClickable(true);
        view.setOnTouchListener(this);
        view.setOnClickListener(this);
        this.mOnViewTapListener = onViewTapListener;
    }

    public void setOnViewTapListener(OnViewTapListener onViewTapListener) {
        this.mOnViewTapListener = onViewTapListener;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        initGesture(view.getContext());
        impl.onTouchEvent(motionEvent);
        return false;
    }

    private void initGesture(Context context) {
        if (impl == null) {
            impl = new GestureDetectorCompat(context,
                    new GestureDetector.SimpleOnGestureListener());
            impl.setOnDoubleTapListener(new GestureDetector.OnDoubleTapListener() {

                private long delay = 0;

                @Override
                public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                    if (mOnViewTapListener != null) {
                        if (System.currentTimeMillis() - delay > 1000) {
                            delay= System.currentTimeMillis();
                            mOnViewTapListener.onViewSingleTap();
                        }
                    }
                    return false;
                }

                @Override
                public boolean onDoubleTap(MotionEvent motionEvent) {
                    if (mOnViewTapListener != null) {
                            delay = System.currentTimeMillis();
                            mOnViewTapListener.onViewDoubleTap();
                    }
                    return true;
                }

                @Override
                public boolean onDoubleTapEvent(MotionEvent motionEvent) {
                    return true;
                }
            });
        }
    }

    @Override
    public void onClick(View view) {

    }

    public interface OnViewTapListener {
        void onViewDoubleTap();

        void onViewSingleTap();
    }
}
