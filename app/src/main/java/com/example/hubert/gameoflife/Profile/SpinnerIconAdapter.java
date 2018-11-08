package com.example.hubert.gameoflife.Profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.hubert.gameoflife.R;


public class SpinnerIconAdapter extends BaseAdapter {

    Context context;
    int width;
    int icons[] = {
            R.drawable.man,
            R.drawable.man_1,
            R.drawable.man_3,
            R.drawable.man_4,
            R.drawable.girl,
            R.drawable.girl_1,
    };
    LayoutInflater inflter;

    public SpinnerIconAdapter(Context applicationContext, int width) {
        this.context = applicationContext;
        this.width = width;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return icons.length;
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
//        view.getLayoutParams().width = width;
//        view.getLayoutParams().height = width;
        ImageView icon =  view.findViewById(R.id.icon_spinner);
//        icon.setLayoutParams(new ViewGroup.LayoutParams(width, width));
        icon.setImageResource(icons[i]);
        return view;
    }
}

// Feaure not supported yet!
