package com.example.noureldinzeina.fixit;

import android.content.Intent;
import android.provider.ContactsContract;
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

public class SearchByRating extends AppCompatActivity implements AdapterView.OnItemClickListener {
    DatabaseReference SPBase;
    ListView list;
    public static ArrayList<String> List;
    public static ArrayList<String> List2;
    public static ArrayList<String> providersMatchRating,emails;
    public String match,name;
    private int currentID;
    EditText ratingInput;
    public String rating;
    private String Email,userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_rating);

        Email = getIntent().getExtras().getString("userEmail");
        userName = getIntent().getExtras().getString("userName");

        ratingInput = (EditText) findViewById(R.id.SearchInput);
        rating = ratingInput.getText().toString().trim();
        SPBase = FirebaseDatabase.getInstance().getReference("ServiceProvider");

        List = new ArrayList<String>();
        List2 = new ArrayList<String>();
        providersMatchRating = new ArrayList<String>();
        emails  = new ArrayList<String>();


        list = (ListView) findViewById(R.id.SearchList);
        Button search = findViewById(R.id.SearchButton);
        final ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, providersMatchRating);
        list.setAdapter(myArrayAdapter);


        search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                rating = ratingInput.getText().toString().trim();
                //ArrayList<String> providersMatchRating;
                //providersMatchRating = new ArrayList<String>();
                providersMatchRating.clear();
                emails.clear();


                if(validate()){

                    SPBase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot childSnapshot : dataSnapshot.getChildren()){
                                Long tmpRating = childSnapshot.child("rating").getValue(Long.class);
                                String currentEmail = childSnapshot.child("email").getValue(String.class);
                                String currentRating = String.valueOf(tmpRating);
                                String name = childSnapshot.child("companyName").getValue(String.class);
                                if(rating.equals(currentRating)){

                                    providersMatchRating.add(name);
                                    emails.add(currentEmail);
                                    myArrayAdapter.notifyDataSetChanged();


                                }



                            }

                            if(providersMatchRating.isEmpty()){
                                Toast.makeText(SearchByRating.this,"No Service Providers exist with this rating",Toast.LENGTH_SHORT).show();

                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    list.setAdapter(myArrayAdapter);
                    myArrayAdapter.notifyDataSetChanged();

                }

            }

        });

        list.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        final String serviceProvider = emails.get(position);
        Intent i = new Intent(SearchByRating.this, bookAppointment.class);
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


    public boolean validate(){
        boolean valid = true;

        if(rating.isEmpty() || !isNumeric(rating) || !ValidNumber()) {
            ratingInput.setError("Please enter a number between 1 and 5 inclusive");
            rating=null;
            valid=false;
        }

        return valid;
    }

    public boolean ValidNumber(){
        if(rating.equals(("0"))){
            return true;
        }

        if (rating.equals("1")){
            return true;
        }

        if (rating.equals("2")){
            return true;
        }

        if (rating.equals("3")){
            return true;
        }

        if (rating.equals("4")){
            return true;
        }

        if (rating.equals("5")){
            return true;
        }

        return false;
    }


    public void toast(){
        Toast.makeText(SearchByRating.this,"No Service Providers Match your search", Toast.LENGTH_SHORT).show();
    }


}
