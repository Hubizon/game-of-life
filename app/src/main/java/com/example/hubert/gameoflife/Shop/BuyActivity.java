package com.example.hubert.gameoflife.Shop;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hubert.gameoflife.MainActivity;
import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.Utils.Fun;
import com.example.hubert.gameoflife.Utils.Lodging;
import com.example.hubert.gameoflife.Utils.Transport;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BuyActivity extends AppCompatActivity implements RecyclerViewShopBuyAdapter.ItemClickListener{

    private int id;
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        view = new View(this);

        ArrayList<String> itemsNames = new ArrayList<>();
        ArrayList<String> itemsPrices = new ArrayList<>();

        ArrayList<String> itemsNamesOfficeJob = new ArrayList<>();
        itemsNamesOfficeJob.add("Lawyer");
        itemsNamesOfficeJob.add("Reporter");
        itemsNamesOfficeJob.add("Engraver");
        itemsNamesOfficeJob.add("Programmer");
        itemsNamesOfficeJob.add("Businessman");

        ArrayList<String> itemsRewardOfficeJob = new ArrayList<>();
        itemsRewardOfficeJob.add("$ 10000");
        itemsRewardOfficeJob.add("$ 2500");
        itemsRewardOfficeJob.add("$ 9500");
        itemsRewardOfficeJob.add("$ 7000");
        itemsRewardOfficeJob.add("$ 11000");

        ArrayList<String> itemsNamesCriminalJob = new ArrayList<>();
        itemsNamesCriminalJob.add("Pickpocket");
        itemsNamesCriminalJob.add("Thief");
        itemsNamesCriminalJob.add("Drug dealer");
        itemsNamesCriminalJob.add("Terrorist");
        itemsNamesCriminalJob.add("Kidnap kids");
        itemsNamesCriminalJob.add("Mafia member");
        itemsNamesCriminalJob.add("Assasin");

        ArrayList<String> itemsRewardCriminalJob = new ArrayList<>();
        itemsRewardCriminalJob.add("$ 6500");
        itemsRewardCriminalJob.add("$ 9000");
        itemsRewardCriminalJob.add("$ 12000");
        itemsRewardCriminalJob.add("$ 10000");
        itemsRewardCriminalJob.add("$ 15000");
        itemsRewardCriminalJob.add("$ 10000");
        itemsRewardCriminalJob.add("$ 20000");

        ArrayList<String> itemsNamesArtsJob = new ArrayList<>();
        itemsNamesArtsJob.add("Writer");
        itemsNamesArtsJob.add("Painter");

        ArrayList<String> itemsRewardArtsJob = new ArrayList<>();
        itemsRewardArtsJob.add("$ 4000");
        itemsRewardArtsJob.add("$ 3750");

        ArrayList<String> itemsNamesMediaJob = new ArrayList<>();
        itemsNamesMediaJob.add("YouTuber");
        itemsNamesMediaJob.add("Streamer");
        itemsNamesMediaJob.add("Blogger");

        ArrayList<String> itemsRewardMediaJob = new ArrayList<>();
        itemsRewardMediaJob.add("$ 30000");
        itemsRewardMediaJob.add("$ 15000");
        itemsRewardMediaJob.add("$ 20000");

        ArrayList<String> itemsNamesOutsideJob = new ArrayList<>();
        itemsNamesOutsideJob.add("Footballer");
        itemsNamesOutsideJob.add("Basketball Player");
        itemsNamesOutsideJob.add("Swimmer");
        itemsNamesOutsideJob.add("Runner");
        itemsNamesOutsideJob.add("Cyclist");
        itemsNamesOutsideJob.add("Geologist");
        itemsNamesOutsideJob.add("Dustman");

        ArrayList<String> itemsRewardOutsideJob = new ArrayList<>();
        itemsRewardOutsideJob.add("$ 2000");
        itemsRewardOutsideJob.add("$ 4000");
        itemsRewardOutsideJob.add("$ 3000");
        itemsRewardOutsideJob.add("$ 1500");
        itemsRewardOutsideJob.add("$ 3500");
        itemsRewardOutsideJob.add("$ 8000");
        itemsRewardOutsideJob.add("$ 2500");

        ArrayList<String> itemsNamesOtherJob = new ArrayList<>();
        itemsNamesOtherJob.add("Doctor");
        itemsNamesOtherJob.add("Teacher");
        itemsNamesOtherJob.add("Scientist");
        itemsNamesOtherJob.add("Salesperson");

        ArrayList<String> itemsRewardOtherJob = new ArrayList<>();
        itemsRewardOtherJob.add("$ 11500");
        itemsRewardOtherJob.add("$ 2250");
        itemsRewardOtherJob.add("$ 8500");
        itemsRewardOtherJob.add("$ 3000");

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewShopBuy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewShopBuyAdapter(this, itemsNames, itemsPrices);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        SharedPreferences sharedPref = getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        ((TextView)(findViewById(R.id.money_buy))).setText("$ " + sharedPref.getInt(getString(R.string.saved_character_money_key), 750));

        id = getIntent().getIntExtra(getResources().getString(R.string.send_shop_click_id), R.id.foodBuyShop);
        switch (id)
        {
            case R.id.foodBuyShop:
                for(int i = 0; i < ShopFragment.foodList.length; i++)
                    itemsNames.add(ShopFragment.foodList[i].getName());
                for(int i = 0; i < ShopFragment.foodList.length; i++)
                    itemsPrices.add("$" + ShopFragment.foodList[i].getPrice());
                ((TextView)(findViewById(R.id.itemToBuy_shop_buy))).setText("BUY FOOD");
                ((ProgressBar)(findViewById(R.id.progressBar_item_shop_buy))).setProgress(sharedPref.getInt(getString(R.string.saved_hungry_key), 750) / 10);
                break;

            case R.id.medicinesBuyShop:
                for(int i = 0; i < ShopFragment.medicinesList.length; i++)
                    itemsNames.add(ShopFragment.medicinesList[i].getName());
                for(int i = 0; i < ShopFragment.medicinesList.length; i++)
                    itemsPrices.add("$" + ShopFragment.medicinesList[i].getPrice());
                ((TextView)(findViewById(R.id.itemToBuy_shop_buy))).setText("BUY MEDICINES");
                ((ProgressBar)(findViewById(R.id.progressBar_item_shop_buy))).setProgress(sharedPref.getInt(getString(R.string.saved_health_key), 750) / 10);
                break;

            case R.id.funBuyShop:
                for(int i = 0; i < ShopFragment.funList.length; i++)
                    itemsNames.add(ShopFragment.funList[i].getName());
                for(int i = 0; i < ShopFragment.funList.length; i++)
                    itemsPrices.add("$" + ShopFragment.funList[i].getPrice());
                ((TextView)(findViewById(R.id.itemToBuy_shop_buy))).setText("BUY FUN ITEMS");
                ((ProgressBar)(findViewById(R.id.progressBar_item_shop_buy))).setProgress(sharedPref.getInt(getString(R.string.saved_happiness_key), 750) / 10);
                break;

            case R.id.lotteryBuyShop:
                for(int i = 0; i < ShopFragment.lotteryList.length; i++)
                    itemsNames.add(ShopFragment.lotteryList[i].getName());
                for(int i = 0; i < ShopFragment.lotteryList.length; i++)
                    itemsPrices.add("$" + ShopFragment.lotteryList[i].getPrice());
                ((TextView)(findViewById(R.id.itemToBuy_shop_buy))).setText("BUY LOTTERIES");
                findViewById(R.id.progressBar_item_shop_buy).setVisibility(View.GONE);
                break;

            case R.id.houseBuyShop:
                for(int i = 0; i < ShopFragment.lodgingList.length; i++)
                    itemsNames.add(ShopFragment.lodgingList[i].getName());
                for(int i = 0; i < ShopFragment.lodgingList.length; i++)
                    itemsPrices.add("$" + ShopFragment.lodgingList[i].getPrice());
                ((TextView)(findViewById(R.id.itemToBuy_shop_buy))).setText("BUY HOUSES");
                findViewById(R.id.progressBar_item_shop_buy).setVisibility(View.GONE);
                break;

            case R.id.transportBuyShop:
                for(int i = 0; i < ShopFragment.transportList.length; i++)
                    itemsNames.add(ShopFragment.transportList[i].getName());
                for(int i = 0; i < ShopFragment.transportList.length; i++)
                    itemsPrices.add("$" + ShopFragment.transportList[i].getPrice());
                ((TextView)(findViewById(R.id.itemToBuy_shop_buy))).setText("BUY TRANSPORT");
                findViewById(R.id.progressBar_item_shop_buy).setVisibility(View.GONE);
                break;

            case R.id.officeWork:
                itemsNames = itemsNamesOfficeJob;
                itemsPrices = itemsRewardOfficeJob;
                ((TextView)(findViewById(R.id.itemToBuy_shop_buy))).setText("OFFICE WORK");
                findViewById(R.id.progressBar_item_shop_buy).setVisibility(View.GONE);
                break;

            case R.id.criminalWork:
                itemsNames = itemsNamesCriminalJob;
                itemsPrices = itemsRewardCriminalJob;
                ((TextView)(findViewById(R.id.itemToBuy_shop_buy))).setText("CRIMINAL WORK");
                findViewById(R.id.progressBar_item_shop_buy).setVisibility(View.GONE);
                break;

            case R.id.artsWork:
                itemsNames = itemsNamesArtsJob;
                itemsPrices = itemsRewardArtsJob;
                ((TextView)(findViewById(R.id.itemToBuy_shop_buy))).setText("ARTS WORK");
                findViewById(R.id.progressBar_item_shop_buy).setVisibility(View.GONE);
                break;

            case R.id.outsideWork:
                itemsNames = itemsNamesMediaJob;
                itemsPrices = itemsRewardMediaJob;
                ((TextView)(findViewById(R.id.itemToBuy_shop_buy))).setText("OUTSIDE WORK");
                findViewById(R.id.progressBar_item_shop_buy).setVisibility(View.GONE);
                break;

            case R.id.otherWork:
                itemsNames = itemsNamesOutsideJob;
                itemsPrices = itemsRewardOutsideJob;
                ((TextView)(findViewById(R.id.itemToBuy_shop_buy))).setText("OTHER WORK");
                findViewById(R.id.progressBar_item_shop_buy).setVisibility(View.GONE);
                break;

//            case R.id.educationWork:
//                itemsNames = itemsNamesOtherJob;
//                itemsPrices = itemsRewardOtherJob;
//                break;

            default:
                break;
        }
    }

    RecyclerViewShopBuyAdapter adapter;
    
    @Override
    public void onItemClick(View view, int position) {

        SharedPreferences sharedPref = getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        Gson gson = new Gson();
        String json;

        switch (id)
        {
            case R.id.foodBuyShop:
                if(sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), 750) >= ShopFragment.foodList[position].getPrice())
                {
                    editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), 750)) - ShopFragment.foodList[position].getPrice()));
                    editor.putInt(getString(R.string.saved_hungry_key), (sharedPref.getInt(getString(R.string.saved_hungry_key), 750) + ShopFragment.foodList[position].getGivenFood()));
                    editor.putInt(getString(R.string.saved_health_key), (sharedPref.getInt(getString(R.string.saved_hungry_key), 750) + ShopFragment.foodList[position].getGivenHealth()));
                    editor.putInt(getString(R.string.saved_energy_key), (sharedPref.getInt(getString(R.string.saved_hungry_key), 750) + ShopFragment.foodList[position].getGivenEnergy()));
                    editor.putInt(getString(R.string.saved_happiness_key), (sharedPref.getInt(getString(R.string.saved_hungry_key), 750) + ShopFragment.foodList[position].getGivenHappiness()));
                }
                else
                    Toast.makeText(this, "Unfortunately, you don't have enough money to buy this thing.", Toast.LENGTH_LONG).show();
                ((ProgressBar)(findViewById(R.id.progressBar_item_shop_buy))).setProgress(sharedPref.getInt(getString(R.string.saved_hungry_key), 750) / 10);
                break;

            case R.id.medicinesBuyShop:
                if(sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), 750) >= ShopFragment.medicinesList[position].getPrice())
                {
                    editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), 750)) - ShopFragment.medicinesList[position].getPrice()));
                    editor.putInt(getString(R.string.saved_health_key), (sharedPref.getInt(getString(R.string.saved_hungry_key), 750) + ShopFragment.medicinesList[position].getGivenHealth()));
                }
                else
                    Toast.makeText(this, "Unfortunately, you don't have enough money to buy this thing.", Toast.LENGTH_LONG).show();
                ((ProgressBar)(findViewById(R.id.progressBar_item_shop_buy))).setProgress(sharedPref.getInt(getString(R.string.saved_health_key), 750) / 10);
                break;

            case R.id.funBuyShop:
                if("Computer".equals(ShopFragment.funList[position].getType()) && sharedPref.getString(getResources().getString(R.string.saved_my_computer_key), null) == null)
                    buyFun(position);
                else if("Computer".equals(ShopFragment.funList[position].getType()))
                    if(alertDialogSellItem(sharedPref.getString(getResources().getString(R.string.saved_my_computer_key), null), ShopFragment.funList[position].getPrice(), ShopFragment.funList[position].getName()))
                        buyFun(position);

                else if(ShopFragment.funList[position].getType().equals("Phone") && sharedPref.getString(getResources().getString(R.string.saved_my_phone_key), null) == null)
                    buyFun(position);
                else if ("Phone".equals(ShopFragment.funList[position].getType()))
                    if(alertDialogSellItem(sharedPref.getString(getResources().getString(R.string.saved_my_phone_key), null), ShopFragment.funList[position].getPrice(), ShopFragment.funList[position].getName()))
                        buyFun(position);

                else if(ShopFragment.funList[position].getType().equals("TV") && sharedPref.getString(getResources().getString(R.string.saved_my_tv_key), null) == null)
                    buyFun(position);
                else if ("TV".equals(ShopFragment.funList[position].getType()))
                    if(alertDialogSellItem(sharedPref.getString(getResources().getString(R.string.saved_my_tv_key), null), ShopFragment.funList[position].getPrice(), ShopFragment.funList[position].getName()))
                        buyFun(position);

                ((ProgressBar)(findViewById(R.id.progressBar_item_shop_buy))).setProgress(sharedPref.getInt(getString(R.string.saved_happiness_key), 750) / 10);
                break;

            case R.id.lotteryBuyShop:
                if(sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), 750) >= ShopFragment.lotteryList[position].getPrice())
                {
                    try {
                        json = gson.toJson(new ArrayList<>());
                        JSONArray jsonArray = new JSONArray(sharedPref.getString(getResources().getString(R.string.saved_owned_lotteries_key), json));
                        jsonArray.put(ShopFragment.lotteryList[position]);
                        editor.putString(getString(R.string.saved_owned_lotteries_key), jsonArray.toString());
                    } catch(JSONException e)
                    { }

                    editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), 750)) - ShopFragment.lotteryList[position].getPrice()));
                }
                else
                    Toast.makeText(view.getContext(), "Unfortunately, you don't have enough money to buy this thing.", Toast.LENGTH_LONG).show();
                break;

            case R.id.houseBuyShop:
                String LodgingName = "";
              //  try {
                    json = sharedPref.getString(getResources().getString(R.string.saved_my_lodging_key), "");
                    Lodging lodging = gson.fromJson(json, Lodging.class);
                    LodgingName = lodging.getName();

                    /*Gson gson = new Gson();
                    String toJson = gson.toJson(new Lodging("Parents House", 0, 10, 5, 125, 5));
                    String json = gson.toJson(sharedPref.getString(getResources().getString(R.string.saved_my_lodging_key), toJson));
                    JSONObject jsonObject = new JSONObject(json);
                    jsonObject.accumulate();
                    LodgingName = jsonObject.getString("name");
                } catch (JSONException t) {
                    Log.e("My App", "Error!");
                }*/
                if(sharedPref.getString(getResources().getString(R.string.saved_my_lodging_key), null) == null || !LodgingName.equals("Parents House"))
                {
                    if(sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), 750) >= ShopFragment.lodgingList[position].getPrice())
                    {
                        editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), 750)) - ShopFragment.lodgingList[position].getPrice()));
                        editor.putString(getResources().getString(R.string.saved_my_lodging_key), sharedPref.getString(getResources().getString(R.string.saved_my_lodging_key), null) + (ShopFragment.lodgingList[position]));
                    }
                    else
                        Toast.makeText(view.getContext(), "Unfortunately, you don't have enough money to buy this thing.", Toast.LENGTH_LONG).show();
                } else
                if(alertDialogSellItem(sharedPref.getString(getResources().getString(R.string.saved_my_lodging_key), null), ShopFragment.funList[position].getPrice(), ShopFragment.funList[position].getName()))
                    editor.putString(getResources().getString(R.string.saved_my_lodging_key), sharedPref.getString(getResources().getString(R.string.saved_my_lodging_key), null) + (ShopFragment.lodgingList[position]));
                break;

            case R.id.transportBuyShop:
                json = sharedPref.getString(getResources().getString(R.string.saved_my_transport_key), "");
                Transport transport = gson.fromJson(json, Transport.class);
                String TransportName = transport.getName();
                if(sharedPref.getString(getResources().getString(R.string.saved_my_transport_key), null) == null || TransportName.equals("Foots"))
                {
                    if(sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), 750) >= ShopFragment.transportList[position].getPrice())
                    {
                        editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), 750)) - ShopFragment.transportList[position].getPrice()));
                        editor.putString(getResources().getString(R.string.saved_my_transport_key), sharedPref.getString(getResources().getString(R.string.saved_my_transport_key), null) + (ShopFragment.lodgingList[position]));
                    }
                    else
                        Toast.makeText(view.getContext(), "Unfortunately, you don't have enough money to buy this thing.", Toast.LENGTH_LONG).show();
                } else
                if(alertDialogSellItem(sharedPref.getString(getResources().getString(R.string.saved_my_lodging_key), null), ShopFragment.funList[position].getPrice(), ShopFragment.funList[position].getName()))
                    editor.putString(getResources().getString(R.string.saved_my_transport_key), sharedPref.getString(getResources().getString(R.string.saved_my_transport_key), null) + (ShopFragment.lodgingList[position]));
                break;

            default:
                break;
        }
        if(sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), 750) > 1000)
            editor.putInt(getString(R.string.saved_hungry_key), 1000);
        if(sharedPref.getInt(getResources().getString(R.string.saved_health_key), 750) > 1000)
            editor.putInt(getString(R.string.saved_health_key), 1000);
        if(sharedPref.getInt(getResources().getString(R.string.saved_energy_key), 750) > 1000)
            editor.putInt(getString(R.string.saved_energy_key), 1000);
        if(sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), 750) > 1000)
            editor.putInt(getString(R.string.saved_happiness_key), 1000);

        editor.apply();
        ((TextView)(findViewById(R.id.money_buy))).setText("$ " + sharedPref.getInt(getString(R.string.saved_character_money_key), 550));
    }

    private boolean alertDialogSellItem(String boughtItemName, final int itemPrice, String itemName)
    {
        final SharedPreferences sharedPref = getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();
        final boolean[] canIBuyIt = {false};

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("You already have purchased '" + boughtItemName)
                //.setIcon(R.drawable.ic_launcher)
                .setMessage("'Do you want to sell it for " + (itemPrice / 2) + " and buy " + itemName + "?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                         dialoginterface.cancel();
                     }})
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), 750)) + (itemPrice / 2)));

                        if(sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), 750) >= itemPrice)
                        {
                            editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), 750)) - itemPrice));
                            canIBuyIt[0] = true;
                        }
                        else
                        {
                            Toast.makeText(view.getContext(), "Unfortunately, you don't have enough money to buy this thing.", Toast.LENGTH_LONG).show();
                            editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), 750)) - (itemPrice / 2)));
                        }
                    }
                }).show();
        return canIBuyIt[0];
    }

    private boolean buyFun(int position)
    {
        SharedPreferences sharedPref = getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        if (sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), 750) >= ShopFragment.funList[position].getPrice())
        {
            editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), 750)) - ShopFragment.foodList[position].getPrice()));
            switch (ShopFragment.funList[position].getType()) {
                case "Exit":
                    editor.putInt(getString(R.string.saved_happiness_key), (sharedPref.getInt(getString(R.string.saved_hungry_key), 750) + ShopFragment.funList[position].getGivenFun()));
                    break;
                case "Tv":
                    editor.putString(getResources().getString(R.string.saved_my_tv_key), sharedPref.getString(getResources().getString(R.string.saved_my_tv_key), null) + (ShopFragment.funList[position]));
                    break;
                case "Computer":
                    editor.putString(getResources().getString(R.string.saved_my_computer_key), sharedPref.getString(getResources().getString(R.string.saved_my_computer_key), null) + (ShopFragment.funList[position]));
                    break;
                case "Phone":
                    editor.putString(getResources().getString(R.string.saved_my_phone_key), sharedPref.getString(getResources().getString(R.string.saved_my_phone_key), null) + (ShopFragment.funList[position]));                    break;
            }
            return true;
        } else
            Toast.makeText(this, "Unfortunately, you don't have enough money to buy this thing.", Toast.LENGTH_LONG).show();

        return false;
    }
}
