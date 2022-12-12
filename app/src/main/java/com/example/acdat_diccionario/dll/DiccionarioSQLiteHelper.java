package com.example.acdat_diccionario.dll;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.acdat_diccionario.dll.data.DiccionarioEntry;
import com.example.acdat_diccionario.enums.TipoDiccionario;
import com.example.acdat_diccionario.modelo.Diccionario;
import com.example.acdat_diccionario.modelo.DiccionarioCriterio;

import java.time.LocalDate;
import java.util.ArrayList;

public class DiccionarioSQLiteHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DiccionarioServidor.db";

    public DiccionarioSQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + DiccionarioEntry.TABLE_NAME + " ("
                + DiccionarioEntry.ID_DICCIONARIO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DiccionarioEntry.PAL_EXPR_ESP + " TEXT NOT NULL, "
                + DiccionarioEntry.PAL_EXPR_ING + " TEXT NOT NULL, "
                + DiccionarioEntry.TIPO_DICCIONARIO + " TEXT NOT NULL, "
                + DiccionarioEntry.FECHA_INTRO + " TEXT, "
                + DiccionarioEntry.FECHA_ULT + " TEXT, "
                + DiccionarioEntry.CONT_ACIERTOS + " INTEGER DEFAULT 0)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int version_anterior, int version_nueva) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DiccionarioEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long saveDiccionario(Diccionario diccionario){

        SQLiteDatabase db = getWritableDatabase();

        return db.insert(DiccionarioEntry.TABLE_NAME, null, diccionario.toContentValues());

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ArrayList<Diccionario> getDiccionarios(){

        ArrayList<Diccionario> listaDiccionarios = new ArrayList<Diccionario>();

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + DiccionarioEntry.TABLE_NAME, null);

        if(cursor.moveToFirst()){
            do{

                listaDiccionarios.add(new Diccionario(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        TipoDiccionario.valueOf(cursor.getString(3)),
                        LocalDate.parse(cursor.getString(4)),
                        LocalDate.parse(cursor.getString(5)),
                        cursor.getInt(6)));

            } while(cursor.moveToNext());
        }

        cursor.close();

        return listaDiccionarios;
    }

    public int updateDiccionario(Diccionario diccionario) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = diccionario.toContentValues();

        return db.update(DiccionarioEntry.TABLE_NAME, values, DiccionarioEntry.ID_DICCIONARIO + "=?", new String[]{diccionario.getId_diccionario().toString()});
    }

    public ArrayList<DiccionarioCriterio> getDiccionariosCriterios(ArrayList listaComponentes) {
        ArrayList<DiccionarioCriterio> listaDiccionarios = new ArrayList<DiccionarioCriterio>();

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT " + listaComponentes.get(2) + ", " + DiccionarioEntry.CONT_ACIERTOS + " FROM "
                + DiccionarioEntry.TABLE_NAME + " WHERE "
                + DiccionarioEntry.TIPO_DICCIONARIO + "='" + listaComponentes.get(3)
                + "' AND " + listaComponentes.get(2) + " LIKE '%" + listaComponentes.get(0)
                + "%' ORDER BY " + listaComponentes.get(1), null);

        if(cursor.moveToFirst()){
            do{

                listaDiccionarios.add(new DiccionarioCriterio(cursor.getString(0), cursor.getInt(1)));

            } while(cursor.moveToNext());
        }

        cursor.close();

        return listaDiccionarios;
    }

    public int contAciertos(Diccionario dicCorr) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DiccionarioEntry.CONT_ACIERTOS, dicCorr.getCont_aciertos() + 1);

        return db.update(DiccionarioEntry.TABLE_NAME, values, DiccionarioEntry.ID_DICCIONARIO + "=?", new String[]{dicCorr.getId_diccionario().toString()});
    }

    public int restAciertos(Diccionario dicCorr) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DiccionarioEntry.CONT_ACIERTOS, 0);

        return db.update(DiccionarioEntry.TABLE_NAME, values, DiccionarioEntry.ID_DICCIONARIO + "=?", new String[]{dicCorr.getId_diccionario().toString()});
    }

}
