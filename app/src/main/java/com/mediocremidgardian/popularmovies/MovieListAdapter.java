package com.mediocremidgardian.popularmovies;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        ImageView imageView;

        if (convertView == null) {
            imageView = (ImageView) mInflater.inflate(resourceId, parent, false);
        } else {
            imageView = (ImageView) convertView;
        }

        Picasso.with(mContext).load(mMovies.get(position).getPosterUrl()).into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String title = mMovies.get(position).getTitle();
                String year = mMovies.get(position).getYear();
                String rating = mMovies.get(position).getRating();
                String description = mMovies.get(position).getDescription();
                String posterPath = mMovies.get(position).getPosterUrl();

                Bundle bundle = new Bundle();
                bundle.putString("title", title);
                bundle.putString("year", year);
                bundle.putString("rating", rating);
                bundle.putString("description", description);
                bundle.putString("posterpath", posterPath);

                Fragment fragment = new MovieDetailFragment();
                fragment.setArguments(bundle);

                FragmentManager manager = ((Activity) mContext).getFragmentManager();

                manager.beginTransaction()
                        .replace(R.id.activity_main_container, fragment).addToBackStack(null).commit();
            }
        });

        return imageView;
    }
}
