package com.example.precious_lord.agsec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Tips extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        setTitle(R.string.title_tips);
    }
}
