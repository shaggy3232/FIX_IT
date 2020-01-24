package com.example.noureldinzeina.fixit;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EditService extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView list3;
    CustomAdapter2 myCustomAdapter;
    public static ArrayList<String> serviceNames;
    public static ArrayList<String> hourlyRates;
    private ListView list;
    private DatabaseReference firebase;
    private ArrayAdapter<String> myArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_service);

        serviceNames = new ArrayList<String>();
        hourlyRates = new ArrayList<String>();


        list = (ListView) findViewById(R.id.ServiceL);


        myArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, serviceNames);

        list.setAdapter(myArrayAdapter);


        firebase = FirebaseDatabase.getInstance().getReference("Services");

        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {


                    String serviceName = childSnapshot.child("name").getValue(String.class);
                    String hourlyRate = childSnapshot.child("hourlyRate").getValue(String.class);


                    serviceNames.add(serviceName);
                    hourlyRates.add(hourlyRate);

                    myArrayAdapter.notifyDataSetChanged();

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        list.setOnItemClickListener(this);
    }



        /*
        list3 = (ListView) findViewById(R.id.ServiceL);
        myCustomAdapter = new CustomAdapter2(EditService.this,MainActivity.services);
        list3.setAdapter(myCustomAdapter);
        list3.setOnItemClickListener(this);
        */


    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        OpenEditBox(EditService.serviceNames.get(i),EditService.hourlyRates.get(i),i);
    }

    public void OpenEditBox(final String oldName, String oldRate, int index){
        final int i = index;
        final Dialog dialog = new Dialog(EditService.this);
        dialog.setTitle("Input Box");
        dialog.setContentView(R.layout.edit_service_input);
        TextView textView = findViewById(R.id.title);
        final EditText editItem = dialog.findViewById(R.id.EditService);
        final EditText editItem2 = dialog.findViewById(R.id.EditRate);
        editItem.setText(oldName);
        editItem2.setText(oldRate);
        Button button = dialog.findViewById(R.id.Done);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String newName = editItem.getText().toString().trim();
                final String newRate = editItem2.getText().toString().trim();
                EditService.serviceNames.set(i, newName);
                EditService.hourlyRates.set(i, newRate);

                firebase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot dummySnapShot : dataSnapshot.getChildren()){
                            String currentService = dummySnapShot.child("name").getValue(String.class);
                            String currentRate = dummySnapShot.child("hourlyRate").getValue(String.class);
                            if(currentService.equals(oldName)){
                                dummySnapShot.getRef().child("name").setValue(newName);
                                dummySnapShot.getRef().child("hourlyRate").setValue(newRate);
                                Toast.makeText(EditService.this,"Service Edited", Toast.LENGTH_SHORT).show();
                                finish();

                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


                //Service newservice = new Service(newName,newRate);
                //MainActivity.services.set(i, newservice);
                myArrayAdapter.notifyDataSetChanged();
                //myCustomAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        dialog.show();


    }

}
