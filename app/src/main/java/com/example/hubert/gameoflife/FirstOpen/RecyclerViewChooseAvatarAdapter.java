package com.example.hubert.gameoflife.FirstOpen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hubert.gameoflife.House.Lodging;
import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.Shop.RecyclerViewShopBuyAdapter;
import com.example.hubert.gameoflife.Utils.Arrays;

import java.util.List;

import static com.example.hubert.gameoflife.Utils.Arrays.lodgingList;
import static com.example.hubert.gameoflife.Utils.Arrays.transportList;

public class RecyclerViewChooseAvatarAdapter extends RecyclerView.Adapter<RecyclerViewChooseAvatarAdapter.ViewHolder> {

    private List<Integer> mDataAvatars;
    private LayoutInflater mInflater;
    private RecyclerViewChooseAvatarAdapter.ItemClickListener mClickListener;

    Context context;

    // data is passed into the constructor
    RecyclerViewChooseAvatarAdapter(Context context, List<Integer> dataNamesAvatars) {
        this.mInflater = LayoutInflater.from(context);
        this.mDataAvatars = dataNamesAvatars;
        this.context = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public RecyclerViewChooseAvatarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = mInflater.inflate(R.layout.recyclerview_choose_avatar_row, parent, false);

        return new RecyclerViewChooseAvatarAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int ItemName = mDataAvatars.get(position);

        holder.myImageViewAvatar.setImageResource(ItemName);
        holder.myImageViewAvatar.setTag(ItemName);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mDataAvatars.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView myImageViewAvatar;

        ViewHolder(View itemView) {
            super(itemView);
            myImageViewAvatar = itemView.findViewById(R.id.imageViewAvatar);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    int getItem(int id) {
        return mDataAvatars.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(RecyclerViewChooseAvatarAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
