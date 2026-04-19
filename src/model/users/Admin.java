package model.users;

import enums.Language;

public class Admin extends Employee {
    public Admin() {
        super();
    }

    public Admin(String login, String password, String name, Language language,
                 String employeeId, String department, double salary) {
        super(login, password, name, language, employeeId, department, salary);
    }

    @Override
    public String toString() {
        return "Admin: " +
                "id=" + getId() +
                ", name=" + getName() +
                ", employeeId=" + getEmployeeId() +
                ", department=" + getDepartment() +
                ", salary=" + getSalary();
    }
}