package com.howky.hubert.gameoflife.house;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.howky.hubert.gameoflife.MainActivity;
import com.howky.hubert.gameoflife.R;
import com.howky.hubert.gameoflife.SettingsActivity;
import com.howky.hubert.gameoflife.utils.MyDialogFragment;
import com.howky.hubert.gameoflife.utils.SharedPreferencesDefaultValues;

public class HomeFragment extends Fragment
    implements View.OnClickListener {


    public HomeFragment() {}


    public static HomeFragment newInstance() {
        return new HomeFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,
                container, false);

        CardView tvcardview = view.findViewById(R.id.cardview_tv);
        tvcardview.setOnClickListener(this);

        CardView bedcardview = view.findViewById(R.id.cardview_bed);
        bedcardview.setOnClickListener(this);

        CardView computercardview = view.findViewById(R.id.cardview_computer);
        computercardview.setOnClickListener(this);

        CardView safecardview = view.findViewById(R.id.cardview_safe);
        safecardview.setOnClickListener(this);

        CardView fitnesscardview = view.findViewById(R.id.cardview_fitness);
        fitnesscardview.setOnClickListener(this);

        CardView adcardview = view.findViewById(R.id.cardview_ad);
        adcardview.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {




        final SharedPreferences sharedPref = MainActivity.userSharedPref;
        //final SharedPreferences sharedPref = getActivity().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        Intent intent = null;
        DialogFragment newDialog = null;

        switch (view.getId()) {
            case R.id.cardview_tv:
                if(sharedPref.getString(getResources().getString(R.string.saved_my_tv_key), SharedPreferencesDefaultValues.DefaultMyTv) != null)
                    newDialog = MyDialogFragment.newInstance(view.getId());
                else
                    Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R.string.dont_have_televisor), Toast.LENGTH_SHORT).show();
                break;
            case R.id.cardview_bed:
                newDialog = MyDialogFragment.newInstance(view.getId());
                break;
            case R.id.cardview_computer:
                if(sharedPref.getString(getResources().getString(R.string.saved_my_computer_key), SharedPreferencesDefaultValues.DefaultMyComputer) != null || sharedPref.getString(getResources().getString(R.string.saved_my_phone_key), SharedPreferencesDefaultValues.DefaultMyPhone) != null)
                    intent = new Intent(getActivity().getApplicationContext(), ComputerActivity.class);
                else
                    Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R.string.dont_have_computer_phone), Toast.LENGTH_SHORT).show();
                break;
            case R.id.cardview_safe:
                if(!sharedPref.getBoolean(getResources().getString(R.string.saved_have_safe_key), SharedPreferencesDefaultValues.DefaultHaveSafe)) {
                    final Context context = getContext();
                    AlertDialog.Builder dialog;
                    SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    boolean isDark = mSettings.getBoolean(SettingsActivity.DARK_SWITCH_KEY, false);
                    if (isDark) {
                        dialog = new AlertDialog.Builder(context, R.style.Theme_AppCompat_Dialog_Alert);
                    }
                    else {
                        dialog = new AlertDialog.Builder(context, R.style.Theme_AppCompat_Light_Dialog_Alert);
                    }
                    dialog.setTitle(getResources().getString(R.string.dont_have_safe))
                            //.setIcon(R.drawable.ic_launcher)
                            .setMessage(getResources().getString(R.string.sure_to_buy_for_7500))
                            .setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialoginterface, int i) {
                                    dialoginterface.cancel();
                                }})
                            .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialoginterface, int i) {
                                    if((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) >= 7500)
                                    {
                                        SharedPreferences.Editor editor = sharedPref.edit();
                                        editor.putBoolean(getResources().getString(R.string.saved_have_safe_key), true);
                                        editor.putInt(getResources().getString(R.string.saved_character_money_key), (sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) - 7500 );
                                        editor.apply();
                                    }
                                    else
                                        Toast.makeText(context, getResources().getString(R.string.not_enough_money), Toast.LENGTH_SHORT).show();
                                }
                            }).show();
                }
                else
                    newDialog = MyDialogSafeFragment.newInstance();
                break;

            case R.id.cardview_fitness:
                Toast.makeText(getContext(), R.string.fitnessNotYet, Toast.LENGTH_SHORT).show();
                break;

            case R.id.cardview_ad:
                if (MainActivity.mRewardedVideoAd.isLoaded()) {
                    MainActivity.mRewardedVideoAd.show();
                } else {
                    Toast.makeText(getContext(), getResources().getString(R.string.no_ads), Toast.LENGTH_SHORT).show();
                }
        }

        if (intent != null) startActivity(intent);
        else if (newDialog != null) newDialog.show(getActivity().getSupportFragmentManager(), "home_dialog_tag");
    }
}
