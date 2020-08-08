package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SQLHelper extends SQLiteOpenHelper {
    private static final String TAG = "SQLHelper";
    static String DB_NAME = "Contact.db";
    static String DB_TABLE = "Contact";
    static int DB_VERSION = 1;
    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;
    public SQLHelper(@Nullable Context context) {
        super( context, DB_NAME, null, DB_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = String.format( "CREATE TABLE '%s' ("+
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
                +"avt INTEGER,"
                +"name Text,"
                +"phoneNumber Text)"
                ,DB_TABLE);
        db.execSQL( query );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion!=newVersion){
            db.execSQL( "DROP TABLE IF EXISTS "+DB_TABLE );
            onCreate( db );
        }
    }
    public void insertContact(Contact contact){
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues(  );
        contentValues.put( "avt",contact.getImgAvatar() );
        contentValues.put( "name",contact.getName() );
        contentValues.put( "phoneNumber",contact.getPhoneNumber() );
        sqLiteDatabase.insert( DB_TABLE,null,contentValues );
    }
    public boolean deleteContact(String name){
        sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete( DB_TABLE,"name = ?",new String[]{name} ) >=0;
    }
    public boolean updateContact(String name, Contact new_Contact){
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues(  );
        contentValues.put( "avt",new_Contact.getImgAvatar());
        contentValues.put( "name",new_Contact.getName());
        contentValues.put( "phoneNumber",new_Contact.getPhoneNumber());
        return sqLiteDatabase.update( DB_TABLE, contentValues,"name = ?",new String[]{name}) >=0;
    }
    public ArrayList<Contact> gettAllContact(){
        ArrayList<Contact> contacts = new ArrayList<>(  );
        sqLiteDatabase = getWritableDatabase();
        Contact contact;
        Cursor cursor = sqLiteDatabase.query( false, DB_TABLE,null,null,null
        ,null,null,null,null);
        while (cursor.moveToNext()){
            String name = cursor.getString( cursor.getColumnIndex( "name" ) );
            String phoneNumber = cursor.getString( cursor.getColumnIndex( "phoneNumber" ) );
            int img = cursor.getInt( cursor.getColumnIndex( "avt" ) );
            contact = new Contact( img,name,phoneNumber );
            contacts.add( contact );
        }
        return contacts;
    }
}
