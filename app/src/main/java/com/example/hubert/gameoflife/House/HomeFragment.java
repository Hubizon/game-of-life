package com.example.hubert.gameoflife.House;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hubert.gameoflife.R;

public class HomeFragment extends Fragment
implements View.OnClickListener{

    private OnFragmentInteractionListener mListener;

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
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction();
    }



}
