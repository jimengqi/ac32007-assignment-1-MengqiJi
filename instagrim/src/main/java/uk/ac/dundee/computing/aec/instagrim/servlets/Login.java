/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.Cluster;

import java.util.UUID;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.models.User;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    Cluster cluster=null;


    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username=request.getParameter("username");
        String password=request.getParameter("password");
       
        
String[] isUseCookies=request.getParameterValues("isUseCookie");
    	
    	if(isUseCookies!=null && isUseCookies.length>0)
    	{
            Cookie usernameCookie = new Cookie("username", username);
            Cookie passwordCookie = new Cookie("password",password);
    		usernameCookie.setMaxAge(1*24*3600);
            passwordCookie.setMaxAge(1*24*3600);
            response.addCookie(usernameCookie);
    		response.addCookie(passwordCookie);
    	}
    	else
    	{
    		Cookie[] cookies =request.getCookies();
    		if(cookies!=null && cookies.length>0)
    		{
    			for(Cookie c:cookies)
    			{
    				if(c.getName().equals("username") || c.getName().equals("password"))
    				{
    					c.setMaxAge(0);
    					response.addCookie(c);
    				}
    					
    			}
    		}
    	}
       
    	
    	User us=new User();
        us.setCluster(cluster);
        boolean isValid=us.IsValidUser(username, password);
        HttpSession session=request.getSession();
        System.out.println("Session in servlet "+session);
        if (isValid){
            LoggedIn lg= new LoggedIn();
            lg.setLogedin();
            lg.setUsername(username);
           String firstname= us.getFirstname(username);
           String lastname = us.getLastname(username);
           String email =  us.getEmail(username);
           UUID picid= us.getPicid(username);
            //request.setAttribute("LoggedIn", lg);
           lg.setFirstname(firstname);
           lg.setLastname(lastname);
           lg.setEmail(email);
           lg.setPicid(picid);
            
           session.setAttribute("LoggedIn", lg);
            System.out.println("Session in servlet "+session);
            RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
	    rd.forward(request,response);
            
        }else{
            response.sendRedirect("/instagrim/login.jsp");
        }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
