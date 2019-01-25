package com.hualala.mobilebox;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import butterknife.BindView;
import com.google.gson.Gson;
import com.hualala.domain.interactor.DefaultObserver;
import com.hualala.domain.model.MVideo;
import com.hualala.domain.usecase.VideoListUseCase;
import com.hualala.mobilebox.utils.WifiUtils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public RecyclerView mListView ;


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
        VideoListUseCase videoListUseCase = MBBusinessContractor.getBusinessContractor().create(VideoListUseCase.class);
        videoListUseCase.execute(new DefaultObserver<List<MVideo>>() {
            @Override
            public void onNext(List<MVideo> mVideos) {

            }


            @Override
            public void onError(Throwable throwable) {
                super.onError(throwable);
            }

            @Override
            public void onComplete() {
                super.onComplete();
            }
        }, "mp3");


    }

    private void showVideo() {
        VideoListUseCase videoListUseCase = MBBusinessContractor.getBusinessContractor().create(VideoListUseCase.class);
        videoListUseCase.execute(new DefaultObserver<List<MVideo>>() {
            @Override
            public void onNext(List<MVideo> mVideos) {

                Log.d("test", new Gson().toJson(mVideos));

            }


            @Override
            public void onError(Throwable throwable) {
                super.onError(throwable);
            }

            @Override
            public void onComplete() {
                super.onComplete();
            }
        }, "mp4");

    }

    private void showPicture() {
        VideoListUseCase videoListUseCase = MBBusinessContractor.getBusinessContractor().create(VideoListUseCase.class);
        videoListUseCase.execute(new DefaultObserver<List<MVideo>>() {
            @Override
            public void onNext(List<MVideo> mVideos) {
                Log.d("test", new Gson().toJson(mVideos));

            }


            @Override
            public void onError(Throwable throwable) {
                super.onError(throwable);
            }

            @Override
            public void onComplete() {
                super.onComplete();
            }
        }, "jpg");

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String ip = WifiUtils.getWifiIp(this);
        if (!TextUtils.isEmpty(ip)) {
            MBBusinessContractor.getBusinessContractor().getTerminalDataRepository().changeServerAddr("http://" + ip + ":8888/");
        }

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



    }

}
