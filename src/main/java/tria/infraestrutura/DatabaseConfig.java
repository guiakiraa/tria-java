package tria.infraestrutura;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static String USER = "RM556128";
    private static String PASSWORD = "081005";
    private static String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCl";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
