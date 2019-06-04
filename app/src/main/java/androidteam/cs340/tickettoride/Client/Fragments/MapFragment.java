package androidteam.cs340.tickettoride.Client.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Button;

import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;

import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

import androidteam.cs340.tickettoride.Client.Phase2Facade;
import androidteam.cs340.tickettoride.Client.Presenters.IPresenter;
import androidteam.cs340.tickettoride.R;
import androidteam.cs340.tickettoride.Shared.Route;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment implements IPresenter, OnMapReadyCallback {
    private List<Route> lastList = new ArrayList<>();
    private OnFragmentInteractionListener mListener;
    private Button mClaimRoute;
    private String ID;
    private Spinner mRouteSpinner;
    private String mRouteString;
    private GoogleMap mMap;
    private MapView mMapView;
    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_map, container,false);

        mRouteSpinner = (Spinner) mView.findViewById(R.id.claimRouteSpinner);
        mClaimRoute = (Button) mView.findViewById(R.id.claimRoute);

        String[] items = new String[]{"Values"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, items);
        mRouteSpinner.setAdapter(adapter);

        mRouteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mRouteString = (String) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMapView = (MapView) mView.findViewById(R.id.mapView);
        if (mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        mMap = googleMap;
        boolean success = googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(getContext(),R.raw.map_styles));

        //Centers map on united states
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(41.8780, -93.0977), 3));

        Polyline mSeattlePortland = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(47.6062, -122.3321), new LatLng(45.5155, -122.6793))
                .width(8)
                .color(Color.parseColor("gray"))
        );

        Polyline mSeattlePortland2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(47.603877, -122.965880), new LatLng(45.581488, -123.369117))
                .width(8)
                .color(Color.parseColor("gray"))
        );

        Polyline mSeattleVancouver = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(47.6062, -122.3321), new LatLng(49.2827, -123.1207))
                .width(8)
                .color(Color.parseColor("gray"))
        );

        Polyline mSeattleVancouver2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(47.603877, -122.965880), new LatLng(49.227659, -123.583162))
                .width(8)
                .color(Color.parseColor("gray"))
        );

        Polyline mVancouverCalgary = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(49.2827, -123.1207), new LatLng(51.0486, -114.0708))
                .width(10)
                .color(Color.parseColor("gray"))
        );

        Polyline mSeattleCalgary = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(47.6062, -122.3321), new LatLng(51.0486, -114.0708))
                .width(10)
                .color(Color.parseColor("gray"))
        );

        Polyline mSeattleHelena = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(47.6062, -122.3321), new LatLng(46.5891, -112.0391))
                .width(10)
                .color(Color.rgb(234, 222, 45))
        );

        Polyline mPortlandSaltLake = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(45.5155, -122.6793), new LatLng(40.7608, -111.8910))
                .width(10)
                .color(Color.parseColor("blue"))
        );

        Polyline mSaltLakeHelena = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(40.7608, -111.8910), new LatLng(46.5891, -112.0391))
                .width(10)
                .color(Color.rgb(255,0,255))//pink
        );

        Polyline mPortlandSanFrancisco = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(45.5155, -122.6793), new LatLng(37.7749, -122.4194))
                .width(8)
                .color(Color.parseColor("green"))
        );

        Polyline mPortlandSanFrancisco2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(45.581488, -123.369117), new LatLng(37.730641, -122.864849))
                .width(8)
                .color(Color.rgb(255,0,255))//pink
        );

        Polyline mSanFranciscoSaltLake = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(37.7749, -122.4194), new LatLng(40.7608, -111.8910))
                .width(8)
                .color(Color.parseColor("white"))
        );

        Polyline mSanFranciscoSaltLake2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(38.086034, -122.458533), new LatLng(41.021161, -112.098136))
                .width(8)
                .color(Color.rgb(255,165,0))//orange
        );

        Polyline mSanFranciscoLosAngeles = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(37.7749, -122.4194), new LatLng(34.0522, -118.2437))
                .width(8)
                .color(Color.rgb(234, 222, 45))
        );

        Polyline mSanFranciscoLosAngeles2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(37.730641, -122.864849), new LatLng(33.856838, -118.417167))
                .width(8)
                .color(Color.rgb(255,0,255))//pink
        );

        Polyline mLosAngelesLasVegas = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(34.0522, -118.2437), new LatLng(36.1699, -115.1398))
                .width(10)
                .color(Color.parseColor("gray"))
        );

        Polyline mLasVegasSaltLake = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(36.1699, -115.1398), new LatLng(40.7608, -111.8910))
                .width(10)
                .color(Color.rgb(255,165,0))//orange
        );

        Polyline mLosAngelesPhoenix = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(34.0522, -118.2437), new LatLng(33.4484, -112.0740))
                .width(10)
                .color(Color.parseColor("gray"))
        );

        Polyline mLosAngelesElPaso = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(34.0522, -118.2437), new LatLng(31.7619, -106.4850))
                .width(10)
                .color(Color.parseColor("black"))
        );

        Polyline mPhoenixElPaso = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(33.4484, -112.0740), new LatLng(31.7619, -106.4850))
                .width(10)
                .color(Color.parseColor("gray"))
        );

        Polyline mPhoenixSantaFe = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(33.4484, -112.0740), new LatLng(35.6870, -105.9378))
                .width(10)
                .color(Color.parseColor("gray"))
        );

        Polyline mPhoenixDenver = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(33.4484, -112.0740), new LatLng(39.7392, -104.9903))
                .width(10)
                .color(Color.parseColor("white"))
        );

        Polyline mElPasoSantaFe = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(31.7619, -106.4850), new LatLng(35.6870, -105.9378))
                .width(10)
                .color(Color.parseColor("gray"))
        );

        Polyline mSantaFeDenver = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(35.6870, -105.9378), new LatLng(39.7392, -104.9903))
                .width(10)
                .color(Color.parseColor("gray"))
        );

        Polyline mSaltLakeDenver = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(40.7608, -111.8910), new LatLng(39.7392, -104.9903))
                .width(8)
                .color(Color.parseColor("red"))
        );

        Polyline mSaltLakeDenver2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(41.021161, -112.098136), new LatLng(39.925305, -104.944488))
                .width(8)
                .color(Color.rgb(234, 222, 45))
        );

        Polyline mHelenaDenver = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(46.5891,-112.0391), new LatLng(39.7392, -104.9903))
                .width(10)
                .color(Color.parseColor("green"))
        );

        Polyline mCalgaryWinnipeg = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(51.0486,-114.0708), new LatLng(49.8951, -97.1384))
                .width(10)
                .color(Color.parseColor("white"))
        );

        Polyline mHelenaWinnipeg = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(46.5891,-112.0391), new LatLng(49.8951, -97.1384))
                .width(10)
                .color(Color.parseColor("blue"))
        );

        //TODO: KEEPS BREAKING WHEN TRYING TO ADD THIS ROUTE
