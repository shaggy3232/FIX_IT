package com.example.noureldinzeina.fixit;

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

public class ProviderDeleteService extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView list;
    private DatabaseReference SPBase,subServiceBase;
    //private boolean deleteValueKnown = false;
    private String UserID,userKey;
    //public String wantedKey;
    public static ArrayList<String> List;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_delete_service);

        UserID= getIntent().getExtras().getString("ID");

        List = new ArrayList<String>();

        list = (ListView) findViewById(R.id.List);

        final ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, List);
        list.setAdapter(myArrayAdapter);

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


        list.setOnItemClickListener(this);



        /*
        list2 = (ListView) findViewById(R.id.List);
        CustomAdapter2 myCustomAdapter = new CustomAdapter2(ProviderDeleteService.this,MainActivity.servicesProvider);
        list2.setAdapter(myCustomAdapter);
        list2.setOnItemClickListener(this);
        */
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)  {


        final String serviceToDelete = List.get(position);
        subServiceBase = FirebaseDatabase.getInstance().getReference().child("ServiceProvider").child(userKey).child("SubServices");
        subServiceBase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dummySnapShot : dataSnapshot.getChildren()){
                    String currentService = dummySnapShot.getValue(String.class);
                    if(currentService.equals(serviceToDelete)){
                        dummySnapShot.getRef().removeValue();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //subServiceBase.setValue(null);
        Toast.makeText(ProviderDeleteService.this,"Service Deleted", Toast.LENGTH_SHORT).show();
        finish();
        //Toast.makeText(ProviderDeleteService.this,"Wanted Key: "+  wantedKey, Toast.LENGTH_SHORT).show();
        //subServiceBase.child(wantedKey).removeValue();

        /*
        Service service = MainActivity.servicesProvider.get(i);
        MainActivity.servicesProvider.remove(service);
        CustomAdapter2 adapter = new CustomAdapter2(ProviderDeleteService.this,MainActivity.servicesProvider);
        list2.setAdapter(adapter);
        */
    }
}
