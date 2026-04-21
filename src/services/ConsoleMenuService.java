package services;

import enums.CitationFormat;
import enums.Language;
import enums.ManagerType;
import enums.TeacherPosition;
import enums.UrgencyLevel;
import exceptions.AuthenticationFailedException;
import exceptions.CreditLimitExceededException;
import exceptions.InvalidSupervisorException;
import interfaces.Researcher;
import model.academic.AttendanceRecord;
import model.academic.Course;
import model.academic.Mark;
import model.academic.Registration;
import model.academic.Transcript;
import model.communication.Comment;
import model.communication.Message;
import model.communication.News;
import model.requests.Request;
import model.users.Admin;
import model.users.Employee;
import model.users.Manager;
import model.users.Student;
import model.users.Teacher;
import model.users.TechSupportSpecialist;
import model.users.User;
import storage.DataStore;
import storage.LogEntry;
import util.UserFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class ConsoleMenuService {
    private final AuthService authService;
    private final RegistrationService registrationService;
    private final MarkService markService;
    private final RequestService requestService;
    private final NewsService newsService;
    private final ResearchService researchService;
    private final AttendanceService attendanceService;
    private final RatingService ratingService;
    private final SearchService searchService;
    private final DataStore dataStore;
    private final Scanner scanner;

    public ConsoleMenuService() {
        this.authService = new AuthService();
        this.registrationService = new RegistrationService();
        this.markService = new MarkService();
        this.requestService = new RequestService();
        this.newsService = new NewsService();
        this.researchService = new ResearchService();
        this.attendanceService = new AttendanceService();
        this.ratingService = new RatingService();
        this.searchService = new SearchService();
        this.dataStore = DataStore.getInstance();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            printMainMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    handleLogin();
                    break;
                case "2":
                    openSearchMenu();
                    break;
                case "0":
                    running = false;
                    System.out.println("System stopped.");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void printMainMenu() {
        System.out.println();
        System.out.println("=== UNIVERSITY SYSTEM ===");
        System.out.println("1. Login");
        System.out.println("2. Search");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    private void handleLogin() {
        try {
            System.out.print("Enter login: ");
            String login = scanner.nextLine();

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            User user = authService.authenticate(login, password);

            System.out.println("Login successful.");
            System.out.println("Welcome, " + user.getName());

            openRoleMenu(user);

        } catch (AuthenticationFailedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void openRoleMenu(User user) {
        if (user instanceof Student) {
            openStudentMenu((Student) user);
        } else if (user instanceof Teacher) {
            openTeacherMenu((Teacher) user);
        } else if (user instanceof Manager) {
            openManagerMenu((Manager) user);
        } else if (user instanceof Admin) {
            openAdminMenu((Admin) user);
        } else if (user instanceof TechSupportSpecialist) {
            openTechSupportMenu((TechSupportSpecialist) user);
        } else {
            System.out.println("No menu available for this user role.");
        }
    }

    private void openStudentMenu(Student student) {
        boolean inMenu = true;

        while (inMenu) {
            System.out.println();
            System.out.println("=== STUDENT MENU ===");
            System.out.println("1. View profile");
            System.out.println("2. View transcript");
            System.out.println("3. Register for course");
            System.out.println("4. View all courses");
            System.out.println("5. View news");
            System.out.println("6. Rate teacher");
            System.out.println("0. Logout");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println(student);
                    break;
                case "2":
                    Transcript transcript = dataStore.getTranscript(student.getStudentId());
                    if (transcript == null) {
                        System.out.println("Transcript is empty.");
                    } else {
                        System.out.println(transcript);
                    }
                    break;
                case "3":
                    registerStudentForCourse(student);
                    break;
                case "4":
                    printAllCourses();
                    break;
                case "5":
                    viewAllNews();
                    break;
                case "6":
                    rateTeacher();
                    break;
                case "0":
                    inMenu = false;
                    System.out.println("Logged out.");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void openTeacherMenu(Teacher teacher) {
        boolean inMenu = true;

        while (inMenu) {
            System.out.println();
            System.out.println("=== TEACHER MENU ===");
            System.out.println("1. View profile");
            System.out.println("2. Put marks");
            System.out.println("3. View all courses");
            System.out.println("4. View journals");
            System.out.println("5. View research projects");
            System.out.println("6. View all papers");
            System.out.println("7. Create request");
            System.out.println("8. Mark attendance");
            System.out.println("9. View attendance records");
            System.out.println("10. Send complaint");
            System.out.println("11. Send message");
            System.out.println("12. View my messages");
            System.out.println("13. View assigned courses");
            System.out.println("0. Logout");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println(teacher);
                    break;
                case "2":
                    putMarks();
                    break;
                case "3":
                    printAllCourses();
                    break;
                case "4":
                    viewJournals();
                    break;
                case "5":
                    viewResearchProjects();
                    break;
                case "6":
                    viewAllResearchPapers();
                    break;
                case "7":
                    createRequest(teacher.getLogin());
                    break;
                case "8":
                    markAttendance();
                    break;
                case "9":
                    viewAttendanceRecords();
                    break;
                case "10":
                    createTeacherComplaint(teacher);
                    break;
                case "11":
                    sendMessage(teacher);
                    break;
                case "12":
                    viewMyMessages(teacher);
                    break;
                case "13":
                    viewAssignedCourses(teacher);
                    break;
                case "0":
                    inMenu = false;
                    System.out.println("Logged out.");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void openManagerMenu(Manager manager) {
        boolean inMenu = true;

        while (inMenu) {
            System.out.println();
            System.out.println("=== MANAGER MENU ===");
            System.out.println("1. View profile");
            System.out.println("2. Approve registration");
            System.out.println("3. Create news");
            System.out.println("4. View all registrations");
            System.out.println("5. Publish paper to journal");
            System.out.println("6. Add paper to research project");
            System.out.println("7. View news");
            System.out.println("8. Create pinned research news");
            System.out.println("9. Announce top cited researcher");
            System.out.println("10. Send message");
            System.out.println("11. View my messages");
            System.out.println("12. Assign teacher to course");
            System.out.println("13. View statistical report");
            System.out.println("0. Logout");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println(manager);
                    break;
                case "2":
                    approveRegistration();
                    break;
                case "3":
                    createNews();
                    break;
                case "4":
                    printAllRegistrations();
                    break;
                case "5":
                    publishPaperToJournal();
                    break;
                case "6":
                    addPaperToProject();
                    break;
                case "7":
                    viewAllNews();
                    break;
                case "8":
                    createPinnedResearchNews();
                    break;
                case "9":
                    announceTopCitedResearcher();
                    break;
                case "10":
                    sendMessage(manager);
                    break;
                case "11":
                    viewMyMessages(manager);
                    break;
                case "12":
                    assignTeacherToCourse();
                    break;
                case "13":
                    viewManagerStatisticalReport();
                    break;
                case "0":
                    inMenu = false;
                    System.out.println("Logged out.");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void openAdminMenu(Admin admin) {
        boolean inMenu = true;

        while (inMenu) {
            System.out.println();
            System.out.println("=== ADMIN MENU ===");
            System.out.println("1. View profile");
            System.out.println("2. View logs");
            System.out.println("3. View users");
            System.out.println("4. View news");
            System.out.println("5. Add user");
            System.out.println("6. Remove user");
            System.out.println("7. Update user");
            System.out.println("8. Send message");
            System.out.println("9. View my messages");
            System.out.println("0. Logout");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println(admin);
                    break;
                case "2":
                    if (dataStore.getLogs().isEmpty()) {
                        System.out.println("No logs found.");
                    } else {
                        dataStore.getLogs().forEach(System.out::println);
                    }
                    break;
                case "3":
                    dataStore.getUsers().forEach(System.out::println);
                    break;
                case "4":
                    viewAllNews();
                    break;
                case "5":
                    addUserAsAdmin(admin);
                    break;
                case "6":
                    removeUserAsAdmin(admin);
                    break;
                case "7":
                    updateUserAsAdmin(admin);
                    break;
                case "8":
                    sendMessage(admin);
                    break;
                case "9":
                    viewMyMessages(admin);
                    break;
                case "0":
                    inMenu = false;
                    System.out.println("Logged out.");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void addUserAsAdmin(Admin admin) {
        System.out.println("Choose user type:");
        System.out.println("1. User");
        System.out.println("2. Student");
        System.out.println("3. Graduate student");
        System.out.println("4. Employee");
        System.out.println("5. Teacher");
        System.out.println("6. Manager");
        System.out.println("7. Admin");
        System.out.println("8. Tech support specialist");
        System.out.print("Your choice: ");
        String userType = scanner.nextLine();

        System.out.print("Enter login: ");
        String login = scanner.nextLine();

        if (dataStore.findUserByLogin(login) != null) {
            System.out.println("User with this login already exists.");
            return;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        Language language = readLanguage();
        User newUser;

        switch (userType) {
            case "1":
                newUser = UserFactory.createUser(login, password, name, language);
                break;
            case "2":
                newUser = createStudentWithFactory(login, password, name, language);
                break;
            case "3":
                newUser = createGraduateStudentWithFactory(login, password, name, language);
                if (newUser == null) {
                    return;
                }
                break;
            case "4":
                newUser = createEmployeeWithFactory(login, password, name, language);
                break;
            case "5":
                newUser = createTeacherWithFactory(login, password, name, language);
                break;
            case "6":
                newUser = createManagerWithFactory(login, password, name, language);
                break;
            case "7":
                newUser = createAdminWithFactory(login, password, name, language);
                break;
            case "8":
                newUser = createTechSupportWithFactory(login, password, name, language);
                break;
            default:
                System.out.println("Invalid user type.");
                return;
        }

        dataStore.addUser(newUser);
        addAdminLog("Added user " + login, admin);
        System.out.println("User added: " + newUser);
    }

    private User createStudentWithFactory(String login, String password, String name, Language language) {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();

        System.out.print("Enter year of study: ");
        int yearOfStudy = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter major: ");
        String major = scanner.nextLine();

        System.out.print("Enter credits: ");
        int credits = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter failed courses count: ");
        int failedCoursesCount = Integer.parseInt(scanner.nextLine());

        return UserFactory.createStudent(login, password, name, language,
                studentId, yearOfStudy, major, credits, failedCoursesCount);
    }

    private User createGraduateStudentWithFactory(String login, String password, String name, Language language) {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();

        System.out.print("Enter year of study: ");
        int yearOfStudy = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter major: ");
        String major = scanner.nextLine();

        System.out.print("Enter credits: ");
        int credits = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter failed courses count: ");
        int failedCoursesCount = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter degree type: ");
        String degreeType = scanner.nextLine();

        System.out.print("Enter supervisor login: ");
        String supervisorLogin = scanner.nextLine();

        User supervisorUser = dataStore.findUserByLogin(supervisorLogin);

        if (!(supervisorUser instanceof Researcher)) {
            System.out.println("Supervisor must be a researcher.");
            return null;
        }

        System.out.print("Enter published papers count: ");
        int publishedPapersCount = Integer.parseInt(scanner.nextLine());

        try {
            return UserFactory.createGraduateStudent(login, password, name, language,
                    studentId, yearOfStudy, major, credits, failedCoursesCount,
                    degreeType, (Researcher) supervisorUser, publishedPapersCount);
        } catch (InvalidSupervisorException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private User createEmployeeWithFactory(String login, String password, String name, Language language) {
        System.out.print("Enter employee ID: ");
        String employeeId = scanner.nextLine();

        System.out.print("Enter department: ");
        String department = scanner.nextLine();

        System.out.print("Enter salary: ");
        double salary = Double.parseDouble(scanner.nextLine());

        return UserFactory.createEmployee(login, password, name, language,
                employeeId, department, salary);
    }

    private User createTeacherWithFactory(String login, String password, String name, Language language) {
        System.out.print("Enter employee ID: ");
        String employeeId = scanner.nextLine();

        System.out.print("Enter department: ");
        String department = scanner.nextLine();

        System.out.print("Enter salary: ");
        double salary = Double.parseDouble(scanner.nextLine());

        TeacherPosition position = readTeacherPosition();

        System.out.print("Enter rating: ");
        double rating = Double.parseDouble(scanner.nextLine());

        return UserFactory.createTeacher(login, password, name, language,
                employeeId, department, salary, position, rating);
    }

    private User createManagerWithFactory(String login, String password, String name, Language language) {
        System.out.print("Enter employee ID: ");
        String employeeId = scanner.nextLine();

        System.out.print("Enter department: ");
        String department = scanner.nextLine();

        System.out.print("Enter salary: ");
        double salary = Double.parseDouble(scanner.nextLine());

        ManagerType managerType = readManagerType();

        return UserFactory.createManager(login, password, name, language,
                employeeId, department, salary, managerType);
    }

    private User createAdminWithFactory(String login, String password, String name, Language language) {
        System.out.print("Enter employee ID: ");
        String employeeId = scanner.nextLine();

        System.out.print("Enter department: ");
        String department = scanner.nextLine();

        System.out.print("Enter salary: ");
        double salary = Double.parseDouble(scanner.nextLine());

        return UserFactory.createAdmin(login, password, name, language,
                employeeId, department, salary);
    }

    private User createTechSupportWithFactory(String login, String password, String name, Language language) {
        System.out.print("Enter employee ID: ");
        String employeeId = scanner.nextLine();

        System.out.print("Enter department: ");
        String department = scanner.nextLine();

        System.out.print("Enter salary: ");
        double salary = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter specialization: ");
        String specialization = scanner.nextLine();

        return UserFactory.createTechSupportSpecialist(login, password, name, language,
                employeeId, department, salary, specialization);
    }

    private void removeUserAsAdmin(Admin admin) {
        System.out.print("Enter login of user to remove: ");
        String login = scanner.nextLine();

        User user = dataStore.findUserByLogin(login);

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        if (user.equals(admin)) {
            System.out.println("Admin cannot remove own account.");
            return;
        }

        if (dataStore.removeUser(user)) {
            addAdminLog("Removed user " + login, admin);
            System.out.println("User removed.");
        } else {
            System.out.println("User could not be removed.");
        }
    }

    private void updateUserAsAdmin(Admin admin) {
        System.out.print("Enter login of user to update: ");
        String oldLogin = scanner.nextLine();

        User user = dataStore.findUserByLogin(oldLogin);

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.print("Enter new login or press Enter to keep current: ");
        String newLogin = scanner.nextLine();

        if (!newLogin.isEmpty() && !newLogin.equalsIgnoreCase(oldLogin)) {
            if (dataStore.findUserByLogin(newLogin) != null) {
                System.out.println("User with this login already exists.");
                return;
            }
            user.setLogin(newLogin);
        }

        System.out.print("Enter new password or press Enter to keep current: ");
        String newPassword = scanner.nextLine();
        if (!newPassword.isEmpty()) {
            user.setPassword(newPassword);
        }

        System.out.print("Enter new name or press Enter to keep current: ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            user.setName(newName);
        }

        System.out.print("Change language? (yes/no): ");
        String changeLanguage = scanner.nextLine();
        if (changeLanguage.equalsIgnoreCase("yes")) {
            user.setLanguage(readLanguage());
        }

        addAdminLog("Updated user " + oldLogin, admin);
        System.out.println("User updated: " + user);
    }

    private Language readLanguage() {
        System.out.println("Choose language:");
        System.out.println("1. EN");
        System.out.println("2. RU");
        System.out.println("3. KZ");
        System.out.print("Your choice: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "2":
                return Language.RU;
            case "3":
                return Language.KZ;
            case "1":
            default:
                return Language.EN;
        }
    }

    private TeacherPosition readTeacherPosition() {
        System.out.println("Choose teacher position:");
        System.out.println("1. TUTOR");
        System.out.println("2. LECTOR");
        System.out.println("3. SENIOR_LECTOR");
        System.out.println("4. PROFESSOR");
        System.out.print("Your choice: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "2":
                return TeacherPosition.LECTOR;
            case "3":
                return TeacherPosition.SENIOR_LECTOR;
            case "4":
                return TeacherPosition.PROFESSOR;
            case "1":
            default:
                return TeacherPosition.TUTOR;
        }
    }

    private ManagerType readManagerType() {
        System.out.println("Choose manager type:");
        System.out.println("1. OR");
        System.out.println("2. DEPARTMENT");
        System.out.println("3. DEAN_OFFICE");
        System.out.print("Your choice: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "2":
                return ManagerType.DEPARTMENT;
            case "3":
                return ManagerType.DEAN_OFFICE;
            case "1":
            default:
                return ManagerType.OR;
        }
    }

    private void addAdminLog(String action, Admin admin) {
        dataStore.addLog(new LogEntry(action, LocalDateTime.now().toString(), admin.getLogin()));
    }

    private void openTechSupportMenu(TechSupportSpecialist specialist) {
        boolean inMenu = true;

        while (inMenu) {
            System.out.println();
            System.out.println("=== TECH SUPPORT MENU ===");
            System.out.println("1. View profile");
            System.out.println("2. View requests");
            System.out.println("3. Update request status");
            System.out.println("4. Send message");
            System.out.println("5. View my messages");
            System.out.println("0. Logout");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println(specialist);
                    break;
                case "2":
                    viewRequests();
                    break;
                case "3":
                    updateRequestStatus();
                    break;
                case "4":
                    sendMessage(specialist);
                    break;
                case "5":
                    viewMyMessages(specialist);
                    break;
                case "0":
                    inMenu = false;
                    System.out.println("Logged out.");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void sendMessage(Employee sender) {
        System.out.print("Enter receiver employee login: ");
        String receiverLogin = scanner.nextLine();

        User receiver = dataStore.findUserByLogin(receiverLogin);

        if (!(receiver instanceof Employee)) {
            System.out.println("Employee receiver not found.");
            return;
        }

        if (receiver.getLogin().equalsIgnoreCase(sender.getLogin())) {
            System.out.println("You cannot send a message to yourself.");
            return;
        }

        System.out.print("Enter message text: ");
        String text = scanner.nextLine();

        Message message = new Message(
                sender.getLogin(),
                receiver.getLogin(),
                text,
                LocalDateTime.now().toString()
        );

        dataStore.addMessage(message);
        System.out.println("Message sent.");
    }

    private void viewMyMessages(Employee employee) {
        boolean found = false;

        for (Message message : dataStore.getMessages()) {
            if (message.getSenderLogin().equalsIgnoreCase(employee.getLogin())
                    || message.getReceiverLogin().equalsIgnoreCase(employee.getLogin())) {
                System.out.println(message);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No messages found.");
        }
    }

    private void openSearchMenu() {
        boolean inMenu = true;

        while (inMenu) {
            System.out.println();
            System.out.println("=== SEARCH MENU ===");
            System.out.println("1. Search courses");
            System.out.println("2. Search news");
            System.out.println("3. Search research papers");
            System.out.println("0. Back");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    searchCourses();
                    break;
                case "2":
                    searchNews();
                    break;
                case "3":
                    searchResearchPapers();
                    break;
                case "0":
                    inMenu = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void searchCourses() {
        System.out.print("Enter keyword: ");
        String keyword = scanner.nextLine();

        List<Course> results = searchService.searchCoursesByKeyword(keyword);

        if (results.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }

        results.forEach(System.out::println);
    }

    private void searchNews() {
        System.out.print("Enter keyword: ");
        String keyword = scanner.nextLine();

        List<News> results = searchService.searchNewsByKeyword(keyword);

        if (results.isEmpty()) {
            System.out.println("No news found.");
            return;
        }

        results.forEach(System.out::println);
    }

    private void searchResearchPapers() {
        System.out.print("Enter keyword: ");
        String keyword = scanner.nextLine();

        List<model.research.ResearchPaper> results = searchService.searchResearchPapersByKeyword(keyword);

        if (results.isEmpty()) {
            System.out.println("No research papers found.");
            return;
        }

        results.forEach(System.out::println);
    }

    private void registerStudentForCourse(Student student) {
        printAllCourses();

        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();

        Course course = dataStore.findCourseByCode(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        try {
            Registration registration = registrationService.registerStudentToCourse(student, course);
            System.out.println("Registration created: " + registration);
        } catch (CreditLimitExceededException e) {
            System.out.println(e.getMessage());
        }
    }

    private void putMarks() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();

        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();

        System.out.print("Enter first attestation: ");
        double first = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter second attestation: ");
        double second = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter final exam: ");
        double finalExam = Double.parseDouble(scanner.nextLine());

        Mark mark = markService.createMark(first, second, finalExam);
        markService.addMarkToStudentTranscript(studentId, courseCode, mark);

        System.out.println("Mark added successfully.");
    }

    private void approveRegistration() {
        List<Registration> pendingRegistrations = registrationService.getPendingRegistrations();

        if (pendingRegistrations.isEmpty()) {
            System.out.println("No pending registrations.");
            return;
        }

        for (int i = 0; i < pendingRegistrations.size(); i++) {
            System.out.println((i + 1) + ". " + pendingRegistrations.get(i));
        }

        System.out.print("Choose registration number to approve: ");
        int choice = Integer.parseInt(scanner.nextLine());

        if (choice < 1 || choice > pendingRegistrations.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Registration registration = pendingRegistrations.get(choice - 1);
        registrationService.approveRegistration(registration);

        System.out.println("Registration approved: " + registration);
    }

    private void assignTeacherToCourse() {
        printAllCourses();

        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();

        Course course = dataStore.findCourseByCode(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        System.out.print("Enter teacher login: ");
        String teacherLogin = scanner.nextLine();

        User user = dataStore.findUserByLogin(teacherLogin);

        if (!(user instanceof Teacher)) {
            System.out.println("Teacher not found.");
            return;
        }

        course.assignTeacher(teacherLogin);
        System.out.println("Teacher assigned to course: " + course);
    }

    private void viewManagerStatisticalReport() {
        int totalRegistrations = dataStore.getRegistrations().size();
        int approvedRegistrations = 0;
        int pendingRegistrations = 0;

        for (Registration registration : dataStore.getRegistrations()) {
            if (registration.isApproved()) {
                approvedRegistrations++;
            } else {
                pendingRegistrations++;
            }
        }

        Map<String, Integer> requestStatusCounts = new HashMap<>();
        Map<String, Integer> requestTypeCounts = new HashMap<>();

        for (Request request : dataStore.getRequests()) {
            String status = request.getStatus().toString();
            String type = request.getRequestType();

            requestStatusCounts.put(status, requestStatusCounts.getOrDefault(status, 0) + 1);
            requestTypeCounts.put(type, requestTypeCounts.getOrDefault(type, 0) + 1);
        }

        int teacherCount = 0;
        double totalRating = 0;

        for (User user : dataStore.getUsers()) {
            if (user instanceof Teacher) {
                teacherCount++;
                totalRating += ((Teacher) user).getRating();
            }
        }

        double averageTeacherRating = 0;

        if (teacherCount > 0) {
            averageTeacherRating = totalRating / teacherCount;
        }

        int totalPapers = researchService.getAllPapersFromAllJournals().size();

        System.out.println();
        System.out.println("=== MANAGER STATISTICAL REPORT ===");
        System.out.println("Total courses: " + dataStore.getCourses().size());
        System.out.println("Total users: " + dataStore.getUsers().size());
        System.out.println("Total registrations: " + totalRegistrations);
        System.out.println("Approved registrations: " + approvedRegistrations);
        System.out.println("Pending registrations: " + pendingRegistrations);
        System.out.println("Requests by status: " + requestStatusCounts);
        System.out.println("Requests by type: " + requestTypeCounts);
        System.out.println("Teacher count: " + teacherCount);
        System.out.println("Average teacher rating: " + String.format("%.2f", averageTeacherRating));
        System.out.println("Research paper count: " + totalPapers);
    }

    private void createNews() {
        System.out.print("Enter news title: ");
        String title = scanner.nextLine();

        System.out.print("Enter news content: ");
        String content = scanner.nextLine();

        System.out.print("Enter topic: ");
        String topic = scanner.nextLine();

        News news = newsService.createNews(title, content, topic, false, "2026-04-19");
        System.out.println("News created: " + news);

        System.out.print("Do you want to add a comment now? (yes/no): ");
        String answer = scanner.nextLine();

        if (answer.equalsIgnoreCase("yes")) {
            System.out.print("Enter author name: ");
            String author = scanner.nextLine();

            System.out.print("Enter comment text: ");
            String text = scanner.nextLine();

            Comment comment = new Comment(author, text, "2026-04-19");
            newsService.addComment(news, comment);

            System.out.println("Comment added.");
        }
    }

    private void createPinnedResearchNews() {
        System.out.print("Enter pinned research news title: ");
        String title = scanner.nextLine();

        System.out.print("Enter pinned research news content: ");
        String content = scanner.nextLine();

        News news = newsService.createPinnedResearchNews(title, content, "2026-04-19");
        System.out.println("Pinned research news created: " + news);
    }

    private void announceTopCitedResearcher() {
        String topResearcher = researchService.findTopCitedResearcher();

        if (topResearcher == null) {
            System.out.println("No researcher data found.");
            return;
        }

        int citations = researchService.getTotalCitationsForResearcher(topResearcher);

        News news = newsService.createPinnedResearchNews(
                "Top Cited Researcher Announcement",
                "Top cited researcher: " + topResearcher + " with total citations: " + citations,
                "2026-04-19"
        );

        System.out.println("Announcement created: " + news);
    }

    private void viewAllNews() {
        List<News> allNews = newsService.getSortedNewsPinnedFirst();

        if (allNews.isEmpty()) {
            System.out.println("No news found.");
            return;
        }

        allNews.forEach(System.out::println);
    }

    private void viewRequests() {
        List<Request> requests = requestService.getAllRequests();

        if (requests.isEmpty()) {
            System.out.println("No requests found.");
            return;
        }

        requests.forEach(System.out::println);
    }

    private void updateRequestStatus() {
        List<Request> requests = requestService.getAllRequests();

        if (requests.isEmpty()) {
            System.out.println("No requests found.");
            return;
        }

        for (int i = 0; i < requests.size(); i++) {
            System.out.println((i + 1) + ". " + requests.get(i));
        }

        System.out.print("Choose request number: ");
        int requestNumber = Integer.parseInt(scanner.nextLine());

        if (requestNumber < 1 || requestNumber > requests.size()) {
            System.out.println("Invalid request number.");
            return;
        }

        Request request = requests.get(requestNumber - 1);

        System.out.println("1. Mark viewed");
        System.out.println("2. Accept");
        System.out.println("3. Reject");
        System.out.println("4. Mark done");
        System.out.print("Choose status action: ");

        String action = scanner.nextLine();

        switch (action) {
            case "1":
                requestService.markViewed(request);
                break;
            case "2":
                requestService.accept(request);
                break;
            case "3":
                requestService.reject(request);
                break;
            case "4":
                requestService.markDone(request);
                break;
            default:
                System.out.println("Invalid action.");
                return;
        }

        System.out.println("Updated request: " + request);
    }

    private void createRequest(String authorLogin) {
        System.out.print("Enter request ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter request text: ");
        String text = scanner.nextLine();

        UrgencyLevel urgencyLevel = readUrgencyLevel();

        if (urgencyLevel == null) {
            System.out.println("Invalid urgency level.");
            return;
        }

        Request request = requestService.createRequest(
                id,
                authorLogin,
                text,
                urgencyLevel,
                LocalDateTime.now().toString()
        );
        System.out.println("Request created: " + request);
    }

    private void createTeacherComplaint(Teacher teacher) {
        String id = "C" + (dataStore.getRequests().size() + 1);

        System.out.print("Enter complaint text: ");
        String text = scanner.nextLine();

        UrgencyLevel urgencyLevel = readUrgencyLevel();

        if (urgencyLevel == null) {
            System.out.println("Invalid urgency level.");
            return;
        }

        Request complaint = requestService.createComplaint(
                id,
                teacher.getLogin(),
                text,
                urgencyLevel,
                LocalDateTime.now().toString()
        );

        System.out.println("Complaint sent: " + complaint);
    }

    private UrgencyLevel readUrgencyLevel() {
        System.out.println("Choose urgency level:");
        System.out.println("1. LOW");
        System.out.println("2. MEDIUM");
        System.out.println("3. HIGH");
        System.out.print("Your choice: ");

        String urgencyChoice = scanner.nextLine();

        switch (urgencyChoice) {
            case "1":
                return UrgencyLevel.LOW;
            case "2":
                return UrgencyLevel.MEDIUM;
            case "3":
                return UrgencyLevel.HIGH;
            default:
                return null;
        }
    }

    private void viewJournals() {
        List<model.research.Journal> journals = researchService.getAllJournals();

        if (journals.isEmpty()) {
            System.out.println("No journals found.");
            return;
        }

        journals.forEach(System.out::println);
    }

    private void viewResearchProjects() {
        List<model.research.ResearchProject> projects = researchService.getAllResearchProjects();

        if (projects.isEmpty()) {
            System.out.println("No research projects found.");
            return;
        }

        projects.forEach(System.out::println);
    }

    private void viewAllResearchPapers() {
        List<model.research.ResearchPaper> papers = researchService.getAllPapersFromAllJournals();

        if (papers.isEmpty()) {
            System.out.println("No research papers found.");
            return;
        }

        for (int i = 0; i < papers.size(); i++) {
            System.out.println((i + 1) + ". " + papers.get(i));
        }

        System.out.println("1. Show Plain Text citations");
        System.out.println("2. Show Bibtex citations");
        System.out.print("Choose citation format: ");
        String choice = scanner.nextLine();

        if (choice.equals("1")) {
            for (model.research.ResearchPaper paper : papers) {
                System.out.println(researchService.getPaperCitation(paper, CitationFormat.PLAIN_TEXT));
            }
        } else if (choice.equals("2")) {
            for (model.research.ResearchPaper paper : papers) {
                System.out.println(researchService.getPaperCitation(paper, CitationFormat.BIBTEX));
            }
        } else {
            System.out.println("Invalid option.");
        }
    }

    private void publishPaperToJournal() {
        List<model.research.Journal> journals = researchService.getAllJournals();

        if (journals.isEmpty()) {
            System.out.println("No journals found.");
            return;
        }

        for (int i = 0; i < journals.size(); i++) {
            System.out.println((i + 1) + ". " + journals.get(i));
        }

        System.out.print("Choose journal number: ");
        int journalNumber = Integer.parseInt(scanner.nextLine());

        if (journalNumber < 1 || journalNumber > journals.size()) {
            System.out.println("Invalid journal number.");
            return;
        }

        System.out.print("Enter paper title: ");
        String title = scanner.nextLine();

        System.out.print("Enter authors: ");
        String authors = scanner.nextLine();

        System.out.print("Enter journal name: ");
        String journalName = scanner.nextLine();

        System.out.print("Enter pages: ");
        int pages = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter publication year/date: ");
        String publicationDate = scanner.nextLine();

        System.out.print("Enter citations: ");
        int citations = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter DOI: ");
        String doi = scanner.nextLine();

        model.research.ResearchPaper paper = new model.research.ResearchPaper(
                title, authors, journalName, pages, publicationDate, citations, doi
        );

        model.research.Journal journal = journals.get(journalNumber - 1);
        researchService.publishPaperToJournal(journal, paper);

        System.out.println("Paper published to journal: " + journal.getName());
    }

    private void addPaperToProject() {
        List<model.research.ResearchProject> projects = researchService.getAllResearchProjects();
        List<model.research.ResearchPaper> papers = researchService.getAllPapersFromAllJournals();

        if (projects.isEmpty()) {
            System.out.println("No research projects found.");
            return;
        }

        if (papers.isEmpty()) {
            System.out.println("No research papers found.");
            return;
        }

        System.out.println("Choose project:");
        for (int i = 0; i < projects.size(); i++) {
            System.out.println((i + 1) + ". " + projects.get(i));
        }
        System.out.print("Your choice: ");
        int projectNumber = Integer.parseInt(scanner.nextLine());

        if (projectNumber < 1 || projectNumber > projects.size()) {
            System.out.println("Invalid project number.");
            return;
        }

        System.out.println("Choose paper:");
        for (int i = 0; i < papers.size(); i++) {
            System.out.println((i + 1) + ". " + papers.get(i));
        }
        System.out.print("Your choice: ");
        int paperNumber = Integer.parseInt(scanner.nextLine());

        if (paperNumber < 1 || paperNumber > papers.size()) {
            System.out.println("Invalid paper number.");
            return;
        }

        model.research.ResearchProject project = projects.get(projectNumber - 1);
        model.research.ResearchPaper paper = papers.get(paperNumber - 1);

        researchService.addPaperToProject(project, paper);

        System.out.println("Paper added to project successfully.");
    }

    private void rateTeacher() {
        System.out.print("Enter teacher login: ");
        String teacherLogin = scanner.nextLine();

        User user = dataStore.findUserByLogin(teacherLogin);

        if (!(user instanceof Teacher)) {
            System.out.println("Teacher not found.");
            return;
        }

        Teacher teacher = (Teacher) user;

        System.out.print("Enter rating (0 to 5): ");
        double rating = Double.parseDouble(scanner.nextLine());

        ratingService.rateTeacher(teacher, rating);
        System.out.println("Teacher rating updated: " + teacher);
    }

    private void markAttendance() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();

        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();

        System.out.println("1. Present");
        System.out.println("2. Absent");
        System.out.print("Choose attendance status: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                attendanceService.markPresent(studentId, courseCode);
                System.out.println("Attendance marked as present.");
                break;
            case "2":
                attendanceService.markAbsent(studentId, courseCode);
                System.out.println("Attendance marked as absent.");
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private void viewAttendanceRecords() {
        List<AttendanceRecord> records = attendanceService.getAllAttendanceRecords();

        if (records.isEmpty()) {
            System.out.println("No attendance records found.");
            return;
        }

        records.forEach(System.out::println);
    }

    private void viewAssignedCourses(Teacher teacher) {
        boolean found = false;

        for (Course course : dataStore.getCourses()) {
            if (course.isTeacherAssigned(teacher.getLogin())) {
                System.out.println(course);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No assigned courses found.");
        }
    }

    private void printAllCourses() {
        if (dataStore.getCourses().isEmpty()) {
            System.out.println("No courses found.");
            return;
        }

        dataStore.getCourses().forEach(System.out::println);
    }

    private void printAllRegistrations() {
        if (dataStore.getRegistrations().isEmpty()) {
            System.out.println("No registrations found.");
            return;
        }

        dataStore.getRegistrations().forEach(System.out::println);
    }
}
