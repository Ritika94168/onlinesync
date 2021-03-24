package com.example.onlinesync;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.security.acl.Owner;
import java.util.ArrayList;
import java.util.List;

public class OwnerDBAdapter extends DatabaseHandler {



    public static final String TABLE_OWNER = "owner_detail";
    public static final String OWNER_ID = "id";
    public static final String OWNER_NAME = "entered_value";
    public static final String SYCNEDDATA = "sync_data";

    public OwnerDBAdapter(Context context) {
        super(context);
    }
    public int addOwner(OwnerListAd owner) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
//        values.put(KEY_ID, property.getId());
        values.put(OWNER_NAME, owner.getOwner_name()); // Contact Name

        // Inserting Row
        long rowInserted = db.insert(TABLE_OWNER, null, values);
        if (rowInserted != -1) {
            System.out.println("record inserted");
        } else {
            System.out.println(" no record inserted");
        }
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
        return (int) rowInserted;
    }
    public Cursor getAllOwner() {
        ArrayList<OwnerListAd> contactList = new ArrayList<OwnerListAd>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_OWNER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        return cursor;

    }
    public int dbSyncCount(){
        int count=0;
        String selectquery="Select * from owner_detail where sync_data=0";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectquery, null);
        count=cursor.getCount();
        db.close();
        return count;
    }


    public boolean updateOwner(String s) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("UPDATE owner_detail SET sync_data ='1' WHERE entered_value ='"+s+"'");
//        db.execSQL("UPDATE "+TABLE_OWNER+" SET sync_data ='1' WHERE "+ OWNER_NAME+" = "+"'"+s+"'");
        return true;
    }

    public boolean deleteOwner(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(TABLE_OWNER, OwnerDBAdapter.OWNER_ID + "=" + id, null) > 0;
    }

}
