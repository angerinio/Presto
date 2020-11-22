package com.example.business;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class EntrepreneurSignup extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrepreneursignup);

        dbHelper = new DatabaseHelper(this);

        Bundle extras = getIntent().getExtras();
        String username = extras.getString("username");


        Button save = findViewById(R.id.button_ESave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText fname =findViewById(R.id.editTextEFullname);
                EditText ebname =findViewById(R.id.editTextEBName);
                EditText eblocation =findViewById(R.id.editTextEBLocation);
                EditText ebtype =findViewById(R.id.editTextEBType);
                EditText ebstage =findViewById(R.id.editTextEBStage);
                EditText einvestment =findViewById(R.id.editTextEInvestment);
                EditText einvestor =findViewById(R.id.editTextEInvestor);
                EditText ebgoals =findViewById(R.id.editTextEBGoals);
                String type = "ENTREPRENEUR";
                String name = fname.getText().toString();
                String businessName = ebname.getText().toString();
                String location = eblocation.getText().toString();
                String businessType = ebtype.getText().toString();
                String stage = ebstage.getText().toString();
                String investmentType = einvestment.getText().toString();
                String investorType = einvestor.getText().toString();
                String goals = ebgoals.getText().toString();
                dbHelper.updateEntrepreneur(username,type,name,businessName, location, businessType, stage, investmentType, investorType, goals);
                Intent intent = new Intent(view.getContext(), InvestorSwipe.class);
                intent.putExtra("username",username);
                view.getContext().startActivity(intent);}
        });

    }





}
