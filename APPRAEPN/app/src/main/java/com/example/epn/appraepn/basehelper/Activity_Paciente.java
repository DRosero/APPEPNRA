package com.example.epn.appraepn.basehelper;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.example.epn.appraepn.R;

/**
 * Created by Diego on 31/03/2015.
 */
public class Activity_Paciente extends Activity {


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
}
