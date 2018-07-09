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

    GoogleMap mGoogleMap;

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

    LatLng[] coordinates = {new LatLng(-27.457, 153.040),
            new LatLng(-33.852, 151.211),
            new LatLng(-37.813, 144.962),
            new LatLng(-34.928, 138.599)};

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mGoogleMap = googleMap;

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


        drawFirstLine();

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-23.684, 133.903), 4));


        // Set listeners for click events.
        googleMap.setOnPolylineClickListener(this);
        googleMap.setOnPolygonClickListener(this);


    }

    private void drawParalelLine(int distance) {
        double decrease = (float) distance / 10;
        Point oriPoint = new Point(coordinates[0]);
        Line oriLine = new Line(new Point(beginLine[0]), new Point(beginLine[1]));
//        Line vuonggocLine = new Line(oriP, oriLine.getCp());

//        double a = 130;
//        drawALine(new Point(a, vuonggocLine.generateY(a)), oriP);


        Point runPoint = oriPoint;
        for (int i = 0; i < 5; i++) {
            double dis1;
            double k = distance * 5;
            Line resultLine;
            do {
                k -= decrease;
                Point newPoint = new Point(runPoint.getX() - k, runPoint.getY() - k);
                resultLine = new Line(newPoint, oriLine.getPt());
                dis1 = resultLine.getDistance(runPoint);
            } while (dis1 > distance);
            double xNew = runPoint.getX() - 3;
            runPoint = new Point(xNew, resultLine.generateY(xNew));
            double p = runPoint.getX() + 8;
            drawALine(runPoint, new Point(p, resultLine.generateY(p)));
        }


        //        Vector chinhphuong = getBaseVector();
//        Vector phaptuyen = new Vector(-chinhphuong.getY(), chinhphuong.getX());
//
//        Line line = new Line(phaptuyen.getX(), phaptuyen.getY(), 0);
//
//        Point ori1 = new Point(beginLine[0]);
//        Point la1 = new Point(ori1.getX() - 2, ori1.getY() - 2);
//        line.setC(-(line.getA() * la1.getX() + line.getB() * la1.getY()));
//
//        double a = 154.2;
//        Point la2 = new Point(a, line.generateY(a));
//
//        drawALine(la1.toLatLng(), la2.toLatLng());

//        mGoogleMap.addPolyline(new PolylineOptions().add(new LatLng(1, 1), new LatLng(-28, 32.049)));
    }

    private void drawFirstLine() {
        drawParalelLine(3);
    }

    private void drawALine(Point a, Point b) {
        mGoogleMap.addPolyline(new PolylineOptions().add(a.toLatLng(), b.toLatLng()));
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
