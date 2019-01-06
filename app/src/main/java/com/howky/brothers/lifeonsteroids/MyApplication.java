package com.howky.brothers.lifeonsteroids;

import android.app.Application;
import androidx.lifecycle.ProcessLifecycleOwner;
import android.content.Context;
import android.content.SharedPreferences;

import com.howky.brothers.lifeonsteroids.utils.MainTimer;


public class MyApplication extends Application implements SampleLifecycleListener.OnLifeCycleEventChange {

    private final SampleLifecycleListener lifecycleListener = new SampleLifecycleListener(this);

    public MainTimer mainTimer;
    public static Context CurrentContext;

    public static SharedPreferences mainSharedPref;
    public static SharedPreferences userSharedPref;
    public static int currentUserNumber;

    @Override
    public void onCreate (){
        super.onCreate();
        setupLifecycleListener();
        mainTimer = new MainTimer();

    }

    private void setupLifecycleListener() {
        ProcessLifecycleOwner.get().getLifecycle().addObserver(lifecycleListener);
    }

    @Override
    public void stopGlobalTimer() {
        mainTimer.stopTimer();
    }

    @Override
    public void startGlobalTimer() {
        if (MainTimer.shouldWork)
            mainTimer.startTimer();
    }

    public static Context getMyContext() {
        return CurrentContext;
    }


}