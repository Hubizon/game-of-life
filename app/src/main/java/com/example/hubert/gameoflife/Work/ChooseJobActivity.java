package com.example.hubert.gameoflife.Work;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hubert.gameoflife.Education.Skill;
import com.example.hubert.gameoflife.House.Fun;
import com.example.hubert.gameoflife.House.Lodging;
import com.example.hubert.gameoflife.House.Transport;
import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.Shop.ShopFragment;
import com.example.hubert.gameoflife.Utils.SharedPreferencesDefaultValues;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ChooseJobActivity extends AppCompatActivity implements ChooseJobAdapter.ItemClickListener{

    int id;
    View view;

    //TODO: zrobic listę z przedmiotów
    /*
    public static Job[] officeJobsList = new Job[] {
            new Job("Lawyer", 80, 90, 100, 15, "History", R.string.saved_communication_skills_key),
            new Job("Reporter", 50, 65, 80, 5, "English", R.string.saved_communication_skills_key),
            new Job("Engraver", 65, 75, 85, 7, "Art", R.string.saved_communication_skills_key),
            new Job("Programmer", 60, 75, 90, 7, "Information Technology", R.string.saved_programming_skills_key),
            new Job("Businessman", 80, 110, 150, 50, "All", R.string.saved_communication_skills_key)
     };
     */
    public static final Job[] officeJobsList = new Job[] {
            new Job("Beggar", 20, 30, 50, 1,  0, null, null, null, null),
            new Job("Salesperson", 20, 30, 50, 1,  0, null, null, ShopFragment.transportList[0], null),
            new Job("Dustman", 45, 50, 65, 1, 0, null, null, ShopFragment.transportList[0], null),
            new Job("Teacher", 30, 55, 75, 5,  R.string.saved_communication_skills_key, ShopFragment.funList[1], ShopFragment.lodgingList[0], ShopFragment.transportList[2], null ),
            new Job("Painter", 45, 60, 85, 10,  R.string.saved_drawing_skills_key, ShopFragment.funList[3], ShopFragment.lodgingList[1], ShopFragment.transportList[3], null),
            new Job("YouTuber", 30, 75, 85, 5,  R.string.saved_recording_skills_key, ShopFragment.funList[8], ShopFragment.lodgingList[2], ShopFragment.transportList[1], new Skill[] { SharedPreferencesDefaultValues.PassPrimarySchool, SharedPreferencesDefaultValues.PassSecondarySchool }),
            new Job("Programmer", 60, 75, 90, 7,  R.string.saved_programming_skills_key, ShopFragment.funList[8], ShopFragment.lodgingList[3], ShopFragment.transportList[5], new Skill[] { SharedPreferencesDefaultValues.PassPrimarySchool, SharedPreferencesDefaultValues.PassSecondarySchool, SharedPreferencesDefaultValues.PassHighSchool }),
            new Job("Footballer", 60, 65, 120, 5,  0, ShopFragment.funList[5], ShopFragment.lodgingList[3], ShopFragment.transportList[5], new Skill[] { SharedPreferencesDefaultValues.PassPrimarySchool, SharedPreferencesDefaultValues.PassHighSchool }),
            new Job("Scientist", 40, 75, 95, 50,  0, ShopFragment.funList[8], ShopFragment.lodgingList[4], ShopFragment.transportList[5], new Skill[] { SharedPreferencesDefaultValues.PassPrimarySchool, SharedPreferencesDefaultValues.PassSecondarySchool }),
            new Job("Doctor", 60, 75, 95, 50,  0, ShopFragment.funList[8], ShopFragment.lodgingList[5], ShopFragment.transportList[5], new Skill[] { SharedPreferencesDefaultValues.PassPrimarySchool, SharedPreferencesDefaultValues.PassSecondarySchool, SharedPreferencesDefaultValues.PassHighSchool }),
            new Job("Lawyer", 80, 90, 100, 15,  R.string.saved_communication_skills_key, ShopFragment.funList[8], ShopFragment.lodgingList[4], ShopFragment.transportList[6], new Skill[] { SharedPreferencesDefaultValues.PassPrimarySchool, SharedPreferencesDefaultValues.PassSecondarySchool, SharedPreferencesDefaultValues.PassHighSchool, SharedPreferencesDefaultValues.StudyAtCollage }),
            new Job("Businessman", 80, 110, 150, 50,  R.string.saved_communication_skills_key, ShopFragment.funList[8], ShopFragment.lodgingList[5], ShopFragment.transportList[6], new Skill[] { SharedPreferencesDefaultValues.PassPrimarySchool, SharedPreferencesDefaultValues.PassSecondarySchool }),



            /*new CriminalJob("Thief", 45, 55, 65, 0, "", R.string.saved_criminal_points_key, 75),
            new CriminalJob("Drug dealer", 60, 75, 90, 0, "", R.string.saved_criminal_points_key, 40),
            new CriminalJob("Terrorist", 70, 75, 80, 0, "", R.string.saved_criminal_points_key, 25),
            new CriminalJob("Kidnap kids", 75, 85, 95, 0, "", R.string.saved_criminal_points_key, 20),
            new CriminalJob("Mafia member", 90, 100, 110, 0, "", R.string.saved_criminal_points_key, 15),
            new CriminalJob("Assasin", 125, 140, 160, 0, "", R.string.saved_criminal_points_key, 10),*/
    };



    public static CriminalJob[] criminalJobsList = new CriminalJob[] {
            /*new CriminalJob("Pickpocket", 30, 35, 45, 0, "", R.string.saved_criminal_points_key, 125),
            new CriminalJob("Thief", 45, 55, 65, 0, "", R.string.saved_criminal_points_key, 75),
            new CriminalJob("Drug dealer", 60, 75, 90, 0, "", R.string.saved_criminal_points_key, 40),
            new CriminalJob("Terrorist", 70, 75, 80, 0, "", R.string.saved_criminal_points_key, 25),
            new CriminalJob("Kidnap kids", 75, 85, 95, 0, "", R.string.saved_criminal_points_key, 20),
            new CriminalJob("Mafia member", 90, 100, 110, 0, "", R.string.saved_criminal_points_key, 15),
            new CriminalJob("Assasin", 125, 140, 160, 0, "", R.string.saved_criminal_points_key, 10),*/
    };

    public static Job[] artsWorksList = new Job[] {
            /*new Job("Writer", 40, 60, 90, 10, "English", R.string.saved_drawing_skills_key),
            new Job("Painter", 45, 60, 85, 10, "English", R.string.saved_drawing_skills_key),*/
    };

    public static Job[] outsideWorksList = new Job[] {
            /*new Job("Footballer", 60, 65, 120, 5, "Psychical Education", 0),
            new Job("Basketball Player", 55, 70, 115, 5, "Psychical Education", 0),
            new Job("Swimmer", 50, 75, 85, 5, "Psychical Education", 0),
            new Job("Runner", 40, 50, 85, 5, "Psychical Education", 0),
            new Job("Swimmer", 40, 50, 90, 5, "Psychical Education", 0),
            new Job("Cyclist", 40, 50, 100, 5, "Psychical Education", 0),
            new Job("Geologist", 55, 65, 75, 15, "Biology", 0),
            new Job("Dustman", 45, 50, 65, 1, "Psychical Education", 0),**/
    };

    public static Job[] otherWorksList = new Job[] {
            /*new Job("YouTuber", 30, 75, 85, 5, "English", R.string.saved_recording_skills_key),
            new Job("Streamer", 25, 75, 85, 5, "English", R.string.saved_recording_skills_key),
            new Job("Blogger", 35, 75, 85, 5, "English", R.string.saved_communication_skills_key),
            new Job("Doctor", 60, 75, 95, 50, "Biology", 0),
            new Job("Teacher", 30, 55, 75, 5, "All", R.string.saved_communication_skills_key),
            new Job("Scientist", 40, 75, 95, 50, "All", 0),
            new Job("Salesperson", 20, 30, 50, 1, "Mathematics", 0),*/
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_job);
        view = new View(this);

        ArrayList<String> worksNames = new ArrayList<>();
        ArrayList<String> worksSalary = new ArrayList<>();

        SharedPreferences sharedPref = getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        ((TextView) (findViewById(R.id.money_choose_work))).setText("$ " + sharedPref.getInt(getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney));

        id = getIntent().getIntExtra(getResources().getString(R.string.send_shop_click_id), R.id.cardview_food);
        switch (id) {
            case R.id.cardview_officeWork:
                for(int i = 0; i < officeJobsList.length; i++)
                    worksNames.add(officeJobsList[i].getName());
                for(int i = 0; i < officeJobsList.length; i++)
                    worksSalary.add("$" + officeJobsList[i].getSalary());
                ((TextView) (findViewById(R.id.workToChoose))).setText("OFFICE WORK");
                break;

            case R.id.cardview_criminalWork:
                for(int i = 0; i < criminalJobsList.length; i++)
                    worksNames.add(criminalJobsList[i].getName());
                for(int i = 0; i < criminalJobsList.length; i++)
                    worksSalary.add("$" + criminalJobsList[i].getSalary());
                ((TextView) (findViewById(R.id.workToChoose))).setText("CRIMINAL WORK");
                break;

            case R.id.cardview_artsWork:
                for(int i = 0; i < artsWorksList.length; i++)
                    worksNames.add(artsWorksList[i].getName());
                for(int i = 0; i < artsWorksList.length; i++)
                    worksSalary.add("$" + artsWorksList[i].getSalary());
                ((TextView) (findViewById(R.id.workToChoose))).setText("ARTS WORK");
                break;

            case R.id.cardview_outsideWork:
                for(int i = 0; i < outsideWorksList.length; i++)
                    worksNames.add(outsideWorksList[i].getName());
                for(int i = 0; i < outsideWorksList.length; i++)
                    worksSalary.add("$" + outsideWorksList[i].getSalary());
                ((TextView) (findViewById(R.id.workToChoose))).setText("OUTSIDE WORK");
                break;

            case R.id.cardview_otherWork:
                for(int i = 0; i < otherWorksList.length; i++)
                    worksNames.add(otherWorksList[i].getName());
                for(int i = 0; i < otherWorksList.length; i++)
                    worksSalary.add("$" + otherWorksList[i].getSalary());
                ((TextView) (findViewById(R.id.workToChoose))).setText("OTHER WORK");
                break;

            default:
                break;
        }

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewChooseWork);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ChooseJobAdapter(this, officeJobsList);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    ChooseJobAdapter adapter;

    @Override
    public void onItemClick(View view, int position) {

        SharedPreferences sharedPref = getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        Gson gson = new Gson();
        String json;

        Job job = officeJobsList[position];
        String toToast = "";
        if(job != null) {
            if (job.getMinFunNeeded() != null) {
                switch (job.getMinFunNeeded().getType()) {
                    case "Phone":
                        if (sharedPref.getString(getResources().getString(R.string.saved_my_phone_key), SharedPreferencesDefaultValues.DefaultMyPhone) != null) {
                            json = sharedPref.getString(getResources().getString(R.string.saved_my_phone_key), SharedPreferencesDefaultValues.DefaultMyPhone);
                            Fun mFun = gson.fromJson(json, Fun.class);
                            if (mFun.getGivenFun() <= job.getMinFunNeeded().getGivenFun())
                                toToast += "\n" + job.getMinFunNeeded().getName();
                        } else
                            toToast += "\n" + job.getMinFunNeeded().getName();
                        break;

                    case "TV":
                        if (sharedPref.getString(getResources().getString(R.string.saved_my_tv_key), SharedPreferencesDefaultValues.DefaultMyPhone) != null) {
                            json = sharedPref.getString(getResources().getString(R.string.saved_my_tv_key), SharedPreferencesDefaultValues.DefaultMyPhone);
                            Fun mFun = gson.fromJson(json, Fun.class);
                            if (mFun.getGivenFun() <= job.getMinFunNeeded().getGivenFun())
                                toToast += "\n" + job.getMinFunNeeded().getName();
                        } else
                            toToast += "\n" + job.getMinFunNeeded().getName();
                        break;

                    case "Computer":
                        if (sharedPref.getString(getResources().getString(R.string.saved_my_computer_key), SharedPreferencesDefaultValues.DefaultMyComputer) != null) {
                            json = sharedPref.getString(getResources().getString(R.string.saved_my_computer_key), SharedPreferencesDefaultValues.DefaultMyComputer);
                            Fun mFun = gson.fromJson(json, Fun.class);
                            if (mFun.getGivenFun() <= job.getMinFunNeeded().getGivenFun())
                                toToast += "\n" + job.getMinFunNeeded().getName();
                        } else
                            toToast += "\n" + job.getMinFunNeeded().getName();
                        break;
                }
            }

            if (job.getMinLodgingNeeded() != null) {
                if (!sharedPref.getString(getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodging).equals(SharedPreferencesDefaultValues.DefaultMyLodging) || sharedPref.getString(getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodging).equals(SharedPreferencesDefaultValues.DefaultMyLodgingAfter18)) {
                    json = sharedPref.getString(getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodging);
                    Lodging mLodging = gson.fromJson(json, Lodging.class);
                    if (mLodging.getPrice() <= job.getMinFunNeeded().getPrice())
                        toToast += "\n" + job.getMinLodgingNeeded().getName();
                } else
                    toToast += "\n" + job.getMinLodgingNeeded().getName();
            }

            if (job.getMinTransportNeeded() != null) {
                if (!sharedPref.getString(getResources().getString(R.string.saved_my_transport_key), SharedPreferencesDefaultValues.DefaultMyTransport).equals(SharedPreferencesDefaultValues.DefaultMyTransport)) {
                    json = sharedPref.getString(getResources().getString(R.string.saved_my_transport_key), SharedPreferencesDefaultValues.DefaultMyTransport);
                    Transport mTransport = gson.fromJson(json, Transport.class);
                    if (mTransport.getPrice() <= job.getMinTransportNeeded().getPrice())
                        toToast += "\n" + job.getMinTransportNeeded().getName();
                } else
                    toToast += "\n" + job.getMinTransportNeeded().getName();
            }

            if (job.getSkillsNeeded() != null) {
                try {
                    JSONArray jsonArray = new JSONArray(gson.toJson(job.getSkillsNeeded()));

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        if (!jsonObject.getBoolean("isLearned"))
                            toToast += "\n" + jsonObject.getString("name");
                    }
                } catch (JSONException e) {
                }
            }

            if (toToast.equals("")) {
                editor.putBoolean(getResources().getString(R.string.saved_is_in_school_now_key), false);
                editor.putString(getResources().getString(R.string.saved_my_job_key), gson.toJson(job));
                //TODO: Michal!!! Wlacz tu fragment edu
            } else
                Toast.makeText(this, "Unfortunately, you don't have a:" + toToast, Toast.LENGTH_SHORT).show();
        }

        /*switch (id) {
            case R.id.cardview_officeWork:
                editor.putBoolean(getResources().getString(R.string.saved_is_in_school_now_key), false);
                editor.putString(getResources().getString(R.string.saved_my_job_key), gson.toJson(officeJobsList[position]));
                break;

            case R.id.cardview_criminalWork:
                editor.putBoolean(getResources().getString(R.string.saved_is_in_school_now_key), false);
                editor.putString(getResources().getString(R.string.saved_my_job_key), gson.toJson(criminalJobsList[position]));
                break;

            case R.id.cardview_artsWork:
                editor.putBoolean(getResources().getString(R.string.saved_is_in_school_now_key), false);
                editor.putString(getResources().getString(R.string.saved_my_job_key), gson.toJson(artsWorksList[position]));
                break;

            case R.id.cardview_outsideWork:
                editor.putBoolean(getResources().getString(R.string.saved_is_in_school_now_key), false);
                editor.putString(getResources().getString(R.string.saved_my_job_key), gson.toJson(outsideWorksList[position]));
                break;

            case R.id.cardview_otherWork:
                editor.putBoolean(getResources().getString(R.string.saved_is_in_school_now_key), false);
                editor.putString(getResources().getString(R.string.saved_my_job_key), gson.toJson(otherWorksList[position]));
                break;

            default:
                break;
        }*/
        editor.apply();
    }
}
