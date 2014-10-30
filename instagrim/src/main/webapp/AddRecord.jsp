<%@page import="java.util.UUID"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<%@ page import="uk.ac.dundee.computing.aec.instagrim.models.*" %>
<%@ page import="java.util.UUID" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Every Picture Has its Story</title>

 <%PicRecord picrecord = (PicRecord) session.getAttribute("PicRecord");
  UUID picidforrecord=picrecord.getPicid();
   String pr=picrecord.getRecordFromDb(picidforrecord);
   if(pr==null){%>
	No Record added before!
	<%}else{%>
       <%=pr%>		
	    	
<%}%> 
</head>
<body>
     <div align="left"><img src="/instagrim/Thumb/<%=request.getParameter("picid")%>"></div>
     <div align="right" style="color:#00FF00" ><h1>your Record of the Picture</h1></div>
        <div  align="right">
	    <h3>Add Your Picture Story:</h3>
		 <form method="POST" action="/instagrim/AddRecord">
		 <input type="text" size="50" maxlength="50" value="add not more than 100 words" style="height: 100px"  name="text" /><br> 
	     <input type="hidden" value="<%=request.getParameter("picid") %>"  name="picidid">
	     <input type="submit" value="AddRecord" name="addrecord" />
	    </form>  
	    </div> 
</body>
</html>