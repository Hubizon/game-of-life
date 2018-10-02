package com.example.hubert.gameoflife.Education;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hubert.gameoflife.CustomPagerAdapter;
import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.Shop.RecyclerViewShopBuyAdapter;
import com.example.hubert.gameoflife.Utils.SharedPreferencesDefaultValues;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link SkillsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SkillsFragment extends Fragment implements RecyclerViewSkillsAdapter.ItemClickListener {

    public SkillsFragment() {
        // Required empty public constructor
    }


    public static SkillsFragment newInstance() {
        SkillsFragment fragment = new SkillsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    RecyclerViewSkillsAdapter adapter;

    private ViewPager mPager;
    private CustomPagerAdapter mPagerAdapter;
    private TabLayout mTabLayout;
    private int[] tabIcons = {
            R.drawable.profile_icon,
            R.drawable.education_icon,
            R.drawable.shop_icon,
            R.drawable.girlboyfriend_icon,
            R.drawable.house_icon
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_skills, container, false);

        mPager = view.findViewById(R.id.pager);
        mPagerAdapter = new CustomPagerAdapter(getActivity().getSupportFragmentManager(), getContext());
        mPager.setAdapter(mPagerAdapter);

        mTabLayout = view.findViewById(R.id.tablayout);
        mTabLayout.setupWithViewPager(mPager);
        setupTabIcons();

        ArrayList<String> mSkillsNames = new ArrayList<>();
        ArrayList<String> mSkillsPrices = new ArrayList<>();

        SharedPreferences sharedPref = getActivity().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        try {
            JSONArray jsonArray = new JSONArray(sharedPref.getString(getResources().getString(R.string.saved_skills_education_list_skey), SharedPreferencesDefaultValues.DefaultSkillsEducationList));

            for(int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if(!jsonObject.getBoolean("isLearned"))
                {
                    mSkillsNames.add(jsonObject.getString("name"));
                    mSkillsPrices.add(jsonObject.getString("price") + "$");
                }
            }

        } catch (JSONException e) {
        }

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewSkills);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecyclerViewSkillsAdapter(getContext(), mSkillsNames, mSkillsPrices);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void setupTabIcons() {
        mTabLayout.getTabAt(0).setIcon(tabIcons[0]);
        mTabLayout.getTabAt(1).setIcon(tabIcons[1]);
        mTabLayout.getTabAt(2).setIcon(tabIcons[2]);
        mTabLayout.getTabAt(3).setIcon(tabIcons[3]);
        mTabLayout.getTabAt(4).setIcon(tabIcons[4]);
    }

    @Override
    public void onItemClick(View view, int position) {
        //TODO: fill it
    }
}
