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
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.UUID;
import java.util.List;

import androidteam.cs340.tickettoride.Client.Phase2Facade;
import androidteam.cs340.tickettoride.Client.Presenters.IPresenter;
import androidteam.cs340.tickettoride.Client.State.EndTurn;
import androidteam.cs340.tickettoride.Client.State.TurnState;
import androidteam.cs340.tickettoride.R;
import androidteam.cs340.tickettoride.Shared.Result;
import androidteam.cs340.tickettoride.Shared.Route;
import androidteam.cs340.tickettoride.Shared.Player;
import androidteam.cs340.tickettoride.Shared.TrainCard;

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
    List<Route> lastAvailableRoutes = new ArrayList<>(Arrays.asList(Route.values()));
    private Map< Route,Polyline> mRouteMap = new HashMap< Route,Polyline>();
    private List<TrainCard> cardsUsedToClaim = new ArrayList<>();
    private Route routeToClaim;
    private ImageView mpointsLegend;
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
        mClaimRoute.setEnabled(isMyTurn());
        mpointsLegend = mView.findViewById(R.id.points_legend);
        mpointsLegend.bringToFront();
        String[] items = new String[]{"Values"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, items);
        mRouteSpinner.setAdapter(adapter);
        mRouteSpinner.setOnItemSelectedListener(new spinnerOnClickListener());

        mClaimRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TurnState.SINGLETON.isAnythingState() && Phase2Facade.SINGLETON.isMyTurn()){
                    Result result = Phase2Facade.SINGLETON.claimRoute(routeToClaim, cardsUsedToClaim);
                    if(result.getStatusCode() == HttpURLConnection.HTTP_OK){
                        Toast.makeText(getActivity(), "Route Claimed", Toast.LENGTH_SHORT).show();
                        TurnState.SINGLETON.setState(EndTurn.SINGLETON);
                        Phase2Facade.SINGLETON.endTurn();
                    }
                    else{
                        Toast.makeText(getActivity(), result.getErrorInfo(), Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getActivity(), "Sorry, can't do that", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return mView;
    }


    /*--------
     SPINNER LOGIC
     --------*/
    Map<Route,ArrayList<String>> routeSelections = new TreeMap<>();
    public void updateSpinner(){
        int size = Phase2Facade.SINGLETON.getCurrentGame().getAvailableRoutes().size();
        ArrayList<String> items = new ArrayList<>();
        boolean update = true;

        if(lastList.size() == Phase2Facade.SINGLETON.getCurrentGame().getAvailableRoutes().size()){
            update = false;
        }else{
            lastList = Phase2Facade.SINGLETON.getCurrentGame().getAvailableRoutes();
        }

        if(update){
            // Watch for where this loop gets called if no one claims a route and the spinner doesn't change.
            // No matter what, always pass what cards they are using to claim the route to the server.
            routeSelections = createRouteSelections();
            for(Map.Entry<Route,ArrayList<String>> entry : routeSelections.entrySet()){
                for (String color : entry.getValue()) {
                    items.add(entry.getKey().name() + "_" + color);
                }
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, items);
            mRouteSpinner.setAdapter(adapter);
            mRouteSpinner.setOnItemSelectedListener(new spinnerOnClickListener());
        }
    }

    private void addCards(Route routeToClaim, int numCardColor, TrainCard toAdd) {
        if (numCardColor >= routeToClaim.length) {
            for (int j = 0; j < routeToClaim.length; j++) {
                cardsUsedToClaim.add(toAdd);
            }
        } else {
            for (int j = 0; j < numCardColor; j++) {
                cardsUsedToClaim.add(toAdd);
            }
        }
    }

    private class spinnerOnClickListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            mRouteString = (String) parent.getSelectedItem();

            if (!mRouteString.equals("Values")) {

                String[] enumWithColor = mRouteString.split("_");

                String enumRoute = enumWithColor[0] + "_" + enumWithColor[1];
                String color = enumWithColor[2];

                routeToClaim = Route.valueOf(enumRoute);
                cardsUsedToClaim = new ArrayList<>();

                switch (color) {
                    case "white":
                        addCards(routeToClaim, numWhiteCards, TrainCard.Passenger);
                        break;
                    case "orange":
                        addCards(routeToClaim, numOrangeCards, TrainCard.Freight);
                        break;
                    case "red":
                        addCards(routeToClaim, numRedCards, TrainCard.Coal);
                        break;
                    case "blue":
                        addCards(routeToClaim, numBlueCards, TrainCard.Tanker);
                        break;
                    case "green":
                        addCards(routeToClaim, numGreenCards, TrainCard.Caboose);
                        break;
                    case "pink":
                        addCards(routeToClaim, numPinkCards, TrainCard.Box);
                        break;
                    case "black":
                        addCards(routeToClaim, numBlackCards, TrainCard.Hopper);
                        break;
                    case "yellow":
                        addCards(routeToClaim, numYellowCards, TrainCard.Reefer);
                        break;
                }

                int remainingSpaces = routeToClaim.length - cardsUsedToClaim.size();
                if (cardsUsedToClaim.size() < routeToClaim.length) {
                    for (int j = 0; j < remainingSpaces; j++) {
                        cardsUsedToClaim.add(TrainCard.Locomotive);
                    }
                }

            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    /*--------
     ROUTE CLAIMING LOGIC
     --------*/
    Map<Route,ArrayList<String>> routesToSelectFrom = new HashMap<>();
    private Map<Route,ArrayList<String>> createRouteSelections() {
        List<Route> unclaimedRoutes = Phase2Facade.SINGLETON.getCurrentGame().getAvailableRoutes();
        updateCurrentCardCount(Phase2Facade.SINGLETON.getMyDeck());
        for (Route route : unclaimedRoutes) {
            // If addClaimRoute returns null, it means the route can't be claimed with the player's
            // current cards.
            if (addClaimRouteOption(route) != null) {
                if (route.color.equals("grey")) {
                    routesToSelectFrom.put(route, new ArrayList<String>());
                    for (String option : addClaimGreyRouteOption(route.length)) {
                        routesToSelectFrom.get(route).add(option);
                    }
                } else {
                    routesToSelectFrom.put(route, new ArrayList<String>());
                    routesToSelectFrom.get(route).add(addClaimRouteOption(route));
                }
            }
        }
        return routesToSelectFrom;
    }

    int numOrangeCards;
    int numWhiteCards;
    int numRedCards;
    int numWildCards;
    int numBlueCards;
    int numGreenCards;
    int numPinkCards;
    int numBlackCards;
    int numYellowCards;

    private String addClaimRouteOption(Route routeToCheck) {
        int routeLength = routeToCheck.length;
        String color = routeToCheck.color;

        switch(color) {
            case "white":
                if (numWhiteCards + numWildCards >= routeLength) {
                    return "white";
                } else { break; }
            case "orange":
                if (numOrangeCards + numWildCards >= routeLength) {
                    return "orange";
                } else { break; }
            case "red":
                if (numRedCards + numWildCards >= routeLength) {
                    return "red";
                } else { break;}
            case "blue":
                if (numBlueCards + numWildCards >= routeLength) {
                    return "blue";
                } else { break;}
            case "green":
                if (numGreenCards + numWildCards >= routeLength) {
                    return "green";
                } else { break;}
            case "pink":
                if (numPinkCards + numWildCards >= routeLength) {
                    return "pink";
                } else { break;}
            case "black":
                if (numBlackCards + numWildCards >= routeLength) {
                    return "black";
                } else { break;}
            case "yellow":
                if (numYellowCards + numWildCards >= routeLength) {
                    return "yellow";
                } else { break;}
            case "grey":
                return "";
        }

        if (numWildCards >= routeLength) {
            return "wild";
        }

        return null;

    }

    private ArrayList<String> addClaimGreyRouteOption(int routeLength) {
        ArrayList<String> greyRouteOptions = new ArrayList<>();
        if (numWhiteCards + numWildCards >= routeLength) {
            greyRouteOptions.add("white");
        }
        if (numOrangeCards + numWildCards >= routeLength) {
            greyRouteOptions.add("orange");
        }
        if (numRedCards + numWildCards >= routeLength) {
            greyRouteOptions.add("red");
        }
        if (numBlueCards + numWildCards >= routeLength) {
            greyRouteOptions.add("blue");
        }
        if (numGreenCards + numWildCards >= routeLength) {
            greyRouteOptions.add("green");
        }
        if (numPinkCards + numWildCards >= routeLength) {
            greyRouteOptions.add("pink");
        }
        if (numBlackCards + numWildCards >= routeLength) {
            greyRouteOptions.add("black");
        }
        if (numYellowCards + numWildCards >= routeLength) {
            greyRouteOptions.add("yellow");
        }
        if (numWildCards >= routeLength) {
            greyRouteOptions.add("wild");
        }
        return greyRouteOptions;
    }

    private void updateCurrentCardCount(List<TrainCard> cards){
        numOrangeCards = 0;
        numWhiteCards = 0;
        numRedCards = 0;
        numWildCards = 0;
        numBlueCards = 0;
        numGreenCards = 0;
        numPinkCards = 0;
        numBlackCards = 0;
        numYellowCards = 0;
        for(TrainCard card : cards){
            if(card.color.equals("black")){
                numBlackCards = numBlackCards + 1;
            }
            if(card.color.equals("white")){
                numWhiteCards = numWhiteCards + 1;
            }
            if(card.color.equals("blue")){
                numBlueCards = numBlueCards + 1;
            }
            if(card.color.equals("orange")){
                numOrangeCards = numOrangeCards + 1;
            }
            if(card.color.equals("red")){
                numRedCards = numRedCards + 1;
            }
            if(card.color.equals("wild")){
                numWildCards = numWildCards + 1;
            }
            if(card.color.equals("yellow")){
                numYellowCards = numYellowCards + 1;
            }
            if(card.color.equals("green")){
                numGreenCards = numGreenCards + 1;
            }
            if(card.color.equals("pink")){
                numPinkCards = numPinkCards + 1;
            }
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

    public boolean isMyTurn(){
        if(Phase2Facade.SINGLETON.getCurrentPlayer().getUID().equals(Phase2Facade.SINGLETON.getCurrentGame().getPlayerTurn())){
            return true;
        }else{
            return false;
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
        if (lastAvailableRoutes != Phase2Facade.SINGLETON.getCurrentGame().getAvailableRoutes()){
            for(Player player : Phase2Facade.SINGLETON.getCurrentGame().getPlayersList()){
                for(Route route : player.getClaimedRoutes()){
                    if(player.getColor().equals("red")){
                        mRouteMap.get(route).setColor(Color.rgb(255,0,0));
                    }
                    if(player.getColor().equals("blue")){
                        mRouteMap.get(route).setColor(Color.rgb(0,255,255));
                    }
                    if(player.getColor().equals("green")){
                        mRouteMap.get(route).setColor(Color.rgb(0,255,0));
                    }
                    if(player.getColor().equals("yellow")){
                        mRouteMap.get(route).setColor(Color.rgb(255,255,0));
                    }
                    if(player.getColor().equals("black")){
                        mRouteMap.get(route).setColor(Color.rgb(0,0,0));
                    }
                }
            }
            lastAvailableRoutes = Phase2Facade.SINGLETON.getCurrentGame().getAvailableRoutes();
        }
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
//        LatLng SeattlePortland = new LatLng(46.744096, -124.340318);
//        GroundOverlayOptions sp = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.onewhite))
//                .position(SeattlePortland, 135000f, 135000f);
//        googleMap.addGroundOverlay(sp);

        mSeattlePortland2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(47.603877, -122.965880), new LatLng(45.581488, -123.369117))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Portland_Seattle_2,mSeattlePortland2);
//        LatLng SeattlePortland2 = new LatLng(46.457254, -121.397269);
//        GroundOverlayOptions sp2 = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.onewhite))
//                .position(SeattlePortland2, 135000f, 135000f);
//        googleMap.addGroundOverlay(sp2);

        mSeattleVancouver = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(47.6062, -122.3321), new LatLng(49.2827, -123.1207))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Seattle_Vancouver,mSeattleVancouver);
//        LatLng SeattleVancouver = new LatLng(48.189723, -124.259329);
//        GroundOverlayOptions sv = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.onewhite))
//                .position(SeattleVancouver, 135000f, 135000f);
//        googleMap.addGroundOverlay(sv);

        mSeattleVancouver2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(47.603877, -122.965880), new LatLng(49.227659, -123.583162))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Seattle_Vancouver_2,mSeattleVancouver2);
//        LatLng SeattleVancouver2 = new LatLng(48.733097, -121.643220);
//        GroundOverlayOptions sv2 = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.onewhite))
//                .position(SeattleVancouver2, 135000f, 135000f);
//        googleMap.addGroundOverlay(sv2);

        mVancouverCalgary = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(49.2827, -123.1207), new LatLng(51.0486, -114.0708))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Vancouver_Calgary,mVancouverCalgary);
