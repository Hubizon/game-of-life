package com.example.hubert.gameoflife.Work;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hubert.gameoflife.Education.EduFragment;
import com.example.hubert.gameoflife.Education.RecyclerViewSkillsAdapter;
import com.example.hubert.gameoflife.Education.Skill;
import com.example.hubert.gameoflife.Education.SkillsFragment;
import com.example.hubert.gameoflife.House.Fun;
import com.example.hubert.gameoflife.House.Lodging;
import com.example.hubert.gameoflife.House.Transport;
import com.example.hubert.gameoflife.MainActivity;
import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.Utils.Arrays;
import com.example.hubert.gameoflife.Utils.SharedPreferencesDefaultValues;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.hubert.gameoflife.Utils.Arrays.criminalJobsList;
import static com.example.hubert.gameoflife.Utils.Arrays.officeJobsList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChooseJobFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChooseJobFragment extends Fragment implements ChooseJobAdapter.ItemClickListener {

    public ChooseJobFragment() {
        // Required empty public constructor
    }


    public static ChooseJobFragment newInstance() {
        ChooseJobFragment fragment = new ChooseJobFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    ChooseJobAdapter adapter;

    static int page = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_choose_job, container, false);

        SharedPreferences sharedPref = MainActivity.userSharedPref;
        //SharedPreferences sharedPref = getContext().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        ((TextView) (view.findViewById(R.id.money_choose_work))).setText("$ " + sharedPref.getInt(getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney));


        // set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewChooseWork);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if(page == 1) {
            adapter = new ChooseJobAdapter(getContext(), officeJobsList, page);
            page++; }
        else {
            adapter = new ChooseJobAdapter(getContext(), criminalJobsList, page);
            page = 1;
        }
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        return view;
    }



    @Override
    public void onItemClick(View view, int position) {
        SharedPreferences sharedPref = MainActivity.userSharedPref;
        //SharedPreferences sharedPref = getContext().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        Gson gson = new Gson();
        String json;

        Job job = null;
        try
        {
            JSONArray jsonArrayOfficeJobs = new JSONArray(sharedPref.getString(getResources().getString(R.string.saved_office_jobs_list_key), SharedPreferencesDefaultValues.DefaultOfficeJobsList));
            JSONArray jsonArrayCriminalJobs = new JSONArray(sharedPref.getString(getResources().getString(R.string.saved_criminal_jobs_list_key), SharedPreferencesDefaultValues.DefaultCriminalJobsList));
            JSONObject jsonObjectOfficeJob = jsonArrayOfficeJobs.getJSONObject(position);

            if(((TextView)view.findViewById(R.id.chooseJobName)).getText().equals(jsonObjectOfficeJob.getString("name")))
                job = gson.fromJson(jsonObjectOfficeJob.toString(), Job.class);
            else
            {
                JSONObject jsonObjectCriminalJob = jsonArrayCriminalJobs.getJSONObject(position);
                job = gson.fromJson(jsonObjectCriminalJob.toString(), Job.class);
            }

        } catch (JSONException e) { }



        String toToast = "";
        if(job != null) {
            if (job.getMinPhoneNeeded() != null) {
                if (sharedPref.getString(getResources().getString(R.string.saved_my_phone_key), SharedPreferencesDefaultValues.DefaultMyPhone) != null) {
                    json = sharedPref.getString(getResources().getString(R.string.saved_my_phone_key), SharedPreferencesDefaultValues.DefaultMyPhone);
                    Fun mFun = gson.fromJson(json, Fun.class);
                    if (mFun.getGivenFun() < job.getMinPhoneNeeded().getGivenFun())
                        toToast += "\n" + job.getMinPhoneNeeded().getName();
                } else
                    toToast += "\n" + job.getMinPhoneNeeded().getName();
            }
            if(job.getMinTvNeeded() != null)
            {
                if (sharedPref.getString(getResources().getString(R.string.saved_my_tv_key), SharedPreferencesDefaultValues.DefaultMyPhone) != null) {
                    json = sharedPref.getString(getResources().getString(R.string.saved_my_tv_key), SharedPreferencesDefaultValues.DefaultMyPhone);
                    Fun mFun = gson.fromJson(json, Fun.class);
                    if (mFun.getGivenFun() < job.getMinTvNeeded().getGivenFun())
                        toToast += "\n" + job.getMinTvNeeded().getName();
                } else
                    toToast += "\n" + job.getMinTvNeeded().getName();
            }
            if(job.getMinComputerNeeded() != null)
            {
                if (sharedPref.getString(getResources().getString(R.string.saved_my_computer_key), SharedPreferencesDefaultValues.DefaultMyComputer) != null) {
                    json = sharedPref.getString(getResources().getString(R.string.saved_my_computer_key), SharedPreferencesDefaultValues.DefaultMyComputer);
                    Fun mFun = gson.fromJson(json, Fun.class);
                    if (mFun.getGivenFun() < job.getMinComputerNeeded().getGivenFun())
                        toToast += "\n" + job.getMinComputerNeeded().getName();
                } else
                    toToast += "\n" + job.getMinComputerNeeded().getName();
            }

            if (job.getMinLodgingNeeded() != null) {
                //   if (!sharedPref.getString(getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodging).equals(SharedPreferencesDefaultValues.DefaultMyLodging) || sharedPref.getString(getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodging).equals(SharedPreferencesDefaultValues.DefaultMyLodgingAfter18)) {
                json = sharedPref.getString(getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodging);
                Lodging mLodging = gson.fromJson(json, Lodging.class);
                if (mLodging.getPrice() < job.getMinLodgingNeeded().getPrice())
                    toToast += "\n" + job.getMinLodgingNeeded().getName();
                //   } else
                //     toToast += "\n" + job.getMinLodgingNeeded().getName();
            }

            if (job.getMinTransportNeeded() != null) {
                //       if (!sharedPref.getString(getResources().getString(R.string.saved_my_transport_key), SharedPreferencesDefaultValues.DefaultMyTransport).equals(SharedPreferencesDefaultValues.DefaultMyTransport)) {
                json = sharedPref.getString(getResources().getString(R.string.saved_my_transport_key), SharedPreferencesDefaultValues.DefaultMyTransport);
                Transport mTransport = gson.fromJson(json, Transport.class);
                if (mTransport.getPrice() < job.getMinTransportNeeded().getPrice())
                    toToast += "\n" + job.getMinTransportNeeded().getName();
                //   } else
                //       toToast += "\n" + job.getMinTransportNeeded().getName();
            }

            if (job.getSkillsNeeded() != null) {
                try {
                    JSONArray skillsNeeded = new JSONArray(gson.toJson(job.getSkillsNeeded()));
                    JSONArray eduSkills = new JSONArray(sharedPref.getString(getResources().getString(R.string.saved_skills_education_list_key), SharedPreferencesDefaultValues.DefaultSkillsEducationList));
                    JSONArray crimSkills = new JSONArray(sharedPref.getString(getResources().getString(R.string.saved_skills_criminal_list_key), SharedPreferencesDefaultValues.DefaultSkillsEducationList));

                    for (int i = 0; i < skillsNeeded.length(); i++) {
                        JSONObject jsonObject = skillsNeeded.getJSONObject(i);

                        boolean haveThisSkill = false;
                        for (int x = 0; x < eduSkills.length(); x++) {
                            JSONObject jsonObjectSkill = eduSkills.getJSONObject(x);
                            if(jsonObject.getString("name").equals(jsonObjectSkill.getString("name")))
                                if(jsonObjectSkill.getBoolean("isBought")) {
                                    haveThisSkill = true;
                                    break; }
                        }
                        for (int x = 0; x < crimSkills.length(); x++) {
                            JSONObject jsonObjectSkill = crimSkills.getJSONObject(x);
                            if(jsonObject.getString("name").equals(jsonObjectSkill.getString("name")))
                                if(jsonObjectSkill.getBoolean("isBought")) {
                                    haveThisSkill = true;
                                    break; }
                        }
                        if (!haveThisSkill)
                            toToast += "\n" + jsonObject.getString("name");
                    }
                } catch (JSONException e) { }
            }

            if(job instanceof CriminalJob)
            {
                CriminalJob criminalJob = (CriminalJob)job;
                if(criminalJob.getWeaponsNeeded() != null)
                {
                    try {
                        JSONArray weaponsNeeded = new JSONArray(gson.toJson(criminalJob.getWeaponsNeeded()));
                        JSONArray allWeapons = new JSONArray(sharedPref.getString(getResources().getString(R.string.saved_weapons_list_key), SharedPreferencesDefaultValues.DefaultWeapons));

                        for (int y = 0; y < weaponsNeeded.length(); y++) {
                            JSONObject jsonObject = weaponsNeeded.getJSONObject(y);

                            boolean haveThisWeapon = false;
                            for (int x = 0; x < allWeapons.length(); x++) {
                                JSONObject jsonObjectWeapon = allWeapons.getJSONObject(x);
                                if(jsonObject.getString("name").equals(jsonObjectWeapon.getString("name")))
                                    if(jsonObjectWeapon.getBoolean("isBought")) {
                                        haveThisWeapon = true;
                                        break;
                                    }
                            }
                            if (!haveThisWeapon)
                                toToast += "\n" + jsonObject.getString("name");
                        }
                    } catch (JSONException e) { }
                }
            }

            if (toToast.equals("")) {
                //editor.putBoolean(getResources().getString(R.string.saved_is_in_school_now_key), false);
                editor.putString(getResources().getString(R.string.saved_my_job_key), gson.toJson(job));
                //EduFragment.changeWorkStatus(true);
                //mCallback.UpdateJobStatus(true);
            } else
                Toast.makeText(getContext(), "Unfortunately, you don't have a:" + toToast, Toast.LENGTH_SHORT).show();
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
