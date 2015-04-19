package com.example.epn.vista;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.epn.MainActivity;
import com.example.epn.appraepn.R;

/**
 * Created by Gabriel on 02/04/2015.
 */
public class ActivityAdministrarSitiosConocidos extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrar_sitios_conocidos);

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
