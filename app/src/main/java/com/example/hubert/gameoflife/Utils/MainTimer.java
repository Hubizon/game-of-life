package com.example.hubert.gameoflife.Utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;

import com.example.hubert.gameoflife.MainActivity;
import com.example.hubert.gameoflife.R;


public class MainTimer {

    private Context mContext;
    private SharedPreferences userSharedPref;
    private UpdateValues updateValues;

    private OnTimerInterActionListener mListener;
    public interface OnTimerInterActionListener {
        void onDeathInteraction();
    }

    public MainTimer() {
        updateValues = new UpdateValues();
    }

    public MainTimer(Context context, SharedPreferences sharedPreferences) {
        mContext = context;
        userSharedPref = sharedPreferences;

        updateValues = new UpdateValues();
        isRunning = false;

        if (context instanceof OnTimerInterActionListener) {
            mListener = (OnTimerInterActionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    public static boolean shouldWork = true;
    public boolean isRunning;
    public static boolean isMainActvityActive;
    private static final int TIMER_LOOP_TIME = 1000;
    private Handler mHandler;
    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            UpdateValues.updateSharedPreferences(mContext, userSharedPref);
            updateValues.interactWithUI(mContext, userSharedPref);

            if (userSharedPref.getInt(mContext.getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth) <= 0) {
                userSharedPref.edit().putBoolean(mContext.getString(R.string.saved_is_dead_key), true).apply();
                if (isMainActvityActive) mListener.onDeathInteraction();
                else {
                    MainTimer.shouldWork = false;

                    Intent intent = new Intent(mContext, MainActivity.class);
                    mContext.startActivity(intent);
                }
            } else {
                if (isRunning) mHandler.postDelayed(mRunnable, TIMER_LOOP_TIME);
            }
        }
    };

    public void stopTimer() {
        isRunning = false;
        if (mHandler != null) {
            mHandler.removeCallbacks(mRunnable);
        }
    }

    public void startTimer() {
        if (isRunning) return;
        isRunning = true;
        mHandler = new Handler();
        mHandler.postDelayed(mRunnable, TIMER_LOOP_TIME);
//        if (mHandler == null) {
//            mHandler = new Handler();
//            mHandler.postDelayed(mRunnable, TIMER_LOOP_TIME);
//        } else {
//            mHandler.removeCallbacksAndMessages(null);
//            mHandler.postDelayed(mRunnable, TIMER_LOOP_TIME);
//        }
    }

    public void setContext(Context context) {
        this.mContext = context;

        if (context instanceof OnTimerInterActionListener) {
            mListener = (OnTimerInterActionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public void setUserSharedPref(SharedPreferences userSharedPref) {
        this.userSharedPref = userSharedPref;
    }

}