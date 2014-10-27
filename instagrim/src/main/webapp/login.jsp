<%-- 
    Document   : login.jsp
    Created on : Sep 28, 2014, 12:04:14 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>instagrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />

    </head>
    <body>
        <header>
        <h1>instagrim ! </h1>
        <h2>Your world in Black and White</h2>
        </header>
        <nav>
            <ul>
                
                <li><a href="/instagrim/Images/majed">Sample Images</a></li>
            </ul>
        </nav>
       
        <article>
             <h3>Login</h3>
            <form method="POST"  action="Login">
               <table>
               <tr>
                <td>User Name:
                <input type="text" name="username"></td>
               </tr>
               <tr>
               <td> Password:
               <input type="password" name="password"></td>
               </tr>
               <tr>
               <td>You want to keep this for 1 day <input type="checkbox" name="isUseCookie" checked="checked"></td>
               </tr>
               <tr>
               <td><input type="submit" value="Login"></td>
               </tr>
                
               </table>
                   
             </form>

        </article>
        <footer>
            <ul>
                <li class="footer"><a href="/instagrim">Home</a></li>
            </ul>
        </footer>
    </body>
</html>
