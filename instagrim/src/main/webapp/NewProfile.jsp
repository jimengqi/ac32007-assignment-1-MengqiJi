<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Here is your Profile Information!</title>
</head>
<body>
                   <%
                        
                        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                        if (lg != null) {
                            String UserName = lg.getUsername();
                            if (lg.getlogedin()) {
                    %>
                    <ul>
                    <li>Your Username: <%=  lg.getUsername() %></li>
                    <li>Your Firstname:<%=  lg.getFirstname() %></li>
                    <li>Your Lastname: <%=   lg.getLastname() %></li>
                    <li>Your E-mail:   <%=    lg.getEmail()    %></li>
                    <li>Here is your profile picture</li>
                    </ul>
                   <img src="/instagrim/Thumb/<%=lg.getSpicid()%>">
                    <%}
                        }else{%>
                   
                        	
            <ul>          	
                 <li><a href="register.jsp">Register</a></li>
                 <li><a href="login.jsp">Login</a></li>
                
                                        
                            
                    <%}%>
           </ul>
 
                    
        <footer>
            <ul>
                <li class="footer"><a href="/instagrim">Home</a></li>
            </ul>
        </footer>
                   
</body>
</html>