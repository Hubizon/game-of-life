package com.howky.hubert.gameoflife;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


import com.howky.hubert.gameoflife.utils.Dialogs;
import com.howky.hubert.gameoflife.firstOpen.MyDialogOpenFragment;
import com.howky.hubert.gameoflife.utils.MainTimer;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import static com.howky.hubert.gameoflife.MyApplication.currentUserNumber;
import static com.howky.hubert.gameoflife.MyApplication.userSharedPref;
import static com.howky.hubert.gameoflife.MyApplication.mainSharedPref;

public class MainActivity extends AppCompatActivity
    implements RewardedVideoAdListener, MyDialogOpenFragment.OnNewUserAdd,
        MyDialogDead.OnDialogDeadInteractionListener, Dialogs.OnResumeDialogInteractionListener {

    public MainTimer mainTimer;


    public static final String INTENT_PAGE = "intent_page";
    private static AnimatedVectorDrawable mPlayDrawable;
    private static AnimatedVectorDrawable mPauseDrawable;

    public RewardedVideoAd mRewardedVideoAd;
    private ViewPager mPager;
    private CustomPagerAdapter mPagerAdapter;
    private TabLayout mTabLayout;
    private final int[] tabIcons = {
            R.drawable.profile_icon,
            R.drawable.ic_education,
            R.drawable.shop_icon,
            R.drawable.girlboyfriend_icon,
            R.drawable.house_icon
    };

   private Context mContext;


    public static boolean hasAdd = false;

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

        mContext = this;
        mainSharedPref = getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);

        currentUserNumber = mainSharedPref.getInt(getString(R.string.saved_current_user), 0);
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

        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();

        MyApplication.CurrentContext = this;
        final MyApplication globalApplication = (MyApplication) getApplicationContext();
        mainTimer = globalApplication.mainTimer;
        mainTimer.setUserSharedPref(userSharedPref);

        MainTimer.isMainActvityActive = true;
        if (!mainTimer.isRunning && MainTimer.shouldWork) mainTimer.startTimer();


        if (mainSharedPref.getBoolean(getString(R.string.saved_is_first_time_key), true)) {
            MainTimer.shouldWork = false;
            mainTimer.stopTimer();
            DialogFragment newDialog = MyDialogOpenFragment.newInstance(MyDialogOpenFragment.MODE_NEW);
            newDialog.show(getSupportFragmentManager(), "first_time_dialog_tag");
        }
        else {
            if(userSharedPref.getBoolean(getString(R.string.saved_is_dead_key), false)) mainTimer.Die();
        }

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

    static final int START_SETTINGS_REQUEST = 1;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_pause:
                item.setIcon(mPauseDrawable);
                mPauseDrawable.start();
                MainTimer.shouldWork = false;
                mainTimer.stopTimer();
                Dialogs dialogs = new Dialogs(mContext);
                dialogs.showResumeDialog(this, item);
                return true;
            case R.id.menu_item_setings:
                mainTimer.stopTimer();
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivityForResult(intent, START_SETTINGS_REQUEST);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == START_SETTINGS_REQUEST) {
            mainTimer.startTimer();
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
        mainTimer.stopTimer();

        MyApplication.CurrentContext = null;
    }

    @Override
    protected void onStop() {
        super.onStop();
        MainTimer.isMainActvityActive = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPager = null;
        mPagerAdapter = null;
        mTabLayout = null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.CurrentContext = this;

        if (mPagerAdapter == null) {
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
        }
    }


    private void onResumeDialogClicked(MenuItem item) {
        item.setIcon(mPlayDrawable);
        mPlayDrawable.start();
        mainTimer.startTimer();
    }


    private void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd(getString(R.string.MONEYBOOSTER_ADMOB_ID),
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
        mainTimer.stopTimer();
    }

    @Override
    public void onRewardedVideoStarted() {}

    @Override
    public void onRewardedVideoAdClosed() {
        mainTimer.startTimer();
        loadRewardedVideoAd();
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        if (userSharedPref.getBoolean(getString(R.string.saved_is_dead_key), false)) {
            Toast.makeText(this, getResources().getString(R.string.on_rewarded_life), Toast.LENGTH_LONG).show();
            SharedPreferences.Editor editor = userSharedPref.edit();
            editor.putBoolean(getResources().getString(R.string.saved_is_dead_key), false);
            editor.putInt(getResources().getString(R.string.saved_health_key), 100);
            editor.putInt(getResources().getString(R.string.saved_hungry_key), 250);
            editor.putInt(getResources().getString(R.string.saved_energy_key), 250);
            editor.putInt(getResources().getString(R.string.saved_happiness_key), 250);
            editor.apply();
        } else {
            Toast.makeText(this, getResources().getString(R.string.on_rewarded_money) + " " + rewardItem.getType() + "  " + getResources().getString(R.string.amount) + " " +
                    rewardItem.getAmount(), Toast.LENGTH_LONG).show();
            int currentMoney = userSharedPref.getInt(getString(R.string.saved_character_money_key), 0) + rewardItem.getAmount();
            userSharedPref.edit().putInt(getString(R.string.saved_character_money_key), currentMoney).apply();
        }
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {}

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {}

    @Override
    public void onRewardedVideoCompleted() {}



    @Override
    public void onNewUserAdd() {

        currentUserNumber = MyApplication.mainSharedPref.getInt(getString(R.string.saved_current_user), 0);
        userSharedPref = getSharedPreferences(getString(R.string.shared_preferences_user_key) + currentUserNumber, MODE_PRIVATE);

        mPagerAdapter = new CustomPagerAdapter(getSupportFragmentManager(), this);
        mPager.setAdapter(mPagerAdapter);

        Intent intent = getIntent();
        if (intent.hasExtra(INTENT_PAGE)) {
            mPager.setCurrentItem(intent.getIntExtra(INTENT_PAGE, 0));
        }

        mTabLayout = findViewById(R.id.tablayout);
        mTabLayout.setupWithViewPager(mPager);
        setupTabIcons();

        mainTimer.setUserSharedPref(userSharedPref);

        MainTimer.shouldWork = true;
        mainTimer.startTimer();
    }

    @Override
    public void onDialogDeadInteraction() {
        DialogFragment newDialog = MyDialogOpenFragment.newInstance(MyDialogOpenFragment.MODE_RESET);
        newDialog.show(getSupportFragmentManager(), "open_user_dialog_tag");
    }

    @Override
    public void onDialogResume(MenuItem item) {
        onResumeDialogClicked(item);
    }


}