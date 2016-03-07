package edu.wou.shaas.wouami;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
//import com.google.android.gms.maps.model.TileOverlay;
//import com.google.android.gms.maps.model.TileOverlayOptions;
//import com.google.android.gms.maps.model.TileProvider;
//import com.google.android.gms.maps.model.UrlTileProvider;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

//public class CampusMapActivity extends FragmentActivity implements OnMapReadyCallback {
public class CampusMapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnGroundOverlayClickListener {

    private GoogleMap mMap;
    //private TileOverlay resHallHertTile;
    private GroundOverlay hertGroundOverlay;
    private GroundOverlay hertGroundOverlayRotated;
    private static final LatLng HERITAGE_HALL = new LatLng(44.854315, -123.23854);
    private static final LatLng M_HERITAGE_HALL = new LatLng(44.854664, -123.238240);
    private static final LatLng NEAR_HERITAGE_HALL = new LatLng(HERITAGE_HALL.latitude - 0.001, HERITAGE_HALL.longitude - 0.025);
    private final List<BitmapDescriptor> mImages = new ArrayList<BitmapDescriptor>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

//        LatLng monmouth = new LatLng(44.8528, -123.2394);
//        mMap.addMarker(new MarkerOptions().position(monmouth).title("Marker in Monmouth"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(monmouth , 14.0f));
        mMap.addMarker(new MarkerOptions().position(M_HERITAGE_HALL).title("Heritage Hall"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(M_HERITAGE_HALL , (float) 21.0));
        //Residence Hall Floor Plans...Heritage Hall 1st
    //GROUNDOVERLAY ATTEMPT
        // Register a listener to respond to clicks on GroundOverlays.
        mMap.setOnGroundOverlayClickListener(this);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(HERITAGE_HALL, 11));

        mImages.clear();
        mImages.add(BitmapDescriptorFactory.fromResource(R.drawable.nobkg_heritage_1));
       // mImages.add(BitmapDescriptorFactory.fromResource(R.drawable.);

        // Add a small, rotated overlay that is clickable by default
        // (set by the initial state of the checkbox.)
//        hertGroundOverlayRotated = mMap.addGroundOverlay(new GroundOverlayOptions()
//                .image(mImages.get(1)).anchor(0, 1)
//                .position(NEAR_HERITAGE_HALL, 4300f, 3025f);
                //.bearing(30)
                //.clickable(((CheckBox) findViewById(R.id.toggleClickability)).isChecked()));

        // Add a large overlay at Newark on top of the smaller overlay.
        hertGroundOverlay = mMap.addGroundOverlay(new GroundOverlayOptions()
                .image(mImages.get(0)).anchor(0, 1)
                .position(HERITAGE_HALL, 58f, 75f));

        //mTransparencyBar.setOnSeekBarChangeListener(this);

        // Override the default content description on the view, for accessibility mode.
        // Ideally this string would be localised.
        mMap.setContentDescription("Google Map with ground overlay.");


        //TileOverlay attempt
//        TileProvider tileProvider = new UrlTileProvider(256, 256) {
//            @Override
//            public URL getTileUrl(int x, int y, int zoom) {
//
//    /* Define the URL pattern for the tile images */
//                String s = String.format("file:///C:/Users/shaas/Documents/CS/425/Android_Proj/Floor%20Plans/Residence%20Halls/Heritage%20Hall/Heritage_1.png",//"http://my.image.server/images/%d/%d/%d.png",
//                        zoom, x, y);
//
//                if (!checkTileExists(x, y, zoom)) {
//                    return null;
//                }
//
//                try {
//                    return new URL(s);
//                } catch (MalformedURLException e) {
//                    throw new AssertionError(e);
//                }
//            }
//            /*
//        * Check that the tile server supports the requested x, y and zoom.
//        * Complete this stub according to the tile range you support.
//        * If you support a limited range of tiles at different zoom levels, then you
//        * need to define the supported x, y range at each zoom level.
//        */
//            private boolean checkTileExists(int x, int y, int zoom) {
//                int minZoom = 12;
//                int maxZoom = 16;
//
//                if ((zoom < minZoom || zoom > maxZoom)) {
//                    return false;
//                }
//
//                return true;
//            }
//        };
//
//        resHallHertTile = mMap.addTileOverlay(new TileOverlayOptions()
//                .tileProvider(tileProvider));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.campus_map) {
            return true;
        }
        else if (id == R.id.calendar){
            return true;
        }
        else if (id == R.id.res_search){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGroundOverlayClick(GroundOverlay groundOverlay) {
        // Toggle transparency value between 0.0f and 0.5f. Initial default value is 0.0f.
        hertGroundOverlay.setTransparency(0.5f - hertGroundOverlay.getTransparency());
    }
}
