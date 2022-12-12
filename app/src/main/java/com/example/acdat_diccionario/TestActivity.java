package com.example.acdat_diccionario;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.acdat_diccionario.databinding.ActivityTestBinding;
import com.example.acdat_diccionario.dll.DAODiccionario;
import com.example.acdat_diccionario.modelo.Diccionario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {
    private Random random;
    private ActivityTestBinding binding;
    private Integer contTest = 1, dirRbCorr, aciertos = 0;
    private ArrayList<Diccionario> listaDiccionariosOrden;
    private Diccionario dicCorr;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTestBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getSupportActionBar().hide();

        this.random = new Random();

        binding.btnContTest.setOnClickListener(this);

        this.listaDiccionariosOrden = DAODiccionario.getInstance().getDiccionarios();

        Collections.sort(this.listaDiccionariosOrden, new Diccionario.CompararAciertosYFecha());

        cambiarTest();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {

        if(contTest < 5){

            if(binding.rbGrpTest.getCheckedRadioButtonId() == dirRbCorr){
                DAODiccionario.getInstance().contAciertos(dicCorr);
                aciertos++;
            } else {
                DAODiccionario.getInstance().restAciertos(dicCorr);
            }

            cambiarTest();

            contTest++;

            binding.lblTestCont.setText("Test: " + contTest);

        } else {
            if(contTest == 5){
                if(binding.rbGrpTest.getCheckedRadioButtonId() == dirRbCorr){
                    DAODiccionario.getInstance().contAciertos(dicCorr);
                    aciertos++;
                } else {
                    DAODiccionario.getInstance().restAciertos(dicCorr);
                }

                contTest++;
            }
            binding.btnContTest.setText("Ver nota");
            Toast t = Toast.makeText(TestActivity.this, "Has acertado " + aciertos + " tests", Toast.LENGTH_SHORT);
            t.show();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void cambiarTest() {
        dicCorr = listaDiccionariosOrden.get(random.nextInt(listaDiccionariosOrden.size()));
        listaDiccionariosOrden.remove(dicCorr);

        binding.lblPalabraEspanol.setText(dicCorr.getPal_expr_esp());

        Integer rbCorrecto = random.nextInt(4);

        if(rbCorrecto == 0){
            binding.rbOp1.setText(dicCorr.getPal_expr_ing());
            dirRbCorr = R.id.rbOp1;
        } else {
            binding.rbOp1.setText(listaDiccionariosOrden.get(0).getPal_expr_ing());
            listaDiccionariosOrden.remove(0);
        }

        if(rbCorrecto == 1){
            binding.rbOp2.setText(dicCorr.getPal_expr_ing());
            dirRbCorr = R.id.rbOp2;
        } else {
            binding.rbOp2.setText(listaDiccionariosOrden.get(0).getPal_expr_ing());
            listaDiccionariosOrden.remove(0);
        }

        if(rbCorrecto == 2){
            binding.rbOp3.setText(dicCorr.getPal_expr_ing());
            dirRbCorr = R.id.rbOp3;
        } else {
            binding.rbOp3.setText(listaDiccionariosOrden.get(0).getPal_expr_ing());
            listaDiccionariosOrden.remove(0);
        }

        if(rbCorrecto == 3){
            binding.rbOp4.setText(dicCorr.getPal_expr_ing());
            dirRbCorr = R.id.rbOp4;
        } else {
            binding.rbOp4.setText(listaDiccionariosOrden.get(0).getPal_expr_ing());
            listaDiccionariosOrden.remove(0);
        }

    }

}