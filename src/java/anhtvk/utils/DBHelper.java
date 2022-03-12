/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhtvk.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

/**
 *
 * @author kiman
 */
public class DBHelper implements Serializable{
    public static Connection makeConnection() throws /*ClassNotFoundException*/ NamingException, SQLException {
        
        Context context = new InitialContext();
        Context tomcatContext = (Context)context.lookup("java:comp/env");
        DataSource ds = (DataSource)tomcatContext.lookup("SE1614");
        Connection con = ds.getConnection();
        
        return con;
        
        
//        Connection con = null;
//        
//        //1. Load Driver
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2. Create Connection String
//        String url = "jdbc:sqlserver://"
//                + "localhost:1433"
//                + ";databaseName=FPTU";
//        //3. Open Connection
//        con = DriverManager.getConnection(url, "sa", "12345678");
//        
//        return con;
    }
    
    public static void getSiteMaps (ServletContext context) throws IOException {
        //get properties file path
        String filePath = context.getInitParameter("SITE_MAP_FILE_PATH");
        // convert properties file into input stream
        InputStream is = null;
        is = context.getResourceAsStream(filePath);
        // create Properties object
        Properties siteMaps = new Properties();
        siteMaps.load(is);
        //set it into attribute of context Scope
        context.setAttribute("SITEMAPS", siteMaps);
    }
}
