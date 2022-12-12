package com.example.acdat_diccionario;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.acdat_diccionario.databinding.ActivityInsertarDiccionarioBinding;
import com.example.acdat_diccionario.dll.DAODiccionario;
import com.example.acdat_diccionario.enums.TipoDiccionario;
import com.example.acdat_diccionario.modelo.Diccionario;

import java.time.LocalDate;
import java.util.ArrayList;

public class InsertarDiccionario extends AppCompatActivity {

    private ActivityInsertarDiccionarioBinding binding;
    private TipoDiccionario tipoDiccionario;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertarDiccionarioBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getSupportActionBar().hide();

        Diccionario diccionario = (Diccionario) getIntent().getSerializableExtra("diccionario");

        ArrayList<String> listTemp = new ArrayList<String>();

        for (TipoDiccionario value : TipoDiccionario.values()) {
            listTemp.add(value.toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listTemp);

        binding.spinDiccionario.setAdapter(adapter);

        binding.spinDiccionario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tipoDiccionario = TipoDiccionario.valueOf(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                tipoDiccionario = TipoDiccionario.PALABRA;
            }
        });

        if(diccionario == null){
            binding.txtId.setEnabled(Boolean.FALSE);
            binding.txtId.setText("AUTOINCREMENT");
            binding.txtFechIntro.setEnabled(Boolean.FALSE);
            binding.txtFechIntro.setText(LocalDate.now().toString());
            binding.txtFechUlt.setEnabled(Boolean.FALSE);
            binding.txtFechUlt.setText(LocalDate.now().toString());
            binding.txtContAciertos.setEnabled(Boolean.FALSE);
            binding.txtContAciertos.setText(Integer.toString(0));

            binding.btnContinuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(DAODiccionario.getInstance().insertarDiccionario(new Diccionario(
                            null,
                            binding.txtPalExprEsp.getText().toString(),
                            binding.txtPalExprIng.getText().toString(),
                            tipoDiccionario,
                            LocalDate.parse(binding.txtFechIntro.getText()),
                            LocalDate.parse(binding.txtFechUlt.getText()),
                            Integer.parseInt(binding.txtContAciertos.getText().toString()))) > 0){
                        Toast t = Toast.makeText(InsertarDiccionario.this, "Se ha insertado correctamente al diccionario", Toast.LENGTH_SHORT);
                        t.show();
                    } else {
                        Toast t = Toast.makeText(InsertarDiccionario.this, "Se ha producido un error al insertar al diccionario", Toast.LENGTH_SHORT);
                        t.show();
                    }
                }
            });

        } else {
            binding.txtId.setEnabled(Boolean.FALSE);
            binding.txtId.setText(diccionario.getId_diccionario().toString());
            binding.txtPalExprEsp.setText(diccionario.getPal_expr_esp());
            binding.txtPalExprIng.setText(diccionario.getPal_expr_ing());
            binding.spinDiccionario.setSelection(diccionario.getTipoDiccionario().ordinal());
            binding.txtFechIntro.setEnabled(Boolean.FALSE);
            binding.txtFechIntro.setText(diccionario.getFecha_intro().toString());
            binding.txtFechUlt.setEnabled(Boolean.FALSE);
            binding.txtFechUlt.setText(diccionario.getFecha_ult().toString());
            binding.txtContAciertos.setText(diccionario.getCont_aciertos().toString());

            binding.btnContinuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(DAODiccionario.getInstance().updateDiccionario(new Diccionario(
                            Integer.parseInt(binding.txtId.getText().toString()),
                            binding.txtPalExprEsp.getText().toString(),
                            binding.txtPalExprIng.getText().toString(),
                            tipoDiccionario,
                            LocalDate.parse(binding.txtFechIntro.getText()),
                            LocalDate.parse(binding.txtFechUlt.getText()),
                            Integer.parseInt(binding.txtContAciertos.getText().toString()))) > 0){
                        Toast t = Toast.makeText(InsertarDiccionario.this, "Se ha modificado correctamente el diccionario", Toast.LENGTH_SHORT);
                        t.show();
                    } else {
                        Toast t = Toast.makeText(InsertarDiccionario.this, "Se ha producido un error al modificar el diccionario", Toast.LENGTH_SHORT);
                        t.show();
                    }
                }
            });

        }

    }
}