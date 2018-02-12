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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.bean.Fights;
import com.bean.Players;
import com.project.derby.components.Components;

public class PointsMappingUI extends JFrame{


	private Container contentPane;
	private JTable jtable;
	private DefaultTableModel tableModel;;
	private String[] columns = { "PLAYER NAME", "POINTS" };

	public PointsMappingUI() {
		contentPane = getContentPane();
		contentPane.setLayout(null);
		addListeners();
	}

	public void initializeJTable(List<Players> lis) {
		PointsMappingUI omiu = new PointsMappingUI();
		omiu.setTitle("::::-Points Form-::::");
		omiu.setSize(800, 650);
		omiu.setVisible(true);
		omiu.initiateAndAddJTable(lis);
	}

	private void initiateAndAddJTable(List<Players> pointsList) {
		tableModel = new DefaultTableModel(columns, 0);
		jtable = new JTable(tableModel);
		jtable.setRowHeight(30);
		int i = 0;
		while (i < pointsList.size() - 1) {
			tableModel.addRow(getRowElementsFromObject(pointsList.get(i)));
			i = i + 2;
		}
		JScrollPane sp = new JScrollPane(jtable);
		sp.setBorder(Components.addBorders());
		sp.setBounds(10, 10, 780, 630);
		contentPane.add(sp);
	}

	private Object[] getRowElementsFromObject(Players player) {
		Object o[] = new Object[2];
		o[0] = player.getName();
		o[1] = player.getPoints();
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
}
