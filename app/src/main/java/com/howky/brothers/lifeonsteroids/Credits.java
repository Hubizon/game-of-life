package com.howky.brothers.lifeonsteroids;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.howky.brothers.lifeonsteroids.utils.ScrollingTextView;

public class Credits extends AppCompatActivity {

    private static final String authorsText = "Icons designed by:\n(flaticon.com)\nFreepik\nVectors Market\nSmashicons\nNikita Golubev\nRoundicons\n************\n" +
            "\n(flaticon.com)\nFreepik\nVectors Market\nSmashicons\nNikita Golubev\nRoundicons\n************\n" +
            "\n(flaticon.com)\nFreepik\nVectors Market\nSmashicons\nNikita Golubev\nRoundicons\n************\n" +
            "\n(flaticon.com)\nFreepik\nVectors Market\nSmashicons\nNikita Golubev\nRoundicons\n************\n" +
            "\n(flaticon.com)\nFreepik\nVectors Market\nSmashicons\nNikita Golubev\nRoundicons\n************\n" +
            "\n(flaticon.com)\nFreepik\nVectors Market\nSmashicons\nNikita Golubev\nRoundicons\n************\n" +
            "\n(flaticon.com)\nFreepik\nVectors Market\nSmashicons\nNikita Golubev\nRoundicons\n************\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        ScrollingTextView scrollingTextView = findViewById(R.id.credit_tv);
        scrollingTextView.setText(authorsText);
    }
}
