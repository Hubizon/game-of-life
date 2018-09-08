package com.example.hubert.gameoflife.Shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.hubert.gameoflife.Utils.Food;
import com.example.hubert.gameoflife.Utils.Fun;
import com.example.hubert.gameoflife.Utils.Lodging;
import com.example.hubert.gameoflife.Utils.Lottery;
import com.example.hubert.gameoflife.Utils.Medicines;
import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.Utils.Transport;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopFragment extends Fragment
implements View.OnClickListener{

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
            new Fun("Go to The Cinema", "Exit",15, 50),
            new Fun("Old Phone", "Phone", 50, 100),
            new Fun("Black and White TV", "TV", 75, 125),
            new Fun("Wooden PC", "Computer",100, 150),
            new Fun("TV", "TV", 200, 250),
            new Fun("Smartphone", "Phone", 400, 350),
            new Fun("Computer", "Computer",650, 500),
            new Fun("Plasma TV", "TV",1000, 750),
            new Fun("Modern Computer", "Computer", 1500, 1000),
    };

    public static Lottery[] lotteryList = new Lottery[]{
            new Lottery("Scratchcard", 5, 0.5, 10000),
            new Lottery("Lotto", 8, 0.01, 2000000),
            new Lottery("SuperLotto Plus", 10, 0.005, 5000000),
            new Lottery("Powerball", 12, 150, 10000000),
            new Lottery("Euro Jackpot", 14, 350, 7500000),
            new Lottery("Mega Milions", 15, 0.075, 1500000),
            new Lottery("Euro Millions", 20, 0.001, 10000000),
            new Lottery("Win The Life", 100, 0.0001, 999999999),
    };

    public static Lodging LodgingAfter18 = new Lodging("Public Bench and Blanket from The Garbage Bin", 0, 0, -5, 75, -5);
    public static Lodging[] lodgingList = new Lodging[]{
            new Lodging("Rent a Cheap Flat in Dangerous Neighborhood For a Month", 500, 0, -2, 100, -2),
            new Lodging("Rent a Cheap Flat for a Month", 650, 0, -2, 100, -2),
            new Lodging("Rent a Flat for a Month", 800, 0, 0, 125, -1),
            new Lodging("Rent a House for a Month", 1500, 0, 2, 150, 3),
            new Lodging("Buy a Motel for a Week", 450, 3, 1, 125, 2),
            new Lodging("Buy a 1 Star Hotel for a Week", 500, 3, 2, 125, 3),
            new Lodging("Buy a 2 Star Hotel for a Week", 600, 3, 3, 150, 3),
            new Lodging("Buy a 3 Star Hotel for a Week", 750, 4, 4, 175, 4),
            new Lodging("Buy a 4 Star Hotel for a Week", 1000, 5, 5, 200, 5),
            new Lodging("Buy a 5 Star Hotel for a Week", 1500, 7, 7, 225, 8),
            new Lodging("Buy Flat", 40000, 0, 0, 125, -1),
            new Lodging("Buy Small House", 75000, 0, 1, 135, 2),
            new Lodging("Buy Big House", 150000, 0, 2, 175, 5),
            new Lodging("Buy Villa", 500000, 0, 8, 225, 10),
            new Lodging("Buy Very Exclusive Villa", 2500000, 0, 10, 275, 15),
            new Lodging("Buy Super Exclusive and Modern Villa", 50000000, 0, 12, 400, 25),
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

        ImageButton foodBuybutton = view.findViewById(R.id.foodBuyShop);
        foodBuybutton.setOnClickListener(this);

        ImageButton medicinesBuybutton = view.findViewById(R.id.medicinesBuyShop);
        medicinesBuybutton.setOnClickListener(this);

        ImageButton funBuybutton = view.findViewById(R.id.funBuyShop);
        funBuybutton.setOnClickListener(this);

        ImageButton lotteryBuybutton = view.findViewById(R.id.lotteryBuyShop);
        lotteryBuybutton.setOnClickListener(this);

        ImageButton houseBuybutton = view.findViewById(R.id.houseBuyShop);
        houseBuybutton.setOnClickListener(this);

        ImageButton transportBuybutton = view.findViewById(R.id.transportBuyShop);
        transportBuybutton.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.foodBuyShop:
                break;
            case R.id.medicinesBuyShop:
                break;
            case R.id.funBuyShop:
                break;
            case R.id.lotteryBuyShop:
                break;
            case R.id.houseBuyShop:
                break;
            case R.id.transportBuyShop:
                break;
        }

        Intent intent = new Intent(getActivity().getApplicationContext(), BuyActivity.class);
        intent.putExtra(getResources().getString(R.string.send_shop_click_id), view.getId());
        startActivity(intent);
    }

   /* public void onShopButtonClick(View view) {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();

        switch(view.getId())
        {

            case R.id.giveUpSchoolEducation:
                MainActivity.IsInSchoolNow = false;
                ft.replace(R.id.mainFragmentHolder, new FindJobFragment());

            case R.id.learnAtHomeEducation:
                ft.replace(R.id.mainFragmentHolder, new LearnInHomeFragment());

            default:
                break;

        }

        ft.commit();
    }*/

}

