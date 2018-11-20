package com.example.hubert.gameoflife.Shop;

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
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hubert.gameoflife.MainActivity;
import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.House.Fun;
import com.example.hubert.gameoflife.House.Lodging;
import com.example.hubert.gameoflife.Utils.SharedPreferencesDefaultValues;
import com.example.hubert.gameoflife.House.Transport;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

import static com.example.hubert.gameoflife.MainActivity.userSharedPref;
import static com.example.hubert.gameoflife.Utils.Arrays.foodList;
import static com.example.hubert.gameoflife.Utils.Arrays.funList;
import static com.example.hubert.gameoflife.Utils.Arrays.lodgingList;
import static com.example.hubert.gameoflife.Utils.Arrays.lotteryList;
import static com.example.hubert.gameoflife.Utils.Arrays.medicinesList;
import static com.example.hubert.gameoflife.Utils.Arrays.transportList;
import static com.example.hubert.gameoflife.Utils.Arrays.weaponList;

public class BuyActivity extends AppCompatActivity implements RecyclerViewShopBuyAdapter.ItemClickListener {

    private static final int PAGE_NUMBER = 2;

    private int id;
    View view;

//    Spinner spinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        setTitle(R.string.title_shop);
        view = new View(this);

        ArrayList<String> itemsNames = new ArrayList<>();
        ArrayList<String> itemsPrices = new ArrayList<>();
        Lodging[] lodgingObjects = null;

        SharedPreferences sharedPref = userSharedPref;
        //SharedPreferences sharedPref = getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        ((TextView)(findViewById(R.id.money_buy))).setText(sharedPref.getInt(getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) + getResources().getString(R.string.currency));

