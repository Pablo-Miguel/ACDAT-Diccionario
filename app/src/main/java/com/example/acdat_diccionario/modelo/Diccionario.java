package com.example.acdat_diccionario.modelo;

import android.content.ContentValues;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.acdat_diccionario.dll.data.DiccionarioEntry;
import com.example.acdat_diccionario.enums.TipoDiccionario;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;

public class Diccionario implements Serializable {
    private Integer id_diccionario;
    private String pal_expr_esp, pal_expr_ing;
    private TipoDiccionario tipoDiccionario;
    private LocalDate fecha_intro, fecha_ult;
    private Integer cont_aciertos;

    public Diccionario(Integer id_diccionario, String pal_expr_esp, String pal_expr_ing, TipoDiccionario tipoDiccionario, LocalDate fecha_intro, LocalDate fecha_ult, Integer cont_aciertos) {
        this.id_diccionario = id_diccionario;
        this.pal_expr_esp = pal_expr_esp;
        this.pal_expr_ing = pal_expr_ing;
        this.tipoDiccionario = tipoDiccionario;
        this.fecha_intro = fecha_intro;
        this.fecha_ult = fecha_ult;
        this.cont_aciertos = cont_aciertos;
    }

    public Integer getId_diccionario() {
        return id_diccionario;
    }

    public void setId_diccionario(Integer id_diccionario) {
        this.id_diccionario = id_diccionario;
    }

    public String getPal_expr_esp() {
        return pal_expr_esp;
    }

    public void setPal_expr_esp(String pal_expr_esp) {
        this.pal_expr_esp = pal_expr_esp;
    }

    public String getPal_expr_ing() {
        return pal_expr_ing;
    }

    public void setPal_expr_ing(String pal_expr_ing) {
        this.pal_expr_ing = pal_expr_ing;
    }

    public TipoDiccionario getTipoDiccionario() {
        return tipoDiccionario;
    }

    public void setTipoDiccionario(TipoDiccionario tipoDiccionario) {
        this.tipoDiccionario = tipoDiccionario;
    }

    public LocalDate getFecha_intro() {
        return fecha_intro;
    }

    public void setFecha_intro(LocalDate fecha_intro) {
        this.fecha_intro = fecha_intro;
    }

    public LocalDate getFecha_ult() {
        return fecha_ult;
    }

    public void setFecha_ult(LocalDate fecha_ult) {
        this.fecha_ult = fecha_ult;
    }

    public Integer getCont_aciertos() {
        return cont_aciertos;
    }

    public void setCont_aciertos(Integer cont_aciertos) {
        this.cont_aciertos = cont_aciertos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Diccionario)) return false;
        Diccionario that = (Diccionario) o;
        return getId_diccionario().equals(that.getId_diccionario());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId_diccionario());
    }

    @Override
    public String toString() {
        return "Diccionario{" +
                "id_diccionario=" + id_diccionario +
                ", pal_expr_esp='" + pal_expr_esp + '\'' +
                ", pal_expr_ing='" + pal_expr_ing + '\'' +
                ", tipoDiccionario=" + tipoDiccionario +
                ", fecha_intro=" + fecha_intro +
                ", fecha_ult=" + fecha_ult +
                ", cont_aciertos=" + cont_aciertos +
                '}';
    }

    public ContentValues toContentValues(){
        ContentValues values = new ContentValues();

        values.put(DiccionarioEntry.PAL_EXPR_ESP, getPal_expr_esp());
        values.put(DiccionarioEntry.PAL_EXPR_ING, getPal_expr_ing());
        values.put(DiccionarioEntry.TIPO_DICCIONARIO, getTipoDiccionario().toString());
        values.put(DiccionarioEntry.FECHA_INTRO, getFecha_intro().toString());
        values.put(DiccionarioEntry.FECHA_ULT, getFecha_ult().toString());
        values.put(DiccionarioEntry.CONT_ACIERTOS, getCont_aciertos());

        return values;
    }

    public static class CompararAciertosYFecha implements Comparator<Diccionario> {

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public int compare(Diccionario diccionario1, Diccionario diccionario2) {

            if(diccionario1.getCont_aciertos().compareTo(diccionario2.getCont_aciertos()) != 0){
                return diccionario1.getCont_aciertos().compareTo(diccionario2.getCont_aciertos());
            }
            else{
                return diccionario1.getFecha_ult().compareTo(diccionario2.getFecha_ult());
            }

        }

    }

}
