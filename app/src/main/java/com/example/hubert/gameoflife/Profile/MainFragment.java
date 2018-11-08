package com.example.hubert.gameoflife.Profile;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hubert.gameoflife.Girlboyfriend.Children;
import com.example.hubert.gameoflife.Girlboyfriend.Love;
import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.House.Lodging;
import com.example.hubert.gameoflife.SettingsActivity;
import com.example.hubert.gameoflife.Utils.SharedPreferencesDefaultValues;
import com.example.hubert.gameoflife.House.Transport;
import com.example.hubert.gameoflife.Work.Job;
import com.google.gson.Gson;

public class MainFragment extends Fragment {


    public MainFragment() {}

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler();
    }

    private TextView characternametext, charactermoneytext, timetext, moneytext, agetext, lodgingtext, educationtext, transporttext, girltext, childtext;
    private ProgressBar hungerprogress, healthprogress, energyprogress, happinessprogress;
    private ImageView charactericon;

    private SharedPreferences mSharedPref;
    private Handler mHandler;
    public Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mSharedPref = getActivity().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);

            String timetxt = mSharedPref.getInt(getResources().getString(R.string.saved_date_years_key), SharedPreferencesDefaultValues.DefaultDateYears) + "."
                    + mSharedPref.getInt(getResources().getString(R.string.saved_date_months_key), SharedPreferencesDefaultValues.DefaultDateMonths) + "."
                    + mSharedPref.getInt(getResources().getString(R.string.saved_date_days_key), SharedPreferencesDefaultValues.DefaultDateDays) + " "
                    + mSharedPref.getInt(getResources().getString(R.string.saved_time_hours_key), SharedPreferencesDefaultValues.DefaultTimeHours) + ":" + "00";
            timetext.setText(timetxt);
            hungerprogress.setProgress((mSharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry)));
            healthprogress.setProgress((mSharedPref.getInt(getResources().getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth)));
            energyprogress.setProgress((mSharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy)));
            happinessprogress.setProgress((mSharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)));

            mHandler.postDelayed(mRunnable, 1000);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,
                container, false);
        charactermoneytext = view.findViewById(R.id.characterMoney);
        characternametext = view.findViewById(R.id.characterName);
        timetext = view.findViewById(R.id.time);
        agetext = view.findViewById(R.id.characterAge);

        lodgingtext = view.findViewById(R.id.characterLodging);
        //educationtext = view.findViewById(R.id.characterEducationWork);
        transporttext = view.findViewById(R.id.characterTransport);
        girltext = view.findViewById(R.id.characterGirlboyfriend);
        //childtext = view.findViewById(R.id.characterChildren);

        hungerprogress = view.findViewById(R.id.progressBar_character_hungry);
        healthprogress = view.findViewById(R.id.progressBar_character_health);
        energyprogress = view.findViewById(R.id.progressBar_character_energy);
        happinessprogress = view.findViewById(R.id.progressBar_character_happiness);

        //charactericon = view.findViewById(R.id.characterIcon);

        Spinner iconSpinner = view.findViewById(R.id.spinner);
        SpinnerIconAdapter spinnerIconAdapter = new SpinnerIconAdapter(getContext(), iconSpinner.getWidth());
        iconSpinner.setAdapter(spinnerIconAdapter);
        iconSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            //    coordinatorLayout = (LinearLayout) findViewById(R.id  .coordinatorLayout);
              //  Snackbar.make(view, "This feature is not supported yet!", Snackbar.LENGTH_SHORT).show();
            }
        });

        updateLabels();
        return view;
    }

    private void updateLabels() {
        SharedPreferences sharedPref = getActivity().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);

       // charactericon.setBackground(getResources().getDrawable(sharedPref.getInt(getResources().getString(R.string.saved_character_icon_key), R.drawable.avatar_icon1)));

        String moneyStirng = "$" + sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney);
        charactermoneytext.setText(moneyStirng);

        String ageString = sharedPref.getInt(getResources().getString(R.string.saved_age_years_key), SharedPreferencesDefaultValues.DefaultAgeYears)
                + " years " + sharedPref.getInt(getString(R.string.saved_age_days_key), SharedPreferencesDefaultValues.DefaultAgeDays) + " days";
        agetext.setText(ageString);

        String timeString = sharedPref.getInt(getResources().getString(R.string.saved_date_years_key), SharedPreferencesDefaultValues.DefaultDateYears) + "."
                + sharedPref.getInt(getResources().getString(R.string.saved_date_months_key), SharedPreferencesDefaultValues.DefaultDateMonths) + "."
                + sharedPref.getInt(getResources().getString(R.string.saved_date_days_key), SharedPreferencesDefaultValues.DefaultDateDays) + " "
                + sharedPref.getInt(getResources().getString(R.string.saved_time_hours_key), SharedPreferencesDefaultValues.DefaultTimeHours) + ":" + "00";
        timetext.setText(timeString);

        hungerprogress.setProgress((sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry)));
        healthprogress.setProgress((sharedPref.getInt(getResources().getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth)));
        energyprogress.setProgress((sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy)));
        happinessprogress.setProgress((sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)));

        Gson gson = new Gson();

        String json = sharedPref.getString(getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodging);
        Lodging lodging = gson.fromJson(json, Lodging.class);
        if(lodging != null)
            lodgingtext.setText(lodging.getName());

      /*  if(sharedPref.getBoolean(getResources().getString(R.string.saved_is_in_school_now_key), true))
            educationtext.setText(getString(R.string.school_text));
        else
        {
            json = sharedPref.getString(getResources().getString(R.string.saved_my_job_key), SharedPreferencesDefaultValues.DefaultMyJob);
            gson.fromJson(json, Job.class);
            gson.newBuilder().setLenient().create();
            Job job = gson.fromJson(json, Job.class);

            if(job != null)
                educationtext.setText(job.getName());
            else
                educationtext.setText("-");
        }*/

        json = sharedPref.getString(getResources().getString(R.string.saved_my_transport_key), SharedPreferencesDefaultValues.DefaultMyTransport);
        Transport transport = gson.fromJson(json, Transport.class);
        if(transport != null)
            transporttext.setText(transport.getName());

        json = sharedPref.getString(getResources().getString(R.string.saved_love_key), "");
        Love love = gson.fromJson(json, Love.class);
        String girlString = "Girlfriend: ";
        if(love != null)
            girlString = girlString + love.getName();
        else {
            girlString = "Single";
        }
        girltext.setText(girlString);

        json = sharedPref.getString(getResources().getString(R.string.saved_my_children_key), "");
        Children children = gson.fromJson(json, Children.class);
        /*if(children != null)
            childtext.setText(children.getName());
        else
            childtext.setText(getString(R.string.no_children));*/
    }

    @Override
    public void onStart() {
        super.onStart();
        mHandler.post(mRunnable);

        SharedPreferences sharedPrefSettings = PreferenceManager.getDefaultSharedPreferences(getContext());
        String nameChange = sharedPrefSettings.getString(SettingsActivity.NAME_EDIT_KEY, SharedPreferencesDefaultValues.DefaultName);
        characternametext.setText(nameChange);
    }

    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mRunnable);
    }
}
