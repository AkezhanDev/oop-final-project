package model.academic;

import java.util.HashMap;
import java.util.Map;

public class Transcript {
    private Map<String, Mark> courseMarks;

    public Transcript() {
        courseMarks = new HashMap<>();
    }

    public Map<String, Mark> getCourseMarks() {
        return courseMarks;
    }

    public void addMark(String courseCode, Mark mark) {
        courseMarks.put(courseCode, mark);
    }

    public Mark getMark(String courseCode) {
        return courseMarks.get(courseCode);
    }

    @Override
    public String toString() {
        return "Transcript: courseMarks=" + courseMarks;
    }
}