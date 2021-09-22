package com.example.movies_roomdb.data.remote;

import com.example.movies_roomdb.data.Client;
import com.example.movies_roomdb.data.Services;
import com.example.movies_roomdb.data.model.Genres;
import com.example.movies_roomdb.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GetGenresRemote {

    public void getGenres(BaseCallback errCallback, OnCallback callback) {

        Client.getInstance().create(Services.class).getGenres(Utils.API_KEY)
                .enqueue(new Callback<Genres>() {
                    @Override
                    public void onResponse(Call<Genres> call, Response<Genres> response) {
                        callback.onCallback(response.body());
                    }

                    @Override
                    public void onFailure(Call<Genres> call, Throwable t) {
                        errCallback.onDataNotAvailable(null, null);
                    }
                });
    }

    public interface OnCallback {
        void onCallback(Genres body);
    }
}
