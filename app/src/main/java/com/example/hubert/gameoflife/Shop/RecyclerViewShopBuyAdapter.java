package com.example.hubert.gameoflife.Shop;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hubert.gameoflife.House.Transport;
import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.House.Lodging;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Hubert on 31.08.2018.
 */

public class RecyclerViewShopBuyAdapter extends RecyclerView.Adapter<RecyclerViewShopBuyAdapter.ViewHolder> {

    private List<String> mDataNames;
    private List<String> mDataPrices;
    private LayoutInflater mInflater;
    private RecyclerViewShopBuyAdapter.ItemClickListener mClickListener;

    Context context;

    // data is passed into the constructor
    RecyclerViewShopBuyAdapter(Context context, List<String> dataNames, List<String> dataPrices, Lodging[] dataLodgings) {
        this.mInflater = LayoutInflater.from(context);
        this.mDataNames = dataNames;
        this.mDataPrices = dataPrices;
        this.context = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public RecyclerViewShopBuyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_shopbuy_row, parent, false);

        Spinner spinner = view.findViewById(R.id.buy_method_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                int i = 0;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

        return new RecyclerViewShopBuyAdapter.ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(RecyclerViewShopBuyAdapter.ViewHolder holder, int position) {
        String ItemName = mDataNames.get(position);
        String ItemPrice = mDataPrices.get(position);

        holder.myTextViewName.setText(ItemName);
        holder.myTextViewPrice.setText(ItemPrice);

        if(mDataNames != null)
            if(mDataNames.get(0).equals(ShopFragment.transportList[0].getName()) || mDataNames.get(0).equals(ShopFragment.lodgingList[0].getName()))
                holder.mySpinnerBuyMethod.setVisibility(View.VISIBLE);
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
        Spinner mySpinnerBuyMethod;

        ViewHolder(View itemView) {
            super(itemView);
            myTextViewName = itemView.findViewById(R.id.shopBuyItemName);
            myTextViewPrice = itemView.findViewById(R.id.shopBuyItemPrice);
            mySpinnerBuyMethod = itemView.findViewById(R.id.buy_method_spinner);
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