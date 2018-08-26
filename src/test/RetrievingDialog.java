package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import retro.Retro;

import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

public class RetrievingDialog {
	
	private String path_old;
	private String path_new;
	private String path_req;

	private JFrame frmRetrievingProcess;
	public boolean isFinish;
	public Retro retro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RetrievingDialog window = new RetrievingDialog();
					window.frmRetrievingProcess.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RetrievingDialog() {
		initialize();
	}
	
	public void show() {
		frmRetrievingProcess.setVisible(true);
	}
	
	public void hide() {
		frmRetrievingProcess.setVisible(false);
	}
	
	public void close() {
		frmRetrievingProcess.dispose();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.isFinish = false;
		frmRetrievingProcess = new JFrame();
		frmRetrievingProcess.setResizable(false);
		frmRetrievingProcess.setTitle("Retrieving process");
		frmRetrievingProcess.setBounds(100, 100, 286, 161);
		frmRetrievingProcess.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRetrievingProcess.getContentPane().setLayout(null);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setEnabled(false);
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmRetrievingProcess.dispose();
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancel.setBounds(177, 99, 93, 23);
		frmRetrievingProcess.getContentPane().add(btnCancel);
		
		JLabel lblRetrievingRequirements = new JLabel("Retrieving Requirements ...");
		lblRetrievingRequirements.setBounds(69, 30, 162, 16);
		frmRetrievingProcess.getContentPane().add(lblRetrievingRequirements);
		
		JLabel lblPleaseWait = new JLabel("Please wait");
		lblPleaseWait.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseWait.setBounds(79, 65, 128, 15);
		frmRetrievingProcess.getContentPane().add(lblPleaseWait);
		frmRetrievingProcess.setVisible(true);
	}
	

	
	
	protected void setPath_old(String path_old){
		this.path_old = path_old;
	}

	protected void setPath_new(String path_new){
		this.path_new = path_new;
	}

	protected void setPath_req(String path_req){
		this.path_req = path_req;
	}
}
