package com.example.hubert.gameoflife.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hubert.gameoflife.House.Fun;
import com.example.hubert.gameoflife.House.Lodging;
import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.Work.ChooseJobActivity;
import com.example.hubert.gameoflife.Work.Job;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class MyDialogSafeFragment extends DialogFragment implements View.OnClickListener{

    public MyDialogSafeFragment() {}

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    View view;

    public static MyDialogSafeFragment newInstance() {
        MyDialogSafeFragment frag = new MyDialogSafeFragment();
        return frag;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Material_Light_Dialog_MinWidth);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.dialog_safe, container, false);
        sharedPref = getActivity().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);

        String cashText = "Cash: " + sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) + "$";
        ((TextView)view.findViewById(R.id.money_dialogsafe)).setText(cashText);

        String safeText = "Safe: " + sharedPref.getInt(getResources().getString(R.string.saved_money_in_safe_key), SharedPreferencesDefaultValues.DefaultMoneyInSafe) + "$";
        ((TextView)view.findViewById(R.id.safeMoney_dialogsafe)).setText(safeText);

        sharedPref = getActivity().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);

        Button despositButton = view.findViewById(R.id.despositSafe);
        despositButton.setOnClickListener(this);

        Button wiothdrawButton = view.findViewById(R.id.withdrawSafe);
        wiothdrawButton.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {
        sharedPref = getActivity().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        int amountOfCashToDespositWithdraw = Integer.valueOf(((EditText)view.findViewById(R.id.amountOfCashToDespositWithdraw)).getText().toString());

        //TODO: Michal!!! getView().getId() + - 1 :/
        switch (getView().getId())
        {
            case R.id.despositSafe:
                if(amountOfCashToDespositWithdraw <= 0)
                    Toast.makeText(getActivity().getApplicationContext(), ("Amount to the Deposit must be more than 0"), Toast.LENGTH_LONG).show();
                else if(amountOfCashToDespositWithdraw > sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney))
                    Toast.makeText(getActivity().getApplicationContext(), ("You don't have enough money to deposit!"), Toast.LENGTH_LONG).show();
                else
                {
                    editor.putInt(getResources().getString(R.string.saved_character_money_key), (sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) - amountOfCashToDespositWithdraw);
                    editor.putInt(getResources().getString(R.string.saved_money_in_safe_key), (sharedPref.getInt(getResources().getString(R.string.saved_money_in_safe_key), SharedPreferencesDefaultValues.DefaultMoneyInSafe)) + amountOfCashToDespositWithdraw);
                }
                break;
            case R.id.withdrawSafe:
                if(amountOfCashToDespositWithdraw <= 0)
                    Toast.makeText(getActivity().getApplicationContext(), ("Amount to the Deposit must be more than 0"), Toast.LENGTH_LONG).show();
                else if(amountOfCashToDespositWithdraw > sharedPref.getInt(getResources().getString(R.string.saved_money_in_safe_key), SharedPreferencesDefaultValues.DefaultMoneyInSafe))
                    Toast.makeText(getActivity().getApplicationContext(), ("You don't have enough money to withdraw!"), Toast.LENGTH_LONG).show();
                else
                {
                    editor.putInt(getResources().getString(R.string.saved_character_money_key), (sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) + amountOfCashToDespositWithdraw);
                    editor.putInt(getResources().getString(R.string.saved_money_in_safe_key), (sharedPref.getInt(getResources().getString(R.string.saved_money_in_safe_key), SharedPreferencesDefaultValues.DefaultMoneyInSafe)) - amountOfCashToDespositWithdraw);
                }
                break;
        }

        editor.apply();
        ((TextView)view.findViewById(R.id.money_dialogsafe)).setText("Cash:   " + sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) + "$");
        ((TextView)view.findViewById(R.id.safeMoney_dialogsafe)).setText("Safe:   " + sharedPref.getInt(getResources().getString(R.string.saved_money_in_safe_key), SharedPreferencesDefaultValues.DefaultMoneyInSafe) + "$");
    }
}
