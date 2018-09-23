package com.example.hubert.gameoflife.Work;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.Utils.MyDialogFragment;


public class WorkFragment extends Fragment implements View.OnClickListener {

    private static final String WORK_DIALOG_TAG = "work_dialog_tag";

    public WorkFragment() {}

    public static WorkFragment newInstance() {
        WorkFragment fragment = new WorkFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_work, container, false);

        Button goWorkButton = view.findViewById(R.id.btn_go_work);
        goWorkButton.setOnClickListener(this);

        Button workHardButton = view.findViewById(R.id.btn_work_hard);
        workHardButton.setOnClickListener(this);

        Button hangAroundButton = view.findViewById(R.id.btn_hang_around);
        hangAroundButton.setOnClickListener(this);

        Button giveUpWorkButton = view.findViewById(R.id.giveUpWorkWork);
        giveUpWorkButton.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {
        DialogFragment newDialog = null;

        int view_id = view.getId();
        switch (view_id)
        {
            case R.id.btn_go_work:
                newDialog = MyDialogFragment.newInstance(view_id);
                break;

            case R.id.btn_work_hard:
                newDialog = MyDialogFragment.newInstance(view_id);
                break;

            case R.id.btn_hang_around:
                newDialog = MyDialogFragment.newInstance(view_id);
                break;

            case R.id.giveUpWorkWork:
                SharedPreferences sharedPref = getActivity().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(getResources().getString(R.string.saved_my_job_key), null);

                Intent intent = new Intent(getActivity().getApplicationContext(), FindJobActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }
        if (newDialog != null)
            newDialog.show(getActivity().getSupportFragmentManager(), WORK_DIALOG_TAG);
    }
}
