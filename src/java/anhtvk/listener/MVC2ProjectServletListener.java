/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhtvk.listener;

import anhtvk.utils.DBHelper;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author kiman
 */
public class MVC2ProjectServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        try {
            DBHelper.getSiteMaps(context);
        } catch (IOException ex) {
            context.log("MVC2ProjectServletListener _ IO " + ex.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        
        context.removeAttribute("STIEMAPS");
    }
}
