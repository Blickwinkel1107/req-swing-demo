package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.DefaultCaret;

import edu.nju.cs.inform.core.type.CodeElementChange;
import retro.Retro;
import sql.SqlExecuter;
import gui.UpdateSaved;
import gui.UpdateLog;
import gui.ShowLog;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MaintainerWin {

	public static void makeFace(JTable table) {
		try {
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
				private static final long serialVersionUID = 1L;

				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
						boolean hasFocus, int row, int column) {
					if (row % 2 == 0)
						setBackground(Color.white); // 璁剧疆濂囨暟琛屽簳鑹�?
					else if (row % 2 == 1)
						setBackground(new Color(206, 231, 255)); // 璁剧疆鍋舵暟琛屽簳鑹�?
					return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				}
			};
			for (int i = 0; i < table.getColumnCount(); i++) {
				table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/*
	 * modified by YHR
	 */
	private JPopupMenu m_popupMenu;
	private int row;
	private JButton btnAddUpdateLog;
	protected Object[][] recommendMethodList;

	// the functions for the popup-menu
	protected void createPopupMenu() {
		m_popupMenu = new JPopupMenu();

		JMenuItem menItem_1 = new JMenuItem();
		menItem_1.setText("Mark as outdated");
		menItem_1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				// System.out.print(Arrays.toString(reqElementsList[row]));
				reqElementsList[row][3] = "Outdated";

				tblReqElementsList.updateUI();
				//textAreaUpdateInfo.setEditable(true);
				//textAreaUpdateInfo.setText("");
				btnAddUpdateLog.setEnabled(true);
				String reqName = String.valueOf(tblReqElementsList.getValueAt(row, 2));
				System.out.println(reqName);
				String sql = "UPDATE reqList SET status = \'Outdated\' WHERE id = \'" + reqName + "\';";
				SqlExecuter.process(sql);
			}
		});
		m_popupMenu.add(menItem_1);

		JMenuItem menItem_2 = new JMenuItem();
		menItem_2.setText("Methods recommend");
		menItem_2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				// the function for 'Methods recommand'
				String reqName = reqElementsList[row][2].toString();
				lblRecomendMethods.setText("Recommend Methods for " + reqName); // change title
				ArrayList<Object[]> data = new ArrayList<Object[]>();
				List<String> recommendList = retro.recommendMethodsForRequirements.get(reqName);
				int index = 1;
				for (String method : recommendList) {
					data.add(new String[] { index + "", method });
					++index;
				}
				recommendMethodList = new Object[data.size()][2];
				for (int i = 0; i < data.size(); ++i) {
					recommendMethodList[i] = data.get(i);
				}
				tblRecommendMethods = new JTable(recommendMethodList, tblRecommendMethodsColumns);
				tblRecommendMethods.addMouseListener(new MouseAdapter() {
					private int previousSelectedRow = -1;

					/*
					 * modified by YHR 表格接收鼠标左�?�右键点击事�?
					 */
					@Override
					public void mouseClicked(MouseEvent e) {
						if (e.getButton() == MouseEvent.BUTTON1
								&& previousSelectedRow != tblRecommendMethods.rowAtPoint(e.getPoint())) {
							// LEFT MOUSE CLICKED
							int tableRow = tblRecommendMethods.rowAtPoint(e.getPoint());
							previousSelectedRow = tableRow;
							System.out.println(recommendMethodList[tableRow][1]);
							String selectedMethod = recommendMethodList[tableRow][1].toString();
							lblMethodContent.setText("Method Content -- " + selectedMethod); //change title
							String methodBody = retro.recommentMethodsBodyCollection.get(selectedMethod);
							textAreaMethodContent.setText(methodBody);
						}
					}
				});
				makeFace(tblRecommendMethods);
				scrollPaneRecommendMethods.setViewportView(tblRecommendMethods);
			}
		});
		m_popupMenu.add(menItem_2);
	}

	private JFrame frmRequirementsUpdate;
	private JTable tblCodeElementsList;
	private JTable tblReqElementsList;
	private JTable tblRecommendMethods;
	private String[] codeColumns;
	private Object[][] codeElementsList;
	private String[] reqColumn;

	public Retro retro;
	private Object[][] reqElementsList;
	private String[] tblRecommendMethodsColumns;
	private JScrollPane scrollPaneRecommendMethods;
	private JTextArea textAreaReqText;
	private JTextArea textAreaMethodContent;
	private JTable tblUpdateLog;
	private String[] updateLogColumns;
	private JLabel lblRecomendMethods;
	private JLabel lblRequirmentsText;
	private JLabel lblMethodContent;
	private JLabel lblUpdateLog;
	public String logContent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MaintainerWin window = new MaintainerWin(null);
					window.frmRequirementsUpdate.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MaintainerWin(Retro retro) {
		this.retro = retro;
		try {
			initialize();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws SQLException, ClassNotFoundException {

		frmRequirementsUpdate = new JFrame();
		frmRequirementsUpdate.setTitle("Requirements update - maintainer");
		frmRequirementsUpdate.setBounds(100, 100, 836, 663);
		frmRequirementsUpdate.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmRequirementsUpdate.getContentPane().setLayout(null);

		JPanel panelDifferingCodeElements = new JPanel();
		panelDifferingCodeElements.setBounds(6, 6, 407, 28);
		frmRequirementsUpdate.getContentPane().add(panelDifferingCodeElements);

		JLabel lblDifferingCodeElements = new JLabel("Differing Code Elements");
		panelDifferingCodeElements.add(lblDifferingCodeElements);

		JPanel panelReqElements = new JPanel();
		panelReqElements.setBounds(423, 6, 371, 28);
		frmRequirementsUpdate.getContentPane().add(panelReqElements);

		JLabel lblReqElements = new JLabel("Requirement Elements");
		panelReqElements.add(lblReqElements);

		JScrollPane scrollPaneReq = new JScrollPane();
		scrollPaneReq.setBounds(423, 257, 371, 138);
		frmRequirementsUpdate.getContentPane().add(scrollPaneReq);

		textAreaReqText = new JTextArea();
		textAreaReqText.setFont(new Font("Arial", Font.PLAIN, 13));
		textAreaReqText.setWrapStyleWord(true);
		textAreaReqText.setEditable(false);
		textAreaReqText.setLineWrap(true);
		textAreaReqText.setBackground(Color.WHITE);
		DefaultCaret caret = (DefaultCaret) textAreaReqText.getCaret();
		caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
		scrollPaneReq.setViewportView(textAreaReqText);

		JPanel panelUpdateLog = new JPanel();
		panelUpdateLog.setBounds(423, 407, 371, 26);
		frmRequirementsUpdate.getContentPane().add(panelUpdateLog);

		lblUpdateLog = new JLabel("Update Log");
		panelUpdateLog.add(lblUpdateLog);

		JScrollPane scrollPaneUpdateLogTable = new JScrollPane();
		scrollPaneUpdateLogTable.setBounds(423, 433, 371, 142);
		frmRequirementsUpdate.getContentPane().add(scrollPaneUpdateLogTable);


		updateLogColumns = new String[] { "No", "Date", "Author", "Status"};
		tblUpdateLog = new JTable(new Object[][] {}, updateLogColumns);
		scrollPaneUpdateLogTable.setViewportView(tblUpdateLog);

		JPanel panelSave = new JPanel();
		panelSave.setBounds(423, 574, 371, 43);
		frmRequirementsUpdate.getContentPane().add(panelSave);

		btnAddUpdateLog = new JButton("Add update log");
		btnAddUpdateLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddUpdateLog.setBounds(21, 6, 140, 29);
		btnAddUpdateLog.addMouseListener(new MouseAdapter() {
			@Override
			/*
			 * (non-Javadoc)
			 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
			 * modified by YHR
			 */
			public void mouseClicked(MouseEvent e) {
				String reqName = lblRequirmentsText.getText().split("for")[1].trim();
				UpdateLog pop_upUpdate = new UpdateLog(reqName);
			}
		});

		/*btnAddUpdateLog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String updateInfo = textAreaUpdateInfo.getText();
				boolean notEmpty = updateInfo.equals("") ? false : true;
				new UpdateSaved(notEmpty);
				if (notEmpty) {
					String reqName = String.valueOf(tblReqElementsList.getValueAt(row, 2));
					String sql = "UPDATE reqList SET pending = pending + 1 WHERE id = \'" + reqName + "\';";
					SqlExecuter.process(sql);
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
					sql = "INSERT INTO logList VALUES(\'" + reqName + "\', \'" + df.format(new Date()) + "\', \'"
							+ Login.getUserName() + "\', \'" + updateInfo + "\')";
					SqlExecuter.process(sql);
				}
				textAreaUpdateInfo.setText("");
			}
		});*/
		panelSave.setLayout(null);
		btnAddUpdateLog.setEnabled(false);
		panelSave.add(btnAddUpdateLog);

		JButton btnShowLogInfo = new JButton("Show Log Info");
		btnShowLogInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ShowLog logInfo = new ShowLog(logContent);
				//System.out.println(logContent);
			}
		});
		btnShowLogInfo.setBounds(226, 6, 117, 29);
		btnShowLogInfo.setEnabled(false);
		panelSave.add(btnShowLogInfo);

		JScrollPane scrollPaneCodeElementsList = new JScrollPane();
		scrollPaneCodeElementsList.setBounds(6, 35, 407, 185);
		frmRequirementsUpdate.getContentPane().add(scrollPaneCodeElementsList);

		codeColumns = new String[] { "No", "Id", "Type", "Changed" };
		// codeElementsList = new Object[4][4];
		int idx;
		ArrayList<Object[]> data;
		data = new ArrayList<Object[]>();
		idx = 1;
		for (CodeElementChange elementChange : retro.codeElementChangeList) {
			data.add(new String[] { idx + "", elementChange.getElementName().toString(),
					elementChange.getElementType().toString(), elementChange.getChangeType().toString() });
			++idx;
		}
		codeElementsList = new Object[data.size()][4];
		for (int i = 0; i < data.size(); ++i) {
			codeElementsList[i] = data.get(i);
		}
		tblCodeElementsList = new JTable(codeElementsList, codeColumns);
		// tblCodeElementsList.isCellEditable(row, column)
		makeFace(tblCodeElementsList);
		scrollPaneCodeElementsList.setViewportView(tblCodeElementsList);

		idx = 1;
		reqColumn = new String[] { "No", "Score", "Id", "Status" };
		data = new ArrayList<Object[]>();
		idx = 1;
		Class.forName("org.sqlite.JDBC");
		Connection c = DriverManager.getConnection("jdbc:sqlite::resource:sql/test.db");
		c.setAutoCommit(false);
		Statement stmt = c.createStatement();
		ResultSet rs;
		for (Map.Entry<String, Double> map : retro.reqElementList) {
			String reqName = map.getKey();
			rs = stmt.executeQuery("SELECT status FROM reqList WHERE id = \'"+reqName+"\';");
			data.add(new String[] { idx + "", String.valueOf(map.getValue()), reqName, rs.getString(1)});
			++idx;
		}
	    stmt.close();
	    c.close();
		reqElementsList = new Object[data.size()][4];
		for (int i = 0; i < data.size(); ++i) {
			reqElementsList[i] = data.get(i);
		}

		scrollPaneRecommendMethods = new JScrollPane();
		scrollPaneRecommendMethods.setBounds(6, 257, 407, 140);
		frmRequirementsUpdate.getContentPane().add(scrollPaneRecommendMethods);

		tblRecommendMethodsColumns = new String[] { "No", "Id" };
		tblRecommendMethods = new JTable(new Object[][] {}, tblRecommendMethodsColumns);
		makeFace(tblRecommendMethods);
		scrollPaneRecommendMethods.setViewportView(tblRecommendMethods);

		JPanel panelRecomendMethods = new JPanel();
		panelRecomendMethods.setBounds(6, 232, 407, 163);
		frmRequirementsUpdate.getContentPane().add(panelRecomendMethods);

		lblRecomendMethods = new JLabel("Recommend Methods");
		panelRecomendMethods.add(lblRecomendMethods);

		JScrollPane scrollPaneMethodContent = new JScrollPane();
		scrollPaneMethodContent.setBounds(6, 433, 407, 142);
		frmRequirementsUpdate.getContentPane().add(scrollPaneMethodContent);

		textAreaMethodContent = new JTextArea();
		textAreaMethodContent.setFont(new Font("Consolas", Font.PLAIN, 11));
		textAreaMethodContent.setWrapStyleWord(true);
		textAreaMethodContent.setEditable(false);
		textAreaMethodContent.setLineWrap(true);
		textAreaMethodContent.setBackground(Color.WHITE);
		caret = (DefaultCaret) textAreaMethodContent.getCaret();
		caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
		scrollPaneMethodContent.setViewportView(textAreaMethodContent);

		JScrollPane scrollPaneReqElementsList = new JScrollPane();
		scrollPaneReqElementsList.setBounds(423, 35, 371, 185);
		frmRequirementsUpdate.getContentPane().add(scrollPaneReqElementsList);
		tblReqElementsList = new JTable(reqElementsList, reqColumn);
		tblReqElementsList.addMouseListener(new MouseAdapter() {
			private int previousSelectRow = -1;
			private Object[][] updateLogList;

			/*
			 * modified by YHR 表格接收鼠标左�?�右键点击事�?
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1 && previousSelectRow != tblReqElementsList.getSelectedRow()) {
					// LEFT MOUSE CLICKED
					previousSelectRow = tblReqElementsList.getSelectedRow();
					int tableRow = tblReqElementsList.getSelectedRow();
					boolean isNormal = String.valueOf(tblReqElementsList.getValueAt(tableRow, 3)).equals("Normal");
					if(!isNormal){
						btnAddUpdateLog.setEnabled(true);
					}
					else
						btnAddUpdateLog.setEnabled(false);
					/*if (isNormal) {
						textAreaUpdateInfo.setEditable(false);
						textAreaUpdateInfo.setText(
								"Please mark the outdated requirments then you can edit update information here.");
						btnAddUpdateLog.setEnabled(false);
					} else {
						textAreaUpdateInfo.setEditable(true);
						textAreaUpdateInfo.setText("");
					}*/
					String reqName = String.valueOf(tblReqElementsList.getValueAt(tableRow, 2));
					lblRequirmentsText.setText("Requirements Text for " + reqName); // change title
					if(isNormal)
						lblUpdateLog.setText(reqName + " is up-to-date"); // change title
					else
						lblUpdateLog.setText("Update Logs for " + reqName); // change title
					System.out.println(reqName);
					try {
						File root = new File(retro.requirement_Path);
						String originReqPath = root.getParentFile().getAbsolutePath() + "/AquaLush_Requirement_Origin/";
						InputStream f = new FileInputStream(originReqPath + reqName);
						byte[] b = new byte[1024];// 把所有的数据读取到这个字节当�?
						f.read(b);
						String str = new String(b);
						str = str.trim();
						textAreaReqText.setText(str);
						textAreaReqText.setSelectionStart(0);
						f.close();

					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					ArrayList<Object[]> data;
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

					updateLogList = new Object[data.size()][5];
					for (int i = 0; i < data.size(); ++i) {
						updateLogList[i] = data.get(i);
					}
					tblUpdateLog = new JTable(updateLogList, updateLogColumns);
					tblUpdateLog.addMouseListener(new MouseAdapter() {
						private int previousSelectedRow = -1;
						@Override
						public void mouseClicked(MouseEvent e) {
							if (e.getButton() == MouseEvent.BUTTON1 && previousSelectedRow != tblUpdateLog.rowAtPoint(e.getPoint())) {
								// LEFT MOUSE CLICKED
								int tableRow = tblUpdateLog.rowAtPoint(e.getPoint());
								previousSelectedRow = tableRow;
								//System.out.println(updateLogList[tableRow][1]);
								logContent = updateLogList[tableRow][4].toString();
								if (logContent.equals(""))
									btnShowLogInfo.setEnabled(false);
								else
									btnShowLogInfo.setEnabled(true);
								//System.out.println(logContent);
							}
						}
					});
					MaintainerWin.makeFace(tblUpdateLog);
					scrollPaneUpdateLogTable.setViewportView(tblUpdateLog);
				}
				if (e.getButton() == MouseEvent.BUTTON3) {
					int focusedRowIndex = tblReqElementsList.rowAtPoint(e.getPoint());
					if (focusedRowIndex == -1) {
						return;
					}
					row = focusedRowIndex;
					tblReqElementsList.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
					m_popupMenu.show(tblReqElementsList, e.getX(), e.getY());
				}
			}
		});
		makeFace(tblReqElementsList);
		scrollPaneReqElementsList.setViewportView(tblReqElementsList);

		JPanel panelRequirmentsText = new JPanel();
		panelRequirmentsText.setBounds(423, 232, 371, 28);
		frmRequirementsUpdate.getContentPane().add(panelRequirmentsText);

		lblRequirmentsText = new JLabel("Requirements Text");
		panelRequirmentsText.add(lblRequirmentsText);

		JPanel panelMethodContent = new JPanel();
		panelMethodContent.setBounds(6, 407, 407, 28);
		frmRequirementsUpdate.getContentPane().add(panelMethodContent);

		lblMethodContent = new JLabel("Method Content");
		panelMethodContent.add(lblMethodContent);
		frmRequirementsUpdate.setVisible(true);
	}
}
