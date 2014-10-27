package uk.ac.dundee.computing.aec.instagrim.servlets;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datastax.driver.core.Cluster;

import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.lib.Convertors;
import uk.ac.dundee.computing.aec.instagrim.models.*;

/**
 * Servlet implementation class UsersPics
 */
@WebServlet("/UsersPics")
public class UsersPics extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Cluster cluster;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersPics() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String picidid=request.getParameter("picidfordelete");
		LoggedIn loggedIn = (LoggedIn) request.getSession().getAttribute("LoggedIn");
		UUID picidfordelete=null;
		
		picidfordelete=UUID.fromString(picidid);
	    
		PicModel Pic=new PicModel();
		Pic.setCluster(CassandraHosts.getCluster());
	    Pic.deletePic(picidfordelete);
	
	    RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
	}

}
