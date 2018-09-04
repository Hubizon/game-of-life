package com.example.hubert.gameoflife;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.lang.UCharacter;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements MainFragment.OnFragmentInteractionListener, EducationFragment.OnFragmentInteractionListener,
                    ShopFragment.OnFragmentInteractionListener, ShopBuyFragment.OnFragmentInteractionListener, GirlboyfriendFragment.OnFragmentInteractionListener,
                    HomeFragment.OnFragmentInteractionListener, ComputerFragment.OnFragmentInteractionListener, FindJobFragment.OnFragmentInteractionListener,
                    WorkFragment.OnFragmentInteractionListener, LearnInHomeFragment.OnFragmentInteractionListener, DoingSomethingFragment.OnFragmentInteractionListener{


    public static String Name = "";
    public static int Icon = R.drawable.avatar_icon1;
    public static int Money = 750;

    public static int AgeYears = 7;
    public static int AgeDays = 0;

    public static int DateYears = 2000;
    public static int DateMonths = 1;
    public static long DateDays = 1;
    public static int TimeHours = 12;

    public static Lodging MyLodging = new Lodging("Parents House", 0, 10, 5, 125, 5);;
    public static String SchoolName = "Primary School";
    public static Job MyJob = null;
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

    public static int CommunicationSkills = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.mainFragmentHolder, new MainFragment());
        ft.commit();

        Timer timer = new Timer();
        TimerTask updateValues = new ChangeProgressBars();
        timer.scheduleAtFixedRate(updateValues, 0, 1500);
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
        }
    }

    public void onProfileWorkClick(View view) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragmentHolder, new MainFragment());
        ft.commit();
    }

    public void onEducationWorkClick(View view) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragmentHolder, new EducationFragment());
        ft.commit();
    }

    public void onShopClick(View view) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragmentHolder, new ShopFragment());
        ft.commit();
    }

    public void onGirlBoyFriendClick(View view) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragmentHolder, new GirlboyfriendFragment());
        ft.commit();
    }

    public void onHomeClick(View view) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragmentHolder, new HomeFragment());
        ft.commit();
    }

    public static String ThingToDo = "food";
    public void onFoodBuyClick(View view) {
        ThingToDo = "foodBuy";
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragmentHolder, new ShopBuyFragment());
        ft.commit();
    }

    public void onMedicinesBuyClick(View view) {
        ThingToDo = "medicinesBuy";
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragmentHolder, new ShopBuyFragment());
        ft.commit();
    }

    public void onFunBuyClick(View view) {
        ThingToDo = "funBuy";
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragmentHolder, new ShopBuyFragment());
        ft.commit();
    }

    public void onLotteryBuyClick(View view) {
        ThingToDo = "lotteryBuy";
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragmentHolder, new ShopBuyFragment());
        ft.commit();
    }

    public void onHouseBuyClick(View view) {
        ThingToDo = "houseBuy";
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragmentHolder, new ShopBuyFragment());
        ft.commit();
    }

    public void onTransportBuyClick(View view) {
        ThingToDo = "transportBuy";
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragmentHolder, new ShopBuyFragment());
        ft.commit();
    }

    public void onFridgeClick(View view) {
        ThingToDo = "foodEat";
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragmentHolder, new ShopBuyFragment());
        ft.commit();
    }

    public void onMedikitClick(View view) {
        ThingToDo = "medicinesEat";
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragmentHolder, new ShopBuyFragment());
        ft.commit();
    }

    public void onOfficeWorkClick(View view) {
        ThingToDo = "officeJob";
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragmentHolder, new ShopBuyFragment());
        ft.commit();
    }

    public void onCriminalWorkClick(View view) {
        ThingToDo = "criminalJob";
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragmentHolder, new ShopBuyFragment());
        ft.commit();
    }

    public void onArtsWorkClick(View view) {
        ThingToDo = "artsJob";
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragmentHolder, new ShopBuyFragment());
        ft.commit();
    }

    public void onMediaWorkClick(View view) {
        ThingToDo = "mediaJob";
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragmentHolder, new ShopBuyFragment());
        ft.commit();
    }

    public void onOutsideWorkClick(View view) {
        ThingToDo = "outsideJob";
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragmentHolder, new ShopBuyFragment());
        ft.commit();
    }

    public void onOtherWorkClick(View view) {
        ThingToDo = "otherJob";
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragmentHolder, new ShopBuyFragment());
        ft.commit();
    }

    public void onComputerClick(View view) {
        if(MainActivity.MyComputer != null || MainActivity.MyPhone != null)
        {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFragmentHolder, new ComputerFragment());
            ft.commit();
        }
    }

    public void onLearnAtHomeClick(View view) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragmentHolder, new LearnInHomeFragment());
        ft.commit();
    }

    public void onGiveUpSchoolClick(View view) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragmentHolder, new FindJobFragment());
        ft.commit();
    }

    public void onGiveUpWorkClick(View view) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragmentHolder, new FindJobFragment());
        ft.commit();
    }

    public void onEducationClick(View view) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragmentHolder, new EducationFragment());
        ft.commit();
    }

    public static String ThingToDoForDoingSomething;
    public void onBedClick(View view)
    {
        ThingToDoForDoingSomething = "Sleep";
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.mainFragmentHolder, new DoingSomethingFragment());
        ft.commit();
    }

    public void onTvClick(View view)
    {
        if(MainActivity.MyTv != null)
        {
            ThingToDoForDoingSomething = "WatchTV";
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.mainFragmentHolder, new DoingSomethingFragment());
            ft.commit();
        }
    }

    public void onPlayClick(View view)
    {
        if(MyComputer == null || MyPhone == null)
        {
            if(MyPhone == null)
                ThingToDoForDoingSomething = "PlayComputer";
            else if(MyComputer == null)
                ThingToDoForDoingSomething = "PlayPhone";
        }
        else
        {
            if(MyComputer.getPrice() >= MyPhone.getPrice())
                ThingToDoForDoingSomething = "PlayComputer";
            else
                ThingToDoForDoingSomething = "PlayPhone";
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.mainFragmentHolder, new DoingSomethingFragment());
        ft.commit();
    }

    public void onSupportClick(View view)
    {
        alertView("Support a charity event", "Do you really want to support a charity event for $50?");
    }

    private void alertView(String title, String message) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(title)
                //.setIcon(R.drawable.ic_launcher)
                .setMessage(message)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.cancel();
                    }})
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        Money -= 50;
                        CommunicationSkills += 15;
                    }
                }).show();
    }

    public void onTalkClick(View view)
    {
        ThingToDoForDoingSomething = "TalkOnMessengers";
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.mainFragmentHolder, new DoingSomethingFragment());
        ft.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
