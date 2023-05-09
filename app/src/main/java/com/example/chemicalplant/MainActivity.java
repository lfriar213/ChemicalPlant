package com.example.chemicalplant;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;

    DatabaseHelper myDB;
    ArrayList<String> company_id, company_name, company_address, company_year, company_chemical;
    CustomAdapter customAdapter;
    private GoogleMap mMap;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapView mapView = findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {

            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                LatLng location = new LatLng(37.4220, -122.0841);
                mMap.addMarker(new MarkerOptions().position(location).title("Marker Title"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
            }
        });






        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        myDB = new DatabaseHelper(MainActivity.this);
        company_id = new ArrayList<>();
        company_name = new ArrayList<>();
        company_address = new ArrayList<>();
        company_year = new ArrayList<>();
        company_chemical = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(MainActivity.this,this, company_id, company_name, company_address, company_year, company_chemical);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        // Find the search view widget in the layout


    }

    private void handleSearch(String query) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                company_id.add(cursor.getString(0));
                company_name.add(cursor.getString(1));
                company_address.add(cursor.getString(2));
                company_year.add(cursor.getString(3));
                company_chemical.add(cursor.getString(4));
            }
        }
    }
}