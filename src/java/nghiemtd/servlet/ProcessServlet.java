/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiemtd.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author trndu
 */
@MultipartConfig
public class ProcessServlet extends HttpServlet {
    private final String LOGIN_PAGE = "login.html";
    private final String LOGOUT_SERVLET = "LogoutServlet";
    private final String SEARCH_BY_NAME = "SearchNameServlet";
    private final String SEARCH_BY_PRICE = "SearchPriceServlet";
    private final String SEARCH_BY_CATEGORY = "SearchCategoryServlet";
    private final String CREATE_NEW_FOOD_PAGE = "createNew.jsp";
    private final String CREATE_NEW_FOOD = "CreateFoodServlet";
    private final String LOAD_FOOD = "LoadFoodServlet";
    private final String LOGIN_SERVLET = "LoginServlet";
    private final String ADD_TO_CART = "AddFoodToCartServlet";
    private final String UPDATE_FOOD = "UpdateServlet";
    private final String ORDER_SERVLET = "OrderServlet";
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
        try {
            String button = request.getParameter("btAction");
            String url = LOAD_FOOD;

            if (button == null){

            }else if (button.equals("Login")){
                url = LOGIN_PAGE;
            }else if (button.equals("Logout")){
                url = LOGOUT_SERVLET;
            }else if (button.equals("SearchByName")){
                url = SEARCH_BY_NAME;
            }else if (button.equals("SearchByPrice")){
                url = SEARCH_BY_PRICE;
            }else if (button.equals("SearchByCategory")){
                url = SEARCH_BY_CATEGORY;
            }else if (button.equals("PageAll")){
                url = LOAD_FOOD;
            }else if (button.equals("PagePrice")){
                url = SEARCH_BY_PRICE;
            }else if (button.equals("PageName")){
                url = SEARCH_BY_NAME;
            }else if (button.equals("PageCategory")){
                url = SEARCH_BY_CATEGORY;
            }else if (button.equals("New")){
                url = CREATE_NEW_FOOD_PAGE;
            }else if (button.equals("Create")){
                url = CREATE_NEW_FOOD;
            }else if (button.equals("SignIn")){
                url = LOGIN_SERVLET;
            }else if (button.equals("Add")){
                url = ADD_TO_CART;
            }else if (button.equals("Update")){
                url = UPDATE_FOOD;
            }else if (button.equals("Order")){
                url = ORDER_SERVLET;
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } finally {
            out.close();
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
