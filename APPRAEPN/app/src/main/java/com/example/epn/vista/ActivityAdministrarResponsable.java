package com.example.epn.vista;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.epn.MainActivity;
import com.example.epn.appraepn.R;

/**
 * Created by Gabriel on 02/04/2015.
 */
public class ActivityAdministrarResponsable extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrar_responsable);
    }

    public void irNuevoResponsable(View vista){
        try {
            Intent intent = new Intent(this, ActivityRegistrarResponsable.class);
            startActivity(intent);

        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(), "No se puede abrir ventana Registrar Respnsable", Toast.LENGTH_SHORT).show();
        }
    }

    public void irRegresar (View view){
        onPause();
        Toast.makeText(getApplicationContext(),"Menú Principal", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent );
    }
}
