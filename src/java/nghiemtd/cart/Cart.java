package nghiemtd.cart;

import tbl_foods.FoodDTO;

import java.io.Serializable;
import java.util.HashMap;

public class Cart implements Serializable {
    private HashMap<Integer, FoodDTO> cart;
    private String customerID;

    public Cart() {
    }

    public Cart(HashMap<Integer, FoodDTO> cart, String customerID) {
        this.cart = new HashMap<>();
        this.customerID = customerID;
    }

    public HashMap<Integer, FoodDTO> getCart() {
        return cart;
    }

    public void setCart(HashMap<Integer, FoodDTO> cart) {
        this.cart = cart;
    }

    public String getCustomerName() {
        return customerID;
    }

    public void setCustomerName(int customerName) {
        this.customerID = customerID;
    }

    public Cart(String customerID) {
        this.customerID = customerID;
        this.cart = new HashMap<>();
    }

    public void addToCart(FoodDTO dto) throws Exception{
        if (this.cart.containsKey(dto.getFoodID())){
            int newQuantity = this.cart.get(dto.getFoodID()).getQuantity() + 1;
            this.cart.get(dto.getFoodID()).setQuantity(newQuantity);
        }else {
            this.cart.put(dto.getFoodID(), dto);
        }
    }

    public void remove(Integer id) throws Exception{
        this.cart.remove(id);
    }

    public float getToTal() throws Exception{
        float result = 0;
        for (FoodDTO dto : this.cart.values()){
            result += dto.getQuantity() * dto.getPrice();
        }
        return result;
    }

    public void updateCart(Integer id, int quantity) throws Exception{
        if (this.cart.containsKey(id)){
            this.cart.get(id).setQuantity(quantity);
        }
    }
}
