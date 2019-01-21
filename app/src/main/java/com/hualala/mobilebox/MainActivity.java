package com.hualala.mobilebox;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import java.text.DecimalFormat;
import java.text.ParseException;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
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

    }

}
