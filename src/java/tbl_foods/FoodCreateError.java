package tbl_foods;

import java.io.Serializable;

public class FoodCreateError implements Serializable {
    private String foodNameLengthErr;
    private String foodQuantityErr;
    private String foodPriceErr;

    public String getFoodNameLengthErr() {
        return foodNameLengthErr;
    }

    public void setFoodNameLengthErr(String foodNameLengthErr) {
        this.foodNameLengthErr = foodNameLengthErr;
    }

    public String getFoodQuantityErr() {
        return foodQuantityErr;
    }

    public void setFoodQuantityErr(String foodQuantityErr) {
        this.foodQuantityErr = foodQuantityErr;
    }

    public String getFoodPriceErr() {
        return foodPriceErr;
    }

    public void setFoodPriceErr(String foodPriceErr) {
        this.foodPriceErr = foodPriceErr;
    }
}
