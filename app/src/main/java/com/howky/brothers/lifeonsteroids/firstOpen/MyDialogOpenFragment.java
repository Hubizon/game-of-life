package com.howky.brothers.lifeonsteroids.firstOpen;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.howky.brothers.lifeonsteroids.R;
import com.howky.brothers.lifeonsteroids.SettingsActivity;
import com.howky.brothers.lifeonsteroids.utils.NewUser;

import static android.content.Context.MODE_PRIVATE;

public class MyDialogOpenFragment extends DialogFragment implements View.OnClickListener {

    public MyDialogOpenFragment() {}

    public interface OnNewUserAdd {
        void onNewUserAdd ();
    }
    private OnNewUserAdd mListener;
    private Context mContext;

    private static final String ARG_MODE = "modeArgKey";
    public static final int MODE_NEW = 0;
    public static final int MODE_RESET = 1;

    private int mode;

    private int avatarRes = R.drawable.man;

    private SharedPreferences sharedPref;

    private EditText nameEdit;
    private ImageView avatarImage;
    private Spinner sexSpinner;
    private Button saveButton;


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

        if (getArguments() != null)
        mode = getArguments().getInt(ARG_MODE, 0);

        sharedPref = mContext.getSharedPreferences(getResources().getString(R.string.shared_preferences_key), MODE_PRIVATE);

        SharedPreferences defSP = PreferenceManager.getDefaultSharedPreferences(getContext());
        boolean isDark = defSP.getBoolean(SettingsActivity.DARK_SWITCH_KEY, false);
        if (isDark) {
            setStyle(DialogFragment.STYLE_NO_TITLE, R.style.ThemeOverlay_MaterialComponents_Dialog);
        } else {
            setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_MaterialComponents_Light_Dialog);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_open, container, false);
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
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.saveButton:
                sharedPref.edit().putBoolean(getResources().getString(R.string.saved_is_first_time_key), false).apply();
                NewUser newUser = new NewUser();

                if (mode == MODE_NEW) {
                    newUser.createUser (
                        mContext,
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
                MyDialogChooseAvatar newDialog = MyDialogChooseAvatar.newInstance();
                newDialog.setCallBack(dialogCallback).show(getActivity().getSupportFragmentManager(), "choose_avatar_tag");
                break;
        }
    }

    private final MyDialogChooseAvatar.DialogCallback dialogCallback = new MyDialogChooseAvatar.DialogCallback() {
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
            mContext = context;
            mListener = (OnNewUserAdd) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnNewUserAdd; MainActivity MUST be the context!");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mListener = null;
        mContext = null;
    }

}