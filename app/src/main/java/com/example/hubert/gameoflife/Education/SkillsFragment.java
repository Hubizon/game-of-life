package com.example.hubert.gameoflife.Education;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hubert.gameoflife.MainActivity;
import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.Shop.RecyclerViewShopBuyAdapter;
import com.example.hubert.gameoflife.Utils.Arrays;
import com.example.hubert.gameoflife.Utils.SharedPreferencesDefaultValues;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link SkillsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SkillsFragment extends Fragment implements RecyclerViewSkillsAdapter.ItemClickListener {

    public SkillsFragment() {
        // Required empty public constructor
    }


    public static SkillsFragment newInstance() {
        SkillsFragment fragment = new SkillsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    RecyclerViewSkillsAdapter adapter;

    static int page = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_skills, container, false);

        ArrayList<String> mSkillsNames = new ArrayList<>();
        ArrayList<String> mSkillsPrices = new ArrayList<>();

        SharedPreferences sharedPref = MainActivity.userSharedPref;
        //SharedPreferences sharedPref = getActivity().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        try {
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject;
            if(page == 1) {
                JSONArray defaultJsonArray = new JSONArray(sharedPref.getString(getResources().getString(R.string.saved_skills_education_list_key), SharedPreferencesDefaultValues.DefaultSkillsEducationList));
                for(int i = 0; i < defaultJsonArray.length(); i++)
                {
                    jsonObject = defaultJsonArray.getJSONObject(i);
                    if(!jsonObject.getBoolean("isBought"))
                        jsonArray.put(jsonObject);
                }
                page++;
            } else {
                JSONArray defaultJsonArray = new JSONArray(sharedPref.getString(getResources().getString(R.string.saved_skills_criminal_list_key), SharedPreferencesDefaultValues.DefaultSkillsCriminalList));
                for(int i = 0; i < defaultJsonArray.length(); i++)
                {
                    jsonObject = defaultJsonArray.getJSONObject(i);
                    if(!jsonObject.getBoolean("isBought"))
                        jsonArray.put(jsonObject);
                }
                page = 1;
            }

            for(int i = 0; i < jsonArray.length(); i++)
            {
                jsonObject = jsonArray.getJSONObject(i);
                if(!jsonObject.getBoolean("isLearned"))
                {
                    mSkillsNames.add(jsonObject.getString("name"));
                    mSkillsPrices.add(jsonObject.getString("price") + "$");
                }
            }

        } catch (JSONException e) {
        }

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewSkills);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecyclerViewSkillsAdapter(getContext(), mSkillsNames, mSkillsPrices);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        return view;
    }



    @Override
    public void onItemClick(View view, int position) {
        boolean isEduPage = true;

        for(int i = 0; i < Arrays.skillsEducationList.length; i++)
        {
            if(((TextView)view.findViewById(R.id.skillName)).getText().equals(Arrays.skillsEducationList[i].getName()))
            {
                isEduPage = true;
                position = i;
            }
            else if(((TextView)view.findViewById(R.id.skillName)).getText().equals(Arrays.skillsCriminalList[i].getName()))
            {
                isEduPage = false;
                position = i;
            }
        }


        if(isEduPage)
            alertDialogLearnSkill(position, Arrays.skillsEducationList, SharedPreferencesDefaultValues.DefaultSkillsEducationList, getResources().getString(R.string.saved_skills_education_list_key), Arrays.skillsEducationList[position].getName());
        else
            alertDialogLearnSkill(position, Arrays.skillsCriminalList, SharedPreferencesDefaultValues.DefaultSkillsCriminalList, getResources().getString(R.string.saved_skills_criminal_list_key), Arrays.skillsCriminalList[position].getName());
    }

    private void alertDialogLearnSkill(final int position, final Skill[] skills, final String defaultSkills, final String resSavedList, String skillName)
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext(), R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setTitle("Buying skill")
                //.setIcon(R.drawable.ic_launcher)
                .setMessage("Are you sure you want to buy and learn " + skillName + "?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.cancel();
                        //TODO: start timer
                    }})
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {

                        SharedPreferences sharedPref = MainActivity.userSharedPref;
                        SharedPreferences.Editor editor = sharedPref.edit();
                        JSONArray jsonArray;
                        JSONObject jsonObject;

                        if(skills[position].getPrice() <= sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney))
                        {
                            if(!skills[position].getIsBought())
                            {
                                try
                                {
                                    jsonArray = new JSONArray(sharedPref.getString(resSavedList, defaultSkills));
                                    jsonObject = jsonArray.getJSONObject(position);
                                    if(!jsonObject.getBoolean("isBought"))
                                    {
                                        jsonObject.put("isBought", true);
                                        jsonArray.put(position, jsonObject);
                                        editor.putString(resSavedList, jsonArray.toString());
                                        editor.putInt(getResources().getString(R.string.saved_character_money_key), sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) - jsonObject.getInt("price"));
                                        Toast.makeText(getContext(), "You successful bought " + skills[position].getName() + "!", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                        Toast.makeText(getContext(), "You already have had buy it!", Toast.LENGTH_SHORT).show();
                                }
                                catch (JSONException e)
                                { }
                            }
                            else
                                Toast.makeText(getContext(), "You already have had buy it!", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(getContext(), "Unfortunately you don't have enough money to but this", Toast.LENGTH_SHORT).show();
                        editor.apply();
                    }
                }).show();
    }
}
