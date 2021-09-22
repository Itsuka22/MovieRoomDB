package com.example.movies_roomdb.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.movies_roomdb.data.model.Movie;

import java.util.List;

@Dao
public interface MoviesDao {

    @Query("SELECT * FROM Movie")
    List<Movie> getMoviesList();

    @Query("SELECT * FROM Movie")
    LiveData<List<Movie>> getAllMovies();

    @Query("SELECT * FROM Movie WHERE id = :id")
    boolean isMovieFavourite(long id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMovie(Movie movie);

    @Query("DELETE FROM Movie WHERE id = :id")
    void deleteMovie(long id);
}