package com.example.hubert.gameoflife;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.hubert.gameoflife.Education.EducationFragment;
import com.example.hubert.gameoflife.Girlboyfriend.GirlboyfriendFragment;
import com.example.hubert.gameoflife.House.HomeFragment;
import com.example.hubert.gameoflife.Profile.MainFragment;
import com.example.hubert.gameoflife.Shop.ShopFragment;

public class CustomPagerAdapter extends FragmentPagerAdapter {

    private static final int NUM_PAGES = 5;

    public CustomPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return EducationFragment.newInstance();
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

//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        switch (position) {
//            case 1:
//                return "Edu";
//            case 2:
//                return "Shop";
//            case 3:
//                return "Love";
//            case 4:
//                return "Home";
//            default:
//                return "Profile";
//        }
//    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
