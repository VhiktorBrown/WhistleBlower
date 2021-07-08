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
import com.chocolatedevelopers.whistleblower.data.model.Reports;
import com.chocolatedevelopers.whistleblower.data.model.Transactions;
import com.chocolatedevelopers.whistleblower.data.model.User;

import java.util.ArrayList;
import java.util.List;

import static android.provider.BaseColumns._ID;
import static com.chocolatedevelopers.whistleblower.data.local.SqlContract.*;

public class SqlConnector extends SQLiteOpenHelper {

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

    public void insertTransaction(Transactions transactions) {
        SQLiteDatabase database = instance.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Constants.COLUMN_AMOUNT, transactions.getAmount());
            contentValues.put(Constants.COLUMN_USER_ID, transactions.getUserId());
            contentValues.put(Constants.COLUMN_ITEM_BOUGHT, transactions.getItemBought());
            contentValues.put(Constants.COLUMN_DATE, transactions.getDate());
            contentValues.put(Constants.COLUMN_TRANSACTION_STATUS, transactions.getTransactionStatus().name());
            contentValues.put(Constants.COLUMN_TRANSACTION_TYPE, transactions.getTransactionType().name());

            database.insertWithOnConflict(TRANSACTION_TABLE, null, contentValues,
                    SQLiteDatabase.CONFLICT_REPLACE);
        }catch (Exception e) {
            e.printStackTrace();
        }
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

    public void insertCard(Card card) {
        SQLiteDatabase database = instance.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Constants.COLUMN_CARD_CVV, card.getCardCVV());
            contentValues.put(Constants.COLUMN_CARD_NUMBER, card.getCardNumber());
            contentValues.put(Constants.COLUMN_CARD_EXPIRY_DATE, card.getCardExpiryDate());

            database.insertWithOnConflict(CARD_TABLE, null, contentValues,
                    SQLiteDatabase.CONFLICT_REPLACE);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertLevel(Levels levels) {
        SQLiteDatabase database = instance.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Constants.COLUMN_CARD_ID, levels.getCardId());
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

    public void insertReports(Reports reports) {
        SQLiteDatabase database = instance.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Constants.COLUMN_MONTH, reports.getMonth());
            contentValues.put(Constants.COLUMN_START_BALANCE, reports.getStartBalance());
            contentValues.put(Constants.COLUMN_END_BALANCE, reports.getEndBalance());
            contentValues.put(Constants.COLUMN_PROFIT, reports.getProfit());
            contentValues.put(Constants.COLUMN_NUMBER_OF_FLAGGED_TRANSACTIONS, reports.getNumberOfFlaggedTransactions());

            database.insertWithOnConflict(TRANSACTION_TABLE, null, contentValues,
                    SQLiteDatabase.CONFLICT_REPLACE);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*********************************************
     * Returns the whole saved data pertaining All
     * @param
     * @return
     */
    public List<PdfMeta> getAllPdfMeta() {
        List<PdfMeta> pdfMetas = new ArrayList<>();
        String query = "SELECT * FROM exam_history_tbl ORDER BY time DESC";
        Cursor cursor = getReadableDatabase().rawQuery(query, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                PdfMeta pdfMeta = new PdfMeta();
                pdfMeta.setId(cursor.getInt(cursor.getColumnIndex(_ID)));
                pdfMeta.setTitle(cursor.getString(cursor.getColumnIndex(TableConstants.COLUMN_TITLE)));
                pdfMeta.setAuthor(cursor.getString(cursor.getColumnIndex(TableConstants.COLUMN_AUTHOR)));
                pdfMeta.setCreationDate(cursor.getString(cursor.getColumnIndex(TableConstants.COLUMN_CREATION_DATE)));
                pdfMeta.setCreator(cursor.getString(cursor.getColumnIndex(TableConstants.COLUMN_CREATOR)));
                pdfMeta.setKeywords(cursor.getString(cursor.getColumnIndex(TableConstants.COLUMN_KEYWORDS)));
                pdfMeta.setModDate(cursor.getString(cursor.getColumnIndex(TableConstants.COLUMN_MOD_DATE)));
                pdfMeta.setProducer(cursor.getString(cursor.getColumnIndex(TableConstants.COLUMN_PRODUCER)));
                pdfMeta.setTotalPages(cursor.getString(cursor.getColumnIndex(TableConstants.COLUMN_TOTAL_PAGES)));
                pdfMeta.setSubject(cursor.getString(cursor.getColumnIndex(TableConstants.COLUMN_SUBJECT)));

                pdfMetas.add(pdfMeta);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return pdfMetas;
    }
}
