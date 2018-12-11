package com.example.precious_lord.agsec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class CropGallery extends AppCompatActivity {

    CardView tomato;
    CardView maize;
    CardView cassava;
    CardView beans;
    CardView rice;
    CardView oilpalm;
    CardView pumpkin;
    CardView egusi;
    CardView potato;
    CardView yam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_gallery);
        getSupportActionBar().hide();

        tomato = findViewById(R.id.tomato);
        maize = findViewById(R.id.maize);
        cassava = findViewById(R.id.cassava);
        beans = findViewById(R.id.beans);
        rice = findViewById(R.id.rice);
        oilpalm = findViewById(R.id.oilpalm);
        pumpkin = findViewById(R.id.ugu);
        egusi = findViewById(R.id.egusi);
        potato = findViewById(R.id.potato);
        yam = findViewById(R.id.yam);

        tomato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tIntent = new Intent(getApplicationContext(), Tomato.class);
                startActivity(tIntent);
            }
        });

        maize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getApplicationContext(), Maize.class);
                startActivity(mIntent);
            }
        });

        cassava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cIntent = new Intent(getApplicationContext(), Cassava.class);
                startActivity(cIntent);
            }
        });

        beans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bIntent = new Intent(getApplicationContext(), Beans.class);
                startActivity(bIntent);
            }
        });

        rice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rIntent = new Intent(getApplicationContext(), Rice.class);
                startActivity(rIntent);
            }
        });

        oilpalm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent oIntent = new Intent(getApplicationContext(), Oilpalm.class);
                startActivity(oIntent);
            }
        });

        pumpkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pIntent = new Intent(getApplicationContext(), Pumpkin.class);
                startActivity(pIntent);
            }
        });

        egusi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent eIntent = new Intent(getApplicationContext(), Egusi.class);
                startActivity(eIntent);
            }
        });

        potato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent poIntent = new Intent(getApplicationContext(), Potato.class);
                startActivity(poIntent);
            }
        });

        yam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent yIntent = new Intent(getApplicationContext(), Yam.class);
                startActivity(yIntent);
            }
        });
    }
}
