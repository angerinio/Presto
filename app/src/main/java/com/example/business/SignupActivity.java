package com.example.business;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Bundle extras = getIntent().getExtras();
        String username = extras.getString("username");



        Button button_entrepreneur = findViewById(R.id.button_Entrepreneur);
        button_entrepreneur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EntrepreneurSignup.class);
                intent.putExtra("username",username);
                view.getContext().startActivity(intent);}
        });

        Button button_investor = findViewById(R.id.button_Investor);
        button_investor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), InvestorSignup.class);
                intent.putExtra("username",username);
                view.getContext().startActivity(intent);}
        });

    }
}
