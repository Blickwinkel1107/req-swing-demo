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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{97, 234, 68, 0, 0};
		gbl_contentPane.rowHeights = new int[]{79, 39, 42, 35, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		lblEnterRequirementPath = new JLabel("Enter requirement path");
		GridBagConstraints gbc_lblEnterRequirementPath = new GridBagConstraints();
		gbc_lblEnterRequirementPath.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterRequirementPath.anchor = GridBagConstraints.EAST;
		gbc_lblEnterRequirementPath.gridx = 0;
		gbc_lblEnterRequirementPath.gridy = 1;
		contentPane.add(lblEnterRequirementPath, gbc_lblEnterRequirementPath);

		textFieldReq = new JTextField();
		GridBagConstraints gbc_textFieldReq = new GridBagConstraints();
		gbc_textFieldReq.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldReq.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldReq.gridx = 1;
		gbc_textFieldReq.gridy = 1;
		contentPane.add(textFieldReq, gbc_textFieldReq);
		textFieldReq.setColumns(10);

		btnReq = new JButton("Files..");
		GridBagConstraints gbc_btnReq = new GridBagConstraints();
		gbc_btnReq.insets = new Insets(0, 0, 5, 5);
		gbc_btnReq.gridx = 2;
		gbc_btnReq.gridy = 1;
		contentPane.add(btnReq, gbc_btnReq);

		lblEnterOldVersion = new JLabel("Enter old version path");
		GridBagConstraints gbc_lblEnterOldVersion = new GridBagConstraints();
		gbc_lblEnterOldVersion.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterOldVersion.anchor = GridBagConstraints.EAST;
		gbc_lblEnterOldVersion.gridx = 0;
		gbc_lblEnterOldVersion.gridy = 2;
		contentPane.add(lblEnterOldVersion, gbc_lblEnterOldVersion);

		textFieldOldVer = new JTextField();
		GridBagConstraints gbc_textFieldOldVer = new GridBagConstraints();
		gbc_textFieldOldVer.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldOldVer.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldOldVer.gridx = 1;
		gbc_textFieldOldVer.gridy = 2;
		contentPane.add(textFieldOldVer, gbc_textFieldOldVer);
		textFieldOldVer.setColumns(10);

		btnChangeOld = new JButton("Files..");
		GridBagConstraints gbc_btnChangeOld = new GridBagConstraints();
		gbc_btnChangeOld.insets = new Insets(0, 0, 5, 5);
		gbc_btnChangeOld.gridx = 2;
		gbc_btnChangeOld.gridy = 2;
		contentPane.add(btnChangeOld, gbc_btnChangeOld);

		lblEnterNewVersion = new JLabel("Enter new version path");
		GridBagConstraints gbc_lblEnterNewVersion = new GridBagConstraints();
		gbc_lblEnterNewVersion.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterNewVersion.anchor = GridBagConstraints.EAST;
		gbc_lblEnterNewVersion.gridx = 0;
		gbc_lblEnterNewVersion.gridy = 3;
		contentPane.add(lblEnterNewVersion, gbc_lblEnterNewVersion);

		textFieldNewVer = new JTextField();
		GridBagConstraints gbc_textFieldNewVer = new GridBagConstraints();
		gbc_textFieldNewVer.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNewVer.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNewVer.gridx = 1;
		gbc_textFieldNewVer.gridy = 3;
		contentPane.add(textFieldNewVer, gbc_textFieldNewVer);
		textFieldNewVer.setColumns(10);

						btnChangeNew = new JButton("Files..");
						btnChangeNew.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
							}
						});
						GridBagConstraints gbc_btnChangeNew = new GridBagConstraints();
						gbc_btnChangeNew.insets = new Insets(0, 0, 5, 5);
						gbc_btnChangeNew.gridx = 2;
						gbc_btnChangeNew.gridy = 3;
						contentPane.add(btnChangeNew, gbc_btnChangeNew);

						JButton btnConfirm = new JButton("Confirm");
						GridBagConstraints gbc_btnConfirm = new GridBagConstraints();
						gbc_btnConfirm.insets = new Insets(0, 0, 5, 5);
						gbc_btnConfirm.gridx = 1;
						gbc_btnConfirm.gridy = 5;
						contentPane.add(btnConfirm, gbc_btnConfirm);
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
