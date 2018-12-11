package com.example.precious_lord.agsec;

import android.provider.BaseColumns;

public class ExpertContract {

    public static final class ExpertEntry implements BaseColumns {

        public static final String EXPERT_TABLE = "expert";
        public static final String EXPERT_ID = "expert_id";
        public static final String EXPERT_FULL_NAME = "expert_fullname";
        public static final String EXPERT_EMAIL = "expert_email";
        public static final String EXPERT_PHONE = "expert_phone";
        public static final String EXPERT_FIELD = "expert_field";
        public static final String EXPERT_PASSWORD = "expert_password";

    }
}
