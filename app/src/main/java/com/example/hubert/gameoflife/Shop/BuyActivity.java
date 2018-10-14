package com.example.hubert.gameoflife.Shop;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hubert.gameoflife.House.ComputerActivity;
import com.example.hubert.gameoflife.MainActivity;
import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.House.Fun;
import com.example.hubert.gameoflife.House.Lodging;
import com.example.hubert.gameoflife.Utils.SharedPreferencesDefaultValues;
import com.example.hubert.gameoflife.House.Transport;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Random;

import static com.example.hubert.gameoflife.Utils.Dialogs.showAlertDialog;

public class BuyActivity extends AppCompatActivity implements RecyclerViewShopBuyAdapter.ItemClickListener {

    private static final int PAGE_NUMBER = 2;

    private int id;
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        setTitle(R.string.title_shop);
        view = new View(this);

        ArrayList<String> itemsNames = new ArrayList<>();
        ArrayList<String> itemsPrices = new ArrayList<>();
        Lodging[] lodgingObjects = null;

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

            case R.id.buyLotteries:
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
                lodgingObjects = ShopFragment.lodgingList;
                break;

            case R.id.cardview_transport:
                for(int i = 0; i < ShopFragment.transportList.length; i++)
                    itemsNames.add(ShopFragment.transportList[i].getName());
                for(int i = 0; i < ShopFragment.transportList.length; i++)
                    itemsPrices.add("$" + ShopFragment.transportList[i].getPrice());
                ((TextView)(findViewById(R.id.itemToBuy_shop_buy))).setText("BUY TRANSPORT");
                findViewById(R.id.progressBar_item_shop_buy).setVisibility(View.GONE);
                break;

            default:
                break;
        }

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewShopBuy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewShopBuyAdapter(this, itemsNames, itemsPrices, lodgingObjects);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
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
                break;

            case R.id.buyLotteries:
                if(sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) >= ShopFragment.lotteryList[position].getPrice())
                {
                    editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) - ShopFragment.lotteryList[position].getPrice()));

                    Random random = new Random();
                    if(random.nextInt(ShopFragment.lotteryList[position].getChanceToWin()) == 1)
                    {
                        editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) + ShopFragment.lotteryList[position].getPrize()));
                        Toast.makeText(view.getContext(), "Wow! You won " + ShopFragment.lotteryList[position].getPrize() + "$ in lottery!", Toast.LENGTH_LONG).show();
                        editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) + ShopFragment.lotteryList[position].getPrize()));
                    }
                    else
                        Toast.makeText(view.getContext(), "Unfortunately, you didn't win this lottery", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(view.getContext(), "Unfortunately, you don't have enough money to buy this thing.", Toast.LENGTH_LONG).show();
                break;

            case R.id.cardview_house:
                json = sharedPref.getString(getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodging);
                //TODO: zobaczy czy dziala bez tego
                gson.fromJson(json, Lodging.class);
                gson.newBuilder().setLenient().create();

                Lodging lodging = gson.fromJson(json, Lodging.class);


                if(sharedPref.getString(getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodging) == null || json.equals(SharedPreferencesDefaultValues.DefaultMyLodging) || json.equals(SharedPreferencesDefaultValues.DefaultMyLodgingAfter18))
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
                    //TODO: nie dostawać kasy za kupienie nowego domu (jeżeli to jest wypożyczony), tylko np. "You still have rented [name]. Are you sure
                    // you want to buy this for [price]?
                    /*if("rent".equals(lodging.getType()))
                        ;
                    else if(json.equals(SharedPreferencesDefaultValues.DefaultMyLodging))
                        ;
                    else*/
                        alertDialogSellItem(lodging.getName(), lodging.getPrice(), ShopFragment.lodgingList[position].getPrice(), ShopFragment.lodgingList[position].getName(), "lodging", position);

                break;

            case R.id.cardview_transport:
                json = sharedPref.getString(getResources().getString(R.string.saved_my_transport_key), SharedPreferencesDefaultValues.DefaultMyTransport);
                Transport transport = gson.fromJson(json, Transport.class);

                double transportPriceDouble = ShopFragment.transportList[position].getPrice();
                if("Rent for a month".equals(String.valueOf(((Spinner)view.findViewById(R.id.buy_method_spinner)).getSelectedItem())))
                    transportPriceDouble *= 4.75;
                else if("Buy".equals(String.valueOf(((Spinner)view.findViewById(R.id.buy_method_spinner)).getSelectedItem())))
                    transportPriceDouble *= 25.0;
                int transportPrice = (int)Math.round(transportPriceDouble);

                if(sharedPref.getString(getResources().getString(R.string.saved_my_transport_key), SharedPreferencesDefaultValues.DefaultMyTransport) == null || json.equals(SharedPreferencesDefaultValues.DefaultMyTransport))
                {
                    if(sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) >= transportPrice)
                    {
                        editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) - transportPrice));
                        json = gson.toJson(ShopFragment.transportList[position]);
                        editor.putString(getResources().getString(R.string.saved_my_transport_key), json);
                    }
                    else
                        Toast.makeText(view.getContext(), "Unfortunately, you don't have enough money to buy this thing.", Toast.LENGTH_LONG).show();
                } else
                    alertDialogSellItem(transport.getName(), transport.getPrice(), transportPrice, ShopFragment.transportList[position].getName(), "transport", position);
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

        editor.apply();

        ((TextView)(findViewById(R.id.money_buy))).setText("$ " + sharedPref.getInt(getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney));
    }

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
                            String json;
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



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(BuyActivity.this, MainActivity.class);
        intent.putExtra(MainActivity.INTENT_PAGE, PAGE_NUMBER);
        startActivity(intent);
        finish();
    }
}