//        LatLng VancouverCalgary = new LatLng(51.026983, -118.996176);
//        GroundOverlayOptions vc = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.threewhite))
//                .position(VancouverCalgary, 135000f, 135000f);
//        googleMap.addGroundOverlay(vc);

        mSeattleCalgary = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(47.6062, -122.3321), new LatLng(51.0486, -114.0708))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Seattle_Calgary,mSeattleCalgary);
//        LatLng SeattleCalgary = new LatLng(48.856358, -117.488752);
//        GroundOverlayOptions seacal = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.fourwhite))
//                .position(SeattleCalgary, 135000f, 135000f);
//        googleMap.addGroundOverlay(seacal);

        mSeattleHellena = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(47.6062, -122.3321), new LatLng(46.5891, -112.0391))
                .width(10)
                .color(Color.rgb(234, 222, 45))
        );
        mRouteMap.put(Route.Seattle_Hellena,mSeattleHellena);
//        LatLng SeattleHellena = new LatLng(46.253710, -116.906759);
//        GroundOverlayOptions seahel = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.sixwhite))
//                .position(SeattleHellena, 135000f, 135000f);
//        googleMap.addGroundOverlay(seahel);

        mPortlandSaltLake = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(45.5155, -122.6793), new LatLng(40.7608, -111.8910))
                .width(10)
                .color(Color.parseColor("blue"))
        );
        mRouteMap.put(Route.Portland_SaltLakeCity,mPortlandSaltLake);
