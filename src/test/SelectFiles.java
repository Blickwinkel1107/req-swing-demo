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
		lblEnterRequirementPath.setBounds(41, 158, 167, 15);

		textFieldReq = new JTextField();
		textFieldReq.setBounds(244, 156, 291, 19);
		textFieldReq.setColumns(10);

		btnReq = new JButton("Files..");
		btnReq.setBounds(541, 153, 77, 25);

		lblEnterOldVersion = new JLabel("Enter old version path");
		lblEnterOldVersion.setBounds(46, 198, 157, 15);

		textFieldOldVer = new JTextField();
		textFieldOldVer.setBounds(244, 196, 291, 19);
		textFieldOldVer.setColumns(10);

		btnChangeOld = new JButton("Files..");
		btnChangeOld.setBounds(541, 193, 77, 25);

		lblEnterNewVersion = new JLabel("Enter new version path");
		lblEnterNewVersion.setBounds(42, 234, 165, 15);

		textFieldNewVer = new JTextField();
		textFieldNewVer.setBounds(244, 232, 291, 19);
		textFieldNewVer.setColumns(10);

						btnChangeNew = new JButton("Files..");
						btnChangeNew.setBounds(541, 229, 77, 25);
						btnChangeNew.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
							}
						});

						JButton btnConfirm = new JButton("Confirm");
						btnConfirm.setBounds(345, 292, 88, 25);
						contentPane.setLayout(null);
						contentPane.add(lblEnterRequirementPath);
						contentPane.add(textFieldReq);
						contentPane.add(btnReq);
						contentPane.add(lblEnterOldVersion);
						contentPane.add(textFieldOldVer);
						contentPane.add(btnChangeOld);
						contentPane.add(lblEnterNewVersion);
						contentPane.add(textFieldNewVer);
						contentPane.add(btnChangeNew);
						contentPane.add(btnConfirm);
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
