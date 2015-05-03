package com.example.epn.vista;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import com.example.epn.appraepn.R;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.SimpleLocationOverlay;


/**
 * Created by Gabriel on 02/04/2015.
 */
public class ActivityEstablecerSitioCentral extends Activity{

    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establecer_sitio_central);

        mapView = (MapView) findViewById(R.id.mapView);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setMultiTouchControls(true);
        MapController mapController = (MapController) mapView.getController();
        mapController.setZoom(12);

        GeoPoint myLocation = new GeoPoint(getMyLocation());

        SimpleLocationOverlay myLocationOverlay = new SimpleLocationOverlay(this);
        mapView.getOverlays().add(myLocationOverlay);

        mapController.setCenter(myLocation);
        myLocationOverlay.setLocation(myLocation);

        añadirSitio();
    }

    Location getMyLocation(){
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    }

    public void añadirSitio(){
        final Location location = null;
        mapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OverlayItem olItem = new OverlayItem("Here", "SampleDescription",new GeoPoint(location.getLatitude(),location.getLongitude()));
                Drawable newMarker=getResources().getDrawable(R.drawable.ic_menu_mylocation);
                olItem.setMarker(newMarker);
            }
        });
    }



}
