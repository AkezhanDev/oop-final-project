package model.users;

import enums.Language;
import exceptions.InvalidSupervisorException;
import model.research.ResearcherDecorator;

public class GraduateStudent extends Student {
    private static final long serialVersionUID = 1L;

    private String degreeType;
    private ResearcherDecorator supervisor;
    private String supervisorName;
    private int publishedPapersCount;

    public GraduateStudent() {
        super();
    }

    public GraduateStudent(String login, String password, String name, Language language,
                           String studentId, int yearOfStudy, String major, int credits, int failedCoursesCount,
                           String degreeType, ResearcherDecorator supervisor, int publishedPapersCount)
            throws InvalidSupervisorException {
        super(login, password, name, language, studentId, yearOfStudy, major, credits, failedCoursesCount);
        this.degreeType = degreeType;
        this.publishedPapersCount = publishedPapersCount;
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
        if (supervisor != null) {
            return supervisor.getResearcherName();
        }

        if (supervisorName != null) {
            return supervisorName;
        }

        return "Not assigned";
    }

    public ResearcherDecorator getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(ResearcherDecorator supervisor) throws InvalidSupervisorException {
        if (supervisor == null) {
            throw new InvalidSupervisorException("Supervisor cannot be null.");
        }

        if (supervisor.calculateHIndex() < 3) {
            throw new InvalidSupervisorException("Supervisor h-index must be at least 3.");
        }

        this.supervisor = supervisor;
        this.supervisorName = supervisor.getResearcherName();
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
