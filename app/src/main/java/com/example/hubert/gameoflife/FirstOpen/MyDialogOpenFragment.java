package com.example.hubert.gameoflife.FirstOpen;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.hubert.gameoflife.MainActivity;
import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.SettingsActivity;
import com.example.hubert.gameoflife.Utils.NewUser;

import static android.content.Context.MODE_PRIVATE;
import static com.example.hubert.gameoflife.MainActivity.currentUserNumber;

public class MyDialogOpenFragment extends DialogFragment implements View.OnClickListener {

    public MyDialogOpenFragment() {}

    public interface OnNewUserAdd {
        void onNewUserAdd ();
    }
    OnNewUserAdd mListener;

    public static final String ARG_MODE = "modeArgKey";
    public static final int MODE_NEW = 0;
    public static final int MODE_RESET = 1;

    public int mode;

    public int avatarRes = R.drawable.avatar_icon1;

    SharedPreferences sharedPref;

    EditText nameEdit;
    ImageView avatarImage;
    Spinner sexSpinner;
    Button saveButton;

    View view;

    public static MyDialogOpenFragment newInstance(int mode) {
        MyDialogOpenFragment myDialogOpenFragment = new MyDialogOpenFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_MODE, mode);
        myDialogOpenFragment.setArguments(args);

        return myDialogOpenFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mode = getArguments().getInt(ARG_MODE, 0);

        sharedPref = getActivity().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), MODE_PRIVATE);

        SharedPreferences defSP = PreferenceManager.getDefaultSharedPreferences(getContext());
        boolean isDark = defSP.getBoolean(SettingsActivity.DARK_SWITCH_KEY, false);
        if (isDark) {
            setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_AppCompat_Dialog_Alert);
        } else {
            setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_AppCompat_Light_Dialog_Alert);
        }
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

//    @Override
//    public void onResume() {
//        super.onResume();
//
//        ImageView imageViewAvatar = view.findViewById(R.id.avatarImage);
//        imageViewAvatar.setImageResource(avatarRes);
//        Toast.makeText(getContext(), "asd", Toast.LENGTH_SHORT).show();
//    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.saveButton:
                NewUser newUser = new NewUser();

                if (mode == MODE_NEW) {
                    newUser.createUser (
                        getContext(),
                        nameEdit.getText().toString(),
                        avatarRes,
                        String.valueOf(sexSpinner.getSelectedItem()).equals("Men"));
                } else {
                    newUser.resetUser(
                            getContext(),
                            nameEdit.getText().toString(),
                            avatarRes,
                            String.valueOf(sexSpinner.getSelectedItem()).equals("Men"));
                }

                mListener.onNewUserAdd();
                dismiss();
                break;

            case R.id.avatarImage:
                MyDialogChooseAvatar newDialog = MyDialogChooseAvatar.newInstance(this);
                newDialog.setCallBack(dialogCallback).show(getActivity().getSupportFragmentManager(), "choose_avatar_tag");
                break;
        }
    }

    MyDialogChooseAvatar.DialogCallback dialogCallback = new MyDialogChooseAvatar.DialogCallback() {
        @Override
        public void getResults(int avatarPath) {
            if(avatarPath > 0){
                avatarRes = avatarPath;
                avatarImage.setImageResource(avatarPath);
            }
        }
    };


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnNewUserAdd) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnNewUserAdd");
        }
    }
}