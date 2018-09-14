package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import gui.UpdateSaved;
import sql.SqlExecuter;

public class UpdateLog {

	private JFrame frame;
	public String reqName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateLog window = new UpdateLog("");
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
	public UpdateLog(String reqName) {
		this.reqName = reqName;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		JPanel panel = new JPanel();
		panel.setBounds(26, 22, 402, 223);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblUpdateLog = new JLabel("Update Log");
		lblUpdateLog.setBounds(162, 6, 92, 16);
		panel.add(lblUpdateLog);

		JTextPane textPane = new JTextPane();
		textPane.setBounds(39, 36, 346, 133);
		panel.add(textPane);

		JButton btnSave = new JButton("Save");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String content = textPane.getText();
				System.out.println(content);
				if(content.trim().equals(""))
					new UpdateSaved(false);
				else {
					new UpdateSaved(true);
					String sql = "UPDATE reqList SET pending = pending + 1 WHERE id = \'" + reqName + "\';";
					SqlExecuter.process(sql);
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					sql = "INSERT INTO logList VALUES(\'" + reqName + "\', \'" + df.format(new Date()) + "\', \'"
							+ Login.getUserName() + "\', \'" + content + "\', 'Unchecked')";
					SqlExecuter.process(sql);
					frame.dispose();
				}
			}
		});
		btnSave.setBounds(49, 188, 117, 29);
		panel.add(btnSave);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
		btnCancel.setBounds(268, 188, 117, 29);
		panel.add(btnCancel);
	}

}
