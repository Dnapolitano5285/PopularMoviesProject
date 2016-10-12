package com.mediocremidgardian.popularmovies;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Popular Movies");

        //if we are not returning to app or restoring
        //state then we create new movie list fragment from scratch
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.activity_main_container, new MovieListFragment())
                    .commit();
        }


    }
}
