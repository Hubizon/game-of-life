package com.howky.brothers.lifeonsteroids.education;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.howky.brothers.lifeonsteroids.MyApplication;
import com.howky.brothers.lifeonsteroids.education.expandable.ChildList;
import com.howky.brothers.lifeonsteroids.education.expandable.MyExpandableRecyclerAdapter;
import com.howky.brothers.lifeonsteroids.education.expandable.ParentList;
import com.howky.brothers.lifeonsteroids.R;
import com.howky.brothers.lifeonsteroids.utils.SharedPreferencesDefaultValues;
import com.howky.brothers.lifeonsteroids.work.Job;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class EduFragment extends Fragment
        implements View.OnClickListener, MyExpandableRecyclerAdapter.EduFragmentCallback{

    public static final String TITLE_SCHOOL = "School";
    public static final String TITLE_CRIMINAL = "Criminal";
    public static final String TITLE_WORK = "Work";

    private MyExpandableRecyclerAdapter adapter;

    private final List<ParentList> Parent = new ArrayList<>();
    private final List<ChildList> ChildSchool = new ArrayList<>();
    private final List<ChildList> ChildCriminal = new ArrayList<>();
    private final List<ChildList> ChildWork = new ArrayList<>();

    private CardView workCardView;
    private TextView workTitle;
    private TextView workPosition;
    private ProgressBar workProgressPosition;

    public EduFragment() {}

    public static EduFragment newInstance() {
        return new EduFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edu, container, false);

        RecyclerView recycler_view = view.findViewById(R.id.recycler_Expand);
        recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));

        SharedPreferences sharedPref = MyApplication.userSharedPref;
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
            workTitle.setText(getResources().getString(R.string.work) + " " + job.getName());
            workProgressPosition.setProgress(job.getPositionPoints());
            workPosition.setText(getResources().getString(R.string.position) + "" + job.getPosition());
        }


        if (!(Parent.size() > 0)) {
            ChildSchool.add(new ChildList("Go to school"));
            //ChildSchool.add(new ChildList("learn hard"));
            //ChildSchool.add(new ChildList("hand around"));
            ChildSchool.add(new ChildList("Get some skills"));
            //ChildSchool.add(new ChildList("Give up school"));
            Parent.add(new ParentList(TITLE_SCHOOL, ChildSchool));

            //ChildWork.add(new ChildList("work hard"));
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
        SharedPreferences sharedPref = MyApplication.userSharedPref;
        SharedPreferences.Editor editor = sharedPref.edit();
        Intent intent = null;

        startActivity(intent);

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

            SharedPreferences sharedPref = MyApplication.userSharedPref;
            Gson gson = new Gson();
            String json = sharedPref.getString(getResources().getString(R.string.saved_my_job_key), SharedPreferencesDefaultValues.DefaultMyJob);
            Job job = gson.fromJson(json, Job.class);
            workTitle.setText(getResources().getString(R.string.work) + "" + job.getName());
            workProgressPosition.setProgress(job.getPositionPoints());
            workPosition.setText(getResources().getString(R.string.position) + "" + job.getPosition());
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
        SharedPreferences sharedPref = MyApplication.userSharedPref;
        Gson gson = new Gson();
        String json = sharedPref.getString(getResources().getString(R.string.saved_my_job_key), SharedPreferencesDefaultValues.DefaultMyJob);
        Job job = gson.fromJson(json, Job.class);
        if (job != null) UpdateJobStatus(true);
    }

}