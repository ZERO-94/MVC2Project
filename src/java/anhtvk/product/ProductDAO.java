/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhtvk.product;

import anhtvk.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author kiman
 */
public class ProductDAO implements Serializable{
    
    public List<ProductDTO> getAllProducts() throws NamingException, SQLException {
        
        List<ProductDTO> products = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            //1. connect database
            con = DBHelper.makeConnection();
            
            //2. create sql string
            String sql = "Select id, name, quantity, price, status "
                    + "From Product "
                    + "Where Product.status = 1";
            
            //3. create Statement
            stm = con.prepareStatement(sql);
            //4. execute statement
            rs = stm.executeQuery();
            
            //5. process result
            while(rs.next()) {
                if(products == null) {
                    products = new ArrayList<>();
                }
                
                String id = rs.getString("id");
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");
                int price = rs.getInt("price");
                boolean status = rs.getBoolean("status");
                
                ProductDTO dto = new ProductDTO(id, name, quantity, price, status);
                products.add(dto);
            }
          
            
        } finally {
            
            if(rs != null) {
                rs.close();
            }
            
            if(stm != null) {
                stm.close();
            }
            
            if(con != null) {
                con.close();
            }
        }
        
        return products;
    }
    
    public ProductDTO getProductById (String id) throws NamingException, SQLException {
        
        ProductDTO result = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            //1. get connect
            con = DBHelper.makeConnection();
        
            //2. create sql string
            String sql = "Select id, name, quantity, price, status "
                    + "From Product "
                    + "Where id = ?";
            
            //3. create statement an execute it
            stm = con.prepareStatement(sql);
            stm.setString(1, id);
            
            rs = stm.executeQuery();
            
            //4. process result
            if(rs.next()) {
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");
                int price = rs.getInt("price");
                boolean status = rs.getBoolean("status");
                
                result = new ProductDTO(id, name, quantity, price, status);
                
            }
            
        } finally {
            
            if(rs != null) {
                rs.close();
            }
            
            if(stm != null) {
                stm.close();
            }
            
            if(con != null) {
                con.close();
            }
        }
        
        
        return result;
    }
    
    public List<ProductDTO> searchProductByName(String searchValue) throws NamingException, SQLException {
        
        List<ProductDTO> products = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            //1. connect database
            con = DBHelper.makeConnection();
            
            //2. create sql string
            String sql = "Select id, name, quantity, price, status "
                    + "From Product "
                    + "Where name like ?";
            
            //3. create Statement
            stm = con.prepareStatement(sql);
            
            //4. execute statement
            stm.setString(1, "%" + searchValue + "%");
            rs = stm.executeQuery();
            
            
            //5. process result
            while(rs.next()) {
                if(products == null) {
                    products = new ArrayList<>();
                }
                
                String id = rs.getString("id");
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");
                int price = rs.getInt("price");
                boolean status = rs.getBoolean("status");
                
                ProductDTO dto = new ProductDTO(id, name, quantity, price, status);
                products.add(dto);
            }
          
            
        } finally {
            
            if(rs != null) {
                rs.close();
            }
            
            if(stm != null) {
                stm.close();
            }
            
            if(con != null) {
                con.close();
            }
        }
        
        return products;
    }
    
}
