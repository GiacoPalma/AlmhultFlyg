package swing;
import app.Database;
import app.User;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.Color;

public class Login {

	public JFrame frmAlmhultAirlinesFlygbokning;
	private JTextField textFielduser;
	private JButton btnSkapaNyttKonto;
	private JButton btnLoggaIn;
	private JPasswordField passwordField;
	public Database DB = new Database();
	private JLabel label;
	private JLabel lblVlkommenTillAlmhult;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmAlmhultAirlinesFlygbokning.setVisible(true);
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
		frmAlmhultAirlinesFlygbokning = new JFrame();
		frmAlmhultAirlinesFlygbokning.setTitle("Almhult Airlines Flygbokning v 1.0");
		frmAlmhultAirlinesFlygbokning.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Giaco\\Downloads\\plane.png"));
		frmAlmhultAirlinesFlygbokning.setBounds(100, 100, 769, 488);
		frmAlmhultAirlinesFlygbokning.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAlmhultAirlinesFlygbokning.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Email:");
		lblNewLabel.setBounds(249, 148, 55, 15);
		frmAlmhultAirlinesFlygbokning.getContentPane().add(lblNewLabel);
		
		JLabel lblLsenord = new JLabel("L\u00F6senord:");
		lblLsenord.setBounds(249, 191, 87, 15);
		frmAlmhultAirlinesFlygbokning.getContentPane().add(lblLsenord);
		
		textFielduser = new JTextField();
		textFielduser.setBounds(311, 139, 121, 32);
		frmAlmhultAirlinesFlygbokning.getContentPane().add(textFielduser);
		textFielduser.setColumns(10);
		
		btnLoggaIn = new JButton("Logga in");
		btnLoggaIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String passWord = new String(passwordField.getPassword()); 
				String user = new String(textFielduser.getText());
				User login = DB.loginUser(user, passWord);
				
				if(login != null) {
					if(login.admin_status == 1){
						AdminMenu reload = new AdminMenu();
						Login.this.frmAlmhultAirlinesFlygbokning.dispose();
						reload.setVisible(true);
					}
					if(login.admin_status == 0){
						UserMenu reload = new UserMenu(login);
						Login.this.frmAlmhultAirlinesFlygbokning.dispose();
						reload.setVisible(true);
					}
				} else if(user.length() == 0){
					JOptionPane.showMessageDialog(null, "Du har inte skrivit in något användarnamn eller lösenord");
				} else if(login == null && user.length() != 0) {
					JOptionPane.showMessageDialog(null, "Fel lösenord eller användarnamn");
				} 
			}
		});
		btnLoggaIn.setBounds(291, 232, 156, 25);
		frmAlmhultAirlinesFlygbokning.getContentPane().add(btnLoggaIn);
		
		btnSkapaNyttKonto = new JButton("Skapa nytt konto");
		btnSkapaNyttKonto.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent arg0) {
				AddUser newWindow = new AddUser();
				Login.this.frmAlmhultAirlinesFlygbokning.dispose();
				newWindow.frame.setVisible(true);
			}
		});
		btnSkapaNyttKonto.setBounds(291, 268, 156, 25);
		frmAlmhultAirlinesFlygbokning.getContentPane().add(btnSkapaNyttKonto);
		
		JLabel lblLoggaIn = new JLabel("Logga in");
		lblLoggaIn.setFont(new Font("Dialog", Font.BOLD, 25));
		lblLoggaIn.setBounds(323, 82, 147, 58);
		frmAlmhultAirlinesFlygbokning.getContentPane().add(lblLoggaIn);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(311, 182, 121, 32);
		frmAlmhultAirlinesFlygbokning.getContentPane().add(passwordField);
		
		JPanel panel = new JPanel();
		panel.setBounds(512, 103, 212, 336);
		frmAlmhultAirlinesFlygbokning.getContentPane().add(panel);
		
		label = new JLabel("");
		label.setBackground(Color.LIGHT_GRAY);
		label.setIcon(new ImageIcon(Login.class.getResource("/swing/plane.png")));
		label.setOpaque(true);
		panel.add(label);
		
		lblVlkommenTillAlmhult = new JLabel("V\u00E4lkommen till Almhult Airlines Flygbokning");
		lblVlkommenTillAlmhult.setFont(new Font("Dialog", Font.BOLD, 25));
		lblVlkommenTillAlmhult.setBounds(124, 39, 545, 32);
		frmAlmhultAirlinesFlygbokning.getContentPane().add(lblVlkommenTillAlmhult);
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
