package com.example.precious_lord.agsec;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.precious_lord.agsec.Database.DatabaseHelper;

public class ExpertLogin extends AppCompatActivity {

    EditText expertemail;
    EditText expertpassword;

    Button expertbtnLogin;

    TextView expertregisterQuestion;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expert_login);
        setTitle("Login As Expert");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize views
        expertemail = findViewById(R.id.expertemail);
        expertpassword = findViewById(R.id.expertpassword);

        expertbtnLogin = findViewById(R.id.expertbtnLogin);
        expertregisterQuestion = findViewById(R.id.expertregisterQuestion);

        // Initialize object
        databaseHelper = new DatabaseHelper(this);

        // Initialize Listeners
        expertbtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expertemail.getText().toString().isEmpty() || expertpassword.getText().toString().isEmpty()){
                    Snackbar.make(v, "Fill in empty field(s)", Snackbar.LENGTH_LONG).show();
                }

                else if (databaseHelper.checkExpert(expertemail.getText().toString(), expertpassword.getText().toString())){
//
                    Intent verifiedIntent = DashboardActivity.makeIntent(ExpertLogin.this, expertemail.getText().toString());
                    Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_LONG).show();
                    emptyEditText();
                    startActivity(verifiedIntent);

                }else{
                    Snackbar.make(v, "Invalid Username or Password", Snackbar.LENGTH_LONG).show();
                    emptyEditText();
                }
            }
        });

        expertregisterQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExpertLogin.this, ExpertRegister.class);
                startActivity(intent);
            }
        });
    }

    public void emptyEditText(){
        expertemail.setText(null);
        expertpassword.setText(null);
    }

    public void onBackPressed(){
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }
}
