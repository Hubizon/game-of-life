package com.example.hubert.gameoflife.Shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hubert.gameoflife.House.Fun;
import com.example.hubert.gameoflife.House.Lodging;
import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.House.Transport;


public class ShopFragment extends Fragment
    implements View.OnClickListener {

    public static Food[] foodList = new Food[] {
            new Food("Eat Thrashes", 0, 50, -20, 0, -15),
            new Food("Drink Water from Wasterwater", 0, 35, -10, 0, -10),
            new Food("Drink Water", 3, 40, 10, 0, 0),
            new Food("Drink Milk", 4, 30, 20, 0, 0),
            new Food("Eat Chips", 4, 50, -5, 0, 20),
            new Food("Eat Sandwich", 5, 75, 0, 0, 0),
            new Food("Drink Cofee", 7, 20, 0, 50, 0),
            new Food("Drink Beer", 9, 50, -10, 10, 10),
            new Food("Eat Cereal with Milk", 10, 200, 5, 0, 0),
            new Food("Drink Energy Drink", 12, 40, -5, 100, 0),
            new Food("Drink Wine", 14, 40, -5, -10, 25),
            new Food("Eat Soup", 16, 100, 5, 0, 0),
            new Food("Eat Burger", 20, 125, -5, 0, 5),
            new Food("Eat Meat", 30, 200, 0, 0, 0),
            new Food("Eat Fast Food", 40, 350, -45, 0, 20),
            new Food("Eat in Restaurant", 100, 500, 0, 0, 0),
            new Food("Eat in Exclusive Restaurant", 300, 1000, 50, 0, 50),
    };

    public static Medicines[] medicinesList = new Medicines[]{
            new Medicines("Eat Tablet", 10, 50),
            new Medicines("Go to Small Clinic", 30, 125),
            new Medicines("Go to Big Clinic", 50, 200),
            new Medicines("Go to Local Doctor", 100, 350),
            new Medicines("Go to Hospital", 150, 500),
            new Medicines("Go to Private Doctor", 225, 750),
            new Medicines("Go to World Class Hospital", 300, 1000)
    };


    public static Fun GoToTheCinema = new Fun("Go to The Cinema", "Exit",15, 120);
    public static Fun OldPhone = new Fun("Old Phone", "Phone", 50, 120);
    public static Fun BlackAndWhiteTv = new Fun("Black and White TV", "TV", 150, 180);
    public static Fun WoodenPc =  new Fun("Wooden PC", "Computer",100, 150);
    public static Fun Tv = new Fun("TV", "TV", 200, 180);
    public static Fun Smartphone = new Fun("Smartphone", "Phone", 400, 180);
    public static Fun Computer = new Fun("Computer", "Computer",650, 220);
    public static Fun PlasmaTv = new Fun("Plasma TV", "TV",1000, 360);
    public static Fun ModernComputer = new Fun("Modern Computer", "Computer", 1500, 300);

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
            new Lottery("Scratchcard", 5, 200, 10000),
            new Lottery("Lotto", 8, 1000, 2000000),
            new Lottery("SuperLotto Plus", 10, 20000, 5000000),
            new Lottery("Powerball", 12, 150, 10000000),
            new Lottery("Euro Jackpot", 14, 350, 7500000),
            new Lottery("Mega Milions", 15, 15000, 1500000),
            new Lottery("Euro Millions", 20, 10000, 10000000),
            new Lottery("Win The Life", 100, 100000, 999999999),
    };


    public static Weapon knife = new Weapon("Knife", 20);
    public static Weapon pistol = new Weapon("Pistol", 50);
    public static Weapon grenades = new Weapon("Grenades", 150);
    public static Weapon ak47 = new Weapon("AK-47", 350);
    public static Weapon bombs = new Weapon("Bombs", 600);
    public static Weapon sniperRifle = new Weapon("Sniper Rifle", 1000);
    public static Weapon rocketLauncher = new Weapon("Rocket Launcher", 2500);

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


    public static Lodging CheapFlatInTheDangerousDistrict = new Lodging("Cheap Flat in the dangerous district", 150,  -2, 100, -2);
    public static Lodging CheapFlat = new Lodging("Cheap Flat", 185, -2, 100, -2);
    public static Lodging Flat = new Lodging("Flat", 220, 0, 125, -1);
    public static Lodging House = new Lodging("House", 425, 2, 150, 3);
    public static Lodging Motel = new Lodging("Motel", 550,  1, 125, 2);
    public static Lodging Hotel1 = new Lodging("1 Star Hotel", 675,2, 125, 3);
    public static Lodging Hotel2 = new Lodging("2 Star Hotel", 800,3, 150, 3);
    public static Lodging Hotel3 = new Lodging("3 Star Hotel", 1000,4, 175, 4);
    public static Lodging Hotel4 =  new Lodging("4 Star Hotel", 1350,5, 200, 5);
    public static Lodging Hotel5 = new Lodging("5 Star Hotel", 2000,7, 225, 8);
    public static Lodging SmallHouse = new Lodging("Small House", 350,1, 135, 2);
    public static Lodging BigHouse =  new Lodging("Big House", 750,2, 175, 5);
    public static Lodging Villa = new Lodging("Villa", 2500,  8,225, 10);
    public static Lodging VeryExclusiveVilla = new Lodging("Very Exclusive Villa", 7500,10, 275, 15);
    public static Lodging SuperExclusiveAndModernVilla = new Lodging("Super Exclusive and Modern Villa", 15000, 12, 400, 25);

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


    public static Transport Boots = new Transport("Boots", 100, 9);
    public static Transport Skateboard = new Transport("Skateboard", 250, 8);
    public static Transport Bicycle = new Transport("Bicycle", 650, 7);
    public static Transport Bus = new Transport("Bus", 1500, 6);
    public static Transport Motorcycle = new Transport("Motorcycle", 3500, 5);
    public static Transport Car = new Transport("Car", 20000, 4);
    public static Transport SportsCar = new Transport("Sports Car", 100000, 3);
    public static Transport Aeroplane = new Transport("Aeroplane", 5000000, 2);
    public static Transport Rocket = new Transport("Rocket", 25000000, 1);
    public static Transport Teleporter = new Transport("Teleporter", 100000000, 0);

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



    public ShopFragment() {}

    public static ShopFragment newInstance() {
        ShopFragment fragment = new ShopFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shop,
                container, false);

        CardView foodcardview = view.findViewById(R.id.cardview_food);
        foodcardview.setOnClickListener(this);

        CardView medicinescardview = view.findViewById(R.id.cardview_medicines);
        medicinescardview.setOnClickListener(this);

        CardView funcardview = view.findViewById(R.id.cardview_fun);
        funcardview.setOnClickListener(this);

        CardView blackmarketcardview = view.findViewById(R.id.cardview_blackmarket);
        blackmarketcardview.setOnClickListener(this);

        CardView housecardview = view.findViewById(R.id.cardview_house);
        housecardview.setOnClickListener(this);

        CardView transportcardview = view.findViewById(R.id.cardview_transport);
        transportcardview.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity().getApplicationContext(), BuyActivity.class);
        /*switch (view.getId()) {
            case R.id.cardview_food:
                break;
            case R.id.cardview_medicines:
                break;
            case R.id.cardview_fun:
                break;
            case R.id.cardview_lottery:
                break;
            case R.id.cardview_house:
                break;
            case R.id.cardview_transport:
                break;
            default:
                break;
        }*/
        intent.putExtra(getResources().getString(R.string.send_shop_click_id), view.getId());
        startActivity(intent);
    }

}

