package com.example.hubert.gameoflife.Work;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.Utils.SharedPreferencesDefaultValues;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ChooseJobAdapter extends RecyclerView.Adapter<ChooseJobAdapter.ViewHolder> {
    private List<String> mDataNames;
    private List<String> mDataPrices;
    private LayoutInflater mInflater;
    private ChooseJobAdapter.ItemClickListener mClickListener;

    // data is passed into the constructor
    ChooseJobAdapter(Context context, List<String> dataNames, List<String> dataPrices) {
        this.mInflater = LayoutInflater.from(context);
        this.mDataNames = dataNames;
        this.mDataPrices = dataPrices;
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
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mDataNames.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextViewName;
        TextView myTextViewPrice;

        ViewHolder(View itemView) {
            super(itemView);
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
