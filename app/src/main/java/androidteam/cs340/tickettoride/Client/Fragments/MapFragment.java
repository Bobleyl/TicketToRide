package androidteam.cs340.tickettoride.Client.Fragments;

import android.content.Context;
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
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

import androidteam.cs340.tickettoride.Client.Phase2Facade;
import androidteam.cs340.tickettoride.Client.Presenters.IPresenter;
import androidteam.cs340.tickettoride.R;
import androidteam.cs340.tickettoride.Shared.Colors;
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

        //Centers map on united states
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(41.8780, -93.0977), 3));

        //TODO: CREATE LIST OF ROUTES AND DISPLAY IN PROPER COLORS ON MAP
        Polyline mSeattlePortland = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(47.6062, -122.3321), new LatLng(45.5155, -122.6793))
                .width(10)
        );

        Polyline mSeattlePortland2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(47.6062, -122.3321), new LatLng(45.5155, -122.6793))
                .width(10)
        );

        Polyline mSeattleVancouver = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(47.6062, -122.3321), new LatLng(49.2827, -123.1207))
                .width(10)
        );

        Polyline mSeattleVancouver2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(47.6062, -122.3321), new LatLng(49.2827, -123.1207))
                .width(10)
        );

        Polyline mVancouverCalgary = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(49.2827, -123.1207), new LatLng(51.0486, -114.0708))
                .width(10)
        );

        Polyline mSeattleCalgary = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(47.6062, -122.3321), new LatLng(51.0486, -114.0708))
                .width(10)
        );

        Polyline mSeattleHelena = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(47.6062, -122.3321), new LatLng(46.5891, -112.0391))
                .width(10)
        );

        Polyline mPortlandSaltLake = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(45.5155, -122.6793), new LatLng(40.7608, -111.8910))
                .width(10)
        );

        Polyline mCalgaryHelena = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(51.0486, -114.0708), new LatLng(46.5891, -112.0391))
                .width(10)
        );

        Polyline mSaltLakeHelena = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(40.7608, -111.8910), new LatLng(46.5891, -112.0391))
                .width(10)
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
