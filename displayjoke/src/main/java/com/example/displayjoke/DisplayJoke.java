package com.example.displayjoke;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by brockrice on 2/23/18.
 */

public class DisplayJoke extends AppCompatActivity {

    private static final String JOKE_KEY = "joke_key";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvivity_display_joke);

        TextView jokeTextView = findViewById(R.id.tv_joke);

        Intent intentFromTask = getIntent();
        String jokeString = intentFromTask.getStringExtra(JOKE_KEY);
        jokeTextView.setText(jokeString);
        Log.d("JOKE_STRING", "onCreate: " + jokeString);

    }
}
