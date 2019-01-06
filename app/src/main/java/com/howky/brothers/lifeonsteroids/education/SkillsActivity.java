package com.howky.brothers.lifeonsteroids.education;

import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.howky.brothers.lifeonsteroids.MyApplication;
import com.howky.brothers.lifeonsteroids.R;
import com.howky.brothers.lifeonsteroids.utils.Dialogs;

public class SkillsActivity extends AppCompatActivity implements Dialogs.OnResumeDialogInteractionListener {

    private ViewPager mPager;
    private CustomPagerSkillsAdapter mPagerAdapter;
    private TabLayout mTabLayout;
    private final int[] tabIcons = {
            R.drawable.skills,
            R.drawable.crim_skills,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);

        mPager = findViewById(R.id.pagerSkills);
        mPagerAdapter = new CustomPagerSkillsAdapter(getSupportFragmentManager(), this);
        mPager.setAdapter(mPagerAdapter);

        mTabLayout = findViewById(R.id.tablayoutskills);
        mTabLayout.setupWithViewPager(mPager);
        setupTabIcons();
    }

    private void setupTabIcons() {
        mTabLayout.getTabAt(0).setIcon(tabIcons[0]);
        mTabLayout.getTabAt(1).setIcon(tabIcons[1]);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.CurrentContext = this;
    }

    @Override
    public void onDialogResume(MenuItem item) {
        Log.e("TODO", "TODO");
    }
}
