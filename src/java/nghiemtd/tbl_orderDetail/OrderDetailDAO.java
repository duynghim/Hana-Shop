package nghiemtd.tbl_orderDetail;

import nghiemtd.connection.MyConnection;
import tbl_foods.FoodDTO;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAO implements Serializable {
    private Connection conn;
    private PreparedStatement stm;
    private ResultSet rs;

    public OrderDetailDAO() {
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

    public int getLastOrderDetail() throws Exception{
        int lastID = 0;
        String sql = "Select Max(ID) as 'lastID' " +
                "From Tbl_OrderDetail ";
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

    public void insertOrderDetail(int orderDetailID, int orderID, FoodDTO food) throws Exception{
        String sql = "Insert into Tbl_OrderDetail(ID, orderID, foodID, foodName, quantity, price) " +
                "Values(?,?,?,?,?,?)";
        try {
            conn = MyConnection.getMyConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, orderDetailID);
            stm.setInt(2, orderID);
            stm.setInt(3, food.getFoodID());
            stm.setString(4, food.getFoodName());
            stm.setInt(5, food.getQuantity());
            stm.setFloat(6, food.getPrice());
            stm.executeUpdate();
        }finally {
            closeConnection();
        }
    }

    public List<OrderDetailDTO> getAllListOrderByOrderID(int orderID) throws Exception{
        List<OrderDetailDTO> listOrderDetail = new ArrayList<>();
        String sql = "Select ID, orderID, foodID, foodName, quantity, price " +
                "From Tbl_OrderDetail " +
                "Where orderID = ?";
        try {
            conn = MyConnection.getMyConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, orderID);
            rs = stm.executeQuery();
            while (rs.next()){
                int id = rs.getInt("ID");
                int foodID = rs.getInt("foodID");
                String foodName = rs.getString("foodName");
                int quantity = rs.getInt("quantity");
                float price = rs.getFloat("price");
                OrderDetailDTO dto = new OrderDetailDTO(id, orderID, foodID, foodName, quantity, price);
                listOrderDetail.add(dto);
            }
        }finally {
            closeConnection();
        }
        return listOrderDetail;
    }
}
