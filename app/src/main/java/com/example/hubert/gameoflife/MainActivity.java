package com.example.hubert.gameoflife;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hubert.gameoflife.Education.Subject;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity{


    public static Subject[] subjectsList = new Subject[] { new Subject("Mathematics", 4),
    new Subject("English", 4),
    new Subject("Languages", 4),
    new Subject("History", 4),
    new Subject("Chemistry", 4),
    new Subject("Psychic", 4),
    new Subject("Biology", 4),
    new Subject("Information Technology", 4),
    new Subject("Art", 4),
    new Subject("Music", 4),
    new Subject("Psychical Education", 4),
    new Subject("Behavior", 4), };

    public static int CommunicationSkills = 100;

    private ViewPager mPager;
    private CustomPagerAdapter mPagerAdapter;
    private TabLayout mTabLayout;
    private int[] tabIcons = {
            R.drawable.profile_icon,
            R.drawable.education_icon,
            R.drawable.shop_icon,
            R.drawable.girlboyfriend_icon,
            R.drawable.house_icon
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPager = findViewById(R.id.pager);
        mPagerAdapter = new CustomPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        mTabLayout = findViewById(R.id.tablayout);
        mTabLayout.setupWithViewPager(mPager);
        setupTabIcons();

        Timer timer = new Timer();
        TimerTask updateValues = new UpdateValues();
        //  TODO: Ten timer ma dzialac w calej apce, nie tylko w tym Activity
        timer.scheduleAtFixedRate(updateValues, 0, 1500);
    }

    private void setupTabIcons() {
        mTabLayout.getTabAt(0).setIcon(tabIcons[0]);
        mTabLayout.getTabAt(1).setIcon(tabIcons[1]);
        mTabLayout.getTabAt(2).setIcon(tabIcons[2]);
        mTabLayout.getTabAt(3).setIcon(tabIcons[3]);
        mTabLayout.getTabAt(4).setIcon(tabIcons[4]);
    }


    class UpdateValues extends TimerTask {
        public void run() {
            SharedPreferences sharedPref = getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();

            if(!(sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry) > 0))
                editor.putInt(getResources().getString(R.string.saved_hungry_key), ((sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry)) - 5));
            if(sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry) <= 0)
                editor.putInt(getResources().getString(R.string.saved_health_key), ((sharedPref.getInt(getResources().getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth)) - 25));

            if(!(sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy) > 0))
                editor.putInt(getResources().getString(R.string.saved_energy_key), ((sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy)) - 5));
            if(sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy) <= 0)
                editor.putInt(getResources().getString(R.string.saved_health_key), ((sharedPref.getInt(getResources().getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth)) - 25));

            if(!(sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness) > 0))
                editor.putInt(getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)) - 5));
            if(sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness) <= 0)
                editor.putInt(getResources().getString(R.string.saved_health_key), ((sharedPref.getInt(getResources().getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth)) - 25));


            editor.putInt(getResources().getString(R.string.saved_time_hours_key), ((sharedPref.getInt(getResources().getString(R.string.saved_time_hours_key), SharedPreferencesDefaultValues.DefaultTimeHours)) + 1));
            if (sharedPref.getInt(getResources().getString(R.string.saved_time_hours_key), SharedPreferencesDefaultValues.DefaultTimeHours) >= 23) {
                editor.putInt(getResources().getString(R.string.saved_time_hours_key), 0);
                editor.putInt(getResources().getString(R.string.saved_age_days_key), ((sharedPref.getInt(getResources().getString(R.string.saved_age_days_key), SharedPreferencesDefaultValues.DefaultAgeDays)) + 1));
                editor.putInt(getResources().getString(R.string.saved_date_days_key), ((sharedPref.getInt(getResources().getString(R.string.saved_date_days_key), SharedPreferencesDefaultValues.DefaultDateDays)) + 1));

                for (int i = 0; i <= (subjectsList.length - 1); i++)
                    subjectsList[i].IsTodaysHomeworkDone = false;

                if (sharedPref.getInt(getResources().getString(R.string.saved_date_days_key), SharedPreferencesDefaultValues.DefaultDateDays) >= 31) {
                    editor.putInt(getResources().getString(R.string.saved_date_days_key), 0);
                    if (sharedPref.getInt(getResources().getString(R.string.saved_date_months_key), SharedPreferencesDefaultValues.DefaultDateMonths) >= 12) {
                        editor.putInt(getResources().getString(R.string.saved_date_years_key), ((sharedPref.getInt(getResources().getString(R.string.saved_date_years_key), SharedPreferencesDefaultValues.DefaultDateYears)) + 1));
                        editor.putInt(getResources().getString(R.string.saved_age_years_key), ((sharedPref.getInt(getResources().getString(R.string.saved_date_years_key), SharedPreferencesDefaultValues.DefaultDateYears)) + 1));
                        editor.putInt(getResources().getString(R.string.saved_age_days_key), ((sharedPref.getInt(getResources().getString(R.string.saved_age_days_key), SharedPreferencesDefaultValues.DefaultAgeDays)) + 1));
                        editor.putInt(getResources().getString(R.string.saved_date_months_key), 0);
                    } else
                        editor.putInt(getResources().getString(R.string.saved_date_months_key), ((sharedPref.getInt(getResources().getString(R.string.saved_date_months_key), SharedPreferencesDefaultValues.DefaultDateMonths)) + 1));
                } else
                {
                    editor.putInt(getResources().getString(R.string.saved_date_days_key), ((sharedPref.getInt(getResources().getString(R.string.saved_date_days_key), SharedPreferencesDefaultValues.DefaultDateDays)) + 1));
                    editor.putInt(getResources().getString(R.string.saved_age_days_key), ((sharedPref.getInt(getResources().getString(R.string.saved_age_days_key), SharedPreferencesDefaultValues.DefaultAgeDays)) + 1));
                }
            } else
                editor.putInt(getResources().getString(R.string.saved_time_hours_key), ((sharedPref.getInt(getResources().getString(R.string.saved_time_hours_key), SharedPreferencesDefaultValues.DefaultTimeHours)) + 1));

            if (sharedPref.getInt(getResources().getString(R.string.saved_saved_age_years_key), SharedPreferencesDefaultValues.DefaultAgeYears) <= 18) {
                for (int i = 0; i < MainActivity.subjectsList.length; i++) {
                    MainActivity.subjectsList[i].decreaseToAnotherMark(1);
                }
            }
            editor.apply();
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
}