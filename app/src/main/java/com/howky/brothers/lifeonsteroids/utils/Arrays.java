package com.howky.brothers.lifeonsteroids.utils;

import android.support.annotation.MainThread;

import com.howky.brothers.lifeonsteroids.MyApplication;
import com.howky.brothers.lifeonsteroids.education.Skill;
import com.howky.brothers.lifeonsteroids.house.Fun;
import com.howky.brothers.lifeonsteroids.house.Lodging;
import com.howky.brothers.lifeonsteroids.house.Transport;
import com.howky.brothers.lifeonsteroids.R;
import com.howky.brothers.lifeonsteroids.shop.Food;
import com.howky.brothers.lifeonsteroids.shop.Lottery;
import com.howky.brothers.lifeonsteroids.shop.Medicines;
import com.howky.brothers.lifeonsteroids.shop.Weapon;
import com.howky.brothers.lifeonsteroids.work.CriminalJob;
import com.howky.brothers.lifeonsteroids.work.Job;

public class Arrays {

    private static Skill PassPrimarySchool = new Skill(MyApplication.getMyContext().getString(R.string.pass_primary_school), MyApplication.getMyContext().getString(R.string.primary_school), 100, 10);
    private static Skill PassSecondarySchool = new Skill(MyApplication.getMyContext().getString(R.string.pss_secondary_school), MyApplication.getMyContext().getString(R.string.secondary_school), 750, 15);
    private static Skill PassHighSchool = new Skill(MyApplication.getMyContext().getString(R.string.pass_high_school), MyApplication.getMyContext().getString(R.string.high_school), 2500, 20);
    private static Skill GeneralTraining = new Skill(MyApplication.getMyContext().getString(R.string.general_training), MyApplication.getMyContext().getString(R.string.general_training), 7500, 25);
    private static Skill StudyAtCollage = new Skill(MyApplication.getMyContext().getString(R.string.study_at_collage), MyApplication.getMyContext().getString(R.string.student), 12500, 30);
    private static Skill GetAMastersDegree = new Skill(MyApplication.getMyContext().getString(R.string.get_a_masters_degree), MyApplication.getMyContext().getString(R.string.masters_degree), 25000, 35);
    public static final Skill[] skillsEducationList = new Skill[] {
            PassPrimarySchool,
            PassSecondarySchool,
            PassHighSchool,
            GeneralTraining,
            StudyAtCollage,
            GetAMastersDegree,
    };

    private static Skill WeaponSkillsBeginner = new Skill(MyApplication.getMyContext().getString(R.string.wapons_skills_beginner), MyApplication.getMyContext().getString(R.string.wapons_skills_beginner), 100, 10);
    private static Skill WeaponSkillsIntermediate = new Skill(MyApplication.getMyContext().getString(R.string.weapon_skills_intermediate), MyApplication.getMyContext().getString(R.string.weapon_skills_intermediate), 750, 15);
    private static Skill WeaponSkillsAdvanced = new Skill(MyApplication.getMyContext().getString(R.string.weapon_skills_advanced), MyApplication.getMyContext().getString(R.string.weapon_skills_advanced), 2500, 20);
    private static Skill ThiefSkillsBeginner = new Skill(MyApplication.getMyContext().getString(R.string.thief_skills_beginner), MyApplication.getMyContext().getString(R.string.thief_skills_beginner), 100, 10);
    private static Skill ThiefSkillsIntermediate = new Skill(MyApplication.getMyContext().getString(R.string.thief_skills_intermediate), MyApplication.getMyContext().getString(R.string.thief_skills_intermediate), 750, 15);
    private static Skill ThiefSkillsAdvanced = new Skill(MyApplication.getMyContext().getString(R.string.thief_skills_advanced), MyApplication.getMyContext().getString(R.string.thief_skills_advanced), 2500, 20);

    public static final Skill[] skillsCriminalList = new Skill[] {
            WeaponSkillsBeginner,
            WeaponSkillsIntermediate,
            WeaponSkillsAdvanced,
            ThiefSkillsBeginner,
            ThiefSkillsIntermediate,
            ThiefSkillsAdvanced,
    };

