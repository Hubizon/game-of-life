package com.howky.brothers.lifeonsteroids;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.howky.brothers.lifeonsteroids.education.EduFragment;
import com.howky.brothers.lifeonsteroids.girlboyfriend.GirlboyfriendFragment;
import com.howky.brothers.lifeonsteroids.house.HomeFragment;
import com.howky.brothers.lifeonsteroids.profile.MainFragment;
import com.howky.brothers.lifeonsteroids.shop.ShopFragment;

class CustomPagerAdapter extends FragmentPagerAdapter {

    private static final int NUM_PAGES = 5;
    // --Commented out by Inspection (12/8/2018 12:30 AM):private final Context context;

    public CustomPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        //this.context = context;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return EduFragment.newInstance();
            case 2:
                return ShopFragment.newInstance();
            case 3:
                return GirlboyfriendFragment.newInstance();
            case 4:
                return HomeFragment.newInstance();
            default:
                return MainFragment.newInstance();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 1:
                return MyApplication.getMyContext().getString(R.string.edu_education);
            case 2:
                return MyApplication.getMyContext().getString(R.string.shop);
            case 3:
                return MyApplication.getMyContext().getString(R.string.love);
            case 4:
                return MyApplication.getMyContext().getString(R.string.home);
            default:
                return MyApplication.getMyContext().getString(R.string.main);
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
