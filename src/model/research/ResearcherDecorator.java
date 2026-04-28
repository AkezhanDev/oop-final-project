package model.research;

import interfaces.Researcher;
import model.users.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class ResearcherDecorator implements Researcher, Serializable {
    private static final long serialVersionUID = 1L;

    private User user;
    private List<ResearchPaper> researchPapers;
    private List<ResearchProject> researchProjects;

    public ResearcherDecorator(User user) {
        this.user = user;
        this.researchPapers = new ArrayList<>();
        this.researchProjects = new ArrayList<>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getResearcherName() {
        if (user == null) {
            return "Unknown researcher";
        }

        return user.getName();
    }

    public String getResearcherLogin() {
        if (user == null) {
            return "";
        }

        return user.getLogin();
    }

    @Override
    public int calculateHIndex() {
        initializeCollections();
        List<ResearchPaper> sortedPapers = new ArrayList<>(researchPapers);
        sortedPapers.sort(Comparator.comparingInt(ResearchPaper::getCitations).reversed());

        int hIndex = 0;

        for (int i = 0; i < sortedPapers.size(); i++) {
            if (sortedPapers.get(i).getCitations() >= i + 1) {
                hIndex = i + 1;
            } else {
                break;
            }
        }

        return hIndex;
    }

    @Override
    public void publishPaper(ResearchPaper paper) {
        initializeCollections();

        if (paper != null) {
            researchPapers.add(paper);
        }
    }

    @Override
    public void printPapers(Comparator<ResearchPaper> comparator) {
        initializeCollections();
        List<ResearchPaper> papersToPrint = new ArrayList<>(researchPapers);

        if (comparator != null) {
            papersToPrint.sort(comparator);
        }

        if (papersToPrint.isEmpty()) {
            System.out.println("No research papers found.");
            return;
        }

        papersToPrint.forEach(System.out::println);
    }

    @Override
    public void joinResearchProject(ResearchProject project) {
        initializeCollections();

        if (project == null) {
            return;
        }

        if (!researchProjects.contains(project)) {
            researchProjects.add(project);
        }

        project.addResearcherParticipant(this);
    }

    @Override
    public List<ResearchPaper> getResearchPapers() {
        initializeCollections();
        return researchPapers;
    }

    public List<ResearchProject> getResearchProjects() {
        initializeCollections();
        return researchProjects;
    }

    private void initializeCollections() {
        if (researchPapers == null) {
            researchPapers = new ArrayList<>();
        }
        if (researchProjects == null) {
            researchProjects = new ArrayList<>();
        }
    }

    @Override
    public String toString() {
        return "ResearcherDecorator: researcher=" + getResearcherName() +
                ", papers=" + getResearchPapers().size() +
                ", projects=" + getResearchProjects().size();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof ResearcherDecorator)) {
            return false;
        }

        ResearcherDecorator other = (ResearcherDecorator) object;
        return Objects.equals(user, other.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }
}
