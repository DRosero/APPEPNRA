package com.example.epn.vista;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_responsable, menu);
    }

    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrar_responsable);
        lstResponsable=(ListView)findViewById(R.id.lstResponsable);
        extras = new Bundle();
        serviciosResponsable=new ServiciosResponsable(this);

        registerForContextMenu(lstResponsable);
        lstResponsable.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                responsable=(Responsable)parent.getItemAtPosition(position);
                extras.putInt("idResponsable",responsable.getIdresponsable());
                return false;
            }
        });
        llenarListView();
    }

    public void llenarListView() {
        serviciosResponsable.abrirConexion();

        List<Responsable> responsable = serviciosResponsable.listarResponsbale();
        ArrayAdapter<Responsable> adapter = new ArrayAdapter<Responsable>(this, android.R.layout.simple_list_item_1, responsable);
        lstResponsable.setAdapter(adapter);
        serviciosResponsable.cerrarConexion();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actualizar:
                /*startActivity(new Intent(ContactosActivity.this,
                        ActualizarActivity.class).putExtras(extras));
                return true;*/
            case R.id.eliminar:
                eliminarResponsable(responsable);
                return true;
            case R.id.prioridad:
                darPrioridad(responsable);
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void darPrioridad(Responsable responsableRecuperado) {
        serviciosResponsable.abrirConexion();
        List<Responsable> responsableslista =serviciosResponsable.listarResponsbale();

        for(Responsable responsable: responsableslista){
            responsable.setPrioridadResponsable(0);
            serviciosResponsable.actualizar(responsable);
        }
        responsableRecuperado.setPrioridadResponsable(1);
        serviciosResponsable.actualizar(responsableRecuperado);
        serviciosResponsable.cerrarConexion();

        llenarListView();
        Toast.makeText(getApplicationContext(), "Prioridad actualizada",
                Toast.LENGTH_SHORT).show();
    }

    public void eliminarResponsable(final Responsable responsable) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Borrar Responsable");
        builder.setMessage("Seguro que desea borrarlo?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    serviciosResponsable.abrirConexion();

                    if (responsable.getPrioridadResponsable() == 1) {
                        Toast.makeText(getApplicationContext(),
                                "No puede eliminar el contacto con prioridad de llamada",
                                Toast.LENGTH_LONG).show();
                    } else {
                        serviciosResponsable.eliminar(responsable);
                        Toast.makeText(getApplicationContext(),
                                "Contacto eliminado", Toast.LENGTH_SHORT)
                                .show();
                    }
                    serviciosResponsable.cerrarConexion();

                    llenarListView();
                }

                catch (Exception exception) {
                    Toast.makeText(getApplicationContext(),"Error al eliminar esta en el catch",Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.show();
    }


}
