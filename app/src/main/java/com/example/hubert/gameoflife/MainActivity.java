package com.example.hubert.gameoflife;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hubert.gameoflife.Utils.SharedPreferencesDefaultValues;
import com.example.hubert.gameoflife.Utils.UpdateValues;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    public static final String INTENT_PAGE = "intent_page";

    Gson gson = new Gson();
    String json;
    private ViewPager mPager;
    private CustomPagerAdapter mPagerAdapter;
    private TabLayout mTabLayout;
    private int[] tabIcons = {
            R.drawable.profile_icon,
            R.drawable.education_icon,
            R.drawable.shop_icon,
            R.drawable.girlboyfriend_icon,
            R.drawable.house_icon
    };

    static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        mPager = findViewById(R.id.pager);
        mPagerAdapter = new CustomPagerAdapter(getSupportFragmentManager(), this);
        mPager.setAdapter(mPagerAdapter);

        Intent intent = getIntent();
        if (intent.hasExtra(INTENT_PAGE)) {
            mPager.setCurrentItem(intent.getIntExtra(INTENT_PAGE, 0));
        }

        mTabLayout = findViewById(R.id.tablayout);
        mTabLayout.setupWithViewPager(mPager);
        setupTabIcons();

        mHandler = new Handler();
        mHandler.postDelayed(mRunnable,1000);
    }

    static SharedPreferences sharedPref;
    private Handler mHandler;
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            sharedPref = getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
            UpdateValues.updateSharedPreferences(MainActivity.this, sharedPref);
            mHandler.postDelayed(mRunnable,1000);
        }
    };


    private void setupTabIcons() {
        mTabLayout.getTabAt(0).setIcon(tabIcons[0]);
        mTabLayout.getTabAt(1).setIcon(tabIcons[1]);
        mTabLayout.getTabAt(2).setIcon(tabIcons[2]);
        mTabLayout.getTabAt(3).setIcon(tabIcons[3]);
        mTabLayout.getTabAt(4).setIcon(tabIcons[4]);
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(mRunnable);
    }
}