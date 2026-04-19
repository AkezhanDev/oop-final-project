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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStore {
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