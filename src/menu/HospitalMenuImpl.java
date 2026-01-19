package menu;

import model.*;
import exception.InvalidMedicalDataException;
import java.util.ArrayList;
import java.util.Scanner;

public class HospitalMenuImpl implements HospitalMenu {
    private ArrayList<Person> people = new ArrayList<>();
    private ArrayList<MedicalProcedure> procedures = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private int personIdCounter = 1000;
    private int procedureIdCounter = 5000;

    public HospitalMenuImpl() {
        initializeTestData();
    }

    private void initializeTestData() {
        try {
            Patient patient = new Patient(++personIdCounter, "Aibek", 25);
            patient.setDiagnosis("Flu");
            people.add(patient);

            Doctor doctor = new Doctor(++personIdCounter, "Dr. Smith", 40);
            doctor.setSpecialization("Therapist");
            people.add(doctor);

            MedicalProcedure procedure = new MedicalProcedure(++procedureIdCounter, "X-Ray", 150.0);
            procedures.add(procedure);

        } catch (IllegalArgumentException e) {
            System.out.println("Error initializing test data: " + e.getMessage());
        }
    }

    @Override
    public void displayMenu() {
        System.out.println("\n=== HOSPITAL MANAGEMENT SYSTEM ===");
        System.out.println("1. Add model.Patient");
        System.out.println("2. Add model.Doctor");
        System.out.println("3. Add Medical Procedure");
        System.out.println("4. View All People");
        System.out.println("5. View Patients Only");
        System.out.println("6. View Doctors Only");
        System.out.println("7. Perform Treatment (Interface Demo)");
        System.out.println("8. Make All People Perform Duties (Polymorphism)");
        System.out.println("9. View Medical Procedures");
        System.out.println("0. Exit");
        System.out.print("Select option: ");
    }

    @Override
    public void run() {
        while (true) {
            displayMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> addPatient();
                    case 2 -> addDoctor();
                    case 3 -> addMedicalProcedure();
                    case 4 -> viewAllPeople();
                    case 5 -> viewPatientsOnly();
                    case 6 -> viewDoctorsOnly();
                    case 7 -> performTreatmentDemo();
                    case 8 -> makeAllPerformDuties();
                    case 9 -> viewMedicalProcedures();
                    case 0 -> {
                        System.out.println("Exiting... Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid option! Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number!");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (InvalidMedicalDataException e) {
                System.out.println("Medical Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
    }

    private void addPatient() throws InvalidMedicalDataException {
        try {
            System.out.println("\n--- ADD NEW PATIENT ---");

            System.out.print("Enter patient name: ");
            String name = scanner.nextLine();

            System.out.print("Enter patient age: ");
            int age = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter diagnosis: ");
            String diagnosis = scanner.nextLine();

            if (diagnosis.trim().isEmpty()) {
                throw new InvalidMedicalDataException("Diagnosis cannot be empty!");
            }

            Patient patient = new Patient(++personIdCounter, name, age);
            patient.setDiagnosis(diagnosis);
            people.add(patient);

            System.out.println("model.Patient added successfully! ID: " + patient.getId());

        } catch (NumberFormatException e) {
            throw new InvalidMedicalDataException("Age must be a valid number!");
        }
    }

    private void addDoctor() {
        System.out.println("\n--- ADD NEW DOCTOR ---");

        System.out.print("Enter doctor name: ");
        String name = scanner.nextLine();

        System.out.print("Enter doctor age: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter specialization: ");
        String specialization = scanner.nextLine();

        Doctor doctor = new Doctor(++personIdCounter, name, age);
        doctor.setSpecialization(specialization);
        people.add(doctor);

        System.out.println("model.Doctor added successfully! ID: " + doctor.getId());
    }

    private void addMedicalProcedure() {
        System.out.println("\n--- ADD MEDICAL PROCEDURE ---");

        System.out.print("Enter procedure name: ");
        String name = scanner.nextLine();

        System.out.print("Enter procedure cost: ");
        double cost = Double.parseDouble(scanner.nextLine());

        if (cost <= 0) {
            throw new IllegalArgumentException("Cost must be positive!");
        }

        MedicalProcedure procedure = new MedicalProcedure(++procedureIdCounter, name, cost);
        procedures.add(procedure);

        System.out.println("Procedure added successfully! ID: " + procedure.getId());
    }

    private void viewAllPeople() {
        System.out.println("\n--- ALL PEOPLE IN HOSPITAL ---");
        if (people.isEmpty()) {
            System.out.println("No people found.");
            return;
        }

        for (Person person : people) {
            System.out.println(person);
        }
    }

    private void viewPatientsOnly() {
        System.out.println("\n--- PATIENTS ONLY ---");
        boolean found = false;

        for (Person person : people) {
            if (person instanceof Patient) {
                System.out.println(person);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No patients found.");
        }
    }

    private void viewDoctorsOnly() {
        System.out.println("\n--- DOCTORS ONLY ---");
        boolean found = false;

        for (Person person : people) {
            if (person instanceof Doctor) {
                System.out.println(person);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No doctors found.");
        }
    }

    private void performTreatmentDemo() {
        System.out.println("\n--- TREATMENT DEMO (Interface) ---");

        if (people.isEmpty()) {
            System.out.println("No people to treat.");
            return;
        }

        for (Person person : people) {
            if (person instanceof Treatable) {
                System.out.print(person.getName() + ": ");
                ((Treatable) person).performTreatment();
            }
        }

        if (!procedures.isEmpty()) {
            System.out.println("\n--- Medical Procedures ---");
            for (MedicalProcedure procedure : procedures) {
                System.out.print("Procedure " + procedure.getName() + ": ");
                procedure.performTreatment();
            }
        }
    }

    private void makeAllPerformDuties() {
        System.out.println("\n--- POLYMORPHISM DEMO ---");
        System.out.println("Making all people perform their duties:");

        if (people.isEmpty()) {
            System.out.println("No people found.");
            return;
        }

        for (Person person : people) {
            System.out.print(person.getName() + " (" + person.getRole() + "): ");
            person.performDuties();
        }
    }

    private void viewMedicalProcedures() {
        System.out.println("\n--- MEDICAL PROCEDURES ---");
        if (procedures.isEmpty()) {
            System.out.println("No procedures found.");
            return;
        }

        for (MedicalProcedure procedure : procedures) {
            System.out.println(procedure.getTreatmentDetails());
        }
    }
}