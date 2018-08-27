package test;

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
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class MaintainerWin {

	public static void makeFace(JTable table) {
		try {
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
						boolean hasFocus, int row, int column) {
					if (row % 2 == 0)
						setBackground(Color.white); // 璁剧疆濂囨暟琛屽簳鑹�
					else if (row % 2 == 1)
						setBackground(new Color(206, 231, 255)); // 璁剧疆鍋舵暟琛屽簳鑹�
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
	private JTextArea textAreaUpdateInfo;
	private JButton btnSave;
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
				textAreaUpdateInfo.setEditable(true);
				textAreaUpdateInfo.setText("");
			}
		});
		m_popupMenu.add(menItem_1);

		JMenuItem menItem_2 = new JMenuItem();
		menItem_2.setText("Methods recommand");
		menItem_2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				// the function for 'Methods recommand'
				String req = reqElementsList[row][2].toString();
				ArrayList<Object[]> data = new ArrayList<Object[]>();
				List<String> recommendList = retro.recommendMethodsForRequirements.get(req);
				int index = 1;
				for (String method : recommendList) {
					data.add(new String[]{
						index + "",
						method
					});
					++index;
				}
				recommendMethodList = new Object[data.size()][2];
				for (int i = 0; i < data.size(); ++i) {
					recommendMethodList[i] = data.get(i);
				}
				tblRecommendMethods = new JTable(recommendMethodList, tblRecommendMethodsColumns);
				tblRecommendMethods.addMouseListener(new MouseAdapter() {
					private int previousSelectedRow;
					/*
					 * modified by YHR 表格接收鼠标左、右键点击事件
					 */
					@Override
					public void mouseClicked(MouseEvent e) {
						if (e.getButton() == MouseEvent.BUTTON1 && previousSelectedRow != tblRecommendMethods.rowAtPoint(e.getPoint())) {
							// LEFT MOUSE CLICKED
							int tableRow = tblRecommendMethods.rowAtPoint(e.getPoint());
							previousSelectedRow = tableRow;
							System.out.println(recommendMethodList[tableRow][1]);
							String selectedMethod = recommendMethodList[tableRow][1].toString();
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
	private JScrollPane scrollPane_1;
	private Object[][] reqElementsList;
	private String[] tblRecommendMethodsColumns;
	private JScrollPane scrollPaneRecommendMethods;
	private JTextArea textAreaReqText;
	private JTextArea textAreaMethodContent;

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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmRequirementsUpdate = new JFrame();
		frmRequirementsUpdate.setTitle("Requirements update - maintainer");
		frmRequirementsUpdate.setBounds(100, 100, 836, 663);
		frmRequirementsUpdate.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		textAreaReqText.setFont(new Font("Monospaced", Font.PLAIN, 13));
		textAreaReqText.setWrapStyleWord(true);
		textAreaReqText.setEditable(false);
		textAreaReqText.setLineWrap(true);
		textAreaReqText.setBackground(Color.WHITE);
		DefaultCaret caret = (DefaultCaret) textAreaReqText.getCaret();
		caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
		scrollPaneReq.setViewportView(textAreaReqText);

		JPanel panelUpdateInfo = new JPanel();
		panelUpdateInfo.setBounds(423, 407, 371, 26);
		frmRequirementsUpdate.getContentPane().add(panelUpdateInfo);

		JLabel lblUpdateInfo = new JLabel("Update Info");
		panelUpdateInfo.add(lblUpdateInfo);

		JScrollPane scrollPaneUpdateInfo = new JScrollPane();
		scrollPaneUpdateInfo.setEnabled(false);
		scrollPaneUpdateInfo.setBounds(423, 433, 371, 142);
		frmRequirementsUpdate.getContentPane().add(scrollPaneUpdateInfo);

		textAreaUpdateInfo = new JTextArea();
		textAreaUpdateInfo.setWrapStyleWord(true);
		textAreaUpdateInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				String content = textAreaUpdateInfo.getText();
				if (!content.equals("") && !content
						.equals("Please mark the outdated requirments then you can edit update information here.")) {
					btnSave.setEnabled(true);
				}
			}
		});
		textAreaUpdateInfo.setEditable(false);
		textAreaUpdateInfo.setFont(new Font("Monospaced", Font.PLAIN, 13));
		textAreaUpdateInfo.setLineWrap(true);
		textAreaUpdateInfo.setText("Please mark the outdated requirments then you can edit update information here.");
		scrollPaneUpdateInfo.setViewportView(textAreaUpdateInfo);

		JPanel panelSave = new JPanel();
		panelSave.setBounds(423, 574, 371, 43);
		frmRequirementsUpdate.getContentPane().add(panelSave);

		btnSave = new JButton("Save");
		btnSave.setEnabled(false);
		panelSave.add(btnSave);

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
		for (Map.Entry<String, Double> map : retro.reqElementList) {
			data.add(new String[] { idx + "", String.valueOf(map.getValue()), map.getKey(), "Normal" });
			++idx;
		}
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

		JLabel lblRecomendMethods = new JLabel("Recommend Methods");
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
			private int previousSelectRow;

			/*
			 * modified by YHR 表格接收鼠标左、右键点击事件
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1 && previousSelectRow != tblReqElementsList.getSelectedRow()) {
					// LEFT MOUSE CLICKED
					previousSelectRow = tblReqElementsList.getSelectedRow();
					int tableRow = tblReqElementsList.getSelectedRow();
					String reqName = String.valueOf(tblReqElementsList.getValueAt(tableRow, 2));
					System.out.println(reqName);
					try {
						InputStream f = new FileInputStream("data/sample/AquaLush_Requirement_Origin/" + reqName);
						byte[] b = new byte[1024];// 把所有的数据读取到这个字节当中
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

		JLabel lblRequirmentsText = new JLabel("Requirements Text");
		panelRequirmentsText.add(lblRequirmentsText);

		JPanel panelMethodContent = new JPanel();
		panelMethodContent.setBounds(6, 407, 407, 28);
		frmRequirementsUpdate.getContentPane().add(panelMethodContent);

		JLabel lblMethodContent = new JLabel("Method Content");
		panelMethodContent.add(lblMethodContent);
		frmRequirementsUpdate.setVisible(true);
	}

}
