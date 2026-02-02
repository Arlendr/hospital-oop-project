package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/hospital_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "mango225"; // ВАШ ПАРОЛЬ!

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Подключение к базе данных успешно!");

        } catch (ClassNotFoundException e) {
            System.out.println("❌ JDBC драйвер не найден!");
            System.out.println("Добавьте postgresql-XX.jar в проект!");
            e.printStackTrace();

        } catch (SQLException e) {
            System.out.println("❌ Ошибка подключения к базе данных!");
            System.out.println("Проверьте:");
            System.out.println("1. PostgreSQL запущен");
            System.out.println("2. Пароль правильный: " + PASSWORD);
            System.out.println("3. База данных 'hospital_db' существует");
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("🔌 Соединение закрыто");
            } catch (SQLException e) {
                System.out.println("Ошибка при закрытии соединения");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Connection conn = getConnection();
        if (conn != null) {
            System.out.println("🎉 Тест подключения пройден!");
            closeConnection(conn);
        }
    }
}