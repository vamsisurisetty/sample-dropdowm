package com.vtalent.dropdown;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryStateDao {
	 Connection con;
	    PreparedStatement pst;
	    String query;
	    ResultSet rs;
	    
	    public CountryStateDao(Connection con) {
	        this.con = con;
	    }
	    
	    public List<Country> getAllCountry(){
	    	List<Country> list  = new ArrayList<>();
	        try{
	            query = "select * from country";
	            pst = this.con.prepareStatement(query);
	            rs = pst.executeQuery();
	            while(rs.next()){
	                Country country = new Country();
	                country.setId(rs.getInt("c_id"));
	                country.setName(rs.getString("c_name"));
	                list.add(country);
	            }
	        }catch(SQLException e){
	            e.printStackTrace();
	        }
	        return list;
	    }
	    public List<State> getStateByCountryId(int countryId){
	        List<State> list = new ArrayList<>();
	        try{
	            query = "select * from state where c_id=?";
	            
	            pst = this.con.prepareStatement(query);
	            pst.setInt(1, countryId);
	            rs = pst.executeQuery();
	            while(rs.next()){
	                State state = new State();
	                state.setId(rs.getInt("id"));
	                
	                state.setCountryId(rs.getInt("c_id"));
	                state.setName(rs.getString("name"));
	                list.add(state);
	            }
	        }catch(SQLException e){
	            e.printStackTrace();
	        } 
	        return list;
	    }
	    public List<City> getCityByStateId(int stateId){
	        List<City> list = new ArrayList<>();
	        try{
	            query = "select * from city where s_id=?";
	            pst = this.con.prepareStatement(query);
	            pst.setInt(1, stateId);
	            rs = pst.executeQuery();
	            while(rs.next()){
	                City city = new City();
	                city.setId(rs.getInt("id"));
	                city.setCountryId(rs.getInt("c_id"));
	                city.setStateId(rs.getInt("s_id"));
	                city.setName(rs.getString("name"));
	                list.add(city);
	            }
	        }catch(SQLException e){
	            e.printStackTrace();
	        }
	        return list;
	    }
}


   
    

