package com.example.movies_roomdb.data.remote;

import com.example.movies_roomdb.data.ApiResponse;
import com.example.movies_roomdb.data.Client;
import com.example.movies_roomdb.data.ResponseWrapper;
import com.example.movies_roomdb.data.Services;
import com.example.movies_roomdb.data.model.Movie;
import com.example.movies_roomdb.utils.Utils;

import java.util.List;


public class SearchMoviesRemote {

    public void searchMovie(int pageNum,String query, BaseCallback errCallback, SearchMovieCallback callback) {

        Client.getInstance().create(Services.class).searchMovie(Utils.API_KEY, pageNum,query)
                .enqueue(new ResponseWrapper<ApiResponse<List<Movie>>>(errCallback) {
                    @Override
                    public void onSuccessCase(ApiResponse<List<Movie>> body) {
                        callback.onCallback(body);
                    }
                });
    }

    public interface SearchMovieCallback {
        void onCallback(ApiResponse<List<Movie>> body);
    }
}
