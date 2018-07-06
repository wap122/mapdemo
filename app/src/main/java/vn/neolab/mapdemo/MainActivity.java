package vn.neolab.mapdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleMap.OnPolylineClickListener, GoogleMap.OnPolygonClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert fragment != null;
        fragment.getMapAsync(this);
    }

    LatLng[] beginLine = {new LatLng(-27.457, 150.040),
            new LatLng(-30.852, 153.211)};

    LatLng originalLat = new LatLng(-27.457, 150.040);

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng[] coordinates = {new LatLng(-27.457, 153.040),
                new LatLng(-33.852, 151.211),
                new LatLng(-37.813, 144.962),
                new LatLng(-34.928, 138.599)};

        PolygonOptions poly = new PolygonOptions().clickable(true);
        for (int i = 0, length = coordinates.length; i < length; i++) {
            LatLng lat = coordinates[i];
            MarkerOptions mark = new MarkerOptions().position(lat)
                    .title(String.valueOf(i + 1));
            googleMap.addMarker(mark);
            poly.add(lat);
        }
        Polygon area = googleMap.addPolygon(poly);
        stylePolygon(area);
        googleMap.addPolyline(new PolylineOptions().add(beginLine));

        area.setTag("alpha");

        drawParalelLine(3);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-23.684, 133.903), 4));


        // Set listeners for click events.
        googleMap.setOnPolylineClickListener(this);
        googleMap.setOnPolygonClickListener(this);


    }

    private void drawParalelLine(int distance) {

    }

    private void drawFirstLine(LatLng original) {

    }

    private static final int COLOR_GREEN_ARGB = 0xff388E3C;
    private static final int COLOR_PURPLE_ARGB = 0xff81C784;

    private static final int POLYGON_STROKE_WIDTH_PX = 8;

    private void stylePolygon(Polygon polygon) {
        polygon.setStrokeWidth(POLYGON_STROKE_WIDTH_PX);
        polygon.setStrokeColor(COLOR_GREEN_ARGB);
        polygon.setFillColor(COLOR_PURPLE_ARGB);
    }


    @Override
    public void onPolygonClick(Polygon polygon) {

    }

    @Override
    public void onPolylineClick(Polyline polyline) {

    }
}
