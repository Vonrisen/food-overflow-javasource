package utilities;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import db_connection.DBconnection;

public class DButility {
	
	public DButility()
	{
		
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
	            System.out.println("conn chiusa");
	        } catch (SQLException e) { /* ignored */}
}
	    return;
	}

}
