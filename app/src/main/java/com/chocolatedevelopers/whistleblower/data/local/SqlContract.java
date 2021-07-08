package com.chocolatedevelopers.whistleblower.data.local;

import android.provider.BaseColumns;

import static android.provider.BaseColumns._ID;

//
// Created by  on 7/7/2021.
//
public final class SqlContract {
    public SqlContract() {
    }


    public static final String DATABASE_NAME = "whistle.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TRANSACTION_TABLE = "transaction_tbl";
    public static final String USERS_TABLE = "users_tbl";
    public static final String LEVELS_TABLE = "levels_tbl";
    public static final String REPORTS_TABLE = "reports_tbl";
    public static final String CARD_TABLE = "card_tbl";

    public static class Constants implements BaseColumns {
        public static final String COLUMN_USER_ID = "user_id";
        public static final String COLUMN_AMOUNT = "amount";
        public static final String COLUMN_ITEM_BOUGHT = "item_bought";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_TRANSACTION_STATUS = "transaction_status";
        public static final String COLUMN_TRANSACTION_TYPE = "transaction_type";
        public static final String COLUMN_LEVEL_ID = "level_id";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_CARD_ID = "card_id";
        public static final String COLUMN_BUDGET_AMOUNT = "budget_amount";
        public static final String COLUMN_BALANCE = "balance";
        public static final String COLUMN_PROFIT_EXPECTED = "profit_expected";
        public static final String COLUMN_LEVEL_SALARY = "level_salary";
        public static final String COLUMN_CARD_NUMBER = "card_number";
        public static final String COLUMN_CARD_CVV = "card_cvv";
        public static final String COLUMN_CARD_EXPIRY_DATE = "card_expiry_date";
        public static final String COLUMN_REPORTS_ID = "reports_id";
        public static final String COLUMN_MONTH = "month";
        public static final String COLUMN_START_BALANCE = "start_balance";
        public static final String COLUMN_END_BALANCE = "end_balance";
        public static final String COLUMN_PROFIT = "profit";
        public static final String COLUMN_NUMBER_OF_FLAGGED_TRANSACTIONS =
                "number_of_flagged_transactions";

    }

    public static final String SQL_CREATE_TRANSACTION_TBL = "CREATE TABLE transactions_tbl ( " +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Constants.COLUMN_USER_ID + " INTEGER, " +
            Constants.COLUMN_AMOUNT + " CHAR, " +
            Constants.COLUMN_ITEM_BOUGHT + " CHAR, " +
            Constants.COLUMN_DATE + " CHAR, " +
            Constants.COLUMN_TRANSACTION_STATUS + " CHAR, " +
            Constants.COLUMN_TRANSACTION_TYPE + " CHAR " +
            " )";

    public static final String SQL_CREATE_USER_TBL = "CREATE TABLE users_tbl ( " +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Constants.COLUMN_LEVEL_ID + " INTEGER, " +
            Constants.COLUMN_USERNAME + " CHAR, " +
            Constants.COLUMN_PASSWORD + " CHAR " +
            " )";

    public static final String SQL_CREATE_LEVEL_TBL = "CREATE TABLE levels_tbl ( " +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Constants.COLUMN_CARD_ID + " INTEGER, " +
            Constants.COLUMN_BUDGET_AMOUNT + " CHAR, " +
            Constants.COLUMN_BALANCE + " CHAR, " +
            Constants.COLUMN_PROFIT_EXPECTED + " CHAR, " +
            Constants.COLUMN_LEVEL_SALARY + " CHAR " +
            " )";

    public static final String SQL_CREATE_CARD_TBL = "CREATE TABLE card_tbl ( " +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Constants.COLUMN_CARD_NUMBER + " CHAR, " +
            Constants.COLUMN_CARD_CVV + " CHAR, " +
            Constants.COLUMN_CARD_EXPIRY_DATE + " CHAR " +
            " )";

    public static final String SQL_CREATE_REPORTS_TBL = "CREATE TABLE reports_tbl ( " +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Constants.COLUMN_MONTH + " CHAR, " +
            Constants.COLUMN_START_BALANCE + " CHAR, " +
            Constants.COLUMN_END_BALANCE + " CHAR, " +
            Constants.COLUMN_PROFIT + " CHAR, " +
            Constants.COLUMN_NUMBER_OF_FLAGGED_TRANSACTIONS + " CHAR " +
            " )";
}
