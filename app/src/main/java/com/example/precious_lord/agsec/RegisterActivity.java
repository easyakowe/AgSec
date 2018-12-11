package com.example.precious_lord.agsec;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Looper;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.precious_lord.agsec.Database.DatabaseHelper;
//import com.example.precious_lord.agsec.Database.InputValidation;
import com.example.precious_lord.agsec.Database.Users;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = RegisterActivity.this;

    private EditText fullname;
    private EditText email;
    private EditText phone;
    private EditText password;
    private EditText confirmPassword;

    Button regButton;
    TextView loginQuestion;

    //private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private Users user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle(R.string.reg_btn_name);

        initViews();
        initListeners();
        initObjects();

    }

    private void initViews(){
        fullname = findViewById(R.id.fullname);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);

        regButton = findViewById(R.id.regButton);
        loginQuestion = findViewById(R.id.loginQuestion);
    }

    private void initListeners(){
        regButton.setOnClickListener(this);
        loginQuestion.setOnClickListener(this);
    }

    private void initObjects(){
        databaseHelper = new DatabaseHelper(activity);
        user = new Users();
    }

//    private void postDataToSQLite(){
//        if (!databaseHelper.checkUser(phone.getText().toString())){
//
//            user.setFullName(fullname.getText().toString());
//            user.setEmail(email.getText().toString());
//            user.setPhone(phone.getText().toString());
//            user.setPassword(password.getText().toString());
//
//            databaseHelper.addUser(user);
//
//            Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show();
//            emptyInputText();
//        }else {
//            Toast.makeText(this, "The username exists already", Toast.LENGTH_LONG).show();
//            emptyInputText();
//        }
//    }

    private void emptyInputText(){
        fullname.setText(null);
        email.setText(null);
        phone.setText(null);
        password.setText(null);
        confirmPassword.setText(null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.regButton:
                if (!password.getText().toString().equals(confirmPassword.getText().toString())){
                    Snackbar.make(v, "Password do not match", Snackbar.LENGTH_LONG).show();
                }else if (!databaseHelper.checkUser(email.getText().toString(), password.getText().toString())){

                    user.setFullName(fullname.getText().toString());
                    user.setEmail(email.getText().toString());
                    user.setPhone(phone.getText().toString());
                    user.setPassword(password.getText().toString());

                    databaseHelper.addUser(user);

                    Intent regIntent = new Intent(getApplicationContext(), LoginActivity.class);
                    Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    emptyInputText();
                    startActivity(regIntent);
                }else {
                    Toast.makeText(this, "The username exists already", Toast.LENGTH_SHORT).show();
                    emptyInputText();
                }
                break;

            case R.id.loginQuestion:
                Intent redirectIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(redirectIntent);
                break;
        }
    }
}
