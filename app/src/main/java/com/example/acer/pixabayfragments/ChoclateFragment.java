package com.example.acer.pixabayfragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChoclateFragment extends Fragment implements LoaderManager.LoaderCallbacks<String>
{
    RecyclerView rv1;
    ProgressBar pb1;
    ArrayList<ModelClass> models;
    String imgurl="https://pixabay.com/api/?key=10850293-beb2bf524b904f5509c8eb1b8&q=chocolates";
    public static  final  int Loader_id=12;


    public ChoclateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_choclate, container, false);
        rv1=v.findViewById(R.id.recycleone);
        pb1=v.findViewById(R.id.progressone);
        models=new ArrayList<>();
        //new PixabayAPi().execute();
        getLoaderManager().initLoader(Loader_id,null,this);
        return v;
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle)
    {
        return new AsyncTaskLoader<String>(getContext())
        {
            @Nullable
            @Override
            public String loadInBackground()
            {
                try {
                    URL url = new URL(imgurl);
                    Log.i("imageurl", String.valueOf(url));
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.connect();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    Scanner scanner = new Scanner(inputStream);
                    scanner.useDelimiter("\\A");
                    if (scanner.hasNext()) {
                        return scanner.next();
                    } else {
                        return null;
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }
            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                pb1.setVisibility(View.VISIBLE);
                forceLoad();
            }

        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s)
    {
        pb1.setVisibility(View.GONE);

        if (s!=null){
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("hits");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject hits = jsonArray.getJSONObject(i);
                    String imageurl = hits.getString("largeImageURL");
                    Log.i("imageurl",imageurl);
                    models.add(new ModelClass(imageurl));

                }
                rv1.setLayoutManager(new LinearLayoutManager(getActivity()));
                rv1.setAdapter(new ChoclateAdapter(getActivity(),models));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    /*class PixabayAPi extends AsyncTask<String,Void,String> {


        @Override
        protected void onPreExecute()
        {

            super.onPreExecute();
            pb1.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                URL url = new URL(imgurl);
                Log.i("imageurl", String.valueOf(url));
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                InputStream inputStream = httpURLConnection.getInputStream();
                Scanner scanner = new Scanner(inputStream);
                scanner.useDelimiter("\\A");
                if (scanner.hasNext()) {
                    return scanner.next();
                } else {
                    return null;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);
            pb1.setVisibility(View.GONE);

            if (s!=null){
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArray = jsonObject.getJSONArray("hits");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject hits = jsonArray.getJSONObject(i);
                        String imageurl = hits.getString("largeImageURL");
                        Log.i("imageurl",imageurl);
                        models.add(new ModelClass(imageurl));

                    }
                    rv1.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv1.setAdapter(new ChoclateAdapter(getActivity(),models));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }


    }*/

}
