package org.mossin.com.appbook.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mossin on 6/12/14.
 */
public class AyudanteBd extends SQLiteOpenHelper {
    //Query de la tabla Libros
    private String query="CREATE TABLE Libros(nombre TEXT primary key,autor TEXT,editorial TEXT, descripcion TEXT, precioCompra INTEGER,rating INTEGER)";

    public AyudanteBd(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     db.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
