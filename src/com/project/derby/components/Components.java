package com.project.derby.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.KeyboardFocusManager;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Components {
	JLabel label;
	JTextField textField;
	JButton button;


	public JLabel getLabel(String lableName, int bound1, int bound2, int bound3, int bound4) {
		label = new JLabel(lableName);
		label.setFont(getFont(15));
		label.setBounds(bound1, bound2, bound3, bound4);
		return label;
	}

	public JTextField getTextField(int bound1, int bound2, int bound3, int bound4,String defaultString) {
		// Defining ID field
		textField = new JTextField(defaultString);
		textField.setBounds(bound1, bound2, 210, bound4);
		textField.setBorder(addBorders());
		textField.setFont(getFont(14));
//		textField.setHorizontalAlignment(JTextField.CENTER);
		/*
		 * idField.addKeyListener(new KeyAdapter() { public void keyTyped(KeyEvent e) {
		 * char c = e.getKeyChar(); if (!((c >= '0') && (c <= '9') || (c ==
		 * KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) { e.consume();
		 * 
		 * } }
		 * 
		 * });
		 */

		return textField;
	}

	public JButton getButton(String buttonName, int bound1, int bound2, int bound3, int bound4) {
		button = new JButton(buttonName);
		button.setBounds(bound1, bound2, bound3, bound4);
		button.setBorder(addBorders());
		return button;
	}
	private Font getFont(int size) {
		return new Font("Calibri", Font.ITALIC | Font.BOLD, size);
	}
	 public static Border addBorders() {
		 Border border=BorderFactory.createLineBorder(Color.BLUE,2);
		 return border;
		 	 
		 }
}
