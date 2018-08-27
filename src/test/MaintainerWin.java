package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JLabel;
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
import java.util.Map;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
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

	private JFrame frmRequirementsUpdate;
	private JTable tblCodeElementsList;
	private JTable tblReqElementsList;
	private JTable tableRecommendMethods;
	private String[] codeColumns;
	private Object[][] codeElementsList;
	private String[] reqColumn;

	public Retro retro;
	private JScrollPane scrollPane_1;
	private Object[][] reqElementsList;

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

		JTextArea textAreaReqText = new JTextArea();
		textAreaReqText.setEditable(false);
		textAreaReqText.setLineWrap(true);
		textAreaReqText.setBackground(Color.WHITE);
		DefaultCaret caret = (DefaultCaret)textAreaReqText.getCaret();  
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

		JTextArea textAreaUpdateInfo = new JTextArea();
		textAreaUpdateInfo.setEditable(false);
		textAreaUpdateInfo.setFont(new Font("Monospaced", Font.PLAIN, 13));
		textAreaUpdateInfo.setLineWrap(true);
		textAreaUpdateInfo.setText("Please mark the outdated requirments then you can edit update information here.");
		scrollPaneUpdateInfo.setViewportView(textAreaUpdateInfo);

		JPanel panelSave = new JPanel();
		panelSave.setBounds(423, 574, 371, 43);
		frmRequirementsUpdate.getContentPane().add(panelSave);

		JButton btnSave = new JButton("Save");
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
		tblCodeElementsList.addMouseListener(new MouseAdapter() {
			/*
			 * modified by YHR 表格接收鼠标左、右键点击事件
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					// LEFT MOUSE CLICKED
					int tableRow = tblCodeElementsList.rowAtPoint(e.getPoint());
					System.out.println(tableRow);
				}
				if (e.getButton() == MouseEvent.BUTTON3) {
					// RIGHT MOUSE CLICKED
					int tableRow = tblCodeElementsList.rowAtPoint(e.getPoint());
					System.out.println(tableRow);
				}
			}
		});
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

		JScrollPane scrollPaneRecommendMethods = new JScrollPane();
		scrollPaneRecommendMethods.setBounds(6, 257, 407, 140);
		frmRequirementsUpdate.getContentPane().add(scrollPaneRecommendMethods);

		String[] names_3 = { "No", "Id" };
		tableRecommendMethods = new JTable(new Object[][] {}, names_3);
		tableRecommendMethods.addMouseListener(new MouseAdapter() {
			/*
			 * modified by YHR 表格接收鼠标左、右键点击事件
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					// LEFT MOUSE CLICKED
					int tableRow = tableRecommendMethods.rowAtPoint(e.getPoint());
					System.out.println(tableRow);
				}
				if (e.getButton() == MouseEvent.BUTTON3) {
					// RIGHT MOUSE CLICKED
				}
			}
		});
		makeFace(tableRecommendMethods);
		scrollPaneRecommendMethods.setViewportView(tableRecommendMethods);

		JPanel panelRecomendMethods = new JPanel();
		panelRecomendMethods.setBounds(6, 232, 407, 163);
		frmRequirementsUpdate.getContentPane().add(panelRecomendMethods);

		JLabel lblRecomendMethods = new JLabel("Recommend Methods");
		panelRecomendMethods.add(lblRecomendMethods);

		JScrollPane scrollPaneMethodContent = new JScrollPane();
		scrollPaneMethodContent.setBounds(6, 433, 407, 142);
		frmRequirementsUpdate.getContentPane().add(scrollPaneMethodContent);

		JTextArea textAreaMethodContent = new JTextArea();
		textAreaMethodContent.setEditable(false);
		textAreaMethodContent.setLineWrap(true);
		textAreaMethodContent.setBackground(Color.WHITE);
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
						byte[] b = new byte[1024];//把所有的数据读取到这个字节当中
						f.read(b);
						String str = new String(b);
						str = str.trim();
						textAreaReqText.setText(str);
						textAreaReqText.setSelectionStart(0);
						f.close();
						
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (e.getButton() == MouseEvent.BUTTON3) {
					// RIGHT MOUSE CLICKED
					int tableRow = tblReqElementsList.rowAtPoint(e.getPoint());
					System.out.println(tableRow);
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
