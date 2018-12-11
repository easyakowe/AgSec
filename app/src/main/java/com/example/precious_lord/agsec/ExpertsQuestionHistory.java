package com.example.precious_lord.agsec;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.precious_lord.agsec.Database.DatabaseHelper;

import java.util.ArrayList;

public class ExpertsQuestionHistory extends AppCompatActivity implements SearchView.OnQueryTextListener{

    public static final String EXTRA_EMAIL = "The first activity is: ";
    RecyclerView expertRecyclerQuestionList;

    private AppCompatActivity activity = ExpertsQuestionHistory.this;
    Context context = ExpertsQuestionHistory.this;
    private ArrayList<Question> listQuestion;
    private QuestionsRecyclerAdapter questionsRecyclerAdapter;
    private DatabaseHelper databaseHelper;
    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experts_question_history);
        setTitle("List of Questions");

        initViews();
        initObjects();

        extractDataFromIntent();

    }

    private void initViews() {
        expertRecyclerQuestionList = (RecyclerView) findViewById(R.id.expertRecyclerQuestionList);
    }

    private void initObjects() {
        listQuestion = new ArrayList<>();
        questionsRecyclerAdapter = new QuestionsRecyclerAdapter(listQuestion, this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        expertRecyclerQuestionList.setLayoutManager(mLayoutManager);
        expertRecyclerQuestionList.setItemAnimator(new DefaultItemAnimator());
        expertRecyclerQuestionList.setHasFixedSize(true);
        expertRecyclerQuestionList.setAdapter(questionsRecyclerAdapter);
        databaseHelper = new DatabaseHelper(activity);

        getDataFromSQLite();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return super.onOptionsItemSelected(item);
    }

    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listQuestion.clear();
                listQuestion.addAll(databaseHelper.getAllQuestions());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                questionsRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    private void extractDataFromIntent() {
        Intent intent = getIntent();
        userEmail= intent.getStringExtra(EXTRA_EMAIL);
    }

    public static Intent makeIntent(Context context, String email){
        Intent intent = new Intent(context, ExpertsQuestionHistory.class);
        intent.putExtra(EXTRA_EMAIL, email);
        return intent;
    }

// Collecting the user email from Intent
}
