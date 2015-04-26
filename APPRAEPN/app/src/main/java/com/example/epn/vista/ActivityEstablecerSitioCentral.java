package com.example.epn.vista;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.epn.appraepn.R;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.SimpleLocationOverlay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Gabriel on 02/04/2015.
 */
public class ActivityEstablecerSitioCentral extends Activity {

    /** Called when the activity is first created. */
    private MapController mapController;
    private MapView mapView;

    LinearLayout searchPanel;
    Button searchButton;
    EditText searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establecer_sitio_central);

        mapView = (MapView) findViewById(R.id.mapView);

        searchPanel = (LinearLayout) findViewById(R.id.searchPanel);
        searchButton = (Button) findViewById(R.id.searchButton);
        searchText = (EditText) findViewById(R.id.searchText);

        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setBuiltInZoomControls(true);
        mapController =(MapController) mapView.getController();
        mapController.setZoom(15);

        /*GeoPoint point2 = new GeoPoint(-0.18659558628491132,-78.4305382);
        mapController.setCenter(point2);*/

        GeoPoint myLocation = new GeoPoint(getMyLocation());

        SimpleLocationOverlay myLocationOverlay = new SimpleLocationOverlay(this);
        mapView.getOverlays().add(myLocationOverlay);

        mapController.setCenter(myLocation);
        myLocationOverlay.setLocation(myLocation);

        /*ItemizedIconOverlay.OnItemGestureListener<OverlayItem> myOnItemGestureListener = new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {

            @Override
            public boolean onItemLongPress(int arg0, OverlayItem arg1) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem item) {

                return true;
            }

        };

        ArrayList<OverlayItem> anotherOverlayItemArray;
        anotherOverlayItemArray = new ArrayList<OverlayItem>();

        anotherOverlayItemArray.add(new OverlayItem("0, 0", "0, 0", new GeoPoint(0, 0)));
        anotherOverlayItemArray.add(new OverlayItem("US", "US", new GeoPoint(38.883333, -77.016667)));
        anotherOverlayItemArray.add(new OverlayItem("China", "China", new GeoPoint(39.916667, 116.383333)));
        anotherOverlayItemArray.add(new OverlayItem("United Kingdom", "United Kingdom", new GeoPoint(51.5, -0.116667)));
        anotherOverlayItemArray.add(new OverlayItem("Germany", "Germany", new GeoPoint(52.516667, 13.383333)));
        anotherOverlayItemArray.add(new OverlayItem("Korea", "Korea", new GeoPoint(38.316667, 127.233333)));
        anotherOverlayItemArray.add(new OverlayItem("India", "India", new GeoPoint(28.613333, 77.208333)));
        anotherOverlayItemArray.add(new OverlayItem("Russia", "Russia", new GeoPoint(55.75, 37.616667)));
        anotherOverlayItemArray.add(new OverlayItem("France", "France", new GeoPoint(48.856667, 2.350833)));
        anotherOverlayItemArray.add(new OverlayItem("Canada", "Canada", new GeoPoint(45.4, -75.666667)));

        ItemizedOverlayWithFocus<OverlayItem> anotherItemizedIconOverlay = new ItemizedOverlayWithFocus<OverlayItem>(this, anotherOverlayItemArray, myOnItemGestureListener);
        mapView.getOverlays().add(anotherItemizedIconOverlay);*/

        /*searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String searchFor = searchText.getText().toString();
                JSONArray results = searchLocation(searchFor);

                if (results.length() > 0) {
                    try {
                        JSONObject firstResult = (JSONObject)results.get(0);
                        Double lat = firstResult.getDouble("lat");
                        Double lon = firstResult.getDouble("lon");

                        GeoPoint point = new GeoPoint((int) (lat * 1E6),
                                (int) (lon * 1E6));
                        mapController.setZoom(15);
                        mapController.setCenter(point);

                        //hideSearchPanel();

                        mapView.invalidate();

                    } catch (JSONException e) {
                        Log.e("OnClickListener", e.getMessage());
                    }
                } else {
                    Toast.makeText(view.getContext(),
                            "No results found",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }

    Location getMyLocation(){
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    }

    public void irBuscar(View view){
        String searchFor = searchText.getText().toString();
        JSONArray results = searchLocation(searchFor);

        if (results.length() > 0) {
            try {
                JSONObject firstResult = (JSONObject)results.get(0);
                Double lat = firstResult.getDouble("lat");
                Double lon = firstResult.getDouble("lon");

                GeoPoint point = new GeoPoint((int) (lat * 1E6),
                        (int) (lon * 1E6));
                mapController.setZoom(15);
                mapController.setCenter(point);

                hideSearchPanel();

                mapView.invalidate();

            } catch (JSONException e) {
                Log.e("OnClickListener", e.getMessage());
            }
        } else {
            Toast.makeText(getApplicationContext(),
                    "No results found",
                    Toast.LENGTH_SHORT).show();
        }

    }

    public JSONArray searchLocation(String query) {
        JSONArray results = new JSONArray();

        try {
            query = URLEncoder.encode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return results;
        }
        String url = "http://nominatim.openstreetmap.org/search?"+"q=" + query+"&format=json";

        HttpGet httpGet = new HttpGet(url);
        DefaultHttpClient httpClient = new DefaultHttpClient();

        try {
            HttpResponse response = httpClient.execute(httpGet);
            String content = EntityUtils.toString(response.getEntity(), "utf-8");
            results = new JSONArray(content);

        } catch (Exception e) {
            Log.e("searchLocation",
                    "Error executing url: " + url + "; " + e.getMessage());
        }

        return results;
    }

    public void hideSearchPanel() {
        InputMethodManager imm =
                (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(
                searchText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        searchPanel.setVisibility(View.INVISIBLE);
    }

}
