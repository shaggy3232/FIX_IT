package com.example.noureldinzeina.fixit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CreateHomeOwner extends AppCompatActivity {
    public Button SignUP1;
    EditText Name1;
    String str1, username, firstName, lastName, password, email;
    EditText Eusername, EfirstName, ElastName, Epassword, Eemail;
    HomeOwner homeOwner;
    Boolean isUnique;
    int currentAccountNumber;
    private ArrayList<String> emaillist;
    private ArrayList<Integer> accountNumberList;
    private DatabaseReference firebase,adminBase,HOBase,SPBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_home_owner);

        emaillist = new ArrayList<>();
        accountNumberList = new ArrayList<>();
        isUnique = true;

        SignUP1  = (Button) findViewById(R.id.SignUpButton1);
        Name1 = findViewById(R.id.FirstNameInput1);

        firebase = FirebaseDatabase.getInstance().getReference("HomeOwner");

        HOBase = FirebaseDatabase.getInstance().getReference("HomeOwner");
        adminBase = FirebaseDatabase.getInstance().getReference("AdminUser");
        SPBase = FirebaseDatabase.getInstance().getReference("ServiceProvider");


        Eusername = ((EditText) findViewById(R.id.UsernameInput1));
        EfirstName = ((EditText) findViewById(R.id.FirstNameInput1));
        ElastName = ((EditText) findViewById(R.id.LastNameInput1));
        Epassword = ((EditText) findViewById(R.id.PasswordInput1));
        Eemail = ((EditText) findViewById(R.id.EmailInput1));

        SignUP1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signUp();
            }
        });

        adminBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot childSnapshot : dataSnapshot.getChildren()){

                    String Email = childSnapshot.child("email").getValue(String.class);

                    emaillist.add(Email);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        HOBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot childSnapshot : dataSnapshot.getChildren()){

                    String Email = childSnapshot.child("email").getValue(String.class);
                    String AccountNumber = childSnapshot.child("accNumber").getValue(String.class);

                    int accNumber = Integer.parseInt(AccountNumber);
                    emaillist.add(Email);
                    accountNumberList.add(accNumber);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        SPBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot childSnapshot : dataSnapshot.getChildren()){

                    String Email = childSnapshot.child("email").getValue(String.class);
                    String AccountNumber = childSnapshot.child("accNumber").getValue(String.class);

                    int accNumber = Integer.parseInt(AccountNumber);

                    emaillist.add(Email);
                    accountNumberList.add(accNumber);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void signUp() {

        isUnique = true;
        intialize();


        for (int i = 0; i < emaillist.size(); i++) {
            if (email.equals(emaillist.get(i))) {
                Toast.makeText(this, "This Email has already been used!", Toast.LENGTH_SHORT).show();
                isUnique = false;

            }

        }


        if (isUnique) {
            if (!validate()) {
                Toast.makeText(this, "Sign Up has Failed, Please enter Valid Input", Toast.LENGTH_SHORT).show();
            } else {

                signupSuccess();
            }
        }
    }



    private void intialize() {
        username = Eusername.getText().toString().trim();
        firstName = EfirstName.getText().toString().trim();
        lastName = ElastName.getText().toString().trim();
        password = Epassword.getText().toString().trim();
        email = Eemail.getText().toString().trim();
    }

    public boolean validate(){
        boolean valid = true;
        if(username.isEmpty() || username.length()>32 || !verifyUsername(username)){
            Eusername.setError("Please enter a Valid Username");
            username=null;
            valid=false;
        }

        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches() || !verifyEmail(email)){
            Eemail.setError("Please enter a Valid Email Address");
            email=null;
            valid=false;
        }

        if(password.isEmpty()){
            Epassword.setError("Please enter a Valid Password");
            valid=false;
        }

        if(lastName.isEmpty() || lastName.length()>32){
            ElastName.setError("Please enter a Valid Last Name");
            valid=false;
        }

        if(firstName.isEmpty() || firstName.length()>32){
            EfirstName.setError("Please enter a Valid First Name");
            valid=false;
        }


        return valid;
    }

    public void signupSuccess(){

        currentAccountNumber = accountNumberList.size() + 1;
        homeOwner = new HomeOwner(currentAccountNumber, firstName,lastName,username,password,email);
        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(username);
        System.out.println(password);
        System.out.println(email);
        System.out.println("Current amount of accounts: ");
        System.out.println(currentAccountNumber);



        firebase.push().setValue(homeOwner);
        MainActivity.accounts.add(homeOwner);

        Intent i = new Intent(CreateHomeOwner.this, WelcomePage.class);
        str1 = "Welcome "+Name1.getText().toString()+" your Account Has been successfully Created, you are logged in as a Home Owner";
        i.putExtra("Value",str1);

        startActivity(i);
        finish();
    }

    public boolean verifyEmail(String email) {
        if (MainActivity.accounts.isEmpty()) {
            return true;
        }
        for (int i = 0; i < MainActivity.accounts.size(); i++) {
            if (MainActivity.accounts.get(i).getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    public boolean verifyUsername(String username) {
        if (MainActivity.accounts.isEmpty()) {
            return true;
        }
        for (int i = 0; i < MainActivity.accounts.size(); i++) {
            if (MainActivity.accounts.get(i).getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

}
