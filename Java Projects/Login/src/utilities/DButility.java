package utilities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db_connection.DBconnection;

public class DButility {
	
	public DButility()
	{
		
	}
	public void releaseResources(ResultSet rs, Statement st, Connection conn)
	{
		 if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException e) { /* ignored */}
		    }
		    if (st != null) {
		        try {
		        	st.close();
		        } catch (SQLException e) { /* ignored */}
		    }
		    if (conn!= null) {
		        try {
		            conn.close();
		        } catch (SQLException e) { /* ignored */}
	}
		    return;
	}
	public void releaseResources(ResultSet rs, Statement st)
	{
		 if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException e) { /* ignored */}
		        if (st != null) {
			        try {
			        	st.close();
			        } catch (SQLException e) { /* ignored */}
			    }       
		    return;
		 }
	}
		 public void releaseResources(Statement st)
			{
				        if (st != null) {
					        try {
					        	st.close();
					        } catch (SQLException e) { /* ignored */}
					    }       
				    return;
			 }
		 public void releaseResources(ResultSet rs)
			{
				        if (rs != null) {
					        try {
					        	rs.close();
					        } catch (SQLException e) { /* ignored */}
					    }       
				    return;
			 }
	public void closeConnection(Connection conn)
	{
		if (conn!= null) {
	        try {
	            conn.close();
	        } catch (SQLException e) { /* ignored */}
}
	    return;
	}
	
	public void closeCurrentConnection()
	{
		try {
			DBconnection instance = DBconnection.getInstance();
			Connection connection = instance.getConnection();
			closeConnection(connection);
			System.out.println("CONNECTION CLOSED");
		} catch (SQLException e) {
			/* ignored */
		}
	}
	

	

}
