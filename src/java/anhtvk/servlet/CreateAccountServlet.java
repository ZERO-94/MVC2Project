/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhtvk.servlet;

import anhtvk.registration.RegistrationCreateError;
import anhtvk.registration.RegistrationDAO;
import anhtvk.registration.RegistrationDTO;
import anhtvk.utils.MVC2ProjectConstants;
import java.io.IOException;
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
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {
    
    private final String ERROR_PAGE = "createNewAccount.jsp";
    private final String LOGIN_PAGE = "login.html";

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
        
        String url = siteMaps.getProperty(MVC2ProjectConstants.DispatchFeatures.CREATE_ACCOUNT_JSP_PAGE);
        
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullname");
        
        RegistrationCreateError errors = new RegistrationCreateError();
        //use flag var to check error
        boolean foundErr = false;
        
        try {
            //1. check constraint
            if(username.trim().length() < 6 || username.trim().length() > 20) {
                foundErr = true;
                errors.setUsernameLengthError("Username is required from 6 to 20 chars");
            }
            if(password.trim().length() < 6 || username.trim().length() > 30) {
                foundErr = true;
                errors.setPasswordLengthError("Password is required from 6 to 30 chars");
            } else {
                if(!confirm.trim().equals(password.trim())) {
                    foundErr = true;
                    errors.setConfirmNotMatched("Confirm password must match password");
                }
            }
            if(fullname.trim().length() < 2 || fullname.trim().length() > 50) {
                foundErr = true;
                errors.setFullnameLengthError("Fullname is required from 2 to 50 chars");
            }
            
            //2. process
            if(foundErr) {
                //2.1 forward errors to create account to notify
                request.setAttribute("CREATEERRORS", errors);
            } else {
                //2.2 call dao
                RegistrationDAO dao = new RegistrationDAO();
                RegistrationDTO dto = new RegistrationDTO(username, password, fullname, false);
                boolean result = dao.createNewAccount(dto);
                
                if(result) {
                    url = siteMaps.getProperty(MVC2ProjectConstants.DispatchFeatures.LOGIN_PAGE);
                }
            }
        } catch (SQLException e) {
            String msg = e.getMessage();
            log("CreateAccountServlet _ SQL " + msg);
            if(msg.contains("duplicate")) {
                errors.setUsernameIsExisted(username + " is existed");
                request.setAttribute("CREATEERRORS", errors);
            }
        } catch (NamingException e) {
            log("CreateAccountServlet _ Naming " + e.getMessage());
        }finally {
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
