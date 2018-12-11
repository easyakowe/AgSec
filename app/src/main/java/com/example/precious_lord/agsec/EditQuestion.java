package com.example.precious_lord.agsec;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.precious_lord.agsec.Database.DatabaseHelper;

public class EditQuestion extends AppCompatActivity {

    public static final String EXTRA_EMAIL = "The email is: ";
    public static final String EXTRA_DOMAIN = "The domain is: ";
    public static final String EXTRA_QUESTION = "The question is: ";


    EditText editIntentEmail;
    EditText editIntentDomain;
    EditText editIntentQuestion;

    Button editIntentSubmit;

    TextView giveReplyPrompt;
    EditText expertEmailConfirm;
    EditText expertReplyText;
    Button expertReplySend;
    LinearLayout expertReplyLyt;

    DatabaseHelper databaseHelper;

    String intentEmail;
    String intentDomain;
    String intentQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_question);
        setTitle("Reconstruct Question");

        editIntentEmail = findViewById(R.id.editIntentEmail);
        editIntentDomain = findViewById(R.id.editIntentDomain);
        editIntentQuestion = findViewById(R.id.editIntentQuestion);
        editIntentSubmit = findViewById(R.id.editIntentSubmit);

        giveReplyPrompt = findViewById(R.id.giveReplyPrompt);
        expertEmailConfirm = findViewById(R.id.expertEmailConfirm);
        expertReplyText = findViewById(R.id.expertReplyText);
        expertReplySend = findViewById(R.id.expertReplySend);
        expertReplyLyt = findViewById(R.id.expertReplyLyt);

        extractDataFromIntent();

        databaseHelper = new DatabaseHelper(this);

        editIntentSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postUpdateToSqlite(v);
            }
        });

        giveReplyPrompt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                giveReplyPrompt.setVisibility(View.GONE);
                expertReplyLyt.setVisibility(View.VISIBLE);
            }
        });

        expertReplySend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expertEmailConfirm.getText().toString().isEmpty() || expertReplyText.getText().toString().isEmpty()){
                    Snackbar.make(v, "Fill in empty field(s)", Snackbar.LENGTH_SHORT).show();
                }else{
                    sendReplyToSQLite(v);
                }
            }
        });
    }

    public void sendReplyToSQLite(View v){
        if (databaseHelper.checkExpert1(expertEmailConfirm.getText().toString())){
            databaseHelper.replyQuestion(editIntentQuestion.getText().toString(), expertReplyText.getText().toString());
            Snackbar.make(v, "Reply Sent", Snackbar.LENGTH_SHORT).show();
            emptyFields();
        }else{
            Snackbar.make(v, "Invalid Expert ID...", Snackbar.LENGTH_SHORT).show();
            emptyFields();
        }
    }

    public void emptyFields(){
        expertEmailConfirm.setText(null);
        expertReplyText.setText(null);
    }

    // Collecting the user email from Intent
    private void extractDataFromIntent() {
        Intent intent = getIntent();
        intentEmail= intent.getStringExtra(EXTRA_EMAIL);
        intentDomain= intent.getStringExtra(EXTRA_DOMAIN);
        intentQuestion= intent.getStringExtra(EXTRA_QUESTION);

        editIntentEmail.setText(intentEmail);
        editIntentDomain.setText(intentDomain);
        editIntentQuestion.setText(intentQuestion);
    }

    public static Intent makeIntent(Context context, String email, String domain, String question){
        Intent intent = new Intent(context, EditQuestion.class);
        intent.putExtra(EXTRA_EMAIL, email);
        intent.putExtra(EXTRA_DOMAIN, domain);
        intent.putExtra(EXTRA_QUESTION, question);
        return intent;
    }

// Collecting the user email from Intent

    public void postUpdateToSqlite(View v){
        databaseHelper.editQuestion(editIntentDomain.getText().toString(), editIntentQuestion.getText().toString());
        Snackbar.make(v, "Updated Sucessfully", Snackbar.LENGTH_SHORT).show();
    }
}
