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

public class LoginPage extends AppCompatActivity {
    EditText Eemail, Epassword;
    Button Login;
    String Email, passWord;
    String UserType, UserID, UserName;
    String[] User;
    Boolean userExists;
    String userType,userName,userPassword;

    int index = 0;
    private ArrayList<String> emaillist;
    private DatabaseReference firebase, adminBase, HOBase, SPBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        Login = (Button) findViewById(R.id.LoginButton);
        Eemail = ((EditText) findViewById(R.id.LoginEmailInput));
        Epassword = ((EditText) findViewById(R.id.LoginPasswordInput));
        adminBase = FirebaseDatabase.getInstance().getReference("AdminUser");
        HOBase = FirebaseDatabase.getInstance().getReference("HomeOwner");
        SPBase = FirebaseDatabase.getInstance().getReference("ServiceProvider");
        emaillist = new ArrayList<>();
        User = new String[4];


        Login.setOnClickListener(new View.OnClickListener() {
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
                    String Password = childSnapshot.child("password").getValue(String.class);
                    String Type = childSnapshot.child("type").getValue(String.class);
                    String Name = childSnapshot.child("username").getValue(String.class);


                    emaillist.add(Email);
                    emaillist.add(Password);
                    emaillist.add(Type);
                    emaillist.add(Name);

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
                    String Password = childSnapshot.child("password").getValue(String.class);
                    String Type = childSnapshot.child("type").getValue(String.class);
                    String Name = childSnapshot.child("username").getValue(String.class);


                    emaillist.add(Email);
                    emaillist.add(Password);
                    emaillist.add(Type);
                    emaillist.add(Name);
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
                    String Password = childSnapshot.child("password").getValue(String.class);
                    String Type = childSnapshot.child("type").getValue(String.class);
                    String Name = childSnapshot.child("username").getValue(String.class);


                    emaillist.add(Email);
                    emaillist.add(Password);
                    emaillist.add(Type);
                    emaillist.add(Name);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        System.out.println(emaillist.size());
    }

    public void signUp() {

        System.out.println(emaillist.size());
        userExists = false;
        intialize();



        for(int i = 0 ; i < emaillist.size() ; i++){
            System.out.println(emaillist.get(i));
        }

        for(int i  = 0 ; i < emaillist.size() ; i++){
            if(Email.equals(emaillist.get(i))){
                //Toast.makeText(this,"User Found",Toast.LENGTH_SHORT).show();
                userExists = true;
                userPassword = emaillist.get(i+1);
                userType = emaillist.get(i+2);
                userName = emaillist.get(i+3);
                System.out.println("Now that we found the proper email, the type is : " + userType);
                System.out.println("Now that we found the proper email, the username is : " + userName);

            }
        }


        if(userExists){

            if(!(passWord.equals(userPassword))){
                Toast.makeText(this, "Incorrect Password", Toast.LENGTH_SHORT).show();
            }

            else if (!validate()) {

                Toast.makeText(this, "Sign Up has Failed, Please enter Valid Input", Toast.LENGTH_SHORT).show();

            }else{

                if (userType.equals("Admin")) {
                    signupSuccess();
                } else if (userType.equals("Service Provider")) {
                    signupSuccess1();
                } else {
                    signupSuccess2();
                }

            }
        }else{
            Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
        }

        }


        private void intialize () {
            passWord = Epassword.getText().toString().trim();
            Email = Eemail.getText().toString().trim();

        }


        public boolean validate () {
            boolean valid = true;

        if(Email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            Eemail.setError("Please enter a proper Email Address");
            Email=null;
            valid=false;
        }


            if (passWord.isEmpty() || passWord.length() > 32) {
                Epassword.setError("Please enter your password");
                passWord = null;
                valid = false;
            }

        /*
        if(verifyEmail(Email)){
            if(!verifyPassword(passWord,index) ){
                password.setError("Please enter the correct password");
                passWord=null;
                valid=false;
            }
        }
        */

            return valid;
        }

        public void signupSuccess () {
            Intent i = new Intent(LoginPage.this, AdminProfile.class);
            //String str1 = MainActivity.accounts.get(index).getUsername();
            String str1 = userName;
            i.putExtra("Admin", str1);
            startActivity(i);
            finish();
        }

        public void signupSuccess1 () {
            Intent i = new Intent(LoginPage.this, ServiceProviderProfile.class);
            //String str1 = MainActivity.accounts.get(index).getUsername();
            String str1 = userName;
            i.putExtra("Admin", str1);
            i.putExtra("ID",Email);
            startActivity(i);
            finish();
        }

        public void signupSuccess2 () {
            Intent i = new Intent(LoginPage.this, UserProfile.class);
            //String str1 = MainActivity.accounts.get(index).getUsername();
            String str2 = Email;
            String str1 = userName;
            i.putExtra("Email",Email);
            i.putExtra("Admin", str1);
            startActivity(i);
            finish();
        }

        public boolean verifyEmail (String email){
            if (MainActivity.accounts.isEmpty()) {
                return false;
            }
            for (int i = 0; i < MainActivity.accounts.size(); i++) {
                if (MainActivity.accounts.get(i).getEmail().equals(email)) {
                    index = i;
                    return true;
                }
            }
            return false;
        }

        public boolean verifyPassword (String password,int index){
            if (MainActivity.accounts.get(index).getPassword().equals(password)) {
                return true;
            }
            return false;
        }
        public void openAdminProfile () {
            Intent intent = new Intent(this, AdminWelcomePage.class);
            startActivity(intent);
        }

        public String getType() {
            return MainActivity.accounts.get(index).getType();
        }

    }


