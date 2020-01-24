package com.example.noureldinzeina.fixit;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class WelcomePage extends AppCompatActivity {
    TextView textView;
    Button ToLogin;
    String string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        textView=findViewById(R.id.txtView);
        string=getIntent().getExtras().getString("Value");
        textView.setText(string);

        ToLogin = (Button) findViewById(R.id.toLogin);

        ToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLoginPage();
            }
        });
    }

    public void goToLoginPage(){
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }
}
