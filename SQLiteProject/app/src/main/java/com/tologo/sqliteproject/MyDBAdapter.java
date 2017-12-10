package com.tologo.sqliteproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by tologo.
 */



public class MyDBAdapter {

    private EditText ciclo;
    private EditText curso;

    // Definiciones y constantes
    private static final String DATABASE_NAME = "dbescuela.db";
    private static final String DATABASE_TABLE1 = "estudiantes";
    private static final String DATABASE_TABLE2 = "profesores";
    private static final int DATABASE_VERSION = 1;

    private static final String NOMBRE = "nombre";
    private static final String EDAD = "edad";
    private static final String CICLO = "ciclo";
    private static final String CURSO = "curso";
    private static final String NOTA = "nota";
    private static final String DESPACHO = "despacho";

    private static final String DATABASE_CREATE1 = "CREATE TABLE "+ DATABASE_TABLE1 +" (_id integer primary key autoincrement,nombre text, edad integer, ciclo text, curso text, nota integer);";
    private static final String DATABASE_CREATE2 = "CREATE TABLE "+ DATABASE_TABLE2 +" (_id integer primary key autoincrement,nombre text, edad integer, ciclo text, curso text, despacho text);";
    private static final String DATABASE_DROP1 = "DROP TABLE1 IF EXISTS "+ DATABASE_TABLE1 +";";
    private static final String DATABASE_DROP2 = "DROP TABLE2 IF EXISTS "+ DATABASE_TABLE2 +";";

    // Contexto de la aplicación que usa la base de datos
    private final Context context;
    // Clase SQLiteOpenHelper para crear/actualizar la base de datos
    private MyDbHelper dbHelper;
    // Instancia de la base de datos
    private SQLiteDatabase db;

    public MyDBAdapter (Context c){
        context = c;
        dbHelper = new MyDbHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        //OJO open();
    }

    public void open(){

        try{
            db = dbHelper.getWritableDatabase();
        }catch(SQLiteException e){
            db = dbHelper.getReadableDatabase();
        }

    }

    public void insertarAlumno(String n, int e, String c, String cu, int no){
        //Creamos un nuevo registro de valores a insertar
        ContentValues newValues = new ContentValues();
        //Asignamos los valores de cada campo
        newValues.put(NOMBRE,n);
        newValues.put(EDAD,e);
        newValues.put(CICLO,c);
        newValues.put(CURSO,cu);
        newValues.put(NOTA,no);
        db.insert(DATABASE_TABLE1,null,newValues);
    }

    public void insertarProfesor(String n, int e, String c, String cu, String d){
        //Creamos un nuevo registro de valores a insertar
        ContentValues newValues = new ContentValues();
        //Asignamos los valores de cada campo
        newValues.put(NOMBRE,n);
        newValues.put(EDAD,e);
        newValues.put(CICLO,c);
        newValues.put(CURSO,cu);
        newValues.put(DESPACHO,d);
        db.insert(DATABASE_TABLE2,null,newValues);
    }

    // Método para recuperar todos los alumnos
    public ArrayList<String> recuperarTodoAlumnos(){
        ArrayList<String> alumnos = new ArrayList<String>();
        //Recuperamos en un cursor la consulta realizada
        Cursor cursor = db.query(DATABASE_TABLE1,null,null,null,null,null,null);
        //Recorremos el cursor
        if (cursor != null && cursor.moveToFirst()){
            do{
            // (1) y (2) hacen referencia a la columna
                alumnos.add(cursor.getString(1)+", "+cursor.getString(2)+", "+cursor.getString(3)+", "+cursor.getString(4)+", "+cursor.getString(5));
            }while (cursor.moveToNext());
        }
        return alumnos;
    }

    // Método para recuperar todos los profesores
    public ArrayList<String> recuperarTodoProfesores(){
        ArrayList<String> profesores = new ArrayList<String>();
        //Recuperamos en un cursor la consulta realizada
        Cursor cursor = db.query(DATABASE_TABLE2,null,null,null,null,null,null);
        //Recorremos el cursor
        if (cursor != null && cursor.moveToFirst()){
            do{
                // (1) y (2) hacen referencia a la columna
                profesores.add(cursor.getString(1)+", "+cursor.getString(2)+", "+cursor.getString(3)+", "+cursor.getString(4)+", "+cursor.getString(5));
            }while (cursor.moveToNext());
        }
        return profesores;
    }


    // Método para recuperar alumnos por ciclo

    public ArrayList<String> recuperarAlumnosCiclo(String arg){

        ArrayList<String> consulta = new ArrayList<String>();
        //Preparamos la consulta
        String selection = CICLO + " LIKE ?";
        String selectionArgs[] = new String[]{arg};

        //Recuperamos en un cursor la consulta realizada
        //Cursor cursor = db.rawQuery(MY_QUERY, null);
        Cursor cursor = db.query(DATABASE_TABLE1,null,selection,selectionArgs,null,null,null);
        //Recorremos el cursor
        if (cursor != null && cursor.moveToFirst()){
            do{
                // (1) y (2) hacen referencia a la columna
                consulta.add(cursor.getString(1)+", "+cursor.getString(2)+", "+cursor.getString(3));
            }while (cursor.moveToNext());
        }

        return consulta;
    }

    // Método para recuperar alumnos por curso

