package com.example.hubert.gameoflife.Education;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hubert.gameoflife.Education.expandable.ChildList;
import com.example.hubert.gameoflife.Education.expandable.MyExpandableRecyclerAdapter;
import com.example.hubert.gameoflife.Education.expandable.ParentList;
import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.Utils.MyDialogFragment;
import com.example.hubert.gameoflife.Utils.SharedPreferencesDefaultValues;
import com.example.hubert.gameoflife.Work.FindJobActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class EduFragment extends Fragment implements View.OnClickListener{

    private static final String EDU_DIALOG_TAG = "edu_dialog_tag2";

    public static final String TITLE_SCHOOL = "School";
    public static final String TITLE_CRIMINAL = "Criminal";
    public static final String TITLE_WORK = "Work";

    private RecyclerView recycler_view;
    private MyExpandableRecyclerAdapter adapter;

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

        final List<ParentList> Parent = new ArrayList<>();
        final List<ChildList> ChildSchool = new ArrayList<>();
        final List<ChildList> ChildCriminal = new ArrayList<>();
        final List<ChildList> ChildWork = new ArrayList<>();


        ChildSchool.add(new ChildList("Go to school"));
        ChildSchool.add(new ChildList("learn hard"));
        ChildSchool.add(new ChildList("hand around"));
        ChildSchool.add(new ChildList("learn at home"));
        ChildSchool.add(new ChildList("Give up school"));
        Parent.add(new ParentList(TITLE_SCHOOL, ChildSchool));

        ChildWork.add(new ChildList("Start working"));
        ChildWork.add(new ChildList("work hard"));
        ChildWork.add(new ChildList("give up work"));
        Parent.add(new ParentList(TITLE_WORK, ChildWork));

        ChildCriminal.add(new ChildList("Get new friends"));
        ChildCriminal.add(new ChildList("steal stuff"));
        ChildCriminal.add(new ChildList("sell drugs"));
        ChildCriminal.add(new ChildList("threat teachers"));
        Parent.add(new ParentList(TITLE_CRIMINAL, ChildCriminal));

        RecyclerView.ItemAnimator animator = recycler_view.getItemAnimator();
        if (animator instanceof DefaultItemAnimator) {
            ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
        }

        adapter = new MyExpandableRecyclerAdapter(Parent, getContext());
        recycler_view.setAdapter(adapter);

//        Button goSchoolButton = view.findViewById(R.id.GoToSchoolEducation);
//        goSchoolButton.setOnClickListener(this);
//        Button learnInHomeButton = view.findViewById(R.id.LearnInHomeEducation);
//        learnInHomeButton.setOnClickListener(this);
//        Button giveUpSchoolButton = view.findViewById(R.id.GiveUpSchool);
//        giveUpSchoolButton.setOnClickListener(this);
//        Button getNewFriendsButton = view.findViewById(R.id.getNewFriendsCriminal);
//        getNewFriendsButton.setOnClickListener(this);
//        Button stealSomethingButton = view.findViewById(R.id.stealSomethingCriminal);
//        stealSomethingButton.setOnClickListener(this);
//        Button sellDrugsButton = view.findViewById(R.id.sellDrugsCriminal);
//        sellDrugsButton.setOnClickListener(this);
//        Button threatTeachersButton = view.findViewById(R.id.threatTeachersCriminal);
//        threatTeachersButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        SharedPreferences sharedPref = getActivity().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        Intent intent = null;
        DialogFragment newDialog = null;
        Random r = new Random();

        int view_id = view.getId();
        switch(view_id) {
            case R.id.GiveUpSchool:
                editor.putBoolean(getString(R.string.saved_is_in_school_now_key), false);
                intent = new Intent(getActivity().getApplicationContext(), FindJobActivity.class);
                break;

            case R.id.LearnInHomeEducation:
                intent = new Intent(getActivity().getApplicationContext(), LearnInHomeActivity.class);
                break;

            case R.id.GoToSchoolEducation:
                newDialog = MyDialogFragment.newInstance(view_id);
                break;

            case R.id.getNewFriendsCriminal:
                newDialog = MyDialogFragment.newInstance(view_id);
                break;

            case R.id.stealSomethingCriminal:
                if(r.nextInt(25) == 1) {
                    editor.putInt(getResources().getString(R.string.saved_character_money_key), 0);
                    Toast.makeText(getActivity().getApplicationContext(), ("You got busted! You lost all your money."), Toast.LENGTH_LONG).show();
                    if(r.nextInt(25) == 1)
                        if(sharedPref.getBoolean(getResources().getString(R.string.saved_have_safe_key), SharedPreferencesDefaultValues.DefaultHaveSafe))
                        {
                            editor.putInt(getResources().getString(R.string.saved_money_in_safe_key), 0);
                            Toast.makeText(getActivity().getApplicationContext(), ("Policeman found your safe! You lost all your money in safe."), Toast.LENGTH_LONG).show();
                        }
                }
                else
                {
                    editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) + 50));
                    Toast.makeText(getActivity().getApplicationContext(), ("You got 50 money!"), Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.sellDrugsCriminal:
                newDialog = MyDialogFragment.newInstance(view_id);
                break;

            case R.id.threatTeachersCriminal:
                if(r.nextInt(5) == 1)
                {
                    try {
                        JSONArray jsonArray = new JSONArray(sharedPref.getString(getResources().getString(R.string.saved_subjects_list_key), SharedPreferencesDefaultValues.DefaultSubjectsList));
                        JSONObject jsonObject = (JSONObject)(jsonArray.get(jsonArray.length() - 1));
                        if(jsonObject.getInt("subjectMark") <= 1)
                        {
                            Toast.makeText(getActivity().getApplicationContext(), ("Teacher reported this and you were expelled from school! You can not come back untill" + (sharedPref.getInt(getResources().getString(R.string.saved_date_years_key), SharedPreferencesDefaultValues.DefaultAgeYears) + 2) + "year"), Toast.LENGTH_LONG).show();
                            editor.putBoolean(getResources().getString(R.string.saved_can_go_to_school_key), false);
                            editor.putInt(getResources().getString(R.string.saved_year_when_can_go_to_school_key), (sharedPref.getInt(getResources().getString(R.string.saved_date_years_key), SharedPreferencesDefaultValues.DefaultAgeYears) + 2));
                        }
                        else
                        {
                            Toast.makeText(getActivity().getApplicationContext(), ("Teacher reported this and you got 1 from Behavior! Next time you will be expelled from school."), Toast.LENGTH_LONG).show();
                            jsonObject.put("subjectMark", 1);
                        }
                        jsonArray.put(jsonArray.length() - 1, jsonObject);
                        editor.putString(getResources().getString(R.string.saved_subjects_list_key), jsonArray.toString());
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    try
                    {
                        JSONArray jsonArray = new JSONArray(sharedPref.getString(getResources().getString(R.string.saved_subjects_list_key), SharedPreferencesDefaultValues.DefaultSubjectsList));
                        int rnd = r.nextInt(jsonArray.length());
                        JSONObject jsonObject = (JSONObject)jsonArray.get(rnd);
                        jsonObject.put("subjectMark", (jsonObject.getInt("subjectMark") + 1 ));
                        if(jsonObject.getInt("subjectMark") >= 6)
                            Toast.makeText(getActivity().getApplicationContext(), ("You already have 6 from " + jsonObject.getString("subjectName")), Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getActivity().getApplicationContext(), ("You have now " + jsonObject.get("subjectMark") + " from " + jsonObject.getString("subjectName")), Toast.LENGTH_LONG).show();
                        jsonArray.put(rnd, jsonObject);
                        editor.putString(getResources().getString(R.string.saved_subjects_list_key), jsonArray.toString());
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                break;

            default:
                break;
        }

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
}