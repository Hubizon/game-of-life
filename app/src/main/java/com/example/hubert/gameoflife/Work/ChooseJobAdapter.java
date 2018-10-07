package com.example.hubert.gameoflife.Work;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hubert.gameoflife.House.Fun;
import com.example.hubert.gameoflife.House.Lodging;
import com.example.hubert.gameoflife.House.Transport;
import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.Utils.SharedPreferencesDefaultValues;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChooseJobAdapter extends RecyclerView.Adapter<ChooseJobAdapter.ViewHolder> {
    private ArrayList<String> mDataNames = new ArrayList<>();
    private ArrayList<String> mDataPrices = new ArrayList<>();
    private LayoutInflater mInflater;
    private ChooseJobAdapter.ItemClickListener mClickListener;

    private int lastItemColor;

    // data is passed into the constructor
    ChooseJobAdapter(Context context, Job[] mDataJobs) {
        this.mInflater = LayoutInflater.from(context);


        lastItemColor = ContextCompat.getColor(context, R.color.colorDark);
        SharedPreferences sharedPref = context.getSharedPreferences(context.getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json;
        int lastPosition = 0;
        // Dodać opcję, żeby wyświetały się wszystkie

        for(int i = 0; i < mDataJobs.length; i++) {
            Job job = mDataJobs[i];
            boolean canDoThisWork = true;
            if (job != null) {
                if (job.getMinFunNeeded() != null) {
                    switch (job.getMinFunNeeded().getType()) {
                        case "Phone":
                            if (sharedPref.getString(context.getResources().getString(R.string.saved_my_phone_key), SharedPreferencesDefaultValues.DefaultMyPhone) != null) {
                                json = sharedPref.getString(context.getResources().getString(R.string.saved_my_phone_key), SharedPreferencesDefaultValues.DefaultMyPhone);
                                Fun mFun = gson.fromJson(json, Fun.class);
                                if (mFun.getGivenFun() <= job.getMinFunNeeded().getGivenFun())
                                    canDoThisWork = false;
                            } else
                                canDoThisWork = false;
                            break;

                        case "TV":
                            if (sharedPref.getString(context.getResources().getString(R.string.saved_my_tv_key), SharedPreferencesDefaultValues.DefaultMyPhone) != null) {
                                json = sharedPref.getString(context.getResources().getString(R.string.saved_my_tv_key), SharedPreferencesDefaultValues.DefaultMyPhone);
                                Fun mFun = gson.fromJson(json, Fun.class);
                                if (mFun.getGivenFun() <= job.getMinFunNeeded().getGivenFun())
                                    canDoThisWork = false;
                            } else
                                canDoThisWork = false;
                            break;

                        case "Computer":
                            if (sharedPref.getString(context.getResources().getString(R.string.saved_my_computer_key), SharedPreferencesDefaultValues.DefaultMyComputer) != null) {
                                json = sharedPref.getString(context.getResources().getString(R.string.saved_my_computer_key), SharedPreferencesDefaultValues.DefaultMyComputer);
                                Fun mFun = gson.fromJson(json, Fun.class);
                                if (mFun.getGivenFun() <= job.getMinFunNeeded().getGivenFun())
                                    canDoThisWork = false;
                            } else
                                canDoThisWork = false;
                            break;
                    }
                }


                if (job.getMinLodgingNeeded() != null) {
                    if (!sharedPref.getString(context.getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodging).equals(SharedPreferencesDefaultValues.DefaultMyLodging) || sharedPref.getString(context.getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodging).equals(SharedPreferencesDefaultValues.DefaultMyLodgingAfter18)) {
                        json = sharedPref.getString(context.getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodging);
                        Lodging mLodging = gson.fromJson(json, Lodging.class);
                        if (mLodging.getPrice() <= job.getMinFunNeeded().getPrice())
                            canDoThisWork = false;
                    } else
                        canDoThisWork = false;
                }

                if (job.getMinTransportNeeded() != null) {
                    if (!sharedPref.getString(context.getResources().getString(R.string.saved_my_transport_key), SharedPreferencesDefaultValues.DefaultMyTransport).equals(SharedPreferencesDefaultValues.DefaultMyTransport)) {
                        json = sharedPref.getString(context.getResources().getString(R.string.saved_my_transport_key), SharedPreferencesDefaultValues.DefaultMyTransport);
                        Transport mTransport = gson.fromJson(json, Transport.class);
                        if (mTransport.getPrice() <= job.getMinTransportNeeded().getPrice())
                            canDoThisWork = false;
                    } else
                        canDoThisWork = false;
                }

                if (job.getSkillsNeeded() != null) {
                    try {
                        JSONArray jsonArray = new JSONArray(gson.toJson(job.getSkillsNeeded()));

                        for (int x = 0; x < jsonArray.length(); x++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(x);
                            if (!jsonObject.getBoolean("isLearned"))
                                canDoThisWork = false;
                        }
                    } catch (JSONException e)
                    { }
                }

                if (canDoThisWork) {
                    mDataNames.add(mDataJobs[i].getName());
                    mDataPrices.add(mDataJobs[i].getSalary() + "$");
                   // mDataJobs[i]
                }
                else
                {
                    lastPosition = i;
                    break;
                }
            }
        }

        mDataNames.add(mDataJobs[lastPosition].getName());
        mDataPrices.add(mDataJobs[lastPosition ].getSalary() + "$");
    }

    // inflates the row layout from xml when needed
    @Override
    public ChooseJobAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_choosejob_row, parent, false);
        return new ChooseJobAdapter.ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ChooseJobAdapter.ViewHolder holder, int position) {
        String ItemName = mDataNames.get(position);
        String ItemPrice = mDataPrices.get(position);
        holder.myTextViewName.setText(ItemName);
        holder.myTextViewPrice.setText(ItemPrice);
        if(position == (mDataNames.size() - 1))
            holder.myCardViewChooseJob.setBackgroundColor(lastItemColor);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mDataNames.size() ;
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView myCardViewChooseJob;
        TextView myTextViewName;
        TextView myTextViewPrice;

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

    // convenience method for getting data at click position
    String getItem(int id) {
        return mDataNames.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
