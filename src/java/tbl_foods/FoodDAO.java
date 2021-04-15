package tbl_foods;

import nghiemtd.connection.MyConnection;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FoodDAO implements Serializable {
    private Connection conn;
    private PreparedStatement stm;
    private ResultSet rs;

    public FoodDAO() {
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


    public List<FoodDTO> getAllFoodPage1(int page) throws Exception {
        String sql = "Select foodID, foodName, createDate, categoryID," +
                " image, quantity, price, status " +
                "From Tbl_Foods " +
                "Where status = 1 and quantity > 0 " +
                "Order by createDate Asc " +
                "Offset ? rows " +
                "Fetch Next 5 rows only ";

        return getFoodDTOS(page, sql);
    }

    private List<FoodDTO> getFoodDTOS(int page, String sql) throws Exception {
        List<FoodDTO> listFood = new ArrayList<>();
        try {
            conn = MyConnection.getMyConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, page);
            createNewFoodDto(listFood);
        } finally {
            closeConnection();
        }
        return listFood;
    }

    public List<FoodDTO> searchByName(String searchValue, int page) throws Exception {
        String sql = "Select foodID, foodName, createDate, categoryID, image, quantity, price, status " +
                "From Tbl_Foods " +
                "Where status = 1 and quantity > 0  and foodName Like '%" + searchValue + "%' " +
                "Order by createDate Asc " +
                "Offset ? rows " +
                "Fetch Next 5 rows only ";

        return getFoodDTOS(page, sql);
    }

    public List<FoodDTO> searchByPrice(float min, float max, int page) throws Exception {
        String sql = "Select foodID, foodName, createDate, categoryID, image, quantity, price, status " +
                "From Tbl_Foods " +
                "Where status = 1 and quantity > 0 and price Between ? and ? " +
                "Order by createDate Asc " +
                "Offset ? rows " +
                "Fetch Next 5 rows only ";
        List<FoodDTO> listFood = new ArrayList<>();
        try {
            conn = MyConnection.getMyConnection();
            stm = conn.prepareStatement(sql);
            stm.setFloat(1, min);
            stm.setFloat(2, max);
            stm.setInt(3, page);
            createNewFoodDto(listFood);
        } finally {
            closeConnection();
        }
        return listFood;
    }

    public int countSearchPrice(float min, float max) throws Exception {
        int count = 0;
        String sql = "Select count(foodID) as 'Number' " +
                "From Tbl_Foods " +
                "Where status = 1 and quantity > 0 and price Between ? and ? ";
        try {
            conn = MyConnection.getMyConnection();
            stm = conn.prepareStatement(sql);
            stm.setFloat(1, min);
            stm.setFloat(2, max);
            rs = stm.executeQuery();
            if (rs.next()) {
                count = rs.getInt("Number");
            }
        } finally {
            closeConnection();
        }
        return count;
    }

    public boolean removeFood(int id) throws Exception {
        String sql = "Update Tbl_Foods " +
                "Set status = 0 " +
                "Where foodID = ?";
        try {
            conn = MyConnection.getMyConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            int row = stm.executeUpdate();
            if (row > 0) {
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public List<FoodDTO> searchFoodByCategory(int id, int page) throws Exception {
        String sql = "Select foodID, foodName, createDate, categoryID, image, quantity, price, status " +
                "From Tbl_Foods " +
                "Where status = 1 and quantity > 0 and categoryID = ? " +
                "Order by createDate Asc " +
                "Offset ? rows " +
                "Fetch Next 5 rows only ";

        List<FoodDTO> listFood = new ArrayList<>();
        try {
            conn = MyConnection.getMyConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            stm.setInt(2, page);
            createNewFoodDto(listFood);
        } finally {
            closeConnection();
        }
        return listFood;
    }

    public int countSearchAll() throws Exception {
        String sql = "Select count(foodID) as 'Number' " +
                "From Tbl_Foods " +
                "Where status = 1 and quantity > 0";
        return count(sql);
    }

    private int count(String sql) throws Exception {
        int count = 0;
        try {
            conn = MyConnection.getMyConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                count = rs.getInt("Number");
            }
        } finally {
            closeConnection();
        }
        return count;
    }

    public int countSearchByName(String name) throws Exception {
        String sql = "Select count(foodID) as 'Number' " +
                "From Tbl_Foods " +
                "Where status = 1 and quantity > 0  and foodName Like '%" + name + "%' ";
        return count(sql);
    }

    public int countByCategory(int id) throws Exception {
        int count = 0;
        String sql = "Select count(foodID) as 'Number' " +
                "From Tbl_Foods " +
                "Where status = 1 and quantity > 0  and categoryID = ?";
        try {
            conn = MyConnection.getMyConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                count = rs.getInt("Number");
            }
        } finally {
            closeConnection();
        }
        return count;
    }

    public boolean insertFood(int foodID, String foodName, java.sql.Date createDate, int categoryID, int quantity, float price, String image) throws Exception {
        String sql = "Insert Into Tbl_Foods(foodID, foodName, createDate, categoryID, quantity, price, status, image) " +
                "Values(?,?,?,?,?,?,?,?)";
        try {
            conn = MyConnection.getMyConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, foodID);
            stm.setString(2, foodName);
            stm.setDate(3, createDate);
            stm.setInt(4, categoryID);
            stm.setInt(5, quantity);
            stm.setFloat(6, price);
            stm.setBoolean(7, true);
            stm.setString(8, image);
            int row = stm.executeUpdate();
            if (row > 0) {
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public int getLastIDOfFood() throws Exception {
        String sql = "Select Max (foodID) as 'LastID' From Tbl_Foods ";

        try {
            conn = MyConnection.getMyConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("LastID") + 1;
            }
        } finally {
            closeConnection();
        }
        return 1;
    }

    public boolean updateFood(String foodID, String foodName, float foodPrice, int quantity, String categoryID, String image) throws Exception {
        String sql = "Update Tbl_Foods " +
                "Set foodName = ?, price = ?, quantity = ?, categoryID = ?, image = ? " +
                "Where foodID = ?";
        try {
            conn = MyConnection.getMyConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, foodName);
            stm.setFloat(2, foodPrice);
            stm.setInt(3, quantity);
            stm.setString(4, categoryID);
            stm.setString(5, image);
            stm.setString(6, foodID);
            int row = stm.executeUpdate();
            if (row > 0) {
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    private void createNewFoodDto(List<FoodDTO> listFood) throws SQLException {
        rs = stm.executeQuery();
        while (rs.next()) {
            int foodID = rs.getInt("foodID");
            String foodName = rs.getString("foodName");
            Date createDate = rs.getDate("createDate");
            String categoryID = rs.getString("categoryID");
            String image = rs.getString("image");
            int quantity = rs.getInt("quantity");
            float price = rs.getFloat("price");
            boolean status = rs.getBoolean("status");

            FoodDTO dto = new FoodDTO(foodID, foodName, createDate, categoryID, image, quantity, price, status);

            listFood.add(dto);
        }
    }

    public FoodDTO findFoodByFoodID(int id) throws Exception{
        FoodDTO dto = null;
        String sql = "Select foodID, foodName, createDate, categoryID," +
                " image, quantity, price, status " +
                "From Tbl_Foods " +
                "Where foodID = ? ";
        try {
            conn = MyConnection.getMyConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if (rs.next()){
                int foodID = rs.getInt("foodID");
                String foodName = rs.getString("foodName");
                Date createDate = rs.getDate("createDate");
                String categoryID = rs.getString("categoryID");
                String image = rs.getString("image");
                int quantity = rs.getInt("quantity");
                float price = rs.getFloat("price");
                boolean status = rs.getBoolean("status");

                dto = new FoodDTO(foodID, foodName, createDate, categoryID, image, quantity, price, status);
                return dto;
            }
        }finally {
            closeConnection();
        }
        return dto;
    }

    public boolean updateQuantityOfFoodAfterOrder(int foodID, int quantity) throws Exception{
        String sql = "Update Tbl_Foods " +
                "Set quantity = quantity - ? " +
                "Where foodID = ?";
        try {
            conn = MyConnection.getMyConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, quantity);
            stm.setInt(2, foodID);
            int row = stm.executeUpdate();
            if (row > 0){
                return true;
            }
        }finally {
            closeConnection();
        }
        return false;
    }
}
