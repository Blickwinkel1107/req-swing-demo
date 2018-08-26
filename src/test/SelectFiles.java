package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectFiles {

	private JFrame frmSelectFilesPath;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

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
		btnSelect.setBounds(519, 183, 106, 25);
		frmSelectFilesPath.getContentPane().add(btnSelect);

		JButton button = new JButton("Select..");
		button.setBounds(519, 142, 106, 25);
		frmSelectFilesPath.getContentPane().add(button);

		JButton button_1 = new JButton("Select..");
		button_1.setBounds(519, 96, 106, 25);
		frmSelectFilesPath.getContentPane().add(button_1);
		frmSelectFilesPath.setVisible(true);	//显示窗口
	}
}
