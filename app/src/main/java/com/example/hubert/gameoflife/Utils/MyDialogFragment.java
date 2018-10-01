package com.example.hubert.gameoflife.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hubert.gameoflife.House.Fun;
import com.example.hubert.gameoflife.House.Lodging;
import com.example.hubert.gameoflife.House.Transport;
import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.Work.ChooseJobActivity;
import com.example.hubert.gameoflife.Work.CriminalJob;
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


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO customize
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Material_Light_Dialog_MinWidth);

        Bundle args = getArguments();
        Log.d("onCraate", "args: " + args);
        if (args != null) {
            mId = args.getInt(BUNDLE_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.dialog_my, container, false);

        switch (mId) {
            case R.id.cardview_bed:
                getDialog().setTitle(R.string.sleeping);
                break;

            case R.id.playComputer:
                getDialog().setTitle(R.string.playing);
                break;

            case R.id.talkComputer:
                getDialog().setTitle(R.string.talking_on_messengers);
                break;

            case R.id.makeGameComputer:
                getDialog().setTitle(R.string.making_computer_game);
                view.findViewById(R.id.textToDoingThing).setVisibility(View.VISIBLE);
                view.findViewById(R.id.progressBar_doing_thing).setVisibility(View.VISIBLE);
                break;

            case R.id.drawSomethingComputer:
                getDialog().setTitle(R.string.drawing_something);
                view.findViewById(R.id.textToDoingThing).setVisibility(View.VISIBLE);
                view.findViewById(R.id.progressBar_doing_thing).setVisibility(View.VISIBLE);
                break;

            case R.id.writePoemComputer:
                getDialog().setTitle(R.string.writing_poem);
                view.findViewById(R.id.textToDoingThing).setVisibility(View.VISIBLE);
                view.findViewById(R.id.progressBar_doing_thing).setVisibility(View.VISIBLE);
                break;

            case R.id.recordMoviesComputer:
                getDialog().setTitle(R.string.recording_movies);
                view.findViewById(R.id.textToDoingThing).setVisibility(View.VISIBLE);
                view.findViewById(R.id.progressBar_doing_thing).setVisibility(View.VISIBLE);
                break;

            case R.id.GoToSchoolEducation:
                getDialog().setTitle(R.string.learning);
                break;

            case R.id.GoToSchoolLearnHardEducation:
                getDialog().setTitle(R.string.learning);
                break;

            case R.id.GoToSchoolHangAroundEducation:
                getDialog().setTitle(R.string.learning);
                break;

            case R.id.subjectHomework:
                try {
                    JSONArray jsonArray = new JSONArray(sharedPref.getString(getResources().getString(R.string.saved_subjects_list_key), SharedPreferencesDefaultValues.DefaultSubjectsList));
                    JSONObject jsonObject = (JSONObject)(jsonArray.get(Integer.valueOf(getTag())));

                    getDialog().setTitle(R.string.learning + jsonObject.getInt("subjectName"));
                }
                catch (JSONException e)
                { }
                break;

            case R.id.btn_go_work:
                getDialog().setTitle(R.string.working);
                break;

            case R.id.btn_work_hard:
                getDialog().setTitle(R.string.working);
                break;

            case R.id.btn_hang_around:
                getDialog().setTitle(R.string.working);
                break;

            case R.id.getNewFriendsCriminal:
                getDialog().setTitle(R.string.getting_new_friends);
                break;

            case R.id.sellDrugsCriminal:
                getDialog().setTitle(R.string.sell_drugs);
                break;

            default:
                break;
        }

        sharedPref = getActivity().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);

        String moneyText = "$" + sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney);
        ((TextView) view.findViewById(R.id.money_dialog)).setText(moneyText);

        String stringDate = sharedPref.getInt(getResources().getString(R.string.saved_date_years_key), SharedPreferencesDefaultValues.DefaultDateYears) + "-"
                + sharedPref.getInt(getResources().getString(R.string.saved_date_months_key), SharedPreferencesDefaultValues.DefaultDateMonths) + "-"
                + sharedPref.getInt(getResources().getString(R.string.saved_date_days_key), SharedPreferencesDefaultValues.DefaultDateDays) + " "
                + sharedPref.getInt(getResources().getString(R.string.saved_time_hours_key), SharedPreferencesDefaultValues.DefaultTimeHours) + ":" + "00";
        ((TextView) view.findViewById(R.id.time_dialog)).setText(stringDate);

        ((ProgressBar)view.findViewById(R.id.progressBar_health_dialog)).setProgress(sharedPref.getInt(getResources().getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth) / 10);
        ((ProgressBar)view.findViewById(R.id.progressBar_hungry_dialog)).setProgress(sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry) / 10);
        ((ProgressBar)view.findViewById(R.id.progressBar_energy_dialog)).setProgress(sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy) / 10);
        ((ProgressBar)view.findViewById(R.id.progressBar_happiness_dialog)).setProgress(sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness) / 10);

        return view;
    }


    private Handler mHandler;
    public Runnable mTimerRunnable = new Runnable() {
        @Override
        public void run() {
            onTimerDelay();
            mHandler.postDelayed(mTimerRunnable, timerDelay);
        }
    };

    private void onTimerDelay()
    {
        sharedPref = getActivity().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        Random r = new Random();

        Log.d(MyDialogFragment.class.getSimpleName(), "mId: " + mId);
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
                editor.putInt(getResources().getString(R.string.saved_communication_skills_key), ((sharedPref.getInt(getResources().getString(R.string.saved_communication_skills_key), SharedPreferencesDefaultValues.DefaultCommunicationsSkills)) + 20));
                editor.putInt(getResources().getString(R.string.saved_hungry_key), ((sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry)) - 10));
                editor.putInt(getResources().getString(R.string.saved_energy_key), ((sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy)) - 10));
                break;

            case R.id.makeGameComputer:
                editor.putString(getResources().getString(R.string.saved_games_key), doSomething(getResources().getString(R.string.saved_progress_making_game_key), getResources().getString(R.string.saved_programming_skills_key), getResources().getString(R.string.saved_games_key), "Information Technology"));
                break;

            case R.id.drawSomethingComputer:
                editor.putString(getResources().getString(R.string.saved_drawings_key), doSomething(getResources().getString(R.string.saved_progress_making_drawings_key), getResources().getString(R.string.saved_drawing_skills_key), getResources().getString(R.string.saved_games_key), "Art"));
                break;

            case R.id.writePoemComputer:
                editor.putString(getResources().getString(R.string.saved_books_key), doSomething(getResources().getString(R.string.saved_progress_making_book_key), getResources().getString(R.string.saved_writing_skills_key), getResources().getString(R.string.saved_games_key), "English"));
                break;

            case R.id.recordMoviesComputer:
                editor.putString(getResources().getString(R.string.saved_movies_key), doSomething(getResources().getString(R.string.saved_progress_making_movies_key), getResources().getString(R.string.saved_recording_skills_key), getResources().getString(R.string.saved_games_key), "Music"));
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

                editor.putInt(getResources().getString(R.string.saved_energy_key), ((sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy)) - 30));
                editor.putInt(getResources().getString(R.string.saved_hungry_key), ((sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry)) - 20));
                editor.putInt(getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)) - 40));
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

                editor.putInt(getResources().getString(R.string.saved_energy_key), ((sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy)) - 20));
                editor.putInt(getResources().getString(R.string.saved_hungry_key), ((sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry)) - 20));
                editor.putInt(getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)) - 43));
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

                editor.putInt(getResources().getString(R.string.saved_energy_key), ((sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy)) - 25));
                editor.putInt(getResources().getString(R.string.saved_hungry_key), ((sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry)) - 18));
                editor.putInt(getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)) + 60));
                break;

            case R.id.subjectHomework:
                try {
                    jsonArray = new JSONArray(sharedPref.getString(getResources().getString(R.string.saved_subjects_list_key), SharedPreferencesDefaultValues.DefaultSubjectsList));
                    jsonObject = (JSONObject)(jsonArray.get(Integer.valueOf(getTag())));

                    jsonObject.put("toAnotherMark", (jsonObject.getInt("toAnotherMark") + 40));
                    jsonArray.put(Integer.valueOf(getTag()), jsonObject);
                }
                catch (JSONException e)
                { }
                editor.putString(getResources().getString(R.string.saved_subjects_list_key), jsonArray.toString());

                editor.putInt(getResources().getString(R.string.saved_energy_key), ((sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy)) - 30));
                editor.putInt(getResources().getString(R.string.saved_hungry_key), ((sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry)) - 45));
                editor.putInt(getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)) - 50));
                break;

            case R.id.btn_go_work:
                json = sharedPref.getString(getResources().getString(R.string.saved_my_job_key), SharedPreferencesDefaultValues.DefaultMyJob);
                Job mJob = gson.fromJson(json, Job.class);
                int subjectMark = 3;

                if(mJob != null)
                {
                    try {
                        jsonArray = new JSONArray(sharedPref.getString(getResources().getString(R.string.saved_subjects_list_key), SharedPreferencesDefaultValues.DefaultSubjectsList));
                        /*for (int i = 0; i <= jsonArray.length(); i++)
                        {
                            jsonObject = (JSONObject) jsonArray.get(i);
                            if(mJob.getSubjectNameNeededToWork().equals(jsonObject.getString("subjectName")))
                            {
                                subjectMark = jsonObject.getInt("subjectMark");
                                break;
                            }
                        }*/
                    }
                    catch (JSONException e)
                    { }

                    int additionalSkills = 50;
                    if(mJob.getAdditionalSkillsRes() != 0)
                        additionalSkills = sharedPref.getInt(getResources().getString(mJob.getAdditionalSkillsRes()), 50);
                    double salary = (mJob.getSalary() * (((100.0 - mJob.getMarkRatio() * 3.0) + subjectMark * mJob.getMarkRatio()) / 100.0 ) * ((100.0 + ((additionalSkills - 50.0) / 5.0)) / 100.0));
                    if(salary <= 0)
                        salary = mJob.getSalary() * 0.10;

                    editor.putInt(getResources().getString(R.string.saved_character_money_key), sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) + (int)Math.round(salary));
                }

                editor.putInt(getResources().getString(R.string.saved_work_relations_key), ((sharedPref.getInt(getResources().getString(R.string.saved_work_relations_key), SharedPreferencesDefaultValues.DefaultWorkRelations)) + 5));

                editor.putInt(getResources().getString(R.string.saved_energy_key), ((sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy)) - 40));
                editor.putInt(getResources().getString(R.string.saved_hungry_key), ((sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry)) - 20));
                editor.putInt(getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)) - 66));

                if(mJob instanceof CriminalJob)
                    if(r.nextInt(((CriminalJob)mJob).getChanceToGetBusted()) == 1)
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
               //editor.apply();
                break;

            case R.id.btn_work_hard:
                json = sharedPref.getString(getResources().getString(R.string.saved_my_job_key), SharedPreferencesDefaultValues.DefaultMyJob);
                Job mJobWorkHard = gson.fromJson(json, Job.class);
                int subjectMarkWorkHard = 3;

                if(mJobWorkHard != null)
                {
                    try {
                        jsonArray = new JSONArray(sharedPref.getString(getResources().getString(R.string.saved_subjects_list_key), SharedPreferencesDefaultValues.DefaultSubjectsList));
                        /*for (int i = 0; i <= jsonArray.length(); i++)
                        {
                            if(mJobWorkHard.getSubjectNameNeededToWork().equals(jsonArray.get(i)))
                            {
                                jsonObject = (JSONObject) jsonArray.get(i);
                                subjectMarkWorkHard = jsonObject.getInt("subjectMark");
                                break;
                            }
                        }*/
                    }
                    catch (JSONException e)
                    { }

                    int additionalSkills = sharedPref.getInt(getResources().getString(mJobWorkHard.getAdditionalSkillsRes()), 50);
                    double salary = (mJobWorkHard.getSalary() * (((100 - mJobWorkHard.getMarkRatio() * 3) + subjectMarkWorkHard * mJobWorkHard.getMarkRatio()) / 100 ) * ((100 + ((additionalSkills - 50) / 5)) / 100));
                    if(salary <= 0)
                        salary = mJobWorkHard.getSalary() * 0.10;

                    editor.putInt(getResources().getString(R.string.saved_character_money_key), sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) + (int)Math.round(salary));
                }
                editor.putInt(getResources().getString(R.string.saved_work_relations_key), ((sharedPref.getInt(getResources().getString(R.string.saved_work_relations_key), SharedPreferencesDefaultValues.DefaultWorkRelations)) + 20));

                //TODO: więcej hajsu za ciężkie pracowanie
                editor.putInt(getResources().getString(R.string.saved_energy_key), ((sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy)) - 100));
                editor.putInt(getResources().getString(R.string.saved_hungry_key), ((sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry)) - 80));
                editor.putInt(getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)) - 150));

                if(mJobWorkHard instanceof CriminalJob)
                    if(r.nextInt(((CriminalJob)mJobWorkHard).getChanceToGetBusted()) == 1)
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
                break;

            case R.id.btn_hang_around:
                json = sharedPref.getString(getResources().getString(R.string.saved_my_job_key), SharedPreferencesDefaultValues.DefaultMyJob);
                Job mJobHangAround = gson.fromJson(json, Job.class);
                int subjectMarkHangAround = 3;

                if(mJobHangAround != null)
                {
                    try {
                        jsonArray = new JSONArray(sharedPref.getString(getResources().getString(R.string.saved_subjects_list_key), SharedPreferencesDefaultValues.DefaultSubjectsList));
                        /*for (int i = 0; i <= jsonArray.length(); i++)
                        {
                            if(mJobHangAround.getSubjectNameNeededToWork().equals(jsonArray.get(i)))
                            {
                                jsonObject = (JSONObject) jsonArray.get(i);
                                subjectMarkHangAround = jsonObject.getInt("subjectMark");
                                break;
                            }
                        }*/
                    }
                    catch (JSONException e)
                    { }

                    int additionalSkills = sharedPref.getInt(getResources().getString(mJobHangAround.getAdditionalSkillsRes()), 50);
                    double salary = (mJobHangAround.getSalary() * (((100 - mJobHangAround.getMarkRatio() * 3) + subjectMarkHangAround * mJobHangAround.getMarkRatio()) / 100 ) * ((100 + ((additionalSkills - 50) / 5)) / 100));
                    if(salary <= 0)
                        salary = mJobHangAround.getSalary() * 0.10;

                    editor.putInt(getResources().getString(R.string.saved_character_money_key), sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) + (int)Math.round(salary));
                }
                editor.putInt(getResources().getString(R.string.saved_work_relations_key), ((sharedPref.getInt(getResources().getString(R.string.saved_work_relations_key), SharedPreferencesDefaultValues.DefaultWorkRelations)) - 15));

                editor.putInt(getResources().getString(R.string.saved_energy_key), ((sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy)) - 30));
                editor.putInt(getResources().getString(R.string.saved_hungry_key), ((sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry)) - 30));
                editor.putInt(getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)) + 90));

                if(mJobHangAround instanceof CriminalJob)
                    if(r.nextInt(((CriminalJob)mJobHangAround).getChanceToGetBusted()) == 1)
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
                break;

            case R.id.getNewFriendsCriminal:
                editor.putInt(getResources().getString(R.string.saved_energy_key), ((sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy)) - 90));
                editor.putInt(getResources().getString(R.string.saved_criminal_points_key), (sharedPref.getInt(getResources().getString(R.string.saved_criminal_points_key), SharedPreferencesDefaultValues.DefaultCriminalPoints) + 2));
                break;

            case R.id.sellDrugsCriminal:
                editor.putInt(getResources().getString(R.string.saved_energy_key), ((sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy)) - 180));
                editor.putInt(getResources().getString(R.string.saved_hungry_key), ((sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry)) - 60));
                editor.putInt(getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)) - 90));

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

        if(sharedPref.getInt(getResources().getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth) < 0)
            editor.putInt(getResources().getString(R.string.saved_health_key), 0);
        if(sharedPref.getInt(getResources().getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth) > 1000)
            editor.putInt(getResources().getString(R.string.saved_health_key), 1000);

        if(sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry) < 0)
            editor.putInt(getResources().getString(R.string.saved_hungry_key), 0);
        if(sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry) > 1000)
            editor.putInt(getResources().getString(R.string.saved_hungry_key), 1000);

        if(sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy) < 0)
            editor.putInt(getResources().getString(R.string.saved_energy_key), 0);
        if(sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy) > 1000)
            editor.putInt(getResources().getString(R.string.saved_energy_key), 1000);

        if(sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness) < 0)
            editor.putInt(getResources().getString(R.string.saved_happiness_key), 0);
        if(sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness) > 1000)
            editor.putInt(getResources().getString(R.string.saved_happiness_key), 1000);

        editor.apply();

        ((TextView)view.findViewById(R.id.money_dialog)).setText("$" + sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney));
        ((TextView)view.findViewById(R.id.time_dialog)).setText(sharedPref.getInt(getResources().getString(R.string.saved_date_years_key), SharedPreferencesDefaultValues.DefaultDateYears) + "."
                + sharedPref.getInt(getResources().getString(R.string.saved_date_months_key), SharedPreferencesDefaultValues.DefaultDateMonths) + "."
                + sharedPref.getInt(getResources().getString(R.string.saved_date_days_key), SharedPreferencesDefaultValues.DefaultDateDays) + " "
                + sharedPref.getInt(getResources().getString(R.string.saved_time_hours_key), SharedPreferencesDefaultValues.DefaultTimeHours) + ":" + "00");

        ((ProgressBar)view.findViewById(R.id.progressBar_health_dialog)).setProgress(sharedPref.getInt(getResources().getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth) / 10);
        ((ProgressBar)view.findViewById(R.id.progressBar_hungry_dialog)).setProgress(sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry) / 10);
        ((ProgressBar)view.findViewById(R.id.progressBar_energy_dialog)).setProgress(sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy) / 10);
        ((ProgressBar)view.findViewById(R.id.progressBar_happiness_dialog)).setProgress(sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness) / 10);
    }

    private String doSomething(String resSavedProgress, String resSavedSkills, String resSavedList, String subjectToThing) {
        editor.putInt(resSavedProgress, (sharedPref.getInt(resSavedProgress, 0) + 1));
        editor.putInt(getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)) - 1));
        editor.putInt(getResources().getString(R.string.saved_hungry_key), ((sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry)) - 1));
        editor.putInt(getResources().getString(R.string.saved_energy_key), ((sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy)) - 3));

        ((ProgressBar) view.findViewById(R.id.progressBar_doing_thing)).setProgress(((ProgressBar) view.findViewById(R.id.progressBar_doing_thing)).getProgress() + 1);
        editor.putInt(resSavedProgress, ((ProgressBar) view.findViewById(R.id.progressBar_doing_thing)).getProgress());

        if (sharedPref.getInt(resSavedProgress, 0) >= 100) {
            editor.putInt(resSavedSkills, (sharedPref.getInt(resSavedSkills, 0) + 2));
            editor.putInt(resSavedSkills, 0);

            JSONObject jsonObject;
            int gameScore = 0;
            try {
                JSONArray jsonArray = new JSONArray(sharedPref.getString(getResources().getString(R.string.saved_subjects_list_key), SharedPreferencesDefaultValues.DefaultSubjectsList));
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    if (subjectToThing.equals(jsonObject.get("subjectName"))) {
                        gameScore = (jsonObject.getInt("subjectMark") * 10) + (sharedPref.getInt(getResources().getString(R.string.saved_programming_skills_key),0));
                        break;
                    }
                }
            } catch (JSONException e) {
            }

            JSONArray toJsonArray = new JSONArray();
            toJsonArray.put(gameScore);
            Random random = new Random();
            if (random.nextInt(500) < gameScore)//bestseller
                toJsonArray.put(true);
            else
                toJsonArray.put(false);
            toJsonArray.put(sharedPref.getInt(getResources().getString(R.string.saved_date_years_key), SharedPreferencesDefaultValues.DefaultDateYears));
            toJsonArray.put(sharedPref.getInt(getResources().getString(R.string.saved_date_months_key), SharedPreferencesDefaultValues.DefaultDateMonths));

            try {
                JSONArray jsonArray = new JSONArray(sharedPref.getString(resSavedList, SharedPreferencesDefaultValues.DefaultGamesList));
                jsonArray.put(toJsonArray);
                return jsonArray.toString();
                //editor.putString(getResources().getString(R.string.saved_games_key), jsonArray.toString());
            } catch (JSONException e) {
            }
        }
        return "";
    }

    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mTimerRunnable);
    }

    int timerDelay = 1000;
    @Override
    public void onStart() {
        super.onStart();

        json = sharedPref.getString(getResources().getString(R.string.saved_my_transport_key), SharedPreferencesDefaultValues.DefaultMyTransport);
        Transport transport = gson.fromJson(json, Transport.class);
        timerDelay = 1000 - (transport.getSpeed() * 35);

        mHandler = new Handler();
        mHandler.post(mTimerRunnable);
    }
}
