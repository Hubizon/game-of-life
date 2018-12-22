package com.example.hubert.gameoflife.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.hubert.gameoflife.R;

import java.util.ArrayList;


class SpinnerIconAdapter extends BaseAdapter {

    private final Context context;
    private final ArrayList<Integer> icons;
    private final LayoutInflater inflter;
    private final int numberOfUsers;

    public SpinnerIconAdapter(Context applicationContext) {
        this.context = applicationContext;
        inflter = (LayoutInflater.from(applicationContext));

        SharedPreferences mainSharedPref = context.getSharedPreferences(context.getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        numberOfUsers = mainSharedPref.getInt(context.getString(R.string.saved_number_of_users), 0);
        icons = getAvatarts();
    }

    @Override
    public int getCount() {
        return numberOfUsers;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override 
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.spinner_layout_icons, null);

        ImageView icon =  view.findViewById(R.id.icon_spinner);
        if(icons.size() > i)
            icon.setImageResource(icons.get(i));
        return view;
    }

    private ArrayList<Integer> getAvatarts() {
        ArrayList<Integer> avatarIcons = new ArrayList<>();
        for (int i = 0; i < numberOfUsers; i++) {
            SharedPreferences userSharedPref = context.getSharedPreferences(context.getString(R.string.shared_preferences_user_key) + i, 0);
            int avatar = userSharedPref.getInt(context.getString(R.string.saved_character_icon_key), 0);
            if (avatar != 0) avatarIcons.add(avatar);
        }
        //avatarIcons.add(R.drawable.ic_new_user);

        return avatarIcons;
    }
}
