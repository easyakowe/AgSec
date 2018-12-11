package com.example.precious_lord.agsec;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.precious_lord.agsec.Database.DatabaseHelper;

public class AskQuestions extends AppCompatActivity {

    public static final String EXTRA_EMAIL = "The email is: ";

    EditText question_userEmail;
    EditText question_details;

    Button submit_question;
    TextView viewMyQuestions;

    RadioButton agro_engnr;
    RadioButton soil_mgt;
    RadioButton horticulture;
    RadioButton animal_sci;
    RadioButton agro_econs;
    String questionDomain;

    private DatabaseHelper databaseHelper;
    private Question question;

    String loggedInUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_questions);
        setTitle("Ask the Experts");

        question_userEmail = findViewById(R.id.question_userEmail);
        question_details = findViewById(R.id.question_details);

        agro_engnr = findViewById(R.id.agro_engnr);
        soil_mgt = findViewById(R.id.soil_mgt);
        horticulture = findViewById(R.id.horticulture);
        animal_sci = findViewById(R.id.animal_sci);
        agro_econs = findViewById(R.id.agro_econs);

        submit_question = findViewById(R.id.submit_question);
        viewMyQuestions = findViewById(R.id.viewMyQuestions);

        // Initialize objects
        databaseHelper = new DatabaseHelper(this);
        question = new Question();

        // Get email from dashboard
        extractDataFromIntent();

        //Initialize listeners
        submit_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postToSQLiteDatabase(v);
            }
        });

        viewMyQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendEmailToHistory = QuestionHistory.makeIntent(AskQuestions.this,
                        question_userEmail.getText().toString());
                startActivity(sendEmailToHistory);
            }
        });
    }

    public void domainOfQuestion(View v){

        switch (v.getId()){
            case R.id.agro_engnr:
                questionDomain = agro_engnr.getText().toString();
                break;
            case R.id.soil_mgt:
                questionDomain = soil_mgt.getText().toString();
                break;
            case R.id.horticulture:
                questionDomain = horticulture.getText().toString();
                break;
            case R.id.animal_sci:
                questionDomain = animal_sci.getText().toString();
                break;
            case R.id.agro_econs:
                questionDomain = agro_econs.getText().toString();
                break;
        }
    }

    public void postToSQLiteDatabase(View v){
        if (questionDomain.isEmpty() || question_userEmail.getText().toString().isEmpty() ||
                question_details.getText().toString().isEmpty()){
            Snackbar.make(v, "Ooops!...Something went Wrong", Snackbar.LENGTH_SHORT).show();
            emptyFields();
        }else{
            question.setUser_email(question_userEmail.getText().toString());
            question.setQuestion_domain(questionDomain);
            question.setQuestion_detail(question_details.getText().toString());

            databaseHelper.addQuestion(question);
            Snackbar.make(v, "Question Registered!", Snackbar.LENGTH_SHORT).show();
            emptyFields();
        }
    }

    public void emptyFields(){
        agro_engnr.setChecked(false);
        soil_mgt.setChecked(false);
        agro_econs.setChecked(false);
        animal_sci.setChecked(false);
        horticulture.setChecked(false);
        question_details.setText(null);
    }

    private void extractDataFromIntent() {
        Intent intent = getIntent();
        loggedInUser= intent.getStringExtra(EXTRA_EMAIL);
        question_userEmail.setText(loggedInUser);
    }

    public static Intent makeIntent(Context context, String email){
        Intent intent = new Intent(context, AskQuestions.class);
        intent.putExtra(EXTRA_EMAIL, email);
        return intent;
    }
}
