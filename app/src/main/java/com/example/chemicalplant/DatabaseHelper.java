package com.example.chemicalplant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String Database_NAME = "ChemicalCompanies.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "companies";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME =  "company_name";
    private static final String COLUMN_ADDRESS = "company_address";
    private static final String COLUMN_YEAR = "year_established";
    private static final String COLUMN_CHEMICAL = "company_chemical";


    public DatabaseHelper(@Nullable Context context) {
        super(context, Database_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "Create Table " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_ADDRESS + " TEXT, " +
                        COLUMN_YEAR + " INTEGER, " +
                        COLUMN_CHEMICAL + " TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME );
        onCreate(db);


    }

    void addCompany(String name, String address, int year, String chemical){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();


        cv.put(COLUMN_NAME,name);
        cv.put(COLUMN_ADDRESS,address);
        cv.put(COLUMN_YEAR,year);
        cv.put(COLUMN_CHEMICAL,chemical);
        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context,"Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor= db.rawQuery(query, null);
        }
        return cursor;

    }

    void updateData(String row_id, String name, String address,String year, String chemical){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_ADDRESS, address);
        cv.put(COLUMN_YEAR, year);
        cv.put(COLUMN_CHEMICAL, chemical);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Update.", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(context, "Successfully Updated!", Toast.LENGTH_SHORT).show();
        }

    }
}