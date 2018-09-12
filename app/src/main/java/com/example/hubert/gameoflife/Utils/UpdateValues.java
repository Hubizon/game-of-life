package com.example.hubert.gameoflife.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.hubert.gameoflife.Education.Subject;
import com.example.hubert.gameoflife.MainActivity;
import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.Shop.ShopFragment;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UpdateValues {

    static JSONArray jsonArray;
    static Gson gson = new Gson();
    static JSONObject json = null;

    public static void updateSharedPreferences(Context context, SharedPreferences sharedPref) {
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putInt(context.getResources().getString(R.string.saved_hungry_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry)) - 5));
        if(sharedPref.getInt(context.getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry) <= 0)
            editor.putInt(context.getResources().getString(R.string.saved_health_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth)) - 25));

        editor.putInt(context.getResources().getString(R.string.saved_energy_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy)) - 5));
        if(sharedPref.getInt(context.getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy) <= 0)
            editor.putInt(context.getResources().getString(R.string.saved_health_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth)) - 25));

        editor.putInt(context.getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)) - 5));
        if(sharedPref.getInt(context.getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness) <= 0)
            editor.putInt(context.getResources().getString(R.string.saved_health_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth)) - 25));


        editor.putInt(context.getResources().getString(R.string.saved_time_hours_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_time_hours_key), SharedPreferencesDefaultValues.DefaultTimeHours)) + 1));
        if (sharedPref.getInt(context.getResources().getString(R.string.saved_time_hours_key), SharedPreferencesDefaultValues.DefaultTimeHours) >= 23) {
            editor.putInt(context.getResources().getString(R.string.saved_time_hours_key), 0);
            editor.putInt(context.getResources().getString(R.string.saved_age_days_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_age_days_key), SharedPreferencesDefaultValues.DefaultAgeDays)) + 1));
            editor.putInt(context.getResources().getString(R.string.saved_date_days_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_date_days_key), SharedPreferencesDefaultValues.DefaultDateDays)) + 1));

            // TODO: Czy to nie bedzie za wolno dzialac? (Jak tak bedzie sie robilo co chwile, a juz teraz na chwile zacina apke
            try {
                jsonArray = new JSONArray(sharedPref.getString(context.getResources().getString(R.string.saved_subjects_list_key), SharedPreferencesDefaultValues.DefaultSubjectsList));
                for (int i = 0; i <= (jsonArray.length() - 1); i++)
                {
                    json  = (JSONObject) jsonArray.get(i);
                    json.put("IsTodaysHomeworkDone", false);
                    jsonArray.put(i, json);
                }
            }
            catch (JSONException e)
            { }
            editor.putString(context.getResources().getString(R.string.saved_subjects_list_key), jsonArray.toString());

            if (sharedPref.getInt(context.getResources().getString(R.string.saved_date_days_key), SharedPreferencesDefaultValues.DefaultDateDays) >= 31) {
                editor.putInt(context.getResources().getString(R.string.saved_date_days_key), SharedPreferencesDefaultValues.DefaultDateDays);
                if (sharedPref.getInt(context.getResources().getString(R.string.saved_date_months_key), SharedPreferencesDefaultValues.DefaultDateMonths) >= 12) {
                    editor.putInt(context.getResources().getString(R.string.saved_date_years_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_date_years_key), SharedPreferencesDefaultValues.DefaultDateYears)) + 1));
                    editor.putInt(context.getResources().getString(R.string.saved_age_years_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_age_years_key), SharedPreferencesDefaultValues.DefaultAgeYears)) + 1));
                    editor.putInt(context.getResources().getString(R.string.saved_age_days_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_age_days_key), SharedPreferencesDefaultValues.DefaultAgeDays)) + 1));
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

        if (sharedPref.getInt(context.getResources().getString(R.string.saved_saved_age_years_key), SharedPreferencesDefaultValues.DefaultAgeYears) <= 18) {
            try {
                jsonArray = new JSONArray(sharedPref.getString(context.getResources().getString(R.string.saved_subjects_list_key), SharedPreferencesDefaultValues.DefaultSubjectsList));
                for (int i = 0; i < jsonArray.length(); i++) {
                    json = (JSONObject) jsonArray.get(i);
                    json.put("toAnotherMark", (json.getInt("toAnotherMark") + 1));
                    jsonArray.put(i, json);
                }

            } catch (JSONException e) { }
        }

        editor.apply();
    }
}
