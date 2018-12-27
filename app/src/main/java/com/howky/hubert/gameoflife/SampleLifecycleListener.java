package com.howky.hubert.gameoflife;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;

class SampleLifecycleListener implements LifecycleObserver {

    private OnLifeCycleEventChange mListener;
    public interface OnLifeCycleEventChange {
        void stopGlobalTimer();
        void startGlobalTimer();
    }

    SampleLifecycleListener(Context context) {
        if (context instanceof OnLifeCycleEventChange) {
            mListener = (OnLifeCycleEventChange) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnLifeCycleEventChange");
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onMoveToForeground() {
        mListener.startGlobalTimer();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onMoveToBackground() {
        mListener.stopGlobalTimer();
    }
}
