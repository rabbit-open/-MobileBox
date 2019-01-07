package com.hualala.mobilebox

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                showPicture()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                showVideo()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                showMusic();
                return@OnNavigationItemSelectedListener true
            }
        }
        return@OnNavigationItemSelectedListener false
    }

    private fun showMusic() {


    }

    private fun showVideo() {


    }

    private fun showPicture() {


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

}
