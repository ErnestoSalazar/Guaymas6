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
 * Created by soygo on 03/04/2016.
 */
public class DirectorioCursorAdapter extends CursorAdapter{

    public DirectorioCursorAdapter(Context context, Cursor cursor, int flags){
        super(context, cursor,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent){
        return LayoutInflater.from(context).inflate(R.layout.item_directorio, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor){
        TextView txtNombreDep = (TextView) view.findViewById(R.id.txtNombreDep);
        TextView txtTelefonoDep = (TextView) view.findViewById(R.id.txtTelefonoDep);
        TextView txtLugarDep = (TextView) view.findViewById(R.id.txtLugarDep);
        TextView txtPaginaDep = (TextView) view.findViewById(R.id.txtPaginaDep);

        //String body = cursor.getString(cursor.getColumnIndexOrThrow("body"));
        String nombreDep = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
        String telefonoDep = cursor.getString(cursor.getColumnIndexOrThrow("telefono"));
        String lugarDep = cursor.getString(cursor.getColumnIndexOrThrow("direccion"));
        String paginaDep = cursor.getString(cursor.getColumnIndexOrThrow("web"));

        txtNombreDep.setText(nombreDep);
        txtTelefonoDep.setText(telefonoDep);
        txtLugarDep.setText(lugarDep);
        txtPaginaDep.setText(paginaDep);
    }
}
