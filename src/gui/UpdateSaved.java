package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UpdateSaved {

	private JFrame frmNotice;
	private boolean notNull;

	/**
	 * Create the application.
	 */
	public UpdateSaved(boolean notEmpty) {
		this.notNull = notEmpty;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNotice = new JFrame();
		frmNotice.setTitle("Notice");
		frmNotice.setAlwaysOnTop(true);
		frmNotice.setResizable(false);
		frmNotice.setBounds(100, 100, 253, 155);
		frmNotice.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmNotice.getContentPane().setLayout(null);
		
		JLabel lblUpdateSuccessfully;
		if(notNull)
			lblUpdateSuccessfully = new JLabel("Update Successfully!");
		else
			lblUpdateSuccessfully = new JLabel("Empty not allowed!");
		lblUpdateSuccessfully.setFont(new Font("Arial", Font.PLAIN, 13));
		lblUpdateSuccessfully.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdateSuccessfully.setBounds(32, 38, 180, 35);
		frmNotice.getContentPane().add(lblUpdateSuccessfully);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmNotice.dispose();
			}
		});
		btnReturn.setBounds(79, 93, 93, 23);
		frmNotice.getContentPane().add(btnReturn);
		frmNotice.setVisible(true);
	}
}
