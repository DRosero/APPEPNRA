package com.example.epn.vista;

import android.app.Activity;
import android.os.Bundle;
import com.example.epn.appraepn.R;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;

/**
 * Created by Gabriel on 02/04/2015.
 */
public class ActivityEstablecerSitioCentral extends Activity {

    private MapView myOpenMapView;
    private MapController myMapController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establecer_sitio_central);

        myOpenMapView = (MapView)findViewById(R.id.openmapview);
        myOpenMapView.setBuiltInZoomControls(true);
        myMapController = (MapController) myOpenMapView.getController();
        myMapController.setZoom(4);

    }
}
