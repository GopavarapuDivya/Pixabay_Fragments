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
public class TeddyBearFragment extends Fragment implements LoaderManager.LoaderCallbacks<String>
{
    RecyclerView rv2;
    ProgressBar pb2;
    ArrayList<ModelClass> modelClasses;
    String imgurl="https://pixabay.com/api/?key=10850293-beb2bf524b904f5509c8eb1b8&q=teddybear";
    public static  final  int Loader_id=12;

    public TeddyBearFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View v=inflater.inflate(R.layout.fragment_teddy_bear, container, false);
         rv2=v.findViewById(R.id.recycletwo);
         pb2=v.findViewById(R.id.progresstwo);
         modelClasses=new ArrayList<>();
        getLoaderManager().initLoader(Loader_id,null,this);
        return v;
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new AsyncTaskLoader<String>(getContext()) {
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
                pb2.setVisibility(View.VISIBLE);
                forceLoad();
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s)
    {
        pb2.setVisibility(View.GONE);

        if (s!=null)
        {
            try
            {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("hits");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject hits = jsonArray.getJSONObject(i);
                    String image = hits.getString("largeImageURL");
                    Log.i("imageurl",image);
                    modelClasses.add(new ModelClass(image));
                }
                rv2.setLayoutManager(new LinearLayoutManager(getActivity()));
                rv2.setAdapter(new TeddyBearAdapter(getActivity(),modelClasses));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader)
    {

    }

    /*class Pixabayapi extends AsyncTask<String,Void,String>
    {


        @Override
        protected void onPreExecute() {


            super.onPreExecute();
            pb2.setVisibility(View.VISIBLE);
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
        protected void onPostExecute(String s)
        {

            super.onPostExecute(s);
            pb2.setVisibility(View.GONE);

            if (s!=null)
            {
                try
                {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArray = jsonObject.getJSONArray("hits");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject hits = jsonArray.getJSONObject(i);
                        String image = hits.getString("largeImageURL");
                        Log.i("imageurl",image);
                        modelClasses.add(new ModelClass(image));
                        }
                    rv2.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv2.setAdapter(new TeddyBearAdapter(getActivity(),modelClasses));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }


    }*/


}
