package com.example.noureldinzeina.fixit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserProfile extends AppCompatActivity {

    private String Email, string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        TextView textView=findViewById(R.id.UserUsername);

        Email = getIntent().getExtras().getString("Email");
        string = getIntent().getExtras().getString("Admin");
        textView.setText(string);

        Button timeSearch = findViewById(R.id.SearchTime);
        Button rateSearch = findViewById(R.id.SearchRate);
        Button typeSearch = findViewById(R.id.SearchType);
        Button rateProvider = findViewById(R.id.RateProvider);
        timeSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSearchByTimeActivity();
            }
        });

        rateSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSearchByRateActivity();
            }
        });

        rateProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRateActivity();
            }
        });

        typeSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSearchByTypeActivity();
            }
        });
    }


    public void openRateActivity() {
        Intent intent = new Intent(UserProfile.this,RateService.class);
        startActivity(intent);
    }
    public void openSearchByTimeActivity() {
        Intent intent = new Intent(UserProfile.this,SearchByTime.class);
        intent.putExtra("userEmail",Email);
        intent.putExtra("userName", string);
        startActivity(intent);
    }

    public void openSearchByTypeActivity() {
        Intent intent = new Intent(UserProfile.this,SearchByType.class);
        intent.putExtra("userEmail",Email);
        intent.putExtra("userName", string);
        startActivity(intent);
    }

    public void openSearchByRateActivity() {
        Intent intent = new Intent(UserProfile.this,SearchByRating.class);
        intent.putExtra("userEmail",Email);
        intent.putExtra("userName", string);
        startActivity(intent);
    }
}
