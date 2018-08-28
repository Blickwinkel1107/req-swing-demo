package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;

import retro.Retro;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectFiles_Manager {

	private JFrame frame;
	private JTextField textField;
	private String path_req;
	private String username;
	private static String MANAGER = "manager";

	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectFiles_Manager window = new SelectFiles_Manager();
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
	public SelectFiles_Manager() {
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
		panel.setBounds(6, 98, 438, 29);
		frame.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Requirement");
		panel.add(lblNewLabel, BorderLayout.WEST);
		
		textField = new JTextField();
		panel.add(textField, BorderLayout.CENTER);
		textField.setColumns(10);
		
		JButton btnSelectFiles = new JButton("Select Files");
		btnSelectFiles.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser("/Users/mac/Desktop");
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fileChooser.showOpenDialog(fileChooser);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					path_req = fileChooser.getSelectedFile().getAbsolutePath();
					System.out.println(path_req);
					textField.setText(path_req);
				}
			}
		});
		panel.add(btnSelectFiles, BorderLayout.EAST);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(122, 166, 204, 29);
		frame.getContentPane().add(panel_1);
		
		JButton btnConfirm = new JButton("confirm");
		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(username);
				if (username.equals(MANAGER) == true) {
					System.out.println("enter ManagerWin");
					String reqPath;
					if (textField.getText().equals("")) {
						reqPath = "/Users/mac/Desktop/interfaceDemo/data/sample/AquaLush_Requirement";
					} else {
						reqPath = textField.getText();
					}
					ManagerWin manager = new ManagerWin();
					manager.setPath_req(reqPath);
				} 
				frame.dispose();
			}
		});
		panel_1.add(btnConfirm);
		frame.setVisible(true);
	}
}
