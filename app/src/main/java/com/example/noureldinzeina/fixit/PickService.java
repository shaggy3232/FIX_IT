package com.example.noureldinzeina.fixit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PickService extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private DatabaseReference SubServices;
    private String UserKey;
    private DatabaseReference SPServiceBase;
    public static ArrayList<String> List;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_service);
        UserKey= getIntent().getExtras().getString("SPKey");

        List = new ArrayList<String>();
        list = (ListView) findViewById(R.id.serviceList);

        final ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, List);
        list.setAdapter(myArrayAdapter);

        SPServiceBase = FirebaseDatabase.getInstance().getReference("ServiceProvider").child(UserKey).child("SubServices");
        SPServiceBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List.clear();
                for(DataSnapshot childSnapshot : dataSnapshot.getChildren()){
                    String service = childSnapshot.getValue(String.class);
                    List.add(service);
                    myArrayAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        list.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final String serviceChosen = List.get(position);
        Intent intent = new Intent();
        intent.putExtra("ServiceChosen", serviceChosen);
        setResult(RESULT_OK, intent);
        finish();
    }
}
