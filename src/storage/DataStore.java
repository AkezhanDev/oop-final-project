package storage;

import model.academic.AttendanceRecord;
import model.academic.Course;
import model.academic.Registration;
import model.academic.Transcript;
import model.communication.Message;
import model.communication.News;
import model.requests.Request;
import model.research.Journal;
import model.research.ResearchProject;
import model.users.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStore implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String DEFAULT_FILE_NAME = "university_data.ser";

    private static DataStore instance;

    private List<User> users;
    private List<LogEntry> logs;
    private List<Course> courses;
    private List<Registration> registrations;
    private List<News> newsList;
    private List<Message> messages;
    private List<Request> requests;
    private List<Journal> journals;
    private List<ResearchProject> researchProjects;
    private Map<String, Transcript> transcripts;
    private List<AttendanceRecord> attendanceRecords;

    private DataStore() {
        users = new ArrayList<>();
        logs = new ArrayList<>();
        courses = new ArrayList<>();
        registrations = new ArrayList<>();
        newsList = new ArrayList<>();
        messages = new ArrayList<>();
        requests = new ArrayList<>();
        journals = new ArrayList<>();
        researchProjects = new ArrayList<>();
        transcripts = new HashMap<>();
        attendanceRecords = new ArrayList<>();
    }

    public static DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }
        return instance;
    }

    public static DataStore loadFromFile(String fileName) {
        File file = new File(fileName);

        if (!file.exists()) {
            instance = new DataStore();
            return instance;
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            instance = (DataStore) inputStream.readObject();
            instance.initializeMissingCollections();
            User.synchronizeNextId(instance.users);
            return instance;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not load saved data. Starting with empty data store.");
            instance = new DataStore();
            return instance;
        }
    }

    public void saveToFile(String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(this);
        } catch (IOException e) {
            System.out.println("Could not save data: " + e.getMessage());
        }
    }

    private void initializeMissingCollections() {
        if (users == null) {
            users = new ArrayList<>();
        }
        if (logs == null) {
            logs = new ArrayList<>();
        }
        if (courses == null) {
            courses = new ArrayList<>();
        }
        if (registrations == null) {
            registrations = new ArrayList<>();
        }
        if (newsList == null) {
            newsList = new ArrayList<>();
        }
        if (messages == null) {
            messages = new ArrayList<>();
        }
        if (requests == null) {
            requests = new ArrayList<>();
        }
        if (journals == null) {
            journals = new ArrayList<>();
        }
        if (researchProjects == null) {
            researchProjects = new ArrayList<>();
        }
        if (transcripts == null) {
            transcripts = new HashMap<>();
        }
        if (attendanceRecords == null) {
            attendanceRecords = new ArrayList<>();
        }
    }

    public boolean isEmpty() {
        return users.isEmpty()
                && courses.isEmpty()
                && registrations.isEmpty()
                && newsList.isEmpty()
                && messages.isEmpty()
                && requests.isEmpty()
                && journals.isEmpty()
                && researchProjects.isEmpty()
                && transcripts.isEmpty()
                && attendanceRecords.isEmpty();
    }

    public List<User> getUsers() {
        return users;
    }

    public List<LogEntry> getLogs() {
        return logs;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public List<Journal> getJournals() {
        return journals;
    }

    public List<ResearchProject> getResearchProjects() {
        return researchProjects;
    }

    public Map<String, Transcript> getTranscripts() {
        return transcripts;
    }

    public List<AttendanceRecord> getAttendanceRecords() {
        return attendanceRecords;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public boolean removeUser(User user) {
        return users.remove(user);
    }

    public void addLog(LogEntry logEntry) {
        logs.add(logEntry);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addRegistration(Registration registration) {
        registrations.add(registration);
    }

    public void addNews(News news) {
        newsList.add(news);
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public void addRequest(Request request) {
        requests.add(request);
    }

    public void addJournal(Journal journal) {
        journals.add(journal);
    }

    public void addResearchProject(ResearchProject researchProject) {
        researchProjects.add(researchProject);
    }

    public void putTranscript(String studentId, Transcript transcript) {
        transcripts.put(studentId, transcript);
    }

    public Transcript getTranscript(String studentId) {
        return transcripts.get(studentId);
    }

    public void addAttendanceRecord(AttendanceRecord attendanceRecord) {
        attendanceRecords.add(attendanceRecord);
    }

    public AttendanceRecord findAttendanceRecord(String studentId, String courseCode) {
        for (AttendanceRecord record : attendanceRecords) {
            if (record.getStudentId().equalsIgnoreCase(studentId)
                    && record.getCourseCode().equalsIgnoreCase(courseCode)) {
                return record;
            }
        }
        return null;
    }

    public Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                return course;
            }
        }
        return null;
    }

    public User findUserByLogin(String login) {
        for (User user : users) {
            if (user.getLogin().equalsIgnoreCase(login)) {
                return user;
            }
        }
        return null;
    }
}
