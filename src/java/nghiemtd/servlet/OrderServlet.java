/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiemtd.servlet;

import nghiemtd.cart.Cart;
import nghiemtd.tbl_order.OrderDAO;
import nghiemtd.tbl_order.OrderDTO;
import nghiemtd.tbl_orderDetail.OrderDetailDAO;
import nghiemtd.tbl_user.UserDTO;
import tbl_foods.FoodDTO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import static nghiemtd.util.MyToys.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static nghiemtd.util.MyToys.*;

/**
 *
 * @author trndu
 */
@WebServlet(name = "OrderServlet", urlPatterns = {"/OrderServlet"})
public class OrderServlet extends HttpServlet {
    private final String SHOPPING_PAGE = "LoadFoodServlet";
    private final String ERROR_PAGE = "viewCart.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = SHOPPING_PAGE;
        try  {
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("USER_CART");
            UserDTO user = (UserDTO) session.getAttribute("USER");
            List<FoodDTO> listFoodInCart = new ArrayList<FoodDTO>(cart.getCart().values());

            List<FoodDTO> listFoodOutOfStock = checkQuantityOfFood(listFoodInCart);

            if (listFoodOutOfStock.size() > 0){
                url = ERROR_PAGE;
                request.setAttribute("ERROR", listFoodOutOfStock);
            }else {
                OrderDAO orderDAO = new OrderDAO();
                int lastID = orderDAO.getLastIDOfOrder() + 1;
                long milis = System.currentTimeMillis();
                Date date = new Date(milis);
                OrderDTO dto = new OrderDTO(lastID, cart.getToTal(), date, user.getUserID());
                orderDAO.insertNewOrder(dto);
                insertOrderDetail(lastID, listFoodInCart);
                updateQuantityOfFood(listFoodInCart);
                session.setAttribute("USER_CART", null);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
