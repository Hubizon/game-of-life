package com.example.hubert.gameoflife.Profile;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hubert.gameoflife.Girlboyfriend.Children;
import com.example.hubert.gameoflife.Girlboyfriend.Love;
import com.example.hubert.gameoflife.MainActivity;
import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.House.Lodging;
import com.example.hubert.gameoflife.Utils.SharedPreferencesDefaultValues;
import com.example.hubert.gameoflife.House.Transport;
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

    private static final String TAG = MainFragment.class.getSimpleName();
    private TextView characternametext, charactermoneytext, timetext, moneytext, agetext, lodgingtext, educationtext, transporttext, girltext, childtext;
    private ProgressBar hungerprogress, healthprogress, energyprogress, happinessprogress;
    private ImageView charactericon;
    private LinearLayout mainLayout;
    private Spinner iconSpinner;

    private SharedPreferences mMainSharedPref;
    public static SharedPreferences mUserSharedPref;
    private Handler mHandler;
    public Runnable mRunnable = new Runnable() {
        @Override
        public void run() {

        //    Log.d(TAG, "fragment handler is running!!!");
            String timetxt = mUserSharedPref.getInt(getResources().getString(R.string.saved_date_years_key), SharedPreferencesDefaultValues.DefaultDateYears) + "."
                    + mUserSharedPref.getInt(getResources().getString(R.string.saved_date_months_key), SharedPreferencesDefaultValues.DefaultDateMonths) + "."
                    + mUserSharedPref.getInt(getResources().getString(R.string.saved_date_days_key), SharedPreferencesDefaultValues.DefaultDateDays) + " "
                    + mUserSharedPref.getInt(getResources().getString(R.string.saved_time_hours_key), SharedPreferencesDefaultValues.DefaultTimeHours) + ":" + "00";
            timetext.setText(timetxt);
        //    Log.d(TAG, "time: "+ timetxt);
            hungerprogress.setProgress((mUserSharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry)));
            healthprogress.setProgress((mUserSharedPref.getInt(getResources().getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth)));
            energyprogress.setProgress((mUserSharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy)));
            happinessprogress.setProgress((mUserSharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)));

            mHandler.postDelayed(mRunnable, 1000);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,
                container, false);

        Log.d(TAG, "onCreateView called");

        mMainSharedPref = getActivity().getSharedPreferences(getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);

        mUserSharedPref = MainActivity.userSharedPref;

        mainLayout = view.findViewById(R.id.profile_main_layout);
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

        iconSpinner = view.findViewById(R.id.spinner);
        SpinnerIconAdapter spinnerIconAdapter = new SpinnerIconAdapter(getContext(), iconSpinner.getWidth());
        iconSpinner.setAdapter(spinnerIconAdapter);
        //iconSpinner.setSelection(MainActivity.currentUserNumber);

        updateLabels();
        return view;
    }

    private void updateLabels() {

       // charactericon.setBackground(getResources().getDrawable(sharedPref.getInt(getResources().getString(R.string.saved_character_icon_key), R.drawable.avatar_icon1)));


        String nameChange = mUserSharedPref.getString(getResources().getString(R.string.saved_character_name_key), SharedPreferencesDefaultValues.DefaultName);
        characternametext.setText(nameChange);

        String moneyStirng = "$" + mUserSharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney);
        charactermoneytext.setText(moneyStirng);

        String ageString = mUserSharedPref.getInt(getResources().getString(R.string.saved_age_years_key), SharedPreferencesDefaultValues.DefaultAgeYears)
                + " years " + mUserSharedPref.getInt(getString(R.string.saved_age_days_key), SharedPreferencesDefaultValues.DefaultAgeDays) + " days";
        agetext.setText(ageString);

        String timeString = mUserSharedPref.getInt(getResources().getString(R.string.saved_date_years_key), SharedPreferencesDefaultValues.DefaultDateYears) + "."
                + mUserSharedPref.getInt(getResources().getString(R.string.saved_date_months_key), SharedPreferencesDefaultValues.DefaultDateMonths) + "."
                + mUserSharedPref.getInt(getResources().getString(R.string.saved_date_days_key), SharedPreferencesDefaultValues.DefaultDateDays) + " "
                + mUserSharedPref.getInt(getResources().getString(R.string.saved_time_hours_key), SharedPreferencesDefaultValues.DefaultTimeHours) + ":" + "00";
        timetext.setText(timeString);

        hungerprogress.setProgress((mUserSharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry)));
        healthprogress.setProgress((mUserSharedPref.getInt(getResources().getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth)));
        energyprogress.setProgress((mUserSharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy)));
        happinessprogress.setProgress((mUserSharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)));

        Gson gson = new Gson();

        String json = mUserSharedPref.getString(getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodging);
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

        json = mUserSharedPref.getString(getResources().getString(R.string.saved_my_transport_key), SharedPreferencesDefaultValues.DefaultMyTransport);
        Transport transport = gson.fromJson(json, Transport.class);
        if(transport != null)
            transporttext.setText(transport.getName());

        json = mUserSharedPref.getString(getResources().getString(R.string.saved_love_key), "");
        Love love = gson.fromJson(json, Love.class);
        String girlString = "Girlfriend: ";
        if(love != null)
            girlString = girlString + love.getName();
        else {
            girlString = "Single";
        }
        girltext.setText(girlString);

        json = mUserSharedPref.getString(getResources().getString(R.string.saved_my_children_key), "");
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
        updateLabels();
    }

    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mRunnable);
    }

}
