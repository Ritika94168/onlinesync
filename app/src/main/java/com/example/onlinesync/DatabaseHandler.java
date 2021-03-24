package com.example.onlinesync;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "listmanager";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PropertyDBAdapter.TABLE_PROPERTY);

//
//
//        String CREATE_RENT_TABLE = "CREATE TABLE " + RentDBAdapter.TABLE_RENT + "("
//                + RentDBAdapter.KEY_ID + " INTEGER PRIMARY KEY," + RentDBAdapter.KEY_NAME + " TEXT,"
//                + RentDBAdapter.KEY_Address1 + " TEXT" + "," + RentDBAdapter.KEY_Address2 + " TEXT," + RentDBAdapter.KEY_StartPeriod + " TEXT,"+RentDBAdapter.KEY_EndPeriod + " TEXT,"+ RentDBAdapter.KEY_RentAmount +" TEXT,"+ RentDBAdapter.KEY_AdditionalInfo +" TEXT,"+ RentDBAdapter.PROPERTY_ID +" TEXT)";
//        sqLiteDatabase.execSQL(CREATE_RENT_TABLE);
        String CREATE_OWNER_TABLE = "CREATE TABLE " + OwnerDBAdapter.TABLE_OWNER + "("
                + OwnerDBAdapter.OWNER_ID + " INTEGER PRIMARY KEY," + OwnerDBAdapter.OWNER_NAME + " TEXT,"
                + OwnerDBAdapter.SYCNEDDATA + " INTEGER  DEFAULT 0" + ")";
        sqLiteDatabase.execSQL(CREATE_OWNER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + OwnerDBAdapter.TABLE_OWNER);

//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RentDBAdapter.TABLE_RENT);
        // Create tables again
        onCreate(sqLiteDatabase);

    }

//
//    // code to update the single contact
//    public int updateContact(Property contact) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
////        values.put(KEY_NAME, contact.getName());
////        values.put(KEY_PH_NO, contact.getPhoneNumber());
//
//        // updating row
//        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
////                new String[] { String.valueOf(contact.getID()) });
//    }
//
//    // Deleting single contact
//    public void deleteContact(Property contact) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
//                new String[] { String.valueOf(contact.getID()) });
//        db.close();
//    }
//
//    // Getting contacts Count
//    public int getContactsCount() {
//        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(countQuery, null);
//        cursor.close();
//
//        // return count
//        return cursor.getCount();
//    }
}
