package com.example.hubert.gameoflife.House;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hubert.gameoflife.Education.EducationFragment;
import com.example.hubert.gameoflife.Girlboyfriend.GirlboyfriendFragment;
import com.example.hubert.gameoflife.MainActivity;
import com.example.hubert.gameoflife.Profile.MainFragment;
import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.Shop.BuyActivity;
import com.example.hubert.gameoflife.Shop.ShopFragment;

public class HomeFragment extends Fragment
implements View.OnClickListener{

    public HomeFragment() {}


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,
                container, false);

        ImageView tvbutton = view.findViewById(R.id.watchTvHome);
        tvbutton.setOnClickListener(this);

        ImageView sleepbutton = view.findViewById(R.id.sleepHome);
        sleepbutton.setOnClickListener(this);

        ImageView computerbutton = view.findViewById(R.id.computerHome);
        computerbutton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {

        SharedPreferences sharedPref = getActivity().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);

        switch (view.getId()) {
            case R.id.watchTvHome:
                Toast.makeText(getContext(), String.format("The %s view is not yet implemented!",
                        getResources().getResourceEntryName(view.getId())), Toast.LENGTH_SHORT).show();
                break;
            case R.id.sleepHome:
                Toast.makeText(getContext(), String.format("The %s view is not yet implemented!",
                        getResources().getResourceEntryName(view.getId())), Toast.LENGTH_SHORT).show();
                break;
            case R.id.computerHome:
                if(sharedPref.getString(getResources().getString(R.string.saved_my_computer_key), null) != null || sharedPref.getString(getResources().getString(R.string.saved_my_phone_key), null) != null)
                {
                    Intent intent = new Intent(getActivity().getApplicationContext(), ComputerActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getActivity().getApplicationContext(), "Unfortunately you don't have a computer or a phone", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
