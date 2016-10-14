package com.mediocremidgardian.popularmovies;


import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieListFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<List<Movie>> {

    MovieListAdapter mAdapter;
    List<Movie> mMovies;
    GridView movieGridView;
    int mSpinnerValue = 0;

    public MovieListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_movie_list, container, false);

        //set array adapter - will be empty at first and will swap when we query
        mMovies = new ArrayList<>();
        mAdapter = new MovieListAdapter(getActivity(), R.layout.grid_imageview, mMovies);

        movieGridView = (GridView) rootView.findViewById(R.id.movie_gridview);
        movieGridView.setAdapter(mAdapter);


        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        getLoaderManager().initLoader(1, null, this);
    }

    @Override
    public Loader<List<Movie>> onCreateLoader(int id, Bundle bundle) {

        Uri baseUri = null;

        if (mSpinnerValue == 0) {
            //create the uri which will send as string to create Loader
            baseUri = Uri.parse(QueryUtils.MOVIEDB_POPULAR_URL);
        } else if (mSpinnerValue == 1) {
            baseUri = Uri.parse(QueryUtils.MOVIEDB_TOP_RATES_URL);
        }
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("api_key", QueryUtils.API_KEY);

        return new MovieLoader(getActivity(), uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> movies) {

        mMovies = movies;

        if (mMovies != null) {
            mAdapter.clear();
            mAdapter.addAll(mMovies);
            mAdapter.notifyDataSetChanged();
        }

    }


    @Override
    public void onLoaderReset(Loader loader) {

        mAdapter.clear();
        getLoaderManager().getLoader(1).reset();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.sort_spinner, menu);

        Spinner spinner = (Spinner) menu.findItem(R.id.spinner).getActionView();

        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.array_sort_spinner,
                R.layout.spinner_textview);

        adapter.setDropDownViewResource(R.layout.spinner_textview);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                mSpinnerValue = position;
                getLoaderManager().restartLoader(1, null, MovieListFragment.this);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        {

        }
    }
}
