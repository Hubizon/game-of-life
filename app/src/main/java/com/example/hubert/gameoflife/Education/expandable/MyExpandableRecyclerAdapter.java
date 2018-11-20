package com.example.hubert.gameoflife.Education.expandable;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hubert.gameoflife.Education.EduFragment;
import com.example.hubert.gameoflife.MainActivity;
import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.Education.SkillsActivity;
import com.example.hubert.gameoflife.Utils.MyDialogFragment;
import com.example.hubert.gameoflife.Utils.SharedPreferencesDefaultValues;
import com.example.hubert.gameoflife.Work.ChooseJobActivity;
import com.example.hubert.gameoflife.Work.Job;
import com.google.gson.Gson;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;
import java.util.Random;

public class MyExpandableRecyclerAdapter extends ExpandableRecyclerViewAdapter<MyParentViewHolder,MyChildViewHolder> {

    private static final String EDU_DIALOG_TAG = "edu_dialog_tag";

    private static final int INDEX_SCHOOL = 0;
    private static final int INDEX_WORK = 1;
    private static final int INDEX_CRIMINAL = 2;

    private static final int go_to_school_index = 0;
    private static final int get_some_skills = 1;
    private static final int learn_hard_index = 3;
    private static final int hang_around_index = 2;
    private static final int learn_at_home_index = 1;
    private static final int give_up_school_index = 4;

    private static final int get_new_friends_index = 0;
    private static final int steal_stuff_index = 1;
    private static final int sell_drugs_index = 2;
    private static final int threat_teacher_index = 3;

    private static final int start_working_index = 0;
    private static final int give_up_work_index = 1;
    private static final int work_hard_index = 2;

    Context mContext;

    EduFragmentCallback mCallback;
    public interface EduFragmentCallback {
        void UpdateJobStatus(boolean hasJob);
    }


    public MyExpandableRecyclerAdapter(List<ParentList> groups, Context context, EduFragmentCallback eduListener) {
        super(groups);
        mContext = context;
        mCallback = eduListener;
    }

