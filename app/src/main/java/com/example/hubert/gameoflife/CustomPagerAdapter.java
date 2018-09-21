package com.example.hubert.gameoflife;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.hubert.gameoflife.Utils.SharedPreferencesDefaultValues;
import com.example.hubert.gameoflife.Work.WorkFragment;

public class CustomPagerAdapter extends FragmentPagerAdapter {

    private static final int NUM_PAGES = 5;
    Context context;

    public CustomPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                SharedPreferences sharedPref = context.getSharedPreferences(context.getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
                if(sharedPref.getBoolean(context.getResources().getString(R.string.saved_is_in_school_now_key), SharedPreferencesDefaultValues.DefaultIsInSchoolNow))
                    return EducationFragment.newInstance();
                else
                    return WorkFragment.newInstance();
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
