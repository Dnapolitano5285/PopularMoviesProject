package com.mediocremidgardian.popularmovies;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by Valhalla on 10/11/16.
 */

public class MovieListAdapter extends ArrayAdapter<Movie> {


    public MovieListAdapter(Context context, int resource, List<Movie> objects) {
        super(context, resource, objects);
    }


}
