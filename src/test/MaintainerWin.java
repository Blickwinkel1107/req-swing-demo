package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;

import edu.nju.cs.inform.core.type.CodeElementChange;
import retro.Retro;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Map;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MaintainerWin {

	public static void makeFace(JTable table) {
		   try {
		    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
		     public Component getTableCellRendererComponent(JTable table,
		       Object value, boolean isSelected, boolean hasFocus,
		       int row, int column) {
		      if (row % 2 == 0)
		       setBackground(Color.white); // 璁剧疆濂囨暟琛屽簳鑹�
		      else if (row % 2 == 1)
		       setBackground(new Color(206, 231, 255)); // 璁剧疆鍋舵暟琛屽簳鑹�
		      return super.getTableCellRendererComponent(table, value,
		        isSelected, hasFocus, row, column);
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

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(6, 257, 407, 28);
		frmRequirementsUpdate.getContentPane().add(panel_2);

		JLabel lblRecomand = new JLabel("Recommend Method");
		panel_2.add(lblRecomand);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(423, 257, 371, 28);
		frmRequirementsUpdate.getContentPane().add(panel_3);

		JLabel lblRequirmentsText = new JLabel("Requirements Text");
		panel_3.add(lblRequirmentsText);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(423, 285, 371, 110);
		frmRequirementsUpdate.getContentPane().add(scrollPane_3);

		JTextArea textAreaReqText = new JTextArea();
		textAreaReqText.setLineWrap(true);
		textAreaReqText.setBackground(Color.WHITE);
		scrollPane_3.setViewportView(textAreaReqText);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(423, 407, 371, 26);
		frmRequirementsUpdate.getContentPane().add(panel_4);

				JTextArea textArea_1 = new JTextArea();
				panel_4.add(textArea_1);
				textArea_1.setBackground(Color.WHITE);

		JLabel lblUpdateInfo = new JLabel("Update Info");
		panel_4.add(lblUpdateInfo);

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setEnabled(false);
		scrollPane_4.setBounds(423, 433, 371, 142);
		frmRequirementsUpdate.getContentPane().add(scrollPane_4);
		
		JTextArea textAreaUpdateInfo = new JTextArea();
		textAreaUpdateInfo.setEditable(false);
		textAreaUpdateInfo.setFont(new Font("Monospaced", Font.PLAIN, 13));
		textAreaUpdateInfo.setLineWrap(true);
		textAreaUpdateInfo.setText("Please mark the outdated requirments then you can edit update information here.");
		scrollPane_4.setViewportView(textAreaUpdateInfo);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(423, 574, 371, 43);
		frmRequirementsUpdate.getContentPane().add(panel_5);

		JButton btnSave = new JButton("Save");
		btnSave.setEnabled(false);
		panel_5.add(btnSave);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 35, 407, 223);
		frmRequirementsUpdate.getContentPane().add(scrollPane);

		codeColumns = new String[]{ "No", "Id", "Type", "Changed"};
		//codeElementsList = new Object[4][4];
		int idx;
		ArrayList<Object[]> data; 
		data = new ArrayList<Object[]>();
		idx = 1;
		for (CodeElementChange elementChange : retro.codeElementChangeList) {
			data.add(new String[]{
				idx + "",
				elementChange.getElementName().toString(),
				elementChange.getElementType().toString(),
				elementChange.getChangeType().toString()
				});
			++idx;
        }
		codeElementsList = new Object[data.size()][4];
		for(int i = 0; i < data.size(); ++i){
			codeElementsList[i] = data.get(i);
		}
		tblCodeElementsList = new JTable(codeElementsList, codeColumns);
		tblCodeElementsList.addMouseListener(new MouseAdapter() {
			/*
			 modified by YHR
			 表格接收鼠标左、右键点击事件
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1){
					//LEFT MOUSE CLICKED
					int tableRow = tblCodeElementsList.rowAtPoint(e.getPoint());
					System.out.print(tableRow);
				}
				if(e.getButton() == MouseEvent.BUTTON3) {
					//RIGHT MOUSE CLICKED
				}
			}	
		});
		makeFace(tblCodeElementsList);
		scrollPane.setViewportView(tblCodeElementsList);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(423, 35, 371, 223);
		frmRequirementsUpdate.getContentPane().add(scrollPane_1);
		
		idx = 1;
		reqColumn = new String[]{"No", "Score", "Id", "Status"};
		data = new ArrayList<Object[]>();
		idx = 1;
		for (Map.Entry<String, Double> map : retro.reqElementList) {
			data.add(new String[]{
				idx + "",
				String.valueOf(map.getValue()),
				map.getKey(),
				"Normal"
				});
			++idx;
        }
		reqElementsList = new Object[data.size()][4];
		for(int i = 0; i < data.size(); ++i){
			reqElementsList[i] = data.get(i);
		}
		tblReqElementsList = new JTable(reqElementsList, reqColumn);
		tblReqElementsList.addMouseListener(new MouseAdapter() {
			/*
			 modified by YHR
			 表格接收鼠标左、右键点击事件
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1){
					//LEFT MOUSE CLICKED
					int tableRow = tblReqElementsList.rowAtPoint(e.getPoint());
					System.out.print(tableRow);
				}
				if(e.getButton() == MouseEvent.BUTTON3) {
					//RIGHT MOUSE CLICKED
				}
			}	
		});
		makeFace(tblReqElementsList);
		scrollPane_1.setViewportView(tblReqElementsList);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(6, 285, 407, 110);
		frmRequirementsUpdate.getContentPane().add(scrollPane_2);

		String[] names_3 = {"No", "Id"};
		table_3 = new JTable(new Object[][]{}, names_3);
		table_3.addMouseListener(new MouseAdapter() {
			/*
			 modified by YHR
			 表格接收鼠标左、右键点击事件
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1){
					//LEFT MOUSE CLICKED
					int tableRow = table_3.rowAtPoint(e.getPoint());
					System.out.print(tableRow);
				}
				if(e.getButton() == MouseEvent.BUTTON3) {
					//RIGHT MOUSE CLICKED
				}
			}	
		});
		makeFace(table_3);
		scrollPane_2.setViewportView(table_3);
		frmRequirementsUpdate.setVisible(true);
	}
	
}
