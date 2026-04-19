package model.communication;

import java.util.ArrayList;
import java.util.List;

public class News {
    private String title;
    private String content;
    private String topic;
    private boolean pinned;
    private String createdDate;
    private List<Comment> comments;

    public News() {
        comments = new ArrayList<>();
    }

    public News(String title, String content, String topic, boolean pinned, String createdDate) {
        this.title = title;
        this.content = content;
        this.topic = topic;
        this.pinned = pinned;
        this.createdDate = createdDate;
        this.comments = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getTopic() {
        return topic;
    }

    public boolean isPinned() {
        return pinned;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setPinned(boolean pinned) {
        this.pinned = pinned;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public String toString() {
        return "News: title=" + title +
                ", topic=" + topic +
                ", pinned=" + pinned +
                ", createdDate=" + createdDate +
                ", comments=" + comments.size();
    }
}