    public static Food[] foodList = new Food[] {
            new Food(MyApplication.getMyContext().getString(R.string.eat_thrashes), 0, 50, -20, 0, -15),
            new Food(MyApplication.getMyContext().getString(R.string.drink_water_waster_water), 0, 35, -10, 0, -10),
            new Food(MyApplication.getMyContext().getString(R.string.drink_water), 3, 40, 10, 0, 0),
            new Food(MyApplication.getMyContext().getString(R.string.drink_milk), 4, 30, 20, 0, 0),
            new Food(MyApplication.getMyContext().getString(R.string.eat_chips), 4, 50, -5, 0, 20),
            new Food(MyApplication.getMyContext().getString(R.string.eat_sandwich), 5, 75, 0, 0, 0),
            new Food(MyApplication.getMyContext().getString(R.string.drink_cofee), 7, 20, 0, 50, 0),
            new Food(MyApplication.getMyContext().getString(R.string.drink_beer), 9, 50, -10, 10, 10),
            new Food(MyApplication.getMyContext().getString(R.string.eat_cereal_milk), 10, 200, 5, 0, 0),
            new Food(MyApplication.getMyContext().getString(R.string.drink_energy_drink), 12, 40, -5, 100, 0),
            new Food(MyApplication.getMyContext().getString(R.string.drink_wine), 14, 40, -5, -10, 25),
            new Food(MyApplication.getMyContext().getString(R.string.eat_soup), 16, 100, 5, 0, 0),
            new Food(MyApplication.getMyContext().getString(R.string.eat_burger), 20, 125, -5, 0, 5),
            new Food(MyApplication.getMyContext().getString(R.string.eat_meat), 30, 200, 0, 0, 0),
            new Food(MyApplication.getMyContext().getString(R.string.eat_fast_food), 40, 350, -45, 0, 20),
            new Food(MyApplication.getMyContext().getString(R.string.eat_restourant), 100, 500, 0, 0, 0),
            new Food(MyApplication.getMyContext().getString(R.string.eat_exclusive_restourant), 300, 1000, 50, 0, 50),
    };

    public static Medicines[] medicinesList = new Medicines[]{
            new Medicines(MyApplication.getMyContext().getString(R.string.eat_tablet), 10, 50),
            new Medicines(MyApplication.getMyContext().getString(R.string.go_small_clinic), 30, 125),
            new Medicines(MyApplication.getMyContext().getString(R.string.go_big_clinic), 50, 200),
            new Medicines(MyApplication.getMyContext().getString(R.string.go_local_doctor), 100, 350),
            new Medicines(MyApplication.getMyContext().getString(R.string.go_hospital), 150, 500),
            new Medicines(MyApplication.getMyContext().getString(R.string.go_private_doctor), 225, 750),
            new Medicines(MyApplication.getMyContext().getString(R.string.go_world_class_hospitall), 300, 1000)
    };


    private static Fun GoToTheCinema = new Fun(MyApplication.getMyContext().getString(R.string.go_cinema), "Exit",15, 120);
    private static Fun OldPhone = new Fun(MyApplication.getMyContext().getString(R.string.old_phone), "Phone", 50, 120);
    private static Fun BlackAndWhiteTv = new Fun(MyApplication.getMyContext().getString(R.string.black_white_tv), "TV", 150, 180);
    private static Fun WoodenPc =  new Fun(MyApplication.getMyContext().getString(R.string.wooden_pc), "Computer",100, 150);
    private static Fun Tv = new Fun(MyApplication.getMyContext().getString(R.string.tv), "TV", 200, 180);
    private static Fun Smartphone = new Fun(MyApplication.getMyContext().getString(R.string.smartphone), "Phone", 400, 180);
    private static Fun Computer = new Fun(MyApplication.getMyContext().getString(R.string.computer), "Computer",650, 220);
    private static Fun PlasmaTv = new Fun(MyApplication.getMyContext().getString(R.string.plasma_tv), "TV",1000, 360);
    private static Fun ModernComputer = new Fun(MyApplication.getMyContext().getString(R.string.modern_computer), "Computer", 1500, 300);

