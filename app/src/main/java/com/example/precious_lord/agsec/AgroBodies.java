package com.example.precious_lord.agsec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AgroBodies extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agro_bodies);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        setTitle(R.string.title_agrobodies);
    }
}
