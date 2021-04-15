package nghiemtd.tbl_orderDetail;

import java.io.Serializable;

public class OrderDetailDTO implements Serializable {
    private int orderDetailID;
    private int orderID;
    private int foodID;
    private String foodName;
    private int quantity;
    private float price;

    public OrderDetailDTO(int orderDetailID, int orderID, int foodID, String foodName, int quantity, float price) {
        this.orderDetailID = orderDetailID;
        this.orderID = orderID;
        this.foodID = foodID;
        this.foodName = foodName;
        this.quantity = quantity;
        this.price = price;
    }

    public int getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(int orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
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
}
