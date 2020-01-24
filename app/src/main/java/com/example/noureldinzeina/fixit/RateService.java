package com.example.noureldinzeina.fixit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RateService extends AppCompatActivity {
    Button rate;
    EditText comment,rating, companyName;
    DatabaseReference SPbase, CommentBase;
    String Rate, Comment, CompanyName, key;
    int counter, rating1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_service);

        rate = findViewById(R.id.RateButton);
        rating = findViewById(R.id.RateInputR);
        comment = findViewById(R.id.CommentInput);
        companyName = findViewById(R.id.CompanyInput);

        SPbase = FirebaseDatabase.getInstance().getReference("ServiceProvider");

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intialize();
                if (validate()) {
                    Rate();
                    if( (key!=null)){
                        int newCounter = counter+1;
                        SPbase.child(key).child("ratingCounter").setValue(newCounter);
                        int newRating = (Integer.valueOf(Rate)+rating1)/newCounter;
                        SPbase.child(key).child("rating").setValue(newRating);
                        Comment();
                        openSuccessActivity();}

                }
            }
        });


    }


    public void openSuccessActivity() {
        Intent intent = new Intent(RateService.this,RateSuccessful.class);
        int newCounter = counter+1;
        int newRating = (Integer.valueOf(Rate)+rating1)/newCounter;
        intent.putExtra("New Rating",String.valueOf(newRating));
        startActivity(intent);
    }

    public boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }


    public boolean validate(){
        boolean valid = true;

        if(Rate.isEmpty() || !isNumeric(Rate) || !ValidNumber() ){
            rating.setError("Please enter a Number (1 to 5) inclusive");
            Rate=null;
            valid=false;
        }

        if(Comment.isEmpty()){
            comment.setError("Please enter a Valid Comment");
            valid=false;
        }

        if(CompanyName.isEmpty() ){
            companyName.setError("Please enter a Valid Address");
            valid=false;
        }



        return valid;
    }

    public boolean ValidNumber(){
        if (Rate.equals("1")){
            return true;
        }

        if (Rate.equals("2")){
            return true;
        }

        if (Rate.equals("3")){
            return true;
        }

        if (Rate.equals("4")){
            return true;
        }

        if (Rate.equals("5")){
            return true;
        }

        return false;
    }

    public void Rate(){
        SPbase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot childSnapshot : dataSnapshot.getChildren()){
                    String CurrentID = childSnapshot.child("companyName").getValue(String.class);
                    counter = childSnapshot.child("ratingCounter").getValue(Integer.class);
                    rating1 = childSnapshot.child("rating").getValue(Integer.class);
                    if (CurrentID.equals(CompanyName)){
                        key = childSnapshot.getKey();

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void Comment(){
        CommentBase = FirebaseDatabase.getInstance().getReference().child("ServiceProvider").child(key).child("Comments");
        CommentBase.push().setValue(Comment);
    }

    private void intialize() {
        Rate = rating.getText().toString().trim();
        CompanyName = companyName.getText().toString().trim();
        Comment = comment.getText().toString().trim();

    }

}
