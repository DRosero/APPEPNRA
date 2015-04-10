package com.example.epn;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.epn.appraepn.R;
import com.example.epn.vista.ActivityAdministrarPaciente;
import com.example.epn.vista.ActivityAdministrarResponsable;
import com.example.epn.vista.ActivityAdministrarSitiosConocidos;
import com.example.epn.vista.ActivityAreaMovimientoPermitido;
import com.example.epn.vista.ActivityEstablecerIntervaloAlertas;
import com.example.epn.vista.ActivityEstablecerSitioCentral;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void irAdministrarPaciente (View vista){
        try {
            Intent intent = new Intent(this, ActivityAdministrarPaciente.class);
            startActivity(intent);
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(),"Error en Main: No se puede abrir este módulo", Toast.LENGTH_SHORT).show();
        }
    }
    public void irAdministrarResponsable (View vista){
        try {
            Intent intent = new Intent(this,ActivityAdministrarResponsable.class);
            startActivity(intent);
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(),"Error en Main: No se puede abrir este módulo", Toast.LENGTH_SHORT).show();
        }

    }

    public void irEstablecerSitioCentral (View vista){
        try {
            Intent intent = new Intent(this,ActivityEstablecerSitioCentral.class);
            startActivity(intent);
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(),"Error en Main: No se puede abrir este módulo", Toast.LENGTH_SHORT).show();
        }

    }
    public void irAreaMovimientoPermitido (View vista){
        try {
            Intent intent = new Intent(this, ActivityAreaMovimientoPermitido.class);
            startActivity(intent);
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(),"Error en Main: No se puede abrir este módulo", Toast.LENGTH_SHORT).show();
        }
    }
    public void irAdministrarSitiosConocidos (View vista){
        try {
            Intent intent = new Intent(this,ActivityAdministrarSitiosConocidos.class);
            startActivity(intent);
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(),"Error en Main: No se puede abrir este módulo", Toast.LENGTH_SHORT).show();
        }
    }
    public void irEstablecerIntervaloAlertas (View vista){
        try {

            Intent intent = new Intent(this, ActivityEstablecerIntervaloAlertas.class);
            startActivity(intent);
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(),"Error en Main: No se puede abrir este módulo", Toast.LENGTH_SHORT).show();
        }
    }

    public void irSalir (View view){
        onPause();
        Toast.makeText(getApplicationContext(),"Aplicación Pausada", Toast.LENGTH_SHORT).show();
        onStop();
    }
}
