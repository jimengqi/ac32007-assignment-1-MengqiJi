package uk.ac.dundee.computing.aec.instagrim.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.models.User;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;

import com.datastax.driver.core.Cluster;

/**
 * Servlet implementation class NewProfile
 */
@WebServlet("/NewProfile")
public class NewProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Cluster cluster; 
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewProfile() {
        super();
       

    }
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String picid=request.getParameter("picid");
		LoggedIn loggedIn = (LoggedIn) request.getSession().getAttribute("LoggedIn");
		
	    User user=new User();
	    user.setCluster(CassandraHosts.getCluster());
	    user.setProfilePic(loggedIn.getUsername(), picid);
	    loggedIn.setPicid(UUID.fromString(picid));
	    RequestDispatcher rd=request.getRequestDispatcher("NewProfile.jsp");
	    rd.forward(request,response);
	}
	
}
