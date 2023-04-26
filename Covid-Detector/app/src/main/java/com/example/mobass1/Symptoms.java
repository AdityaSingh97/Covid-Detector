package com.example.mobass1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

public class Symptoms extends AppCompatActivity {

    private Button symp;
    private Button subsymp;
    float ratingValue;
    String spinnerValue;
    Spinner mySpin;
    RatingBar rating;
    DatabaseHelper mdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);

        //mdata = GlobalClass.get().getmData();

        symp = (Button) findViewById(R.id.backsymp);
        symp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Symptoms.this , MainActivity.class);
                startActivity(intent);
            }
        });

        mySpin = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Symptoms.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.symptoms));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpin.setAdapter(myAdapter);




        rating = (RatingBar) findViewById(R.id.ratingBar);




        subsymp = (Button) findViewById(R.id.submitsymp);
        subsymp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Here we program the submit button to add the current dropdown and rating value to the db
                //so we take ratingvalue and spinnervalue and send it to a function i will write called addtodb(?)
                //in that function i will have a select/if clause where spinnervalue is checked and i will input the rating.
                Toast.makeText(Symptoms.this, "Submitted", Toast.LENGTH_SHORT).show();
                addtoDB();
            }
        });
    }

    void addtoDB(){
        spinnerValue = mySpin.getSelectedItem().toString();
        ratingValue = rating.getRating();
        boolean insertData;

        if(spinnerValue=="Nausea"){
            insertData = mdata.addData3(ratingValue);

            if(insertData){
                Toast.makeText(Symptoms.this, "Inserted", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(Symptoms.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }

        } else if(spinnerValue=="Headache") {
            insertData = mdata.addData4(ratingValue);
            if(insertData){
                Toast.makeText(Symptoms.this, "Inserted", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(Symptoms.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }

        } else if(spinnerValue=="Diarrhea") {
            insertData = mdata.addData5(ratingValue);
            if(insertData){
                Toast.makeText(Symptoms.this, "Inserted", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(Symptoms.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        } else if(spinnerValue=="Sore Throat") {
            insertData = mdata.addData6(ratingValue);
            if(insertData){
                Toast.makeText(Symptoms.this, "Inserted", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(Symptoms.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        } else if(spinnerValue=="Fever") {
            insertData = mdata.addData7(ratingValue);
            if(insertData){
                Toast.makeText(Symptoms.this, "Inserted", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(Symptoms.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        } else if(spinnerValue=="Muscle Ache") {
            insertData = mdata.addData8(ratingValue);
            if(insertData){
                Toast.makeText(Symptoms.this, "Inserted", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(Symptoms.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        } else if(spinnerValue=="Loss of Smell/Taste") {
            insertData = mdata.addData9(ratingValue);
            if(insertData){
                Toast.makeText(Symptoms.this, "Inserted", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(Symptoms.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        } else if(spinnerValue=="Cough") {
            insertData = mdata.addData10(ratingValue);
            if(insertData){
                Toast.makeText(Symptoms.this, "Inserted", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(Symptoms.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        } else if(spinnerValue=="Shortness of Breath") {
            insertData = mdata.addData11(ratingValue);
            if(insertData){
                Toast.makeText(Symptoms.this, "Inserted", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(Symptoms.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        } else if(spinnerValue=="Feeling tired") {
            insertData = mdata.addData12(ratingValue);
            if(insertData){
                Toast.makeText(Symptoms.this, "Inserted", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(Symptoms.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }

    }
}