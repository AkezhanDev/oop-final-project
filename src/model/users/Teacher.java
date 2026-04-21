package model.users;

import enums.Language;
import enums.TeacherPosition;
import interfaces.Researcher;
import model.research.ResearchPaper;
import model.research.ResearchProject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Teacher extends Employee implements Researcher {
    private static final long serialVersionUID = 1L;

    private TeacherPosition position;
    private double rating;
    private List<ResearchPaper> researchPapers;
    private List<ResearchProject> researchProjects;

    public Teacher() {
        super();
        this.researchPapers = new ArrayList<>();
        this.researchProjects = new ArrayList<>();
    }

    public Teacher(String login, String password, String name, Language language,
                   String employeeId, String department, double salary,
                   TeacherPosition position, double rating) {
        super(login, password, name, language, employeeId, department, salary);
        this.position = position;
        this.rating = rating;
        this.researchPapers = new ArrayList<>();
        this.researchProjects = new ArrayList<>();
    }

    public TeacherPosition getPosition() {
        return position;
    }

    public void setPosition(TeacherPosition position) {
        this.position = position;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        if (rating >= 0 && rating <= 5) {
            this.rating = rating;
        }
    }

    public void updateRating(double newRating) {
        setRating(newRating);
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
        return "Teacher: " +
                "id=" + getId() +
                ", name=" + getName() +
                ", employeeId=" + getEmployeeId() +
                ", department=" + getDepartment() +
                ", position=" + position +
                ", salary=" + getSalary() +
                ", rating=" + rating;
    }
}
