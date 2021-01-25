package com.example.myloginpagewithdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "userDetails.db";
    private static final String TABLE_NAME = "user_details";
    private static final String ID = "Id";
    private static final String NAME = "Name";
    private static final String EMAIL = "Email";
    private static final String USERNAME = "Username";
    private static final String PASSWORD = "Password";
    private static final int VERSION_NUMBER = 2;

    private  Context context;

    private static  final String CREATE_TABLE = "CREATE TABLE  "+TABLE_NAME+"( "+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+ " VARCHAR(255) NOT NULL, "+EMAIL+" TEXT NOT NULL, "+USERNAME+" TEXT NOT NULL, "+ PASSWORD +" TEXT NOT NULL)";
    private static  final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,  null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL(CREATE_TABLE);
            Toast.makeText(context, "onCreate is Called", Toast.LENGTH_LONG).show();
        }
        catch (Exception ex) {
            Toast.makeText(context, "Exception : " + ex, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {
            Toast.makeText(context, "on Upgrade is Called", Toast.LENGTH_LONG).show();
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }
        catch (Exception ex) {
            Toast.makeText(context, "Exception : " + ex, Toast.LENGTH_LONG).show();
        }
    }

    public long insertData(UserDetails userDetails) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, userDetails.getName());
        contentValues.put(EMAIL, userDetails.getEmail());
        contentValues.put(USERNAME, userDetails.getUsername());
        contentValues.put(PASSWORD, userDetails.getPassword());

        long rowId = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        return rowId;
    }

    public Boolean findPassword(String userName, String password) {

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        Boolean result = false;

        if(cursor.getCount()==0) {
            Toast.makeText(context, "No Data is found", Toast.LENGTH_LONG).show();
        }
        else {
            while (cursor.moveToNext()) {
                String user = cursor.getString(3);
                String pass = cursor.getString(4);

                if(user.equals(userName) && pass.equals(password)) {
                    result = true;
                    break;
                }
                else {

                }
            }
        }

        return  result;
    }
}
