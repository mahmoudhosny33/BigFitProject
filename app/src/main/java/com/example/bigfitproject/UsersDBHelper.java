package com.example.bigfitproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class UsersDBHelper extends SQLiteOpenHelper {
    private static String DBName = "UsersDatabase";
    SQLiteDatabase UsersDatabase;

    public UsersDBHelper(Context context) {
        super(context, DBName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user (id integer primary key autoincrement, firstname text, lastname text, username text, email text, password text, gender text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
        onCreate(db);
    }

    public void addUser(String firstname, String lastname, String username, String email, String password, String gender) {
        ContentValues row = new ContentValues();
        row.put("firstname", firstname);
        row.put("lastname", lastname);
        row.put("username", username);
        row.put("email", email);
        row.put("password", password);
        row.put("gender", gender);
        UsersDatabase = getWritableDatabase();
        UsersDatabase.insert("user", null, row);
        UsersDatabase.close();
    }

    public Cursor fetchAllUsers() {
        UsersDatabase = getReadableDatabase();
        String[] rowDetails = {"id", "firstname", "lastname", "username", "email", "password", "gender"};
        Cursor cursor = UsersDatabase.query("user", rowDetails, null, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        UsersDatabase.close();
        return cursor;
    }

    public Boolean correctPassword(String username, String password) {
        UsersDatabase = getReadableDatabase();
        String[] arg = {username};
        Cursor cursor = UsersDatabase.rawQuery("Select password from user where username like ?", arg);
        cursor.moveToFirst();

        if (password.equals(cursor.getString(0))) {
            UsersDatabase.close();
            return true;
        }
        UsersDatabase.close();
        return false;
    }

    public Boolean userExists(String username) {

        UsersDatabase = getReadableDatabase();
        String [] arg = {username};
        Cursor c = UsersDatabase.rawQuery("SELECT * FROM user WHERE username like ?" , arg);

        if (c.moveToFirst()) {
            UsersDatabase.close();
            return true;
        } else {
            UsersDatabase.close();
            return false;
        }
    }
}
        /*
            Cursor c = UsersDatabase.rawQuery("SELECT * FROM user where username= "+username, null);
            if(c.getCount() > 0)
            {
                c.close();
                return true;
            }
            else
            {
                c.close();
                return false;
            }


        //String [] arg = {username};
        //Cursor cursor = UsersDatabase.rawQuery("Select id from user where username like ?", arg);
        /*if(cursor != null)
        cursor.moveToFirst();
        else
        {
            UsersDatabase.close();
            return false;
        }
        UsersDatabase.close();
        return true;

        Cursor cursor = UsersDatabase.rawQuery("Select username from user", null);
        for(int i =0; i<cursor.getCount(); i++)
        {
            if(username.equals(cursor.getString(0)))
            {
                UsersDatabase.close();
                return true;
            }
            else
            {
                cursor.moveToNext();
            }
        }*/

