package model.academic;

import enums.CourseType;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String courseCode;
    private String title;
    private int credits;
    private CourseType courseType;
    private String majorFor;
    private int yearOfStudy;
    private List<Lesson> lessons;

    public Course() {
        lessons = new ArrayList<>();
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
        return lessons;
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    @Override
    public String toString() {
        return "Course: code=" + courseCode +
                ", title=" + title +
                ", credits=" + credits +
                ", type=" + courseType +
                ", majorFor=" + majorFor +
                ", yearOfStudy=" + yearOfStudy;
    }
}