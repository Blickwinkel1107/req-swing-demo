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
import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.event.MenuListener;

import com.sun.xml.internal.ws.api.Component;

import javax.swing.event.MenuEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuKeyEvent;

public class SelectFiles_Manager {

	private JFrame frmSelectFiles;
	private JTextField textField;
	private String path_req;
	private String username;
	private static String MANAGER = "manager";
	private JMenu mnSelectExistingProjects;

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void actionPerformed(ActionEvent e) {
        if(e.getSource().getClass().equals(JMenuItem.class)){
        	mnSelectExistingProjects.setText("����");
        }
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
		frmSelectFiles.setResizable(false);
		frmSelectFiles.setTitle("Select Files");
		frmSelectFiles.setBounds(100, 100, 489, 319);
		frmSelectFiles.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSelectFiles.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(22, 143, 438, 29);
		frmSelectFiles.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));

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
		panel_1.setBounds(138, 184, 204, 38);
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
						reqPath = "/Users/mac/Desktop/interfaceDemo/data/sample/AquaLush_Requirement";
						//reqPath = "E:\\Desktop\\Class\\Coding\\Java\\req-swing-demo\\data\\sample\\AquaLush_Requirement";
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
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(22, 76, 89, 25);
		frmSelectFiles.getContentPane().add(menuBar);
		
		mnSelectExistingProjects = new JMenu("Project name");
		menuBar.add(mnSelectExistingProjects);
		
		JMenuItem mntmAqualush = new JMenuItem("Aqualush");
		mnSelectExistingProjects.add(mntmAqualush);
		
		JMenuItem mntmItrust = new JMenuItem("iTrust");
		mnSelectExistingProjects.add(mntmItrust);
		
		JMenuItem mntmConnect = new JMenuItem("Connect");
		mnSelectExistingProjects.add(mntmConnect);
		
		for(int i = 0; i < mnSelectExistingProjects.getMenuComponentCount(); ++i) {
			JMenuItem item = mnSelectExistingProjects.getItem(i);
			item.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					mnSelectExistingProjects.setText(item.getText());
				}
			});
		}
		
		JLabel lblNewLabel = new JLabel("... or enter requirement path below");
		lblNewLabel.setBounds(22, 113, 221, 29);
		frmSelectFiles.getContentPane().add(lblNewLabel);
		
		JLabel lblSelectExistingProject = new JLabel("Select existing project");
		lblSelectExistingProject.setBounds(22, 35, 165, 29);
		frmSelectFiles.getContentPane().add(lblSelectExistingProject);
		frmSelectFiles.setVisible(true);
	}
}
