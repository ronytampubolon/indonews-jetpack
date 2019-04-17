package org.tampubolon.indonews.repository;

import android.util.Log;

import com.google.gson.Gson;

import org.tampubolon.indonews.config.Main;
import org.tampubolon.indonews.model.News;
import org.tampubolon.indonews.model.response.ListNewsReponse;
import org.tampubolon.indonews.services.APIService;
import org.tampubolon.indonews.services.NewsAPI;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {
    private NewsAPI web_service;

    public NewsRepository(){
        web_service = APIService.getClient().create(NewsAPI.class);
    }

    public LiveData<List<News>> getNews(final String category) {
        final MutableLiveData<List<News>> data = new MutableLiveData<>();
        Map<String, String> params = new HashMap<String, String>() {
            {
                put("country", Main.COUNTRY);
                put("category", category);
                put("apiKey", Main.API_KEY);

            }
        };
        web_service.getNewsByCategory(params).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()) {
                    try {
                        String _data = response.body().string();
                        Log.d("JSON",_data);
                        Gson mGson = new Gson();
                        ListNewsReponse response_data = mGson.fromJson(_data,ListNewsReponse.class);
                        if(response_data!=null){
                            data.setValue(response_data.getArticles());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    Log.d("error","Error Parsing");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });
        return data;
    }

}
