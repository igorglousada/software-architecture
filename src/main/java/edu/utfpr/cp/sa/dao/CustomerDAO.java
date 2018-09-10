package edu.utfpr.cp.sa.dao;
import edu.utfpr.cp.sa.entity.Customer;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.ArrayList;
import edu.utfpr.cp.sa.util.Conection;



public class CustomerDAO {
    private final Connection conn;
    private PreparedStatement ps;

    public CustomerDAO(){
        this.conn= new Conection().Conector();
    }
         public void insert(Customer cust){
         String sql = "INSERT INTO customer (name, phone, age, creditLimit, country) VALUES (?, ?, ?, ?, ?)";
        try{
            PreparedStatement pst = this.conn.prepareStatement(sql);
            pst.setString(1, cust.getName());
            pst.setString(2, cust.getPhone());
            pst.setInt(3, cust.getAge());
            pst.setDouble(4, cust.getCreditLimit());
            pst.setString(5, cust.getCountry().getName());
            
            pst.execute();  
            pst.close();
            
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");

        } catch (Exception e) {
            System.err.println("\nCAUSA: " + e.getCause());
            System.err.println("\nMENSAGEM " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente!");
        }
    }
    
    public ArrayList<Customer> listarCustomer() {
         String sql = "SELECT cust.name, phone, age, creditLimit " +
                        "count.name " +
                        "FROM customer AS cust INNER JOIN country AS count ON count.name = cust.name";

        try {
            ArrayList<Customer> customer = new ArrayList<>();
            PreparedStatement pst = this.conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                Customer cust = new Customer();
                cust.setName(rs.getString("name"));
                cust.setPhone(rs.getString("phone")); 
                cust.setAge(rs.getInt("age"));  
                cust.getCountry().setName(rs.getString("name"));
                cust.setCreditLimit(rs.getInt("creditLimit"));
                customer.add(cust);
            }
            rs.close();
            pst.close();
          
            return customer;
        } catch (Exception e) {
            System.err.println("\n " + e.getCause());
            System.err.println("\n " + e.getMessage());
            throw new RuntimeException(e);              
        }   
    }
    
}
