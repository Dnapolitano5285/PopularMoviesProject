package com.mediocremidgardian.popularmovies;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragment extends Fragment {

    ImageView mPoster;
    TextView mTitle;
    TextView mYear;
    TextView mRating;
    TextView mDescription;


    public MovieDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        mPoster = (ImageView)rootView.findViewById(R.id.detail_poster_imageview);
        mTitle = (TextView) rootView.findViewById(R.id.title_detail_fragment);
        mYear = (TextView)rootView.findViewById(R.id.year_detail_textview);
        mRating = (TextView)rootView.findViewById(R.id.rating_detail_textview);
        mDescription = (TextView)rootView.findViewById(R.id.description_detail_fragment);

        Bundle bundle = getArguments();
        String title = bundle.getString("title");
        String year = bundle.getString("year");
        String rating = bundle.getString("rating");
        String description = bundle.getString("description");
        String posterPath = bundle.getString("posterpath");

        String[] yearPart = year.split("-");

        Picasso.with(getActivity()).load(posterPath).into(mPoster);
        mTitle.setText(title);
        mYear.setText(yearPart[0]);
        mRating.setText(rating + "/10");
        mDescription.setText(description);

        return rootView;
    }

}
