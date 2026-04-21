package services;

import enums.UrgencyLevel;
import model.requests.Request;
import storage.DataStore;

import java.util.List;

public class RequestService {
    private DataStore dataStore;

    public RequestService() {
        this.dataStore = DataStore.getInstance();
    }

    public Request createRequest(String id, String authorLogin, String text,
                                 UrgencyLevel urgencyLevel, String createdDate) {
        Request request = new Request(id, authorLogin, text, urgencyLevel, createdDate);
        dataStore.addRequest(request);
        return request;
    }

    public Request createComplaint(String id, String teacherLogin, String text,
                                   UrgencyLevel urgencyLevel, String createdDate) {
        Request complaint = new Request(id, teacherLogin, text, Request.TYPE_COMPLAINT, urgencyLevel, createdDate);
        dataStore.addRequest(complaint);
        return complaint;
    }

    public void markViewed(Request request) {
        request.markViewed();
    }

    public void accept(Request request) {
        request.accept();
    }

    public void reject(Request request) {
        request.reject();
    }

    public void markDone(Request request) {
        request.markDone();
    }

    public List<Request> getAllRequests() {
        return dataStore.getRequests();
    }
}
