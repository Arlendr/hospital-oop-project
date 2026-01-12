import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Person> allPeople = new ArrayList<>();
    private static ArrayList<Patient> patients = new ArrayList<>();
    private static ArrayList<Doctor> doctors = new ArrayList<>();
    private static ArrayList<Appointment> appointments = new ArrayList<>();

    private static Scanner scanner = new Scanner(System.in);
    private static int nextId = 1000;

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║      🏥 HOSPITAL MANAGEMENT SYSTEM    ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        loadSampleData();

        boolean running = true;
        while (running) {
            displayMenu();
            System.out.print("Enter your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addPatient();
                case 2 -> viewAllPatients();
                case 3 -> addDoctor();
                case 4 -> viewAllDoctors();
                case 5 -> addAppointment();
                case 6 -> viewAllAppointments();
                case 7 -> showSystemInfo();
                case 0 -> {
                    System.out.println("Thank you for using Hospital System!");
                    running = false;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }

            if (running) {
                System.out.print("Press Enter to continue...");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n════════════════════════════════════════");
        System.out.println("           MAIN MENU");
        System.out.println("════════════════════════════════════════");
        System.out.println("1. 👤 Add New Patient");
        System.out.println("2. 📋 View All Patients");
        System.out.println("3. 👨‍⚕️ Add New Doctor");
        System.out.println("4. 📋 View All Doctors");
        System.out.println("5. 📅 Add New Appointment");
        System.out.println("6. 📋 View All Appointments");
        System.out.println("7. 📊 System Information");
        System.out.println("0. ❌ Exit");
        System.out.println("════════════════════════════════════════");
    }

    private static void loadSampleData() {
        System.out.println("Loading sample data...\n");

        // Создаем тестовых пациентов
        Patient p1 = new Patient(1001, "Aidar", 30, "+77011234567",
                "A+", "Hypertension", true);
        Patient p2 = new Patient(1002, "Asel", 25, "+77012345678",
                "B-", "Emergency: Severe Migraine", false);
        Patient p3 = new Patient(1003, "Baurzhan", 16, "+77013456789",
                "O+", "Appendicitis Surgery", true);

        Doctor d1 = new Doctor(2001, "Zhumabayev", 45, "+77014567890",
                "Cardiology", 15, true);
        Doctor d2 = new Doctor(2002, "Ahmetova", 38, "+77015678901",
                "Surgery", 8, true);

        Appointment a1 = new Appointment(3001, "Aidar", "Dr. Zhumabayev",
                "2025-03-10", "10:00", "Scheduled", "Room 301");
        Appointment a2 = new Appointment(3002, "Baurzhan", "Dr. Ahmetova",
                "2025-03-11", "09:00", "Scheduled", "Room 205");

        patients.add(p1); allPeople.add(p1);
        patients.add(p2); allPeople.add(p2);
        patients.add(p3); allPeople.add(p3);

        doctors.add(d1); allPeople.add(d1);
        doctors.add(d2); allPeople.add(d2);

        appointments.add(a1);
        appointments.add(a2);

        nextId = 4001;
        System.out.println("✅ Sample data loaded:");
        System.out.println("   Patients: " + patients.size());
        System.out.println("   Doctors: " + doctors.size());
        System.out.println("   Appointments: " + appointments.size());
    }


    private static void addPatient() {
        System.out.println("\n════════════════════════════════════════");
        System.out.println("        ADD NEW PATIENT");
        System.out.println("════════════════════════════════════════");

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Contact: ");
        String contact = scanner.nextLine();

        System.out.print("Blood Type (A+, A-, B+, B-, AB+, AB-, O+, O-): ");
        String bloodType = scanner.nextLine();

        System.out.print("Diagnosis: ");
        String diagnosis = scanner.nextLine();

        System.out.print("Has Insurance? (true/false): ");
        boolean hasInsurance = scanner.nextBoolean();
        scanner.nextLine();

        int id = ++nextId;
        Patient patient = new Patient(id, name, age, contact, bloodType, diagnosis, hasInsurance);

        patients.add(patient);
        allPeople.add(patient);

        System.out.println("\n✅ Patient added successfully!");
        System.out.println("ID: " + id + " | Name: " + name);
    }

    private static void viewAllPatients() {
        System.out.println("          ALL PATIENTS");
        System.out.println("════════════════════════════════════════");

        if (patients.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }

        System.out.println("Total: " + patients.size() + " patients\n");

        for (int i = 0; i < patients.size(); i++) {
            Patient p = patients.get(i);
            System.out.println("[" + (i + 1) + "] " + p.getName() +
                    " (ID: " + p.getId() + ", Age: " + p.getAge() + ")");
            System.out.println("   Diagnosis: " + p.getDiagnosis());
            System.out.println("   Blood Type: " + p.getBloodType());
            System.out.println("   Insurance: " + (p.hasInsurance() ? "Yes" : "No"));

            if (p.needsEmergencyCare()) {
                System.out.println("   ⚠️  EMERGENCY CASE");
            }
            System.out.println();
        }
    }

    private static void addDoctor() {
        System.out.println("         ADD NEW DOCTOR");
        System.out.println("════════════════════════════════════════");

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Contact: ");
        String contact = scanner.nextLine();

        System.out.print("Specialization: ");
        String specialization = scanner.nextLine();

        System.out.print("Years of Experience: ");
        int experience = scanner.nextInt();
        scanner.nextLine();

        System.out.print("On Duty? (true/false): ");
        boolean onDuty = scanner.nextBoolean();
        scanner.nextLine();

        int id = ++nextId + 1000;
        Doctor doctor = new Doctor(id, name, age, contact, specialization, experience, onDuty);

        doctors.add(doctor);
        allPeople.add(doctor);

        System.out.println("\n✅ Doctor added successfully!");
        System.out.println("ID: " + id + " | Dr. " + name);
    }

    private static void viewAllDoctors() {
        System.out.println("           ALL DOCTORS");
        System.out.println("════════════════════════════════════════");

        if (doctors.isEmpty()) {
            System.out.println("No doctors found.");
            return;
        }

        System.out.println("Total: " + doctors.size() + " doctors\n");

        for (int i = 0; i < doctors.size(); i++) {
            Doctor d = doctors.get(i);
            System.out.println("[" + (i + 1) + "] Dr. " + d.getName() +
                    " (ID: " + d.getId() + ")");
            System.out.println("   Specialization: " + d.getSpecialization());
            System.out.println("   Experience: " + d.getYearsOfExperience() + " years");
            System.out.println("   Status: " + (d.isOnDuty() ? "On Duty" : "Off Duty"));

            if (d.canPerformSurgery()) {
                System.out.println("   🔪 SURGEON");
            }
            System.out.println();
        }
    }

    private static void addAppointment() {
        System.out.println("       ADD NEW APPOINTMENT");
        System.out.println("════════════════════════════════════════");

        if (patients.isEmpty() || doctors.isEmpty()) {
            System.out.println("❌ Need at least one patient and one doctor!");
            return;
        }

        System.out.println("Select Patient:");
        for (int i = 0; i < patients.size(); i++) {
            System.out.println((i + 1) + ". " + patients.get(i).getName());
        }
        System.out.print("Patient number: ");
        int patientIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (patientIndex < 0 || patientIndex >= patients.size()) {
            System.out.println("Invalid patient selection!");
            return;
        }
        String patientName = patients.get(patientIndex).getName();

        System.out.println("\nSelect Doctor:");
        for (int i = 0; i < doctors.size(); i++) {
            Doctor d = doctors.get(i);
            System.out.println((i + 1) + ". Dr. " + d.getName() + " (" + d.getSpecialization() + ")");
        }
        System.out.print("Doctor number: ");
        int doctorIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (doctorIndex < 0 || doctorIndex >= doctors.size()) {
            System.out.println("Invalid doctor selection!");
            return;
        }
        String doctorName = "Dr. " + doctors.get(doctorIndex).getName();

        System.out.print("Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        System.out.print("Time (HH:MM): ");
        String time = scanner.nextLine();

        System.out.print("Room: ");
        String room = scanner.nextLine();

        int id = ++nextId + 2000;
        Appointment appointment = new Appointment(id, patientName, doctorName, date, time, "Scheduled", room);
        appointments.add(appointment);

        System.out.println("\n✅ Appointment added successfully!");
        System.out.println("ID: " + id + " | " + patientName + " with " + doctorName);
    }

    private static void viewAllAppointments() {
        System.out.println("         ALL APPOINTMENTS");
        System.out.println("════════════════════════════════════════");

        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }

        System.out.println("Total: " + appointments.size() + " appointments\n");

        for (int i = 0; i < appointments.size(); i++) {
            Appointment a = appointments.get(i);
            System.out.println("[" + (i + 1) + "] Appointment #" + a.getAppointmentId());
            System.out.println("   Patient: " + a.getPatientName());
            System.out.println("   Doctor: " + a.getDoctorName());
            System.out.println("   Date: " + a.getDate() + " at " + a.getTime());
            System.out.println("   Status: " + a.getStatus());
            System.out.println("   Room: " + a.getRoomNumber());

            if (a.getStatus().equals("Scheduled")) {
                System.out.println("   ⏰ UPCOMING");
            }
            System.out.println();
        }
    }



    private static void showSystemInfo() {
        System.out.println("        SYSTEM INFORMATION");
        System.out.println("════════════════════════════════════════");

        System.out.println("📊 Statistics:");
        System.out.println("-".repeat(40));
        System.out.println("Total People: " + allPeople.size());
        System.out.println("Patients: " + patients.size());
        System.out.println("Doctors: " + doctors.size());
        System.out.println("Appointments: " + appointments.size());

        System.out.println("\n👥 Patient Statistics:");
        System.out.println("-".repeat(40));
        int emergency = 0;
        int insured = 0;
        for (Patient p : patients) {
            if (p.needsEmergencyCare()) emergency++;
            if (p.hasInsurance()) insured++;
        }
        System.out.println("Emergency Cases: " + emergency);
        System.out.println("Insured Patients: " + insured + "/" + patients.size());

        System.out.println("\n👨‍⚕️ Doctor Statistics:");
        System.out.println("-".repeat(40));
        int surgeons = 0;
        int onDuty = 0;
        for (Doctor d : doctors) {
            if (d.canPerformSurgery()) surgeons++;
            if (d.isOnDuty()) onDuty++;
        }
        System.out.println("Surgeons: " + surgeons);
        System.out.println("On Duty: " + onDuty + "/" + doctors.size());

        System.out.println("\n📅 Appointment Statistics:");
        System.out.println("-".repeat(40));
        int scheduled = 0;
        int completed = 0;
        for (Appointment a : appointments) {
            if (a.getStatus().equals("Scheduled")) scheduled++;
            if (a.getStatus().equals("Completed")) completed++;
        }
        System.out.println("Scheduled: " + scheduled);
        System.out.println("Completed: " + completed);
    }
}