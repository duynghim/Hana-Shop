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
@WebServlet(name = "SearchPriceServlet", urlPatterns = {"/SearchPriceServlet"})
public class SearchPriceServlet extends HttpServlet {
    private final String SHOW_SEARCH_RESULT = "searchAdmin.jsp";
    private final String ERROR_PAGE = "errorFind.jsp";

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
        String url = SHOW_SEARCH_RESULT;

        HttpSession session = request.getSession(false);
        String stringMin = request.getParameter("txtMinPrice");
        String stringMax = request.getParameter("txtMaxPrice");
        String stringPage = request.getParameter("page");

        try{
            float min, max;
            if (stringMin == null){
                min = (Float) session.getAttribute("MIN_VALUE");
            }else {
                min = MyToys.convertMin(stringMin);
            }
            if (stringMax == null){
                max = (Float) session.getAttribute("MAX_VALUE");
            }else {
                max = MyToys.convertMax(stringMax);
            }

            if (min == -1 || max == -1 || min > max){
                session.setAttribute("ERROR", "Min and max is a number and >0 or Min < Max");
                url = ERROR_PAGE;
            }

            if (min != 0 || max != Float.MAX_VALUE){
                int page = MyToys.convertPage(stringPage);
                FoodDAO dao = new FoodDAO();
                List<FoodDTO> listFood = dao.searchByPrice(min, max, page);
                List<Integer> listPage = MyToys.listPage(dao.countSearchPrice(min, max));
                session.setAttribute("MIN_VALUE", min);
                session.setAttribute("MAX_VALUE", max);
                session.setAttribute("LIST_FOOD", listFood);
                session.setAttribute("LIST_TYPE", "price");
                session.setAttribute("PAGE", listPage);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
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
