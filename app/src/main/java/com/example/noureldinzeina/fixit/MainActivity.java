package com.example.noureldinzeina.fixit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button CreateAccountButton;
    private Button LoginButton;
    private Button ListButton;
    public static ArrayList<Account> accounts= new ArrayList<Account>();
    public static ArrayList<Service> services= new ArrayList<Service>();
    public static ArrayList<Service> servicesProvider= new ArrayList<Service>();
    public static ArrayList<ServiceProvider> Provider= new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (accounts.isEmpty()){
        Admin admin = new Admin("admin","admin","admin","admin","admin@uottawa.ca");
        accounts.add(admin);}
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CreateAccountButton = (Button) findViewById(R.id.CreateButton);


        LoginButton = (Button) findViewById(R.id.LoginButton);
        ListButton = (Button) findViewById(R.id.ListButton);
        CreateAccountButton.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View v){
            openCreateActivity();
          }
        });

        LoginButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v ){
                openLoginPage();
            }
        });
        ListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openListActivity();
            }
        });
    }


    public void openCreateActivity(){
    Intent intent = new Intent(this, CreateAccount.class);
    startActivity(intent);
    }

    public void openLoginPage(){
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }

    public void openListActivity() {
        Intent intent = new Intent(this,CurrentUsersListVIew.class);
        startActivity(intent);
    }

}
