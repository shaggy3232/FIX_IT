package com.example.noureldinzeina.fixit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
import java.util.List;

public class AddDeleteServiceList extends AppCompatActivity  {
    EditText ServiceName,HourlyRate;
    String service, rate;
    ListView list;
    boolean isUnique;
    private DatabaseReference serviceBase;
    private ArrayList<String> servicelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_delete_service_list);

        servicelist = new ArrayList<>();
        serviceBase = FirebaseDatabase.getInstance().getReference("Services");
        ServiceName = findViewById(R.id.ServiceNameInput);
        HourlyRate = findViewById(R.id.HourlyRateInput);
        Button button = findViewById(R.id.AddServiceButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean b = signUp();
                if(b){
                openServiceCreated();}
            }
        });


        serviceBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot childSnapshot : dataSnapshot.getChildren()){

                    String serviceName = childSnapshot.child("name").getValue(String.class);

                    servicelist.add(serviceName);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void openServiceCreated() {
        Intent intent = new Intent(AddDeleteServiceList.this,ServiceCreated.class);
        startActivity(intent);
    }

    public boolean signUp() {
        boolean go;
        intialize();
        isUnique = true;

        for(int i = 0 ; i < servicelist.size() ; i++){
            if(service.equals(servicelist.get(i))){
                isUnique = false;
            }
        }


        if(isUnique){
            if(!validate()){
                Toast.makeText(this,"Adding Service has Failed, Please enter Valid Input",Toast.LENGTH_SHORT).show();
                go=false;
            }
            else{
                signupSuccess();
                go=true;
            }

        }else{
            Toast.makeText(this,"This service already exists!",Toast.LENGTH_SHORT).show();
            go = false;
        }

        return go;
    }

    public boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }

    private void intialize() {
        service = ServiceName.getText().toString().trim();
        rate = HourlyRate.getText().toString().trim();
    }

    public boolean validate(){
        boolean valid = true;
        if(service.isEmpty() || service.length()>32){
            ServiceName.setError("Please enter valid Name");
            service=null;
            valid=false;
        }

        if(rate.isEmpty() || !isNumeric(rate)){
            HourlyRate.setError("Please enter a valid rate (Number)");
            rate=null;
            valid=false;
        }

        return valid;
    }

    public void signupSuccess(){

        Service aService = new Service(service,rate);
        serviceBase.push().setValue(aService);
        MainActivity.services.add(aService);
    }

    public boolean verifyName(String name) {
        if (MainActivity.services.isEmpty()) {
            return false;
        }
        for (int i = 0; i < MainActivity.services.size(); i++) {
            if (MainActivity.services.get(i).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

}
