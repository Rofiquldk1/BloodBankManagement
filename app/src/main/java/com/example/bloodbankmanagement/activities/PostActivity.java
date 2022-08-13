package com.example.bloodbankmanagement.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bloodbankmanagement.R;
import com.example.bloodbankmanagement.viewmodels.UserData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class PostActivity extends AppCompatActivity {

    ProgressDialog pd;

    EditText text_contact, text_hospital, text_reason;
    Spinner spinner_district, spinner_blood_grp, spinner_amount_of_blood_grp;
    Button btnpost;

    FirebaseDatabase fdb;
    DatabaseReference db_ref;
    FirebaseAuth mAuth;

    Calendar cal;
    String uid;
    String Time, Date;
    int sec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        pd = new ProgressDialog(this);
        pd.setMessage("Loading...");
        pd.setCancelable(true);
        pd.setCanceledOnTouchOutside(false);

        //getSupportActionBar().setTitle("Post Blood Request");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        text_contact = findViewById(R.id.contact);
        text_hospital = findViewById(R.id.namOfHospital);
        text_reason = findViewById(R.id.reasonOfBlood);

        spinner_district = findViewById(R.id.SpinnerDistrict);
        spinner_blood_grp = findViewById(R.id.SpinnerBlood);
        spinner_amount_of_blood_grp = findViewById(R.id.SpinnerBloodAmount);

        btnpost = findViewById(R.id.postbtn);

        generateDateAndTime();


        FirebaseUser cur_user = mAuth.getInstance().getCurrentUser();

        if(cur_user == null)
        {
            startActivity(new Intent(PostActivity.this, LoginActivity.class));
        } else {
            uid = cur_user.getUid();
        }

        mAuth = FirebaseAuth.getInstance();
        fdb = FirebaseDatabase.getInstance();
        db_ref = fdb.getReference("posts");

        try {
            btnpost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pd.show();
                    final Query findname = fdb.getReference("users").child(uid);

                    if(text_hospital.getText().length() == 0)
                    {
                        Toast.makeText(getApplicationContext(), "Enter your hospital name!",
                                Toast.LENGTH_LONG).show();
                    }
                    else if(text_contact.getText().length() == 0)
                    {
                        Toast.makeText(getApplicationContext(), "Enter your contact number!",
                                Toast.LENGTH_LONG).show();
                    }
                    else if(text_reason.getText().length() == 0)
                    {
                        Toast.makeText(getApplicationContext(), "Enter why do you need blood!",
                                Toast.LENGTH_LONG).show();
                    }
                    else {
                        findname.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String pid = getAlphaNumericString(Calendar.getInstance().getTimeInMillis()+"");
                                String timeInMilis = Calendar.getInstance().getTimeInMillis()+"";
                                if (dataSnapshot.exists()) {
                                    db_ref.child(pid).child("Uid").setValue(uid);
                                    db_ref.child(pid).child("Name").setValue(dataSnapshot.getValue(UserData.class).getName());
                                    db_ref.child(pid).child("HospitalName").setValue(text_hospital.getText().toString());
                                    db_ref.child(pid).child("Address").setValue("");
                                    db_ref.child(pid).child("Message").setValue(text_reason.getText().toString());
                                    db_ref.child(pid).child("Contact").setValue("+88"+text_contact.getText().toString());
                                    db_ref.child(pid).child("Amount").setValue(spinner_amount_of_blood_grp.getSelectedItem().toString());
                                    db_ref.child(pid).child("Division").setValue(spinner_district.getSelectedItem().toString());
                                    db_ref.child(pid).child("BloodGroup").setValue(spinner_blood_grp.getSelectedItem().toString());
                                    db_ref.child(pid).child("Time").setValue(Time);
                                    db_ref.child(pid).child("Date").setValue(Date);
                                    db_ref.child(pid).child("DateTime").setValue(timeInMilis+"");
                                    Toast.makeText(PostActivity.this, "Your post has been created successfully",
                                            Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(PostActivity.this, Dashboard.class));

                                } else {
                                    Toast.makeText(getApplicationContext(), "Database error occured.",
                                            Toast.LENGTH_LONG).show();
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Log.d("User", databaseError.getMessage());

                            }
                        });
                    }
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        pd.dismiss();

    }

    private void generateDateAndTime() {
        cal = Calendar.getInstance();

        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        int hour = cal.get(Calendar.HOUR);
        int min = cal.get(Calendar.MINUTE);
        sec = cal.get(Calendar.SECOND);
        month+=1;
        Time = "";
        Date = "";
        String ampm="AM";

        if(cal.get(Calendar.AM_PM) ==1)
        {
            ampm = "PM";
        }

        if(hour<10)
        {
            Time += "0";
        }
        Time += hour;
        Time +=":";

        if(min<10) {
            Time += "0";
        }

        Time +=min;
        Time +=(" "+ampm);

        Date = day+"/"+month+"/"+year;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private String getAlphaNumericString(String dt)
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(dt);

        for (int i = 0; i < 10; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
}
