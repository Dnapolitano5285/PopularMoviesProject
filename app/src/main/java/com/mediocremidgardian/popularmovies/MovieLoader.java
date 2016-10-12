package com.mediocremidgardian.popularmovies;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.AsyncTaskLoader;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Valhalla on 10/11/16.
 */

public class MovieLoader extends AsyncTaskLoader<List<Movie>> {

    private List<Movie> mMovies;
    URL mUrl;

    public MovieLoader(Context context, String url) {
        super(context);

        try{
            mUrl = new URL(url);
        } catch (MalformedURLException e){
           //should be unreachable due to no user input for url
            Toast.makeText(getContext(),"Invalid Url",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public List<Movie> loadInBackground() {

        //check for internet connection
        ConnectivityManager cm =
                (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if(activeNetwork !=null && activeNetwork.isConnectedOrConnecting()){
            String jsonResponse = "";
            jsonResponse = makeHttpRequest(mUrl);

            mMovies = QueryUtils.extractMovies(jsonResponse);
        }

        return mMovies;
    }

    private String makeHttpRequest(URL mUrl) {
        return null;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
