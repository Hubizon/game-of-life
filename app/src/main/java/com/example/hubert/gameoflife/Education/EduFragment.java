package com.example.hubert.gameoflife.Education;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.hubert.gameoflife.Education.expandable.ChildList;
import com.example.hubert.gameoflife.Education.expandable.MyExpandableRecyclerAdapter;
import com.example.hubert.gameoflife.Education.expandable.ParentList;
import com.example.hubert.gameoflife.MainActivity;
import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.Utils.SharedPreferencesDefaultValues;
import com.example.hubert.gameoflife.Work.Job;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class EduFragment extends Fragment
        implements View.OnClickListener, MyExpandableRecyclerAdapter.EduFragmentCallback{

    private static final String EDU_DIALOG_TAG = "edu_dialog_tag2";

    public static final String TITLE_SCHOOL = "School";
    public static final String TITLE_CRIMINAL = "Criminal";
    public static final String TITLE_WORK = "Work";

    private RecyclerView recycler_view;
    private MyExpandableRecyclerAdapter adapter;

    public List<ParentList> Parent = new ArrayList<>();
    public List<ChildList> ChildSchool = new ArrayList<>();
    public List<ChildList> ChildCriminal = new ArrayList<>();
    public  List<ChildList> ChildWork = new ArrayList<>();

    CardView workCardView;
    TextView workTitle, workPosition;
    ProgressBar workProgressPosition;

    public EduFragment() {}

    public static EduFragment newInstance() {
        return new EduFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edu, container, false);

        recycler_view = view.findViewById(R.id.recycler_Expand);
        recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));

        SharedPreferences sharedPref = MainActivity.userSharedPref;
        //SharedPreferences sharedPref = getActivity().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPref.getString(getResources().getString(R.string.saved_my_job_key), SharedPreferencesDefaultValues.DefaultMyJob);
        Job job = gson.fromJson(json, Job.class);

        workCardView = view.findViewById(R.id.cardViewWorkInfo);
        workTitle = view.findViewById(R.id.work_name);
        workPosition = view.findViewById(R.id.position_work);
        workProgressPosition = view.findViewById(R.id.ProgressBar_work_position);

        if(job != null)
        {
            workCardView.setVisibility(View.VISIBLE);
            workTitle.setText("Work: " + job.getName());
            workProgressPosition.setProgress(job.getPositionPoints());
            workPosition.setText("Position: " + job.getPosition());
        }


        if (!(Parent.size() > 0)) {
            ChildSchool.add(new ChildList("Go to school"));
            //ChildSchool.add(new ChildList("learn hard"));
            //ChildSchool.add(new ChildList("hand around"));
            ChildSchool.add(new ChildList("Get some skills"));
            //ChildSchool.add(new ChildList("Give up school"));
            Parent.add(new ParentList(TITLE_SCHOOL, ChildSchool));

            //ChildWork.add(new ChildList("work hard"));

            //TODO make it in chnageWOrkStatus method
            if(job == null)
                ChildWork.add(new ChildList("Find a Job"));
            else {
                ChildWork.add(new ChildList("Start working"));
                ChildWork.add(new ChildList("Give up work"));
            }
            Parent.add(new ParentList(TITLE_WORK, ChildWork));

            ChildCriminal.add(new ChildList("Get new friends"));
            ChildCriminal.add(new ChildList("Steal stuff"));
            ChildCriminal.add(new ChildList("Sell drugs"));
            ChildCriminal.add(new ChildList("Threat teachers"));
            Parent.add(new ParentList(TITLE_CRIMINAL, ChildCriminal));
        }

        RecyclerView.ItemAnimator animator = recycler_view.getItemAnimator();
        if (animator instanceof DefaultItemAnimator) {
            ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
        }

        adapter = new MyExpandableRecyclerAdapter(Parent, getContext(), this);
        recycler_view.setAdapter(adapter);

        return view;
    }

    @Override
    public void onClick(View view) {
        SharedPreferences sharedPref = MainActivity.userSharedPref;
        //SharedPreferences sharedPref = getActivity().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        Intent intent = null;
        DialogFragment newDialog = null;

        if (intent != null) startActivity(intent);
        else if (newDialog != null) newDialog.show(getActivity().getSupportFragmentManager(), EDU_DIALOG_TAG);

        editor.apply();
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        adapter.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        adapter.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void UpdateJobStatus(boolean hasJob) {
        ChildWork.clear();
        if( hasJob ) {
            ChildWork.add(new ChildList("Start Working"));
            ChildWork.add(new ChildList("Quit the job"));

            SharedPreferences sharedPref = MainActivity.userSharedPref;
            Gson gson = new Gson();
            String json = sharedPref.getString(getResources().getString(R.string.saved_my_job_key), SharedPreferencesDefaultValues.DefaultMyJob);
            Job job = gson.fromJson(json, Job.class);
            workTitle.setText("Work: " + job.getName());
            workProgressPosition.setProgress(job.getPositionPoints());
            workPosition.setText("Position: " + job.getPosition());
            workCardView.setVisibility(View.VISIBLE);
        }
        else {
            ChildWork.add(new ChildList("Find a job"));
            workCardView.setVisibility(View.GONE);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences sharedPref = MainActivity.userSharedPref;
        Gson gson = new Gson();
        String json = sharedPref.getString(getResources().getString(R.string.saved_my_job_key), SharedPreferencesDefaultValues.DefaultMyJob);
        Job job = gson.fromJson(json, Job.class);
        if (job != null) UpdateJobStatus(true);
    }

}