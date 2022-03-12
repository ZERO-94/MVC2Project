/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhtvk.servlet;

import anhtvk.registration.RegistrationDAO;
import anhtvk.registration.RegistrationDTO;
import anhtvk.registration.RegistrationLoginError;
import anhtvk.utils.MVC2ProjectConstants;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kiman
 */
public class LoginServlet extends HttpServlet {

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
        
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties)context.getAttribute("SITEMAPS");
        
        String url = MVC2ProjectConstants.DispatchFeatures.LOGIN_JSP_PAGE;

        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        
        //create Error object
        RegistrationLoginError errors = new RegistrationLoginError();
        
        //create error flag
        boolean errorFlag = false;

        try {
            
            //1. call model
            RegistrationDAO dao = new RegistrationDAO();
            RegistrationDTO result = dao.checkLogin(username, password);

            //2. process result
            if (result != null) {
                url = MVC2ProjectConstants.LoginFeatures.SEARCH_PAGE;

                //check authorization
                HttpSession session = request.getSession(); //<-auto cấp vì login đúng rồi
                session.setAttribute("USERNAME", username);
                session.setAttribute("FULLNAME", result.getFullname());
                session.setAttribute("ROLE", result.isRole());
                
            } else {
                errorFlag = true;
                errors.setInvalidAccountError("Invalid account! Try again");
                url = MVC2ProjectConstants.DispatchFeatures.LOGIN_JSP_PAGE 
                        + "?error=" + errors.getInvalidAccountError();
            }
        } catch (NamingException e) {
            log("LoginServlet _ Naming " + e.getMessage());
        } catch (SQLException e) {
            log("LoginServlet _ SQL " + e.getMessage());
        } finally {
            //This lets the client only knows dispatcher
            response.sendRedirect(url);
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
