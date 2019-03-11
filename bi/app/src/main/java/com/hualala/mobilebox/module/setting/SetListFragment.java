package com.hualala.mobilebox.module.setting;

import android.os.Bundle;
import android.view.View;

import com.hualala.bi.framework.base.BaseFragment;
import com.hualala.mobilebox.R;
import com.hualala.mobilebox.R2;
import com.hualala.ui.widget.recyclelib.SupetRecyclerAdapter;
import com.hualala.ui.widget.recyclelib.SupetRecyclerView;
import com.hualala.ui.widget.recyclelib.SupetStaggeredGridLayoutManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SetListFragment extends BaseFragment {

    @BindView(R2.id.list)
    public SupetRecyclerView mListView;

    private SupetRecyclerAdapter adapter;

    @Override
    public int getContentLayout() {
        return R.layout.activity_sets_fragment;
    }

    @Override
    public void findViews(View view) {
        ButterKnife.bind(this, view);

        mListView.setLayoutManager(new SupetStaggeredGridLayoutManager(1, SupetStaggeredGridLayoutManager.VERTICAL));
        adapter = new SettingsAdapter(getContext(), this);
        mListView.setAdapter(adapter);

    }


    @Override
    public void setListeners() {

    }

    @Override
    public void process() {

    }

    public static SetListFragment newInstance() {
        Bundle bundle = new Bundle();
        SetListFragment fragment = new SetListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

}
