package Persistance;
/**
 * Created by Raul on 4/4/2017.
 */
import java.sql.*;
import java.util.ArrayList;
import Database.DbConn;
public class Client {
    /**
     * CRUD OPERATIONS
     */
    public ArrayList<ClientGateway> findAll() {
        Connection conn = DbConn.openConnection();
        Statement stmt = null;
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            ArrayList<ClientGateway> clientGateways = new ArrayList<ClientGateway>();
            String sql;
            sql = "SELECT idCardNumber,name,cnp,address FROM ClientGateway";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("name");
                int idCardNumber = rs.getInt("idCardNumber");
                int CNP = rs.getInt("CNP");
                String address = rs.getString("address");
                clientGateways.add(new ClientGateway(idCardNumber,name,CNP,address));
                System.out.print("ID: " + idCardNumber);
                System.out.print(", Name: " + name);
                System.out.print(", CNP: " + CNP);
                System.out.println(", Address: " +address);
            }
            //rs.close();
            return clientGateways;
        }catch (SQLException se) {
            se.printStackTrace();
        }
        DbConn.closeConnection(conn,stmt);
        return null;
    }

    public ResultSet findById(int id) {
        Connection conn = DbConn.openConnection();
        Statement stmt = null;
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT idCardNumber,name,cnp,address FROM ClientGateway WHERE idCardNumber='"+id+"'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //Retrieve by column name
                String name = rs.getString("name");
                int idCardNumber = rs.getInt("idCardNumber");
                int CNP = rs.getInt("cnp");
                String address = rs.getString("address");

                //Display values
                System.out.print("ID: " + idCardNumber);
                System.out.print(", Name: " + name);
                System.out.print(", CNP: " + CNP);
                System.out.println(", Address: " +address);
            }

            // rs.close();
            return rs;
        }catch (SQLException se) {
            se.printStackTrace();
        }
        DbConn.closeConnection(conn,stmt);
        return null;
    }

    public void create(String name, int cnp, String address) {
        //dbConn=new dbConn();
        Connection conn = DbConn.openConnection();
        Statement stmt = null;
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "insert into ClientGateway (name, cnp, address) values ('"+name+"','"+cnp+"','"+address+"')";
            stmt.execute(sql);
        }catch (SQLException se) {
            se.printStackTrace();

        }
        DbConn.closeConnection(conn,stmt);
    }

    public void update(int idCardNumber, String name, int cnp, String address){
        Connection conn = DbConn.openConnection();
        Statement stmt = null;
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "update ClientGateway set name='" + name + "', cnp='" + cnp + "', address='" + address + "' where idCardNumber='" + idCardNumber + "'";
            stmt.execute(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
        DbConn.closeConnection(conn,stmt);
    }

    public void delete(int idCardNumber){
        Connection conn = DbConn.openConnection();
        Statement stmt = null;
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "delete FROM ClientGateway where idCardNumber='" + idCardNumber + "'";
            stmt.execute(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
        DbConn.closeConnection(conn,stmt);
    }

    public static void main(String[] args)
    {
        Client cl= new Client();
        //cl.create();
        //cl.update(1,"adaa",112,"add");
        cl.create("boss",13,"adresa1");
        cl.update(2,"tudor",122,"viilor");
        //cl.delete(7);
        cl.findAll();
    }
}
