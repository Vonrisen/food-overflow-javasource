package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.CustomerController;
import gui_support.RoundJTextField;

@SuppressWarnings("serial")
public class CustomerFrame extends JFrame {

	private Dimension screen_dim = Toolkit.getDefaultToolkit().getScreenSize();
	private Dimension long_dim_of_textfield;
	private ImageIcon backgroundIMG;
	private ImageIcon logoIMG;
	private ImageIcon search_inactiveIMG;
	private ImageIcon search_activeIMG;
	private ImageIcon profile_inactiveIMG;
	private ImageIcon profile_activeIMG;

	private Dimension west_east_size;
	private Dimension north_south_size;

	private JPanel west_panel;
	private JPanel east_panel;
	private JPanel north_panel;
	private JPanel south_panel;
	private JPanel center_panel;
	private JPanel logo_panel;
	private JPanel text_panel;
	private JPanel search_panel;
	private JPanel button_panel;

	private JLabel background;
	private JLabel logo;
	private JLabel magheggio;
	private JLabel magheggio2;

	private JTextField provinceTF;
	private JButton searchJB;
	private JButton profileJB;
	private CustomerController customer_controller;

	public CustomerFrame(CustomerController customer_controller) {

		initialize();
		frameSetup();
		events();
		this.customer_controller = customer_controller;
	}

	// Initialize variables
	private void initialize() {

		logoIMG = new ImageIcon("src\\images\\customer\\Logo.png");
		backgroundIMG = new ImageIcon("src\\images\\customer\\WALLPAPER.PNG");
		search_inactiveIMG = new ImageIcon("src\\images\\customer\\searchButtonInactive.PNG");
		search_activeIMG = new ImageIcon("src\\images\\customer\\searchButtonActive.PNG");
		profile_inactiveIMG = new ImageIcon("src\\images\\customer\\profileButtonInactive.png");
		profile_activeIMG = new ImageIcon("src\\images\\customer\\profileButtonActive.png");

		long_dim_of_textfield = new Dimension(335, 25);
		west_east_size = new Dimension(100, 50);
		north_south_size = new Dimension(100, 25);

		west_panel = new JPanel();
		east_panel = new JPanel();
		north_panel = new JPanel();
		south_panel = new JPanel();
		center_panel = new JPanel();
		logo_panel = new JPanel();
		text_panel = new JPanel();
		search_panel = new JPanel();
		button_panel = new JPanel();

		background = new JLabel();
		logo = new JLabel();

		provinceTF = new RoundJTextField(new Color(0x771007));
		searchJB = new JButton();
		profileJB = new JButton();
		magheggio = new JLabel();
		magheggio2 = new JLabel();

	}

	private void frameSetup() {

		// Layout setup

		this.setTitle("Food Overflow - Home");
		this.setSize(1280, 720);
		background.setIcon(resize(backgroundIMG, this.getWidth(), this.getHeight()));
		setIconImage(new ImageIcon("src\\images\\startup\\icon.png").getImage());
		this.setMinimumSize(new Dimension(800, 650));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int central_width = screen_dim.width / 2 - this.getSize().width / 2;
		int central_height = screen_dim.height / 2 - this.getSize().height / 2;
		this.setLocation(central_width, central_height); // Setta il frame a centro monitor
		this.getContentPane().setLayout(new BorderLayout());
		this.setContentPane(background);
		this.getContentPane().setLayout(new BorderLayout());

		createStandardPanel(west_panel, null, west_east_size);
		west_panel.setOpaque(false);
		this.getContentPane().add(west_panel, BorderLayout.WEST);

		createStandardPanel(east_panel, null, west_east_size);
		east_panel.setOpaque(false);
		this.getContentPane().add(east_panel, BorderLayout.EAST);

		createStandardPanel(north_panel, null, north_south_size);
		north_panel.setOpaque(false);
		this.getContentPane().add(north_panel, BorderLayout.NORTH);

		createStandardPanel(south_panel, null, north_south_size);
		south_panel.setOpaque(false);
		this.getContentPane().add(south_panel, BorderLayout.SOUTH);

		center_panel.setLayout(new BorderLayout());
		center_panel.setBackground(Color.black);
		center_panel.setOpaque(false);
		this.getContentPane().add(center_panel, BorderLayout.CENTER);

		createStandardPanel(logo_panel, null, new Dimension(100, 320));
		logo_panel.setOpaque(false);
		center_panel.add(logo_panel, BorderLayout.NORTH);

		createStandardPanel(text_panel, null, new Dimension(100, 350));
		text_panel.setLayout(new BorderLayout());
		text_panel.setOpaque(false);
		center_panel.add(text_panel, BorderLayout.CENTER);

		createStandardPanel(search_panel, null, new Dimension(100, 70));
		search_panel.setOpaque(false);
		text_panel.add(search_panel, BorderLayout.NORTH);

		createStandardPanel(button_panel, null, new Dimension(100, 500));
		button_panel.setOpaque(false);
		text_panel.add(button_panel, BorderLayout.CENTER);

		logo.setIcon(logoIMG);
		logo_panel.add(logo);
		logo.setFocusable(true);

		createTextField(provinceTF, "Inserisci la provincia", long_dim_of_textfield);
		search_panel.add(provinceTF, BorderLayout.CENTER);

		magheggio.setPreferredSize(new Dimension(15, 15));
		logo_panel.add(magheggio);

		setupButton(searchJB, search_inactiveIMG, new Dimension(25, 25));
		search_panel.add(searchJB, BorderLayout.CENTER);

		setupButton(profileJB, profile_inactiveIMG, new Dimension(100, 100));
		button_panel.add(profileJB);

		magheggio2.setPreferredSize(new Dimension(15, 15));
		button_panel.add(magheggio2);

	}

