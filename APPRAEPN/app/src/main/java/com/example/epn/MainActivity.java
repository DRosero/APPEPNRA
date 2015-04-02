package com.example.epn;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.epn.appraepn.R;
import com.example.epn.vista.ActivityRegistrarPaciente;
import com.example.epn.vista.ActivityVisualizarPaciente;


public class MainActivity extends ActionBarActivity {

    private Button btnRegistrarDatosPaciente;
    private Button btnRegistrarDatosResponsable;
    private Button btnRegistrarSitios;
    private Button btnDesplegarMapaSitios;
    private Button btnEstablecerPrioridades;

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


    public void irVisualizarPaciente (View vista){
        Intent intent = new Intent(this, ActivityVisualizarPaciente.class);
        startActivity(intent);
    }
/*
    public void irRegistrarDatosResponsable (View vista){
        Intent intent = new Intent(this, RegistrarDatosResponsables.class);
        startActivity(intent);
    }

    public void irRegistrarSitios (View vista){
        Intent intent=new Intent(this,RegistrarSitios.class);
        startActivity(intent);
    }

    public void irDesplegarMapaSitios (View vista){
        Intent intent = new Intent(this, DesplegarSitios.class);
        startActivity(intent);
    }

    public void irEstablecerPrioridades (View vista){
        Intent intent = new Intent(this, EstablecerPrioridades.class);
        startActivity(intent);
    }
*/
    public void irSalir (){
        System.exit(0);
    }
}
