/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.dundee.computing.aec.instagrim.models;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import uk.ac.dundee.computing.aec.instagrim.lib.AeSimpleSHA1;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

/**
 *
 * @author Administrator
 */
public class User {
    Cluster cluster;
    String username;
    String password;
    String firstname;
    String lastname;
    String email;
    UUID picture;
    
    public User(){
        
    }
    
    public String getUsername(){
    	return this.username;
    }
    public void initUserPic() {

		Session session = cluster.connect("instagrim");
		PreparedStatement ps = session
				.prepare("SELECT * FROM userprofiles WHERE login=?");
		ResultSet rsUser = session.execute(ps.bind(this.username));
		if (!rsUser.isExhausted()) {
			Row rowUser = rsUser.one();
			this.firstname = rowUser.getString("first_name");
			this.lastname = rowUser.getString("last_name");
			Set<String> emailSet = rowUser.getSet("email", String.class);
			this.email = emailSet.toString();
			this.picture = rowUser.getUUID("picture");

		}
    }
    public void setProfilePic(String username,String picid){
    	UUID uuid=UUID.fromString(picid);
    	Session session = cluster.connect("instagrim");
		PreparedStatement ps = session
				.prepare("UPDATE userprofiles set picture=? WHERE login=?");
		session.execute(ps.bind(uuid,username));
		
    }
    public UUID getPicid(String username){
    	Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select picture from userprofiles where login =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        UUID picid=null;
        if (rs.isExhausted()) {
            System.out.println("No pictures returned");
            return null;
        } else {
        	
            for (Row row : rs) {
             picid=row.getUUID("picture");
               
            }
    	return picid;
    }
    } 
    private boolean validUserName(String username) {
		String pattern = "^[a-zA-Z0-9_-]{3,15}$";
		if (username.matches(pattern))
			return true;
		else
			return false;

	}
    public void deleteUserPic(){
		Session session = cluster.connect("instagrim");
		PreparedStatement ps1 = session
				.prepare("UPDATE userprofiles SET picture=NULL WHERE username=?");
		session.execute(ps1.bind(this.username));
		this.picture=null;
		
	}
    /*public boolean registerUser(String username,String password) throws Throwable {

		if ( !validUserName(username)){
			throw new Throwable("Username must not be empty and between 3 and  15 Characters long");
		}
		String encodedPassword = " ";
		try {
			encodedPassword = AeSimpleSHA1.SHA1(password);
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException et) {
			System.out.println("Can't check your password");
			throw new Throwable("Please don't use special characters in your password");
		}
		
		return true;
	}*/

    
    public boolean RegisterUser(String username, String Password,String firstname,String lastname,String email){
    	AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        String EncodedPassword=null;
        Set<String> emailset=new HashSet<String>();
        emailset.add(email);
        
        try {
            EncodedPassword= AeSimpleSHA1.SHA1(Password);
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException et){
            System.out.println("Can't check your password");
            
            return false;
        }
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("insert into userprofiles (login,password,first_name,last_name,email) Values(?,?,?,?,?)");
       
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username,EncodedPassword,firstname,lastname,emailset));
        //We are assuming this always works.  Also a transaction would be good here !
        
        return true;
    }
    
    public String getFirstname(String username){
    	Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select first_name from userprofiles where login =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        String firstname=" ";
        if (rs.isExhausted()) {
            System.out.println("No firstname returned");
            return null;
        } else {
        	
            for (Row row : rs) {
             firstname=row.getString("first_name");
               
            }
    	
    }
        return firstname;
   } 
    
    public String getLastname(String username){
    	Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select last_name from userprofiles where login =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        String lastname=" ";
        if (rs.isExhausted()) {
            System.out.println("No lastname returned");
            return null;
        } else {
        	
            for (Row row : rs) {
             lastname=row.getString("last_name");
               
            }
    	
    }
        session.close();
        return lastname;
   }  
    public String getEmail(String username){
    	Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select email from userprofiles where login =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
       
        Set<String> email=null;
        if (rs.isExhausted()) {
            System.out.println("No email returned");
            return null;
        } else {
        	
            for (Row row : rs) {
            email=row.getSet("email",String.class);
               
            }
    	
    }
        session.close();
        return email.toString();
   }  
    public boolean IsValidUser(String username, String Password){
        AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        String EncodedPassword=null;
        try {
            EncodedPassword= sha1handler.SHA1(Password);
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException et){
            System.out.println("Can't check your password");
            return false;
        }
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select password from userprofiles where login =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        if (rs.isExhausted()) {
            System.out.println("No Images returned");
            return false;
        } else {
            for (Row row : rs) {
               
                String StoredPass = row.getString("password");
                if (StoredPass.compareTo(EncodedPassword) == 0)
                    return true;
            }
        }
   
    
    return false;  
    }
       public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    
}
