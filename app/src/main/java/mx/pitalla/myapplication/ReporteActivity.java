package mx.pitalla.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import mx.pitalla.myapplication.funciones.miActionBar;

public class ReporteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte);

        miActionBar ab= new miActionBar(getSupportActionBar(),"reporte");

    }



}
