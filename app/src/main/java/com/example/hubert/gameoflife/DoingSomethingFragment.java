package com.example.hubert.gameoflife;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
        Timer timer = new Timer();
        TimerTask updateValues = new ChangeLabelsDoingSomething();
        timer.scheduleAtFixedRate(updateValues, 0, 2000);
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_doing_something,
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
            switch (view.getId())
            {
                case R.id.sleepHome:
                    MainActivity.Energy += MainActivity.MyLodging.getGivenEnergy();
                    MainActivity.Health += MainActivity.MyLodging.getGivenHealth();
                    MainActivity.Happiness += MainActivity.MyLodging.getGivenHappiness();
                    break;

                case R.id.watchTvHome:
                    if(MainActivity.MyTv != null)
                        MainActivity.Happiness += MainActivity.MyTv.getGivenFun();
                    else
                        Toast.makeText(getActivity().getApplicationContext(), "Unfortunately you don't have a televisor", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.playComputer:
                    if(MainActivity.MyComputer == null || MainActivity.MyPhone == null)
                    {
                        if(MainActivity.MyPhone == null)
                            MainActivity.Happiness += MainActivity.MyComputer.getGivenFun();
                        else if(MainActivity.MyComputer == null)
                            MainActivity.Happiness += MainActivity.MyPhone.getGivenFun();
                    }
                    else
                    {
                        if(MainActivity.MyComputer.getPrice() >= MainActivity.MyPhone.getPrice())
                            MainActivity.Happiness += MainActivity.MyComputer.getGivenFun();
                        else
                            MainActivity.Happiness += MainActivity.MyPhone.getGivenFun();
                    }
                    break;

                case R.id.talkComputer:
                    MainActivity.CommunicationSkills += 2;
                    MainActivity.Hungry--;
                    MainActivity.Energy--;
                    break;

                case R.id.GoToSchoolEducation:
                    for(int i = 0; i < MainActivity.subjectsList.length; i++)
                        MainActivity.subjectsList[i].increaseToAnotherMark(5);
                    MainActivity.Hungry--;
                    MainActivity.Energy--;
                    MainActivity.Happiness -= 2;
                    break;

                case R.id.GoToSchoolLearnHardEducation:
                    for(int i = 0; i < MainActivity.subjectsList.length; i++)
                        MainActivity.subjectsList[i].increaseToAnotherMark(10);
                    MainActivity.Hungry--;
                    MainActivity.Energy -= 4;
                    MainActivity.Happiness -= 7;
                    break;

                case R.id.GoToSchoolHangAroundEducation:
                    for(int i = 0; i < MainActivity.subjectsList.length; i++)
                        MainActivity.subjectsList[i].decreaseToAnotherMark(10);
                    MainActivity.subjectsList[MainActivity.subjectsList.length - 1].decreaseToAnotherMark(15);
                    MainActivity.Hungry--;
                    MainActivity.Energy--;
                    MainActivity.Happiness += 25;
                    break;

               /* case R.id.subjectCardView:
                    view.findViewById(R.id.textview_homework).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.progressBar_homework_doingSomething).setVisibility(View.VISIBLE);
                    ((ProgressBar)(view.findViewById(R.id.progressBar_homework_doingSomething))).setProgress(
                            ((ProgressBar)(view.findViewById(R.id.progressBar_homework_doingSomething))).getProgress() + 25);
                    if(((ProgressBar)(view.findViewById(R.id.progressBar_homework_doingSomething))).getProgress() >= 100)
                        MainActivity.subjectsList[LearnInHomeFragment.NumberToDoForDoingSomething].increaseToAnotherMark(50);

                    getActivity().getSupportFragmentManager().beginTransaction()
                            .add(R.id.mainFragmentHolder, new DoingSomethingFragment())
                            .commit();

                    break;*/
            }


            ((ProgressBar)(view.findViewById(R.id.progressBar_health_doingSomething))).setProgress((MainActivity.Hungry / 10));
            ((ProgressBar)(view.findViewById(R.id.progressBar_hungry_doingSomething))).setProgress((MainActivity.Health / 10));
            ((ProgressBar)(view.findViewById(R.id.progressBar_energy_doingSomething))).setProgress((MainActivity.Energy / 10));
            ((ProgressBar)(view.findViewById(R.id.progressBar_happiness_doingSomething))).setProgress((MainActivity.Happiness / 10));

            ((TextView)(view.findViewById(R.id.time_doingSomething))).setText(MainActivity.DateYears + "." + MainActivity.DateMonths + "." + MainActivity.DateDays + " "
                    + MainActivity.TimeHours + ":" + "00");
        }
    }
}
