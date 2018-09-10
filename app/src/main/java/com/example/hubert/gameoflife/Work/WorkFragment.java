package com.example.hubert.gameoflife.Work;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.Utils.MyDialogFragment;


public class WorkFragment extends Fragment {

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
        goWorkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newDialog = new MyDialogFragment();
                newDialog.show(getActivity().getSupportFragmentManager(), "MY_DIALOG");
            }
        });

        return view;
    }

    public void onWorkButtonClick(View view) {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();

        switch(view.getId())
        {
            case R.id.giveUpWorkWork:
                ft.replace(R.id.pager, new FindJobFragment());

            default:
                break;

        }
        ft.commit();
    }
}
