package model.users;

import enums.Language;
import exceptions.InvalidSupervisorException;
import interfaces.Researcher;
import model.research.ResearchPaper;
import model.research.ResearchProject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GraduateStudent extends Student implements Researcher {
    private static final long serialVersionUID = 1L;

    private String degreeType;
    private Researcher supervisor;
    private String supervisorName;
    private int publishedPapersCount;
    private List<ResearchPaper> researchPapers;
    private List<ResearchProject> researchProjects;

    public GraduateStudent() {
        super();
        this.researchPapers = new ArrayList<>();
        this.researchProjects = new ArrayList<>();
    }

    public GraduateStudent(String login, String password, String name, Language language,
                           String studentId, int yearOfStudy, String major, int credits, int failedCoursesCount,
                           String degreeType, Researcher supervisor, int publishedPapersCount)
            throws InvalidSupervisorException {
        super(login, password, name, language, studentId, yearOfStudy, major, credits, failedCoursesCount);
        this.degreeType = degreeType;
        this.publishedPapersCount = publishedPapersCount;
        this.researchPapers = new ArrayList<>();
        this.researchProjects = new ArrayList<>();
        setSupervisor(supervisor);
    }

    public GraduateStudent(String login, String password, String name, Language language,
                           String studentId, int yearOfStudy, String major, int credits, int failedCoursesCount,
                           String degreeType, String supervisorName, int publishedPapersCount) {
        throw new IllegalArgumentException("GraduateStudent supervisor must be a Researcher object.");
    }

    public String getDegreeType() {
        return degreeType;
    }

    public void setDegreeType(String degreeType) {
        this.degreeType = degreeType;
    }

    public String getSupervisorName() {
        if (supervisor instanceof User) {
            return ((User) supervisor).getName();
        }

        if (supervisor != null) {
            return supervisor.toString();
        }

        if (supervisorName != null) {
            return supervisorName;
        }

        return "Not assigned";
    }

    public Researcher getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Researcher supervisor) throws InvalidSupervisorException {
        if (supervisor == null) {
            throw new InvalidSupervisorException("Supervisor cannot be null.");
        }

        if (supervisor.calculateHIndex() < 3) {
            throw new InvalidSupervisorException("Supervisor h-index must be at least 3.");
        }

        this.supervisor = supervisor;

        if (supervisor instanceof User) {
            this.supervisorName = ((User) supervisor).getName();
        } else {
            this.supervisorName = supervisor.toString();
        }
    }

    public int getPublishedPapersCount() {
        return publishedPapersCount;
    }

    public void setPublishedPapersCount(int publishedPapersCount) {
        if (publishedPapersCount >= 0) {
            this.publishedPapersCount = publishedPapersCount;
        }
    }

    public void addPublishedPaper() {
        publishedPapersCount++;
    }

    private void initializeResearchCollections() {
        if (researchPapers == null) {
            researchPapers = new ArrayList<>();
        }
        if (researchProjects == null) {
            researchProjects = new ArrayList<>();
        }
    }

    @Override
    public int calculateHIndex() {
        initializeResearchCollections();
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
        initializeResearchCollections();
        if (paper != null) {
            researchPapers.add(paper);
            publishedPapersCount = researchPapers.size();
        }
    }

    @Override
    public void printPapers(Comparator<ResearchPaper> comparator) {
        initializeResearchCollections();
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
        initializeResearchCollections();
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
        initializeResearchCollections();
        return researchPapers;
    }

    public List<ResearchProject> getResearchProjects() {
        initializeResearchCollections();
        return researchProjects;
    }

    @Override
    public String toString() {
        return "GraduateStudent: " +
                "id=" + getId() +
                ", name=" + getName() +
                ", studentId=" + getStudentId() +
                ", major=" + getMajor() +
                ", yearOfStudy=" + getYearOfStudy() +
                ", degreeType=" + degreeType +
                ", supervisorName=" + getSupervisorName() +
                ", publishedPapersCount=" + publishedPapersCount;
    }
}
