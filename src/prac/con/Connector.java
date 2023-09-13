package prac.con;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connector {
    private static final String url="jdbc:postgresql://localhost:5432/MYDB";
    private static final String user="root";
    private static final String password="root";
    public static Connection getConnection() throws Exception{

        Connection con = DriverManager.getConnection(url, user, password);
        return con;
    }
}
