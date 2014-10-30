package uk.ac.dundee.computing.aec.instagrim.models;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;





import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import uk.ac.dundee.computing.aec.instagrim.lib.*;
import uk.ac.dundee.computing.aec.instagrim.models.User;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;

public class PicRecord {
	UUID picid;
    Date addeddate;
	String user;
	String text;
	Cluster cluster;

	
	
   public PicRecord(){
		
	    }
   public void setCluster(Cluster cluster) {
       this.cluster = cluster;
   }
	
	public PicRecord(String user, String text,UUID picid, Date addeddate ){
		this.picid = picid;
		this.addeddate = addeddate;
		this.user = user;
		this.text= text;
	}

	public UUID getPicid() {
		return picid;
	}

	public Date getAddedDate() {
		return addeddate;
	}

	public String getUser() {
		return user;
	}

	public String getText() {
		return text;
	}
	
	 public String getRecordFromDb(UUID picid){
		//LinkedList<PicRecord> picrecordList = new LinkedList<PicRecord>();
		
		Session session = CassandraHosts.getCluster().connect("instagrim");
		PreparedStatement ps = session.prepare("SELECT * FROM picrecords WHERE picid = ? ");
		ResultSet records = session.execute(ps.bind(picid));
		String picrecord=" ";
		 if (records.isExhausted()) {
	            System.out.println("No records returned");
	            return null;
	        } else {
	        	
	            for (Row row : records) {
	             picrecord=row.getString("records");
	               
	            }
	    	
	    }
	        session.close();
	        return picrecord;
}
	
	
	
	public void insertRecords(String user, String text,UUID picid, Date addeddate ){
		/*if(this.picid == null || this.user == null || 
				this.user.getUsername() == null || this.user.getUsername().isEmpty()){
			}*/
		
		try{
			Session session = cluster.connect("instagrim");
			
			PreparedStatement psInsertPicRecord = session.prepare("insert into picrecords (user,records,picid,added_time )"
					+ " VALUES(?,?,?,?);");
			BoundStatement bsInsertPicRecord = new BoundStatement(psInsertPicRecord);
			session.execute(bsInsertPicRecord.bind(user,text,picid,addeddate ));
			session.close();
			
		}catch(Exception e){
			System.err.println("Error in insertRecords "+e);
			
		}
		
	}
}