    public ArrayList<String> recuperarAlumnosCurso(String arg){

        ArrayList<String> consulta = new ArrayList<String>();
        //Preparamos la consulta
        String selection = CURSO + " LIKE ?";
        String selectionArgs[] = new String[]{arg};

        //Recuperamos en un cursor la consulta realizada
        //Cursor cursor = db.rawQuery(MY_QUERY, null);
        Cursor cursor = db.query(DATABASE_TABLE1,null,selection,selectionArgs,null,null,null);
        //Recorremos el cursor
        if (cursor != null && cursor.moveToFirst()){
            do{
                // (1) y (2) hacen referencia a la columna
                consulta.add(cursor.getString(1)+", "+cursor.getString(2)+", "+cursor.getString(4));
            }while (cursor.moveToNext());
        }

        return consulta;
    }

    // Método para recuperar alumnos por ciclo y curso

    public ArrayList<String> recuperarAlumnosCicloCurso(String arg1, String arg2){

        ArrayList<String> consulta = new ArrayList<String>();
        //Preparamos la consulta
        String selection = CICLO + " LIKE ? AND "+CURSO + " LIKE ?";
        String selectionArgs[] = new String[]{arg1,arg2};

        //Recuperamos en un cursor la consulta realizada
        //Cursor cursor = db.rawQuery(MY_QUERY, null);
        Cursor cursor = db.query(DATABASE_TABLE1,null,selection,selectionArgs,null,null,null);
        //Recorremos el cursor
        if (cursor != null && cursor.moveToFirst()){
            do{
                // (1) y (2) hacen referencia a la columna
                consulta.add(cursor.getString(1)+", "+cursor.getString(2)+", "+cursor.getString(3)+", "+cursor.getString(4));
            }while (cursor.moveToNext());
        }

        return consulta;
    }

    // Método para recuperar profesores por ciclo

    public ArrayList<String> recuperarProfesoresCiclo(String arg){

        ArrayList<String> consulta = new ArrayList<String>();
        //Preparamos la consulta
        String selection = CICLO + " LIKE ?";
        String selectionArgs[] = new String[]{arg};

        //Recuperamos en un cursor la consulta realizada
        //Cursor cursor = db.rawQuery(MY_QUERY, null);
        Cursor cursor = db.query(DATABASE_TABLE2,null,selection,selectionArgs,null,null,null);
        //Recorremos el cursor
        if (cursor != null && cursor.moveToFirst()){
            do{
                // (1) y (2) hacen referencia a la columna
                consulta.add(cursor.getString(1)+", "+cursor.getString(2)+", "+cursor.getString(3));
            }while (cursor.moveToNext());
        }

        return consulta;
    }

    // Método para recuperar profesores por curso

    public ArrayList<String> recuperarProfesoresCurso(String arg){

        ArrayList<String> consulta = new ArrayList<String>();
        //Preparamos la consulta
        String selection = CURSO + " LIKE ?";
        String selectionArgs[] = new String[]{arg};

        //Recuperamos en un cursor la consulta realizada
        //Cursor cursor = db.rawQuery(MY_QUERY, null);
        Cursor cursor = db.query(DATABASE_TABLE2,null,selection,selectionArgs,null,null,null);
        //Recorremos el cursor
        if (cursor != null && cursor.moveToFirst()){
            do{
                // (1) y (2) hacen referencia a la columna
                consulta.add(cursor.getString(1)+", "+cursor.getString(2)+", "+cursor.getString(4));
            }while (cursor.moveToNext());
        }

        return consulta;
    }

    // Método para recuperar profesores por ciclo y curso

    public ArrayList<String> recuperarProfesoresCicloCurso(String arg1, String arg2) {

        ArrayList<String> consulta = new ArrayList<String>();
        //Preparamos la consulta
        String selection = CICLO + " LIKE ? AND " + CURSO + " LIKE ?";
        String selectionArgs[] = new String[]{arg1, arg2};

        //Recuperamos en un cursor la consulta realizada
        Cursor cursor = db.query(DATABASE_TABLE2, null, selection, selectionArgs, null, null, null);
        //Recorremos el cursor
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // (1) y (2) hacen referencia a la columna
                consulta.add(cursor.getString(1) + ", " + cursor.getString(2) + ", " + cursor.getString(3) + ", " + cursor.getString(4));
            } while (cursor.moveToNext());
        }
        return consulta;
    }

    // Método para recuperar todos los profesores y alumnos
    public ArrayList<String> recuperarTodo() {
        ArrayList<String> consulta = new ArrayList<String>();
        //Preparamos la consulta
        String query = "SELECT  * FROM " + DATABASE_TABLE1+" UNION SELECT * FROM "+DATABASE_TABLE2;

        //Recuperamos en un cursor la consulta realizada
        try (Cursor cursor = db.rawQuery(query, null)) {

            //Recorremos el cursor
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    // (1) y (2) hacen referencia a la columna
                    consulta.add(cursor.getString(1) + " " + cursor.getString(2));
                } while (cursor.moveToNext());
            }
        }

        return consulta;

    }


    private static class MyDbHelper extends SQLiteOpenHelper {

        public MyDbHelper (Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
            super(context,name,factory,version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE1);
            db.execSQL(DATABASE_CREATE2);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DATABASE_DROP1);
            db.execSQL(DATABASE_DROP2);
            onCreate(db);
        }

    }
}