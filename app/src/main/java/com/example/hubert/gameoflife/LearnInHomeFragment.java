package com.example.hubert.gameoflife;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

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
        subjectNames.add("Mathematics: 4");
        subjectNames.add("English: 4");
        subjectNames.add("Languages: 4");
        subjectNames.add("Art: 4");
        subjectNames.add("Music: 4");
        subjectNames.add("Psychical Education: 4");
        subjectNames.add("Information Technology: 4");
        subjectNames.add("Biology: 4");
        subjectNames.add("Chemistry: 4");
        subjectNames.add("History: 4");
        subjectNames.add("Behavior: 4");

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
        //    Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
