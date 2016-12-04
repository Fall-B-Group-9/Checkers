package checkers;

import javax.swing.*;
import java.awt.*;

public class MustCapture extends JDialog {
	// Gives user a pop up message when they must make a capturing move
	// Added 12/3/16 by Kendra Neil
	// Implements E04
	Point p;
	JLabel message = new JLabel();

	MustCapture(Point p) {
		this.p = p;
		message.setText("   You must capture opponent piece.");
		setupGUI();
	}

	private void setupGUI() {
		new PlaySound("src//sounds//option.wav").start();
		message.setFont(new Font("dialog", Font.BOLD, 16));
		add(message);

		setAlwaysOnTop(true);
		setLocation((int) p.getX() + 100, (int) p.getY() + 200);
		setResizable(false);
		setSize(300, 80);
		setTitle("Move Not Valid");
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

}