        id = getIntent().getIntExtra(getResources().getString(R.string.send_shop_click_id), R.id.cardview_food);
        switch (id)
        {
            case R.id.cardview_food:
                for (Food aFoodList : foodList) itemsNames.add(aFoodList.getName());
                for(int i = 0; i < foodList.length; i++)
                    itemsPrices.add(foodList[i].getPrice() + getResources().getString(R.string.currency));
                ((TextView)(findViewById(R.id.itemToBuy_shop_buy))).setText(getResources().getString(R.string.buy_food));
                ((ProgressBar)(findViewById(R.id.progressBar_item_shop_buy))).setProgress(sharedPref.getInt(getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry));
                break;

            case R.id.cardview_medicines:
                for(int i = 0; i < medicinesList.length; i++)
                    itemsNames.add(medicinesList[i].getName());
                for(int i = 0; i < medicinesList.length; i++)
                    itemsPrices.add(medicinesList[i].getPrice() + getResources().getString(R.string.currency));
                ((TextView)(findViewById(R.id.itemToBuy_shop_buy))).setText(getResources().getString(R.string.buy_medicines));
                ((ProgressBar)(findViewById(R.id.progressBar_item_shop_buy))).setProgress(sharedPref.getInt(getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth));
                break;

            case R.id.cardview_fun:
                for(int i = 0; i < funList.length; i++)
                    itemsNames.add(funList[i].getName());
                for(int i = 0; i < funList.length; i++)
                    itemsPrices.add(funList[i].getPrice() + getResources().getString(R.string.currency));
                ((TextView)(findViewById(R.id.itemToBuy_shop_buy))).setText(getResources().getString(R.string.buy_fun_items));
                ((ProgressBar)(findViewById(R.id.progressBar_item_shop_buy))).setProgress(sharedPref.getInt(getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness));
                break;

            case R.id.buyLotteries:
                for(int i = 0; i < lotteryList.length; i++)
                    itemsNames.add(lotteryList[i].getName());
                for(int i = 0; i < lotteryList.length; i++)
                    itemsPrices.add(lotteryList[i].getPrice() + getResources().getString(R.string.currency));
                ((TextView)(findViewById(R.id.itemToBuy_shop_buy))).setText(getResources().getString(R.string.buy_lotteries));
                findViewById(R.id.progressBar_item_shop_buy).setVisibility(View.GONE);
                break;

            case R.id.cardview_blackmarket:
                JSONArray jsonArray;
                JSONObject jsonObject;
                try {
                    jsonArray = new JSONArray(sharedPref.getString(getResources().getString(R.string.saved_weapons_list_key), SharedPreferencesDefaultValues.DefaultWeapons));
                    for(int i = 0; i < jsonArray.length(); i++)
                    {
                        jsonObject = jsonArray.getJSONObject(i);
                        if(!jsonObject.getBoolean("isBought"))
                        {
                            itemsNames.add(weaponList[i].getName());
                            itemsPrices.add(weaponList[i].getPrice() + getResources().getString(R.string.currency));
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                ((TextView)(findViewById(R.id.itemToBuy_shop_buy))).setText(getResources().getString(R.string.buy_lotteries));
                findViewById(R.id.progressBar_item_shop_buy).setVisibility(View.GONE);
                break;

            case R.id.cardview_house:
                for(int i = 0; i < lodgingList.length; i++)
                    itemsNames.add(lodgingList[i].getName());
                for(int i = 0; i < lodgingList.length; i++)
                    itemsPrices.add((lodgingList[i].getPrice() * 25) + getResources().getString(R.string.currency));
                ((TextView)(findViewById(R.id.itemToBuy_shop_buy))).setText(getResources().getString(R.string.buy_houses));
                findViewById(R.id.progressBar_item_shop_buy).setVisibility(View.GONE);
                lodgingObjects = lodgingList;
                break;

            case R.id.cardview_transport:
                for(int i = 0; i < transportList.length; i++)
                    itemsNames.add(transportList[i].getName());
                for(int i = 0; i < transportList.length; i++)
                    itemsPrices.add((transportList[i].getPrice() * 25) + getResources().getString(R.string.currency));
                ((TextView)(findViewById(R.id.itemToBuy_shop_buy))).setText(getResources().getString(R.string.buy_transport));
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

        SharedPreferences sharedPref = userSharedPref;
        SharedPreferences.Editor editor = sharedPref.edit();
        Gson gson = new Gson();
        String json;

        switch (id)
        {
            case R.id.cardview_food:
                if(sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) >= foodList[position].getPrice())
                {
                    editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) - foodList[position].getPrice()));
                    editor.putInt(getString(R.string.saved_hungry_key), (sharedPref.getInt(getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry) + foodList[position].getGivenFood()));
                    editor.putInt(getString(R.string.saved_health_key), (sharedPref.getInt(getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth) + foodList[position].getGivenHealth()));
                    editor.putInt(getString(R.string.saved_energy_key), (sharedPref.getInt(getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy) + foodList[position].getGivenEnergy()));
                    editor.putInt(getString(R.string.saved_happiness_key), (sharedPref.getInt(getString(R.string.saved_happiness_key), 750) + foodList[position].getGivenHappiness()));
                    editor.apply();
                }
                else
                    Toast.makeText(this, getResources().getString(R.string.not_enough_money), Toast.LENGTH_LONG).show();
                ((ProgressBar)(findViewById(R.id.progressBar_item_shop_buy))).setProgress(sharedPref.getInt(getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry));
                ((TextView)(findViewById(R.id.money_buy))).setText(sharedPref.getInt(getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) + getResources().getString(R.string.currency));
                break;

            case R.id.cardview_medicines:
                if(sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) >= medicinesList[position].getPrice())
                {
                    editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) - medicinesList[position].getPrice()));
                    editor.putInt(getString(R.string.saved_health_key), (sharedPref.getInt(getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth) + medicinesList[position].getGivenHealth()));
                }
                else
                    Toast.makeText(this, getResources().getString(R.string.not_enough_money), Toast.LENGTH_LONG).show();
                ((ProgressBar)(findViewById(R.id.progressBar_item_shop_buy))).setProgress(sharedPref.getInt(getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth) / 10);
                ((TextView)(findViewById(R.id.money_buy))).setText(R.string.currency+ " " + sharedPref.getInt(getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney));
                break;

            case R.id.cardview_fun:
                /*String computerName = null;
                String phoneName = null;
                String tvName = null;
                int computerPrice = 0;
                int phonePrice = 0;
                int tvPrice = 0;

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
                }*/
                String jsonComputer = sharedPref.getString(getResources().getString(R.string.saved_my_computer_key), SharedPreferencesDefaultValues.DefaultMyComputer);
                String jsonPhone = sharedPref.getString(getResources().getString(R.string.saved_my_phone_key), SharedPreferencesDefaultValues.DefaultMyPhone);
                String jsonTv = sharedPref.getString(getResources().getString(R.string.saved_my_tv_key), SharedPreferencesDefaultValues.DefaultMyTv);
                Fun funComputer = gson.fromJson(jsonComputer, Fun.class);
                Fun funPhone = gson.fromJson(jsonPhone, Fun.class);
                Fun funTv = gson.fromJson(jsonTv, Fun.class);

                if("Exit".equals(funList[position].getType()))
                    buyFun(position);

                else if("Computer".equals(funList[position].getType()) && funComputer == null)
                    buyFun(position);
                else if("Computer".equals(funList[position].getType()))
                    alertDialogSellItem(  funList[position].getPrice(),  funList[position].getName(), "buy","fun", position);

                else if( funList[position].getType().equals("Phone") && funPhone == null)
                    buyFun(position);
                else if ("Phone".equals( funList[position].getType()))
                    alertDialogSellItem( funList[position].getPrice(),  funList[position].getName(), "buy","fun", position);

                else if( funList[position].getType().equals("TV") && funTv == null)
                    buyFun(position);
                else if ("TV".equals( funList[position].getType()))
                    alertDialogSellItem(funList[position].getPrice(),  funList[position].getName(), "buy","fun", position);

                ((ProgressBar)(findViewById(R.id.progressBar_item_shop_buy))).setProgress(sharedPref.getInt(getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness));
                break;
            case R.id.buyLotteries:
                if(sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) >= lotteryList[position].getPrice())
                {
                    editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) - lotteryList[position].getPrice()));

                    Random random = new Random();
                    if(random.nextInt( lotteryList[position].getChanceToWin()) == 1)
                    {
                        editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) +  lotteryList[position].getPrize()));
                        Toast.makeText(view.getContext(), getResources().getString(R.string.won) + " " +  lotteryList[position].getPrize() + getResources().getString(R.string.in_lottery), Toast.LENGTH_LONG).show();
                        editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) +  lotteryList[position].getPrize()));
                    }
                    else
                        Toast.makeText(view.getContext(), getResources().getString(R.string.didnt_win_lottery), Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(view.getContext(), getResources().getString(R.string.not_enough_money), Toast.LENGTH_LONG).show();
                break;

            case R.id.cardview_blackmarket:
            {
                JSONArray jsonArray;
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonArray = new JSONArray(sharedPref.getString(getResources().getString(R.string.saved_weapons_list_key), SharedPreferencesDefaultValues.DefaultWeapons));
                    for(int s = 0; s < jsonArray.length(); s++)
                    {
                        jsonObject = jsonArray.getJSONObject(s);
                        if(((TextView)view.findViewById(R.id.shopBuyItemName)).getText().equals(jsonObject.getString("name")))
                            break;
                    }
                    if(sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) >= jsonObject.getInt("price"))
                    {
                        if (!jsonObject.getBoolean("isBought")) {
                            jsonObject.put("isBought", true);
                            jsonArray.put(position, jsonObject);
                            editor.putString(getResources().getString(R.string.saved_weapons_list_key), jsonArray.toString());
                            editor.putInt(getResources().getString(R.string.saved_character_money_key), sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) - jsonObject.getInt("price"));
                            Toast.makeText(this, getResources().getString(R.string.successful_bought)+ " " + jsonObject.getString("name") + "!", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(this, getResources().getString(R.string.already_had_it), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            }

            case R.id.cardview_house:
                json = sharedPref.getString(getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodging);
                // zobaczyc czy dziala bez tego
                gson.fromJson(json, Lodging.class);
                gson.newBuilder().setLenient().create();

                Lodging lodging = gson.fromJson(json, Lodging.class);

                double lodgingPriceDouble =  lodgingList[position].getPrice();
                if(getResources().getString(R.string.rent_for_a_month).equals(String.valueOf(((Spinner)view.findViewById(R.id.buy_method_spinner)).getSelectedItem())))
                    lodgingPriceDouble = lodgingPriceDouble * 3.5;
                else if(getResources().getString(R.string.buy).equals(String.valueOf(((Spinner)view.findViewById(R.id.buy_method_spinner)).getSelectedItem())))
                    lodgingPriceDouble = lodgingPriceDouble * 25.0;
                int lodgingPrice = (int)Math.round(lodgingPriceDouble);

                // showDialogWithChoose(sharedPref, view.getContext(), "Buying lodging", "Are you sure you want to buy ", 6);
                alertDialogSellItem(lodgingPrice, lodgingList[position].getName(), String.valueOf(((Spinner)view.findViewById(R.id.buy_method_spinner)).getSelectedItem()),"lodging", position);
                break;

            case R.id.cardview_transport:
                json = sharedPref.getString(getResources().getString(R.string.saved_my_transport_key), SharedPreferencesDefaultValues.DefaultMyTransport);
                Transport transport = gson.fromJson(json, Transport.class);

                double transportPriceDouble =  transportList[position].getPrice();
                if(getResources().getString(R.string.rent_for_a_month).equals(String.valueOf(((Spinner)view.findViewById(R.id.buy_method_spinner)).getSelectedItem())))
                    transportPriceDouble *= 3.5;
                else if(getResources().getString(R.string.buy).equals(String.valueOf(((Spinner)view.findViewById(R.id.buy_method_spinner)).getSelectedItem())))
                    transportPriceDouble *= 25.0;
                int transportPrice = (int)Math.round(transportPriceDouble);

                alertDialogSellItem(transportPrice, transport.getName(), String.valueOf(((Spinner)view.findViewById(R.id.buy_method_spinner)).getSelectedItem()),"transport", position);
//
//                if(sharedPref.getString(getResources().getString(R.string.saved_my_transport_key), SharedPreferencesDefaultValues.DefaultMyTransport) == null || json.equals(SharedPreferencesDefaultValues.DefaultMyTransport))
//                {
//                    if(sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) >= transportPrice)
//                    {
//                        editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) - transportPrice));
//                        json = gson.toJson( transportList[position]);
//                        editor.putString(getResources().getString(R.string.saved_my_transport_key), json);
//                    }
//                    else
//                        Toast.makeText(view.getContext(), "Unfortunately, you don't have enough money to buy this thing.", Toast.LENGTH_LONG).show();
//                } else
//                    alertDialogSellItem( transportPrice,  transportList[position].getName(), "transport", position);
//                ((TextView)(findViewById(R.id.money_buy))).setText("$ " + sharedPref.getInt(getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney));
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

        ((TextView)(findViewById(R.id.money_buy))).setText(sharedPref.getInt(getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) + getResources().getString(R.string.currency));
    }

    private void alertDialogSellItem(/*String boughtItemName, final int boughtItemPrice,*/ final int itemPrice, String itemName, final String purchaseMethod, final String itemType, final int position)
    {
        final SharedPreferences sharedPref = userSharedPref;
        //final SharedPreferences sharedPref = getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(getResources().getString(R.string.buying) + " " + itemType)
                //.setIcon(R.drawable.ic_launcher)
                .setMessage(getResources().getString(R.string.sure_to) + " " + purchaseMethod + " " + itemName + " " + getResources().getString(R.string.for_string) + " " + itemPrice + getResources().getString(R.string.currency) + "?")
                .setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                         dialoginterface.cancel();
                     }})
                .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                      //  editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) + (boughtItemPrice / 2)));
                       // editor.apply();

                        if(sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) >= itemPrice)
                        {
                            editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) - itemPrice));
                            Gson gson = new Gson();
                            String json;
                            String[] buyMethods = getResources().getStringArray(R.array.buy_method_array);
                            switch (itemType)
                            {
                                case "fun":
                                    buyFun(position);
                                    break;
                                case "lodging":
                                    if(purchaseMethod.equals(buyMethods[1]))
                                        editor.putInt(getResources().getString(R.string.saved_renting_time_lodging_key), 7);
                                    else if(purchaseMethod.equals(buyMethods[2]))
                                        editor.putInt(getResources().getString(R.string.saved_renting_time_lodging_key), 31);

                                    json = gson.toJson( lodgingList[position]);
                                    editor.putString(getResources().getString(R.string.saved_my_lodging_key), json);
                                    editor.apply();
                                    break;
                                case "transport":
                                    if(purchaseMethod.equals(buyMethods[1]))
                                        editor.putInt(getResources().getString(R.string.saved_renting_time_transport_key), 7);
                                    else if(purchaseMethod.equals(buyMethods[2]))
                                        editor.putInt(getResources().getString(R.string.saved_renting_time_transport_key), 31);

                                    json = gson.toJson( transportList[position]);
                                    editor.putString(getResources().getString(R.string.saved_my_transport_key), json);
                                    break;
                            }
                        }
                        else
                        {
                            Toast.makeText(view.getContext(), getResources().getString(R.string.not_enough_money), Toast.LENGTH_LONG).show();
                          //  editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) - (boughtItemPrice / 2)));
                        }
                        editor.apply();
                    }
                }).show();
    }

    private void buyFun(int position)
    {
        SharedPreferences sharedPref = userSharedPref;
        //SharedPreferences sharedPref = getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        if (sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) >=  funList[position].getPrice())
        {
            editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) -  funList[position].getPrice()));
            Gson gson = new Gson();
            String json = gson.toJson( funList[position]);
            switch ( funList[position].getType()) {
                //  create enum with those types
                case "Exit":
                    editor.putInt(getString(R.string.saved_happiness_key), (sharedPref.getInt(getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness) +  funList[position].getGivenFun()));
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
            Toast.makeText(this, getResources().getString(R.string.not_enough_money), Toast.LENGTH_LONG).show();
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
