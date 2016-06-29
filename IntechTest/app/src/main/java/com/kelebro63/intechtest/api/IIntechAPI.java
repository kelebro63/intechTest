package com.kelebro63.intechtest.api;


import com.kelebro63.intechtest.models.ResponseMelody;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by kelebro63 on 02.06.2016
 */
public interface IIntechAPI {


    @GET("tracks")
    Observable<ResponseMelody> getSongsList(@Query("client_id") String client_id, @Query("limit") int limit, @Query("linked_partitioning") int offset);
}
