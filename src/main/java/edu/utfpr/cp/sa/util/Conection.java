/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utfpr.cp.sa.util;
import java.sql.*;

/**
 *
 * @author igorl_000
 */
public class Conection {
    /*    String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost/aqtsoft?autoReconnect=true&useSSL=false";
        String user = "root";
        String password = "";*/
    public static Connection Conector() {
        java.sql.Connection conection = null;
    String dbURL = "jdbc:mysql://localhost:3306/arquitetura";
    String username = "root";
    String password = "sofia199516oi"; 
    String driver = "com.mysql.jdbc.Driver";
    
    try {
            Class.forName(driver);
            conection = DriverManager.getConnection(dbURL, username, password);
            return conection;
        } catch (Exception e) {
            System.err.println("\n " + e.getCause());
            System.err.println("\n " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    
}
