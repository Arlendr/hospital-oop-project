public class Main {
    public static void main(String[] args) {
        Patient patient = new Patient(1001, "John Smith", 35, "A+", "Hypertension", true);

        Doctor doctor = new Doctor(201, "Dr. Wilson", "Cardiologist", 15, true, 20, "Room 301");

        Appointment appointment = new Appointment(301, "John Smith", "Dr. Wilson",
                "2025-03-15", "10:30", "Scheduled", "Room 201");

        System.out.println("PATIENT INFO");
        System.out.println(patient);
        System.out.println("Is minor: " + (patient.getAge() < 18));
        System.out.println("Has insurance: " + patient.getHasinsurance());

        System.out.println("\nDOCTOR INFO");
        System.out.println("Name: Dr. " + doctor.getName());
        System.out.println("Specialization: " + doctor.getSpecialization());
        System.out.println("Experience: " + doctor.getYearsOfExp() + " years");
        System.out.println("On duty: " + doctor.getIsonduty());

        System.out.println("\nAPPOINTMENT INFO");
        System.out.println("Patient: " + appointment.getPatientname());
        System.out.println("Doctor: " + appointment.getDoctorname());
        System.out.println("Date: " + appointment.getDate());
        System.out.println("Time: " + appointment.getTime());
        System.out.println("Status: " + appointment.getStatus());

        System.out.println("\nDEMONSTRATING MODIFICATIONS");

        System.out.println("\n1. Changing patient diagnosis:");
        System.out.println("Before: " + patient.getDiagnosis());
        patient.setDiagnosis("Hypertension Stage 2");
        System.out.println("After: " + patient.getDiagnosis());

        System.out.println("\n2. Completing appointment:");
        System.out.println("Before: " + appointment.getStatus());
        appointment.setStatus("Completed");
        System.out.println("After: " + appointment.getStatus());

        System.out.println("\nFINAL SUMMARY");
        System.out.println("Patient: " + patient.getFullname());
        System.out.println("Doctor: Dr. " + doctor.getName());
        System.out.println("Appointment status: " + appointment.getStatus());

    }
}