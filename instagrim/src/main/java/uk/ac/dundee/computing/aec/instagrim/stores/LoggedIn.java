/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.dundee.computing.aec.instagrim.stores;

import java.util.UUID;

import uk.ac.dundee.computing.aec.instagrim.models.User;

/**
 *
 * @author Administrator
 */
public class LoggedIn {
    boolean logedin=false;
    String Username=null;
    String Firstname=null;
    String Lastname=null;
    String email=null;
    User user=null;
    UUID picid=null;
    
    public String getSpicid(){
    	if(picid != null ){
        return picid.toString();
    	}else {return "";}
    }
    public UUID getPicid() {
		return picid;
	}

	public void setPicid(UUID picid) {
		this.picid = picid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isLogedin() {
		return logedin;
	}

	public void setLogedin(boolean logedin) {
		this.logedin = logedin;
	}

	public String getFirstname() {
		return Firstname;
	}

	public void setFirstname(String firstname) {
		this.Firstname = firstname;
	}

	public String getLastname() {
		return Lastname;
	}

	public void setLastname(String lastname) {
		this.Lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void LogedIn(){
        
    }
    
    public void setUsername(String name){
        this.Username=name;
    }
    public String getUsername(){
        return Username;
    }
    public void setLogedin(){
        logedin=true;
    }
    public void setLogedout(){
        logedin=false;
    }
    
    public void setLoginState(boolean logedin){
        this.logedin=logedin;
    }
    public boolean getlogedin(){
        return logedin;
    }
}
