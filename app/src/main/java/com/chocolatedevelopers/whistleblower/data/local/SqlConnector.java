package com.chocolatedevelopers.whistleblower.data.local;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.chocolatedevelopers.whistleblower.data.model.Card;
import com.chocolatedevelopers.whistleblower.data.model.Levels;
import com.chocolatedevelopers.whistleblower.data.model.ReportDetails;
import com.chocolatedevelopers.whistleblower.data.model.TransactionDetails;
import com.chocolatedevelopers.whistleblower.data.model.User;

import java.util.ArrayList;

import static com.chocolatedevelopers.whistleblower.data.local.SqlContract.CARD_TABLE;
import static com.chocolatedevelopers.whistleblower.data.local.SqlContract.Constants;
import static com.chocolatedevelopers.whistleblower.data.local.SqlContract.Constants.COLUMN_BALANCE;
import static com.chocolatedevelopers.whistleblower.data.local.SqlContract.Constants.COLUMN_BUDGET_AMOUNT;
import static com.chocolatedevelopers.whistleblower.data.local.SqlContract.Constants.COLUMN_CARD_CVV;
import static com.chocolatedevelopers.whistleblower.data.local.SqlContract.Constants.COLUMN_CARD_EXPIRY_DATE;
import static com.chocolatedevelopers.whistleblower.data.local.SqlContract.Constants.COLUMN_CARD_ID;
import static com.chocolatedevelopers.whistleblower.data.local.SqlContract.Constants.COLUMN_CARD_NAME;
import static com.chocolatedevelopers.whistleblower.data.local.SqlContract.Constants.COLUMN_CARD_NUMBER;
import static com.chocolatedevelopers.whistleblower.data.local.SqlContract.Constants.COLUMN_IS_FLAGGED;
import static com.chocolatedevelopers.whistleblower.data.local.SqlContract.Constants.COLUMN_LEVEL_ID;
import static com.chocolatedevelopers.whistleblower.data.local.SqlContract.Constants.COLUMN_LEVEL_SALARY;
import static com.chocolatedevelopers.whistleblower.data.local.SqlContract.Constants.COLUMN_NAME;
import static com.chocolatedevelopers.whistleblower.data.local.SqlContract.Constants.COLUMN_PASSWORD;
import static com.chocolatedevelopers.whistleblower.data.local.SqlContract.Constants.COLUMN_PROFIT_EXPECTED;
import static com.chocolatedevelopers.whistleblower.data.local.SqlContract.Constants.COLUMN_TRANSACTION_LIMIT;
import static com.chocolatedevelopers.whistleblower.data.local.SqlContract.Constants.COLUMN_USERNAME;
import static com.chocolatedevelopers.whistleblower.data.local.SqlContract.DATABASE_NAME;
import static com.chocolatedevelopers.whistleblower.data.local.SqlContract.DATABASE_VERSION;
import static com.chocolatedevelopers.whistleblower.data.local.SqlContract.LEVELS_TABLE;
import static com.chocolatedevelopers.whistleblower.data.local.SqlContract.REPORTS_TABLE;
import static com.chocolatedevelopers.whistleblower.data.local.SqlContract.SQL_CREATE_CARD_TBL;
import static com.chocolatedevelopers.whistleblower.data.local.SqlContract.SQL_CREATE_LEVEL_TBL;
import static com.chocolatedevelopers.whistleblower.data.local.SqlContract.SQL_CREATE_REPORTS_TBL;
import static com.chocolatedevelopers.whistleblower.data.local.SqlContract.SQL_CREATE_TRANSACTION_TBL;
import static com.chocolatedevelopers.whistleblower.data.local.SqlContract.SQL_CREATE_USER_TBL;
import static com.chocolatedevelopers.whistleblower.data.local.SqlContract.TRANSACTION_TABLE;
import static com.chocolatedevelopers.whistleblower.data.local.SqlContract.USERS_TABLE;

public class SqlConnector extends SQLiteOpenHelper {

    //@TODO Save user's name and id to shared preference.

    @SuppressLint("StaticFieldLeak")
    private static SqlConnector instance;

    public synchronized static SqlConnector getInstance(Context context) {
        if (instance == null) {
            instance = new SqlConnector(context.getApplicationContext());
        }
        return instance;
    }

