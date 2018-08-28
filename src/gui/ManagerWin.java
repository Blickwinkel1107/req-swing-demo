package gui;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import gui.MaintainerWin;

import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ManagerWin {

	private JFrame frame;
	private JTable reqElementsTable;
	private JTable updateLogTable;
	private MaintainerWin m;

	/*
	 * modified by YHR
	 */
	private String path_req;

	protected void setPath_req(String path_req){
		this.path_req = path_req;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerWin window = new ManagerWin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ManagerWin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 645);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 369, 26);
		frame.getContentPane().add(panel);

		JLabel lblNewLabel = new JLabel("Requirement Elements");
		panel.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 30, 369, 180);
		frame.getContentPane().add(scrollPane);

		Object[][] playerInfo = {
	            // 鍒涘缓琛ㄦ牸涓殑鏁版嵁
	            { "", "", "", ""},
	            { "", "", "", ""},
	            { "", "", "", ""},
	            { "", "", "", ""},
	            { "", "", "", ""}};
		String[] names_1 = { "No", "Id", "Status", "Pending"};
		reqElementsTable = new JTable(playerInfo, names_1);
		reqElementsTable.addMouseListener(new MouseAdapter() {
			/*
			 modified by YHR
			 琛ㄦ牸鎺ユ敹榧犳爣宸︺�佸彸閿偣鍑讳簨浠�
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1){
					//LEFT MOUSE CLICKED
					int tableRow = reqElementsTable.rowAtPoint(e.getPoint());
					System.out.print(tableRow);
				}
			}	
		});
		m.makeFace(reqElementsTable);
		scrollPane.setViewportView(reqElementsTable);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(424, 6, 370, 26);
		frame.getContentPane().add(panel_1);

		JLabel lblUpdateLog = new JLabel("Update Log");
		panel_1.add(lblUpdateLog);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(424, 30, 370, 180);
		frame.getContentPane().add(scrollPane_1);

		String[] names_2 = { "No", "CommitId", "Author"};
		updateLogTable = new JTable(playerInfo, names_2);
		updateLogTable.addMouseListener(new MouseAdapter() {
			/*
			 modified by YHR
			 琛ㄦ牸鎺ユ敹榧犳爣宸︺�佸彸閿偣鍑讳簨浠�
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1){
					//LEFT MOUSE CLICKED
					int tableRow = updateLogTable.rowAtPoint(e.getPoint());
					System.out.print(tableRow);
				}
			}	
		});
		m.makeFace(updateLogTable);
		scrollPane_1.setViewportView(updateLogTable);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(6, 222, 369, 26);
		frame.getContentPane().add(panel_2);

		JLabel lblRequirementsText = new JLabel("Requirements Text");
		panel_2.add(lblRequirementsText);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(6, 249, 369, 142);
		frame.getContentPane().add(scrollPane_2);

		JTextArea reqText = new JTextArea();
		scrollPane_2.setViewportView(reqText);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(424, 222, 370, 26);
		frame.getContentPane().add(panel_3);

		JLabel lblUpdateInfo = new JLabel("Update Info");
		panel_3.add(lblUpdateInfo);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(424, 249, 370, 113);
		frame.getContentPane().add(scrollPane_3);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(6, 403, 369, 26);
		frame.getContentPane().add(panel_4);

		JLabel lblUpdateRequirements = new JLabel("Update Requirements");
		panel_4.add(lblUpdateRequirements);

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(6, 429, 369, 142);
		frame.getContentPane().add(scrollPane_4);

		JTextArea updateReq = new JTextArea();
		scrollPane_4.setViewportView(updateReq);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(6, 570, 369, 34);
		frame.getContentPane().add(panel_5);

		JButton btnSave = new JButton("Save");
		panel_5.add(btnSave);
		frame.setVisible(true);
	}
}
