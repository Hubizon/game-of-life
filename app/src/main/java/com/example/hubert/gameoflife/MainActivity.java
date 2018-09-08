package com.example.hubert.gameoflife;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hubert.gameoflife.Education.Subject;
import com.example.hubert.gameoflife.Girlboyfriend.Children;
import com.example.hubert.gameoflife.Girlboyfriend.Girlboyfriend;

import com.example.hubert.gameoflife.Shop.ShopFragment;
import com.example.hubert.gameoflife.Utils.Fun;
import com.example.hubert.gameoflife.Utils.Lodging;
import com.example.hubert.gameoflife.Utils.Lottery;
import com.example.hubert.gameoflife.Utils.Transport;
import com.example.hubert.gameoflife.Work.Job;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity{

    /*public static String Name = "";
    public static int Icon = R.drawable.avatar_icon1;
    public static int Money = 750;

    public static int AgeYears = 7;
    public static int AgeDays = 0;

    public static int DateYears = 2000;
    public static int DateMonths = 1;
    public static int DateDays = 1;
    public static long TimeHours = 12;

    public static Lodging MyLodging = new Lodging("Parents House", 0, 10, 5, 125, 5);
    public static Job MyJob = null;

    public static boolean IsInSchoolNow = true;
    public static Transport MyTransport = new Transport("Foots", 0, 10);
    public static Girlboyfriend MyGirlboyfriend = null;
    public static Children MyChildren = null;

//    public static boolean IsInSchoolNow = true;

    public static int Health = 750;
    public static int Hungry = 750;
    public static int Energy = 750;
    public static int Happiness = 750;

    public static Fun MyComputer = null;
    public static Fun MyTv = null;
    public static Fun MyPhone = null;
    public static ArrayList<Lottery> OwnedLotteries = new ArrayList<>();*/

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

        /*SharedPreferences sharedPref = getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        Gson gson = new Gson();


        editor.putString(getString(R.string.saved_character_name_key), "");
        editor.putInt(getString(R.string.saved_character_icon_key), R.drawable.avatar_icon1);
        editor.putInt(getString(R.string.saved_character_money_key), 10000);
        editor.putInt(getString(R.string.saved_date_years_key), 2000);
        editor.putInt(getString(R.string.saved_date_months_key), 1);
        editor.putInt(getString(R.string.saved_date_days_key), 1);
        editor.putInt(getString(R.string.saved_time_hours_key), 12);

        String json = gson.toJson(new Lodging("Parents House", 0, 10, 5, 125, 5));
        editor.putString(getString(R.string.saved_my_lodging_key), json);
        json = gson.toJson(null);
        editor.putString(getString(R.string.saved_my_job_key), json);
        json = gson.toJson(new Transport("Foots", 0, 10));
        editor.putString(getString(R.string.saved_my_transport_key), json);
        json = gson.toJson(null);
        editor.putString(getString(R.string.saved_my_girlboyfriend_key), json);
        //json = gson.toJson(null);
        editor.putString(getString(R.string.saved_my_children_key), json);

        editor.putBoolean(getString(R.string.saved_is_in_school_now_key), true);
        editor.putInt(getString(R.string.saved_hungry_key), 750);
        editor.putInt(getString(R.string.saved_health_key), 750);
        editor.putInt(getString(R.string.saved_energy_key), 750);
        editor.putInt(getString(R.string.saved_happiness_key), 750);

        json = gson.toJson(null);
        editor.putString(getString(R.string.saved_my_computer_key), json);
        //json = gson.toJson(null);
        editor.putString(getString(R.string.saved_my_tv_key), json);
        //json = gson.toJson(null);
        editor.putString(getString(R.string.saved_my_phone_key), json);
        json = gson.toJson(new ArrayList<>());
        editor.putString(getString(R.string.saved_owned_lotteries_key), json);
        json = gson.toJson(subjectsList);
        editor.putString(getString(R.string.saved_subjects_list_key), json);

        editor.commit();*/

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

            editor.putInt(getResources().getString(R.string.saved_hungry_key), ((sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), 750)) - 5));
            if(sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), 750) <= 0)
                editor.putInt(getResources().getString(R.string.saved_health_key), ((sharedPref.getInt(getResources().getString(R.string.saved_health_key), 750)) - 25));

            editor.putInt(getResources().getString(R.string.saved_energy_key), ((sharedPref.getInt(getResources().getString(R.string.saved_energy_key), 750)) - 5));
            if(sharedPref.getInt(getResources().getString(R.string.saved_energy_key), 750) <= 0)
                editor.putInt(getResources().getString(R.string.saved_health_key), ((sharedPref.getInt(getResources().getString(R.string.saved_health_key), 750)) - 25));

            editor.putInt(getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), 750)) - 5));
            if(sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), 750) <= 0)
                editor.putInt(getResources().getString(R.string.saved_health_key), ((sharedPref.getInt(getResources().getString(R.string.saved_health_key), 750)) - 25));


            editor.putInt(getResources().getString(R.string.saved_time_hours_key), ((sharedPref.getInt(getResources().getString(R.string.saved_time_hours_key), 12)) + 1));
            if (sharedPref.getInt(getResources().getString(R.string.saved_time_hours_key), 12) >= 23) {
                editor.putInt(getResources().getString(R.string.saved_time_hours_key), 0);
                editor.putInt(getResources().getString(R.string.saved_age_years_key), ((sharedPref.getInt(getResources().getString(R.string.saved_age_years_key), 8)) + 1));
                editor.putInt(getResources().getString(R.string.saved_date_days_key), ((sharedPref.getInt(getResources().getString(R.string.saved_age_years_key), 8)) + 1));

                for (int i = 0; i <= (subjectsList.length - 1); i++)
                    subjectsList[i].IsTodaysHomeworkDone = false;

                if (sharedPref.getInt(getResources().getString(R.string.saved_date_days_key), 12) >= 31) {
                    editor.putInt(getResources().getString(R.string.saved_date_days_key), 0);
                    if (sharedPref.getInt(getResources().getString(R.string.saved_date_months_key), 12) >= 12) {
                        editor.putInt(getResources().getString(R.string.saved_date_years_key), ((sharedPref.getInt(getResources().getString(R.string.saved_date_years_key), 2000)) + 1));
                        editor.putInt(getResources().getString(R.string.saved_age_years_key), ((sharedPref.getInt(getResources().getString(R.string.saved_date_years_key), 8)) + 1));
                        editor.putInt(getResources().getString(R.string.saved_age_days_key), ((sharedPref.getInt(getResources().getString(R.string.saved_age_days_key), 1)) + 1));
                        editor.putInt(getResources().getString(R.string.saved_date_months_key), 0);
                    } else
                        editor.putInt(getResources().getString(R.string.saved_date_months_key), ((sharedPref.getInt(getResources().getString(R.string.saved_date_months_key), 1)) + 1));
                } else
                {
                    editor.putInt(getResources().getString(R.string.saved_date_days_key), ((sharedPref.getInt(getResources().getString(R.string.saved_date_days_key), 1)) + 1));
                    editor.putInt(getResources().getString(R.string.saved_age_days_key), ((sharedPref.getInt(getResources().getString(R.string.saved_age_days_key), 1)) + 1));
                }
            } else
                editor.putInt(getResources().getString(R.string.saved_time_hours_key), ((sharedPref.getInt(getResources().getString(R.string.saved_time_hours_key), 12)) + 1));

            if (sharedPref.getInt(getResources().getString(R.string.saved_saved_age_years_key), 12) <= 18) {
                for (int i = 0; i < MainActivity.subjectsList.length; i++) {
                    MainActivity.subjectsList[i].decreaseToAnotherMark(1);
                }
            }
            editor.apply();
        }
    }

//    @Override
//    public void onBackPressed() {
//        if (mPager.getCurrentItem() == 0) {
//            // If the user is currently looking at the first step, allow the system to handle the
//            // Back button. This calls finish() on this activity and pops the back stack.
//            super.onBackPressed();
//        } else {
//            // Otherwise, select the previous step.
//            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
//        }
//    }


//    private void alertView(String title, String message) {
//        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
//        dialog.setTitle(title)
//                //.setIcon(R.drawable.ic_launcher)
//                .setMessage(message)
//                .setNegativeButton("Nah", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialoginterface, int i) {
//                        dialoginterface.cancel();
//                    }})
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialoginterface, int i) {
//                        Money -= 50;
//                        CommunicationSkills += 15;
//                    }
//                }).show();
//    }

    /*(public void onBuyOrWorkClick(View view) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.pager, new BuyActivity()); !!!
        ft.commit();
    }

    public void onThingsClick(View view) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.pager, new DoingSomethingFragment());
        ft.commit();
    }*/
}