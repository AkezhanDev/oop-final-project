package model.research;

import model.communication.Message;
import model.users.User;
import storage.DataStore;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Journal implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private List<ResearchPaper> papers;
    private List<String> subscribers;
    private List<User> subscriberUsers;

    public Journal() {
        papers = new ArrayList<>();
        subscribers = new ArrayList<>();
        subscriberUsers = new ArrayList<>();
    }

    public Journal(String name) {
        this.name = name;
        this.papers = new ArrayList<>();
        this.subscribers = new ArrayList<>();
        this.subscriberUsers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<ResearchPaper> getPapers() {
        initializeCollections();
        return papers;
    }

    public List<String> getSubscribers() {
        initializeCollections();
        return subscribers;
    }

    public List<User> getSubscriberUsers() {
        initializeCollections();
        return subscriberUsers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addPaper(ResearchPaper paper) {
        initializeCollections();
        papers.add(paper);
        notifySubscribers(paper);
    }

    public void subscribe(String userName) {
        initializeCollections();

        if (userName == null || userName.isEmpty()) {
            return;
        }

        for (String subscriber : subscribers) {
            if (subscriber.equalsIgnoreCase(userName)) {
                return;
            }
        }

        subscribers.add(userName);
    }

    public void subscribe(User user) {
        initializeCollections();

        if (user == null) {
            return;
        }

        if (!subscriberUsers.contains(user)) {
            subscriberUsers.add(user);
        }

        subscribe(user.getName());
    }

    private void notifySubscribers(ResearchPaper paper) {
        List<String> notifiedSubscribers = new ArrayList<>();

        for (User user : subscriberUsers) {
            Message message = new Message(
                    "journal",
                    user.getLogin(),
                    "New paper published in " + name + ": " + paper.getTitle(),
                    LocalDate.now().toString()
            );

            DataStore.getInstance().addMessage(message);
            notifiedSubscribers.add(user.getName().toLowerCase());
            notifiedSubscribers.add(user.getLogin().toLowerCase());
        }

        for (String subscriber : subscribers) {
            if (!notifiedSubscribers.contains(subscriber.toLowerCase())) {
                Message message = new Message(
                        "journal",
                        subscriber,
                        "New paper published in " + name + ": " + paper.getTitle(),
                        LocalDate.now().toString()
                );

                DataStore.getInstance().addMessage(message);
            }
        }
    }

    private void initializeCollections() {
        if (papers == null) {
            papers = new ArrayList<>();
        }
        if (subscribers == null) {
            subscribers = new ArrayList<>();
        }
        if (subscriberUsers == null) {
            subscriberUsers = new ArrayList<>();
        }
    }

    @Override
    public String toString() {
        initializeCollections();
        return "Journal: name=" + name +
                ", papers=" + papers.size() +
                ", subscribers=" + subscribers.size();
    }
}
