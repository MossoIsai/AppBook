package org.mossin.com.appbook.app;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

public class ConsultaIndividual extends Activity {
  private  String libro;
  private TextView nomb,aut,edito,descrip,precioComp;
  private String nombreBaseDatos = "LIBRERIA";
  AyudanteBd aBd;
  SQLiteDatabase db;
    String registro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_individual);
        nomb = (TextView)findViewById(R.id.cItextView);
        aut = (TextView)findViewById(R.id.cItextView2);
        edito = (TextView)findViewById(R.id.cItextView3);
        descrip = (TextView)findViewById(R.id.cItextView4);
        precioComp = (TextView)findViewById(R.id.cItextView5);

        Bundle bolsitaRecibido = getIntent().getExtras();
        libro = bolsitaRecibido.getString("LIBRO");
        String queryConsultInd = "SELECT * FROM Libros WHERE nombre='"+libro+"'";

        try {
            aBd = new AyudanteBd(this,nombreBaseDatos,null,1);
            db = aBd.getReadableDatabase();
            if(db != null){
                Vector<String> valores = new Vector<String>();
                Cursor cursor = db.rawQuery(queryConsultInd,null);
                //Numero de Columnas
                int columnas = cursor.getColumnCount();
                int filas =  cursor.getCount();
                //mientras haya una siguiente fila
                while(cursor.moveToNext()){
                  valores.add(cursor.getString(0));
                  valores.add(cursor.getString(1));
                  valores.add(cursor.getString(2));
                  valores.add(cursor.getString(3));
                  valores.add(cursor.getString(4));
                }
                cursor.close();
                    nomb.setText("Titulo:\t"+valores.get(0).toString());
                    aut.setText("Autor:\t"+valores.get(1).toString());
                    edito.setText("Editorial:\t"+valores.get(2).toString());
                    descrip.setText("Descripción:\t"+valores.get(3).toString());
                    precioComp.setText("Precio de Compra:\t"+"$"+valores.get(4).toString());
            }

        }catch (Exception e){
            Toast.makeText(this, "Error al Consultar", Toast.LENGTH_LONG).show();

        }
        finally {
            if(db != null){
                db.close();
            }
        }


    }



}
