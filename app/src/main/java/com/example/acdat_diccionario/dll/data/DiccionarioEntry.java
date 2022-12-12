package com.example.acdat_diccionario.dll.data;

import android.provider.BaseColumns;

public abstract class DiccionarioEntry implements BaseColumns {
    public static final String TABLE_NAME ="Diccionario";

    public static final String ID_DICCIONARIO = "id_diccionario";
    public static final String PAL_EXPR_ESP = "pal_expr_esp";
    public static final String PAL_EXPR_ING = "pal_expr_ing";
    public static final String TIPO_DICCIONARIO = "tipo_diccionario";
    public static final String FECHA_INTRO = "fecha_intro";
    public static final String FECHA_ULT = "fecha_ult";
    public static final String CONT_ACIERTOS = "cont_aciertos";

}
