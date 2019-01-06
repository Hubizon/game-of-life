package com.howky.brothers.lifeonsteroids.education;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CustomPagerSkillsAdapter extends FragmentPagerAdapter {

    private static final int NUM_PAGES = 2;
    Context context;

    public CustomPagerSkillsAdapter(FragmentManager fm, Context context) {
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
                return SkillsFragment.newInstance();
            case 2:
                return SkillsFragment.newInstance();
            default:
                return SkillsFragment.newInstance();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Skills";
            case 1:
                return "Crim Skills";
            default:
                return "Skills";
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}