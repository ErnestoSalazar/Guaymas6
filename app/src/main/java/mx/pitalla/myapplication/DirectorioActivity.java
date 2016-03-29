package mx.pitalla.myapplication;

import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;

import java.util.ArrayList;

import mx.pitalla.myapplication.adapter.DirectorioAdapter;
import mx.pitalla.myapplication.adapter.FavoritoAdapter;
import mx.pitalla.myapplication.entidad.Directorio;
import mx.pitalla.myapplication.entidad.Favorito;
import mx.pitalla.myapplication.funciones.miActionBar;

import static android.view.View.GONE;

/**
 * Created by soygo on 27/02/2016.
 */
public class DirectorioActivity extends AppCompatActivity {
    AQuery aq;
    Directorio directorio;
    Context context;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_favoritos, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_addFavoritos:
                //Obtener numero y telefono de la vista
                TextView obtenerNombre = (TextView) findViewById(R.id.tvDependencia);
                String on = obtenerNombre.getText().toString().trim();
                TextView obtenerTelefono = (TextView) findViewById(R.id.tvTelefonoDependencia);
                String ot = obtenerTelefono.getText().toString().trim();

                //Invocar metodo
                nuevoRegistro(on, ot);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_directorio);

        context = this;

        aq = new AQuery(this);
        directorio = getIntent().getParcelableExtra("datosDirectorio");

        miActionBar ab = new miActionBar(getSupportActionBar(),"directorio");

        //Creamos variables para poder asignarles un valor y poder compararlas
        String dependencia = directorio.getNombre();
        String subTelefono = directorio.getTelefono();
        String lugarDependencia = directorio.getDireccion();
        String web = directorio.getWeb();

        //Creamos una comparacion para poder asígnarle los datos correctos que sean correspondientes a detalle_directorio.xml
        //Comparamos si la dependencia es igual a null
        if(dependencia.isEmpty()){
            aq.id(R.id.tvDependencia).text("Nombre de dependencia no disponible");
        }
        else{
            aq.id(R.id.tvDependencia).text(directorio.getNombre());
        }
        //Comprobamos si los numeros despues de la lada(622) iguales a null
        if(subTelefono.isEmpty()){
            aq.id(R.id.tvTelefonoDependencia).text("Telefono no disponible");
        }
        else{
            aq.id(R.id.tvTelefonoDependencia).text("622 "+directorio.getTelefono());
        }
        //Comparamos si la direccion de la dependencia es igual a null
        if(lugarDependencia.isEmpty()){
            aq.id(R.id.tvLugarDependencia).text("Dirección no disponible");
        }
        else{
            aq.id(R.id.tvLugarDependencia).text(directorio.getDireccion());
        }
        //Comparamos si la web es igual a null
        if(web.isEmpty()|| web.equals("null")){
            aq.id(R.id.tvWeb).text("Pagina web no disponible");
        }
        else{
            aq.id(R.id.tvWeb).text(directorio.getWeb());
        }

    }

    //Metodo para añadir un nuevo registro
    public void nuevoRegistro(String n, String t) {
        FavoritosActivity fa = new FavoritosActivity(); //Se crea una instancia
        fa.addFav(this, n, t); //Se manda a llamar el metodo para añadir

    }

}
