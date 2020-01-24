package com.example.noureldinzeina.fixit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ServiceProviderProfile extends AppCompatActivity {
    String ID,string;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_profile);



        TextView textView=findViewById(R.id.ProviderUsername);
        string = getIntent().getExtras().getString("Admin");
        ID = getIntent().getExtras().getString("ID");
        textView.setText(string);
        Button button = (Button) findViewById(R.id.AddService);
        Button button1 = findViewById(R.id.DeleteService);
        Button button2 = findViewById(R.id.ListUsers);
        Button button3 = findViewById(R.id.EditService);
        Button Availability = findViewById(R.id.Availabiltiy);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openServicesList();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDeleteList();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openListActivity();
            }
        });

        Availability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAvalibiltyManager();
            }
        });

    }

    public void openListActivity() {
        Intent intent = new Intent(ServiceProviderProfile.this,ServicesOffered.class);
        intent.putExtra("ID", ID);
        startActivity(intent);
    }

    private void openDeleteList() {
        Intent intent = new Intent(ServiceProviderProfile.this,ProviderDeleteService.class);
        intent.putExtra("ID", ID);
        startActivity(intent);
    }

    private void openServicesList() {
        Intent intent = new Intent(ServiceProviderProfile.this,ProviderAddService.class);
        intent.putExtra("ID", ID);
        startActivity(intent);
    }

    private void openAvalibiltyManager(){
        Intent intent = new Intent(ServiceProviderProfile.this,AvailabilityManager.class);
        intent.putExtra("ID", ID);
        startActivity(intent);

    }


}
