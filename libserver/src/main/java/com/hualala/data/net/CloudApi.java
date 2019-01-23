package com.hualala.data.net;

import com.hualala.data.entity.reponse.MBVideoListResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface CloudApi {

    @Headers({"Accept:application/json"})
    @GET("api/v1/video/list")
    Observable<MBVideoListResponse> getVideoList();

}
