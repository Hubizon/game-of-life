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

import com.example.hubert.gameoflife.MainActivity;
import com.example.hubert.gameoflife.R;

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
        TimerTask updateLabels = new MainFragment.ChangeProgressBars(view);
        //timer.scheduleAtFixedRate(updateLabels, 0, 1500);

        // Do testow sharedPrefernce:
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

        // TODO: zawsze wszystko jest rowne domyslna wartosc, chociaz, ze w BuyActivity sie zmienia wszystko dobrze, to jak przechodze znowu tu,
        // to znwou jest rowne domyslna wartosc
        ((TextView)(view.findViewById(R.id.characterMoney))).setText("$ " + sharedPref.getInt(getString(R.string.saved_character_money_key), 550));
        ((ProgressBar)(view.findViewById(R.id.progressBar_character_hungry))).setProgress((sharedPref.getInt(getString(R.string.saved_hungry_key), 750) / 10));
        ((ProgressBar)(view.findViewById(R.id.progressBar_character_health))).setProgress((sharedPref.getInt(getString(R.string.saved_health_key), 750) / 10));
        ((ProgressBar)(view.findViewById(R.id.progressBar_character_energy))).setProgress((sharedPref.getInt(getString(R.string.saved_energy_key), 750) / 10));
        ((ProgressBar)(view.findViewById(R.id.progressBar_character_happiness))).setProgress((sharedPref.getInt(getString(R.string.saved_happiness_key), 750) / 10));

        return view;
    }

    class ChangeProgressBars extends TimerTask {

        View view;

        public ChangeProgressBars(View view)
        {
            this.view = view;
        }

        @Override
        public void run () {
            // TODO: naprawic: tutaj wywala blad "Only the original thread that created a view hierarchy can touch its views." (gdy jest nie zakomentowany "timer.scheduleAtFixedRate")
            ((TextView)(view.findViewById(R.id.time))).setText(MainActivity.DateYears + "." + MainActivity.DateMonths + "." + MainActivity.DateDays + " "
                    + MainActivity.TimeHours + ":" + "00");
            ((ProgressBar)(view.findViewById(R.id.progressBar_character_hungry))).setProgress((MainActivity.Hungry / 10));
            ((ProgressBar)(view.findViewById(R.id.progressBar_character_health))).setProgress((MainActivity.Health / 10));
            ((ProgressBar)(view.findViewById(R.id.progressBar_character_energy))).setProgress((MainActivity.Energy / 10));
            ((ProgressBar)(view.findViewById(R.id.progressBar_character_happiness))).setProgress((MainActivity.Happiness / 10));
        }
    }

    private void updateLabels(View view)
    {
        ((view.findViewById(R.id.characterIcon))).setBackground(getResources().getDrawable(MainActivity.Icon));
        ((TextView)(view.findViewById(R.id.characterName))).setText(MainActivity.Name);
        ((TextView)(view.findViewById(R.id.characterAge))).setText(MainActivity.AgeYears + " " + getResources().getString(R.string.years) + " " + MainActivity.AgeDays
                + " " + getResources().getString(R.string.days));
        ((TextView)(view.findViewById(R.id.characterMoney))).setText("$ " + MainActivity.Money);
        ((TextView)(view.findViewById(R.id.time))).setText(MainActivity.DateYears + "." + MainActivity.DateMonths + "." + MainActivity.DateDays + " "
                + MainActivity.TimeHours + ":" + "00");
        ((ProgressBar)(view.findViewById(R.id.progressBar_character_hungry))).setProgress((MainActivity.Hungry / 10));
        ((ProgressBar)(view.findViewById(R.id.progressBar_character_health))).setProgress((MainActivity.Health / 10));
        ((ProgressBar)(view.findViewById(R.id.progressBar_character_energy))).setProgress((MainActivity.Energy / 10));
        ((ProgressBar)(view.findViewById(R.id.progressBar_character_happiness))).setProgress((MainActivity.Happiness / 10));

        ((TextView)(view.findViewById(R.id.characterLodging))).setText(getResources().getString(R.string.loudging) + " " + MainActivity.MyLodging.getName());
        ((TextView)(view.findViewById(R.id.characterTransport))).setText(getResources().getString(R.string.transport) + " " + MainActivity.MyTransport.getName());
    }

}
