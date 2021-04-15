package nghiemtd.tbl_category;

import nghiemtd.connection.MyConnection;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements Serializable {
    private Connection conn;
    private PreparedStatement stm;
    private ResultSet rs;

    public CategoryDAO() {
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

    public List<CategoryDTO> getCategoryList() throws Exception{
        List<CategoryDTO> listCategory = new ArrayList<>();
        String sql = "Select categoryId, categoryName " +
                "From Tbl_Categorys ";

        try {
            conn = MyConnection.getMyConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()){
                String categoryID = rs.getString("categoryId");
                String categoryName = rs.getString("categoryName");
                CategoryDTO dto = new CategoryDTO(categoryID, categoryName);
                listCategory.add(dto);
            }
        }finally {
            closeConnection();
        }
        return  listCategory;
    }
}
