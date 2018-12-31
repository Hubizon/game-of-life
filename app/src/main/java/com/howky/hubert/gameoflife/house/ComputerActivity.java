package com.howky.hubert.gameoflife.house;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.github.florent37.tutoshowcase.TutoShowcase;
import com.howky.hubert.gameoflife.MainActivity;
import com.howky.hubert.gameoflife.MyApplication;
import com.howky.hubert.gameoflife.R;
import com.howky.hubert.gameoflife.shop.BuyActivity;
import com.howky.hubert.gameoflife.utils.Dialogs;
import com.howky.hubert.gameoflife.utils.MyDialogFragment;
import com.howky.hubert.gameoflife.utils.SharedPreferencesDefaultValues;

public class ComputerActivity extends AppCompatActivity implements View.OnClickListener,
        Dialogs.OnResumeDialogInteractionListener{

    private static final int PAGE_NUMBER = 4;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer);
        setTitle(R.string.title_computer);

        sharedPref = MyApplication.userSharedPref;
        ((TextView)(findViewById(R.id.time_computer))).setText(getString(R.string.date_format,
                sharedPref.getInt(getString(R.string.saved_date_years_key), SharedPreferencesDefaultValues.DefaultDateYears),
                sharedPref.getInt(getString(R.string.saved_date_months_key), SharedPreferencesDefaultValues.DefaultDateMonths),
                sharedPref.getInt(getString(R.string.saved_date_days_key), SharedPreferencesDefaultValues.DefaultDateDays),
                sharedPref.getInt(getString(R.string.saved_time_hours_key), SharedPreferencesDefaultValues.DefaultTimeHours)));


        Button playComputer = findViewById(R.id.playComputer);
        playComputer.setOnClickListener(this);

        Button TalkComputer = findViewById(R.id.talkComputer);
        TalkComputer.setOnClickListener(this);

        Button supportComputer = findViewById(R.id.supportComputer);
        supportComputer.setOnClickListener(this);

        Button buyLotteries = findViewById(R.id.buyLotteries);
        buyLotteries.setOnClickListener(this);

        Button makeGameComputer = findViewById(R.id.makeGameComputer);
        makeGameComputer.setOnClickListener(this);

        Button drawSomethingComputer = findViewById(R.id.drawSomethingComputer);
        drawSomethingComputer.setOnClickListener(this);

        Button writePoemComputer = findViewById(R.id.writePoemComputer);
        writePoemComputer.setOnClickListener(this);

        Button recordMoviesComputer = findViewById(R.id.recordMoviesComputer);
        recordMoviesComputer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        DialogFragment newDialog = null;
        SharedPreferences.Editor editor = sharedPref.edit();

        switch (v.getId())
        {
            case R.id.playComputer:
                newDialog = MyDialogFragment.newInstance(v.getId());
                break;

            case R.id.talkComputer:
                newDialog = MyDialogFragment.newInstance(v.getId());
                break;

            case  R.id.supportComputer:
                showDialog(getResources().getString(R.string.support_a_charity_event), getResources().getString(R.string.sure_to_support_a_charity_event));
                break;

            case R.id.buyLotteries:
                Intent intent = new Intent(getApplicationContext(), BuyActivity.class);
                intent.putExtra(getResources().getString(R.string.send_shop_click_id), v.getId());
                startActivity(intent);
                break;

            case R.id.makeGameComputer:
                newDialog = MyDialogFragment.newInstance(v.getId());
                break;

            case R.id.drawSomethingComputer:
                newDialog = MyDialogFragment.newInstance(v.getId());
                break;

            case R.id.writePoemComputer:
                newDialog = MyDialogFragment.newInstance(v.getId());
                break;

            case R.id.recordMoviesComputer:
                newDialog = MyDialogFragment.newInstance(v.getId());
                break;
        }
        editor.apply();

        if (newDialog != null) newDialog.show(getSupportFragmentManager(), "home_dialog_tag");
    }

    private void showDialog(final String title, final String message)
    {
        final SharedPreferences.Editor editor = sharedPref.edit();

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(title)
                .setMessage(message)
                .setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.cancel();
                    }})
                .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        if(sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) >= 100)
                        {
                            editor.putInt(getResources().getString(R.string.saved_character_money_key), (sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) - 100);
                            editor.putInt(getResources().getString(R.string.saved_karma_points_key), (sharedPref.getInt(getResources().getString(R.string.saved_karma_points_key), SharedPreferencesDefaultValues.DefaultKarmaPoints)) + 5);
                            Toast.makeText(getApplicationContext(), "Your help will certainly be appreciated!", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.not_enough_money), Toast.LENGTH_SHORT).show();
                        editor.apply();
                    }
                }).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ComputerActivity.this, MainActivity.class);
        intent.putExtra(MainActivity.INTENT_PAGE, PAGE_NUMBER);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.CurrentContext = this;
    }

    @Override
    public void onDialogResume(MenuItem item) {
        Log.e("TODO", "TODO");
    }
}
