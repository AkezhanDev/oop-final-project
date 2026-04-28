package model.research;

import exceptions.NotResearcherException;
import model.users.User;
import storage.DataStore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResearchProject implements Serializable {
    private static final long serialVersionUID = 1L;

    private String topic;
    private List<String> participants;
    private List<ResearcherDecorator> researcherParticipants;
    private List<ResearchPaper> publishedPapers;

    public ResearchProject() {
        participants = new ArrayList<>();
        researcherParticipants = new ArrayList<>();
        publishedPapers = new ArrayList<>();
    }

    public ResearchProject(String topic) {
        this.topic = topic;
        this.participants = new ArrayList<>();
        this.researcherParticipants = new ArrayList<>();
        this.publishedPapers = new ArrayList<>();
    }

    public String getTopic() {
        return topic;
    }

    public List<String> getParticipants() {
        initializeCollections();
        return participants;
    }

    public List<ResearcherDecorator> getResearcherParticipants() {
        initializeCollections();
        return researcherParticipants;
    }

    public List<ResearchPaper> getPublishedPapers() {
        initializeCollections();
        return publishedPapers;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void addParticipant(User user) throws NotResearcherException {
        if (user == null) {
            throw new NotResearcherException("Only researchers can join research projects.");
        }

        ResearcherDecorator researcher = DataStore.getInstance().findResearcherByUserLogin(user.getLogin());

        if (researcher == null) {
            throw new NotResearcherException("Only researchers can join research projects.");
        }

        addResearcherParticipant(researcher);
    }

    public void addResearcherParticipant(ResearcherDecorator researcher) {
        initializeCollections();

        if (researcher == null) {
            return;
        }

        if (!researcherParticipants.contains(researcher)) {
            researcherParticipants.add(researcher);
        }

        String participantName = getResearcherName(researcher);

        for (String participant : participants) {
            if (participant.equalsIgnoreCase(participantName)) {
                return;
            }
        }

        participants.add(participantName);
    }

    public void addPublishedPaper(ResearchPaper paper) {
        initializeCollections();
        publishedPapers.add(paper);
    }

    private void initializeCollections() {
        if (participants == null) {
            participants = new ArrayList<>();
        }
        if (researcherParticipants == null) {
            researcherParticipants = new ArrayList<>();
        }
        if (publishedPapers == null) {
            publishedPapers = new ArrayList<>();
        }
    }

    private String getResearcherName(ResearcherDecorator researcher) {
        return researcher.getResearcherName();
    }

    @Override
    public String toString() {
        return "ResearchProject: topic=" + topic +
                ", participants=" + participants +
                ", publishedPapers=" + publishedPapers.size();
    }
}
