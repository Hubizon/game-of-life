package com.example.hubert.gameoflife;

import android.app.Application;
import android.arch.lifecycle.ProcessLifecycleOwner;

import com.example.hubert.gameoflife.utils.MainTimer;


public class MyApplication extends Application implements SampleLifecycleListener.OnLifeCycleEventChange{

    private final SampleLifecycleListener lifecycleListener = new SampleLifecycleListener(this);

    public MainTimer mainTimer;

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
}