package com.mediocremidgardian.popularmovies;

/**
 * Created by Valhalla on 10/11/16.
 */

public class Movie {

    //variables we need to populate the list view as well as details screen
    String title;
    String year;
    String runtime;
    String rating;
    String description;
    String posterUrl;

    //needed for future features
    String trailerUrl;


    //Constructor without trailer url for now - will add for next step of project
    public Movie(String title, String year, String runtime, String rating, String description, String posterUrl) {
        this.title = title;
        this.year = year;
        this.runtime = runtime;
        this.rating = rating;
        this.description = description;
        this.posterUrl = posterUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }
}
