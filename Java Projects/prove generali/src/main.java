import java.awt.EventQueue;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class main {

	public static void main(String[] args) {
		

		
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AdminFrame frame = new AdminFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProvaFor frame = new ProvaFor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
