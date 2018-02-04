package com.project.derby.action;

import java.awt.Color;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import com.bean.Entries;
import com.bean.Players;
import com.project.derby.components.Components;

@SuppressWarnings("serial")

public class RegistrationForm extends JFrame implements ActionListener {
	JLabel title, idLabel, nameLabel, contactLabel, emailIdLabel, numberOfEntriesLabel;
	JTextField idField, nameField, contactField, emailField, numberOfEntriesField;
	JButton registerButton, exitButton, playerButton;
	JRadioButton male, female;
	ButtonGroup bg;
	JPanel panel;
	JTable table;
	String gender = "";
	private JPanel jpanel;
	private List<JTextField> weightTextsFields = new ArrayList<JTextField>();;
	// Returns a column class of Object
	DefaultTableModel model;
	Components components = new Components();
	private static RegistrationForm rf;

	RegistrationForm() {

		setSize(1000, 700);
		setTitle("::::-Registration Form-::::");
		setLayout(null);

		// Defining Labels
		title = components.getLabel("Register new person", 60 + 40, 7, 200, 30);

		nameLabel = components.getLabel("Name", 30 + 40, 85, 60, 30);
		nameField = components.getTextField(30 + 165, 85, 150, 30, "name");

	
		contactLabel = components.getLabel("Contact", 30 + 40, 155, 60, 30);
		contactField = components.getTextField(30 + 165, 155, 150, 30, "7675");

		contactField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}

		});

		emailIdLabel = components.getLabel("EmailId", 30 + 40, 205, 60, 30);
		emailField = components.getTextField(30 + 165, 205, 150, 30, "myemail@gmail");

		numberOfEntriesLabel = components.getLabel("Number of Entries", 30 + 40, 250, 120, 50);
		numberOfEntriesField = components.getTextField(30 + 165, 255, 150, 30, "3");
		numberOfEntriesField.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);

		numberOfEntriesField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		numberOfEntriesField.addActionListener(this);

		// Defining Exit Button
		exitButton = components.getButton("Exit", 85 + 85, 320 + 40, 80, 30);
		exitButton.addActionListener(this);

		// Defining Register Button
		registerButton = components.getButton("Register", 180 + 85, 320 + 40, 100, 30);
		registerButton.addActionListener(this);

		playerButton = components.getButton("view Registered Players", 340 + 85,  40, 200, 30);
		playerButton.addActionListener(this);
		
		// fixing all Label,TextField,Button
		add(title);
		add(nameLabel);
		add(contactLabel);
		add(emailIdLabel);
		add(numberOfEntriesLabel);
		add(nameField);
		add(contactField);
		add(emailField);
		add(numberOfEntriesField);
		add(exitButton);
		add(registerButton);
		add(playerButton);

		model = new DefaultTableModel();
		table = new JTable(model);
		table.setEnabled(true);

		// Defining Column Names on model
		// model.addColumn("ID");
		model.addColumn("Name");
		model.addColumn("Contact");
		model.addColumn("EmailId");
		model.addColumn("Number Of Entries");

		JFrame f = new JFrame();
		f.getContentPane().add(table).setBackground(Color.RED);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setVisible(true);

	}

	public void actionPerformed(ActionEvent ae) {
		Players player = new Players();
		Entries entries = new Entries();
		PlayerEntriesDao playerDao = new PlayerEntriesDao();
		List<JTextField> addedTextsFields;
		String playerId = null ;

		if (ae.getSource() == numberOfEntriesField) {
			int numberOfTextFields = Integer.parseInt(ae.getActionCommand());

			rf.setSize(999, 700);
			jpanel = new JPanel();
			jpanel.setLayout(null);
			jpanel.setBorder(Components.addBorders());
			JLabel label = components.getLabel("Weights(lbs)", 10, 5, 60, 30);
			jpanel.add(label);

			addTexFieldsToJPanel(numberOfTextFields, jpanel);
			JScrollPane scrollPane = null;

			scrollPane = new JScrollPane(jpanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setBounds(530, 100, 35 * numberOfTextFields, 50 * numberOfTextFields);
			rf.add(scrollPane);
			revalidate();
			validate();

		}

		if (ae.getSource() == exitButton) {
			System.exit(0);
		}

		if (ae.getSource() == registerButton) {

			if (nameField.getText().equals("") || emailField.getText().equals("")
					|| contactField.getText().equals("")) {
				JOptionPane.showMessageDialog(idField, "Fields will not be blank");
			} else {
				List<String> generatedEntries = null;
				addedTextsFields = getAddedTexFieldsToJPanel();
				player.setName(nameField.getText());		
				player.setPhone(Long.parseLong(contactField.getText()));
				player.setEmail(emailField.getText());
				try {
					playerId = playerDao.insertPlayer(player);
				}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				entries.setPalyerId(playerId);
				double derbyWeight = 0.0;
				List<Double> weightList = new ArrayList<Double>();
				for (int i = 0; i < addedTextsFields.size(); i++) {
					derbyWeight = Double.parseDouble(addedTextsFields.get(i).getText());
					if(derbyWeight != 0.0) {
					weightList.add(derbyWeight);
					}
				}
				entries.setDerbyWeightList(weightList);
				try {
					generatedEntries = playerDao.insertEntries(entries);
				}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(this, nameField.getText() + "Successfully Registered with plyerid " + playerId +" entries"  + generatedEntries);
				nameField.setText("");
				contactField.setText("");
				emailField.setText("");
				numberOfEntriesField.setText("");
			}
		}
		
		if (ae.getSource() == playerButton) {
			playerDao.displayRegisteredPlayers();
			}
	}

	public static void main(String[] args) {
		setDefaultLookAndFeelDecorated(true);
		rf = new RegistrationForm();
	}

	private void addTexFieldsToJPanel(int numberOfTextFields, JPanel panel) {

		for (int i = 0; i < numberOfTextFields; i++) {
			JTextField jf = new JTextField("0.0");
			panel.add(jf);
			jf.setBounds(10, (i + 1) * 30, 50, 28);
			jf.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)
							|| (c == KeyEvent.VK_PERIOD))) {
						e.consume();
					}
				}
			});
			this.weightTextsFields.add(jf);
		}
	}

	private List<JTextField> getAddedTexFieldsToJPanel() {

		return this.weightTextsFields;
	}

}