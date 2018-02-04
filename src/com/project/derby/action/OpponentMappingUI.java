package com.project.derby.action;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.bean.Players;
import com.project.derby.components.Components;
public class OpponentMappingUI extends JFrame {

	private Container contentPane;
	private JTable jtable;
	//private DerbyDAOImpl daoImpl=new DerbyDAOImpl();
	private JButton button=new JButton("Submit");
	private DefaultTableModel tableModel;;
	private String[] columns={"ID","Opponent 1","Opponent 2","Fight status","Won By"};
	public OpponentMappingUI() {
		contentPane=getContentPane();
		contentPane.setLayout(null);
		addListeners();
	}
	public static void main(String[] args) {
		OpponentMappingUI.setDefaultLookAndFeelDecorated(true);
		OpponentVO o1=new OpponentVO("1","Hiren","Raghu","Pending");
		OpponentVO o2=new OpponentVO("2","Water","Rain","Pending");
		OpponentVO o3=new OpponentVO("3","Nag","Manish","Pending");
		OpponentVO o4=new OpponentVO("1","Hiren","Raghu","Pending");
		OpponentVO o5=new OpponentVO("2","Water","Rain","Pending");
		OpponentVO o6=new OpponentVO("3","Nag","Manish","Pending");

		OpponentVO o7=new OpponentVO("1","Hiren","Raghu","Pending");
		OpponentVO o8=new OpponentVO("2","Water","Rain","Pending");
		OpponentVO o9=new OpponentVO("3","Nag","Manish","Pending");
	
		OpponentVO o10=new OpponentVO("1","Hiren","Raghu","Pending");
		OpponentVO o11=new OpponentVO("2","Water","Rain","Pending");
		OpponentVO o12=new OpponentVO("3","Nag","Manish","Pending");
	
		OpponentVO o13=new OpponentVO("1","Hiren","Raghu","Pending");
		OpponentVO o14=new OpponentVO("2","Water","Rain","Pending");
		OpponentVO o15=new OpponentVO("3","Nag","Manish","Pending");
	
		OpponentVO o16=new OpponentVO("1","Hiren","Raghu","Pending");
		OpponentVO o17=new OpponentVO("2","Water","Rain","Pending");
		OpponentVO o18=new OpponentVO("3","Nag","Manish","Pending");
		
		List<OpponentVO> lis=new ArrayList<OpponentVO>();
		lis.add(o1);
		lis.add(o2);
		lis.add(o3);
	//	initializeJTable(lis);
		}
	public static void initializeJTable(List<Players> lis) {
		OpponentMappingUI omiu=new OpponentMappingUI();
		omiu.setTitle("::::-Opponent Form-::::");
		omiu.setSize(520,490);
		omiu.setVisible(true);
		omiu.initiateAndAddJTable(lis);
	}
	private void initiateAndAddJTable(List<Players> opponentsList) {
		button.setBounds(215,410,100,30);
		contentPane.add(button);
		tableModel=new DefaultTableModel(columns,0);
		jtable=new JTable(tableModel);
		for (Players opponentVO : opponentsList) {
			tableModel.addRow(getRowElementsFromObject(opponentVO));
		}
		JScrollPane sp=new JScrollPane(jtable);
		sp.setBorder(Components.addBorders());
		sp.setBounds(10,10,490,390);
		contentPane.add(sp);
	}
	private Object[] getRowElementsFromObject(Players opponentVO) {
		Object o[]=new Object[5];
			o[0]=opponentVO.getPlayerId();
			o[1]=opponentVO.getName();
			o[2]=opponentVO.getPhone();
			//o[3]=opponentVO.getFightStatus();
		//o[4]=opponentVO.getWonBy();
			return o;
	 }
	private void addListeners() {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we){
				System.out.println("EXIT 0");
				System.exit(1);
				System.out.println("EXIT 1");
				System.exit(0);
			}
		});
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me){
				System.out.println("X "+me.getX());
				System.out.println("Y "+me.getY());
			}
		});
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				insertOpponentsToDB();
			}
		});
	}
	private void insertOpponentsToDB() {
		int rowCOunt=jtable.getRowCount();
		OpponentVO opponentVOs[]=new OpponentVO[rowCOunt];
		int k=0;
		while(k<rowCOunt){
			OpponentVO opponentVO=new OpponentVO();
			opponentVO.setOpponent1(jtable.getValueAt(k, 1).toString());
			opponentVO.setOpponent2(jtable.getValueAt(k, 2).toString());
			opponentVO.setFightStatus(jtable.getValueAt(k, 3).toString());
			opponentVO.setWonBy(jtable.getValueAt(k, 4).toString());
			opponentVOs[k]=opponentVO;
			System.out.println(opponentVO);
			k++;
		}
	//	daoImpl.insertOpponents(opponentVOs);
	}
	private void generateUniqueFightID(OpponentVO opponentVO) {

	}
}
