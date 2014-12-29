package org.mossin.com.appbook.app;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AltaUsuario extends Activity implements View.OnClickListener {
    private EditText usuario,passwd;
    private Button registrar,cancelar;
    AyudanteBd aBd;
    SQLiteDatabase db;
    String usuarioCad,passwdCad;
    private String nombreBaseDatos = "LIBRERIA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_usuario);

        usuario = (EditText)findViewById(R.id.reditText);
        passwd = (EditText)findViewById(R.id.reditText2);
        registrar = (Button)findViewById(R.id.rbutton);
        cancelar = (Button)findViewById(R.id.rbutton2);

        registrar.setOnClickListener(this);
        cancelar.setOnClickListener(this);



    }


    @Override
    public void onClick(View v) {
        usuarioCad = usuario.getText().toString().trim();
        passwdCad = passwd.getText().toString().trim();
        String queryInsertUsuarios ="INSERT INTO Usuarios VALUES('"+usuarioCad+"','"+passwdCad+"')";
        switch (v.getId()){

            case R.id.rbutton:
                if(usuarioCad.equals("") && passwdCad.equals("")){
                  Toast.makeText(this,"Llena el campo vacio",Toast.LENGTH_LONG).show();
              }
              else {
                  try {
                      aBd = new AyudanteBd(this,nombreBaseDatos,null,1);

                      db = aBd.getWritableDatabase();
                      if(db != null){//Si la base de datos se creo
                          db.execSQL(queryInsertUsuarios);
                          Toast.makeText(this,"¡Usuario Registrado!",Toast.LENGTH_LONG).show();
                      }

                  }catch (Exception e){
                      Toast.makeText(this,"Error al ingresa usuario: "+e.getMessage(),Toast.LENGTH_LONG).show();
                  }
                  finally {
                      if (db != null){
                          db.close();
                      }
                  }
              }

            break;
            case R.id.rbutton2:
                Intent regresaPrincipio = new Intent(this,Principal.class);
                startActivity(regresaPrincipio);
            break;
        }

    }
}
