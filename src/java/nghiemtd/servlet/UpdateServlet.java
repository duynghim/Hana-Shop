/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiemtd.servlet;

import tbl_foods.FoodDAO;
import tbl_foods.FoodDTO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 *
 * @author trndu
 */
@MultipartConfig
@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})
public class UpdateServlet extends HttpServlet {
    private final String UPDATE_ALL_PAGE = "LoadFoodServlet";
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

        HttpSession session = request.getSession(false);
        String foodID = request.getParameter("txtFoodID");
        String foodName = request.getParameter("txtFoodName");
        String url = UPDATE_ALL_PAGE;

        float foodPrice = 0;
        int quantity = 0;
        try {
            foodPrice = Float.parseFloat(request.getParameter("txtFoodPrice"));
            quantity = Integer.parseInt(request.getParameter("txtQuantity"));
            if (foodPrice <= 0 || quantity <= 0){
                url = ERROR_PAGE;
                session.setAttribute("ERROR", "Price and Quantity is number and > 0");
            }
        }catch (Exception e){
            url = ERROR_PAGE;
            session.setAttribute("ERROR", "Price and Quantity is number and > 0");
        }

        String categoryID = request.getParameter("category");
        Part filePart = request.getPart("FILE");
        String patchOfImage = null;
        String nameOfImage = Paths.get(filePart.getSubmittedFileName()).toString();


        try {
            FoodDAO foodDAO = new FoodDAO();
            if (nameOfImage.length() == 0){
                patchOfImage = foodDAO.findFoodByFoodID(Integer.parseInt(foodID)).getImage();
            }else   {
                patchOfImage = "Image\\" + nameOfImage;
                String pathToWriteImage = "D:\\Java Web\\J3.L.P0013\\web\\Image\\";
                BufferedImage bi = ImageIO.read(filePart.getInputStream());
                ImageIO.write(bi, "png", new File( pathToWriteImage + nameOfImage));
            }
            if (!url.equals(ERROR_PAGE)){
                foodDAO.updateFood(foodID, foodName, foodPrice, quantity, categoryID, patchOfImage);
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
