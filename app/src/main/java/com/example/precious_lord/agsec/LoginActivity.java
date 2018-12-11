package com.example.precious_lord.agsec;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.precious_lord.agsec.Database.DatabaseHelper;
import com.example.precious_lord.agsec.Database.Users;

public class LoginActivity extends AppCompatActivity {

    EditText email;
    EditText password;

    Button btnLogin;

    TextView registerQuestion;
    TextView expertloginQuestion;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle(R.string.title_loginpage);

        //Initialize views

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        btnLogin = findViewById(R.id.btnLogin);

        registerQuestion = findViewById(R.id.registerQuestion);
        expertloginQuestion = findViewById(R.id.expertloginQuestion);

        // Initialize Object

        databaseHelper = new DatabaseHelper(this);

        // Initialize onClickListeners

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                    Snackbar.make(v, "Fill in empty field(s)", Snackbar.LENGTH_LONG).show();
                }

                else if (databaseHelper.checkUser(email.getText().toString(), password.getText().toString())){
//                    Intent verifiedIntent = new Intent(getApplicationContext(), DashboardActivity.class);
                    Intent verifiedIntent = DashboardActivity.makeIntent(LoginActivity.this, email.getText().toString());
                    Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_LONG).show();
                    emptyEditText();
                    startActivity(verifiedIntent);

                }else{
                    Snackbar.make(v, "Invalid Username or Password", Snackbar.LENGTH_LONG).show();
                    emptyEditText();
                }
            }
        });

        registerQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regIntent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(regIntent);
            }
        });

        expertloginQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent expregIntent = new Intent(getApplicationContext(), ExpertLogin.class);
                startActivity(expregIntent);
            }
        });

    }

    private void verifyFromSQLite(){
        if (databaseHelper.checkUser(email.getText().toString(), password.getText().toString())){
            Intent verifiedIntent = new Intent(getApplicationContext(), DashboardActivity.class);
            Toast.makeText(this, "Accepted", Toast.LENGTH_SHORT).show();
            emptyEditText();
            startActivity(verifiedIntent);
        }else{
            Toast.makeText(this, "Invalid Email or Password", Toast.LENGTH_LONG).show();
            emptyEditText();
        }
    }

    public void emptyEditText(){
        email.setText(null);
        password.setText(null);
    }

    public void onBackPressed(){
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }
}