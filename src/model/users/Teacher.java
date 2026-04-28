package model.users;

import enums.Language;
import enums.TeacherPosition;

public class Teacher extends Employee {
    private static final long serialVersionUID = 1L;

    private TeacherPosition position;
    private double rating;

    public Teacher() {
        super();
    }

    public Teacher(String login, String password, String name, Language language,
                   String employeeId, String department, double salary,
                   TeacherPosition position, double rating) {
        super(login, password, name, language, employeeId, department, salary);
        this.position = position;
        this.rating = rating;
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