    public static Fun[] funList = new Fun[]{
            GoToTheCinema,
            OldPhone,
            BlackAndWhiteTv,
            WoodenPc,
            Tv,
            Smartphone,
            Computer,
            PlasmaTv,
            ModernComputer,
    };


    public static Lottery[] lotteryList = new Lottery[]{
            new Lottery(MyApplication.getMyContext().getString(R.string.scratchcard), 5, 200, 10000),
            new Lottery(MyApplication.getMyContext().getString(R.string.lotto), 8, 1000, 2000000),
            new Lottery(MyApplication.getMyContext().getString(R.string.superlotto_plus), 10, 20000, 5000000),
            new Lottery(MyApplication.getMyContext().getString(R.string.powerball), 12, 150, 10000000),
            new Lottery(MyApplication.getMyContext().getString(R.string.euro_jackpot), 14, 350, 7500000),
            new Lottery(MyApplication.getMyContext().getString(R.string.mega_millions), 15, 15000, 1500000),
            new Lottery(MyApplication.getMyContext().getString(R.string.euro_millions), 20, 10000, 10000000),
            new Lottery(MyApplication.getMyContext().getString(R.string.win_the_life), 100, 100000, 999999999),
    };


    private static Weapon knife = new Weapon(MyApplication.getMyContext().getString(R.string.knife), 20);
    private static Weapon pistol = new Weapon(MyApplication.getMyContext().getString(R.string.pistol), 50);
    private static Weapon grenades = new Weapon(MyApplication.getMyContext().getString(R.string.grenades), 150);
    private static Weapon ak47 = new Weapon(MyApplication.getMyContext().getString(R.string.ak47), 350);
    private static Weapon bombs = new Weapon(MyApplication.getMyContext().getString(R.string.bombs), 600);
    private static Weapon sniperRifle = new Weapon(MyApplication.getMyContext().getString(R.string.sniper_rifle), 1000);
    private static Weapon rocketLauncher = new Weapon(MyApplication.getMyContext().getString(R.string.rocket_launcher), 2500);

    public static Weapon[] weaponList = new Weapon[]
            {
                    knife,
                    pistol,
                    grenades,
                    ak47,
                    sniperRifle,
                    bombs,
                    rocketLauncher,
            };


    static Lodging CheapFlatInTheDangerousDistrict = new Lodging(MyApplication.getMyContext().getString(R.string.cheap_flat_dangerous_district), 150,  -2, 100, -2);
    private static Lodging CheapFlat = new Lodging(MyApplication.getMyContext().getString(R.string.cheap_flat), 185, -2, 100, -2);
    private static Lodging Flat = new Lodging(MyApplication.getMyContext().getString(R.string.flat), 220, 0, 125, -1);
    private static Lodging House = new Lodging(MyApplication.getMyContext().getString(R.string.house), 425, 2, 150, 3);
    private static Lodging Motel = new Lodging(MyApplication.getMyContext().getString(R.string.motel), 550,  1, 125, 2);
    private static Lodging Hotel1 = new Lodging(MyApplication.getMyContext().getString(R.string.one_star_hotel), 675,2, 125, 3);
    private static Lodging Hotel2 = new Lodging(MyApplication.getMyContext().getString(R.string.two_star_hotel), 800,3, 150, 3);
    private static Lodging Hotel3 = new Lodging(MyApplication.getMyContext().getString(R.string.three_star_hotel), 1000,4, 175, 4);
    private static Lodging Hotel4 =  new Lodging(MyApplication.getMyContext().getString(R.string.four_star_hotel), 1350,5, 200, 5);
    private static Lodging Hotel5 = new Lodging(MyApplication.getMyContext().getString(R.string.five_star_hotel), 2000,7, 225, 8);
    private static Lodging SmallHouse = new Lodging(MyApplication.getMyContext().getString(R.string.small_house), 350,1, 135, 2);
    private static Lodging BigHouse =  new Lodging(MyApplication.getMyContext().getString(R.string.big_house), 750,2, 175, 5);
    private static Lodging Villa = new Lodging(MyApplication.getMyContext().getString(R.string.villa), 2500,  8,225, 10);
    private static Lodging VeryExclusiveVilla = new Lodging(MyApplication.getMyContext().getString(R.string.very_exclusive_villa), 7500,10, 275, 15);
    private static Lodging SuperExclusiveAndModernVilla = new Lodging(MyApplication.getMyContext().getString(R.string.super_exclusive_moder_villa), 15000, 12, 400, 25);

