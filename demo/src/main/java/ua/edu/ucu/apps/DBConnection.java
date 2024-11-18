package ua.edu.ucu.apps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import lombok.SneakyThrows;

public class DBConnection {

    private Connection dbConn;
    private static DBConnection instance;

    @SneakyThrows
    private DBConnection() {
        this.dbConn = DriverManager.getConnection("C:\\Users\\admin\\Documents\\cache1.db");
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    @SneakyThrows
    public void createDocument(String path, String content) {
        PreparedStatement statement = dbConn
            .prepareStatement("INSERT INTO test(path, parsed) VALUES(?, ?)");
        statement.setString(1, path);
        statement.setString(2, content);
        statement.executeUpdate();
        statement.close();
    }

    @SneakyThrows
    public String getDocument(String path) {
        PreparedStatement query = 
            dbConn.prepareStatement("SELECT * FROM DOCUMENT WHERE path=?");
        query.setString(1, path);
        ResultSet resultSet = query.executeQuery();
        return resultSet.getString("parsed");
    }
}