//        LatLng PortlandSaltLake = new LatLng(43.786215, -116.101161);
//        GroundOverlayOptions portSalt = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.sixwhite))
//                .position(PortlandSaltLake, 135000f, 135000f);
//        googleMap.addGroundOverlay(portSalt);

        mSaltLakeHelena = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(40.7608, -111.8910), new LatLng(46.5891, -112.0391))
                .width(10)
                .color(Color.rgb(255,0,255))//pink
        );
        mRouteMap.put(Route.SaltLakeCity_Hellena,mSaltLakeHelena);
//        LatLng SaltLakeHelena = new LatLng(44.198513, -113.216691);
//        GroundOverlayOptions salHel = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.threewhite))
//                .position(SaltLakeHelena, 135000f, 135000f);
//        googleMap.addGroundOverlay(salHel);

        mPortlandSanFrancisco = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(45.5155, -122.6793), new LatLng(37.7749, -122.4194))
                .width(8)
                .color(Color.parseColor("green"))
        );
        mRouteMap.put(Route.SanFrancisco_Portland,mPortlandSanFrancisco);
//        LatLng PortlandSanFran = new LatLng(41.153325, -124.433904);
//        GroundOverlayOptions portSan = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.fivewhite))
//                .position(PortlandSanFran, 135000f, 135000f);
//        googleMap.addGroundOverlay(portSan);

        mPortlandSanFrancisco2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(45.581488, -123.369117), new LatLng(37.730641, -122.864849))
                .width(8)
                .color(Color.rgb(255,0,255))//pink
        );
        mRouteMap.put(Route.SanFrancisco_Portland_2,mPortlandSanFrancisco2);
//        LatLng PortlandSanFran2 = new LatLng(41.582066, -121.095521);
//        GroundOverlayOptions portSan2 = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.fivewhite))
//                .position(PortlandSanFran2, 135000f, 135000f);
//        googleMap.addGroundOverlay(portSan2);

        mSanFranciscoSaltLake = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(37.7749, -122.4194), new LatLng(40.7608, -111.8910))
                .width(8)
                .color(Color.parseColor("white"))
        );
        mRouteMap.put(Route.SanFrancisco_SaltLakeCity,mSanFranciscoSaltLake);
//        LatLng SanSaltLake = new LatLng(38.585375, -116.505781);
//        GroundOverlayOptions sanSalt = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.fivewhite))
//                .position(SanSaltLake, 135000f, 135000f);
//        googleMap.addGroundOverlay(sanSalt);

        mSanFranciscoSaltLake2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(38.086034, -122.458533), new LatLng(41.021161, -112.098136))
                .width(8)
                .color(Color.rgb(255,165,0))//orange
        );
        mRouteMap.put(Route.SanFrancisco_SaltLakeCity_2,mSanFranciscoSaltLake2);
//        LatLng SanSaltLake2 = new LatLng(40.619201, -117.252524);
//        GroundOverlayOptions sanSalt2 = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.fivewhite))
//                .position(SanSaltLake2, 135000f, 135000f);
//        googleMap.addGroundOverlay(sanSalt2);

        mSanFranciscoLosAngeles = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(37.7749, -122.4194), new LatLng(34.0522, -118.2437))
                .width(8)
                .color(Color.rgb(234, 222, 45))
        );
        mRouteMap.put(Route.LosAngeles_SanFrancisco,mSanFranciscoLosAngeles);
//        LatLng SanFranLosAngeles = new LatLng(35.100249, -121.031027);
//        GroundOverlayOptions sanLosAng = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.threewhite))
//                .position(SanFranLosAngeles, 135000f, 135000f);
//        googleMap.addGroundOverlay(sanLosAng);

        mSanFranciscoLosAngeles2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(37.730641, -122.864849), new LatLng(33.856838, -118.417167))
                .width(8)
                .color(Color.rgb(255,0,255))//pink
        );
        mRouteMap.put(Route.LosAngeles_SanFrancisco_2,mSanFranciscoLosAngeles2);
//        LatLng SanFranLosAngeles2 = new LatLng(36.171694, -119.493613);
//        GroundOverlayOptions sanLosAng2 = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.threewhite))
//                .position(SanFranLosAngeles2, 135000f, 135000f);
//        googleMap.addGroundOverlay(sanLosAng2);

        mLosAngelesLasVegas = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(34.0522, -118.2437), new LatLng(36.1699, -115.1398))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.LosAngeles_LasVegas,mLosAngelesLasVegas);
//        LatLng angelesVegas = new LatLng(35.423189, -117.429087);
//        GroundOverlayOptions angVeg = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
//                .position(angelesVegas, 135000f, 135000f);
//        googleMap.addGroundOverlay(angVeg);

        mLasVegasSaltLake = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(36.1699, -115.1398), new LatLng(40.7608, -111.8910))
                .width(10)
                .color(Color.rgb(255,165,0))//orange
        );
        mRouteMap.put(Route.SaltLakeCity_LasVegas,mLasVegasSaltLake);
//        LatLng VegasSaltLake = new LatLng(37.558595, -113.033217);
//        GroundOverlayOptions vegSalt = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.threewhite))
//                .position(VegasSaltLake, 135000f, 135000f);
//        googleMap.addGroundOverlay(vegSalt);

        mLosAngelesPhoenix = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(34.0522, -118.2437), new LatLng(33.4484, -112.0740))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.LosAngeles_Phoenix,mLosAngelesPhoenix);
//        LatLng LosAngelesPhoenix = new LatLng(34.353640, -114.905052);
//        GroundOverlayOptions AngelPho = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.threewhite))
//                .position(LosAngelesPhoenix, 135000f, 135000f);
//        googleMap.addGroundOverlay(AngelPho);

        mLosAngelesElPaso = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(34.0522, -118.2437), new LatLng(31.7619, -106.4850))
                .width(10)
                .color(Color.parseColor("black"))
        );
        mRouteMap.put(Route.ElPaso_LosAngeles,mLosAngelesElPaso);
//        LatLng LosAngelesPaso = new LatLng(32.104680, -113.204485);
//        GroundOverlayOptions AngelPaso = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.sixwhite))
//                .position(LosAngelesPaso, 135000f, 135000f);
//        googleMap.addGroundOverlay(AngelPaso);

        mPhoenixElPaso = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(33.4484, -112.0740), new LatLng(31.7619, -106.4850))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.ElPaso_Phoenix,mPhoenixElPaso);
//        LatLng PhoenixPaso = new LatLng(33.030542, -108.724023);
//        GroundOverlayOptions PhoPaso = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.threewhite))
//                .position(PhoenixPaso, 135000f, 135000f);
//        googleMap.addGroundOverlay(PhoPaso);

        mPhoenixSantaFe = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(33.4484, -112.0740), new LatLng(35.6870, -105.9378))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.SantaFe_Phoenix,mPhoenixSantaFe);
//        LatLng PhoSantaFe = new LatLng(35.364976, -108.487246);
//        GroundOverlayOptions PhoFe = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.threewhite))
//                .position(PhoSantaFe, 135000f, 135000f);
//        googleMap.addGroundOverlay(PhoFe);

        mPhoenixDenver = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(33.4484, -112.0740), new LatLng(39.7392, -104.9903))
                .width(10)
                .color(Color.parseColor("white"))
        );
        mRouteMap.put(Route.Denver_Phoenix,mPhoenixDenver);
//        LatLng PhoenixDenver = new LatLng(36.889850, -109.193495);
//        GroundOverlayOptions PhoDen = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.fivewhite))
//                .position(PhoenixDenver, 135000f, 135000f);
//        googleMap.addGroundOverlay(PhoDen);

        mElPasoSantaFe = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(31.7619, -106.4850), new LatLng(35.6870, -105.9378))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.ElPaso_SantaFe,mElPasoSantaFe);
