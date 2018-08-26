package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import test.MaintainerWin;
import test.ManagerWin;

public class SelectFiles {

	private JFrame frmSelectFilesPath;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	/*
	 * modified by YHR
	 */
	private String path_old;
	private String path_new;
	private String path_req;
	private String username;
	private char[] password;
	private static String MAINTAINER = "maintainer";
	private static String MANAGER = "manager";

	public void setUsername(String username){
		this.username = username;
	}

	public void setPassword(char[] password){
		this.password = password;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectFiles window = new SelectFiles();
					window.frmSelectFilesPath.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SelectFiles() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//System.out.println(this.username);
		//System.out.println(this.password);
		frmSelectFilesPath = new JFrame();
		frmSelectFilesPath.setTitle("Select files path");
		frmSelectFilesPath.setBounds(100, 100, 655, 442);
		frmSelectFilesPath.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSelectFilesPath.getContentPane().setLayout(null);

		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//frmSelectFilesPath.setVisible(false);
				/*
				 * modified by YHR
				 */
				System.out.println(username);
				System.out.println(password);
				if (username.equals(MAINTAINER) == true){
					System.out.println("fuck");
					MaintainerWin maintainer = new MaintainerWin();
					System.out.println("fuck");
					maintainer.setPath_old(path_old);
					maintainer.setPath_new(path_new);
					maintainer.setPath_req(path_req);
				}
				else if (username.equals(MANAGER) == true){
					ManagerWin manager = new ManagerWin();
					manager.setPath_old(path_old);
					manager.setPath_new(path_new);
					manager.setPath_req(path_req);
				}
				frmSelectFilesPath.dispose();
			}
		});
		btnConfirm.setBounds(269, 336, 117, 25);
		frmSelectFilesPath.getContentPane().add(btnConfirm);

		textField = new JTextField();
		textField.setBounds(118, 99, 390, 19);
		frmSelectFilesPath.getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(118, 145, 390, 19);
		frmSelectFilesPath.getContentPane().add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(118, 186, 390, 19);
		frmSelectFilesPath.getContentPane().add(textField_2);

		JLabel lblOldVersion = new JLabel("Old version");
		lblOldVersion.setBounds(12, 94, 100, 29);
		frmSelectFilesPath.getContentPane().add(lblOldVersion);

		JLabel lblNewVersion = new JLabel("New version");
		lblNewVersion.setBounds(12, 135, 100, 29);
		frmSelectFilesPath.getContentPane().add(lblNewVersion);

		JLabel lblRequirement = new JLabel("Requirement");
		lblRequirement.setBounds(12, 181, 100, 29);
		frmSelectFilesPath.getContentPane().add(lblRequirement);

		JButton btnSelect = new JButton("Select..");
		btnSelect.addMouseListener(new MouseAdapter() {
			@Override
			/*
			 * modified by YHR
			 */
			public void mouseClicked(MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser("/Users/mac/Desktop");
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fileChooser.showOpenDialog(fileChooser);
				if(returnVal == JFileChooser.APPROVE_OPTION){
				path_req = fileChooser.getSelectedFile().getAbsolutePath();//这个就是你选择的文件夹的路径
				System.out.println(path_req);
				textField_2.setText(path_req);
				}
			}
			});

		btnSelect.setBounds(519, 183, 106, 25);
		frmSelectFilesPath.getContentPane().add(btnSelect);

		JButton button = new JButton("Select..");
		button.addMouseListener(new MouseAdapter() {
			@Override
			/*
			 * modified by YHR
			 */
			public void mouseClicked(MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser("/Users/mac/Desktop");
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fileChooser.showOpenDialog(fileChooser);
				if(returnVal == JFileChooser.APPROVE_OPTION){
				path_new = fileChooser.getSelectedFile().getAbsolutePath();//这个就是你选择的文件夹的路径
				System.out.println(path_new);
				textField_1.setText(path_new);
				}
			}
		});
		button.setBounds(519, 142, 106, 25);
		frmSelectFilesPath.getContentPane().add(button);

		JButton button_1 = new JButton("Select..");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			/*
			 * modified by YHR
			 */
			public void mouseClicked(MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser("/Users/mac/Desktop");
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fileChooser.showOpenDialog(fileChooser);
				if(returnVal == JFileChooser.APPROVE_OPTION){
				path_old = fileChooser.getSelectedFile().getAbsolutePath();//这个就是你选择的文件夹的路径
				System.out.println(path_old);
				}
				textField.setText(path_old);
			}
		});
		button_1.setBounds(519, 96, 106, 25);
		frmSelectFilesPath.getContentPane().add(button_1);
		frmSelectFilesPath.setVisible(true);	//显示窗口
	}
}
