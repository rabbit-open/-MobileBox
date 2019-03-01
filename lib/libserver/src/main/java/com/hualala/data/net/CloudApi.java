package com.hualala.data.net;

import com.hualala.data.entity.reponse.MBVideoListResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface CloudApi {

    @Headers({"Accept:application/json"})
    @GET("/files")
    Observable<MBVideoListResponse> getVideoList(@Query("format") String name);

}
