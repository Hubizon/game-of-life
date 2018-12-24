package com.howky.hubert.gameoflife.work;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class CustomPagerJobAdapter extends FragmentPagerAdapter {
    private static final int NUM_PAGES = 2;
    // --Commented out by Inspection (12/8/2018 12:30 AM):private final Context context;

    public CustomPagerJobAdapter(FragmentManager fm, Context context) {
        super(fm);
        //this.context = context;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
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
            case 0:
                return "Job";
            case 1:
                return "Crim Job";
            default:
                return "Job";
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
