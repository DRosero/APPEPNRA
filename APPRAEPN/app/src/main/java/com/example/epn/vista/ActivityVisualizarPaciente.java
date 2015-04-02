package com.example.epn.vista;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.example.epn.appraepn.R;
import com.example.epn.basehelper.BaseHelper;
import com.example.epn.modelo.entidades.Paciente;
import com.example.epn.modelo.servicios.ServiciosPaciente;
import java.util.List;

/**
 * Created by Diego on 01/04/2015.
 */
public class ActivityVisualizarPaciente extends Activity {
    Bundle extras;
    private ServiciosPaciente serviciosPaciente;
    private ListView lstpaciente;
    private Paciente pacienteRecuperado;
    BaseHelper baseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_paciente);
        lstpaciente = (ListView) findViewById(R.id.Lstpaciente);

        extras = new Bundle();
        serviciosPaciente = new ServiciosPaciente();
        registerForContextMenu(lstpaciente);
        lstpaciente.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                pacienteRecuperado = (Paciente) parent.getItemAtPosition(position);
                extras.putInt("idPaciente", pacienteRecuperado.getIdpaciente());
                return false;
            }
        });

        llenarListView();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_paciente, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.actualizar:
                //falta implementar

            case R.id.eliminar:
                eliminarPaciente(pacienteRecuperado);
                return true;


            default:
                return super.onContextItemSelected(item);
        }
    }

    public void eliminarPaciente(final Paciente paciente){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Borrar Paciente");
        builder.setMessage("¿Está Seguro que desea borrarlo?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                baseHelper.getWritableDatabase();
                serviciosPaciente.eliminar(paciente);
                Toast.makeText(getApplicationContext(),"Registro Eliminado", Toast.LENGTH_SHORT).show();
                baseHelper.close();
                llenarListView();
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("","Cancelado");
            }
        });
        builder.show();
    }

    public void llenarListView(){
        baseHelper.getWritableDatabase();
        List<Paciente>paciente=serviciosPaciente.recuperarTodos();
        ArrayAdapter<Paciente>adapter=new ArrayAdapter<Paciente>(this,android.R.layout.simple_list_item_1,paciente);
        lstpaciente.setAdapter(adapter);
        baseHelper.close();
    }


}
