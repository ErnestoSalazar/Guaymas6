package mx.pitalla.myapplication.cursoradapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import mx.pitalla.myapplication.R;

/**
 * Created by soygo on 04/04/2016.
 */
public class EventoCursorAdapter extends CursorAdapter {

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
        String eventPlace = cursor.getString(cursor.getColumnIndexOrThrow("lugar"));

        txtEventTitle.setText(eventTitle);
        txtEventDate.setText(eventDate);
        txtEventPlace.setText(eventPlace);
    }

}
