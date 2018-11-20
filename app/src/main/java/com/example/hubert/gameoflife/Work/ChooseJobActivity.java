package com.example.hubert.gameoflife.Work;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hubert.gameoflife.R;

public class ChooseJobActivity extends AppCompatActivity{


    private ViewPager mPager;
    private CustomPagerJobAdapter mPagerAdapter;
    private TabLayout mTabLayout;
    private int[] tabIcons = {
            R.drawable.job,
            R.drawable.crim_job,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);

        mPager = findViewById(R.id.pagerSkills);
        mPagerAdapter = new CustomPagerJobAdapter(getSupportFragmentManager(), this);
        mPager.setAdapter(mPagerAdapter);

        mTabLayout = findViewById(R.id.tablayoutskills);
        mTabLayout.setupWithViewPager(mPager);
        setupTabIcons();
    }

    private void setupTabIcons() {
        mTabLayout.getTabAt(0).setIcon(tabIcons[0]);
        mTabLayout.getTabAt(1).setIcon(tabIcons[1]);
    }



    /*public static Job[] officeJobsList = new Job[] {
            new Job("Lawyer", 80, 90, 100, 15, "History", R.string.saved_communication_skills_key),
            new Job("Reporter", 50, 65, 80, 5, "English", R.string.saved_communication_skills_key),
            new Job("Engraver", 65, 75, 85, 7, "Art", R.string.saved_communication_skills_key),
            new Job("Programmer", 60, 75, 90, 7, "Information Technology", R.string.saved_programming_skills_key),
            new Job("Businessman", 80, 110, 150, 50, "All", R.string.saved_communication_skills_key)
     };

   public static Job[] artsWorksList = new Job[] {
            new Job("Writer", 40, 60, 90, 10, "English", R.string.saved_drawing_skills_key),
            new Job("Painter", 45, 60, 85, 10, "English", R.string.saved_drawing_skills_key),
    };

    public static Job[] outsideWorksList = new Job[] {
            new Job("Footballer", 60, 65, 120, 5, "Psychical Education", 0),
            new Job("Basketball Player", 55, 70, 115, 5, "Psychical Education", 0),
            new Job("Swimmer", 50, 75, 85, 5, "Psychical Education", 0),
            new Job("Runner", 40, 50, 85, 5, "Psychical Education", 0),
            new Job("Swimmer", 40, 50, 90, 5, "Psychical Education", 0),
            new Job("Cyclist", 40, 50, 100, 5, "Psychical Education", 0),
            new Job("Geologist", 55, 65, 75, 15, "Biology", 0),
            new Job("Dustman", 45, 50, 65, 1, "Psychical Education", 0),
    };

    public static Job[] otherWorksList = new Job[] {
            new Job("YouTuber", 30, 75, 85, 5, "English", R.string.saved_recording_skills_key),
            new Job("Streamer", 25, 75, 85, 5, "English", R.string.saved_recording_skills_key),
            new Job("Blogger", 35, 75, 85, 5, "English", R.string.saved_communication_skills_key),
            new Job("Doctor", 60, 75, 95, 50, "Biology", 0),
            new Job("Teacher", 30, 55, 75, 5, "All", R.string.saved_communication_skills_key),
            new Job("Scientist", 40, 75, 95, 50, "All", 0),
            new Job("Salesperson", 20, 30, 50, 1, "Mathematics", 0),
    };*/

}
