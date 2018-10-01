package com.example.hubert.gameoflife.Work;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.hubert.gameoflife.Education.EducationFragment;
import com.example.hubert.gameoflife.Education.LearnInHomeActivity;
import com.example.hubert.gameoflife.MainActivity;
import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.Shop.BuyActivity;
import com.example.hubert.gameoflife.Utils.SharedPreferencesDefaultValues;

public class FindJobActivity extends AppCompatActivity
        implements View.OnClickListener{

    private static final int PAGE_NUMBER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_job);
        setTitle(R.string.title_find_job);

        CardView officeWorkCardview = findViewById(R.id.cardview_officeWork);
        officeWorkCardview.setOnClickListener(this);

        CardView criminalWorkCardview = findViewById(R.id.cardview_criminalWork);
        criminalWorkCardview.setOnClickListener(this);

        CardView artsWorkCardview = findViewById(R.id.cardview_artsWork);
        artsWorkCardview.setOnClickListener(this);

        CardView outsideWorkCardview = findViewById(R.id.cardview_outsideWork);
        outsideWorkCardview.setOnClickListener(this);

        CardView otherWorkCardview = findViewById(R.id.cardview_otherWork);
        otherWorkCardview.setOnClickListener(this);

        CardView educationWorkCardview = findViewById(R.id.cardview_educationWork);
        educationWorkCardview.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(), ChooseJobActivity.class);
        switch (view.getId()) {
            case R.id.cardview_officeWork:
                intent.putExtra(getResources().getString(R.string.send_shop_click_id), view.getId());
                startActivity(intent);
                break;

            case R.id.cardview_criminalWork:
                intent.putExtra(getResources().getString(R.string.send_shop_click_id), view.getId());
                startActivity(intent);
                break;

            case R.id.cardview_artsWork:
                intent.putExtra(getResources().getString(R.string.send_shop_click_id), view.getId());
                startActivity(intent);
                break;

            case R.id.cardview_outsideWork:
                intent.putExtra(getResources().getString(R.string.send_shop_click_id), view.getId());
                startActivity(intent);
                break;

            case R.id.cardview_otherWork:
                intent.putExtra(getResources().getString(R.string.send_shop_click_id), view.getId());
                startActivity(intent);
                break;

            case R.id.cardview_educationWork:
                SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(getApplicationContext().getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
                sharedPref.edit().putBoolean(getApplicationContext().getResources().getString(R.string.saved_is_in_school_now_key), SharedPreferencesDefaultValues.DefaultIsInSchoolNow).apply();
                onBackPressed();
                break;

            default:
                break;
        }
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
        Intent intent = new Intent(FindJobActivity.this, MainActivity.class);
        intent.putExtra(MainActivity.INTENT_PAGE, PAGE_NUMBER);
        startActivity(intent);
        finish();
    }

}
