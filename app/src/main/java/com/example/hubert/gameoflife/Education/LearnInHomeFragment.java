package com.example.hubert.gameoflife.Education;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hubert.gameoflife.DoingSomethingFragment;
import com.example.hubert.gameoflife.MainActivity;
import com.example.hubert.gameoflife.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LearnInHomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LearnInHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LearnInHomeFragment extends Fragment implements RecyclerViewSubjectAdapter.ItemClickListener {

    public static int NumberToDoForDoingSomething;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LearnInHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EducationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LearnInHomeFragment newInstance(String param1, String param2) {
        LearnInHomeFragment fragment = new LearnInHomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    RecyclerViewSubjectAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_learn_in_home,
                container, false);

        ArrayList<String> subjectNames = new ArrayList<>();
        for(int i = 0; i <= (MainActivity.subjectsList.length - 1); i++)
            subjectNames.add(MainActivity.subjectsList[(i)].getSubjectName());

        // set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewSubjects);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new RecyclerViewSubjectAdapter(view.getContext(), subjectNames);
        adapter.setClickListener(LearnInHomeFragment.this);
        recyclerView.setAdapter(adapter);


        return view;
    }

    @Override
    public void onItemClick(View view, int position) {
        if(view.findViewById(R.id.subjectHomework).getVisibility() == View.VISIBLE)
        {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .add(R.id.pager, new DoingSomethingFragment())
                    .commit();
        }
    }
}