//        Polyline mCalgaryHelena = googleMap.addPolyline(new PolylineOptions()
//                .add(new LatLng(51.0486, -114.0708), new LatLng(46.5891, -112.0391))
//                .width(10)
//        );

        Polyline mHelenaDuluth = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(46.5891,-112.0391), new LatLng(46.7867, -92.1005))
                .width(10)
                .color(Color.rgb(255,165,0))//orange
        );

        Polyline mWinnipegDuluth = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(49.8951, -97.1384), new LatLng(46.7867, -92.1005))
                .width(10)
                .color(Color.parseColor("black"))
        );

        Polyline mHelenaOmaha = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(46.5891,-112.0391), new LatLng(41.2565, -95.9345))
                .width(10)
                .color(Color.parseColor("red"))
        );

        Polyline mDenverOmaha = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(39.7392, -104.9903), new LatLng(41.2565, -95.9345))
                .width(10)
                .color(Color.rgb(255,0,255))//pink
        );

        Polyline mDuluthOmaha = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(46.7867, -92.1005), new LatLng(41.2565, -95.9345))
                .width(8)
                .color(Color.parseColor("gray"))
        );

        Polyline mDuluthOmaha2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(46.753932, -92.484388), new LatLng(41.267743, -96.132537))
                .width(8)
                .color(Color.parseColor("gray"))
        );

        Polyline mDenverKansasCity = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(39.7392, -104.9903), new LatLng(39.0997, -94.5786))
                .width(8)
                .color(Color.parseColor("black"))
        );

        Polyline mDenverKansasCity2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(39.925305, -104.944488), new LatLng(39.220950, -94.558615))
                .width(8)
                .color(Color.rgb(255,165,0))//orange
        );

        Polyline mOmahaKansasCity = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(41.2565, -95.9345), new LatLng(39.0997, -94.5786))
                .width(8)
                .color(Color.parseColor("gray"))
        );

        Polyline mOmahaKansasCity2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(41.267743, -95.714889), new LatLng(39.145305, -94.372355))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        Polyline mDenverOklahomaCity = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(39.7392, -104.9903), new LatLng(35.4676, -97.5164))
                .width(10)
                .color(Color.parseColor("red"))
        );

        Polyline mSantaFeOklahomaCity = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(35.6870, -105.9378), new LatLng(35.4676, -97.5164))
                .width(10)
                .color(Color.parseColor("blue"))
        );

        Polyline mElPasoOklahomaCity = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(31.7619, -106.4850), new LatLng(35.4676, -97.5164))
                .width(10)
                .color(Color.rgb(234, 222, 45))
        );

        Polyline mKansasCityOklahomaCity = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(39.0997, -94.5786), new LatLng(35.4676, -97.5164))
                .width(8)
                .color(Color.parseColor("gray"))
        );

        Polyline mKansasCityOklahomaCity2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(39.145305, -94.372355), new LatLng(35.404845, -97.286023))
                .width(8)
                .color(Color.parseColor("gray"))
        );

        Polyline mOklahomaCityDallas = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(35.4676, -97.5164), new LatLng(32.7767, -96.7970))
                .width(8)
                .color(Color.parseColor("gray"))
        );

        Polyline mOklahomaCityDallas2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(35.404845, -97.286023), new LatLng(32.679479, -96.472422))
                .width(8)
                .color(Color.parseColor("gray"))
        );

        Polyline mElPasoDallas = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(31.7619, -106.4850), new LatLng(32.7767, -96.7970))
                .width(10)
                .color(Color.parseColor("red"))
        );

        Polyline mDallasHouston = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(32.7767, -96.7970), new LatLng(29.7604, -95.3698))
                .width(8)
                .color(Color.parseColor("gray"))
        );

        Polyline mDallasHouston2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(32.679479, -96.472422), new LatLng(29.782332, -95.034271))
                .width(8)
                .color(Color.parseColor("gray"))
        );

        Polyline mElPasoHouston = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(31.7619, -106.4850), new LatLng(29.7604, -95.3698))
                .width(10)
                .color(Color.parseColor("green"))
        );

        Polyline mHoustonNewOrleans = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(29.7604, -95.3698), new LatLng(29.9511, -90.0715))
                .width(10)
                .color(Color.parseColor("gray"))
        );

        Polyline mLittleRockNewOrleans = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(34.7465, -92.2896), new LatLng(29.9511, -90.0715))
                .width(10)
                .color(Color.parseColor("green"))
        );

        Polyline mDallasLittleRock = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(32.7767, -96.7970), new LatLng(34.7465, -92.2896))
                .width(10)
                .color(Color.parseColor("gray"))
        );

        Polyline mOklahomaCityLittleRock = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(35.4676, -97.5164), new LatLng(34.7465, -92.2896))
                .width(10)
                .color(Color.parseColor("gray"))
        );

        Polyline mSaintLouislittleRock = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(38.6270, -90.1994), new LatLng(34.7465, -92.2896))
                .width(10)
                .color(Color.parseColor("gray"))
        );

        Polyline mKansasCitySaintLouis = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(39.0997, -94.5786), new LatLng(38.6270, -90.1994))
                .width(8)
                .color(Color.parseColor("blue"))
        );

        Polyline mKansasCitySaintLouis2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(39.287678, -94.561266), new LatLng(38.827004, -90.190619))
                .width(8)
                .color(Color.rgb(255,0,255))//pink
        );

        Polyline mSaintLouisChicago = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(38.6270, -90.1994), new LatLng(41.8781, -87.6298))
                .width(8)
                .color(Color.parseColor("green"))
        );

        Polyline mSaintLouisChicago2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(38.827004, -90.190619), new LatLng(41.900680, -87.984877))
                .width(8)
                .color(Color.parseColor("white"))
        );

        Polyline mOmahaChicago = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(41.2565, -95.9345), new LatLng(41.8781, -87.6298))
                .width(10)
                .color(Color.parseColor("blue"))
        );

        Polyline mDuluthChicago = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(46.7867, -92.1005), new LatLng(41.8781, -87.6298))
                .width(10)
                .color(Color.parseColor("red"))
        );

        Polyline mChicagoToronto = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(41.8781, -87.6298), new LatLng(43.6532, -79.3832))
                .width(10)
                .color(Color.parseColor("white"))
        );

        Polyline mDuluthToronto = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(46.7867, -92.1005), new LatLng(43.6532, -79.3832))
                .width(10)
                .color(Color.rgb(255,0,255))//pink
        );

        Polyline mDuluthStMarie = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(46.7867, -92.1005), new LatLng(46.5136, -84.3358))
                .width(10)
                .color(Color.parseColor("gray"))
        );

        Polyline mWinnipegStMarie = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(49.8951, -97.1384), new LatLng(46.5136, -84.3358))
                .width(10)
                .color(Color.parseColor("gray"))
        );

        Polyline mStMarieToronto = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(46.5136, -84.3358), new LatLng(43.6532, -79.3832))
                .width(10)
                .color(Color.parseColor("gray"))
        );

        Polyline mStMarieMontreal = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(46.5136, -84.3358), new LatLng(45.5017, -73.5673))
                .width(10)
                .color(Color.parseColor("black"))
        );

        Polyline mMontrealBoston = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(45.5017, -73.5673), new LatLng(42.3601, -71.0589))
                .width(8)
                .color(Color.parseColor("gray"))
        );

        Polyline mMontrealBoston2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(45.568120, -73.520557), new LatLng(42.439247, -70.906324))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        //
        Polyline mMontrealNewYork = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(45.5017, -73.5673), new LatLng(40.7128, -74.0060))
                .width(10)
                .color(Color.parseColor("blue"))
        );

        Polyline mTorontoMontreal = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(43.6532, -79.3832), new LatLng(45.5017, -73.5673))
                .width(10)
                .color(Color.parseColor("gray"))
        );

        Polyline mTorontoPittsburg = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(43.6532, -79.3832), new LatLng(40.4406, -79.9959))
                .width(10)
                .color(Color.parseColor("gray"))
        );

        Polyline mChicagoPittsburg = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(41.8781, -87.6298), new LatLng(40.4406, -79.9959))
                .width(8)
                .color(Color.parseColor("black"))
        );

        Polyline mChicagoPittsburg2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(42.014866, -87.551682), new LatLng(40.519704, -79.981175))
                .width(8)
                .color(Color.rgb(255,165,0))//orange
        );

        Polyline mSaintLouisPittsburg = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(38.6270, -90.1994), new LatLng(40.4406, -79.9959))
                .width(10)
                .color(Color.parseColor("green"))
        );

        Polyline mBostonNewYork = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(42.3601, -71.0589), new LatLng(40.7128, -74.0060))
                .width(8)
                .color(Color.parseColor("red"))
        );

        Polyline mBostonNewYork2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(42.439247, -70.906324), new LatLng(40.525022, -73.717557))
                .width(8)
                .color(Color.rgb(234, 222, 45))
        );

        Polyline mNewYorkPittsburg = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(40.7128, -74.0060), new LatLng(40.4406, -79.9959))
                .width(8)
                .color(Color.parseColor("white"))
        );

        Polyline mNewYorkPittsburg2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(40.493568, -73.993039), new LatLng(40.259224, -79.923062))
                .width(8)
                .color(Color.parseColor("green"))
        );

        Polyline mPittsburgWashington = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(40.4406, -79.9959), new LatLng(38.9072, -77.0369))
                .width(10)
                .color(Color.parseColor("gray"))
        );

        Polyline mNewYorkWashington = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(40.7128, -74.0060), new LatLng(38.9072, -77.0369))
                .width(10)
                .color(Color.parseColor("black"))
        );

        Polyline mNewYorkWashington2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(40.7128, -74.0060), new LatLng(38.9072, -77.0369))
                .width(10)
                .color(Color.rgb(255,165,0))//orange
        );

        Polyline mWashingtonRaleigh = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(38.9072, -77.0369), new LatLng(35.7796, -78.6382))
                .width(10)
                .color(Color.parseColor("gray"))
        );

        Polyline mWashingtonRaleigh2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(38.9072, -77.0369), new LatLng(35.7796, -78.6382))
                .width(10)
                .color(Color.parseColor("gray"))
        );

        Polyline mPittsburgRaleigh = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(40.4406, -79.9959), new LatLng(35.7796, -78.6382))
                .width(10)
                .color(Color.parseColor("gray"))
        );

        Polyline mNashvilleRaleigh = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(36.1627, -86.7816), new LatLng(35.7796, -78.6382))
                .width(10)
                .color(Color.parseColor("black"))
        );

        Polyline mSaintLouisNashville = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(38.6270, -90.1994), new LatLng(36.1627, -86.7816))
                .width(10)
                .color(Color.parseColor("gray"))
        );

        Polyline mPittsburgNashville = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(40.4406, -79.9959), new LatLng(36.1627, -86.7816))
                .width(10)
                .color(Color.rgb(234, 222, 45))
        );

        Polyline mLittleRockNashville = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(34.7465, -92.2896), new LatLng(36.1627, -86.7816))
                .width(10)
                .color(Color.parseColor("white"))
        );

        Polyline mNashvilleAtlanta = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(36.1627, -86.7816), new LatLng(33.7490, -84.3880))
                .width(10)
                .color(Color.parseColor("gray"))
        );

        Polyline mRaleighAtlanta = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(35.7796, -78.6382), new LatLng(33.7490, -84.3880))
                .width(10)
                .color(Color.parseColor("gray"))
        );

        Polyline mRaleighAtlanta2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(35.7796, -78.6382), new LatLng(33.7490, -84.3880))
                .width(10)
                .color(Color.parseColor("gray"))
        );

        Polyline mNewOrleansAtlanta = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(29.9511, -90.0715), new LatLng(33.7490, -84.3880))
                .width(10)
                .color(Color.rgb(234, 222, 45))
        );

        Polyline mNewOrleansAtlanta2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(29.9511, -90.0715), new LatLng(33.7490, -84.3880))
                .width(10)
                .color(Color.rgb(255,165,0))//orange
        );

        Polyline mRaleighCharleston = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(35.7796, -78.6382), new LatLng(32.7765, -79.9311))
                .width(10)
                .color(Color.parseColor("gray"))
        );

        Polyline mAtlantaCharleston = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(33.7490, -84.3880), new LatLng(32.7765, -79.9311))
                .width(10)
                .color(Color.parseColor("gray"))
        );

        Polyline mCharlestonMiami = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(32.7765, -79.9311), new LatLng(25.7617, -80.1918))
                .width(10)
                .color(Color.rgb(255,0,255))//pink
        );

        Polyline mAtlantaMiami = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(33.7490, -84.3880), new LatLng(25.7617, -80.1918))
                .width(10)
                .color(Color.parseColor("blue"))
        );

        Polyline mNewOrleansMiami = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(29.9511, -90.0715), new LatLng(25.7617, -80.1918))
                .width(10)
                .color(Color.parseColor("red"))
        );

    }

    public void updateSpinner(){
        int size = Phase2Facade.SINGLETON.getCurrentGame().getAvailableRoutes().size();
        String[] items = new String[size];
        boolean update = true;

        if(lastList.size() == Phase2Facade.SINGLETON.getCurrentGame().getAvailableRoutes().size()){
            update = false;
        }else{
            lastList = Phase2Facade.SINGLETON.getCurrentGame().getAvailableRoutes();
        }

        if(update == true){
            int i = 0;
            for(Route route : Phase2Facade.SINGLETON.getCurrentGame().getAvailableRoutes()){
                items[i] = ("" + route);
                i++;
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, items);
            mRouteSpinner.setAdapter(adapter);

            mRouteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    mRouteString = (String) parent.getSelectedItem();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

    public MapFragment() {
        this.ID = UUID.randomUUID().toString();
    }

    @Override
    public void onResume(){
        super.onResume();
        this.ID = UUID.randomUUID().toString();
        Phase2Facade.SINGLETON.addPresenter(this);
    }

    @Override
    public void onPause(){
        super.onPause();
        Phase2Facade.SINGLETON.removePresenter(this);
    }

    @Override
    public void Update() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                updateSpinner();
                updateRoutes();
            }
        });
    }

    public void updateRoutes(){

    }

    @Override
    public String getID() {
        return ID;
    }

    public static MapFragment newInstance(String param1, String param2) {
        MapFragment fragment = new MapFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
