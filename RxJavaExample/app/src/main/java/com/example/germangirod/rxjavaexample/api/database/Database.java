package com.example.germangirod.rxjavaexample.api.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by germangirod on 5/20/15.
 */
public class Database extends SQLiteOpenHelper {

    private String name="hola";
    private SQLiteDatabase sqLiteDatabase;
    private Cursor cursor;
    private String[] TABLE_COLUMNS={"id_user","last_visited"};

    public Database(Context context) {
        super(context, "test", null, 1);
    }

    @Override public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS " + name +
                "(id_user Integer," +
                "last_visited DATETIME);";

        db.execSQL(sql);

    }

    public void save(int id){
        if(checkIfServiceProviderExist(id)){
            updateThis(id);
        }else{
            savethis(id);
        }
    }

    private boolean checkIfServiceProviderExist(int id) {

        sqLiteDatabase = getReadableDatabase();

        cursor = sqLiteDatabase.query(name, TABLE_COLUMNS, "id_user=?", new String[] { Integer.toString(id) }, null, null, null);

        if (cursor.getCount() <= 0) {
            return false;
        }
        cursor.close();
        return true;
    }

    public void getThis(){

        cursor = sqLiteDatabase.query(name, TABLE_COLUMNS, null, null, null, null,
                "last_visited DESC");

        if (cursor.moveToFirst()) {
            do {
                String s = cursor.getString(0);
                String s1=cursor.getString(1);
                Log.e("me devuelve esto ", "mirar "+s+" "+s1);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    public void updateThis(int id){
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        ContentValues values = new ContentValues();
        values.put("last_visited", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        sqLiteDatabase.update(name, values, "id_user=?", new String[] { Integer.toString(id) });
        //sqLiteDatabase.close();
        //sqLiteDatabase.endTransaction();
    }

    public void savethis(int id){

        sqLiteDatabase = getWritableDatabase();
//sqLiteDatabase.beginTransaction();
        if (sqLiteDatabase != null) {

            ContentValues values = new ContentValues();
            values.put("id_user", id);
            values.put("last_visited", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

            sqLiteDatabase.insert(name, null, values);
            //sqLiteDatabase.close();
            //sqLiteDatabase.endTransaction();
        }
    }

    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
