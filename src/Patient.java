public class Patient extends Person {
    private String bloodType;
    private String diagnosis;
    private boolean hasInsurance;

    public Patient(int id, String name, int age, String contactInfo,
                   String bloodType, String diagnosis, boolean hasInsurance) {
        super(id, name, age, contactInfo);
        this.bloodType = bloodType;
        this.diagnosis = diagnosis;
        this.hasInsurance = hasInsurance;
    }

    public Patient() {
        super();
        this.bloodType = "Unknown";
        this.diagnosis = "Not diagnosed";
        this.hasInsurance = false;
    }

    public String getBloodType() {
        return bloodType;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public boolean hasInsurance() {
        return hasInsurance;
    }

    public void setBloodType(String bloodType) {
        if (bloodType != null && !bloodType.trim().isEmpty()) {
            this.bloodType = bloodType;
        }
    }

    public void setDiagnosis(String diagnosis) {
        if (diagnosis != null && !diagnosis.trim().isEmpty()) {
            this.diagnosis = diagnosis;
        }
    }

    public void setHasInsurance(boolean hasInsurance) {
        this.hasInsurance = hasInsurance;
    }

    @Override
    public void work() {
        System.out.println("Patient " + getName() + " is recovering from " + diagnosis);
    }

    @Override
    public void introduce() {
        System.out.println("I am patient " + getName() + " (ID: " + getId() +
                "). Diagnosis: " + diagnosis);
    }

    @Override
    public String toString() {
        String insuranceStatus = hasInsurance ? "Insured" : "Not Insured";
        return super.toString() +
                "\n  Blood Type: " + bloodType +
                ", Diagnosis: " + diagnosis +
                ", " + insuranceStatus;
    }

    public boolean needsEmergencyCare() {
        return diagnosis != null &&
                (diagnosis.toLowerCase().contains("emergency") ||
                        diagnosis.toLowerCase().contains("severe") ||
                        diagnosis.toLowerCase().contains("critical"));
    }

    public boolean needsSurgery() {
        return diagnosis != null &&
                diagnosis.toLowerCase().contains("surgery");
    }

    public String getTreatmentPlan() {
        if (isChild()) {
            return "Pediatric treatment for " + getName();
        } else if (isSenior()) {
            return "Geriatric treatment for " + getName();
        }
        return "Standard treatment for " + getName();
    }

    public String getAgeCategory() {
        if (getAge() < 18) {
            return "child";
        } else if (getAge() < 65) {
            return "adult";
        } else {
            return "senior";
        }
    }

    public boolean isMinor() {
        return getAge() < 18;
    }
}