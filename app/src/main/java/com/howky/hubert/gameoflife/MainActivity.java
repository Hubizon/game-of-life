package com.howky.hubert.gameoflife;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.github.florent37.tutoshowcase.TutoShowcase;
import com.howky.hubert.gameoflife.education.EduFragment;
import com.howky.hubert.gameoflife.girlboyfriend.GirlboyfriendFragment;
import com.howky.hubert.gameoflife.shop.BuyActivity;
import com.howky.hubert.gameoflife.shop.ShopFragment;
import com.howky.hubert.gameoflife.utils.Dialogs;
import com.howky.hubert.gameoflife.firstOpen.MyDialogOpenFragment;
import com.howky.hubert.gameoflife.utils.MainTimer;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.howky.hubert.gameoflife.utils.SharedPreferencesDefaultValues;

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
            askToStartTutorial();
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

    void askToStartTutorial()
    {
        AlertDialog.Builder dialog;

        SharedPreferences settingsSharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isDark = settingsSharedPref.getBoolean(SettingsActivity.DARK_SWITCH_KEY, false);
        if (isDark) {
            dialog = new AlertDialog.Builder(this, R.style.Theme_MaterialComponents_Dialog_Alert);
        } else {
            dialog = new AlertDialog.Builder(this, R.style.Theme_MaterialComponents_Light_Dialog_Alert);
        }

        dialog.setTitle("Do you want to see tutorial?")
                .setMessage("Hi! I see you are first time here! Do you want to see tutorial before starting playing?")
                .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {

                        dialoginterface.cancel();
                    }})
                .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        startTutorialOne();
                        dialoginterface.cancel();
                    }
                }).show();
    }

    void startTutorialOne()
    {
        MainTimer.shouldWork = false;
        mainTimer.stopTimer();

        TapTargetView.showFor(this,
                TapTarget.forView(findViewById(R.id.spaceTutorialOne), "Top panel", "Here you can see information about yourself." +
                        "Your avatar, nickname, money, etc.")
                        // All options below are optional
                        .outerCircleColor(R.color.black)
                        .outerCircleAlpha(0.8f)
                        .targetCircleColor(R.color.white)
                        .titleTextSize(25)
                        .titleTextColor(R.color.white)
                        .descriptionTextSize(15)
                        .descriptionTextColor(R.color.white)
                        // .textColor(R.color.blue)
                        .textTypeface(Typeface.SANS_SERIF)
                        .dimColor(R.color.black)
                        .drawShadow(true)
                        .cancelable(false)
                        .tintTarget(true)
                        .transparentTarget(true)
                        //      .icon(Drawable)
                        .targetRadius(120),
                new TapTargetView.Listener() {
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);
                        startTutorialTwo();
                    }
                });
    }

    void startTutorialTwo()
    {
        TapTargetView.showFor(this,
                TapTarget.forView(findViewById(R.id.spaceTutorialTwo), "Center panel", "This's the most important thing in the whole game. " +
                        "Here you can see 4 Progress Bars showing your actual level of: health, hungry, exhaust and happiness." +
                        " But watch out! These bars are getting lower every second!")
                        // All options below are optional
                        .outerCircleColor(R.color.black)
                        .outerCircleAlpha(0.8f)
                        .targetCircleColor(R.color.white)
                        .titleTextSize(25)
                        .titleTextColor(R.color.white)
                        .descriptionTextSize(15)
                        .descriptionTextColor(R.color.white)
                        // .textColor(R.color.blue)
                        .textTypeface(Typeface.SANS_SERIF)
                        .dimColor(R.color.black)
                        .drawShadow(true)
                        .cancelable(false)
                        .tintTarget(true)
                        .transparentTarget(true)
                        //      .icon(Drawable)
                        .targetRadius(130),
                new TapTargetView.Listener() {
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);

                        startTutorialThree();
                    }
                });
    }

    void startTutorialThree()
    {
        TapTargetView.showFor(this,
                TapTarget.forView(findViewById(R.id.spaceTutorialThree), "Bottom panel", "It's just showing where do you live, your education, " +
                        "transport and potential partner")
                        // All options below are optional
                        .outerCircleColor(R.color.black)
                        .outerCircleAlpha(0.8f)
                        .targetCircleColor(R.color.white)
                        .titleTextSize(25)
                        .titleTextColor(R.color.white)
                        .descriptionTextSize(15)
                        .descriptionTextColor(R.color.white)
                        // .textColor(R.color.blue)
                        .textTypeface(Typeface.SANS_SERIF)
                        .dimColor(R.color.black)
                        .drawShadow(true)
                        .cancelable(false)
                        .tintTarget(true)
                        .transparentTarget(true)
                        //      .icon(Drawable)
                        .targetRadius(140),
                new TapTargetView.Listener() {
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);

                        startTutorialFour();
                    }
                });
    }

    void startTutorialFour()
    {
        TapTargetView.showFor(this,
                TapTarget.forView(findViewById(R.id.spaceTutorialFour), "Education/Work", "Click now here, to move to the next tab")
                        // All options below are optional
                        .outerCircleColor(R.color.black)
                        .outerCircleAlpha(0.8f)
                        .targetCircleColor(R.color.white)
                        .titleTextSize(25)
                        .titleTextColor(R.color.white)
                        .descriptionTextSize(15)
                        .descriptionTextColor(R.color.white)
                        // .textColor(R.color.blue)
                        .textTypeface(Typeface.SANS_SERIF)
                        .dimColor(R.color.black)
                        .drawShadow(true)
                        .cancelable(false)
                        .tintTarget(true)
                        .transparentTarget(true)
                        //      .icon(Drawable)
                        .targetRadius(80),
                new TapTargetView.Listener() {
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);

                        mPager.setCurrentItem(1);
                        startTutorialFive();
                    }
                });
    }

    void startTutorialFive()
    {
        TapTargetView.showFor(this,
                TapTarget.forView(findViewById(R.id.spaceTutorialFive), "School", "Here, you can go to school (to have higher salary in work), " +
                        "or learn some new skills")
                        // All options below are optional
                        .outerCircleColor(R.color.black)
                        .outerCircleAlpha(0.8f)
                        .targetCircleColor(R.color.white)
                        .titleTextSize(25)
                        .titleTextColor(R.color.white)
                        .descriptionTextSize(15)
                        .descriptionTextColor(R.color.white)
                        // .textColor(R.color.blue)
                        .textTypeface(Typeface.SANS_SERIF)
                        .dimColor(R.color.black)
                        .drawShadow(true)
                        .cancelable(false)
                        .tintTarget(true)
                        .transparentTarget(true)
                        //      .icon(Drawable)
                        .targetRadius(120),
                new TapTargetView.Listener() {
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);

                        mPager.setCurrentItem(1);
                        startTutorialSix();
                    }
                });
    }

    void startTutorialSix()
    {
        TapTargetView.showFor(this,
                TapTarget.forView(findViewById(R.id.spaceTutorialSix), "Work", "You can find a job here and then go to work")
                        // All options below are optional
                        .outerCircleColor(R.color.black)
                        .outerCircleAlpha(0.8f)
                        .targetCircleColor(R.color.white)
                        .titleTextSize(25)
                        .titleTextColor(R.color.white)
                        .descriptionTextSize(15)
                        .descriptionTextColor(R.color.white)
                        // .textColor(R.color.blue)
                        .textTypeface(Typeface.SANS_SERIF)
                        .dimColor(R.color.black)
                        .drawShadow(true)
                        .cancelable(false)
                        .tintTarget(true)
                        .transparentTarget(true)
                        //      .icon(Drawable)
                        .targetRadius(120),
                new TapTargetView.Listener() {
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);

                        mPager.setCurrentItem(1);
                        startTutorialSeven();
                    }
                });
    }

    void startTutorialSeven()
    {
        TapTargetView.showFor(this,
                TapTarget.forView(findViewById(R.id.spaceTutorialSeven), "Criminal", "If you want to do something not really legal, you can always" +
                        " check here. There're few options: 'Get new friends' (to make more money from criminal jobs), 'Steal stuff', 'sell drugs', and 'threat teachers." +
                        " But try not to get caught!")
                        // All options below are optional
                        .outerCircleColor(R.color.black)
                        .outerCircleAlpha(0.8f)
                        .targetCircleColor(R.color.white)
                        .titleTextSize(25)
                        .titleTextColor(R.color.white)
                        .descriptionTextSize(15)
                        .descriptionTextColor(R.color.white)
                        // .textColor(R.color.blue)
                        .textTypeface(Typeface.SANS_SERIF)
                        .dimColor(R.color.black)
                        .drawShadow(true)
                        .cancelable(false)
                        .tintTarget(true)
                        .transparentTarget(true)
                        //      .icon(Drawable)
                        .targetRadius(120),
                new TapTargetView.Listener() {
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);

                        startTutorialEight();
                    }
                });
    }

    void startTutorialEight()
    {
        TapTargetView.showFor(this,
                TapTarget.forView(findViewById(R.id.spaceTutorialEight), "Shop", "You can buy many things here! I think that's pretty easy " +
                        "to understand, but let's have a look at this")
                        // All options below are optional
                        .outerCircleColor(R.color.black)
                        .outerCircleAlpha(0.8f)
                        .targetCircleColor(R.color.white)
                        .titleTextSize(25)
                        .titleTextColor(R.color.white)
                        .descriptionTextSize(15)
                        .descriptionTextColor(R.color.white)
                        // .textColor(R.color.blue)
                        .textTypeface(Typeface.SANS_SERIF)
                        .dimColor(R.color.black)
                        .drawShadow(true)
                        .cancelable(false)
                        .tintTarget(true)
                        .transparentTarget(true)
                        //      .icon(Drawable)
                        .targetRadius(80),
                new TapTargetView.Listener() {
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);

                        mPager.setCurrentItem(2);
                        startTutorialNine();
                    }
                });
    }

    void startTutorialNine()
    {
        TapTargetView.showFor(this,
                TapTarget.forView(findViewById(R.id.spaceTutorialNine), "Love", "Ok, so now let's go to the next tab")
                        // All options below are optional
                        .outerCircleColor(R.color.black)
                        .outerCircleAlpha(0.8f)
                        .targetCircleColor(R.color.white)
                        .titleTextSize(25)
                        .titleTextColor(R.color.white)
                        .descriptionTextSize(15)
                        .descriptionTextColor(R.color.white)
                        // .textColor(R.color.blue)
                        .textTypeface(Typeface.SANS_SERIF)
                        .dimColor(R.color.black)
                        .drawShadow(true)
                        .cancelable(false)
                        .tintTarget(true)
                        .transparentTarget(true)
                        //      .icon(Drawable)
                        .targetRadius(80),
                new TapTargetView.Listener() {
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);

                        mPager.setCurrentItem(3);
                        startTutorialTen();
                    }
                });
    }

    void startTutorialTen()
    {
        TapTargetView.showFor(this,
                TapTarget.forView(findViewById(R.id.button_love_SearchLove), "Find a partner", "As the name suggests, you can find a partner here." +
                        " Then you will be able to 'buy a gift' or 'meet' to enlarge your relationship. But remember, if you will forget about your partner, you might" +
                        " stay with nothing and I suppose that's not the best feel!")
                        // All options below are optional
                        .outerCircleColor(R.color.black)
                        .outerCircleAlpha(0.8f)
                        .targetCircleColor(R.color.white)
                        .titleTextSize(25)
                        .titleTextColor(R.color.white)
                        .descriptionTextSize(15)
                        .descriptionTextColor(R.color.white)
                        // .textColor(R.color.blue)
                        .textTypeface(Typeface.SANS_SERIF)
                        .dimColor(R.color.black)
                        .drawShadow(true)
                        .cancelable(false)
                        .tintTarget(true)
                        .transparentTarget(true)
                        //      .icon(Drawable)
                        .targetRadius(130),
                new TapTargetView.Listener() {
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);

                        startTutorialEleven();
                    }
                });
    }

    void startTutorialEleven()
    {
        TapTargetView.showFor(this,
                TapTarget.forView(findViewById(R.id.spaceTutorialEleven), "Home", "Let's go home tab now!")
                        // All options below are optional
                        .outerCircleColor(R.color.black)
                        .outerCircleAlpha(0.8f)
                        .targetCircleColor(R.color.white)
                        .titleTextSize(25)
                        .titleTextColor(R.color.white)
                        .descriptionTextSize(15)
                        .descriptionTextColor(R.color.white)
                        // .textColor(R.color.blue)
                        .textTypeface(Typeface.SANS_SERIF)
                        .dimColor(R.color.black)
                        .drawShadow(true)
                        .cancelable(false)
                        .tintTarget(true)
                        .transparentTarget(true)
                        //      .icon(Drawable)
                        .targetRadius(80),
                new TapTargetView.Listener() {
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);

                        mPager.setCurrentItem(4);
                        startTutorialTwelve();
                    }
                });
    }

    void startTutorialTwelve()
    {
        TapTargetView.showFor(this,
                TapTarget.forView(findViewById(R.id.computer_img), "Computer", "You need phone/computer to enter that tab, but when you do that you'll" +
                        " be able to do many interesting things! Like e.g. 'Make a game'. That will take a little time, but when you'll finish doing that,  you'll get some" +
                        " money every week")
                        // All options below are optional
                        .outerCircleColor(R.color.black)
                        .outerCircleAlpha(0.8f)
                        .targetCircleColor(R.color.white)
                        .titleTextSize(25)
                        .titleTextColor(R.color.white)
                        .descriptionTextSize(15)
                        .descriptionTextColor(R.color.white)
                        // .textColor(R.color.blue)
                        .textTypeface(Typeface.SANS_SERIF)
                        .dimColor(R.color.black)
                        .drawShadow(true)
                        .cancelable(false)
                        .tintTarget(true)
                        .transparentTarget(true)
                        //      .icon(Drawable)
                        .targetRadius(120),
                new TapTargetView.Listener() {
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);

                        startTutorialThirteen();
                    }
                });
    }

    void startTutorialThirteen()
    {
        TapTargetView.showFor(this,
                TapTarget.forView(findViewById(R.id.safe_img), "Safe", "If you will choose a criminal path, safe will be extremely useful. With it," +
                        " when you will get caught, probably police won't find that hidden safe! ")
                        // All options below are optional
                        .outerCircleColor(R.color.black)
                        .outerCircleAlpha(0.8f)
                        .targetCircleColor(R.color.white)
                        .titleTextSize(25)
                        .titleTextColor(R.color.white)
                        .descriptionTextSize(15)
                        .descriptionTextColor(R.color.white)
                        // .textColor(R.color.blue)
                        .textTypeface(Typeface.SANS_SERIF)
                        .dimColor(R.color.black)
                        .drawShadow(true)
                        .cancelable(false)
                        .tintTarget(true)
                        .transparentTarget(true)
                        //      .icon(Drawable)
                        .targetRadius(120),
                new TapTargetView.Listener() {
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);

                        startTutorialFourteen();
                    }
                });
    }

    void startTutorialFourteen()
    {
        TapTargetView.showFor(this,
                TapTarget.forView(findViewById(R.id.ad_img), "Ad", "You can also watch ad to earn some money!")
                        // All options below are optional
                        .outerCircleColor(R.color.black)
                        .outerCircleAlpha(0.8f)
                        .targetCircleColor(R.color.white)
                        .titleTextSize(25)
                        .titleTextColor(R.color.white)
                        .descriptionTextSize(15)
                        .descriptionTextColor(R.color.white)
                        // .textColor(R.color.blue)
                        .textTypeface(Typeface.SANS_SERIF)
                        .dimColor(R.color.black)
                        .drawShadow(true)
                        .cancelable(false)
                        .tintTarget(true)
                        .transparentTarget(true)
                        //      .icon(Drawable)
                        .targetRadius(120),
                new TapTargetView.Listener() {
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);

                        startTutorialFifteen();
                    }
                });
    }

    void startTutorialFifteen()
    {
        TapTargetView.showFor(this,
                TapTarget.forView(findViewById(R.id.spaceTutorialFifteen), "Main", "So, I think, that's it! Go to main tab now, and start playing! " +
                        " Good Luck!")
                        // All options below are optional
                        .outerCircleColor(R.color.black)
                        .outerCircleAlpha(0.8f)
                        .targetCircleColor(R.color.white)
                        .titleTextSize(25)
                        .titleTextColor(R.color.white)
                        .descriptionTextSize(15)
                        .descriptionTextColor(R.color.white)
                        // .textColor(R.color.blue)
                        .textTypeface(Typeface.SANS_SERIF)
                        .dimColor(R.color.black)
                        .drawShadow(true)
                        .cancelable(false)
                        .tintTarget(true)
                        .transparentTarget(true)
                        //      .icon(Drawable)
                        .targetRadius(80),
                new TapTargetView.Listener() {
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);

                        mPager.setCurrentItem(0);
                        mPlayDrawable.start();
                        mainTimer.startTimer();
                    }
                });
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
        if (!mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.loadAd(getString(R.string.MONEYBOOSTER_ADMOB_ID),
                    new AdRequest.Builder().build());
        }
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