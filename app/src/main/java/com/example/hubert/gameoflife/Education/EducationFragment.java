package com.example.hubert.gameoflife.Education;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hubert.gameoflife.MainActivity;
import com.example.hubert.gameoflife.R;
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

        switch(view.getId())
        {
            case R.id.giveUpSchoolEducation:
                MainActivity.IsInSchoolNow = false;
                ft.replace(R.id.pager, new FindJobFragment());

            case R.id.learnAtHomeEducation:
                Intent intent = new Intent(getActivity().getApplicationContext(), LearnInHomeActivity.class);
                startActivity(intent);

            default:
                break;
        }

        ft.commit();
    }

}
