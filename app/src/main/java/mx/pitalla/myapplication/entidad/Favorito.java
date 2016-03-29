package mx.pitalla.myapplication.entidad;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Fernando on 13/03/2016.
 */
public class Favorito implements Parcelable {
    String nombre;
    String telefono;

    public Favorito() {}

    public Favorito(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    protected Favorito(Parcel in) {
        nombre = in.readString();
        telefono = in.readString();
    }

    public static final Creator<Favorito> CREATOR = new Creator<Favorito>() {
        @Override
        public Favorito createFromParcel(Parcel in) {
            return new Favorito(in);
        }

        @Override
        public Favorito[] newArray(int size) {
            return new Favorito[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(telefono);
    }
}
