/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhtvk.registration;

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
public class RegistrationDAO implements Serializable{
    
    private List<RegistrationDTO> accounts;
    
    public RegistrationDTO checkLogin(String username, String password) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        RegistrationDTO result = null;
        
        try {
        //1.Connect DB
            con = DBHelper.makeConnection();
            if(con != null) { //check if connection is closed or server is crashed
                //2. Create SQL String
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Registration "
                        + "Where username = ? "
                        + "AND password = ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. Execute Statement to get result
                rs = stm.executeQuery();
                //5. Process result
                while (rs.next()) {
                    String resultUsername = rs.getString("username");
                    String resultPassword = rs.getString("password");
                    String resultFullname = rs.getString("lastname");
                    boolean resultRole = rs.getBoolean("isAdmin");
                    
                    result = new RegistrationDTO(resultUsername, resultPassword, resultFullname, resultRole);
                    
                }
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

    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }
    
    public void searchLastname(String searchValue) throws SQLException, /*ClassNotFoundException,*/ NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
        //1.Connect DB
            con = DBHelper.makeConnection();
            if(con != null) { //check if connection is closed or server is crashed
                //2. Create SQL String
                String sql = "Select username, password, lastname, isAdmin " 
                        + "From Registration " 
                        + "Where lastname like ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");

                //4. Execute Statement to get result
                rs = stm.executeQuery();
                //5. Process result
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    
                    RegistrationDTO dto = new RegistrationDTO(username, password, fullname, role);
                    
                    if(this.accounts == null) {
                        this.accounts = new ArrayList<>();
                    } //if not create new list
                    
                    this.accounts.add(dto);
                }
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
    }
    
    public boolean deleteAccount(String username) throws SQLException, /*ClassNotFoundException,*/ NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
        //1.Connect DB
            con = DBHelper.makeConnection();
            if(con != null) { //check if connection is closed or server is crashed
                //2. Create SQL String
                String sql = "Delete From Registration "
                        + "Where username = ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4. Execute Statement to get result
                int effectRows = stm.executeUpdate();
                //5. Process result
                if(effectRows > 0) {
                    return true;
                }
                
            }
        } finally {
            if(stm != null) {
                stm.close();
            }
            
            if(con != null) {
                con.close();
            }    
        }
        return false;
    }
    
    public boolean updateRoleAccount(String isAdmin, String username) throws SQLException, /*ClassNotFoundException,*/ NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
        //1.Connect DB
            con = DBHelper.makeConnection();
            if(con != null) { //check if connection is closed or server is crashed
                //2. Create SQL String
                String sql = "Update Registration "
                        + "Set isAdmin = ? "
                        + "Where username = ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, (isAdmin != null));
                stm.setString(2, username);
                //4. Execute Statement to get result
                int effectRows = stm.executeUpdate();
                //5. Process result
                if(effectRows > 0) {
                    return true;
                }
            }
        } finally {
            if(stm != null) {
                stm.close();
            }
            
            if(con != null) {
                con.close();
            }    
        }
        return false;
    }
    
    public boolean updateGeneralInformation(String fullname, String username) throws SQLException, /*ClassNotFoundException,*/ NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
        //1.Connect DB
            con = DBHelper.makeConnection();
            if(con != null) { //check if connection is closed or server is crashed
                //2. Create SQL String
                String sql = "Update Registration "
                        + "Set lastname = ? "
                        + "Where username = ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, fullname);
                stm.setString(2, username);
                //4. Execute Statement to get result
                int effectRows = stm.executeUpdate();
                //5. Process result
                if(effectRows > 0) {
                    return true;
                }
            }
        } finally {
            if(stm != null) {
                stm.close();
            }
            
            if(con != null) {
                con.close();
            }    
        }
        return false;
    }
    
    public boolean changePassword(String password, String username) throws SQLException, /*ClassNotFoundException,*/ NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
        //1.Connect DB
            con = DBHelper.makeConnection();
            if(con != null) { //check if connection is closed or server is crashed
                //2. Create SQL String
                String sql = "Update Registration "
                        + "Set password = ? "
                        + "Where username = ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setString(2, username);
                //4. Execute Statement to get result
                int effectRows = stm.executeUpdate();
                //5. Process result
                if(effectRows > 0) {
                    return true;
                }
            }
        } finally {
            if(stm != null) {
                stm.close();
            }
            
            if(con != null) {
                con.close();
            }    
        }
        return false;
    }
    
    public boolean createNewAccount(RegistrationDTO dto) throws SQLException, /*ClassNotFoundException,*/ NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        
        if(dto == null) {
            return false;
        }
        
        try {
        //1.Connect DB
            con = DBHelper.makeConnection();
            if(con != null) { //check if connection is closed or server is crashed
                //2. Create SQL String
                String sql = "Insert Into Registration("
                        + "username, password, lastname, isAdmin"
                        + ") Values(?, ?, ?, ?)";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getFullname());
                stm.setBoolean(4, dto.isRole());
                //4. Execute Statement to get result
                int effectRows = stm.executeUpdate();
                //5. Process result
                if(effectRows > 0) {
                    return true;
                }
                
            }
        } finally {
            if(stm != null) {
                stm.close();
            }
            
            if(con != null) {
                con.close();
            }    
        }
        return false;
    }
    
    public boolean checkPassword(String username, String password) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
        //1.Connect DB
            con = DBHelper.makeConnection();
            if(con != null) { //check if connection is closed or server is crashed
                //2. Create SQL String
                String sql = "Select username, password " 
                        + "From Registration " 
                        + "Where username = ? AND password = ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);

                //4. Execute Statement to get result
                rs = stm.executeQuery();
                //5. Process result
                if (rs.next()) {
                    return true;
                }
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
        
        return false;
    }
}
