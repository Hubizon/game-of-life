package com.example.hubert.gameoflife.Work;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.hubert.gameoflife.Education.SkillsFragment;

public class CustomPagerJobAdapter extends FragmentPagerAdapter {
    private static final int NUM_PAGES = 2;
    Context context;

    public CustomPagerJobAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
//                SharedPreferences sharedPref = context.getSharedPreferences(context.getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
//                if(sharedPref.getBoolean(context.getResources().getString(R.string.saved_is_in_school_now_key), SharedPreferencesDefaultValues.DefaultIsInSchoolNow))
//                    return EducationFragment.newInstance();
//                else
//                    return WorkFragment.newInstance();
                return ChooseJobFragment.newInstance();
            case 2:
                return ChooseJobFragment.newInstance();
            default:
                return ChooseJobFragment.newInstance();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 1:
                return "Edu Skills";
            case 2:
                return "Crim Skills";
            case 3:
                return "Sth Skills";
            default:
                return "Skills";
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
