package sweng.dante.practice5.fragment.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import sweng.dante.practice5.R;
import sweng.dante.practice5.fragment.Adapter.CityAdapter;
import sweng.dante.practice5.fragment.Class.City;

public class MapsFragment extends Fragment {
    private FusedLocationProviderClient fusedLocationClient;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private Context mContext;
    private GoogleMap mMap;
    private SearchView mSearchView;

    List<City> mCityList;
    RecyclerView mRecyclerView;
    CityAdapter mCityAdapter;

    FloatingActionButton fab;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;

            /** Chcek if permission is granted for user location*/
            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                // Enable the "My Location" layer on the map
                mMap.setMyLocationEnabled(true);

                // Move camera to current location
                fusedLocationClient.getLastLocation()
                        .addOnSuccessListener(location -> {
                            if (location != null) {
                                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                            }
                        });
            } else {
                // Request location permissions
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_maps, container, false);
        /** Instantiating necessary elements.*/
        mContext=getContext();
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(mContext);
        mSearchView = rootView.findViewById(R.id.idSearchView);

        /** Include a listener to the searchView */
        createSearchViewListener();


        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }

        /** instantiate recycler view*/
        mRecyclerView = view.findViewById(R.id.recyclerView3);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        fab=view.findViewById(R.id.fab);


        mCityList = new ArrayList<>();

        /** Adding city data*/

        mCityList.add(new City("New York","8,500,000"));
        mCityList.add(new City("Los Angeles","3,900,000"));
        mCityList.add(new City("Chicago","2,700,000"));
        mCityList.add(new City("Houston","2,300,000"));
        mCityList.add(new City("Philadelphia","1,600,000"));

        /** Set adapter and attach to recycler view*/
        mCityAdapter = new CityAdapter(mCityList);
        mRecyclerView.setAdapter(mCityAdapter);



        /** setting listener to floating action button.*/
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** getting selected City from user, and moving to that city placing a marker.*/
                City selected_city = mCityAdapter.selectedCity;

                if (selected_city!=null){
                    String locationName = selected_city.getName();


                    List<Address> addressList = null;
                    /** Check if the location is null */
                    if (locationName != null || locationName.equals("")){
                        /** Initializing the geocode */
                        Geocoder geocoder = new Geocoder(getContext());
                        try {
                            addressList = geocoder.getFromLocationName(locationName, 1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        /** Getting the location in the first position */
                        Address address = addressList.get(0);



                        /** Creating the LatLng object to store the address coordinates */
                        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                        /** Add a marker */
                        mMap.addMarker(new MarkerOptions().position(latLng).title(locationName));
                        /** Animate the camera */
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 8));
                    }

                }
                else{
                    Toast.makeText(getContext(), "Please select an item", Toast.LENGTH_SHORT).show();

                }

            }
        });



    }

    /** Asking user for permission to track location. This is to ensure the app navigates to user location on start.*/
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, enable the "My Location" layer on the map
                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    mMap.setMyLocationEnabled(true);
                }
            }
        }
    }

    private void createSearchViewListener(){
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                /** surrounding in try catch to ensure app continues to run if user inputs bad location. */
                try{

                    /** Getting the location name from the searchView */
                    String locationName = mSearchView.getQuery().toString();
                    List<Address> addressList = null;
                    /** Check if the location is null */
                    if (locationName != null || locationName.equals("")){

                        Geocoder geocoder = new Geocoder(getContext());
                        try {
                            addressList = geocoder.getFromLocationName(locationName, 1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        /** Getting the location in the first position */
                        Address address = addressList.get(0);



                        /** Creating the LatLng object to store the address coordinates */
                        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                        /** Add a marker */
                        mMap.addMarker(new MarkerOptions().position(latLng).title(locationName));
                        /** Animate the camera */
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 8));
                    }


                } catch (Exception e){
                    Toast.makeText(getContext(),"Error finding location",Toast.LENGTH_SHORT).show();
                }

                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
    }



}