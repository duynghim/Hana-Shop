import nghiemtd.tbl_order.OrderDAO;

public class Test {
    public static void main(String[] args) throws Exception {
//        Cart cart = new Cart();
//        FoodDAO dao = new FoodDAO();
//        FoodDTO food1 = dao.findFoodByFoodID(1);
//        food1.setQuantity(3);
//        cart.addToCart(food1);
//
//        FoodDTO food2 = dao.findFoodByFoodID(2);
//        food1.setQuantity(3);
//        cart.addToCart(food2);
//
//        System.out.println(cart.getCart().size());
        OrderDAO orderDAO = new OrderDAO();
        System.out.println(orderDAO.getLastIDOfOrder());
    }
}