package com.example.earthquake;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes();


            ListView earthquakeListView = (ListView) findViewById(R.id.list);

            final EarthquakeAdapter adapter = new EarthquakeAdapter(this, earthquakes);

            earthquakeListView.setAdapter(adapter);

            earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                    Earthquake currentEarthquake = adapter.getItem(position);


                    Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());


                    Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                    startActivity(websiteIntent);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
