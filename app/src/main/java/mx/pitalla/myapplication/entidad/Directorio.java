package mx.pitalla.myapplication.entidad;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by soygo on 27/02/2016.
 */
public class Directorio implements Parcelable{
    private int id;
    private String nombre;
    private String dependencia_text;
    private String tipo;
    private String correo;
    private String telefono;
    private String direccion;
    private String web;
    private String latitud;
    private String longitud;

    public Directorio(){}

    public Directorio(String nombre, String telefono){
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Directorio(String nombre, String telefono, String direccion, String web) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.web = web;
    }

    public Directorio(int id, String nombre, String dependencia_text, String tipo, String correo, String telefono, String direccion, String web, String latitud, String longitud) {
        this.id = id;
        this.nombre = nombre;
        this.dependencia_text = dependencia_text;
        this.tipo = tipo;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.web = web;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    protected Directorio(Parcel in){
        id = in.readInt();
        nombre = in.readString();
        dependencia_text = in.readString();
        tipo = in.readString();
        correo = in.readString();
        telefono = in.readString();
        direccion = in.readString();
        web = in.readString();
        latitud = in.readString();
    }

    public static final Creator<Directorio> CREATOR = new Creator<Directorio>() {
        @Override
        public Directorio createFromParcel(Parcel in) {
            return new Directorio(in);
        }

        @Override
        public Directorio[] newArray(int size) {
            return new Directorio [size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDependencia_text() {
        return dependencia_text;
    }

    public void setDependencia_text(String dependencia_text) {
        this.dependencia_text = dependencia_text;
    }

    public String getTipo(){
        return tipo;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel des, int flags){
        des.writeInt(id);
        des.writeString(nombre);
        des.writeString(dependencia_text);
        des.writeString(tipo);
        des.writeString(correo);
        des.writeString(telefono);
        des.writeString(direccion);
        des.writeString(web);
        des.writeString(latitud);
        des.writeString(longitud);
    }

}
