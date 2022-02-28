package com.voting.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.AbstractWindowedCursor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB = "logindb.db";

    public DBHelper(Context context) {
        super(context, "logindb.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase mydb) {
    mydb.execSQL("create table users(username TEXT primary key, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase mydb, int oldVersion, int newVersion) {
    mydb.execSQL("drop table if exists users");
    }
    public boolean insertdata(String username, String password){
        SQLiteDatabase mydb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("usernmae",username);
        contentValues.put("password",password);
        long results = mydb.insert("users",null,contentValues);
        if(results==-1) return false;
        else
            return true;
    }
    public boolean checkuser(String username){
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("select * from users where username = ?",new String[]{username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
        }
    public boolean getusrpass(String username, String password){
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("select * from users where username=? and password=?",new String[] {username, password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
        }

    }


