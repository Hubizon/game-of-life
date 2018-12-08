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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

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
    private TextView characternametext, charactermoneytext, timetext, agetext, lodgingtext, transporttext, girltext;
    private ProgressBar hungerprogress, healthprogress, energyprogress, happinessprogress;

    private LinearLayout mainLayout;
    private Spinner iconSpinner;

    private SharedPreferences mMainSharedPref;
    public static SharedPreferences mUserSharedPref;
    private Handler mHandler;
    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {

            String timetxt = mUserSharedPref.getInt(getResources().getString(R.string.saved_date_years_key), SharedPreferencesDefaultValues.DefaultDateYears) + "."
                    + mUserSharedPref.getInt(getResources().getString(R.string.saved_date_months_key), SharedPreferencesDefaultValues.DefaultDateMonths) + "."
                    + mUserSharedPref.getInt(getResources().getString(R.string.saved_date_days_key), SharedPreferencesDefaultValues.DefaultDateDays) + " "
                    + mUserSharedPref.getInt(getResources().getString(R.string.saved_time_hours_key), SharedPreferencesDefaultValues.DefaultTimeHours) + ":" + "00";
            timetext.setText(timetxt);
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
        ImageView genderImage = view.findViewById(R.id.iv_gender);
        if (mUserSharedPref.getBoolean(getString(R.string.saved_sex_key), true)) genderImage.setImageResource(R.drawable.ic_mars);
        else genderImage.setImageResource(R.drawable.ic_venus);

        lodgingtext = view.findViewById(R.id.characterLodging);
        transporttext = view.findViewById(R.id.characterTransport);
        girltext = view.findViewById(R.id.characterGirlboyfriend);

        hungerprogress = view.findViewById(R.id.progressBar_character_hungry);
        healthprogress = view.findViewById(R.id.progressBar_character_health);
        energyprogress = view.findViewById(R.id.progressBar_character_energy);
        happinessprogress = view.findViewById(R.id.progressBar_character_happiness);


        iconSpinner = view.findViewById(R.id.spinner);
        SpinnerIconAdapter spinnerIconAdapter = new SpinnerIconAdapter(getContext());
        iconSpinner.setAdapter(spinnerIconAdapter);

        updateLabels();
        return view;
    }

    private void updateLabels() {
        String nameChange = mUserSharedPref.getString(getResources().getString(R.string.saved_character_name_key), SharedPreferencesDefaultValues.DefaultName);
        characternametext.setText(nameChange);

        String moneyStirng = "$" + mUserSharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney);
        charactermoneytext.setText(moneyStirng);

        String ageString = mUserSharedPref.getInt(getResources().getString(R.string.saved_age_years_key), SharedPreferencesDefaultValues.DefaultAgeYears)
                + " " + getResources().getString(R.string.years) + " " + mUserSharedPref.getInt(getString(R.string.saved_age_days_key), SharedPreferencesDefaultValues.DefaultAgeDays) + " " + getResources().getString(R.string.days);
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

        json = mUserSharedPref.getString(getResources().getString(R.string.saved_my_transport_key), SharedPreferencesDefaultValues.DefaultMyTransport);
        Transport transport = gson.fromJson(json, Transport.class);
        if(transport != null)
            transporttext.setText(transport.getName());

        json = mUserSharedPref.getString(getResources().getString(R.string.saved_love_key), "");
        Love love = gson.fromJson(json, Love.class);
        String girlString = getResources().getString(R.string.girlfriend) + " ";
        if(love != null)
            girlString = girlString + love.getName();
        else {
            girlString = getResources().getString(R.string.single);
        }
        girltext.setText(girlString);
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
