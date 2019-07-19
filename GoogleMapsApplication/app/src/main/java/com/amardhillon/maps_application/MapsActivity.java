package com.amardhillon.maps_application;

import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.amardhillon.maps_application.R.id.editText;

/**
 * Created by Amarjit Singh Dhillon on 2017-05-27.
 */

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    // my API key name =  API key 1
    // AIzaSyCa7_p2TdLspIbLk2EM7kL-d40jy8sU9OE

    public GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Infosys and move the camera
       // mMap.setMyLocationEnabled(true);
        LatLng infy = new LatLng(12.842562, 77.658531);
        mMap.addMarker(new MarkerOptions().position(infy).title("Welcome to Infosys"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(infy));
        mMap.setTrafficEnabled(true);
        mMap.animateCamera(CameraUpdateFactory.zoomTo(5));
    } //onMapReady

    public void onSearch(View view) throws IOException {
        //getting the address text
        EditText address_text = (EditText) findViewById(R.id.editText);

        // convert the text to string
         String location = address_text.getText().toString();

       // create a addresslist -> it will be used for geocoder
         List<Address> addresslist = null;

       Geocoder geocoder = new Geocoder(this);
       addresslist = geocoder.getFromLocationName(location,1);

        Address address = addresslist.get(0);
        LatLng latLng1 = new LatLng(address.getLatitude(),address.getLongitude());
        mMap.addMarker(new MarkerOptions().position(latLng1).title(location));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng1));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(10));
    } //onsearch

    public class DirectionFinder {

        private static  final String  DIRECTION_URL_API = "https://maps.googleapis.com/maps/api/directions/json?";
        private static final  String google_api_key = "AIzaSyCa7_p2TdLspIbLk2EM7kL-d40jy8sU9OE";

        private String origin = "ottawa";
        private String destinaiton = "montreal";


        private String downloadUrl() throws UnsupportedEncodingException {
            String url_origin = URLEncoder.encode(origin, "utf-8");
            String url_destination = URLEncoder.encode(destinaiton, "utf-8");
            String complete_URL = null;
            complete_URL = DIRECTION_URL_API + "origin=" + url_origin + "destination=" + url_destination + "&key=" + google_api_key ;
            return complete_URL;
        }//createurl



        // Fetches data from url passed
        private class DownloadTask extends AsyncTask<String, Void, String> {

            // Downloading data in non-ui thread
            @Override
            protected String doInBackground(String... url) {

                // For storing data from web service
                String data = "";

                try{
                    // Fetching the data from web service
                    data = downloadUrl();
                }catch(Exception e){
                    Log.d("Background Task",e.toString());
                }
                return data;
            }

            // Executes in UI thread, after the execution of doInBackground()
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                ParserTask parserTask = new ParserTask();

                // Invokes the thread for parsing the JSON data
                parserTask.execute(result);
            }
        }

        /** A class to parse the Google Places in JSON format */
        private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> >{

            // Parsing the data in non-ui thread
            @Override
            protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

                JSONObject jObject;
                List<List<HashMap<String, String>>> routes = null;

                try{
                    jObject = new JSONObject(jsonData[0]);
                    DirectionsJSONParser parser = new DirectionsJSONParser();

                    // Starts parsing data
                    routes = parser.parse(jObject);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                return routes;
            }

            // Executes in UI thread, after the parsing process
            @Override
            protected void onPostExecute(List<List<HashMap<String, String>>> result) {
                ArrayList<LatLng> points = null;
                PolylineOptions lineOptions = null;
                MarkerOptions markerOptions = new MarkerOptions();
                String distance = "";
                String duration = "";


                // Traversing through all the routes
                for(int i=0;i<result.size();i++){
                    points = new ArrayList<LatLng>();
                    lineOptions = new PolylineOptions();

                    // Fetching i-th route
                    List<HashMap<String, String>> path = result.get(i);

                    // Fetching all the points in i-th route
                    for(int j=0;j<path.size();j++){
                        HashMap<String,String> point = path.get(j);

                        if(j==0){	// Get distance from the list
                            distance = (String)point.get("distance");
                            continue;
                        }else if(j==1){ // Get duration from the list
                            duration = (String)point.get("duration");
                            continue;
                        }

                        double lat = Double.parseDouble(point.get("lat"));
                        double lng = Double.parseDouble(point.get("lng"));
                        LatLng position = new LatLng(lat, lng);

                        points.add(position);
                    }

                    // Adding all the points in the route to LineOptions
                    lineOptions.addAll(points);
                    lineOptions.width(2);
                    lineOptions.color(Color.RED);
                }

                // tvDistanceDuration.setText("Distance:"+distance + ", Duration:"+duration);
                GoogleMap map = null;

                // Drawing polyline in the Google Map for the i-th route
                map.addPolyline(lineOptions);
            }
        }
    } // DirectionFinder class
}
