package com.vmax.nearby;

import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class FetchFromServerTask extends AsyncTask<String, Void, String> {
    private int id;
    FetchFromServerUser user;
    String lat, lon, kind;

    public FetchFromServerTask(FetchFromServerUser user, int id, String lat, String lon, String kind) {
        this.user = user;
        this.id = id;
        this.kind = kind;
        this.lat = lat;
        this.lon = lon;
    }


    private String createUrl() throws UnsupportedEncodingException {

        String lati = URLEncoder.encode(lat, "utf-8");
        String longi = URLEncoder.encode(lon, "utf-8");
        String kinda = URLEncoder.encode(kind, "utf-8");
        return "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + lati + "," + longi + "&rankby=distance&types=" + kinda + "&key=" + "AIzaSyDKv97jTLfbhTRIFjpOtojS4cKWEHG95nY";
    }
    protected void onPreExecute() {
        super.onPreExecute();

    }

    protected String doInBackground(String... params)  {

        String link = null;
        try {
            link = createUrl();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            URL url = new URL(link);
            InputStream is = url.openConnection().getInputStream();
            StringBuffer buffer = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            Log.e("Why data ", buffer.toString());
            return buffer.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(String string) {
        Log.e("Data server json", string);
       user.onFetchCompletion(string,0);
    }
    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
