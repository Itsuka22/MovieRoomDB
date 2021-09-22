package com.example.movies_roomdb.favouritesDetailed;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import com.example.han.PopularMovies_ContentProvider.R;
import com.example.movies_roomdb.base.BaseActivity;

public class FavouritesDetailedActivity extends BaseActivity<FavouritesDetailedViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites_detailed);

        Bundle extras = getIntent().getExtras();
        if (savedInstanceState == null) {
            FavouritesDetailedActivityFragment favouritesDetailedActivityFragment = new FavouritesDetailedActivityFragment();

            favouritesDetailedActivityFragment.setArguments(extras);
            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout1, favouritesDetailedActivityFragment).commit();
        }
    }

    @Override
    public FavouritesDetailedViewModel getViewModel() {
        return new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(FavouritesDetailedViewModel.class);
    }
}
