package com.example.epn.vista;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.epn.MainActivity;
import com.example.epn.appraepn.R;

/**
 * Created by Gabriel on 02/04/2015.
 */
public class ActivityEstablecerPrioridades extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establecer_prioridades_responsable);

    }


    public void irRegresar (View view){
        onPause();
        Toast.makeText(getApplicationContext(),"Menú Principal", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent );
    }
}
