package com.mario21ic.android_player;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Vector;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private LayoutInflater inflador;
    private Vector<String> lista;
    Context micontext;

    public MyAdapter(Context context, Vector<String> lista) {
        this.lista = lista;
        micontext=context;
        inflador = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titulo, subtitulo;
        public ImageView icon;
        public Button miboton;

        ViewHolder(View itemView) {
            super(itemView);
            titulo = (TextView)itemView.findViewById(R.id.titulo);
            subtitulo = (TextView)itemView.findViewById(R.id.subtitulo);
            icon = (ImageView)itemView.findViewById(R.id.icono);
            miboton= itemView.findViewById(R.id.mibotonitem);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflador.inflate(R.layout.miitem, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int i) {
        holder.titulo.setText(lista.get(i));
        holder.subtitulo.setText("Loyalty Freak");

//        holder.miboton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                ((InterfaceToast)micontext).mensaje("Button - posición: "+ i + " Su valor es: " + lista.get(i));
//            }
//        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((InterfaceMyPlay)micontext).mensaje("Playing position: "+ i + " - song: " + lista.get(i));
                ((InterfaceMyPlay)micontext).playSong(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

}