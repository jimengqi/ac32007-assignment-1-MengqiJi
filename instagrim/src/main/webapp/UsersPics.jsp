<%-- 
    Document   : UsersPics
    Created on : Sep 24, 2014, 2:52:48 PM
    Author     : Administrator
--%>

<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>instagrim</title>
        <link rel="stylesheet" type="text/css" href="/instagrim/Styles.css" />
    </head>
    <body>
        <header>
        
        <h1>instagrim ! </h1>
        <h2>Your world in Black and White</h2>
        </header>
        
        <nav>
            <ul>
                <li class="nav"><a href="/instagrim/upload.jsp">Upload</a></li>
                <li class="nav"><a href="/instagrim/Images/majed">Sample Images</a></li>
            </ul>
        </nav>
 
        <article>
            <h1>Your Pics</h1>
        <%
            java.util.LinkedList<Pic> lsPics = (java.util.LinkedList<Pic>) request.getAttribute("Pics");
            if (lsPics == null) {
        %>
        <p>No Pictures found</p>
        <%
        } else {
            Iterator<Pic> iterator;
            iterator = lsPics.iterator();
            while (iterator.hasNext()) {
                Pic p = (Pic) iterator.next();

        %>
        <a href="/instagrim/Image/<%=p.getSUUID()%>" ><img src="/instagrim/Thumb/<%=p.getSUUID()%>"></a><br/>
        <a href="/instagrim/UsersPics?picidfordelete=<%=p.getSUUID() %>">Delete</a></br>
        
        <a href="/instagrim/AddRecord.jsp?picid=<%=p.getSUUID()%>">Go To See and Add Record</a>
        <%-- <form method="GET" action="/instagrim/UsersPics">
        <input type="submit" value="Delete">
        <input type="text"   value="<%=p.getSUUID()%>"  name="picidfordelete">
        </form> --%>
       
        <form method="POST" action="/instagrim/NewProfile">
		<input type="submit" value="use as my profile"  >
		<input type="text" value="<%=p.getSUUID()%>" name="picid">
		</form>
        
       <!--  <div  align="left">
	    <h3>Add Your Picture Story:</h3>
		 <form method="POST" action="/instagrim/UsersPics">
		 <input type="text" size="30" maxlength="100" value="add not more than 100 words" 
		 style="height: 100px"  name="text" /><br> 
	     <input type="submit" value="sendRecord" name="addrecord" />
	    </form>  
	    </div>  -->
            
            
         <% }} %>
       
        </article>
        <footer>
            <ul>
                <li class="footer"><a href="/instagrim">Home</a></li>
            </ul>
        </footer>
    </body>
</html>
