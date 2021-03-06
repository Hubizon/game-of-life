package com.howky.brothers.lifeonsteroids.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.howky.brothers.lifeonsteroids.girlboyfriend.Love;
import com.howky.brothers.lifeonsteroids.house.Lodging;
import com.howky.brothers.lifeonsteroids.house.Transport;
import com.howky.brothers.lifeonsteroids.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Random;


class UpdateValues {

    private static final Gson gson = new Gson();

    private static SharedPreferences sharedPreferences;

    void interactWithUI(Context context, SharedPreferences sharedPref) {

        Dialogs dialogs = new Dialogs(context);
        if(sharedPref.getString(context.getString(R.string.saved_love_key), SharedPreferencesDefaultValues.DefaultLove) != null) {
            if(sharedPref.getInt(context.getResources().getString(R.string.saved_love_relations_key), SharedPreferencesDefaultValues.DefaultLoveRelations) <= 0) {
                dialogs.showAlertDialog(context, context.getString(R.string.break_up), context.getString(R.string.partner_broke_up));
                Love.BreakUp(context);
            }
        }

        if(sharedPref.getInt(context.getResources().getString(R.string.saved_time_hours_key), SharedPreferencesDefaultValues.DefaultTimeHours) == 1)
        {
            String jsonString = sharedPref.getString(context.getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodging);
            Lodging lodging = gson.fromJson(jsonString, Lodging.class);
            if(lodging != null)
                if(0 < sharedPref.getInt(context.getResources().getString(R.string.saved_renting_time_lodging_key), 0) && sharedPref.getInt(context.getResources().getString(R.string.saved_renting_time_lodging_key), 0) == 0)
                    dialogs.showAlertDialog(context, context.getString(R.string.rental_time_ended), context.getString(R.string.kicked_from_home));

            jsonString = sharedPref.getString(context.getResources().getString(R.string.saved_my_transport_key), SharedPreferencesDefaultValues.DefaultMyTransport);
            Transport transport = gson.fromJson(jsonString, Transport.class);
            if(transport != null)
                if(0 < sharedPref.getInt(context.getResources().getString(R.string.saved_renting_time_transport_key), 0) && sharedPref.getInt(context.getResources().getString(R.string.saved_renting_time_transport_key), 0) == 0)
                    dialogs.showAlertDialog(context, context.getString(R.string.rental_time_ended), context.getString(R.string.no_access_transport));
        }

        if(sharedPref.getBoolean(context.getResources().getString(R.string.saved_do_borrow_money_key), false))
        {
            SharedPreferences.Editor editor = sharedPref.edit();

            int moneyNow = sharedPref.getInt(context.getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney);
            Random random = new Random();
            if(random.nextInt(50) == 1)
                if(sharedPref.getInt(context.getResources().getString(R.string.saved_karma_points_key), SharedPreferencesDefaultValues.DefaultKarmaPoints) > 50)
                {
                    switch (random.nextInt(2))
                    {
                        case 0:
                            dialogs.showAlertDialog(context, context.getString(R.string.neigbour_loan), context.getString(R.string.neigbour_returned_money));
                            moneyNow += 1000;
                            editor.putInt(context.getString(R.string.saved_character_money_key), moneyNow);
                            editor.putBoolean(context.getResources().getString(R.string.saved_do_borrow_money_key), false);
                            break;

                        case 1:
                            dialogs.showAlertDialog(context, context.getString(R.string.neigbour_loan), context.getString(R.string.neigbour_returned_money_gave_500));
                            moneyNow += 1500;
                            editor.putInt(context.getString(R.string.saved_character_money_key), moneyNow);
                            editor.putBoolean(context.getResources().getString(R.string.saved_do_borrow_money_key), false);
                            break;
                        case 2:
                            dialogs.showAlertDialog(context, context.getString(R.string.neigbour_loan), context.getString(R.string.neighbour_returned_money_gave_1000));
                            moneyNow += 2000;
                            editor.putInt(context.getString(R.string.saved_character_money_key), moneyNow);
                            editor.putBoolean(context.getResources().getString(R.string.saved_do_borrow_money_key), false);
                            break;
                    }
                }
                else
                {
                    switch (random.nextInt(2))
                    {
                        case 0:
                            dialogs.showAlertDialog(context, context.getString(R.string.neigbour_loan), context.getString(R.string.neigbour_returned_money));
                            moneyNow += 1000;
                            editor.putInt(context.getString(R.string.saved_character_money_key), moneyNow);
                            editor.putBoolean(context.getResources().getString(R.string.saved_do_borrow_money_key), false);
                            break;

                        case 1: case 2:
                        dialogs.showAlertDialog(context, context.getString(R.string.neigbour_loan), context.getString(R.string.neigbour_stole_money));
                        editor.putBoolean(context.getResources().getString(R.string.saved_do_borrow_money_key), false);
                    }
                }

//            switch ( random.nextInt(100))
//            {
//                case 0: case 1:
//                    dialogs.showAlertDialog(context, "Neighbour loan", "Neighbour returned your money.");
//                    moneyNow += 1000;
//                    editor.putInt(context.getString(R.string.saved_character_money_key), moneyNow);
//                    editor.putBoolean(context.getResources().getString(R.string.saved_do_borrow_money_key), false);
//                    break;
//
//                case 2: case 3: case 4: case 5: case 6:
//                    dialogs.showAlertDialog(context, "Neighbour loan", "Neighbour returned your money and additionally gave you 500$!");
//                    moneyNow += 1500;
//                    editor.putInt(context.getString(R.string.saved_character_money_key), moneyNow);
//                    editor.putBoolean(context.getResources().getString(R.string.saved_do_borrow_money_key), false);
//                    break;
//
//                case 7:
//                    dialogs.showAlertDialog(context, "Neighbour loan", "Neighbour stole your money and run out from city.");
//                    editor.putBoolean(context.getResources().getString(R.string.saved_do_borrow_money_key), false);
//                    break;
//            }
            editor.apply();
        }

        if (sharedPref.getInt(context.getResources().getString(R.string.saved_time_hours_key), SharedPreferencesDefaultValues.DefaultTimeHours) == 1) {
            if(sharedPref.getInt(context.getResources().getString(R.string.saved_day_week_key), SharedPreferencesDefaultValues.DefaultDateDays) == 6) {
                getPayment(context);
            }
        }

        randomEvents(context);
    }

