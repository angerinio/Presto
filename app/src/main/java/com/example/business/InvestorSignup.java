package com.example.business;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class InvestorSignup extends AppCompatActivity  {

    private DatabaseHelper dbHelper;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investorsignup);

        dbHelper = new DatabaseHelper(this);

        Bundle extras = getIntent().getExtras();
        String username = extras.getString("username");







        //dbHelper.updateTask(id, name, amount, description, interest,frequency, startDate, endDate);


        //EditText name = findViewById(R.id.editTextInvestorFullName);
       // name.setText(username);

        Button button_save = findViewById(R.id.button_investorSave);
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText Iname = findViewById(R.id.editTextInvestorFullName);
                EditText Ilocation = findViewById(R.id.editTextInvestorLocation);
                EditText IbusinessExperience = findViewById(R.id.editTextInvestorExperience);
                EditText Iskills = findViewById(R.id.editTextInvestorSkills);
                EditText IinvestmentType = findViewById(R.id.editTextInvestorInvestment);
                EditText IbusinessType = findViewById(R.id.editTextInvestorBusiness);
                String type = "INVESTOR";
                String name = Iname.getText().toString();
                String location = Ilocation.getText().toString();
                String businessType = IbusinessType.getText().toString();
                String investmentType = IinvestmentType.getText().toString();
                String businessExperience = IbusinessExperience.getText().toString();
                String skills = Iskills.getText().toString();

                dbHelper.updateInvestor(username, type, name, location, businessType, investmentType, businessExperience, skills);

                Intent intent = new Intent(view.getContext(), EntrepreneurSwipe.class);
                intent.putExtra("username",username);
                view.getContext().startActivity(intent);}

        });

    }
}
