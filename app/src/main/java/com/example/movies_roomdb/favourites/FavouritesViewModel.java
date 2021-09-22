package com.example.movies_roomdb.favourites;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.movies_roomdb.base.BaseViewModel;
import com.example.movies_roomdb.data.model.Movie;

import java.util.List;

public class FavouritesViewModel extends BaseViewModel {

    public MutableLiveData<List<Movie>> movies_liveData=new MutableLiveData<>();

    public FavouritesViewModel(@NonNull Application application) {
        super(application);
    }

    public void getAllFavourites() {
        getExecutor(() -> {
            List<Movie> moviesList = getDatabase().moviesDao().getMoviesList();
            movies_liveData.postValue(moviesList);
        });
    }
}
