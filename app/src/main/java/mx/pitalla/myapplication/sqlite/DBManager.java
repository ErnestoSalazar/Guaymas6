package mx.pitalla.myapplication.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by soygo on 29/03/2016.
 */
public class DBManager {


    private DbHelper helper;
    private SQLiteDatabase db;

    public DBManager(Context context){
        helper = new DbHelper(context);
        db = helper.getWritableDatabase();
    }

    //Tabla para el directorio
    public static final String TABLE_NAME_DIRECTORIO = "directorio";

    public static final String CN_ID = "_id"; //CN = ColumnName
    public static final String CN_NOMBRE = "nombre";
    public static final String CN_DEPENDENCIA_TEXT = "dependencia_text";
    public static final String CN_TIPO = "tipo";
    public static final String CN_CORREO = "correo";
    public static final String CN_TELEFONO ="telefono";
    public static final String CN_DIRECCION ="direccion";
    public static final String CN_WEB = "web";
    public static final String CN_LAT = "latitud";
    public static final String CN_LONG = "longitud";

    public static final String CREATE_TABLE_D ="create table "+TABLE_NAME_DIRECTORIO+" ("
            +CN_ID+" integer primary key autoincrement not null,"
            +CN_NOMBRE+" text not null,"
            +CN_DEPENDENCIA_TEXT+" text,"
            +CN_TIPO+" text not null,"
            +CN_CORREO+" text,"
            +CN_TELEFONO+" text not null,"
            +CN_DIRECCION+" text not null,"
            +CN_WEB+" text,"
            +CN_LAT+" text,"
            +CN_LONG+ " text);";


    public ContentValues generarContentValuesDirectorios(String nombre, String dependencia_text, String tipo, String correo, String telefono, String direccion,String web, String latitud, String longitud){
        ContentValues valores = new ContentValues();
        valores.put(CN_NOMBRE, nombre);
        valores.put(CN_DEPENDENCIA_TEXT, dependencia_text);
        valores.put(CN_TIPO, tipo);
        valores.put(CN_CORREO, correo);
        valores.put(CN_TELEFONO, telefono);
        valores.put(CN_DIRECCION, direccion);
        valores.put(CN_WEB, web);
        valores.put(CN_LAT, latitud);
        valores.put(CN_LONG, longitud);
        return valores;
    }

    public void insertarDirectorios(String nombre, String dependencia_text, String tipo, String correo, String telefono, String direccion,String web, String latitud, String longitud){
        db.insert(TABLE_NAME_DIRECTORIO, null, generarContentValuesDirectorios(nombre, dependencia_text, tipo, correo, telefono, direccion, web, latitud, longitud));
    }

    public void eliminarDirectorio(){
        //db.delete(TABLE_NAME_DIRECTORIO, CN_NOMBRE + "=?", new String[]{nombre});
        db.execSQL("DELETE  FROM " + TABLE_NAME_DIRECTORIO);
    }

    public void modificarRegistroDirectorio(String nombre, String dependencia_text, String tipo, String correo, String nuevoTelefono, String direccion,String web, String latitud, String longitud){
        db.update(TABLE_NAME_DIRECTORIO, generarContentValuesDirectorios(nombre, dependencia_text, tipo, correo, nuevoTelefono, direccion, web, latitud, longitud),CN_NOMBRE+"=?",new String[]{nombre});
    }

    //Recuperar/cargar datos
    public Cursor cargarCursorDirectorios(){
//        String[] columnas = new String[]{CN_NOMBRE, CN_DEPENDENCIA_TEXT, CN_TIPO, CN_CORREO, CN_TELEFONO, CN_DIRECCION, CN_WEB, CN_LAT, CN_LONG};
//        return db.query(TABLE_NAME_DIRECTORIO, columnas, null, null, null, null, null);
        Cursor todoCursor = db.rawQuery("SELECT * FROM "+TABLE_NAME_DIRECTORIO,null);
        return todoCursor;
    }




    /*---------------------------------------------------------------------------------------*/

    //Tabla para Evento
    public static final String TABLE_NAME_EVENTO = "evento";

    public static final String CN_ID_E = "_id";
    public static final String CN_NOMBRE_EVENTO = "nombre_evento";
    public static final String CN_DESC_E ="desc";
    public static final String CN_FECHA_E = "fecha";
    public static final String CN_LUGAR_E = "lugar";
    public static final String CN_HORA_E = "hora";
    public static final String CN_ORGANIZA_E ="organiza";
    public static final String CN_CONTACTO_E = "contacto";
    public static final String CN_IMAGEN_E = "imagen";

    public static final String CREATE_TABLE_E = "create table "+TABLE_NAME_EVENTO+" ("
            +CN_ID_E+" integer primary key not null,"
            +CN_NOMBRE_EVENTO+" text,"
            +CN_DESC_E+" text,"
            +CN_FECHA_E+" text,"
            +CN_LUGAR_E+" text,"
            +CN_HORA_E+" text,"
            +CN_ORGANIZA_E+" text,"
            +CN_CONTACTO_E+" text,"
            +CN_IMAGEN_E+" text);";


