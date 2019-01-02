package vimalesh.fire;

/**
 * Created by User on 2/8/2017.
 */

public class UserInformation {

    private String name;
    private String email;
    private String balance;
    private String barcode;
    private String id;
    public UserInformation(){

    }


    public void setid(String id) {
        this.id = id;
    }
    public String getid() {
        return id;
    }


    public String getbarcode() {
        return barcode;
    }

    public void setbarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

   public String getbalance() {
        return balance;
    }

    public void setbalance(String balance) {
        this.balance = balance;
    }


}
