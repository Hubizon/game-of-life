package com.howky.hubert.gameoflife.education;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.howky.hubert.gameoflife.MainActivity;
import com.howky.hubert.gameoflife.MyApplication;
import com.howky.hubert.gameoflife.R;
import com.howky.hubert.gameoflife.utils.Arrays;
import com.howky.hubert.gameoflife.utils.SharedPreferencesDefaultValues;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link SkillsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class
SkillsFragment extends Fragment implements RecyclerViewSkillsAdapter.ItemClickListener {

    public SkillsFragment() {
        // Required empty public constructor
    }


    public static SkillsFragment newInstance() {
        return new SkillsFragment();
    }

    RecyclerViewSkillsAdapter adapter;

    static int page = 1;


    private Context mContext;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_skills, container, false);

        ArrayList<String> mSkillsNames = new ArrayList<>();
        ArrayList<String> mSkillsPrices = new ArrayList<>();

        SharedPreferences sharedPref = MyApplication.userSharedPref;
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
            e.printStackTrace();
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
        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext, R.style.Theme_MaterialComponents_Light_Dialog_Alert);
        dialog.setTitle(getResources().getString(R.string.buying_skills))
                //.setIcon(R.drawable.ic_launcher)Are you sure you want to buy and learn
                .setMessage(getResources().getString(R.string.sure_to_buy_learn) + "" + skillName + "?")
                .setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.cancel();
                        //TODO: start timer
                        // raczej niepotrzebny tu jest dialog
                    }})
                .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {

                        SharedPreferences sharedPref = MyApplication.userSharedPref;
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
                                        Toast.makeText(getContext(),getResources().getString(R.string.successful_bought) + " " + skills[position].getName() + "!", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                        Toast.makeText(getContext(), getResources().getString(R.string.already_had_it), Toast.LENGTH_SHORT).show();
                                }
                                catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            else
                                Toast.makeText(getContext(), getResources().getString(R.string.already_had_it), Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(getContext(), getResources().getString(R.string.not_enough_money), Toast.LENGTH_SHORT).show();
                        editor.apply();
                    }
                }).show();
    }


}