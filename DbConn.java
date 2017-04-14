package Database; /**
 * Created by Raul on 4/4/2017.
 */
import java.sql.*;
public class DbConn {
    // JDBC driver name and database URL
    static final String DB_URL = "jdbc:sqlserver://localhost:1433;DatabaseName=Bank;";

    //  Database credentials
    static final String USER = "sa";
    static final String PASS = "sqlserver";

    public static Connection openConnection() {
        Connection conn = null;
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost/Bank";
                conn = DriverManager.getConnection(url, "root", "admin");
            } catch (ClassNotFoundException cnfe) {
                System.out.println("Could not find the JDBC driver!");
                System.exit(1);
            }
            //Connection conn = null;
            //conn = DriverManager.getConnection(DB_URL, USER, PASS);
            return conn;
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }

        return null;


    }

    public static void closeConnection(Connection conn, Statement stmt) {
        try {
            if (stmt != null)
                stmt.close();
        } catch (SQLException se2) {
        }// nothing we can do
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}