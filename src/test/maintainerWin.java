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

public class maintainerWin {

	public static void makeFace(JTable table) {

		   try {
		    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
		     public Component getTableCellRendererComponent(JTable table,
		       Object value, boolean isSelected, boolean hasFocus,
		       int row, int column) {
		      if (row % 2 == 0)
		       setBackground(Color.white); // 设置奇数行底色
		      else if (row % 2 == 1)
		       setBackground(new Color(206, 231, 255)); // 设置偶数行底色
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

	private JFrame frame;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					maintainerWin window = new maintainerWin();
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
	public maintainerWin() {
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
		panel.setBounds(6, 6, 343, 28);
		frame.getContentPane().add(panel);

		JLabel lblDifferingCodeElements = new JLabel("Differing Code Elements");
		panel.add(lblDifferingCodeElements);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(423, 6, 371, 28);
		frame.getContentPane().add(panel_1);

		JLabel lblNewLabel = new JLabel("Requirement Elements");
		panel_1.add(lblNewLabel);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(6, 257, 343, 28);
		frame.getContentPane().add(panel_2);

		JLabel lblRecomand = new JLabel("Recommend Method");
		panel_2.add(lblRecomand);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(423, 257, 371, 28);
		frame.getContentPane().add(panel_3);

		JLabel lblRequirmentsText = new JLabel("Requirements Text");
		panel_3.add(lblRequirmentsText);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(423, 285, 371, 110);
		frame.getContentPane().add(scrollPane_3);

		JTextArea textArea = new JTextArea();
		textArea.setBackground(Color.WHITE);
		scrollPane_3.setViewportView(textArea);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(423, 407, 371, 26);
		frame.getContentPane().add(panel_4);

				JTextArea textArea_1 = new JTextArea();
				panel_4.add(textArea_1);
				textArea_1.setBackground(Color.WHITE);

		JLabel lblUpdateInfo = new JLabel("Update Info");
		panel_4.add(lblUpdateInfo);

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(423, 433, 371, 142);
		frame.getContentPane().add(scrollPane_4);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(423, 574, 371, 43);
		frame.getContentPane().add(panel_5);

		JButton btnSave = new JButton("Save");
		panel_5.add(btnSave);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 35, 343, 223);
		frame.getContentPane().add(scrollPane);

		Object[][] playerInfo = {
	            // 创建表格中的数据
	            { "", "", "", ""},
	            { "", "", "", ""},
	            { "", "", "", ""},
	            { "", "", "", ""},
	            { "", "", "", ""}};
		String[] names_1 = { "No", "Id", "Type", "Changed"};
		table_1 = new JTable(playerInfo, names_1);
		makeFace(table_1);
		scrollPane.setViewportView(table_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(423, 35, 371, 223);
		frame.getContentPane().add(scrollPane_1);

		String[] names_2 = {"No", "Score", "Id", "Status"};
		table_2 = new JTable(playerInfo, names_2);
		makeFace(table_2);
		scrollPane_1.setViewportView(table_2);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(6, 285, 343, 110);
		frame.getContentPane().add(scrollPane_2);

		String[] names_3 = {"No", "Id"};
		table_3 = new JTable(playerInfo, names_3);
		makeFace(table_3);
		scrollPane_2.setViewportView(table_3);
	}
}
