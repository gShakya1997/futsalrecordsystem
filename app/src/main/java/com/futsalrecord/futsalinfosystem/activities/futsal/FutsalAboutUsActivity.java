package com.futsalrecord.futsalinfosystem.activities.futsal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.activities.FutsalDashboard;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class FutsalAboutUsActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_futsal_about_us);
        SupportMapFragment mapFragment = (SupportMapFragment) this.getSupportFragmentManager().findFragmentById(R.id.officeMap);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(FutsalAboutUsActivity.this, FutsalDashboard.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        GoogleMap officeMap = googleMap;

        CameraUpdate center, zoom;
        center = CameraUpdateFactory.newLatLng(new LatLng(27.706264, 85.30513));
        zoom = CameraUpdateFactory.zoomTo(10);

        officeMap.addMarker(new MarkerOptions().position(new LatLng(27.706264, 85.30513)).title("Our Office"));
        officeMap.moveCamera(center);
        officeMap.animateCamera(zoom);
        officeMap.getUiSettings().setZoomControlsEnabled(true);
    }
}
