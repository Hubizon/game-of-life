package com.example.hubert.gameoflife;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DoingSomethingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DoingSomethingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DoingSomethingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DoingSomethingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DoingSomethingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DoingSomethingFragment newInstance(String param1, String param2) {
        DoingSomethingFragment fragment = new DoingSomethingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Timer timer = new Timer();
        TimerTask updateValues = new DoingSomethingFragment.ChangeLabelsDoingSomething();
        timer.scheduleAtFixedRate(updateValues, 0, 2000);
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_computer,
                container, false);

        /*((TextView)(view.findViewById(R.id.money_doingSomething))).setText("$ " + MainActivity.Money);
        ((TextView)(view.findViewById(R.id.time_doingSomething))).setText(MainActivity.DateYears + "." + MainActivity.DateMonths + "." + MainActivity.DateDays + " "
                + MainActivity.TimeHours + ":" + "00");

        ((ProgressBar)(view.findViewById(R.id.progressBar_health_doingSomething))).setProgress((MainActivity.Hungry / 10));
        ((ProgressBar)(view.findViewById(R.id.progressBar_hungry_doingSomething))).setProgress((MainActivity.Health / 10));
        ((ProgressBar)(view.findViewById(R.id.progressBar_energy_doingSomething))).setProgress((MainActivity.Energy / 10));
        ((ProgressBar)(view.findViewById(R.id.progressBar_happiness_doingSomething))).setProgress((MainActivity.Happiness / 10));*/



        return view;
    }

    class ChangeLabelsDoingSomething extends TimerTask {

        public void run() {
            switch (MainActivity.ThingToDoForDoingSomething)
            {
                case "Sleep":
                    MainActivity.Energy += MainActivity.MyLodging.getGivenEnergy();
                    MainActivity.Health += MainActivity.MyLodging.getGivenHealth();
                    MainActivity.Happiness += MainActivity.MyLodging.getGivenHappiness();
                    break;

                case "WatchTV":
                    MainActivity.Happiness += MainActivity.MyTv.getGivenFun();
                    break;

                case "PlayComputer":
                    MainActivity.Happiness += MainActivity.MyComputer.getGivenFun();
                    break;

                case "PlayPhone":
                    MainActivity.Happiness += MainActivity.MyPhone.getGivenFun();
                    break;

                case "TalkOnMessengers":
                    MainActivity.CommunicationSkills += 2;
                    MainActivity.Hungry--;
                    MainActivity.Energy--;
                    break;

                case "GoToSchool":
                    for(int i = 0; i < MainActivity.subjectsList.length; i++)
                        MainActivity.subjectsList[i].increaseToAnotherMark(5);
                    MainActivity.Hungry--;
                    MainActivity.Energy--;
                    MainActivity.Happiness -= 2;
                    break;

                case "GoToSchoolLearnHard":
                    for(int i = 0; i < MainActivity.subjectsList.length; i++)
                        MainActivity.subjectsList[i].increaseToAnotherMark(10);
                    MainActivity.Hungry--;
                    MainActivity.Energy -= 4;
                    MainActivity.Happiness -= 7;
                    break;

                case "GoToSchoolHangAround":
                    for(int i = 0; i < MainActivity.subjectsList.length; i++)
                        MainActivity.subjectsList[i].decreaseToAnotherMark(10);
                    MainActivity.subjectsList[MainActivity.subjectsList.length - 1].decreaseToAnotherMark(15);
                    MainActivity.Hungry--;
                    MainActivity.Energy--;
                    MainActivity.Happiness += 25;
                    break;

                case "DoHomework":
                    view.findViewById(R.id.textview_homework).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.progressBar_homework_doingSomething).setVisibility(View.VISIBLE);
                    ((ProgressBar)(view.findViewById(R.id.progressBar_homework_doingSomething))).setProgress(
                            ((ProgressBar)(view.findViewById(R.id.progressBar_homework_doingSomething))).getProgress() + 25);
                    if(((ProgressBar)(view.findViewById(R.id.progressBar_homework_doingSomething))).getProgress() >= 100)
                        MainActivity.subjectsList[LearnInHomeFragment.NumberToDoForDoingSomething].increaseToAnotherMark(50);

                    // TODO: fix this error
                   // FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                   // ft.remove(new DoingSomethingFragment());
                  //  ft.commit();

                    break;
            }


            ((ProgressBar)(view.findViewById(R.id.progressBar_health_doingSomething))).setProgress((MainActivity.Hungry / 10));
            ((ProgressBar)(view.findViewById(R.id.progressBar_hungry_doingSomething))).setProgress((MainActivity.Health / 10));
            ((ProgressBar)(view.findViewById(R.id.progressBar_energy_doingSomething))).setProgress((MainActivity.Energy / 10));
            ((ProgressBar)(view.findViewById(R.id.progressBar_happiness_doingSomething))).setProgress((MainActivity.Happiness / 10));

            ((TextView)(view.findViewById(R.id.time_doingSomething))).setText(MainActivity.DateYears + "." + MainActivity.DateMonths + "." + MainActivity.DateDays + " "
                    + MainActivity.TimeHours + ":" + "00");
        }
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
