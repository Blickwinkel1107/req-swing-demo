package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login {

	private JFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private String username;
	private char[] password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
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

		usernameField = new JTextField();
		usernameField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//username = textField_1.getText();
			}
		});
		panel.add(usernameField);
		usernameField.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(52, 91, 341, 36);
		frame.getContentPane().add(panel_1);

		JLabel lblPassword = new JLabel("password:");
		panel_1.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				//password = passwordField.getText();
			}
		});
		passwordField.setColumns(10);
		panel_1.add(passwordField);

		JButton btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				username = usernameField.getText();
				password = passwordField.getPassword();
				System.out.println(username);
				System.out.println(password);
				//MaintainerWin window = new MaintainerWin();
				new SelectFiles();
				//frame.setVisible(false);
				frame.dispose();
			}
		});
		btnLogin.setBounds(155, 139, 117, 29);
		frame.getContentPane().add(btnLogin);

		JButton btnExit = new JButton("Exit");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
		btnExit.setBounds(155, 180, 117, 29);
		frame.getContentPane().add(btnExit);
	}
}