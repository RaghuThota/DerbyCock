package com.project.derby.action;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DerbyHome {
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;

	public DerbyHome() {
		prepareGUI();
	}

	public static void main(String[] args) {
		DerbyHome swingControlDemo = new DerbyHome();
		swingControlDemo.showEventDemo();

	}

	private void prepareGUI() {
		mainFrame = new JFrame("Derby Home");
		mainFrame.setSize(400, 400);
		mainFrame.setLayout(new GridLayout(3, 1));

		headerLabel = new JLabel("", JLabel.CENTER);
		statusLabel = new JLabel("", JLabel.CENTER);
		statusLabel.setSize(350, 100);

		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());

		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.add(statusLabel);
		mainFrame.setVisible(true);
	}

	private void showEventDemo() {
		headerLabel.setText("Select Game Type");

		JButton cock2Derby = new JButton("2 Cock Derby");
		JButton cock3Derby = new JButton("3 Cock Derby");
		JButton cock4Derby = new JButton("4 Cock Derby");
		JButton cock5Derby = new JButton("5 Cock Derby");

		cock2Derby.setActionCommand("2CockDerby");
		cock3Derby.setActionCommand("3CockDerby");
		cock4Derby.setActionCommand("4CockDerby");
		cock5Derby.setActionCommand("5CockDerby");

		cock2Derby.addActionListener(new ButtonClickListener());
		cock3Derby.addActionListener(new ButtonClickListener());
		cock4Derby.addActionListener(new ButtonClickListener());
		cock5Derby.addActionListener(new ButtonClickListener());

		controlPanel.add(cock2Derby);
		controlPanel.add(cock3Derby);
		controlPanel.add(cock4Derby);
		controlPanel.add(cock5Derby);

		mainFrame.setVisible(true);
	}

	private class ButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			 
			String command = e.getActionCommand();

			if (command.equals("2CockDerby")) {
				new RegistrationForm();
				statusLabel.setText("2CockDerby Button clicked.");
			} else if (command.equals("3CockDerby")) {
				statusLabel.setText("3CockDerby Button clicked.");
			} else if (command.equals("4CockDerby")) {
				statusLabel.setText("4CockDerby Button clicked.");
			} else {
				statusLabel.setText("5CockDerby Button clicked.");
			}
		}
	}
}