//        LatLng ElPasoSantaFe = new LatLng(33.822227, -106.946898);
//        GroundOverlayOptions PasoFe = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
//                .position(ElPasoSantaFe, 135000f, 135000f);
//        googleMap.addGroundOverlay(PasoFe);

        mSantaFeDenver = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(35.6870, -105.9378), new LatLng(39.7392, -104.9903))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Denver_SantaFe,mSantaFeDenver);
//        LatLng SantaFeDenver = new LatLng(37.286346, -106.499830);
//        GroundOverlayOptions FeDen = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
//                .position(SantaFeDenver, 135000f, 135000f);
//        googleMap.addGroundOverlay(FeDen);

        mSaltLakeDenver = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(40.7608, -111.8910), new LatLng(39.7392, -104.9903))
                .width(8)
                .color(Color.parseColor("red"))
        );
        mRouteMap.put(Route.SaltLakeCity_Denver,mSaltLakeDenver);
//        LatLng SaltLakeDenver = new LatLng(41.009439, -108.470395);
//        GroundOverlayOptions SalDen = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.threewhite))
//                .position(SaltLakeDenver, 135000f, 135000f);
//        googleMap.addGroundOverlay(SalDen);

        mSaltLakeDenver2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(41.021161, -112.098136), new LatLng(39.925305, -104.944488))
                .width(8)
                .color(Color.rgb(234, 222, 45))
        );
        mRouteMap.put(Route.SaltLakeCity_Denver_2,mSaltLakeDenver2);
//        LatLng SaltLakeDenver2 = new LatLng(39.754185, -108.733952);
//        GroundOverlayOptions SalDen2 = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.threewhite))
//                .position(SaltLakeDenver2, 135000f, 135000f);
//        googleMap.addGroundOverlay(SalDen2);

        mHelenaDenver = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(46.5891,-112.0391), new LatLng(39.7392, -104.9903))
                .width(10)
                .color(Color.parseColor("green"))
        );
        mRouteMap.put(Route.Hellena_Denver,mHelenaDenver);
//        LatLng HelenaDenver = new LatLng(44.114951, -110.582058);
//        GroundOverlayOptions HelDen = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.fourwhite))
//                .position(HelenaDenver, 135000f, 135000f);
//        googleMap.addGroundOverlay(HelDen);

        mCalgaryWinnipeg = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(51.0486,-114.0708), new LatLng(49.8951, -97.1384))
                .width(10)
                .color(Color.parseColor("white"))
        );
        mRouteMap.put(Route.Calgary_Winnipeg,mCalgaryWinnipeg);
//        LatLng CalgaryWinnipeg = new LatLng(51.096300, -104.400824);
//        GroundOverlayOptions CalWin = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.sixwhite))
//                .position(CalgaryWinnipeg, 135000f, 135000f);
//        googleMap.addGroundOverlay(CalWin);

        mHelenaWinnipeg = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(46.5891,-112.0391), new LatLng(49.8951, -97.1384))
                .width(10)
                .color(Color.parseColor("blue"))
        );
        mRouteMap.put(Route.Winnipeg_Hellena,mHelenaWinnipeg);
//        LatLng HelenaWinnipeg = new LatLng(48.855680, -105.544547);
//        GroundOverlayOptions HelWin = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.fourwhite))
//                .position(HelenaWinnipeg, 135000f, 135000f);
//        googleMap.addGroundOverlay(HelWin);

        //this was the one that was breaking
        mCalgaryHelena = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(51.0486, -114.0708), new LatLng(46.5891, -112.0391))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Calgary_Hellena,mCalgaryHelena);
//        LatLng CalgaryHelena = new LatLng(49.186809, -111.890500);
//        GroundOverlayOptions Calhel = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.fourwhite))
//                .position(CalgaryHelena, 135000f, 135000f);
//        googleMap.addGroundOverlay(Calhel);

        mHelenaDuluth = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(46.5891,-112.0391), new LatLng(46.7867, -92.1005))
                .width(10)
                .color(Color.rgb(255,165,0))//orange
        );
        mRouteMap.put(Route.Hellena_Duluth,mHelenaDuluth);
//        LatLng HelenaDuluth = new LatLng(48.070239, -100.766039);
//        GroundOverlayOptions heldul = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.sixwhite))
//                .position(HelenaDuluth, 135000f, 135000f);
//        googleMap.addGroundOverlay(heldul);

        mWinnipegDuluth = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(49.8951, -97.1384), new LatLng(46.7867, -92.1005))
                .width(10)
                .color(Color.parseColor("black"))
        );
        mRouteMap.put(Route.Winnipeg_Duluth,mWinnipegDuluth);
//        LatLng WinniDuluth = new LatLng(47.724083, -95.700811);
//        GroundOverlayOptions WinDul = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.fourwhite))
//                .position(WinniDuluth, 135000f, 135000f);
//        googleMap.addGroundOverlay(WinDul);

        mHelenaOmaha = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(46.5891,-112.0391), new LatLng(41.2565, -95.9345))
                .width(10)
                .color(Color.parseColor("red"))
        );
        mRouteMap.put(Route.Hellena_Omaha,mHelenaOmaha);
//        LatLng HelenaOmaha = new LatLng(44.494056, -102.971695);
//        GroundOverlayOptions Helo = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.fivewhite))
//                .position(HelenaOmaha, 135000f, 135000f);
//        googleMap.addGroundOverlay(Helo);

        mDenverOmaha = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(39.7392, -104.9903), new LatLng(41.2565, -95.9345))
                .width(10)
                .color(Color.rgb(255,0,255))//pink
        );
        mRouteMap.put(Route.Denver_Omaha,mDenverOmaha);
//        LatLng DenvOmaha = new LatLng(41.242965, -101.220313);
//        GroundOverlayOptions Denvo = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.fourwhite))
//                .position(DenvOmaha, 135000f, 135000f);
//        googleMap.addGroundOverlay(Denvo);

        mDuluthOmaha = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(46.7867, -92.1005), new LatLng(41.2565, -95.9345))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Duluth_Omaha,mDuluthOmaha);
//        LatLng DuluthOmaha = new LatLng(44.508381, -95.304838);
//        GroundOverlayOptions Dulo = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
//                .position(DuluthOmaha, 135000f, 135000f);
//        googleMap.addGroundOverlay(Dulo);

        mDuluthOmaha2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(46.753932, -92.484388), new LatLng(41.267743, -96.132537))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Duluth_Omaha_2,mDuluthOmaha2);
//        LatLng DuluthOmaha2 = new LatLng(44.036393, -92.471604);
//        GroundOverlayOptions Dulo2 = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
//                .position(DuluthOmaha2, 135000f, 135000f);
//        googleMap.addGroundOverlay(Dulo2);

        mDenverKansasCity = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(39.7392, -104.9903), new LatLng(39.0997, -94.5786))
                .width(8)
                .color(Color.parseColor("black"))
        );
        mRouteMap.put(Route.KansasCity_Denver,mDenverKansasCity);
