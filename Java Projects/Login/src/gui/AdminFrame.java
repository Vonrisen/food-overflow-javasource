package gui;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class AdminFrame {

	public JFrame frame;
	private int mouseX=0;
	private int mouseY=0;
	
	/**
	 * Create the application.
	 */
	public AdminFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		GridBagLayout gbl = new GridBagLayout();
		ImageIcon maximize_button = new ImageIcon("src\\images\\FrameIcons\\maximizeFrameButton.png");
		ImageIcon minimize_button = new ImageIcon("src\\images\\FrameIcons\\minimizeFrameButton.png");
		ImageIcon close_button = new ImageIcon("src\\images\\FrameIcons\\closeFrameButton.png");
		ImageIcon iconifier_button = new ImageIcon("src\\images\\FrameIcons\\iconifierFrameButton.png");
		ImageIcon customer_button = new StretchIcon("src\\images\\AdminFrame\\testbutton.png");
		
		frame = new JFrame();
		frame.setSize(1600,900);
		int central_width = dim.width/2-frame.getSize().width/2;
		int central_height = dim.height/2-frame.getSize().height/2;
		frame.setLocation(central_width, central_height); //Setta il frame a centro monitor
		frame.setUndecorated(true);
		frame.getContentPane().setLayout(new BorderLayout());
		
		JPanel home_panel = new JPanel();
		home_panel.setLayout(gbl);
		home_panel.setBackground(new Color(0xf3ecd7));
		frame.getContentPane().add(home_panel, BorderLayout.CENTER);
		
		JPanel shop_panel = new JPanel();
		shop_panel.setBackground(new Color(0xf3ecd7));
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topPanel.setPreferredSize(new Dimension(100,85));
		frame.getContentPane().add(topPanel, BorderLayout.NORTH);
		topPanel.setVisible(true);
		
		JPanel framePanel = new JPanel();
		framePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		framePanel.setPreferredSize(new Dimension(100,35));
		framePanel.setBackground(new Color(0x771007));
		framePanel.setVisible(true);
		topPanel.add(framePanel, BorderLayout.NORTH);
		
		JPanel menuPanel = new JPanel();
		menuPanel.setPreferredSize(new Dimension(100,50));
		menuPanel.setBackground(new Color(0xf49969));
		menuPanel.setVisible(true);
		topPanel.add(menuPanel, BorderLayout.SOUTH);
		
		JLabel iconifierFrameButton = new JLabel();
		iconifierFrameButton.setIcon(iconifier_button);
		framePanel.add(iconifierFrameButton);
		
		JLabel resizeFrameButton = new JLabel();
		resizeFrameButton.setIcon(maximize_button);
		framePanel.add(resizeFrameButton);
		
		JLabel closeFrameButton = new JLabel();
		closeFrameButton.setIcon(close_button);
		framePanel.add(closeFrameButton);
		
		JButton customerButton = new JButton();
		customerButton.setIcon(customer_button);
		JButton shopButton = new JButton("Negozio");
		JButton riderButton = new JButton("Button 3");
		JButton mealButton = new JButton("Button 4");
		
		framePanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				
				frame.setLocation (frame.getX()+e.getX()-mouseX,frame.getY()+e.getY()-mouseY);
				
			}
		});
		framePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				mouseX = e.getX();
				mouseY = e.getY();
				
				}
		});
		
		closeFrameButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				System.exit(0); // Da rivedere la chiusura del frame
				
			}
		});
		
		resizeFrameButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(frame.getSize().equals(new Dimension(1600,900))) {
					
					resizeFrameButton.setIcon(minimize_button);
					frame.setLocation(central_width, central_height);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					
				} else if(frame.getSize().equals(dim)) {
					
					resizeFrameButton.setIcon(maximize_button);
					frame.setSize(1600,900);
					frame.setLocation(central_width, central_height);
					
				}
				
			}
		});
		
		iconifierFrameButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				frame.setState(Frame.ICONIFIED);
				
			}
		});
		
		shopButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				frame.getContentPane().remove(home_panel);
				frame.getContentPane().add(shop_panel, BorderLayout.CENTER);
				frame.getContentPane().validate();
				frame.getContentPane().repaint();
				
			}
		});
		
		GridBagConstraints gcon = new GridBagConstraints();
		gcon.insets = new Insets(50,100,50,100);

		gcon.weightx = 1;
		gcon.weighty = 1;
		
		gcon.fill = GridBagConstraints.BOTH;
		
		//1
		gcon.gridx = 0;
		gcon.gridy = 0;
		gcon.gridwidth = 1;
		gcon.gridheight = 1;
		
		gbl.setConstraints(customerButton, gcon);
		home_panel.add(customerButton);
		
		gcon.gridx = 2;
		
		gbl.setConstraints(shopButton, gcon);
		home_panel.add(shopButton);
		
		gcon.gridx = 0;
		gcon.gridy = 2;

		gbl.setConstraints(riderButton, gcon);
		home_panel.add(riderButton);
		
		gcon.gridx = 2;
		gcon.gridy = 2;
		
		gbl.setConstraints(mealButton, gcon);
		home_panel.add(mealButton);
		
	}

}
