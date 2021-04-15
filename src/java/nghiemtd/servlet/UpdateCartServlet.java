/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiemtd.servlet;

import nghiemtd.cart.Cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author trndu
 */
@WebServlet(name = "UpdateCartServlet", urlPatterns = {"/UpdateCartServlet"})
public class UpdateCartServlet extends HttpServlet {
    private final String ERROR_PAGE = "errorCart.jsp";
    private final String SUCCESS = "viewCart.jsp";


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
        String url = SUCCESS;

        try {
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("USER_CART");

            String button = request.getParameter("btAction");
            if (button.equals("RemoveCart")) {
                String[] listRemoveFoodID = request.getParameterValues("chkRemove");
                if (listRemoveFoodID != null){
                    int[] listFoodID = new int[listRemoveFoodID.length];
                    for (int i = 0; i < listRemoveFoodID.length; i++) {
                        listFoodID[i] = Integer.parseInt(listRemoveFoodID[i]);
                    }
                    for (int x : listFoodID) {
                        cart.remove(x);
                    }
                }
            } else if (button.equals("UpdateCart")) {
                String[] listUpdateFoodID = request.getParameterValues("txtFoodID");
                int[] listFoodID = new int[listUpdateFoodID.length];

                for (int i = 0; i < listUpdateFoodID.length; i++) {
                    listFoodID[i] = Integer.parseInt(listUpdateFoodID[i]);
                }
                String[] listQuantity = request.getParameterValues("txtQuantity");
                try {
                    for (int i = 0; i < listFoodID.length; i++) {
                        int quantity = Integer.parseInt(listQuantity[i]);
                        if (quantity <= 0){
                            url = ERROR_PAGE;
                            request.setAttribute("ERROR", "Quantity must be > 0");
                        }
                        cart.updateCart(listFoodID[i], Integer.parseInt(listQuantity[i]));
                    }
                }catch (Exception e){
                    request.setAttribute("ERROR", "Quantity must be a number");
                    url = ERROR_PAGE;
                }
            }
            session.setAttribute("USER_CART", cart);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
