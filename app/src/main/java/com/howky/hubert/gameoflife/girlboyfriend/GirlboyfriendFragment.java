package com.howky.hubert.gameoflife.girlboyfriend;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.howky.hubert.gameoflife.MainActivity;
import com.howky.hubert.gameoflife.MyApplication;
import com.howky.hubert.gameoflife.R;
import com.howky.hubert.gameoflife.SettingsActivity;
import com.howky.hubert.gameoflife.utils.Arrays;
import com.howky.hubert.gameoflife.utils.Dialogs;
import com.howky.hubert.gameoflife.utils.MyDialogFragment;
import com.howky.hubert.gameoflife.utils.MainTimer;
import com.howky.hubert.gameoflife.utils.SharedPreferencesDefaultValues;
import com.google.gson.Gson;

import java.util.Objects;
import java.util.Random;

import static com.howky.hubert.gameoflife.utils.Arrays.boyfriendsNames;
import static com.howky.hubert.gameoflife.utils.Arrays.girlfriendsNames;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link GirlboyfriendFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GirlboyfriendFragment extends Fragment implements View.OnClickListener {


    public GirlboyfriendFragment() {}

    public static GirlboyfriendFragment newInstance() {
        return new GirlboyfriendFragment();
    }

    private View view;
    private ProgressBar progressBar;
    private TextView loveName;
    private TextView loveStatus;
    private Context mContext;


    void onGirlTimerStop(Context context) {
        final MyApplication globalApplication = (MyApplication) context.getApplicationContext();
        MainTimer.shouldWork = false;
        globalApplication.mainTimer.stopTimer();
    }

    void onGirlTimerStart(Context context) {
        final MyApplication globalApplication = (MyApplication) context.getApplicationContext();
        MainTimer.shouldWork = true;
        globalApplication.mainTimer.startTimer();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_girlboyfriend,
                container, false);

        mContext = view.getContext();

        SharedPreferences sharedPref = MyApplication.userSharedPref;

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
            view.findViewById(R.id.button_love_SearchLove).setVisibility(View.GONE);

            if(sharedPref.getBoolean(getResources().getString(R.string.saved_sex_key), SharedPreferencesDefaultValues.DefaultIsMale))
                loveName.setText(getResources().getString(R.string.girlfriend) + " " + sharedPref.getString(getResources().getString(R.string.saved_love_name_key), ""));
            else
                loveName.setText(getResources().getString(R.string.boyfriend) + " " + sharedPref.getString(getResources().getString(R.string.saved_love_name_key), ""));

            if(sharedPref.getInt(getResources().getString(R.string.saved_love_relationship_level_key), SharedPreferencesDefaultValues.DefaultLoveRelationshipLevel) <= 1)
                loveStatus.setText(getResources().getString(R.string.status) + " " + getResources().getString(R.string.lovers));
            else if(sharedPref.getInt(getResources().getString(R.string.saved_love_relationship_level_key), SharedPreferencesDefaultValues.DefaultLoveRelationshipLevel) == 2)
                loveStatus.setText(getResources().getString(R.string.status) + " " + getResources().getString(R.string.betrothed));
            else if(sharedPref.getInt(getResources().getString(R.string.saved_love_relationship_level_key), SharedPreferencesDefaultValues.DefaultLoveRelationshipLevel) == 2)
                loveStatus.setText(getResources().getString(R.string.status) + " " + getResources().getString(R.string.marry));

            progressBar.setProgress(sharedPref.getInt(getResources().getString(R.string.saved_love_relations_key), SharedPreferencesDefaultValues.DefaultLoveRelations) / 10);
        }
        else
        {
            loveName.setText(getResources().getString(R.string.single));
            view.findViewById(R.id.girlboyfriend_status).setVisibility(View.GONE);
            view.findViewById(R.id.button_love_IncreaseRelationship).setVisibility(View.GONE);
            view.findViewById(R.id.progressBar_girlboyFriend_relations).setVisibility(View.GONE);
            view.findViewById(R.id.relations).setVisibility(View.GONE);
            view.findViewById(R.id.button_love_buyGift).setVisibility(View.GONE);
            view.findViewById(R.id.button_love_takeSomewhere).setVisibility(View.GONE);
            view.findViewById(R.id.button_love_brokeUp).setVisibility(View.GONE);
            view.findViewById(R.id.button_love_SearchLove).setVisibility(View.VISIBLE);
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
        SharedPreferences sharedPref = MyApplication.userSharedPref;
        //SharedPreferences sharedPref = getActivity().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        Dialogs dialogs = new Dialogs(mContext);
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
                                    dialogs.showAlertDialog(getContext(), getResources().getString(R.string.engagement), getResources().getString(R.string.successful_engagement));
                                    editor.putInt(getResources().getString(R.string.saved_love_relationship_level_key), 2);
                                }
                                else if(sharedPref.getInt(getResources().getString(R.string.saved_love_relationship_level_key), SharedPreferencesDefaultValues.DefaultLoveRelationshipLevel) == 2)
                                {
                                    dialogs.showAlertDialog(getContext(), getResources().getString(R.string.getting_married), getResources().getString(R.string.successful_marrying));
                                    editor.putInt(getResources().getString(R.string.saved_love_relationship_level_key), 3);
                                }

                                editor.apply();
                                if(sharedPref.getInt(getResources().getString(R.string.saved_love_relationship_level_key), SharedPreferencesDefaultValues.DefaultLoveRelationshipLevel) <= 1)
                                    loveStatus.setText(getResources().getString(R.string.status) + " " + getResources().getString(R.string.lovers));
                                else if(sharedPref.getInt(getResources().getString(R.string.saved_love_relationship_level_key), SharedPreferencesDefaultValues.DefaultLoveRelationshipLevel) == 2)
                                    loveStatus.setText(getResources().getString(R.string.status) + " " + getResources().getString(R.string.betrothed));
                                else if(sharedPref.getInt(getResources().getString(R.string.saved_love_relationship_level_key), SharedPreferencesDefaultValues.DefaultLoveRelationshipLevel) == 2)
                                    loveStatus.setText(getResources().getString(R.string.status) + " " + getResources().getString(R.string.marry));
                            }
                            else
                            {
                                if(sharedPref.getInt(getResources().getString(R.string.saved_love_relationship_level_key), SharedPreferencesDefaultValues.DefaultLoveRelationshipLevel) <= 1)
                                    dialogs.showAlertDialog(getContext(), getResources().getString(R.string.engagement), getResources().getString(R.string.unsuccessful_engagement));
                                else if(sharedPref.getInt(getResources().getString(R.string.saved_love_relationship_level_key), SharedPreferencesDefaultValues.DefaultLoveRelationshipLevel) == 2)
                                    dialogs.showAlertDialog(getContext(), getResources().getString(R.string.getting_married), getResources().getString(R.string.unsuccessful_marrying));
                                editor.putInt(getResources().getString(R.string.saved_love_relations_key), (sharedPref.getInt(getResources().getString(R.string.saved_love_relations_key), SharedPreferencesDefaultValues.DefaultLoveRelations) - 75));
                            }
                        }
                        else
                        {
                            if(sharedPref.getInt(getResources().getString(R.string.saved_love_relationship_level_key), SharedPreferencesDefaultValues.DefaultLoveRelationshipLevel) <= 1)
                                dialogs.showAlertDialog(getContext(), getResources().getString(R.string.engagement), getResources().getString(R.string.unsuccessful_engagement));
                            else if(sharedPref.getInt(getResources().getString(R.string.saved_love_relationship_level_key), SharedPreferencesDefaultValues.DefaultLoveRelationshipLevel) == 2)
                                dialogs.showAlertDialog(getContext(), getResources().getString(R.string.getting_married), getResources().getString(R.string.unsuccessful_marrying));
                            editor.putInt(getResources().getString(R.string.saved_love_relations_key), (sharedPref.getInt(getResources().getString(R.string.saved_love_relations_key), SharedPreferencesDefaultValues.DefaultLoveRelations) - 75));
                        }
                    }
                }
                else
                    dialogs.showAlertDialog(getContext(), getResources().getString(R.string.increasing_relationship_level), getResources().getString(R.string.max_relationship_level));
                break;

            case R.id.button_love_buyGift:
                if(sharedPref.getString(getResources().getString(R.string.saved_love_key), SharedPreferencesDefaultValues.DefaultLove) != null)
                    if(sharedPref.getBoolean(getResources().getString(R.string.saved_sex_key), SharedPreferencesDefaultValues.DefaultIsMale))
                        showDialogWithChoose(getResources().getString(R.string.buy_a_gift), getResources().getString(R.string.sure_to_buy_her_a_gift), 1);
                    else
                        showDialogWithChoose(getResources().getString(R.string.buy_a_gift), getResources().getString(R.string.sure_to_buy_him_a_gift), 1);
                break;

            case R.id.button_love_takeSomewhere:
                DialogFragment newDialog = MyDialogFragment.newInstance(v.getId());
                newDialog.show(getActivity().getSupportFragmentManager(), "love_dialog_tag");
                break;

            case R.id.button_love_brokeUp:
                if(sharedPref.getString(getResources().getString(R.string.saved_love_key), SharedPreferencesDefaultValues.DefaultLove) != null)
                {
                    if(sharedPref.getBoolean(getResources().getString(R.string.saved_sex_key), SharedPreferencesDefaultValues.DefaultIsMale))
                        showDialogWithChoose(getResources().getString(R.string.break_up), getResources().getString(R.string.sure_to_break_up_with_her), 3);
                    else
                        showDialogWithChoose(getResources().getString(R.string.break_up), getResources().getString(R.string.sure_to_break_up_with_him), 3);
                    //Love.BreakUp(Objects.requireNonNull(getContext()));
                }
                break;

            case R.id.button_love_SearchLove:
                if(sharedPref.getString(getResources().getString(R.string.saved_love_key), SharedPreferencesDefaultValues.DefaultLove) == null && !sharedPref.getBoolean(getResources().getString(R.string.saved_is_sad_key), false))
                {
                    Random random = new Random();
                    Gson gson = new Gson();
                    Love love;
                    if(sharedPref.getBoolean(getResources().getString(R.string.saved_sex_key), SharedPreferencesDefaultValues.DefaultIsMale))
                        love = new Love(girlfriendsNames[random.nextInt(girlfriendsNames.length)]);
                    else
                        love = new Love(boyfriendsNames[random.nextInt(boyfriendsNames.length)]);
                    String json = gson.toJson(love);

                    editor.putString(getResources().getString(R.string.saved_love_key), json);
                    editor.putString(getResources().getString(R.string.saved_love_name_key), love.getName());

                    if(sharedPref.getBoolean(getResources().getString(R.string.saved_sex_key), SharedPreferencesDefaultValues.DefaultIsMale))
                        dialogs.showAlertDialog(getContext(), getResources().getString(R.string.found_love), getResources().getString(R.string.successful_found_girlfriend) + " " + love.getName());
                    else
                        dialogs.showAlertDialog(getContext(), getResources().getString(R.string.found_love), getResources().getString(R.string.successful_found_boyfriend) + " " + love.getName());
                    editor.apply();

                    view.findViewById(R.id.girlboyfriend_status).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.button_love_IncreaseRelationship).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.progressBar_girlboyFriend_relations).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.relations).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.button_love_buyGift).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.button_love_takeSomewhere).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.button_love_brokeUp).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.button_love_SearchLove).setVisibility(View.GONE);

                    if(sharedPref.getBoolean(getResources().getString(R.string.saved_sex_key), SharedPreferencesDefaultValues.DefaultIsMale))
                        loveName.setText(getResources().getString(R.string.girlfriend) + "" + sharedPref.getString(getResources().getString(R.string.saved_love_name_key), ""));
                    else
                        loveName.setText(getResources().getString(R.string.boyfriend) + "" + sharedPref.getString(getResources().getString(R.string.saved_love_name_key), ""));

                    if(sharedPref.getInt(getResources().getString(R.string.saved_love_relationship_level_key), SharedPreferencesDefaultValues.DefaultLoveRelationshipLevel) <= 1)
                        loveStatus.setText(getResources().getString(R.string.status) + " " + getResources().getString(R.string.lovers));
                    else if(sharedPref.getInt(getResources().getString(R.string.saved_love_relationship_level_key), SharedPreferencesDefaultValues.DefaultLoveRelationshipLevel) == 2)
                        loveStatus.setText(getResources().getString(R.string.status) + " " + getResources().getString(R.string.betrothed));
                    else if(sharedPref.getInt(getResources().getString(R.string.saved_love_relationship_level_key), SharedPreferencesDefaultValues.DefaultLoveRelationshipLevel) >= 3)
                        loveStatus.setText(getResources().getString(R.string.status) + " " + getResources().getString(R.string.marry));

                    progressBar.setProgress(sharedPref.getInt(getResources().getString(R.string.saved_love_relations_key), SharedPreferencesDefaultValues.DefaultLoveRelations));
                }
                else
                    Toast.makeText(mContext, "You can't do it yet", Toast.LENGTH_SHORT).show();
                break;
        }

        editor.apply();
        progressBar.setProgress(sharedPref.getInt(getResources().getString(R.string.saved_love_relations_key), SharedPreferencesDefaultValues.DefaultLoveRelations));
    }

    private void showDialogWithChoose(final String title, final String message, final int whichOneEvent)
    {
        final SharedPreferences sharedPref = MyApplication.userSharedPref;
        final SharedPreferences.Editor editor = sharedPref.edit();

        AlertDialog.Builder dialog;

        onGirlTimerStop(getActivity());

        SharedPreferences settingsSharedPref = PreferenceManager.getDefaultSharedPreferences(mContext);
        boolean isDark = settingsSharedPref.getBoolean(SettingsActivity.DARK_SWITCH_KEY, false);
        if (isDark) {
            dialog = new AlertDialog.Builder(getContext(), R.style.Theme_MaterialComponents_Dialog_Alert);
        } else {
            dialog = new AlertDialog.Builder(getContext(), R.style.Theme_MaterialComponents_Light_Dialog_Alert);
        }
        dialog.setTitle(title)
                //.setIcon(R.drawable.ic_launcher)
                .setMessage(message)
                .setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        switch (whichOneEvent)
                        {
                            case 1:
                                //Die();
                                break;
                        }
                        onGirlTimerStart(getActivity());
                        dialoginterface.cancel();
                    }})
                .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        switch (whichOneEvent)
                        {
                            case 1:
                                if(sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) >= 100)
                                {
                                    editor.putInt(getResources().getString(R.string.saved_character_money_key), (sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) - 100));
                                    if(sharedPref.getInt(getResources().getString(R.string.saved_love_relations_key), SharedPreferencesDefaultValues.DefaultLoveRelations) <= 875)
                                        editor.putInt(getResources().getString(R.string.saved_love_relations_key), (sharedPref.getInt(getResources().getString(R.string.saved_love_relations_key), SharedPreferencesDefaultValues.DefaultLoveRelations) + 125));
                                    else
                                        editor.putInt(getResources().getString(R.string.saved_love_relations_key), 1000);

                                }
                                else
                                    Toast.makeText(getContext(), getResources().getString(R.string.not_enough_money), Toast.LENGTH_SHORT).show();

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
                                    Toast.makeText(getContext(), getResources().getString(R.string.not_enough_money), Toast.LENGTH_SHORT).show();

                                editor.apply();
                                progressBar.setProgress(sharedPref.getInt(getResources().getString(R.string.saved_love_relations_key), SharedPreferencesDefaultValues.DefaultLoveRelations));
                                dialoginterface.cancel();
                                break;

                            case 3:
                                Love.BreakUp(Objects.requireNonNull(getContext()));
