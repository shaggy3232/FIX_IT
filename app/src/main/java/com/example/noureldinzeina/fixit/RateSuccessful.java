package com.example.noureldinzeina.fixit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RateSuccessful extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_successful);
        TextView oldRating = findViewById(R.id.oldRate);
        String string1 = getIntent().getExtras().getString("New Rating");
        oldRating.setText(string1);


    }




}
