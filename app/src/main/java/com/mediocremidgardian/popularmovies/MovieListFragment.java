package com.mediocremidgardian.popularmovies;


import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

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

    public MovieListFragment() {
        // Required empty public constructor
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
        //TODO shared preferences to decide sort order

        //create the uri which will send as string to create Loader
        Uri baseUri = Uri.parse(QueryUtils.MOVIEDB_POPULAR_URL);
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

        movieGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                String title = mMovies.get(position).getTitle();
                String year = mMovies.get(position).getYear();
                String rating = mMovies.get(position).getRating();
                String description = mMovies.get(position).getDescription();
                String posterPath = mMovies.get(position).getPosterUrl();

                Bundle bundle = new Bundle();
                bundle.putString("title",title);
                bundle.putString("year",year);
                bundle.putString("rating",rating);
                bundle.putString("description", description);
                bundle.putString("posterpath",posterPath);


                Log.e("asdfasdfasdfasdf","loading fragment");
                Fragment fragment = new MovieDetailFragment();
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction()
                        .add(R.id.activity_main_container, fragment).commit();
            }
        });
    }


    @Override
    public void onLoaderReset(Loader loader) {

        mAdapter.clear();
        getLoaderManager().getLoader(1).reset();

    }
}