//        LatLng KansasDenver = new LatLng(39.945674, -99.763766);
//        GroundOverlayOptions KanDen = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.fourwhite))
//                .position(KansasDenver, 135000f, 135000f);
//        googleMap.addGroundOverlay(KanDen);

        mDenverKansasCity2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(39.925305, -104.944488), new LatLng(39.220950, -94.558615))
                .width(8)
                .color(Color.rgb(255,165,0))//orange
        );
        mRouteMap.put(Route.KansasCity_Denver_2,mDenverKansasCity2);
//        LatLng KansasDenver2 = new LatLng(38.841981, -99.895544);
//        GroundOverlayOptions KanDen2 = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.fourwhite))
//                .position(KansasDenver2, 135000f, 135000f);
//        googleMap.addGroundOverlay(KanDen2);


        mOmahaKansasCity = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(41.2565, -95.9345), new LatLng(39.0997, -94.5786))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.KansasCity_Omaha,mOmahaKansasCity);
//        LatLng KansasOmaha = new LatLng(40.120944, -96.357091);
//        GroundOverlayOptions KanOma = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.onewhite))
//                .position(KansasOmaha, 135000f, 135000f);
//        googleMap.addGroundOverlay(KanOma);

        mOmahaKansasCity2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(41.267743, -95.714889), new LatLng(39.145305, -94.372355))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.KansasCity_Omaha_2,mOmahaKansasCity2);
//        LatLng KansasOmaha2 = new LatLng(39.973437, -94.079343);
//        GroundOverlayOptions KanOma2 = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.onewhite))
//                .position(KansasOmaha2, 135000f, 135000f);
//        googleMap.addGroundOverlay(KanOma2);

        mDenverOklahomaCity = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(39.7392, -104.9903), new LatLng(35.4676, -97.5164))
                .width(10)
                .color(Color.parseColor("red"))
        );
        mRouteMap.put(Route.Denver_OklahomaCity,mDenverOklahomaCity);
//        LatLng DenverOKC = new LatLng(37.025854, -101.397414);
//        GroundOverlayOptions DenOKC = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.fourwhite))
//                .position(DenverOKC, 135000f, 135000f);
//        googleMap.addGroundOverlay(DenOKC);

        mSantaFeOklahomaCity = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(35.6870, -105.9378), new LatLng(35.4676, -97.5164))
                .width(10)
                .color(Color.parseColor("blue"))
        );
        mRouteMap.put(Route.SantaFe_OklahomaCity,mSantaFeOklahomaCity);
//        LatLng SantaOKC = new LatLng(36.149173, -103.017789);
//        GroundOverlayOptions SanOKC = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.threewhite))
//                .position(SantaOKC, 135000f, 135000f);
//        googleMap.addGroundOverlay(SanOKC);

        mElPasoOklahomaCity = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(31.7619, -106.4850), new LatLng(35.4676, -97.5164))
                .width(10)
                .color(Color.rgb(234, 222, 45))
        );
        mRouteMap.put(Route.ElPaso_OklahomaCity,mElPasoOklahomaCity);
//        LatLng PasoOKC = new LatLng(34.144771, -101.844350);
//        GroundOverlayOptions PasOKC = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.fivewhite))
//                .position(PasoOKC, 135000f, 135000f);
//        googleMap.addGroundOverlay(PasOKC);

        mKansasCityOklahomaCity = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(39.0997, -94.5786), new LatLng(35.4676, -97.5164))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.KansasCity_OklahomaCity,mKansasCityOklahomaCity);
//        LatLng KansasOKC = new LatLng(37.559684, -96.771143);
//        GroundOverlayOptions KanOKC = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
//                .position(KansasOKC, 135000f, 135000f);
//        googleMap.addGroundOverlay(KanOKC);

        mKansasCityOklahomaCity2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(39.145305, -94.372355), new LatLng(35.404845, -97.286023))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.KansasCity_OklahomaCity_2,mKansasCityOklahomaCity2);
//        LatLng KansasOKC2 = new LatLng(37.157991, -95.277655);
//        GroundOverlayOptions KanOKC2 = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
//                .position(KansasOKC2, 135000f, 135000f);
//        googleMap.addGroundOverlay(KanOKC2);

        mOklahomaCityDallas = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(35.4676, -97.5164), new LatLng(32.7767, -96.7970))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Dallas_OklahomaCity,mOklahomaCityDallas);
//        LatLng DallasOKC = new LatLng(34.255869, -96.281435);
//        GroundOverlayOptions DakOKC = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
//                .position(DallasOKC, 135000f, 135000f);
//        googleMap.addGroundOverlay(DakOKC);

        mOklahomaCityDallas2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(35.404845, -97.286023), new LatLng(32.679479, -96.472422))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Dallas_OklahomaCity_2,mOklahomaCityDallas2);
//        LatLng DallasOKC2 = new LatLng(33.964790, -97.994553);
//        GroundOverlayOptions DakOKC2 = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
//                .position(DallasOKC2, 135000f, 135000f);
//        googleMap.addGroundOverlay(DakOKC2);

        mElPasoDallas = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(31.7619, -106.4850), new LatLng(32.7767, -96.7970))
                .width(10)
                .color(Color.parseColor("red"))
        );
        mRouteMap.put(Route.ElPaso_Dallas,mElPasoDallas);
//        LatLng PasoDallas = new LatLng(32.714511, -101.748605);
//        GroundOverlayOptions PalDal = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.fourwhite))
//                .position(PasoDallas, 135000f, 135000f);
//        googleMap.addGroundOverlay(PalDal);

        mDallasHouston = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(32.7767, -96.7970), new LatLng(29.7604, -95.3698))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Houston_Dallas,mDallasHouston);
//        LatLng DallasHouston = new LatLng(31.178074, -97.044137);
//        GroundOverlayOptions DalHous = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.onewhite))
//                .position(DallasHouston, 135000f, 135000f);
//        googleMap.addGroundOverlay(DalHous);

        mDallasHouston2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(32.679479, -96.472422), new LatLng(29.782332, -95.034271))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Houston_Dallas_2,mDallasHouston2);
//        LatLng DallasHouston2 = new LatLng(31.609461, -95.484760);
//        GroundOverlayOptions DalHous2 = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.onewhite))
//                .position(DallasHouston2, 135000f, 135000f);
//        googleMap.addGroundOverlay(DalHous2);

        mElPasoHouston = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(31.7619, -106.4850), new LatLng(29.7604, -95.3698))
                .width(10)
                .color(Color.parseColor("green"))
        );
        mRouteMap.put(Route.Houston_ElPaso,mElPasoHouston);
//        LatLng PasoHouston = new LatLng(29.904502, -100.758637);
//        GroundOverlayOptions PasHous = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.sixwhite))
//                .position(PasoHouston, 135000f, 135000f);
//        googleMap.addGroundOverlay(PasHous);

        mHoustonNewOrleans = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(29.7604, -95.3698), new LatLng(29.9511, -90.0715))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.NewOrleans_Houston,mHoustonNewOrleans);
//        LatLng HoustonNew = new LatLng(29.476367, -92.322545);
//        GroundOverlayOptions NewHous = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
//                .position(HoustonNew, 135000f, 135000f);
//        googleMap.addGroundOverlay(NewHous);

        mLittleRockNewOrleans = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(34.7465, -92.2896), new LatLng(29.9511, -90.0715))
                .width(10)
                .color(Color.parseColor("green"))
        );
        mRouteMap.put(Route.NewOrleans_LittleRock,mLittleRockNewOrleans);
