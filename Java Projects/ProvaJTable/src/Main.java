import java.awt.EventQueue;
import java.sql.Connection;

public class Main {

	public static void main(String[] args) {
		

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DBconnection dbconn = DBconnection.getInstance();
			        Connection connection = dbconn.getConnection();
					Login loginFrame = new Login(connection);
					loginFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
