package com.example.hubert.gameoflife.Shop;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.JsonReader;
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
import com.example.hubert.gameoflife.Utils.FunTypes;
import com.example.hubert.gameoflife.Utils.Lodging;
import com.example.hubert.gameoflife.Utils.SharedPreferencesDefaultValues;
import com.example.hubert.gameoflife.Utils.Transport;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
        ((TextView)(findViewById(R.id.money_buy))).setText("$ " + sharedPref.getInt(getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney));

        id = getIntent().getIntExtra(getResources().getString(R.string.send_shop_click_id), R.id.cardview_food);
        switch (id)
        {
            case R.id.cardview_food:
                for(int i = 0; i < ShopFragment.foodList.length; i++)
                    itemsNames.add(ShopFragment.foodList[i].getName());
                for(int i = 0; i < ShopFragment.foodList.length; i++)
                    itemsPrices.add("$" + ShopFragment.foodList[i].getPrice());
                ((TextView)(findViewById(R.id.itemToBuy_shop_buy))).setText("BUY FOOD");
                ((ProgressBar)(findViewById(R.id.progressBar_item_shop_buy))).setProgress(sharedPref.getInt(getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry) / 10);
                break;

            case R.id.cardview_medicines:
                for(int i = 0; i < ShopFragment.medicinesList.length; i++)
                    itemsNames.add(ShopFragment.medicinesList[i].getName());
                for(int i = 0; i < ShopFragment.medicinesList.length; i++)
                    itemsPrices.add("$" + ShopFragment.medicinesList[i].getPrice());
                ((TextView)(findViewById(R.id.itemToBuy_shop_buy))).setText("BUY MEDICINES");
                ((ProgressBar)(findViewById(R.id.progressBar_item_shop_buy))).setProgress(sharedPref.getInt(getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth) / 10);
                break;

            case R.id.cardview_fun:
                for(int i = 0; i < ShopFragment.funList.length; i++)
                    itemsNames.add(ShopFragment.funList[i].getName());
                for(int i = 0; i < ShopFragment.funList.length; i++)
                    itemsPrices.add("$" + ShopFragment.funList[i].getPrice());
                ((TextView)(findViewById(R.id.itemToBuy_shop_buy))).setText("BUY FUN ITEMS");
                ((ProgressBar)(findViewById(R.id.progressBar_item_shop_buy))).setProgress(sharedPref.getInt(getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness) / 10);
                break;

            case R.id.cardview_lottery:
                for(int i = 0; i < ShopFragment.lotteryList.length; i++)
                    itemsNames.add(ShopFragment.lotteryList[i].getName());
                for(int i = 0; i < ShopFragment.lotteryList.length; i++)
                    itemsPrices.add("$" + ShopFragment.lotteryList[i].getPrice());
                ((TextView)(findViewById(R.id.itemToBuy_shop_buy))).setText("BUY LOTTERIES");
                findViewById(R.id.progressBar_item_shop_buy).setVisibility(View.GONE);
                break;

            case R.id.cardview_house:
                for(int i = 0; i < ShopFragment.lodgingList.length; i++)
                    itemsNames.add(ShopFragment.lodgingList[i].getName());
                for(int i = 0; i < ShopFragment.lodgingList.length; i++)
                    itemsPrices.add("$" + ShopFragment.lodgingList[i].getPrice());
                ((TextView)(findViewById(R.id.itemToBuy_shop_buy))).setText("BUY HOUSES");
                findViewById(R.id.progressBar_item_shop_buy).setVisibility(View.GONE);
                break;

            case R.id.cardview_transport:
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
            case R.id.cardview_food:
                if(sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) >= ShopFragment.foodList[position].getPrice())
                {
                    editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) - ShopFragment.foodList[position].getPrice()));
                    editor.putInt(getString(R.string.saved_hungry_key), (sharedPref.getInt(getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry) + ShopFragment.foodList[position].getGivenFood()));
                    editor.putInt(getString(R.string.saved_health_key), (sharedPref.getInt(getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth) + ShopFragment.foodList[position].getGivenHealth()));
                    editor.putInt(getString(R.string.saved_energy_key), (sharedPref.getInt(getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy) + ShopFragment.foodList[position].getGivenEnergy()));
                    editor.putInt(getString(R.string.saved_happiness_key), (sharedPref.getInt(getString(R.string.saved_happiness_key), 750) + ShopFragment.foodList[position].getGivenHappiness()));
                }
                else
                    Toast.makeText(this, "Unfortunately, you don't have enough money to buy this thing.", Toast.LENGTH_LONG).show();
                ((ProgressBar)(findViewById(R.id.progressBar_item_shop_buy))).setProgress(sharedPref.getInt(getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry) / 10);
                ((TextView)(findViewById(R.id.money_buy))).setText("$ " + sharedPref.getInt(getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney));
                break;

            case R.id.cardview_medicines:
                if(sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) >= ShopFragment.medicinesList[position].getPrice())
                {
                    editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) - ShopFragment.medicinesList[position].getPrice()));
                    editor.putInt(getString(R.string.saved_health_key), (sharedPref.getInt(getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth) + ShopFragment.medicinesList[position].getGivenHealth()));
                }
                else
                    Toast.makeText(this, "Unfortunately, you don't have enough money to buy this thing.", Toast.LENGTH_LONG).show();
                ((ProgressBar)(findViewById(R.id.progressBar_item_shop_buy))).setProgress(sharedPref.getInt(getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth) / 10);
                ((TextView)(findViewById(R.id.money_buy))).setText("$ " + sharedPref.getInt(getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney));
                break;

            case R.id.cardview_fun:
                String computerName = null;
                String phoneName = null;
                String tvName = null;
                int computerPrice = 0;
                int phonePrice = 0;
                int tvPrice = 0;

                String jsonComputer = sharedPref.getString(getResources().getString(R.string.saved_my_computer_key), SharedPreferencesDefaultValues.DefaultMyComputer);
                String jsonPhone = sharedPref.getString(getResources().getString(R.string.saved_my_phone_key), SharedPreferencesDefaultValues.DefaultMyPhone);
                String jsonTv = sharedPref.getString(getResources().getString(R.string.saved_my_tv_key), SharedPreferencesDefaultValues.DefaultMyTv);
                Fun funComputer = gson.fromJson(jsonComputer, Fun.class);
                Fun funPhone = gson.fromJson(jsonPhone, Fun.class);
                Fun funTv = gson.fromJson(jsonTv, Fun.class);

                if(funComputer != null)
                {
                    computerName = funComputer.getName();
                    computerPrice = funComputer.getPrice();
                }
                if(funPhone != null)
                {
                    phoneName = funPhone.getName();
                    phonePrice = funPhone.getPrice();
                }
                if(funTv != null)
                {
                    tvName = funTv.getName();
                    tvPrice = funTv.getPrice();
                }

                if("Exit".equals(ShopFragment.funList[position].getType()))
                    buyFun(position);

                else if("Computer".equals(ShopFragment.funList[position].getType()) && funComputer == null)
                    buyFun(position);
                else if("Computer".equals(ShopFragment.funList[position].getType()))
                    alertDialogSellItem(computerName, computerPrice, ShopFragment.funList[position].getPrice(), ShopFragment.funList[position].getName(), "fun", position);

                else if(ShopFragment.funList[position].getType().equals("Phone") && funPhone == null)
                    buyFun(position);
                else if ("Phone".equals(ShopFragment.funList[position].getType()))
                    alertDialogSellItem(phoneName, phonePrice, ShopFragment.funList[position].getPrice(), ShopFragment.funList[position].getName(), "fun", position);

                else if(ShopFragment.funList[position].getType().equals("TV") && funTv == null)
                    buyFun(position);
                else if ("TV".equals(ShopFragment.funList[position].getType()))
                    alertDialogSellItem(tvName, tvPrice, ShopFragment.funList[position].getPrice(), ShopFragment.funList[position].getName(), "fun", position);

                ((ProgressBar)(findViewById(R.id.progressBar_item_shop_buy))).setProgress(sharedPref.getInt(getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness) / 10);
                ((TextView)(findViewById(R.id.money_buy))).setText("$ " + sharedPref.getInt(getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney));
                break;

            case R.id.cardview_lottery:
                if(sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) >= ShopFragment.lotteryList[position].getPrice())
                {
                    try {
                        JSONArray jsonArray = new JSONArray(sharedPref.getString(getResources().getString(R.string.saved_owned_lotteries_key), SharedPreferencesDefaultValues.DefaultOwnedLotteries));
                        jsonArray.put(ShopFragment.lotteryList[position]);
                        editor.putString(getString(R.string.saved_owned_lotteries_key), jsonArray.toString());
                    } catch(JSONException e)
                    { }

                    editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) - ShopFragment.lotteryList[position].getPrice()));
                }
                else
                    Toast.makeText(view.getContext(), "Unfortunately, you don't have enough money to buy this thing.", Toast.LENGTH_LONG).show();
                ((TextView)(findViewById(R.id.money_buy))).setText("$ " + sharedPref.getInt(getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney));
                break;

            case R.id.cardview_house:
                String lodgingName = null;
                int lodgingPrice = 0;
                json = sharedPref.getString(getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodging);
                gson.fromJson(json, Lodging.class);
                gson.newBuilder().setLenient().create();

                Lodging lodging = gson.fromJson(json, Lodging.class);
                if(lodging != null)
                {
                    lodgingName = lodging.getName();
                    lodgingPrice = lodging.getPrice();
                }

                if(18 >= sharedPref.getInt(getResources().getString(R.string.saved_age_years_key), SharedPreferencesDefaultValues.DefaultAgeYears))
                    Toast.makeText(view.getContext(), "You can't buy new house when you are under 18.", Toast.LENGTH_LONG).show();
                else if(sharedPref.getString(getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodging) == null || lodgingName.equals("Foots"))
                {
                    if(sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) >= ShopFragment.lodgingList[position].getPrice())
                    {
                        editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) - ShopFragment.lodgingList[position].getPrice()));
                        json = gson.toJson(ShopFragment.lodgingList[position]);
                        editor.putString(getResources().getString(R.string.saved_my_lodging_key), json);
                    }
                    else
                        Toast.makeText(view.getContext(), "Unfortunately, you don't have enough money to buy this thing.", Toast.LENGTH_LONG).show();
                } else
                    alertDialogSellItem(lodgingName, lodgingPrice, ShopFragment.lodgingList[position].getPrice(), ShopFragment.lodgingList[position].getName(), "lodging", position);
                ((TextView)(findViewById(R.id.money_buy))).setText("$ " + sharedPref.getInt(getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney));
                break;

            case R.id.cardview_transport:
                String transportName = null;
                int transportPrice = 0;
                json = sharedPref.getString(getResources().getString(R.string.saved_my_transport_key), SharedPreferencesDefaultValues.DefaultMyTransport);
                Transport transport = gson.fromJson(json, Transport.class);
                if(transport != null)
                {
                    transportName = transport.getName();
                    transportPrice = transport.getPrice();
                }

                if(sharedPref.getString(getResources().getString(R.string.saved_my_transport_key), SharedPreferencesDefaultValues.DefaultMyTransport) == null || transportName.equals("Foots"))
                {
                    if(sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) >= ShopFragment.transportList[position].getPrice())
                    {
                        editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) - ShopFragment.transportList[position].getPrice()));
                        json = gson.toJson(ShopFragment.transportList[position]);
                        editor.putString(getResources().getString(R.string.saved_my_transport_key), json);
                    }
                    else
                        Toast.makeText(view.getContext(), "Unfortunately, you don't have enough money to buy this thing.", Toast.LENGTH_LONG).show();
                } else
                    alertDialogSellItem(transportName, transportPrice, ShopFragment.transportList[position].getPrice(), ShopFragment.transportList[position].getName(), "transport", position);
                ((TextView)(findViewById(R.id.money_buy))).setText("$ " + sharedPref.getInt(getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney));
                break;

            default:
                break;
        }
        if(sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry) > 1000)
            editor.putInt(getString(R.string.saved_hungry_key), 1000);
        if(sharedPref.getInt(getResources().getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth) > 1000)
            editor.putInt(getString(R.string.saved_health_key), 1000);
        if(sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy) > 1000)
            editor.putInt(getString(R.string.saved_energy_key), 1000);
        if(sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness) > 1000)
            editor.putInt(getString(R.string.saved_happiness_key), 1000);

        editor.apply(); }

    private void alertDialogSellItem(String boughtItemName, final int boughtItemPrice, final int itemPrice, String itemName, final String itemType, final int position)
    {
        final SharedPreferences sharedPref = getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("You already have purchased '" + boughtItemName + "'")
                //.setIcon(R.drawable.ic_launcher)
                .setMessage("'Do you want to sell it for " + (boughtItemPrice / 2) + " and " + itemName + "?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                         dialoginterface.cancel();
                     }})
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) + (boughtItemPrice / 2)));
                        editor.apply();

                        if(sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) >= itemPrice)
                        {
                            editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) - itemPrice));
                            Gson gson = new Gson();
                            String json = null;
                            switch (itemType)
                            {
                                case "fun":
                                    buyFun(position);
                                    break;
                                case "lodging":
                                    json = gson.toJson(ShopFragment.lodgingList[position]);
                                    editor.putString(getResources().getString(R.string.saved_my_lodging_key), json);
                                    break;
                                case "transport":
                                    json = gson.toJson(ShopFragment.transportList[position]);
                                    editor.putString(getResources().getString(R.string.saved_my_transport_key), json);
                                    break;
                            }
                        }
                        else
                        {
                            Toast.makeText(view.getContext(), "Unfortunately, you don't have enough money to buy this thing.", Toast.LENGTH_LONG).show();
                            editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) - (boughtItemPrice / 2)));
                        }
                        editor.apply();
                    }
                }).show();
    }

    private void buyFun(int position)
    {
        SharedPreferences sharedPref = getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        if (sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) >= ShopFragment.funList[position].getPrice())
        {
            editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) - ShopFragment.funList[position].getPrice()));
            Gson gson = new Gson();
            String json = gson.toJson(ShopFragment.funList[position]);
            switch (ShopFragment.funList[position].getType()) {
                // TODO: create enum with those types
                case "Exit":
                    editor.putInt(getString(R.string.saved_happiness_key), (sharedPref.getInt(getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness) + ShopFragment.funList[position].getGivenFun()));
                    break;
                case "TV":
                    editor.putString(getResources().getString(R.string.saved_my_tv_key), json);
                    break;
                case "Computer":
                    editor.putString(getResources().getString(R.string.saved_my_computer_key), json);
                    break;
                case "Phone":
                    editor.putString(getResources().getString(R.string.saved_my_phone_key), json);
            }
        } else
            Toast.makeText(this, "Unfortunately, you don't have enough money to buy this thing.", Toast.LENGTH_LONG).show();
        editor.apply();
    }
}
