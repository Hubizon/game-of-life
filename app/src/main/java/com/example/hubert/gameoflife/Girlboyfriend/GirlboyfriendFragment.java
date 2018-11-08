package com.example.hubert.gameoflife.Girlboyfriend;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.SettingsActivity;
import com.example.hubert.gameoflife.Utils.SharedPreferencesDefaultValues;
import com.google.gson.Gson;

import java.util.Random;

import static com.example.hubert.gameoflife.Utils.Dialogs.showAlertDialog;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link GirlboyfriendFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GirlboyfriendFragment extends Fragment implements View.OnClickListener {

    private String[] boyfriendsNames = new String[] {
            "Liam", "Noah", "William", "James", "Logan", "Benjamin", "Mason", "Elijah", "Oliver", "Jacob", "Lucas", "Michael", "Alexander", "Ethan", "Daniel", "Matthew",
            "Aiden", "Henry", "Joseph", "Jackson", "Samuel", "Sebastian", "David", "Carter", "Wyatt", "Jayden", "John", "Owen", "Dylan", "Luke", "Gabriel", "Anthony",
            "Isaac", "Grayson", "Jack", "Julian", "Levi", "Christopher", "Joshua", "Andrew", "Lincoln", "Mateo", "Ryan", "Jaxon", "Nathan", "Aaron", "Isaiah", "Thomas",
            "Charles", "Caleb", "Josiah", "Christian", "Hunter", "Eli", "Jonathan", "Connor", "Landon", "Adrian", "Asher", "Cameron", "Leo", "Theodore", "Jeremiah",
            "Hudson", "Robert", "Easton", "Nolan", "Nicholas", "Ezra", "Colton", "Angel", "Brayden", "Jordan", "Dominic", "Austin", "Ian", "Adam", "Elias", "Jaxson",
            "Greyson", "Jose", "Ezekiel", "Carson", "Evan", "Maverick", "Bryson", "Jace", "Cooper", "Xavier", "Parker", "Roman", "Jason", "Santiago", "Chase", "Sawyer",
            "Gavin", "Leonardo", "Kayden", "Ayden", "Jameson",
    };

    private String[] girlfriendsNames = new String[] {
            "Liam", "Noah", "William", "James", "Logan", "Benjamin", "Mason", "Elijah", "Oliver", "Jacob", "Lucas", "Michael", "Alexander", "Ethan", "Daniel", "Matthew",
            "Aiden", "Henry", "Joseph", "Jackson", "Samuel", "Sebastian", "David", "Carter", "Wyatt", "Jayden", "John", "Owen", "Dylan", "Luke", "Gabriel", "Anthony",
            "Isaac", "Grayson", "Jack", "Julian", "Levi", "Christopher", "Joshua", "Andrew", "Lincoln", "Mateo", "Ryan", "Jaxon", "Nathan", "Aaron", "Isaiah", "Thomas",
            "Charles", "Caleb", "Josiah", "Christian", "Hunter", "Eli", "Jonathan", "Connor", "Landon", "Adrian", "Asher", "Cameron", "Leo", "Theodore", "Jeremiah",
            "Hudson", "Robert", "Easton", "Nolan", "Nicholas", "Ezra", "Colton", "Angel", "Brayden", "Jordan", "Dominic", "Austin", "Ian", "Adam", "Elias", "Jaxson",
            "Greyson", "Jose", "Ezekiel", "Carson", "Evan", "Maverick", "Bryson", "Jace", "Cooper", "Xavier", "Parker", "Roman", "Jason", "Santiago", "Chase", "Sawyer",
            "Gavin", "Leonardo", "Kayden", "Ayden", "Jameson",
    };

    public GirlboyfriendFragment() {}

    public static GirlboyfriendFragment newInstance() {
        GirlboyfriendFragment fragment = new GirlboyfriendFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    View view;
    ProgressBar progressBar;
    TextView loveName;
    TextView loveStatus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_girlboyfriend,
                container, false);

        SharedPreferences sharedPref = getActivity().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);

        progressBar = view.findViewById(R.id.progressBar_girlboyFriend_relations);
        loveName = view.findViewById(R.id.love_name);
        loveStatus = view.findViewById(R.id.girlboyfriend_status);

        if(sharedPref.getString(getResources().getString(R.string.saved_love_key), SharedPreferencesDefaultValues.DefaultLove) != null)
        {
            view.findViewById(R.id.girlboyfriend_status).setVisibility(View.VISIBLE);
            view.findViewById(R.id.button_love_IncreaseRelationship).setVisibility(View.VISIBLE);
            view.findViewById(R.id.progressBar_girlboyFriend_relations).setVisibility(View.VISIBLE);
            view.findViewById(R.id.relations).setVisibility(View.VISIBLE);
            view.findViewById(R.id.button_love_buyGift).setVisibility(View.VISIBLE);
            view.findViewById(R.id.button_love_takeSomewhere).setVisibility(View.VISIBLE);
            view.findViewById(R.id.button_love_brokeUp).setVisibility(View.VISIBLE);

            if("girl".equals(sharedPref.getString(getResources().getString(R.string.saved_sexuality_key), SharedPreferencesDefaultValues.DefaultSexuality)))
                loveName.setText("Girlfriend: " + sharedPref.getString(getResources().getString(R.string.saved_love_name_key), ""));
            else
                loveName.setText("Boyfriend: " + sharedPref.getString(getResources().getString(R.string.saved_love_name_key), ""));

            if(sharedPref.getInt(getResources().getString(R.string.saved_love_relationship_level_key), SharedPreferencesDefaultValues.DefaultLoveRelationshipLevel) <= 1)
                loveStatus.setText("Status: lovers");
            else if(sharedPref.getInt(getResources().getString(R.string.saved_love_relationship_level_key), SharedPreferencesDefaultValues.DefaultLoveRelationshipLevel) == 2)
                loveStatus.setText("Status: betrothed");
            else if(sharedPref.getInt(getResources().getString(R.string.saved_love_relationship_level_key), SharedPreferencesDefaultValues.DefaultLoveRelationshipLevel) == 2)
                loveStatus.setText("Status: marry");

            progressBar.setProgress(sharedPref.getInt(getResources().getString(R.string.saved_love_relations_key), SharedPreferencesDefaultValues.DefaultLoveRelations) / 10);
        }
        else
        {
            loveName.setText("Single");
            view.findViewById(R.id.girlboyfriend_status).setVisibility(View.GONE);
            view.findViewById(R.id.button_love_IncreaseRelationship).setVisibility(View.GONE);
            view.findViewById(R.id.progressBar_girlboyFriend_relations).setVisibility(View.GONE);
            view.findViewById(R.id.relations).setVisibility(View.GONE);
            view.findViewById(R.id.button_love_buyGift).setVisibility(View.GONE);
            view.findViewById(R.id.button_love_takeSomewhere).setVisibility(View.GONE);
            view.findViewById(R.id.button_love_brokeUp).setVisibility(View.GONE);
        }

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
                if(sharedPref.getInt(getResources().getString(R.string.saved_love_relationship_level_key), SharedPreferencesDefaultValues.DefaultLoveRelationshipLevel) < 3)
                {
                    if(sharedPref.getString(getResources().getString(R.string.saved_love_key), SharedPreferencesDefaultValues.DefaultLove) != null)
                    {
                        if(sharedPref.getInt(getResources().getString(R.string.saved_love_relations_key), SharedPreferencesDefaultValues.DefaultLoveRelations) >= 750)
                        {
                            Random random = new Random();
                            if(random.nextInt(300) > (1000 - sharedPref.getInt(getResources().getString(R.string.saved_love_relations_key), SharedPreferencesDefaultValues.DefaultLoveRelations)))
                            {
                                editor.putInt(getResources().getString(R.string.saved_love_relations_key), 0);
                                editor.putInt(getResources().getString(R.string.saved_love_relationship_level_key), SharedPreferencesDefaultValues.DefaultLoveRelationshipLevel);

                                if(sharedPref.getInt(getResources().getString(R.string.saved_love_relationship_level_key), SharedPreferencesDefaultValues.DefaultLoveRelationshipLevel) <= 1)
                                {
                                    showAlertDialog(getContext(), sharedPref,"Engagement", "You successful were engagement!");
                                    editor.putInt(getResources().getString(R.string.saved_love_relationship_level_key), 2);
                                }
                                else if(sharedPref.getInt(getResources().getString(R.string.saved_love_relationship_level_key), SharedPreferencesDefaultValues.DefaultLoveRelationshipLevel) == 2)
                                {
                                    showAlertDialog(getContext(), sharedPref, "Getting married", "You successful got married!");
                                    editor.putInt(getResources().getString(R.string.saved_love_relationship_level_key), 3);
                                }

                                editor.apply();
                                if(sharedPref.getInt(getResources().getString(R.string.saved_love_relationship_level_key), SharedPreferencesDefaultValues.DefaultLoveRelationshipLevel) <= 1)
                                    loveStatus.setText("Status: lovers");
                                else if(sharedPref.getInt(getResources().getString(R.string.saved_love_relationship_level_key), SharedPreferencesDefaultValues.DefaultLoveRelationshipLevel) == 2)
                                    loveStatus.setText("Status: betrothed");
                                else if(sharedPref.getInt(getResources().getString(R.string.saved_love_relationship_level_key), SharedPreferencesDefaultValues.DefaultLoveRelationshipLevel) >= 3)
                                    loveStatus.setText("Status: marry");
                            }
                            else
                            {
                                if(sharedPref.getInt(getResources().getString(R.string.saved_love_relationship_level_key), SharedPreferencesDefaultValues.DefaultLoveRelationshipLevel) <= 1)
                                    showAlertDialog(getContext(), sharedPref, "Engagement", "Unfortunately your love didn't accept your engagement request");
                                else if(sharedPref.getInt(getResources().getString(R.string.saved_love_relationship_level_key), SharedPreferencesDefaultValues.DefaultLoveRelationshipLevel) == 2)
                                    showAlertDialog(getContext(), sharedPref, "Getting married", "Unfortunately your love didn't accept your married request");
                                editor.putInt(getResources().getString(R.string.saved_love_relations_key), (sharedPref.getInt(getResources().getString(R.string.saved_love_relations_key), SharedPreferencesDefaultValues.DefaultLoveRelations) - 75));
                            }
                        }
                        else
                        {
                            if(sharedPref.getInt(getResources().getString(R.string.saved_love_relationship_level_key), SharedPreferencesDefaultValues.DefaultLoveRelationshipLevel) <= 1)
                                showAlertDialog(getContext(), sharedPref, "Engagement", "Unfortunately your love didn't accept your engagement request");
                            else if(sharedPref.getInt(getResources().getString(R.string.saved_love_relationship_level_key), SharedPreferencesDefaultValues.DefaultLoveRelationshipLevel) == 2)
                                showAlertDialog(getContext(), sharedPref, "Getting married", "Unfortunately your love didn't accept your married request");
                            editor.putInt(getResources().getString(R.string.saved_love_relations_key), (sharedPref.getInt(getResources().getString(R.string.saved_love_relations_key), SharedPreferencesDefaultValues.DefaultLoveRelations) - 75));
                        }
                    }
                }
                else
                    showAlertDialog(getContext(), sharedPref, "Increasing relationship level", "Unfortunately you have max relationship level");
                break;

            case R.id.button_love_buyGift:
                if(sharedPref.getString(getResources().getString(R.string.saved_love_key), SharedPreferencesDefaultValues.DefaultLove) != null)
                    if("girl".equals(sharedPref.getString(getResources().getString(R.string.saved_sexuality_key), SharedPreferencesDefaultValues.DefaultSexuality)))
                        showDialogWithChoose("Buy a gift", "Are you sure you want to buy her a gift for a 100$?", 1);
                    else
                        showDialogWithChoose("Buy a gift", "Are you sure you want to buy him a gift for a 100$?", 1);
                break;

            case R.id.button_love_takeSomewhere:
                if(sharedPref.getString(getResources().getString(R.string.saved_love_key), SharedPreferencesDefaultValues.DefaultLove) != null)
                    if("girl".equals(sharedPref.getString(getResources().getString(R.string.saved_sexuality_key), SharedPreferencesDefaultValues.DefaultSexuality)))
                        showDialogWithChoose("Take somewhere", "Are you sure you want to take her somewhere for a 75$", 2);
                    else
                        showDialogWithChoose("Take somewhere", "Are you sure you want to take him somewhere for a 75$", 2);
                break;

            case R.id.button_love_brokeUp:
                if(sharedPref.getString(getResources().getString(R.string.saved_love_key), SharedPreferencesDefaultValues.DefaultLove) != null)
                {
                    if("girl".equals(sharedPref.getString(getResources().getString(R.string.saved_sexuality_key), SharedPreferencesDefaultValues.DefaultSexuality)))
                        showDialogWithChoose("Break up", "Are you want to break up with her?", 3);
                    else
                        showDialogWithChoose("Break up", "Are you want to break up with him?", 3);
                    Love.BreakUp(getContext());
                }
                break;

            case R.id.button_love_SearchLove:
                if(sharedPref.getString(getResources().getString(R.string.saved_love_key), SharedPreferencesDefaultValues.DefaultLove) == null)
                {
                    Random random = new Random();
                    Gson gson = new Gson();
                    Love love;
                    if("girl".equals(sharedPref.getString(getResources().getString(R.string.saved_sexuality_key), SharedPreferencesDefaultValues.DefaultSexuality)))
                        love = new Love(girlfriendsNames[random.nextInt(girlfriendsNames.length)]);
                    else
                        love = new Love(boyfriendsNames[random.nextInt(boyfriendsNames.length)]);
                    String json = gson.toJson(love);

                    editor.putString(getResources().getString(R.string.saved_love_key), json);
                    editor.putString(getResources().getString(R.string.saved_love_name_key), love.getName());

                    if("girl".equals(sharedPref.getString(getResources().getString(R.string.saved_sexuality_key), SharedPreferencesDefaultValues.DefaultSexuality)))
                        showAlertDialog(getContext(), sharedPref, "Found love", "You successful found love! Her name is " + love.getName());
                    else
                        showAlertDialog(getContext(), sharedPref, "Found love", "You successful found love! His name is " + love.getName());
                    editor.apply();

                    view.findViewById(R.id.girlboyfriend_status).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.button_love_IncreaseRelationship).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.progressBar_girlboyFriend_relations).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.relations).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.button_love_buyGift).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.button_love_takeSomewhere).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.button_love_brokeUp).setVisibility(View.VISIBLE);

                    if("girl".equals(sharedPref.getString(getResources().getString(R.string.saved_sexuality_key), SharedPreferencesDefaultValues.DefaultSexuality)))
                        loveName.setText("Girlfriend: " + sharedPref.getString(getResources().getString(R.string.saved_love_name_key), ""));
                    else
                        loveName.setText("Boyfriend: " + sharedPref.getString(getResources().getString(R.string.saved_love_name_key), ""));

                    if(sharedPref.getInt(getResources().getString(R.string.saved_love_relationship_level_key), SharedPreferencesDefaultValues.DefaultLoveRelationshipLevel) <= 1)
                        loveStatus.setText("Status: lovers");
                    else if(sharedPref.getInt(getResources().getString(R.string.saved_love_relationship_level_key), SharedPreferencesDefaultValues.DefaultLoveRelationshipLevel) == 2)
                        loveStatus.setText("Status: betrothed");
                    else if(sharedPref.getInt(getResources().getString(R.string.saved_love_relationship_level_key), SharedPreferencesDefaultValues.DefaultLoveRelationshipLevel) == 2)
                        loveStatus.setText("Status: marry");

                    progressBar.setProgress(sharedPref.getInt(getResources().getString(R.string.saved_love_relations_key), SharedPreferencesDefaultValues.DefaultLoveRelations));
                }
                break;
        }

        editor.apply();
        progressBar.setProgress(sharedPref.getInt(getResources().getString(R.string.saved_love_relations_key), SharedPreferencesDefaultValues.DefaultLoveRelations));
    }

    private void showDialogWithChoose(final String title, final String message, final int whichOneEvent)
    {
        final SharedPreferences sharedPref = getActivity().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();

        AlertDialog.Builder dialog;

        boolean isDark = sharedPref.getBoolean(SettingsActivity.DARK_SWITCH_KEY, true);
        if (isDark) {
            dialog = new AlertDialog.Builder(getContext(), R.style.Theme_AppCompat_Dialog_Alert);
        } else {
            dialog = new AlertDialog.Builder(getContext(), R.style.Theme_AppCompat_Light_Dialog_Alert);
        }
        dialog.setTitle(title)
                //.setIcon(R.drawable.ic_launcher)
                .setMessage(message)
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        switch (whichOneEvent)
                        {
                            case 1:
                                //Die();
                                break;
                        }
                        dialoginterface.cancel();
                        //TODO: start timer
                    }})
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        switch (whichOneEvent)
                        {
                            case 1:
                                if(sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) >= 100)
                                {
                                    editor.putInt(getResources().getString(R.string.saved_character_money_key), (sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) - 100));
                                    if(sharedPref.getInt(getResources().getString(R.string.saved_love_relations_key), SharedPreferencesDefaultValues.DefaultLoveRelations) < 1000)
                                        editor.putInt(getResources().getString(R.string.saved_love_relations_key), (sharedPref.getInt(getResources().getString(R.string.saved_love_relations_key), SharedPreferencesDefaultValues.DefaultLoveRelations) + 50));
                                }
                                else
                                    Toast.makeText(getContext(), "Unfortunately, you don't have enough money to do this.", Toast.LENGTH_SHORT).show();

                                editor.apply();
                                progressBar.setProgress(sharedPref.getInt(getResources().getString(R.string.saved_love_relations_key), SharedPreferencesDefaultValues.DefaultLoveRelations));
                                dialoginterface.cancel();
                                break;

                            case 2:
                                if(sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) >= 75)
                                {
                                    editor.putInt(getResources().getString(R.string.saved_character_money_key), (sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) - 75));
                                    if(sharedPref.getInt(getResources().getString(R.string.saved_love_relations_key), SharedPreferencesDefaultValues.DefaultLoveRelations) < 1000)
                                        editor.putInt(getResources().getString(R.string.saved_love_relations_key), (sharedPref.getInt(getResources().getString(R.string.saved_love_relations_key), SharedPreferencesDefaultValues.DefaultLoveRelations) + 40));
                                }
                                else
                                    Toast.makeText(getContext(), "Unfortunately, you don't have enough money to do this.", Toast.LENGTH_SHORT).show();

                                editor.apply();
                                progressBar.setProgress(sharedPref.getInt(getResources().getString(R.string.saved_love_relations_key), SharedPreferencesDefaultValues.DefaultLoveRelations));
                                dialoginterface.cancel();
                                break;

                            case 3:
                                editor.putString(getResources().getString(R.string.saved_love_key), null);
                                editor.putInt(getResources().getString(R.string.saved_love_relations_key), SharedPreferencesDefaultValues.DefaultLoveRelations);
                                editor.putInt(getResources().getString(R.string.saved_love_relationship_level_key), SharedPreferencesDefaultValues.DefaultLoveRelationshipLevel);
                                editor.apply();

                                loveName.setText("Single");
                                view.findViewById(R.id.girlboyfriend_status).setVisibility(View.GONE);
                                view.findViewById(R.id.button_love_IncreaseRelationship).setVisibility(View.GONE);
                                view.findViewById(R.id.progressBar_girlboyFriend_relations).setVisibility(View.GONE);
                                view.findViewById(R.id.relations).setVisibility(View.GONE);
                                view.findViewById(R.id.button_love_buyGift).setVisibility(View.GONE);
                                view.findViewById(R.id.button_love_takeSomewhere).setVisibility(View.GONE);
                                view.findViewById(R.id.button_love_brokeUp).setVisibility(View.GONE);
                                break;

                            default:
                                dialoginterface.cancel();
                                break;
                        }
                        //TODO: Michal!!! start timer
                    }
                }).show();
    }
}
