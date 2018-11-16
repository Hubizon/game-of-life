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
import com.example.hubert.gameoflife.Utils.UpdateValues;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public class MainActivity extends AppCompatActivity
    implements RewardedVideoAdListener, MyDialogOpenFragment.OnNewUserAdd {

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

    static Context context;

    public static int currentUserNumber;
    public static SharedPreferences userSharedPref;
    static SharedPreferences sharedPref;
    private static Handler mHandler;
    private static Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            UpdateValues.updateSharedPreferences(context, userSharedPref);
            if (mHandler != null) {
                mHandler.postDelayed(mRunnable, TIMER_LOOP_TIME);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);

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

        context = this;
        sharedPref = getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);

        String user_key = context.getString(R.string.shared_preferences_user_key)
                + currentUserNumber;
        userSharedPref = context.getSharedPreferences(user_key, MODE_PRIVATE);

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

        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();

        if (mHandler == null) {
            Log.d(TAG, "the Handler is null!");
            mHandler = new Handler();
            mHandler.postDelayed(mRunnable, TIMER_LOOP_TIME);
        }


        if (sharedPref.getBoolean(getResources().getString(R.string.saved_is_first_time_key), true))
        {
            sharedPref.edit().putBoolean(getResources().getString(R.string.saved_is_first_time_key), false).apply();
            DialogFragment newDialog = MyDialogOpenFragment.newInstance();
            newDialog.show(getSupportFragmentManager(), "open_dialog_tag");
        }
        else {
            if(sharedPref.getBoolean(getResources().getString(R.string.saved_is_dead_key), false)) Die();
        }
    }

    public static void stopTimer()
    {
        Log.d(TAG, "stopTimer");
        if (mHandler != null) {
            mHandler.removeCallbacks(mRunnable);
            mHandler = null;
        }
    }

    public static void startTimer()
    {
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
                Dialogs.showResumeDialog(this, item, mRunnable);
                return true;
            case R.id.menu_item_setings:
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                //settings();
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


    public static void onResumeDialogClicked(MenuItem item, Runnable runnable) {
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
        Toast.makeText(this, "Ad is available!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdOpened() {
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
        Toast.makeText(this, "onRewarded! currency: " + rewardItem.getType() + "  amount: " +
                rewardItem.getAmount(), Toast.LENGTH_LONG).show();
        SharedPreferences sharedPref = this.getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        int currentMoney = sharedPref.getInt(getString(R.string.saved_character_money_key), 0) + rewardItem.getAmount();
        sharedPref.edit().putInt(getString(R.string.saved_character_money_key), currentMoney).apply();
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {}

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {}

    @Override
    public void onRewardedVideoCompleted() {}

    public static void Die()
    {
        SharedPreferences.Editor editor = userSharedPref.edit();
        editor.putBoolean(context.getResources().getString(R.string.saved_is_dead_key), true);
        Dialogs.showDialogWithChoose(userSharedPref, context, "You just died!", "Do you want to rescue by watching ad?", 7);
        editor.apply();
    }

    @Override
    public void onNewUserAdd() {
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
}