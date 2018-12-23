package com.example.hubert.gameoflife.firstOpen;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.SettingsActivity;

public class MyDialogChooseAvatar extends DialogFragment implements RecyclerViewChooseAvatarAdapter.ItemClickListener {

    public MyDialogChooseAvatar() {}

    public interface DialogCallback {
        void getResults(int results);
    }

    private DialogCallback dialogCallback;
    public MyDialogChooseAvatar setCallBack(DialogCallback dialogCallback){
        this.dialogCallback = dialogCallback;
        return this;
    }

    private final Integer[] avatars = {
            R.drawable.boy,
            R.drawable.girl,
            R.drawable.boy_1,
            R.drawable.avatar_icon1,
            R.drawable.girl_1,
            R.drawable.man,
            R.drawable.man_1,
            R.drawable.man_2,
            R.drawable.man_4 };


    public static MyDialogChooseAvatar newInstance() {
        return new MyDialogChooseAvatar();
    }

    SharedPreferences sharedPreferences;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean isDark = sharedPreferences.getBoolean(SettingsActivity.DARK_SWITCH_KEY, false);
        if (isDark) {
            setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_MaterialComponents_Dialog_MinWidth);
        } else {
            setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_MaterialComponents_Dialog_MinWidth);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_choose_avatar, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewChooseAvatar);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(mLayoutManager);
        RecyclerViewChooseAvatarAdapter adapter = new RecyclerViewChooseAvatarAdapter(getContext(), avatars);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        return view;
    }


    @Override
    public void onItemClick(int position) {
       // ImageView imageViewAvatar = view.findViewById(R.id.imageViewAvatar);
       // MyDialogOpenFragment.avatarRes = (Integer)imageViewAvatar.getTag();

       // MyDialogOpenFragment.onDialogIconClick(avatars[position]);

        dialogCallback.getResults(avatars[position]);
        dismiss();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        dialogCallback = null;
    }
}