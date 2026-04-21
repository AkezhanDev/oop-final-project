package model.academic;

import java.io.Serializable;

public class AttendanceRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    private String studentId;
    private String courseCode;
    private int attendedLessons;
    private int missedLessons;

    public AttendanceRecord() {
    }

    public AttendanceRecord(String studentId, String courseCode, int attendedLessons, int missedLessons) {
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.attendedLessons = attendedLessons;
        this.missedLessons = missedLessons;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getAttendedLessons() {
        return attendedLessons;
    }

    public void setAttendedLessons(int attendedLessons) {
        if (attendedLessons >= 0) {
            this.attendedLessons = attendedLessons;
        }
    }

    public int getMissedLessons() {
        return missedLessons;
    }

    public void setMissedLessons(int missedLessons) {
        if (missedLessons >= 0) {
            this.missedLessons = missedLessons;
        }
    }

    public void markPresent() {
        attendedLessons++;
    }

    public void markAbsent() {
        missedLessons++;
    }

    @Override
    public String toString() {
        return "AttendanceRecord: studentId=" + studentId +
                ", courseCode=" + courseCode +
                ", attendedLessons=" + attendedLessons +
                ", missedLessons=" + missedLessons;
    }
}
