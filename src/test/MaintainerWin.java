package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
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
import java.util.Arrays;
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
	
	/*
	 * modified by YHR
	 */
	private JPopupMenu m_popupMenu;
	private int row;
	private JTextArea textAreaUpdateInfo;
	private JButton btnSave;
	//the functions for the popup-menu
	protected void createPopupMenu() {
        m_popupMenu = new JPopupMenu();
        
        JMenuItem menItem_1 = new JMenuItem();
        menItem_1.setText("Mark as outdate");
        menItem_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	//System.out.print(Arrays.toString(reqElementsList[row]));
            	reqElementsList[row][3] = "Outdate";
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
            }
        });
        m_popupMenu.add(menItem_2);
    }

	private JFrame frmRequirementsUpdate;
	private JTable tblCodeElementsList;
	private JTable tblReqElementsList;
	private JTable table_3;
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

		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 407, 28);
		frmRequirementsUpdate.getContentPane().add(panel);

		JLabel lblDifferingCodeElements = new JLabel("Differing Code Elements");
		panel.add(lblDifferingCodeElements);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(423, 6, 371, 28);
		frmRequirementsUpdate.getContentPane().add(panel_1);

		JLabel lblNewLabel = new JLabel("Requirement Elements");
		panel_1.add(lblNewLabel);

		JScrollPane scrollPaneReq = new JScrollPane();
		scrollPaneReq.setBounds(423, 285, 371, 110);
		frmRequirementsUpdate.getContentPane().add(scrollPaneReq);

		JTextArea textAreaReqText = new JTextArea();
		textAreaReqText.setEditable(false);
		textAreaReqText.setLineWrap(true);
		textAreaReqText.setBackground(Color.WHITE);
		DefaultCaret caret = (DefaultCaret)textAreaReqText.getCaret();  
        caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);  
		scrollPaneReq.setViewportView(textAreaReqText);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(423, 407, 371, 26);
		frmRequirementsUpdate.getContentPane().add(panel_4);

		JLabel lblUpdateInfo = new JLabel("Update Info");
		panel_4.add(lblUpdateInfo);

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setEnabled(false);
		scrollPane_4.setBounds(423, 433, 371, 142);
		frmRequirementsUpdate.getContentPane().add(scrollPane_4);

		textAreaUpdateInfo = new JTextArea();
		textAreaUpdateInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				String content = textAreaUpdateInfo.getText();
				if (!content.equals("") && !content.equals("Please mark the outdated requirments then you can edit update information here.")) {
					btnSave.setEnabled(true);
				}
			}
		});
		textAreaUpdateInfo.setEditable(false);
		textAreaUpdateInfo.setFont(new Font("Monospaced", Font.PLAIN, 13));
		textAreaUpdateInfo.setLineWrap(true);
		textAreaUpdateInfo.setText("Please mark the outdated requirments then you can edit update information here.");
		scrollPane_4.setViewportView(textAreaUpdateInfo);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(423, 574, 371, 43);
		frmRequirementsUpdate.getContentPane().add(panel_5);

		btnSave = new JButton("Save");
		btnSave.setEnabled(false);
		panel_5.add(btnSave);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 35, 407, 223);
		frmRequirementsUpdate.getContentPane().add(scrollPane);

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
		scrollPane.setViewportView(tblCodeElementsList);

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

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(6, 435, 407, 140);
		frmRequirementsUpdate.getContentPane().add(scrollPane_2);

		String[] names_3 = { "No", "Id" };
		table_3 = new JTable(new Object[][] {}, names_3);
		table_3.addMouseListener(new MouseAdapter() {
			/*
			 * modified by YHR 表格接收鼠标左、右键点击事件
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					// LEFT MOUSE CLICKED
					int tableRow = table_3.rowAtPoint(e.getPoint());
					System.out.println(tableRow);
				}
				if (e.getButton() == MouseEvent.BUTTON3) {
					// RIGHT MOUSE CLICKED
				}
			}
		});
		makeFace(table_3);
		scrollPane_2.setViewportView(table_3);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(6, 407, 407, 168);
		frmRequirementsUpdate.getContentPane().add(panel_2);

		JLabel lblRecomand = new JLabel("Recommend Method");
		panel_2.add(lblRecomand);

		JPanel panel_6 = new JPanel();
		panel_6.setBounds(6, 257, 407, 28);
		frmRequirementsUpdate.getContentPane().add(panel_6);

		JLabel lblMethodContent = new JLabel("Method Content");
		panel_6.add(lblMethodContent);

		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(6, 285, 407, 110);
		frmRequirementsUpdate.getContentPane().add(scrollPane_5);

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setBackground(Color.WHITE);
		scrollPane_5.setViewportView(textArea);

		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setBounds(423, 35, 371, 223);
		frmRequirementsUpdate.getContentPane().add(scrollPane_1_1);
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
		scrollPane_1_1.setViewportView(tblReqElementsList);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(423, 257, 371, 28);
		frmRequirementsUpdate.getContentPane().add(panel_3);

		JLabel lblRequirmentsText = new JLabel("Requirements Text");
		panel_3.add(lblRequirmentsText);
		frmRequirementsUpdate.setVisible(true);
	}

}
