/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiemtd.servlet;

import nghiemtd.tbl_category.CategoryDAO;
import nghiemtd.tbl_category.CategoryDTO;
import nghiemtd.util.MyToys;
import tbl_foods.FoodDAO;
import tbl_foods.FoodDTO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author trndu
 */
@MultipartConfig
@WebServlet(name = "LoadFoodServlet", urlPatterns = {"/LoadFoodServlet"})
public class LoadFoodServlet extends HttpServlet {
    final private String SEARCH_PAGE = "searchAdmin.jsp";


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
        String stringPage = request.getParameter("page");
        int page = MyToys.convertPage(stringPage);
        try {
            HttpSession session = request.getSession();

            FoodDAO foodDAO = new FoodDAO();
            List<Integer> listPage = MyToys.listPage(foodDAO.countSearchAll());
            List<FoodDTO> listFood = foodDAO.getAllFoodPage1(page);

            CategoryDAO categoryDAO = new CategoryDAO();
            List<CategoryDTO> listCategory = categoryDAO.getCategoryList();

            session.setAttribute("LIST_FOOD", listFood);
            session.setAttribute("LIST_CATEGORY", listCategory);
            session.setAttribute("PAGE", listPage);
            session.setAttribute("LIST_TYPE", "all");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            RequestDispatcher rd = request.getRequestDispatcher(SEARCH_PAGE);
            rd.forward(request, response);
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
