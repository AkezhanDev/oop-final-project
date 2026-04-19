package model.academic;

public class Registration {
    private String studentId;
    private String courseCode;
    private boolean approved;

    public Registration() {
    }

    public Registration(String studentId, String courseCode, boolean approved) {
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.approved = approved;
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

    public boolean isApproved() {
        return approved;
    }

    public void approve() {
        approved = true;
    }

    public void reject() {
        approved = false;
    }

    @Override
    public String toString() {
        return "Registration: studentId=" + studentId +
                ", courseCode=" + courseCode +
                ", approved=" + approved;
    }
}