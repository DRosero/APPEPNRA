package com.example.epn.vista;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.epn.appraepn.R;

/**
 * Created by Gabriel on 03/04/2015.
 */
public class ActivityRegistrarResponsable extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        System.out.println("registrar responsable ojo");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_responsable);

    }

    public void irCancelar (View view){
        onPause();
        Toast.makeText(getApplicationContext(), "Listando Responsables", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ActivityAdministrarPaciente.class);
        startActivity(intent );
    }

    public void irGuardar(View view){
        Toast.makeText(getApplicationContext(), "Implementar", Toast.LENGTH_SHORT).show();
    }
}
