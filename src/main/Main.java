package main;

import enums.*;
import model.academic.Course;
import model.academic.Lesson;
import model.communication.News;
import model.requests.Request;
import model.research.Journal;
import model.research.ResearchPaper;
import model.research.ResearchProject;
import model.users.*;
import services.ConsoleMenuService;
import storage.DataStore;
import storage.LogEntry;

public class Main {
    public static void main(String[] args) {
        DataStore dataStore = DataStore.getInstance();

        User user = new User("user1", "1111", "Aruzhan", Language.EN);

        Employee employee = new Employee(
                "emp1",
                "2222",
                "Dias",
                Language.EN,
                "E001",
                "FIT",
                350000
        );

        Student student = new Student(
                "stud1",
                "3333",
                "Alim",
                Language.EN,
                "S001",
                2,
                "Computer Science",
                18,
                1
        );

        GraduateStudent graduateStudent = new GraduateStudent(
                "grad1",
                "4444",
                "Aida",
                Language.EN,
                "GS001",
                1,
                "Data Science",
                15,
                0,
                "Master",
                "Dr. Nurlan",
                2
        );

        Teacher teacher = new Teacher(
                "teach1",
                "5555",
                "Madina",
                Language.EN,
                "T001",
                "FIT",
                500000,
                TeacherPosition.PROFESSOR,
                4.8
        );

        Manager manager = new Manager(
                "manager1",
                "6666",
                "Nursultan",
                Language.EN,
                "M001",
                "Office of Registrar",
                600000,
                ManagerType.OR
        );

        Admin admin = new Admin(
                "admin1",
                "7777",
                "Dana",
                Language.EN,
                "A001",
                "Administration",
                700000
        );

        TechSupportSpecialist specialist = new TechSupportSpecialist(
                "tech1",
                "8888",
                "Arman",
                Language.EN,
                "TS001",
                "IT Support",
                400000,
                "Projectors and Printers"
        );

        dataStore.addUser(user);
        dataStore.addUser(employee);
        dataStore.addUser(student);
        dataStore.addUser(graduateStudent);
        dataStore.addUser(teacher);
        dataStore.addUser(manager);
        dataStore.addUser(admin);
        dataStore.addUser(specialist);

        Course oopCourse = new Course(
                "CS101",
                "Object-Oriented Programming",
                2,
                CourseType.MAJOR,
                "Computer Science",
                2
        );
        oopCourse.addLesson(new Lesson(LessonType.LECTURE, "Monday", "10:00", "Room 301"));
        oopCourse.addLesson(new Lesson(LessonType.PRACTICE, "Wednesday", "12:00", "Room 205"));

        Course dbCourse = new Course(
                "CS202",
                "Database Systems",
                5,
                CourseType.MAJOR,
                "Computer Science",
                2
        );
        dbCourse.addLesson(new Lesson(LessonType.LECTURE, "Tuesday", "11:00", "Room 210"));
        dbCourse.addLesson(new Lesson(LessonType.PRACTICE, "Thursday", "14:00", "Room 115"));

        Course statsCourse = new Course(
                "ST201",
                "Statistics",
                4,
                CourseType.MINOR,
                "Data Science",
                2
        );
        statsCourse.addLesson(new Lesson(LessonType.LECTURE, "Friday", "09:00", "Room 401"));

        dataStore.addCourse(oopCourse);
        dataStore.addCourse(dbCourse);
        dataStore.addCourse(statsCourse);

        ResearchPaper paper1 = new ResearchPaper(
                "The Influence of Retaking a Course",
                "Aida, Madina",
                "KBTU Research Journal",
                12,
                "2026",
                5,
                "10.1234/kbtu.2026.001"
        );

        ResearchPaper paper2 = new ResearchPaper(
                "Improving Attendance Monitoring",
                "Arman, Nursultan",
                "Education Systems Review",
                9,
                "2025",
                8,
                "10.5678/esr.2025.010"
        );

        ResearchProject researchProject1 = new ResearchProject("Student Performance Analysis");
        researchProject1.addParticipant("Aida");
        researchProject1.addParticipant("Madina");
        researchProject1.addPublishedPaper(paper1);

        ResearchProject researchProject2 = new ResearchProject("Attendance and Academic Results");
        researchProject2.addParticipant("Arman");
        researchProject2.addParticipant("Nursultan");
        researchProject2.addPublishedPaper(paper2);

        dataStore.addResearchProject(researchProject1);
        dataStore.addResearchProject(researchProject2);

        Journal journal1 = new Journal("KBTU Research Journal");
        journal1.addPaper(paper1);
        journal1.subscribe("Alim");
        journal1.subscribe("Aruzhan");

        Journal journal2 = new Journal("Education Systems Review");
        journal2.addPaper(paper2);
        journal2.subscribe("Madina");
        journal2.subscribe("Dana");

        dataStore.addJournal(journal1);
        dataStore.addJournal(journal2);

        News news1 = new News(
                "New Research Paper Published",
                "A new research paper was published in the university journal.",
                "Research",
                true,
                "2026-04-19"
        );

        News news2 = new News(
                "Course Registration Is Open",
                "Students can now register for the next semester courses.",
                "Academic",
                false,
                "2026-04-20"
        );

        dataStore.addNews(news1);
        dataStore.addNews(news2);

        Request request1 = new Request(
                "R001",
                "teach1",
                "Projector in Room 301 is not working.",
                UrgencyLevel.HIGH,
                "2026-04-19"
        );

        Request request2 = new Request(
                "R002",
                "stud1",
                "Wi-Fi is unstable in the library.",
                UrgencyLevel.MEDIUM,
                "2026-04-20"
        );

        dataStore.addRequest(request1);
        dataStore.addRequest(request2);

        LogEntry logEntry1 = new LogEntry(
                "Created initial demo data",
                "2026-04-19 16:30",
                "admin1"
        );

        LogEntry logEntry2 = new LogEntry(
                "Added initial courses and research objects",
                "2026-04-19 16:45",
                "admin1"
        );

        dataStore.addLog(logEntry1);
        dataStore.addLog(logEntry2);

        ConsoleMenuService consoleMenuService = new ConsoleMenuService();
        consoleMenuService.start();
    }
}