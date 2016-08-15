package com.example.jamesrondina.gson_hw;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";

    private static String mUrl = "https://api.thingspeak.com/channels/124877/fields/1.json?results=200";

    private DataAsyncTask mTask;
    private ArrayAdapter<Datapoint> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new ArrayAdapter<Datapoint>(this,android.R.layout.simple_list_item_2,
                android.R.id.text1, new ArrayList<Datapoint>());

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(mAdapter);


        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            Toast.makeText(MainActivity.this, "Connected", Toast.LENGTH_SHORT).show();

            if (mTask != null && (mTask.getStatus() != AsyncTask.Status.FINISHED)) {
                mTask.cancel(true);
            }
            mTask = new DataAsyncTask();
            mTask.execute(mUrl);
        } else {
            Toast.makeText(MainActivity.this, "No network connection detected", Toast.LENGTH_SHORT).show();
        }


    }

    public class DataAsyncTask extends AsyncTask<String, Void, DataList> {

        @Override
        protected DataList doInBackground(String... params) {
            DataList result = null;

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(params[0])
                    .build();
            
            try {
                Response response = client.newCall(request).execute();

                Log.i(TAG, "doInBackground: " + response.toString());

                Gson gson = new Gson();

                result = gson.fromJson(response.body().string(), DataList.class);

                Log.i(TAG, "doInBackground: " + result.toString());

            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(DataList list) {
            super.onPostExecute(list);
            mAdapter.clear();
            mAdapter.addAll(list.getFeeds());
        }
    }
}