//        LatLng LittleOrleans = new LatLng(32.176339, -92.144558);
//        GroundOverlayOptions Newlittle = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.threewhite))
//                .position(LittleOrleans, 135000f, 135000f);
//        googleMap.addGroundOverlay(Newlittle);

        mDallasLittleRock = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(32.7767, -96.7970), new LatLng(34.7465, -92.2896))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.LittleRock_Dallas,mDallasLittleRock);
//        LatLng DallasLittle = new LatLng(33.181023, -94.027375);
//        GroundOverlayOptions DalLit = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
//                .position(DallasLittle, 135000f, 135000f);
//        googleMap.addGroundOverlay(DalLit);

        mOklahomaCityLittleRock = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(35.4676, -97.5164), new LatLng(34.7465, -92.2896))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.LittleRock_OklahomaCity,mOklahomaCityLittleRock);
//        LatLng LittleOKC = new LatLng(35.506658, -94.468180);
//        GroundOverlayOptions LitOKC = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
//                .position(LittleOKC, 135000f, 135000f);
//        googleMap.addGroundOverlay(LitOKC);

        mSaintLouislittleRock = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(38.6270, -90.1994), new LatLng(34.7465, -92.2896))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.SaintLouis_LittleRock,mSaintLouislittleRock);
//        LatLng SaintLittle = new LatLng(36.978352, -92.023332);
//        GroundOverlayOptions SaLit = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
//                .position(SaintLittle, 135000f, 135000f);
//        googleMap.addGroundOverlay(SaLit);

        mKansasCitySaintLouis = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(39.0997, -94.5786), new LatLng(38.6270, -90.1994))
                .width(8)
                .color(Color.parseColor("blue"))
        );
        mRouteMap.put(Route.SaintLouis_KansasCity,mKansasCitySaintLouis);
//        LatLng KCStLouis = new LatLng(38.383449, -92.396790);
//        GroundOverlayOptions KCSTL = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
//                .position(KCStLouis, 135000f, 135000f);
//        googleMap.addGroundOverlay(KCSTL);

        mKansasCitySaintLouis2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(39.287678, -94.561266), new LatLng(38.827004, -90.190619))
                .width(8)
                .color(Color.rgb(255,0,255))//pink
        );
        mRouteMap.put(Route.SaintLouis_KansasCity_2,mKansasCitySaintLouis2);
//        LatLng KCStLouis2 = new LatLng(39.881549, -92.288711);
//        GroundOverlayOptions KCSTL2 = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
//                .position(KCStLouis2, 135000f, 135000f);
//        googleMap.addGroundOverlay(KCSTL2);

        mSaintLouisChicago = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(38.6270, -90.1994), new LatLng(41.8781, -87.6298))
                .width(8)
                .color(Color.parseColor("green"))
        );
        mRouteMap.put(Route.Chicago_SaintLouis,mSaintLouisChicago);
//        LatLng STLChicago = new LatLng(40.512755, -89.463971);
//        GroundOverlayOptions STLchic = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
//                .position(STLChicago, 135000f, 135000f);
//        googleMap.addGroundOverlay(STLchic);

        mSaintLouisChicago2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(38.827004, -90.190619), new LatLng(41.900680, -87.984877))
                .width(8)
                .color(Color.parseColor("white"))
        );
        mRouteMap.put(Route.Chicago_SaintLouis_2,mSaintLouisChicago2);
//        LatLng STLChicago2 = new LatLng(40.177824, -88.365818);
//        GroundOverlayOptions STLchic2 = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
//                .position(STLChicago2, 135000f, 135000f);
//        googleMap.addGroundOverlay(STLchic2);

        mOmahaChicago = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(41.2565, -95.9345), new LatLng(41.8781, -87.6298))
                .width(10)
                .color(Color.parseColor("blue"))
        );
        mRouteMap.put(Route.Chicago_Omaha,mOmahaChicago);
//        LatLng OmahaChicago = new LatLng(42.196923, -91.736908);
//        GroundOverlayOptions OmaChic = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.fourwhite))
//                .position(OmahaChicago, 135000f, 135000f);
//        googleMap.addGroundOverlay(OmaChic);

        mDuluthChicago = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(46.7867, -92.1005), new LatLng(41.8781, -87.6298))
                .width(10)
                .color(Color.parseColor("red"))
        );
        mRouteMap.put(Route.Duluth_Chicago,mDuluthChicago);
//        LatLng DuluthChicago = new LatLng(44.467172, -88.713042);
//        GroundOverlayOptions Dulchic = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.threewhite))
//                .position(DuluthChicago, 135000f, 135000f);
//        googleMap.addGroundOverlay(Dulchic);

        mChicagoToronto = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(41.8781, -87.6298), new LatLng(43.6532, -79.3832))
                .width(10)
                .color(Color.parseColor("white"))
        );
        mRouteMap.put(Route.Chicago_Toronto,mChicagoToronto);
//        LatLng ChicagoToronto = new LatLng(43.147090, -84.387007);
//        GroundOverlayOptions ChicTor = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.fourwhite))
//                .position(ChicagoToronto, 135000f, 135000f);
//        googleMap.addGroundOverlay(ChicTor);

        mDuluthToronto = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(46.7867, -92.1005), new LatLng(43.6532, -79.3832))
                .width(10)
                .color(Color.rgb(255,0,255))//pink
        );
        mRouteMap.put(Route.Duluth_Toronto,mDuluthToronto);
//        LatLng DuluthToronto = new LatLng(44.795175, -86.674253);
//        GroundOverlayOptions DulTor = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.sixwhite))
//                .position(DuluthToronto, 135000f, 135000f);
//        googleMap.addGroundOverlay(DulTor);

        mDuluthStMarie = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(46.7867, -92.1005), new LatLng(46.5136, -84.3358))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.SaultSteMarie_Duluth,mDuluthStMarie);
//        LatLng SaultDuluth = new LatLng(47.053107, -90.303475);
//        GroundOverlayOptions DulSal = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.threewhite))
//                .position(SaultDuluth, 135000f, 135000f);
//        googleMap.addGroundOverlay(DulSal);

        mWinnipegStMarie = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(49.8951, -97.1384), new LatLng(46.5136, -84.3358))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Winnipeg_SaultSteMarie,mWinnipegStMarie);
//        LatLng WinnipegSault = new LatLng(48.686654, -89.581183);
//        GroundOverlayOptions WinniSal = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.sixwhite))
//                .position(WinnipegSault, 135000f, 135000f);
//        googleMap.addGroundOverlay(WinniSal);

        mStMarieToronto = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(46.5136, -84.3358), new LatLng(43.6532, -79.3832))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.SaultSteMarie_Toronto,mStMarieToronto);
//        LatLng SaultToronto = new LatLng(45.019731, -80.431024);
//        GroundOverlayOptions SalTor = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
//                .position(SaultToronto, 135000f, 135000f);
//        googleMap.addGroundOverlay(SalTor);

        mStMarieMontreal = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(46.5136, -84.3358), new LatLng(45.5017, -73.5673))
                .width(10)
                .color(Color.parseColor("black"))
        );
        mRouteMap.put(Route.SaultSteMarie_Montreal,mStMarieMontreal);
//        LatLng SaultMontreal = new LatLng(46.458733, -78.230000);
//        GroundOverlayOptions Salmon = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.fivewhite))
//                .position(SaultMontreal, 135000f, 135000f);
//        googleMap.addGroundOverlay(Salmon);

        mMontrealBoston = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(45.5017, -73.5673), new LatLng(42.3601, -71.0589))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Montreal_Boston,mMontrealBoston);
