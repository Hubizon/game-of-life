package com.example.hubert.gameoflife.Education;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.hubert.gameoflife.MainActivity;
import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.Shop.ShopFragment;
import com.example.hubert.gameoflife.Work.FindJobFragment;


public class EducationFragment extends Fragment implements View.OnClickListener {


    public EducationFragment() {}

    public static EducationFragment newInstance() {
        EducationFragment fragment = new EducationFragment();
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
        return inflater.inflate(R.layout.fragment_education, container, false);
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        SharedPreferences sharedPref = getActivity().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        switch(view.getId())
        {
            case R.id.giveUpSchoolEducation:
                editor.putBoolean(getString(R.string.saved_is_in_school_now_key), false);
                ft.replace(R.id.pager, new FindJobFragment());
                break;

            case R.id.learnAtHomeEducation:
                Intent intent = new Intent(getActivity().getApplicationContext(), LearnInHomeActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }

        ft.commit();
        editor.commit();
    }

}
