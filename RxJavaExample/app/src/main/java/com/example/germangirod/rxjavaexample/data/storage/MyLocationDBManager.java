package com.example.germangirod.rxjavaexample.data.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.germangirod.rxjavaexample.data.storage.LocationContract.LocationEntry;
import java.util.Date;

/**
 * Created by germangirod on 10/22/15.
 */
public class MyLocationDBManager extends SQLiteOpenHelper {

    private static final String DB_NAME = "WindApp.db";
    private static final int version = 1;
    private SQLiteDatabase sqLiteDatabase;

    public MyLocationDBManager(Context context) {
        super(context, DB_NAME, null, version);
    }

    @Override public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + LocationEntry.TABLE_NAME +
                "(" + LocationEntry.COL_CITY_ID + " Integer," +
                "" + LocationEntry.COL_DATE_ADD + " DATE );";

        db.execSQL(sql);
    }

    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + LocationEntry.TABLE_NAME;
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void saveCity(String cityId) {
        sqLiteDatabase = getWritableDatabase();
        Date date = new Date();
        if (sqLiteDatabase != null) {
            if (!isSaved(cityId)) {
                LocationContract.saveCity(cityId, date, sqLiteDatabase);
            }
        }
    }

    public void deleteCity(String cityId) {
        if (sqLiteDatabase != null) {
            LocationContract.deleteCity(cityId, sqLiteDatabase);
        }
    }

    public void updateCity(String city) {
        sqLiteDatabase = getWritableDatabase();
        if (sqLiteDatabase != null) {
            LocationContract.updateCity(city, sqLiteDatabase);
        }
    }

    public String getCityIds() {
        sqLiteDatabase = getReadableDatabase();
        if (sqLiteDatabase != null) {
            return LocationContract.getCitiesId(sqLiteDatabase);
        }

        return null;
    }

    public boolean isSaved(String cityId) {
        int city = 0;
        sqLiteDatabase = getReadableDatabase();
        if (sqLiteDatabase != null) {
            city = LocationContract.isSaved(cityId, sqLiteDatabase);
        }

        if (city == 0) {
            return false;
        }
        return true;
    }
}
