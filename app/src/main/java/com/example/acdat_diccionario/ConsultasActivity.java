package com.example.acdat_diccionario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.example.acdat_diccionario.adapters.DiccionarioAdapterConsultas;
import com.example.acdat_diccionario.databinding.ActivityConsultasBinding;
import com.example.acdat_diccionario.dll.data.DiccionarioEntry;
import com.example.acdat_diccionario.enums.TipoDiccionario;

import java.util.ArrayList;

public class ConsultasActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private ActivityConsultasBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConsultasBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getSupportActionBar().hide();

        DiccionarioAdapterConsultas adapter = new DiccionarioAdapterConsultas(getListaComponentes());
        binding.recyclerConsulta.setAdapter(adapter);

        binding.btnBuscar.setOnClickListener(this);
        binding.rbGrp1.setOnCheckedChangeListener(this);
        binding.rbGrp2.setOnCheckedChangeListener(this);
        binding.rbGrp3.setOnCheckedChangeListener(this);

    }

    @Override
    public void onClick(View view) {
        binding.recyclerConsulta.setAdapter(new DiccionarioAdapterConsultas(getListaComponentes()));
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        binding.recyclerConsulta.setAdapter(new DiccionarioAdapterConsultas(getListaComponentes()));
    }

    private ArrayList getListaComponentes(){
        ArrayList listaComponentes = new ArrayList();

        listaComponentes.add(binding.editTxtBuscar.getText().toString());

        if(binding.rbGrp1.getCheckedRadioButtonId() == R.id.rbOrdenAlfabetico){
            if(binding.rbGrp2.getCheckedRadioButtonId() == R.id.rbIngles){
                listaComponentes.add(DiccionarioEntry.PAL_EXPR_ING);
            } else {
                listaComponentes.add(DiccionarioEntry.PAL_EXPR_ESP);
            }
        } else {
            listaComponentes.add(DiccionarioEntry.CONT_ACIERTOS);
        }

        if(binding.rbGrp2.getCheckedRadioButtonId() == R.id.rbIngles){
            listaComponentes.add(DiccionarioEntry.PAL_EXPR_ING);
        } else {
            listaComponentes.add(DiccionarioEntry.PAL_EXPR_ESP);
        }

        if(binding.rbGrp3.getCheckedRadioButtonId() == R.id.rbPalabra){
            listaComponentes.add(TipoDiccionario.PALABRA.toString());
        } else {
            listaComponentes.add(TipoDiccionario.EXPRESION.toString());
        }
        return listaComponentes;
    }
}