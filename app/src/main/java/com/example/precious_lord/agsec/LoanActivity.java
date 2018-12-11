package com.example.precious_lord.agsec;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.rengwuxian.materialedittext.MaterialEditText;

import info.hoang8f.widget.FButton;

public class LoanActivity extends AppCompatActivity {

    EditText principalAmount;
    EditText rate;
    EditText noOfYears;
    FButton computeLoan;

    EditText monthlyPayment;
    EditText totalPayment;
    EditText interest;

    FButton btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);
        setTitle(R.string.title_loan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        principalAmount = findViewById(R.id.principalAmount);
        rate = findViewById(R.id.rate);
        noOfYears = findViewById(R.id.noOfYears);

        computeLoan = findViewById(R.id.computeLoan);

        monthlyPayment = findViewById(R.id.monthlyPayment);
        totalPayment = findViewById(R.id.totalPayment);
        interest = findViewById(R.id.totalInterest);

        btnClear = findViewById(R.id.btnClear);

        computeLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (principalAmount.getText().toString().isEmpty() || rate.getText().toString().isEmpty() ||
                        noOfYears.getText().toString().isEmpty()){
                    Snackbar.make(v, "Fill in empty fields", Snackbar.LENGTH_LONG).show();
                }else{
                    String principal = principalAmount.getText().toString();
                    String intRate = rate.getText().toString();
                    String time = noOfYears.getText().toString();

                    float p1 = Float.parseFloat(principal);
                    float r1 = Float.parseFloat(intRate);
                    float t1 = Float.parseFloat(time);

                    //EMI = P * R * ((1+r)^n)/(((1+r)^n) - 1)

                    float p_amnt = getPrincipal(p1);
                    float interestRate = getRate(r1);
                    float years = getTime(t1);

                    float numerator = getNum(interestRate, years);
                    float denominator = getDen(numerator);
                    float emi = getEmi(p_amnt, interestRate, numerator, denominator);
                    float totalEmi = getTotalPayment(emi, years);
                    float tot_interest = getInterest(p_amnt, totalEmi);


                    monthlyPayment.setText(String.valueOf(emi));
                    totalPayment.setText(String.valueOf(totalEmi));
                    interest.setText(String.valueOf(tot_interest));
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                principalAmount.setText(null);
                rate.setText(null);
                noOfYears.setText(null);
               monthlyPayment.setText(null);
               totalPayment.setText(null);
               interest.setText(null);
            }
        });
    }

    float getPrincipal(float p){
        return p;
    }

    float getRate(float r){
        return r/12/100;
    }

    float getTime(float t){
        return t * 12;
    }

    float getNum(float intRate, float time){
        return (float) Math.pow(1 + intRate, time);
    }

    float getDen(float num){
        return num - 1;
    }

    float getEmi(float p_amnt, float rate, float num, float den){
        return (p_amnt * rate * num)/den;
    }

    float getTotalPayment(float emi, float time){
        return emi * time;
    }

    float getInterest(float p_amnt, float tot_paymt){
        return tot_paymt - p_amnt;
    }

}
