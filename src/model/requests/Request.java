package model.requests;

import enums.RequestStatus;
import enums.UrgencyLevel;

import java.io.Serializable;

public class Request implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String TYPE_REQUEST = "REQUEST";
    public static final String TYPE_COMPLAINT = "COMPLAINT";

    private String id;
    private String authorLogin;
    private String text;
    private String requestType;
    private RequestStatus status;
    private UrgencyLevel urgencyLevel;
    private String createdDate;

    public Request() {
        this.requestType = TYPE_REQUEST;
        this.status = RequestStatus.NEW;
    }

    public Request(String id, String authorLogin, String text,
                   UrgencyLevel urgencyLevel, String createdDate) {
        this.id = id;
        this.authorLogin = authorLogin;
        this.text = text;
        this.requestType = TYPE_REQUEST;
        this.urgencyLevel = urgencyLevel;
        this.createdDate = createdDate;
        this.status = RequestStatus.NEW;
    }

    public Request(String id, String authorLogin, String text, String requestType,
                   UrgencyLevel urgencyLevel, String createdDate) {
        this.id = id;
        this.authorLogin = authorLogin;
        this.text = text;
        this.requestType = requestType;
        this.urgencyLevel = urgencyLevel;
        this.createdDate = createdDate;
        this.status = RequestStatus.NEW;
    }

    public String getId() {
        return id;
    }

    public String getAuthorLogin() {
        return authorLogin;
    }

    public String getText() {
        return text;
    }

    public String getRequestType() {
        if (requestType == null) {
            return TYPE_REQUEST;
        }

        return requestType;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public UrgencyLevel getUrgencyLevel() {
        return urgencyLevel;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAuthorLogin(String authorLogin) {
        this.authorLogin = authorLogin;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public void setUrgencyLevel(UrgencyLevel urgencyLevel) {
        this.urgencyLevel = urgencyLevel;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void markViewed() {
        status = RequestStatus.VIEWED;
    }

    public void accept() {
        status = RequestStatus.ACCEPTED;
    }

    public void reject() {
        status = RequestStatus.REJECTED;
    }

    public void markDone() {
        status = RequestStatus.DONE;
    }

    @Override
    public String toString() {
        return "Request: id=" + id +
                ", authorLogin=" + authorLogin +
                ", type=" + getRequestType() +
                ", text=" + text +
                ", urgencyLevel=" + urgencyLevel +
                ", status=" + status +
                ", createdDate=" + createdDate;
    }
}
