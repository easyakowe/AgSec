package com.example.precious_lord.agsec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import info.hoang8f.widget.FButton;

public class LoanInfo extends AppCompatActivity {

    FButton viewCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_info);
        setTitle(R.string.title_loanInfo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        viewCalc = findViewById(R.id.btnViewCalc);

        viewCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calcIntent = new Intent(getApplicationContext(), LoanActivity.class);
                startActivity(calcIntent);
            }
        });
    }
}
