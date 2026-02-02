package database;

import model.Person;
import model.Patient;
import model.Doctor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {


    public void insertPatient(Patient patient) {
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return;

        try {
            String sql;
            PreparedStatement statement;

            if (patient.getId() == 0) {
                sql = "INSERT INTO person (name, age, role, diagnosis) VALUES (?, ?, 'Patient', ?)";
                statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, patient.getName());
                statement.setInt(2, patient.getAge());
                statement.setString(3, patient.getDiagnosis());
            } else {
                sql = "INSERT INTO person (person_id, name, age, role, diagnosis) VALUES (?, ?, ?, 'Patient', ?)";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, patient.getId());
                statement.setString(2, patient.getName());
                statement.setInt(3, patient.getAge());
                statement.setString(4, patient.getDiagnosis());
            }

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                if (patient.getId() == 0) {
                    ResultSet generatedKeys = statement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        patient.setId(generatedId);
                        System.out.println("✅ Patient saved to DB with auto-generated ID: " + generatedId);
                    }
                } else {
                    System.out.println("✅ Patient saved to DB with manual ID: " + patient.getId());
                }
            }

            statement.close();

        } catch (SQLException e) {
            System.out.println("❌ Error saving patient: " + e.getMessage());
            if (e.getMessage().contains("duplicate key")) {
                System.out.println("⚠️ ID already exists! Please choose another ID.");
            }
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }


    public void insertDoctor(Doctor doctor) {
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return;

        try {
            String sql;
            PreparedStatement statement;

            if (doctor.getId() == 0) {
                sql = "INSERT INTO person (name, age, role, specialization) VALUES (?, ?, 'Doctor', ?)";
                statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, doctor.getName());
                statement.setInt(2, doctor.getAge());
                statement.setString(3, doctor.getSpecialization());
            } else {
                sql = "INSERT INTO person (person_id, name, age, role, specialization) VALUES (?, ?, ?, 'Doctor', ?)";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, doctor.getId());
                statement.setString(2, doctor.getName());
                statement.setInt(3, doctor.getAge());
                statement.setString(4, doctor.getSpecialization());
            }

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                if (doctor.getId() == 0) {
                    ResultSet generatedKeys = statement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        doctor.setId(generatedId);
                        System.out.println("✅ Doctor saved to DB with auto-generated ID: " + generatedId);
                    }
                } else {
                    System.out.println("✅ Doctor saved to DB with manual ID: " + doctor.getId());
                }
            }

            statement.close();

        } catch (SQLException e) {
            System.out.println("❌ Error saving doctor: " + e.getMessage());
            if (e.getMessage().contains("duplicate key")) {
                System.out.println("⚠️ ID already exists! Please choose another ID.");
            }
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }


    public boolean isIdExists(int personId) {
        String sql = "SELECT COUNT(*) FROM person WHERE person_id = ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, personId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }

        } catch (SQLException e) {
            System.out.println("❌ Error checking ID: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return false;
    }


    public int getNextAvailableId() {
        String sql = "SELECT COALESCE(MAX(person_id), 0) + 1 FROM person";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return 1;

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error getting next ID: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return 1;
    }



    public Person getPersonById(int personId) {
        String sql = "SELECT * FROM person WHERE person_id = ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, personId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return createPersonFromResultSet(resultSet);
                }
            }

        } catch (SQLException e) {
            System.out.println("❌ Error getting person by ID: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return null;
    }


    public List<Person> getAllPeople() {
        List<Person> people = new ArrayList<>();
        String sql = "SELECT * FROM person ORDER BY person_id";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return people;

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Person person = createPersonFromResultSet(resultSet);
                if (person != null) {
                    people.add(person);
                }
            }

            System.out.println("✅ Loaded " + people.size() + " people from database");

        } catch (SQLException e) {
            System.out.println("❌ Error reading from DB: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return people;
    }


    public List<Patient> getPatientsOnly() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM person WHERE role = 'Patient' ORDER BY name";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return patients;

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Patient patient = new Patient(
                        resultSet.getInt("person_id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age")
                );
                patient.setDiagnosis(resultSet.getString("diagnosis"));
                patients.add(patient);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error reading patients: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return patients;
    }


    public List<Doctor> getDoctorsOnly() {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM person WHERE role = 'Doctor' ORDER BY name";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return doctors;

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Doctor doctor = new Doctor(
                        resultSet.getInt("person_id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age")
                );
                doctor.setSpecialization(resultSet.getString("specialization"));
                doctors.add(doctor);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error reading doctors: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return doctors;
    }


    public boolean updatePatient(Patient patient) {
        String sql = "UPDATE person SET name = ?, age = ?, diagnosis = ? WHERE person_id = ? AND role = 'Patient'";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, patient.getName());
            statement.setInt(2, patient.getAge());
            statement.setString(3, patient.getDiagnosis());
            statement.setInt(4, patient.getId());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("✅ Patient updated: " + patient.getName());
                return true;
            }

        } catch (SQLException e) {
            System.out.println("❌ Error updating patient: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return false;
    }


    public boolean updateDoctor(Doctor doctor) {
        String sql = "UPDATE person SET name = ?, age = ?, specialization = ? WHERE person_id = ? AND role = 'Doctor'";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, doctor.getName());
            statement.setInt(2, doctor.getAge());
            statement.setString(3, doctor.getSpecialization());
            statement.setInt(4, doctor.getId());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("✅ Doctor updated: " + doctor.getName());
                return true;
            }

        } catch (SQLException e) {
            System.out.println("❌ Error updating doctor: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return false;
    }


    public boolean deletePerson(int personId) {
        String sql = "DELETE FROM person WHERE person_id = ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, personId);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("✅ Person deleted (ID: " + personId + ")");
                return true;
            } else {
                System.out.println("❌ No person found with ID: " + personId);
                return false;
            }

        } catch (SQLException e) {
            System.out.println("❌ Error deleting person: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return false;
    }


    public List<Person> searchByName(String name) {
        List<Person> results = new ArrayList<>();
        String sql = "SELECT * FROM person WHERE name ILIKE ? ORDER BY name";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return results;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "%" + name + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Person person = createPersonFromResultSet(resultSet);
                    if (person != null) {
                        results.add(person);
                    }
                }
            }

            System.out.println("🔍 Found " + results.size() + " people with name like: " + name);

        } catch (SQLException e) {
            System.out.println("❌ Error searching by name: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return results;
    }


    private Person createPersonFromResultSet(ResultSet resultSet) throws SQLException {
        String role = resultSet.getString("role");

        if (role.equals("Patient")) {
            Patient patient = new Patient(
                    resultSet.getInt("person_id"),
                    resultSet.getString("name"),
                    resultSet.getInt("age")
            );
            patient.setDiagnosis(resultSet.getString("diagnosis"));
            return patient;

        } else if (role.equals("Doctor")) {
            Doctor doctor = new Doctor(
                    resultSet.getInt("person_id"),
                    resultSet.getString("name"),
                    resultSet.getInt("age")
            );
            doctor.setSpecialization(resultSet.getString("specialization"));
            return doctor;
        }

        return null;
    }
}