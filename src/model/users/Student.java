package model.users;

import enums.Language;

public class Student extends User {
    private static final int MAX_CREDITS = 21;
    private static final int MAX_FAILS = 3;

    private String studentId;
    private int yearOfStudy;
    private String major;
    private int credits;
    private int failedCoursesCount;

    public Student() {
        super();
    }

    public Student(String login, String password, String name, Language language,
                   String studentId, int yearOfStudy, String major, int credits, int failedCoursesCount) {
        super(login, password, name, language);
        this.studentId = studentId;
        this.yearOfStudy = yearOfStudy;
        this.major = major;
        this.credits = credits;
        this.failedCoursesCount = failedCoursesCount;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        if (credits >= 0 && credits <= MAX_CREDITS) {
            this.credits = credits;
        }
    }

    public int getFailedCoursesCount() {
        return failedCoursesCount;
    }

    public void setFailedCoursesCount(int failedCoursesCount) {
        if (failedCoursesCount >= 0 && failedCoursesCount <= MAX_FAILS) {
            this.failedCoursesCount = failedCoursesCount;
        }
    }

    public void increaseYearOfStudy() {
        yearOfStudy++;
    }

    public boolean canRegisterMoreCredits(int newCredits) {
        return credits + newCredits <= MAX_CREDITS;
    }

    public boolean canContinueStudy() {
        return failedCoursesCount <= MAX_FAILS;
    }

    @Override
    public String toString() {
        return "Student: " +
                "id=" + getId() +
                ", name=" + getName() +
                ", studentId=" + studentId +
                ", major=" + major +
                ", yearOfStudy=" + yearOfStudy +
                ", credits=" + credits +
                ", failedCoursesCount=" + failedCoursesCount;
    }
}