package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

public class demoWin {

	private JFrame frame;
	private JTextField textField_1;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					demoWin window = new demoWin();
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
	public demoWin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(52, 43, 341, 36);
		frame.getContentPane().add(panel);

		JLabel lblUsername = new JLabel("username:");
		panel.add(lblUsername);

		textField_1 = new JTextField();
		panel.add(textField_1);
		textField_1.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(52, 91, 341, 36);
		frame.getContentPane().add(panel_1);

		JLabel lblPassword = new JLabel("password:");
		panel_1.add(lblPassword);

		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);

		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(155, 139, 117, 29);
		frame.getContentPane().add(btnLogin);
	}
}
