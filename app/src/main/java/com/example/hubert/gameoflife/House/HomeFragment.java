package com.example.hubert.gameoflife.House;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hubert.gameoflife.MainActivity;
import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.Shop.BuyActivity;
import com.example.hubert.gameoflife.Shop.ShopFragment;
import com.example.hubert.gameoflife.Utils.MyDialogFragment;
import com.example.hubert.gameoflife.Utils.SharedPreferencesDefaultValues;

public class HomeFragment extends Fragment
implements View.OnClickListener{

    public HomeFragment() {}


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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

        return view;
    }

    @Override
    public void onClick(View view) {

        final SharedPreferences sharedPref = getActivity().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        DialogFragment newDialog = new MyDialogFragment();

        switch (view.getId()) {
            case R.id.cardview_tv:
                newDialog.show(getActivity().getSupportFragmentManager(), "MY_DIALOG");
                break;
            case R.id.cardview_bed:
                newDialog.show(getActivity().getSupportFragmentManager(), "MY_DIALOG");
                break;
            case R.id.cardview_computer:
                if(sharedPref.getString(getResources().getString(R.string.saved_my_computer_key), SharedPreferencesDefaultValues.DefaultMyComputer) != null || sharedPref.getString(getResources().getString(R.string.saved_my_phone_key), SharedPreferencesDefaultValues.DefaultMyPhone) != null)
                {
                    Intent intent = new Intent(getActivity().getApplicationContext(), ComputerActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getActivity().getApplicationContext(), "Unfortunately you don't have a computer or a phone", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cardview_safe:
                if(sharedPref.getBoolean(getResources().getString(R.string.saved_have_safe_key), SharedPreferencesDefaultValues.DefaultHaveSafe))
                    newDialog.show(getActivity().getSupportFragmentManager(), "MY_DIALOG");
                else
                {
                    final Context context = getActivity().getApplicationContext();
                    AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                    dialog.setTitle("You don't have the safe!")
                            //.setIcon(R.drawable.ic_launcher)
                            .setMessage("Do you want to buy it for 25000$?")
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialoginterface, int i) {
                                    dialoginterface.cancel();
                                }})
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialoginterface, int i) {
                                    if((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) >= 25000)
                                    {
                                        SharedPreferences.Editor editor = sharedPref.edit();
                                        editor.putBoolean(getResources().getString(R.string.saved_have_safe_key), true);
                                        editor.putInt(getResources().getString(R.string.saved_character_money_key), (sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) - 25000 );
                                        editor.apply();
                                    }
                                    else
                                        Toast.makeText(context, "Unfortunately you don't have enough money to buy this", Toast.LENGTH_SHORT).show();
                                }
                            }).show();
                    //TODO: Michal!!! error "java.lang.IllegalStateException: You need to use a Theme.AppCompat theme (or descendant) with this activity."

                }
                break;
        }
    }
}
