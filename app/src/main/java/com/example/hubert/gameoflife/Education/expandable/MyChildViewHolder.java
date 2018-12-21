package com.example.hubert.gameoflife.Education.expandable;

import android.view.View;
import android.widget.TextView;

import com.example.hubert.gameoflife.R;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class MyChildViewHolder extends ChildViewHolder {

    public final TextView listChild;

    public MyChildViewHolder(View itemView) {
        super(itemView);
        listChild = itemView.findViewById(R.id.list_item_artist_name);


    }

    public void onBind(String Sousdoc) {
        listChild.setText(Sousdoc);

    }


}