package com.example.hubert.gameoflife.Utils;

import com.example.hubert.gameoflife.Education.Subject;
import com.example.hubert.gameoflife.Girlboyfriend.Children;
import com.example.hubert.gameoflife.Girlboyfriend.Girlboyfriend;
import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.Work.Job;
import com.google.gson.Gson;

import java.util.ArrayList;

public class SharedPreferencesDefaultValues {

    private static Gson gson = new Gson();

    public static String DefaultName = "Mr. Hubi";
    public static int DefaultIcon = R.drawable.avatar_icon1;
    public static int DefaultMoney = 100000;

    public static int DefaultAgeYears = 19;
    public static int DefaultAgeDays = 0;

    public static int DefaultDateYears = 2000;
    public static int DefaultDateMonths = 1;
    public static int DefaultDateDays = 1;
    public static int DefaultTimeHours = 12;

    public static String DefaultMyLodging = gson.toJson(new Lodging("Parents House", 0, 10, 5, 125, 5));
    public static boolean DefaultIsInSchoolNow = true;
    public static String DefaultMyJob = null;
    public static String DefaultMyTransport = gson.toJson(new Transport("Foots", 0, 10));
    public static String DefaultMyGirlboyfriend = null;
    public static String DefaultMyChildren = null;

    public static int DefaultHealth = 750;
    public static int DefaultHungry = 750;
    public static int DefaultEnergy = 750;
    public static int DefaultHappiness = 750;

    public static String DefaultMyComputer = null;
    public static String DefaultMyTv = null;
    public static String DefaultMyPhone = null;
    public static String DefaultOwnedLotteries = gson.toJson(new ArrayList<>());

    static private Subject[] subjectsList = new Subject[] { new Subject("Mathematics", 4),
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

    public static String DefaultSubjectsList = gson.toJson(subjectsList);

    public static int DefaultCommunicationsSkills = 100;
}
