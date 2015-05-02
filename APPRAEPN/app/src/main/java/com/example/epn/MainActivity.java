package com.example.epn;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.epn.appraepn.R;
import com.example.epn.vista.ActivityAdministrarPaciente;
import com.example.epn.vista.ActivityAdministrarResponsable;
import com.example.epn.vista.ActivityAdministrarSitiosConocidos;
import com.example.epn.vista.ActivityAreaMovimientoPermitido;
import com.example.epn.vista.ActivityEstablecerIntervaloAlertas;
import com.example.epn.vista.ActivityEstablecerSitioCentral;

public class MainActivity extends ActionBarActivity {

    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerList = (ListView)findViewById(R.id.navList);mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    private void addDrawerItems() {
        final String[] osArray = { "Administrar Paciente", "Administrar Responsables", "Sitio Central", "Área de movimiento Permitido",
                "Administrar Sitios Conocidos","Intervalo Alertas","Salir"};
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(osArray[position]=="Administrar Paciente"){
                    Intent intent = new Intent(MainActivity.this, ActivityAdministrarPaciente.class);
                    startActivity(intent);
                }

                else  if(osArray[position]=="Administrar Responsables"){
                    Intent intent1 = new Intent(MainActivity.this,ActivityAdministrarResponsable.class);
                    startActivity(intent1);
                }

                else if(osArray[position]=="Sitio Central"){
                    Intent intent = new Intent(MainActivity.this,ActivityEstablecerSitioCentral.class);
                    startActivity(intent);
                }

                else if(osArray[position]=="Área de movimiento Permitido"){
                    Intent intent = new Intent(MainActivity.this, ActivityAreaMovimientoPermitido.class);
                    startActivity(intent);
                }

                else if(osArray[position]=="Administrar Sitios Conocidos"){
                    Intent intent = new Intent(MainActivity.this,ActivityAdministrarSitiosConocidos.class);
                    startActivity(intent);
                }

                else if(osArray[position]=="Intervalo Alertas"){
                    Intent intent = new Intent(MainActivity.this, ActivityEstablecerIntervaloAlertas.class);
                    startActivity(intent);
                }

                else if(osArray[position]=="Salir"){
                    onPause();
                    Toast.makeText(getApplicationContext(),"Aplicación Pausada", Toast.LENGTH_SHORT).show();
                    onStop();
                }
            }
        });
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
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

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*@Override
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
    }*/

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
