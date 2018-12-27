package com.howky.hubert.gameoflife.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;

import com.howky.hubert.gameoflife.MainActivity;
import com.howky.hubert.gameoflife.MyApplication;
import com.howky.hubert.gameoflife.MyDialogDead;
import com.howky.hubert.gameoflife.R;

import static com.howky.hubert.gameoflife.MainActivity.hasAdd;
import static com.howky.hubert.gameoflife.MainActivity.mRewardedVideoAd;
import static com.howky.hubert.gameoflife.MyApplication.CurrentContext;

public class MainTimer {

    //private Context mContext;
    private SharedPreferences userSharedPref;
    private UpdateValues updateValues;


    public MainTimer() {
        updateValues = new UpdateValues();
    }

    public MainTimer(Context context, SharedPreferences sharedPreferences) {
        //mContext = context;
        userSharedPref = sharedPreferences;

        updateValues = new UpdateValues();
        isRunning = false;
    }

    public static boolean shouldWork = true;
    public boolean isRunning;
    public static boolean isMainActvityActive;
    private static final int TIMER_LOOP_TIME = 1000;
    private Handler mHandler;
    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            UpdateValues.updateSharedPreferences(MyApplication.CurrentContext, userSharedPref);
            updateValues.interactWithUI(MyApplication.CurrentContext, userSharedPref);

            if (userSharedPref.getInt(MyApplication.CurrentContext.getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth) <= 0) {
                userSharedPref.edit().putBoolean(MyApplication.CurrentContext.getString(R.string.saved_is_dead_key), true).apply();
                if (isMainActvityActive) Die();
                else {
                    MainTimer.shouldWork = false;

                    Intent intent = new Intent(MyApplication.CurrentContext, MainActivity.class);
                    MyApplication.CurrentContext.startActivity(intent);
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
    }

    public void setContext(Context context) {
        //this.mContext = context;
    }

    public void setUserSharedPref(SharedPreferences userSharedPref) {
        this.userSharedPref = userSharedPref;
    }


    public void Die() {

        SharedPreferences.Editor editor = userSharedPref.edit();
        editor.putBoolean(CurrentContext.getString(R.string.saved_is_dead_key), true);
        editor.apply();

        if (hasAdd && mRewardedVideoAd.isLoaded())
            (new Dialogs(CurrentContext)).showDialogWithChoose(userSharedPref, CurrentContext, CurrentContext.getString(R.string.died), "Do you want to be rescued by watching an ad?", 7);
        else {
            MainTimer.shouldWork = false;
            stopTimer();
            DialogFragment deadDialog = MyDialogDead.newInstance();
            deadDialog.show(((AppCompatActivity)CurrentContext).getSupportFragmentManager(), "open_dead_dialog_tag");
        }
    }

}