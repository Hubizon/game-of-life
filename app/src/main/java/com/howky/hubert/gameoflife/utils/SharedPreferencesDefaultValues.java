package com.howky.hubert.gameoflife.utils;

import com.howky.hubert.gameoflife.education.Skill;
import com.howky.hubert.gameoflife.house.Lodging;
import com.howky.hubert.gameoflife.house.Transport;
import com.howky.hubert.gameoflife.R;
import com.google.gson.Gson;

import java.util.ArrayList;

public class SharedPreferencesDefaultValues {

    public static final int DefaultNumberOfUsers = 0;

    public static final int DefaultWorkPosition = 1;
    private static final Gson gson = new Gson();

    // sa 2 domyslne wartosci, tu i w strings.xml (do preference_headers potrzeba strings.xml)
    public static final String DefaultName = "";
    public static final boolean DefaultIsMale = true;
    public static final int DefaultIcon = R.drawable.avatar_icon1;
    public static final int DefaultMoney = 5000;
    public static final int DefaultMoneyInSafe = 0;

    public static final int DefaultAgeYears = 19;
    public static final int DefaultAgeDays = 0;

    public static final int DefaultDateYears = 2000;
    public static final int DefaultDateMonths = 1;
    public static final int DefaultDateDays = 1;
    public static final int DefaultTimeHours = 12;

    public static final int DefaultDayWeek = 1;

    public static final String DefaultMyLodging = gson.toJson(new Lodging("Street", 0,  5, 100, 5));
    public static final String DefaultMyLodgingAfter18 = gson.toJson(new Lodging("Public Bench and Blanket from The Garbage Bin", 0,  -5, 75, -5));
    public static final boolean DefaultIsInSchoolNow = true;
    public static final String DefaultMyJob = null;
    public static final String DefaultMyTransport = gson.toJson(new Transport("Foot", 0, 10));
    public static final String DefaultLove = null;
    public static final int DefaultLoveRelations = 50;
    public static final int DefaultLoveRelationshipLevel = 1;
    public static final String DefaultMyChildren = null;

//    public static final int DefaultWorkPosition = 0;
//    public static final String DefaultMyTransport = gson.toJson(new Transport("On foot", 0, 10));
//    public static final String DefaultMyGirlboyfriend = "None";
//    public static final String DefaultMyChildren = "None";

    public static final int DefaultHealth = 750;
    public static final int DefaultHungry = 650;
    public static final int DefaultEnergy = 650;
    public static final int DefaultHappiness = 650;

    public static final String DefaultMyComputer = null;
    public static final String DefaultMyTv = null;
    public static final String DefaultMyPhone = null;
    public static final String DefaultOwnedLotteries = gson.toJson(new ArrayList<>());
    public static final boolean DefaultHaveSafe = false;

    /*static private final Subject[] subjectsList = new Subject[] {
            new Subject("Mathematics", 4),
            new Subject("English", 4),
            new Subject("Mathematics", 4),
            new Subject("Languages", 4),
            new Subject("History", 4),
            new Subject("Geographic", 4),
            new Subject("Psychic", 4),
            new Subject("Biology", 4),
            new Subject("Information Technology", 4),
            new Subject("Art", 4),
            new Subject("Music", 4),
            new Subject("Psychical Education", 4),
            new Subject("Behavior", 4), };*/



    static private final Skill[] skillsSpecialList = new Skill[] {
            new Skill("Driving Skills", 100, 10),};

    //public static final String DefaultSubjectsList = gson.toJson(subjectsList);
    public static final int DefaultEducationPoints = 100;
    public static final String DefaultSkillsEducationList = gson.toJson(Arrays.skillsEducationList);
    public static final String DefaultSkillsCriminalList = gson.toJson(Arrays.skillsCriminalList);
    public static final String DefaultSkillsSpecialList = gson.toJson(skillsSpecialList);
    public static final String DefaultWeapons = gson.toJson(Arrays.weaponList);
    public static final String DefaultOfficeJobsList = gson.toJson(Arrays.officeJobsList);
    public static final String DefaultCriminalJobsList = gson.toJson(Arrays.criminalJobsList);

    static final int DefaultCommunicationsSkills = 100;
    public static final int DefaultCriminalPoints = 100;
    public final static int DefaultKarmaPoints = 70;
    public final static int DefaultWorkPositionPoints = 75;
    public static final int DefaultWorkRelations = 100;

    //NIGDY NIE DAWAJ NULL! godzine nad tym bledem siedzialem..
    static final String DefaultGamesList = gson.toJson(new ArrayList<>());
    static final String DefaultDrawingsList = gson.toJson(new ArrayList<>());
    static final String DefaultBooksList = gson.toJson(new ArrayList<>());
    static final String DefaultMoviesList = gson.toJson(new ArrayList<>());
}
