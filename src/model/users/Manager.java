package model.users;

import enums.Language;
import enums.ManagerType;

public class Manager extends Employee {
    private ManagerType managerType;

    public Manager() {
        super();
    }

    public Manager(String login, String password, String name, Language language,
                   String employeeId, String department, double salary,
                   ManagerType managerType) {
        super(login, password, name, language, employeeId, department, salary);
        this.managerType = managerType;
    }

    public ManagerType getManagerType() {
        return managerType;
    }

    public void setManagerType(ManagerType managerType) {
        this.managerType = managerType;
    }

    @Override
    public String toString() {
        return "Manager: " +
                "id=" + getId() +
                ", name=" + getName() +
                ", employeeId=" + getEmployeeId() +
                ", department=" + getDepartment() +
                ", managerType=" + managerType +
                ", salary=" + getSalary();
    }
}