package com.example.movies_roomdb;

import com.example.movies_roomdb.data.model.Movie;

public interface Communicator{
    void setMovie(Movie movie);
    void setMoviePosition(int position);
}

