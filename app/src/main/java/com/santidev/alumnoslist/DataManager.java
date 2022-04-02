package com.santidev.alumnoslist;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private static SQLiteDatabase db;

    public static final String TABLE_ROW_ID = "ID";
    public static final String TABLE_ROW_NOMBRE = "NOMBRE";
    public static final String TABLE_ROW_CARRERA = "CARRERA";
    public static final String TABLE_ROW_MATRICUALA = "MATRICULA";

    public static final String DB_NAME = "RegistroAlumnos";
    public static final int DB_VERSION = 1;
    public static final String TABLE_ALUMNOS = "Alumnos";

    public DataManager(Context context){
        MySqlOpenHelper helper = new MySqlOpenHelper(context);

        db = helper.getWritableDatabase();
    }

    public void insert(Alumno alumno){
        String query = "INSERT INTO "+
                        TABLE_ALUMNOS + " (" +
                        TABLE_ROW_NOMBRE + ", " +
                        TABLE_ROW_CARRERA + ", " +
                        TABLE_ROW_MATRICUALA + ") "+
                        "VALUES (" +
                        "'"+alumno.getNombre()+"'"+", "+
                        "'"+alumno.getCarrera()+"'"+", "+
                        "'"+alumno.getMatricula()+"'"+");";

        Log.i("insert()= ", query);
        db.execSQL(query);
    }

    public Cursor selectAll(){
        String query = "SELECT * FROM " + TABLE_ALUMNOS + ";";
        Cursor cursor = db.rawQuery(query, null);
        Log.i("selectAll() = ", query);
        return  cursor;
    }

    public List<Alumno> createListAlumnos(){
        List<Alumno> alumnos = new ArrayList<>();
        Cursor cursor = selectAll();
        while (cursor.moveToNext()){
            Alumno alumno = new Alumno(Integer.parseInt(cursor.getString(0)),cursor.getString(1), cursor.getString(2), cursor.getString(3));
            alumnos.add(alumno);
        }
        return alumnos;
    }

    public  void update(Alumno alumno){
        String query = "UPDATE " + TABLE_ALUMNOS + " SET "+
                        TABLE_ROW_NOMBRE + " = '" + alumno.getNombre()+ "'," +
                        TABLE_ROW_CARRERA + " = '" + alumno.getCarrera()+"'," +
                        TABLE_ROW_MATRICUALA + " = '"+alumno.getMatricula()+"' WHERE "+
                        TABLE_ROW_ID + " = " + alumno.getId()+";";
        Log.i("update", query);
        db.execSQL(query);
    }

    public static void delete(int id){
        String query = "DELETE FROM " + TABLE_ALUMNOS + " WHERE " + TABLE_ROW_ID + " = " + id + ";";
        Log.i("delete()= ", query);
        db.execSQL(query);
    }
}
