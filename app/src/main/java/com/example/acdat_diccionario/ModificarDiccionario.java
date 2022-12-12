package com.example.acdat_diccionario;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.acdat_diccionario.adapters.DiccionarioActivityMod;
import com.example.acdat_diccionario.databinding.ActivityModificarDiccionarioBinding;
import com.example.acdat_diccionario.dll.DAODiccionario;
import com.example.acdat_diccionario.dll.DiccionarioSQLiteHelper;
import com.example.acdat_diccionario.modelo.Diccionario;

public class ModificarDiccionario extends AppCompatActivity implements View.OnClickListener {

    private ActivityModificarDiccionarioBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityModificarDiccionarioBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getSupportActionBar().hide();

        DiccionarioActivityMod adapter = new DiccionarioActivityMod(DAODiccionario.getInstance().getDiccionarios());
        binding.recyclerMod.setAdapter(adapter);

        adapter.setOnClickListener(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        Diccionario diccionario = DAODiccionario.getInstance().getDiccionarios().get(binding.recyclerMod.getChildAdapterPosition(view));

        Intent intentInsert = new Intent(ModificarDiccionario.this, InsertarDiccionario.class);
        intentInsert.putExtra("diccionario", diccionario);
        startActivity(intentInsert);

    }
}