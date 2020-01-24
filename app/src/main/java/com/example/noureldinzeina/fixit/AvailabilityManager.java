package com.example.noureldinzeina.fixit;



import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AvailabilityManager extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    DatabaseReference SPBase,ABase;
    String ID;
    String day;
    String UserKey;
    Availability MondayAvailaibiltiy = new Availability("Monday",00,  00, 00,  00);
    Availability TuesdayAvailaibiltiy = new Availability("Tuesday",00,  00, 00,  00);
    Availability WednesdayAvailaibiltiy = new Availability("Wednesday",00,  00, 00,  00);
    Availability ThursdayAvailaibiltiy = new Availability("Thursday",00,  00, 00,  00);
    Availability FridayAvailaibiltiy = new Availability("Friday",00,  00, 00,  00);
    Availability SatursdayAvailaibiltiy = new Availability("Satursday",00,  00, 00,  00);
    Availability SundayAvailaibiltiy = new Availability("Sunday",00,  00, 00,  00);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availability_manager);

        ID = getIntent().getExtras().getString("ID");
        
        Button set = (Button) findViewById(R.id.set);

        SPBase = FirebaseDatabase.getInstance().getReference("ServiceProvider");


        SPBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot childSnapshot : dataSnapshot.getChildren()){

                    String Email = childSnapshot.child("email").getValue(String.class);
                    String Password = childSnapshot.child("password").getValue(String.class);
                    String Type = childSnapshot.child("type").getValue(String.class);
                    String Name = childSnapshot.child("username").getValue(String.class);

                    if(ID.equals(Email)){
                        UserKey = childSnapshot.getKey();
                    }


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });









        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ArrayList<Availability> availabilities = new ArrayList<Availability>();

                ABase = FirebaseDatabase.getInstance().getReference("ServiceProvider").child(UserKey).child("Availabilities");

                Integer mondaystart = MondayAvailaibiltiy.getStarthour();

                Integer mondayend = MondayAvailaibiltiy.getEndhour();



                if ( mondaystart != 0 && mondayend != 0){

                    MondayAvailaibiltiy = new Availability("Monday", mondaystart,  00, mondayend,  00);
                    ABase.push().setValue(MondayAvailaibiltiy);
                    //finish();
                }

                Integer tuesdaystart = TuesdayAvailaibiltiy.getStarthour();

                Integer tuesdayend = TuesdayAvailaibiltiy.getEndhour();

                if ( tuesdaystart != 0 && tuesdayend != 0){
                    TuesdayAvailaibiltiy = new Availability("Tuesday", tuesdaystart,  00, tuesdayend,  00);
                    ABase.push().setValue(TuesdayAvailaibiltiy);
                }

                Integer wednesdaystart = WednesdayAvailaibiltiy.getStarthour();

                Integer wednesdayend = WednesdayAvailaibiltiy.getEndhour();

                if (wednesdaystart != 0 && wednesdayend != 0){
                    WednesdayAvailaibiltiy= new Availability("Wednesday", wednesdaystart,  00, wednesdayend,  00);
                    ABase.push().setValue(WednesdayAvailaibiltiy);
                }


                Integer thursdaystart = ThursdayAvailaibiltiy.getStarthour();

                Integer thursdayend = ThursdayAvailaibiltiy.getEndhour();

                if (thursdaystart != 0 && thursdayend!= 0){
                    ThursdayAvailaibiltiy= new Availability("Thursday", thursdaystart,  00, thursdayend,  00);
                    ABase.push().setValue(ThursdayAvailaibiltiy);
                }

                Integer fridaystart = FridayAvailaibiltiy.getStarthour();

                Integer fridayend = FridayAvailaibiltiy.getEndhour();

                if (fridaystart != 0 && fridayend != 0){

                    FridayAvailaibiltiy= new Availability("Friday", fridaystart, 00, fridayend,  00);
                    ABase.push().setValue(FridayAvailaibiltiy);
                }

                Integer saturdaystart = SatursdayAvailaibiltiy.getStarthour();

                Integer saturdayend = SatursdayAvailaibiltiy.getEndhour();

                if (saturdaystart != 0 && saturdayend != 0){
                    SatursdayAvailaibiltiy= new Availability("Saturday", saturdaystart, 00, saturdayend,  00);
                    ABase.push().setValue(SatursdayAvailaibiltiy);
                }

                Integer sundaystart = SundayAvailaibiltiy.getStarthour();

                Integer sundayend = SundayAvailaibiltiy.getEndhour();

                if (sundaystart != 0 && sundayend != 0){
                    SundayAvailaibiltiy= new Availability("Sunday", sundaystart, 00, sundayend,  00);
                    ABase.push().setValue(SundayAvailaibiltiy);
                }


                Toast.makeText(AvailabilityManager.this, "Availabilities Added", Toast.LENGTH_SHORT).show();
                finish();


                /*dB = FirebaseDatabase.getInstance().getReference("ServiceProvider");
                dB.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot childSnapshot: dataSnapshot.getChildren()){
                            String currentUser = childSnapshot.child("email").getValue(String.class);
                            String currentKey = childSnapshot.getKey();
                            if (currentUser.equals(ID)){
                                availabilityDB = FirebaseDatabase.getInstance().getReference("ServiceProvider").child(currentKey).child("availabilities");
                                availabilityDB.push().setValue(availabilities);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                */
                //startActivity(new Intent(AvailabilityManager.this, ServiceProviderProfile.class));
                //Toast.makeText(AvailabilityManager.this, "Your availabilities have been set.", Toast.LENGTH_SHORT).show();
            }
        });


        TextView mondayStartTime = findViewById(R.id.MondayStartTime);
        TextView mondayendTime = findViewById(R.id.MondayEndTime);
        TextView TuedayStartTime = findViewById(R.id.TuesdayStartTime);
        TextView Tuesdayendtime = findViewById(R.id.TuesdayEndTime);
        TextView wednesdayStartTime = findViewById(R.id.WednesdayStartTime);
        TextView wednesdayendtime = findViewById(R.id.WednesdayEndTime);
        TextView thursdaystarttime = findViewById(R.id.ThursdayStartTime);
        TextView ThursdayendTime = findViewById(R.id.ThursdayEndTime);
        TextView FridayStarttime = findViewById(R.id.FridayStartTime);
        TextView Fridayendtime = findViewById(R.id.FridayEndTime);
        TextView SaturdayStarttime = findViewById(R.id.SaturdayStartTime);
        TextView Saturdayendtime = findViewById(R.id.SaturdayEndTime);
        TextView sundaystarttime = findViewById(R.id.SundayStartTime);
        TextView sundayEndtime = findViewById(R.id.SundayEndTIme);


        mondayStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day="MondayStart";
                DialogFragment MondayStarttime = new TimePickerFragment();
                MondayStarttime.show(getSupportFragmentManager(),"timepicker");

            }
        });

        mondayendTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day="MondayEnd";
                DialogFragment MondayEndtime = new TimePickerFragment();
                MondayEndtime.show(getSupportFragmentManager(),"timepicker");

            }
        });
        TuedayStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day="TuesdayStart";
                DialogFragment TuesdayStartTime = new TimePickerFragment();
                TuesdayStartTime.show(getSupportFragmentManager(),"timePicker");

            }
        });

        Tuesdayendtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day="TuesdayEnd";
                DialogFragment TuesdayEndtime = new TimePickerFragment();
                TuesdayEndtime.show(getSupportFragmentManager(),"timepicker");
            }
        });

        wednesdayStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day="WednesdayStart";
                DialogFragment WednesdayStartTime = new TimePickerFragment();
                WednesdayStartTime.show(getSupportFragmentManager(),"timepicker");

            }
        });

        wednesdayendtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day="WednesdayEnd";
                DialogFragment WednesdayEndTime = new TimePickerFragment();
                WednesdayEndTime.show(getSupportFragmentManager(),"timepicker");
            }
        });

        thursdaystarttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day="ThursdayStart";
                DialogFragment ThursdayStartTime = new TimePickerFragment();
                ThursdayStartTime.show(getSupportFragmentManager(),"timepicker");

            }
        });

        ThursdayendTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day="ThursdayEnd";
                DialogFragment ThursdayEndTime = new TimePickerFragment();
                ThursdayEndTime.show(getSupportFragmentManager(),"timepicker");
            }
        });

        FridayStarttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day="FridayStart";
                DialogFragment FridayStartTime = new TimePickerFragment();
                FridayStartTime.show(getSupportFragmentManager(),"timepicker");
            }
        });

        Fridayendtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day="FridayEnd";
                DialogFragment FridayEndTime = new TimePickerFragment();
                FridayEndTime.show(getSupportFragmentManager(),"timepicker");

            }
        });

        SaturdayStarttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day="SaturdayStart";
                DialogFragment SaturdayStartTime = new TimePickerFragment();
                SaturdayStartTime.show(getSupportFragmentManager(),"timepicker");
            }
        });

        Saturdayendtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day="SaturdayEnd";
                DialogFragment SaturdayEndtime = new TimePickerFragment();
                SaturdayEndtime.show(getSupportFragmentManager(),"timepicker");
            }
        });

        sundaystarttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day="SundayStart";
                DialogFragment SundayStartTime = new TimePickerFragment();
                SundayStartTime.show(getSupportFragmentManager(),"timepicker");

            }
        });

        sundayEndtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day="SundayEnd";
                DialogFragment SundayEndtime = new TimePickerFragment();
                SundayEndtime.show(getSupportFragmentManager(),"timepicker");

            }


        });
    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if (day == "SundayStart") {
            TextView sundayStartTime = (TextView)findViewById(R.id.SundayStartTime);
            sundayStartTime.setText(hourOfDay+":"+ minute);
            SundayAvailaibiltiy.setStarthour(hourOfDay);
            SundayAvailaibiltiy.setStartmin(minute);


        }
        if (day == "SundayEnd") {
            TextView sundayEndTime = (TextView)findViewById(R.id.SundayEndTIme    );
            sundayEndTime.setText(hourOfDay+":"+ minute);
            MondayAvailaibiltiy.setEndhour(hourOfDay);
            MondayAvailaibiltiy.setEndmin(minute);

        }
        if (day == "MondayStart") {
            TextView mondayStartTime = (TextView)findViewById(R.id.MondayStartTime);
            mondayStartTime.setText(hourOfDay+":"+ minute);
            MondayAvailaibiltiy.setStarthour(hourOfDay);
            MondayAvailaibiltiy.setStartmin(minute);

        }
        if (day == "MondayEnd") {
            TextView mondayendTime = (TextView)findViewById(R.id.MondayEndTime);
            mondayendTime.setText(hourOfDay+":"+ minute);
            MondayAvailaibiltiy.setEndhour(hourOfDay);
            MondayAvailaibiltiy.setEndmin(minute);

        }
        if (day == "TuesdayStart") {
            TextView TuesdayStartTime = (TextView)findViewById(R.id.TuesdayStartTime);
            TuesdayStartTime.setText(hourOfDay+":"+ minute);
            TuesdayAvailaibiltiy.setStarthour(hourOfDay);
            TuesdayAvailaibiltiy.setStartmin(minute);
        }
        if (day == "TuesdayEnd") {
            TextView Tuesdayendtime = (TextView)findViewById(R.id.TuesdayEndTime);
            Tuesdayendtime.setText(hourOfDay+":"+ minute);
            TuesdayAvailaibiltiy.setEndhour(hourOfDay);
            TuesdayAvailaibiltiy.setEndmin(minute);
        }
        if (day == "WednesdayStart") {
            TextView wednesdayStartTime = (TextView)findViewById(R.id.WednesdayStartTime);
            wednesdayStartTime.setText(hourOfDay+":"+ minute);
            WednesdayAvailaibiltiy.setStarthour(hourOfDay);
            WednesdayAvailaibiltiy.setStartmin(minute);
        }
        if (day == "WednesdayEnd") {

            TextView wednesdayendtime = (TextView)findViewById(R.id.WednesdayEndTime);
            wednesdayendtime.setText(hourOfDay+":"+ minute);
            WednesdayAvailaibiltiy.setEndhour(hourOfDay);
            WednesdayAvailaibiltiy.setEndmin(minute);

        }
        if (day == "ThursdayStart") {


            TextView thursdaystarttime = (TextView)findViewById(R.id.ThursdayStartTime);
            thursdaystarttime.setText(hourOfDay+":"+ minute);
            ThursdayAvailaibiltiy.setStarthour(hourOfDay);
            ThursdayAvailaibiltiy.setStartmin(minute);
        }
        if (day == "ThursdayEnd") {
            TextView Thursdayendtime = (TextView)findViewById(R.id.ThursdayEndTime);
            Thursdayendtime.setText(hourOfDay+":"+ minute);
            ThursdayAvailaibiltiy.setEndhour(hourOfDay);
            ThursdayAvailaibiltiy.setEndmin(minute);
        }
        if (day == "FridayStart") {
            TextView FridayStarttime = (TextView)findViewById(R.id.FridayStartTime);
            FridayStarttime.setText(hourOfDay+":"+ minute);
            FridayAvailaibiltiy.setStarthour(hourOfDay);
            FridayAvailaibiltiy.setStartmin(minute);
        }
        if (day == "FridayEnd") {

            TextView Fridayendtime = (TextView)findViewById(R.id.FridayEndTime);
            Fridayendtime.setText(hourOfDay+":"+ minute);
            FridayAvailaibiltiy.setEndhour(hourOfDay);
            FridayAvailaibiltiy.setEndmin(minute);

        }
        if (day == "SaturdayStart") {
            TextView SaturdayStarttime = (TextView)findViewById(R.id.SaturdayStartTime);
            SaturdayStarttime.setText(hourOfDay+":"+ minute);
            SatursdayAvailaibiltiy.setStarthour(hourOfDay);
            SatursdayAvailaibiltiy.setStartmin(minute);

        }
        if (day == "SaturdayEnd") {
            TextView Saturdayendtime = (TextView)findViewById(R.id.SaturdayEndTime);
            Saturdayendtime.setText(hourOfDay+":"+ minute);
            SatursdayAvailaibiltiy.setEndhour(hourOfDay);
            SatursdayAvailaibiltiy.setEndmin(minute);
        }


    }

}
