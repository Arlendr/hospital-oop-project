package menu;

import model.*;
import database.PersonDAO;
import exception.InvalidMedicalDataException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class HospitalMenuImpl implements HospitalMenu {
    private PersonDAO personDAO = new PersonDAO();
    private List<MedicalProcedure> procedures = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void displayMenu() {
        System.out.println("\n" + "═".repeat(50));
        System.out.println("🏥 HOSPITAL MANAGEMENT SYSTEM (with Manual ID)");
        System.out.println("═".repeat(50));
        System.out.println("1.  Add Patient (Auto ID)");
        System.out.println("2.  Add Patient with Manual ID");
        System.out.println("3.  Add Doctor (Auto ID)");
        System.out.println("4.  Add Doctor with Manual ID");
        System.out.println("5.  Add Medical Procedure");
        System.out.println("6.  View All People");
        System.out.println("7.  View Patients Only");
        System.out.println("8.  View Doctors Only");
        System.out.println("9.  View Medical Procedures");
        System.out.println("10. 🔄 Update Person Data");
        System.out.println("11. ❌ Delete Person");
        System.out.println("12. 🔍 Search by Name");
        System.out.println("13. 🔍 Search by Age Range");
        System.out.println("14. 🔍 Search Patients by Diagnosis");
        System.out.println("15. 🔍 Search Doctors by Specialization");
        System.out.println("16. 🎭 All Perform Duties (Polymorphism)");
        System.out.println("17. 💉 Perform Treatment (Interface Demo)");
        System.out.println("18. 🔢 Show Next Available ID");
        System.out.println("0.  Exit");
        System.out.println("═".repeat(50));
        System.out.print("Select option: ");
    }

    @Override
    public void run() {
        System.out.println("\n" + "★".repeat(50));
        System.out.println("     WELCOME TO HOSPITAL MANAGEMENT SYSTEM");
        System.out.println("     Now with Manual ID Support!");
        System.out.println("★".repeat(50));

        // Добавим тестовую процедуру для демонстрации
        procedures.add(new MedicalProcedure(1, "X-Ray", 150.0));
        procedures.add(new MedicalProcedure(2, "Blood Test", 50.0));

        while (true) {
            displayMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> addPatientAutoId();
                    case 2 -> addPatientManualId();
                    case 3 -> addDoctorAutoId();
                    case 4 -> addDoctorManualId();
                    case 5 -> addMedicalProcedure();
                    case 6 -> viewAllPeople();
                    case 7 -> viewPatientsOnly();
                    case 8 -> viewDoctorsOnly();
                    case 9 -> viewMedicalProcedures();
                    case 10 -> updatePerson();
                    case 11 -> deletePerson();
                    case 12 -> searchByName();
                    case 13 -> searchByAgeRange();
                    case 14 -> searchPatientsByDiagnosis();
                    case 15 -> searchDoctorsBySpecialization();
                    case 16 -> makeAllPerformDuties();
                    case 17 -> performTreatmentDemo();
                    case 18 -> showNextAvailableId();
                    case 0 -> {
                        System.out.println("\nThank you for using Hospital Management System!");
                        System.out.println("Goodbye! 👋");
                        return;
                    }
                    default -> System.out.println("❌ Invalid option! Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Error: Please enter a valid number!");
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Validation error: " + e.getMessage());
            } catch (InvalidMedicalDataException e) {
                System.out.println("❌ Medical error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("❌ Unexpected error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    // ========== ADD PATIENT WITH AUTO ID ==========

    private void addPatientAutoId() throws InvalidMedicalDataException {
        System.out.println("\n" + "─".repeat(40));
        System.out.println("📝 ADD PATIENT (Auto-Generated ID)");
        System.out.println("─".repeat(40));

        try {
            System.out.print("Enter patient name: ");
            String name = scanner.nextLine();

            System.out.print("Enter patient age: ");
            int age = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter diagnosis: ");
            String diagnosis = scanner.nextLine();

            if (diagnosis.trim().isEmpty()) {
                throw new InvalidMedicalDataException("Diagnosis cannot be empty!");
            }

            // Используем конструктор без ID (ID = 0)
            Patient patient = new Patient(name, age);
            patient.setDiagnosis(diagnosis);

            // ID будет сгенерирован автоматически
            personDAO.insertPatient(patient);

            System.out.println("\n✅ Patient added successfully!");
            System.out.println("Assigned ID: " + patient.getId());

        } catch (NumberFormatException e) {
            throw new InvalidMedicalDataException("Age must be a number!");
        }
    }


    private void addPatientManualId() throws InvalidMedicalDataException {
        System.out.println("\n" + "─".repeat(40));
        System.out.println("📝 ADD PATIENT (Manual ID)");
        System.out.println("─".repeat(40));

        try {
            System.out.println("Next available ID: " + personDAO.getNextAvailableId());
            System.out.println("\nEnter 0 for auto-generated ID");
            System.out.print("Enter patient ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            // Если ID = 0, используем авто-генерацию
            if (id == 0) {
                addPatientAutoId();
                return;
            }

            if (personDAO.isIdExists(id)) {
                System.out.println("❌ ID " + id + " already exists! Please choose another ID.");
                System.out.println("Next available ID: " + personDAO.getNextAvailableId());
                return;
            }

            System.out.print("Enter patient name: ");
            String name = scanner.nextLine();

            System.out.print("Enter patient age: ");
            int age = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter diagnosis: ");
            String diagnosis = scanner.nextLine();

            if (diagnosis.trim().isEmpty()) {
                throw new InvalidMedicalDataException("Diagnosis cannot be empty!");
            }

            Patient patient = new Patient(id, name, age);
            patient.setDiagnosis(diagnosis);

            personDAO.insertPatient(patient);

            System.out.println("\n✅ Patient added successfully with manual ID: " + id);

        } catch (NumberFormatException e) {
            throw new InvalidMedicalDataException("Please enter valid numbers!");
        }
    }


    private void addDoctorAutoId() {
        System.out.println("\n" + "─".repeat(40));
        System.out.println("👨‍⚕️ ADD DOCTOR (Auto-Generated ID)");
        System.out.println("─".repeat(40));

        System.out.print("Enter doctor name: ");
        String name = scanner.nextLine();

        System.out.print("Enter doctor age: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter specialization: ");
        String specialization = scanner.nextLine();

        // Используем конструктор без ID
        Doctor doctor = new Doctor(name, age);
        doctor.setSpecialization(specialization);

        personDAO.insertDoctor(doctor);

        System.out.println("\n✅ Doctor added successfully!");
        System.out.println("Assigned ID: " + doctor.getId());
    }


    private void addDoctorManualId() {
        System.out.println("\n" + "─".repeat(40));
        System.out.println("👨‍⚕️ ADD DOCTOR (Manual ID)");
        System.out.println("─".repeat(40));

        System.out.println("Next available ID: " + personDAO.getNextAvailableId());
        System.out.println("\nEnter 0 for auto-generated ID");
        System.out.print("Enter doctor ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (id == 0) {
            addDoctorAutoId();
            return;
        }

        if (personDAO.isIdExists(id)) {
            System.out.println("❌ ID " + id + " already exists! Please choose another ID.");
            System.out.println("Next available ID: " + personDAO.getNextAvailableId());
            return;
        }

        System.out.print("Enter doctor name: ");
        String name = scanner.nextLine();

        System.out.print("Enter doctor age: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter specialization: ");
        String specialization = scanner.nextLine();

        Doctor doctor = new Doctor(id, name, age);
        doctor.setSpecialization(specialization);

        personDAO.insertDoctor(doctor);

        System.out.println("\n✅ Doctor added successfully with manual ID: " + id);
    }


    private void addMedicalProcedure() {
        System.out.println("\n" + "─".repeat(40));
        System.out.println("🔬 ADD MEDICAL PROCEDURE");
        System.out.println("─".repeat(40));

        System.out.print("Enter procedure name: ");
        String name = scanner.nextLine();

        if (name.trim().isEmpty()) {
            System.out.println("❌ Procedure name cannot be empty!");
            return;
        }

        System.out.print("Enter procedure cost: ");
        try {
            double cost = Double.parseDouble(scanner.nextLine());

            if (cost <= 0) {
                System.out.println("❌ Cost must be positive!");
                return;
            }

            MedicalProcedure procedure = new MedicalProcedure(procedures.size() + 1, name, cost);
            procedures.add(procedure);

            System.out.println("✅ Procedure added: " + procedure.getName());

        } catch (NumberFormatException e) {
            System.out.println("❌ Cost must be a number!");
        }
    }


    private void showNextAvailableId() {
        System.out.println("\n" + "─".repeat(40));
        System.out.println("🔢 NEXT AVAILABLE ID");
        System.out.println("─".repeat(40));

        int nextId = personDAO.getNextAvailableId();
        System.out.println("Next available ID: " + nextId);
        System.out.println("\n💡 Tip: Use this ID when adding patients/doctors manually.");
        System.out.println("     Enter 0 for auto-generated ID.");
    }


    private void viewAllPeople() {
        System.out.println("\n" + "─".repeat(50));
        System.out.println("👥 ALL PEOPLE IN HOSPITAL");
        System.out.println("─".repeat(50));

        List<Person> people = personDAO.getAllPeople();

        if (people.isEmpty()) {
            System.out.println("No people found in database.");
            return;
        }

        System.out.println("Total people: " + people.size());
        System.out.println();

        for (Person person : people) {
            System.out.println(person);
            System.out.println("─".repeat(40));
        }
    }


    private void viewPatientsOnly() {
        System.out.println("\n" + "─".repeat(50));
        System.out.println("🛏️ PATIENTS");
        System.out.println("─".repeat(50));

        List<Patient> patients = personDAO.getPatientsOnly();

        if (patients.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }

        System.out.println("Total patients: " + patients.size());
        System.out.println();

        for (Patient patient : patients) {
            System.out.println(patient);
            System.out.println("─".repeat(40));
        }
    }


    private void viewDoctorsOnly() {
        System.out.println("\n" + "─".repeat(50));
        System.out.println("👨‍⚕️ DOCTORS");
        System.out.println("─".repeat(50));

        List<Doctor> doctors = personDAO.getDoctorsOnly();

        if (doctors.isEmpty()) {
            System.out.println("No doctors found.");
            return;
        }

        System.out.println("Total doctors: " + doctors.size());
        System.out.println();

        for (Doctor doctor : doctors) {
            System.out.println(doctor);
            System.out.println("─".repeat(40));
        }
    }


    private void viewMedicalProcedures() {
        System.out.println("\n" + "─".repeat(50));
        System.out.println("🔬 MEDICAL PROCEDURES");
        System.out.println("─".repeat(50));

        if (procedures.isEmpty()) {
            System.out.println("No medical procedures added yet.");
            System.out.println("Use option 5 to add a medical procedure.");
            return;
        }

        System.out.println("Total procedures: " + procedures.size());
        System.out.println();

        for (MedicalProcedure procedure : procedures) {
            System.out.println(procedure.getTreatmentDetails());
            System.out.println("─".repeat(40));
        }
    }


    private void updatePerson() {
        System.out.println("\n" + "─".repeat(50));
        System.out.println("🔄 UPDATE PERSON DATA");
        System.out.println("─".repeat(50));

        System.out.print("Enter person ID to update: ");
        int id = Integer.parseInt(scanner.nextLine());

        Person person = personDAO.getPersonById(id);

        if (person == null) {
            System.out.println("❌ No person found with ID: " + id);
            return;
        }

        System.out.println("\nCurrent information:");
        System.out.println(person);

        System.out.println("\nEnter new values (press Enter to keep current):");

        System.out.print("New name [" + person.getName() + "]: ");
        String newName = scanner.nextLine();
        if (newName.trim().isEmpty()) {
            newName = person.getName();
        }

        System.out.print("New age [" + person.getAge() + "]: ");
        String ageInput = scanner.nextLine();
        int newAge = ageInput.trim().isEmpty() ? person.getAge() : Integer.parseInt(ageInput);

        boolean success = false;

        if (person instanceof Patient) {
            Patient patient = (Patient) person;

            System.out.print("New diagnosis [" + patient.getDiagnosis() + "]: ");
            String newDiagnosis = scanner.nextLine();
            if (newDiagnosis.trim().isEmpty()) {
                newDiagnosis = patient.getDiagnosis();
            }

            Patient updatedPatient = new Patient(id, newName, newAge);
            updatedPatient.setDiagnosis(newDiagnosis);
            success = personDAO.updatePatient(updatedPatient);

        } else if (person instanceof Doctor) {
            Doctor doctor = (Doctor) person;

            System.out.print("New specialization [" + doctor.getSpecialization() + "]: ");
            String newSpecialization = scanner.nextLine();
            if (newSpecialization.trim().isEmpty()) {
                newSpecialization = doctor.getSpecialization();
            }

            Doctor updatedDoctor = new Doctor(id, newName, newAge);
            updatedDoctor.setSpecialization(newSpecialization);
            success = personDAO.updateDoctor(updatedDoctor);
        }

        if (success) {
            System.out.println("✅ Person data updated successfully!");
        } else {
            System.out.println("❌ Failed to update person data.");
        }
    }


    private void deletePerson() {
        System.out.println("\n" + "─".repeat(50));
        System.out.println("❌ DELETE PERSON");
        System.out.println("─".repeat(50));

        System.out.print("Enter person ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());

        Person person = personDAO.getPersonById(id);

        if (person == null) {
            System.out.println("❌ No person found with ID: " + id);
            return;
        }

        System.out.println("\n⚠️ WARNING: This action cannot be undone!");
        System.out.println("Person to be deleted:");
        System.out.println(person);

        System.out.print("\nAre you sure? (yes/no): ");
        String confirmation = scanner.nextLine().toLowerCase();

        if (confirmation.equals("yes") || confirmation.equals("y")) {
            boolean success = personDAO.deletePerson(id);
            if (success) {
                System.out.println("✅ Person deleted successfully!");
            }
        } else {
            System.out.println("❌ Deletion cancelled.");
        }
    }


    private void searchByName() {
        System.out.println("\n" + "─".repeat(50));
        System.out.println("🔍 SEARCH BY NAME");
        System.out.println("─".repeat(50));

        System.out.print("Enter name (or part of name): ");
        String name = scanner.nextLine();

        List<Person> results = personDAO.searchByName(name);

        if (results.isEmpty()) {
            System.out.println("No people found.");
            return;
        }

        System.out.println("\nSearch results (" + results.size() + " found):");
        for (Person person : results) {
            System.out.println(person);
            System.out.println("─".repeat(40));
        }
    }


    private void searchByAgeRange() {
        System.out.println("\n" + "─".repeat(50));
        System.out.println("🔍 SEARCH BY AGE RANGE");
        System.out.println("─".repeat(50));

        System.out.print("Enter minimum age: ");
        int minAge = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter maximum age: ");
        int maxAge = Integer.parseInt(scanner.nextLine());

        if (minAge > maxAge) {
            System.out.println("❌ Minimum age cannot be greater than maximum age!");
            return;
        }

        List<Person> people = personDAO.getAllPeople();
        List<Person> results = new ArrayList<>();

        for (Person person : people) {
            if (person.getAge() >= minAge && person.getAge() <= maxAge) {
                results.add(person);
            }
        }

        if (results.isEmpty()) {
            System.out.println("No people found aged " + minAge + "-" + maxAge);
            return;
        }

        System.out.println("\nSearch results (" + results.size() + " found):");
        for (Person person : results) {
            System.out.println(person);
            System.out.println("─".repeat(40));
        }
    }


    private void searchPatientsByDiagnosis() {
        System.out.println("\n" + "─".repeat(50));
        System.out.println("🔍 SEARCH PATIENTS BY DIAGNOSIS");
        System.out.println("─".repeat(50));

        System.out.print("Enter diagnosis (or part of diagnosis): ");
        String diagnosis = scanner.nextLine();

        if (diagnosis.trim().isEmpty()) {
            System.out.println("❌ Diagnosis cannot be empty!");
            return;
        }

        List<Patient> patients = personDAO.getPatientsOnly();
        List<Patient> results = new ArrayList<>();

        for (Patient patient : patients) {
            if (patient.getDiagnosis().toLowerCase().contains(diagnosis.toLowerCase())) {
                results.add(patient);
            }
        }

        if (results.isEmpty()) {
            System.out.println("No patients found with diagnosis: " + diagnosis);
            return;
        }

        System.out.println("\nSearch results (" + results.size() + " found):");
        for (Patient patient : results) {
            System.out.println(patient);
            System.out.println("─".repeat(40));
        }
    }


    private void searchDoctorsBySpecialization() {
        System.out.println("\n" + "─".repeat(50));
        System.out.println("🔍 SEARCH DOCTORS BY SPECIALIZATION");
        System.out.println("─".repeat(50));

        System.out.print("Enter specialization (or part of specialization): ");
        String specialization = scanner.nextLine();

        if (specialization.trim().isEmpty()) {
            System.out.println("❌ Specialization cannot be empty!");
            return;
        }

        List<Doctor> doctors = personDAO.getDoctorsOnly();
        List<Doctor> results = new ArrayList<>();

        for (Doctor doctor : doctors) {
            if (doctor.getSpecialization().toLowerCase().contains(specialization.toLowerCase())) {
                results.add(doctor);
            }
        }

        if (results.isEmpty()) {
            System.out.println("No doctors found with specialization: " + specialization);
            return;
        }

        System.out.println("\nSearch results (" + results.size() + " found):");
        for (Doctor doctor : results) {
            System.out.println(doctor);
            System.out.println("─".repeat(40));
        }
    }


    private void makeAllPerformDuties() {
        System.out.println("\n" + "─".repeat(50));
        System.out.println("🎭 POLYMORPHISM DEMONSTRATION");
        System.out.println("All people performing their duties:");
        System.out.println("─".repeat(50));

        List<Person> people = personDAO.getAllPeople();

        if (people.isEmpty()) {
            System.out.println("No people found in database.");
            return;
        }

        System.out.println("Total people: " + people.size());
        System.out.println();

        for (Person person : people) {
            System.out.print(person.getName() + " (" + person.getRole() + "): ");
            person.performDuties();
        }

        System.out.println("\n✨ This demonstrates polymorphism:");
        System.out.println("- Same method call: person.performDuties()");
        System.out.println("- Different behavior for Patient vs Doctor");
        System.out.println("- Java decides at runtime which method to call");
    }


    private void performTreatmentDemo() {
        System.out.println("\n" + "─".repeat(50));
        System.out.println("💉 INTERFACE DEMONSTRATION");
        System.out.println("Objects implementing Treatable interface:");
        System.out.println("─".repeat(50));

        List<Person> people = personDAO.getAllPeople();
        int treatableCount = 0;

        System.out.println("People who can be treated:");
        for (Person person : people) {
            if (person instanceof Treatable) {
                System.out.print("- " + person.getName() + ": ");
                ((Treatable) person).performTreatment();
                treatableCount++;
            }
        }

        if (!procedures.isEmpty()) {
            System.out.println("\nMedical procedures:");
            for (MedicalProcedure procedure : procedures) {
                System.out.print("- " + procedure.getName() + ": ");
                procedure.performTreatment();
                treatableCount++;
            }
        }

        System.out.println("\n✨ Total objects implementing Treatable: " + treatableCount);
        System.out.println("This demonstrates interface implementation:");
        System.out.println("- Different classes (Patient, MedicalProcedure)");
        System.out.println("- Same interface (Treatable)");
        System.out.println("- Same method: performTreatment()");
    }
}