package com.example.hubert.gameoflife.Utils;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class MyDialogFragment extends DialogFragment  {

    private static final String BUNDLE_ID = "bundle_id";

    public MyDialogFragment() {}

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    View view;

    private int mId;
    Gson gson = new Gson();
    String json;
    JSONArray jsonArray;
    JSONObject jsonObject;

    public static MyDialogFragment newInstance(int id) {
        MyDialogFragment frag = new MyDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_ID, id);
        frag.setArguments(bundle);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Bundle args = getArguments();
        if (args != null) {
            mId = args.getInt(BUNDLE_ID);
        }
        //TODO: Michal!!! args == null

        if(mId == R.id.cardview_safe)
        {
            view = inflater.inflate(R.layout.dialog_safe, container, false);
            ((TextView)view.findViewById(R.id.money_dialogsafe)).setText("Cash:   " + sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) + "$");
            ((TextView)view.findViewById(R.id.safeMoney_dialogsafe)).setText("Safe:   " + sharedPref.getInt(getResources().getString(R.string.saved_money_in_safe_key), SharedPreferencesDefaultValues.DefaultMoneyInSafe) + "$");
        }
        else
            view = inflater.inflate(R.layout.dialog_my, container, false);

        switch (mId)
        {
            case R.id.cardview_bed:
                ((TextView) view.findViewById(R.id.doingThingName)).setText(getResources().getString(R.string.sleeping));
                break;

            case R.id.playComputer:
                ((TextView) view.findViewById(R.id.doingThingName)).setText(getResources().getString(R.string.playing));
                break;

            case R.id.talkComputer:
                ((TextView) view.findViewById(R.id.doingThingName)).setText(getResources().getString(R.string.talking_on_messengers));
                break;

            case R.id.GoToSchoolEducation:
                ((TextView) view.findViewById(R.id.doingThingName)).setText(getResources().getString(R.string.learning));
                break;

            case R.id.GoToSchoolLearnHardEducation:
                ((TextView) view.findViewById(R.id.doingThingName)).setText(getResources().getString(R.string.learning));
                break;

            case R.id.GoToSchoolHangAroundEducation:
                ((TextView) view.findViewById(R.id.doingThingName)).setText(getResources().getString(R.string.learning));
                break;

            case R.id.btn_go_work:
                ((TextView) view.findViewById(R.id.doingThingName)).setText(getResources().getString(R.string.working));
                break;

            case R.id.btn_work_hard:
                ((TextView) view.findViewById(R.id.doingThingName)).setText(getResources().getString(R.string.working));
                break;

            case R.id.btn_hang_around:
                ((TextView) view.findViewById(R.id.doingThingName)).setText(getResources().getString(R.string.working));
                break;

            case R.id.getNewFriendsCriminal:
                ((TextView) view.findViewById(R.id.doingThingName)).setText(getResources().getString(R.string.getting_new_friends));
                break;

            case R.id.sellDrugsCriminal:
                ((TextView) view.findViewById(R.id.doingThingName)).setText(getResources().getString(R.string.selling_drugs));
                break;

            default:
                break;
        }

        sharedPref = getActivity().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);

        //TODO: Michal!!! czm to nie dziala? nic sie nie wyswietla :/
        ((TextView) view.findViewById(R.id.money_dialog)).setText("$" + sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney));
        ((TextView) view.findViewById(R.id.time_dialog)).setText(sharedPref.getInt(getResources().getString(R.string.saved_date_years_key), SharedPreferencesDefaultValues.DefaultDateYears) + "."
                + sharedPref.getInt(getResources().getString(R.string.saved_date_months_key), SharedPreferencesDefaultValues.DefaultDateMonths) + "."
                + sharedPref.getInt(getResources().getString(R.string.saved_date_days_key), SharedPreferencesDefaultValues.DefaultDateDays) + " "
                + sharedPref.getInt(getResources().getString(R.string.saved_time_hours_key), SharedPreferencesDefaultValues.DefaultTimeHours) + ":" + "00");

        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout

        Bundle args = getArguments();
        if (args != null) {
            mId = args.getInt(BUNDLE_ID);
        }

        //TODO: Michal!!! mId = 0
        if(mId == R.id.cardview_safe)
            builder.setView(inflater.inflate(R.layout.dialog_safe, null));
        else
            builder.setView(inflater.inflate(R.layout.dialog_my, null));
                // Add action buttons

        //TODO: Michal!!! Zrobic timer wywolujacy ta metode
        onTimerDelay();

        return builder.create();
    }

    private void onTimerDelay()
    {
        sharedPref = getActivity().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        switch (mId)
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

            case R.id.playComputer:
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
                editor.putInt(getResources().getString(R.string.saved_communication_skills_key), ((sharedPref.getInt(getResources().getString(R.string.saved_communication_skills_key), SharedPreferencesDefaultValues.DefaultCommunicationsSkills)) + 2));
                editor.putInt(getResources().getString(R.string.saved_hungry_key), ((sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry)) - 1));
                editor.putInt(getResources().getString(R.string.saved_energy_key), ((sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy)) - 1));
                break;

            case R.id.makeGameComputer:
                editor.putInt(getResources().getString(R.string.saved_progress_making_game_key), (sharedPref.getInt(getResources().getString(R.string.saved_progress_making_game_key), SharedPreferencesDefaultValues.DefaultProgressProgramming) + 1));
                editor.putInt(getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)) - 1));
                editor.putInt(getResources().getString(R.string.saved_hungry_key), ((sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry)) - 1));
                editor.putInt(getResources().getString(R.string.saved_energy_key), ((sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy)) - 3));
                editor.apply();

                if(!(sharedPref.getInt(getResources().getString(R.string.saved_progress_making_game_key), SharedPreferencesDefaultValues.DefaultProgressProgramming) >= 100))
                {
                    editor.putInt(getResources().getString(R.string.saved_progress_making_game_key), (sharedPref.getInt(getResources().getString(R.string.saved_progress_making_game_key), SharedPreferencesDefaultValues.DefaultProgressProgramming) + 1));
                    editor.putInt(getResources().getString(R.string.saved_programming_skills_key), (sharedPref.getInt(getResources().getString(R.string.saved_programming_skills_key), SharedPreferencesDefaultValues.DefaultProgrammingSkills) + 2));

                    JSONObject jsonObject;
                    int gameScore = 0;
                    try
                    {
                        JSONArray jsonArray = new JSONArray(sharedPref.getString(getResources().getString(R.string.saved_subjects_list_key), SharedPreferencesDefaultValues.DefaultSubjectsList));
                        for(int i = 0; i < jsonArray.length(); i++)
                        {
                            jsonObject = jsonArray.getJSONObject(i);
                            if("Information Technology".equals(jsonObject.get("subjectName")))
                            {
                                gameScore = (jsonObject.getInt("subjectMark") * 10) + (sharedPref.getInt(getResources().getString(R.string.saved_programming_skills_key), SharedPreferencesDefaultValues.DefaultProgrammingSkills));
                                return;
                            }
                        }
                    }
                    catch (JSONException e)
                    { }

                    JSONArray toJsonArray = new JSONArray();
                    toJsonArray.put(gameScore);
                    Random random = new Random();
                    if(random.nextInt(500) < gameScore)//bestseller
                        toJsonArray.put(true);
                    else
                        toJsonArray.put(false);
                    toJsonArray.put(sharedPref.getInt(getResources().getString(R.string.saved_date_years_key), SharedPreferencesDefaultValues.DefaultDateYears));
                    toJsonArray.put(sharedPref.getInt(getResources().getString(R.string.saved_date_months_key), SharedPreferencesDefaultValues.DefaultDateMonths));

                    try
                    {
                        JSONArray jsonArray = new JSONArray(sharedPref.getString(getResources().getString(R.string.saved_games_key), SharedPreferencesDefaultValues.DefaultGamesList));
                        jsonArray.put(toJsonArray);
                        editor.putString(getResources().getString(R.string.saved_games_key), jsonArray.toString());
                    }
                    catch (JSONException e)
                    { }

                    editor.apply();
                }
                break;

            case R.id.drawSomethingComputer:
                //TODO: fill it
                break;

            case R.id.writePoemComputer:
                //TODO: fill it
                break;

            case R.id.recordMoviesComputer:
                //TODO: fill it
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

            case R.id.btn_go_work:
                json = sharedPref.getString(getResources().getString(R.string.saved_my_job_key), SharedPreferencesDefaultValues.DefaultMyJob);
                Job mJob = gson.fromJson(json, Job.class);

                if(mJob != null)
                    editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) + mJob.getSalary()));
                editor.putInt(getResources().getString(R.string.saved_work_relations_key), ((sharedPref.getInt(getResources().getString(R.string.saved_work_relations_key), SharedPreferencesDefaultValues.DefaultWorkRelations)) + 5));

                editor.putInt(getResources().getString(R.string.saved_energy_key), ((sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy)) - 3));
                editor.putInt(getResources().getString(R.string.saved_hungry_key), ((sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry)) - 1));
                editor.putInt(getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)) - 15));

                for(int i = 0; i < ChooseJobActivity.criminalJobsList.length; i++)
                    if(mJob == ChooseJobActivity.criminalJobsList[i])
                    {
                        Random r = new Random();
                        if(r.nextInt(50) == 1)
                        {
                            editor.putInt(getResources().getString(R.string.saved_character_money_key), 0);
                            Toast.makeText(getActivity().getApplicationContext(), ("You got busted! You lost all your money."), Toast.LENGTH_LONG).show();
                            if(r.nextInt(25) == 1)
                                if(sharedPref.getBoolean(getResources().getString(R.string.saved_have_safe_key), SharedPreferencesDefaultValues.DefaultHaveSafe))
                                {
                                    editor.putInt(getResources().getString(R.string.saved_money_in_safe_key), 0);
                                    Toast.makeText(getActivity().getApplicationContext(), ("Policeman found your safe! You lost all your money in safe."), Toast.LENGTH_LONG).show();
                                }
                        }
                        else
                            editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) + 25));
                    }

                break;

            case R.id.btn_work_hard:
                json = sharedPref.getString(getResources().getString(R.string.saved_my_job_key), SharedPreferencesDefaultValues.DefaultMyJob);
                Job mJobWorkHard = gson.fromJson(json, Job.class);

                if(mJobWorkHard != null)
                    editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) + (int)(mJobWorkHard.getSalary() * 1.3)));
                editor.putInt(getResources().getString(R.string.saved_work_relations_key), ((sharedPref.getInt(getResources().getString(R.string.saved_work_relations_key), SharedPreferencesDefaultValues.DefaultWorkRelations)) + 20));

                editor.putInt(getResources().getString(R.string.saved_energy_key), ((sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy)) - 10));
                editor.putInt(getResources().getString(R.string.saved_hungry_key), ((sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry)) - 2));
                editor.putInt(getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)) - 40));
                break;

            case R.id.btn_hang_around:
                json = sharedPref.getString(getResources().getString(R.string.saved_my_job_key), SharedPreferencesDefaultValues.DefaultMyJob);
                Job mJobHangAround = gson.fromJson(json, Job.class);

                if(mJobHangAround != null)
                    editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) + (int)(mJobHangAround.getSalary() * 0.7)));
                editor.putInt(getResources().getString(R.string.saved_work_relations_key), ((sharedPref.getInt(getResources().getString(R.string.saved_work_relations_key), SharedPreferencesDefaultValues.DefaultWorkRelations)) - 15));

                editor.putInt(getResources().getString(R.string.saved_energy_key), ((sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy)) - 1));
                editor.putInt(getResources().getString(R.string.saved_hungry_key), ((sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry)) - 1));
                editor.putInt(getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)) + 20));
                break;

            case R.id.getNewFriendsCriminal:
                editor.putInt(getResources().getString(R.string.saved_energy_key), ((sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy)) - 3));
                editor.putInt(getResources().getString(R.string.saved_criminal_points_key), (sharedPref.getInt(getResources().getString(R.string.saved_criminal_points_key), SharedPreferencesDefaultValues.DefaultCriminalPoints) + 3));
                break;

            case R.id.sellDrugsCriminal:
                editor.putInt(getResources().getString(R.string.saved_energy_key), ((sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy)) - 5));

                Random r = new Random();
                if(r.nextInt(100) == 1)
                {
                    editor.putInt(getResources().getString(R.string.saved_character_money_key), 0);
                    Toast.makeText(getActivity().getApplicationContext(), ("You got busted! You lost all your money."), Toast.LENGTH_LONG).show();
                    if(r.nextInt(25) == 1)
                        if(sharedPref.getBoolean(getResources().getString(R.string.saved_have_safe_key), SharedPreferencesDefaultValues.DefaultHaveSafe))
                        {
                            editor.putInt(getResources().getString(R.string.saved_money_in_safe_key), 0);
                            Toast.makeText(getActivity().getApplicationContext(), ("Policeman found your safe! You lost all your money in safe."), Toast.LENGTH_LONG).show();
                        }
                }
                else
                    editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) + 25));
                break;

            default:
                break;

        }
        editor.apply();
    }

    //TODO: don't delete it!  make it work
    /*@Override
    onClick()
    {
        sharedPref = sharedPref = getActivity().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        int amountOfCashToDespositWithdraw = Integer.valueOf(((EditText)view.findViewById(R.id.amountOfCashToDespositWithdraw)).getText().toString());

        switch (mId)
        {
            case R.id.despositSafe:
                if(amountOfCashToDespositWithdraw == 0)
                    Toast.makeText(getActivity().getApplicationContext(), ("Amount to the Deposit must be other than 0"), Toast.LENGTH_LONG).show();
                else if(amountOfCashToDespositWithdraw > sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney))
                    Toast.makeText(getActivity().getApplicationContext(), ("You don't have enough money to deposit!"), Toast.LENGTH_LONG).show();
                else
                {
                    editor.putInt(getResources().getString(R.string.saved_character_money_key), (sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) - amountOfCashToDespositWithdraw);
                    editor.putInt(getResources().getString(R.string.saved_money_in_safe_key), (sharedPref.getInt(getResources().getString(R.string.saved_money_in_safe_key), SharedPreferencesDefaultValues.DefaultMoneyInSafe)) + amountOfCashToDespositWithdraw);
                }
                break;
            case R.id.withdrawSafe:

                break;
        }

        editor.apply();
        ((TextView)view.findViewById(R.id.money_dialogsafe)).setText("Cash:   " + sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) + "$");
        ((TextView)view.findViewById(R.id.safeMoney_dialogsafe)).setText("Safe:   " + sharedPref.getInt(getResources().getString(R.string.saved_money_in_safe_key), SharedPreferencesDefaultValues.DefaultMoneyInSafe) + "$");
    }*/
}
