package proto;

import java.io.Serializable;

/**
 * Created by whydk on 2016/8/10.
 */
public class User implements Serializable {
    private int ID;
    private String userName;
    private String Password;
    private String address;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", userName='" + userName + '\'' +
                ", Password='" + Password + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
