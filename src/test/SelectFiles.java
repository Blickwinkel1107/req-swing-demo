package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.CardLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JToggleButton;
import java.awt.Button;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Window.Type;

public class SelectFiles extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldReq;
	private JTextField textFieldOldVer;
	private JTextField textFieldNewVer;
	private JButton btnChangeNew;
	private JButton btnChangeOld;
	private JButton btnReq;
	private JLabel lblEnterNewVersion;
	private JLabel lblEnterOldVersion;
	private JLabel lblEnterRequirementPath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectFiles frame = new SelectFiles();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SelectFiles() {
		setTitle("Select Files Path");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 653, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		lblEnterRequirementPath = new JLabel("Enter requirement path");

		textFieldReq = new JTextField();
		textFieldReq.setColumns(10);

		btnReq = new JButton("Files..");

		lblEnterOldVersion = new JLabel("Enter old version path");

		textFieldOldVer = new JTextField();
		textFieldOldVer.setColumns(10);

		btnChangeOld = new JButton("Files..");

		lblEnterNewVersion = new JLabel("Enter new version path");

		textFieldNewVer = new JTextField();
		textFieldNewVer.setColumns(10);

						btnChangeNew = new JButton("Files..");
						btnChangeNew.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
							}
						});

						JButton btnConfirm = new JButton("Confirm");
						GroupLayout gl_contentPane = new GroupLayout(contentPane);
						gl_contentPane.setHorizontalGroup(
							gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(36)
									.addComponent(lblEnterRequirementPath)
									.addGap(36)
									.addComponent(textFieldReq, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(btnReq))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(41)
									.addComponent(lblEnterOldVersion)
									.addGap(41)
									.addComponent(textFieldOldVer, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(btnChangeOld))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(37)
									.addComponent(lblEnterNewVersion)
									.addGap(37)
									.addComponent(textFieldNewVer, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(btnChangeNew))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(340)
									.addComponent(btnConfirm))
						);
						gl_contentPane.setVerticalGroup(
							gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(148)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(5)
											.addComponent(lblEnterRequirementPath))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(3)
											.addComponent(textFieldReq, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addComponent(btnReq))
									.addGap(15)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(5)
											.addComponent(lblEnterOldVersion))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(3)
											.addComponent(textFieldOldVer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addComponent(btnChangeOld))
									.addGap(11)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(5)
											.addComponent(lblEnterNewVersion))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(3)
											.addComponent(textFieldNewVer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addComponent(btnChangeNew))
									.addGap(38)
									.addComponent(btnConfirm))
						);
						contentPane.setLayout(gl_contentPane);
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
