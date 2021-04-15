package tbl_foods;

import java.io.Serializable;
import java.util.Date;

public class FoodDTO implements Serializable {
    private int foodID;
    private String foodName;
    private Date createDate;
    private String categoryID;
    private String image;
    private int quantity;
    private float price;
    private boolean status;


    public FoodDTO(int foodID, String foodName, Date createDate, String categoryID, String image, int quantity, float price, boolean status) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.createDate = createDate;
        this.categoryID = categoryID;
        this.image = image;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
    }

    public int getFoodID() {
        return foodID;
    }

    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
