package com.example.movies_roomdb.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.movies_roomdb.data.model.Movie;


public class OfflineDBBaseViewModel extends BaseViewModel {

    public MutableLiveData<Boolean> isMovieFavourite_liveData = new MutableLiveData<>();

    public OfflineDBBaseViewModel(@NonNull Application application) {
        super(application);
    }

    public void isMovieFavourite(long id) {
        getExecutor(() -> {
            boolean isFavourite = getDatabase().moviesDao().isMovieFavourite(id);
            isMovieFavourite_liveData.postValue(isFavourite);
        });
    }

    public void markMovieAsFavourite(Movie movie) {
        getExecutor(() -> {
            getDatabase().moviesDao().insertMovie(movie);
        });
    }

    public void removeMovieFromFavourites(long id) {
        getExecutor(() -> {
            getDatabase().moviesDao().deleteMovie(id);
        });
    }
}
