package com.howky.brothers.lifeonsteroids.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.howky.brothers.lifeonsteroids.MainActivity;
import com.howky.brothers.lifeonsteroids.MyApplication;
import com.howky.brothers.lifeonsteroids.MyDialogDead;
import com.howky.brothers.lifeonsteroids.R;
import com.howky.brothers.lifeonsteroids.SettingsActivity;

import java.util.Objects;

public class Dialogs {

    private OnResumeDialogInteractionListener mListener;
    public interface OnResumeDialogInteractionListener {
        void onDialogResume(MenuItem item);
    }

    private void onDialogTimerStop(Context context) {
        final MyApplication globalApplication = (MyApplication) context.getApplicationContext();
        MainTimer.shouldWork = false;
        globalApplication.mainTimer.stopTimer();
    }

    private void onDialogTimerStart(Context context) {
        final MyApplication globalApplication = (MyApplication) context.getApplicationContext();
        MainTimer.shouldWork = true;
        globalApplication.mainTimer.startTimer();
    }


    public Dialogs(Context context) {
        if (context instanceof OnResumeDialogInteractionListener) {
            mListener = (OnResumeDialogInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnResumeDialogInteractionListener");
        }
    }

    void showDialogWithChoose(final SharedPreferences sharedPref, final Context context, final String title, final String message, final int whichOneEvent)
    {
        onDialogTimerStop(context);
        final SharedPreferences.Editor editor = sharedPref.edit();

        AlertDialog.Builder dialog;

        SharedPreferences settingsSharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        boolean isDark = settingsSharedPref.getBoolean(SettingsActivity.DARK_SWITCH_KEY, false);
        if (isDark) {
            dialog = new AlertDialog.Builder(context, R.style.Theme_MaterialComponents_Dialog_Alert);
        } else {
            dialog = new AlertDialog.Builder(context, R.style.Theme_MaterialComponents_Light_Dialog_Alert);
        }
        dialog.setTitle(title)
                .setMessage(message)
                .setNegativeButton(context.getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        switch (whichOneEvent)
                        {
                            case 1:
                                editor.putInt(context.getResources().getString(R.string.saved_health_key), 0);
                                onDialogTimerStart(context);
                                break;

                            case 6:
                                editor.putInt(context.getResources().getString(R.string.saved_karma_points_key), sharedPref.getInt(context.getResources().getString(R.string.saved_karma_points_key), SharedPreferencesDefaultValues.DefaultKarmaPoints) - 10);
                                onDialogTimerStart(context);
                                break;

                            case 7:
                                DialogFragment deadDialog = MyDialogDead.newInstance();
                                deadDialog.show(((AppCompatActivity) context).getSupportFragmentManager(), "open_dead_dialog_tag");
                                break;
                             default:
                                 onDialogTimerStart(context);
                                 break;
                        }
                        dialoginterface.cancel();
                    }})
                .setPositiveButton(context.getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        switch (whichOneEvent)
                        {
                            case 1:
                                Toast.makeText(context, "Thank you, I hope that we'll meet soon in this dangerous district", Toast.LENGTH_SHORT).show();
                                break;

                            case 2:
                                editor.putInt(context.getResources().getString(R.string.saved_character_money_key), (sharedPref.getInt(context.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) + 2500));
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
                                break;

                            case 5:
                                editor.putString(context.getResources().getString(R.string.saved_love_key), null);
                                if(sharedPref.getInt(context.getResources().getString(R.string.saved_love_relations_key), SharedPreferencesDefaultValues.DefaultLoveRelations) < 1000)
                                    editor.putInt(context.getResources().getString(R.string.saved_love_relations_key), SharedPreferencesDefaultValues.DefaultLoveRelations);
                                Toast.makeText(context, "You are a single now.", Toast.LENGTH_SHORT).show();
                                break;

                            case 6:
                                editor.putInt(context.getResources().getString(R.string.saved_karma_points_key), sharedPref.getInt(context.getResources().getString(R.string.saved_karma_points_key), SharedPreferencesDefaultValues.DefaultKarmaPoints) + 10);
                                editor.putBoolean(context.getResources().getString(R.string.saved_do_borrow_money_key), true);
                                editor.putInt(context.getResources().getString(R.string.saved_character_money_key), (sharedPref.getInt(context.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) - 1000));
                                break;

                            case 7:
                                if (MainActivity.hasAdd)
                                    ((MainActivity)mListener).mRewardedVideoAd.show();
                                else
                                    Toast.makeText(context, context.getResources().getString(R.string.no_ads), Toast.LENGTH_SHORT).show();
                                break;

                            default:
                                break;
                        }
                        editor.apply();
                        onDialogTimerStart(context);
                        dialoginterface.cancel();
                    }
                }).show();
    }

    public void showAlertDialog(final Context context, String title, final String message)
    {
        onDialogTimerStop(context);

        AlertDialog.Builder dialog;

        SharedPreferences settingsSharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        boolean isDark = settingsSharedPref.getBoolean(SettingsActivity.DARK_SWITCH_KEY, false);
        if (isDark) {
            dialog = new AlertDialog.Builder(context, R.style.Theme_MaterialComponents_Dialog_Alert);
        } else {
            dialog = new AlertDialog.Builder(context, R.style.Theme_MaterialComponents_Light_Dialog_Alert);
        }
        dialog.setTitle(title)
                .setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        MainTimer.shouldWork = true;
                        onDialogTimerStart(context);
                        dialoginterface.dismiss();
                    }
                })
                .setCancelable(false)
                .show();
    }

    public void showResumeDialog(Context context, final MenuItem item) {
        Dialog resumeDialog = new Dialog(context) {
            @Override
            public boolean onTouchEvent(@NonNull MotionEvent event) {
                this.dismiss();
                MainTimer.shouldWork = true;
                mListener.onDialogResume(item);
                return true;
            }
        };
        resumeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        resumeDialog.setContentView(R.layout.dialog_stop);
        final Window window = resumeDialog.getWindow();
        Objects.requireNonNull(window).setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setBackgroundDrawableResource(R.color.translucent_black);
        resumeDialog.show();
    }

}
