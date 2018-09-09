package com.example.hubert.gameoflife;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hubert.gameoflife.Education.Subject;
import com.example.hubert.gameoflife.Utils.UpdateValues;

public class MainActivity extends AppCompatActivity{

    //TODO: make default value to sharedPrefernces

    public static Subject[] subjectsList = new Subject[] { new Subject("Mathematics", 4),
    new Subject("English", 4),
    new Subject("Languages", 4),
    new Subject("History", 4),
    new Subject("Chemistry", 4),
    new Subject("Psychic", 4),
    new Subject("Biology", 4),
    new Subject("Information Technology", 4),
    new Subject("Art", 4),
    new Subject("Music", 4),
    new Subject("Psychical Education", 4),
    new Subject("Behavior", 4), };

    public static int CommunicationSkills = 100;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPager = findViewById(R.id.pager);
        mPagerAdapter = new CustomPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        mTabLayout = findViewById(R.id.tablayout);
        mTabLayout.setupWithViewPager(mPager);
        setupTabIcons();

        mHandler = new Handler();
        mHandler.postDelayed(mRunnable,1000);
    }

    SharedPreferences sharedPref;
    private Handler mHandler;
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            sharedPref = getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
            UpdateValues.updateSharedPreferences(getApplicationContext(), sharedPref);
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