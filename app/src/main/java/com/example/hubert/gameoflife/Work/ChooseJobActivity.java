package com.example.hubert.gameoflife.Work;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.Utils.SharedPreferencesDefaultValues;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ChooseJobActivity extends AppCompatActivity implements ChooseJobAdapter.ItemClickListener{

    private int id;
    View view;

    public static Job[] officeJobsList = new Job[] {
            new Job("Lawyer", 80),
            new Job("Reporter", 40),
            new Job("Engraver", 55),
            new Job("Programmer", 60),
            new Job("Businessman", 70),
    };

    public static Job[] criminalJobsList = new Job[] {
            new Job("Pickpocket", 30),
            new Job("Thief", 45),
            new Job("Drug dealer", 60),
            new Job("Terrorist", 70),
            new Job("Kidnap kids", 75),
            new Job("Mafia member", 90),
            new Job("Assasin", 125),
    };

    public static Job[] artsWorksList = new Job[] {
            new Job("Writer", 40),
            new Job("Painter", 45),
    };

    public static Job[] outsideWorksList = new Job[] {
            new Job("Footballer", 60),
            new Job("Basketball Player", 55),
            new Job("Swimmer", 50),
            new Job("Runner", 40),
            new Job("Swimmer", 40),
            new Job("Cyclist", 40),
            new Job("Geologist", 55),
            new Job("Dustman", 45),
    };

    public static Job[] otherWorksList = new Job[] {
            new Job("YouTuber", 30),
            new Job("Streamer", 25),
            new Job("Blogger", 35),
            new Job("Doctor", 60),
            new Job("Teacher", 25),
            new Job("Scientist", 40),
            new Job("Salesperson", 20),
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_job);
        view = new View(this);

        ArrayList<String> worksNames = new ArrayList<>();
        ArrayList<String> worksSalary = new ArrayList<>();

        SharedPreferences sharedPref = getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        ((TextView) (findViewById(R.id.money_choose_work))).setText("$ " + sharedPref.getInt(getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney));

        id = getIntent().getIntExtra(getResources().getString(R.string.send_shop_click_id), R.id.cardview_food);
        switch (id) {
            case R.id.cardview_officeWork:
                for(int i = 0; i < officeJobsList.length; i++)
                    worksNames.add(officeJobsList[i].getName());
                for(int i = 0; i < officeJobsList.length; i++)
                    worksSalary.add("$" + officeJobsList[i].getSalary());
                ((TextView) (findViewById(R.id.workToChoose))).setText("OFFICE WORK");
                break;

            case R.id.cardview_criminalWork:
                for(int i = 0; i < criminalJobsList.length; i++)
                    worksNames.add(criminalJobsList[i].getName());
                for(int i = 0; i < criminalJobsList.length; i++)
                    worksSalary.add("$" + criminalJobsList[i].getSalary());
                ((TextView) (findViewById(R.id.workToChoose))).setText("CRIMINAL WORK");
                break;

            case R.id.cardview_artsWork:
                for(int i = 0; i < artsWorksList.length; i++)
                    worksNames.add(artsWorksList[i].getName());
                for(int i = 0; i < artsWorksList.length; i++)
                    worksSalary.add("$" + artsWorksList[i].getSalary());
                ((TextView) (findViewById(R.id.workToChoose))).setText("ARTS WORK");
                break;

            case R.id.cardview_outsideWork:
                for(int i = 0; i < outsideWorksList.length; i++)
                    worksNames.add(outsideWorksList[i].getName());
                for(int i = 0; i < outsideWorksList.length; i++)
                    worksSalary.add("$" + outsideWorksList[i].getSalary());
                ((TextView) (findViewById(R.id.workToChoose))).setText("OUTSIDE WORK");
                break;

            case R.id.cardview_otherWork:
                for(int i = 0; i < otherWorksList.length; i++)
                    worksNames.add(otherWorksList[i].getName());
                for(int i = 0; i < otherWorksList.length; i++)
                    worksSalary.add("$" + otherWorksList[i].getSalary());
                ((TextView) (findViewById(R.id.workToChoose))).setText("OTHER WORK");
                break;

            default:
                break;
        }

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewChooseWork);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ChooseJobAdapter(this, worksNames, worksSalary);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    ChooseJobAdapter adapter;

    @Override
    public void onItemClick(View view, int position) {

        SharedPreferences sharedPref = getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        Gson gson = new Gson();

        switch (id) {
            case R.id.cardview_officeWork:
                editor.putBoolean(getResources().getString(R.string.saved_is_in_school_now_key), false);
                editor.putString(getResources().getString(R.string.saved_my_job_key), gson.toJson(officeJobsList[position]));
                break;

            case R.id.cardview_criminalWork:
                editor.putBoolean(getResources().getString(R.string.saved_is_in_school_now_key), false);
                editor.putString(getResources().getString(R.string.saved_my_job_key), gson.toJson(criminalJobsList[position]));
                break;

            case R.id.cardview_artsWork:
                editor.putBoolean(getResources().getString(R.string.saved_is_in_school_now_key), false);
                editor.putString(getResources().getString(R.string.saved_my_job_key), gson.toJson(artsWorksList[position]));
                break;

            case R.id.cardview_outsideWork:
                editor.putBoolean(getResources().getString(R.string.saved_is_in_school_now_key), false);
                editor.putString(getResources().getString(R.string.saved_my_job_key), gson.toJson(outsideWorksList[position]));
                break;

            case R.id.cardview_otherWork:
                editor.putBoolean(getResources().getString(R.string.saved_is_in_school_now_key), false);
                editor.putString(getResources().getString(R.string.saved_my_job_key), gson.toJson(otherWorksList[position]));
                break;

            default:
                break;
        }
        editor.apply();

        //TODO: Michal!!!
    }
}
