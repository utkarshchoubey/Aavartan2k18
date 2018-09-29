package com.technocracy.app.aavartan.helper;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.technocracy.app.aavartan.api.User;

/**
 * Created by nsn on 9/15/2015.
 */
public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 4;

    // Database Name
    private static final String DATABASE_NAME = "android_api";

    // Login table name
    private static final String TABLE_LOGIN = "login";

    // Login Table Columns names
    private static final String USER_ID = "id";
    private static final String FIRST_NAME = "fname";
    private static final String LAST_NAME = "lname";
    private static final String EMAIL = "email";
    private static final String PHONE = "phone";
    private static final String MEMBER_SINCE = "created_at";
    private static final String COLLEGE_NAME = "college";
    private static final String COUNT_EVENT_REGISTERED = "count_event_registered";

    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_LOGIN + "("
                + USER_ID + " INTEGER PRIMARY KEY," + FIRST_NAME + " TEXT,"
                + LAST_NAME + " TEXT,"
                + EMAIL + " TEXT UNIQUE,"
                + PHONE + " TEXT,"
                + COLLEGE_NAME + " TEXT,"
                + MEMBER_SINCE + " TEXT,"
                + COUNT_EVENT_REGISTERED + " INTEGER" + ")";
        db.execSQL(CREATE_LOGIN_TABLE);

        Log.d(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);

        // Create tables again
        onCreate(db);
    }

    /**
     * Storing user details in database
     * */
    public void addUser(int user_id, String firstname, String lastname, String email,String phone,String college,String member_since,int count_event_registered) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_ID, user_id); // Name
        values.put(FIRST_NAME, firstname); // Email
        values.put(LAST_NAME, lastname); // Email
        values.put(EMAIL, email); // Created At
        values.put(PHONE, phone);
        values.put(COLLEGE_NAME, college);
        values.put(MEMBER_SINCE,member_since);
        values.put(COUNT_EVENT_REGISTERED, count_event_registered);
        // Inserting Row
        long id = db.insert(TABLE_LOGIN, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New user inserted into sqlite: " + id);
    }


    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_ID, user.getUser_id());
        values.put(FIRST_NAME, user.getFirst_name());
        values.put(LAST_NAME, user.getLast_name());
        values.put(EMAIL, user.getEmail());
        values.put(PHONE, user.getPhone());
        values.put(COLLEGE_NAME, user.getCollege());
        values.put(MEMBER_SINCE, user.getMember_since());
        values.put(COUNT_EVENT_REGISTERED, user.getcount_event_registered());

        // updating row
        db.update(TABLE_LOGIN, values, USER_ID + " = ?",
                new String[]{String.valueOf(user.getUser_id())});
    }

    /**
     * Getting user data from database
     * */

    public User getUser() {
        User user = new User();
        String selectQuery = "SELECT  * FROM " + TABLE_LOGIN;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        if (cursor != null) {
            cursor.moveToFirst();
            user.setUser_id(cursor.getInt(0));
            user.setFirst_name(cursor.getString(1));
            user.setLast_name(cursor.getString(2));
            user.setEmail(cursor.getString(3));
            user.setPhone(cursor.getString(4));
            user.setCollege(cursor.getString(5));
            user.setMember_since(cursor.getString(6));
            user.setcount_event_registered(cursor.getInt(7));
            cursor.close();
        }
        db.close();

        return user;
    }


    /**
     * Getting user login status return true if rows are there in table
     * */
    public int getRowCount() {
        String countQuery = "SELECT  * FROM " + TABLE_LOGIN;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        db.close();
        cursor.close();

        // return row count
        return rowCount;
    }

    public void updateeventscount(User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(COUNT_EVENT_REGISTERED,user.getcount_event_registered());
        db.update(TABLE_LOGIN,value,USER_ID + "=?",new String[]{String.valueOf(user.getUser_id())});

    }

    /**
     * Re crate database Delete all tables and create them again
     * */
    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_LOGIN, null, null);
        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");
    }

}
