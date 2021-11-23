/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;


import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author Camilo
 */
public class connection {
    
    static String url = "jdbc:mysql://localhost:3306/techintdb?useUnicode=yes&characterEncoding=UTF-8";
    static Connection conn = null;
    static String user="root";
    static String password="";
    
    public static Connection getConnection(){
    
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(url,user,password);
            
        } catch (Exception e) {
            System.out.println("Error :"+e);
        }
        
        return conn;
        
    }
    
}
