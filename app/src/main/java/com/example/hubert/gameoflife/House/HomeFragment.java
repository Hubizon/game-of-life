package com.example.hubert.gameoflife.House;

import android.content.Context;
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
                Toast.makeText(getContext(), String.format("The %s view is not yet implemented!",
                        getResources().getResourceEntryName(view.getId())), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void onHomeButtonClick(View view) {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();

        switch(view.getId())
        {

            case R.id.computerHome:
                if(MainActivity.MyComputer != null || MainActivity.MyPhone != null)
                    ft.replace(R.id.mainFragmentHolder, new ComputerFragment());
                else
                    Toast.makeText(getActivity().getApplicationContext(), "Unfortunately you don't have a computer or a phone", Toast.LENGTH_SHORT).show();
                break;

            default:
                return;

        }

        ft.commit();
    }
}
