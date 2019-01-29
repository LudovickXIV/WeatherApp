package com.test.weatherapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.test.weatherapp.dialogs.BottomDialog;
import com.test.weatherapp.dialogs.PlacesListDialog;
import com.test.weatherapp.dialogs.SettingsDialog;

import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMarkerClickListener {

    public static final String KEY_BOTTOM_SHEET_DIALOG = "bts012371";
    public static final String KEY_SETTING_SEARCH = "srchbool98";
    public static final String KEY_SETTING_STYLE = "stl778";
    public static final String KEY_IS_SAVED = "s2tl7wqw78";
    public static final String KEY_RETURN_LAT = "12stlw8";
    public static final String KEY_RETURN_LONT = "sa123";

    private GoogleMap mMap;
    private PlaceAutocompleteFragment placeAutoComplete;
    @BindView(R.id.mNavigationDrawer)
    DrawerLayout mDrawerLayout;
    private Geocoder geocoder;
    private BottomDialog bottomSheetDialog;
    private ActionBar actionbar;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.exit_btn)
    Button exit;

    private LatLng mLatLng;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        ButterKnife.bind(this);

        Realm.init(this);
        geocoder = new Geocoder(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setTitle(null);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black);

        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    switch (menuItem.getItemId()) {
                        case R.id.nav_my_place:
                            if (!realmSize()) {
                                PlacesListDialog pld = new PlacesListDialog();
                                pld.show(getSupportFragmentManager(), "");
                                pld.setSettingChangeListener((activity, place) -> setMarker(new LatLng(place.getLatitude(), place.getLongitude())));
                            } else {
                                Toast.makeText(this, getResources().getString(R.string.list_of_places), Toast.LENGTH_LONG).show();
                            }
                            break;
                        case R.id.nav_settings:
                            showSettings();
                            break;
                    }
                    mDrawerLayout.closeDrawers();
                    return true;
                });

        placeAutoComplete = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete);
        placeAutoComplete.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                setMarker(place.getLatLng());
            }
            @Override
            public void onError(Status status) {
                Log.d("Maps", "An error occurred: " + status);
            }
        });

        exit.setOnClickListener(v -> {
            Intent returnIntent = new Intent();
            returnIntent.putExtra(KEY_RETURN_LAT, mLatLng.latitude);
            returnIntent.putExtra(KEY_RETURN_LONT, mLatLng.longitude);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        });
    }



    private void showSettings() {

        SettingsDialog dialog = new SettingsDialog();
        dialog.show(getSupportFragmentManager(), "");
        dialog.setSettingChangeListener(new SettingsChange<Activity>() {
            @Override
            public void onChanged(Activity activity) {
                settingsSet();
                SharedPreferences sp = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean(MapsActivity.KEY_SETTING_SEARCH, Settings.isShowSearch());
                editor.putInt(MapsActivity.KEY_SETTING_STYLE, Settings.getStyle());
                editor.apply();
            }
            @Override
            public void onNonChanged(Activity activity) {

            }
        });
    }

    private void settingsSet(){
        try {
            boolean success = mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(MapsActivity.this, Settings.getStyle()));
            if (!Settings.isShowSearch()) {
                findViewById(R.id.search_inc).setVisibility(View.GONE);
            } else {
                findViewById(R.id.search_inc).setVisibility(View.VISIBLE);
            }
            View view = navigationView.getHeaderView(0);
            ConstraintLayout layout = (ConstraintLayout)view.findViewById(R.id.nav_view_header);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Settings.getStyle() == R.raw.map_dark) {
                    window.setStatusBarColor(Color.BLACK);
                    actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_white);
                    layout.setBackgroundColor(getResources().getColor(R.color.headBlack));
                } else if (Settings.getStyle() == R.raw.map_standard) {
                    window.setStatusBarColor(getResources().getColor(R.color.statusBarStandart));
                    actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black);
                    layout.setBackgroundColor(getResources().getColor(R.color.headStandart));
                } else if (Settings.getStyle() == R.raw.map_retro) {
                    window.setStatusBarColor(getResources().getColor(R.color.statusBarRetro));
                    actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black);
                    layout.setBackgroundColor(getResources().getColor(R.color.headRetro));
                } else {
                    window.setStatusBarColor(getResources().getColor(R.color.statusBarStandart));
                    layout.setBackgroundColor(getResources().getColor(R.color.headStandart));
                }
            }
            if (!success) {
                Log.e("TAG", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("TAG", "Can't find style. Error: ", e);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(this);
        mMap.setOnMarkerClickListener(this);

        SharedPreferences sp = this.getPreferences(MODE_PRIVATE);
        Settings.setShowSearch(sp.getBoolean(KEY_SETTING_SEARCH, true));
        Settings.setMapStyle(sp.getInt(KEY_SETTING_STYLE, R.raw.map_standard));

        settingsSet();
        Intent intent = getIntent();
        if (intent != null) {
            double lat = intent.getDoubleExtra(MainActivity.KEY_LAT, 0);
            double lon = intent.getDoubleExtra(MainActivity.KEY_LONG, 0);
            DecimalFormat df = new DecimalFormat("#.#####");
            lat = Double.valueOf(df.format(lat).replaceAll(",", "."));
            lon = Double.valueOf(df.format(lon).replaceAll(",", "."));

            setMarker(new LatLng(lat, lon));
        }
    }

    private void setMarker(LatLng latLng){
        mMap.clear();

        if (bottomSheetDialog != null) {
            bottomSheetDialog = null;
        }

        MarkerOptions options = new MarkerOptions();
        options.position(latLng);

        navigationView.getHeaderView(0);
        TextView city = navigationView.findViewById(R.id.header_city);
        TextView lat = navigationView.findViewById(R.id.nav_lat);
        TextView lon = navigationView.findViewById(R.id.nav_lon);

        try {
            List<Address> data = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            Bundle bundle = new Bundle();
            bundle.putSerializable(KEY_BOTTOM_SHEET_DIALOG, (Serializable) data);
            bundle.putBoolean(KEY_IS_SAVED, true);
            bottomSheetDialog = new BottomDialog();
            bottomSheetDialog.setArguments(bundle);

            String cityStr = (String.valueOf(data.get(0).getLocality()).contains("null"))?
                    String.valueOf(getResources().getString(R.string.warning_unknown_city_state)) :
                    String.valueOf(data.get(0).getLocality());

            String latStr = (String.valueOf(data.get(0).getLatitude()).length() > 8)?
                    String.valueOf(data.get(0).getLatitude()).substring(0, 7) : String.valueOf(data.get(0).getLatitude());

            String lonStr = (String.valueOf(data.get(0).getLongitude()).length() > 8)?
                    String.valueOf(data.get(0).getLongitude()).substring(0, 7) : String.valueOf(data.get(0).getLongitude());

            city.setText(cityStr);

            lat.setText(latStr);

            lon.setText(lonStr);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            city.setText(getResources().getString(R.string.warning_unknown_city_state));
            lat.setText("0.0");
            lon.setText("0.0");
        }

        mLatLng = options.getPosition();
        mMap.addMarker(options);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10f));
    }

    @Override
    public void onMapClick(LatLng latLng) {
        setMarker(latLng);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (bottomSheetDialog != null) {
            bottomSheetDialog.show(getSupportFragmentManager(), "");
        }
        return false;
    }

    private boolean realmSize() {
        Realm realm = Realm.getDefaultInstance();
        return realm.isEmpty();
    }

}
