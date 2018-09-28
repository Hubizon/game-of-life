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

    public static Fun[] funList = new Fun[]{
            new Fun("Go to The Cinema", "Exit",15, 40),
            new Fun("Old Phone", "Phone", 50, 40),
            new Fun("Black and White TV", "TV", 75, 40),
            new Fun("Wooden PC", "Computer",100, 50),
            new Fun("TV", "TV", 200, 60),
            new Fun("Smartphone", "Phone", 400, 60),
            new Fun("Computer", "Computer",650, 70),
            new Fun("Plasma TV", "TV",1000, 85),
            new Fun("Modern Computer", "Computer", 1500, 100),
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

    public static Lodging[] lodgingList = new Lodging[]{
            new Lodging("Cheap", 500,  -2, 100, -2, "rent", 30),
            new Lodging("Cheap", 650, -2, 100, -2, "rent", 30),
            new Lodging("Flat", 800, 0, 125, -1, "rent", 30),
            new Lodging("House", 1500, 2, 150, 3, "rent", 30),
            new Lodging("Motel", 450,  1, 125, 2, "rent", 7),
            new Lodging("1 Star Hotel", 500,2, 125, 3, "rent", 7),
            new Lodging("2 Star Hotel", 600,3, 150, 3, "rent", 7),
            new Lodging("3 Star Hotel", 750,4, 175, 4, "rent", 7),
            new Lodging("4 Star Hotel", 1000,5, 200, 5, "rent", 7),
            new Lodging("5 Star Hotel", 1500,7, 225, 8, "rent", 7),
            new Lodging("Flat", 40000,  0, 125, -1, "buy", 0),
            new Lodging("Small House", 75000,1, 135, 2, "buy", 0),
            new Lodging("Big House", 150000,2, 175, 5, "buy", 0),
            new Lodging("Villa", 500000,  8,225, 10, "buy", 0),
            new Lodging("Very Exclusive Villa", 2500000,10, 275, 15, "buy", 0),
            new Lodging("Super Exclusive and Modern Villa", 50000000, 12, 400, 25, "buy", 0),
    };

    public static Transport[] transportList = new Transport[]{
            new Transport("Buy a Boots", 100, 9),
            new Transport("Buy a Skateboard", 250, 8),
            new Transport("Buy a Bicycle", 650, 7),
            new Transport("Buy a Bus Ticket for The Whole Life", 1500, 6),
            new Transport("Buy a Motorcycle", 3500, 5),
            new Transport("Buy a Car", 20000, 4),
            new Transport("Buy a Sports Car", 100000, 3),
            new Transport("Buy a Aeroplane Ticket for The Whole Life", 5000000, 2),
            new Transport("Buy a Rocket", 25000000, 1),
            new Transport("Buy a Teleporter", 100000000, 0),
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

        CardView lotterycardview = view.findViewById(R.id.cardview_lottery);
        lotterycardview.setOnClickListener(this);

        CardView housecardview = view.findViewById(R.id.cardview_house);
        housecardview.setOnClickListener(this);

        CardView transportcardview = view.findViewById(R.id.cardview_transport);
        transportcardview.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity().getApplicationContext(), BuyActivity.class);
        switch (view.getId()) {
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
        }
        intent.putExtra(getResources().getString(R.string.send_shop_click_id), view.getId());
        startActivity(intent);
    }

}

