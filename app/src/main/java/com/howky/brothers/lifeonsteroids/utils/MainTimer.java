package com.howky.brothers.lifeonsteroids.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AppCompatActivity;

import com.howky.brothers.lifeonsteroids.MainActivity;
import com.howky.brothers.lifeonsteroids.MyApplication;
import com.howky.brothers.lifeonsteroids.MyDialogDead;
import com.howky.brothers.lifeonsteroids.R;

import static com.howky.brothers.lifeonsteroids.MainActivity.hasAdd;
import static com.howky.brothers.lifeonsteroids.MyApplication.CurrentContext;
import static com.howky.brothers.lifeonsteroids.MyApplication.getMyContext;

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

        if (hasAdd)
            (new Dialogs(CurrentContext)).showDialogWithChoose(userSharedPref, CurrentContext, CurrentContext.getString(R.string.died), getMyContext().getString(R.string.want_to_be_rescued_watc_ad), 7);
        else {
            MainTimer.shouldWork = false;
            stopTimer();
            DialogFragment deadDialog = MyDialogDead.newInstance();
            deadDialog.show(((AppCompatActivity)CurrentContext).getSupportFragmentManager(), "open_dead_dialog_tag");
        }
    }

}