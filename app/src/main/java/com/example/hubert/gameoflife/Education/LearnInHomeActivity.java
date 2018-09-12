package com.example.hubert.gameoflife.Education;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.Utils.SharedPreferencesDefaultValues;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LearnInHomeActivity extends AppCompatActivity implements RecyclerViewSubjectAdapter.ItemClickListener {


    RecyclerViewSubjectAdapter adapter;
    static JSONArray jsonArray;
    static Gson gson = new Gson();
    static JSONObject json = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_in_home);

        SharedPreferences sharedPreferences = getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);

        ArrayList<String> subjectNames = new ArrayList<>();

        try {
            jsonArray = new JSONArray(sharedPreferences.getString(getApplicationContext().getResources().getString(R.string.saved_subjects_list_key), SharedPreferencesDefaultValues.DefaultSubjectsList));
            for (int i = 0; i <= (jsonArray.length() - 1); i++)
            {
                json  = (JSONObject) jsonArray.get(i);
                subjectNames.add(json.get("subjectName").toString());
            }
        }
        catch (JSONException e)
        { }

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewSubjects);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewSubjectAdapter(this, subjectNames);
        adapter.setClickListener(LearnInHomeActivity.this);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onItemClick(View view, int position) {
        if(view.findViewById(R.id.subjectHomework).getVisibility() == View.VISIBLE)
        {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.pager, new DoingSomethingFragment())
                    .commit();
        }
    }
}
