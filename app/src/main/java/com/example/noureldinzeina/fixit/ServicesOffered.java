package com.example.noureldinzeina.fixit;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ServicesOffered extends AppCompatActivity {
    ListView list;
    public static ArrayList<String> List;
    public String UserID,userKey;
    private DatabaseReference SPBase,subServiceBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_offered);

        List = new ArrayList<String>();
        list = (ListView) findViewById(R.id.ListView);

        final ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, List);
        list.setAdapter(myArrayAdapter);

        UserID= getIntent().getExtras().getString("ID");



        SPBase = FirebaseDatabase.getInstance().getReference("ServiceProvider");

        SPBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()){
                    String currentID = childSnapshot.child("email").getValue(String.class);


                    if(currentID.equals(UserID)){

                        userKey = childSnapshot.getKey();


                        subServiceBase = FirebaseDatabase.getInstance().getReference().child("ServiceProvider").child(userKey).child("SubServices");
                        subServiceBase.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for(DataSnapshot childSnapshot : dataSnapshot.getChildren()){
                                    String currentKey = childSnapshot.getKey();

                                    Object object = childSnapshot.getValue();
                                    String service = String.valueOf(object);

                                    List.add(service);
                                    myArrayAdapter.notifyDataSetChanged();

                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


                    }
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //Toast.makeText(ServicesOffered.this, "Current Key: " + userKey, Toast.LENGTH_SHORT).show();




        /*
        subServiceBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot childSnapshot : dataSnapshot.getChildren()){
                    String currentKey = childSnapshot.getKey();
                    Object object = childSnapshot.child(currentKey).getValue(Object.class);
                    String service = String.valueOf(object);
                    List.add(service);
                    myArrayAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        */





    }
}
