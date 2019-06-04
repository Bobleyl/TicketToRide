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
import java.util.Map;
import java.util.HashMap;
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
    private Map< Route,Polyline> mRouteMap = new HashMap< Route,Polyline>();
    Polyline mSeattlePortland;
    Polyline mSeattlePortland2;
    Polyline mSeattleVancouver;
    Polyline mSeattleVancouver2;
    Polyline mVancouverCalgary;
    Polyline mSeattleCalgary;
    Polyline mSeattleHellena;
    Polyline mPortlandSaltLake;
    Polyline mSaltLakeHelena;
    Polyline mPortlandSanFrancisco;
    Polyline mPortlandSanFrancisco2;
    Polyline mSanFranciscoSaltLake;
    Polyline mSanFranciscoSaltLake2;
    Polyline mSanFranciscoLosAngeles;
    Polyline mSanFranciscoLosAngeles2;
    Polyline mLosAngelesLasVegas;
    Polyline mLasVegasSaltLake;
    Polyline mLosAngelesPhoenix;
    Polyline mLosAngelesElPaso;
    Polyline mPhoenixElPaso;
    Polyline mPhoenixSantaFe;
    Polyline mPhoenixDenver;
    Polyline mElPasoSantaFe;
    Polyline mSantaFeDenver;
    Polyline mSaltLakeDenver;
    Polyline mSaltLakeDenver2;
    Polyline mHelenaDenver;
    Polyline mCalgaryWinnipeg;
    Polyline mHelenaWinnipeg;
    Polyline mCalgaryHelena;
    Polyline mHelenaDuluth;
    Polyline mWinnipegDuluth;
    Polyline mHelenaOmaha;
    Polyline mDenverOmaha;
    Polyline mDuluthOmaha;
    Polyline mDuluthOmaha2;
    Polyline mDenverKansasCity;
    Polyline mDenverKansasCity2;
    Polyline mOmahaKansasCity;
    Polyline mOmahaKansasCity2;
    Polyline mDenverOklahomaCity;
    Polyline mSantaFeOklahomaCity;
    Polyline mElPasoOklahomaCity;
    Polyline mKansasCityOklahomaCity;
    Polyline mKansasCityOklahomaCity2;
    Polyline mOklahomaCityDallas;
    Polyline mOklahomaCityDallas2;
    Polyline mElPasoDallas;
    Polyline mDallasHouston;
    Polyline mDallasHouston2;
    Polyline mElPasoHouston;
    Polyline mHoustonNewOrleans;
    Polyline mLittleRockNewOrleans;
    Polyline mDallasLittleRock;
    Polyline mOklahomaCityLittleRock;
    Polyline mSaintLouislittleRock;
    Polyline mKansasCitySaintLouis;
    Polyline mKansasCitySaintLouis2;
    Polyline mSaintLouisChicago;
    Polyline mSaintLouisChicago2;
    Polyline mOmahaChicago;
    Polyline mDuluthChicago;
    Polyline mChicagoToronto;
    Polyline mDuluthToronto;
    Polyline mDuluthStMarie;
    Polyline mWinnipegStMarie;
    Polyline mStMarieToronto;
    Polyline mStMarieMontreal;
    Polyline mMontrealBoston;
    Polyline mMontrealBoston2;
    Polyline mMontrealNewYork;
    Polyline mTorontoMontreal;
    Polyline mTorontoPittsburg;
    Polyline mChicagoPittsburg;
    Polyline mChicagoPittsburg2;
    Polyline mSaintLouisPittsburg;
    Polyline mBostonNewYork;
    Polyline mBostonNewYork2;
    Polyline mNewYorkPittsburg;
    Polyline mNewYorkPittsburg2;
    Polyline mPittsburgWashington;
    Polyline mNewYorkWashington;
    Polyline mNewYorkWashington2;
    Polyline mWashingtonRaleigh;
    Polyline mWashingtonRaleigh2;
    Polyline mPittsburgRaleigh;
    Polyline mNashvilleRaleigh;
    Polyline mSaintLouisNashville;
    Polyline mPittsburgNashville;
    Polyline mLittleRockNashville;
    Polyline mNashvilleAtlanta;
    Polyline mRaleighAtlanta;
    Polyline mRaleighAtlanta2;
    Polyline mNewOrleansAtlanta;
    Polyline mNewOrleansAtlanta2;
    Polyline mRaleighCharleston;
    Polyline mAtlantaCharleston;
    Polyline mCharlestonMiami;
    Polyline mAtlantaMiami;
    Polyline mNewOrleansMiami;

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
        mRouteSpinner.setOnItemSelectedListener(new spinnerOnClickListener());



        return mView;
    }

    /*--------
     SPINNER LOGIC
     --------*/
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
            mRouteSpinner.setOnItemSelectedListener(new spinnerOnClickListener());
        }
    }

    private class spinnerOnClickListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            //TODO: Implement claiming a route here.
            mRouteString = (String) parent.getSelectedItem();
            // How do we turn this string into a route enum?
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    /*--------
     MAP LOGIC
     --------*/
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
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(35.8780, -93.0977), 3));

        drawPolyLines(googleMap);
    }

    private void updateRoutes(){
        //USE A MAP OF <NAME, POLYLINE>
        //TODO: Make this method update the routes shown on the map to show who owns them.
    }

    /*--------
    FRAGMENT OVERRIDES AND DEFAULTS
    --------*/
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

    @Override
    public String getID() {
        return ID;
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

    /*--------
    UTILITY
    --------*/
    private void drawPolyLines(GoogleMap googleMap) {

        mSeattlePortland = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(47.6062, -122.3321), new LatLng(45.5155, -122.6793))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Portland_Seattle,mSeattlePortland);

        mSeattlePortland2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(47.603877, -122.965880), new LatLng(45.581488, -123.369117))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Portland_Seattle_2,mSeattlePortland2);

        mSeattleVancouver = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(47.6062, -122.3321), new LatLng(49.2827, -123.1207))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Seattle_Vancouver,mSeattleVancouver);

        mSeattleVancouver2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(47.603877, -122.965880), new LatLng(49.227659, -123.583162))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Seattle_Vancouver_2,mSeattleVancouver2);

        mVancouverCalgary = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(49.2827, -123.1207), new LatLng(51.0486, -114.0708))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Vancouver_Calgary,mVancouverCalgary);

        mSeattleCalgary = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(47.6062, -122.3321), new LatLng(51.0486, -114.0708))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Seattle_Calgary,mSeattleCalgary);

        mSeattleHellena = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(47.6062, -122.3321), new LatLng(46.5891, -112.0391))
                .width(10)
                .color(Color.rgb(234, 222, 45))
        );
        mRouteMap.put(Route.Seattle_Hellena,mSeattleHellena);

        mPortlandSaltLake = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(45.5155, -122.6793), new LatLng(40.7608, -111.8910))
                .width(10)
                .color(Color.parseColor("blue"))
        );
        mRouteMap.put(Route.Portland_SaltLakeCity,mPortlandSaltLake);

        mSaltLakeHelena = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(40.7608, -111.8910), new LatLng(46.5891, -112.0391))
                .width(10)
                .color(Color.rgb(255,0,255))//pink
        );
        mRouteMap.put(Route.SaltLakeCity_Hellena,mSaltLakeHelena);

        mPortlandSanFrancisco = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(45.5155, -122.6793), new LatLng(37.7749, -122.4194))
                .width(8)
                .color(Color.parseColor("green"))
        );
        mRouteMap.put(Route.SanFrancisco_Portland,mPortlandSanFrancisco);
        mPortlandSanFrancisco2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(45.581488, -123.369117), new LatLng(37.730641, -122.864849))
                .width(8)
                .color(Color.rgb(255,0,255))//pink
        );
        mRouteMap.put(Route.SanFrancisco_Portland_2,mPortlandSanFrancisco2);

        mSanFranciscoSaltLake = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(37.7749, -122.4194), new LatLng(40.7608, -111.8910))
                .width(8)
                .color(Color.parseColor("white"))
        );
        mRouteMap.put(Route.SanFrancisco_SaltLakeCity,mSanFranciscoSaltLake);

        mSanFranciscoSaltLake2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(38.086034, -122.458533), new LatLng(41.021161, -112.098136))
                .width(8)
                .color(Color.rgb(255,165,0))//orange
        );
        mRouteMap.put(Route.SanFrancisco_SaltLakeCity_2,mSanFranciscoSaltLake2);

        mSanFranciscoLosAngeles = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(37.7749, -122.4194), new LatLng(34.0522, -118.2437))
                .width(8)
                .color(Color.rgb(234, 222, 45))
        );
        mRouteMap.put(Route.LosAngeles_SanFrancisco,mSanFranciscoLosAngeles);

        mSanFranciscoLosAngeles2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(37.730641, -122.864849), new LatLng(33.856838, -118.417167))
                .width(8)
                .color(Color.rgb(255,0,255))//pink
        );
        mRouteMap.put(Route.LosAngeles_SanFrancisco_2,mSanFranciscoLosAngeles2);

        mLosAngelesLasVegas = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(34.0522, -118.2437), new LatLng(36.1699, -115.1398))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.LosAngeles_LasVegas,mLosAngelesLasVegas);

        mLasVegasSaltLake = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(36.1699, -115.1398), new LatLng(40.7608, -111.8910))
                .width(10)
                .color(Color.rgb(255,165,0))//orange
        );
        mRouteMap.put(Route.SaltLakeCity_LasVegas,mLasVegasSaltLake);

        mLosAngelesPhoenix = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(34.0522, -118.2437), new LatLng(33.4484, -112.0740))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.LosAngeles_Phoenix,mLosAngelesPhoenix);

        mLosAngelesElPaso = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(34.0522, -118.2437), new LatLng(31.7619, -106.4850))
                .width(10)
                .color(Color.parseColor("black"))
        );
        mRouteMap.put(Route.ElPaso_LosAngeles,mLosAngelesElPaso);

        mPhoenixElPaso = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(33.4484, -112.0740), new LatLng(31.7619, -106.4850))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.ElPaso_Phoenix,mPhoenixElPaso);

        mPhoenixSantaFe = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(33.4484, -112.0740), new LatLng(35.6870, -105.9378))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.SantaFe_Phoenix,mPhoenixSantaFe);

        mPhoenixDenver = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(33.4484, -112.0740), new LatLng(39.7392, -104.9903))
                .width(10)
                .color(Color.parseColor("white"))
        );
        mRouteMap.put(Route.Denver_Phoenix,mPhoenixDenver);

        mElPasoSantaFe = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(31.7619, -106.4850), new LatLng(35.6870, -105.9378))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.ElPaso_SantaFe,mElPasoSantaFe);

        mSantaFeDenver = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(35.6870, -105.9378), new LatLng(39.7392, -104.9903))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Denver_SantaFe,mSantaFeDenver);

        mSaltLakeDenver = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(40.7608, -111.8910), new LatLng(39.7392, -104.9903))
                .width(8)
                .color(Color.parseColor("red"))
        );
        mRouteMap.put(Route.SaltLakeCity_Denver,mSaltLakeDenver);

        mSaltLakeDenver2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(41.021161, -112.098136), new LatLng(39.925305, -104.944488))
                .width(8)
                .color(Color.rgb(234, 222, 45))
        );
        mRouteMap.put(Route.SaltLakeCity_Denver_2,mSaltLakeDenver2);

        mHelenaDenver = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(46.5891,-112.0391), new LatLng(39.7392, -104.9903))
                .width(10)
                .color(Color.parseColor("green"))
        );
        mRouteMap.put(Route.Hellena_Denver,mHelenaDenver);

        mCalgaryWinnipeg = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(51.0486,-114.0708), new LatLng(49.8951, -97.1384))
                .width(10)
                .color(Color.parseColor("white"))
        );
        mRouteMap.put(Route.Calgary_Winnipeg,mCalgaryWinnipeg);

        mHelenaWinnipeg = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(46.5891,-112.0391), new LatLng(49.8951, -97.1384))
                .width(10)
                .color(Color.parseColor("blue"))
        );
        mRouteMap.put(Route.Winnipeg_Hellena,mHelenaWinnipeg);

        //this was the one that was breaking
        mCalgaryHelena = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(51.0486, -114.0708), new LatLng(46.5891, -112.0391))
                .width(10)
                .color(Color.parseColor("black"))
        );
        mRouteMap.put(Route.Calgary_Hellena,mCalgaryHelena);

        mHelenaDuluth = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(46.5891,-112.0391), new LatLng(46.7867, -92.1005))
                .width(10)
                .color(Color.rgb(255,165,0))//orange
        );
        mRouteMap.put(Route.Hellena_Duluth,mHelenaDuluth);

        mWinnipegDuluth = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(49.8951, -97.1384), new LatLng(46.7867, -92.1005))
                .width(10)
                .color(Color.parseColor("black"))
        );
        mRouteMap.put(Route.Winnipeg_Duluth,mWinnipegDuluth);

        mHelenaOmaha = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(46.5891,-112.0391), new LatLng(41.2565, -95.9345))
                .width(10)
                .color(Color.parseColor("red"))
        );
        mRouteMap.put(Route.Hellena_Omaha,mHelenaOmaha);

        mDenverOmaha = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(39.7392, -104.9903), new LatLng(41.2565, -95.9345))
                .width(10)
                .color(Color.rgb(255,0,255))//pink
        );
        mRouteMap.put(Route.Denver_Omaha,mDenverOmaha);

        mDuluthOmaha = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(46.7867, -92.1005), new LatLng(41.2565, -95.9345))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Duluth_Omaha,mDuluthOmaha);

        mDuluthOmaha2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(46.753932, -92.484388), new LatLng(41.267743, -96.132537))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Duluth_Omaha_2,mDuluthOmaha2);

        mDenverKansasCity = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(39.7392, -104.9903), new LatLng(39.0997, -94.5786))
                .width(8)
                .color(Color.parseColor("black"))
        );
        mRouteMap.put(Route.KansasCity_Denver,mDenverKansasCity);

        mDenverKansasCity2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(39.925305, -104.944488), new LatLng(39.220950, -94.558615))
                .width(8)
                .color(Color.rgb(255,165,0))//orange
        );
        mRouteMap.put(Route.KansasCity_Denver_2,mDenverKansasCity2);

        mOmahaKansasCity = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(41.2565, -95.9345), new LatLng(39.0997, -94.5786))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.KansasCity_Omaha,mOmahaKansasCity);

        mOmahaKansasCity2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(41.267743, -95.714889), new LatLng(39.145305, -94.372355))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.KansasCity_Omaha_2,mOmahaKansasCity2);

        mDenverOklahomaCity = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(39.7392, -104.9903), new LatLng(35.4676, -97.5164))
                .width(10)
                .color(Color.parseColor("red"))
        );
        mRouteMap.put(Route.Denver_OklahomaCity,mDenverOklahomaCity);

        mSantaFeOklahomaCity = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(35.6870, -105.9378), new LatLng(35.4676, -97.5164))
                .width(10)
                .color(Color.parseColor("blue"))
        );
        mRouteMap.put(Route.SantaFe_OklahomaCity,mSantaFeOklahomaCity);

        mElPasoOklahomaCity = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(31.7619, -106.4850), new LatLng(35.4676, -97.5164))
                .width(10)
                .color(Color.rgb(234, 222, 45))
        );
        mRouteMap.put(Route.ElPaso_OklahomaCity,mElPasoOklahomaCity);

        mKansasCityOklahomaCity = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(39.0997, -94.5786), new LatLng(35.4676, -97.5164))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.KansasCity_OklahomaCity,mKansasCityOklahomaCity);

        mKansasCityOklahomaCity2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(39.145305, -94.372355), new LatLng(35.404845, -97.286023))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.KansasCity_OklahomaCity_2,mKansasCityOklahomaCity2);

        mOklahomaCityDallas = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(35.4676, -97.5164), new LatLng(32.7767, -96.7970))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Dallas_OklahomaCity,mOklahomaCityDallas);

        mOklahomaCityDallas2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(35.404845, -97.286023), new LatLng(32.679479, -96.472422))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Dallas_OklahomaCity_2,mOklahomaCityDallas2);

        mElPasoDallas = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(31.7619, -106.4850), new LatLng(32.7767, -96.7970))
                .width(10)
                .color(Color.parseColor("red"))
        );
        mRouteMap.put(Route.ElPaso_Dallas,mElPasoDallas);

        mDallasHouston = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(32.7767, -96.7970), new LatLng(29.7604, -95.3698))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Houston_Dallas,mDallasHouston);

        mDallasHouston2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(32.679479, -96.472422), new LatLng(29.782332, -95.034271))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Houston_Dallas_2,mDallasHouston2);

        mElPasoHouston = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(31.7619, -106.4850), new LatLng(29.7604, -95.3698))
                .width(10)
                .color(Color.parseColor("green"))
        );
        mRouteMap.put(Route.Houston_ElPaso,mElPasoHouston);

        mHoustonNewOrleans = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(29.7604, -95.3698), new LatLng(29.9511, -90.0715))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.NewOrleans_Houston,mHoustonNewOrleans);

        mLittleRockNewOrleans = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(34.7465, -92.2896), new LatLng(29.9511, -90.0715))
                .width(10)
                .color(Color.parseColor("green"))
        );
        mRouteMap.put(Route.NewOrleans_LittleRock,mLittleRockNewOrleans);

        mDallasLittleRock = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(32.7767, -96.7970), new LatLng(34.7465, -92.2896))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.LittleRock_Dallas,mDallasLittleRock);

        mOklahomaCityLittleRock = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(35.4676, -97.5164), new LatLng(34.7465, -92.2896))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.LittleRock_OklahomaCity,mOklahomaCityLittleRock);

        mSaintLouislittleRock = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(38.6270, -90.1994), new LatLng(34.7465, -92.2896))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.SaintLouis_LittleRock,mSaintLouislittleRock);

        mKansasCitySaintLouis = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(39.0997, -94.5786), new LatLng(38.6270, -90.1994))
                .width(8)
                .color(Color.parseColor("blue"))
        );
        mRouteMap.put(Route.SaintLouis_KansasCity,mKansasCitySaintLouis);

        mKansasCitySaintLouis2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(39.287678, -94.561266), new LatLng(38.827004, -90.190619))
                .width(8)
                .color(Color.rgb(255,0,255))//pink
        );
        mRouteMap.put(Route.SaintLouis_KansasCity_2,mKansasCitySaintLouis2);

        mSaintLouisChicago = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(38.6270, -90.1994), new LatLng(41.8781, -87.6298))
                .width(8)
                .color(Color.parseColor("green"))
        );
        mRouteMap.put(Route.Chicago_SaintLouis,mSaintLouisChicago);

        mSaintLouisChicago2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(38.827004, -90.190619), new LatLng(41.900680, -87.984877))
                .width(8)
                .color(Color.parseColor("white"))
        );
        mRouteMap.put(Route.Chicago_SaintLouis_2,mSaintLouisChicago2);

        mOmahaChicago = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(41.2565, -95.9345), new LatLng(41.8781, -87.6298))
                .width(10)
                .color(Color.parseColor("blue"))
        );
        mRouteMap.put(Route.Chicago_Omaha,mOmahaChicago);

        mDuluthChicago = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(46.7867, -92.1005), new LatLng(41.8781, -87.6298))
                .width(10)
                .color(Color.parseColor("red"))
        );
        mRouteMap.put(Route.Duluth_Chicago,mDuluthChicago);

        mChicagoToronto = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(41.8781, -87.6298), new LatLng(43.6532, -79.3832))
                .width(10)
                .color(Color.parseColor("white"))
        );
        mRouteMap.put(Route.Chicago_Toronto,mChicagoToronto);

        mDuluthToronto = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(46.7867, -92.1005), new LatLng(43.6532, -79.3832))
                .width(10)
                .color(Color.rgb(255,0,255))//pink
        );
        mRouteMap.put(Route.Duluth_Toronto,mDuluthToronto);

        mDuluthStMarie = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(46.7867, -92.1005), new LatLng(46.5136, -84.3358))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.SaultSteMarie_Duluth,mDuluthStMarie);

        mWinnipegStMarie = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(49.8951, -97.1384), new LatLng(46.5136, -84.3358))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Winnipeg_SaultSteMarie,mWinnipegStMarie);

        mStMarieToronto = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(46.5136, -84.3358), new LatLng(43.6532, -79.3832))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.SaultSteMarie_Toronto,mStMarieToronto);

        mStMarieMontreal = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(46.5136, -84.3358), new LatLng(45.5017, -73.5673))
                .width(10)
                .color(Color.parseColor("black"))
        );
        mRouteMap.put(Route.SaultSteMarie_Montreal,mStMarieMontreal);

        mMontrealBoston = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(45.5017, -73.5673), new LatLng(42.3601, -71.0589))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Montreal_Boston,mMontrealBoston);

        mMontrealBoston2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(45.568120, -73.520557), new LatLng(42.439247, -70.906324))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Montreal_Boston_2,mMontrealBoston2);

        mMontrealNewYork = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(45.5017, -73.5673), new LatLng(40.7128, -74.0060))
                .width(10)
                .color(Color.parseColor("blue"))
        );
        mRouteMap.put(Route.Montreal_NewYork,mMontrealNewYork);

        mTorontoMontreal = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(43.6532, -79.3832), new LatLng(45.5017, -73.5673))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Montreal_Toronto,mTorontoMontreal);

        mTorontoPittsburg = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(43.6532, -79.3832), new LatLng(40.4406, -79.9959))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Pittsburgh_Toronto,mTorontoPittsburg);

        mChicagoPittsburg = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(41.8781, -87.6298), new LatLng(40.4406, -79.9959))
                .width(8)
                .color(Color.parseColor("black"))
        );
        mRouteMap.put(Route.Pittsburgh_Chicago,mChicagoPittsburg);

        mChicagoPittsburg2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(42.014866, -87.551682), new LatLng(40.519704, -79.981175))
                .width(8)
                .color(Color.rgb(255,165,0))//orange
        );
        mRouteMap.put(Route.Pittsburgh_Chicago_2,mChicagoPittsburg2);

        mSaintLouisPittsburg = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(38.6270, -90.1994), new LatLng(40.4406, -79.9959))
                .width(10)
                .color(Color.parseColor("green"))
        );
        mRouteMap.put(Route.Pittsburgh_SaintLouis,mSaintLouisPittsburg);

        mBostonNewYork = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(42.3601, -71.0589), new LatLng(40.7128, -74.0060))
                .width(8)
                .color(Color.parseColor("red"))
        );
        mRouteMap.put(Route.Boston_NewYork,mBostonNewYork);

        mBostonNewYork2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(42.439247, -70.906324), new LatLng(40.525022, -73.717557))
                .width(8)
                .color(Color.rgb(234, 222, 45))
        );
        mRouteMap.put(Route.Boston_NewYork_2,mBostonNewYork2);

        mNewYorkPittsburg = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(40.7128, -74.0060), new LatLng(40.4406, -79.9959))
                .width(8)
                .color(Color.parseColor("white"))
        );
        mRouteMap.put(Route.NewYork_Pittsburgh,mNewYorkPittsburg);

        mNewYorkPittsburg2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(40.493568, -73.993039), new LatLng(40.259224, -79.923062))
                .width(8)
                .color(Color.parseColor("green"))
        );
        mRouteMap.put(Route.NewYork_Pittsburgh_2,mNewYorkPittsburg2);

        mPittsburgWashington = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(40.4406, -79.9959), new LatLng(38.9072, -77.0369))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Washington_Pittsburgh,mPittsburgWashington);

        mNewYorkWashington = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(40.7128, -74.0060), new LatLng(38.9072, -77.0369))
                .width(8)
                .color(Color.parseColor("black"))
        );
        mRouteMap.put(Route.NewYork_Washington,mNewYorkWashington);

        mNewYorkWashington2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(40.597531, -73.744582), new LatLng(38.720073, -76.775483))
                .width(8)
                .color(Color.rgb(255,165,0))//orange
        );
        mRouteMap.put(Route.NewYork_Washington_2,mNewYorkWashington2);

        mWashingtonRaleigh = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(38.9072, -77.0369), new LatLng(35.7796, -78.6382))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Washington_Raleigh,mWashingtonRaleigh);

        mWashingtonRaleigh2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(38.720073, -76.775483), new LatLng(35.677448, -78.409070))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Washington_Raleigh_2,mWashingtonRaleigh2);

        mPittsburgRaleigh = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(40.4406, -79.9959), new LatLng(35.7796, -78.6382))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Pittsburgh_Raleigh,mPittsburgRaleigh);

        mNashvilleRaleigh = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(36.1627, -86.7816), new LatLng(35.7796, -78.6382))
                .width(10)
                .color(Color.parseColor("black"))
        );
        mRouteMap.put(Route.Raleigh_Nashville,mNashvilleRaleigh);

        mSaintLouisNashville = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(38.6270, -90.1994), new LatLng(36.1627, -86.7816))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.SaintLouis_Nashville,mSaintLouisNashville);

        mPittsburgNashville = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(40.4406, -79.9959), new LatLng(36.1627, -86.7816))
                .width(10)
                .color(Color.rgb(234, 222, 45))
        );
        mRouteMap.put(Route.Pittsburgh_Nashville,mPittsburgNashville);

        mLittleRockNashville = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(34.7465, -92.2896), new LatLng(36.1627, -86.7816))
                .width(10)
                .color(Color.parseColor("white"))
        );
        mRouteMap.put(Route.Nashville_LittleRock,mLittleRockNashville);

        mNashvilleAtlanta = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(36.1627, -86.7816), new LatLng(33.7490, -84.3880))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Nashville_Atlanta,mNashvilleAtlanta);

        mRaleighAtlanta = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(35.7796, -78.6382), new LatLng(33.7490, -84.3880))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Raleigh_Atlanta,mRaleighAtlanta);

        mRaleighAtlanta2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(35.677448, -78.409070), new LatLng(33.577872, -84.164505))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Raleigh_Atlanta_2,mRaleighAtlanta2);

        mNewOrleansAtlanta = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(29.9511, -90.0715), new LatLng(33.7490, -84.3880))
                .width(8)
                .color(Color.rgb(234, 222, 45))
        );
        mRouteMap.put(Route.NewOrleans_Atlanta,mNewOrleansAtlanta);

        mNewOrleansAtlanta2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(29.792165, -89.925687), new LatLng(33.577872, -84.164505))
                .width(8)
                .color(Color.rgb(255,165,0))//orange
        );
        mRouteMap.put(Route.NewOrleans_Atlanta_2,mNewOrleansAtlanta2);

        mRaleighCharleston = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(35.7796, -78.6382), new LatLng(32.7765, -79.9311))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Raleigh_Charleston,mRaleighCharleston);

        mAtlantaCharleston = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(33.7490, -84.3880), new LatLng(32.7765, -79.9311))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Charleston_Atlanta,mAtlantaCharleston);

        mCharlestonMiami = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(32.7765, -79.9311), new LatLng(25.7617, -80.1918))
                .width(10)
                .color(Color.rgb(255,0,255))//pink
        );
        mRouteMap.put(Route.Charleston_Miami,mCharlestonMiami);

        mAtlantaMiami = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(33.7490, -84.3880), new LatLng(25.7617, -80.1918))
                .width(10)
                .color(Color.parseColor("blue"))
        );
        mRouteMap.put(Route.Miami_Atlanta,mAtlantaMiami);

        mNewOrleansMiami = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(29.9511, -90.0715), new LatLng(25.7617, -80.1918))
                .width(10)
                .color(Color.parseColor("red"))
        );
        mRouteMap.put(Route.Miami_NewOrleans,mNewOrleansMiami);
//        System.out.println("MAP OF VALUES: ");
//        System.out.println(mRouteMap);
    }

    public static MapFragment newInstance(String param1, String param2) {
        MapFragment fragment = new MapFragment();

        return fragment;
    }
}