    public static Lodging[] lodgingList = new Lodging[]{
            CheapFlatInTheDangerousDistrict,
            CheapFlat,
            Flat,
            House,
            Motel,
            Hotel1,
            Hotel2,
            Hotel3,
            Hotel4,
            Hotel5,
            SmallHouse,
            BigHouse,
            Villa,
            VeryExclusiveVilla,
            SuperExclusiveAndModernVilla
    };


    private static Transport Boots = new Transport(MyApplication.getMyContext().getString(R.string.shoes), 4, 9);
    private static Transport Skateboard = new Transport(MyApplication.getMyContext().getString(R.string.skateboard), 10, 8);
    private static Transport Bicycle = new Transport(MyApplication.getMyContext().getString(R.string.bicycle), 22, 7);
    private static Transport Bus = new Transport(MyApplication.getMyContext().getString(R.string.bus), 60, 6);
    private static Transport Motorcycle = new Transport(MyApplication.getMyContext().getString(R.string.motorcycle), 150, 5);
    private static Transport Car = new Transport(MyApplication.getMyContext().getString(R.string.car), 175, 4);
    private static Transport SportsCar = new Transport(MyApplication.getMyContext().getString(R.string.sports_car), 400, 3);
    private static Transport Aeroplane = new Transport(MyApplication.getMyContext().getString(R.string.aeroplane), 5000, 2);
    private static Transport Rocket = new Transport(MyApplication.getMyContext().getString(R.string.rocket), 250000, 1);
    private static Transport Teleporter = new Transport(MyApplication.getMyContext().getString(R.string.teleporter), 1000000, 0);

    public static Transport[] transportList = new Transport[]{
            Boots,
            Skateboard,
            Bicycle,
            Bus,
            Motorcycle,
            Car,
            SportsCar,
            Aeroplane,
            Rocket,
            Teleporter,
    };



