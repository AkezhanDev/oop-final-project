package model.academic;

public class Mark {
    private double firstAttestation;
    private double secondAttestation;
    private double finalExam;

    public Mark() {
    }

    public Mark(double firstAttestation, double secondAttestation, double finalExam) {
        this.firstAttestation = firstAttestation;
        this.secondAttestation = secondAttestation;
        this.finalExam = finalExam;
    }

    public double getFirstAttestation() {
        return firstAttestation;
    }

    public void setFirstAttestation(double firstAttestation) {
        this.firstAttestation = firstAttestation;
    }

    public double getSecondAttestation() {
        return secondAttestation;
    }

    public void setSecondAttestation(double secondAttestation) {
        this.secondAttestation = secondAttestation;
    }

    public double getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(double finalExam) {
        this.finalExam = finalExam;
    }

    public double calculateTotal() {
        return firstAttestation + secondAttestation + finalExam;
    }

    @Override
    public String toString() {
        return "Mark: firstAttestation=" + firstAttestation +
                ", secondAttestation=" + secondAttestation +
                ", finalExam=" + finalExam +
                ", total=" + calculateTotal();
    }
}