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

public class SearchByType extends AppCompatActivity implements AdapterView.OnItemClickListener {
    DatabaseReference SPBase,SubServiceBase;
    ListView list;
    public static ArrayList<String> List;
    public static ArrayList<String> List2;
    public static ArrayList<String> providersMatchType,emails;
    public String match,name;
    EditText serviceType;
    String type,compnayName,Email,userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_type);

        Email = getIntent().getExtras().getString("userEmail");
        userName = getIntent().getExtras().getString("userName");

        serviceType = (EditText) findViewById(R.id.SearchInput1);
        type = serviceType.getText().toString().trim();
        SPBase = FirebaseDatabase.getInstance().getReference("ServiceProvider");


        List = new ArrayList<String>();
        emails = new ArrayList<String>();
        providersMatchType = new ArrayList<String>();

        list = (ListView) findViewById(R.id.SearchList1);
        Button search = findViewById(R.id.SearchButton1);
        final ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, providersMatchType);
        list.setAdapter(myArrayAdapter);

        search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                type = serviceType.getText().toString().trim();
                //ArrayList<String> providersMatchRating;
                //providersMatchRating = new ArrayList<String>();
                providersMatchType.clear();
                emails.clear();
                myArrayAdapter.notifyDataSetChanged();


                if(validate()){

                    SPBase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                                String currentKey = childSnapshot.getKey();
                                final String name = childSnapshot.child("companyName").getValue(String.class);
                                final String currentEmail = childSnapshot.child("email").getValue(String.class);
                                SubServiceBase = FirebaseDatabase.getInstance().getReference("ServiceProvider").child(currentKey).child("SubServices");
                                SubServiceBase.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot){
                                        for (DataSnapshot subchildSnapshot : dataSnapshot.getChildren()) {
                                            String subService = subchildSnapshot.getValue(String.class);
                                            if (type.equals(subService)) {
                                                providersMatchType.add(name);
                                                emails.add(currentEmail);
                                                myArrayAdapter.notifyDataSetChanged();

                                            }


                                        }


                                    }


                                    @Override
                                    public void onCancelled(DatabaseError databaseError){

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

        });

        list.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        final String serviceProvider = emails.get(position);
        Intent i = new Intent(SearchByType.this, bookAppointment.class);
        String str1 = serviceProvider;
        i.putExtra("userEmail",Email);
        i.putExtra("userName",userName);
        i.putExtra("SP", str1);
        startActivity(i);
        finish();
    }



    public boolean validate(){
        boolean valid = true;

        if(type.isEmpty() ){
            serviceType.setError("Please enter a Valid type of service");
            type=null;
            valid=false;
        }

        return valid;
    }

}
