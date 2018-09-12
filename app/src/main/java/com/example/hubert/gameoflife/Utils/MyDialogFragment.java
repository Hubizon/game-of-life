package com.example.hubert.gameoflife.Utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hubert.gameoflife.MainActivity;
import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.Shop.ShopFragment;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyDialogFragment extends DialogFragment {

    public MyDialogFragment() {}

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    private int id;
    Gson gson = new Gson();
    String json;
    JSONArray jsonArray;
    JSONObject jsonObject;

    public static MyDialogFragment newInstance() {
        MyDialogFragment frag = new MyDialogFragment();
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //TODO: ustaw wartosc id na id z kliknietego guziku (np R.id.cardview_bed)
        id = 0;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.dialog_my, null));
                // Add action buttons

        //TODO: Zrobic timer wywolujacy ta metode
        onTimerDelay();

        return builder.create();
    }

    private void onTimerDelay()
    {
        sharedPref = getActivity().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        switch (id)
        {
            case R.id.cardview_bed:
                json = sharedPref.getString(getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodging);
                Lodging lodging = gson.fromJson(json, Lodging.class);

                editor.putInt(getResources().getString(R.string.saved_energy_key), ((sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy)) + lodging.getGivenEnergy()));
                editor.putInt(getResources().getString(R.string.saved_health_key), ((sharedPref.getInt(getResources().getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth)) + lodging.getGivenHealth()));
                editor.putInt(getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)) + lodging.getGivenHappiness()));
                break;

            case R.id.cardview_tv:
                json = sharedPref.getString(getResources().getString(R.string.saved_my_tv_key), SharedPreferencesDefaultValues.DefaultMyTv);
                Fun myTv = gson.fromJson(json, Fun.class);

                if(myTv != null)
                    editor.putInt(getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHealth)) + myTv.getGivenFun()));
                else
                    Toast.makeText(getActivity().getApplicationContext(), "Unfortunately you don't have a televisor", Toast.LENGTH_SHORT).show();
                break;

            case R.id.cardview_computer:
                json = sharedPref.getString(getResources().getString(R.string.saved_my_computer_key), SharedPreferencesDefaultValues.DefaultMyComputer);
                Fun myComputer = gson.fromJson(json, Fun.class);
                String jsonPhone = sharedPref.getString(getResources().getString(R.string.saved_my_phone_key), SharedPreferencesDefaultValues.DefaultMyPhone);
                Fun myPhone = gson.fromJson(jsonPhone, Fun.class);

                if(myComputer == null || myPhone == null)
                {
                    if(myPhone != null)
                        editor.putInt(getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)) + myPhone.getGivenFun()));
                    else if(myComputer != null)
                        editor.putInt(getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)) + myComputer.getGivenFun()));
                    else
                        Toast.makeText(getActivity().getApplicationContext(), "Unfortunately you don't have a computer or a phone", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(myComputer.getGivenFun() >= myPhone.getGivenFun())
                        editor.putInt(getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)) + myComputer.getGivenFun()));
                    else
                        editor.putInt(getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)) + myPhone.getGivenFun()));
                }
                break;

            case R.id.talkComputer:
                editor.putInt(getResources().getString(R.string.saved_communication_skills_key), ((sharedPref.getInt(getResources().getString(R.string.saved_communication_skills_key), SharedPreferencesDefaultValues.DefaultHappiness)) + 2));
                editor.putInt(getResources().getString(R.string.saved_hungry_key), ((sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry)) - 1));
                editor.putInt(getResources().getString(R.string.saved_energy_key), ((sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy)) - 1));
                break;

            case R.id.GoToSchoolEducation:

                try {
                    jsonArray = new JSONArray(sharedPref.getString(getResources().getString(R.string.saved_subjects_list_key), SharedPreferencesDefaultValues.DefaultSubjectsList));
                    for (int i = 0; i <= jsonArray.length(); i++)//TODO: jezeli zadziala to zmienic wszedzie (jsonArray.length() - 1) na jsonArray.length()
                    {
                        jsonObject = (JSONObject) jsonArray.get(i);
                        jsonObject.put("toAnotherMark", (jsonObject.getInt("toAnotherMark") + 5));
                        jsonArray.put(i, jsonObject);
                    }
                }
                catch (JSONException e)
                { }//TODO: to musi byc wszedzie!
                editor.putString(getResources().getString(R.string.saved_subjects_list_key), jsonArray.toString());

                editor.putInt(getResources().getString(R.string.saved_energy_key), ((sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy)) - 1));
                editor.putInt(getResources().getString(R.string.saved_hungry_key), ((sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry)) - 2));
                editor.putInt(getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)) - 3));
                break;

            case R.id.GoToSchoolLearnHardEducation:
                try {
                    jsonArray = new JSONArray(sharedPref.getString(getResources().getString(R.string.saved_subjects_list_key), SharedPreferencesDefaultValues.DefaultSubjectsList));
                    for (int i = 0; i <= jsonArray.length(); i++)
                    {
                        jsonObject = (JSONObject) jsonArray.get(i);
                        jsonObject.put("toAnotherMark", (jsonObject.getInt("toAnotherMark") + 10));
                        jsonArray.put(i, jsonObject);
                    }
                }
                catch (JSONException e)
                { }
                editor.putString(getResources().getString(R.string.saved_subjects_list_key), jsonArray.toString());

                editor.putInt(getResources().getString(R.string.saved_energy_key), ((sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy)) - 1));
                editor.putInt(getResources().getString(R.string.saved_hungry_key), ((sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry)) - 4));
                editor.putInt(getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)) - 7));
                break;

            case R.id.GoToSchoolHangAroundEducation:
                try {
                    jsonArray = new JSONArray(sharedPref.getString(getResources().getString(R.string.saved_subjects_list_key), SharedPreferencesDefaultValues.DefaultSubjectsList));
                    for (int i = 0; i <= jsonArray.length(); i++)
                    {
                        jsonObject = (JSONObject) jsonArray.get(i);
                        jsonObject.put("toAnotherMark", (jsonObject.getInt("toAnotherMark") - 15));
                        jsonArray.put(i, jsonObject);
                    }
                }
                catch (JSONException e)
                { }
                editor.putString(getResources().getString(R.string.saved_subjects_list_key), jsonArray.toString());

                editor.putInt(getResources().getString(R.string.saved_energy_key), ((sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy)) - 1));
                editor.putInt(getResources().getString(R.string.saved_hungry_key), ((sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry)) - 1));
                editor.putInt(getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)) - 25));
                break;
            default:
                break;

        }
        editor.apply();
    }
}
