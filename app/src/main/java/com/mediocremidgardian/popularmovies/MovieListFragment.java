package com.mediocremidgardian.popularmovies;


import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieListFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<List<Movie>> {

    MovieListAdapter mAdapter;
    ArrayList<Movie> mMovies;
    ListView movieListView;

    public MovieListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_movie_list,container,false);

        //set array adapter - will be empty at first and will swap when we query
        mMovies = new ArrayList<>();
        mAdapter = new MovieListAdapter(getActivity(),R.layout.list_item_movies,mMovies);

        movieListView = (ListView) rootView.findViewById(R.id.listview_movies);
        movieListView.setAdapter(mAdapter);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        getLoaderManager().initLoader(1,null,this);
    }

    @Override
    public Loader onCreateLoader(int i, Bundle bundle) {
        //TODO shared preferences to decide sort order

        return null;
    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> movies) {

    }


    @Override
    public void onLoaderReset(Loader loader) {

    }
}
