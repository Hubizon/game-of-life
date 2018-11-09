package com.example.hubert.gameoflife.FirstOpen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.SettingsActivity;

public class MyDialogOpenFragment extends DialogFragment implements View.OnClickListener {

    public MyDialogOpenFragment() {}

    private static final String TAG = MyDialogOpenFragment.class.getSimpleName();
    public static int avatarRes = R.drawable.avatar_icon1;

    SharedPreferences sharedPref;

    EditText nameEdit;
    ImageView avatarImage;
    Spinner sexSpinner;
    Button saveButton;

    View view;

    public static MyDialogOpenFragment newInstance() {
        MyDialogOpenFragment frag = new MyDialogOpenFragment();
        return frag;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPref = getActivity().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        boolean isDark = sharedPref.getBoolean(SettingsActivity.DARK_SWITCH_KEY, false);
        if (isDark) {
            setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_AppCompat_Dialog_Alert);
        } else {
            setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_AppCompat_Light_Dialog_Alert);
        }

        //setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Material_Light_Dialog_NoActionBar_MinWidth);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.dialog_open, container, false);
        setCancelable(false);

        saveButton = view.findViewById(R.id.saveButton);
        saveButton.setAlpha(.5f);
        saveButton.setEnabled(false);
        saveButton.setOnClickListener(this);

        avatarImage = view.findViewById(R.id.avatarImage);
        avatarImage.setOnClickListener(this);

        sexSpinner = view.findViewById(R.id.sexSpinner);

        nameEdit = view.findViewById(R.id.nameEdit);
        nameEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().trim().length() > 2 && s.toString().trim().length() < 10){
                    saveButton.setAlpha(1);
                    saveButton.setEnabled(true);
                } else {
                    saveButton.setAlpha(.5f);
                    saveButton.setEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        ImageView imageViewAvatar = view.findViewById(R.id.avatarImage);
        imageViewAvatar.setImageResource(avatarRes);
        Toast.makeText(getContext(), "asd", Toast.LENGTH_SHORT).show();
    }

    public void changeIcon() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.saveButton:
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean(getResources().getString(R.string.saved_is_first_time_key), false);
                editor.putString(getResources().getString(R.string.saved_character_name_key), nameEdit.getText().toString());
                editor.putInt(getResources().getString(R.string.saved_character_icon_key), /*TODO avatarImage.getResources()*/1);
                editor.putString(getResources().getString(R.string.saved_sex_key), String.valueOf(sexSpinner.getSelectedItem()));
                editor.apply();
                onStop();
                break;

            case R.id.avatarImage:
                MyDialogChooseAvatar newDialog = MyDialogChooseAvatar.newInstance(this);

                newDialog.setCallBack(dialogCallback).show(getActivity().getSupportFragmentManager(), "choose_avatar_tag");
                //onPause();
                break;
        }
    }

    MyDialogChooseAvatar.DialogCallback dialogCallback = new MyDialogChooseAvatar.DialogCallback() {
        @Override
        public void getResults(int avatarPath) {
            if(avatarPath > 0){
                avatarImage.setImageResource(avatarPath);
            }
        }
    };

}