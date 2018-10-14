package com.example.hubert.gameoflife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hubert.gameoflife.Utils.ScrollingTextView;

public class Credits extends AppCompatActivity {

    private static final String authorsText = "Icons made by:\n(flaticon.com)\nFreepik\nVectors Market\n Smashicons";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        ScrollingTextView scrollingTextView = findViewById(R.id.credit_tv);
        scrollingTextView.setText(authorsText);
    }
}
