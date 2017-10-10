package com.example.abecruz.cinepolistestia.Perfil.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.abecruz.cinepolistestia.R;

import java.util.List;

/**
 * Created by albertocruz on 28/09/17.
 */

public class PerfilListaAdapter extends BaseAdapter {

    //le pasamos el contexto porque aqui es donde lo vamos a mostrar
    private Context context;
    private int layout;
    private List<String> items;

    //Click isquierdo y generate constructor (como en una clase java necesita constructor de que se va a pasar)
    public PerfilListaAdapter(Context context, int layout, List<String> items) {
        this.context = context;
        this.layout = layout;
        this.items = items;
    }


    //Cuantas veces vamos a dibujar el list item, no sabemos pero le decimos que del tamaño de la lista
    @Override
    public int getCount() {
        return this.items.size();
    }

    //No se usa tanto pero se le pasa un una posicion de item de la coleccion
    @Override
    public Object getItem(int position) {
        return this.items.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }


    //Este es importante! normalmente se pasa position, converview (asi se llama)
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        //Copiamos la vista
        View v = convertView;

        //Inflamos la vista que nos ha llegado con nustro layout personalizado
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        v = layoutInflater.inflate(R.layout.perfil_list_item,null);

        //mandamos traer el valor actual dependiente de la posicion.
        String currenName = items.get(position);

        //devolvemos la vista inflada
        return v;


    }

}