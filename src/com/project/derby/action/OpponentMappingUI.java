package com.project.derby.action;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.bean.Fights;
import com.bean.Players;
import com.project.derby.components.Components;

public class OpponentMappingUI extends JFrame {

	private Container contentPane;
	private JTable jtable;
	private static int fightID = 1;
	private JButton button = new JButton("Submit");
	private DefaultTableModel tableModel;;
	private String[] columns = { "FIGHTID", "PLAYER 1", "PLAYER1_ENTRY", "PLAYER 2", "PLAYER2_ENTRY", "WON BY" };
	Components components = new Components();
	private JLabel msgLabel = components.getLabel("No Pairs Found", 60 + 40, 7, 200, 30);

	public OpponentMappingUI() {
		contentPane = getContentPane();
		contentPane.setLayout(null);
		addListeners();
	}

	public void initializeJTable(List<Players> lis) {
		OpponentMappingUI omiu = new OpponentMappingUI();
		omiu.setTitle("::::-Fights Form-::::");
		JLabel roundLabel = components.getLabel("Register new person", 60 + 40, 7, 200, 30);
		omiu.setSize(800, 600);
		omiu.setVisible(true);
		omiu.initiateAndAddJTable(lis);
		omiu.add(roundLabel);
		// omiu.setDefaultCloseOperation(omiu.DISPOSE_ON_CLOSE);
	}

	private void initiateAndAddJTable(List<Players> opponentsList) {
		button.setBounds(280, 520, 100, 30);
		contentPane.add(button);
		tableModel = new DefaultTableModel(columns, 0);
		jtable = new JTable(tableModel);
		jtable.setRowHeight(30);
		int i = 0;
		while (i < opponentsList.size() - 1) {
			if(opponentsList.size() != 0) {
			tableModel.addRow(getRowElementsFromObject(opponentsList.get(i), opponentsList.get(i + 1)));
			i = i + 2;
			} else {
				jtable.add(msgLabel);
			}
		}

		TableColumn sportColumn = jtable.getColumnModel().getColumn(5);
			JComboBox comboBox = new JComboBox();
			comboBox.addItem( "PLAYER1 WON");
			comboBox.addItem("PLAYER2 WON");
			comboBox.addItem("FIGHT DRAWN");
			comboBox.addItem("ABANDONED");
			sportColumn.setCellEditor(new DefaultCellEditor(comboBox));
		JScrollPane sp = new JScrollPane(jtable);
		sp.setBorder(Components.addBorders());
		sp.setBounds(10, 10, 700, 500);
		contentPane.add(sp);
	}

	private Object[] getRowElementsFromObject(Players player1, Players player2) {
		Object o[] = new Object[5];
		o[0] = fightID++;
		o[1] = player1.getName();
		o[2] = player1.getEntry().getEntryId().concat("_").concat(String.valueOf(player1.getEntry().getDerbyWeight()));
		o[3] = player2.getName();
		o[4] = player2.getEntry().getEntryId().concat("_").concat(String.valueOf(player2.getEntry().getDerbyWeight()));
		return o;
	}

	private void addListeners() {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.out.println("EXIT 0");
				System.exit(1);
				System.out.println("EXIT 1");
				System.exit(0);
			}
		});
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				System.out.println("X " + me.getX());
				System.out.println("Y " + me.getY());
			}
		});
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					insertOpponentsToDB();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	private void insertOpponentsToDB() throws SQLException {
		FightsDao fightsDao = new FightsDao();
		int rowCOunt = jtable.getRowCount();
		List<Fights> fightList = new ArrayList<Fights>();
		Fights fights = null;
		int k = 0;
		while (k < rowCOunt) {
			fights = new Fights();

			fights.setFightId(Integer.valueOf((jtable.getValueAt(k, 0).toString())));
			fights.setPlayer1IdName(jtable.getValueAt(k, 1).toString());
			fights.setEntry1Id(jtable.getValueAt(k, 2).toString());
			fights.setPlayer2IdName(jtable.getValueAt(k, 3).toString());
			fights.setEntry2Id(jtable.getValueAt(k, 4).toString());
			fights.setWonBy(jtable.getValueAt(k, 5).toString());
			System.out.println("fights bean : " + fights);
			fightList.add(fights);
			k++;
		}
		fightsDao.insertFights(fightList);
	}

}
