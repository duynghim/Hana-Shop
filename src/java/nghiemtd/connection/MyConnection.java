/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiemtd.connection;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author trndu
 */
public class MyConnection implements Serializable{
    public static Connection getMyConnection() throws Exception{
         //1. Load driver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //2. Create connection statement
        String url = "jdbc:sqlserver://localhost:1433;databaseName=J3.L.P0013";
        //3. Open connection
        Connection con = DriverManager.getConnection(url, "sa", "Nh@Tr@ng29");
        return con;
    }
}
