package com.example.hubert.gameoflife.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
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

import com.example.hubert.gameoflife.Education.EduFragment;
import com.example.hubert.gameoflife.House.Fun;
import com.example.hubert.gameoflife.House.Lodging;
import com.example.hubert.gameoflife.House.Transport;
import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.SettingsActivity;
import com.example.hubert.gameoflife.Work.CriminalJob;
import com.example.hubert.gameoflife.Work.Job;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

import static com.example.hubert.gameoflife.Utils.Dialogs.showAlertDialog;

public class  MyDialogFragment extends DialogFragment  {

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

    static String title;
    static int position;

    private static final int go_to_school_index = 0;
    private static final int learn_hard_index = 3;
    private static final int hang_around_index = 2;
    private static final int learn_at_home_index = 1;
    private static final int give_up_school_index = 4;

    private static final int get_new_friends_index = 0;
    private static final int steal_stuff_index = 1;
    private static final int sell_drugs_index = 2;
    private static final int threat_teacher_index = 3;

    private static final int start_working_index = 0;
    private static final int work_hard_index = 2;
    private static final int give_up_work_index = 1;

    public static MyDialogFragment newInstance(int id) {
        MyDialogFragment frag = new MyDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_ID, id);
        frag.setArguments(bundle);
        return frag;
    }

    public static MyDialogFragment newInstanceWithPosition(int id, String toTitle, int toPosition) {
        MyDialogFragment frag = new MyDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_ID, id);
        title = toTitle;
        position = toPosition;
        frag.setArguments(bundle);
        return frag;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // customize
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        boolean isDark = sharedPreferences.getBoolean(SettingsActivity.DARK_SWITCH_KEY, true);
        if (isDark) {
            setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Material_Dialog_MinWidth);
        } else {
            setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Material_Light_Dialog_MinWidth);
        }

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

        if(mId == R.id.list_item_artist_name)
        {
            if (title.equals(EduFragment.TITLE_SCHOOL)) {
                switch (position) {
                    case go_to_school_index:
                        getDialog().setTitle(R.string.learning);
                        break;
                    case hang_around_index:

                        break;
                }
            } else if (title.equals(EduFragment.TITLE_CRIMINAL)) {
                switch (position) {
                    case get_new_friends_index:

                        break;
                    case sell_drugs_index:

                        break;
                }
            } else if (title.equals(EduFragment.TITLE_WORK)) {
                switch (position) {
                    case start_working_index:
                        getDialog().setTitle(R.string.working);
                    case work_hard_index:

                        break;
                }
            }
        }

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

        ((ProgressBar)view.findViewById(R.id.progressBar_health_dialog)).setProgress(sharedPref.getInt(getResources().getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth));
        ((ProgressBar)view.findViewById(R.id.progressBar_hungry_dialog)).setProgress(sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry));
        ((ProgressBar)view.findViewById(R.id.progressBar_energy_dialog)).setProgress(sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy));
        ((ProgressBar)view.findViewById(R.id.progressBar_happiness_dialog)).setProgress(sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness));

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

        if(mId == R.id.list_item_artist_name)
        {
            if (title.equals(EduFragment.TITLE_SCHOOL)) {
                switch (position) {
                    case go_to_school_index:
                        editor.putInt(getResources().getString(R.string.saved_education_points_key), sharedPref.getInt(getResources().getString(R.string.saved_education_points_key), SharedPreferencesDefaultValues.DefaultEducationPoints) + 10);
                        editor.putInt(getResources().getString(R.string.saved_energy_key), ((sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy)) - 35));
                        editor.putInt(getResources().getString(R.string.saved_hungry_key), ((sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry)) - 35));
                        editor.putInt(getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)) - 45));
                        break;
                }
            } else if (title.equals(EduFragment.TITLE_CRIMINAL)) {
                switch (position) {
                    case get_new_friends_index:

                        break;
                    case sell_drugs_index:

                        break;
                }
            } else if (title.equals(EduFragment.TITLE_WORK)) {
                switch (position) {
                    case start_working_index:
                        json = sharedPref.getString(getResources().getString(R.string.saved_my_job_key), SharedPreferencesDefaultValues.DefaultMyJob);
                        try {
                            jsonObject = new JSONObject(json);
                        } catch (JSONException e ) { }
                        Job mJob = gson.fromJson(json, Job.class);
                        int educationPoints = sharedPref.getInt(getResources().getString(R.string.saved_education_points_key), SharedPreferencesDefaultValues.DefaultEducationPoints);
                        //int workPosition = sharedPref.getInt(getResources().getString(R.string.saved_work_position_key), SharedPreferencesDefaultValues.DefaultWorkPosition);

                        if(mJob != null)
                        {
                            double salary;
                            salary = mJob.getSalary();

                            double moneyFromAdditionalSkills = 0;
                            if(mJob.getAdditionalSkillsRes() != 0)
                            {
                                int additionalSkills = sharedPref.getInt(getResources().getString(mJob.getAdditionalSkillsRes()), 50);
                                moneyFromAdditionalSkills = (salary * 0.1 * (Math.sqrt(90.0 + additionalSkills / 10.0))) - salary;
                            }

                            double moneyFromEducation = ((salary * 0.1 * (Math.sqrt(85.0 + educationPoints * 0.1))) - salary) * (Math.sqrt(mJob.getMarkRatio()));

                            salary = salary + moneyFromAdditionalSkills + moneyFromEducation;

//                            salary = (mJob.getSalary() * (((100.0 - mJob.getMarkRatio() * 3.0) + subjectMark * mJob.getMarkRatio()) / 100.0 ) * ((100.0 + ((additionalSkills - 50.0) / 5.0)) / 100.0));
//                            if(salary <= 0)
//                                salary = mJob.getSalary() * 0.10;

                            editor.putInt(getResources().getString(R.string.saved_character_money_key), sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) + (int)Math.round(salary));
                        }

                        Random random = new Random();
                        if(random.nextInt(1000) <= mJob.getPositionPoints() && mJob.getPositionPoints() > 900)
                        {
                            try {
                                jsonObject.put("position", jsonObject.getInt("position") + 1);
                                jsonObject.put("positionPoints", 75);
                                jsonObject.put("salary", Math.round(jsonObject.getLong("salary") * (1 + 0.01 * jsonObject.getLong("salaryIncrease"))));
                                editor.putString(getResources().getString(R.string.saved_my_job_key), jsonObject.toString());

                            }
                            catch (JSONException e) { }

                            /*mJob.setPosition(mJob.getPosition() + 1);
                            mJob.setPositionPoints(75);
                            mJob.setSalary(Math.round(mJob.getSalary() * 0.10 * mJob.getSalaryIncrease()));*/
                            showAlertDialog(getContext(), sharedPref, "Promotion", "Congratulation! You got promotion in the work!");
                        }

                        try {
                            JSONArray jsonArrayOfficeJobs = new JSONArray(sharedPref.getString(getResources().getString(R.string.saved_office_jobs_list_key), SharedPreferencesDefaultValues.DefaultOfficeJobsList));
                            JSONArray jsonArrayCriminalJobs = new JSONArray(sharedPref.getString(getResources().getString(R.string.saved_criminal_jobs_list_key), SharedPreferencesDefaultValues.DefaultCriminalJobsList));
                            jsonObject.put("positionPoints", (jsonObject.getInt("positionPoints") + 15));

                            for (int i = 0; i < jsonArrayOfficeJobs.length(); i++) {
                                JSONObject object = jsonArrayOfficeJobs.getJSONObject(i);
                                if (object.getString("name").equals(jsonObject.getString("name"))) {
                                    jsonArrayOfficeJobs.put(i, jsonObject);
                                    editor.putString(getResources().getString(R.string.saved_office_jobs_list_key), jsonArrayOfficeJobs.toString());
                                    editor.putString(getResources().getString(R.string.saved_my_job_key), jsonObject.toString());
                                    editor.apply();
                                    break;
                                }
                            }
                            for (int i = 0; i < jsonArrayCriminalJobs.length(); i++) {
                                JSONObject object = jsonArrayCriminalJobs.getJSONObject(i);
                                if (object.getString("name").equals(jsonObject.getString("name"))) {
                                    jsonArrayCriminalJobs.put(i, jsonObject);
                                    editor.putString(getResources().getString(R.string.saved_criminal_points_key), jsonArrayCriminalJobs.toString());
                                    editor.putString(getResources().getString(R.string.saved_my_job_key), jsonObject.toString());
                                    editor.apply();
                                    break;
                                }
                            }
                        }
                        catch (JSONException e) { }

                        editor.putInt(getResources().getString(R.string.saved_energy_key), ((sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy)) - 30));
                        editor.putInt(getResources().getString(R.string.saved_hungry_key), ((sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry)) - 30));
                        editor.putInt(getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)) - 50));

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
                        break;
                }
            }
        }

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

        ((ProgressBar)view.findViewById(R.id.progressBar_health_dialog)).setProgress(sharedPref.getInt(getResources().getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth));
        ((ProgressBar)view.findViewById(R.id.progressBar_hungry_dialog)).setProgress(sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry));
        ((ProgressBar)view.findViewById(R.id.progressBar_energy_dialog)).setProgress(sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy));
        ((ProgressBar)view.findViewById(R.id.progressBar_happiness_dialog)).setProgress(sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness));
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

            /*JSONObject jsonObject;
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
            } catch (JSONException e)
            { }*/

            int gameScore = sharedPref.getInt(getResources().getString(R.string.saved_education_points_key), SharedPreferencesDefaultValues.DefaultEducationPoints)/ 10 + sharedPref.getInt(getResources().getString(R.string.saved_programming_skills_key),0);

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
