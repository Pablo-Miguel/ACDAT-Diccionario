package com.example.acdat_diccionario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.acdat_diccionario.databinding.ActivityMainBinding;
import com.example.acdat_diccionario.dll.DAODiccionario;
import com.example.acdat_diccionario.dll.DiccionarioSQLiteHelper;
import com.example.acdat_diccionario.modelo.Diccionario;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getSupportActionBar().hide();
        DAODiccionario.establecerConexion(new DiccionarioSQLiteHelper(MainActivity.this));

        binding.btnInsertar.setOnClickListener(this);
        binding.btnModificar.setOnClickListener(this);
        binding.btnConsultas.setOnClickListener(this);
        binding.btnTest.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnInsertar){
            Intent intentInsert = new Intent(MainActivity.this, InsertarDiccionario.class);
            intentInsert.putExtra("diccionario", (Diccionario) null);
            startActivity(intentInsert);
        } else if (view.getId() == R.id.btnModificar) {
            Intent intentMod = new Intent(MainActivity.this, ModificarDiccionario.class);
            startActivity(intentMod);
        } else if (view.getId() == R.id.btnConsultas) {
            Intent intentCons = new Intent(MainActivity.this, ConsultasActivity.class);
            startActivity(intentCons);
        } else if(view.getId() == R.id.btnTest){
            Intent intentTest = new Intent(MainActivity.this, TestActivity.class);
            startActivity(intentTest);
        }
    }
}