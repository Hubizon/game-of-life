package com.example.hubert.gameoflife.Education;

import android.app.PendingIntent;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hubert.gameoflife.MainActivity;
import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.Utils.SharedPreferencesDefaultValues;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Hubert on 30.08.2018.
 */

public class RecyclerViewSubjectAdapter extends RecyclerView.Adapter<RecyclerViewSubjectAdapter.ViewHolder> {

    private List<String> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    Context context;

    // data is passed into the constructor
    RecyclerViewSubjectAdapter(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
        }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_subjects_row, parent, false);

        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String animal = mData.get(position);
        holder.myTextView.setText(animal);

        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        try {
            JSONArray jsonArray = new JSONArray(sharedPreferences.getString(context.getApplicationContext().getResources().getString(R.string.saved_subjects_list_key), SharedPreferencesDefaultValues.DefaultSubjectsList));
            JSONObject json = (JSONObject)jsonArray.get(position);
            if(json.getBoolean("IsTodaysHomeworkDone"))
                holder.myTextViewHomework.setVisibility(View.GONE);
            else
                holder.myTextViewHomework.setVisibility(View.VISIBLE);
        }
        catch (JSONException e)
        { }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        TextView myTextViewHomework;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.subjectNameMark);
            myTextViewHomework = itemView.findViewById(R.id.subjectHomework);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return mData.get(id);
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