    public ContentValues generarContentValuesEventos(String nombre_evento, String desc ,String fecha,  String lugar,  String hora,  String organiza,  String contacto,  String imagen){
        ContentValues valores = new ContentValues();
        valores.put(CN_NOMBRE_EVENTO, nombre_evento);
        valores.put(CN_DESC_E, desc);
        valores.put(CN_FECHA_E, fecha);
        valores.put(CN_LUGAR_E, lugar);
        valores.put(CN_HORA_E, hora);
        valores.put(CN_ORGANIZA_E, organiza);
        valores.put(CN_CONTACTO_E, contacto);
        valores.put(CN_IMAGEN_E, imagen);

        return valores;
    }

    public void insertarEventos(String nombre_evento, String desc ,String fecha,  String lugar,  String hora,  String organiza,  String contacto,  String imagen){
        db.insert(TABLE_NAME_EVENTO, null, generarContentValuesEventos(nombre_evento, desc, fecha, lugar, hora, organiza, contacto, imagen));
    }

    public void eliminarEvento(){
//        db.delete(TABLE_NAME_EVENTO, CN_NOMBRE_EVENTO + "=?", new String[]{nombre_evento});
        db.execSQL("DELETE FROM "+TABLE_NAME_EVENTO);
    }

    public void modificarRegistroEvento(String nombre_evento, String desc ,String fecha,  String lugar,  String hora,  String organiza,  String contacto,  String imagen){
        db.update(TABLE_NAME_EVENTO, generarContentValuesEventos(nombre_evento, desc, fecha, lugar, hora, organiza, contacto, imagen), CN_NOMBRE_EVENTO + "=?", new String[]{nombre_evento});
    }

    public Cursor cargarCursorEventos(){
        Cursor todoCursor = db.rawQuery("SELECT * FROM "+TABLE_NAME_EVENTO,null);
        return todoCursor;
    }




    /*---------------------------------------------------------------------------------------*/

    //Tabla para Noticia
    public static final String TABLE_NAME_NOTICIA = "noticia";

    public static final String CN_ID_N = "id";
    public static final String CN_URL_N = "url";
    public static final String CN_STATUS_N = "status";
    public static final String CN_TITLE_N = "title";
    public static final String CN_CONTENT_N ="content";
    public static final String CN_DATE_N = "date";
    public static final String CN_CATEGORIA_N ="categoria";
    public static final String CN_IMG_N = "img";
    public static final String CN_URLIMG_N = "urlimg";

    public static final String CREATE_TABLE_N = "create table "+TABLE_NAME_NOTICIA+" ("
            +CN_ID_N+" integer primary key not null,"
            +CN_URL_N+" text,"
            +CN_STATUS_N+" text,"
            +CN_TITLE_N+" text,"
            +CN_CONTENT_N+" text,"
            +CN_DATE_N+" text,"
            +CN_CATEGORIA_N+" text,"
            +CN_IMG_N+" integer,"
            +CN_URLIMG_N+" text);";


    public ContentValues generarContentValuesNoticias(String url, String status, String title, String content, String date, String categoria, int  img, String urlimg){
        ContentValues valores = new ContentValues();

        valores.put(CN_URL_N, url);
        valores.put(CN_STATUS_N, status);
        valores.put(CN_TITLE_N, title);
        valores.put(CN_CONTENT_N, content);
        valores.put(CN_DATE_N, date);
        valores.put(CN_CATEGORIA_N, categoria);
        valores.put(CN_IMG_N, img);
        valores.put(CN_URLIMG_N, urlimg);

        return valores;
    }

    public void insertarNoticias(String url, String status, String title, String content, String date, String categoria, int  img, String urlimg){
        db.insert(TABLE_NAME_NOTICIA, null, generarContentValuesNoticias(url, status, title, content, date, categoria, img, urlimg));
    }

    public void eliminarNoticia(String title){
        db.delete(TABLE_NAME_NOTICIA, CN_TITLE_N + "=?", new String[]{title});
    }

    public void modificarRegistroNoticia(String url, String status, String title, String content, String date, String categoria, int  img, String urlimg){
        db.update(TABLE_NAME_NOTICIA, generarContentValuesNoticias(url, status, title, content, date, categoria, img, urlimg), CN_TITLE_N + "=?", new String[]{title});
    }



    //Comprobamos que exista conexión a internet

    //Conexion a wifi
    protected Boolean conexionWifi(Context context){
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivity != null){
            NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

            if(info != null){
                if(info.isConnected()){
                    return true;
                }
            }
        }
        return false;
    }

    //Conecxion via Datos
    protected Boolean conexionDatos(Context context){
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivity != null){
            NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if(info != null){
                if(info.isConnected()){
                    return true;
                }
            }
        }
        return false;
    }


    //Creamos un metodo general para saber si estamos conectados con wifi o datos :D
    public Boolean tipoConexion(Context context){
        if(conexionWifi(context)){
            Log.i("Conexion: ","Estas conectado via wifi");
            return true;
        }
        else if(conexionDatos(context)){
            Log.i("Conexion: ","Estacas conectado via datos");
            return true;
        }
        else{
            return false;
        }
    }

}
