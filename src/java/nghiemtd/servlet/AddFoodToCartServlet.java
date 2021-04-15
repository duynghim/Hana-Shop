/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiemtd.servlet;

import nghiemtd.cart.Cart;
import nghiemtd.tbl_user.UserDTO;
import tbl_foods.FoodDAO;
import tbl_foods.FoodDTO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author trndu
 */
@MultipartConfig
@WebServlet(name = "AddFoodToCartServlet", urlPatterns = {"/AddFoodToCartServlet"})
public class AddFoodToCartServlet extends HttpServlet {
    private final String LOGIN_PAGE = "login.html";
    private final String  ADD_SUCCESS = "searchAdmin.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = ADD_SUCCESS;

        try {
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("USER");
            if (user == null) {
                url = LOGIN_PAGE;
            } else {
                Cart cart = (Cart) session.getAttribute("USER_CART");
                if (cart == null) {
                    cart = new Cart(user.getFullname());
                }
                int foodID = Integer.parseInt(request.getParameter("txtFoodID"));
                int quantity = 1;

                FoodDAO foodDAO = new FoodDAO();
                FoodDTO dto = foodDAO.findFoodByFoodID(foodID);
                dto.setQuantity(quantity);
                cart.addToCart(dto);
                session.setAttribute("USER_CART", cart);

                @SuppressWarnings("unchecked")
                List<FoodDTO> listFood = (List<FoodDTO>) session.getAttribute("LIST_FOOD");

                session.setAttribute("LIST_FOOD", listFood);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            response.sendRedirect(url);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
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
