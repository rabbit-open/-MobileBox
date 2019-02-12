package com.hualala.mobilebox.module.boot.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.hualala.mobilebox.R;
import com.hualala.mobilebox.R2;
import com.hualala.mobilebox.base.BaseFragment;
import com.hualala.mobilebox.base.ViewDelegate;
import com.hualala.mobilebox.module.boot.presenter.MainPresenter;
import com.hualala.mobilebox.module.boot.viewmodel.MainShareViewModel;
import com.hualala.mobilebox.module.boot.viewmodel.MainViewData;
import com.hualala.mobilebox.module.boot.viewmodel.MainViewModel;
import com.hualala.mobilebox.widget.recyclelib.SupetRecyclerAdapter;
import com.hualala.mobilebox.widget.recyclelib.SupetRecyclerView;
import com.hualala.mobilebox.widget.recyclelib.SupetStaggeredGridLayoutManager;

public class PictureListFragment extends BaseFragment {

    @BindView(R2.id.list)
    public SupetRecyclerView mListView;

    private SupetRecyclerAdapter adapter;

    private MainPresenter mainPresenter;

    @Override
    public int getContentLayout() {
        return R.layout.activity_fragment;
    }

    @Override
    public void findViews(View view) {
        ButterKnife.bind(this, view);

        mListView.setLayoutManager(new SupetStaggeredGridLayoutManager(2, SupetStaggeredGridLayoutManager.VERTICAL));
        adapter = new MainAdapter(getContext());
        mListView.setAdapter(adapter);

        mainPresenter = new MainPresenter(new ViewDelegate<>(getContentView()));

    }

    private void updateData() {
        Integer type = getIntArgument("type");
        if (1 == type) {
            mainPresenter.getMediaImage();
        } else if (2 == type) {
            mainPresenter.getMediaVideo();
        } else if (3 == type) {
            mainPresenter.getMediaAudio();
        }
    }

    @Override
    public void setListeners() {
        MainViewModel viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        viewModel.getData().observe(getActivity(), new Observer<MainViewData>() {
            @Override
            public void onChanged(@Nullable MainViewData mainViewData) {
                updateData(mainViewData);
            }
        });

        MainShareViewModel model = ViewModelProviders.of(getActivity()).get(MainShareViewModel.class);
        model.getData().observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean != null && aBoolean) {
                    updateData();
                }
            }
        });
    }

    @Override
    public void process() {
        updateData();
    }

    @SuppressWarnings("unchecked")
    public void updateData(MainViewData originData) {
        Integer type = getIntArgument("type");
        if (type == originData.getType()) {
            if (adapter != null) {
                adapter.addHomePage(originData.getVideoList());
            }
        }
    }

    public static PictureListFragment newInstance(Integer type) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        PictureListFragment fragment = new PictureListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

}
