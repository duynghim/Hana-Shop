package nghiemtd.util;

import nghiemtd.tbl_orderDetail.OrderDetailDAO;
import tbl_foods.FoodDAO;
import tbl_foods.FoodDTO;

import java.util.ArrayList;
import java.util.List;

public class MyToys {
    public static int convertPage(String stringPage){
        int page = 0;
        if (stringPage == null){
            return page;
        }
        return (Integer.parseInt(stringPage) - 1)  * 5;
    }

    public static float convertMin(String stringMin){
        float min = 0;
        return convertFloat(stringMin, min);
    }
    public static float convertMax(String stringMax){
        float max = Float.MAX_VALUE;
        return convertFloat(stringMax, max);
    }

    private static float convertFloat(String stringMax, float max) {
        try {
            if (stringMax.length() == 0){
                return max;
            }else {
                max = Float.parseFloat(stringMax);
                if (max < 0 || max > Float.MAX_VALUE){
                    return -1;
                }
            }
        }catch (Exception e){
            return -1;
        }
        return max;
    }

    public static List<Integer> listPage(int count){
        int n = (int) Math.ceil((double)count/5);
        System.out.println(n);
        List<Integer> listPage = new ArrayList<>();
        for (int i = 1; i <= n ; i++){
            listPage.add(i);
        }
        return listPage;
    }

    public static List<FoodDTO> checkQuantityOfFood(List<FoodDTO> listFood){
        FoodDAO foodDAO = new FoodDAO();
        List<FoodDTO> listFoodOutOfStock = new ArrayList<>();

        try {
            for (FoodDTO x : listFood){
                int currentQuantity = foodDAO.findFoodByFoodID(x.getFoodID()).getQuantity();
                if (x.getQuantity() > currentQuantity){
                    listFoodOutOfStock.add(x);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
     return listFoodOutOfStock;
    }

    public static void updateQuantityOfFood(List<FoodDTO> listFood){
        FoodDAO foodDAO = new FoodDAO();
        try {
            for (FoodDTO x : listFood){
                foodDAO.updateQuantityOfFoodAfterOrder(x.getFoodID(), x.getQuantity());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void insertOrderDetail(int orderID, List<FoodDTO> listFood){
        FoodDAO foodDAO = new FoodDAO();
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        try {

            for (FoodDTO x : listFood){
                int lastOrderDetailID = orderDetailDAO.getLastOrderDetail() + 1;
                orderDetailDAO.insertOrderDetail(lastOrderDetailID, orderID, x);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
