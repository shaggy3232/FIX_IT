package com.example.noureldinzeina.fixit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class CreateAdminUser extends AppCompatActivity {
    public Button SignUP;
    EditText Name;
    String str, username, firstName, lastName, password, email;
    EditText Eusername, EfirstName, ElastName, Epassword, Eemail;
    Admin admin;
    Boolean isUnique;
    private ArrayList<String> emaillist;
    private DatabaseReference firebase,adminBase,HOBase,SPBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_admin_user);

        SignUP  = (Button) findViewById(R.id.SignUpButton);
        Name = findViewById(R.id.FirstNameInput);

        isUnique = true;
        emaillist = new ArrayList<>();

        firebase = FirebaseDatabase.getInstance().getReference("AdminUser");
        HOBase = FirebaseDatabase.getInstance().getReference("HomeOwner");
        adminBase = FirebaseDatabase.getInstance().getReference("AdminUser");
        SPBase = FirebaseDatabase.getInstance().getReference("ServiceProvider");

        Eusername = ((EditText) findViewById(R.id.UsernameInput));
        EfirstName = ((EditText) findViewById(R.id.FirstNameInput));
        ElastName = ((EditText) findViewById(R.id.LastNameInput));
        Epassword = ((EditText) findViewById(R.id.PasswordInput));
        Eemail = ((EditText) findViewById(R.id.EmailInput));



        SignUP.setOnClickListener(new View.OnClickListener() {
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

                    System.out.println(Email);
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

                    emaillist.add(Email);

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

                    emaillist.add(Email);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        System.out.println(emaillist.size());


    }

    public void signUp() {
        isUnique = true;
        intialize();


        for(int i  = 0 ; i < emaillist.size() ; i++){
            if(email.equals(emaillist.get(i))){
                Toast.makeText(this,"This Email has already been used!",Toast.LENGTH_SHORT).show();
                isUnique = false;

            }
        }

        if(isUnique) {

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
        if(username.isEmpty() || firstName.length()>32 || !verifyUsername(username)){
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
        admin = new Admin(firstName,lastName,username,password,email);

        firebase.push().setValue(admin);


        MainActivity.accounts.add(admin);
        Intent i = new Intent(CreateAdminUser.this, AdminWelcomePage.class);
        str = "Welcome "+Name.getText().toString()+" your Account Has been successfully Created, you are logged in as an Admin";
        i.putExtra("Value",str);
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
