package com.example.hubert.gameoflife;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.hubert.gameoflife.Profile.MainFragment;
import com.example.hubert.gameoflife.Utils.Dialogs;
import com.example.hubert.gameoflife.FirstOpen.MyDialogOpenFragment;
import com.example.hubert.gameoflife.Utils.SharedPreferencesDefaultValues;
import com.example.hubert.gameoflife.Utils.UpdateValues;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public class MainActivity extends AppCompatActivity
    implements RewardedVideoAdListener, MyDialogOpenFragment.OnNewUserAdd, MyDialogDead.OnDialogDeadInteractionListener, Dialogs.OnDialogInteractionListener {

    private boolean isMainActvityActive;

    private static final int TIMER_LOOP_TIME = 1000;
    private static final String TAG = MainActivity.class.getSimpleName();

    public static final String INTENT_PAGE = "intent_page";
    private static AnimatedVectorDrawable mPlayDrawable;
    private static AnimatedVectorDrawable mPauseDrawable;

    public static RewardedVideoAd mRewardedVideoAd;
    private ViewPager mPager;
    private CustomPagerAdapter mPagerAdapter;
    private TabLayout mTabLayout;
    private int[] tabIcons = {
            R.drawable.profile_icon,
            R.drawable.ic_education,
            R.drawable.shop_icon,
            R.drawable.girlboyfriend_icon,
            R.drawable.house_icon
    };

   Context mContext;

   private UpdateValues updateValues;

    public static int currentUserNumber;
    public static SharedPreferences userSharedPref;
    static SharedPreferences sharedPref;
    private Handler mHandler;
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            UpdateValues.updateSharedPreferences(mContext, userSharedPref);
            updateValues.interactWithUI(mContext, userSharedPref);
            if (mHandler != null) {
                mHandler.postDelayed(mRunnable, TIMER_LOOP_TIME);
                if(userSharedPref.getInt(getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth) <= 0)
                    if (isMainActvityActive) Die();
                    else {
                        mHandler = null;
                        startActivity(new Intent(mContext, MainActivity.class));
                    }
            }
        }
    };

    private static boolean hasAdd = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);

        isMainActvityActive = true;

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean isDark = sharedPreferences.getBoolean(SettingsActivity.DARK_SWITCH_KEY, false);
        if (isDark) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        MobileAds.initialize(this, getString(R.string.MY_ADMOB_APP_ID));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        sharedPref = getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);

        String user_key = getString(R.string.shared_preferences_user_key)
                + currentUserNumber;
        userSharedPref = getSharedPreferences(user_key, MODE_PRIVATE);

        mPager = findViewById(R.id.pager);
        mPagerAdapter = new CustomPagerAdapter(getSupportFragmentManager(), this);
        mPager.setAdapter(mPagerAdapter);

        Intent intent = getIntent();
        if (intent.hasExtra(INTENT_PAGE)) {
            mPager.setCurrentItem(intent.getIntExtra(INTENT_PAGE, 0));
        }

        mTabLayout = findViewById(R.id.tablayout);
        mTabLayout.setupWithViewPager(mPager);
        setupTabIcons();

        mPlayDrawable = (AnimatedVectorDrawable) getDrawable(R.drawable.avd_play_pause);
        mPauseDrawable = (AnimatedVectorDrawable) getDrawable(R.drawable.avd_pause_play);

        updateValues = new UpdateValues();

        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();

        if (mHandler == null) {
            Log.d(TAG, "the Handler is null!");
            mHandler = new Handler();
            mHandler.postDelayed(mRunnable, TIMER_LOOP_TIME);
        }


        if (sharedPref.getBoolean(getResources().getString(R.string.saved_is_first_time_key), true)) {
            stopTimer();
            DialogFragment newDialog = MyDialogOpenFragment.newInstance(MyDialogOpenFragment.MODE_NEW);
            newDialog.show(getSupportFragmentManager(), "open_dialog_tag");
        }
        else {
            if(sharedPref.getBoolean(getResources().getString(R.string.saved_is_dead_key), false)) Die();
        }

    }

    public void stopTimer() {
        Log.d(TAG, "stopTimer");
        if (mHandler != null) {
            mHandler.removeCallbacks(mRunnable);
            mHandler = null;
        }
    }

    public void startTimer() {
        Log.d(TAG, "Timer has started!");
        if (mHandler == null) {
            mHandler = new Handler();
        }
        mHandler.postDelayed(mRunnable, TIMER_LOOP_TIME);
    }

    private void setupTabIcons() {
        mTabLayout.getTabAt(0).setIcon(tabIcons[0]);
        mTabLayout.getTabAt(1).setIcon(tabIcons[1]);
        mTabLayout.getTabAt(2).setIcon(tabIcons[2]);
        mTabLayout.getTabAt(3).setIcon(tabIcons[3]);
        mTabLayout.getTabAt(4).setIcon(tabIcons[4]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_pause:
                item.setIcon(mPauseDrawable);
                mPauseDrawable.start();
                mHandler.removeCallbacks(mRunnable);
                Dialogs dialogs = new Dialogs(mContext);
                dialogs.showResumeDialog(this, item, mRunnable);
                return true;
            case R.id.menu_item_setings:
                mHandler = null;
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(mRunnable);
    }

    @Override
    protected void onStart() {
        super.onStart();
        isMainActvityActive = true;
        if (mHandler == null) {
            mHandler = new Handler();
            mHandler.postDelayed(mRunnable, TIMER_LOOP_TIME);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        isMainActvityActive = false;
    }

    public void onResumeDialogClicked(MenuItem item, Runnable runnable) {
        item.setIcon(mPlayDrawable);
        mPlayDrawable.start();
        mHandler.postDelayed(runnable, TIMER_LOOP_TIME);
    }


    private void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd(getString(R.string.TEST_SAMPLE_ADMOB_REWARDED),
                new AdRequest.Builder().build());
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        hasAdd = true;
        Toast.makeText(this, getResources().getString(R.string.ad_avaible), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdOpened() {
        hasAdd = false;
        stopTimer();
    }

    @Override
    public void onRewardedVideoStarted() {}

    @Override
    public void onRewardedVideoAdClosed() {
        startTimer();
        loadRewardedVideoAd();
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        if("money".equals(rewardItem.getType()))
        {
            Toast.makeText(this, getResources().getString(R.string.on_rewarded_money) + " " + rewardItem.getType() + "  " + getResources().getString(R.string.amount) + " " +
                    rewardItem.getAmount(), Toast.LENGTH_LONG).show();
            int currentMoney = userSharedPref.getInt(getString(R.string.saved_character_money_key), 0) + rewardItem.getAmount();
            userSharedPref.edit().putInt(getString(R.string.saved_character_money_key), currentMoney).apply();
        }
        else if("life".equals(rewardItem.getType()))
        {
            Toast.makeText(this, getResources().getString(R.string.on_rewarded_life), Toast.LENGTH_LONG).show();
            SharedPreferences.Editor editor = userSharedPref.edit();
            editor.putBoolean(getResources().getString(R.string.saved_is_dead_key), false);
            editor.putInt(getResources().getString(R.string.saved_health_key), 100);
            editor.putInt(getResources().getString(R.string.saved_hungry_key), 250);
            editor.putInt(getResources().getString(R.string.saved_energy_key), 250);
            editor.putInt(getResources().getString(R.string.saved_happiness_key), 250);
            editor.apply();
        }
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {}

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {}

    @Override
    public void onRewardedVideoCompleted() {}

    public void Die() {

        SharedPreferences.Editor editor = userSharedPref.edit();
        editor.putBoolean(getString(R.string.saved_is_dead_key), true);
        editor.apply();
        //for testing purposes
        hasAdd = false;
        if (hasAdd)
            (new Dialogs(mContext)).showDialogWithChoose(userSharedPref, mContext, getString(R.string.died), "Do you want to be rescued by watching an ad?", 7);
        else {
            stopTimer();
            DialogFragment deadDialog = MyDialogDead.newInstance();
            deadDialog.show(getSupportFragmentManager(), "open_dead_dialog_tag");
        }
    }

    @Override
    public void onNewUserAdd() {
        startTimer();

        currentUserNumber = sharedPref.getInt(getString(R.string.saved_current_user), 0);
        Log.d(TAG, "current user:" + currentUserNumber);
        userSharedPref = getSharedPreferences(getString(R.string.shared_preferences_user_key) + currentUserNumber, MODE_PRIVATE);

        MainFragment.mUserSharedPref = userSharedPref;

        mPagerAdapter = new CustomPagerAdapter(getSupportFragmentManager(), this);
        mPager.setAdapter(mPagerAdapter);

        Intent intent = getIntent();
        if (intent.hasExtra(INTENT_PAGE)) {
            mPager.setCurrentItem(intent.getIntExtra(INTENT_PAGE, 0));
        }

        mTabLayout = findViewById(R.id.tablayout);
        mTabLayout.setupWithViewPager(mPager);
        setupTabIcons();
    }

    @Override
    public void onDialogDeadInteraction() {
        DialogFragment newDialog = MyDialogOpenFragment.newInstance(MyDialogOpenFragment.MODE_RESET);
        newDialog.show(getSupportFragmentManager(), "open_user_dialog_tag");
    }

    @Override
    public void onDialogInteractionTimerStop() {
        stopTimer();
    }

    @Override
    public void onDialogInteractionTimerStart() {
        startTimer();
    }

    @Override
    public void onDialogInteractionDie() {
        Die();
    }

    @Override
    public void onDialogResume(MenuItem item, Runnable runnable) {
        onResumeDialogClicked(item, runnable);
    }
}