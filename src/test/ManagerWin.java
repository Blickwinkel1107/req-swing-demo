package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JButton;

import test.MaintainerWin;

public class ManagerWin {

	private JFrame frame;
	private JTable table;
	private JTable table_1;
	private MaintainerWin m;

	/*
	 * modified by YHR
	 */
	private String path_old;
	private String path_new;
	private String path_req;

	protected void setPath_old(String path_old){
		this.path_old = path_old;
	}

	protected void setPath_new(String path_new){
		this.path_new = path_new;
	}

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
	            // 创建表格中的数据
	            { "", "", "", ""},
	            { "", "", "", ""},
	            { "", "", "", ""},
	            { "", "", "", ""},
	            { "", "", "", ""}};
		String[] names_1 = { "No", "Id", "Status", "Pending"};
		table = new JTable(playerInfo, names_1);
		m.makeFace(table);
		scrollPane.setViewportView(table);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(424, 6, 370, 26);
		frame.getContentPane().add(panel_1);

		JLabel lblUpdateLog = new JLabel("Update Log");
		panel_1.add(lblUpdateLog);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(424, 30, 370, 180);
		frame.getContentPane().add(scrollPane_1);

		String[] names_2 = { "No", "CommitId", "Author"};
		table_1 = new JTable(playerInfo, names_2);
		m.makeFace(table_1);
		scrollPane_1.setViewportView(table_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(6, 222, 369, 26);
		frame.getContentPane().add(panel_2);

		JLabel lblRequirementsText = new JLabel("Requirements Text");
		panel_2.add(lblRequirementsText);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(6, 249, 369, 142);
		frame.getContentPane().add(scrollPane_2);

		JTextArea textArea = new JTextArea();
		scrollPane_2.setViewportView(textArea);

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

		JTextArea textArea_1 = new JTextArea();
		scrollPane_4.setViewportView(textArea_1);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(6, 570, 369, 34);
		frame.getContentPane().add(panel_5);

		JButton btnSave = new JButton("Save");
		panel_5.add(btnSave);
	}
}
