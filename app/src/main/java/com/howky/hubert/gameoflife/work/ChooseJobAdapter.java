package com.howky.hubert.gameoflife.work;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.howky.hubert.gameoflife.house.Fun;
import com.howky.hubert.gameoflife.house.Lodging;
import com.howky.hubert.gameoflife.house.Transport;
import com.howky.hubert.gameoflife.MainActivity;
import com.howky.hubert.gameoflife.R;
import com.howky.hubert.gameoflife.utils.Arrays;
import com.howky.hubert.gameoflife.utils.SharedPreferencesDefaultValues;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ChooseJobAdapter extends RecyclerView.Adapter<ChooseJobAdapter.ViewHolder> {
    private final ArrayList<String> mDataNames = new ArrayList<>();
    private final ArrayList<String> mDataPrices = new ArrayList<>();
    private final LayoutInflater mInflater;
    private ChooseJobAdapter.ItemClickListener mClickListener;

    private final int lastItemColor;

    // data is passed into the constructor
    //static int lastPosition;
    private static int lastPositionPage1;
    private static int lastPositionPage2;

    ChooseJobAdapter(Context context, Job[] mDataJobs, int page) {
        this.mInflater = LayoutInflater.from(context);
        lastItemColor = ContextCompat.getColor(context, R.color.colorDark);
        SharedPreferences sharedPref = MainActivity.userSharedPref;
        Gson gson = new Gson();
        String json;
        if(page == 1)
            lastPositionPage1 = 100;
        else
            lastPositionPage2 = 100;

        for(int i = 0; i < mDataJobs.length; i++) {
            Job job = mDataJobs[i];
            boolean canDoThisWork = true;
            if (job != null)
            {
                if (job.getMinPhoneNeeded() != null) {
                    if (sharedPref.getString(context.getResources().getString(R.string.saved_my_phone_key), SharedPreferencesDefaultValues.DefaultMyPhone) != null) {
                        json = sharedPref.getString(context.getResources().getString(R.string.saved_my_phone_key), SharedPreferencesDefaultValues.DefaultMyPhone);
                        Fun mFun = gson.fromJson(json, Fun.class);
                        if (mFun.getGivenFun() < job.getMinPhoneNeeded().getGivenFun())
                            canDoThisWork = false;
                    } else
                        canDoThisWork = false;
                }
                if(job.getMinTvNeeded() != null)
                {
                    if (sharedPref.getString(context.getResources().getString(R.string.saved_my_tv_key), SharedPreferencesDefaultValues.DefaultMyPhone) != null) {
                        json = sharedPref.getString(context.getResources().getString(R.string.saved_my_tv_key), SharedPreferencesDefaultValues.DefaultMyPhone);
                        Fun mFun = gson.fromJson(json, Fun.class);
                        if (mFun.getGivenFun() < job.getMinTvNeeded().getGivenFun())
                            canDoThisWork = false;
                    } else
                        canDoThisWork = false;
                }
                if(job.getMinComputerNeeded() != null)
                {
                    if (sharedPref.getString(context.getResources().getString(R.string.saved_my_computer_key), SharedPreferencesDefaultValues.DefaultMyComputer) != null) {
                        json = sharedPref.getString(context.getResources().getString(R.string.saved_my_computer_key), SharedPreferencesDefaultValues.DefaultMyComputer);
                        Fun mFun = gson.fromJson(json, Fun.class);
                        if (mFun.getGivenFun() < job.getMinComputerNeeded().getGivenFun())
                            canDoThisWork = false;
                    } else
                        canDoThisWork = false;
                }

                if (job.getMinLodgingNeeded() != null) {
                    //     if (!sharedPref.getString(context.getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodging).equals(SharedPreferencesDefaultValues.DefaultMyLodging) || sharedPref.getString(context.getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodging).equals(SharedPreferencesDefaultValues.DefaultMyLodgingAfter18)) {
                    json = sharedPref.getString(context.getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodging);
                    Lodging mLodging = gson.fromJson(json, Lodging.class);
                    if (mLodging.getPrice() < job.getMinLodgingNeeded().getPrice())
                        canDoThisWork = false;
                    // } else
                    //     canDoThisWork = false;
                }

                if (job.getMinTransportNeeded() != null) {
                    //   if (!sharedPref.getString(context.getResources().getString(R.string.saved_my_transport_key), SharedPreferencesDefaultValues.DefaultMyTransport).equals(SharedPreferencesDefaultValues.DefaultMyTransport)) {
                    json = sharedPref.getString(context.getResources().getString(R.string.saved_my_transport_key), SharedPreferencesDefaultValues.DefaultMyTransport);
                    Transport mTransport = gson.fromJson(json, Transport.class);
                    if (mTransport.getPrice() < job.getMinTransportNeeded().getPrice())
                        canDoThisWork = false;
                    //   } else
                    //      canDoThisWork = false;
                }

                if (job.getSkillsNeeded() != null) {
                    try {
                        JSONArray skillsNeeded = new JSONArray(gson.toJson(job.getSkillsNeeded()));
                        JSONArray eduSkills = new JSONArray(sharedPref.getString(context.getResources().getString(R.string.saved_skills_education_list_key), SharedPreferencesDefaultValues.DefaultSkillsEducationList));
                        JSONArray crimSkills = new JSONArray(sharedPref.getString(context.getResources().getString(R.string.saved_skills_criminal_list_key), SharedPreferencesDefaultValues.DefaultSkillsEducationList));

                        for (int y = 0; y < skillsNeeded.length(); y++) {
                            JSONObject jsonObject = skillsNeeded.getJSONObject(i);

                            boolean haveThisSkill = false;
                            for (int x = 0; x < eduSkills.length(); x++) {
                                JSONObject jsonObjectSkill = eduSkills.getJSONObject(x);
                                if(jsonObject.getString("name").equals(jsonObjectSkill.getString("name")))
                                    if(jsonObjectSkill.getBoolean("isBought")) {
                                        haveThisSkill = true;
                                        break;
                                    }
                            }
                            for (int x = 0; x < crimSkills.length(); x++) {
                                JSONObject jsonObjectSkill = crimSkills.getJSONObject(x);
                                if(jsonObject.getString("name").equals(jsonObjectSkill.getString("name")))
                                    if(jsonObjectSkill.getBoolean("isBought")) {
                                        haveThisSkill = true;
                                        break;
                                    }
                            }
                            if (!haveThisSkill)
                                canDoThisWork = false;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    if(job instanceof CriminalJob)
                    {
                        CriminalJob criminalJob = (CriminalJob)job;
                        if(criminalJob.getWeaponsNeeded() != null)
                        {
                            try {
                                JSONArray weaponsNeeded = new JSONArray(gson.toJson(criminalJob.getWeaponsNeeded()));
                                JSONArray allWeapons = new JSONArray(sharedPref.getString(context.getResources().getString(R.string.saved_weapons_list_key), SharedPreferencesDefaultValues.DefaultWeapons));
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
                                        canDoThisWork = false;
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                if (canDoThisWork) {
                    mDataNames.add(mDataJobs[i].getName());
                    mDataPrices.add(mDataJobs[i].getSalary() + context.getResources().getString(R.string.currency));
                    //mDataJobs[i]
                }
                else
                {
                    if(page == 1)
                        lastPositionPage1 = i;
                    else
                        lastPositionPage2 = i;
                    break;
                }
            }
        }
        if(page == 1)
        {
            if(lastPositionPage1 != 100 || mDataNames.isEmpty())
            {
                for(int i = lastPositionPage1; i < mDataJobs.length; i++) {
                    mDataNames.add(mDataJobs[i].getName());
                    mDataPrices.add(mDataJobs[i].getSalary() + context.getResources().getString(R.string.currency));
                }
            }
        }
        else
            if(lastPositionPage2 != 0 || mDataNames.isEmpty())
            {
                for(int i = lastPositionPage2; i < mDataJobs.length; i++) {
                    mDataNames.add(mDataJobs[i].getName());
                    mDataPrices.add(mDataJobs[i].getSalary() + context.getResources().getString(R.string.currency));
                }
            }
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public ChooseJobAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_choosejob_row, parent, false);
        return new ChooseJobAdapter.ViewHolder(view);
    }

    // --Commented out by Inspection (12/8/2018 12:30 AM):static int page = 1;
//    static boolean isFirstTime0 = true;
    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(@NonNull ChooseJobAdapter.ViewHolder holder, int position) {
        String ItemName = mDataNames.get(position);
        String ItemPrice = mDataPrices.get(position);
        holder.myTextViewName.setText(ItemName);
        holder.myTextViewPrice.setText(ItemPrice);

//        if(position == 0)
//        {
//            isFirstTime0 = !isFirstTime0;
//        }

        if(!mDataNames.get(0).equals(Arrays.criminalJobsList[0].getName()))
        {
            if(position >= lastPositionPage1 && lastPositionPage1 != 100)
                holder.myCardViewChooseJob.setCardBackgroundColor(lastItemColor);
        }
        else
            if(position >= lastPositionPage2 && lastPositionPage2 != 100)
                holder.myCardViewChooseJob.setCardBackgroundColor(lastItemColor);

//        if(!isFirstTime0)
//        {
//            if(position >= lastPositionPage1 && lastPositionPage1 != 100)
//                holder.myCardViewChooseJob.setBackgroundColor(lastItemColor);
//        }
//        else
//            if(position >= lastPositionPage2 && lastPositionPage2 != 100)
//                holder.myCardViewChooseJob.setBackgroundColor(lastItemColor);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mDataNames.size() ;
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final CardView myCardViewChooseJob;
        final TextView myTextViewName;
        final TextView myTextViewPrice;

        ViewHolder(View itemView) {
            super(itemView);
            myCardViewChooseJob = itemView.findViewById(R.id.cardview_choosejob);
            myTextViewName = itemView.findViewById(R.id.chooseJobName);
            myTextViewPrice = itemView.findViewById(R.id.chooseJobSalary);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

// --Commented out by Inspection START (12/8/2018 12:30 AM):
//    // convenience method for getting data at click position
//    String getItem(int id) {
//        return mDataNames.get(id);
//    }
// --Commented out by Inspection STOP (12/8/2018 12:30 AM)

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}