//        LatLng MontrealBoston = new LatLng(44.065633, -71.751433);
//        GroundOverlayOptions BosMon = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
//                .position(MontrealBoston, 135000f, 135000f);
//        googleMap.addGroundOverlay(BosMon);

        mMontrealBoston2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(45.568120, -73.520557), new LatLng(42.439247, -70.906324))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Montreal_Boston_2,mMontrealBoston2);
//        LatLng MontrealBoston2 = new LatLng(43.828335, -72.783697);
//        GroundOverlayOptions BosMon2 = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
//                .position(MontrealBoston2, 135000f, 135000f);
//        googleMap.addGroundOverlay(BosMon2);

        mMontrealNewYork = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(45.5017, -73.5673), new LatLng(40.7128, -74.0060))
                .width(10)
                .color(Color.parseColor("blue"))
        );
        mRouteMap.put(Route.Montreal_NewYork,mMontrealNewYork);
//        LatLng MontrealNew = new LatLng(43.173386, -74.409649);
//        GroundOverlayOptions Newmon = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.threewhite))
//                .position(MontrealNew, 135000f, 135000f);
//        googleMap.addGroundOverlay(Newmon);

        mTorontoMontreal = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(43.6532, -79.3832), new LatLng(45.5017, -73.5673))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Montreal_Toronto,mTorontoMontreal);
//        LatLng TorontoMontreal = new LatLng(44.005163, -76.029810);
//        GroundOverlayOptions Tormon = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.threewhite))
//                .position(TorontoMontreal, 135000f, 135000f);
//        googleMap.addGroundOverlay(Tormon);

        mTorontoPittsburg = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(43.6532, -79.3832), new LatLng(40.4406, -79.9959))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Pittsburgh_Toronto,mTorontoPittsburg);
//        LatLng TorontoPitt = new LatLng(41.779761, -78.868191);
//        GroundOverlayOptions pittor = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
//                .position(TorontoPitt, 135000f, 135000f);
//        googleMap.addGroundOverlay(pittor);

        mChicagoPittsburg = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(41.8781, -87.6298), new LatLng(40.4406, -79.9959))
                .width(8)
                .color(Color.parseColor("black"))
        );
        mRouteMap.put(Route.Pittsburgh_Chicago,mChicagoPittsburg);
//        LatLng ChicagoPitt = new LatLng(41.800495, -83.529934);
//        GroundOverlayOptions ChicPitt = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.threewhite))
//                .position(ChicagoPitt, 135000f, 135000f);
//        googleMap.addGroundOverlay(ChicPitt);

        mChicagoPittsburg2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(42.014866, -87.551682), new LatLng(40.519704, -79.981175))
                .width(8)
                .color(Color.rgb(255,165,0))//orange
        );
        mRouteMap.put(Route.Pittsburgh_Chicago_2,mChicagoPittsburg2);
//        LatLng ChicagoPitt2 = new LatLng(40.599095, -83.771527);
//        GroundOverlayOptions ChicPitt2 = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.threewhite))
//                .position(ChicagoPitt2, 135000f, 135000f);
//        googleMap.addGroundOverlay(ChicPitt2);

        mSaintLouisPittsburg = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(38.6270, -90.1994), new LatLng(40.4406, -79.9959))
                .width(10)
                .color(Color.parseColor("green"))
        );
        mRouteMap.put(Route.Pittsburgh_SaintLouis,mSaintLouisPittsburg);
//        LatLng SaintPittsburg = new LatLng(39.955083, -84.584687);
//        GroundOverlayOptions StlPitts = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.fivewhite))
//                .position(SaintPittsburg, 135000f, 135000f);
//        googleMap.addGroundOverlay(StlPitts);

        mBostonNewYork = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(42.3601, -71.0589), new LatLng(40.7128, -74.0060))
                .width(8)
                .color(Color.parseColor("red"))
        );
        mRouteMap.put(Route.Boston_NewYork,mBostonNewYork);
//        LatLng BostonNew = new LatLng(41.712392, -73.092864);
//        GroundOverlayOptions BosNew = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
//                .position(BostonNew, 135000f, 135000f);
//        googleMap.addGroundOverlay(BosNew);

        mBostonNewYork2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(42.439247, -70.906324), new LatLng(40.525022, -73.717557))
                .width(8)
                .color(Color.rgb(234, 222, 45))
        );
        mRouteMap.put(Route.Boston_NewYork_2,mBostonNewYork2);
//        LatLng BostonNew2 = new LatLng(41.102639, -72.060601);
//        GroundOverlayOptions BosNew2 = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
//                .position(BostonNew2, 135000f, 135000f);
//        googleMap.addGroundOverlay(BosNew2);

        mNewYorkPittsburg = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(40.7128, -74.0060), new LatLng(40.4406, -79.9959))
                .width(8)
                .color(Color.parseColor("white"))
        );
        mRouteMap.put(Route.NewYork_Pittsburgh,mNewYorkPittsburg);
//        LatLng NewPittsburg = new LatLng(40.853942, -77.144875);
//        GroundOverlayOptions NewPitt = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
//                .position(NewPittsburg, 135000f, 135000f);
//        googleMap.addGroundOverlay(NewPitt);

        mNewYorkPittsburg2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(40.493568, -73.993039), new LatLng(40.259224, -79.923062))
                .width(8)
                .color(Color.parseColor("green"))
        );
        mRouteMap.put(Route.NewYork_Pittsburgh_2,mNewYorkPittsburg2);
//        LatLng NewPittsburg2 = new LatLng(39.883001, -77.035060);
//        GroundOverlayOptions NewPitt2 = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
//                .position(NewPittsburg2, 135000f, 135000f);
//        googleMap.addGroundOverlay(NewPitt2);

        mPittsburgWashington = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(40.4406, -79.9959), new LatLng(38.9072, -77.0369))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Washington_Pittsburgh,mPittsburgWashington);
//        LatLng WashingtonPitt = new LatLng(39.299967, -78.835344);
//        GroundOverlayOptions pittWash = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
//                .position(WashingtonPitt, 135000f, 135000f);
//        googleMap.addGroundOverlay(pittWash);

        mNewYorkWashington = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(40.7128, -74.0060), new LatLng(38.9072, -77.0369))
                .width(8)
                .color(Color.parseColor("black"))
        );
        mRouteMap.put(Route.NewYork_Washington,mNewYorkWashington);
//        LatLng NewWashington = new LatLng(39.353414, -75.048005);
//        GroundOverlayOptions newwash = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
//                .position(NewWashington, 135000f, 135000f);
//        googleMap.addGroundOverlay(newwash);

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
//        LatLng WashingtonRaleigh = new LatLng(37.298295, -76.935712);
//        GroundOverlayOptions WashRal = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
//                .position(WashingtonRaleigh, 135000f, 135000f);
//        googleMap.addGroundOverlay(WashRal);


        mWashingtonRaleigh2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(38.720073, -76.775483), new LatLng(35.677448, -78.409070))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Washington_Raleigh_2,mWashingtonRaleigh2);
