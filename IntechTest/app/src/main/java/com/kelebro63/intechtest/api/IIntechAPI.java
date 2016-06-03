package com.kelebro63.intechtest.api;


import com.kelebro63.intechtest.models.Melody;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by kelebro63 on 02.06.2016
 */
public interface IIntechAPI {

    @FormUrlEncoded
    @POST("public/marketplaces/1/tags/4/melodies")
    Observable<Melody> getSongsList(@Field("limit") int limit, @Field("offset") int offset);

}
