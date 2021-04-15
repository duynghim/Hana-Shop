/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiemtd.servlet;

import tbl_foods.FoodCreateError;
import tbl_foods.FoodDAO;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.Date;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * @author trndu
 */
@MultipartConfig
@WebServlet(name = "CreateFoodServlet", urlPatterns = {"/CreateFoodServlet"})
public class CreateFoodServlet extends HttpServlet {
    private final String CREATE_ERROR_PAGE = "createNew.jsp";
    private final String CREATE_SUCCESS = "createNew.jsp";


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


        Part filePart = request.getPart("FILE");
        String patchOfImage = null;
        String nameOfImage = Paths.get(filePart.getSubmittedFileName()).toString();
        if (nameOfImage.length() == 0){
            patchOfImage = null;
        }else   {
            patchOfImage = "Image\\" + nameOfImage;
        }
        String pathToWriteImage = "D:\\Java Web\\J3.L.P0013\\web\\Image\\";
        BufferedImage bi = ImageIO.read(filePart.getInputStream());
        ImageIO.write(bi, "png", new File( pathToWriteImage + nameOfImage));
        String foodName = request.getParameter("txtFoodName");
        String stringQuantity = request.getParameter("txtQuantity");
        String stringPrice = request.getParameter("txtPrice");
        int categoryID = Integer.parseInt(request.getParameter("category"));

        String url = CREATE_ERROR_PAGE;
        boolean errFound = false;
        FoodCreateError error = new FoodCreateError();
        try {
            int quantity = 0;
            if (stringQuantity != null) {
                try {
                    quantity = Integer.parseInt(stringQuantity);
                    if (quantity <= 0) {
                        error.setFoodQuantityErr("Quantity must be Integer Number");
                        errFound = true;
                    }
                } catch (Exception e) {
                    error.setFoodQuantityErr("Quantity must be Integer Number");
                    errFound = true;
                }
            } else {
                error.setFoodQuantityErr("You must input quantity of Food");
                errFound = true;
            }

            float price = 0;
            if (stringPrice != null) {
                try {
                    price = Float.parseFloat(stringPrice);
                    if (price <= 0) {
                        error.setFoodPriceErr("Price must be > 0");
                        errFound = true;
                    }
                } catch (Exception e) {
                    error.setFoodPriceErr("Price is a number");
                    errFound = true;
                }
            } else {
                error.setFoodPriceErr("You must input price of food!");
                errFound = true;
            }

            if (foodName.length() == 0) {
                error.setFoodNameLengthErr("You must input name of food");
                errFound = true;
            } else if (foodName.length() < 3 || foodName.length() > 20) {
                error.setFoodNameLengthErr("Food Name 3 - 20 chars");
                errFound = true;
            }

            if (errFound) {
                request.setAttribute("CREATE_ERROR", error);
            } else {
                FoodDAO foodDAO = new FoodDAO();

                long milis = System.currentTimeMillis();
                Date date = new Date(milis);
                foodDAO.insertFood(foodDAO.getLastIDOfFood(), foodName, date, categoryID, quantity, price, patchOfImage);
                url = CREATE_SUCCESS;
            }
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
