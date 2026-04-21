package model.communication;

import java.io.Serializable;

public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    private String authorName;
    private String text;
    private String createdDate;

    public Comment() {
    }

    public Comment(String authorName, String text, String createdDate) {
        this.authorName = authorName;
        this.text = text;
        this.createdDate = createdDate;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getText() {
        return text;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Comment: authorName=" + authorName +
                ", text=" + text +
                ", createdDate=" + createdDate;
    }
}
