package services;

import model.communication.Comment;
import model.communication.News;
import storage.DataStore;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NewsService {
    private DataStore dataStore;

    public NewsService() {
        this.dataStore = DataStore.getInstance();
    }

    public News createNews(String title, String content, String topic, boolean pinned, String createdDate) {
        News news = new News(title, content, topic, pinned, createdDate);
        dataStore.addNews(news);
        return news;
    }

    public News createPinnedResearchNews(String title, String content, String createdDate) {
        News news = new News(title, content, "Research", true, createdDate);
        dataStore.addNews(news);
        return news;
    }

    public void addComment(News news, Comment comment) {
        news.addComment(comment);
    }

    public List<News> getAllNews() {
        return dataStore.getNewsList();
    }

    public List<News> getSortedNewsPinnedFirst() {
        List<News> sortedNews = new ArrayList<>(dataStore.getNewsList());

        sortedNews.sort(Comparator
                .comparing(News::isPinned)
                .reversed()
                .thenComparing(News::getCreatedDate)
                .reversed());

        return sortedNews;
    }
}