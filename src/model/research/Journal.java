package model.research;

import java.util.ArrayList;
import java.util.List;

public class Journal {
    private String name;
    private List<ResearchPaper> papers;
    private List<String> subscribers;

    public Journal() {
        papers = new ArrayList<>();
        subscribers = new ArrayList<>();
    }

    public Journal(String name) {
        this.name = name;
        this.papers = new ArrayList<>();
        this.subscribers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<ResearchPaper> getPapers() {
        return papers;
    }

    public List<String> getSubscribers() {
        return subscribers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addPaper(ResearchPaper paper) {
        papers.add(paper);
    }

    public void subscribe(String userName) {
        subscribers.add(userName);
    }

    @Override
    public String toString() {
        return "Journal: name=" + name +
                ", papers=" + papers.size() +
                ", subscribers=" + subscribers.size();
    }
}