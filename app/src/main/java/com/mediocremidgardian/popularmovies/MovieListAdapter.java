package com.mediocremidgardian.popularmovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Valhalla on 10/11/16.
 */

public class MovieListAdapter extends ArrayAdapter<Movie> {

    //global variables to increase readability of code in later methods
    Context mContext;
    LayoutInflater mInflater;
    List<Movie> mMovies;
    int resourceId;


    public MovieListAdapter(Context context, int resource, List<Movie> objects) {
        super(context, resource, objects);

        mContext = context;
        mInflater = LayoutInflater.from(getContext());
        mMovies = objects;
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView;

        if(convertView == null){
            imageView = (ImageView) mInflater.inflate(resourceId, parent, false);
        } else {
            imageView = (ImageView) convertView;
        }

        Picasso.with(mContext).load(mMovies.get(position).getPosterUrl()).into(imageView);

        return imageView;
    }
}
