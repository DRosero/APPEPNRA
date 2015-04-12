package com.example.epn.vista;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.epn.appraepn.R;
import com.example.epn.modelo.entidades.Responsable;
import com.example.epn.modelo.servicios.ServiciosResponsable;

import java.util.List;

/**
 * Created by Gabriel on 03/04/2015.
 */
public class ActivityRegistrarResponsable extends Activity {

    ServiciosResponsable serviciosResponsable;

    static final int PICK_CONTACT_REQUEST = 1;  // The request code
    private Button btnSeleccionar;
    private Button btnGuardar;

    private TextView txtId;
    private EditText txtNombre;
    private EditText txtNumeroMovil;

    private EditText txtNumeroFijo;
    private EditText txtDireccionHogar;
    private EditText getTxtDireccionTrabajo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_responsable);
        serviciosResponsable=new ServiciosResponsable(this);


        txtNombre = (EditText) findViewById(R.id.txtNombreResponsable);
        txtNumeroMovil = (EditText) findViewById(R.id.txtTelefonoMovil);
        txtId= (TextView) findViewById(R.id.txtIdResponsable);
        txtNumeroFijo = (EditText) findViewById(R.id.txtTelefonoFijo);
        txtDireccionHogar=(EditText) findViewById(R.id.txtDireccionHogarResponsable);
        getTxtDireccionTrabajo=(EditText) findViewById(R.id.txtDireccionTrabajoResponsable);

        btnSeleccionar =(Button)findViewById(R.id.btnSeleccionarContacto);
        btnGuardar =(Button)findViewById(R.id.btnGuardarResponsable);

        btnSeleccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickContactIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);//Uri.parse("content://contacts"));
                pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE); // Indica los contactos unicamente que tienen numero telefonico
                startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (requestCode == PICK_CONTACT_REQUEST) {
            if (resultCode == RESULT_OK) {

                //Capturar el valor de la Uri
                Uri contactUri = intent.getData();
                //Procesar la Uri
                renderContact(contactUri);
            }
        }
    }

    private void renderContact(Uri uri) {

        txtNombre.setText(tomarNombre(uri));
        txtNumeroMovil.setText(tomarNumero(uri));
        txtId.setText(tomarId(uri));

    }

    private String tomarNombre(Uri uri) {

        //Valor a retornar
        String name = null;

        //Obtener una instancia del Content Resolver
        ContentResolver contentResolver = getContentResolver();

        //Cursor para recorrer los datos de la consulta
        String[] projection = {ContactsContract.Contacts.DISPLAY_NAME};
        Cursor c = getContentResolver().query(uri, projection, null, null, null);

        //Consultando el primer y único resultado elegido
        if(c.moveToFirst()){
            name = c.getString(0);
        }

        //Cerramos el cursor
        c.close();

        return name;
    }

    private String tomarNumero(Uri uri) {

        String number = null;
        //Obtener una instancia del Content Resolver
        ContentResolver contentResolver = getContentResolver();

        //Cursor para recorrer los datos de la consulta
        String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);

        //Consultando el primer y único resultado elegido
        if(cursor.moveToFirst()){
            //Recuperar el número de teléfono de la columna NÚMERO
            int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            number = cursor.getString(column);
        }
        return number;
    }

    private String tomarId(Uri uri){
        /*
        Variables temporales para el id
         */
        String id = null;

        /*
        Obtener el _ID del contacto
         */
        Cursor contactCursor = getContentResolver().query(uri, new String[]{ContactsContract.Contacts._ID},null, null, null);

        if (contactCursor.moveToFirst()) {
            id = contactCursor.getString(0);
        }
        contactCursor.close();
        return id;
    }

    private String getPhone(Uri uri) {// para tomar solo el numero que sea de tipo mobile.. TIENE ERRORES....
        /*
        Variables temporales para el id y el teléfono
         */
        String id = null;
        String phone = null;

        /************* PRIMERA CONSULTA ************/
        /*
        Obtener el _ID del contacto
         */
        Cursor contactCursor = getContentResolver().query(uri, new String[]{ContactsContract.Contacts._ID},null, null, null);

        if (contactCursor.moveToFirst()) {
            id = contactCursor.getString(0);
        }
        contactCursor.close();

        /************* SEGUNDA CONSULTA ************/
        /*
        Sentencia WHERE para especificar que solo deseamos
        números de telefonía móvil
         */
        String selectionArgs =
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? AND " +
                        ContactsContract.CommonDataKinds.Phone.TYPE+"= " +
                        ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE;

        /*
        Obtener el número telefónico
         */
        Cursor phoneCursor = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[] { ContactsContract.CommonDataKinds.Phone.NUMBER },
                selectionArgs
                ,
                new String[] { id },
                null
        );
        if (phoneCursor.moveToFirst()) {
            phone = phoneCursor.getString(0);
        }
        phoneCursor.close();

        return phone;
    }

    public void irCancelar (View view){
        onPause();
        Toast.makeText(getApplicationContext(), "Listando Responsables", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ActivityAdministrarResponsable.class);
        startActivity(intent );
    }

    public void irGuardar(View view){
        Responsable responsable=new Responsable();
        int verificador= irValidar();

        if (verificador==1){
        try{
            responsable.setNombre(txtNombre.getText().toString());
            responsable.setTelefonoMovil(txtNumeroMovil.getText().toString());
            responsable.setTelefonoFijo(txtNumeroFijo.getText().toString());
            responsable.setDireccionHogar(txtDireccionHogar.getText().toString());
            responsable.setDireccionTrabajo(getTxtDireccionTrabajo.getText().toString());
            if (existePrioritario()==true) {
                responsable.setPrioridadResponsable(0);
            } else {
                responsable.setPrioridadResponsable(1);
            }

            serviciosResponsable.abrirConexion();
            serviciosResponsable.insertar(responsable);
            Toast.makeText(getApplicationContext(), "Guardando Contacto", Toast.LENGTH_SHORT).show();
            serviciosResponsable.cerrarConexion();

        }
        catch(Exception e){

        }
        }
        else{
            Toast.makeText(getApplicationContext(), "Nombre y Número Móvil son obligatorios", Toast.LENGTH_SHORT).show();

        }

    }
    public int irValidar(){

        if(txtNombre.getText().toString().equals("") || txtNumeroMovil.getText().toString().equals("")){
            return 0;
        }
        else{
            Toast.makeText(getApplicationContext(), "Campos Correctos", Toast.LENGTH_SHORT).show();
            System.out.println("Validar que numero telefonico sea el celular");
            return 1;
        }
    }

    private boolean existePrioritario() {
        serviciosResponsable.abrirConexion();

        List<Responsable> responsables = serviciosResponsable.listarResponsbale();
        if (responsables.size()> 0) {
            for (Responsable responsable : responsables) {
                if (responsable.getPrioridadResponsable()==1) {
                    serviciosResponsable.cerrarConexion();
                    return true;
                }
            }
        }
        serviciosResponsable.cerrarConexion();
        return false;

    }
}
