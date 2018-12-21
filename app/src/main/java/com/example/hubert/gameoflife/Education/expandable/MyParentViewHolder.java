package com.example.hubert.gameoflife.Education.expandable;

import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hubert.gameoflife.Education.EduFragment;
import com.example.hubert.gameoflife.R;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

public class MyParentViewHolder extends GroupViewHolder {

    private final TextView listGroup;
    private final ImageView arrow;
    private final ImageView icon;

    public MyParentViewHolder(View itemView) {
        super(itemView);
        listGroup = itemView.findViewById(R.id.list_item_type_name);
        arrow = itemView.findViewById(R.id.list_item_genre_arrow);
        icon = itemView.findViewById(R.id.list_item_genre_icon);
    }

    public void setParentTitle(ExpandableGroup group) {
        listGroup.setText(group.getTitle());
        String title = group.getTitle();
        switch (title) {
            case EduFragment.TITLE_CRIMINAL:
                icon.setBackgroundResource(R.drawable.ic_robber);
                break;
            case EduFragment.TITLE_SCHOOL:
                icon.setBackgroundResource(R.drawable.ic_school);
                break;
            case EduFragment.TITLE_WORK:
                icon.setBackgroundResource(R.drawable.ic_network);
                break;
        }
    }

    @Override
    public void expand() {
        animateExpand();
    }

    @Override
    public void collapse() {
        animateCollapse();
    }

    private void animateExpand() {
        RotateAnimation rotate =
                new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }

    private void animateCollapse() {
        RotateAnimation rotate =
                new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }
}
