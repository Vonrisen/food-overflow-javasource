package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class ComplexFrame extends JFrame {

	protected Dimension screen_dim = Toolkit.getDefaultToolkit().getScreenSize();
	protected Dimension long_dim_of_textfield;
	protected Dimension short_dim_of_textfield;
	protected Dimension button_size;
	protected Dimension west_east_size;
	protected Dimension north_south_size;

	protected ImageIcon table_title;
	protected ImageIcon delete_inactiveIMG;
	protected ImageIcon delete_activeIMG;
	protected ImageIcon update_inactiveIMG;
	protected ImageIcon update_activeIMG;
	protected ImageIcon insert_inactiveIMG;
	protected ImageIcon insert_activeIMG;
	protected ImageIcon go_back_inactiveIMG;
	protected ImageIcon go_back_activeIMG;

	protected JPanel west_panel;
	protected JPanel east_panel;
	protected JPanel north_panel;
	protected JPanel south_panel;
	protected JPanel center_panel;
	protected JPanel sql_panel;
	protected JPanel attributes_panel;
	protected JPanel buttons_panel;

	protected JTable table;
	protected JScrollPane scroll_pane;

	protected JButton insert_sqlJB;
	protected JButton update_sqlJB;
	protected JButton delete_sqlJB;
	protected JButton go_backJB;

	protected JLabel table_titleLB;
	protected JLabel label;

	protected DefaultTableModel model;

	protected Color background_color = new Color(0xf3ecd7);

	// Costruttore
	public ComplexFrame() {

		initialize();
		frameSetup();
		events();

	}

	private void initialize() {

		update_inactiveIMG = new ImageIcon("src\\images\\buttons\\updateButtonInactive.png");
		update_activeIMG = new ImageIcon("src\\images\\buttons\\updateButtonActive.png");
		delete_inactiveIMG = new ImageIcon("src\\images\\buttons\\deleteButtonInactive.png");
		delete_activeIMG = new ImageIcon("src\\images\\buttons\\deleteButtonActive.png");
		insert_inactiveIMG = new ImageIcon("src\\images\\buttons\\insertButtonInactive.png");
		insert_activeIMG = new ImageIcon("src\\images\\buttons\\insertButtonActive.png");
		go_back_inactiveIMG = new ImageIcon("src\\images\\buttons\\goBackInactive.png");
		go_back_activeIMG = new ImageIcon("src\\images\\buttons\\goBackActive.png");

		long_dim_of_textfield = new Dimension(335, 25);
		short_dim_of_textfield = new Dimension(150, 25);
		button_size = new Dimension(150, 30);
		west_east_size = new Dimension(100, 50);
		north_south_size = new Dimension(100, 75);

		label = new JLabel();
		table_titleLB = new JLabel();

		west_panel = new JPanel();
		east_panel = new JPanel();
		north_panel = new JPanel();
		south_panel = new JPanel();
		center_panel = new JPanel();
		sql_panel = new JPanel();
		buttons_panel = new JPanel();
		attributes_panel = new JPanel();

		table = (new JTable() {
			@Override
			public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
				super.changeSelection(rowIndex, columnIndex, true, false);
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		scroll_pane = new JScrollPane(table);

		go_backJB = new JButton();
		insert_sqlJB = new JButton();
		delete_sqlJB = new JButton();
		update_sqlJB = new JButton();

	}

	private void frameSetup() {

		// Layout setup
		setSize(1280, 720);
		setMinimumSize(new Dimension(800, 680));
		setIconImage(new ImageIcon("src\\images\\startup\\icon.png").getImage());
		int central_width = screen_dim.width / 2 - getSize().width / 2;
		int central_height = screen_dim.height / 2 - getSize().height / 2;
		setLocation(central_width, central_height); // Setta il frame a centro monitor
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setBackground(background_color);

		createStandardPanel(west_panel, null, west_east_size);
		this.getContentPane().add(west_panel, BorderLayout.WEST);

		createStandardPanel(east_panel, null, west_east_size);
		this.getContentPane().add(east_panel, BorderLayout.EAST);

		createStandardPanel(north_panel, null, north_south_size);
		this.getContentPane().add(north_panel, BorderLayout.NORTH);

		createStandardPanel(south_panel, null, north_south_size);
		this.getContentPane().add(south_panel, BorderLayout.SOUTH);

		center_panel.setLayout(new BorderLayout());
		center_panel.setBackground(null);
		this.getContentPane().add(center_panel, BorderLayout.CENTER);

		// Impostazione JTable
		table.setAutoCreateRowSorter(false);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		table.setPreferredScrollableViewportSize(new Dimension(500, 50));
		table.setFillsViewportHeight(true);

		// Subpanels di center_panel
		attributes_panel.setLayout(new FlowLayout(FlowLayout.LEADING, 35, 10));
		createStandardPanel(attributes_panel, null, (new Dimension(100, 500)));
		sql_panel.add(attributes_panel, BorderLayout.CENTER);

		sql_panel.setLayout(new BorderLayout());
		createStandardPanel(sql_panel, null, (new Dimension(400, 50)));
		center_panel.add(sql_panel, BorderLayout.EAST);

		center_panel.add(scroll_pane, BorderLayout.CENTER);

		// Sotto pannelli di "sql_panel"
		attributes_panel.setLayout(new FlowLayout(FlowLayout.LEADING, 35, 10));
		createStandardPanel(attributes_panel, null, (new Dimension(100, 500)));
		sql_panel.add(attributes_panel, BorderLayout.CENTER);

		buttons_panel.setLayout(new FlowLayout(FlowLayout.LEADING, 35, 10));
		createStandardPanel(buttons_panel, null, (new Dimension(100, 100)));
		sql_panel.add(buttons_panel, BorderLayout.SOUTH);

		// Textfields setup
		table_titleLB.setSize(225, 100);
		north_panel.add(table_titleLB);
		label.setPreferredSize(long_dim_of_textfield);

		// Buttons setup
		setupButton(insert_sqlJB, insert_inactiveIMG, button_size);
		setupButton(update_sqlJB, update_inactiveIMG, button_size);
		setupButton(delete_sqlJB, delete_inactiveIMG, button_size);
		setupButton(go_backJB, go_back_inactiveIMG, button_size);

	}

	private void events() {

		insert_sqlJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				insert_sqlJB.setIcon(insert_activeIMG);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				insert_sqlJB.setIcon(insert_inactiveIMG);
			}
		});

		update_sqlJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				update_sqlJB.setIcon(update_activeIMG);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				update_sqlJB.setIcon(update_inactiveIMG);
			}
		});

		delete_sqlJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				delete_sqlJB.setIcon(delete_activeIMG);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				delete_sqlJB.setIcon(delete_inactiveIMG);
			}
		});

		go_backJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				go_backJB.setIcon(go_back_activeIMG);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				go_backJB.setIcon(go_back_inactiveIMG);
			}
		});
	}

	protected void textFieldFocusGained(JTextField text_field, String string) {
		if (text_field.getText().equals(string)) {
			text_field.setText("");
			text_field.setHorizontalAlignment(JTextField.LEFT);
		}
	}

	protected void textFieldFocusLost(JTextField text_field, String string) {
		if (text_field.getText().equals("")) {
			text_field.setText(string);
			text_field.setHorizontalAlignment(JTextField.CENTER);
		}
	}

	protected void createTextField(JTextField text_field, String text, Dimension dimension) {
		text_field.setHorizontalAlignment(JTextField.CENTER);
		text_field.setText(text);
		text_field.setPreferredSize(dimension);
	}

	protected void setupButton(JButton button, ImageIcon image, Dimension dimension) {
		button.setIcon(image);
		button.setSize(dimension);
		button.setBorder(null);
		button.setFocusable(false);
		button.setContentAreaFilled(false);
	}

	protected void createStandardPanel(JPanel panel, Color color, Dimension dimension) {
		panel.setBackground(color);
		panel.setPreferredSize(dimension);
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public JTable getTable() {
		return table;
	}

}
