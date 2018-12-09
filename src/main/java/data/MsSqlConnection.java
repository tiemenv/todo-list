package data;

import util.TodoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MsSqlConnection {
    private static final String connectionString = Secret.getConnString();

    public static Connection getConnection(){
        try {
            Connection con = DriverManager.getConnection(connectionString);
            return con;
        } catch (SQLException ex){
            throw new TodoException("Can't connect to SQL Server", ex);
        }
    }
}
