package com.example.noureldinzeina.fixit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdminWelcomePage extends AppCompatActivity {
    TextView textView;
    Button ToLogin;
    String string;

    Button ListButton1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_welcome_page);
        textView=findViewById(R.id.txtView1);
        string=getIntent().getExtras().getString("Value");
        textView.setText(string);

        ToLogin = (Button) findViewById(R.id.toLogin1);
        ListButton1= (Button) findViewById(R.id.users);

        ToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLoginPage();
            }
        });
        ListButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openListActivity();
            }
        });}

        public void openListActivity() {
            Intent intent = new Intent(this,CurrentUsersListVIew.class);
            startActivity(intent);
        }

    public void goToLoginPage(){
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }
}
