package nghiemtd.tbl_user;

import java.io.Serializable;


public class UserDTO implements Serializable {
    private String userID;
    private String password;
    private String fullname;
    private boolean isAdmin;

    public UserDTO(String userID, String password, String fullname, boolean isAdmin) {
        this.userID = userID;
        this.password = password;
        this.fullname = fullname;
        this.isAdmin = isAdmin;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
