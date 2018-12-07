package com.example.hubert.gameoflife.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

public class MainTimer {

    private Context mContext;
    private SharedPreferences userSharedPref;

    public MainTimer(Context context, SharedPreferences sharedPreferences) {
        mContext = context;
        userSharedPref = sharedPreferences;
    }

    private static final int TIMER_LOOP_TIME = 1000;
    private Handler mHandler;
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (mHandler != null) {
                mHandler.postDelayed(mRunnable, TIMER_LOOP_TIME);
            }

        }
    };

    public void stopTimer() {
        if (mHandler != null) {
            mHandler.removeCallbacks(mRunnable);
            mHandler = null;
        }
    }

    public void startTimer() {
        if (mHandler == null) {
            mHandler = new Handler();
        }
        mHandler.postDelayed(mRunnable, TIMER_LOOP_TIME);
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    public void setUserSharedPref(SharedPreferences userSharedPref) {
        this.userSharedPref = userSharedPref;
    }

}