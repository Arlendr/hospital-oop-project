import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("🏥 HOSPITAL SYSTEM - WEEK 4: INHERITANCE & POLYMORPHISM\n");


        Patient patient1 = new Patient(1001, "Aidar", 30, "+77011234567",
                "A+", "Hypertension", true);
        Patient patient2 = new Patient(1002, "Asel", 25, "+77012345678",
                "B-", "Emergency: Severe Migraine", false);
        Patient patient3 = new Patient(1003, "Baurzhan", 16, "+77013456789",
                "O+", "Appendicitis Surgery", true);

        Doctor doctor1 = new Doctor(2001, "Zhumabayev", 45, "+77014567890",
                "Cardiology", 15, true);
        Doctor doctor2 = new Doctor(2002, "Ahmetova", 38, "+77015678901",
                "Surgery", 8, true);

        Appointment appointment1 = new Appointment(3001, "Aidar", "Dr. Zhumabayev",
                "2025-03-10", "10:00", "Scheduled", "Room 301");
        Appointment appointment2 = new Appointment(3002, "Baurzhan", "Dr. Ahmetova",
                "2025-03-11", "09:00", "Scheduled", "Room 205");



        System.out.println("📚 1. POLYMORPHISM: ONE ARRAYLIST FOR ALL PERSON TYPES");
        System.out.println("=".repeat(60));

        ArrayList<Person> allPeople = new ArrayList<>();


        allPeople.add(patient1);
        allPeople.add(patient2);
        allPeople.add(patient3);
        allPeople.add(doctor1);
        allPeople.add(doctor2);

        System.out.println("Total people in list: " + allPeople.size());
        System.out.println("List contains: Patients, Doctors (all are Person objects)\n");



        System.out.println("✨ 2. METHOD OVERRIDING DEMONSTRATION");
        System.out.println("=".repeat(60));

        System.out.println("A. Calling work() on all people:");
        System.out.println("-".repeat(40));
        for (Person person : allPeople) {
            person.work();
        }

        System.out.println("\nB. Calling introduce() on all people:");
        System.out.println("-".repeat(40));
        for (Person person : allPeople) {
            person.introduce();
        }

        System.out.println("\nC. Calling toString() on all people:");
        System.out.println("-".repeat(40));
        for (Person person : allPeople) {
            System.out.println(person);
            System.out.println();
        }


        System.out.println("🔍 3. INSTANCEOF AND DOWNCASTING");
        System.out.println("=".repeat(60));

        int patientCount = 0;
        int doctorCount = 0;

        for (Person person : allPeople) {
            if (person instanceof Patient) {
                patientCount++;
                Patient patient = (Patient) person;
                System.out.println("Patient #" + patientCount + ": " + patient.getName());
                System.out.println("  Diagnosis: " + patient.getDiagnosis());
                System.out.println("  Needs emergency care: " + patient.needsEmergencyCare());
                System.out.println("  Treatment plan: " + patient.getTreatmentPlan());

            } else if (person instanceof Doctor) {
                doctorCount++;
                Doctor doctor = (Doctor) person;
                System.out.println("Doctor #" + doctorCount + ": Dr. " + doctor.getName());
                System.out.println("  Specialization: " + doctor.getSpecialization());
                System.out.println("  Can perform surgery: " + doctor.canPerformSurgery());
                System.out.println("  Experience level: " + doctor.getExperienceLevel());
            }
            System.out.println();
        }


        System.out.println("⚙️ 4. CHILD-SPECIFIC METHODS");
        System.out.println("=".repeat(60));

        System.out.println("Patient-specific methods:");
        System.out.println("Patient 1: " + patient1.getName());
        System.out.println("  Blood type: " + patient1.getBloodType());
        System.out.println("  Age category: " + patient1.getAgeCategory());
        System.out.println("  Needs surgery: " + patient1.needsSurgery());

        System.out.println("\nDoctor-specific methods:");
        doctor1.prescribeMedication(patient1.getName(), "Aspirin");
        System.out.println("Dr. " + doctor1.getName() + " is experienced: " + doctor1.isExperienced());


        System.out.println("\n📐 5. INHERITANCE AND SUPER KEYWORD");
        System.out.println("=".repeat(60));

        System.out.println("A. Patient class hierarchy:");
        System.out.println("   Patient extends Person");
        System.out.println("   Uses super() in constructor");
        System.out.println("   Overrides: work(), introduce(), toString()");

        System.out.println("\nB. Doctor class hierarchy:");
        System.out.println("   Doctor extends Person");
        System.out.println("   Uses super() in constructor");
        System.out.println("   Overrides: work(), introduce(), toString()");

        System.out.println("\nC. Fields inherited from Person:");
        System.out.println("   Both Patient and Doctor have:");
        System.out.println("   - id, name, age, contactInfo (protected in Person)");
        System.out.println("   - getters/setters for these fields");


        System.out.println("\n📅 6. APPOINTMENTS (WEEK 3 CODE)");
        System.out.println("=".repeat(60));

        System.out.println("Appointment 1:");
        System.out.println(appointment1);

        System.out.println("\nAppointment 2:");
        System.out.println(appointment2);

        System.out.println("\nModifying appointments:");
        appointment1.complete();
        appointment2.reschedule("2025-03-12", "14:30");


        System.out.println("\n" + "=".repeat(70));
        System.out.println("                     SUMMARY - WEEK 4 REQUIREMENTS");
        System.out.println("=".repeat(70));

        System.out.println("✅ 1. INHERITANCE:");
        System.out.println("   • Patient extends Person");
        System.out.println("   • Doctor extends Person");
        System.out.println("   • Both use super() in constructors");

        System.out.println("\n✅ 2. METHOD OVERRIDING:");
        System.out.println("   • @Override work(), introduce(), toString()");
        System.out.println("   • Each class has different implementation");

        System.out.println("\n✅ 3. POLYMORPHISM:");
        System.out.println("   • ArrayList<Person> stores different types");
        System.out.println("   • Same method calls produce different behavior");

        System.out.println("\n✅ 4. INSTANCEOF AND DOWNCASTING:");
        System.out.println("   • instanceof used to check object type");
        System.out.println("   • Downcasting: (Patient) person, (Doctor) person");
        System.out.println("   • Access child-specific methods after casting");

        System.out.println("\n✅ 5. PROTECTED FIELDS:");
        System.out.println("   • Person has protected fields");
        System.out.println("   • Child classes can access them directly");

        System.out.println("\n✅ 6. WEEK 3 CODE INTEGRATED:");
        System.out.println("   • Appointment class (from Week 3)");
        System.out.println("   • Works alongside inheritance structure");

        System.out.println("\n" + "=".repeat(70));
        System.out.println("Total Patients: " + patientCount + " | Total Doctors: " + doctorCount);
        System.out.println("Total Appointments: 2");
        System.out.println("ALL WEEK 4 REQUIREMENTS COMPLETED! 🎯");

    }
}