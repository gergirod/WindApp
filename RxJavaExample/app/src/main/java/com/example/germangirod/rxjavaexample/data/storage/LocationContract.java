package com.example.germangirod.rxjavaexample.data.storage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import java.util.Date;

/**
 * Created by germangirod on 10/27/15.
 */
public abstract class LocationContract {

    static void saveCity(String cityId, Date date, SQLiteDatabase sqLiteDatabase) {
        ContentValues cityValues = new ContentValues();
        cityValues.put(LocationEntry.COL_CITY_ID, cityId);
        cityValues.put(LocationEntry.COL_DATE_ADD, date.toString());

        sqLiteDatabase.insert(LocationEntry.TABLE_NAME, null, cityValues);
    }

    static void deleteCity(String cityId, SQLiteDatabase sqLiteDatabase) {
        String selection = LocationEntry.COL_CITY_ID + " =?";
        String[] selectionArgs = { String.valueOf(cityId) };

        sqLiteDatabase.delete(LocationEntry.TABLE_NAME, selection, selectionArgs);
    }

    static void updateCity(String cityId, SQLiteDatabase sqLiteDatabase) {
        ContentValues cityValues = new ContentValues();

        cityValues.put(LocationEntry.COL_DATE_ADD, new Date().toString());

        String selection = LocationEntry.COL_CITY_ID + " =?";
        String[] selectionArgs = { String.valueOf(cityId) };

        sqLiteDatabase.update(LocationEntry.TABLE_NAME, cityValues, selection, selectionArgs);
    }

    static String getCitiesId(SQLiteDatabase sqLiteDatabase) {

        String citiesIds = "";
        String[] columns = { LocationEntry.COL_CITY_ID, LocationEntry.COL_DATE_ADD };

        Cursor c = sqLiteDatabase.query(LocationEntry.TABLE_NAME, columns, null, null, null, null, LocationEntry.COL_DATE_ADD + " DESC");

        while (c.moveToNext()) {
            citiesIds = citiesIds + String.valueOf(c.getInt(c.getColumnIndex(LocationEntry.COL_CITY_ID)));
            if (!c.isLast()) {
                citiesIds += ",";
            }
        }

        return citiesIds;
    }

    static int isSaved(String cityId, SQLiteDatabase sqLiteDatabase) {
        String[] columns = { LocationEntry.COL_CITY_ID };
        String where = LocationEntry.COL_CITY_ID + " =?";
        String[] selection = new String[] { cityId };

        Cursor cursor = sqLiteDatabase.query(LocationEntry.TABLE_NAME, columns, where, selection, null, null, null);
        int city = 0;
        while (cursor.moveToNext()) {
            city = cursor.getInt(cursor.getColumnIndex(LocationEntry.COL_CITY_ID));
        }

        return city;
    }

    public static abstract class LocationEntry implements BaseColumns {

        public static final String TABLE_NAME = "city";
        public static final String COL_CITY_ID = "city_id";
        public static final String COL_DATE_ADD = "date_add";
    }
}
