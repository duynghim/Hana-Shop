package nghiemtd.tbl_order;

import nghiemtd.connection.MyConnection;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements Serializable {
    private Connection conn;
    private PreparedStatement stm;
    private ResultSet rs;

    public OrderDAO() {
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

    public void insertNewOrder(OrderDTO dto) throws Exception{
        String sql = "Insert into Tbl_Orders(ID, total, date, userID, paymentTypeID) " +
                "values(?,?,?,?,?)";
        try {
            conn = MyConnection.getMyConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, dto.getOrderID());
            stm.setFloat(2, dto.getTotal());
            stm.setDate(3, dto.getCreateOfDate());
            stm.setString(4, dto.getUserID());
            stm.setInt(5, 1);
            stm.executeUpdate();
        }finally {
            closeConnection();
        }
    }

    public int getLastIDOfOrder() throws Exception{
        int lastID = 0;
        String sql = "Select Max(ID) as 'lastID' " +
                "From Tbl_Orders ";
        try {
            conn = MyConnection.getMyConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()){
                lastID = rs.getInt("lastID");
            }
        }finally {
            closeConnection();
        }
        return lastID;
    }

    public List<OrderDTO> getAllOrderByUserID(String IDOfUser) throws Exception{
        List<OrderDTO> listOrder = new ArrayList<>();
        String sql = "Select ID, total, date " +
                "From Tbl_Orders " +
                "Where userID = ? " +
                "Order By date Desc";
        try {
            conn = MyConnection.getMyConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, IDOfUser);
            rs = stm.executeQuery();
            while (rs.next()){
                int id = rs.getInt("ID");
                float total = rs.getFloat("total");
                Date date = rs.getDate("date");
                OrderDTO dto = new OrderDTO(id, total, date, IDOfUser);
                listOrder.add(dto);
            }
        }finally {
            closeConnection();
        }
        return listOrder;
    }

    public List<OrderDTO> getOrderByDate(Date date) throws Exception{
        List<OrderDTO> listOrder = new ArrayList<>();
        String sql = "Select ID, total, date, userID " +
                "From Tbl_Orders " +
                "Where date = ? " +
                "Order By date Desc";
        try {
            conn = MyConnection.getMyConnection();
            stm = conn.prepareStatement(sql);
            stm.setDate(1, date);
            rs = stm.executeQuery();
            while (rs.next()){
                int id = rs.getInt("ID");
                float total = rs.getFloat("total");
                String userID = rs.getString("userID");
                Date dateOfCreate = rs.getDate("date");
                OrderDTO dto = new OrderDTO(id, total, dateOfCreate, userID);
                listOrder.add(dto);
            }
        }finally {
            closeConnection();
        }
        return listOrder;
    }
}
