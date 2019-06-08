package com.example.earthquake;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public final class QueryUtils {


    private static String jsonResponse = "";


    public void setText(String a) {
        jsonResponse = a;

    }

    public QueryUtils() {
    }


    public static ArrayList<Earthquake> extractEarthquakes() {


        ArrayList<Earthquake> earthquakes = new ArrayList<>();


        try {



            JSONObject baseJsonResponse = new JSONObject(jsonResponse);


            JSONArray earthquakeArray = baseJsonResponse.getJSONArray("features");

            for (int i = 0; i < earthquakeArray.length(); i++) {

                JSONObject currentEarthquake = earthquakeArray.getJSONObject(i);

                JSONObject properties = currentEarthquake.getJSONObject("properties");

                double magnitude = properties.getDouble("mag");

                String location = properties.getString("place");

                long time = properties.getLong("time");

                String url = properties.getString("url");

                Earthquake earthquake = new Earthquake(magnitude, location, time, url);

                earthquakes.add(earthquake);
            }

        } catch (JSONException e) {

            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return earthquakes;
    }


}
