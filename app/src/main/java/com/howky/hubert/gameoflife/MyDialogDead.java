package com.howky.hubert.gameoflife;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MyDialogDead extends DialogFragment {

    private OnDialogDeadInteractionListener mListener;

    public MyDialogDead() {}

    public static MyDialogDead newInstance() {
        return new MyDialogDead();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_dialog_dead, container, false);
        setCancelable(false);
        Button okbtn = view.findViewById(R.id.btn_newGame);
        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onDialogDeadInteraction();
                    dismiss();
                }
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDialogDeadInteractionListener) {
            mListener = (OnDialogDeadInteractionListener) context;
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

    public interface OnDialogDeadInteractionListener {
        void onDialogDeadInteraction();
    }
}
