package org.mossin.com.appbook.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Principal extends ActionBarActivity implements View.OnClickListener {
    private Button entrar,salir;
    private EditText correo,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_principal);
        entrar = (Button)findViewById(R.id.button);
        salir = (Button)findViewById(R.id.button2);

        correo = (EditText)findViewById(R.id.editText);
        password = (EditText)findViewById(R.id.editText2);

        entrar.setOnClickListener(this);
        salir.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        String email = correo.getText().toString().trim();
        String passwd = password.getText().toString().trim();
        switch (v.getId()){
            case R.id.button:
                if(email.equals("")){
                    Toast.makeText(this,"Ingresa un correo electronico",Toast.LENGTH_LONG).show();
                }else if(passwd.equals("")){
                    Toast.makeText(this,"Ingresa una contraseña",Toast.LENGTH_LONG).show();
                }else{
                    if(email.equals("isai") && passwd.equals("123") ){
                        correo.setText("");
                        password.setText("");
                        Intent perfil = new Intent(this,Perfil.class);
                        startActivity(perfil);

                    }else{
                        Toast.makeText(this,"Usuario o Contraseña Incorrecta",Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case R.id.button2:
                //boton salir
                finish();
                break;
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.perfil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent registro = new Intent(this,AltaUsuario.class);
            startActivity(registro);

        }
        return super.onOptionsItemSelected(item);
    }



}
