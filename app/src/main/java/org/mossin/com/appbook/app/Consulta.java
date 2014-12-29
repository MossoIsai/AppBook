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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Consulta extends Activity  {
    private DrawerLayout navDraweLayout;
    private ListView navLista;
    private TypedArray navIconos;
    private String[] titulo;
    private ArrayList<ItmObject> navItems;
    NavigationAdapter navigationAdapter;

    /*Variables de esta clase de Consulta*/
    private ListView listaLibros;
    AyudanteBd aBD;
    SQLiteDatabase db = null;
    private String nombreBaseDatos = "LIBRERIA";
    private ArrayList<String> arrayListaLibros;
    private String queryConsulta = "SELECT nombre FROM Libros";
    ArrayAdapter<String> adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
        /*Variables de esta clase de Consulta*/
        listaLibros = (ListView)findViewById(R.id.libros);
        listaLibros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // al momento de seleccionar un elemento de la lista te manda al la cosulta general
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String nombreLibro = (String) listaLibros.getItemAtPosition(position);
                //Toast.makeText(getApplicationContext(),nombreLibro,Toast.LENGTH_LONG).show();

               Intent consultaIndiv = new Intent(getApplicationContext(),ConsultaIndividual.class);
                Bundle bolsa = new Bundle();
                bolsa.putString("LIBRO",nombreLibro);
                consultaIndiv.putExtras(bolsa);
                startActivity(consultaIndiv);
            }
        });

         try {
             aBD =  new AyudanteBd(this,nombreBaseDatos,null,1);
             db = aBD.getReadableDatabase();//Consulta por eso del getReadableDatabase
             if(db != null){
                 Cursor cursor = db.rawQuery(queryConsulta,null);
                 //Solo obtendre el titulo del libro la posicion [0]
                 arrayListaLibros =  new ArrayList<String>();
                 while(cursor.moveToNext()){
                      arrayListaLibros.add(cursor.getString(0).toString());
                 }

                 cursor.close();
                 //incluyendo los titulos de los libros en la ListView
                adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayListaLibros);
                listaLibros.setAdapter(adaptador);
             }

         }catch (Exception e){
             Toast.makeText(this,"Error en la consulta General: "+ e.getMessage(),Toast.LENGTH_LONG).show();
         }
         finally {
             if(db != null) {
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
                        Intent perfil = new Intent(Consulta.this,Perfil.class);
                        startActivity(perfil);
                        break;
                    case 2:
                        Intent regitra = new Intent(Consulta.this,Bienvenido.class);
                        startActivity(regitra);
                        break;
                    case 3:
                        Intent consulta = new Intent(Consulta.this,Consulta.class);
                        startActivity(consulta);
                        break;
                    case 4:
                        Intent elimina = new Intent(Consulta.this,Elimina.class);
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
