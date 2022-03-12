/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhtvk.servlet;

import anhtvk.registration.RegistrationDAO;
import anhtvk.utils.MVC2ProjectConstants;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
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
@WebServlet(name = "DeleteServlet", urlPatterns = {"/DeleteServlet"})
public class DeleteServlet extends HttpServlet {

    private final String ERROR_PAGE = "blinkError.html";
    
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
        
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties)context.getAttribute("SITEMAPS");
        
        String url = MVC2ProjectConstants.DispatchFeatures.ERROR_PAGE;
        
        String username = request.getParameter("pk");
        String searchValue = request.getParameter("lastSearchValue");
        
        try {
            
            HttpSession session = request.getSession(false);
            
            if(session != null && !session.getAttribute("USERNAME").equals(username)) {
                //1.call DAO
                RegistrationDAO dao = new RegistrationDAO();
                boolean result = dao.deleteAccount(username);
                
                if(!result) {
                    log("DeleteServlet _ NotFound No suitable data to operate");
                }
                
                //2.call Search again using url rewriting
                if(searchValue != null) {
                    url = MVC2ProjectConstants.LoginFeatures.SEARCH_LASTNAME_CONTROLLER
                        +"?txtSearchValue=" + searchValue;
                } else {
                    url = MVC2ProjectConstants.LoginFeatures.SEARCH_LASTNAME_CONTROLLER;
                }
                
            }
        } catch (SQLException e) {
            log("DeleteServlet _ SQL " + e.getMessage());
        } catch (NamingException e) {
            log("DeleteServlet _ Naming " + e.getMessage());
        }finally {
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
