package mx.pitalla.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mx.pitalla.myapplication.R;
import mx.pitalla.myapplication.entidad.Favorito;

/**
 * Created by Fernando on 13/03/2016.
 */
public class FavoritoAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<Favorito> datos;

    public FavoritoAdapter(Context context, ArrayList<Favorito> datos){
        super(context, R.layout.item_favorito, datos);
        this.context = context;
        this.datos = datos;
        //inflater = LayoutInflater.from(context);

        //this.datos = new ArrayList<Favorito>();
        //this.datos.addAll(datos);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.item_favorito, null);

        TextView nombre = (TextView) item.findViewById(R.id.favNombre);
        TextView tel = (TextView) item.findViewById(R.id.favTelefono);

        //String subNombre = datos.get(position).getNombre();
        //String subTelefono = datos.get(position).getTelefono();

        nombre.setText(datos.get(position).getNombre());
        tel.setText(datos.get(position).getTelefono());

        return item;
    }
}