    public static final Job[] officeJobsList = new Job[] {
            new Job(MyApplication.getMyContext().getString(R.string.beggar), 25, 10, 0,  0, null,null, null, null, null, null),
            new Job(MyApplication.getMyContext().getString(R.string.salesperson), 55, 12, 1,  0, OldPhone, null, null,null, Boots, new Skill[] { PassPrimarySchool}),
            new Job(MyApplication.getMyContext().getString(R.string.dustman), 65, 75, 1, 0, OldPhone, WoodenPc,null, CheapFlatInTheDangerousDistrict, Boots, new Skill[] { PassPrimarySchool}),
            new Job(MyApplication.getMyContext().getString(R.string.painter), 80, 100, 5,  R.string.saved_drawing_skills_key, Smartphone, WoodenPc, BlackAndWhiteTv, CheapFlat, Bicycle, new Skill[] { PassPrimarySchool, PassSecondarySchool }),
            new Job(MyApplication.getMyContext().getString(R.string.teacher), 100, 115, 25,  R.string.saved_communication_skills_key, Smartphone, Computer, BlackAndWhiteTv, CheapFlat, Bicycle, new Skill[] { PassPrimarySchool, PassSecondarySchool, PassHighSchool }),
            new Job(MyApplication.getMyContext().getString(R.string.youtuber), 125, 150, 3,  R.string.saved_recording_skills_key, Smartphone,ModernComputer, Tv, Flat, Motorcycle, new Skill[] { PassPrimarySchool, PassSecondarySchool, PassHighSchool, GeneralTraining }),
            new Job(MyApplication.getMyContext().getString(R.string.programmer), 145, 170, 10,  R.string.saved_programming_skills_key, Smartphone,ModernComputer, Tv, SmallHouse, Motorcycle, new Skill[] { PassPrimarySchool, PassSecondarySchool, PassHighSchool, GeneralTraining, StudyAtCollage }),
            new Job(MyApplication.getMyContext().getString(R.string.footballer), 180, 200, 5,  0, Smartphone,ModernComputer, Tv, SmallHouse, Car, new Skill[] { PassPrimarySchool, PassSecondarySchool, PassHighSchool, GeneralTraining, StudyAtCollage }),
            new Job(MyApplication.getMyContext().getString(R.string.scientist), 220, 245, 50,  0, Smartphone, ModernComputer, PlasmaTv, House, Car, new Skill[] { PassPrimarySchool, PassSecondarySchool, PassHighSchool, GeneralTraining, StudyAtCollage, GetAMastersDegree }),
            new Job(MyApplication.getMyContext().getString(R.string.doctor), 275, 315, 50,  0, Smartphone,ModernComputer, PlasmaTv, Hotel3, Car, new Skill[] { PassPrimarySchool, PassSecondarySchool, PassHighSchool, GeneralTraining, StudyAtCollage, GetAMastersDegree }),
            new Job(MyApplication.getMyContext().getString(R.string.lawyer), 350, 550,15,  R.string.saved_communication_skills_key, Smartphone,ModernComputer, PlasmaTv, BigHouse, SportsCar, new Skill[] { PassPrimarySchool, PassSecondarySchool, PassHighSchool, GeneralTraining, StudyAtCollage, GetAMastersDegree }),
            new Job(MyApplication.getMyContext().getString(R.string.businessman), 500, 800, 50,  R.string.saved_communication_skills_key, Smartphone, ModernComputer, PlasmaTv, Villa, SportsCar, new Skill[] { PassPrimarySchool, PassSecondarySchool, PassHighSchool, GeneralTraining, StudyAtCollage, GetAMastersDegree }),
    };

    public static CriminalJob[] criminalJobsList = new CriminalJob[] {
            new CriminalJob(MyApplication.getMyContext().getString(R.string.pickpocket), 40, 45,null, null, null,null, null, new Skill[] { ThiefSkillsBeginner }, 125, null),
            new CriminalJob(MyApplication.getMyContext().getString(R.string.thief), 55, 65, OldPhone, null, null, CheapFlat, Bicycle,  new Skill[] { ThiefSkillsBeginner, ThiefSkillsIntermediate },75, new Weapon[] { knife }),
            new CriminalJob(MyApplication.getMyContext().getString(R.string.drug_dealer), 70, 75, Smartphone, null, null, SmallHouse, Motorcycle,  new Skill[] { ThiefSkillsBeginner, ThiefSkillsIntermediate, WeaponSkillsBeginner }, 40, new Weapon[] { knife, pistol, grenades }),
            new CriminalJob(MyApplication.getMyContext().getString(R.string.terrorist), 80, 85, Smartphone, WoodenPc, null, House, Car,  new Skill[] { ThiefSkillsBeginner, ThiefSkillsIntermediate, WeaponSkillsBeginner, WeaponSkillsIntermediate },25, new Weapon[] { knife, pistol, grenades, ak47, bombs }),
            new CriminalJob(MyApplication.getMyContext().getString(R.string.kidnap_kids), 90, 100, Smartphone, Computer, BlackAndWhiteTv, BigHouse, Car,  new Skill[] { ThiefSkillsBeginner, ThiefSkillsIntermediate, WeaponSkillsBeginner, WeaponSkillsIntermediate, ThiefSkillsAdvanced }, 20, new Weapon[] { knife, pistol, grenades, ak47, bombs }),
            new CriminalJob(MyApplication.getMyContext().getString(R.string.mafia_member), 95, 120, Smartphone, ModernComputer, Tv, BigHouse, Car,  new Skill[] { ThiefSkillsBeginner, ThiefSkillsIntermediate, WeaponSkillsBeginner, WeaponSkillsIntermediate, ThiefSkillsAdvanced }, 15, new Weapon[] { knife, pistol, grenades, ak47, bombs, sniperRifle }),
            new CriminalJob(MyApplication.getMyContext().getString(R.string.assasin), 125, 160, Smartphone, ModernComputer, PlasmaTv, Villa, SportsCar,  new Skill[] { ThiefSkillsBeginner, ThiefSkillsIntermediate, WeaponSkillsBeginner, WeaponSkillsIntermediate, ThiefSkillsAdvanced, WeaponSkillsAdvanced }, 10, new Weapon[] { knife, pistol, grenades, ak47, bombs, sniperRifle, rocketLauncher }),
    };

