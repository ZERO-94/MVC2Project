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
@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})
public class UpdateServlet extends HttpServlet {

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
        
        String url = siteMaps.getProperty(MVC2ProjectConstants.DispatchFeatures.NOT_FOUND_PAGE);
        
        String btAction = request.getParameter("btAction");
        String searchValue = request.getParameter("lastSearchValue");
        HttpSession session = request.getSession(false);
        
        try {
            if(session != null) {
                if(btAction != null) {

                    if(btAction.equals("Update")) {
                        if((boolean)session.getAttribute("ROLE") == true) {
                            url = siteMaps.getProperty(MVC2ProjectConstants.LoginFeatures.UPDATE_ROLE_CONTROLLER);
                        } else {
                            url = siteMaps.getProperty(MVC2ProjectConstants.DispatchFeatures.NOT_FOUND_PAGE);
                        }
                        
                    } else if(btAction.equals("Update account")) {
                        
                        url = siteMaps.getProperty(MVC2ProjectConstants.LoginFeatures.UPDATE_GENERAL_INFORMATION_CONTROLLER);
                        
                    } else if (btAction.equals("Change password")) {
                        
                        url = siteMaps.getProperty(MVC2ProjectConstants.LoginFeatures.UPDATE_PASSWORD_CONTROLLER);
                        
                    }

                } else {
                    url = siteMaps.getProperty(MVC2ProjectConstants.LoginFeatures.UPDATE_ACCOUNT_PAGE);
                }
            } else {
                url = siteMaps.getProperty(MVC2ProjectConstants.DispatchFeatures.ERROR_PAGE);
            }
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
