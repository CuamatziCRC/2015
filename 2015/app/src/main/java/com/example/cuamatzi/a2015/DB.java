package com.example.cuamatzi.a2015;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

public class DB extends SQLiteOpenHelper {


    DB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context,"Test.db", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //BASE de datos
        db.execSQL("CREATE TABLE STUDENTS (ID INTEGER PRIMARY KEY AUTOINCREMENT , FIRSTNAME TEXT UNIQUE,LASTNAME TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS STUDENTS;");
        onCreate(db);

    }

    void  insertar_estudiante(String nombre, String apellido){
        ContentValues contentValues = new ContentValues();
        contentValues.put("FIRSTNAME",nombre);
        contentValues.put("LASTNAME",apellido);
        this.getWritableDatabase().insertOrThrow("STUDENTS","",contentValues);
    }

    void eliminarestudiante(String nombre){

        this.getWritableDatabase().delete("STUDENTS","FIRSTNAME='"+nombre+"'",null);

    }

    void actualizarestudiante(String antiguonombre, String nuevonombre){

        this.getWritableDatabase().execSQL("UPDATE STUDENTS SET FIRSTNAME = '"+ nuevonombre + "'WHERE FIRSTNAME='"+antiguonombre+"'");

    }

    @SuppressLint("Recycle")
    void listarestudiante(TextView textView){

        @SuppressLint("Recycle")
        Cursor cursor;
        cursor = this.getReadableDatabase().rawQuery("SELECT * FROM STUDENTS",null);
        textView.setText("");
        while (cursor.moveToNext()){
            textView.append(cursor.getString(1)+" "+cursor.getString(2)+"\n");
        }

    }
}
