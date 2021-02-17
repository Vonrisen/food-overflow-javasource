package utilities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DButility {
	
	public DButility()
	{
		
	}
		public void closeStatement(Statement st)
			{
				        if (st != null) {
					        try {
					        	st.close();
					        } catch (SQLException e) { 
					        	JOptionPane.showMessageDialog(null, "Errore. Contattare l' amministratore","Errore",JOptionPane.ERROR_MESSAGE);
							System.exit(-1);}
					    }       
				    return;
			 }
		 public void closeResultSet(ResultSet rs)
			{
				        if (rs != null) {
					        try {
					        	rs.close();
					        } catch (SQLException e) { 
					        	JOptionPane.showMessageDialog(null, "Errore. Contattare l' amministratore","Errore",JOptionPane.ERROR_MESSAGE);
								System.exit(-1);}
					    }       
				    return;
			 }
	public void closeConnection(Connection conn)
	{
		if (conn!= null) {
	        try {
	            conn.close();
	        } catch (SQLException e) {
	        	JOptionPane.showMessageDialog(null, "Errore. Contattare l' amministratore","Errore",JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
			}
}
	    return;
	}

}
