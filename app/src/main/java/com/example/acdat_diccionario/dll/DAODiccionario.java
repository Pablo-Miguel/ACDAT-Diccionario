package com.example.acdat_diccionario.dll;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.acdat_diccionario.modelo.Diccionario;
import com.example.acdat_diccionario.modelo.DiccionarioCriterio;

import java.util.ArrayList;

public class DAODiccionario {
    private static DAODiccionario dao;
    private static ArrayList<Diccionario> listaDiccionario;
    private static DiccionarioSQLiteHelper dbHelper;

    public static DAODiccionario getInstance() {

        if (dao == null) {
            dao = new DAODiccionario();
        }

        if(listaDiccionario == null){
            listaDiccionario = new ArrayList<Diccionario>();
        }

        return dao;
    }

    public static void establecerConexion(DiccionarioSQLiteHelper dbHelper){
        DAODiccionario.dbHelper = dbHelper;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ArrayList<Diccionario> getDiccionarios(){
        return dbHelper.getDiccionarios();
    }

    public long insertarDiccionario(Diccionario diccionario){
        return dbHelper.saveDiccionario(diccionario);
    }

    public int updateDiccionario(Diccionario diccionario){
        return dbHelper.updateDiccionario(diccionario);
    }

    public ArrayList<DiccionarioCriterio> getDiccionariosCriterios(ArrayList listaComponentes) {
        return dbHelper.getDiccionariosCriterios(listaComponentes);
    }

    public int contAciertos(Diccionario dicCorr) {
        return dbHelper.contAciertos(dicCorr);
    }

    public int restAciertos(Diccionario dicCorr) {
        return dbHelper.restAciertos(dicCorr);
    }
}