//        LatLng WashingtonRaleigh2 = new LatLng(37.612260, -78.121716);
//        GroundOverlayOptions WashRal2 = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
//                .position(WashingtonRaleigh2, 135000f, 135000f);
//        googleMap.addGroundOverlay(WashRal2);

        mPittsburgRaleigh = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(40.4406, -79.9959), new LatLng(35.7796, -78.6382))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Pittsburgh_Raleigh,mPittsburgRaleigh);
//        LatLng PittRaleigh = new LatLng(37.967681, -80.357915);
//        GroundOverlayOptions PittRal = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
//                .position(PittRaleigh, 135000f, 135000f);
//        googleMap.addGroundOverlay(PittRal);

        mNashvilleRaleigh = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(36.1627, -86.7816), new LatLng(35.7796, -78.6382))
                .width(10)
                .color(Color.parseColor("black"))
        );
        mRouteMap.put(Route.Raleigh_Nashville,mNashvilleRaleigh);
//        LatLng NashRaleigh = new LatLng(36.406087, -82.729925);
//        GroundOverlayOptions Nral = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.threewhite))
//                .position(NashRaleigh, 135000f, 135000f);
//        googleMap.addGroundOverlay(Nral);

        mSaintLouisNashville = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(38.6270, -90.1994), new LatLng(36.1627, -86.7816))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.SaintLouis_Nashville,mSaintLouisNashville);
//        LatLng STLNash = new LatLng(37.815751, -87.790777);
//        GroundOverlayOptions Snash = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
//                .position(STLNash, 135000f, 135000f);
//        googleMap.addGroundOverlay(Snash);

        mPittsburgNashville = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(40.4406, -79.9959), new LatLng(36.1627, -86.7816))
                .width(10)
                .color(Color.rgb(234, 222, 45))
        );
        mRouteMap.put(Route.Pittsburgh_Nashville,mPittsburgNashville);
//        LatLng PittNash = new LatLng(38.131789, -82.288690);
//        GroundOverlayOptions Pnash = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.fourwhite))
//                .position(PittNash, 135000f, 135000f);
//        googleMap.addGroundOverlay(Pnash);

        mLittleRockNashville = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(34.7465, -92.2896), new LatLng(36.1627, -86.7816))
                .width(10)
                .color(Color.parseColor("white"))
        );
        mRouteMap.put(Route.Nashville_LittleRock,mLittleRockNashville);
//        LatLng LittleNashville = new LatLng(34.952649, -89.102898);
//        GroundOverlayOptions LilNash = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.threewhite))
//                .position(LittleNashville, 135000f, 135000f);
//        googleMap.addGroundOverlay(LilNash);

        mNashvilleAtlanta = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(36.1627, -86.7816), new LatLng(33.7490, -84.3880))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Nashville_Atlanta,mNashvilleAtlanta);
//        LatLng NashAtlanta = new LatLng(34.789629, -86.242297);
//        GroundOverlayOptions NashAtl = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.onewhite))
//                .position(NashAtlanta, 135000f, 135000f);
//        googleMap.addGroundOverlay(NashAtl);

        mRaleighAtlanta = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(35.7796, -78.6382), new LatLng(33.7490, -84.3880))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Raleigh_Atlanta,mRaleighAtlanta);
//        LatLng RaleighAtl = new LatLng(34.775389, -83.131351);
//        GroundOverlayOptions RalAtl = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
//                .position(RaleighAtl, 135000f, 135000f);
//        googleMap.addGroundOverlay(RalAtl);

        mRaleighAtlanta2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(35.677448, -78.409070), new LatLng(33.577872, -84.164505))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Raleigh_Atlanta_2,mRaleighAtlanta2);
//        LatLng RaleighAtl2 = new LatLng(34.304804, -81.176639);
//        GroundOverlayOptions RalAtl2 = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
//                .position(RaleighAtl2, 135000f, 135000f);
//        googleMap.addGroundOverlay(RalAtl2);

        mNewOrleansAtlanta = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(29.9511, -90.0715), new LatLng(33.7490, -84.3880))
                .width(8)
                .color(Color.rgb(234, 222, 45))
        );
        mRouteMap.put(Route.NewOrleans_Atlanta,mNewOrleansAtlanta);
//        LatLng NewAtlanta = new LatLng(32.318908, -87.585217);
//        GroundOverlayOptions NewAtl = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.fourwhite))
//                .position(NewAtlanta, 135000f, 135000f);
//        googleMap.addGroundOverlay(NewAtl);

        mNewOrleansAtlanta2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(29.792165, -89.925687), new LatLng(33.577872, -84.164505))
                .width(8)
                .color(Color.rgb(255,165,0))//orange
        );
        mRouteMap.put(Route.NewOrleans_Atlanta_2,mNewOrleansAtlanta2);
//        LatLng NewAtlanta2 = new LatLng(31.591843, -86.443138);
//        GroundOverlayOptions NewAtl2 = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.fourwhite))
//                .position(NewAtlanta2, 135000f, 135000f);
//        googleMap.addGroundOverlay(NewAtl2);

        mRaleighCharleston = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(35.7796, -78.6382), new LatLng(32.7765, -79.9311))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Raleigh_Charleston,mRaleighCharleston);
//        LatLng RaleighCharles = new LatLng(33.990982, -78.189407);
//        GroundOverlayOptions RalChar = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
//                .position(RaleighCharles, 135000f, 135000f);
//        googleMap.addGroundOverlay(RalChar);

        mAtlantaCharleston = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(33.7490, -84.3880), new LatLng(32.7765, -79.9311))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Charleston_Atlanta,mAtlantaCharleston);
//        LatLng AtlantaCharleston = new LatLng(32.811516, -82.367019);
//        GroundOverlayOptions AtlChar = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
//                .position(AtlantaCharleston, 135000f, 135000f);
//        googleMap.addGroundOverlay(AtlChar);

        mCharlestonMiami = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(32.7765, -79.9311), new LatLng(25.7617, -80.1918))
                .width(10)
                .color(Color.rgb(255,0,255))//pink
        );
        mRouteMap.put(Route.Charleston_Miami,mCharlestonMiami);
//        LatLng CharlestonMiami = new LatLng(29.942589, -79.637424);
//        GroundOverlayOptions MiamiChar = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.fourwhite))
//                .position(CharlestonMiami, 135000f, 135000f);
//        googleMap.addGroundOverlay(MiamiChar);

        mAtlantaMiami = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(33.7490, -84.3880), new LatLng(25.7617, -80.1918))
                .width(10)
                .color(Color.parseColor("blue"))
        );
        mRouteMap.put(Route.Miami_Atlanta,mAtlantaMiami);
//        LatLng AtlantaMiami = new LatLng(30.018571, -83.770804);
//        GroundOverlayOptions AtlMiami = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.fivewhite))
//                .position(AtlantaMiami, 135000f, 135000f);
//        googleMap.addGroundOverlay(AtlMiami);

        mNewOrleansMiami = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(29.9511, -90.0715), new LatLng(25.7617, -80.1918))
                .width(10)
                .color(Color.parseColor("red"))
        );
        mRouteMap.put(Route.Miami_NewOrleans,mNewOrleansMiami);
//        LatLng NewMiami = new LatLng(28.458580, -85.204071);
//        GroundOverlayOptions NewMi = new GroundOverlayOptions()
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.sixwhite))
//                .position(NewMiami, 135000f, 135000f);
//        googleMap.addGroundOverlay(NewMi);
    }

    public static MapFragment newInstance(String param1, String param2) {
        MapFragment fragment = new MapFragment();

        return fragment;
    }
}
