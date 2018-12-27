package com.howky.hubert.gameoflife.utils;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.howky.hubert.gameoflife.MyApplication;
import com.howky.hubert.gameoflife.education.EduFragment;
import com.howky.hubert.gameoflife.house.Fun;
import com.howky.hubert.gameoflife.house.Lodging;
import com.howky.hubert.gameoflife.house.Transport;
import com.howky.hubert.gameoflife.MainActivity;
import com.howky.hubert.gameoflife.R;
import com.howky.hubert.gameoflife.SettingsActivity;
import com.howky.hubert.gameoflife.work.CriminalJob;
import com.howky.hubert.gameoflife.work.Job;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class  MyDialogFragment extends DialogFragment  {

    private static final String BUNDLE_ID = "bundle_id";

    public MyDialogFragment() {}

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    private View view;

    private int mId;
    private final Gson gson = new Gson();
    private String json;
    private JSONObject jsonObject;

    private static String title;
    private static int position;

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
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        boolean isDark = sharedPreferences.getBoolean(SettingsActivity.DARK_SWITCH_KEY, false);
        if (isDark) {
            setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Material_Dialog_MinWidth);
        } else {
            setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Material_Light_Dialog_MinWidth);
        }

        Bundle args = getArguments();
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
            switch (title) {
                case EduFragment.TITLE_SCHOOL:
                    switch (position) {
                        case go_to_school_index:
                            getDialog().setTitle(R.string.learning);
                            break;
                        case hang_around_index:

                            break;
                    }
                    break;
                case EduFragment.TITLE_CRIMINAL:
                    switch (position) {
                        case get_new_friends_index:
                            getDialog().setTitle(getResources().getString(R.string.getting_new_friends));
                            break;
                        case sell_drugs_index:
                            getDialog().setTitle(getResources().getString(R.string.selling_drugs));
                            break;
                    }
                    break;
                case EduFragment.TITLE_WORK:
                    switch (position) {
                        case start_working_index:
                            getDialog().setTitle(R.string.working);
                        case work_hard_index:

                            break;
                    }
                    break;
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
                ((TextView)view.findViewById(R.id.textToDoingThing)).setText("To end that: ");
                view.findViewById(R.id.progressBar_doing_thing).setVisibility(View.VISIBLE);
                break;

            case R.id.drawSomethingComputer:
                getDialog().setTitle(R.string.drawing_something);
                view.findViewById(R.id.textToDoingThing).setVisibility(View.VISIBLE);
                ((TextView)view.findViewById(R.id.textToDoingThing)).setText("To end that: ");
                view.findViewById(R.id.progressBar_doing_thing).setVisibility(View.VISIBLE);
                break;

            case R.id.writePoemComputer:
                getDialog().setTitle(R.string.writing_poem);
                view.findViewById(R.id.textToDoingThing).setVisibility(View.VISIBLE);
                ((TextView)view.findViewById(R.id.textToDoingThing)).setText("To end that: ");
                view.findViewById(R.id.progressBar_doing_thing).setVisibility(View.VISIBLE);
                break;

            case R.id.recordMoviesComputer:
                getDialog().setTitle(R.string.recording_movies);
                view.findViewById(R.id.textToDoingThing).setVisibility(View.VISIBLE);
                ((TextView)view.findViewById(R.id.textToDoingThing)).setText("To end that: ");
                view.findViewById(R.id.progressBar_doing_thing).setVisibility(View.VISIBLE);
                break;

            case R.id.button_love_takeSomewhere:
                getDialog().setTitle(R.string.going_somewhere);
                view.findViewById(R.id.textToDoingThing).setVisibility(View.VISIBLE);
                ((TextView)view.findViewById(R.id.textToDoingThing)).setText("Relationship: ");
                view.findViewById(R.id.progressBar_doing_thing).setVisibility(View.VISIBLE);
                break;

            default:
                break;
        }

        sharedPref = MyApplication.userSharedPref;

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
    private final Runnable mTimerRunnable = new Runnable() {
        @Override
        public void run() {
            onTimerDelay();
            mHandler.postDelayed(mTimerRunnable, timerDelay);
        }
    };

    private void onTimerDelay()
    {
        sharedPref = MyApplication.userSharedPref;
        editor = sharedPref.edit();
        Random r = new Random();

        if(mId == R.id.list_item_artist_name)
        {
            switch (title) {
                case EduFragment.TITLE_SCHOOL:
                    switch (position) {
                        case go_to_school_index:
                            editor.putInt(getResources().getString(R.string.saved_education_points_key), sharedPref.getInt(getResources().getString(R.string.saved_education_points_key), SharedPreferencesDefaultValues.DefaultEducationPoints) + 10);
                            editor.putInt(getResources().getString(R.string.saved_energy_key), ((sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy)) - 15));
                            editor.putInt(getResources().getString(R.string.saved_hungry_key), ((sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry)) - 15));
                            editor.putInt(getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)) - 25));
                            break;
                    }
                    break;
                case EduFragment.TITLE_CRIMINAL:
                    switch (position) {
                        case get_new_friends_index:
                            if (sharedPref.getInt(getResources().getString(R.string.saved_criminal_points_key), SharedPreferencesDefaultValues.DefaultEducationPoints) > 1000) {
                                editor.putInt(getResources().getString(R.string.saved_criminal_points_key), sharedPref.getInt(getResources().getString(R.string.saved_criminal_points_key), 1000));
                                Toast.makeText(getContext(), "You already have a lot of friends.", Toast.LENGTH_SHORT).show();
                            } else
                                editor.putInt(getResources().getString(R.string.saved_criminal_points_key), sharedPref.getInt(getResources().getString(R.string.saved_criminal_points_key), SharedPreferencesDefaultValues.DefaultCriminalPoints) + 10);
                            editor.putInt(getResources().getString(R.string.saved_energy_key), ((sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy)) - 20));
                            editor.putInt(getResources().getString(R.string.saved_hungry_key), ((sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry)) - 20));
                            editor.putInt(getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)) - 15));
                            break;

                        case sell_drugs_index:
                            double multiplyMoneyForDrugs = ((sharedPref.getInt(getResources().getString(R.string.saved_criminal_points_key), SharedPreferencesDefaultValues.DefaultCriminalPoints) - 100) / 25);
                            if(multiplyMoneyForDrugs < 0)
                                multiplyMoneyForDrugs = 0;
                            double moneyForDrugs = 20 * multiplyMoneyForDrugs + 20;
                            editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) + (int)moneyForDrugs));
                            editor.putInt(getResources().getString(R.string.saved_energy_key), ((sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy)) - 20));
                            editor.putInt(getResources().getString(R.string.saved_hungry_key), ((sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry)) - 20));
                            editor.putInt(getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)) - 20));
                            Random random = new Random();
                            if(random.nextInt(25) == 1)
                            {
                                editor.putInt(getResources().getString(R.string.saved_character_money_key), 0);
                                Toast.makeText(getActivity().getApplicationContext(), ("You got busted! You lost all your money."), Toast.LENGTH_LONG).show();
                                if (r.nextInt(25) == 1)
                                    if (sharedPref.getBoolean(getResources().getString(R.string.saved_have_safe_key), SharedPreferencesDefaultValues.DefaultHaveSafe)) {
                                        editor.putInt(getResources().getString(R.string.saved_money_in_safe_key), 0);
                                        Toast.makeText(getActivity().getApplicationContext(), ("Policeman found your safe! You lost all your money in safe."), Toast.LENGTH_LONG).show();
                                    }
                            }
                            break;
                    }
                    break;
                case EduFragment.TITLE_WORK:
                    switch (position) {
                        case start_working_index:
                            json = sharedPref.getString(getResources().getString(R.string.saved_my_job_key), SharedPreferencesDefaultValues.DefaultMyJob);
                            try {
                                jsonObject = new JSONObject(json);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Job mJob = gson.fromJson(json, Job.class);
                            int educationPoints = sharedPref.getInt(getResources().getString(R.string.saved_education_points_key), SharedPreferencesDefaultValues.DefaultEducationPoints);

                            if (mJob != null) {
                                double salary;
                                salary = mJob.getSalary();

                                double moneyFromAdditionalSkills = 0;
                                if (mJob.getAdditionalSkillsRes() != 0) {
                                    int additionalSkills = sharedPref.getInt(getResources().getString(mJob.getAdditionalSkillsRes()), 50);
                                    moneyFromAdditionalSkills = (salary * 0.1 * (Math.sqrt(90.0 + additionalSkills / 10.0))) - salary;//2131689672
                                }

                                double moneyFromEducation = ((salary * 0.1 * (Math.sqrt(85.0 + educationPoints * 0.1))) - salary) * (Math.sqrt(mJob.getMarkRatio()));

                                salary = salary + moneyFromAdditionalSkills + moneyFromEducation;

                                editor.putInt(getResources().getString(R.string.saved_character_money_key), sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) + (int) Math.round(salary));
                            }

                            Random random = new Random();
                            if (random.nextInt(1000) <= mJob.getPositionPoints() && mJob.getPositionPoints() > 900) {
                                try {
                                    jsonObject.put("position", jsonObject.getInt("position") + 1);
                                    jsonObject.put("positionPoints", 75);
                                    jsonObject.put("salary", Math.round(jsonObject.getLong("salary") * (1 + 0.01 * jsonObject.getLong("salaryIncrease"))));
                                    editor.putString(getResources().getString(R.string.saved_my_job_key), jsonObject.toString());

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                (new Dialogs(getContext())).showAlertDialog(getContext(), "Promotion", "Congratulation! You got promotion in the work!");
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
                                        editor.putString(getResources().getString(R.string.saved_criminal_jobs_list_key), jsonArrayCriminalJobs.toString());
                                        editor.putString(getResources().getString(R.string.saved_my_job_key), jsonObject.toString());
                                        editor.apply();
                                        break;
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            editor.putInt(getResources().getString(R.string.saved_energy_key), ((sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy)) - 20));
                            editor.putInt(getResources().getString(R.string.saved_hungry_key), ((sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry)) - 20));
                            editor.putInt(getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)) - 30));

                            if (mJob instanceof CriminalJob)
                                if (r.nextInt(((CriminalJob) mJob).getChanceToGetBusted()) == 1) {
                                    editor.putInt(getResources().getString(R.string.saved_character_money_key), 0);
                                    Toast.makeText(getActivity().getApplicationContext(), ("You got busted! You lost all your money."), Toast.LENGTH_LONG).show();
                                    if (r.nextInt(25) == 1)
                                        if (sharedPref.getBoolean(getResources().getString(R.string.saved_have_safe_key), SharedPreferencesDefaultValues.DefaultHaveSafe)) {
                                            editor.putInt(getResources().getString(R.string.saved_money_in_safe_key), 0);
                                            Toast.makeText(getActivity().getApplicationContext(), ("Policeman found your safe! You lost all your money in safe."), Toast.LENGTH_LONG).show();
                                        }
                                }
                            break;
                    }
                    break;
            }
        }

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
                editor.putString(getResources().getString(R.string.saved_games_key), doSomething(getResources().getString(R.string.saved_progress_making_game_key), getResources().getString(R.string.saved_programming_skills_key), getResources().getString(R.string.saved_games_key), "You successfully made a game"));
                break;

            case R.id.drawSomethingComputer:
                editor.putString(getResources().getString(R.string.saved_drawings_key), doSomething(getResources().getString(R.string.saved_progress_making_drawings_key), getResources().getString(R.string.saved_drawing_skills_key), getResources().getString(R.string.saved_games_key), "You successfully made a drawing"));
                break;

            case R.id.writePoemComputer:
                editor.putString(getResources().getString(R.string.saved_books_key), doSomething(getResources().getString(R.string.saved_progress_making_book_key), getResources().getString(R.string.saved_writing_skills_key), getResources().getString(R.string.saved_games_key), "You successfully wrote a book"));
                break;

            case R.id.recordMoviesComputer:
                editor.putString(getResources().getString(R.string.saved_movies_key), doSomething(getResources().getString(R.string.saved_progress_making_movies_key), getResources().getString(R.string.saved_recording_skills_key), getResources().getString(R.string.saved_games_key), "You successfully made a movie"));
                break;

            case R.id.button_love_takeSomewhere:
                editor.putInt(getResources().getString(R.string.saved_hungry_key), ((sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry)) - 20));
                editor.putInt(getResources().getString(R.string.saved_energy_key), ((sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy)) - 20));
                editor.putInt(getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)) + 10));
                editor.putInt(getResources().getString(R.string.saved_love_relations_key), (sharedPref.getInt(getResources().getString(R.string.saved_love_relations_key), SharedPreferencesDefaultValues.DefaultLoveRelations) + 25));
                ((ProgressBar)view.findViewById(R.id.progressBar_doing_thing)).setProgress((sharedPref.getInt(getResources().getString(R.string.saved_love_relations_key), SharedPreferencesDefaultValues.DefaultLoveRelations)));
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

        ((TextView)view.findViewById(R.id.money_dialog)).setText(getString(R.string.money_formatted, getString(R.string.currency),
                sharedPref.getInt(getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)));

        ((TextView) view.findViewById(R.id.time_dialog)).setText(getString(R.string.date_format,
                sharedPref.getInt(getString(R.string.saved_date_years_key), SharedPreferencesDefaultValues.DefaultDateYears),
                sharedPref.getInt(getString(R.string.saved_date_months_key), SharedPreferencesDefaultValues.DefaultDateMonths),
                sharedPref.getInt(getString(R.string.saved_date_days_key), SharedPreferencesDefaultValues.DefaultDateDays),
                sharedPref.getInt(getString(R.string.saved_time_hours_key), SharedPreferencesDefaultValues.DefaultTimeHours)));

        ((ProgressBar)view.findViewById(R.id.progressBar_health_dialog)).setProgress(sharedPref.getInt(getResources().getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth));
        ((ProgressBar)view.findViewById(R.id.progressBar_hungry_dialog)).setProgress(sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry));
        ((ProgressBar)view.findViewById(R.id.progressBar_energy_dialog)).setProgress(sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy));
        ((ProgressBar)view.findViewById(R.id.progressBar_happiness_dialog)).setProgress(sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness));
    }

    private String doSomething(String resSavedProgress, String resSavedSkills, String resSavedList, String textWhenEnd) {
        editor.putInt(resSavedProgress, (sharedPref.getInt(resSavedProgress, 0) + 1));
        editor.putInt(getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)) - 10));
        editor.putInt(getResources().getString(R.string.saved_hungry_key), ((sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry)) - 10));
        editor.putInt(getResources().getString(R.string.saved_energy_key), ((sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy)) - 30));

        ((ProgressBar) view.findViewById(R.id.progressBar_doing_thing)).setProgress(((ProgressBar) view.findViewById(R.id.progressBar_doing_thing)).getProgress() + 20 );
        editor.putInt(resSavedProgress, ((ProgressBar) view.findViewById(R.id.progressBar_doing_thing)).getProgress());

        if (sharedPref.getInt(resSavedProgress, 0) >= 10) {
            editor.putInt(resSavedSkills, (sharedPref.getInt(resSavedSkills, 0) + 2));
            editor.putInt(resSavedSkills, 0);
            editor.putInt(resSavedProgress, 0);
            editor.apply();

            int gameScore = sharedPref.getInt(getResources().getString(R.string.saved_education_points_key), SharedPreferencesDefaultValues.DefaultEducationPoints) / 10 + sharedPref.getInt(getResources().getString(R.string.saved_programming_skills_key),0);

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
                JSONArray jsonArray = new JSONArray();
                if(!sharedPref.getString(resSavedList, SharedPreferencesDefaultValues.DefaultGamesList).isEmpty())
                    jsonArray = new JSONArray(sharedPref.getString(resSavedList, SharedPreferencesDefaultValues.DefaultGamesList));

                jsonArray.put(toJsonArray);
                return jsonArray.toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Toast.makeText(getContext(), textWhenEnd, Toast.LENGTH_SHORT).show();
        }
        return "";
    }

    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mTimerRunnable);
    }

    private int timerDelay = 1000;
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
