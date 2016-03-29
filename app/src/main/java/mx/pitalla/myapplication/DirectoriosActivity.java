package mx.pitalla.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;

import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import mx.pitalla.myapplication.adapter.DirectorioAdapter;
import mx.pitalla.myapplication.entidad.Directorio;
import mx.pitalla.myapplication.funciones.miActionBar;

public class DirectoriosActivity extends AppCompatActivity {
    AQuery aq;
    ArrayList listaDirectorio;
    DirectorioAdapter adapter;
    ListView lvDirectorio;
    EditText inputText;
    Context context;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_directorio, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_favoritos:
                LayoutInflater inflater = LayoutInflater.from(context);
                View v = inflater.inflate(R.layout.activity_favoritos, null);
                mostrarFavoritos(v);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directorio);
        context = this;
        aq = new AQuery(this);

        inputText = (EditText) findViewById(R.id.etBuscarDependencias);

        miActionBar ab= new miActionBar(getSupportActionBar(),"directorio");

        listaDirectorio = new ArrayList<Directorio>();

        asyncJson();

        inputText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(final CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(final Editable s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        asyncSearch(String.valueOf(s));
                    }
                });
            }
        });

    }

    public void asyncJson(){
        String url = "http://app.guaymas.gob.mx/api2.php/dependencia";
        aq.progress(R.id.progressBar).ajax(url, JSONArray.class, this, "jsonCallBack");
    }

    public void asyncSearch(String busqueda){
        String url = "http://app.guaymas.gob.mx/api2.php/dependencia/"+busqueda;
        aq.ajax(url, JSONArray.class, this, "jsonCallBack");
    }

    public void jsonCallBack(String url, JSONArray json, AjaxStatus status){
        if(json != null){
            try{
                listaDirectorio.clear();
                for(int i = 0; i<json.length();i++){
                    JSONObject item = json.getJSONObject(i);

                    listaDirectorio.add(new Directorio(
                            item.getInt("id"),
                            item.getString("nombre"),
                            item.getString("dependencia_text"),
                            item.getString("tipo"),
                            item.getString("correo"),
                            item.getString("telefono"),
                            item.getString("direccion"),
                            item.getString("web"),
                            item.getString("latitud"),
                            item.getString("longitud")
                    ));
                }

                adapter = new DirectorioAdapter(this, listaDirectorio);

                lvDirectorio = (ListView) findViewById(R.id.lvDependencias);
                lvDirectorio.setAdapter(adapter);

                /*lvDirectorio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Directorio datosDirectorio = (Directorio) parent.getAdapter().getItem(position);
                        Intent i = new Intent(context, DirectorioActivity.class);
                        i.putExtra("datosDirectorio", datosDirectorio);
                        startActivity(i);
                        // Toast.makeText(aq.getContext(), "pos: "+pos+"  title: "+datosNoticia.getTitle(), Toast.LENGTH_LONG).show();
                    }
                });*/

            }
            catch(JSONException ex){
                ex.printStackTrace();
            }
        }
        else{
            Toast.makeText(aq.getContext(), "Error: " + status.getCode(), Toast.LENGTH_LONG).show();
        }

    }

    public void mostrarFavoritos(View v) {
        //Toast.makeText(this, "Añadido a favoritos", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, FavoritosActivity.class);
        startActivity(intent);
    }

}