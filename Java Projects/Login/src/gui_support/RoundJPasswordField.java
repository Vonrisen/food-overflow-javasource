package gui_support;

import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class RoundJPasswordField extends JPasswordField {

	private Color color;

	public RoundJPasswordField(Color color) {

		this.color = color;

	}

	@Override
	protected void paintComponent(Graphics g) {
		if (!isOpaque()) {

			int w = getWidth() - 1;
			int h = getHeight() - 1;
			Graphics2D g2 = (Graphics2D) g.create();

			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setPaint(Color.white);
			g2.fillRoundRect(0, 0, w, h, h, h);
			g2.setPaint(color);
			g2.setStroke(new BasicStroke((float) 2)); // Farlo più spesso quando si clicca dentro
			g2.drawRoundRect(0, 0, w, h, h, h);
			g2.dispose();

		}

		super.paintComponent(g);

	}

	@Override
	public void updateUI() {

		super.updateUI();
		setOpaque(false);
		setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));

	}

}