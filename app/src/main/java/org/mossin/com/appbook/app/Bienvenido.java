package org.mossin.com.appbook.app;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class Bienvenido extends Activity  implements View.OnClickListener{
    private DrawerLayout navDraweLayout;
    private ListView navLista;
    private TypedArray navIconos;
    private String[] titulo;
    private ArrayList<ItmObject> navItems;
    NavigationAdapter navigationAdapter;
    private Button salvar;
    private EditText nombre,autor,editorial,descripcion,precioCompra;
    AyudanteBd aBD;
    SQLiteDatabase db = null;
    private String nombreBaseDatos = "LIBRERIA";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);

        /*Variables  dentro del layout que se muestra*/
        salvar = (Button)findViewById(R.id.button3);
        nombre = (EditText)findViewById(R.id.editText3);
        autor = (EditText)findViewById(R.id.editText4);
        editorial = (EditText)findViewById(R.id.editText5);
        descripcion = (EditText)findViewById(R.id.editText6);
        precioCompra = (EditText)findViewById(R.id.editText7);

        salvar.setOnClickListener(this);


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
                        Intent perfil = new Intent(Bienvenido.this,Perfil.class);
                        startActivity(perfil);
                        break;
                    case 2:
                        Intent registra = new Intent(Bienvenido.this,Bienvenido.class);
                        startActivity(registra);

                        break;
                    case 3:
                        Intent consulta = new Intent(Bienvenido.this,Consulta.class);
                        startActivity(consulta);
                        break;
                    case 4:
                        Intent elimina = new Intent(Bienvenido.this,Elimina.class);
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
    @Override
    public void onClick(View v) {
        String nombreS = nombre.getText().toString();
        String autorS =  autor.getText().toString();
        String editorialS = editorial.getText().toString();
        String descripcionS = descripcion.getText().toString();
        String precioCompraS = precioCompra.getText().toString();
        String queryInsert = "INSERT INTO Libros VALUES('"+nombreS+"','"+autorS+"','"+editorialS+"','"+descripcionS+"',"+precioCompraS+","+0+")";
        //String queryInsert = "INSERT INTO Libros(nombre,autor,editorial,descripcion,precioCompra,rating) VALUES('"+nombreS+"','"+autorS+"','"+editorialS+"','"+descripcionS+"',"+precioCompraS+","+0+","+"null"+")";
     switch(v.getId()){
         case R.id.button3:
             //Boton de Guardar
             if(nombreS.equals("") && autorS.equals("") && editorialS.equals("") && descripcionS.equals("") && precioCompraS.equals("")){
                 Toast.makeText(this,"Llena el campo faltante",Toast.LENGTH_LONG).show();
             }else{
               try {
                   aBD = new AyudanteBd(this, nombreBaseDatos, null, 1);
                   db = aBD.getWritableDatabase();
                   if (db != null) {//Si la base de datos No esta vacia
                       db.execSQL(queryInsert);
                       Toast.makeText(this, "¡Insercción Exitosa!", Toast.LENGTH_LONG).show();
                       nombre.setText("");
                       autor.setText("");
                       editorial.setText("");
                       descripcion.setText("");
                       precioCompra.setText("");
                   }
               }catch (Exception e){
                   Toast.makeText(this, "Error: "+e.getMessage(), Toast.LENGTH_LONG).show();
               }
                 finally {//bloque del finally
                   if(db != null) {
                       db.close();
                   }
               }//fin fially

             }

         break;
     }
    }
}