    static synchronized void updateSharedPreferences(Context context, SharedPreferences sharedPref) {
        SharedPreferences.Editor editor = sharedPref.edit();
        sharedPreferences = sharedPref;

        editor.putInt(context.getString(R.string.saved_hungry_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry)) - 5));
        if(sharedPref.getInt(context.getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry) <= 0)
        {
            editor.putInt(context.getResources().getString(R.string.saved_health_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth)) - 20));
            editor.apply();
        }

        editor.putInt(context.getResources().getString(R.string.saved_energy_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy)) - 5));
        if(sharedPref.getInt(context.getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy) <= 0)
        {
            editor.putInt(context.getResources().getString(R.string.saved_health_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth)) - 20));
            editor.apply();
        }

        editor.putInt(context.getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)) - 5));
        if(sharedPref.getInt(context.getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness) <= 0)
        {
            editor.putInt(context.getResources().getString(R.string.saved_health_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth)) - 20));
            editor.apply();
        }
        if(sharedPref.getString(context.getResources().getString(R.string.saved_love_key), SharedPreferencesDefaultValues.DefaultLove) != null)
        {
            if(sharedPref.getInt(context.getResources().getString(R.string.saved_love_relationship_level_key), SharedPreferencesDefaultValues.DefaultLoveRelationshipLevel) <= 1) {
                editor.putInt(context.getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)) + 1));
                editor.putInt(context.getResources().getString(R.string.saved_love_relations_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_love_relations_key), SharedPreferencesDefaultValues.DefaultLoveRelations)) - 1));
            } else if(sharedPref.getInt((context.getResources().getString(R.string.saved_love_relationship_level_key)), SharedPreferencesDefaultValues.DefaultLoveRelationshipLevel) == 2) {
                editor.putInt(context.getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)) + 3));
                editor.putInt(context.getResources().getString(R.string.saved_love_relations_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_love_relations_key), SharedPreferencesDefaultValues.DefaultLoveRelations)) - 1));
            } else if(sharedPref.getInt((context.getResources().getString(R.string.saved_love_relationship_level_key)), SharedPreferencesDefaultValues.DefaultLoveRelationshipLevel) >= 3) {
                editor.putInt(context.getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)) + 5));
                editor.putInt(context.getResources().getString(R.string.saved_love_relations_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_love_relations_key), SharedPreferencesDefaultValues.DefaultLoveRelations)) - 1));
            }
            editor.apply();
        }

        if(sharedPref.getBoolean(context.getResources().getString(R.string.saved_is_sad_key), false))
        {
            editor.putInt(context.getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)) - 10));
            editor.putInt(context.getResources().getString(R.string.saved_how_long_will_be_sad_key), sharedPref.getInt(context.getResources().getString(R.string.saved_how_long_will_be_sad_key), 0) - 1);
            if(sharedPref.getInt(context.getResources().getString(R.string.saved_how_long_will_be_sad_key), 0) <= 0)
                editor.putBoolean(context.getResources().getString(R.string.saved_is_sad_key), false);
        }

        if (sharedPref.getInt(context.getResources().getString(R.string.saved_time_hours_key), SharedPreferencesDefaultValues.DefaultTimeHours) >= 23) {

            editor.putInt(context.getResources().getString(R.string.saved_time_hours_key), 0);
            editor.putInt(context.getResources().getString(R.string.saved_age_days_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_age_days_key), SharedPreferencesDefaultValues.DefaultAgeDays)) + 1));
            editor.putInt(context.getResources().getString(R.string.saved_date_days_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_date_days_key), SharedPreferencesDefaultValues.DefaultDateDays)) + 1));
            editor.putInt(context.getResources().getString(R.string.saved_day_week_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_day_week_key), SharedPreferencesDefaultValues.DefaultDateDays)) + 1));
            if(sharedPref.getInt(context.getResources().getString(R.string.saved_day_week_key), SharedPreferencesDefaultValues.DefaultDateDays) >= 7) {
                editor.putInt(context.getResources().getString(R.string.saved_day_week_key), 0);
            }
            else
                editor.putInt(context.getResources().getString(R.string.saved_day_week_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_day_week_key), SharedPreferencesDefaultValues.DefaultDateDays)) + 1));

            String jsonString = sharedPref.getString(context.getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodging);
            Lodging lodging = gson.fromJson(jsonString, Lodging.class);

            if(lodging != null)
            {
                if(0 < sharedPref.getInt(context.getResources().getString(R.string.saved_renting_time_lodging_key), 0))
                {
                    editor.putInt(context.getResources().getString(R.string.saved_renting_time_lodging_key), (sharedPref.getInt(context.getResources().getString(R.string.saved_renting_time_lodging_key), 0) - 1));
                    editor.apply();

                    if(sharedPref.getInt(context.getResources().getString(R.string.saved_renting_time_lodging_key), 0) == 0)
                    {
                        editor.putString(context.getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodging);
                        editor.putInt(context.getResources().getString(R.string.saved_renting_time_lodging_key), 0);
                    }
                }
            }

            jsonString = sharedPref.getString(context.getResources().getString(R.string.saved_my_transport_key), SharedPreferencesDefaultValues.DefaultMyTransport);
            Transport transport = gson.fromJson(jsonString, Transport.class);
            if(transport != null)
            {
                if(0 < sharedPref.getInt(context.getResources().getString(R.string.saved_renting_time_transport_key), 0))
                {
                    editor.putString(context.getResources().getString(R.string.saved_my_transport_key), SharedPreferencesDefaultValues.DefaultMyTransport);

                    editor.putInt(context.getResources().getString(R.string.saved_renting_time_transport_key), 0);
                }
                else
                    editor.putInt(context.getResources().getString(R.string.saved_renting_time_transport_key), (sharedPref.getInt(context.getResources().getString(R.string.saved_renting_time_transport_key), 0) - 1));
            }

            if (sharedPref.getInt(context.getResources().getString(R.string.saved_date_days_key), SharedPreferencesDefaultValues.DefaultDateDays) >= 31) {
                editor.putInt(context.getResources().getString(R.string.saved_date_days_key), SharedPreferencesDefaultValues.DefaultDateDays);
                if (sharedPref.getInt(context.getResources().getString(R.string.saved_date_months_key), SharedPreferencesDefaultValues.DefaultDateMonths) >= 12) {
                    editor.putInt(context.getResources().getString(R.string.saved_date_years_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_date_years_key), SharedPreferencesDefaultValues.DefaultDateYears)) + 1));
                    editor.putInt(context.getResources().getString(R.string.saved_age_years_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_age_years_key), SharedPreferencesDefaultValues.DefaultAgeYears)) + 1));
                    editor.putInt(context.getResources().getString(R.string.saved_age_days_key), 0);
                    editor.putInt(context.getResources().getString(R.string.saved_date_months_key), SharedPreferencesDefaultValues.DefaultDateMonths);
                } else
                    editor.putInt(context.getResources().getString(R.string.saved_date_months_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_date_months_key), SharedPreferencesDefaultValues.DefaultDateMonths)) + 1));
            } else
            {
                editor.putInt(context.getResources().getString(R.string.saved_date_days_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_date_days_key), SharedPreferencesDefaultValues.DefaultDateDays)) + 1));
                editor.putInt(context.getResources().getString(R.string.saved_age_days_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_age_days_key), SharedPreferencesDefaultValues.DefaultAgeDays)) + 1));
            }
        } else
            editor.putInt(context.getResources().getString(R.string.saved_time_hours_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_time_hours_key), SharedPreferencesDefaultValues.DefaultTimeHours)) + 1));

        if(sharedPref.getInt(context.getResources().getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth) < 0)
            editor.putInt(context.getResources().getString(R.string.saved_health_key), 0);
        if(sharedPref.getInt(context.getResources().getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth) > 1000)
            editor.putInt(context.getResources().getString(R.string.saved_health_key), 1000);

        if(sharedPref.getInt(context.getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry) < 0)
            editor.putInt(context.getResources().getString(R.string.saved_hungry_key), 0);
        if(sharedPref.getInt(context.getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry) > 1000)
            editor.putInt(context.getResources().getString(R.string.saved_hungry_key), 1000);

        if(sharedPref.getInt(context.getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy) < 0)
            editor.putInt(context.getResources().getString(R.string.saved_energy_key), 0);
        if(sharedPref.getInt(context.getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy) > 1000)
            editor.putInt(context.getResources().getString(R.string.saved_energy_key), 1000);

        if(sharedPref.getInt(context.getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness) < 0)
            editor.putInt(context.getResources().getString(R.string.saved_happiness_key), 0);
        if(sharedPref.getInt(context.getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness) > 1000)
            editor.putInt(context.getResources().getString(R.string.saved_happiness_key), 1000);

        editor.apply();
    }

    private void getPayment(final Context context)//from e.g. "make a game", "write a book"
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        try
        {
            int moneyFromGames = 0;
            String toEnd = "";

            JSONArray jsonArrayGames = new JSONArray(sharedPreferences.getString(context.getResources().getString(R.string.saved_games_key), SharedPreferencesDefaultValues.DefaultGamesList));
            JSONArray jsonArrayDrawings = new JSONArray(sharedPreferences.getString(context.getResources().getString(R.string.saved_books_key), SharedPreferencesDefaultValues.DefaultBooksList));
            JSONArray jsonArrayBooks = new JSONArray(sharedPreferences.getString(context.getResources().getString(R.string.saved_drawings_key), SharedPreferencesDefaultValues.DefaultDrawingsList));
            JSONArray jsonArrayMovies = new JSONArray(sharedPreferences.getString(context.getResources().getString(R.string.saved_movies_key), SharedPreferencesDefaultValues.DefaultMoviesList));

            JSONArray jsonArrayThing = null;
            Dialogs dialogs = new Dialogs(context);
            for(int x = 1; x < 5; x++) {
                switch (x)
                {
                    case 1:
                        jsonArrayThing = jsonArrayGames;
                        toEnd = context.getString(R.string.payment_from_games);
                        break;

                    case 2:
                        jsonArrayThing = jsonArrayDrawings;
                        toEnd = context.getString(R.string.payment_from_drawings);
                        break;

                    case 3:
                        jsonArrayThing = jsonArrayBooks;
                        toEnd = context.getString(R.string.payment_from_books);
                        break;

                    case 4:
                        jsonArrayThing = jsonArrayMovies;
                        toEnd = context.getString(R.string.payment_from_movies);
                        break;
                }

                if (jsonArrayThing.length() > 0) {
                    for (int i = 0; i < jsonArrayThing.length(); i++) {
                        JSONArray jsonArray = jsonArrayThing.getJSONArray(i);
                        Random random = new Random();
                        int moneyFromGame = (jsonArray.getInt(0) * (random.nextInt(5) + 5) + 100);
                        if (jsonArray.getBoolean(1))
                            moneyFromGame *= 2.5;


                        if(sharedPreferences.getInt(context.getString(R.string.saved_age_years_key), SharedPreferencesDefaultValues.DefaultAgeYears) > jsonArray.getInt(2))
                        {
                            if(((365 + sharedPreferences.getInt(context.getString(R.string.saved_age_days_key), SharedPreferencesDefaultValues.DefaultAgeDays)) - jsonArray.getInt(3)) >= 100)
                            {
                                jsonArray.remove(i);
                                editor.putString(context.getString(R.string.saved_games_key), jsonArray.toString());
                            }
                        }
                        else
                        {
                            if((sharedPreferences.getInt(context.getString(R.string.saved_age_days_key), SharedPreferencesDefaultValues.DefaultAgeDays) - jsonArray.getInt(3)) >= 100)
                            {
                                jsonArray.remove(i);
                                editor.putString(context.getString(R.string.saved_games_key), jsonArray.toString());
                            }
                        }

                        moneyFromGames += moneyFromGame;
                    }

                    editor.putInt(context.getResources().getString(R.string.saved_character_money_key), sharedPreferences.getInt(context.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) + moneyFromGames);
                    dialogs.showAlertDialog(context, toEnd, context.getString(R.string.you_got) + " " + moneyFromGames + "$");
                }
            }
            //dialogs.showAlertDialog(context, "You got payment from your created games!", "You got " + moneyFromGames + "$");
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        editor.apply();
    }

    private void randomEvents(Context context)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Random rnd = new Random();

        switch (rnd.nextInt(400))
        {
            case 1:
                drawGoodEvent(context);
                break;

            case 2:
                drawBadEvent(context);
                break;
        }
        editor.apply();
    }

    private void drawGoodEvent(Context context)
    {
        Dialogs dialogs = new Dialogs(context);
        Random rnd = new Random();
        final int karmaPoints = sharedPreferences.getInt(context.getResources().getString(R.string.saved_karma_points_key), SharedPreferencesDefaultValues.DefaultKarmaPoints);
        if(rnd.nextInt(100) < karmaPoints)
        {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            switch (rnd.nextInt(10))
            {
                case 1:
                    dialogs.showDialogWithChoose(sharedPreferences, context,context.getString(R.string.rich_man_want_give_2500), context.getString(R.string.want_accept_it), 2);
                    break;

                case 2: case 3: case 4:
                    int rndFoundMoney = rnd.nextInt(10) * 100 + 500;
                        dialogs.showAlertDialog(context, context.getString(R.string.you_found) + " " + rndFoundMoney + "$", "");
                    editor.putInt(context.getResources().getString(R.string.saved_character_money_key), (sharedPreferences.getInt(context.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) + rndFoundMoney));
                    break;

                case 5: case 6:
                    break;

                case 7: case 8:
                    if(sharedPreferences.getString(context.getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodging) != null)
                        if(sharedPreferences.getInt(context.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) > 1250)
                            dialogs.showDialogWithChoose(sharedPreferences, context, context.getString(R.string.neigbour_loan), context.getString(R.string.neigbour_want_borrow_1000), 6);
                    break;
            }
            editor.apply();
        }
        else
            drawBadEvent(context);
    }

    private void drawBadEvent(Context context)
    {
        Dialogs dialogs = new Dialogs(context);
        Random rnd = new Random();
        String json;
        final int karmaPoints = sharedPreferences.getInt(context.getResources().getString(R.string.saved_karma_points_key), SharedPreferencesDefaultValues.DefaultKarmaPoints);
        Lodging lodging;
        if(rnd.nextInt(100) > 100 - karmaPoints)
        {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            switch (rnd.nextInt(10))
            {
                case 1: case 2:
                    json = sharedPreferences.getString(context.getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodging);
                    gson.fromJson(json, Lodging.class);
                    gson.newBuilder().setLenient().create();

                    lodging = gson.fromJson(json, Lodging.class);
                    if(Arrays.CheapFlatInTheDangerousDistrict.getName().equals(lodging.getName()))
                    {
                        if(sharedPreferences.getInt(context.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) >= 7500)
                            editor.putInt(context.getResources().getString(R.string.saved_character_money_key), (sharedPreferences.getInt(context.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) - 7500));
                        else
                            editor.putBoolean(context.getResources().getString(R.string.saved_is_dead_key), true);
                        dialogs.showDialogWithChoose(sharedPreferences, context,context.getString(R.string.you_were_attacked), context.getString(R.string.want_to_pay_7500_or_die), 1);
                    }
                    break;

                case 3:
                    json = sharedPreferences.getString(context.getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodging);
                    gson.fromJson(json, Lodging.class);
                    gson.newBuilder().setLenient().create();

                    lodging = gson.fromJson(json, Lodging.class);
                    if(lodging != null )
                    {
                        if(lodging.getPrice() != 0 && "buy".equals(lodging.getType()))
                        {
                            dialogs.showAlertDialog(context, context.getString(R.string.your_house_was_burned), (context.getString(R.string.you_got_recompensation) + " " + (lodging.getPrice() / 2)));
                            editor.putInt(context.getResources().getString(R.string.saved_character_money_key), (sharedPreferences.getInt(context.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) + (lodging.getPrice() / 2)));
                            editor.putString(context.getResources().getString(R.string.saved_my_lodging_key), null);
                        }
                    }
                    break;

                    case 4:
                    json = sharedPreferences.getString(context.getResources().getString(R.string.saved_my_transport_key), SharedPreferencesDefaultValues.DefaultMyTransport);
                    gson.fromJson(json, Transport.class);
                    gson.newBuilder().setLenient().create();

                    Transport transport = gson.fromJson(json, Transport.class);
                    if(transport != null)
                    {
                        if(transport.getPrice() != 0)
                        {
                            dialogs.showAlertDialog(context, (context.getString(R.string.somebody_stole_your) + " " + transport.getName()), (context.getString(R.string.you_got_recompensation) + " " + (transport.getPrice() / 2)));
                            editor.putInt(context.getResources().getString(R.string.saved_character_money_key), (sharedPreferences.getInt(context.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) + (transport.getPrice() / 2)));
                            editor.putString(context.getResources().getString(R.string.saved_my_transport_key), null);
                        }
                    }
                    break;

                case 5:
                    dialogs.showAlertDialog(context, context.getString(R.string.youve_been_robbed), context.getString(R.string.somebody_stole) + " " + (sharedPreferences.getInt(context.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) / 2) + "$");
                    if(sharedPreferences.getInt(context.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) >= 100000)
                        editor.putInt(context.getResources().getString(R.string.saved_character_money_key), (sharedPreferences.getInt(context.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) -   10000));
                    else
                        editor.putInt(context.getResources().getString(R.string.saved_character_money_key), (sharedPreferences.getInt(context.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) / (rnd.nextInt(5) + 5)));
                    break;

                case 6:
                    if(sharedPreferences.getBoolean(context.getResources().getString(R.string.saved_have_safe_key), SharedPreferencesDefaultValues.DefaultHaveSafe) )
                    {
                        if(sharedPreferences.getInt(context.getResources().getString(R.string.saved_money_in_safe_key), SharedPreferencesDefaultValues.DefaultMoneyInSafe) > 0)
                        {
                            dialogs.showAlertDialog(context, context.getString(R.string.youve_been_robbed), context.getString(R.string.somebody_stole) + " " + (sharedPreferences.getInt(context.getResources().getString(R.string.saved_money_in_safe_key), SharedPreferencesDefaultValues.DefaultMoney) / 2) + "$ from your safe!");
                            editor.putInt(context.getResources().getString(R.string.saved_money_in_safe_key), (sharedPreferences.getInt(context.getResources().getString(R.string.saved_money_in_safe_key), SharedPreferencesDefaultValues.DefaultMoneyInSafe) / 2));
                        }
                    }
                    break;

                case 7: case 8:
                    if(sharedPreferences.getString(context.getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodging) != null)
                        if(sharedPreferences.getInt(context.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) > 1250 && sharedPreferences.getBoolean(context.getResources().getString(R.string.saved_do_borrow_money_key), false))
                            dialogs.showDialogWithChoose(sharedPreferences, context, context.getString(R.string.neigbour_loan), context.getString(R.string.neigbour_want_borrow_1000), 6);
                break;
            }
            editor.apply();
        }
        else
            drawGoodEvent(context);
    }
}
