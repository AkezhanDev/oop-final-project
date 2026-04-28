package model.academic;

import enums.CourseStatus;

import java.io.Serializable;

public class Registration implements Serializable {
    private static final long serialVersionUID = 1L;

    private String studentId;
    private String courseCode;
    private CourseStatus status;

    public Registration() {
        this.status = CourseStatus.PENDING;
    }

    public Registration(String studentId, String courseCode, CourseStatus status) {
        this.studentId = studentId;
        this.courseCode = courseCode;
        setStatus(status);
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

    public CourseStatus getStatus() {
        if (status == null) {
            status = CourseStatus.PENDING;
        }

        return status;
    }

    public void setStatus(CourseStatus status) {
        if (status == null) {
            this.status = CourseStatus.PENDING;
        } else {
            this.status = status;
        }
    }

    public boolean isApproved() {
        return getStatus() == CourseStatus.ACTIVE
                || getStatus() == CourseStatus.PASSED
                || getStatus() == CourseStatus.FAILED;
    }

    public boolean isPending() {
        return getStatus() == CourseStatus.PENDING;
    }

    public boolean isActive() {
        return getStatus() == CourseStatus.ACTIVE;
    }

    public boolean isPassed() {
        return getStatus() == CourseStatus.PASSED;
    }

    public boolean isFailed() {
        return getStatus() == CourseStatus.FAILED;
    }

    public void approve() {
        status = CourseStatus.ACTIVE;
    }

    public void reject() {
        status = CourseStatus.PENDING;
    }

    public void markPassed() {
        status = CourseStatus.PASSED;
    }

    public void markFailed() {
        status = CourseStatus.FAILED;
    }

    public String getStatusDescription() {
        switch (getStatus()) {
            case ACTIVE:
                return "Approved and currently active";
            case PASSED:
                return "Course completed successfully";
            case FAILED:
                return "Course failed and may require retake";
            case PENDING:
            default:
                return "Waiting for manager approval";
        }
    }

    @Override
    public String toString() {
        return "Registration: studentId=" + studentId +
                ", courseCode=" + courseCode +
                ", status=" + getStatus() +
                ", statusDescription=" + getStatusDescription();
    }
}
