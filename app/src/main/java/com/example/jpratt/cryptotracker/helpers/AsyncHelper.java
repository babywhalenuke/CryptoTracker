package com.example.jpratt.cryptotracker.helpers;

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

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by jpratt on 10/29/2017.
 */

public class AsyncHelper {

    ConnectionType connectionType;
    RequestType requestType;

    public AsyncHelper(ConnectionType connectionType, RequestType requestType){
        this.connectionType = connectionType;
        this.requestType = requestType;
    }

    public String getData(String url){
        try {
            return new MyAsyncTask().execute(url).get();
        } catch (Exception e){
            Log.e("TEST", "");
        }

        return "";
    }

    private class MyAsyncTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            String stringURL = strings[0] != null ? strings[0] : "";
            String result = "";
            HttpURLConnection conn;
            BufferedReader reader;

            try {
                URL url = new URL(stringURL);
                conn = connectionType == ConnectionType.HttpsConnection ?
                        (HttpsURLConnection) url.openConnection() :
                        (HttpURLConnection) url.openConnection();
                conn.setRequestMethod(requestType == requestType.GET ? "GET" : "POST");
                conn.connect();
                // Read the input stream into a String
                InputStream inputStream = conn.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Oops nothing returned
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                return buffer.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }
    }
}
