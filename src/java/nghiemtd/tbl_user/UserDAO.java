package nghiemtd.tbl_user;

import nghiemtd.connection.MyConnection;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO implements Serializable {
    private Connection conn;
    private PreparedStatement stm;
    private ResultSet rs;

    public UserDAO() {
    }

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }

        if (stm != null) {
            stm.close();
        }

        if (conn != null) {
            conn.close();
        }
    }

    public UserDTO checkLogin(String userID, String password) throws Exception{
        String sql = "Select UserID, Password, FullName, IsAdmin " +
                "From Tbl_Users " +
                "Where UserID = ? and Password = ?";
        try {
            conn = MyConnection.getMyConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, userID);
            stm.setString(2, password);
            rs = stm.executeQuery();
            if (rs.next()){
                String fullname = rs.getString("FullName");
                boolean isAdmin = rs.getBoolean("IsAdmin");
                return new UserDTO(userID, password, fullname, isAdmin);
            }
        }finally {
            closeConnection();
        }
        return null;
    }
}
