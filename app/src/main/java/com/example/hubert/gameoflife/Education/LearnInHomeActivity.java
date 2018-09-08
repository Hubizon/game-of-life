package com.example.hubert.gameoflife.Education;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hubert.gameoflife.DoingSomethingFragment;
import com.example.hubert.gameoflife.MainActivity;
import com.example.hubert.gameoflife.R;

import java.util.ArrayList;

public class LearnInHomeActivity extends AppCompatActivity implements RecyclerViewSubjectAdapter.ItemClickListener {


    RecyclerViewSubjectAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_in_home);

        ArrayList<String> subjectNames = new ArrayList<>();
        for(int i = 0; i <= (MainActivity.subjectsList.length - 1); i++)
            subjectNames.add(MainActivity.subjectsList[(i)].getSubjectName());

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
