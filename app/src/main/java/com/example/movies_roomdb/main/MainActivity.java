package com.example.movies_roomdb.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;

import com.example.han.PopularMovies_ContentProvider.R;
import com.example.movies_roomdb.Communicator;
import com.example.movies_roomdb.base.BaseActivity;
import com.example.movies_roomdb.data.model.Movie;
import com.example.movies_roomdb.favourites.FavouritesMainActivityFragment;
import com.example.movies_roomdb.detailed.DetailedActivity;
import com.example.movies_roomdb.detailed.DetailedActivityFragment;
import com.example.movies_roomdb.favouritesDetailed.FavouritesDetailedActivity;
import com.example.movies_roomdb.favouritesDetailed.FavouritesDetailedActivityFragment;
//import com.example.popularmovies_roomdb.search.SearchActivityFragment;
//import com.example.popularmovies_roomdb.topRated.TopRated_ActivityFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends BaseActivity<MainViewModel> implements Communicator {

    boolean twoPane, isFavouritesOpened;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout frameLayout2 = (FrameLayout) findViewById(R.id.framelayout2);
        if (frameLayout2 != null)
            twoPane = true;
        else
            twoPane = false;

        //bottom navigation
        BottomNavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(selectedListener);

        startMainActivityFragment();
    }

    private void startMainActivityFragment() {
        MainActivityFragment mainActivityFragment = new MainActivityFragment();
        mainActivityFragment.setMovieCommunicator(this);
        replaceFragment(R.id.framelayout1, mainActivityFragment, "now_playing");
    }

//    private void startTopRatedFragment() {
//        TopRated_ActivityFragment topRated_activityFragment = new TopRated_ActivityFragment();
//        topRated_activityFragment.setMovieCommunicator(this);
//        replaceFragment(R.id.framelayout1, topRated_activityFragment, "top_rated");
//    }

//    private void startSearchFragment() {
//        SearchActivityFragment searchActivityFragment=new SearchActivityFragment();
//        searchActivityFragment.setMovieCommunicator(this);
//        replaceFragment(R.id.framelayout1, searchActivityFragment, "search");
//    }

    private void startFavouritesFragment() {
        FavouritesMainActivityFragment favouritesMainActivityFragment = new FavouritesMainActivityFragment();
        favouritesMainActivityFragment.setMovieCommunicator(this);
        replaceFragment(R.id.framelayout1, favouritesMainActivityFragment, "favourites");
    }

    private BottomNavigationView.OnNavigationItemSelectedListener selectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    //handle item clicks
                    switch (menuItem.getItemId()) {
                        case R.id.now_playing:
                            setTitle("Now Playing");
                            startMainActivityFragment();
                            isFavouritesOpened = false;
                            return true;
//                        case R.id.top_rated:
//                            setTitle("Top Rated");
//                            startTopRatedFragment();
//                            isFavouritesOpened = false;
//                            return true;
//                        case R.id.search:
//                            setTitle("Search");
//                            startSearchFragment();
//                            isFavouritesOpened = false;
//                            return true;
                        case R.id.favourites:
                            setTitle("Favourites");
                            startFavouritesFragment();
                            isFavouritesOpened = true;
                            return true;
                    }
                    return false;
                }
            };

    @Override
    public MainViewModel getViewModel() {
        return new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(MainViewModel.class);
    }

    @Override
    public void setMovie(Movie movie) {
        if (twoPane) {
            if (isFavouritesOpened) {
                FavouritesDetailedActivityFragment favouritesDetailedActivityFragment = new FavouritesDetailedActivityFragment();
                Bundle extras = new Bundle();
                extras.putParcelable("movie", movie);   //we put it in first Activity (mainActivity) to pass value to detailedActivity by using Bundle
                extras.putIntegerArrayList("genres",movie.genre_ids);
                favouritesDetailedActivityFragment.setArguments(extras);
                replaceFragment(R.id.framelayout2, favouritesDetailedActivityFragment, "favDetailed");
            } else {
                DetailedActivityFragment detailedActivityFragment = new DetailedActivityFragment();
                Bundle extras = new Bundle();
                extras.putParcelable("movie", movie);   //we put it in first Activity (mainActivity) to pass value to detailedActivity by using Bundle
                extras.putIntegerArrayList("genres",movie.genre_ids);
                detailedActivityFragment.setArguments(extras);
                replaceFragment(R.id.framelayout2, detailedActivityFragment, "detailed");
            }
        } else {
            if (isFavouritesOpened) {
                Intent intent = new Intent(this, FavouritesDetailedActivity.class);
                intent.putExtra("movie", movie);   //we put it in first Activity (mainActivity) to pass value to detailedActivity by using Extras
                intent.putIntegerArrayListExtra("genres",movie.genre_ids);
                startActivity(intent);

            } else {
                Intent intent = new Intent(this, DetailedActivity.class);
                intent.putExtra("movie", movie);   //we put it in first Activity (mainActivity) to pass value to detailedActivity by using Extras
                intent.putIntegerArrayListExtra("genres",movie.genre_ids);
                startActivity(intent);
            }
        }
    }

    @Override
    public void setMoviePosition(int position) {
        // i used this method with FavouritesActivity
    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this) ;
        builder.setIcon(R.drawable.ic_baseline_exit_to_app_24);
        builder.setTitle("Setuju Keluar");
        builder.setMessage("Apakah Kamu ingin Keluar?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("Batal",null);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
