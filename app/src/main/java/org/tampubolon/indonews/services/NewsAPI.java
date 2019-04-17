package org.tampubolon.indonews.services;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface NewsAPI {

    /*Get Top Head Lines*/
    @GET("top-headlines")
    Call<ResponseBody> getNewsByCategory(@QueryMap Map<String, String> params);

}
