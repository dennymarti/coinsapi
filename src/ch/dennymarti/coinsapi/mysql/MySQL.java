package ch.dennymarti.coinsapi.mysql;

import java.sql.*;

public class MySQL {

    private String host;
    private String username;
    private String database;
    private String password;
    private String port;

    private static Connection connection;

    public MySQL(String host, String port, String database, String username, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    public boolean isConnected() {
        return connection != null;
    }

    public void connect() {
        if (!isConnected()) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
                System.out.println("");
            } catch (SQLException sqlException) {
                printException("Es konnte keine Verbindung zur Datenbank hergestellt werden!", sqlException.getMessage());
            }
        }
    }

    public void disconnect() {
        if (isConnected()) {
            try {
                connection.close();
                System.out.println("");
            } catch (SQLException sqlException) {
                printException("Die Verbindung konnte nicht geschlossen werden!", sqlException.getMessage());
            }
        }
    }

    public void createTable() {
        if (isConnected()) {
            executeQuery("CREATE TABLE IF NOT EXISTS playercoins (" +
                    "playername VARCHAR(32), uuid VARCHAR(36) NOT NULL, coins INTEGER NOT NUll" +
                    ");");
        }
    }

    public void executeQuery(String sqlQuery) {
        if (!isConnected()) {
            connect();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException sqlException) {
            printException("Ein SQL-Query konnte nicht ausgefuehrt werden!", sqlException.getMessage());
        }
    }

    public ResultSet getResult(String sqlQuery) {
        if (!isConnected()) {
            connect();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            return preparedStatement.executeQuery();
        } catch (SQLException sqlException) {
            printException("Eine SQL-Abfrage konnte nicht ausgefuehrt werden!", sqlException.getMessage());
        }
        return null;
    }

    public void printException(String message, String exception) {
        System.out.println("[CoinsAPI] " + message);
        System.out.print("[CoinsAPI] " + exception);
    }
}
