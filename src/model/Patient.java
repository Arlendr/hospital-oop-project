package model;

public class Patient extends Person implements Treatable {
    private String diagnosis;

    // Конструктор с ID
    public Patient(int id, String name, int age) {
        super(id, name, age);
    }

    // Конструктор без ID
    public Patient(String name, int age) {
        super(name, age);
    }

    @Override
    public String getRole() {
        return "Patient";
    }

    @Override
    public void performDuties() {
        System.out.println("Resting and recovering from " + diagnosis);
    }

    // Реализация интерфейса Treatable
    @Override
    public void performTreatment() {
        System.out.println("Receiving treatment for: " + diagnosis);
    }

    @Override
    public String getTreatmentDetails() {
        return "Patient Treatment - Diagnosis: " + diagnosis + ", Patient: " + getName();
    }

    // Геттер и сеттер
    public String getDiagnosis() { return diagnosis; }

    public void setDiagnosis(String diagnosis) {
        if (diagnosis == null || diagnosis.trim().isEmpty()) {
            throw new IllegalArgumentException("Diagnosis cannot be empty!");
        }
        this.diagnosis = diagnosis.trim();
    }

    @Override
    public String toString() {
        return super.toString() + ", Diagnosis: " + diagnosis;
    }
}