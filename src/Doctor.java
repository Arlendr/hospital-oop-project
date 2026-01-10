public class Doctor extends Person {
    private String specialization;
    private int yearsOfExperience;
    private boolean isOnDuty;

    public Doctor(int id, String name, int age, String contactInfo,
                  String specialization, int yearsOfExperience, boolean isOnDuty) {
        super(id, name, age, contactInfo);
        this.specialization = specialization;
        this.yearsOfExperience = yearsOfExperience;
        this.isOnDuty = isOnDuty;
    }

    public Doctor() {
        super();
        this.specialization = "General Practitioner";
        this.yearsOfExperience = 0;
        this.isOnDuty = false;
    }

    public String getSpecialization() {
        return specialization;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public boolean isOnDuty() {
        return isOnDuty;
    }

    public void setSpecialization(String specialization) {
        if (specialization != null && !specialization.trim().isEmpty()) {
            this.specialization = specialization;
        }
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public void setOnDuty(boolean onDuty) {
        isOnDuty = onDuty;
    }

    @Override
    public void work() {
        System.out.println("Dr. " + getName() + " is consulting patients in " + specialization);
    }

    @Override
    public void introduce() {
        System.out.println("I am Dr. " + getName() + ", " + specialization +
                " with " + yearsOfExperience + " years of experience");
    }

    @Override
    public String toString() {
        String dutyStatus = isOnDuty ? "On Duty" : "Off Duty";
        return super.toString() +
                "\n  Specialization: " + specialization +
                ", Experience: " + yearsOfExperience + " years" +
                ", " + dutyStatus;
    }

    public boolean isExperienced() {
        return yearsOfExperience >= 10;
    }

    public boolean canPerformSurgery() {
        String spec = specialization.toLowerCase();
        return spec.contains("surgeon") || spec.contains("surgery");
    }

    public void prescribeMedication(String patientName, String medication) {
        System.out.println("Dr. " + getName() + " prescribes " + medication + " to " + patientName);
    }

    public String getExperienceLevel() {
        if (yearsOfExperience < 3) {
            return "Junior";
        } else if (yearsOfExperience < 10) {
            return "Middle";
        } else {
            return "Senior";
        }
    }
}