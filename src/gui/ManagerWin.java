package gui;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import gui.MaintainerWin;
import sql.SqlExecuter;

import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ManagerWin {

	private JFrame frmRequirementsUpdate;
	private JTable reqElementsTable;
	private JTable updateLogTable;
	/*
	 * modified by YHR
	 */
	private String path_req;
	private JTextArea reqText;
	private String[] reqColumns;
	private Object[][] reqElementsList;
	private String[] updateLogTableColumn;
	private JTextArea textAreaUpdateInfo;
	private JScrollPane scrollPaneUpdateInfo;
	private JScrollPane scrollPaneUpdateLogTable;
	private JButton btnSave;
	private JTextArea updateReq;
	private String reqName;
	private int tableRow;
	private ArrayList<Object[]> data;
	private Object[][] updateLogList;
	/**
	 * Create the application.
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ManagerWin(String reqPath) throws ClassNotFoundException, SQLException {
		this.path_req = reqPath;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private void initialize() throws SQLException, ClassNotFoundException {
		frmRequirementsUpdate = new JFrame();
		frmRequirementsUpdate.setTitle("Requirements update - manager");
		frmRequirementsUpdate.setBounds(100, 100, 820, 645);
		frmRequirementsUpdate.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmRequirementsUpdate.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 369, 26);
		frmRequirementsUpdate.getContentPane().add(panel);

		JLabel lblNewLabel = new JLabel("Requirement Elements");
		panel.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 30, 369, 180);
		frmRequirementsUpdate.getContentPane().add(scrollPane);

		data = new ArrayList<Object[]>();
		reqColumns = new String[]{ "No", "Id", "Status", "Pending"};
		data = new ArrayList<Object[]>();
		int idx = 1;
		Class.forName("org.sqlite.JDBC");
		Connection c = DriverManager.getConnection("jdbc:sqlite::resource:sql/test.db");
		c.setAutoCommit(false);
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM reqList order by pending DESC, id DESC;");
		while (rs.next()) {
			data.add(new String[] { idx + "", rs.getString("id"), rs.getString("status"), rs.getString("pending")});
			++idx;
		}
	    stmt.close();
	    c.close();
		reqElementsList = new Object[data.size()][4];
		for (int i = 0; i < data.size(); ++i) {
			reqElementsList[i] = data.get(i);
		}
		reqElementsTable = new JTable(reqElementsList, reqColumns);
		reqElementsTable.addMouseListener(new MouseAdapter() {
			private int previewSelectedRow = -1;
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1 && previewSelectedRow != reqElementsTable.rowAtPoint(e.getPoint())){
					//LEFT MOUSE CLICKED
					//updateReq.setEditable(true);
					tableRow = reqElementsTable.rowAtPoint(e.getPoint());
					if (reqElementsList[tableRow][2].equals("Outdated"))
						updateReq.setEditable(true);
					else
						updateReq.setEditable(false);
					System.out.println(tableRow);
					reqName = String.valueOf(reqElementsTable.getValueAt(tableRow, 1));
					System.out.println(reqName);
					try {
						File root = new File(path_req);
						String originReqPath = root.getParentFile().getAbsolutePath() + "/AquaLush_Requirement_Origin/";
						InputStream f = new FileInputStream(originReqPath + reqName);
						byte[] b = new byte[1024];// 把所有的数据读取到这个字节当�?
						f.read(b);
						String str = new String(b);
						str = str.trim();
						reqText.setText(str);
						reqText.setSelectionStart(0);
						f.close();

					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					ArrayList<Object[]> data;
					reqColumns = new String[]{ "No", "Id", "Status", "Pending"};
					data = new ArrayList<Object[]>();
					int idx = 1;
					try {
						Class.forName("org.sqlite.JDBC");
						Connection c = DriverManager.getConnection("jdbc:sqlite::resource:sql/test.db");
						c.setAutoCommit(false);
						Statement stmt = c.createStatement();
						String sql = "SELECT * FROM logList WHERE id = \'"+reqName+"\' ORDER BY date DESC;";
						System.out.println(sql);
						ResultSet rs = stmt.executeQuery(sql);
						while (rs.next()) {
							data.add(new String[] { idx + "", rs.getString("date"), rs.getString("author"), rs.getString("status"), rs.getString("content")});
							++idx;
						}
					    stmt.close();
					    c.close();
					} catch (ClassNotFoundException | SQLException err) {
						err.printStackTrace();
					}

					updateLogList = new Object[data.size()][4];
					for (int i = 0; i < data.size(); ++i) {
						updateLogList[i] = data.get(i);
					}
					updateLogTable = new JTable(updateLogList, updateLogTableColumn);
					updateLogTable.addMouseListener(new MouseAdapter() {
						private int previousSelectedRow = -1;
						@Override
						public void mouseClicked(MouseEvent e) {
							if (e.getButton() == MouseEvent.BUTTON1 && previousSelectedRow != updateLogTable.rowAtPoint(e.getPoint())) {
								// LEFT MOUSE CLICKED
								int tableRow = updateLogTable.rowAtPoint(e.getPoint());
								previousSelectedRow = tableRow;
								System.out.println(updateLogList[tableRow][1]);
								String logContent = updateLogList[tableRow][4].toString();
								textAreaUpdateInfo.setText(logContent);
							}
						}
					});
					MaintainerWin.makeFace(updateLogTable);
					scrollPaneUpdateLogTable.setViewportView(updateLogTable);
				}
			}
		});
		MaintainerWin.makeFace(reqElementsTable);
		scrollPane.setViewportView(reqElementsTable);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(424, 6, 370, 26);
		frmRequirementsUpdate.getContentPane().add(panel_1);

		JLabel lblUpdateLog = new JLabel("Update Log");
		panel_1.add(lblUpdateLog);

		scrollPaneUpdateLogTable = new JScrollPane();
		scrollPaneUpdateLogTable.setBounds(424, 30, 370, 180);
		frmRequirementsUpdate.getContentPane().add(scrollPaneUpdateLogTable);

		updateLogTableColumn = new String[]{ "No", "Date", "Author", "Status"};
		updateLogTable = new JTable(new Object[][] {}, updateLogTableColumn);
		updateLogTable.addMouseListener(new MouseAdapter() {
			private int previousSelectedRow = -1;
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1 && previousSelectedRow != updateLogTable.rowAtPoint(e.getPoint())){
					//LEFT MOUSE CLICKED
					int tableRow = updateLogTable.rowAtPoint(e.getPoint());
					System.out.println(tableRow);
				}
			}
		});
		MaintainerWin.makeFace(updateLogTable);
		scrollPaneUpdateLogTable.setViewportView(updateLogTable);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(6, 222, 369, 26);
		frmRequirementsUpdate.getContentPane().add(panel_2);

		JLabel lblRequirementsText = new JLabel("Requirements Text");
		panel_2.add(lblRequirementsText);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(6, 249, 369, 142);
		frmRequirementsUpdate.getContentPane().add(scrollPane_2);

		reqText = new JTextArea();
		reqText.setEditable(false);
		reqText.setFont(new Font("Arial", Font.PLAIN, 13));
		reqText.setLineWrap(true);
		reqText.setWrapStyleWord(true);
		scrollPane_2.setViewportView(reqText);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(424, 222, 370, 26);
		frmRequirementsUpdate.getContentPane().add(panel_3);

		JLabel lblUpdateInfo = new JLabel("Update Info");
		panel_3.add(lblUpdateInfo);

		scrollPaneUpdateInfo = new JScrollPane();
		scrollPaneUpdateInfo.setBounds(424, 249, 370, 142);
		frmRequirementsUpdate.getContentPane().add(scrollPaneUpdateInfo);

		textAreaUpdateInfo = new JTextArea();
		textAreaUpdateInfo.setEditable(false);
		textAreaUpdateInfo.setFont(new Font("Arial", Font.PLAIN, 13));
		textAreaUpdateInfo.setWrapStyleWord(true);
		textAreaUpdateInfo.setLineWrap(true);
		scrollPaneUpdateInfo.setViewportView(textAreaUpdateInfo);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(6, 403, 369, 26);
		frmRequirementsUpdate.getContentPane().add(panel_4);

		JLabel lblUpdateRequirements = new JLabel("Update Requirements");
		panel_4.add(lblUpdateRequirements);

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(6, 429, 369, 142);
		frmRequirementsUpdate.getContentPane().add(scrollPane_4);

		updateReq = new JTextArea();
		updateReq.setEditable(false);
		updateReq.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println(updateReq.getText());
				System.out.println(updateReq.getText().length());
				if (updateReq.getText().length() != 0)
					btnSave.setEnabled(true);
				else
					btnSave.setEnabled(false);
			}
		});
		updateReq.setFont(new Font("Arial", Font.PLAIN, 13));
		updateReq.setLineWrap(true);
		updateReq.setWrapStyleWord(true);
		scrollPane_4.setViewportView(updateReq);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(6, 570, 369, 34);
		frmRequirementsUpdate.getContentPane().add(panel_5);

		btnSave = new JButton("Save");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
						File root = new File(path_req);
						String reqDocPath = root.getParentFile().getAbsolutePath() + "/AquaLush_Requirement_Origin/" + reqName;
						System.out.print(reqDocPath);
			            File writename = new File(reqDocPath); // 相对路径，如果没有则要建立一个新的output.txt文件
			            writename.createNewFile(); // 创建新文件
			            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
			            out.write(updateReq.getText());
			            out.flush(); // 把缓存区内容压入文件
			            out.close(); // 最后记得关闭文件
			        } catch (IOException event) {
			            event.printStackTrace();
			        }
				String sql = "UPDATE reqList SET status = 'Normal' WHERE id = \'" + reqName + "\';";
				SqlExecuter.process(sql);
				reqElementsList[tableRow][2] = "Normal";
				int newPending = ((int)reqElementsList[tableRow][3] - 1);
				reqElementsList[tableRow][3] = String.valueOf(newPending);
				reqElementsTable.updateUI();
				sql = "UPDATE logList SET status = 'Checked' WHERE id = \'" + reqName + "\';";
				SqlExecuter.process(sql);
				int cntUnchecked = 0;
				for (int i = 0; i < updateLogList.length; i++){
					if(!updateLogList[i][3].equals("Checked"))
						++cntUnchecked;
					updateLogList[i][3] = "Checked";
				}
				sql = "UPDATE reqList SET pending = pending - " + cntUnchecked + " WHERE id = \'" + reqName + "\';";
				SqlExecuter.process(sql);
				updateLogTable.updateUI();
				try {
					File root = new File(path_req);
					String originReqPath = root.getParentFile().getAbsolutePath() + "/AquaLush_Requirement_Origin/";
					InputStream f = new FileInputStream(originReqPath + reqName);
					byte[] b = new byte[1024];
					f.read(b);
					String str = new String(b);
					str = str.trim();
					reqText.setText(str);
					reqText.setSelectionStart(0);
					f.close();

				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				updateReq.setText("");
			}
		});
		btnSave.setEnabled(false);
		panel_5.add(btnSave);
		frmRequirementsUpdate.setVisible(true);
	}
}
