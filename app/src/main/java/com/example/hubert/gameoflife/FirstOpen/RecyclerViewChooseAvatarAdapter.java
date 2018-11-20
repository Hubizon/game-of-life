package com.example.hubert.gameoflife.FirstOpen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hubert.gameoflife.R;

public class RecyclerViewChooseAvatarAdapter extends RecyclerView.Adapter<RecyclerViewChooseAvatarAdapter.ViewHolder> {

    private Integer[] mDataAvatars;
    private LayoutInflater mInflater;
    private RecyclerViewChooseAvatarAdapter.ItemClickListener mClickListener;

    Context context;

    // data is passed into the constructor
    RecyclerViewChooseAvatarAdapter(Context context, Integer[] dataNamesAvatars) {
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
        int ItemName = mDataAvatars[position];

        holder.myImageViewAvatar.setImageResource(ItemName);
        holder.myImageViewAvatar.setTag(ItemName);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mDataAvatars.length;
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
        return mDataAvatars[id];
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
