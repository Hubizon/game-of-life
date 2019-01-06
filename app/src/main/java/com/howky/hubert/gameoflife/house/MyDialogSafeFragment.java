package com.howky.hubert.gameoflife.house;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.howky.hubert.gameoflife.MyApplication;
import com.howky.hubert.gameoflife.R;
import com.howky.hubert.gameoflife.utils.SharedPreferencesDefaultValues;

public class MyDialogSafeFragment extends DialogFragment implements View.OnClickListener{

    public MyDialogSafeFragment() {}

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    private EditText moneyEdit;

    private View view;

    public static MyDialogSafeFragment newInstance() {
        return new MyDialogSafeFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Material_Light_Dialog_NoActionBar_MinWidth);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.dialog_safe, container, false);
        SharedPreferences sharedPref = MyApplication.userSharedPref;
        //sharedPref = getActivity().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);

        String cashText = R.string.cash + " " + sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) + "$";
        ((TextView)view.findViewById(R.id.money_dialogsafe)).setText(cashText);

        String safeText = R.string.safe + " " + sharedPref.getInt(getResources().getString(R.string.saved_money_in_safe_key), SharedPreferencesDefaultValues.DefaultMoneyInSafe) + "$";
        ((TextView)view.findViewById(R.id.safeMoney_dialogsafe)).setText(safeText);

        final Button depositButton = view.findViewById(R.id.despositSafe);
        depositButton.setAlpha(.5f);
        depositButton.setEnabled(false);
        depositButton.setOnClickListener(this);

        final Button withdrawButton = view.findViewById(R.id.withdrawSafe);
        withdrawButton.setAlpha(.5f);
        withdrawButton.setEnabled(false);
        withdrawButton.setOnClickListener(this);

        moneyEdit = view.findViewById(R.id.amountOfCashToDespositWithdraw);
        moneyEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().trim().length() == 0 || s.toString().trim().length() > 10){
                    depositButton.setAlpha(.5f);
                    withdrawButton.setAlpha(.5f);

                    depositButton.setEnabled(false);
                    withdrawButton.setEnabled(false);
                } else {
                    depositButton.setAlpha(1);
                    withdrawButton.setAlpha(1);

                    withdrawButton.setEnabled(true);
                    depositButton.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        return view;
    }


    @Override
    public void onClick(View v) {
        sharedPref = getActivity().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        int amountOfCashToDespositWithdraw = Integer.valueOf(((EditText)view.findViewById(R.id.amountOfCashToDespositWithdraw)).getText().toString());
        switch (v.getId())
        {
            case R.id.despositSafe:
                if(amountOfCashToDespositWithdraw <= 0)
                    Toast.makeText(getActivity().getApplicationContext(), (getString(R.string.desposit_more_0)), Toast.LENGTH_LONG).show();
                else if(amountOfCashToDespositWithdraw > sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney))
                    Toast.makeText(getActivity().getApplicationContext(), (getString(R.string.not_enough_money_desposit)), Toast.LENGTH_LONG).show();
                else
                {
                    editor.putInt(getString(R.string.saved_character_money_key), (sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) - amountOfCashToDespositWithdraw);
                    editor.putInt(getResources().getString(R.string.saved_money_in_safe_key), (sharedPref.getInt(getResources().getString(R.string.saved_money_in_safe_key), SharedPreferencesDefaultValues.DefaultMoneyInSafe)) + amountOfCashToDespositWithdraw);
                }
                break;
            case R.id.withdrawSafe:
                if(amountOfCashToDespositWithdraw <= 0)
                    Toast.makeText(getActivity().getApplicationContext(), (getString(R.string.desposit_more_0)), Toast.LENGTH_LONG).show();
                else if(amountOfCashToDespositWithdraw > sharedPref.getInt(getResources().getString(R.string.saved_money_in_safe_key), SharedPreferencesDefaultValues.DefaultMoneyInSafe))
                    Toast.makeText(getActivity().getApplicationContext(), (getString(R.string.not_enough_money_withdraw)), Toast.LENGTH_LONG).show();
                else
                {
                    editor.putInt(getResources().getString(R.string.saved_character_money_key), (sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) + amountOfCashToDespositWithdraw);
                    editor.putInt(getResources().getString(R.string.saved_money_in_safe_key), (sharedPref.getInt(getResources().getString(R.string.saved_money_in_safe_key), SharedPreferencesDefaultValues.DefaultMoneyInSafe)) - amountOfCashToDespositWithdraw);
                }
                break;
        }

        editor.apply();
        ((TextView)view.findViewById(R.id.money_dialogsafe)).setText(R.string.cash + "   " + sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) + "$");
        ((TextView)view.findViewById(R.id.safeMoney_dialogsafe)).setText(R.string.safe + "   " + sharedPref.getInt(getResources().getString(R.string.saved_money_in_safe_key), SharedPreferencesDefaultValues.DefaultMoneyInSafe) + "$");
    }
}
