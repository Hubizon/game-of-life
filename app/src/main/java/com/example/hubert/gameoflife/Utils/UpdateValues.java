package com.example.hubert.gameoflife.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.hubert.gameoflife.R;

import static com.example.hubert.gameoflife.MainActivity.subjectsList;

public class UpdateValues {

    public static void updateSharedPreferences(Context context, SharedPreferences sharedPref) {
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putInt(context.getResources().getString(R.string.saved_hungry_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_hungry_key), 750)) - 5));
        if(sharedPref.getInt(context.getResources().getString(R.string.saved_hungry_key), 750) <= 0)
            editor.putInt(context.getResources().getString(R.string.saved_health_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_health_key), 750)) - 25));

        editor.putInt(context.getResources().getString(R.string.saved_energy_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_energy_key), 750)) - 5));
        if(sharedPref.getInt(context.getResources().getString(R.string.saved_energy_key), 750) <= 0)
            editor.putInt(context.getResources().getString(R.string.saved_health_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_health_key), 750)) - 25));

        editor.putInt(context.getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_happiness_key), 750)) - 5));
        if(sharedPref.getInt(context.getResources().getString(R.string.saved_happiness_key), 750) <= 0)
            editor.putInt(context.getResources().getString(R.string.saved_health_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_health_key), 750)) - 25));


        editor.putInt(context.getResources().getString(R.string.saved_time_hours_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_time_hours_key), 12)) + 1));
        if (sharedPref.getInt(context.getResources().getString(R.string.saved_time_hours_key), 12) >= 23) {
            editor.putInt(context.getResources().getString(R.string.saved_time_hours_key), 0);
            editor.putInt(context.getResources().getString(R.string.saved_age_years_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_age_years_key), 8)) + 1));
            editor.putInt(context.getResources().getString(R.string.saved_date_days_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_age_years_key), 8)) + 1));

            for (int i = 0; i <= (subjectsList.length - 1); i++)
                subjectsList[i].IsTodaysHomeworkDone = false;

            if (sharedPref.getInt(context.getResources().getString(R.string.saved_date_days_key), 12) >= 31) {
                editor.putInt(context.getResources().getString(R.string.saved_date_days_key), 0);
                if (sharedPref.getInt(context.getResources().getString(R.string.saved_date_months_key), 12) >= 12) {
                    editor.putInt(context.getResources().getString(R.string.saved_date_years_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_date_years_key), 2000)) + 1));
                    editor.putInt(context.getResources().getString(R.string.saved_age_years_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_date_years_key), 8)) + 1));
                    editor.putInt(context.getResources().getString(R.string.saved_age_days_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_age_days_key), 1)) + 1));
                    editor.putInt(context.getResources().getString(R.string.saved_date_months_key), 0);
                } else
                    editor.putInt(context.getResources().getString(R.string.saved_date_months_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_date_months_key), 1)) + 1));
            } else
            {
                editor.putInt(context.getResources().getString(R.string.saved_date_days_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_date_days_key), 1)) + 1));
                editor.putInt(context.getResources().getString(R.string.saved_age_days_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_age_days_key), 1)) + 1));
            }
        } else
            editor.putInt(context.getResources().getString(R.string.saved_time_hours_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_time_hours_key), 12)) + 1));

        if (sharedPref.getInt(context.getResources().getString(R.string.saved_saved_age_years_key), 12) <= 18) {
            for (int i = 0; i < subjectsList.length; i++) {
                subjectsList[i].decreaseToAnotherMark(1);
            }
        }
        editor.apply();
    }
}
