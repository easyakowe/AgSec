package com.example.precious_lord.agsec.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.precious_lord.agsec.Expert;
import com.example.precious_lord.agsec.ExpertContract;
import com.example.precious_lord.agsec.Question;
import com.example.precious_lord.agsec.QuestionsContract;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;

    private static final int DATABASE_VERSION = 6;
    private static final String DATABASE_NAME = "Agsec.db";

    // Users table
    private static final String USER_TABLE = "user";
    private static final String USER_ID = "user_id";
    private static final String USER_FULL_NAME = "user_fullname";
    private static final String USER_EMAIL = "user_email";
    private static final String USER_PHONE = "user_phone";
    private static final String USER_PASSWORD = "user_password";

    // User queries
    String queryCreateUser = "CREATE TABLE " + USER_TABLE + "(" + USER_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT," + USER_FULL_NAME +
            " TEXT," + USER_EMAIL + " TEXT," + USER_PHONE + " INTEGER, "
            + USER_PASSWORD + " TEXT " + ")";

    String queryCreateExpert = "CREATE TABLE " + ExpertContract.ExpertEntry.EXPERT_TABLE + "(" +
            ExpertContract.ExpertEntry.EXPERT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            ExpertContract.ExpertEntry.EXPERT_FULL_NAME + " TEXT," +
            ExpertContract.ExpertEntry.EXPERT_EMAIL + " TEXT," +
            ExpertContract.ExpertEntry.EXPERT_PHONE + " TEXT," +
            ExpertContract.ExpertEntry.EXPERT_FIELD + " TEXT," +
            ExpertContract.ExpertEntry.EXPERT_PASSWORD + " TEXT" + ")";

    String queryCreateQuestion = "CREATE TABLE " + QuestionsContract.QuestionEntry.QUESTION_TABLE + "(" +
            QuestionsContract.QuestionEntry.QUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            QuestionsContract.QuestionEntry.USER_EMAIL + " TEXT," +
            QuestionsContract.QuestionEntry.QUESTION_DOMAIN + " TEXT," +
            QuestionsContract.QuestionEntry.QUESTION_DETAIL + " TEXT," +
            QuestionsContract.QuestionEntry.QUESTION_REPLY + " TEXT" + ")";

    String queryDrop = "DROP TABLE IF EXISTS " + USER_TABLE;

    String queryDropExpert = "DROP TABLE IF EXISTS " + ExpertContract.ExpertEntry.EXPERT_TABLE;

    String queryDropQuestion = "DROP TABLE IF EXISTS " + QuestionsContract.QuestionEntry.QUESTION_TABLE;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(queryCreateUser);
        db.execSQL(queryCreateExpert);
        db.execSQL(queryCreateQuestion);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(queryDrop);
        db.execSQL(queryDropExpert);
        db.execSQL(queryDropQuestion);
        onCreate(db);
    }

    //-- Opens the database
    public DatabaseHelper open() throws SQLException {
        db = databaseHelper.getWritableDatabase();
        return this;
    }

    //-- Closes the database
    public void close(){
        databaseHelper.close();
    }

    public void addUser(Users user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_FULL_NAME, user.getFullName());
        values.put(USER_EMAIL, user.getEmail());
        values.put(USER_PHONE, user.getPhone());
        values.put(USER_PASSWORD, user.getPassword());

        db.insert(USER_TABLE, null, values);
        db.close();
    }

    public void addExpert(Expert expert){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ExpertContract.ExpertEntry.EXPERT_FULL_NAME, expert.getExpert_fullname());
        values.put(ExpertContract.ExpertEntry.EXPERT_EMAIL, expert.getExpert_email());
        values.put(ExpertContract.ExpertEntry.EXPERT_PHONE, expert.getExpert_phone());
        values.put(ExpertContract.ExpertEntry.EXPERT_FIELD, expert.getExpert_field());
        values.put(ExpertContract.ExpertEntry.EXPERT_PASSWORD, expert.getExpert_password());

        db.insert(ExpertContract.ExpertEntry.EXPERT_TABLE, null, values);
        db.close();
    }

    public void addQuestion(Question question){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(QuestionsContract.QuestionEntry.USER_EMAIL, question.getUser_email());
        values.put(QuestionsContract.QuestionEntry.QUESTION_DOMAIN, question.getQuestion_domain());
        values.put(QuestionsContract.QuestionEntry.QUESTION_DETAIL, question.getQuestion_detail());
        values.put(QuestionsContract.QuestionEntry.QUESTION_REPLY, question.getQuestion_reply());

        db.insert(QuestionsContract.QuestionEntry.QUESTION_TABLE, null, values);
        db.close();
    }

    public boolean checkUser(String email, String password){
        String[] columns = {USER_ID};

        SQLiteDatabase db = this.getReadableDatabase();
        String selection = USER_EMAIL + " = ?" + " AND " + USER_PASSWORD + " =?";
        String[] selectionArgs = { email, password };

        Cursor cursor = db.query(USER_TABLE,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0){
            return true;
        }
        return false;
    }

    public boolean checkExpert(String email, String password){
        String[] columns = {ExpertContract.ExpertEntry.EXPERT_ID};

        SQLiteDatabase db = this.getWritableDatabase();
        String selection = ExpertContract.ExpertEntry.EXPERT_EMAIL + " =?" + " AND " +
                ExpertContract.ExpertEntry.EXPERT_PASSWORD + " =?";
        String[] selectionArgs = { email, password };

        Cursor cursor = db.query(ExpertContract.ExpertEntry.EXPERT_TABLE,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0){
            return true;
        }
        return false;
    }

    public boolean checkUser1(String email){
        String[] columns = {USER_ID};

        SQLiteDatabase db = this.getReadableDatabase();
        String selection = USER_EMAIL + " = ?";
        String[] selectionArgs = { email };

        Cursor cursor = db.query(USER_TABLE,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0){
            return true;
        }
        return false;
    }

    public boolean checkExpert1(String email){
        String[] columns = {ExpertContract.ExpertEntry.EXPERT_ID};

        SQLiteDatabase db = this.getReadableDatabase();
        String selection = ExpertContract.ExpertEntry.EXPERT_EMAIL + " = ?";
        String[] selectionArgs = { email };

        Cursor cursor = db.query(ExpertContract.ExpertEntry.EXPERT_TABLE,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0){
            return true;
        }
        return false;
    }

    public String[] getLoggedExpertUser(String email) {
        String[] columns = {
                ExpertContract.ExpertEntry.EXPERT_EMAIL,
                ExpertContract.ExpertEntry.EXPERT_FULL_NAME,
                ExpertContract.ExpertEntry.EXPERT_FIELD
        };
        String[] selectionsArgs = { email };
        SQLiteDatabase db = getWritableDatabase();
        String query = ExpertContract.ExpertEntry.EXPERT_EMAIL + " = ?" ;

        Cursor c = db.query(ExpertContract.ExpertEntry.EXPERT_TABLE, columns, query,
                selectionsArgs, null,null,null);
        c.moveToFirst();
        String[] dbString = new String[3];
        if(c.getCount() != 0) {

            int column_fullname = c.getColumnIndex("expert_fullname");
            int column_email = c.getColumnIndex("expert_email");
            int column_field = c.getColumnIndex("expert_field");


            dbString[0] = c.getString(column_fullname);
            dbString[1] = c.getString(column_email);
            dbString[2] = c.getString(column_field);

        }
        c.close();
        db.close();
        return dbString;
    }

    public String[] getLoggedUser(String email) {
        String[] columns = {
                USER_EMAIL,
                USER_FULL_NAME
        };
        String[] selectionsArgs = { email };
        SQLiteDatabase db = getWritableDatabase();
        String query = USER_EMAIL+ " = ?" ;

        Cursor c = db.query(USER_TABLE, columns, query,
                selectionsArgs, null,null,null);
        c.moveToFirst();
        String[] dbString = new String[2];
        if(c.getCount() != 0) {

            int column_fullname = c.getColumnIndex("user_fullname");
            int column_email = c.getColumnIndex("user_email");


            dbString[0] = c.getString(column_fullname);
            dbString[1] = c.getString(column_email);

        }
        c.close();
        db.close();
        return dbString;
    }

    public List<Question> getAllQuestions(){
        // array of columns to fetch
        String [] columns = {

                QuestionsContract.QuestionEntry.QUESTION_ID,
                QuestionsContract.QuestionEntry.USER_EMAIL,
                QuestionsContract.QuestionEntry.QUESTION_DOMAIN,
                QuestionsContract.QuestionEntry.QUESTION_DETAIL,
                QuestionsContract.QuestionEntry.QUESTION_REPLY
        };
        //Sorting orders
        String sortOrder = QuestionsContract.QuestionEntry.USER_EMAIL + " ASC";
        List<Question> questionList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(QuestionsContract.QuestionEntry.QUESTION_TABLE, columns,
                null,
                null,
                null,
                null,
                sortOrder);
        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()){
            do{
                Question question = new Question();
                question.setQuestion_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(QuestionsContract.QuestionEntry.QUESTION_ID))));
                question.setUser_email(cursor.getString(cursor.getColumnIndex(QuestionsContract.QuestionEntry.USER_EMAIL)));
                question.setQuestion_domain(cursor.getString(cursor.getColumnIndex(QuestionsContract.QuestionEntry.QUESTION_DOMAIN)));
                question.setQuestion_detail(cursor.getString(cursor.getColumnIndex(QuestionsContract.QuestionEntry.QUESTION_DETAIL)));
                question.setQuestion_reply(cursor.getString(cursor.getColumnIndex(QuestionsContract.QuestionEntry.QUESTION_REPLY)));

                // Adding the complaints to list
                questionList.add(question);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return the list
        return questionList;
    }

    public List<Question> getMyQuestions(String email){
        // array of columns to fetch
        String [] columns = {

                QuestionsContract.QuestionEntry.QUESTION_ID,
                QuestionsContract.QuestionEntry.USER_EMAIL,
                QuestionsContract.QuestionEntry.QUESTION_DOMAIN,
                QuestionsContract.QuestionEntry.QUESTION_DETAIL,
                QuestionsContract.QuestionEntry.QUESTION_REPLY
        };
        //Sorting orders
        String sortOrder = QuestionsContract.QuestionEntry.USER_EMAIL + " ASC";
        List<Question> questionList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selection = QuestionsContract.QuestionEntry.USER_EMAIL + " = ?";
        String[] selectionArgs = { email };

        Cursor cursor = db.query(QuestionsContract.QuestionEntry.QUESTION_TABLE, columns,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);

        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()){
            do{
                Question question = new Question();
                question.setQuestion_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(QuestionsContract.QuestionEntry.QUESTION_ID))));
                question.setUser_email(cursor.getString(cursor.getColumnIndex(QuestionsContract.QuestionEntry.USER_EMAIL)));
                question.setQuestion_domain(cursor.getString(cursor.getColumnIndex(QuestionsContract.QuestionEntry.QUESTION_DOMAIN)));
                question.setQuestion_detail(cursor.getString(cursor.getColumnIndex(QuestionsContract.QuestionEntry.QUESTION_DETAIL)));
                question.setQuestion_reply(cursor.getString(cursor.getColumnIndex(QuestionsContract.QuestionEntry.QUESTION_REPLY)));


                // Adding the complaints to list
                questionList.add(question);
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        // return the list
        return questionList;
    }

    public int editQuestion(String domain, String message){
        //UPDATE USER_TABLE SET email='ikj@kjs.com' where email=?test
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(QuestionsContract.QuestionEntry.QUESTION_DETAIL, message);

        String[] args = {domain, message};
        int count = db.update(QuestionsContract.QuestionEntry.QUESTION_TABLE, values,
                QuestionsContract.QuestionEntry.QUESTION_DOMAIN + "=?" + " AND " +
                        QuestionsContract.QuestionEntry.QUESTION_DETAIL + "=?", args);

        return count;
    }

    public int replyQuestion(String message, String reply){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(QuestionsContract.QuestionEntry.QUESTION_REPLY, reply);
        String[] args = {message};
        int count = db.update(QuestionsContract.QuestionEntry.QUESTION_TABLE, values,
                QuestionsContract.QuestionEntry.QUESTION_DETAIL + "=?", args);

        return count;
    }

}
