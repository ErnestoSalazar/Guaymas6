package mx.pitalla.myapplication.adapter;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import mx.pitalla.myapplication.R;
import mx.pitalla.myapplication.entidad.Evento;

/**
 * Created by Ernesto Salazar on 18/02/2016.
 */
public class EventoAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<Evento> datos;

    public EventoAdapter(Context context, ArrayList<Evento> datos){
        super(context, R.layout.item_evento, datos);
        this.context = context;
        this.datos = datos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.item_evento, null);


        ImageView imagen = (ImageView) item.findViewById(R.id.imgViewEvento);
        Picasso.with(context).load(datos.get(position).getImagen()).into(imagen);

        TextView titulo = (TextView) item.findViewById(R.id.eventTitle);
        TextView fechaYhora = (TextView) item.findViewById(R.id.eventDate);
        TextView lugar = (TextView) item.findViewById(R.id.eventPlace);

        String hora = datos.get(position).getHora();


        String subFecha1 = datos.get(position).getFecha().substring(0, 4);
        String subFecha2 = datos.get(position).getFecha().substring(5,7);
        String subFecha3 = datos.get(position).getFecha().substring(8);

        String fecha = fechaFormato(subFecha1,subFecha2,subFecha3);


        titulo.setText(datos.get(position).getNombre_evento());
        fechaYhora.setText(fecha +" | "+hora);
        lugar.setText(datos.get(position).getLugar());



        return item;

    }

    public String fechaFormato(String subFecha1, String subFecha2, String subFecha3){
        if(subFecha2.equals("01") ){
            subFecha2="enero";
        }
        else if(subFecha2.equals("02")){
            subFecha2="febrero";
        }
        else if(subFecha2.equals("03")){
            subFecha2="marzo";
        }
        else if(subFecha2.equals("04")){
            subFecha2="abril";
        }
        else if(subFecha2.equals("05")){
            subFecha2="mayo";
        }
        else if(subFecha2.equals("06")){
            subFecha2="junio";
        }
        else if(subFecha2.equals("07")){
            subFecha2="julio";
        }
        else if(subFecha2.equals("08")){
            subFecha2="agosto";
        }
        else if(subFecha2.equals("09")){
            subFecha2="septiembre";
        }
        else if(subFecha2.equals("10")){
            subFecha2="octubre";
        }
        else if(subFecha2.equals("11")){
            subFecha2="noviembre";
        }
        else if(subFecha2.equals("12")){
            subFecha2="diciembre";
        }
        String fecha = subFecha3+"-"+subFecha2+"-"+subFecha1;
        return fecha;
    }
}
