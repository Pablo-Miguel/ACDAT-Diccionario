package com.example.acdat_diccionario.modelo;

import com.example.acdat_diccionario.enums.TipoDiccionario;

public class DiccionarioCriterio {
    private String pal_expr_esp_ing;
    private Integer cont_aciertos;

    public DiccionarioCriterio(String pal_expr_esp_ing, Integer cont_aciertos) {
        this.pal_expr_esp_ing = pal_expr_esp_ing;
        this.cont_aciertos = cont_aciertos;
    }

    public String getPal_expr_esp_ing() {
        return pal_expr_esp_ing;
    }

    public void setPal_expr_esp_ing(String pal_expr_esp_ing) {
        this.pal_expr_esp_ing = pal_expr_esp_ing;
    }

    public Integer getCont_aciertos() {
        return cont_aciertos;
    }

    public void setCont_aciertos(Integer cont_aciertos) {
        this.cont_aciertos = cont_aciertos;
    }

    @Override
    public String toString() {
        return "DiccionarioCriterio{" +
                "pal_expr_esp_ing='" + pal_expr_esp_ing + '\'' +
                ", cont_aciertos=" + cont_aciertos +
                '}';
    }
}
