package Persistance;

/**
 * Created by Raul on 4/4/2017.
 */
public class ClientGateway {
    private int idCardNumber;
    private String name;
    private int cnp;
    private String address;

    public ClientGateway(int idCardNumber, String name, int cnp, String address) {
        this.idCardNumber = idCardNumber;
        this.name = name;
        this.cnp = cnp;
        this.address = address;
    }

    public int getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(int idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCnp() {
        return cnp;
    }

    public void setCnp(int cnp) {
        this.cnp = cnp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}

