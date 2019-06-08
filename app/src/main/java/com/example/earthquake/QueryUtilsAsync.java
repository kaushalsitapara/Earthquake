package com.example.earthquake;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;

import javax.net.ssl.HttpsURLConnection;


public class QueryUtilsAsync extends AsyncTask<Void, Void, Void> {
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static final String USGS_REQUEST_URL =
            "https://api.myjson.com/bins/zsht9";
    private static String jsonResponse = "";

    @Override
    protected Void doInBackground(Void... voids) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        try {


            URL urlconn = new URL(USGS_REQUEST_URL);

            connection = (HttpsURLConnection) urlconn.openConnection();
            connection.setReadTimeout(10000 /* milliseconds */);
            connection.setConnectTimeout(15000 /* milliseconds */);
            connection.setRequestMethod("GET");

            connection.connect();

            inputStream = connection.getInputStream();

            jsonResponse = readFromStream(inputStream);
            QueryUtils obj = new QueryUtils();
            obj.setText(jsonResponse);


        } catch (ProtocolException e1) {
            Log.e("QueryUtils", "Problem Connecting", e1);
        } catch (MalformedURLException e1) {
            Log.e("QueryUtils", "Problem Connecting", e1);
        } catch (IOException e1) {
            Log.e("QueryUtils", "Problem Connecting", e1);
        } finally {
            if (connection != null) {
                connection.disconnect();

            }

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


}