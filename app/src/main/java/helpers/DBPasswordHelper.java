package helpers;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import models.Account;

public class DBPasswordHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "myprofilebox.db";
    // User table name
    private static final String TABLE_ACCOUNT = "account";
    // User Table Columns names
    private static final String COLUMN_ACCOUNT_ID = "account_id";
    private static final String COLUMN_ACCOUNT_TITLE = "account_title";
    private static final String COLUMN_ACCOUNT_TYPE = "account_type";
    private static final String COLUMN_ACCOUNT_USERNAME = "account_username";
    private static final String COLUMN_ACCOUNT_PASSWORD = "account_password";

    // create table sql query
    private String CREATE_ACCOUNT_TABLE = "CREATE TABLE " + TABLE_ACCOUNT + "("
            + COLUMN_ACCOUNT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_ACCOUNT_TITLE + " TEXT,"
            + COLUMN_ACCOUNT_TYPE + " TEXT,"
            + COLUMN_ACCOUNT_USERNAME + " TEXT,"
            + COLUMN_ACCOUNT_PASSWORD + " TEXT"
            + ")";
    // drop table sql query
    private String DROP_ACCOUNT_TABLE = "DROP TABLE IF EXISTS " + TABLE_ACCOUNT;
    /**
     * Constructor
     *
     * @param context
     */
    public DBPasswordHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ACCOUNT_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop User Table if exist
        db.execSQL(DROP_ACCOUNT_TABLE);
        // Create tables again
        onCreate(db);
    }

    public void addAccount(Account account) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ACCOUNT_TITLE, account.getTitle());
        values.put(COLUMN_ACCOUNT_TYPE, account.getAccountType());
        values.put(COLUMN_ACCOUNT_USERNAME, account.getUsername());
        values.put(COLUMN_ACCOUNT_PASSWORD, account.getPassword());

        // Inserting Row
        db.insert(TABLE_ACCOUNT, null, values);
        db.close();
    }
    @SuppressLint("Range")
    public List<Account> getAllAccounts() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_ACCOUNT_ID,
                COLUMN_ACCOUNT_TITLE,
                COLUMN_ACCOUNT_TYPE,
                COLUMN_ACCOUNT_USERNAME,
                COLUMN_ACCOUNT_PASSWORD
        };
        // sorting orders
        String sortOrder =
                COLUMN_ACCOUNT_ID + " ASC";
        List<Account> accountList = new ArrayList<Account>();
        SQLiteDatabase db = this.getReadableDatabase();
        // query the user table

        Cursor cursor = db.query(TABLE_ACCOUNT, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order
        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Account account = new Account();
                account.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ACCOUNT_ID))));
                account.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_ACCOUNT_TITLE)));
                account.setAccountType(cursor.getString(cursor.getColumnIndex(COLUMN_ACCOUNT_TYPE)));
                account.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN_ACCOUNT_USERNAME)));
                account.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_ACCOUNT_PASSWORD)));

                // Adding user record to list
                accountList.add(account);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return accounts list
        return accountList;
    }
}