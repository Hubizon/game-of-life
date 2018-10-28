package com.example.hubert.gameoflife.Utils;

import com.example.hubert.gameoflife.Education.Skill;

public class Arrays {

    public static Skill PassPrimarySchool = new Skill("Pass Primary School", 100, 10);
    public static Skill PassSecondarySchool = new Skill("Pass Secondary School", 750, 15);
    public static Skill PassHighSchool = new Skill("Pass High School", 2500, 20);
    public static Skill GeneralTraing = new Skill("General training", 7500, 25);
    public static Skill StudyAtCollage = new Skill("Study At Collage", 12500, 30);
    public static Skill GetAMastersDegree = new Skill("Get A Master's Degree", 25000, 35);
    public static final Skill[] skillsEducationList = new Skill[] {
            PassPrimarySchool,
            PassSecondarySchool,
            PassHighSchool,
            GeneralTraing,
            StudyAtCollage,
            GetAMastersDegree,
    };

    public static Skill WeaponSkillsBeginner = new Skill("Weapon Skills Beginner", 100, 10);
    public static Skill WeaponSkillsIntermediate = new Skill("Weapon Skills Intermediate", 750, 15);
    public static Skill WeaponSkillsAdvanced = new Skill("Weapon Skills Advanced", 2500, 20);
    public static Skill ThiefSkillsBeginner = new Skill("Thief Skills Beginner", 100, 10);
    public static Skill ThiefSkillsIntermediate = new Skill("Thief Skills Intermediate", 750, 15);
    public static Skill ThiefSkillsAdvanced = new Skill("Thief Skills Advanced", 2500, 20);

    public static final Skill[] skillsCriminalList = new Skill[] {
            WeaponSkillsBeginner,
            WeaponSkillsIntermediate,
            WeaponSkillsAdvanced,
            ThiefSkillsBeginner,
            ThiefSkillsIntermediate,
            ThiefSkillsAdvanced,
    };

}
