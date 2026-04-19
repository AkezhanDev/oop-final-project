package services;

import exceptions.CreditLimitExceededException;
import model.academic.Course;
import model.academic.Registration;
import model.users.Student;
import storage.DataStore;

import java.util.ArrayList;
import java.util.List;

public class RegistrationService {
    private DataStore dataStore;

    public RegistrationService() {
        this.dataStore = DataStore.getInstance();
    }

    public Registration registerStudentToCourse(Student student, Course course)
            throws CreditLimitExceededException {

        if (!student.canRegisterMoreCredits(course.getCredits())) {
            throw new CreditLimitExceededException("Credit limit exceeded for student " + student.getName());
        }

        Registration registration = new Registration(
                student.getStudentId(),
                course.getCourseCode(),
                false
        );

        dataStore.addRegistration(registration);
        return registration;
    }

    public void approveRegistration(Registration registration) {
        registration.approve();
    }

    public List<Registration> getStudentRegistrations(String studentId) {
        List<Registration> result = new ArrayList<>();

        for (Registration registration : dataStore.getRegistrations()) {
            if (registration.getStudentId().equalsIgnoreCase(studentId)) {
                result.add(registration);
            }
        }

        return result;
    }

    public List<Registration> getPendingRegistrations() {
        List<Registration> result = new ArrayList<>();

        for (Registration registration : dataStore.getRegistrations()) {
            if (!registration.isApproved()) {
                result.add(registration);
            }
        }

        return result;
    }
}