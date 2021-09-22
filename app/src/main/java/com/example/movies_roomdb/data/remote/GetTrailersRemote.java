package com.example.movies_roomdb.data.remote;

import com.example.movies_roomdb.data.Client;
import com.example.movies_roomdb.data.Services;
import com.example.movies_roomdb.data.model.Trailers;
import com.example.movies_roomdb.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GetTrailersRemote {

    public void getTrailers(long movieId, BaseCallback errCallback, GetTrailersCallback callback) {

        Client.getInstance().create(Services.class).getTrailers(movieId,Utils.API_KEY)
                .enqueue(new Callback<Trailers>() {
                    @Override
                    public void onResponse(Call<Trailers> call, Response<Trailers> response) {
                        callback.onCallback(response.body());
                    }

                    @Override
                    public void onFailure(Call<Trailers> call, Throwable t) {
                        errCallback.onDataNotAvailable(null, null);
                    }
                });
    }

    public interface GetTrailersCallback {
        void onCallback(Trailers body);
    }
}
