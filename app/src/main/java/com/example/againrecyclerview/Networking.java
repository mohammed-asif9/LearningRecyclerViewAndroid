package com.example.againrecyclerview;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Networking extends AsyncTask<String,Void,String> {

    @Override
    protected String doInBackground(String... urls) {
        Log.d("dbAsif","Entered in the do in Background method "+ urls[0]);
        URL url;
        HttpURLConnection conn;
        String result = "";
        try{
            url = new URL(urls[0]);
            conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            InputStream in = conn.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);

            int data=reader.read();
            while(data!=-1){
                char ch = (char) data;
                result +=ch;
                data = reader.read();
            }




        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        Log.d("dbAsif","response is "+s);
    }
}
