package Business;

/**
 * Created by Raul on 4/5/2017.
 */


import Persistance.AccountGateway;
import Persistance.Account;
import Persistance.ClientGateway;
import Persistance.Client;

import javax.swing.table.DefaultTableModel;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class UsersBusiness {

    public void createClient(String name, int cnp, String address)
    {
        Client client = new Client();
        client.create(name,cnp,address);
    }

    public void updateClient(int idCardNumber, String name, int cnp, String address)
    {
        Client client = new Client();
        client.update(idCardNumber,name,cnp,address);
    }

    public void deleteClient(int idCardNumber)
    {
        Client client = new Client();
        client.delete(idCardNumber);
    }

    public DefaultTableModel listAllClientInfo()
    {
        Client client = new Client();
        ArrayList<ClientGateway> clientGatewayList = client.findAll();
        try {
            return buildClientTableModel(clientGatewayList);
        }
        catch (SQLException se)
        {se.printStackTrace();}
        return null;
    }

    public void createAccount(int idCardNumber, String type, int amount, String creationDate)
    {
        Account account = new Account();
        account.create(idCardNumber,type, amount, creationDate);
    }

    public void updateAccount(int idNumber, int idCardNumber, String type, int amount, Date creationDate)
    {
        Account account = new Account();
        account.update(idNumber,idCardNumber,type, amount,creationDate);
    }

    public void deleteAccount(int idNumber)
    {
        Account account = new Account();
        account.delete(idNumber);
    }


    public DefaultTableModel listAllAccountInfo()
    {
        Account account = new Account();
        ArrayList<AccountGateway> accountGatewayList = account.findAll();
        try {
            return buildAccountTableModel(accountGatewayList);
        }
        catch (SQLException se)
        {se.printStackTrace();}
        return null;
    }

    public static DefaultTableModel buildClientTableModel(ArrayList<ClientGateway> list)
            throws SQLException {

        String[] columnNames = {"ID",
                "Name",
                "CNP",
                "Address"
        };

        Object[][] array = new Object[list.size()][];
        int i = 0;
        for (ClientGateway c : list)
        {
            array[i] = new Object[4];
            array[i][0] = c.getIdCardNumber();
            array[i][1] = c.getName();
            array[i][2] = c.getCnp();
            array[i][3] = c.getAddress();

            i++;
        }
        return new DefaultTableModel(array, columnNames);
    }
    public static DefaultTableModel buildAccountTableModel(ArrayList<AccountGateway> list)
            throws SQLException {

        String[] columnNames = {"ID Number",
                "ID Card Number",
                "Type",
                "Amount",
                "Creation Date"};

        Object[][] array = new Object[list.size()][];
        int i = 0;
        for (AccountGateway c : list)
        {
            array[i] = new Object[5];
            array[i][0] = c.getIdNumber();
            array[i][1] = c.getIdCardNumber();
            array[i][2] = c.getType();
            array[i][3] = c.getAmount();
            array[i][4] = c.getCreationDate();
            i++;
        }
        return new DefaultTableModel(array, columnNames);
    }
    public int transferMoney(int acc1, int acc2, int amount){
        try {
            PrintWriter sender = new PrintWriter( "AccountGateway"+acc1+".txt");
            PrintWriter receiver = new PrintWriter("AccountGateway"+acc2+".txt");

            Account account = new Account();
            AccountGateway senderAccountGateway = account.findById(acc1);
            AccountGateway receiverAccountGateway = account.findById(acc2);
            if (senderAccountGateway.getAmount() <= amount) {
                return -1;
            } else {
                account.updateSum(acc1, senderAccountGateway.getAmount() - amount);
                account.updateSum(acc2, receiverAccountGateway.getAmount() + amount);
                sender.println("Soldul contului cu ID = "+acc1+" a fost debitat cu suma de "+amount+" lei. ");
                receiver.println("Soldul contului cu ID = "+acc2+" a crescut cu suma de "+amount+" lei. ");
                sender.close();
                receiver.close();
                return 1;
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            return -1;
        }
    }
    public static void openUserReport(String name)
    {
        try {

            PrintWriter user = new PrintWriter(new FileOutputStream(name+".txt", true));
            user.println("La data de "+new Date()+" user-ul "+ name+" s-a logat.");
            user.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public static void closeUserReport(String name)
    {
        try {

            PrintWriter user = new PrintWriter(new FileOutputStream(name+".txt", true));
            user.println("La data de "+new Date()+" user-ul "+ name+" s-a delogat.");
            user.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }


    public static void addUserOperationToReport(String name,String operation,boolean client,String identification)
    {
        try {

            PrintWriter user = new PrintWriter(new FileOutputStream(name+".txt", true));
            if (operation.equals("create"))
            {
                if (client==true)
                {
                    user.println("La data de " + new Date() + " user-ul " + name + " a creat un client nou pe numele"+
                            " = " +identification);
                    user.close();
                }
                if (client==false)
                {
                    user.println("La data de " + new Date() + " user-ul " + name + " a creat un cont nou pentru clientul"+
                            "cu id = " +identification);
                    user.close();
                }
            }
            if (client==true) {
                user.println("La data de " + new Date() + " user-ul " + name + " a facut operatia de "+operation+ "asupra clientului cu " +
                        "id = " +identification);
                user.close();
            }
            else if (client==false)
            {
                user.println("La data de " + new Date() + " user-ul " + name + " a facut operatia de "+operation+ "asupra account cu" +
                        "id = " +identification);
                user.close();
            }

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

}
