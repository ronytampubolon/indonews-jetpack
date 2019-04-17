package org.tampubolon.indonews.model.response;

import org.tampubolon.indonews.model.News;

import java.util.List;

public class ListNewsReponse {
    private String status;
    private int totalResults;
    private List<News> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<News> getArticles() {
        return articles;
    }

    public void setArticles(List<News> articles) {
        this.articles = articles;
    }

    public ListNewsReponse() {

    }
}
