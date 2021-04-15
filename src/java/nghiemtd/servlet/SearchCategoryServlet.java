/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiemtd.servlet;

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
@WebServlet(name = "SearchCategoryServlet", urlPatterns = {"/SearchCategoryServlet"})
public class SearchCategoryServlet extends HttpServlet {
    private final String SHOW_SEARCH_RESULT = "searchAdmin.jsp";

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

        HttpSession session = request.getSession(false);
        String stringPage = request.getParameter("page");
        String stringCategoryID = request.getParameter("category");
        Integer searchValue = (Integer) session.getAttribute("SEARCH_CATEGORY");
        try {
            int categoryID;
            int page = MyToys.convertPage(stringPage);
            if (stringCategoryID == null){
                categoryID = searchValue;
            }else {
                categoryID = Integer.parseInt(stringCategoryID);
            }
            FoodDAO foodDAO = new FoodDAO();
            List<FoodDTO> listFood = foodDAO.searchFoodByCategory(categoryID, page);
            List<Integer> listPage = MyToys.listPage(foodDAO.countByCategory(categoryID));
            session.setAttribute("SEARCH_CATEGORY", categoryID);
            session.setAttribute("LIST_FOOD", listFood);
            session.setAttribute("LIST_TYPE", "category");
            session.setAttribute("LIST_PAGE", listPage);
            session.setAttribute("PAGE", listPage);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            RequestDispatcher rd = request.getRequestDispatcher(SHOW_SEARCH_RESULT);
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
