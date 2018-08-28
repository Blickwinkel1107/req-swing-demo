package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class SelectFiles_Manager {

	private JFrame frmSelectFiles;
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
					window.frmSelectFiles.setVisible(true);
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
		frmSelectFiles = new JFrame();
		frmSelectFiles.setTitle("Select Files");
		frmSelectFiles.setBounds(100, 100, 468, 300);
		frmSelectFiles.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSelectFiles.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(6, 98, 438, 29);
		frmSelectFiles.getContentPane().add(panel);
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
		panel_1.setBounds(122, 166, 204, 38);
		frmSelectFiles.getContentPane().add(panel_1);

		JButton btnConfirm = new JButton("confirm");
		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(username);
				if (username.equals(MANAGER) == true) {
					System.out.println("enter ManagerWin");
					String reqPath;
					if (textField.getText().equals("")) {
						//reqPath = "/Users/mac/Desktop/interfaceDemo/data/sample/AquaLush_Requirement";
						reqPath = "E:\\Desktop\\Class\\Coding\\Java\\req-swing-demo\\data\\sample\\AquaLush_Requirement";
					} else {
						reqPath = textField.getText();
					}
					ManagerWin manager;
					try {
						manager = new ManagerWin(reqPath);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				frmSelectFiles.dispose();
			}
		});
		panel_1.add(btnConfirm);
		frmSelectFiles.setVisible(true);
	}
}
