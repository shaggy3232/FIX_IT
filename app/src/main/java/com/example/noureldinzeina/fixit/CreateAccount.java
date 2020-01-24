package com.example.noureldinzeina.fixit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreateAccount extends AppCompatActivity {
    private Button Admin_Button;
    private Button UserButton;
    private Button ProviderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        Admin_Button  = (Button) findViewById(R.id.AdminButton);
        UserButton  = (Button) findViewById(R.id.HomeOwnerButton);
        ProviderButton  = (Button) findViewById(R.id.ServiceProviderButton);

        Admin_Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openCreateAdminActivity();
            }
        });

        UserButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openCreateUserActivity();
            }
        });


        ProviderButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openCreateProviderActivity();
            }
        });


    }

    public void openCreateAdminActivity(){
        Intent intent = new Intent(this, CreateAdminUser.class);
        startActivity(intent);
    }

    public void openCreateUserActivity(){
        Intent intent = new Intent(this, CreateHomeOwner.class);
        startActivity(intent);
    }

    public void openCreateProviderActivity(){
        Intent intent = new Intent(this, CreateProviderUser.class);
        startActivity(intent);
    }
}
