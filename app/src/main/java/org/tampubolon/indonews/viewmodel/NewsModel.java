package org.tampubolon.indonews.viewmodel;

import org.tampubolon.indonews.model.News;
import org.tampubolon.indonews.repository.NewsRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class NewsModel extends ViewModel {
    /*Define Attribute*/
    private NewsRepository repo;
    LiveData<List<News>> list_news;

    public void init(NewsRepository repo){
        this.repo  = repo;
    }

    public LiveData<List<News>> getNewsByCategory(String categ){
        list_news = this.repo.getNews(categ);
        return this.list_news;
    }

}
