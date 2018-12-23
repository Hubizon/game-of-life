package com.example.hubert.gameoflife;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class MyDialogDead extends DialogFragment {

    private OnDialogDeadInteractionListener mListener;

    public MyDialogDead() {}

    public static MyDialogDead newInstance() {
        return new MyDialogDead();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_dialog_dead, container, false);
        ((TextView)view.findViewById(R.id.score)).setText("You Last " + MainActivity.userSharedPref.getInt(getResources().getString(R.string.saved_age_years_key), 0)
                + " years and " + MainActivity.userSharedPref.getInt(getResources().getString(R.string.saved_age_days_key), 0) + " days.");

        int highscoreYears = 0;
        if(MainActivity.userSharedPref.getInt(getResources().getString(R.string.saved_high_score_key), 0) >= 365)
            highscoreYears = MainActivity.userSharedPref.getInt(getResources().getString(R.string.saved_high_score_key), 0);
        int highscoreDays =  MainActivity.userSharedPref.getInt(getResources().getString(R.string.saved_high_score_key), 0) - (highscoreYears * 365);
        ((TextView)view.findViewById(R.id.high_score)).setText("Your high score " + highscoreYears + " years and " + highscoreDays);
        
        setCancelable(false);
        Button okbtn = view.findViewById(R.id.btn_newGame);
        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onDialogDeadInteraction();
                    dismiss();
                }
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDialogDeadInteractionListener) {
            mListener = (OnDialogDeadInteractionListener) context;
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

    public interface OnDialogDeadInteractionListener {
        void onDialogDeadInteraction();
    }
}
