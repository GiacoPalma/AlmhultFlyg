package swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	public JFrame frame;
	private JTextField textFielduser;
	private JButton btnSkapaNyttKonto;
	private JButton btnLoggaIn;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
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
		
		JLabel lblNewLabel = new JLabel("Email:");
		lblNewLabel.setBounds(110, 96, 55, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblLsenord = new JLabel("Lösenord:");
		lblLsenord.setBounds(89, 139, 87, 15);
		frame.getContentPane().add(lblLsenord);
		
		textFielduser = new JTextField();
		textFielduser.setBounds(177, 88, 121, 32);
		frame.getContentPane().add(textFielduser);
		textFielduser.setColumns(10);
		
		btnLoggaIn = new JButton("Logga in");
		btnLoggaIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AirportSwing reload = new AirportSwing();
				Login.this.frame.dispose();
				reload.setVisible(true);
			}
		});
		btnLoggaIn.setBounds(157, 170, 156, 25);
		frame.getContentPane().add(btnLoggaIn);
		
		btnSkapaNyttKonto = new JButton("Skapa nytt konto");
		btnSkapaNyttKonto.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent arg0) {
				AddUser newWindow = new AddUser();
				Login.this.frame.dispose();
				newWindow.frame.setVisible(true);
			}
		});
		btnSkapaNyttKonto.setBounds(157, 203, 156, 25);
		frame.getContentPane().add(btnSkapaNyttKonto);
		
		JLabel lblLoggaIn = new JLabel("Logga in");
		lblLoggaIn.setFont(new Font("Dialog", Font.BOLD, 25));
		lblLoggaIn.setBounds(134, 12, 147, 58);
		frame.getContentPane().add(lblLoggaIn);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(177, 131, 121, 32);
		frame.getContentPane().add(passwordField);
	}

	public JButton getBtnSkapaNyttKonto() {
		return btnSkapaNyttKonto;
	}
	public JTextField getTextFielduser() {
		return textFielduser;
	}
	public JButton getBtnLoggaIn() {
		return btnLoggaIn;
	}
	public JPasswordField getPasswordField() {
		return passwordField;
	}
}
