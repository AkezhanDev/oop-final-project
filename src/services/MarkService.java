package services;

import model.academic.Mark;
import model.academic.Transcript;
import storage.DataStore;

public class MarkService {
    private DataStore dataStore;
    private RegistrationService registrationService;

    public MarkService() {
        this.dataStore = DataStore.getInstance();
        this.registrationService = new RegistrationService();
    }

    public Mark createMark(double firstAttestation, double secondAttestation, double finalExam) {
        return new Mark(firstAttestation, secondAttestation, finalExam);
    }

    public void addMarkToTranscript(Transcript transcript, String courseCode, Mark mark) {
        transcript.addMark(courseCode, mark);
    }

    public void addMarkToStudentTranscript(String studentId, String courseCode, Mark mark) {
        Transcript transcript = dataStore.getTranscript(studentId);

        if (transcript == null) {
            transcript = new Transcript();
            dataStore.putTranscript(studentId, transcript);
        }

        transcript.addMark(courseCode, mark);
        registrationService.completeRegistrationByMark(studentId, courseCode, mark.calculateTotal());
    }
}
