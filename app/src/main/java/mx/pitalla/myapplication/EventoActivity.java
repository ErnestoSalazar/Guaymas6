package mx.pitalla.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.androidquery.AQuery;
import com.squareup.picasso.Picasso;

import mx.pitalla.myapplication.entidad.Evento;
import mx.pitalla.myapplication.funciones.miActionBar;


/**
 * Created by soygo on 19/02/2016.
 */
public class EventoActivity extends AppCompatActivity{
    AQuery aq;
    Evento evento;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_evento);

        aq = new AQuery(this);
        evento = getIntent().getParcelableExtra("datosEvento");

        miActionBar ab = new miActionBar(getSupportActionBar(),"evento");

        String subFecha1 = evento.getFecha().substring(0, 4);
        String subFecha2 = evento.getFecha().substring(5,7);
        String subFecha3 = evento.getFecha().substring(8);


        if(subFecha2.equals("01") ){
            subFecha2="enero";
        }
        else if(subFecha2.equals("02")){
            subFecha2="febrero";
        }
        else if(subFecha2.equals("03")){
            subFecha2="marzo";
        }
        else if(subFecha2.equals("04")){
            subFecha2="abril";
        }
        else if(subFecha2.equals("05")){
            subFecha2="mayo";
        }
        else if(subFecha2.equals("06")){
            subFecha2="junio";
        }
        else if(subFecha2.equals("07")){
            subFecha2="julio";
        }
        else if(subFecha2.equals("08")){
            subFecha2="agosto";
        }
        else if(subFecha2.equals("09")){
            subFecha2="septiembre";
        }
        else if(subFecha2.equals("10")){
            subFecha2="octubre";
        }
        else if(subFecha2.equals("11")){
            subFecha2="noviembre";
        }
        else if(subFecha2.equals("12")){
            subFecha2="diciembre";
        }
        String fecha = subFecha3+"-"+subFecha2+"-"+subFecha1;




        aq.id(R.id.txtTituloEventoDetalle).text(evento.getNombre_evento());
        aq.id(R.id.txtOrganizaEventoDetalle).text(evento.getOrganiza());
        aq.id(R.id.txtLugarEventoDetalle).text(evento.getLugar());
        aq.id(R.id.txtFechaEventoDetalle).text(fecha);
        aq.id(R.id.txtHoraEventoDetalle).text(evento.getHora());
        aq.id(R.id.txtDescEventoDetalle).text(evento.getDesc());
        aq.id(R.id.txtContactoEventoDetalle).text(evento.getContacto());
        Picasso.with(this).load(evento.getImagen()).error(R.drawable.noticiadefault).into(aq.id(R.id.imgEventoDetalle).getImageView());


    }
}
