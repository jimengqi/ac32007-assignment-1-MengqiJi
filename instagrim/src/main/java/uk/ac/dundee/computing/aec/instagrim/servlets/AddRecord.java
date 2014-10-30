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
import javax.servlet.http.HttpSession;
import com.datastax.driver.core.Cluster;
import javax.servlet.ServletRequest;

import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.lib.Convertors;
import uk.ac.dundee.computing.aec.instagrim.models.PicRecord;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;

/**
 * Servlet implementation class AddRecord
 */
@WebServlet("/AddRecord")
public class AddRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 Cluster cluster=null;
	    public void init(ServletConfig config) throws ServletException {
	   
	        cluster = CassandraHosts.getCluster();
	    }
  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddRecord() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*PicRecord picrecord = (PicRecord) request.getSession().getAttribute("PicRecord");
		//PicRecord pic=new PicRecord();
		String picidforrecord=request.getParameter("picidid");
		//java.util.LinkedList<PicRecord> pr=pic.getRecordListFromDb(UUID.fromString(picidforrecord));
		String pr=picrecord.getRecordFromDb(UUID.fromString(picidforrecord));
		PrintWriter out=null;
		out = new PrintWriter(response.getOutputStream());
		if(pr==null){
			out.println("No Record added before!");
		}else{
			
		    	out.println(pr);
		}
		*/
		    
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//PicRecord picrecord = (PicRecord) request.getSession().getAttribute("PicRecord");
		LoggedIn loggedIn=(LoggedIn) request.getSession().getAttribute("LoggedIn");
		
		String picidforrecord=request.getParameter("picidid");
		String content = (String) request.getParameter("text");
        Date addeddate = new Date();
       
       /* String args[] = Convertors.SplitRequestPath(request);*/
		UUID picid = null;
		try{
			picid = UUID.fromString(picidforrecord);
		}catch(ArrayIndexOutOfBoundsException | IllegalArgumentException ex){
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		} 
		 PicRecord picrecord = new PicRecord(loggedIn.getUsername(),content,picid, addeddate);
         picrecord.setCluster(cluster);
         picrecord.insertRecords(loggedIn.getUsername(),content,picid, addeddate);
         HttpSession session=request.getSession();
         session.setAttribute("PicRecord", picrecord);
         RequestDispatcher rd = request.getRequestDispatcher("/AddRecord.jsp");
         rd.forward(request, response);
		
	    }
}


