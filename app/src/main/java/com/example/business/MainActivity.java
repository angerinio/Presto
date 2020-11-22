package com.example.business;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    private DatabaseHelper dbHelper;
    private SimpleCursorAdapter myCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);

        //Cursor cursor = dbHelper.getAllTasks();
        //String[] fromFieldNames = dbHelper.getAllColumns();


        //dbHelper.updateTask(id, name, amount, description, interest,frequency, startDate, endDate);

        Button button_login = findViewById(R.id.button_Login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText user = findViewById(R.id.editTextTextPersonName);
                EditText pass = findViewById(R.id.editTextTextPassword);
                String username = user.getText().toString();
                String password = pass.getText().toString();


                if(dbHelper.checkUser(username, password)==false){
                    Toast.makeText(getApplicationContext(),"Welcome " + username,Toast.LENGTH_SHORT).show();
                    String type=dbHelper.getType(username,password).getString(0);

                    if (type.equals("INVESTOR")){
                        Intent intent = new Intent(view.getContext(), EntrepreneurSwipe.class);
                        intent.putExtra("username",username);
                        view.getContext().startActivity(intent);
                    }else{
                        Intent intent = new Intent(view.getContext(), InvestorSwipe.class);
                        intent.putExtra("username",username);
                        view.getContext().startActivity(intent);

                    }


                }else{
                    Toast.makeText(getApplicationContext(),"The username or password are incorrect",Toast.LENGTH_SHORT).show();
                }


                //Intent intent1 = new Intent(view.getContext(), EntrepreneurSwipe.class);
                //Intent intent2 = new Intent(view.getContext(), InvestorSwipe.class);
               // view.getContext().startActivity(intent1);
                 }
        });



        Button button_signup = findViewById(R.id.button_Signup);
        button_signup.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               EditText user = findViewById(R.id.editTextTextPersonName);
               EditText pass = findViewById(R.id.editTextTextPassword);
               String username = user.getText().toString();
               String password = pass.getText().toString();
               dbHelper.insertTask(username,password);
               Intent intent = new Intent(view.getContext(), SignupActivity.class);

               SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
               SharedPreferences.Editor editor = pref.edit();
               editor.putString("user", username); // Storing string
               editor.apply();


               intent.putExtra("username",username);
               view.getContext().startActivity(intent);}
        });



        Button button_delete = findViewById(R.id.button_Delete);
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText user = findViewById(R.id.editTextTextPersonName);
                String username = user.getText().toString();

                dbHelper.deleteTask(username);
                Toast.makeText(getApplicationContext(),username +" deleted",Toast.LENGTH_SHORT).show();


                }
        });






    }



}