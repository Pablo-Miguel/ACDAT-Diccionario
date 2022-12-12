package com.example.acdat_diccionario.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.acdat_diccionario.databinding.ViewModItemBinding;
import com.example.acdat_diccionario.modelo.Diccionario;

import java.util.ArrayList;

public class DiccionarioActivityMod extends RecyclerView.Adapter<DiccionarioActivityMod.ViewHolder> implements View.OnClickListener {
    private ArrayList<Diccionario> listaDiccionario;
    private View.OnClickListener listener;

    public DiccionarioActivityMod(ArrayList<Diccionario> listaDiccionario) {
        this.listaDiccionario = listaDiccionario;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewModItemBinding binding = ViewModItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        binding.getRoot().setOnClickListener(this);

        return new ViewHolder(binding);
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
        private ViewModItemBinding binding;
        public ViewHolder(ViewModItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Diccionario diccionario){
            binding.lblIdDic.setText(diccionario.getId_diccionario().toString());
            binding.lblEsp.setText(diccionario.getPal_expr_esp());
            binding.lblIng.setText(diccionario.getPal_expr_ing());
            binding.lblFechaIntro.setText(diccionario.getFecha_intro().toString());
        }

    }
}
