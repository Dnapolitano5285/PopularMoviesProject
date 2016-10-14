package com.mediocremidgardian.popularmovies;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Valhalla on 10/11/16.
 */

public final class QueryUtils {

    private static String LOG_TAG = QueryUtils.class.getName();

    public static final String MOVIEDB_POPULAR_URL = "https://api.themoviedb.org/3/movie/popular";
    public static final String MOVIEDB_TOP_RATES_URL = "https://api.themoviedb.org/3/movie/top_rated";
    public static final String API_KEY = "";

    private QueryUtils() {

    }


    public static ArrayList<Movie> extractMovies(String jsonResponse) {

        //array list we will add movies to as we parse the response
        ArrayList<Movie> movies = new ArrayList<>();

        //setup strings we will need  to create new movies
        String title, releaseDate, rating, description, poster;

        try {

            JSONObject data = new JSONObject(jsonResponse);
            JSONArray movieList = data.getJSONArray("results");

            for (int i = 0; i < movieList.length(); i++) {

                JSONObject movie = movieList.getJSONObject(i);

                title = movie.getString("title");
                releaseDate = movie.getString("release_date");
                rating = movie.getString("vote_average");
                description = movie.getString("overview");
                poster = movie.getString("poster_path");

                movies.add(new Movie(title, releaseDate, rating, description, poster));
            }


        } catch (JSONException e) {

            Log.e(LOG_TAG, "Problem parsing JSON", e);
        }

        return movies;
    }


}
