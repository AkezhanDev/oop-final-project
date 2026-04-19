package model.users;

import enums.Language;

public class GraduateStudent extends Student {
    private String degreeType;
    private String supervisorName;
    private int publishedPapersCount;

    public GraduateStudent() {
        super();
    }

    public GraduateStudent(String login, String password, String name, Language language,
                           String studentId, int yearOfStudy, String major, int credits, int failedCoursesCount,
                           String degreeType, String supervisorName, int publishedPapersCount) {
        super(login, password, name, language, studentId, yearOfStudy, major, credits, failedCoursesCount);
        this.degreeType = degreeType;
        this.supervisorName = supervisorName;
        this.publishedPapersCount = publishedPapersCount;
    }

    public String getDegreeType() {
        return degreeType;
    }

    public void setDegreeType(String degreeType) {
        this.degreeType = degreeType;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
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
                ", supervisorName=" + supervisorName +
                ", publishedPapersCount=" + publishedPapersCount;
    }
}