    public SqlConnector(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TRANSACTION_TBL);
        db.execSQL(SQL_CREATE_CARD_TBL);
        db.execSQL(SQL_CREATE_LEVEL_TBL);
        db.execSQL(SQL_CREATE_REPORTS_TBL);
        db.execSQL(SQL_CREATE_USER_TBL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TRANSACTION_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CARD_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + LEVELS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + REPORTS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE);
        onCreate(db);
    }

    public void insertTransaction(TransactionDetails transactions) {
        SQLiteDatabase database = instance.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_LEVEL_ID, transactions.getLevelId());
            contentValues.put(Constants.COLUMN_AMOUNT, transactions.getAmount());
            contentValues.put(Constants.COLUMN_NAME, transactions.getUsername());
            contentValues.put(Constants.COLUMN_ITEM_BOUGHT, transactions.getItem());
            contentValues.put(Constants.COLUMN_ITEM_QUANTITY, transactions.getQuantity());
            contentValues.put(Constants.COLUMN_DATE, transactions.getDate());
            contentValues.put(Constants.COLUMN_TIME, transactions.getTime());
            contentValues.put(Constants.COLUMN_DETAILS, transactions.getDetails());
            contentValues.put(Constants.COLUMN_IS_FLAGGED, transactions.getIsFlagged());


            database.insertWithOnConflict(TRANSACTION_TABLE, null, contentValues,
                    SQLiteDatabase.CONFLICT_REPLACE);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertTransactionList(ArrayList<TransactionDetails> transactionList){
        SQLiteDatabase database = instance.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        for(TransactionDetails transactions : transactionList){
            try {
                contentValues.put(COLUMN_LEVEL_ID, transactions.getLevelId());
                contentValues.put(Constants.COLUMN_AMOUNT, transactions.getAmount());
                contentValues.put(Constants.COLUMN_NAME, transactions.getUsername());
                contentValues.put(Constants.COLUMN_ITEM_BOUGHT, transactions.getItem());
                contentValues.put(Constants.COLUMN_ITEM_QUANTITY, transactions.getQuantity());
                contentValues.put(Constants.COLUMN_DATE, transactions.getDate());
                contentValues.put(Constants.COLUMN_TIME, transactions.getTime());
                contentValues.put(Constants.COLUMN_DETAILS, transactions.getDetails());
                contentValues.put(Constants.COLUMN_IS_FLAGGED, transactions.getIsFlagged());


                database.insertWithOnConflict(TRANSACTION_TABLE, null, contentValues,
                        SQLiteDatabase.CONFLICT_REPLACE);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<TransactionDetails> getAllTransactions(int levelId) {
        SQLiteDatabase database = this.getReadableDatabase();
        ArrayList<TransactionDetails> all = new ArrayList<>();
        String command = "SELECT * FROM " + TRANSACTION_TABLE + " WHERE " + COLUMN_LEVEL_ID + " = ? "  + " ORDER BY id DESC";
        Cursor cursor = database.rawQuery(command, new String[]{String.valueOf(levelId)});
        if(cursor.moveToFirst())
            do {
                TransactionDetails transaction = new TransactionDetails();
                transaction.setTransactionId(cursor.getInt(0));
                transaction.setLevelId(cursor.getInt(1));
                transaction.setUsername(cursor.getString(2));
                transaction.setAmount(cursor.getString(3));
                transaction.setItem(cursor.getString(4));
                transaction.setQuantity(cursor.getString(5));
                transaction.setDate(cursor.getString(6));
                transaction.setTime(cursor.getString(7));
                transaction.setDetails(cursor.getString(8));
                transaction.setIsFlagged(cursor.getInt(9));

                all.add(transaction);
            } while(cursor.moveToNext());
        return all;
    }

    /*
    This method would fetch either Flagged or Verified transactions
    depending on the parameter that is passed.
     */
    public ArrayList<TransactionDetails> getVerifiedOrDenied(int isFlagged, int levelId) {
        SQLiteDatabase database = this.getReadableDatabase();
        ArrayList<TransactionDetails> all = new ArrayList<>();
        String command = "SELECT * FROM " + TRANSACTION_TABLE + " WHERE " + COLUMN_IS_FLAGGED + " = ? AND " + COLUMN_LEVEL_ID + " = ?" + " ORDER BY id DESC";
        Cursor cursor = database.rawQuery(command, new String[]{String.valueOf(isFlagged), String.valueOf(levelId)});
        if(cursor.moveToFirst())
            do {
                TransactionDetails transaction = new TransactionDetails();
                transaction.setTransactionId(cursor.getInt(0));
                transaction.setUsername(cursor.getString(1));
                transaction.setAmount(cursor.getString(2));
                transaction.setItem(cursor.getString(3));
                transaction.setQuantity(cursor.getString(4));
                transaction.setDate(cursor.getString(5));
                transaction.setTime(cursor.getString(6));
                transaction.setDetails(cursor.getString(7));
                transaction.setIsFlagged(cursor.getInt(8));

                all.add(transaction);
            } while(cursor.moveToNext());
        return all;
    }


    public void insertUser(User user) {
        SQLiteDatabase database = instance.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Constants.COLUMN_LEVEL_ID, user.getLevelId());
            contentValues.put(Constants.COLUMN_USERNAME, user.getUsername());
            contentValues.put(Constants.COLUMN_PASSWORD, user.getPassword());

            database.insertWithOnConflict(USERS_TABLE, null, contentValues,
                    SQLiteDatabase.CONFLICT_REPLACE);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User getUser(String username, String password) {
        SQLiteDatabase database = this.getReadableDatabase();
        String command = "SELECT * FROM " + USERS_TABLE + " WHERE " + COLUMN_NAME + " = ? AND " + COLUMN_PASSWORD + " = ?";
        Cursor cursor = database.rawQuery(command, new String[]{String.valueOf(username), String.valueOf(password)});
        if(cursor != null)
            cursor.moveToFirst();
        return new User(cursor.getInt(0), cursor.getString(1),
                cursor.getString(2));
    }

    public void insertUserList(ArrayList<User> userList){
        SQLiteDatabase database = instance.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        for(User user : userList){
            try {
                contentValues.put(Constants.COLUMN_LEVEL_ID, user.getLevelId());
                contentValues.put(Constants.COLUMN_USERNAME, user.getUsername());
                contentValues.put(Constants.COLUMN_PASSWORD, user.getPassword());

                database.insertWithOnConflict(USERS_TABLE, null, contentValues,
                        SQLiteDatabase.CONFLICT_REPLACE);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //This method would query the data for list of users
    public ArrayList<User> getAllUsers() {
        SQLiteDatabase database = this.getReadableDatabase();
        ArrayList<User> all = new ArrayList<>();
        String command = "SELECT * FROM " + USERS_TABLE + " ORDER BY id DESC";
        Cursor cursor = database.rawQuery(command, null);
        if(cursor.moveToFirst())
            do {
                User user = new User();
                user.setUserId(cursor.getInt(0));
                user.setLevelId(cursor.getInt(1));
                user.setUsername(cursor.getString(2));
                user.setPassword(cursor.getString(3));

                all.add(user);
            } while(cursor.moveToNext());
        return all;
    }

    public void insertCard(Card card) {
        SQLiteDatabase database = instance.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Constants.COLUMN_CARD_CVV, card.getCardCVV());
            contentValues.put(COLUMN_CARD_NAME, card.getCardName());
            contentValues.put(Constants.COLUMN_CARD_NUMBER, card.getCardNumber());
            contentValues.put(Constants.COLUMN_CARD_EXPIRY_DATE, card.getCardExpiryDate());

            database.insertWithOnConflict(CARD_TABLE, null, contentValues,
                    SQLiteDatabase.CONFLICT_REPLACE);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Card getCard(int cardID) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(CARD_TABLE, new String[]{COLUMN_CARD_ID, COLUMN_CARD_NUMBER,
        COLUMN_CARD_CVV, COLUMN_CARD_EXPIRY_DATE, COLUMN_CARD_NAME}, COLUMN_CARD_ID, new String[]{String.valueOf(cardID)}, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        return new Card(cursor.getInt(0), cursor.getString(1),
                cursor.getString(2), cursor.getString(3),
                cursor.getString(4));
    }

    public void insertCardList(ArrayList<Card> cardList){
        SQLiteDatabase database = instance.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        for(Card card : cardList){
            try {
                contentValues.put(COLUMN_CARD_NAME, card.getCardName());
                contentValues.put(Constants.COLUMN_CARD_CVV, card.getCardCVV());
                contentValues.put(Constants.COLUMN_CARD_NUMBER, card.getCardNumber());
                contentValues.put(Constants.COLUMN_CARD_EXPIRY_DATE, card.getCardExpiryDate());

                database.insertWithOnConflict(CARD_TABLE, null, contentValues,
                        SQLiteDatabase.CONFLICT_REPLACE);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void insertLevel(Levels levels) {
        SQLiteDatabase database = instance.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_CARD_ID, levels.getCardId());
            contentValues.put(Constants.COLUMN_BUDGET_AMOUNT, levels.getBudgetAmount());
            contentValues.put(Constants.COLUMN_BALANCE, levels.getBalance());
            contentValues.put(Constants.COLUMN_PROFIT_EXPECTED, levels.getProfitExpected());
            contentValues.put(Constants.COLUMN_LEVEL_SALARY, levels.getLevelSalary());
            contentValues.put(COLUMN_TRANSACTION_LIMIT, levels.getTransactionLimit());

            database.insertWithOnConflict(LEVELS_TABLE, null, contentValues,
                    SQLiteDatabase.CONFLICT_REPLACE);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    //This method is primarily meant for updating balance whenever transaction is made
    public int editLevel(Levels levels) {

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_CARD_ID, levels.getCardId());
        contentValues.put(Constants.COLUMN_BUDGET_AMOUNT, levels.getBudgetAmount());
        contentValues.put(Constants.COLUMN_BALANCE, levels.getBalance());
        contentValues.put(Constants.COLUMN_PROFIT_EXPECTED, levels.getProfitExpected());
        contentValues.put(Constants.COLUMN_LEVEL_SALARY, levels.getLevelSalary());
        contentValues.put(COLUMN_TRANSACTION_LIMIT, levels.getTransactionLimit());

        return database.update(LEVELS_TABLE, contentValues, COLUMN_LEVEL_ID + " = ?", new String[]{String.valueOf(levels.getLevelId())});
    }


    //This method queries the levels table to get details for a single level using the level ID.
    public Levels getLevel(int levelId) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(LEVELS_TABLE, new String[]{COLUMN_LEVEL_ID, COLUMN_CARD_ID,
                COLUMN_BUDGET_AMOUNT, COLUMN_BALANCE, COLUMN_PROFIT_EXPECTED,
                COLUMN_LEVEL_SALARY, COLUMN_TRANSACTION_LIMIT},
                COLUMN_LEVEL_ID, new String[]{String.valueOf(levelId)}, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        return new Levels(cursor.getInt(0), cursor.getInt(1),
                cursor.getString(2), cursor.getString(3),
                cursor.getString(4), cursor.getString(5), cursor.getString(6));
    }

    public void insertLevelList(ArrayList<Levels> levelList){
        SQLiteDatabase database = instance.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        for (Levels levels : levelList){
            try {
                contentValues.put(COLUMN_CARD_ID, levels.getCardId());
                contentValues.put(Constants.COLUMN_BUDGET_AMOUNT, levels.getBudgetAmount());
                contentValues.put(Constants.COLUMN_BALANCE, levels.getBalance());
                contentValues.put(Constants.COLUMN_PROFIT_EXPECTED, levels.getProfitExpected());
                contentValues.put(Constants.COLUMN_LEVEL_SALARY, levels.getLevelSalary());

                database.insertWithOnConflict(LEVELS_TABLE, null, contentValues,
                        SQLiteDatabase.CONFLICT_REPLACE);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //This method would query the data for list of levels
    public ArrayList<Levels> getAllLevels() {
        SQLiteDatabase database = this.getReadableDatabase();
        ArrayList<Levels> all = new ArrayList<>();
        String command = "SELECT * FROM " + LEVELS_TABLE + " ORDER BY id DESC";
        Cursor cursor = database.rawQuery(command, null);
        if(cursor.moveToFirst())
            do {
                Levels levels = new Levels();
                levels.setLevelId(cursor.getInt(0));
                levels.setCardId(cursor.getInt(1));
                levels.setBudgetAmount(cursor.getString(2));
                levels.setBalance(cursor.getString(3));
                levels.setProfitExpected(cursor.getString(4));
                levels.setLevelSalary(cursor.getString(5));
                levels.setTransactionLimit(cursor.getString(6));

                all.add(levels);
            } while(cursor.moveToNext());
        return all;
    }

    public void insertReports(ReportDetails reports) {
        SQLiteDatabase database = instance.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Constants.COLUMN_MONTH, reports.getReportMonth());
            contentValues.put(Constants.COLUMN_START_BALANCE, reports.getStartBalance());
            contentValues.put(Constants.COLUMN_END_BALANCE, reports.getEndBalance());
            contentValues.put(Constants.COLUMN_PROFIT, reports.getProfit());
            contentValues.put(Constants.COLUMN_NUMBER_OF_FLAGGED_TRANSACTIONS, reports.getFlaggedTransactions());

            database.insertWithOnConflict(TRANSACTION_TABLE, null, contentValues,
                    SQLiteDatabase.CONFLICT_REPLACE);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertReportList(ArrayList<ReportDetails> reportList){
        SQLiteDatabase database = instance.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        for(ReportDetails reports : reportList){
            try {
                contentValues.put(Constants.COLUMN_MONTH, reports.getReportMonth());
                contentValues.put(Constants.COLUMN_START_BALANCE, reports.getStartBalance());
                contentValues.put(Constants.COLUMN_END_BALANCE, reports.getEndBalance());
                contentValues.put(Constants.COLUMN_PROFIT, reports.getProfit());
                contentValues.put(Constants.COLUMN_NUMBER_OF_FLAGGED_TRANSACTIONS, reports.getFlaggedTransactions());

                database.insertWithOnConflict(TRANSACTION_TABLE, null, contentValues,
                        SQLiteDatabase.CONFLICT_REPLACE);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
