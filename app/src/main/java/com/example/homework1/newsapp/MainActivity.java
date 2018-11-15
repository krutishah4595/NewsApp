package com.example.homework1.newsapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NewsRecyclerViewAdapter adapter;
    String newsStoriesJson = "";
    ProgressDialog pd;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.news_recyclerview);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        new NewsQueryTask().execute();

        recyclerView.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {
            new NewsQueryTask().execute();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public class NewsQueryTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(MainActivity.this);
            pd.setMessage("Fetching News..");
            pd.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                newsStoriesJson = NetworkUtils.getResponseFromHttpUrl
                        (new URL(NetworkUtils.buildURL(NetworkUtils.BASEURL,
                                NetworkUtils.APIKEY)));
            } catch (IOException e) {
                e.printStackTrace();
                pd.dismiss();
            }
            return newsStoriesJson;
        }

        @Override
        protected void onPostExecute(String Json) {
            super.onPostExecute(Json);
            pd.dismiss();

            if (JsonUtils.parseNews(Json).size() != 0) {
                adapter = new NewsRecyclerViewAdapter(MainActivity.this,
                        JsonUtils.parseNews(Json));
                recyclerView.setAdapter(adapter);
            }
        }
    }

}
