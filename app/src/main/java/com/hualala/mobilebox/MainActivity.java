package com.hualala.mobilebox;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.hualala.domain.interactor.DefaultObserver;
import com.hualala.domain.model.MVideo;
import com.hualala.domain.usecase.VideoListUseCase;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = menuItem -> {
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
    };


    private void showMusic() {


    }

    private void showVideo() {


    }

    private void showPicture() {


    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        try {
            Log.v("tag", DecimalFormat.getInstance().parse("00.00").toString());
            Log.v("tag", DecimalFormat.getInstance().parse("000").toString());
            Log.v("tag", DecimalFormat.getInstance().parse("0.").toString());
            Log.v("tag", DecimalFormat.getInstance().parse(".0").toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        VideoListUseCase videoListUseCase = MBApplication.getBusinessContractor().create(VideoListUseCase.class);
        videoListUseCase.execute(new Function<List<MVideo>, Integer>() {
            @Override
            public Integer apply(List<MVideo> o) throws Exception {
                return 1;
            }
        }, new Consumer<Integer>() {
            @Override
            public void accept(Integer o) throws Exception {

            }
        }, new DefaultObserver<Integer>() {
            @Override
            public void onComplete() {
                super.onComplete();
            }

            @Override
            public void onNext(Integer mVideos) {
                super.onNext(mVideos);
            }

            @Override
            public void onError(Throwable throwable) {
                super.onError(throwable);
            }
        }, null);

        getLifecycle().addObserver(new LifecycleObserver() {

            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            public void onDestory() {
                videoListUseCase.dispose();
            }

        });


    }

}
