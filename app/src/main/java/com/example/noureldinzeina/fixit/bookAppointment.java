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

public class bookAppointment extends AppCompatActivity  implements AdapterView.OnItemClickListener {

    public static ArrayList<String> List,cmpNameList,startTimeList,endTimeList,dayList;
    public static ArrayList<String> Keys;
    private ListView newlist;
    private DatabaseReference firebase;
    private DatabaseReference subAvailability,SPAppointment, SPBase ;
    private String UserID,userKey,name,Email,userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        Email = getIntent().getExtras().getString("userEmail");
        userName = getIntent().getExtras().getString("userName");

        UserID= getIntent().getExtras().getString("SP");

        SPBase = FirebaseDatabase.getInstance().getReference("ServiceProvider");

        List = new ArrayList<String>();
        cmpNameList = new ArrayList<String>();
        startTimeList = new ArrayList<String>();
        endTimeList = new ArrayList<String>();
        dayList = new ArrayList<String>();
        Keys = new ArrayList<String>();
        newlist = (ListView) findViewById(R.id.theAvailabilities);
        final ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, List);
        newlist.setAdapter(myArrayAdapter);
        //Toast.makeText(bookAppointment.this,UserID, Toast.LENGTH_SHORT).show();


        SPBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List.clear();
                cmpNameList.clear();
                startTimeList.clear();
                endTimeList.clear();
                dayList.clear();
                Keys.clear();
                myArrayAdapter.notifyDataSetChanged();

                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()){
                    final String currentID = childSnapshot.child("email").getValue(String.class);
                    final String name = childSnapshot.child("companyName").getValue(String.class);
                    final String currentKey = childSnapshot.getKey();



                    if(currentID.equals(UserID)){

                        userKey = childSnapshot.getKey();
                        //Toast.makeText(bookAppointment.this,userKey, Toast.LENGTH_SHORT).show();
                        subAvailability = FirebaseDatabase.getInstance().getReference("ServiceProvider").child(userKey).child("Availabilities");

                        subAvailability.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for(DataSnapshot childSnapshot : dataSnapshot.getChildren()){

                                    String day = childSnapshot.child("day").getValue(String.class);
                                    Integer startTime = childSnapshot.child("starthour").getValue(Integer.class);
                                    String startTimeString = String.valueOf(startTime);
                                    Integer endTime = childSnapshot.child("endhour").getValue(Integer.class);
                                    String endTimeString = String.valueOf(endTime);
                                    String sentence = "On " + day + " from " + startTimeString + " to " + endTimeString;
                                    List.add(sentence);

                                    myArrayAdapter.notifyDataSetChanged();

                                    startTimeList.add(startTimeString);
                                    endTimeList.add(endTimeString);
                                    cmpNameList.add(name);
                                    dayList.add(day);
                                    Keys.add(currentKey);


                                }

                                if(List.isEmpty()){
                                    Toast.makeText(bookAppointment.this,"Service Provider has no availabilities", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        newlist.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        final String start = startTimeList.get(position);
        final String end = endTimeList.get(position);
        final String name = cmpNameList.get(position);
        final String day = dayList.get(position);
        final String key = Keys.get(position);
        Intent i = new Intent(bookAppointment.this, pickappointmentDay.class);
        i.putExtra("SP", name);
        i.putExtra("start", start);
        i.putExtra("end", end);
        i.putExtra("day", day);
        i.putExtra("key", key);
        i.putExtra("userEmail",Email);
        i.putExtra("userName",userName);

        startActivity(i);

        finish();

    }
}
