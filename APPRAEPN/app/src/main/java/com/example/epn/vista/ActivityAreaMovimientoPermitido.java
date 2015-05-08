package com.example.epn.vista;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.epn.MainActivity;
import com.example.epn.appraepn.R;
import com.example.epn.modelo.entidades.AreaDesplazamiento;
import com.example.epn.modelo.entidades.Paciente;
import com.example.epn.modelo.servicios.ServiciosAreaDesplazamiento;

/**
 * Created by Gabriel on 02/04/2015.
 */
public class ActivityAreaMovimientoPermitido extends Activity {

    ServiciosAreaDesplazamiento serviciosAreaDesplazamiento;
    AreaDesplazamiento areaDesplazamiento;
    EditText txtRadio;
    //Bundle extras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_movimiento_permitido);
        setTitle("Area de Movimiento Permitido");

        txtRadio = (EditText) findViewById(R.id.txtRadio);
        areaDesplazamiento = new AreaDesplazamiento();
        serviciosAreaDesplazamiento = new ServiciosAreaDesplazamiento(this);

        /*extras = this.getIntent().getExtras();
        cargarArea (extras.getInt("idArea"));*/
    }

    /*public void cargarArea(int idArea){
        System.out.println("ID AREA ES: "+idArea);
        idArea=1;
        serviciosAreaDesplazamiento.abrirConexion();
        try {

            Cursor cursor = serviciosAreaDesplazamiento.recuperarElemento(idArea);
            if(cursor != null){
                areaDesplazamiento.setIdArea(cursor.getInt(0));
                areaDesplazamiento.setRadio(cursor.getInt(1));

                txtRadio.setText(areaDesplazamiento.getIdArea());
            }
            else{
                Toast.makeText(this, "No se encontró radio definido", Toast.LENGTH_LONG).show();
            }
            serviciosAreaDesplazamiento.cerrarConexion();
        }
        catch (Exception e){
            e.printStackTrace();
            serviciosAreaDesplazamiento.cerrarConexion();
        }

    }*/

    public void irGuardarRadio(View view) {

        try{
            int actualizaGuarda = serviciosAreaDesplazamiento.conteoRegistros();
            AreaDesplazamiento areaDesplazamiento = new AreaDesplazamiento();

            if (actualizaGuarda>0){//actualizar
                try{
                    serviciosAreaDesplazamiento.abrirConexion();
                    areaDesplazamiento.setRadio(Integer.parseInt(txtRadio.getText().toString()));
                    serviciosAreaDesplazamiento.actualizar(areaDesplazamiento);

                    Toast.makeText(getApplicationContext(), "Actualizacion correcta", Toast.LENGTH_SHORT).show();
                    serviciosAreaDesplazamiento.cerrarConexion();
                    startActivity(new Intent(this, ActivityAreaMovimientoPermitido.class));
                }
                catch(Exception e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Actualizacion incorrecta", Toast.LENGTH_SHORT).show();
                }

            }

            else {//insertar

                int verificador = validarDatosInterfaz();

                if (verificador == 1) {

                    try {

                        tomarDatosInterfazArea(areaDesplazamiento);

                        serviciosAreaDesplazamiento.abrirConexion();
                        serviciosAreaDesplazamiento.insertar(areaDesplazamiento);
                        Toast.makeText(getApplicationContext(), "Radio ingresado correctamente", Toast.LENGTH_SHORT).show();
                        serviciosAreaDesplazamiento.cerrarConexion();

                        Intent intent = new Intent(this, ActivityAreaMovimientoPermitido.class);
                        startActivity(intent);

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "No se puede guardar radio", Toast.LENGTH_SHORT).show();
                    }
                }

                else {
                    Toast.makeText(getApplicationContext(), "Ingresar Radio mayor a cero", Toast.LENGTH_SHORT).show();
                }
            }


        }
        catch(Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "no se realizo el conteo de registros", Toast.LENGTH_SHORT).show();
        }
    }

    public int validarDatosInterfaz() {

        if (txtRadio.getText().toString().equals("") || Integer.parseInt(txtRadio.getText().toString()) <= 0) {
            return 0;
        } else {
            return 1;
        }
    }

    private void tomarDatosInterfazArea(AreaDesplazamiento areaDesplazamiento) {
        areaDesplazamiento.setRadio(Integer.parseInt(txtRadio.getText().toString()));
    }

    public void irCancelar(View view) {
        onPause();
        //Toast.makeText(getApplicationContext(), "Menú Principal", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
