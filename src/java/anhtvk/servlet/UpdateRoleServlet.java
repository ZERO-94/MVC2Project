/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhtvk.servlet;

import anhtvk.registration.RegistrationDAO;
import anhtvk.registration.RegistrationUpdateError;
import anhtvk.utils.MVC2ProjectConstants;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kiman
 */
@WebServlet(name = "UpdateRoleServlet", urlPatterns = {"/UpdateRoleServlet"})
public class UpdateRoleServlet extends HttpServlet {

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
        
        String url = siteMaps.getProperty(MVC2ProjectConstants.LoginFeatures.SEARCH_LASTNAME_CONTROLLER);
        
        String password = request.getParameter("txtPassword");
        String isAdmin = request.getParameter("chkAdmin");
        String username = request.getParameter("txtUsername");
        String searchValue = request.getParameter("lastSearchValue");
        RegistrationUpdateError errors = new RegistrationUpdateError();
        boolean foundErr = false;
        boolean result = false;
        
        try {
//            
//            //validate password
//            if(password.trim().length() < 6 || username.trim().length() > 30) {
//                foundErr = true;
//                errors.setInvalidPasswordLength("Update user with username " + username + " failed because password is required from 6 to 30 chars");
//                errors.setLastPasswordInput(password);
//                errors.setLastUpdateUser(username);
//            }
            
            //1. call DAO
            RegistrationDAO dao = new RegistrationDAO();
            result = dao.updateRoleAccount(isAdmin, username);

            if(!result) {//process result
                foundErr = true;
                errors.setInvalidUpdateUser("Invalid user to update!");
            }
            
            if(foundErr) {
                request.setAttribute("ERRORS", errors);   
            }
            
            
            //2. call search again
            url= siteMaps.getProperty(MVC2ProjectConstants.LoginFeatures.SEARCH_LASTNAME_CONTROLLER)
                    +"?txtSearchValue=" + searchValue;
            
        } catch (SQLException ex) {
            log("UpdateRoleServlet _ SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("UpdateRoleServlet _ Naming " + ex.getMessage());
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
