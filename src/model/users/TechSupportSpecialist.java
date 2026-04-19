package model.users;

import enums.Language;

public class TechSupportSpecialist extends Employee {
    private String specialization;

    public TechSupportSpecialist() {
        super();
    }

    public TechSupportSpecialist(String login, String password, String name, Language language,
                                 String employeeId, String department, double salary,
                                 String specialization) {
        super(login, password, name, language, employeeId, department, salary);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "TechSupportSpecialist: " +
                "id=" + getId() +
                ", name=" + getName() +
                ", employeeId=" + getEmployeeId() +
                ", department=" + getDepartment() +
                ", specialization=" + specialization +
                ", salary=" + getSalary();
    }
}