package mx.pitalla.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import mx.pitalla.myapplication.DirectorioActivity;
import mx.pitalla.myapplication.R;
import mx.pitalla.myapplication.entidad.Directorio;

/**
 * Created by soygo on 27/02/2016.
 */
public class DirectorioAdapter extends ArrayAdapter{
    private Context context;
    private ArrayList<Directorio> datos;
    ImageButton boton;
    Intent llamarIntent;

    public DirectorioAdapter(Context context, ArrayList<Directorio> datos){
        super(context, R.layout.item_directorio, datos);
        this.context = context;
        this.datos = datos;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(context);
        final View item = inflater.inflate(R.layout.item_directorio, null);

        boton = (ImageButton) item.findViewById(R.id.btnLlamarDep);

        //Creamos variables TextView para asignarles un TextView del item directorio
        TextView nombre = (TextView) item.findViewById(R.id.txtNombreDep);
        TextView tel = (TextView) item.findViewById(R.id.txtTelefonoDep);
        TextView lugarDependencia = (TextView) item.findViewById(R.id.txtLugarDep);
        TextView web = (TextView) item.findViewById(R.id.txtPaginaDep);

        //Creamos variables para asignarles los valores respectivos al item y poder crear comparaciones
        final String subNombre = datos.get(position).getNombre();
        final String subTelefono = datos.get(position).getTelefono(); //Comrobamos si existe algun espacio despues del 622
        String lada = "622 ";
        final String subLugarDependencia = datos.get(position).getDireccion();
        final String subWeb = datos.get(position).getWeb();


        //Comparamos si el nombre de la dependencia es igual a null
        if(subNombre.isEmpty()){
            String nombreNull = "Nombre de dependencia no disponible";
            nombre.setText(datos.get(position).getNombre());
            nombre.setText(nombreNull);
        }
        else{
            nombre.setText(datos.get(position).getNombre());
        }
        //Comprobamos si los numeros despues de la lada(622) iguales a null
        if(subTelefono.isEmpty()){
            String telefono = "No disponible";
            tel.setText(datos.get(position).getTelefono());
            tel.setText(telefono);
        }
        else{
            tel.setText(lada+subTelefono);
        }
        //Comprobamos si la direccion de la dependencia es igual a null
        if(subLugarDependencia.isEmpty()){
            String direccionNull = "Dirección no disponible";
            lugarDependencia.setText(datos.get(position).getDireccion());
            lugarDependencia.setText(direccionNull);
        }
        else{
            lugarDependencia.setText(datos.get(position).getDireccion());
        }
        //comprbamos si el nombre de la web es igual a null
        if(subWeb.isEmpty() || subWeb.equals("null")){
            String webNull = "Página web no disponible";
            web.setText(datos.get(position).getWeb());
            web.setText(webNull);
        }
        else{
            web.setText(datos.get(position).getWeb());
        }


        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Directorio datosDirectorio = new Directorio(subNombre,subTelefono,subLugarDependencia,subWeb);

                Intent i = new Intent(context, DirectorioActivity.class);
                i.putExtra("datosDirectorio", datosDirectorio);

                context.startActivity(i);
            }
        });

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    TextView texto = (TextView) item.findViewById(R.id.txtTelefonoDep);
                    String numero = texto.getText().toString().trim();

                    if (numero.equals("No disponible")) {
                        Toast.makeText(context, "No disponible", Toast.LENGTH_SHORT).show();
                    } else {
                        llamarIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + numero));
                        context.startActivity(llamarIntent);
                    }
                    //Toast.makeText(context, numero, Toast.LENGTH_SHORT).show();

                }
                catch (Exception anfe) {
                    Toast.makeText(context, "Error: "+anfe, Toast.LENGTH_SHORT).show();
                }
            }
        });

        return item; //retornamos el item
    }

}
