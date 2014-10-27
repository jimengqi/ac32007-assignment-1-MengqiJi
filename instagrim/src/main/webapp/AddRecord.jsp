<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Every Picture Has its Story</title>

</head>
<body>
     <center><h1>Add your Record of the Picture</h1></center>
        <div  align="center">
	    <h3>Add Your Picture Story:</h3>
		 <form method="POST" action="/instagrim/AddRecord">
		 <input type="text" size="100" maxlength="100" value="add not more than 100 words" style="height: 100px"  name="text" /><br> 
	     <input type="hidden" value="<%=request.getParameter("picid") %>"  name="picidid">
	     <input type="submit" value="AddRecord" name="addrecord" />
	    </form>  
	    </div> 
</body>
</html>