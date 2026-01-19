package model;

public class Doctor extends Person {
    private String specialization;

    public Doctor(int id, String name, int age) {
        super(id, name, age);
    }

    @Override
    public String getRole() {
        return "Doctor";
    }

    @Override
    public void performDuties() {
        System.out.println("Examining patients and providing medical care");
    }

    public String getSpecialization() { return specialization; }

    public void setSpecialization(String specialization) {
        if (specialization == null || specialization.trim().isEmpty()) {
            throw new IllegalArgumentException("Specialization cannot be empty!");
        }
        this.specialization = specialization.trim();
    }

    @Override
    public String toString() {
        return super.toString() + ", Specialization: " + specialization;
    }
}