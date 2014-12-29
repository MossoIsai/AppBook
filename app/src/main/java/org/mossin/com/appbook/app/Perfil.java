package org.mossin.com.appbook.app;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;



public class Perfil extends Activity {
    /*Variables dentro de este layout*/
    private TextView bookTotal,bookPrecioTotal;
    private DrawerLayout navDraweLayout;
    private ListView navLista;
    private TypedArray navIconos;
    private String[] titulo;
    private ArrayList<ItmObject> navItems;
    NavigationAdapter navigationAdapter;
    AyudanteBd aBD;
    ArrayList<String>valores;
    SQLiteDatabase db;
    private String nombreBaseDatos = "LIBRERIA";
    int var = 0;
    int resultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);


        bookTotal = (TextView)findViewById(R.id.totalLibros);
        bookPrecioTotal = (TextView)findViewById(R.id.totalDinero);

        try{
            aBD =  new AyudanteBd(this,nombreBaseDatos,null,1);
            db = aBD.getReadableDatabase();
            if(db != null){
                Cursor cursor = db.rawQuery("SELECT * FROM Libros",null);
                //Numero de filas
                int num = cursor.getCount();
                bookTotal.setText(""+num);
            }

        }catch (Exception e){
            Toast.makeText(this,"Error en la consulta",Toast.LENGTH_LONG).show();

        }
        finally {
            if(db != null){
                db.close();
            }
        }
          //Consulta los precios
        try{
            aBD = new AyudanteBd(this,nombreBaseDatos,null,1);
            db = aBD.getReadableDatabase();
            if (db != null) {
                valores = new ArrayList<String>();
               Cursor cursor2 = db.rawQuery("SELECT * FROM Libros", null);
                while (cursor2.moveToNext()) {
                   valores.add(cursor2.getString(4).toString());
                }

                for (int i=0; i <valores.size() ; i++){
                    /*El metodo get es para obtener un elemento de un artayList parecido a
                    * arreglo[4] ejemplo*/
                      var = Integer.parseInt(valores.get(i));
                   resultado +=var;
                }
                int total= var;
                System.out.println("El total es: "+resultado);
                 bookPrecioTotal.append(String.valueOf(resultado));
            }//fin if

        }catch (Exception e){
            Toast.makeText(this,"Error en la consulta"+e.getMessage(),Toast.LENGTH_LONG).show();
            System.out.println(e.getMessage());

        }

        finally {
            if(db != null){
                db.close();
            }
        }


        // menu Despeable
        navDraweLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navLista = (ListView)findViewById(R.id.lista);
        //declaramos el header el layout

        View header = getLayoutInflater().inflate(R.layout.header,null);
        //Establecemos el header
        navLista.addHeaderView(header);
        //Tomamos lo iconos desde String
        navIconos = getResources().obtainTypedArray(R.array.nav_iconos);
        //Toamos el listado de los titulos
        titulo = getResources().getStringArray(R.array.nav_optiones);
        //Listado de imgs desde drawable
        navItems = new ArrayList<ItmObject>();
        //perfil
        navItems.add( new ItmObject(titulo[0],navIconos.getResourceId(0,-1)));
        navItems.add( new ItmObject(titulo[1],navIconos.getResourceId(1,-1)));
        navItems.add( new ItmObject(titulo[2],navIconos.getResourceId(2,-1)));
        navItems.add( new ItmObject(titulo[3],navIconos.getResourceId(3,-1)));
        navItems.add( new ItmObject(titulo[4],navIconos.getResourceId(4,-1)));
        navItems.add( new ItmObject(titulo[5],navIconos.getResourceId(5,-1)));
        navItems.add( new ItmObject(titulo[6],navIconos.getResourceId(6,-1)));

        navigationAdapter = new NavigationAdapter(this,navItems);
        navLista.setAdapter(navigationAdapter);

        navLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 1:
                        Intent perfil = new Intent(Perfil.this,Perfil.class);
                        startActivity(perfil);
                        break;
                    case 2:
                        Intent registra = new Intent(Perfil.this,Bienvenido.class);
                        startActivity(registra);

                        break;
                    case 3:
                        Intent consulta = new Intent(Perfil.this,Consulta.class);
                        startActivity(consulta);
                        break;
                    case 4:
                        Intent elimina = new Intent(Perfil.this,Elimina.class);
                        startActivity(elimina);
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                }

            }
        });





    }




}
