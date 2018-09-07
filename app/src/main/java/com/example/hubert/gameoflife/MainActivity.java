package com.example.hubert.gameoflife;

import android.content.DialogInterface;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hubert.gameoflife.Education.EducationFragment;
import com.example.hubert.gameoflife.Education.LearnInHomeFragment;
import com.example.hubert.gameoflife.Education.Subject;
import com.example.hubert.gameoflife.Girlboyfriend.Children;
import com.example.hubert.gameoflife.Girlboyfriend.Girlboyfriend;
import com.example.hubert.gameoflife.Girlboyfriend.GirlboyfriendFragment;
import com.example.hubert.gameoflife.House.ComputerFragment;
import com.example.hubert.gameoflife.House.HomeFragment;
import com.example.hubert.gameoflife.Profile.MainFragment;
import com.example.hubert.gameoflife.Shop.ShopBuyFragment;
import com.example.hubert.gameoflife.Shop.ShopFragment;
import com.example.hubert.gameoflife.Utils.Fun;
import com.example.hubert.gameoflife.Utils.Lodging;
import com.example.hubert.gameoflife.Utils.Lottery;
import com.example.hubert.gameoflife.Utils.Transport;
import com.example.hubert.gameoflife.Work.FindJobFragment;
import com.example.hubert.gameoflife.Work.Job;
import com.example.hubert.gameoflife.Work.WorkFragment;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity{


    public static String Name = "";
    public static int Icon = R.drawable.avatar_icon1;
    public static int Money = 750;

    public static int AgeYears = 7;
    public static int AgeDays = 0;

    public static int DateYears = 2000;
    public static int DateMonths = 1;
    public static long DateDays = 1;
    public static int TimeHours = 12;

    public static Lodging MyLodging = new Lodging("Parents House", 0, 10, 5, 125, 5);
    public static Job MyJob = null;
    public static boolean IsInSchoolNow = true;
    public static Transport MyTransport = new Transport("Foots", 0, 10);
    public static Girlboyfriend MyGirlboyfriend = null;
    public static Children MyChildren = null;

    public static int Health = 750;
    public static int Hungry = 750;
    public static int Energy = 750;
    public static int Happiness = 750;

    public static Fun MyComputer = null;
    public static Fun MyTv = null;
    public static Fun MyPhone = null;
    public static ArrayList<Lottery> OwnedLotteries = new ArrayList<>();

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
        TimerTask updateValues = new ChangeProgressBars();
        timer.scheduleAtFixedRate(updateValues, 0, 1500);
    }

    private void setupTabIcons() {
        mTabLayout.getTabAt(0).setIcon(tabIcons[0]);
        mTabLayout.getTabAt(1).setIcon(tabIcons[1]);
        mTabLayout.getTabAt(2).setIcon(tabIcons[2]);
        mTabLayout.getTabAt(3).setIcon(tabIcons[3]);
        mTabLayout.getTabAt(4).setIcon(tabIcons[4]);
    }


    class ChangeProgressBars extends TimerTask {
        public void run() {
            Hungry = Hungry - 5;
            Health--;
            Energy = Energy - 5;
            Happiness = Happiness - 5;

             if(TimeHours >= 23)
             {
                 TimeHours = 0;
                 AgeDays++;

                 for(int i = 0; i <= (subjectsList.length - 1); i++)
                     subjectsList[i].IsTodaysHomeworkDone = false;

                 if(DateDays >= 31)
                 {
                     DateDays = 0;
                     if(DateMonths >= 12)
                     {
                         DateYears++;
                         AgeYears++;
                         DateMonths = 0;
                     }
                     else
                         DateMonths++;
                 }
                 else
                     DateDays++;
             }
             else
                 TimeHours++;

             if(AgeYears <= 18)
             {
                 for(int i = 0; i < MainActivity.subjectsList.length; i++)
                 {
                     MainActivity.subjectsList[i].decreaseToAnotherMark(1);
                 }
             }
        }
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }


    private void alertView(String title, String message) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(title)
                //.setIcon(R.drawable.ic_launcher)
                .setMessage(message)
                .setNegativeButton("Nah", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.cancel();
                    }})
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        Money -= 50;
                        CommunicationSkills += 15;
                    }
                }).show();
    }

    public void onBuyOrWorkClick(View view) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.pager, new ShopBuyFragment());
        ft.commit();
    }

    public void onThingsClick(View view) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.pager, new DoingSomethingFragment());
        ft.commit();
    }
}
