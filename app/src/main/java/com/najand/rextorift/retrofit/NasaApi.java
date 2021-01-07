package com.najand.rextorift.retrofit;

import com.najand.rextorift.model.Items;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface NasaApi {
    @GET("apod?api_key=eOgSrOHLEGHLxl3q5MSers1vzRqHORcdSUmCUO2J&start_date=2020-07-02&end_date=2020-07-20")
    Observable<List<Items>> getItems();
}
