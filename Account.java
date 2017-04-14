package Persistance; /**
 * Created by Raul on 4/4/2017.
 */
import Database.DbConn;
import java.sql.*;

import java.util.ArrayList;
import java.util.Date;
public class Account {
    /**
     * CRUD OPERATIONS
     */
    public ArrayList<AccountGateway> findAll() {
        ArrayList<AccountGateway> accountGateways = new ArrayList<AccountGateway>();
        Connection conn = DbConn.openConnection();
        Statement stmt = null;
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT idNumber,idCardNumber,type,amount,creationDate FROM AccountGateway";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String type = rs.getString("type");
                int idCardNumber = rs.getInt("idCardNumber");
                int idNumber = rs.getInt("idNumber");
                int amount = rs.getInt("amount");
                String creationDate  = rs.getString("creationDate");
                accountGateways.add(new AccountGateway(idNumber,idCardNumber,type,amount,creationDate));
                System.out.print("ID: " + idCardNumber);
                System.out.print(", idNumber: " + idNumber);
                System.out.print(", amount: " + amount);
                System.out.println(", type: " +type);
                System.out.println(", creationDate: " +creationDate);
            }
            // rs.close();
            return accountGateways;
        }catch (SQLException se) {
            se.printStackTrace();
        }
        DbConn.closeConnection(conn,stmt);
        return null;
    }

    public AccountGateway findById(int id) {
        Connection conn = DbConn.openConnection();
        Statement stmt = null;
        AccountGateway accountGateway = new AccountGateway();
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT idNumber,idCardNumber,type,amount,creationDate FROM AccountGateway WHERE idNumber='"+id+"'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String type = rs.getString("type");
                int idCardNumber = rs.getInt("idCardNumber");
                int idNumber = rs.getInt("idNumber");
                int amount = rs.getInt("amount");
                String creationDate  = rs.getString("creationDate");
                accountGateway = new AccountGateway(idNumber,idCardNumber,type,amount,creationDate);

                System.out.print("ID: " + idCardNumber);
                System.out.print(", idNumber: " + idNumber);
                System.out.print(", amount: " + amount);
                System.out.println(", type: " +type);
                System.out.println(", creationDate: " +creationDate);
            }

            // rs.close();
            return accountGateway;
        }catch (SQLException se) {
            se.printStackTrace();
        }
        DbConn.closeConnection(conn,stmt);
        return null;
    }

    public void create(int idCardNumber, String type, int amount, String creationDate) {

        Connection conn = DbConn.openConnection();
        Statement stmt = null;
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "insert into AccountGateway (idCardNumber,type,amount,creationDate) values ('"+idCardNumber+"','"+type+"','"+amount+"','"+creationDate+"')";
            stmt.execute(sql);
        }catch (SQLException se) {
            se.printStackTrace();

        }
        DbConn.closeConnection(conn,stmt);
    }

    public void update(int idNumber, int idCardNumber, String type, int amount, Date creationDate){
        Connection conn = DbConn.openConnection();
        Statement stmt = null;
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "update AccountGateway set idCardNumber='" + idCardNumber + "', type='" + type + "', amount='" + amount + "', creationDate='" +creationDate+ "' where idNumber='" + idNumber + "'";
            stmt.execute(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
        DbConn.closeConnection(conn,stmt);
    }

    public void updateSum(int idNumber, int amount){
        Connection conn = DbConn.openConnection();
        Statement stmt = null;
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "update AccountGateway set amount='" + amount + "' where idNumber='" + idNumber + "'";
            stmt.execute(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
        DbConn.closeConnection(conn,stmt);
    }

    public void delete(int idNumber){
        Connection conn = DbConn.openConnection();
        Statement stmt = null;
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "delete FROM AccountGateway where idNumber='" + idNumber + "'";
            stmt.execute(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
        DbConn.closeConnection(conn,stmt);
    }

    public static void main(String[] args)
    {
        Account acc= new Account();
        //cl.create();
        //cl.update(1,"adaa",112,"add");
        acc.create(8,"spending",210,"2004.02.05");
        //cl.delete(6);
        //cl.delete(7);
        acc.findAll();
        acc.findById(3);
    }
}