//                                editor.putString(getResources().getString(R.string.saved_love_key), null);
//                                editor.putInt(getResources().getString(R.string.saved_love_relations_key), SharedPreferencesDefaultValues.DefaultLoveRelations);
//                                editor.putInt(getResources().getString(R.string.saved_love_relationship_level_key), SharedPreferencesDefaultValues.DefaultLoveRelationshipLevel);
//                                editor.apply();

                                loveName.setText(getResources().getString(R.string.single));
                                view.findViewById(R.id.girlboyfriend_status).setVisibility(View.GONE);
                                view.findViewById(R.id.button_love_IncreaseRelationship).setVisibility(View.GONE);
                                view.findViewById(R.id.progressBar_girlboyFriend_relations).setVisibility(View.GONE);
                                view.findViewById(R.id.relations).setVisibility(View.GONE);
                                view.findViewById(R.id.button_love_buyGift).setVisibility(View.GONE);
                                view.findViewById(R.id.button_love_takeSomewhere).setVisibility(View.GONE);
                                view.findViewById(R.id.button_love_brokeUp).setVisibility(View.GONE);
                                view.findViewById(R.id.button_love_SearchLove).setVisibility(View.VISIBLE);
                                break;

                            default:
                                dialoginterface.cancel();
                                break;
                        }
                        onGirlTimerStart(getActivity());
                    }
                }).show();
    }
}
