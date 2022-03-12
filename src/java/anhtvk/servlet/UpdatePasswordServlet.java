/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhtvk.servlet;

import anhtvk.registration.RegistrationChangePasswordError;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author kiman
 */
@WebServlet(name = "UpdatePasswordServlet", urlPatterns = {"/UpdatePasswordServlet"})
public class UpdatePasswordServlet extends HttpServlet {

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
        
        String url = siteMaps.getProperty(MVC2ProjectConstants.DispatchFeatures.ERROR_PAGE);
        
        String oldPassword = request.getParameter("txtOldPassword");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String username = request.getParameter("txtUsername");
        
        RegistrationChangePasswordError errors = new RegistrationChangePasswordError();
        HttpSession session = request.getSession(false);
        boolean foundErr = false;
        
        try {
            if(session != null) {
                
                if(password.trim().length() < 6 || username.trim().length() > 30) {
                foundErr = true;
                errors.setPasswordLengthError("Password is required from 6 to 30 chars");
                } else {
                    if(!confirm.trim().equals(password.trim())) {
                        foundErr = true;
                        errors.setConfirmNotMatched("Confirm password must match password");
                    }
                }
                
                if(!foundErr) {
                    //call dao to check old passowrd
                    RegistrationDAO dao = new RegistrationDAO();
                    
                    boolean resultCheckOldPassword = dao.checkPassword(username, oldPassword);
                    if(!resultCheckOldPassword) {
                        foundErr = true;
                        errors.setInvalidOldPassword("Invalid old password!");
                    }
                    
                }

                if(foundErr) {
                    request.setAttribute("CHANGEPASSWORDERRORS", errors);
                    
                } else {
                    //2.2 call dao
                    RegistrationDAO dao = new RegistrationDAO();
                    boolean result = dao.changePassword(password, username);

                    if(!result) {
                        log("UpdatePasswordServlet _ NOTFOUND Invalid data to operate");
                    }
                }

                //2. call search again
                url= siteMaps.getProperty(MVC2ProjectConstants.LoginFeatures.UPDATE_ACCOUNT_PAGE);
            }
        } catch (SQLException ex) {
            log("UpdatePasswordServlet _ SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("UpdatePasswordServlet _ Naming " + ex.getMessage());
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
