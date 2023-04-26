package com.example.mobass1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

   private static final String TAG = "Databasehelper";

   private static final String TABLE_NAME = "COVID";
   private static final String COL1 = "Heartrate";
   private static final String COL2 = "Respiratory";
   private static final String COL3 = "Nausea";
   private static final String COL4 = "Headache";
   private static final String COL5 = "Diarrhea";
   private static final String COL6 = "Sore Throat";
   private static final String COL7 = "Fever";
   private static final String COL8 = "Muscle Ache";
   private static final String COL9 = "Loss of smell";
   private static final String COL10 = "Cough";
   private static final String COL11 = "Breath";
   private static final String COL12 = "Tired";

    public DatabaseHelper(Context context) {
        super(context, "covid", null, 1);
    }

//public DatabaseHelper() {
    //    super(context , TABLE_NAME, null,  1);
    //}

    //public DatabaseHelper(GlobalClass context){
    //    super(context,
    //            TABLE_NAME,
    //            null,
    //            1);
    //}

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(" + COL1 + "float," + COL2 + "float," + COL3 + "float," + COL4 + "float," + COL5 + "float," + COL6 + "float," + COL7 + "float," + COL8 + "float," + COL9 + "float," + COL10 + "float," + COL11 + "float," + COL12 + "float);";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public Cursor getData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = sqLiteDatabase.rawQuery(query, null);
        return data;
    }

    public boolean addData1(float item1){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, item1);

        Log.d(TAG, "addData: Adding" + item1 + "to" + TABLE_NAME);

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean addData12(float item1){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL12, item1);

        Log.d(TAG, "addData: Adding" + item1 + "to" + TABLE_NAME);

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean addData3(float item1){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL3, item1);

        Log.d(TAG, "addData: Adding" + item1 + "to" + TABLE_NAME);

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean addData4(float item1){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL4, item1);

        Log.d(TAG, "addData: Adding" + item1 + "to" + TABLE_NAME);

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean addData5(float item1){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL5, item1);

        Log.d(TAG, "addData: Adding" + item1 + "to" + TABLE_NAME);

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean addData6(float item1){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL6, item1);

        Log.d(TAG, "addData: Adding" + item1 + "to" + TABLE_NAME);

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean addData7(float item1){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL7, item1);

        Log.d(TAG, "addData: Adding" + item1 + "to" + TABLE_NAME);

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean addData8(float item1){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL8, item1);

        Log.d(TAG, "addData: Adding" + item1 + "to" + TABLE_NAME);

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean addData9(float item1){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL9, item1);

        Log.d(TAG, "addData: Adding" + item1 + "to" + TABLE_NAME);

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean addData10(float item1){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL10, item1);

        Log.d(TAG, "addData: Adding" + item1 + "to" + TABLE_NAME);

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean addData11(float item1){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL11, item1);

        Log.d(TAG, "addData: Adding" + item1 + "to" + TABLE_NAME);

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        if(result == -1){
            return false;
        } else {
            return true;
        }
    }
}
