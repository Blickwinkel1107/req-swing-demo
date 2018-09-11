package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ShowLog_1 {

	private JFrame frame;
	private static String logContent;
	private static ShowLog_1 window;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new ShowLog_1(logContent);
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
	public ShowLog_1(String logContent) {
		this.logContent = logContent;
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
		panel.setBounds(28, 23, 391, 227);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblLogInfo = new JLabel("Log Info");
		lblLogInfo.setBounds(164, 17, 61, 16);
		panel.add(lblLogInfo);

		JTextPane textPane = new JTextPane();
		textPane.setBounds(52, 45, 299, 126);
		panel.add(textPane);
		textPane.setText(this.logContent);
		//System.out.println(this.logContent);
		textPane.setEditable(false);

		JButton btnOk = new JButton("OK");
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
		btnOk.setBounds(140, 183, 117, 29);
		panel.add(btnOk);
	}

}
