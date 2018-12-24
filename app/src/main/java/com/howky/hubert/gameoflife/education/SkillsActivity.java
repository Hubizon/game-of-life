package com.example.hubert.gameoflife.education;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hubert.gameoflife.R;

public class SkillsActivity extends AppCompatActivity {

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
}
