package com.example.precious_lord.agsec;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.precious_lord.agsec.Database.DatabaseHelper;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    public static final String EXTRA_EMAIL = "The first activity is: ";
    public static final String EXTRA_FULLNAME = "The first fullname is: ";

    TextView loggedinuser;
    TextView loggedinusername;

    CardView about;
    CardView tips;
    CardView loanAcq;
    CardView agroBodies;
    CardView farmSec;
    CardView imgGallery;
    CardView talkToExperts;
    CardView expertsGiveReply;

    private String userEmail;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        setTitle(R.string.dashboard);

        about = findViewById(R.id.about);
        tips = findViewById(R.id.tips);
        loanAcq = findViewById(R.id.loanAcq);
        agroBodies = findViewById(R.id.agroBodies);
        farmSec = findViewById(R.id.farmSec);
        imgGallery = findViewById(R.id.imgGallery);
        talkToExperts = findViewById(R.id.talkToExperts);
        expertsGiveReply = findViewById(R.id.expertsGiveReply);

        loggedinuser = findViewById(R.id.loggedinuser);
        loggedinusername = findViewById(R.id.loggedinusername);

        databaseHelper = new DatabaseHelper(this);

        // Extract data from intent
        extractDataFromIntent();

        if (loggedinuser.getText().toString().equals("null (null)")){
            loggedinuser.setVisibility(View.GONE);
        }else if (loggedinusername.getText().toString().equals("null (null)")){
            loggedinusername.setVisibility(View.GONE);
        }

        // Assign the data to a view
//        loggedinuser.setText(userEmail);
        // Implement onClick listeners on the views

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aboutIntent = new Intent(DashboardActivity.this, About.class);
                startActivity(aboutIntent);
            }
        });

        tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tipsIntent = new Intent(DashboardActivity.this, Tips.class);
                startActivity(tipsIntent);
            }
        });

        loanAcq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loanIntent = new Intent(DashboardActivity.this, LoanInfo.class);
                startActivity(loanIntent);
            }
        });

        agroBodies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bodiesIntent = new Intent(DashboardActivity.this, AgroBodies.class);
                startActivity(bodiesIntent);
            }
        });

        farmSec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent secIntent = new Intent(DashboardActivity.this, Security.class);
                    startActivity(secIntent);

            }
        });

        imgGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(DashboardActivity.this, CropGallery.class);
                startActivity(galleryIntent);
            }
        });

        talkToExperts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent questIntent = new Intent(DashboardActivity.this, AskQuestions.class);
//                startActivity(questIntent);
                Intent getEmailIntent = AskQuestions.makeIntent(DashboardActivity.this, userEmail);
                startActivity(getEmailIntent);
            }
        });

        expertsGiveReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (loggedinusername.getText().toString().equals("null (null)")){

                    Snackbar mySnackBar = Snackbar.make(v, "You are not signed in as an Expert!", Snackbar.LENGTH_LONG);
                    mySnackBar.setAction("Sign in (Expert)", new MySigninListener());
                    mySnackBar.show();
                }else{
                    final Intent intent;
//                    Intent replyIntent = new Intent(DashboardActivity.this, ExpertsQuestionHistory.class);
//                    startActivity(replyIntent);
                    intent = ExpertsQuestionHistory.makeIntent(getApplicationContext(), userEmail);
                    startActivity(intent);
                }
            }
        });


        mDrawerLayout = (DrawerLayout)findViewById(R.id.dashboardDrawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(DashboardActivity.this);

        builder.setTitle("Logout")
                .setMessage("Logging out " + loggedinusername.getText().toString() + "?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        DashboardActivity.super.onBackPressed();
                        Intent newIntent = new Intent(DashboardActivity.this, LoginActivity.class);
                        startActivity(newIntent);
                    }
                })
                .setNegativeButton("No", null).setCancelable(false);
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void extractDataFromIntent() {
        Intent intent = getIntent();
        userEmail= intent.getStringExtra(EXTRA_EMAIL);
        String[] profiledetails = databaseHelper.getLoggedExpertUser(userEmail);
        String[] profiledetails2 = databaseHelper.getLoggedUser(userEmail);
        loggedinusername.setText(profiledetails[0] + " (" + profiledetails[2] + ")");
        loggedinuser.setText(profiledetails2[0] + " (" + profiledetails2[1] + ")");
//        loggedinuser.setText();
    }

    public static Intent makeIntent(Context context, String email){
        Intent intent = new Intent(context, DashboardActivity.class);
        intent.putExtra(EXTRA_EMAIL, email);
        return intent;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.tips) {

            Intent crop_activity_intent = new Intent(DashboardActivity.this, Tips.class);
            startActivity(crop_activity_intent);
        } else if (id == R.id.gallery) {

            Intent crop_activity_intent = new Intent(DashboardActivity.this, CropGallery.class);
            startActivity(crop_activity_intent);
        } else if (id == R.id.security) {

            Intent cropSoil_intent = new Intent(DashboardActivity.this, Security.class);
            startActivity(cropSoil_intent);

        } else if (id == R.id.loan) {

            Intent security = new Intent(DashboardActivity.this, LoanActivity.class);
            startActivity(security);

        } else if (id == R.id.contact) {

//            Intent loan_intent = new Intent(DashboardActivity.this, Contact.class);
//            startActivity(loan_intent);
                AlertDialog.Builder builder = new AlertDialog.Builder(DashboardActivity.this);

                builder.setTitle("Contact Us")
                        .setMessage(R.string.contact_info)
                        .setNegativeButton("Ok", null).setIcon(R.drawable.iconaboutagric).setCancelable(false);
                AlertDialog alert = builder.create();
                alert.show();


        } else if (id == R.id.logout) {
            AlertDialog.Builder builder = new AlertDialog.Builder(DashboardActivity.this);

            builder.setTitle("Logout")
                    .setMessage("Logging out " + loggedinusername.getText().toString() + "?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//                        DashboardActivity.super.onBackPressed();
                            Intent newIntent = new Intent(DashboardActivity.this, LoginActivity.class);
                            startActivity(newIntent);
                        }
                    })
                    .setNegativeButton("No", null).setCancelable(false);
            AlertDialog alert = builder.create();
            alert.show();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.dashboardDrawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Class to implement a button that will be clicked when a snackbar pops up
    private class MySigninListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            onIntentLoginAsExpert();
        }
    }

    public void onIntentLoginAsExpert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(DashboardActivity.this);

        builder.setTitle("Logout")
                .setMessage("Logging out " + loggedinuser.getText().toString() + "?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        DashboardActivity.super.onBackPressed();
                        Intent newIntent = new Intent(DashboardActivity.this, ExpertLogin.class);
                        startActivity(newIntent);
                    }
                })
                .setNegativeButton("No", null).setCancelable(false);
        AlertDialog alert = builder.create();
        alert.show();
    }
}
