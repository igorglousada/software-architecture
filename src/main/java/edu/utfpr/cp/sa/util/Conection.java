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
    String dbURL = "jdbc:mysql://localhost:3306/arquitetura";
    String username = "root";
    String password = "IGlousada171";   
    
    public void insert(){
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
     
        // code to execute SQL queries goes here...
            String sql = "INSERT INTO customer (name, phone, age, creditLimit, country) VALUES (?, ?, ?, ?, ?)";
 
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "bill");
            statement.setString(2, "secretpass");
            statement.setInt(3, 12);
            statement.setDouble(4, 44);
            statement.setString(5, "bla bla bla");

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
