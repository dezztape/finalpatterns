package Singleton;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {
    private static DatabaseConnectionManager instance; // Статический экземпляр Singleton
    private Connection connection;

    // Приватный конструктор, чтобы предотвратить создание экземпляров извне класса
    private DatabaseConnectionManager(String url, String username, String password) throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
    }

    // Метод для получения экземпляра Singleton (создает его при первом вызове)
    public static DatabaseConnectionManager getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnectionManager("jdbc:postgresql://localhost:5432/postgres", "postgres", "Alisher_2003");
        }
        return instance;
    }

    // Метод для получения соединения с базой данных
    public Connection getConnection() {
        return connection;
    }
}
