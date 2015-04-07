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
import com.example.epn.basehelper.BaseHelper;
import com.example.epn.modelo.entidades.Paciente;
import com.example.epn.modelo.servicios.ServiciosPaciente;
import java.util.List;

/**
 * Created by Diego on 01/04/2015.
 */
public class ActivityAdministrarPaciente extends Activity {
    Bundle extras;
    private ServiciosPaciente serviciosPaciente;
    private ListView lstpaciente;
    private Paciente pacienteRecuperado;


    public void irNuevoPaciente(View vista){
        try {
            serviciosPaciente.abrirBD();
            List<Paciente>paciente=serviciosPaciente.recuperarTodos();
            serviciosPaciente.cerrarBD();

            if(paciente.isEmpty()){

                Intent intent = new Intent(this, ActivityRegistrarPaciente.class);
                startActivity(intent);
            }
            else {
                serviciosPaciente.cerrarBD();
                Toast.makeText(getApplicationContext(),"El paciente ya a sido ingresado",Toast.LENGTH_SHORT).show();
            }
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(),"No se puede abrir ventana Registrar Paciente", Toast.LENGTH_SHORT).show();
        }
    }

    public void irRegresar (View view){
        onPause();
        Toast.makeText(getApplicationContext(),"Menú Principal", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrar_paciente);
        lstpaciente = (ListView) findViewById(R.id.lstPaciente);

        extras = new Bundle();
        serviciosPaciente = new ServiciosPaciente(this);
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

    public void llenarListView() {
        serviciosPaciente.abrirBD();
        List<Paciente> paciente = serviciosPaciente.recuperarTodos();
        ArrayAdapter<Paciente> adapter = new ArrayAdapter<Paciente>(this, android.R.layout.simple_list_item_1, paciente);
        lstpaciente.setAdapter(adapter);
        serviciosPaciente.cerrarBD();
    }

  @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_paciente, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        int valorItem=item.getItemId();
        System.out.println("valor Item"+valorItem);
        if(valorItem==2131296361){
            Intent intent=new Intent(this,ActivityActualizarPaciente.class).putExtras(extras);
            startActivity(intent);
            return true;
        }
        else if(valorItem==2131296362){
            eliminarPaciente(pacienteRecuperado);
            return true;
        }
        else {
            return false;
        }


        /*switch (item.getItemId()){
            case R.id.actualizar:
                Intent intent=new Intent(this,ActivityActualizarPaciente.class).putExtras(extras);
                startActivity(intent);
                valor=true;

            case R.id.eliminar:
                eliminarPaciente(pacienteRecuperado);
                valor=true;
        }*/
    }

    public void eliminarPaciente(final Paciente paciente){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Borrar Paciente");
        builder.setMessage("¿Está Seguro que desea borrarlo?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                serviciosPaciente.abrirBD();
                serviciosPaciente.eliminar(paciente);
                Toast.makeText(getApplicationContext(),"Registro Eliminado", Toast.LENGTH_SHORT).show();
                serviciosPaciente.cerrarBD();
                llenarListView();
            }
        });

        builder.setNegativeButton("Cancelar",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


        builder.show();
    }


    


}
