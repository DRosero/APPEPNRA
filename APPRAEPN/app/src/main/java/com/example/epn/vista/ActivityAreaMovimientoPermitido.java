package com.example.epn.vista;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.epn.appraepn.R;

/**
 * Created by Gabriel on 02/04/2015.
 */
public class ActivityAreaMovimientoPermitido extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_movimiento_permitido);

    }

    public void irGuardarRadio(View view){

        Toast.makeText(getApplicationContext(), "Actualmente Implementando Guardar", Toast.LENGTH_SHORT).show();

    }
}
