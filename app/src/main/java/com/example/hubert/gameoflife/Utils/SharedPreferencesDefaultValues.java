package com.example.hubert.gameoflife.Utils;

import com.example.hubert.gameoflife.Girlboyfriend.Children;
import com.example.hubert.gameoflife.Girlboyfriend.Girlboyfriend;
import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.Work.Job;
import com.google.gson.Gson;

import java.util.ArrayList;

public class SharedPreferencesDefaultValues {

    public static String DefaultName = "";
    public static int DefaultIcon = R.drawable.avatar_icon1;
    public static int DefaultMoney = 100000;

    public static int DefaultAgeYears = 19;
    public static int DefaultAgeDays = 0;

    public static int DefaultDateYears = 2000;
    public static int DefaultDateMonths = 1;
    public static int DefaultDateDays = 1;
    public static int DefaultTimeHours = 12;

    private static Gson gson = new Gson();
    private static String jsonLodging = gson.toJson(new Lodging("Parents House", 0, 10, 5, 125, 5));
    public static String DefaultMyLodging = jsonLodging;
    public static boolean DefaultIsInSchoolNow = true;
    public static String DefaultMyJob = null;
    private static String jsonTransport = gson.toJson(new Transport("Foots", 0, 10));
    public static String DefaultMyTransport = jsonTransport;
    public static String DefaultMyGirlboyfriend = null;
    public static String DefaultMyChildren = null;

    public static int DefaultHealth = 750;
    public static int DefaultHungry = 750;
    public static int DefaultEnergy = 750;
    public static int DefaultHappiness = 750;

    public static String DefaultMyComputer = null;
    public static String DefaultMyTv = null;
    public static String DefaultMyPhone = null;
    private static String jsonLotteries = gson.toJson(new ArrayList<>());
    public static String DefaultOwnedLotteries = jsonLotteries;
}
