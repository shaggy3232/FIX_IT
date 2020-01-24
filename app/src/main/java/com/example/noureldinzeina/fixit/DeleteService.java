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

public class DeleteService extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView list;
    private DatabaseReference ServiceBase;
    public static ArrayList<String> List;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_service);

        List = new ArrayList<String>();
        list = (ListView) findViewById(R.id.List);

        final ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, List);
        list.setAdapter(myArrayAdapter);

        ServiceBase = FirebaseDatabase.getInstance().getReference("Services");

        ServiceBase.addValueEventListener(new ValueEventListener() {
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
        list2 = (ListView) findViewById(R.id.List);
        CustomAdapter2 myCustomAdapter = new CustomAdapter2(DeleteService.this,MainActivity.services);
        list2.setAdapter(myCustomAdapter);
        list2.setOnItemClickListener(this);
        */

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        final String serviceToDelete = List.get(position);

        ServiceBase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dummySnapShot : dataSnapshot.getChildren()){
                    String currentService = dummySnapShot.child("name").getValue(String.class);
                    if(currentService.equals(serviceToDelete)){
                        dummySnapShot.getRef().removeValue();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Toast.makeText(DeleteService.this,"Service Deleted", Toast.LENGTH_SHORT).show();
        finish();


        /*
        Service service = MainActivity.services.get(i);
        MainActivity.services.remove(service);
        CustomAdapter2 adapter = new CustomAdapter2(DeleteService.this,MainActivity.services);
        list2.setAdapter(adapter);
        */
    }

}