    @Override
    public MyParentViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.expandable_parent_item, parent, false);
        return new MyParentViewHolder(view);
    }

    @Override
    public MyChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.expandable_child_item, parent, false);
        return new MyChildViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(MyChildViewHolder holder, final int flatPosition, final ExpandableGroup group, final int childIndex) {

        final ChildList childItem = ((ParentList) group).getItems().get(childIndex);
        holder.onBind(childItem.getTitle());
        holder.listChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPref = MainActivity.userSharedPref;
//                SharedPreferences sharedPref = mContext.getSharedPreferences(mContext.getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                Intent intent = null;
                DialogFragment newDialog = null;
                Random r = new Random();

                Gson gson = new Gson();
                String json = sharedPref.getString(mContext.getResources().getString(R.string.saved_my_job_key), SharedPreferencesDefaultValues.DefaultMyJob);
                Job mJob = gson.fromJson(json, Job.class);

                int view_id = view.getId();
                if (group.getTitle().equals(EduFragment.TITLE_SCHOOL)) {
                    switch (childIndex) {
                        case go_to_school_index:
                            newDialog = MyDialogFragment.newInstanceWithPosition(view_id, group.getTitle(), go_to_school_index);
                            break;

                        case get_some_skills:
                            intent = new Intent(mContext.getApplicationContext(), SkillsActivity.class);
                            break;
//                        case learn_hard_index:
//                           // intent = new Intent(mContext.getApplicationContext(), LearnInHomeActivity.class);
//                            break;
//                         case hang_around_index:
//                             newDialog = MyDialogFragment.newInstanceWithPosition(view_id, group.getTitle(), hang_around_index);
//                            break;
                       // case learn_at_home_index:
                            /*FragmentManager fragmentManager = ((AppCompatActivity) mContext).getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            SkillsFragment fragment = new SkillsFragment();
                            fragmentTransaction.add(R.id.pagerSkills, fragment);
                            fragmentTransaction.commit();*/
                           // intent = new Intent(mContext.getApplicationContext(), SkillsActivity.class);
                          //  break;
                      //  case give_up_school_index:
                          //  editor.putBoolean(mContext.getString(R.string.saved_is_in_school_now_key), false);
                            //intent = new Intent(mContext.getApplicationContext(), FindJobActivity.class);
                           // break;
                    }
                } else if (group.getTitle().equals(EduFragment.TITLE_CRIMINAL)) {
                    switch (childIndex) {
                        case get_new_friends_index:
                            //editor.putInt(mContext.getResources().getString(R.string.saved_criminal_points_key), sharedPref.getInt(mContext.getResources().getString(R.string.saved_criminal_points_key), SharedPreferencesDefaultValues.DefaultCriminalPoints) + 2);
                            newDialog = MyDialogFragment.newInstanceWithPosition(view_id, group.getTitle(), get_new_friends_index);
                            break;
                        case steal_stuff_index:
                            if(r.nextInt(25) == 1) {
                                editor.putInt(mContext.getString(R.string.saved_character_money_key), 0);
                                Toast.makeText(mContext.getApplicationContext(), (mContext.getResources().getString(R.string.got_busted)), Toast.LENGTH_LONG).show();
                                if(r.nextInt(25) == 1)
                                    if(sharedPref.getBoolean(mContext.getString(R.string.saved_have_safe_key), SharedPreferencesDefaultValues.DefaultHaveSafe))
                                    {
                                        editor.putInt(mContext.getString(R.string.saved_money_in_safe_key), 0);
                                        Toast.makeText(mContext.getApplicationContext(), (mContext.getResources().getString(R.string.found_safe)), Toast.LENGTH_LONG).show();
                                    }
                            }
                            else
                            {
                                editor.putInt(mContext.getString(R.string.saved_character_money_key), ((sharedPref.getInt(mContext.getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) + 50));
                                Toast.makeText(mContext.getApplicationContext(), (mContext.getResources().getString(R.string.got50Money)), Toast.LENGTH_LONG).show();
                            }
                            break;
                        case sell_drugs_index:
                            newDialog = MyDialogFragment.newInstanceWithPosition(view_id, group.getTitle(), sell_drugs_index);
                            break;
                        case threat_teacher_index:
                            if(r.nextInt(5) == 1)
                            {
                                /*try {
                                    JSONArray jsonArray = new JSONArray(sharedPref.getString(mContext.getResources().getString(R.string.saved_subjects_list_key), SharedPreferencesDefaultValues.DefaultSubjectsList));
                                    JSONObject jsonObject = (JSONObject)(jsonArray.get(jsonArray.length() - 1));
                                    if(jsonObject.getInt("subjectMark") <= 1)
                                    {
                                        Toast.makeText(mContext.getApplicationContext(), ("Teacher reported this and you were expelled from school! You can not come back untill" + (sharedPref.getInt(mContext.getResources().getString(R.string.saved_date_years_key), SharedPreferencesDefaultValues.DefaultAgeYears) + 2) + "year"), Toast.LENGTH_LONG).show();
                                        editor.putBoolean(mContext.getResources().getString(R.string.saved_can_go_to_school_key), false);
                                        editor.putInt(mContext.getResources().getString(R.string.saved_year_when_can_go_to_school_key), (sharedPref.getInt(mContext.getResources().getString(R.string.saved_date_years_key), SharedPreferencesDefaultValues.DefaultAgeYears) + 2));
                                    }
                                    else
                                    {
                                        Toast.makeText(mContext.getApplicationContext(), ("Teacher reported this and you got 1 from Behavior! Next time you will be expelled from school."), Toast.LENGTH_LONG).show();
                                        jsonObject.put("subjectMark", 1);
                                    }
                                    jsonArray.put(jsonArray.length() - 1, jsonObject);
                                    editor.putString(mContext.getResources().getString(R.string.saved_subjects_list_key), jsonArray.toString());
                                }
                                catch (JSONException e)
                                { }*/

                                if(sharedPref.getInt(mContext.getString(R.string.saved_education_points_key), SharedPreferencesDefaultValues.DefaultEducationPoints) >= 250)
                                {
                                    Toast.makeText(mContext.getApplicationContext(), (mContext.getResources().getString(R.string.reported2xWeakerGrades)), Toast.LENGTH_LONG).show();
                                    editor.putInt(mContext.getResources().getString(R.string.saved_education_points_key), sharedPref.getInt(mContext.getResources().getString(R.string.saved_education_points_key), SharedPreferencesDefaultValues.DefaultEducationPoints) / 2);
                                }
                                else
                                {
                                    Toast.makeText(mContext.getApplicationContext(), (mContext.getResources().getString(R.string.reported1FromAllSubjects)), Toast.LENGTH_LONG).show();
                                    editor.putInt(mContext.getResources().getString(R.string.saved_education_points_key), 0);
                                }
                            }
                            else
                            {
                                Toast.makeText(mContext.getApplicationContext(), (mContext.getResources().getString(R.string.aLittleBetterMarks)), Toast.LENGTH_LONG).show();
                                editor.putInt(mContext.getResources().getString(R.string.saved_education_points_key), sharedPref.getInt(mContext.getResources().getString(R.string.saved_education_points_key), SharedPreferencesDefaultValues.DefaultEducationPoints) + 50);

                                /*try
                                {
                                    JSONArray jsonArray = new JSONArray(sharedPref.getString(mContext.getString(R.string.saved_subjects_list_key), SharedPreferencesDefaultValues.DefaultSubjectsList));
                                    int rnd = r.nextInt(jsonArray.length());
                                    JSONObject jsonObject = (JSONObject)jsonArray.get(rnd);
                                    jsonObject.put("subjectMark", (jsonObject.getInt("subjectMark") + 1 ));
                                    if(jsonObject.getInt("subjectMark") >= 6)
                                        Toast.makeText(mContext.getApplicationContext(), ("You already have 6 from " + jsonObject.getString("subjectName")), Toast.LENGTH_LONG).show();
                                    else
                                        Toast.makeText(mContext.getApplicationContext(), ("You have now " + jsonObject.get("subjectMark") + " from " + jsonObject.getString("subjectName")), Toast.LENGTH_LONG).show();
                                    jsonArray.put(rnd, jsonObject);
                                    editor.putString(mContext.getString(R.string.saved_subjects_list_key), jsonArray.toString());
                                }
                                catch (JSONException e)
                                { }*/
                            }
                            break;
                    }
                } else if (group.getTitle().equals(EduFragment.TITLE_WORK)) {
                    switch (childIndex) {
                        case start_working_index:
                            if(mJob == null) {
                                Intent intentWork = new Intent(mContext.getApplicationContext(), ChooseJobActivity.class);
                                mContext.startActivity(intentWork);
                            }
                            else {
                                newDialog = MyDialogFragment.newInstanceWithPosition(view_id, group.getTitle(), start_working_index);
                            }
                            break;
                        case give_up_work_index:
                            Toast.makeText(mContext, "You are unemployed now!", Toast.LENGTH_SHORT).show();
                            mJob = null;
                            editor.putString(mContext.getResources().getString(R.string.saved_my_job_key), gson.toJson(mJob));
                            //EduFragment.changeWorkStatus(false);
                            mCallback.UpdateJobStatus(false);
                            break;
                    }
                }

                if (intent != null) mContext.startActivity(intent);
                    else if (newDialog != null) newDialog.show(((AppCompatActivity) mContext).getSupportFragmentManager(), EDU_DIALOG_TAG);

                editor.apply();
            }

        });
    }

    @Override
    public void onBindGroupViewHolder(MyParentViewHolder holder, int flatPosition, final ExpandableGroup group) {
        holder.setParentTitle(group);
    }
}
