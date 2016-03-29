package mx.pitalla.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

import mx.pitalla.myapplication.adapter.EventoAdapter;
import mx.pitalla.myapplication.entidad.Evento;
import mx.pitalla.myapplication.funciones.ConstantesConfiguracion;
import mx.pitalla.myapplication.funciones.SharedPreferenceHelper;
import mx.pitalla.myapplication.funciones.miActionBar;

public class EventosActivity extends AppCompatActivity {
    AQuery aq;
    Evento evento;
    ArrayList listaEventos;
    EventoAdapter adapter;
    ListView lvEventos;


    int totalPaginas;
    Context context;
    SharedPreferenceHelper sharPrefHelper;
    int pagina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);
        context = this;
        aq = new AQuery(this);
        sharPrefHelper = new SharedPreferenceHelper(ConstantesConfiguracion.SHARED_PREF_NAME, this);
        pagina = Integer.parseInt(sharPrefHelper.getStringFromShprf(ConstantesConfiguracion.PAGINA));


        miActionBar ab= new miActionBar(getSupportActionBar(),"evento");
        listaEventos = new ArrayList<Evento>();
        totalPaginas = -1;
        asyncJson();


    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }


    public void asyncJson(){
        String url = "http://app.guaymas.gob.mx/api.php/calendario";
        aq.progress(R.id.progressBar).ajax(url, JSONArray.class, this, "jsonCallBack");
    }


    public void jsonCallBack(String url, JSONArray json, AjaxStatus status){

        if(json != null){
            try{

                listaEventos.clear();
                for(int i = 0; i<json.length();i++){

                    JSONObject item = json.getJSONObject(i);

                    listaEventos.add(new Evento( // añadimos un objeto de Evento al array por cada iteracion que hacemos
                            item.getInt("id"),
                            item.getString("nombre_evento"),
                            item.getString("desc"),
                            item.getString("fecha"),
                            item.getString("lugar"),
                            item.getString("hora"),
                            item.getString("organiza"),
                            item.getString("contacto"),
                            "http://guaymas.gob.mx/wp-content/themes/aquiesguaymas/img/calendario/"+item.getString("imagen")
                    ));

                }

                Collections.reverse(listaEventos); // Invierte el orden del array

                adapter = new EventoAdapter(this,listaEventos);

                lvEventos = (ListView) findViewById(R.id.lvEventos);
                lvEventos.setAdapter(adapter);

                lvEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Evento datosEvento = (Evento) parent.getAdapter().getItem(position);
                        Intent i = new Intent(context, EventoActivity.class);
                        i.putExtra("datosEvento", datosEvento);
                        startActivity(i);
                        // Toast.makeText(aq.getContext(), "pos: "+pos+"  title: "+datosNoticia.getTitle(), Toast.LENGTH_LONG).show();
                    }
                });

            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else{
            Toast.makeText(aq.getContext(),"Error: "+status.getCode(), Toast.LENGTH_LONG).show();
        }
    }

    public void itemClicked(AdapterView parent, View v, int pos, long id){
        Toast.makeText(aq.getContext(),"pos: "+pos+" long: "+id, Toast.LENGTH_LONG).show();
    }



}
