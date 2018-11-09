package com.example.hubert.gameoflife.FirstOpen;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hubert.gameoflife.R;

import java.util.ArrayList;
import java.util.List;

public class MyDialogChooseAvatar extends DialogFragment implements RecyclerViewChooseAvatarAdapter.ItemClickListener{

    public MyDialogChooseAvatar() {}

    SharedPreferences sharedPref;

    View view;

    public static MyDialogChooseAvatar newInstance() {
        MyDialogChooseAvatar frag = new MyDialogChooseAvatar();
        return frag;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Material_Light_Dialog_NoActionBar_MinWidth);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.dialog_choose_avatar, container, false);

        sharedPref = getActivity().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);

        List<Integer> avatars = new ArrayList<Integer>();
        avatars.add(R.drawable.boy);
        avatars.add(R.drawable.girl);
        avatars.add(R.drawable.boy_1);
        avatars.add(R.drawable.avatar_icon1);
        avatars.add(R.drawable.girl_1);
        avatars.add(R.drawable.man);
        avatars.add(R.drawable.man_1);
        avatars.add(R.drawable.man_2);
        avatars.add(R.drawable.man_3);
        avatars.add(R.drawable.man_4);

//        int[] avatars = new int[] {
//                R.drawable.boy, R.drawable.girl, R.drawable.boy_1, R.drawable.avatar_icon1, R.drawable.girl_1, R.drawable.man,
//                R.drawable.man_1, R.drawable.man_2, R.drawable.man_3, R.drawable.man_4
//        };

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewChooseAvatar);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(mLayoutManager);
        RecyclerViewChooseAvatarAdapter adapter = new RecyclerViewChooseAvatarAdapter(getContext(), avatars);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        return view;
    }


    @Override
    public void onItemClick(View view, int position) {
        ImageView imageViewAvatar = view.findViewById(R.id.imageViewAvatar);
        MyDialogOpenFragment.avatarRes = (Integer)imageViewAvatar.getTag();
        onStop();
    }
}