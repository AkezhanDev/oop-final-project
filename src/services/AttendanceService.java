package services;

import model.academic.AttendanceRecord;
import storage.DataStore;

import java.util.List;

public class AttendanceService {
    private DataStore dataStore;

    public AttendanceService() {
        this.dataStore = DataStore.getInstance();
    }

    public AttendanceRecord createAttendanceRecord(String studentId, String courseCode) {
        AttendanceRecord existing = dataStore.findAttendanceRecord(studentId, courseCode);

        if (existing != null) {
            return existing;
        }

        AttendanceRecord record = new AttendanceRecord(studentId, courseCode, 0, 0);
        dataStore.addAttendanceRecord(record);
        return record;
    }

    public void markPresent(String studentId, String courseCode) {
        AttendanceRecord record = createAttendanceRecord(studentId, courseCode);
        record.markPresent();
    }

    public void markAbsent(String studentId, String courseCode) {
        AttendanceRecord record = createAttendanceRecord(studentId, courseCode);
        record.markAbsent();
    }

    public List<AttendanceRecord> getAllAttendanceRecords() {
        return dataStore.getAttendanceRecords();
    }
}