package com.example.hubert.gameoflife.Girlboyfriend;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hubert.gameoflife.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GirlboyfriendFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GirlboyfriendFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GirlboyfriendFragment extends Fragment {

    public GirlboyfriendFragment() {}

    public static GirlboyfriendFragment newInstance() {
        GirlboyfriendFragment fragment = new GirlboyfriendFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_girlboyfriend,
                container, false);

        return view;
    }

}
