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
import android.widget.TextView;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.UUID;
import java.util.List;

import androidteam.cs340.tickettoride.Client.Phase2Facade;
import androidteam.cs340.tickettoride.Client.Presenters.IPresenter;
import androidteam.cs340.tickettoride.Client.State.TurnState;
import androidteam.cs340.tickettoride.R;
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
    private ArrayList<TrainCard> cardsUsedToClaim = new ArrayList<>();
    private Route routeToClaim;
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

        String[] items = new String[]{"Values"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, items);
        mRouteSpinner.setAdapter(adapter);
        mRouteSpinner.setOnItemSelectedListener(new spinnerOnClickListener());

        mClaimRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Claimed Route" , Toast.LENGTH_SHORT).show();

                Phase2Facade.SINGLETON.claimRoute(routeToClaim, cardsUsedToClaim);
            }
        });

        return mView;
    }


    /*--------
     SPINNER LOGIC
     --------*/
    Map<String,Route> routeSelections = new TreeMap<>();
    public void updateSpinner(){
        int size = Phase2Facade.SINGLETON.getCurrentGame().getAvailableRoutes().size();
        ArrayList<String> items = new ArrayList<>();
        boolean update = true;

        if(lastList.size() == Phase2Facade.SINGLETON.getCurrentGame().getAvailableRoutes().size()){
            update = false;
        }else{
            lastList = Phase2Facade.SINGLETON.getCurrentGame().getAvailableRoutes();
        }

        if(update == true){
            // Watch for where this loop gets called if no one claims a route and the spinner doesn't change.
            // No matter what, always pass what cards they are using to claim the route to the server.
            routeSelections = createRouteSelections();
            for(Map.Entry<String,Route> entry : routeSelections.entrySet()){
                items.add(entry.getValue().name() + " using " + entry.getKey());
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
            int i = 0;
            for (Map.Entry<String,Route> entry : routeSelections.entrySet()) {
                if (i == position) {
                    routeToClaim = entry.getValue();
                    if (entry.getKey().equals("white")) {
                        addCards(routeToClaim, numWhiteCards, TrainCard.Passenger);
                    }
                    if (entry.getKey().equals("orange")) {
                        addCards(routeToClaim, numOrangeCards, TrainCard.Freight);
                    }
                    if (entry.getKey().equals("red")) {
                        addCards(routeToClaim, numRedCards, TrainCard.Coal);
                    }
                    if (entry.getKey().equals("blue")) {
                        addCards(routeToClaim, numBlueCards, TrainCard.Tanker);
                    }
                    if (entry.getKey().equals("green")) {
                        addCards(routeToClaim, numGreenCards, TrainCard.Caboose);
                    }
                    if (entry.getKey().equals("pink")) {
                        addCards(routeToClaim, numPinkCards, TrainCard.Box);
                    }
                    if (entry.getKey().equals("black")) {
                        addCards(routeToClaim, numBlackCards, TrainCard.Hopper);
                    }
                    if (entry.getKey().equals("yellow")) {
                        addCards(routeToClaim, numYellowCards, TrainCard.Reefer);
                    }
                    if (entry.getKey().equals("wild")) {
                        addCards(routeToClaim, numWildCards, TrainCard.Locomotive);
                    }

                    int remainingSpaces = routeToClaim.length - cardsUsedToClaim.size();
                    if (cardsUsedToClaim.size() < routeToClaim.length) {
                        for (int j = 0; j < remainingSpaces; j++) {
                            cardsUsedToClaim.add(TrainCard.Locomotive);
                        }
                    }
                }
                i++;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    /*--------
     ROUTE CLAIMING LOGIC
     --------*/
    Map<String,Route> routesToSelectFrom = new TreeMap<>();
    List<Route> availableRoutes = Phase2Facade.SINGLETON.getCurrentGame().getAvailableRoutes();

    private Map<String,Route> createRouteSelections() {
        updateCount(Phase2Facade.SINGLETON.getMyDeck());
        for (Route route : availableRoutes) {
            // If canClaimRoute returns null, it means the route can't be claimed.
            if (canClaimRoute(route) != null) {
                routesToSelectFrom.put(canClaimRoute(route),route);
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

    private void updateCount(List<TrainCard> cards){
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
            if(card.color == "pink"){
                numPinkCards = numPinkCards + 1;
            }
        }
    }

    private String canClaimRoute(Route routeToCheck) {
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
                } else {break;}
            case "blue":
                if (numBlueCards + numWildCards >= routeLength) {
                    return "blue";
                } else {break;}
            case "green":
                if (numGreenCards + numWildCards >= routeLength) {
                    return "green";
                } else {break;}
            case "pink":
                if (numPinkCards + numWildCards >= routeLength) {
                    return "pink";
                } else {break;}
            case "black":
                if (numBlackCards + numWildCards >= routeLength) {
                    return "black";
                } else {break;}
            case "yellow":
                if (numYellowCards + numWildCards >= routeLength) {
                    return "yellow";
                } else {break;}
            case "grey":
                return canClaimGreyRoute(routeLength);
        }

        if (numWildCards >= routeLength) {
            return "wild";
        }

        return null;

    }

    private String canClaimGreyRoute(int routeLength) {
        if (numWhiteCards + numWildCards >= routeLength) {
            return "white";
        }
        if (numOrangeCards + numWildCards >= routeLength) {
            return "orange";
        }
        if (numRedCards + numWildCards >= routeLength) {
            return "red";
        }
        if (numBlueCards + numWildCards >= routeLength) {
            return "blue";
        }
        if (numGreenCards + numWildCards >= routeLength) {
            return "green";
        }
        if (numPinkCards + numWildCards >= routeLength) {
            return "pink";
        }
        if (numBlackCards + numWildCards >= routeLength) {
            return "black";
        }
        if (numYellowCards + numWildCards >= routeLength) {
            return "yellow";
        }
        if (numWildCards >= routeLength) {
            return "wild";
        }
        return null;
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
        if (lastAvailableRoutes == Phase2Facade.SINGLETON.getCurrentGame().getAvailableRoutes()){
            return;
        }else{
            for(Player player : Phase2Facade.SINGLETON.getCurrentGame().getPlayersList()){
                for(Route route : player.getClaimedRoutes()){
                    if(player.getColor() == "red"){
                        mRouteMap.get(route).setColor(Color.rgb(255,0,0));
                    }
                    if(player.getColor() == "blue"){
                        mRouteMap.get(route).setColor(Color.rgb(0,255,255));
                    }
                    if(player.getColor() == "green"){
                        mRouteMap.get(route).setColor(Color.rgb(0,255,0));
                    }
                    if(player.getColor() == "yellow"){
                        mRouteMap.get(route).setColor(Color.rgb(255,255,0));
                    }
                    if(player.getColor() == "black"){
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
    public void Update() {//TODO: UNCOMMENT UPDATEROUTES() WHEN CLAIMROUTES AND TURN FUNCTIONALITY IS DONE
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
        LatLng SeattlePortland = new LatLng(46.744096, -124.340318);
        GroundOverlayOptions sp = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.onewhite))
                .position(SeattlePortland, 135000f, 135000f);
        googleMap.addGroundOverlay(sp);

        mSeattlePortland2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(47.603877, -122.965880), new LatLng(45.581488, -123.369117))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Portland_Seattle_2,mSeattlePortland2);
        LatLng SeattlePortland2 = new LatLng(46.457254, -121.397269);
        GroundOverlayOptions sp2 = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.onewhite))
                .position(SeattlePortland2, 135000f, 135000f);
        googleMap.addGroundOverlay(sp2);

        mSeattleVancouver = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(47.6062, -122.3321), new LatLng(49.2827, -123.1207))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Seattle_Vancouver,mSeattleVancouver);
        LatLng SeattleVancouver = new LatLng(48.189723, -124.259329);
        GroundOverlayOptions sv = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.onewhite))
                .position(SeattleVancouver, 135000f, 135000f);
        googleMap.addGroundOverlay(sv);

        mSeattleVancouver2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(47.603877, -122.965880), new LatLng(49.227659, -123.583162))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Seattle_Vancouver_2,mSeattleVancouver2);
        LatLng SeattleVancouver2 = new LatLng(48.733097, -121.643220);
        GroundOverlayOptions sv2 = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.onewhite))
                .position(SeattleVancouver2, 135000f, 135000f);
        googleMap.addGroundOverlay(sv2);

        mVancouverCalgary = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(49.2827, -123.1207), new LatLng(51.0486, -114.0708))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Vancouver_Calgary,mVancouverCalgary);
        LatLng VancouverCalgary = new LatLng(51.026983, -118.996176);
        GroundOverlayOptions vc = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.threewhite))
                .position(VancouverCalgary, 135000f, 135000f);
        googleMap.addGroundOverlay(vc);

        mSeattleCalgary = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(47.6062, -122.3321), new LatLng(51.0486, -114.0708))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Seattle_Calgary,mSeattleCalgary);
        LatLng SeattleCalgary = new LatLng(48.856358, -117.488752);
        GroundOverlayOptions seacal = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.fourwhite))
                .position(SeattleCalgary, 135000f, 135000f);
        googleMap.addGroundOverlay(seacal);

        mSeattleHellena = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(47.6062, -122.3321), new LatLng(46.5891, -112.0391))
                .width(10)
                .color(Color.rgb(234, 222, 45))
        );
        mRouteMap.put(Route.Seattle_Hellena,mSeattleHellena);
        LatLng SeattleHellena = new LatLng(46.253710, -116.906759);
        GroundOverlayOptions seahel = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.sixwhite))
                .position(SeattleHellena, 135000f, 135000f);
        googleMap.addGroundOverlay(seahel);

        mPortlandSaltLake = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(45.5155, -122.6793), new LatLng(40.7608, -111.8910))
                .width(10)
                .color(Color.parseColor("blue"))
        );
        mRouteMap.put(Route.Portland_SaltLakeCity,mPortlandSaltLake);
        LatLng PortlandSaltLake = new LatLng(43.786215, -116.101161);
        GroundOverlayOptions portSalt = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.sixwhite))
                .position(PortlandSaltLake, 135000f, 135000f);
        googleMap.addGroundOverlay(portSalt);

        mSaltLakeHelena = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(40.7608, -111.8910), new LatLng(46.5891, -112.0391))
                .width(10)
                .color(Color.rgb(255,0,255))//pink
        );
        mRouteMap.put(Route.SaltLakeCity_Hellena,mSaltLakeHelena);
        LatLng SaltLakeHelena = new LatLng(44.198513, -113.216691);
        GroundOverlayOptions salHel = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.threewhite))
                .position(SaltLakeHelena, 135000f, 135000f);
        googleMap.addGroundOverlay(salHel);

        mPortlandSanFrancisco = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(45.5155, -122.6793), new LatLng(37.7749, -122.4194))
                .width(8)
                .color(Color.parseColor("green"))
        );
        mRouteMap.put(Route.SanFrancisco_Portland,mPortlandSanFrancisco);
        LatLng PortlandSanFran = new LatLng(41.153325, -124.433904);
        GroundOverlayOptions portSan = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.fivewhite))
                .position(PortlandSanFran, 135000f, 135000f);
        googleMap.addGroundOverlay(portSan);

        mPortlandSanFrancisco2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(45.581488, -123.369117), new LatLng(37.730641, -122.864849))
                .width(8)
                .color(Color.rgb(255,0,255))//pink
        );
        mRouteMap.put(Route.SanFrancisco_Portland_2,mPortlandSanFrancisco2);
        LatLng PortlandSanFran2 = new LatLng(41.582066, -121.095521);
        GroundOverlayOptions portSan2 = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.fivewhite))
                .position(PortlandSanFran2, 135000f, 135000f);
        googleMap.addGroundOverlay(portSan2);

        mSanFranciscoSaltLake = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(37.7749, -122.4194), new LatLng(40.7608, -111.8910))
                .width(8)
                .color(Color.parseColor("white"))
        );
        mRouteMap.put(Route.SanFrancisco_SaltLakeCity,mSanFranciscoSaltLake);
        LatLng SanSaltLake = new LatLng(38.585375, -116.505781);
        GroundOverlayOptions sanSalt = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.fivewhite))
                .position(SanSaltLake, 135000f, 135000f);
        googleMap.addGroundOverlay(sanSalt);

        mSanFranciscoSaltLake2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(38.086034, -122.458533), new LatLng(41.021161, -112.098136))
                .width(8)
                .color(Color.rgb(255,165,0))//orange
        );
        mRouteMap.put(Route.SanFrancisco_SaltLakeCity_2,mSanFranciscoSaltLake2);
        LatLng SanSaltLake2 = new LatLng(40.619201, -117.252524);
        GroundOverlayOptions sanSalt2 = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.fivewhite))
                .position(SanSaltLake2, 135000f, 135000f);
        googleMap.addGroundOverlay(sanSalt2);

        mSanFranciscoLosAngeles = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(37.7749, -122.4194), new LatLng(34.0522, -118.2437))
                .width(8)
                .color(Color.rgb(234, 222, 45))
        );
        mRouteMap.put(Route.LosAngeles_SanFrancisco,mSanFranciscoLosAngeles);
        LatLng SanFranLosAngeles = new LatLng(35.100249, -121.031027);
        GroundOverlayOptions sanLosAng = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.threewhite))
                .position(SanFranLosAngeles, 135000f, 135000f);
        googleMap.addGroundOverlay(sanLosAng);

        mSanFranciscoLosAngeles2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(37.730641, -122.864849), new LatLng(33.856838, -118.417167))
                .width(8)
                .color(Color.rgb(255,0,255))//pink
        );
        mRouteMap.put(Route.LosAngeles_SanFrancisco_2,mSanFranciscoLosAngeles2);
        LatLng SanFranLosAngeles2 = new LatLng(36.171694, -119.493613);
        GroundOverlayOptions sanLosAng2 = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.threewhite))
                .position(SanFranLosAngeles2, 135000f, 135000f);
        googleMap.addGroundOverlay(sanLosAng2);

        mLosAngelesLasVegas = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(34.0522, -118.2437), new LatLng(36.1699, -115.1398))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.LosAngeles_LasVegas,mLosAngelesLasVegas);
        LatLng angelesVegas = new LatLng(35.423189, -117.429087);
        GroundOverlayOptions angVeg = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
                .position(angelesVegas, 135000f, 135000f);
        googleMap.addGroundOverlay(angVeg);

        mLasVegasSaltLake = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(36.1699, -115.1398), new LatLng(40.7608, -111.8910))
                .width(10)
                .color(Color.rgb(255,165,0))//orange
        );
        mRouteMap.put(Route.SaltLakeCity_LasVegas,mLasVegasSaltLake);
        LatLng VegasSaltLake = new LatLng(37.558595, -113.033217);
        GroundOverlayOptions vegSalt = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.threewhite))
                .position(VegasSaltLake, 135000f, 135000f);
        googleMap.addGroundOverlay(vegSalt);

        mLosAngelesPhoenix = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(34.0522, -118.2437), new LatLng(33.4484, -112.0740))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.LosAngeles_Phoenix,mLosAngelesPhoenix);
        LatLng LosAngelesPhoenix = new LatLng(34.353640, -114.905052);
        GroundOverlayOptions AngelPho = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.threewhite))
                .position(LosAngelesPhoenix, 135000f, 135000f);
        googleMap.addGroundOverlay(AngelPho);

        mLosAngelesElPaso = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(34.0522, -118.2437), new LatLng(31.7619, -106.4850))
                .width(10)
                .color(Color.parseColor("black"))
        );
        mRouteMap.put(Route.ElPaso_LosAngeles,mLosAngelesElPaso);
        LatLng LosAngelesPaso = new LatLng(32.104680, -113.204485);
        GroundOverlayOptions AngelPaso = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.sixwhite))
                .position(LosAngelesPaso, 135000f, 135000f);
        googleMap.addGroundOverlay(AngelPaso);

        mPhoenixElPaso = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(33.4484, -112.0740), new LatLng(31.7619, -106.4850))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.ElPaso_Phoenix,mPhoenixElPaso);
        LatLng PhoenixPaso = new LatLng(33.030542, -108.724023);
        GroundOverlayOptions PhoPaso = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.threewhite))
                .position(PhoenixPaso, 135000f, 135000f);
        googleMap.addGroundOverlay(PhoPaso);

        mPhoenixSantaFe = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(33.4484, -112.0740), new LatLng(35.6870, -105.9378))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.SantaFe_Phoenix,mPhoenixSantaFe);
        LatLng PhoSantaFe = new LatLng(35.364976, -108.487246);
        GroundOverlayOptions PhoFe = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.threewhite))
                .position(PhoSantaFe, 135000f, 135000f);
        googleMap.addGroundOverlay(PhoFe);

        mPhoenixDenver = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(33.4484, -112.0740), new LatLng(39.7392, -104.9903))
                .width(10)
                .color(Color.parseColor("white"))
        );
        mRouteMap.put(Route.Denver_Phoenix,mPhoenixDenver);
        LatLng PhoenixDenver = new LatLng(36.889850, -109.193495);
        GroundOverlayOptions PhoDen = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.fivewhite))
                .position(PhoenixDenver, 135000f, 135000f);
        googleMap.addGroundOverlay(PhoDen);

        mElPasoSantaFe = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(31.7619, -106.4850), new LatLng(35.6870, -105.9378))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.ElPaso_SantaFe,mElPasoSantaFe);
        LatLng ElPasoSantaFe = new LatLng(33.822227, -106.946898);
        GroundOverlayOptions PasoFe = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
                .position(ElPasoSantaFe, 135000f, 135000f);
        googleMap.addGroundOverlay(PasoFe);

        mSantaFeDenver = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(35.6870, -105.9378), new LatLng(39.7392, -104.9903))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Denver_SantaFe,mSantaFeDenver);
        LatLng SantaFeDenver = new LatLng(37.286346, -106.499830);
        GroundOverlayOptions FeDen = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
                .position(SantaFeDenver, 135000f, 135000f);
        googleMap.addGroundOverlay(FeDen);

        mSaltLakeDenver = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(40.7608, -111.8910), new LatLng(39.7392, -104.9903))
                .width(8)
                .color(Color.parseColor("red"))
        );
        mRouteMap.put(Route.SaltLakeCity_Denver,mSaltLakeDenver);
        LatLng SaltLakeDenver = new LatLng(41.009439, -108.470395);
        GroundOverlayOptions SalDen = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.threewhite))
                .position(SaltLakeDenver, 135000f, 135000f);
        googleMap.addGroundOverlay(SalDen);

        mSaltLakeDenver2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(41.021161, -112.098136), new LatLng(39.925305, -104.944488))
                .width(8)
                .color(Color.rgb(234, 222, 45))
        );
        mRouteMap.put(Route.SaltLakeCity_Denver_2,mSaltLakeDenver2);
        LatLng SaltLakeDenver2 = new LatLng(39.754185, -108.733952);
        GroundOverlayOptions SalDen2 = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.threewhite))
                .position(SaltLakeDenver2, 135000f, 135000f);
        googleMap.addGroundOverlay(SalDen2);

        mHelenaDenver = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(46.5891,-112.0391), new LatLng(39.7392, -104.9903))
                .width(10)
                .color(Color.parseColor("green"))
        );
        mRouteMap.put(Route.Hellena_Denver,mHelenaDenver);
        LatLng HelenaDenver = new LatLng(44.114951, -110.582058);
        GroundOverlayOptions HelDen = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.fourwhite))
                .position(HelenaDenver, 135000f, 135000f);
        googleMap.addGroundOverlay(HelDen);

        mCalgaryWinnipeg = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(51.0486,-114.0708), new LatLng(49.8951, -97.1384))
                .width(10)
                .color(Color.parseColor("white"))
        );
        mRouteMap.put(Route.Calgary_Winnipeg,mCalgaryWinnipeg);
        LatLng CalgaryWinnipeg = new LatLng(51.096300, -104.400824);
        GroundOverlayOptions CalWin = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.sixwhite))
                .position(CalgaryWinnipeg, 135000f, 135000f);
        googleMap.addGroundOverlay(CalWin);

        mHelenaWinnipeg = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(46.5891,-112.0391), new LatLng(49.8951, -97.1384))
                .width(10)
                .color(Color.parseColor("blue"))
        );
        mRouteMap.put(Route.Winnipeg_Hellena,mHelenaWinnipeg);
        LatLng HelenaWinnipeg = new LatLng(48.855680, -105.544547);
        GroundOverlayOptions HelWin = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.fourwhite))
                .position(HelenaWinnipeg, 135000f, 135000f);
        googleMap.addGroundOverlay(HelWin);

        //this was the one that was breaking
        mCalgaryHelena = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(51.0486, -114.0708), new LatLng(46.5891, -112.0391))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Calgary_Hellena,mCalgaryHelena);
        LatLng CalgaryHelena = new LatLng(49.186809, -111.890500);
        GroundOverlayOptions Calhel = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.fourwhite))
                .position(CalgaryHelena, 135000f, 135000f);
        googleMap.addGroundOverlay(Calhel);

        mHelenaDuluth = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(46.5891,-112.0391), new LatLng(46.7867, -92.1005))
                .width(10)
                .color(Color.rgb(255,165,0))//orange
        );
        mRouteMap.put(Route.Hellena_Duluth,mHelenaDuluth);
        LatLng HelenaDuluth = new LatLng(48.070239, -100.766039);
        GroundOverlayOptions heldul = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.sixwhite))
                .position(HelenaDuluth, 135000f, 135000f);
        googleMap.addGroundOverlay(heldul);

        mWinnipegDuluth = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(49.8951, -97.1384), new LatLng(46.7867, -92.1005))
                .width(10)
                .color(Color.parseColor("black"))
        );
        mRouteMap.put(Route.Winnipeg_Duluth,mWinnipegDuluth);
        LatLng WinniDuluth = new LatLng(47.724083, -95.700811);
        GroundOverlayOptions WinDul = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.fourwhite))
                .position(WinniDuluth, 135000f, 135000f);
        googleMap.addGroundOverlay(WinDul);

        mHelenaOmaha = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(46.5891,-112.0391), new LatLng(41.2565, -95.9345))
                .width(10)
                .color(Color.parseColor("red"))
        );
        mRouteMap.put(Route.Hellena_Omaha,mHelenaOmaha);
        LatLng HelenaOmaha = new LatLng(44.494056, -102.971695);
        GroundOverlayOptions Helo = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.fivewhite))
                .position(HelenaOmaha, 135000f, 135000f);
        googleMap.addGroundOverlay(Helo);

        mDenverOmaha = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(39.7392, -104.9903), new LatLng(41.2565, -95.9345))
                .width(10)
                .color(Color.rgb(255,0,255))//pink
        );
        mRouteMap.put(Route.Denver_Omaha,mDenverOmaha);
        LatLng DenvOmaha = new LatLng(41.242965, -101.220313);
        GroundOverlayOptions Denvo = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.fourwhite))
                .position(DenvOmaha, 135000f, 135000f);
        googleMap.addGroundOverlay(Denvo);

        mDuluthOmaha = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(46.7867, -92.1005), new LatLng(41.2565, -95.9345))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Duluth_Omaha,mDuluthOmaha);
        LatLng DuluthOmaha = new LatLng(44.508381, -95.304838);
        GroundOverlayOptions Dulo = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
                .position(DuluthOmaha, 135000f, 135000f);
        googleMap.addGroundOverlay(Dulo);

        mDuluthOmaha2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(46.753932, -92.484388), new LatLng(41.267743, -96.132537))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Duluth_Omaha_2,mDuluthOmaha2);
        LatLng DuluthOmaha2 = new LatLng(44.036393, -92.471604);
        GroundOverlayOptions Dulo2 = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
                .position(DuluthOmaha2, 135000f, 135000f);
        googleMap.addGroundOverlay(Dulo2);

        mDenverKansasCity = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(39.7392, -104.9903), new LatLng(39.0997, -94.5786))
                .width(8)
                .color(Color.parseColor("black"))
        );
        mRouteMap.put(Route.KansasCity_Denver,mDenverKansasCity);
        LatLng KansasDenver = new LatLng(39.945674, -99.763766);
        GroundOverlayOptions KanDen = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.fourwhite))
                .position(KansasDenver, 135000f, 135000f);
        googleMap.addGroundOverlay(KanDen);

        mDenverKansasCity2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(39.925305, -104.944488), new LatLng(39.220950, -94.558615))
                .width(8)
                .color(Color.rgb(255,165,0))//orange
        );
        mRouteMap.put(Route.KansasCity_Denver_2,mDenverKansasCity2);
        LatLng KansasDenver2 = new LatLng(38.841981, -99.895544);
        GroundOverlayOptions KanDen2 = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.fourwhite))
                .position(KansasDenver2, 135000f, 135000f);
        googleMap.addGroundOverlay(KanDen2);


        mOmahaKansasCity = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(41.2565, -95.9345), new LatLng(39.0997, -94.5786))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.KansasCity_Omaha,mOmahaKansasCity);
        LatLng KansasOmaha = new LatLng(40.120944, -96.357091);
        GroundOverlayOptions KanOma = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.onewhite))
                .position(KansasOmaha, 135000f, 135000f);
        googleMap.addGroundOverlay(KanOma);

        mOmahaKansasCity2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(41.267743, -95.714889), new LatLng(39.145305, -94.372355))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.KansasCity_Omaha_2,mOmahaKansasCity2);
        LatLng KansasOmaha2 = new LatLng(39.973437, -94.079343);
        GroundOverlayOptions KanOma2 = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.onewhite))
                .position(KansasOmaha2, 135000f, 135000f);
        googleMap.addGroundOverlay(KanOma2);

        mDenverOklahomaCity = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(39.7392, -104.9903), new LatLng(35.4676, -97.5164))
                .width(10)
                .color(Color.parseColor("red"))
        );
        mRouteMap.put(Route.Denver_OklahomaCity,mDenverOklahomaCity);
        LatLng DenverOKC = new LatLng(37.025854, -101.397414);
        GroundOverlayOptions DenOKC = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.fourwhite))
                .position(DenverOKC, 135000f, 135000f);
        googleMap.addGroundOverlay(DenOKC);

        mSantaFeOklahomaCity = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(35.6870, -105.9378), new LatLng(35.4676, -97.5164))
                .width(10)
                .color(Color.parseColor("blue"))
        );
        mRouteMap.put(Route.SantaFe_OklahomaCity,mSantaFeOklahomaCity);
        LatLng SantaOKC = new LatLng(36.149173, -103.017789);
        GroundOverlayOptions SanOKC = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.threewhite))
                .position(SantaOKC, 135000f, 135000f);
        googleMap.addGroundOverlay(SanOKC);

        mElPasoOklahomaCity = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(31.7619, -106.4850), new LatLng(35.4676, -97.5164))
                .width(10)
                .color(Color.rgb(234, 222, 45))
        );
        mRouteMap.put(Route.ElPaso_OklahomaCity,mElPasoOklahomaCity);
        LatLng PasoOKC = new LatLng(34.144771, -101.844350);
        GroundOverlayOptions PasOKC = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.fivewhite))
                .position(PasoOKC, 135000f, 135000f);
        googleMap.addGroundOverlay(PasOKC);

        mKansasCityOklahomaCity = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(39.0997, -94.5786), new LatLng(35.4676, -97.5164))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.KansasCity_OklahomaCity,mKansasCityOklahomaCity);
        LatLng KansasOKC = new LatLng(37.559684, -96.771143);
        GroundOverlayOptions KanOKC = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
                .position(KansasOKC, 135000f, 135000f);
        googleMap.addGroundOverlay(KanOKC);

        mKansasCityOklahomaCity2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(39.145305, -94.372355), new LatLng(35.404845, -97.286023))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.KansasCity_OklahomaCity_2,mKansasCityOklahomaCity2);
        LatLng KansasOKC2 = new LatLng(37.157991, -95.277655);
        GroundOverlayOptions KanOKC2 = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
                .position(KansasOKC2, 135000f, 135000f);
        googleMap.addGroundOverlay(KanOKC2);

        mOklahomaCityDallas = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(35.4676, -97.5164), new LatLng(32.7767, -96.7970))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Dallas_OklahomaCity,mOklahomaCityDallas);
        LatLng DallasOKC = new LatLng(34.255869, -96.281435);
        GroundOverlayOptions DakOKC = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
                .position(DallasOKC, 135000f, 135000f);
        googleMap.addGroundOverlay(DakOKC);

        mOklahomaCityDallas2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(35.404845, -97.286023), new LatLng(32.679479, -96.472422))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Dallas_OklahomaCity_2,mOklahomaCityDallas2);
        LatLng DallasOKC2 = new LatLng(33.964790, -97.994553);
        GroundOverlayOptions DakOKC2 = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
                .position(DallasOKC2, 135000f, 135000f);
        googleMap.addGroundOverlay(DakOKC2);

        mElPasoDallas = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(31.7619, -106.4850), new LatLng(32.7767, -96.7970))
                .width(10)
                .color(Color.parseColor("red"))
        );
        mRouteMap.put(Route.ElPaso_Dallas,mElPasoDallas);
        LatLng PasoDallas = new LatLng(32.714511, -101.748605);
        GroundOverlayOptions PalDal = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.fourwhite))
                .position(PasoDallas, 135000f, 135000f);
        googleMap.addGroundOverlay(PalDal);

        mDallasHouston = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(32.7767, -96.7970), new LatLng(29.7604, -95.3698))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Houston_Dallas,mDallasHouston);
        LatLng DallasHouston = new LatLng(31.178074, -97.044137);
        GroundOverlayOptions DalHous = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.onewhite))
                .position(DallasHouston, 135000f, 135000f);
        googleMap.addGroundOverlay(DalHous);

        mDallasHouston2 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(32.679479, -96.472422), new LatLng(29.782332, -95.034271))
                .width(8)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.Houston_Dallas_2,mDallasHouston2);
        LatLng DallasHouston2 = new LatLng(31.609461, -95.484760);
        GroundOverlayOptions DalHous2 = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.onewhite))
                .position(DallasHouston2, 135000f, 135000f);
        googleMap.addGroundOverlay(DalHous2);

        mElPasoHouston = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(31.7619, -106.4850), new LatLng(29.7604, -95.3698))
                .width(10)
                .color(Color.parseColor("green"))
        );
        mRouteMap.put(Route.Houston_ElPaso,mElPasoHouston);
        LatLng PasoHouston = new LatLng(29.904502, -100.758637);
        GroundOverlayOptions PasHous = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.sixwhite))
                .position(PasoHouston, 135000f, 135000f);
        googleMap.addGroundOverlay(PasHous);

        mHoustonNewOrleans = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(29.7604, -95.3698), new LatLng(29.9511, -90.0715))
                .width(10)
                .color(Color.parseColor("gray"))
        );
        mRouteMap.put(Route.NewOrleans_Houston,mHoustonNewOrleans);
        LatLng HoustonNew = new LatLng(29.476367, -92.322545);
        GroundOverlayOptions NewHous = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.twowhite))
                .position(HoustonNew, 135000f, 135000f);
        googleMap.addGroundOverlay(NewHous);

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
