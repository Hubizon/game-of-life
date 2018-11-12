package com.example.hubert.gameoflife.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.hubert.gameoflife.Girlboyfriend.Love;
import com.example.hubert.gameoflife.House.Lodging;
import com.example.hubert.gameoflife.House.Transport;
import com.example.hubert.gameoflife.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

import static com.example.hubert.gameoflife.Utils.Dialogs.showAlertDialog;
import static com.example.hubert.gameoflife.Utils.Dialogs.showDialogWithChoose;

public class UpdateValues {

   // private static JSONArray jsonArray;
    private static Gson gson = new Gson();
   // private static JSONObject json = null;

    private static SharedPreferences sharedPreferences;
    private static Context contextThis;

    public static void updateSharedPreferences(Context context, SharedPreferences sharedPref) {
        SharedPreferences.Editor editor = sharedPref.edit();
        sharedPreferences = sharedPref;
        contextThis = context;

        editor.putInt(context.getResources().getString(R.string.saved_hungry_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry)) - 8));
        if(sharedPref.getInt(context.getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry) <= 0)
            editor.putInt(context.getResources().getString(R.string.saved_health_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth)) - 25));

        editor.putInt(context.getResources().getString(R.string.saved_energy_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy)) - 8));
        if(sharedPref.getInt(context.getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy) <= 0)
            editor.putInt(context.getResources().getString(R.string.saved_health_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth)) - 25));

        editor.putInt(context.getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)) - 8));
        if(sharedPref.getInt(context.getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness) <= 0)
            editor.putInt(context.getResources().getString(R.string.saved_health_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth)) - 25));



        if(sharedPref.getBoolean(context.getResources().getString(R.string.saved_is_sad_key), false))
        {
            editor.putInt(context.getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)) - 3));
            editor.putInt(context.getResources().getString(R.string.saved_how_long_will_be_sad_key), sharedPref.getInt(context.getResources().getString(R.string.saved_how_long_will_be_sad_key), 0));
            if(sharedPref.getInt(context.getResources().getString(R.string.saved_how_long_will_be_sad_key), 0) <= 0)
                editor.putBoolean(context.getResources().getString(R.string.saved_is_sad_key), false);
        }

        if (sharedPref.getInt(context.getResources().getString(R.string.saved_time_hours_key), SharedPreferencesDefaultValues.DefaultTimeHours) >= 23) {

            editor.putInt(context.getResources().getString(R.string.saved_time_hours_key), 0);
            editor.putInt(context.getResources().getString(R.string.saved_age_days_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_age_days_key), SharedPreferencesDefaultValues.DefaultAgeDays)) + 1));
            editor.putInt(context.getResources().getString(R.string.saved_date_days_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_date_days_key), SharedPreferencesDefaultValues.DefaultDateDays)) + 1));
            editor.putInt(context.getResources().getString(R.string.saved_day_week_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_day_week_key), SharedPreferencesDefaultValues.DefaultDateDays)) + 1));

            if(sharedPref.getInt(context.getResources().getString(R.string.saved_day_week_key), SharedPreferencesDefaultValues.DefaultDayWeek) == 1)
                getPayment(context);

            String jsonString = sharedPref.getString(context.getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodging);
            Lodging lodging = gson.fromJson(jsonString, Lodging.class);

            if(lodging != null)
            {
                if("rent".equals(lodging.getType()))
                    if(lodging.getRentTime() <= sharedPref.getInt(context.getResources().getString(R.string.saved_renting_time_key), 0))
                    {
                        if(sharedPref.getInt(context.getResources().getString(R.string.saved_age_years_key), SharedPreferencesDefaultValues.DefaultAgeYears) <= 18)
                            editor.putString(context.getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodging);
                        else
                            editor.putString(context.getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodgingAfter18);

                        editor.putInt(context.getResources().getString(R.string.saved_renting_time_key), 0);
                        showAlertDialog(context,sharedPref, "Your time of rental has ended", "You came to the street!");
                    }
                    else
                        editor.putInt(context.getResources().getString(R.string.saved_renting_time_key), (sharedPref.getInt(context.getResources().getString(R.string.saved_renting_time_key), 0) + 1));
            }
            editor.apply();

            if(sharedPref.getBoolean(context.getResources().getString(R.string.saved_do_borrow_money_key), false))
            {
                editor.putBoolean(context.getResources().getString(R.string.saved_do_borrow_money_key), false);
                Random random = new Random();
                switch ( random.nextInt(2))
                {
                    case 0:
                        showAlertDialog(context,sharedPref, "Neighbour loan", "Neighbour returned your money.");
                        editor.putInt(context.getResources().getString(R.string.saved_character_money_key), sharedPref.getInt(context.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) + 1000);
                        break;

                    case 1:
                        showAlertDialog(context,sharedPref, "Neighbour loan", "Neighbour returned your money and additionally gave you 500$!");
                        editor.putInt(context.getResources().getString(R.string.saved_character_money_key), sharedPref.getInt(context.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) + 1500);
                        break;

                    case 2:
                        showAlertDialog(context,sharedPref, "Neighbour loan", "Neighbour stole your money and run out from city.");
                        break;
                }
            }

            editor.apply();

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

        editor.apply();
        randomEvents(context);

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

            if(sharedPref.getInt(context.getResources().getString(R.string.saved_love_relations_key), SharedPreferencesDefaultValues.DefaultLoveRelations) <= 0)
            {
                showAlertDialog(context, sharedPref, "Break up", "Your partner broke up with you!");
                Love.BreakUp(context);
            }
        }

        editor.apply();
    }

    private static void getPayment(final Context context)//from e.g. "make a game", "write a book"
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        try
        {
            //co jakis czas tu sie wywala
            JSONArray jsonArrayGames = new JSONArray(sharedPreferences.getString(contextThis.getResources().getString(R.string.saved_games_key), SharedPreferencesDefaultValues.DefaultGamesList));
            JSONArray jsonArrayDrawings = new JSONArray(sharedPreferences.getString(contextThis.getResources().getString(R.string.saved_books_key), SharedPreferencesDefaultValues.DefaultBooksList));
            JSONArray jsonArrayBooks = new JSONArray(sharedPreferences.getString(contextThis.getResources().getString(R.string.saved_drawings_key), SharedPreferencesDefaultValues.DefaultDrawingsList));
            JSONArray jsonArrayMovies = new JSONArray(sharedPreferences.getString(contextThis.getResources().getString(R.string.saved_movies_key), SharedPreferencesDefaultValues.DefaultMoviesList));

            for(int x = 0; x < 5; x++)
            {
                JSONArray jsonArrayThing = null;
                switch (x)
                {
                    case 1:
                        jsonArrayThing = jsonArrayGames;
                        break;

                    case 2:
                        jsonArrayThing = jsonArrayDrawings;
                        break;

                    case 3:
                        jsonArrayThing = jsonArrayBooks;
                        break;

                    case 4:
                        jsonArrayThing = jsonArrayMovies;
                        break;
                }

                if(jsonArrayThing != null)
                {
                    if(jsonArrayThing.length() > 0)
                    {
                        int moneyFromGames = 0;
                        for(int i = 0; i < jsonArrayThing.length(); i++)
                        {
                            JSONArray jsonArray = jsonArrayThing.getJSONArray(i);
                            Random random = new Random();
                            int moneyFromGame = (jsonArray.getInt(0) * random.nextInt(5) + 10);
                            if(jsonArray.getBoolean(1))
                                moneyFromGame *= 2.5;

                            if(jsonArray.getInt(2) >= sharedPreferences.getInt(contextThis.getResources().getString(R.string.saved_date_years_key), SharedPreferencesDefaultValues.DefaultDateYears))
                            {
                                if((jsonArray.getInt(3) + 10) > 12)
                                {
                                    if((jsonArray.getInt(3) + 10) - 12 == sharedPreferences.getInt(contextThis.getResources().getString(R.string.saved_date_months_key), SharedPreferencesDefaultValues.DefaultDateMonths))
                                    {
                                        jsonArray.remove(i);
                                        editor.putString(contextThis.getResources().getString(R.string.saved_games_key), jsonArray.toString());
                                        // sprawdzić to (czy automatycznie się przesuwa, czy będzie trzeba to jakoś zrobić if(!jsonArray.getInt[i].isNull))
                                    }
                                }
                                else
                                {
                                    if((jsonArray.getInt(3) + 10) == sharedPreferences.getInt(contextThis.getResources().getString(R.string.saved_date_months_key), SharedPreferencesDefaultValues.DefaultDateMonths))
                                    {
                                        jsonArray.remove(i);
                                        editor.putString(contextThis.getResources().getString(R.string.saved_games_key), jsonArray.toString());
                                        //TODO: sprawdzić to (czy automatycznie się przesuwa, czy będzie trzeba to jakoś zrobić if(!jsonArray.getInt[i].isNull))
                                    }
                                }
                            }

                            moneyFromGames += moneyFromGame;
                        }

                        editor.putInt(contextThis.getResources().getString(R.string.saved_character_money_key), sharedPreferences.getInt(context.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) + moneyFromGames);
                        showAlertDialog(context, sharedPreferences, "You got payment from your created games!", "You got " + moneyFromGames + "$");
                    }
                }
            }
        }
        catch (JSONException e)
        { }
    }

    private static void randomEvents(Context context)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Random rnd = new Random();
        //TODO: zrobic, zeby nie zadzialalo jak ktos wylaczy aplikacje podczas dialogu

        switch (rnd.nextInt(5000))
        {
            case 1:
                //TODO: Michal!!! zastopuj timer tu xd
                drawGoodEvent(context);
                break;

            case 2:
                //TODO Michal!!! zastopuj timer tu xd
                drawBadEvent(context);
                break;
        }
        editor.apply();
    }

    private static void drawGoodEvent(Context context)
    {
        Random rnd = new Random();
        final int karmaPoints = sharedPreferences.getInt(contextThis.getResources().getString(R.string.saved_karma_points_key), SharedPreferencesDefaultValues.DefaultKarmaPoints);
        if(rnd.nextInt(100) < karmaPoints)
        {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            switch (rnd.nextInt(50))
            {
                case 1:
                    showDialogWithChoose(sharedPreferences, context,"Some rich man won the lottery and want to give you 2500$!", "Do you want to accept it?", 2);
                    break;

                case 2: case 3: case 4: case 5: case 6:
                    int rndFoundMoney = rnd.nextInt(25) * 100 + 500;
                        showAlertDialog(context,sharedPreferences, "You found " + rndFoundMoney, "");
                    editor.putInt(contextThis.getResources().getString(R.string.saved_character_money_key), (sharedPreferences.getInt(contextThis.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) + rndFoundMoney));
                    break;

                case 7: case 8: case 9:
                    if(sharedPreferences.getString(context.getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodging) != null)
                        if(sharedPreferences.getInt(context.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) > 1250)
                            showDialogWithChoose(sharedPreferences, context, "Neighbour loan", "Your neighbour want to borrow 1000$. Do you want to give it to him?", 6);
                    break;
            }
            editor.apply();
        }
        else
            drawBadEvent(context);
    }

    private static void drawBadEvent(Context context)
    {
        Random rnd = new Random();
        String json;
        final int karmaPoints = sharedPreferences.getInt(contextThis.getResources().getString(R.string.saved_karma_points_key), SharedPreferencesDefaultValues.DefaultKarmaPoints);
        Lodging lodging;
        if(rnd.nextInt(100) > 100 - karmaPoints)
        {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            switch (rnd.nextInt(7))
            {
                case 1: case 2:
                json = sharedPreferences.getString(contextThis.getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodging);
                gson.fromJson(json, Lodging.class);
                gson.newBuilder().setLenient().create();

                lodging = gson.fromJson(json, Lodging.class);
                if(Arrays.CheapFlatInTheDangerousDistrict.getName().equals(lodging.getName()))
                {
                    if(sharedPreferences.getInt(context.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) >= 15000)
                        editor.putInt(context.getResources().getString(R.string.saved_character_money_key), (sharedPreferences.getInt(context.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) - 15000));
                    else
                        editor.putBoolean(context.getResources().getString(R.string.saved_is_dead_key), true);
                    showDialogWithChoose(sharedPreferences, context,"You were attacked by criminalist!", "Do you want to go to the hospital for 15 000 or order the grave?", 1);
                }
                break;

                case 3:
                    json = sharedPreferences.getString(contextThis.getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodging);
                    gson.fromJson(json, Lodging.class);
                    gson.newBuilder().setLenient().create();

                    lodging = gson.fromJson(json, Lodging.class);
                    if(lodging != null )
                    {
                        if(lodging.getPrice() != 0 && "buy".equals(lodging.getType()))
                        {
                            showAlertDialog(context, sharedPreferences, "Your house was burned!", ("You got recompensation" + (lodging.getPrice() / 2)));
                            editor.putInt(contextThis.getResources().getString(R.string.saved_character_money_key), (sharedPreferences.getInt(contextThis.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) + (lodging.getPrice() / 2)));
                            editor.putString(contextThis.getResources().getString(R.string.saved_my_lodging_key), null);
                        }
                    }
                    break;

                    case 4:
                    json = sharedPreferences.getString(contextThis.getResources().getString(R.string.saved_my_transport_key), SharedPreferencesDefaultValues.DefaultMyTransport);
                    gson.fromJson(json, Transport.class);
                    gson.newBuilder().setLenient().create();

                    Transport transport = gson.fromJson(json, Transport.class);
                    if(transport != null)
                    {
                        if(transport.getPrice() != 0)
                        {
                            showAlertDialog(context, sharedPreferences, ("Somebody stole your " + transport.getName()), ("You got recompensation" + (transport.getPrice() / 2)));
                            editor.putInt(contextThis.getResources().getString(R.string.saved_character_money_key), (sharedPreferences.getInt(contextThis.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) + (transport.getPrice() / 2)));
                            editor.putString(contextThis.getResources().getString(R.string.saved_my_transport_key), null);
                        }
                    }
                    break;

                case 5:
                    showAlertDialog(context,sharedPreferences,"You've been robbed!", "Somebody stole " + (sharedPreferences.getInt(contextThis.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) / 2) + "$");
                    editor.putInt(contextThis.getResources().getString(R.string.saved_character_money_key), (sharedPreferences.getInt(contextThis.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) / 2));
                    break;

                case 6:
                    if(sharedPreferences.getBoolean(contextThis.getResources().getString(R.string.saved_have_safe_key), SharedPreferencesDefaultValues.DefaultHaveSafe) )
                    {
                        if(sharedPreferences.getInt(contextThis.getResources().getString(R.string.saved_money_in_safe_key), SharedPreferencesDefaultValues.DefaultMoneyInSafe) > 0)
                        {
                            showAlertDialog(context, sharedPreferences, "You've been robbed!", "Somebody stole " + (sharedPreferences.getInt(contextThis.getResources().getString(R.string.saved_money_in_safe_key), SharedPreferencesDefaultValues.DefaultMoney) / 2) + "$ from your safe!");
                            editor.putInt(contextThis.getResources().getString(R.string.saved_money_in_safe_key), (sharedPreferences.getInt(contextThis.getResources().getString(R.string.saved_money_in_safe_key), SharedPreferencesDefaultValues.DefaultMoneyInSafe) / 2));
                        }
                    }
                    break;

                case 7: case 8: case 9:
                    if(sharedPreferences.getString(context.getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodging) != null)
                        if(sharedPreferences.getInt(context.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) > 1250)
                            showDialogWithChoose(sharedPreferences, context, "Neighbour loan", "Your neighbour want to borrow 1000$. Do you want to give it to him?", 6);
                break;
            }
            editor.apply();
        }
        else
            drawGoodEvent(context);
    }
}
