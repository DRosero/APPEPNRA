package com.example.epn.vista;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.epn.MainActivity;
import com.example.epn.appraepn.BuildConfig;
import com.example.epn.appraepn.R;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.util.constants.UtilConstants;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.MyLocationOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.SimpleLocationOverlay;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.io.File;

/**
 * Created by Gabriel on 02/04/2015.
 */

public class ActivityEstablecerSitioCentral extends Activity{

    private MapView mapView;
    private String name="";
    private Button btnfoto;
    private LocationManager locationManager;
    MapController mapController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establecer_sitio_central);

        btnfoto=(Button)findViewById(R.id.btnDefinirImagenSitio);

        mapView = (MapView) findViewById(R.id.mapView);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setMultiTouchControls(true);
        mapController = (MapController) mapView.getController();
        mapController.setZoom(12);


        SimpleLocationOverlay myLocationOverlay = new SimpleLocationOverlay(this);
        mapView.getOverlays().add(myLocationOverlay);

        /*GeoPoint myLocation = new GeoPoint(getMyLocation());
        mapController.setCenter(myLocation);
        myLocationOverlay.setLocation(myLocation);*/

        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        //for demo, getLastKnownLocation from GPS only, not from NETWORK
        Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(lastLocation != null){
            updateLoc(lastLocation);
        }



        //mapController.setCenter(myLocation);
        /*GeoPoint geoPoint=new GeoPoint(40.396764,-3.713379);
        */

        name = Environment.getExternalStorageDirectory() + "/test.jpg";

        btnfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityEstablecerSitioCentral.this,ActivityEstablecerImagen.class));
            }
        });

        //añadirSitio();
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

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /**
         * A partir del nombre del archivo ya definido lo buscamos y creamos el bitmap
         * para el ImageView
         */

        /**
         * Para guardar la imagen en la galería, utilizamos una conexión a un MediaScanner
         */
        new MediaScannerConnection.MediaScannerConnectionClient() {
            private MediaScannerConnection msc = null; {
                msc = new MediaScannerConnection(getApplicationContext(), this); msc.connect();
            }
            public void onMediaScannerConnected() {
                msc.scanFile(name, null);
            }
            public void onScanCompleted(String path, Uri uri) {
                msc.disconnect();
            }
        };
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, myLocationListener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, myLocationListener);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        locationManager.removeUpdates(myLocationListener);
    }

    private void updateLoc(Location loc){
        GeoPoint locGeoPoint = new GeoPoint(loc.getLatitude(), loc.getLongitude());
        mapController.setCenter(locGeoPoint);
        mapController.setZoom(20);
        mapView.invalidate();
    }

    private LocationListener myLocationListener
            = new LocationListener(){

        @Override
        public void onLocationChanged(Location location) {
            // TODO Auto-generated method stub
            updateLoc(location);
        }

        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub

        }

    };
}
