package com.example.noureldinzeina.fixit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AdminProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);
        TextView textView=findViewById(R.id.AdminUsername);
        String string = getIntent().getExtras().getString("Admin");
        textView.setText(string);
        Button button = (Button) findViewById(R.id.AddService);
        Button button1 = findViewById(R.id.DeleteService);
        Button button2 = findViewById(R.id.ListUsers);
        Button button3 = findViewById(R.id.EditService);
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

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEditActivity();
            }
        });

    }

    public void openListActivity() {
        Intent intent = new Intent(AdminProfile.this,CurrentUsersListVIew.class);
        startActivity(intent);
    }

    private void openServicesList() {
        Intent intent = new Intent(AdminProfile.this,AddDeleteServiceList.class);
        startActivity(intent);
    }

    private void openDeleteList() {
        Intent intent = new Intent(AdminProfile.this,DeleteService.class);
        startActivity(intent);
    }

    public void openEditActivity(){
        Intent intent = new Intent(AdminProfile.this,EditService.class);
        startActivity(intent);
    }
}
