package com.example.precious_lord.agsec;

import android.provider.BaseColumns;

public class QuestionsContract {

    public static final class QuestionEntry implements BaseColumns {

        public static final String QUESTION_TABLE = "question";
        public static final String QUESTION_ID = "question_id";
        public static final String USER_EMAIL = "user_email";
        public static final String QUESTION_DOMAIN = "question_domain";
        public static final String QUESTION_DETAIL = "question_detail";
        public static final String QUESTION_REPLY = "question_reply";

    }
}
