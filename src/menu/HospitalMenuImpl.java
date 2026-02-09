package menu;

import model.*;
import database.PersonDAO;
import exception.InvalidMedicalDataException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class HospitalMenuImpl implements HospitalMenu {
    private PersonDAO personDAO = new PersonDAO();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void displayMenu() {
        System.out.println("\n" + "═".repeat(40));
        System.out.println("🏥 HOSPITAL MANAGEMENT SYSTEM");
        System.out.println("═".repeat(40));
        System.out.println("1. Add Patient");
        System.out.println("2. Add Doctor");
        System.out.println("3. View All People");
        System.out.println("4. View Patients Only");
        System.out.println("5. View Doctors Only");
        System.out.println("6. Update Person");
        System.out.println("7. Delete Person");
        System.out.println("8. Search by Name");
        System.out.println("9. Demo Polymorphism");
        System.out.println("0. Exit");
        System.out.println("═".repeat(40));
        System.out.print("Select option: ");
    }

    @Override
    public void run() {
        System.out.println("\n★ WELCOME TO HOSPITAL SYSTEM ★");
        System.out.println("Simple version with 9 options");

        while (true) {
            displayMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> addPatient();
                    case 2 -> addDoctor();
                    case 3 -> viewAllPeople();
                    case 4 -> viewPatientsOnly();
                    case 5 -> viewDoctorsOnly();
                    case 6 -> updatePerson();
                    case 7 -> deletePerson();
                    case 8 -> searchByName();
                    case 0 -> {
                        System.out.println("\nGoodbye! 👋");
                        return;
                    }
                    default -> System.out.println("❌ Invalid option!");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Please enter a number!");
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
    }


    private void addPatient() {
        System.out.println("\n── ADD PATIENT ──");

        try {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();

            System.out.print("Enter age: ");
            int age = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter diagnosis: ");
            String diagnosis = scanner.nextLine();

            if (diagnosis.trim().isEmpty()) {
                System.out.println("❌ Diagnosis required!");
                return;
            }

            Patient patient = new Patient(name, age);
            patient.setDiagnosis(diagnosis);
            personDAO.insertPatient(patient);

            System.out.println("✅ Patient added! ID: " + patient.getId());

        } catch (NumberFormatException e) {
            System.out.println("❌ Age must be a number!");
        }
    }


    private void addDoctor() {
        System.out.println("\n── ADD DOCTOR ──");

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter age: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter specialization: ");
        String specialization = scanner.nextLine();

        Doctor doctor = new Doctor(name, age);
        doctor.setSpecialization(specialization);
        personDAO.insertDoctor(doctor);

        System.out.println("✅ Doctor added! ID: " + doctor.getId());
    }


    private void viewAllPeople() {
        System.out.println("\n── ALL PEOPLE ──");

        List<Person> people = personDAO.getAllPeople();

        if (people.isEmpty()) {
            System.out.println("No people in database.");
            return;
        }

        System.out.println("Total: " + people.size());
        for (Person person : people) {
            System.out.println(person);
        }
    }



    private void viewPatientsOnly() {
        System.out.println("\n── PATIENTS ──");

        List<Patient> patients = personDAO.getPatientsOnly();

        if (patients.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }

        System.out.println("Patients: " + patients.size());
        for (Patient patient : patients) {
            System.out.println(patient);
        }
    }


    private void viewDoctorsOnly() {
        System.out.println("\n── DOCTORS ──");

        List<Doctor> doctors = personDAO.getDoctorsOnly();

        if (doctors.isEmpty()) {
            System.out.println("No doctors found.");
            return;
        }

        System.out.println("Doctors: " + doctors.size());
        for (Doctor doctor : doctors) {
            System.out.println(doctor);
        }
    }


    private void updatePerson() {
        System.out.println("\n── UPDATE PERSON ──");

        System.out.print("Enter ID to update: ");
        int id = Integer.parseInt(scanner.nextLine());

        Person person = personDAO.getPersonById(id);

        if (person == null) {
            System.out.println("❌ Person not found!");
            return;  // Выходим если не нашли
        }

        System.out.println("Current: " + person);

        System.out.print("New name: ");
        String newName = scanner.nextLine();

        System.out.print("New age: ");
        int newAge = Integer.parseInt(scanner.nextLine());

        boolean success = false;

        if (person instanceof Patient) {
            Patient patient = (Patient) person;

            System.out.print("New diagnosis: ");
            String newDiagnosis = scanner.nextLine();

            Patient updatedPatient = new Patient(id, newName, newAge);
            updatedPatient.setDiagnosis(newDiagnosis);

            success = personDAO.updatePatient(updatedPatient);

        } else if (person instanceof Doctor) {
            Doctor doctor = (Doctor) person;

            System.out.print("New specialization: ");
            String newSpecialization = scanner.nextLine();

            Doctor updatedDoctor = new Doctor(id, newName, newAge);
            updatedDoctor.setSpecialization(newSpecialization);

            success = personDAO.updateDoctor(updatedDoctor);
        }

        if (success) {
            System.out.println("✅ Updated successfully!");
        } else {
            System.out.println("❌ Update failed!");
        }
    }


    private void deletePerson() {
        System.out.println("DELETE PERSON");
        System.out.print("Enter ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());

        Person person = personDAO.getPersonById(id);

        if (person == null) {
            System.out.println("Person not found!");
        }

        System.out.println("Will delete: " + person);
        System.out.println("Are you sure you want to delete?(yes/no)");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            boolean success = personDAO.deletePerson(id);
            if (success) {
            System.out.println("Person deleted successfully!");
            } else{
                System.out.println("Delete failed!");
            }
        }
    }


    private void searchByName() {
        System.out.println("\n── SEARCH BY NAME ──");

        System.out.print("Enter name to search: ");
        String name = scanner.nextLine();

        List<Person> results = personDAO.searchByName(name);

        if (results.isEmpty()) {
            System.out.println("No results found.");
            return;
        }

        System.out.println("Found " + results.size() + " results:");
        for (Person person : results) {
            System.out.println(person);
        }
    }}



