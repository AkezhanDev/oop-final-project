package model.academic;

import enums.CourseType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Course implements Serializable {
    private static final long serialVersionUID = 1L;

    private String courseCode;
    private String title;
    private int credits;
    private CourseType courseType;
    private String majorFor;
    private int yearOfStudy;
    private List<Lesson> lessons;
    private List<String> assignedTeacherLogins;

    public Course() {
        lessons = new ArrayList<>();
        assignedTeacherLogins = new ArrayList<>();
    }

    public Course(String courseCode, String title, int credits, CourseType courseType,
                  String majorFor, int yearOfStudy) {
        this.courseCode = courseCode;
        this.title = title;
        this.credits = credits;
        this.courseType = courseType;
        this.majorFor = majorFor;
        this.yearOfStudy = yearOfStudy;
        this.lessons = new ArrayList<>();
        this.assignedTeacherLogins = new ArrayList<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        if (credits >= 0) {
            this.credits = credits;
        }
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public String getMajorFor() {
        return majorFor;
    }

    public void setMajorFor(String majorFor) {
        this.majorFor = majorFor;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public List<Lesson> getLessons() {
        initializeLists();
        return lessons;
    }

    public void addLesson(Lesson lesson) {
        initializeLists();
        lessons.add(lesson);
    }

    public List<String> getAssignedTeacherLogins() {
        initializeLists();
        return assignedTeacherLogins;
    }

    public void assignTeacher(String teacherLogin) {
        initializeLists();

        if (teacherLogin == null || teacherLogin.isEmpty()) {
            return;
        }

        for (String assignedTeacherLogin : assignedTeacherLogins) {
            if (assignedTeacherLogin.equalsIgnoreCase(teacherLogin)) {
                return;
            }
        }

        assignedTeacherLogins.add(teacherLogin);
    }

    public boolean isTeacherAssigned(String teacherLogin) {
        initializeLists();

        for (String assignedTeacherLogin : assignedTeacherLogins) {
            if (assignedTeacherLogin.equalsIgnoreCase(teacherLogin)) {
                return true;
            }
        }

        return false;
    }

    private void initializeLists() {
        if (lessons == null) {
            lessons = new ArrayList<>();
        }
        if (assignedTeacherLogins == null) {
            assignedTeacherLogins = new ArrayList<>();
        }
    }

    @Override
    public String toString() {
        return "Course: code=" + courseCode +
                ", title=" + title +
                ", credits=" + credits +
                ", type=" + courseType +
                ", majorFor=" + majorFor +
                ", yearOfStudy=" + yearOfStudy +
                ", assignedTeachers=" + getAssignedTeacherLogins();
    }
}
