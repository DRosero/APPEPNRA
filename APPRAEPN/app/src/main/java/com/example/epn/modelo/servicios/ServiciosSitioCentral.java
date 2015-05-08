package com.example.epn.modelo.servicios;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.epn.basehelper.BaseHelper;
import com.example.epn.modelo.entidades.SitioCentral;

/**
 * Created by Diego on 29/04/2015.
 */
public class ServiciosSitioCentral {

    private BaseHelper baseHelper;
    private SQLiteDatabase sqLiteDatabase; //= this.getWritableDatabase;
    private String columnas[] = {"idSitioCentral","nombreSitio", "descripcionSitio","latitud","longitud", "foto"};

    public void añadirSitio(SitioCentral sitioCentral) {

        ContentValues values = new ContentValues();
        values.put("nombreSitio",sitioCentral.getNombresitio());
        System.out.println("llegue aqui");
        values.put("descripcionSitio",sitioCentral.getDescripcionSitio());
        values.put("latitud",sitioCentral.getLatitud());
        values.put("longitud",sitioCentral.getLongitud());
        values.put("foto",sitioCentral.getImagen());

        sqLiteDatabase.insert("SITIOCENTRAL", null, values);

    }

}
