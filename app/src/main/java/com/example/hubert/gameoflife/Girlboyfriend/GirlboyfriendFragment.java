package com.example.hubert.gameoflife.Girlboyfriend;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.Utils.SharedPreferencesDefaultValues;
import com.google.gson.Gson;

import java.util.Random;

import static com.example.hubert.gameoflife.Utils.Dialogs.showAlertDialog;
import static com.example.hubert.gameoflife.Utils.Dialogs.showDialogWithChoose;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link GirlboyfriendFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GirlboyfriendFragment extends Fragment implements View.OnClickListener {

    public GirlboyfriendFragment() {}

    public static GirlboyfriendFragment newInstance() {
        GirlboyfriendFragment fragment = new GirlboyfriendFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_girlboyfriend,
                container, false);

        view.findViewById(R.id.button_love_IncreaseRelationship).setOnClickListener(this);

        view.findViewById(R.id.button_love_buyGift).setOnClickListener(this);

        view.findViewById(R.id.button_love_takeSomewhere).setOnClickListener(this);

        view.findViewById(R.id.button_love_brokeUp).setOnClickListener(this);

        view.findViewById(R.id.button_love_SearchLove).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        SharedPreferences sharedPref = getActivity().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        switch (v.getId())
        {
            case R.id.button_love_IncreaseRelationship:
                if(sharedPref.getString(getResources().getString(R.string.saved_love_key), SharedPreferencesDefaultValues.DefaultLove) != null)
                {
                    if(sharedPref.getInt(getResources().getString(R.string.saved_love_relations_key), SharedPreferencesDefaultValues.DefaultLoveRelations) >= 750)
                    {
                        editor.putInt(getResources().getString(R.string.saved_love_relations_key), 0);
                        editor.putInt(getResources().getString(R.string.saved_love_relationship_level_key), SharedPreferencesDefaultValues.DefaultLoveRelationshipLevel);
                    }
                }
                break;

            case R.id.button_love_buyGift:
                if(sharedPref.getString(getResources().getString(R.string.saved_love_key), SharedPreferencesDefaultValues.DefaultLove) != null)
                    showDialogWithChoose(sharedPref, getContext(), "Buy a gift", "Are you sure you want to buy her(him) a gift for a 100$?", 3);
                break;

            case R.id.button_love_takeSomewhere:
                if(sharedPref.getString(getResources().getString(R.string.saved_love_key), SharedPreferencesDefaultValues.DefaultLove) != null)
                    showDialogWithChoose(sharedPref, getContext(), "Take somewhere", "Are you sure you want to take her(him) somewhere?", 4);
                break;

            case R.id.button_love_brokeUp:
                if(sharedPref.getString(getResources().getString(R.string.saved_love_key), SharedPreferencesDefaultValues.DefaultLove) != null)
                    showDialogWithChoose(sharedPref, getContext(), "Break up", "Are you want to break up with her(him)?", 5);
                break;

            case R.id.button_love_SearchLove:
                Gson gson = new Gson();
                Love love = new Love("Test Love", 20);
                String json = gson.toJson(love);
                editor.putString(getResources().getString(R.string.saved_love_key), json);

                showAlertDialog(getContext(), "Found love", "You successful found a girlfriend/boyfriend! Her/his name is " + love.getName()
                        + " and her/his age is " + love.getAge() + ".");
                break;
        }

        editor.apply();
    }
}