	private void events() {

		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent componentEvent) {
				background
						.setIcon(resize(backgroundIMG, CustomerFrame.this.getWidth(), CustomerFrame.this.getHeight()));
			}
		});

		profileJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				profileJB.setIcon(profile_activeIMG);

			}

			@Override
			public void mouseExited(MouseEvent e) {

				profileJB.setIcon(profile_inactiveIMG);

			}

			public void mousePressed(MouseEvent e) {

				customer_controller.openCustomerProfileFrame();

			}
		});

		searchJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				searchJB.setIcon(search_activeIMG);

			}

			@Override
			public void mouseExited(MouseEvent e) {

				searchJB.setIcon(search_inactiveIMG);

			}

			public void mousePressed(MouseEvent e) {

				customer_controller.checkProvinceAndOpenShopListFrame(CustomerFrame.this);
			}

		});

		provinceTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {

				textFieldFocusGained(provinceTF, "Inserisci la provincia");

			}

			@Override
			public void focusLost(FocusEvent e) {

				textFieldFocusLost(provinceTF, "Inserisci la provincia");

			}
		});

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				customer_controller.releaseAllDaoResourcesAndDisposeFrame(CustomerFrame.this);
			}
		});

	}

	private ImageIcon resize(ImageIcon im, int w, int h) {

		BufferedImage bi = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
		Graphics2D gd = (Graphics2D) bi.createGraphics();
		gd.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
		gd.drawImage(im.getImage(), 0, 0, w, h, null);
		gd.dispose();

		return new ImageIcon(bi);
	}

	private void textFieldFocusGained(JTextField text_field, String string) {

		if (text_field.getText().equals(string)) {
			text_field.setText("");
			text_field.setHorizontalAlignment(JTextField.LEFT);

		}

	}

	private void textFieldFocusLost(JTextField text_field, String string) {

		if (text_field.getText().equals("")) {
			text_field.setText(string);
			text_field.setHorizontalAlignment(JTextField.CENTER);
		}

	}

	private void createTextField(JTextField text_field, String text, Dimension dimension) {

		text_field.setHorizontalAlignment(JTextField.CENTER);
		text_field.setText(text);
		text_field.setPreferredSize(dimension);

	}

	private void setupButton(JButton button, ImageIcon image, Dimension dimension) {

		button.setIcon(image);
		button.setSize(dimension);
		button.setBorder(null);
		button.setFocusable(false);
		button.setContentAreaFilled(false);

	}

	private void createStandardPanel(JPanel panel, Color color, Dimension dimension) {

		panel.setBackground(color);
		panel.setPreferredSize(dimension);

	}

	public JTextField getProvinceTF() {
		return provinceTF;
	}

}
