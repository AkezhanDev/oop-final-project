package model.users;

import enums.Language;

public class Employee extends User {
    private String employeeId;
    private String department;
    private double salary;

    public Employee() {
        super();
    }

    public Employee(String login, String password, String name, Language language,
                    String employeeId, String department, double salary) {
        super(login, password, name, language);
        this.employeeId = employeeId;
        this.department = department;
        this.salary = salary;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void increaseSalary(double amount) {
        if (amount > 0) {
            salary += amount;
        }
    }

    @Override
    public String toString() {
        return "Employee: " +
                "id=" + getId() +
                ", name=" + getName() +
                ", employeeId=" + employeeId +
                ", department=" + department +
                ", salary=" + salary;
    }
}