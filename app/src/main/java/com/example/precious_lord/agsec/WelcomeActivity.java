package com.example.precious_lord.agsec;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    Button btnsign_in;
    Button btnsign_up;

    Button expertbtnsign_in;
    Button expertbtnsign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().hide();

        btnsign_in = findViewById(R.id.btnsign_in);
        btnsign_up = findViewById(R.id.btnsign_up);

        expertbtnsign_in = findViewById(R.id.expertbtnsign_in);
        expertbtnsign_up = findViewById(R.id.expertbtnsign_up);


        btnsign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lgnIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(lgnIntent);
            }
        });

        btnsign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regIntent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(regIntent);
            }
        });

        expertbtnsign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent expertregIntent = new Intent(getApplicationContext(), ExpertLogin.class);
                startActivity(expertregIntent);
            }
        });

        expertbtnsign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent expertlgnIntent = new Intent(getApplicationContext(), ExpertRegister.class);
                startActivity(expertlgnIntent);
            }
        });
    }

    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);

        builder.setTitle("Exit App")
                .setMessage("Saying Goodbye already?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        WelcomeActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null).setCancelable(false);
        AlertDialog alert = builder.create();
        alert.show();
    }
}
