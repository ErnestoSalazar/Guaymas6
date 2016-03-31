package mx.pitalla.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.androidquery.AQuery;

import mx.pitalla.myapplication.funciones.ConstantesConfiguracion;
import mx.pitalla.myapplication.funciones.SharedPreferenceHelper;
import mx.pitalla.myapplication.sqlite.DBManager;
import mx.pitalla.myapplication.sqlite.DbHelper;

public class MainActivity extends AppCompatActivity {
    ImageButton btnNoticias;
    AQuery aq;
    SharedPreferenceHelper sharPrefHelper;
    DBManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharPrefHelper = new SharedPreferenceHelper(ConstantesConfiguracion.SHARED_PREF_NAME, this);

        aq= new AQuery(this);

        /*btnNoticias = (ImageButton) findViewById(R.id.btnNoticias);

        btnNoticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ir(NoticiasActivity.class);
            }
        });*/

        preferencias();

        manager = new DBManager(this);

    }



    public void preferencias(){
        sharPrefHelper.writeString(ConstantesConfiguracion.PAGINA, "1");
    }

    public void ir(Class c){
        Intent intent = new Intent(this,c);
        startActivity(intent);
    }

    public void irNoticias(View v) {
        Intent intent = new Intent(this, NoticiasActivity.class);
        startActivity(intent);
    }

    public void irEventos(View v){
        Intent intent = new Intent(this, EventosActivity.class);
        startActivity(intent);
    }
    public void irReporte(View v){
        Intent intent = new Intent(this, ReporteActivity.class);
        startActivity(intent);
    }

    public void irDirectorio(View v){
        Intent intent = new Intent(this,DirectoriosActivity.class);
        startActivity(intent);
    }

    public void irRadio(View v){
        Intent intent = new Intent(this,RadioActivity.class);
        startActivity(intent);
    }

    public void irFacebook(View v){
        try {
            getPackageManager().getPackageInfo("com.facebook.katana", 0);
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/1625672101022560")));
        } catch (Exception e) {
            //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/AquiEsGuaymas")));
            Toast.makeText(aq.getContext(), "No hay Internet", Toast.LENGTH_LONG).show();
        }
    }

    public void irTwitter(View v) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/Guaymas_2016")));
        } catch (Exception e) {
            Toast.makeText(aq.getContext(), "No hay Internet", Toast.LENGTH_LONG).show();
        }
    }

    public void irYoutube(View v){
        try {
            startActivity( new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UC6LQww1epc_VzH7RYMo8HzQ")));
        } catch (Exception e) {
            Toast.makeText(aq.getContext(), "No hay Internet", Toast.LENGTH_LONG).show();
        }
    }

    public void irWeb(View v){
        try {
            startActivity( new Intent(Intent.ACTION_VIEW, Uri.parse("http://guaymas.gob.mx/")));
        } catch (Exception e) {
            Toast.makeText(aq.getContext(), "No hay Internet", Toast.LENGTH_LONG).show();
        }
    }

}
