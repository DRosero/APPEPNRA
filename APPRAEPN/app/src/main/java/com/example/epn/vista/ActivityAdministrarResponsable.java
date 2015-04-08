package com.example.epn.vista;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.epn.MainActivity;
import com.example.epn.appraepn.R;
import com.example.epn.modelo.entidades.Responsable;
import com.example.epn.modelo.servicios.ServiciosResponsable;

import java.util.List;

/**
 * Created by Gabriel on 02/04/2015.
 */
public class ActivityAdministrarResponsable extends Activity {

    Bundle extras;
    private ServiciosResponsable serviciosResponsable;
    private ListView lstResponsable;
    private Responsable responsable;

    public void irNuevoResponsable(View vista){
        try {
            serviciosResponsable.abrirConexion();
            List<Responsable> responsable=serviciosResponsable.listarResponsbale();
            serviciosResponsable.cerrarConexion();

            if(responsable.isEmpty()){
                Toast.makeText(getApplicationContext(),"No existen Responsables registrados",Toast.LENGTH_SHORT).show();
            }

            Intent intent = new Intent(this, ActivityRegistrarResponsable.class);
            startActivity(intent);//hasta aqui hice hoy

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

    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrar_responsable);
    }


}
