package com.example.noureldinzeina.fixit;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class pickappointmentDay extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private DatabaseReference SPBase,AppointmentBase;
    private String SPKey,day;
    private String AppointmentTimeHour,AppointmentTimeMinute;
    private String serviceToSet;

    private Button serviceButton,bookAppointment;
    private static final int RequestBack = 0;
    private String Email,userName,weekDay,starting,ending;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickappointment_day);

        Email = getIntent().getExtras().getString("userEmail");
        userName = getIntent().getExtras().getString("userName");

        TextView pickDate = (TextView)findViewById(R.id.textView13);
        TextView viewService = (TextView)findViewById(R.id.textView8);
        TextView viewTime = (TextView)findViewById(R.id.textView9);
        TextView viewTitle = (TextView)findViewById(R.id.textView10);

        TextView titleInfo = (TextView)findViewById(R.id.textView11);
        Button timeButton = (Button)findViewById(R.id.timeButton);
        bookAppointment = (Button)findViewById(R.id.Book);
        serviceButton = (Button)findViewById(R.id.serviceButton);

        weekDay = getIntent().getExtras().getString("day");
        SPKey = getIntent().getExtras().getString("key");

        AppointmentBase = FirebaseDatabase.getInstance().getReference("ServiceProvider").child(SPKey).child("Appointments");

        String subTitle = getIntent().getExtras().getString("SP");
        String Title = subTitle + " Services";
        starting = getIntent().getExtras().getString("start");
        ending = getIntent().getExtras().getString("end");

        pickDate.setText(weekDay);
        viewTitle.setText(Title);
        titleInfo.setText(Title + " is available between " + starting +":00 and " + ending +":00 O'Clock on this day, please pick time accordingly.");


        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day="appointmentTime";
                DialogFragment appointmentTime = new TimePickerFragment();
                appointmentTime.show(getSupportFragmentManager(),"timepicker");

            }
        });


        serviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(pickappointmentDay.this,PickService.class);
                i.putExtra("SPKey", SPKey);
                startActivityForResult(i, RequestBack);


                //startActivity(i);

            }
        });


        bookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(serviceToSet != null){

                    if(AppointmentTimeHour == null){
                        Toast.makeText(pickappointmentDay.this,"You havn't chosen a time of day for the service!?", Toast.LENGTH_SHORT).show();

                    }else{

                            if(Integer.parseInt(AppointmentTimeHour) < Integer.parseInt(starting)){
                                Toast.makeText(pickappointmentDay.this,"That's too early for an appointment!", Toast.LENGTH_SHORT).show();


                            }else if(Integer.parseInt(AppointmentTimeHour) > Integer.parseInt(ending)) {
                                Toast.makeText(pickappointmentDay.this,"That's too late for an appointment!", Toast.LENGTH_SHORT).show();
                            }else{
                                String addAppointment = "Appointment with:  " + Email + " on " + weekDay + " at " + AppointmentTimeHour + ":" + AppointmentTimeMinute + " O'Clock for service " + serviceToSet;
                                AppointmentBase.push().setValue(addAppointment);
                                Toast.makeText(pickappointmentDay.this, "Appointment succesfully added!", Toast.LENGTH_SHORT).show();
                                finish();

                            }

                    }


                }else{
                    Toast.makeText(pickappointmentDay.this,"Service has not been set yet!", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RequestBack) {
            if (resultCode == RESULT_OK) {

                String returnString = data.getStringExtra("ServiceChosen");
                //Toast.makeText(pickappointmentDay.this,returnString, Toast.LENGTH_SHORT).show();
                serviceToSet = returnString;
                serviceButton.setText(serviceToSet);


            }

        }

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        if(day == "appointmentTime"){
            Button timeButton = (Button)findViewById(R.id.timeButton);
            timeButton.setText(hourOfDay + ":" + minute);
            AppointmentTimeHour = String.valueOf(hourOfDay);
            AppointmentTimeMinute = String.valueOf(minute);

        }



    }
}
