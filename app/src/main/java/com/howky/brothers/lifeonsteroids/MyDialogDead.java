package com.howky.brothers.lifeonsteroids;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.howky.brothers.lifeonsteroids.utils.SharedPreferencesDefaultValues;


public class MyDialogDead extends DialogFragment {

    private OnDialogDeadInteractionListener mListener;

    public MyDialogDead() {}

    public static MyDialogDead newInstance() {
        return new MyDialogDead();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_dialog_dead, container, false);
        setCancelable(false);

        int pointsNowForSurvival = MyApplication.userSharedPref.getInt(getString(R.string.saved_age_years_key), 0) * 500 + MyApplication.userSharedPref.getInt(getString(R.string.saved_age_days_key), 0);

        int pointsNowForMoney = (MyApplication.userSharedPref.getInt(getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) + MyApplication.userSharedPref.getInt(getString(R.string.saved_money_in_safe_key), 0)) / 50;
        if(pointsNowForMoney > 10000)
            pointsNowForMoney =  10000;

        int pointsNowForHiddenSkills = (MyApplication.userSharedPref.getInt(getString(R.string.saved_communication_skills_key), SharedPreferencesDefaultValues.DefaultCommunicationsSkills) + MyApplication.userSharedPref.getInt(getString(R.string.saved_education_points_key), SharedPreferencesDefaultValues.DefaultEducationPoints)
                + MyApplication.userSharedPref.getInt(getString(R.string.saved_criminal_points_key), SharedPreferencesDefaultValues.DefaultCriminalPoints) + MyApplication.userSharedPref.getInt(getString(R.string.saved_criminal_points_key), SharedPreferencesDefaultValues.DefaultCriminalPoints)
                + MyApplication.userSharedPref.getInt(getString(R.string.saved_writing_skills_key), 100) + MyApplication.userSharedPref.getInt(getString(R.string.saved_programming_skills_key), 100) + MyApplication.userSharedPref.getInt(getString(R.string.saved_recording_skills_key), 100)
                + MyApplication.userSharedPref.getInt(getString(R.string.saved_karma_points_key), SharedPreferencesDefaultValues.DefaultKarmaPoints)) / 5;

        int pointsNowForItems = 0;

        int pointsNow = pointsNowForSurvival + pointsNowForMoney + pointsNowForHiddenSkills + pointsNowForItems;
        int pointsHigh = MyApplication.mainSharedPref.getInt(getString(R.string.saved_high_score_key), 0);

        if(pointsNow > pointsHigh) {
            pointsHigh = pointsNow;
            SharedPreferences.Editor editor = MyApplication.mainSharedPref.edit();
            editor.putInt(getString(R.string.saved_high_score_key), pointsHigh);
            editor.apply();
        }

        ((TextView)view.findViewById(R.id.score)).setText(getString(R.string.socre_format,
                pointsNow));
        ((TextView)view.findViewById(R.id.high_score)).setText(getString(R.string.highscore_format,
                pointsHigh));

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
                    + " must implement OnDialogDeadInteractionListener and only MainActivity implements it so sth went wrong!");
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
