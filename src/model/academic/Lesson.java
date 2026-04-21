package model.academic;

import enums.LessonType;

import java.io.Serializable;

public class Lesson implements Serializable {
    private static final long serialVersionUID = 1L;

    private LessonType lessonType;
    private String day;
    private String time;
    private String room;

    public Lesson() {
    }

    public Lesson(LessonType lessonType, String day, String time, String room) {
        this.lessonType = lessonType;
        this.day = day;
        this.time = time;
        this.room = room;
    }

    public LessonType getLessonType() {
        return lessonType;
    }

    public void setLessonType(LessonType lessonType) {
        this.lessonType = lessonType;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Lesson: type=" + lessonType +
                ", day=" + day +
                ", time=" + time +
                ", room=" + room;
    }
}
