package com.example.noureldinzeina.fixit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchByTime extends AppCompatActivity implements AdapterView.OnItemClickListener {

    DatabaseReference SPBase,subTimeBase;
    ListView list;
    public static ArrayList<String> List;
    public static ArrayList<String> List2;
    public static ArrayList<String> providersMatchRating,providersMatchTime,emails;
    public String match,name;
    private int currentID;
    EditText timeInput;
    public String stringtime;
    public Integer time;
    private String Email, userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_time);

        Email = getIntent().getExtras().getString("userEmail");
        userName = getIntent().getExtras().getString("userName");

        //ratingInput = (EditText) findViewById(R.id.SearchInput);
        timeInput = (EditText) findViewById(R.id.timeinputt);
        //rating = ratingInput.getText().toString().trim();
        stringtime = timeInput.getText().toString().trim();
        SPBase = FirebaseDatabase.getInstance().getReference("ServiceProvider");

        List = new ArrayList<String>();
        List2 = new ArrayList<String>();
        providersMatchRating = new ArrayList<String>();
        providersMatchTime = new ArrayList<String>();
        emails  = new ArrayList<String>();




        list = (ListView) findViewById(R.id.SPPP);

        Button search = findViewById(R.id.searchh);
        final ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, providersMatchTime);
        list.setAdapter(myArrayAdapter);



        search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                stringtime = timeInput.getText().toString().trim();

                if (validateString()) {

                    time = Integer.parseInt(stringtime);

                    if (validateInt()) {


                        //ArrayList<String> providersMatchRating;
                        //providersMatchRating = new ArrayList<String>();
                        providersMatchTime.clear();
                        myArrayAdapter.notifyDataSetChanged();


                        SPBase.addListenerForSingleValueEvent(new ValueEventListener() {

                            @Override

                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                                    String currentKey = childSnapshot.getKey();
                                    final String name = childSnapshot.child("companyName").getValue(String.class);
                                    final String email = childSnapshot.child("email").getValue(String.class);
                                    subTimeBase = FirebaseDatabase.getInstance().getReference("ServiceProvider").child(currentKey).child("Availabilities");
                                    subTimeBase.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            for (DataSnapshot subchildSnapshot : dataSnapshot.getChildren()) {
                                                //String subService = subchildSnapshot.getValue(String.class);
                                                Integer startTime = subchildSnapshot.child("starthour").getValue(Integer.class);
                                                Integer endTime = subchildSnapshot.child("endhour").getValue(Integer.class);
                                                String sentence = name + "                available from " + String.valueOf(startTime) + ":00  to " + String.valueOf(endTime) + ":00";
                                                //Toast.makeText(SearchByTime.this,bla,Toast.LENGTH_SHORT).show();
                                                //Toast.makeText(SearchByTime.this,bla2,Toast.LENGTH_SHORT).show();

                                                if (time >= startTime && time <= endTime) {
                                                    providersMatchTime.add(sentence);
                                                    emails.add(email);
                                                    myArrayAdapter.notifyDataSetChanged();
                                                    //Toast.makeText(SearchByTime.this,"Found",Toast.LENGTH_SHORT).show();

                                                }


                                            }


                                        }


                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });

                                }



                            }


                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                    }

                }
            }


        });


        list.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        final String serviceProvider = emails.get(position);
        //Toast.makeText(SearchByRating.this,serviceProvider, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(SearchByTime.this, bookAppointment.class);
        String str1 = serviceProvider;
        i.putExtra("userEmail",Email);
        i.putExtra("userName",userName);
        i.putExtra("SP", str1);
        startActivity(i);
        finish();

    }



    public boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }


    public boolean validateString(){
        boolean valid = true;

        if(stringtime.isEmpty()){
            timeInput.setError("Please enter a valid input");
            valid = false;
        }

        if(!isNumeric(stringtime)){
            timeInput.setError("Please enter a digit only input");
            valid = false;
        }


        /*
        if(stringtime.isEmpty() || !isNumeric(stringtime) || !ValidNumber()) {
            timeInput.setError("Please enter a number between 1 and 5 inclusive");
            stringtime=null;
            valid=false;
        }
        */
        return valid;

    }


    public boolean validateInt(){
        boolean valid = true;
        if(time != null){
            if(24 < time || time < 0){
                timeInput.setError("Please enter a valid time input (0 to 24)");
                valid = false;
            }
        }
        return valid;
    }


    public boolean ValidNumber(){

        if(time < 0){
            return true;
        }
        if(time>24){
            return true;
        }

        return false;
    }



    public void toast(){
        Toast.makeText(SearchByTime.this,"No Service Providers Match your search", Toast.LENGTH_SHORT).show();
    }








}
