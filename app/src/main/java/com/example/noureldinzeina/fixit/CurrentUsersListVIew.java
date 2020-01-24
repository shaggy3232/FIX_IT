package com.example.noureldinzeina.fixit;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CurrentUsersListVIew extends AppCompatActivity {
    private ListView list;
    private TextView text;
    public static ArrayList<String> List;
    private DatabaseReference firebase;
    private Admin admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_users_list_view);

        List = new ArrayList<String>();


        list = (ListView) findViewById(R.id.ListView);


        final ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,List);

        list.setAdapter(myArrayAdapter);


        firebase = FirebaseDatabase.getInstance().getReference("AdminUser");

        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot childSnapshot : dataSnapshot.getChildren()){


                    String username = childSnapshot.child("username").getValue(String.class);

                    List.add(username);

                    myArrayAdapter.notifyDataSetChanged();

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}


