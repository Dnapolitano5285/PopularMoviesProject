package com.mediocremidgardian.popularmovies;

import java.util.List;

/**
 * Created by Valhalla on 10/11/16.
 */

public final class QueryUtils {

    private static final String MOVIEDB_POPULAR_URL = "https://api.themoviedb.org/3/movie/popular";
    private static final String MOVIEDB_TOP_RATES_URL = "https://api.themoviedb.org/3/movie/top_rated";
    private static final String API_KEY ="b581ea78f98eaf9a41d6c35ec909b481";

    private QueryUtils(){

    }


    public static List<Movie> extractMovies(String jsonResponse) {
        return null;
    }
}
