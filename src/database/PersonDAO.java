package database;

import model.Person;
import model.Patient;
import model.Doctor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {

    public void insertPatient(Patient patient) {
        String sql = "INSERT INTO person (name, age, role, diagnosis) VALUES (?, ?, 'Patient', ?)";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return;

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, patient.getName());
            stmt.setInt(2, patient.getAge());
            stmt.setString(3, patient.getDiagnosis());

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                ResultSet keys = stmt.getGeneratedKeys();
                if (keys.next()) {
                    patient.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    public void insertDoctor(Doctor doctor) {
        String sql = "INSERT INTO person (name, age, role, specialization) VALUES (?, ?, 'Doctor', ?)";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return;

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, doctor.getName());
            stmt.setInt(2, doctor.getAge());
            stmt.setString(3, doctor.getSpecialization());

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                ResultSet keys = stmt.getGeneratedKeys();
                if (keys.next()) {
                    doctor.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    public List<Person> getAllPeople() {
        List<Person> people = new ArrayList<>();
        String sql = "SELECT * FROM person ORDER BY person_id";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return people;

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                people.add(createPerson(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return people;
    }

    public List<Patient> getPatientsOnly() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM person WHERE role = 'Patient'";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return patients;

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Patient patient = new Patient(
                        rs.getInt("person_id"),
                        rs.getString("name"),
                        rs.getInt("age")
                );
                patient.setDiagnosis(rs.getString("diagnosis"));
                patients.add(patient);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return patients;
    }

    public List<Doctor> getDoctorsOnly() {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM person WHERE role = 'Doctor'";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return doctors;

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Doctor doctor = new Doctor(
                        rs.getInt("person_id"),
                        rs.getString("name"),
                        rs.getInt("age")
                );
                doctor.setSpecialization(rs.getString("specialization"));
                doctors.add(doctor);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return doctors;
    }

    public Person getPersonById(int id) {
        String sql = "SELECT * FROM person WHERE person_id = ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return createPerson(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return null;
    }

    public boolean updatePatient(Patient patient) {
        String sql = "UPDATE person SET name = ?, age = ?, diagnosis = ? WHERE person_id = ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, patient.getName());
            stmt.setInt(2, patient.getAge());
            stmt.setString(3, patient.getDiagnosis());
            stmt.setInt(4, patient.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    public boolean updateDoctor(Doctor doctor) {
        String sql = "UPDATE person SET name = ?, age = ?, specialization = ? WHERE person_id = ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, doctor.getName());
            stmt.setInt(2, doctor.getAge());
            stmt.setString(3, doctor.getSpecialization());
            stmt.setInt(4, doctor.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    public boolean deletePerson(int id) {
        String sql = "DELETE FROM person WHERE person_id = ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();
            return rows > 0;


        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    public List<Person> searchByName(String name) {
        List<Person> results = new ArrayList<>();
        String sql = "SELECT * FROM person WHERE name LIKE ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return results;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + name + "%");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                results.add(createPerson(rs));
            }
            rs.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return results;
    }


    private Person createPerson(ResultSet rs) throws SQLException {
        String role = rs.getString("role");

        if (role.equals("Patient")) {
            Patient patient = new Patient(
                    rs.getInt("person_id"),
                    rs.getString("name"),
                    rs.getInt("age")
            );
            patient.setDiagnosis(rs.getString("diagnosis"));
            return patient;
        } else {
            Doctor doctor = new Doctor(
                    rs.getInt("person_id"),
                    rs.getString("name"),
                    rs.getInt("age")
            );
            doctor.setSpecialization(rs.getString("specialization"));
            return doctor;
        }
    }
}