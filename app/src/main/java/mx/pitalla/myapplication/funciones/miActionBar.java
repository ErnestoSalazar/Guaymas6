package mx.pitalla.myapplication.funciones;

import android.support.v7.app.ActionBar;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;

import mx.pitalla.myapplication.R;


public class miActionBar {

  public miActionBar(ActionBar ab, String tipo){


      if(tipo == "noticia"){
          //ab.setHomeButtonEnabled(true);
          //ab.setDisplayHomeAsUpEnabled(true);
          ab.setTitle("Noticias");
          //ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7d0aa1")));
      }
      else if(tipo == "evento"){
          //ab.setHomeButtonEnabled(true);
          //ab.setDisplayHomeAsUpEnabled(true);
          ab.setTitle("Eventos");
          //ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e10a5b")));
      }
      else if(tipo == "radio"){
          //ab.setHomeButtonEnabled(true);
          //ab.setDisplayHomeAsUpEnabled(true);
          ab.setTitle("Radio");
          //ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0fcbc9")));
      }
      else if(tipo == "reporte"){
          //ab.setHomeButtonEnabled(true);
          //ab.setDisplayHomeAsUpEnabled(true);
          ab.setTitle("Reporte");
          //ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#5089a6")));
      }
      else if(tipo == "directorio"){
          //ab.setHomeButtonEnabled(true);
          //ab.setDisplayHomeAsUpEnabled(true);
          ab.setTitle("Directorio");
          //ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f5bc08")));
      }
      else if (tipo == "favoritos") {
          ab.setTitle("Favoritos");
      }


/*    final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
      BitmapDrawable background = new BitmapDrawable (BitmapFactory.decodeResource(getResources(), R.drawable.abnoticias));
      actionBar.setHomeButtonEnabled(true);
      actionBar.setDisplayHomeAsUpEnabled(true);
      actionBar.setTitle("Noticias");
      actionBar.getHeight();
      actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#651080")));
      actionBar.setBackgroundDrawable(background);*/
  }


}
