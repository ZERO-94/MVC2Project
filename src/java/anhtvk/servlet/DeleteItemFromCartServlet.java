/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhtvk.servlet;

import anhtvk.cart.CartItem;
import anhtvk.cart.CartObject;
import anhtvk.utils.MVC2ProjectConstants;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kiman
 */
@WebServlet(name = "DeleteItemFromCartServlet", urlPatterns = {"/DeleteItemFromCartServlet"})
public class DeleteItemFromCartServlet extends HttpServlet {

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
        
        try {
            //1. Cust goes to his/her cart place
            HttpSession session = request.getSession(false);
            if(session != null) {
                //2. Cust gets his/her cart
                CartObject cart = (CartObject) session.getAttribute("CART");
                if(cart != null) {
                    //3. Cust gets all items
                    Map<String, CartItem> items = cart.getItems();
                    if(items != null) {
                        //4. Get all selected items
                        String[] selectedItem = request.getParameterValues("chkItem");
                        if(selectedItem != null) {
                            //5. remove items from cart
                            for(String itemId : selectedItem) {
                                boolean removeResult = cart.removeItemFromCart(itemId);
                                if(!removeResult) {
                                    log("DeleteItemFromCartServlet _ NotFound " + itemId + " is invalid data to delete");
                                }
                            }
                            session.setAttribute("CART", cart);
                        }
                    }//items have value
                }
            }//session existed
            //6. call View Cart function again
            
        } finally {
            response.sendRedirect(MVC2ProjectConstants.CartFeatures.SHOW_CART_PAGE);
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
