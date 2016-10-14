package com.mediocremidgardian.popularmovies;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by Valhalla on 10/11/16.
 */

public class MovieLoader extends AsyncTaskLoader<List<Movie>> {

    private static final String LOG_TAG = MovieLoader.class.getName();

    private List<Movie> mMovies;
    URL mUrl;

    public MovieLoader(Context context, String url) {
        super(context);

        try {
            mUrl = new URL(url);
        } catch (MalformedURLException e) {
            //should be unreachable due to no user input for url
            Toast.makeText(getContext(), "Invalid Url", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public List<Movie> loadInBackground() {

        //check for internet connection
        ConnectivityManager cm =
                (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
            String jsonResponse = "";
            jsonResponse = makeHttpRequest(mUrl);

            mMovies = QueryUtils.extractMovies(jsonResponse);
        }

        return mMovies;
    }

    private String makeHttpRequest(URL url) {

        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        //try to read from the url and open input stream to pull down data
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }

        } catch (IOException e) {
            Log.e(LOG_TAG, "Error making connection", e);
            jsonResponse = null;

        } finally {
            //close the connection and the input stream to avoid data leaks
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {

                try {
                    inputStream.close();
                } catch (IOException e) {
                    Log.e(LOG_TAG, "Error closing connection", e);
                }
            }
        }
        return jsonResponse;
    }

    private String readFromStream(InputStream inputStream) throws IOException {

        StringBuilder output = new StringBuilder();

        if (inputStream != null) {

            InputStreamReader inputStreamReader =
                    new InputStreamReader(inputStream, Charset.forName("UTF-8"));

            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();

            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }

        }
        return output.toString();
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
