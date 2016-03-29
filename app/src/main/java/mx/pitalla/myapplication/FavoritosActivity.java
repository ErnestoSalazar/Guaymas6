package mx.pitalla.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import mx.pitalla.myapplication.adapter.FavoritoAdapter;
import mx.pitalla.myapplication.entidad.Directorio;
import mx.pitalla.myapplication.entidad.Favorito;
import mx.pitalla.myapplication.funciones.miActionBar;

/**
 * Created by Fernando on 12/03/2016.
 */
public class FavoritosActivity extends AppCompatActivity {
    Context context;
    static ArrayList<Favorito> listaFavoritos = new ArrayList<>();
    static HashMap<String, Favorito> listaTemporal = new HashMap<>();
    SharedPreferences ps;
    SharedPreferences.Editor editor;
    FavoritoAdapter adapter;
    ListView listView;
    static Favorito nuevo;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        context = this;
        miActionBar ab = new miActionBar(getSupportActionBar(),"favoritos");

        ps = context.getSharedPreferences("mx.pitalla.myapplication.preferences", context.MODE_PRIVATE);

        //Se limpia la lista para que no se vuelvan a agregar los elementos
        listaFavoritos.clear();

        //Pasar la lista del HashMap a un ArrayList para que pueda ser mostrada
        Iterator it = listaTemporal.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry) it.next();
            listaFavoritos.add((Favorito) e.getValue());

        }

        adapter = new FavoritoAdapter(this, listaFavoritos);
        listView = (ListView) findViewById(R.id.lvFav);
        listView.setAdapter(adapter);
    }

    public void addFav(Context c, String n, String t) {
        ps = c.getSharedPreferences("mx.pitalla.myapplication.preferences", c.MODE_PRIVATE);
        editor = ps.edit();

        nuevo = new Favorito(n, t);

        //Se añaden a las preferencias compartidas
        editor.putString("nombre" + n, n);
        editor.putString("numero" + t, t);

        //String nom = editor.putString("nombre" + n, n).toString();
        //String tel = editor.putString("numero" + t, t).toString();

        /*String nombre = nuevo.getNombre();
        Toast.makeText(c, nombre, Toast.LENGTH_SHORT).show();

        boolean b = listaTemporal.contains(nuevo);
        Toast.makeText(c, "si "+b, Toast.LENGTH_SHORT).show();
        listaTemporal.add(nuevo);*/

        /*for (Favorito f : listaTemporal) {
            boolean b = listaTemporal.get(1).getTelefono().contains()
            Toast.makeText(c, "Existira? "+ b, Toast.LENGTH_SHORT).show();
        }*/

        //Se guardan en la coleccion
        if (listaTemporal.containsKey(n)) {
            listaTemporal.remove(n);
            Toast.makeText(c, "Se elimino de favoritos", Toast.LENGTH_SHORT).show();
        }
        else {
            listaTemporal.put(n, nuevo);
            Toast.makeText(c, "Se añadio a favoritos", Toast.LENGTH_SHORT).show();
        }

        editor.commit();

    }

}
