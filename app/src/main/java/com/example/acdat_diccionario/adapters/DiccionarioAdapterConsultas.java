package com.example.acdat_diccionario.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acdat_diccionario.InsertarDiccionario;
import com.example.acdat_diccionario.databinding.ViewConsultaItemBinding;
import com.example.acdat_diccionario.dll.DAODiccionario;
import com.example.acdat_diccionario.modelo.DiccionarioCriterio;

import java.util.ArrayList;

public class DiccionarioAdapterConsultas extends RecyclerView.Adapter<DiccionarioAdapterConsultas.ViewHolder> implements View.OnClickListener {
    private ArrayList<DiccionarioCriterio> listaDiccionario;
    private View.OnClickListener listener;

    public DiccionarioAdapterConsultas(ArrayList listaComponentes) {
        this.listaDiccionario = DAODiccionario.getInstance().getDiccionariosCriterios(listaComponentes);
    }

    @Override
    public DiccionarioAdapterConsultas.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewConsultaItemBinding binding = ViewConsultaItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        binding.getRoot().setOnClickListener(this);

        return new DiccionarioAdapterConsultas.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.bind(listaDiccionario.get(position));

    }

    @Override
    public int getItemCount() {
        return listaDiccionario.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (this.listener != null) {
            listener.onClick(view);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ViewConsultaItemBinding binding;
        public ViewHolder(ViewConsultaItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(DiccionarioCriterio diccionario){
            binding.lblPalExp.setText(diccionario.getPal_expr_esp_ing());
            binding.lblAciertos.setText(diccionario.getCont_aciertos().toString());

            binding.btnVoz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast t = Toast.makeText(itemView.getContext(), "Lectura por voz en construcción", Toast.LENGTH_SHORT);
                    t.show();
                }
            });

        }

    }
}
