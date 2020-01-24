package com.example.noureldinzeina.fixit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ServiceProviderCreateProfile extends AppCompatActivity {
    String CompanyName, Address, Description, PhoneNumber,name;
    Boolean Licensed;
    EditText companyName, address, description,phoneNumber;
    DatabaseReference firebase = FirebaseDatabase.getInstance().getReference("ServiceProvider");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_create_profile);

        Button finishingServiceProviderProfile = (Button) findViewById(R.id.finishingSProfile);
        companyName= findViewById(R.id.CompanyNameInput);
        address = findViewById(R.id.AddressInput);
        description= findViewById(R.id.DescriptionInput);
        phoneNumber= findViewById(R.id.PhoneNUmberInput);
        final CheckBox licensed = findViewById(R.id.switch1);
        licensed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence string = new StringBuffer("License is true");
                if (licensed.isChecked()) {
                    MainActivity.Provider.get(0).setLicensed(true);
                    Toast.makeText(ServiceProviderCreateProfile.this, "License is true", Toast.LENGTH_SHORT).show();
                }

                else{
                    MainActivity.Provider.get(0).setLicensed(false);
                    Toast.makeText(ServiceProviderCreateProfile.this,"License is false",Toast.LENGTH_SHORT).show();
                }

            }
        });

        finishingServiceProviderProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                signUp();
            }
        });


    }


    public void signUp() {
        intialize();
        if(!validate()){
            Toast.makeText(this,"Sign Up has Failed, Please enter Valid Input",Toast.LENGTH_SHORT).show();
        }
        else{
            signupSuccess();
        }
    }

    private void intialize() {
        CompanyName = companyName.getText().toString().trim();
        PhoneNumber = phoneNumber.getText().toString().trim();
        Description = description.getText().toString().trim();
        Address = address.getText().toString().trim();
        name=MainActivity.Provider.get(0).getFName();
    }

    public boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }


    public boolean validate(){
        boolean valid = true;
        if(CompanyName.isEmpty() || companyName.length()>32 ){
            companyName.setError("Please enter a Valid Company Name");
            CompanyName=null;
            valid=false;
        }

        if(PhoneNumber.isEmpty() || !isNumeric(PhoneNumber)){
            phoneNumber.setError("Please enter a Phone Number (Just Numbers)");
            PhoneNumber=null;
            valid=false;
        }

        if(Description.isEmpty()){
            description.setError("Please enter a Valid Description");
            valid=false;
        }

        if(Address.isEmpty() ){
            address.setError("Please enter a Valid Address");
            valid=false;
        }



        return valid;
    }

    public void signupSuccess(){
        MainActivity.Provider.get(0).setAddress(Address);
        MainActivity.Provider.get(0).setCompanyNameName(CompanyName);
        MainActivity.Provider.get(0).setDescription(Description);
        MainActivity.Provider.get(0).setPhoneNumber(PhoneNumber);

        firebase.push().setValue(MainActivity.Provider.get(0));
        //if (licensed.isChecked()) Licensed = true;
        //else Licensed = false;
        //MainActivity.Provider.get(0).setLicensed(Licensed);
        MainActivity.Provider.remove(0);
        openWelcomePage();
    }

    public void openWelcomePage(){
        Intent i = new Intent(ServiceProviderCreateProfile.this, WelcomePage.class);
        String str2 = "Welcome "+name+" your Account Has been successfully Created, you are logged in as Service Provider";
        i.putExtra("Value",str2);
        startActivity(i);
        finish();

    }
}
