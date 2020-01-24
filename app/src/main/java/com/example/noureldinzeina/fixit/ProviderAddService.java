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

public class ProviderAddService extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static ArrayList<String> List;
    public static ArrayList<String> subServiceList;
    private ListView list;
    private DatabaseReference firebase;
    private DatabaseReference subServiceBase,SPBase;
    private String UserID,userKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_add_service);

        UserID= getIntent().getExtras().getString("ID");

        List = new ArrayList<String>();

        list = (ListView) findViewById(R.id.List2);
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


                    }
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        firebase = FirebaseDatabase.getInstance().getReference("Services");


        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {


                    String serviceName = childSnapshot.child("name").getValue(String.class);

                    List.add(serviceName);

                    myArrayAdapter.notifyDataSetChanged();

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        list.setOnItemClickListener(this);


        /*
        list4 = (ListView) findViewById(R.id.List2);
        CustomAdapter2 myCustomAdapter = new CustomAdapter2(ProviderAddService.this,MainActivity.services);
        list4.setAdapter(myCustomAdapter);
        list4.setOnItemClickListener(this);
        */


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(ProviderAddService.this, List.get(position), Toast.LENGTH_SHORT).show();
        final String serviceToPush = List.get(position);

        //Toast.makeText(ProviderAddService.this,"User Key: "+  userKey, Toast.LENGTH_SHORT).show();
        subServiceBase = FirebaseDatabase.getInstance().getReference().child("ServiceProvider").child(userKey).child("SubServices");
        subServiceBase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean notAddedYet = true;
                for(DataSnapshot dummySnapShot : dataSnapshot.getChildren()){
                    String currentService = dummySnapShot.getValue(String.class);
                    if(currentService.equals(serviceToPush)){
                            notAddedYet = false;
                    }
                }
                if(notAddedYet){
                    subServiceBase.push().setValue(serviceToPush);
                    Toast.makeText(ProviderAddService.this,"Service Added", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ProviderAddService.this,"Service already Added", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        finish();



    }





}



    /*
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Service service = MainActivity.services.get(i);

        if(!MainActivity.servicesProvider.contains(service)){
            MainActivity.servicesProvider.add(service);
        }
        CustomAdapter2 adapter = new CustomAdapter2(ProviderAddService.this,MainActivity.services);
        list4.setAdapter(adapter);
    }
}
*/