    public static final String[] boyfriendsNames = new String[] {
            "Liam", "Noah", "William", "James", "Logan", "Benjamin", "Mason", "Elijah", "Oliver", "Jacob", "Lucas", "Michael", "Alexander", "Ethan", "Daniel", "Matthew",
            "Aiden", "Henry", "Joseph", "Jackson", "Samuel", "Sebastian", "David", "Carter", "Wyatt", "Jayden", "John", "Owen", "Dylan", "Luke", "Gabriel", "Anthony",
            "Isaac", "Grayson", "Jack", "Julian", "Levi", "Christopher", "Joshua", "Andrew", "Lincoln", "Mateo", "Ryan", "Jaxon", "Nathan", "Aaron", "Isaiah", "Thomas",
            "Charles", "Caleb", "Josiah", "Christian", "Hunter", "Eli", "Jonathan", "Connor", "Landon", "Adrian", "Asher", "Cameron", "Leo", "Theodore", "Jeremiah",
            "Hudson", "Robert", "Easton", "Nolan", "Nicholas", "Ezra", "Colton", "Angel", "Brayden", "Jordan", "Dominic", "Austin", "Ian", "Adam", "Elias", "Jaxson",
            "Greyson", "Jose", "Ezekiel", "Carson", "Evan", "Maverick", "Bryson", "Jace", "Cooper", "Xavier", "Parker", "Roman", "Jason", "Santiago", "Chase", "Sawyer",
            "Gavin", "Leonardo", "Kayden", "Ayden", "Jameson",
    };

    public static final String[] girlfriendsNames = new String[] {
            "Emma", "Olivia", "Sophia", "Isabella", "Ava", "Mia", "Emily", "Abigail", "Charlotte", "Jacob", "Harper", "Sofia", "Avery", "Elizabeth", "Amelia", "Evelyn",
            "Ella", "Chloe", "Victoria", "Aubrey", "Grace", "Zoey", "Natalie", "Addison", "Lillian", "Brooklyn", "Lily", "Owen", "Hannah", "Layla", "Scarlett", "Aria",
            "Zoe", "Samantha", "Anna", "Leah", "Audrey", "Ariana", "Savannah", "Arianna", "Camila", "Penelope", "Gabriella", "Claire", "Aaliyah", "Sadie", "Riley", "Skylar",
            "Nora", "Sarah", "Hailey", "Kaylee", "Paisley", "Kennedy", "Ellie", "Connor", "Landon", "Lilly", "Asher", "Lyla", "Salma", "Mya", "Amy",
            "Luna", "Caroline", "Alexa", "Gabriella", "Clara", "Mary", "Quinn", "Peyton", "Annabelle", "Caroline", "Madelyn", "Serenity", "Aubree", "Lyla", "Lucy", "Alexa",
            "Alexis", "Nevaeh", "Stella", "Violet", "Bella", "Maya", "Taylor", "Naomi", "Eva", "Katherine", "Julia", "Ashley", "Ruby", "Sophie", "Alexandra", "Isabelle",
            "Alice", "Jasmine", "Clara", "Natalia", "Valentina",
    };
}