package com.example.precious_lord.agsec;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.precious_lord.agsec.Database.DatabaseHelper;

public class ExpertRegister extends AppCompatActivity {

    EditText expertfullname;
    EditText expertemail;
    EditText expertphone;
    EditText expertdomain;
    EditText expertpassword;
    EditText expertconfirmPassword;

    Button expertregButton;
    TextView expertloginPrompt;

    DatabaseHelper databaseHelper;
    Expert expert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expert_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Register As Expert");

        expertfullname = findViewById(R.id.expertfullname);
        expertemail = findViewById(R.id.expertemail);
        expertphone = findViewById(R.id.expertphone);
        expertdomain = findViewById(R.id.expertdomain);
        expertpassword = findViewById(R.id.expertpassword);
        expertconfirmPassword = findViewById(R.id.expertconfirmPassword);

        expertregButton = findViewById(R.id.expertregButton);
        expertloginPrompt = findViewById(R.id.expertloginPrompt);

        databaseHelper = new DatabaseHelper(this);
        expert = new Expert();

        expertregButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expertpassword.getText().toString().equals(expertconfirmPassword.getText().toString())){
                    if (!databaseHelper.checkExpert(expertemail.getText().toString(), expertpassword.getText().toString())){

                        expert.setExpert_fullname(expertfullname.getText().toString());
                        expert.setExpert_email(expertemail.getText().toString());
                        expert.setExpert_phone(expertphone.getText().toString());
                        expert.setExpert_field(expertdomain.getText().toString());
                        expert.setExpert_password(expertpassword.getText().toString());

                        databaseHelper.addExpert(expert);
                        Snackbar.make(v, "Registration Successful!", Snackbar.LENGTH_SHORT).show();
                        Intent lgnIntent = new Intent(getApplicationContext(), ExpertLogin.class);
                        startActivity(lgnIntent);
                        emptyFields();

                    }else{
                        Snackbar.make(v, "Expert exists already!", Snackbar.LENGTH_SHORT).show();
                        emptyFields();
                    }
                }else{
                    Snackbar.make(v, "Passwords do not match!", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        expertloginPrompt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lgnIntent = new Intent(getApplicationContext(), ExpertLogin.class);
                startActivity(lgnIntent);
            }
        });

    }

    public void emptyFields(){
        expertfullname.setText(null);
        expertemail.setText(null);
        expertphone.setText(null);
        expertdomain.setText(null);
        expertpassword.setText(null);
        expertconfirmPassword.setText(null);
    }
}
