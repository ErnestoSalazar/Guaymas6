package mx.pitalla.myapplication;

import android.app.ActionBar;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.Toolbar;

import com.androidquery.AQuery;
import com.squareup.picasso.Picasso;

import mx.pitalla.myapplication.entidad.Noticia;
import mx.pitalla.myapplication.funciones.miActionBar;

public class NoticiaActivity extends AppCompatActivity {
    AQuery aq;
    Noticia noticia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_noticia);
        aq = new AQuery(this);

        noticia = getIntent().getParcelableExtra("datosNoticia");
        miActionBar ab= new miActionBar(getSupportActionBar(),"noticia");

        aq.id(R.id.txvTituloNoticiaDetalle).text(noticia.getTitle());
        aq.id(R.id.txvFechaNoticiaDetalle).text(noticia.getDate());
        aq.id(R.id.txvContenidoNoticiaDetalle).text(Html.fromHtml(noticia.getContent()));
        Picasso.with(this).load(noticia.getUrlimg()).error(R.drawable.noticiadefault).into(aq.id(R.id.imgDetalleNoticia).getImageView());


    }

}
