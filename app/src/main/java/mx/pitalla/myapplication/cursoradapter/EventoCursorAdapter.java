package mx.pitalla.myapplication.cursoradapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import mx.pitalla.myapplication.R;
import mx.pitalla.myapplication.adapter.EventoAdapter;

/**
 * Created by soygo on 04/04/2016.
 */
public class EventoCursorAdapter extends CursorAdapter {
    EventoAdapter adapter;

    public EventoCursorAdapter(Context context, Cursor cursor, int flags){
       super(context, cursor,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent){
        return LayoutInflater.from(context).inflate(R.layout.item_evento, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor){
        TextView txtEventTitle = (TextView)  view.findViewById(R.id.eventTitle);
        TextView txtEventDate = (TextView) view.findViewById(R.id.eventDate);
        TextView txtEventPlace = (TextView) view.findViewById(R.id.eventPlace);

        String eventTitle = cursor.getString(cursor.getColumnIndexOrThrow("nombre_evento"));
        String eventDate = cursor.getString(cursor.getColumnIndexOrThrow("fecha"));
        String eventHour = cursor.getString(cursor.getColumnIndexOrThrow("hora"));
        String eventPlace = cursor.getString(cursor.getColumnIndexOrThrow("lugar"));

        String subFecha1 = eventDate.substring(0, 4);
        String subFecha2 = eventDate.substring(5, 7);
        String subFecha3 = eventDate.substring(8);

        String fecha = fechaFormato(subFecha1, subFecha2, subFecha3);


        txtEventTitle.setText(eventTitle);
        txtEventDate.setText(fecha+" | "+eventHour);
        txtEventPlace.setText(eventPlace);
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
