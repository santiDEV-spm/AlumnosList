package com.santidev.alumnoslist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class MySqlOpenHelper extends SQLiteOpenHelper {

    public MySqlOpenHelper(@Nullable Context context) {
        super(context, DataManager.DB_NAME, null, DataManager.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String newTablesQuery = "CREATE TABLE " +
                                DataManager.TABLE_ALUMNOS + " (" +
                                DataManager.TABLE_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
                                DataManager.TABLE_ROW_NOMBRE + " TEXT NOT NULL, " +
                                DataManager.TABLE_ROW_CARRERA + " TEXT NOT NULL, " +
                                DataManager.TABLE_ROW_MATRICUALA + " TEXT NOT NULL);";
        Log.i("Creating DataBase", newTablesQuery);
        sqLiteDatabase.execSQL(newTablesQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
