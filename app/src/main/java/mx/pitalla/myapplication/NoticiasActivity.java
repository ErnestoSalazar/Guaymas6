package mx.pitalla.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import mx.pitalla.myapplication.adapter.NoticiaAdapter;
import mx.pitalla.myapplication.entidad.Noticia;
import mx.pitalla.myapplication.funciones.ConstantesConfiguracion;
import mx.pitalla.myapplication.funciones.SharedPreferenceHelper;
import mx.pitalla.myapplication.funciones.miActionBar;

public class NoticiasActivity extends AppCompatActivity {
    AQuery aq ;
    Noticia noticia= new Noticia();
    ArrayList listaNoticias;
    NoticiaAdapter adapter;
    ListView lvNoticias;

    int totalNoticiasPagina;
    int totalNoticias;
    int totalPaginas;
    JSONArray jsonNoticias;
    Context context;
    SharedPreferenceHelper sharPrefHelper;
    int pagina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);
        context=this;
        aq = new AQuery(this);
        sharPrefHelper = new SharedPreferenceHelper(ConstantesConfiguracion.SHARED_PREF_NAME, this);
        pagina=Integer.parseInt(sharPrefHelper.getStringFromShprf(ConstantesConfiguracion.PAGINA));

        miActionBar ab= new miActionBar(getSupportActionBar(),"noticia");
        listaNoticias = new ArrayList<Noticia>();
        totalPaginas=-1;
        asyncJson(pagina);

        aq.id(R.id.btnAtrasNoticias).clicked(this, "atrasNoticias");
        aq.id(R.id.btnSiguienteNoticias).clicked(this, "SiguienteNoticias");



    }

    public void atrasNoticias(View button){
            pagina = pagina - 1;
            sharPrefHelper.writeString(ConstantesConfiguracion.PAGINA, String.valueOf(pagina));

            asyncJson(pagina);
    }


    public void SiguienteNoticias(View button){
        pagina=pagina+1;
        sharPrefHelper.writeString(ConstantesConfiguracion.PAGINA, String.valueOf(pagina));

        asyncJson(pagina);
    }

    public void validarBtnPaginador(){
        getSupportActionBar().setTitle("Noticias - página ("+ pagina + " de " + totalPaginas + ")");
        if(pagina>1){
            aq.id(R.id.btnAtrasNoticias).getButton().setVisibility(View.VISIBLE);
        } else {
            aq.id(R.id.btnAtrasNoticias).getButton().setVisibility(View.GONE);
        }

        if(pagina==totalPaginas){
            aq.id(R.id.btnSiguienteNoticias).getButton().setVisibility(View.GONE);
        } else {
            aq.id(R.id.btnSiguienteNoticias).getButton().setVisibility(View.VISIBLE);
        }

    }

    public void asyncJson(int pagina){
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setIndeterminate(true); dialog.setCancelable(true); dialog.setInverseBackgroundForced(false); dialog.setCanceledOnTouchOutside(true); dialog.setTitle("Descargando Noticias...");
        String url ="http://guaymas.gob.mx/api/get_posts/?count=10&page="+pagina;
        aq.progress(R.id.progressBar).ajax(url, JSONObject.class, this, "jsonCallback");

    }

    public void jsonCallback(String url, JSONObject json, AjaxStatus status){

        if(json != null){

            try {
                jsonNoticias =  json.getJSONArray("posts");
                totalNoticias=json.getInt("count_total");
                totalNoticiasPagina=json.getInt("count");
                totalPaginas=json.getInt("pages");

                listaNoticias.clear();
                for (int i = 0; i < jsonNoticias.length(); i++) {

                    JSONObject item = jsonNoticias.getJSONObject(i);
                    JSONObject imgitemtipo = item.getJSONObject("thumbnail_images");
                    JSONObject imgitem = imgitemtipo.getJSONObject("large");
                    JSONArray categoriaa = item.getJSONArray("categories");

                    listaNoticias.add(new Noticia(
                            item.getInt("id"),
                            item.getString("url"),
                            item.getString("status"),
                            item.getString("date"),
                            item.getString("content"),
                            item.getString("title"),
                            categoriaa.getJSONObject(0).getString("title"),
                            imgitem.getString("url")
                    ));

                }

                // Inicializamos el adapter.
                validarBtnPaginador();
                adapter = new  NoticiaAdapter(this, listaNoticias);

                // Asignamos el Adapter al ListView
                lvNoticias= (ListView) findViewById(R.id.lvNoticias);
                lvNoticias.setAdapter(adapter);

                lvNoticias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Noticia datosNoticia = (Noticia) parent.getAdapter().getItem(position);
                        Intent i = new Intent(context, NoticiaActivity.class);
                        i.putExtra("datosNoticia", datosNoticia);
                        startActivity(i);
                    }
                });

                validarBtnPaginador();

            } catch (JSONException e) {
                e.printStackTrace();
        }
        }else{
            //ajax error, show error code
            Toast.makeText(aq.getContext(), "Error:" + status.getCode(), Toast.LENGTH_LONG).show();
        }
    }


}
