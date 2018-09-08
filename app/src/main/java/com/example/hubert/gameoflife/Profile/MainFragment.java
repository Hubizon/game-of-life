package com.example.hubert.gameoflife.Profile;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.example.hubert.gameoflife.Utils.Transport;
import com.example.hubert.gameoflife.Work.Job;
import com.google.gson.Gson;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main,
                container, false);

        updateLabels(view);

        Timer timer = new Timer();
        TimerTask updateLabels = new MainFragment.UpdateLabels(view);
        //TODO: Error: (When timer.scheduleAtFixedRate is uncommented)"Only the original thread that created a view hierarchy can touch its views."
        //timer.scheduleAtFixedRate(updateLabels, 0, 1500);

        return view;
    }

    class UpdateLabels extends TimerTask {

        View view;

        public UpdateLabels(View view)
        {
            this.view = view;
        }

        @Override
        public void run () {
            SharedPreferences sharedPref = getActivity().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            // TODO: Fix it: tutaj wywala blad "Only the original thread that created a view hierarchy can touch its views." (gdy jest nie zakomentowany "timer.scheduleAtFixedRate")
            ((TextView)(view.findViewById(R.id.time))).setText(sharedPref.getInt(getResources().getString(R.string.saved_date_years_key), 1) + "."
                    + sharedPref.getInt(getResources().getString(R.string.saved_date_months_key), 1) + "."
                    + sharedPref.getInt(getResources().getString(R.string.saved_date_days_key), 1) + " "
                    + sharedPref.getInt(getResources().getString(R.string.saved_time_hours_key), 750) + ":" + "00");
            ((ProgressBar)(view.findViewById(R.id.progressBar_character_hungry))).setProgress((sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), 750) / 10));
            ((ProgressBar)(view.findViewById(R.id.progressBar_character_health))).setProgress((sharedPref.getInt(getResources().getString(R.string.saved_health_key), 750) / 10));
            ((ProgressBar)(view.findViewById(R.id.progressBar_character_energy))).setProgress((sharedPref.getInt(getResources().getString(R.string.saved_energy_key), 750) / 10));
            ((ProgressBar)(view.findViewById(R.id.progressBar_character_happiness))).setProgress((sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), 750) / 10));
        }
    }

    private void updateLabels(View view)
    {
        SharedPreferences sharedPref = getActivity().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        ((view.findViewById(R.id.characterIcon))).setBackground(getResources().getDrawable(sharedPref.getInt(getResources().getString(R.string.saved_character_icon_key), R.drawable.avatar_icon1)));
        ((TextView)(view.findViewById(R.id.characterName))).setText(sharedPref.getString(getResources().getString(R.string.saved_character_name_key), ""));
        ((TextView)(view.findViewById(R.id.time))).setText(sharedPref.getInt(getResources().getString(R.string.saved_date_years_key), 1) + "."
                + sharedPref.getInt(getResources().getString(R.string.saved_date_months_key), 1) + "."
                + sharedPref.getInt(getResources().getString(R.string.saved_date_days_key), 1) + " "
                + sharedPref.getInt(getResources().getString(R.string.saved_time_hours_key), 750) + ":" + "00");
        ((ProgressBar)(view.findViewById(R.id.progressBar_character_hungry))).setProgress((sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), 750) / 10));
        ((ProgressBar)(view.findViewById(R.id.progressBar_character_health))).setProgress((sharedPref.getInt(getResources().getString(R.string.saved_health_key), 750) / 10));
        ((ProgressBar)(view.findViewById(R.id.progressBar_character_energy))).setProgress((sharedPref.getInt(getResources().getString(R.string.saved_energy_key), 750) / 10));
        ((ProgressBar)(view.findViewById(R.id.progressBar_character_happiness))).setProgress((sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), 750) / 10));

        Gson gson = new Gson();

        String json = sharedPref.getString(getResources().getString(R.string.saved_my_lodging_key), "");
        Lodging lodging = gson.fromJson(json, Lodging.class);
        ((TextView)(view.findViewById(R.id.characterLodging))).setText("Lodging " + lodging.getName());

        if(sharedPref.getBoolean(getResources().getString(R.string.saved_is_in_school_now_key), true))
            ((TextView)(view.findViewById(R.id.characterTransport))).setText("School");
        /*else
        {
            json = sharedPref.getString(getResources().getString(R.string.saved_my_work_key), "");
            Job job = gson.fromJson(json, Job.class);
            ((TextView)(view.findViewById(R.id.characterTransport))).setText(getResources().getString(R.string.transport) + " " + job.getName());
        }*/

        json = sharedPref.getString(getResources().getString(R.string.saved_my_transport_key), "");
        Transport transport = gson.fromJson(json, Transport.class);
        ((TextView)(view.findViewById(R.id.characterTransport))).setText(getResources().getString(R.string.transport) + " " + transport.getName());

        /*json = sharedPref.getString(getResources().getString(R.string.saved_my_girlboyfriend_key), "");
        Girlboyfriend girlboyfriend = gson.fromJson(json, Girlboyfriend.class);
        ((TextView)(view.findViewById(R.id.characterGirlboyfriend))).setText("Girlfriend:" + " " + girlboyfriend.getName());

        json = sharedPref.getString(getResources().getString(R.string.saved_my_children_key), "");
        Children children = gson.fromJson(json, Children.class);
        ((TextView)(view.findViewById(R.id.characterChildren))).setText(getResources().getString(R.string.transport) + " " + children.getName());*/
    }

}
