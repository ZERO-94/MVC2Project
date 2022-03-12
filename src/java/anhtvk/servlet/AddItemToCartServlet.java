/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhtvk.servlet;

import anhtvk.cart.CartItem;
import anhtvk.cart.CartItemAddError;
import anhtvk.cart.CartObject;
import anhtvk.product.ProductDAO;
import anhtvk.product.ProductDTO;
import anhtvk.utils.MVC2ProjectConstants;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
@WebServlet(name = "AddItemToCartServlet", urlPatterns = {"/AddItemToCartServlet"})
public class AddItemToCartServlet extends HttpServlet {

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
        
        String isGetAll = request.getParameter("isGetAll");
        
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties)context.getAttribute("SITEMAPS");
        
        String url = siteMaps.getProperty(MVC2ProjectConstants.DispatchFeatures.SHOPPING_SEARCH_CONTROLLER);
        CartItemAddError error = new CartItemAddError();
        boolean errorFlag = false;
        
        try {
            //1. go to cart place
            HttpSession session = request.getSession();
            //2. get the cart
            CartObject cart = (CartObject)session.getAttribute("CART");
            
            //check if cart is unavailable
            if(cart == null) {
                cart = new CartObject();
            }
            
            //3. pick item
            String id = request.getParameter("cboItemId");
            String name = request.getParameter("cboItemName");
            int price = Integer.parseInt(request.getParameter("cboItemPrice"));
            
            //3.1 check if can't add more
            //get product from db
            ProductDAO dao = new ProductDAO();
            ProductDTO dto = dao.getProductById(id);
            
            if(dto != null) {
                //check if the quantity is equal to the quantity in cart
                Map<String, CartItem> items = cart.getItems();
                if( items == null || items.get(id) == null ||
                        items.get(id).getAmount() < dto.getQuantity()) {//error -> can't add more
                    //4. put item into cart
                    cart.addItemToCart(id, name, price);
                } else {
                    //handle error logic in here ?
                    errorFlag = true;
                    error.setOverQuantityError("You can't add " + name + " into you cart anymore because of over quantity problem");
                } 
            }
            
            //4.1. reassign attribute into scope
            session.setAttribute("CART", cart);
            //5. update view -> call back shoppingServlet
            if(errorFlag) {
                request.setAttribute("ERROR", error);
            }
            
            if(isGetAll != null) {
                url = siteMaps.getProperty(MVC2ProjectConstants.DispatchFeatures.GET_ALL_PRODUCTS_CONTROLLER);
            }

        } catch (NamingException ex) {
            log("AddItemToCartServlet _ Naming " + ex.getMessage());
        } catch (SQLException ex) {
            log("AddItemToCartServlet _ SQL " + ex.getMessage());
        } finally {
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
