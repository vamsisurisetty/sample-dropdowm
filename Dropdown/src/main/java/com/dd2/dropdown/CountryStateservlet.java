package com.vtalent.dropdown;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class CountryStateservlet extends HttpServlet {

	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
			
	        try (PrintWriter out = response.getWriter()) {
	        	CountryStateDao csd = new CountryStateDao(DBCon.getCon());
	            String op = request.getParameter("operation");
	                if (op.equals("country")) {
	                List<Country> clist = csd.getAllCountry();
	                System.out.println(clist);
	                Gson json = new Gson();
	                String countryList = json.toJson(clist);
	                response.setContentType("text/html"); 
	                response.getWriter().write(countryList);
	            }

	            if (op.equals("state")) {
	                int id = Integer.parseInt(request.getParameter("id"));
	                List<State> slist = csd.getStateByCountryId(id);
	                Gson json = new Gson();
	                String countryList = json.toJson(slist);
	                response.setContentType("text/html"); 
	                response.getWriter().write(countryList);
	            }

	            if (op.equals("city")) {
	                int id = Integer.parseInt(request.getParameter("id"));
	                List<City> citylist = csd.getCityByStateId(id);
	                Gson json = new Gson();
	                String countryList = json.toJson(citylist);
	                response.setContentType("text/html"); 
	                response.getWriter().write(countryList);
	            }
	        }
	    }
	}


