package model.research;

import java.util.ArrayList;
import java.util.List;

public class ResearchProject {
    private String topic;
    private List<String> participants;
    private List<ResearchPaper> publishedPapers;

    public ResearchProject() {
        participants = new ArrayList<>();
        publishedPapers = new ArrayList<>();
    }

    public ResearchProject(String topic) {
        this.topic = topic;
        this.participants = new ArrayList<>();
        this.publishedPapers = new ArrayList<>();
    }

    public String getTopic() {
        return topic;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public List<ResearchPaper> getPublishedPapers() {
        return publishedPapers;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void addParticipant(String participantName) {
        participants.add(participantName);
    }

    public void addPublishedPaper(ResearchPaper paper) {
        publishedPapers.add(paper);
    }

    @Override
    public String toString() {
        return "ResearchProject: topic=" + topic +
                ", participants=" + participants +
                ", publishedPapers=" + publishedPapers.size();
    }
}