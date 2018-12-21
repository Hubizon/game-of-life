package com.example.hubert.gameoflife.Utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.hubert.gameoflife.MainActivity;
import com.example.hubert.gameoflife.MyDialogDead;
import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.SettingsActivity;

public class Dialogs {

    private OnDialogInteractionListener mListener;
    public interface OnDialogInteractionListener {
        void onDialogInteractionTimerStop();
        void onDialogInteractionTimerStart();
        void onDialogInteractionDie();
        void onDialogResume(MenuItem item, Runnable runnable);
    }

    public Dialogs(Context context) {
        if (context instanceof OnDialogInteractionListener) {
            mListener = (OnDialogInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public void showDialogWithChoose(final SharedPreferences sharedPref, final Context context, final String title, final String message, final int whichOneEvent)
    {
        mListener.onDialogInteractionTimerStop();
        final SharedPreferences.Editor editor = sharedPref.edit();

        AlertDialog.Builder dialog;

        //SharedPreferences settingsSharedPref = context.getSharedPreferences(context.getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        SharedPreferences settingsSharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        boolean isDark = settingsSharedPref.getBoolean(SettingsActivity.DARK_SWITCH_KEY, false);
        if (isDark) {
            dialog = new AlertDialog.Builder(context, R.style.Theme_AppCompat_Dialog_Alert);
        } else {
            dialog = new AlertDialog.Builder(context, R.style.Theme_AppCompat_Light_Dialog_Alert);
        }
        dialog.setTitle(title)
                //.setIcon(R.drawable.ic_launcher)
                .setMessage(message)
                .setNegativeButton(context.getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        mListener.onDialogInteractionTimerStop();
                        switch (whichOneEvent)
                        {
                            case 1:
                                mListener.onDialogInteractionDie();
                                break;

                            case 6:
                                editor.putInt(context.getResources().getString(R.string.saved_karma_points_key), sharedPref.getInt(context.getResources().getString(R.string.saved_karma_points_key), SharedPreferencesDefaultValues.DefaultKarmaPoints) - 10);
                                break;

                            case 7:
                               // sharedPref.edit().clear().apply();
                               // DialogFragment newDialog = MyDialogOpenFragment.newInstance(MyDialogOpenFragment.MODE_RESET);
                               // newDialog.show(((AppCompatActivity)context).getSupportFragmentManager(), "open_dialog_tag");

                                //showAlertDialog(context, sharedPref, "You died", "Do you want to play again?");
                                //TODO: wyczyścic sharedpref

                                mListener.onDialogInteractionTimerStop();
                                DialogFragment deadDialog = MyDialogDead.newInstance();
                                deadDialog.show(((AppCompatActivity) context).getSupportFragmentManager(), "open_dead_dialog_tag");
                                break;
                        }
                        dialoginterface.cancel();
                        //TODO: start timer
                    }})
                .setPositiveButton(context.getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        mListener.onDialogInteractionTimerStart();
                        switch (whichOneEvent)
                        {
                            case 1:
                                if(sharedPref.getInt(context.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) < 0)
                                    mListener.onDialogInteractionDie();
                                editor.apply();
                                dialoginterface.cancel();
                                break;

                            case 2:
                                editor.putInt(context.getResources().getString(R.string.saved_character_money_key), (sharedPref.getInt(context.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) + 2500));
                                editor.apply();
                                dialoginterface.cancel();
                                break;

                            case 3:
                                if(sharedPref.getInt(context.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) >= 100)
                                {
                                    editor.putInt(context.getResources().getString(R.string.saved_character_money_key), (sharedPref.getInt(context.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) - 100));
                                    if(sharedPref.getInt(context.getResources().getString(R.string.saved_love_relations_key), SharedPreferencesDefaultValues.DefaultLoveRelations) < 1000)
                                        editor.putInt(context.getResources().getString(R.string.saved_love_relations_key), (sharedPref.getInt(context.getResources().getString(R.string.saved_love_relations_key), SharedPreferencesDefaultValues.DefaultLoveRelations) + 50));
                                }
                                else
                                    Toast.makeText(context, context.getResources().getString(R.string.not_enough_money), Toast.LENGTH_SHORT).show();
                                editor.apply();
                                dialoginterface.cancel();
                                break;

                            case 4:
                                if(sharedPref.getInt(context.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) >= 75)
                                {
                                    editor.putInt(context.getResources().getString(R.string.saved_character_money_key), (sharedPref.getInt(context.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) - 75));
                                    if(sharedPref.getInt(context.getResources().getString(R.string.saved_love_relations_key), SharedPreferencesDefaultValues.DefaultLoveRelations) < 1000)
                                        editor.putInt(context.getResources().getString(R.string.saved_love_relations_key), (sharedPref.getInt(context.getResources().getString(R.string.saved_love_relations_key), SharedPreferencesDefaultValues.DefaultLoveRelations) + 40));
                                }
                                else
                                    Toast.makeText(context, context.getResources().getString(R.string.not_enough_money), Toast.LENGTH_SHORT).show();
                                editor.apply();
                                dialoginterface.cancel();
                                break;

                            case 5:
                                editor.putString(context.getResources().getString(R.string.saved_love_key), null);
                                if(sharedPref.getInt(context.getResources().getString(R.string.saved_love_relations_key), SharedPreferencesDefaultValues.DefaultLoveRelations) < 1000)
                                    editor.putInt(context.getResources().getString(R.string.saved_love_relations_key), SharedPreferencesDefaultValues.DefaultLoveRelations);
                                editor.apply();
                                dialoginterface.cancel();
                                break;

                            case 6:
                                editor.putInt(context.getResources().getString(R.string.saved_karma_points_key), sharedPref.getInt(context.getResources().getString(R.string.saved_karma_points_key), SharedPreferencesDefaultValues.DefaultKarmaPoints) + 10);
                                editor.putBoolean(context.getResources().getString(R.string.saved_do_borrow_money_key), true);
                                editor.putInt(context.getResources().getString(R.string.saved_character_money_key), (sharedPref.getInt(context.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) - 1000));
                                editor.apply();
                                dialoginterface.cancel();
                                break;

                            case 7:
                                if (MainActivity.mRewardedVideoAd.isLoaded())
                                    MainActivity.mRewardedVideoAd.show();
                                else
                                    Toast.makeText(context, context.getResources().getString(R.string.no_ads), Toast.LENGTH_SHORT).show();
                                break;

                            default:
                                dialoginterface.cancel();
                                break;
                        }
                        //TODO: Michal!!! start timer
                    }
                }).show();
    }

    public void showAlertDialog(Context context, SharedPreferences sharedPreferences, String title, final String message)
    {
        mListener.onDialogInteractionTimerStop();
        AlertDialog.Builder dialog;

        SharedPreferences settingsSharedPref = context.getSharedPreferences(context.getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        boolean isDark = settingsSharedPref.getBoolean(SettingsActivity.DARK_SWITCH_KEY, false);
        if (isDark) {
            dialog = new AlertDialog.Builder(context, R.style.Theme_AppCompat_Dialog_Alert);
        } else {
            dialog = new AlertDialog.Builder(context, R.style.Theme_AppCompat_Light_Dialog_Alert);
        }
        dialog.setTitle(title)
                //.setIcon(R.drawable.ic_launcher)
                .setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        mListener.onDialogInteractionTimerStart();
                        dialoginterface.dismiss();
                    }
                })
                .setCancelable(false)
                .show();
    }

    public void showResumeDialog(Context context, final MenuItem item, final Runnable runnable) {
        Dialog resumeDialog = new Dialog(context) {
            @Override
            public boolean onTouchEvent(@NonNull MotionEvent event) {
                this.dismiss();
                mListener.onDialogResume(item, runnable);
                return true;
            }
        };
        resumeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        resumeDialog.setContentView(R.layout.dialog_stop);
        final Window window = resumeDialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setBackgroundDrawableResource(R.color.translucent_black);
        resumeDialog.show();
    }

}
