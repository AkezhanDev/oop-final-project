package services;

import enums.CourseStatus;
import exceptions.CreditLimitExceededException;
import model.academic.Course;
import model.academic.Registration;
import model.users.Student;
import storage.DataStore;

import java.util.ArrayList;
import java.util.List;

public class RegistrationService {
    private static final double PASSING_TOTAL = 50.0;

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
                CourseStatus.PENDING
        );

        dataStore.addRegistration(registration);
        return registration;
    }

    public void approveRegistration(Registration registration) {
        registration.approve();
    }

    public void completeRegistrationByMark(String studentId, String courseCode, double totalMark) {
        Registration registration = findActiveRegistration(studentId, courseCode);

        if (registration == null) {
            return;
        }

        if (totalMark >= PASSING_TOTAL) {
            registration.markPassed();
        } else {
            registration.markFailed();
        }
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
            if (registration.isPending()) {
                result.add(registration);
            }
        }

        return result;
    }

    public List<Registration> getRegistrationsByStatus(CourseStatus status) {
        List<Registration> result = new ArrayList<>();

        for (Registration registration : dataStore.getRegistrations()) {
            if (registration.getStatus() == status) {
                result.add(registration);
            }
        }

        return result;
    }

    private Registration findActiveRegistration(String studentId, String courseCode) {
        for (int i = dataStore.getRegistrations().size() - 1; i >= 0; i--) {
            Registration registration = dataStore.getRegistrations().get(i);

            if (registration.getStudentId().equalsIgnoreCase(studentId)
                    && registration.getCourseCode().equalsIgnoreCase(courseCode)
                    && registration.isActive()) {
                return registration;
            }
        }

        return null;
    }
}
