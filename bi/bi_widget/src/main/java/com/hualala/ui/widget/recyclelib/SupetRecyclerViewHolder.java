package com.hualala.ui.widget.recyclelib;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SupetRecyclerViewHolder extends RecyclerView.ViewHolder {

    public SupetRecyclerView.SupetRecyclerViewAdapter mDRecyclerViewAdapter;
    public SupetRecyclerAdapter mDBaseRecyclerViewAdapter;
    private View mWholeView;


    public SupetRecyclerViewHolder(View itemView, SupetRecyclerAdapter mDBaseRecyclerViewAdapter) {
        super(itemView);
        mWholeView = itemView;
        this.mDRecyclerViewAdapter = mDBaseRecyclerViewAdapter.getmDRecyclerViewAdapter();
        this.mDBaseRecyclerViewAdapter = mDBaseRecyclerViewAdapter;
    }

    public View getWholeView() {
        return mWholeView;
    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T view(@IdRes int id) {
        return (T) itemView.findViewById(id);
    }

    public <T extends Object> void setData(T data, int position) {

    }

    /**
     * 获取点击的item的position
     *
     * @return
     */
    public int getAdapterItemPosition() {
        int oldPosition = getAdapterPosition();

        if (mDRecyclerViewAdapter == null) {
            return oldPosition;
        }

        if (mDRecyclerViewAdapter.isHeader(oldPosition) || mDRecyclerViewAdapter.isFooter(oldPosition)) {
            return -1;
        } else {
            return oldPosition - mDRecyclerViewAdapter.getHeaderViewsCount();
        }
    }
}
