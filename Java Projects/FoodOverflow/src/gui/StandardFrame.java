package gui;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class StandardFrame extends JFrame {

	protected Dimension screen_dim = Toolkit.getDefaultToolkit().getScreenSize();
	protected Dimension button_size;
	protected Dimension west_east_size;
	protected Dimension north_south_size;

	protected ImageIcon go_back_inactiveIMG;
	protected ImageIcon go_back_activeIMG;
	protected ImageIcon table_title;

	protected JLabel table_titleLB;

	protected JPanel west_panel;
	protected JPanel east_panel;
	protected JPanel north_panel;
	protected JPanel south_panel;
	protected JPanel center_panel;

	protected JTable table;
	protected JScrollPane scroll_pane;

	protected JButton go_backJB;

	protected Color background_color = new Color(0xf3ecd7);

	protected DefaultTableModel model;

	// Costruttore
	public StandardFrame() {
		initialize();
		frameSetup();
		events();
	}

	private void initialize() {

		go_back_inactiveIMG = new ImageIcon("src\\images\\buttons\\goBackInactive.png");
		go_back_activeIMG = new ImageIcon("src\\images\\buttons\\goBackActive.png");

		button_size = new Dimension(150, 30);
		west_east_size = new Dimension(100, 50);
		north_south_size = new Dimension(100, 75);

		west_panel = new JPanel();
		east_panel = new JPanel();
		north_panel = new JPanel();
		south_panel = new JPanel();
		center_panel = new JPanel();

		table_titleLB = new JLabel();

		table = (new JTable() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
				super.changeSelection(rowIndex, columnIndex, true, false);
			}
		});
		scroll_pane = new JScrollPane(table);

		go_backJB = new JButton();

	}

	private void frameSetup() {

		// Layout setup
		this.setSize(1280, 720);
		this.setMinimumSize(new Dimension(640, 500));
		setIconImage(new ImageIcon("src\\images\\startup\\icon.png").getImage());
		int central_width = screen_dim.width / 2 - this.getSize().width / 2;
		int central_height = screen_dim.height / 2 - this.getSize().height / 2;
		this.setLocation(central_width, central_height); // Setta il frame a centro monitor
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().setBackground(background_color);

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

		// Sottopannelli di "center_panel"
		table_titleLB.setSize(225, 100);
		center_panel.add(scroll_pane, BorderLayout.CENTER);
		north_panel.add(table_titleLB);

		// Buttons setup
		go_backJB.setIcon(go_back_inactiveIMG);
		go_backJB.setSize(button_size);
		go_backJB.setBorder(null);
		go_backJB.setFocusable(false);
		go_backJB.setContentAreaFilled(false);
		south_panel.add(go_backJB);

	}

	private void events() {

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

	private void createStandardPanel(JPanel panel, Color color, Dimension dimension) {
		panel.setBackground(color);
		panel.setPreferredSize(dimension);
	}

	public JTable getTable() {
		return table;
	}

	public DefaultTableModel getModel() {
		return model;
	}

}
