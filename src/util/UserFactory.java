package util;

import enums.Language;
import enums.ManagerType;
import enums.TeacherPosition;
import exceptions.InvalidSupervisorException;
import model.research.ResearcherDecorator;
import model.users.Admin;
import model.users.Employee;
import model.users.GraduateStudent;
import model.users.Manager;
import model.users.Student;
import model.users.Teacher;
import model.users.TechSupportSpecialist;
import model.users.User;

public class UserFactory {
    public static User createUser(String login, String password, String name, Language language) {
        return new User(login, password, name, language);
    }

    public static Employee createEmployee(String login, String password, String name, Language language,
                                          String employeeId, String department, double salary) {
        return new Employee(login, password, name, language, employeeId, department, salary);
    }

    public static Student createStudent(String login, String password, String name, Language language,
                                        String studentId, int yearOfStudy, String major,
                                        int credits, int failedCoursesCount) {
        return new Student(login, password, name, language, studentId, yearOfStudy, major, credits, failedCoursesCount);
    }

    public static GraduateStudent createGraduateStudent(String login, String password, String name, Language language,
                                                        String studentId, int yearOfStudy, String major,
                                                        int credits, int failedCoursesCount,
                                                        String degreeType, ResearcherDecorator supervisor,
                                                        int publishedPapersCount)
            throws InvalidSupervisorException {
        return new GraduateStudent(login, password, name, language, studentId, yearOfStudy, major,
                credits, failedCoursesCount, degreeType, supervisor, publishedPapersCount);
    }

    public static Teacher createTeacher(String login, String password, String name, Language language,
                                        String employeeId, String department, double salary,
                                        TeacherPosition position, double rating) {
        return new Teacher(login, password, name, language, employeeId, department, salary, position, rating);
    }

    public static Manager createManager(String login, String password, String name, Language language,
                                        String employeeId, String department, double salary,
                                        ManagerType managerType) {
        return new Manager(login, password, name, language, employeeId, department, salary, managerType);
    }

    public static Admin createAdmin(String login, String password, String name, Language language,
                                    String employeeId, String department, double salary) {
        return new Admin(login, password, name, language, employeeId, department, salary);
    }

    public static TechSupportSpecialist createTechSupportSpecialist(String login, String password, String name, Language language,
                                                                    String employeeId, String department, double salary,
                                                                    String specialization) {
        return new TechSupportSpecialist(login, password, name, language, employeeId, department, salary, specialization);
    }
}
