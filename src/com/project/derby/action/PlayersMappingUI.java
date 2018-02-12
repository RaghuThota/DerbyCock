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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.bean.Fights;
import com.bean.Players;
import com.project.derby.components.Components;

public class PlayersMappingUI extends JFrame {

	private Container contentPane;
	private JTable jtable;
	private static int fightID = 1;
	private DefaultTableModel tableModel;;
	private String[] columns = { "ID", "Player", "Player Entries"};
	Components components = new Components();

	public PlayersMappingUI() {
		contentPane = getContentPane();
		contentPane.setLayout(null);
		addListeners();
	}

	public void initializeJTable(List<Players> lis) {
		PlayersMappingUI omiu = new PlayersMappingUI();
		omiu.setTitle("::::Registered Players::::");
		JLabel roundLabel = components.getLabel("Registered Players", 60 + 40, 7, 200, 30);
		omiu.setSize(800, 600);
		omiu.setVisible(true);
		omiu.initiateAndAddJTable(lis);
		omiu.add(roundLabel);
	}

	private void initiateAndAddJTable(List<Players> playersList) {
		tableModel = new DefaultTableModel(columns, 0);
		jtable = new JTable(tableModel);
		jtable.setRowHeight(30);
		int i = 0;
		while (i < playersList.size() - 1) {
			tableModel.addRow(getRowElementsFromObject(playersList.get(i)));
			i = i + 2;
		}
		JScrollPane sp = new JScrollPane(jtable);
		sp.setBorder(Components.addBorders());
		sp.setBounds(10, 10, 700, 500);
		contentPane.add(sp);
	}

	private Object[] getRowElementsFromObject(Players player) {
		Object o[] = new Object[5];
		o[0] = fightID++;
		o[1] = player.getName();
		o[2] = player.getEntry().getEntryId();
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
