package nghiemtd.tbl_order;

import java.io.Serializable;
import java.sql.Date;

public class OrderDTO implements Serializable {
    private int orderID;
    private float total;
    private Date createOfDate;
    private String userID;

    public OrderDTO(int orderID, float total, Date createOfDate, String userID) {
        this.orderID = orderID;
        this.total = total;
        this.createOfDate = createOfDate;
        this.userID = userID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Date getCreateOfDate() {
        return createOfDate;
    }

    public void setCreateOfDate(Date createOfDate) {
        this.createOfDate = createOfDate;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
