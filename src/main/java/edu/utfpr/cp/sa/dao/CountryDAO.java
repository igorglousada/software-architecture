package edu.utfpr.cp.sa.dao;

import edu.utfpr.cp.sa.entity.Country;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import edu.utfpr.cp.sa.util.Conection;


public class CountryDAO{
	private final Connection conn;

	public CountryDAO(){
		this.conn = new Conection().Conector();
	}

	public void insert (Country country) throws Exception{
		String sql = "insert into country (name, acronym, phoneDigits) values(?,?,?)";
		try{

                    try (PreparedStatement stm = this.conn.prepareStatement(sql)) {
                        stm.setString(1, country.getName());
                        stm.setString(2, country.getAcronym());
                        stm.setInt(3, country.getPhoneDigits());
                        
                        stm.execute();
                    }

		}catch (SQLException ex){
			ex.printStackTrace();
			throw new Exception(ex);
		}
	}
	 public ArrayList<Country> listarCountry() {
         String sql = "SELECT cust.name, phone, age, creditLimit " +
                        "count.name " +
                        "FROM customer AS cust INNER JOIN country AS count ON count.name = cust.name";

        try {
            ArrayList<Country> country = new ArrayList<>();
            PreparedStatement pst = this.conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                Country count = new Country();
                count.setName(rs.getString("name"));
                count.setAcronym(rs.getString("acronym")); 
                count.setPhoneDigits(rs.getInt("phoneDigits"));
                country.add(count);
            }
            rs.close();
            pst.close();
          
            return country;
        } catch (Exception e) {
            System.err.println("\n " + e.getCause());
            System.err.println("\n " + e.getMessage());
            throw new RuntimeException(e);              
        }   
    }
}