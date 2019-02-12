package com.hualala.mobilebox.module.player;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import com.hualala.libutils.view.ToastUtils;
import com.hualala.mobilebox.R;
import com.hualala.mobilebox.base.BaseContractorActivity;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class MP3Player extends BaseContractorActivity {
    private EditText et_path;
    private Button btn_play, btn_pause, btn_replay, btn_stop;
    private SeekBar btn_process;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp3player);

        String path = getIntent().getStringExtra("path");

        et_path = findViewById(R.id.et_path);
        btn_process = findViewById(R.id.btn_process);
        btn_process.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.seekTo(seekBar.getProgress());
                }
            }
        });
        if (path.startsWith("file://")) {
            path = path.replaceFirst("file://", "");
        }

        et_path.setText(path);

        btn_play = findViewById(R.id.btn_play);
        btn_pause = findViewById(R.id.btn_pause);
        btn_replay = findViewById(R.id.btn_replay);
        btn_stop = findViewById(R.id.btn_stop);

        btn_play.setOnClickListener(click);
        btn_pause.setOnClickListener(click);
        btn_replay.setOnClickListener(click);
        btn_stop.setOnClickListener(click);

        mTimer = new Timer();
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    btn_process.setProgress(mediaPlayer.getCurrentPosition());
                }
            }
        };
        mTimer.schedule(mTimerTask, 0, 1000);
    }

    Timer mTimer;
    TimerTask mTimerTask;

    private View.OnClickListener click = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.btn_play:
                    play();
                    break;
                case R.id.btn_pause:
                    pause();
                    break;
                case R.id.btn_replay:
                    replay();
                    break;
                case R.id.btn_stop:
                    stop();
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 播放音乐
     */
    protected void play() {
        String path = et_path.getText().toString().trim();
        File file = new File(path);
        if (file.exists() && file.length() > 0) {
            try {
                btn_process.setProgress(0);

                mediaPlayer = new MediaPlayer();
                // 设置指定的流媒体地址
                mediaPlayer.setDataSource(path);
                // 设置音频流的类型
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

                // 通过异步的方式装载媒体资源
                mediaPlayer.prepareAsync();
                mediaPlayer.setOnPreparedListener(new OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        // 装载完毕 开始播放流媒体
                        mediaPlayer.start();
                        ToastUtils.showToastCenter(MP3Player.this, "开始播放");
                        // 避免重复播放，把播放按钮设置为不可用
                        btn_play.setEnabled(false);
                        btn_process.setMax(mediaPlayer.getDuration());
                    }
                });

                // 设置循环播放
                // mediaPlayer.setLooping(true);
                mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        // 在播放完毕被回调
                        btn_play.setEnabled(true);
                        btn_process.setProgress(mediaPlayer.getDuration());
                    }
                });

                mediaPlayer.setOnErrorListener(new OnErrorListener() {

                    @Override
                    public boolean onError(MediaPlayer mp, int what, int extra) {
                        // 如果发生错误，重新播放
                        replay();
                        return false;
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                ToastUtils.showToastCenter(MP3Player.this, "播放失败");
            }
        } else {
            ToastUtils.showToastCenter(MP3Player.this, "文件不存在");
        }

    }

    /**
     * 暂停
     */
    protected void pause() {
        if (btn_pause.getText().toString().trim().equals("继续")) {
            btn_pause.setText("暂停");
            mediaPlayer.start();
            ToastUtils.showToastCenter(MP3Player.this, "继续播放");
            return;
        }
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            btn_pause.setText("继续");
            ToastUtils.showToastCenter(MP3Player.this, "暂停播放");
        }

    }

    /**
     * 重新播放
     */
    protected void replay() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(0);
            btn_process.setProgress(0);
            ToastUtils.showToastCenter(MP3Player.this, "重新播放");
            btn_pause.setText("暂停");
            return;
        }
        play();
    }

    /**
     * 停止播放
     */
    protected void stop() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            btn_play.setEnabled(true);
            btn_process.setProgress(0);
            ToastUtils.showToastCenter(MP3Player.this, "停止播放");
        }

    }

    @Override
    protected void onDestroy() {
        // 在activity结束的时候回收资源
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }

        if (mTimerTask != null) {
            mTimerTask.cancel();
            mTimer.cancel();
        }
        super.onDestroy();
    }
}