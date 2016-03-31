package mx.pitalla.myapplication.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by soygo on 29/03/2016.
 */
//SQLiteOpenHelper permite 2 metodos para abrirla ya sea en modo escritura o lectura
public class DbHelper extends SQLiteOpenHelper {

    //Constantes para la db de sqlite
    private static final String DB_NAME = "GuaymasV6.sqlite";
    private static final int DB_SCHEME_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEME_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {//Crea la DB sqlite
        db.execSQL(DBManager.CREATE_TABLE_D);
        db.execSQL(DBManager.CREATE_TABLE_E);
        db.execSQL(DBManager.CREATE_TABLE_N);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {//Upgradea la db de sqlite

    }
}
