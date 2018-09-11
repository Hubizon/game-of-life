package com.example.hubert.gameoflife.Profile;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.hubert.gameoflife.Girlboyfriend.Children;
import com.example.hubert.gameoflife.Girlboyfriend.Girlboyfriend;
import com.example.hubert.gameoflife.MainActivity;
import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.Utils.Lodging;
import com.example.hubert.gameoflife.Utils.SharedPreferencesDefaultValues;
import com.example.hubert.gameoflife.Utils.Transport;
import com.example.hubert.gameoflife.Work.Job;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.Timer;
import java.util.TimerTask;

public class MainFragment extends Fragment {


    public MainFragment() {}

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private TextView charactermoneytext, timetext;
    private ProgressBar hungerprogress, healthprogress, energyprogress, happinessprogress;

    private SharedPreferences mSharedPref;
    private Handler mHandler;
    public Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (getActivity() == null) {

            }
            mSharedPref = getActivity().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);

            // TODO (1) inaczej dodaj do ciebie tekst, tak zeby kompilator nie podpowiadal ze ten zapis jest zly
            timetext.setText(mSharedPref.getInt(getResources().getString(R.string.saved_date_years_key), SharedPreferencesDefaultValues.DefaultDateYears) + "."
                    + mSharedPref.getInt(getResources().getString(R.string.saved_date_months_key), SharedPreferencesDefaultValues.DefaultDateMonths) + "."
                    + mSharedPref.getInt(getResources().getString(R.string.saved_date_days_key), SharedPreferencesDefaultValues.DefaultDateDays) + " "
                    + mSharedPref.getInt(getResources().getString(R.string.saved_time_hours_key), SharedPreferencesDefaultValues.DefaultTimeHours) + ":" + "00");
            hungerprogress.setProgress((mSharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry) / 10));
            healthprogress.setProgress((mSharedPref.getInt(getResources().getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth) / 10));
            energyprogress.setProgress((mSharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy) / 10));
            happinessprogress.setProgress((mSharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness) / 10));

            mHandler.postDelayed(mRunnable, 1500);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,
                container, false);
        charactermoneytext = view.findViewById(R.id.characterMoney);
        timetext = view.findViewById(R.id.time);

        hungerprogress = view.findViewById(R.id.progressBar_character_hungry);
        healthprogress = view.findViewById(R.id.progressBar_character_health);
        energyprogress = view.findViewById(R.id.progressBar_character_energy);
        happinessprogress = view.findViewById(R.id.progressBar_character_happiness);

        updateLabels(view);

        return view;
    }

    private void updateLabels(View view) {
        SharedPreferences sharedPref = getActivity().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        ((view.findViewById(R.id.characterIcon))).setBackground(getResources().getDrawable(sharedPref.getInt(getResources().getString(R.string.saved_character_icon_key), R.drawable.avatar_icon1)));
        ((TextView)(view.findViewById(R.id.characterName))).setText(sharedPref.getString(getResources().getString(R.string.saved_character_name_key), SharedPreferencesDefaultValues.DefaultName));
        ((TextView)(view.findViewById(R.id.characterMoney))).setText("$" + sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney));
        ((TextView)(view.findViewById(R.id.characterAge))).setText(sharedPref.getInt(getResources().getString(R.string.saved_age_years_key), SharedPreferencesDefaultValues.DefaultAgeYears)
                + " years " + sharedPref.getInt(getString(R.string.saved_age_days_key), SharedPreferencesDefaultValues.DefaultAgeDays) + " days");
        ((TextView)(view.findViewById(R.id.time))).setText(sharedPref.getInt(getResources().getString(R.string.saved_date_years_key), SharedPreferencesDefaultValues.DefaultDateYears) + "."
                + sharedPref.getInt(getResources().getString(R.string.saved_date_months_key), SharedPreferencesDefaultValues.DefaultDateMonths) + "."
                + sharedPref.getInt(getResources().getString(R.string.saved_date_days_key), SharedPreferencesDefaultValues.DefaultDateDays) + " "
                + sharedPref.getInt(getResources().getString(R.string.saved_time_hours_key), SharedPreferencesDefaultValues.DefaultTimeHours) + ":" + "00");
        ((ProgressBar)(view.findViewById(R.id.progressBar_character_hungry))).setProgress((sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry) / 10));
        ((ProgressBar)(view.findViewById(R.id.progressBar_character_health))).setProgress((sharedPref.getInt(getResources().getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth) / 10));
        ((ProgressBar)(view.findViewById(R.id.progressBar_character_energy))).setProgress((sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy) / 10));
        ((ProgressBar)(view.findViewById(R.id.progressBar_character_happiness))).setProgress((sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness) / 10));

        Gson gson = new Gson();

        String json = sharedPref.getString(getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodging);
        Lodging lodging = gson.fromJson(json, Lodging.class);
        if(lodging != null)
            ((TextView)(view.findViewById(R.id.characterLodging))).setText("Lodging " + lodging.getName());

        if(sharedPref.getBoolean(getResources().getString(R.string.saved_is_in_school_now_key), true))
            ((TextView)(view.findViewById(R.id.characterTransport))).setText("School");
        /*else
        {
            json = sharedPref.getString(getResources().getString(R.string.saved_my_work_key), "");
            Job job = gson.fromJson(json, Job.class);
            if(job != null)
                ((TextView)(view.findViewById(R.id.characterTransport))).setText(getResources().getString(R.string.transport) + " " + job.getName());
        }*/

        json = sharedPref.getString(getResources().getString(R.string.saved_my_transport_key), SharedPreferencesDefaultValues.DefaultMyTransport);
        Transport transport = gson.fromJson(json, Transport.class);
        if(transport != null)
            ((TextView)(view.findViewById(R.id.characterTransport))).setText(getResources().getString(R.string.transport) + " " + transport.getName());

        /*json = sharedPref.getString(getResources().getString(R.string.saved_my_girlboyfriend_key), "");
        Girlboyfriend girlboyfriend = gson.fromJson(json, Girlboyfriend.class);
        if(girlboyfriend != null)
            ((TextView)(view.findViewById(R.id.characterGirlboyfriend))).setText("Girlfriend:" + " " + girlboyfriend.getName());

        json = sharedPref.getString(getResources().getString(R.string.saved_my_children_key), "");
        Children children = gson.fromJson(json, Children.class);
        if(children != null)
            ((TextView)(view.findViewById(R.id.characterChildren))).setText(getResources().getString(R.string.transport) + " " + children.getName());*/
    }

    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mRunnable);
    }

    @Override
    public void onStart() {
        super.onStart();
        mHandler = new Handler();
        mHandler.post(mRunnable);
    }
}
