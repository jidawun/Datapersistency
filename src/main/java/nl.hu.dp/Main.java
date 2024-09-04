package nl.hu.dp;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try {
            testConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            String url =
                    "jdbc:postgresql://localhost/ovchip?user=postgres&password=apenkooi";
            connection = DriverManager.getConnection(url);
        }
        return connection;
    }

    public static void closeConnection() throws
            SQLException {
        if (connection != null) {
            connection.close();
            connection = null;
        }
    }

    public static void testConnection() throws SQLException {
        getConnection();
        String query = "SELECT * FROM reiziger;";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet set = statement.executeQuery();
        while (set != null && set.next()) {
            System.out.println(set.getString("achternaam"));
        }
        closeConnection();
    }
}
