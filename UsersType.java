package Persistance;

/**
 * Created by Raul on 4/5/2017.
 */

import Database.DbConn;
import java.sql.*;

public class UsersType {
    private int id;
    private int userId;
    private String userType;

    public UsersType(){}

    public UsersType(int id, int userId, String userType) {
        this.id = id;
        this.userId = userId;
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public ResultSet findAll() {
        Connection conn = DbConn.openConnection();
        Statement stmt =null;
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, userId, userType FROM UsersType";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                int userId = rs.getInt("userId");
                String userType = rs.getString("userType");
                System.out.print(" ID: " + id);
                System.out.print(" ,User ID: " + userId);
                System.out.println(" , User Type: " + userType);
            }
            //rs.close();
            return rs;
        }catch (SQLException se) {
            se.printStackTrace();
        }
        DbConn.closeConnection(conn,stmt);
        return null;
    }

    public void create (int userId, String userType){
        Connection conn = DbConn.openConnection();
        Statement stmt = null;
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "insert into UsersType (userId, userType) values ('"+userId+"','"+userType+"')";
            stmt.execute(sql);
        }catch (SQLException se) {
            se.printStackTrace();

        }
        DbConn.closeConnection(conn,stmt);
    }

    public void update(int id, int userId, String userType)
    {
        Connection conn  =DbConn.openConnection();
        Statement stmt=null;
        try{
            System.out.println("Creating statement...");
            stmt=conn.createStatement();
            String sql;
            sql="update UsersType set userId='" + userId + "', userType='" + userType + "' where id='" + id + "'";
            stmt.execute(sql);
        }
        catch(SQLException se){
            se.printStackTrace();
        }
        DbConn.closeConnection(conn,stmt);
    }

    public void delete(int id){
        Connection conn = DbConn.openConnection();
        Statement stmt=null;
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "delete FROM usersRole where id='" + id + "'";
            stmt.execute(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
        DbConn.closeConnection(conn,stmt);
    }
}
